package pt.ornrocha.rencoder.gui.components.panels.Scroll;

import javax.swing.JPanel;

import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.mediafiles.files.containers.streams.AudioStreamInfo;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.JCheckBox;

public class AudioInfoPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox comboBox;
	private MediaInfoScrollPanel infopanel;
	private ResourceBundle rb;
	
	
	private ArrayList<AudioStreamInfo> audiostreams;
	private int startstream=1;
	private JCheckBox chckbxDefaultStream;
	
	private String CHANGEAUDIOSTREAM="CHANGEAUDIOSTREAM";
	private String DEFAULTAUDIOSTREAM="DEFAULTAUDIOSTREAM";

	/**
	 * Create the panel.
	 */
	public AudioInfoPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1};
		gridBagLayout.rowHeights = new int[]{1,1};
		gridBagLayout.columnWeights = new double[]{0.5,0.0};
		gridBagLayout.rowWeights = new double[]{0.2,1.0};
		setLayout(gridBagLayout);
		
		comboBox = new JComboBox();
		comboBox.setActionCommand(CHANGEAUDIOSTREAM);
		comboBox.addActionListener(this);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 0;
		add(comboBox, gbc_comboBox);
		
		String[] audiocolnames={LangTools.getResourceBundleWordLanguage(rb, "Parameter","videoaudiogui.parameter"),LangTools.getResourceBundleWordLanguage(rb, "Value","videoaudiogui.value")};
		
		chckbxDefaultStream = new JCheckBox("Make default Stream");
		chckbxDefaultStream.setActionCommand(DEFAULTAUDIOSTREAM);
		chckbxDefaultStream.addActionListener(this);
		GridBagConstraints gbc_chckbxDefaultStream = new GridBagConstraints();
		gbc_chckbxDefaultStream.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxDefaultStream.gridx = 1;
		gbc_chckbxDefaultStream.gridy = 0;
		add(chckbxDefaultStream, gbc_chckbxDefaultStream);
		infopanel = new MediaInfoScrollPanel(audiocolnames,false);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		add(infopanel, gbc_panel);
	}
	
	
	public void addAudioStreams(ArrayList<AudioStreamInfo> audiostreams) {
		this.audiostreams=audiostreams;
		
		this.infopanel.insertData(this.audiostreams.get(0).getAudioInfoToTable());
		this.startstream=audiostreams.get(0).getNumberstream();
		
		//String[] audiostreamsnames = new String[audiostreams.size()];
		for (int i = 0; i < audiostreams.size(); i++) {
			String name =LangTools.getWordLanguage("Stream", "general.Stream")+" "+String.valueOf(audiostreams.get(i).getNumberstream());
			comboBox.addItem(name);
		}
		
		chckbxDefaultStream.setSelected(this.audiostreams.get(0).isDefaultstream());

	}
	
	public void reset() {
		this.audiostreams=null;
		this.comboBox.removeAllItems();
		this.infopanel.reset();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		if(cmd.equals(CHANGEAUDIOSTREAM) && comboBox.getItemCount()>0) {
			this.infopanel.reset();
			this.infopanel.insertData(this.audiostreams.get(comboBox.getSelectedIndex()).getAudioInfoToTable());
			chckbxDefaultStream.setSelected(this.audiostreams.get(comboBox.getSelectedIndex()).isDefaultstream());
		}
		if(cmd.equals(DEFAULTAUDIOSTREAM) && comboBox.getItemCount()>1) {
			if(this.audiostreams.size()>1 && chckbxDefaultStream.isSelected()) {
				for (AudioStreamInfo audioStreamInfo : audiostreams) {
					audioStreamInfo.setDefaultstream(false);
				}
				this.audiostreams.get(comboBox.getSelectedIndex()).setDefaultstream(true);;
			}
		}
		
	}

}
