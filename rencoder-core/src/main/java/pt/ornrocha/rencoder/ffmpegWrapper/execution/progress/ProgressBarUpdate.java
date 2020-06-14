/*
 * Copyright 2014
 *
 * This is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. 
 * 
 * This code is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the 
 * GNU Public License for more details. 
 * 
 * You should have received a copy of the GNU Public License 
 * along with this code. If not, see http://www.gnu.org/licenses/ 
 * 
 * Created by Orlando Rocha
 */
package pt.ornrocha.rencoder.ffmpegWrapper.execution.progress;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingWorker;

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.utilities.FFmpegUtils;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.helpers.osystem.OSystem;

// TODO: Auto-generated Javadoc
/**
 * The Class ProgressBarUpdate.
 */
public class ProgressBarUpdate extends SwingWorker<Integer, String> {

	public static String PROGRESSDOUBLE = "progressdouble";
	public static String PROGRESSMSG = "progresserrormsg";
	private static String builtfontstag = OSystem.getCurrentDir() + OSystem.getSystemSeparator() + "ffmpegfolder"
			+ OSystem.getSystemSeparator() + ".lockfonts";

	/** The sc. */
	Scanner sctime = null;


	/** The tmodel. */
	// FFmpegProgressTable tmodel=null;

	/** The pos. */
	int pos = 0;

	/** The twopassenc. */
	boolean twopassenc = false;

	boolean passenctwostarter = false;
	boolean writelog=false;

	/**
	 * Instantiates a new progress bar update.
	 */
	public ProgressBarUpdate() {
	}

	public ProgressBarUpdate addPosition(int pos) {
		this.pos = pos;
		return this;
	}

	/**
	 * Sets the input stream.
	 *
	 * @param input the new input stream
	 */
	public void setInputStream(InputStream input) {
		this.sctime = new Scanner(input);

	}
	
	public void writeLog(boolean write) {
		this.writelog=write;
	}

	/**
	 * Sets the error flag in process.
	 */
	public void setErrorFlagInProcess() {
		setStringMsg(LangTools.getWordLanguage("Error", "progbar.error"));
	}

	/**
	 * Sets the cancel flag in process.
	 */
	public void setCancelFlagInProcess() {
		setStringMsg(LangTools.getWordLanguage("Canceled", "progbar.canceled"));
	}

	/**
	 * Set2 pass flag in process.
	 */
	public void set2PassFlagInProcess() {
		this.twopassenc = true;
	}

	private void setProgressDouble(double newval) {
		getPropertyChangeSupport().firePropertyChange(PROGRESSDOUBLE, this.pos, newval);
	}

	private void setStringMsg(String msg) {
		getPropertyChangeSupport().firePropertyChange(PROGRESSMSG, this.pos, msg);
	}

	private void checkPassingTwo(double movietimeprogress) {

		if (movietimeprogress > 99.999 && !passenctwostarter) {
			passenctwostarter = true;
			setStringMsg(LangTools.getWordLanguage("Starting pass 2", "progbar.start2pass"));
		} else if (movietimeprogress > 99.5 && !passenctwostarter) {
			setStringMsg(LangTools.getWordLanguage("Starting pass 2", "progbar.start2pass"));
		} else
			setProgressDouble(movietimeprogress);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.SwingWorker#doInBackground()
	 */
	@Override
	protected Integer doInBackground() throws Exception {

		double movietimeprogress = 0.0;
		int current = 0;

		if (OSystem.isWindows()) {
			File check = new File(builtfontstag);
			if (!check.exists()) {
				try {
					check.createNewFile();
				} catch (Exception e) {
					Logger.error(e);
				}
				setStringMsg(LangTools.getWordLanguage("Building Fonts", "progbar.buildingfonts"));
			}
		}

		// setProgressDouble(movietimeprogress);

		Pattern durPattern = Pattern.compile("(?<=Duration: )[^,]*");
		String dur = sctime.findWithinHorizon(durPattern, 0);
		if (dur == null) {
			setStringMsg("Error...time could not be parsed.");
			Logger.error("Movie length analysis could not be performed.");
			throw new RuntimeException("Time could not be parsed.");

		}

		double totalSecs = FFmpegUtils.convertTimetoseconds(dur);

		Pattern timePattern = Pattern.compile("(?<=time=)[\\d.:]*");
		//Matcher matcher = timePattern.matcher(EXAMPLE_TEST);
		String match;

		while (sctime.hasNextLine()) {
			//while (null != (match = sctime.findWithinHorizon(timePattern, 0))) {
			String currentline = sctime.nextLine();
			
			
			//match = sctime.findWithinHorizon(timePattern, 0);
			if(writelog)
				Logger.info(currentline);
		
            Matcher matcher = timePattern.matcher(currentline);
            
            if(matcher.find()) {
            	double currtime = FFmpegUtils.convertTimetoseconds(matcher.group());
            	movietimeprogress = currtime / totalSecs;

            	movietimeprogress = movietimeprogress * 100;

            	if (twopassenc) {
            		checkPassingTwo(movietimeprogress);
            	} else {
            		setProgressDouble(movietimeprogress);
            	}
            }

		}

		if (!twopassenc)
			setProgressDouble(100);
		else if (twopassenc && passenctwostarter)
			setProgressDouble(100);

		return current;
	}

}
