package pt.ornrocha.rencoder.ffmpegWrapper.execution.progress;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import org.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.commands.FFmpegInfoPatterns;
import pt.ornrocha.rencoder.ffmpegWrapper.commands.ReusableInputStream;

public class MergeProgressBarUpdater implements Runnable {

	private PropertyChangeSupport changelst = new PropertyChangeSupport(this);
	private Scanner sc = null;
	private ReusableInputStream reusinput = null;
	private int currentproc = 0;
	private int totalproc = 0;
	private JProgressBar progressBar;

	public static String NUMBEROFMERGEPROCESS = "numbermergeprocess";
	public static String FINISHEDMERGE = "mergefinished";

	public MergeProgressBarUpdater(JProgressBar progressBar, int totalproc, PropertyChangeListener listener) {
		this.progressBar = progressBar;
		this.totalproc = totalproc;
		this.changelst.addPropertyChangeListener(listener);
	}

	public MergeProgressBarUpdater() {

	}

	public void setTotalProcesses(int n) {
		this.totalproc = n;
	}

	public void setInputStream(InputStream input) {
		try {
			this.reusinput = new ReusableInputStream(input);
		} catch (IOException e) {
			Logger.error(e);
		}
		this.sc = new Scanner(reusinput);

	}

	private void setMergingProcessFireEvents(int oldval, int newval) {
		changelst.firePropertyChange(NUMBEROFMERGEPROCESS, oldval, newval);
	}

	private void setFinalProcessFireEvents() {
		changelst.firePropertyChange(FINISHEDMERGE, false, true);
	}

	private float getProgressBarValue(int n) {
		return ((float) n / totalproc) * 100;
	}

	private void updateBar(final int value) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				progressBar.setValue(value);

			}
		});
	}

	@Override
	public void run() {

		int procnumb = 0;

		while (sc.hasNextLine()) {
			String str = sc.nextLine();

			procnumb = FFmpegInfoPatterns.getMergeFileNumber(str);
			if (procnumb != currentproc && procnumb > currentproc) {
				setMergingProcessFireEvents(currentproc, procnumb);
				currentproc = procnumb;
				updateBar((int) getProgressBarValue(currentproc));

			}

			Logger.debug("Merge ffmpeg info: " + str);

		}

		updateBar(100);
		setFinalProcessFireEvents();
	}

}
