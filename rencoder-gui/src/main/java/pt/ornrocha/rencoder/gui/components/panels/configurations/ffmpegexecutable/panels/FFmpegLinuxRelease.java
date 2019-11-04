package pt.ornrocha.rencoder.gui.components.panels.configurations.ffmpegexecutable.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;

public class FFmpegLinuxRelease extends FFmpegRelease implements ActionListener {


    private static final long serialVersionUID = 1L;
    private JCheckBox chckbxreleasejohn;
    private JCheckBox chckbxgitjohn;
    private JCheckBox chckbxlatestrencoder;
    private JButton buttoninforencoder;
    private JButton buttongitjohn;


    private static String LATESTRELEASEJOHN="latestreleasejohn";
    private static String LATESTGITJOHN="latestgitjohn";
    private static String LATESTRENCODER="latestorsvc";

    String release="https://johnvansickle.com/ffmpeg/releases/ffmpeg-release-amd64-static.tar.xz";


    public FFmpegLinuxRelease() {}

    @Override
    public void initGUI() {
	GridBagLayout gridBagLayout = new GridBagLayout();
	gridBagLayout.columnWidths = new int[] { 1, 1};
	gridBagLayout.rowHeights = new int[] { 1, 1, 1 };
	gridBagLayout.columnWeights = new double[] { 1.0, 0.2};
	gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0 };
	setLayout(gridBagLayout);

	chckbxgitjohn = new JCheckBox("Latest git release from johnvansickle.com");
	chckbxgitjohn.setSelected(false);
	chckbxgitjohn.setActionCommand(LATESTGITJOHN);
	chckbxgitjohn.addActionListener(this);

	chckbxreleasejohn = new JCheckBox("Latest stable release from johnvansickle.com");
	chckbxreleasejohn.setSelected(true);
	chckbxreleasejohn.setActionCommand(LATESTRELEASEJOHN);
	chckbxreleasejohn.addActionListener(this);

	chckbxlatestrencoder = new JCheckBox("Latest release from Rencoder");
	chckbxlatestrencoder.setSelected(false);
	chckbxlatestrencoder.setActionCommand(LATESTRENCODER);
	chckbxlatestrencoder.addActionListener(this);
	GridBagConstraints gbc_chckbxlatestorsvc = new GridBagConstraints();
	gbc_chckbxlatestorsvc.anchor = GridBagConstraints.WEST;
	gbc_chckbxlatestorsvc.insets = new Insets(0, 20, 5, 5);
	gbc_chckbxlatestorsvc.gridx = 0;
	gbc_chckbxlatestorsvc.gridy = 0;
	add(chckbxlatestrencoder, gbc_chckbxlatestorsvc);

	buttoninforencoder = new JButton("info");
	GridBagConstraints gbc_buttoninfoorsvc = new GridBagConstraints();
	gbc_buttoninfoorsvc.insets = new Insets(0, 0, 5, 0);
	gbc_buttoninfoorsvc.gridx = 1;
	gbc_buttoninfoorsvc.gridy = 0;
	//add(buttoninfoorsvc, gbc_buttoninfoorsvc);
	GridBagConstraints gbc_chckbxreleasejohn = new GridBagConstraints();
	gbc_chckbxreleasejohn.anchor = GridBagConstraints.WEST;
	gbc_chckbxreleasejohn.insets = new Insets(0, 20, 5, 5);
	gbc_chckbxreleasejohn.gridx = 0;
	gbc_chckbxreleasejohn.gridy = 1;
	add(chckbxreleasejohn, gbc_chckbxreleasejohn);

	JButton btnInforeleasejohn = new JButton("info");
	GridBagConstraints gbc_btnInforeleasejohn = new GridBagConstraints();
	gbc_btnInforeleasejohn.insets = new Insets(0, 0, 5, 0);
	gbc_btnInforeleasejohn.gridx = 1;
	gbc_btnInforeleasejohn.gridy = 1;
	//add(btnInforeleasejohn, gbc_btnInforeleasejohn);
	GridBagConstraints gbc_chckbxgitjohn = new GridBagConstraints();
	gbc_chckbxgitjohn.anchor = GridBagConstraints.WEST;
	gbc_chckbxgitjohn.insets = new Insets(0, 20, 0, 5);
	gbc_chckbxgitjohn.gridx = 0;
	gbc_chckbxgitjohn.gridy = 2;
	add(chckbxgitjohn, gbc_chckbxgitjohn);

	buttongitjohn = new JButton("info");
	GridBagConstraints gbc_buttongitjohn = new GridBagConstraints();
	gbc_buttongitjohn.gridx = 1;
	gbc_buttongitjohn.gridy = 2;
	//add(buttongitjohn, gbc_buttongitjohn);
    }

    @Override
    public String getURLSelectedRelease() {
	return release;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	String cmd=e.getActionCommand();

	if(cmd.equals(LATESTRELEASEJOHN)) {
	    chckbxgitjohn.setSelected(false);
	    chckbxlatestrencoder.setSelected(false);
	    release="https://johnvansickle.com/ffmpeg/releases/ffmpeg-release-amd64-static.tar.xz";
	}
	else if(cmd.equals(LATESTRENCODER)) {
	    chckbxreleasejohn.setSelected(false);
	    chckbxgitjohn.setSelected(false);
	    release="https://github.com/ornrocha/rencoder-ffmpeg/raw/master/ffmpeg_rencoder.tar.gz";
	}
	else if(cmd.equals(LATESTGITJOHN)) {
	    chckbxreleasejohn.setSelected(false);
	    chckbxlatestrencoder.setSelected(false);
	    release="https://johnvansickle.com/ffmpeg/builds/ffmpeg-git-amd64-static.tar.xz";
	}

    }

}
