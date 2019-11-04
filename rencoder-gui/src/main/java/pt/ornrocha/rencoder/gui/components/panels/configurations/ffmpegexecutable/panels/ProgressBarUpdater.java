package pt.ornrocha.rencoder.gui.components.panels.configurations.ffmpegexecutable.panels;

import java.io.File;

import javax.swing.JProgressBar;

import org.kamranzafar.jddl.DownloadListener;

public class ProgressBarUpdater implements DownloadListener{

	private static final String DONE = "Done";
	JProgressBar progressBar;
	int size = -1;
	String fname;

	public ProgressBarUpdater(String fname, JProgressBar progressBar) {
		this.progressBar = progressBar;
		this.progressBar.revalidate();
		this.progressBar.repaint();
		this.progressBar.setStringPainted(true);
		
	}

	@Override
	public void onCancel() {
		new File(fname).delete();
	}

	@Override
	public void onComplete() {
		if (size == -1) {
			progressBar.setIndeterminate(false);
			progressBar.setValue(100);
		}

	}

	@Override
	public void onStart(String arg0, int fsize) {
		if (fsize > -1) {
			progressBar.setMaximum(fsize);
			size = fsize;
		} else {
			progressBar.setIndeterminate(true);
		}

	}

	@Override
	public void onUpdate(int arg0, int totalDownloaded) {
		if (size == -1) {
			progressBar.setString("" + totalDownloaded);
		} else {
			progressBar.setValue(totalDownloaded);
		}

	}

}
