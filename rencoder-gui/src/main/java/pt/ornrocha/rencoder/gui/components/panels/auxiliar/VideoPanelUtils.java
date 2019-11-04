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
package pt.ornrocha.rencoder.gui.components.panels.auxiliar;

import java.awt.Component;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.SwingWorker;

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioCBRValues;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCBRValues;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoFileSize;
import pt.ornrocha.rencoder.gui.components.panels.info.SimpleTextMSGPanel;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.mediafiles.setfiles.processfiles.ProcessFiles;

// TODO: Auto-generated Javadoc
/**
 * The Class VideoPanelUtils.
 */
public class VideoPanelUtils {

	/**
	 * Convert video cbr values to array string.
	 *
	 * @return the string[]
	 */
	public static String[] convertVideoCBRValuesToArrayString() {

		String[] cbrnames = new String[VideoCBRValues.values().length];
		int n = 0;
		for (VideoCBRValues v : VideoCBRValues.values()) {
			cbrnames[n] = v.toString();
			n++;
		}
		return cbrnames;
	}

	/**
	 * Convert audio cbr values to array string.
	 *
	 * @return the string[]
	 */
	public static String[] convertAudioCBRValuesToArrayString() {

		String[] cbrnames = new String[AudioCBRValues.values().length];
		int n = 0;
		for (AudioCBRValues v : AudioCBRValues.values()) {
			cbrnames[n] = v.toString();
			n++;
		}

		return cbrnames;
	}

	/**
	 * Convert file size enum values to array string.
	 *
	 * @return the string[]
	 */
	public static String[] convertFileSizeEnumValuesToArrayString() {

		String[] filesizestr = new String[VideoFileSize.values().length];
		int n = 0;
		for (VideoFileSize f : VideoFileSize.values()) {
			filesizestr[n] = f.toString();
			n++;
		}
		return filesizestr;
	}

	/**
	 * Element of video cbr values.
	 *
	 * @param s the s
	 * @return true, if successful
	 */
	public static boolean elementOfVideoCBRValues(String s) {

		for (VideoCBRValues v : VideoCBRValues.values()) {
			if (v.toString().equals(s))
				return true;
		}

		return false;
	}

	/**
	 * Element of audio cbr values.
	 *
	 * @param s the s
	 * @return true, if successful
	 */
	public static boolean elementOfAudioCBRValues(String s) {

		for (AudioCBRValues b : AudioCBRValues.values()) {
			if (b.toString().equals(s))
				return true;
		}

		return false;
	}

	/**
	 * Index of cbr values.
	 *
	 * @param s     the s
	 * @param model the model
	 * @return the int
	 */
	public static int indexOfCBRValues(String s, BitrateComboBoxModel model) {

		int index = -1;
		int sizeelements = model.getSize();

		for (int i = 0; i < sizeelements; i++) {
			if (model.getElementAt(i).equals(s))
				index = i;
		}

		return index;
	}

	/**
	 * Index of file size values.
	 *
	 * @param s     the s
	 * @param model the model
	 * @return the int
	 */
	public static int indexOfFileSizeValues(String s, FileSizeComboBoxModel model) {

		int index = -1;
		int sizeelements = model.getSize();

		for (int i = 0; i < sizeelements; i++) {
			if (model.getElementAt(i).equals(s))
				index = i;
		}

		return index;
	}

	// from
	// http://stackoverflow.com/questions/602032/getting-java-gui-to-open-a-webpage-in-web-browser
	public static void openInBrowser(String url) {
		try {
			URI uri = new URL(url).toURI();
			Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
			if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE))
				desktop.browse(uri);
		} catch (Exception e) {
			Logger.error(e);
		}
	}

	public static SimpleTextMSGPanel getImportFilesWarningPanel(ArrayList<String> files, Component comp) {
		SimpleTextMSGPanel panel = null;
		if (files.size() >= 50) {
			String msg = "Importing files, please wait...";
			panel = new SimpleTextMSGPanel(LangTools.getWordLanguage(msg, "files.importfiles"), comp);
		}

		return panel;

	}

	public static void RunProcessFilesInBackground(final SimpleTextMSGPanel panel, final ProcessFiles proc) {

		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() {
				try {
					proc.processVideoFiles();
				} catch (CloneNotSupportedException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				while (proc.isExecuting()) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						Logger.error(e);
					}
				}
				return null;
			}

			@Override
			protected void done() {
				panel.dispose();
			}
		};
		worker.execute();
		panel.setVisible(true);
	}

}
