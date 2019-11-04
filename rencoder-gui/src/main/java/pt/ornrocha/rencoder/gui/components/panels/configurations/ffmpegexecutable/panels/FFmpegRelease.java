package pt.ornrocha.rencoder.gui.components.panels.configurations.ffmpegexecutable.panels;

import javax.swing.JPanel;

public abstract class FFmpegRelease extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FFmpegRelease() {
		initGUI();
	}

	public abstract void initGUI();

	public abstract String getURLSelectedRelease();

}
