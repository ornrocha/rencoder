package pt.ornrocha.rencoder.gui.components.panels.configurations.ffmpegexecutable.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;

public class FFmpegWindowsRelease extends FFmpegRelease implements ActionListener {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JCheckBox chckbxlatestzeranoe;
	JCheckBox chckbxstablezeranoe;
	String release="https://ffmpeg.zeranoe.com/builds/win64/static/ffmpeg-latest-win64-static.zip";
	
	private static String latestzeranoe="latestzeranoe";
	private static String release413="release413";
	private JButton buttoninfostable;

	public FFmpegWindowsRelease() {}

	@Override
	public void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 1, 1};
		gridBagLayout.rowHeights = new int[] { 1, 1, 1 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.2};
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0 };
		setLayout(gridBagLayout);

		chckbxlatestzeranoe = new JCheckBox("Latest release from ffmpeg.zeranoe.com");
		chckbxlatestzeranoe.setSelected(true);
		chckbxlatestzeranoe.setActionCommand(latestzeranoe);
		chckbxlatestzeranoe.addActionListener(this);
		GridBagConstraints gbc_chckbxlatestzeranoe = new GridBagConstraints();
		gbc_chckbxlatestzeranoe.anchor = GridBagConstraints.WEST;
		gbc_chckbxlatestzeranoe.insets = new Insets(0, 20, 5, 5);
		gbc_chckbxlatestzeranoe.gridx = 0;
		gbc_chckbxlatestzeranoe.gridy = 0;
		add(chckbxlatestzeranoe, gbc_chckbxlatestzeranoe);
		
		JButton btnInfolatest = new JButton("info");
		GridBagConstraints gbc_btnInfolatest = new GridBagConstraints();
		gbc_btnInfolatest.insets = new Insets(0, 0, 5, 0);
		gbc_btnInfolatest.gridx = 1;
		gbc_btnInfolatest.gridy = 0;
		//add(btnInfolatest, gbc_btnInfolatest);
		
		chckbxstablezeranoe = new JCheckBox("Stable release 4.1.3 from ffmpeg.zeranoe.com");
		chckbxstablezeranoe.setSelected(false);
		chckbxstablezeranoe.setActionCommand(release413);
		chckbxstablezeranoe.addActionListener(this);
		GridBagConstraints gbc_chckbxstablezeranoe = new GridBagConstraints();
		gbc_chckbxstablezeranoe.anchor = GridBagConstraints.WEST;
		gbc_chckbxstablezeranoe.insets = new Insets(0, 20, 5, 5);
		gbc_chckbxstablezeranoe.gridx = 0;
		gbc_chckbxstablezeranoe.gridy = 1;
		add(chckbxstablezeranoe, gbc_chckbxstablezeranoe);
		
		buttoninfostable = new JButton("info");
		GridBagConstraints gbc_buttoninfostable = new GridBagConstraints();
		gbc_buttoninfostable.insets = new Insets(0, 0, 5, 0);
		gbc_buttoninfostable.gridx = 1;
		gbc_buttoninfostable.gridy = 1;
		//add(buttoninfostable, gbc_buttoninfostable);
	}

	@Override
	public String getURLSelectedRelease() {
		return release;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		
		if(cmd.equals(latestzeranoe)) {
			chckbxstablezeranoe.setSelected(false);
			release="https://ffmpeg.zeranoe.com/builds/win64/static/ffmpeg-latest-win64-static.zip";
		}
		else if(cmd.equals(release413)) {
			chckbxlatestzeranoe.setSelected(false);
			release="https://ffmpeg.zeranoe.com/builds/win64/static/ffmpeg-4.1.3-win64-static.zip";
		}

	}

}
