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
package pt.ornrocha.rencoder.gui.components.panels.configurations.generalencoding;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoContainers;
import pt.ornrocha.rencoder.gui.components.panels.Scroll.SubtitlesConfigScrollPanel;
import pt.ornrocha.rencoder.gui.components.panels.Scroll.generalencodingconfig.GeneralAudioConfigScrollPanel;
import pt.ornrocha.rencoder.gui.components.panels.Scroll.generalencodingconfig.GeneralVideoConfigScrollPanel;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.helpers.props.fields.StaticGlobalFields;
import pt.ornrocha.rencoder.helpers.props.fields.StaticVideoEncoderFields;
import pt.ornrocha.rencoder.helpers.props.managers.EncodingProfileManager;
import pt.ornrocha.rencoder.helpers.props.managers.GeneralEncodingPropertiesManager;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;
import pt.ornrocha.rencoder.mediafiles.files.auxiliar.ProcessFilesAux;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;
import pt.ornrocha.rencoder.mediafiles.setfiles.foldersandfiles.ListFiles;

// TODO: Auto-generated Javadoc
/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class MainGeneralConfigPanel extends JDialog implements ChangeListener, ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The j tabbed panemain. */
	private JTabbedPane jTabbedPanemain;

	/** The j buttoncancel. */
	private JButton jButtoncancel;

	/** The j buttonok. */
	private JButton jButtonok;

	/** The j buttonsaveprofile. */
	private JButton jButtonsaveprofile;

	/** The j button loadprofile. */
	private JButton jButtonLoadprofile;

	/** The j buttonchoosepath. */
	private JButton jButtonchoosepath;

	/** The j text fieldoutputpath. */
	private JTextField jTextFieldoutputpath;

	/** The j paneloutputpath. */
	private JPanel jPaneloutputpath;

	/** The j panel subs. */
	private JPanel jPanelSubs;

	/** The j panel audio. */
	private JPanel jPanelAudio;

	/** The j panelvideo. */
	private JPanel jPanelvideo;

	/** The video panel. */
	private GeneralVideoConfigScrollPanel videoPanel=null;

	/** The audio panel. */
	private GeneralAudioConfigScrollPanel audioPanel=null;

	/** The subtitles panel. */
	private SubtitlesConfigScrollPanel subtitlesPanel=null;

	/** The profilepanel. */
	private ProfilesLoaderConfigs profilepanel=null;

	/** The saveprofilepanel. */
	private SaveProfileConfigs saveprofilepanel=null;

	/** The outputfolderofmainpanel. */
	private JTextField outputfolderofmainpanel=null;

	/** The profilemanager. */
	private EncodingProfileManager profilemanager=null;

	/** The mainencodpropsmanager. */
	private GeneralEncodingPropertiesManager mainencodpropsmanager=null;

	/** The infocontainer. */
	private IGeneralVideoEncInfoContainer infocontainer=null;

	/** The usinginfocontainer. */
	private IGeneralVideoEncInfoContainer usinginfocontainer=null;

	/** The codec in general enc info cont. */
	private VideoCodecs codecInGeneralEncInfoCont=null;

	/** The rb. */
	private ResourceBundle rb;


	/** The cancelmainencoconfigs. */
	private static String CANCELMAINENCOCONFIGS="closemainencoconfigs";

	/** The savemainencoconfigs. */
	public static String SAVEMAINENCOCONFIGS="savemainencoconfigs";

	/** The changeoutputfoldermainencoconfigs. */
	private static String CHANGEOUTPUTFOLDERMAINENCOCONFIGS="changeoutputfoldermainencoconfigs";

	/** The loadprofile. */
	private static String LOADPROFILE="loadprofile";

	/** The saveprofile. */
	private static String SAVEPROFILE="saveprofile";


	private JFrame mainframe;
	/**
	 * Instantiates a new main general config panel.
	 *
	 * @param profiles the profiles
	 * @param genencinfomanager the genencinfomanager
	 */
	public MainGeneralConfigPanel (EncodingProfileManager profiles,GeneralEncodingPropertiesManager genencinfomanager, JFrame mainframe){
		this.mainframe=mainframe;
		this.profilemanager=profiles;
		this.mainencodpropsmanager=genencinfomanager;
		this.infocontainer=mainencodpropsmanager.getGenEncInfoContainer();
		this.codecInGeneralEncInfoCont=this.infocontainer.getVideocodec();
		rb = ResourceBundle.getBundle("lang",LangTools.getDefinedLanguage(), LangTools.loadLanguagesPath());
		initGUI();
		//this.setModal(true);
		//this.setAlwaysOnTop(true);
		addTabsChangeListener();
		sendOriginalvideoVBRQuality(this.infocontainer);
		sendGeneralEncodingInfoToComponents(this.infocontainer);
		addComponentsActionListeners();
	}

	/**
	 * Sets the jtext field of output folder main panel.
	 *
	 * @param textpanel the new j text field of outputfolder main panel
	 */
	public void setJTextFieldOfOutputfolderMainPanel(JTextField textpanel){
		this.outputfolderofmainpanel=textpanel;
	}

	/**
	 * Send general encoding info to components.
	 *
	 * @param infocontainertopass the infocontainertopass
	 */
	private void sendGeneralEncodingInfoToComponents(IGeneralVideoEncInfoContainer infocontainertopass){
		try {

			this.usinginfocontainer=(IGeneralVideoEncInfoContainer) infocontainertopass.clone();
			this.videoPanel.setParametersFromEncodingInfoContainer(this.usinginfocontainer);
			this.audioPanel.setParametersFromEncodingInfoContainer(this.usinginfocontainer);
			this.subtitlesPanel.setParametersFromEncodingInfoContainer(this.usinginfocontainer);
		} catch (Exception e) {
           Logger.error(e);
		}
	}

	/**
	 * Send originalvideo vbr quality.
	 *
	 * @param cont the cont
	 */
	private void sendOriginalvideoVBRQuality(IGeneralVideoEncInfoContainer cont){
		if(cont!=null && cont.getVideoVBRQuality()!=null)
			this.videoPanel.setOriginalvideoVBRQuality(cont.getVideoVBRQuality());
	}


	/**
	 * Adds the tabs change listener.
	 */
	private void addTabsChangeListener(){

		jTabbedPanemain.addChangeListener(this);
	}

	/**
	 * Adds the components action listeners.
	 */
	private void addComponentsActionListeners(){
		videoPanel.setExternalActionListener(this);
	}

	/**
	 * Adds the save button action listener.
	 *
	 * @param l the l
	 */
	public void addSaveButtonActionListener(ActionListener l){
		jButtonok.addActionListener(l);
	}

	/**
	 * Inits the gui.
	 */
	private void initGUI() {
		try {
			{   
				GridBagLayout thisLayout = new GridBagLayout();
				thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				thisLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
				thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};

				thisLayout.columnWidths = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
				getContentPane().setLayout(thisLayout);
				this.setTitle(LangTools.getResourceBundleWordLanguage(rb,"Main conversion profile configurations","maingui.mainconverterconfigurations"));
				this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/rencoderbig.png")));
				//this.setIconImage(new ImageIcon(getClass().getResource("/icons/rencoderpic2medium.png")).getImage());
				{
					jTabbedPanemain = new JTabbedPane();
					getContentPane().add(jTabbedPanemain, new GridBagConstraints(0, 0, 14, 11, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					{
						jPanelvideo = new JPanel();
						videoPanel= new GeneralVideoConfigScrollPanel(mainframe);
						jTabbedPanemain.addTab(LangTools.getResourceBundleWordLanguage(rb,"Video","general.video"), null, videoPanel, null);
					}
					{
						jPanelAudio = new JPanel();
						audioPanel=new GeneralAudioConfigScrollPanel(mainframe);
						jTabbedPanemain.addTab(LangTools.getResourceBundleWordLanguage(rb,"Audio","general.audio"), null, audioPanel, null);
					}
					{
						jPanelSubs = new JPanel();
						subtitlesPanel=new SubtitlesConfigScrollPanel(this);
						jTabbedPanemain.addTab(LangTools.getResourceBundleWordLanguage(rb,"Subtitles","general.subtitles"), null, subtitlesPanel, null);
						subtitlesPanel.setIsGeneralConfigUserProfile(true);
					}
				}
				{
					jPaneloutputpath = new JPanel();
					GridBagLayout jPaneloutputpathLayout = new GridBagLayout();
					getContentPane().add(jPaneloutputpath, new GridBagConstraints(0, 12, 14, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPaneloutputpathLayout.rowWeights = new double[] {0.1};
					jPaneloutputpathLayout.rowHeights = new int[] {7};
					jPaneloutputpathLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
					jPaneloutputpathLayout.columnWidths = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
					jPaneloutputpath.setLayout(jPaneloutputpathLayout);
					{
						jButtonchoosepath = new JButton();
						jPaneloutputpath.add(jButtonchoosepath, new GridBagConstraints(0, 0, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						jButtonchoosepath.setText(LangTools.getResourceBundleWordLanguage(rb,"Output Folder","general.outputfolder"));
						jButtonchoosepath.setActionCommand(CHANGEOUTPUTFOLDERMAINENCOCONFIGS);
						jButtonchoosepath.addActionListener(this);
					}
					{
						jTextFieldoutputpath = new JTextField();
						jPaneloutputpath.add(jTextFieldoutputpath, new GridBagConstraints(3, 0, 11, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						jTextFieldoutputpath.setText(this.infocontainer.getOutputFolder());
					}
				}
				{
					jButtonLoadprofile = new JButton();
					getContentPane().add(jButtonLoadprofile, new GridBagConstraints(0, 11, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					jButtonLoadprofile.setText(LangTools.getResourceBundleWordLanguage(rb,"Load Profile","general.loadprofile"));
					jButtonLoadprofile.setActionCommand(LOADPROFILE);
					jButtonLoadprofile.addActionListener(this);
				}
				{
					jButtonsaveprofile = new JButton();
					getContentPane().add(jButtonsaveprofile, new GridBagConstraints(3, 11, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					jButtonsaveprofile.setText(LangTools.getResourceBundleWordLanguage(rb,"Save as profile","general.saveasprofile"));
					jButtonsaveprofile.setActionCommand(SAVEPROFILE);
					jButtonsaveprofile.addActionListener(this);

				}
				{
					jButtonok = new JButton();
					getContentPane().add(jButtonok, new GridBagConstraints(9, 14, 5, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jButtonok.setText(LangTools.getResourceBundleWordLanguage(rb,"Save","general.save"));
					jButtonok.setActionCommand(SAVEMAINENCOCONFIGS);

				}
				{
					jButtoncancel = new JButton();
					getContentPane().add(jButtoncancel, new GridBagConstraints(5, 14, 4, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jButtoncancel.setText(LangTools.getResourceBundleWordLanguage(rb,"Cancel","general.cancel"));
					jButtoncancel.setActionCommand(CANCELMAINENCOCONFIGS);
					jButtoncancel.addActionListener(this);
				}
			}
			{
				this.setSize(700, 700);
			}
		} catch(Exception e) {
		    Logger.error(e);
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if(cmd.equals(GeneralVideoConfigScrollPanel.CHOOSECODEC)){
			setActionCodecSelection();
		}
		else if(cmd.equals(CANCELMAINENCOCONFIGS))
			this.dispose();
		else if(cmd.equals(CHANGEOUTPUTFOLDERMAINENCOCONFIGS))
			changeOutputFolder();
		else if (cmd.equals(LOADPROFILE))
			loadProfilesPanel();
		else if (cmd.equals(ProfilesLoaderConfigs.LOADCHOOSENPROFILE)){
			setProfilePropsAsGeneralParameters();
		}
		else if (cmd.equals(ProfilesLoaderConfigs.LOADPROFILEFROMFILE))
			getprofilefromfile();

		else if (cmd.equals(SAVEPROFILE)){
			saveNewEncodingConfigurations();
			saveProfilePanel();
		}


	}

	/* (non-Javadoc)
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		VideoContainers vcontainerInVideoConf = videoPanel.getSelectedVideoContainer();
		VideoContainers vcontainerInAudioConf = audioPanel.getVideoContainer();

		if(jTabbedPanemain.getSelectedComponent().equals(this.audioPanel)){

			if(!vcontainerInVideoConf.toString().equals(vcontainerInAudioConf.toString())){	

				this.audioPanel.setUsedVideoContainer(vcontainerInVideoConf);
				this.audioPanel.changeComponents();	 
			}
			audioPanel.usingFileSizeInEncoding(this.videoPanel.isUsedFileZise());

		}
		else if(jTabbedPanemain.getSelectedComponent().equals(this.subtitlesPanel)){
			subtitlesPanel.setVideoContainer(vcontainerInVideoConf);
		}

	}


	/**
	 * Sets the action codec selection.
	 */
	private void setActionCodecSelection(){
		VideoCodecs codec = videoPanel.getSelectedCodec();
		IGeneralVideoEncInfoContainer newcont = ProcessFilesAux.copyEncInfoConFromOtherInfoCont(this.usinginfocontainer,codec);
		sendGeneralEncodingInfoToComponents(newcont);
	}
	
	/**
	 * Change output folder.
	 */
	private void changeOutputFolder(){
		String folder = ListFiles.getFolderpathFileChooser(this);
		if(folder!=null){
			jTextFieldoutputpath.setText(folder);
			this.usinginfocontainer.setOutputFolder(folder);
			this.outputfolderofmainpanel.setText(folder);
			PropertiesWorker.ChangePropertiesParam(StaticGlobalFields.RENCODERCONFIGFILE, StaticVideoEncoderFields.SAVECONVERTEDFILEPATH, folder);
		}

	}

	/**
	 * Save new encoding configurations.
	 */
	private void saveNewEncodingConfigurations(){

		this.videoPanel.saveSelectedInformationIntoContainer();
		this.audioPanel.saveSelectedInformationIntoContainer();
		this.subtitlesPanel.saveSelectedInformationIntoContainer();

	}

	/**
	 * Save new encoding properties.
	 */
	public void saveNewEncodingProperties(){
		saveNewEncodingConfigurations();
		this.mainencodpropsmanager.setIGeneralVideoEncInfoContainer(usinginfocontainer);
		this.videoPanel.saveConfigurationsToFile();
		usinginfocontainer.saveConfigurations(StaticVideoEncoderFields.GENERALSETTINGSFILEPATH, null, false);
		
	}

	/**
	 * Gets the enco info container.
	 *
	 * @return the enco info container
	 */
	public IGeneralVideoEncInfoContainer getEncoInfoContainer(){
		return this.usinginfocontainer;
	}

	/**
	 * Load profiles panel.
	 */
	private void loadProfilesPanel(){

		try{
			profilepanel=new ProfilesLoaderConfigs(this.profilemanager);
			profilepanel.addOkActionListener(this);
			profilepanel.addLoadFromFileActionListener(this);
			profilepanel.setLocationRelativeTo(this);
			profilepanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			profilepanel.setVisible(true);
		} catch (Exception e) {
		    Logger.error(e);
		}
	}


	/**
	 * Save profile panel.
	 */
	private void saveProfilePanel(){
		try{
			saveprofilepanel= new SaveProfileConfigs(usinginfocontainer,mainframe);
			saveprofilepanel.setLocationRelativeTo(this);
			saveprofilepanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			saveprofilepanel.setVisible(true);
		} catch (Exception e) {
		    Logger.error(e);
		}

	}


	/**
	 * Sets the profile props as general parameters.
	 */
	private void setProfilePropsAsGeneralParameters(){
		IGeneralVideoEncInfoContainer container =profilepanel.getChoosenProfileEncodingConfigs();
		sendOriginalvideoVBRQuality(container);
		sendGeneralEncodingInfoToComponents(container);
		profilepanel.dispose();
	}

	/**
	 * Gets the profilefromfile.
	 *
	 * @return the profilefromfile
	 */
	private void getprofilefromfile(){
		IGeneralVideoEncInfoContainer container =profilepanel.getProfileFromFileConfig();
		sendOriginalvideoVBRQuality(container);
		sendGeneralEncodingInfoToComponents(container);
		profilepanel.dispose();

	}

}
