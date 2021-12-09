package pt.ornrocha.rencoder.gui.components.panels.configurations.ffmpegexecutable.panels;

import javax.swing.JPanel;

import pt.ornrocha.rencoder.ffmpegWrapper.releases.containers.FFmpegReleaseOsType;

public abstract class FFmpegRelease extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected FFmpegReleaseOsType ostype;
	

	public FFmpegRelease(FFmpegReleaseOsType ostype) {
		this.ostype=ostype;
		initGUI();
	}

	public abstract void initGUI();
	

	public abstract String getURLSelectedRelease();

}
