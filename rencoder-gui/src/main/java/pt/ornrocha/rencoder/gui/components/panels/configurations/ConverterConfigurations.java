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
package pt.ornrocha.rencoder.gui.components.panels.configurations;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoContainers;
import pt.ornrocha.rencoder.gui.components.panels.Scroll.AudioConfigSrollPanel;
import pt.ornrocha.rencoder.gui.components.panels.Scroll.SubtitleScrollPanel;
import pt.ornrocha.rencoder.gui.components.panels.Scroll.SubtitlesConfigScrollPanel;
import pt.ornrocha.rencoder.gui.components.panels.Scroll.VideoConfigScrollPanel;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.helpers.props.managers.EncodingProfileManager;
import pt.ornrocha.rencoder.helpers.props.managers.GeneralEncodingPropertiesManager;
import pt.ornrocha.rencoder.helpers.props.managers.auxiliar.EncodingPropsAuxiliar;
import pt.ornrocha.rencoder.mediafiles.files.auxiliar.ProcessFilesAux;
import pt.ornrocha.rencoder.mediafiles.files.containers.base.Videofile;
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
public class ConverterConfigurations extends JDialog implements ChangeListener, ActionListener{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The j panelchooser. */
	private JPanel jPanelchooser;
	
	/** The j combo boxprofiles. */
	private JComboBox<String> jComboBoxprofiles;
	
	/** The j buttonapplyall. */
	private JButton jButtonapplyall;
	
	/** The j panelsubs. */
	private JPanel jPanelsubs;
	
	/** The j buttonchangepath. */
	private JButton jButtonchangepath;
	
	/** The j text fieldpath. */
	private JTextField jTextFieldpath;
	
	/** The j panelpath. */
	private JPanel jPanelpath;
	
	/** The j buttoncancel. */
	private JButton jButtoncancel;
	
	/** The j buttonok. */
	private JButton jButtonok;
	
	/** The j panelaudio. */
	private JPanel jPanelaudio;
	
	/** The j panelvideo. */
	private JPanel jPanelvideo;
	
	/** The j tabbed panesettings. */
	private JTabbedPane jTabbedPanesettings;
	
	/** The j paneldirandok. */
	private JPanel jPaneldirandok;
	
	/** The videoconfs. */
	private VideoConfigScrollPanel videoconfs;
	
	/** The audioconfs. */
	private AudioConfigSrollPanel audioconfs;
	
	/** The subconfs. */
	private SubtitlesConfigScrollPanel subconfs;
	
	/** The profilemanager. */
	private EncodingProfileManager profilemanager=null;
	
	/** The movie. */
	private Videofile movie=null;
	
	/** The infocontainer. */
	private IGeneralVideoEncInfoContainer infocontainer=null;
	
	/** The usinginfocontainer. */
	private IGeneralVideoEncInfoContainer usinginfocontainer=null;
	
	/** The pathtosave movie. */
	private String pathtosaveMovie=null;
	
	/** The outputfolderchanged. */
	private boolean outputfolderchanged=false;
	
	/** The using profile. */
	private boolean usingProfile=false;
	
	/** The changedinfoincontainer. */
	private boolean changedinfoincontainer=false;

	/** The codec in general enc info cont. */
	private VideoCodecs codecInGeneralEncInfoCont=null;
	
	/** The chooseprofile. */
	private static String CHOOSEPROFILE="chooseprofile";
	
	/** The saveandcloseconvconf. */
	private static String SAVEANDCLOSECONVCONF="saveandcloseconvconf";
	
	/** The cancelconvconf. */
	private static String CANCELCONVCONF="cancelconvconf";
	
	/** The changepathfoldersinglemovie. */
	private static String CHANGEPATHFOLDERSINGLEMOVIE="changepathfoldersingemovie";
	
	/** The saveconfigtoallmovies. */
	public static String SAVECONFIGTOALLMOVIES="saveconfigtoallmovies";
	
	/** The subtitlespanel. */
	private SubtitleScrollPanel subtitlespanel=null;
	
	/** The rb. */
	private ResourceBundle rb;
	
	private PropertyChangeSupport changelst =null;
	
	
	private JFrame mainframe;
	/**
	 * Instantiates a new converter configurations.
	 *
	 * @param profiles the profiles
	 * @param genencinfomanager the genencinfomanager
	 * @param video the video
	 */
	public ConverterConfigurations(EncodingProfileManager profiles,GeneralEncodingPropertiesManager genencinfomanager, Videofile video,JFrame mainframe){
		this.mainframe=mainframe;
		rb = ResourceBundle.getBundle("lang",LangTools.getDefinedLanguage(), LangTools.loadLanguagesPath());
		initGUI();
		//this.setModal(true);
		
		this.profilemanager=profiles;
		this.movie=video;
		this.infocontainer=genencinfomanager.getGenEncInfoContainer();
		this.pathtosaveMovie=infocontainer.getOutputFolder();
		this.codecInGeneralEncInfoCont=this.infocontainer.getVideocodec();
		addComponentsActionListeners();
		setCloseWindowListener();
		populateComponents();
		if(this.movie.getEncodingInfoContainer().getProfileTypeTag()!=null){
			sendOriginalvideoVBRQuality(this.movie.getEncodingInfoContainer());
			sendGeneralEncodingInfoToComponents(this.movie.getEncodingInfoContainer());
			jComboBoxprofiles.setSelectedItem(this.movie.getEncodingInfoContainer().getProfileTypeTag());
		}
		else if(this.movie.getEncodingInfoContainer().isModifiedtag()){
			this.changedinfoincontainer=true;
			sendOriginalvideoVBRQuality(this.movie.getEncodingInfoContainer());
			sendGeneralEncodingInfoToComponents(this.movie.getEncodingInfoContainer());
		}
		else{
			sendOriginalvideoVBRQuality(this.infocontainer);
			sendGeneralEncodingInfoToComponents(this.infocontainer);
			
		}

		addTabsChangeListener();	    
	}
	
	  private void setCloseWindowListener(){
		  this.addWindowListener(new java.awt.event.WindowAdapter() {
			    @Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			    	setConverterConfigurationsFireEvents();
			    	dispose();
			    	
			        
			    }
			});
	  }
	
	/**
	 * Populate components.
	 */
	private void populateComponents(){
		
		jTextFieldpath.setText(this.infocontainer.getOutputFolder());
		populateProfileComboBox();
		this.videoconfs.setExternalActionListener(this);
		this.audioconfs.setVideoInfoContainer(this.movie.getMovieinfocontainer());
		
	}
	
	
	/**
	 * Populate profile combobox.
	 */
	private void populateProfileComboBox(){
		
		String []profnames=null;
		if(this.profilemanager!=null){
			profnames= new String[this.profilemanager.getnameProfiles().length+1];
			profnames[0]=LangTools.getResourceBundleWordLanguage(rb,"User default profile","profilegui.userdefault");
			
			ArrayList<String> profilesnames=this.profilemanager.getArrayListOfProfileNames();
			Collections.sort(profilesnames);
			for (int i = 0; i < profilesnames.size(); i++) {
				profnames[i+1]=profilesnames.get(i);
			}
			
		}
		else{
			profnames= new String[1];
		    profnames[0]=LangTools.getResourceBundleWordLanguage(rb,"User default profile","profilegui.userdefault");
		}
		
		DefaultComboBoxModel<String> profilenames= new DefaultComboBoxModel<>(profnames);
	    jComboBoxprofiles.setModel(profilenames);
		
	}
	
	
	
	/**
	 * Send general encoding info to components.
	 *
	 * @param infocontainertopass the infocontainertopass
	 */
	private void sendGeneralEncodingInfoToComponents(IGeneralVideoEncInfoContainer infocontainertopass){
       // System.out.println(infocontainertopass.getAudiocodec().toString());
		this.usinginfocontainer=(IGeneralVideoEncInfoContainer) infocontainertopass.clone();
		// System.out.println(usinginfocontainer.getAudiocodec().toString());
		if(outputfolderchanged)
			this.usinginfocontainer.setOutputFolder(pathtosaveMovie);
		
		if(this.usinginfocontainer.getVideocodec().equals(VideoCodecs.COPY)){
			VideoContainers vcont= EncodingPropsAuxiliar.getVideoContainerFromMovieExtension(getVideoFileExtension());
			if(vcont!=null)
				this.usinginfocontainer.setVideoContainer(vcont);
		}
		

		this.videoconfs.setParametersFromEncodingInfoContainer(this.usinginfocontainer);
        this.audioconfs.setParametersFromEncodingInfoContainer(this.usinginfocontainer);
		this.subconfs.setParametersFromEncodingInfoContainer(this.usinginfocontainer, this.movie);
	}
	
	
	
	/**
	 * Adds the tabs change listener.
	 */
	private void addTabsChangeListener(){
		
		jTabbedPanesettings.addChangeListener(this);
	}
	
	
	  public void addProcessesPropertyChangeListener(PropertyChangeListener listener) {
			this.changelst= new PropertyChangeSupport(this);
			this.changelst.addPropertyChangeListener(listener);
	     }
	  
	    private void setConverterConfigurationsFireEvents(){
		   changelst.firePropertyChange("closeconverterconfigurations",false,true);
		   
	    }
	
	
	/**
	 * Sets the subtitle panel.
	 *
	 * @param subpanel the new subtitle panel
	 */
	public void setSubtitlePanel(SubtitleScrollPanel subpanel){
		this.subtitlespanel=subpanel;
	}
	
	/**
	 * Send original video vbr quality.
	 *
	 * @param cont the cont
	 */
	private void sendOriginalvideoVBRQuality(IGeneralVideoEncInfoContainer cont){
		if(cont.getVideoVBRQuality()!=null)
			this.videoconfs.setOriginalvideoVBRQuality(cont.getVideoVBRQuality());
	}
	
	public IGeneralVideoEncInfoContainer getinfocontainertouse(){
		if(this.usinginfocontainer!=null)
		   return this.usinginfocontainer;
		else return this.infocontainer;
		
	}

	/**
	 * Inits the gui.
	 */
	private void initGUI() {
		try {
			{
				GridBagLayout thisLayout = new GridBagLayout();
				thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				thisLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
				thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				
				thisLayout.columnWidths = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
				getContentPane().setLayout(thisLayout);
				this.setTitle(LangTools.getResourceBundleWordLanguage(rb,"Conversion settings for selected movie","convertergui.configurations"));
				{
					jPanelchooser = new JPanel();
					GridBagLayout jPanelchooserLayout = new GridBagLayout();
					getContentPane().add(jPanelchooser, new GridBagConstraints(0, 0, 10, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPanelchooserLayout.rowWeights = new double[] {0.1, 0.1, 0.1};
					jPanelchooserLayout.rowHeights = new int[] {7, 7, 7};
					jPanelchooserLayout.columnWeights = new double[] {0.1};
					jPanelchooserLayout.columnWidths = new int[] {7};
					jPanelchooser.setLayout(jPanelchooserLayout);
					jPanelchooser.setBorder(BorderFactory.createTitledBorder(LangTools.getWordLanguage("Choose a profile", "convertergui.profile")));
					{
						
						jComboBoxprofiles = new JComboBox();
						jPanelchooser.add(jComboBoxprofiles, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jComboBoxprofiles.setActionCommand(CHOOSEPROFILE);
					}
				}
				{
					jPaneldirandok = new JPanel();
					GridBagLayout jPaneldirandokLayout = new GridBagLayout();
					getContentPane().add(jPaneldirandok, new GridBagConstraints(0, 10, 10, 4, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPaneldirandokLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
					jPaneldirandokLayout.rowHeights = new int[] {7, 7, 7, 7};
					jPaneldirandokLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
					jPaneldirandokLayout.columnWidths = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
					jPaneldirandok.setLayout(jPaneldirandokLayout);
					{
						jButtonok = new JButton();
						jPaneldirandok.add(jButtonok, new GridBagConstraints(6, 2, 4, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jButtonok.setText(rb.getString("general.ok"));
						jButtonok.setActionCommand(SAVEANDCLOSECONVCONF);
					}
					{
						jButtoncancel = new JButton();
						jPaneldirandok.add(jButtoncancel, new GridBagConstraints(3, 2, 3, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jButtoncancel.setText(LangTools.getResourceBundleWordLanguage(rb,"Cancel","general.cancel"));
						jButtoncancel.setActionCommand(CANCELCONVCONF);
					}
					{
						jPanelpath = new JPanel();
						GridBagLayout jPanelpathLayout = new GridBagLayout();
						jPaneldirandok.add(jPanelpath, new GridBagConstraints(0, 0, 10, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jPanelpathLayout.rowWeights = new double[] {0.1};
						jPanelpathLayout.rowHeights = new int[] {7};
						jPanelpathLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
						jPanelpathLayout.columnWidths = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
						jPanelpath.setLayout(jPanelpathLayout);
						jPanelpath.setBorder(BorderFactory.createTitledBorder(LangTools.getWordLanguage("Save in folder?", "convertergui.savefolder")));
						{
							jTextFieldpath = new JTextField();
							jPanelpath.add(jTextFieldpath, new GridBagConstraints(0, 0, 7, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						}
						{
							jButtonchangepath = new JButton();
							jPanelpath.add(jButtonchangepath, new GridBagConstraints(7, 0, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
							jButtonchangepath.setText(LangTools.getResourceBundleWordLanguage(rb,"Change","general.change"));
							jButtonchangepath.setActionCommand(CHANGEPATHFOLDERSINGLEMOVIE);
						}
					}
					{
						jButtonapplyall = new JButton();
						jPaneldirandok.add(jButtonapplyall, new GridBagConstraints(0, 2, 3, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jButtonapplyall.setText(LangTools.getWordLanguage("Apply to all in list", "convertergui.applyall"));
						jButtonapplyall.setActionCommand(SAVECONFIGTOALLMOVIES);
					}
				}
				{
					jTabbedPanesettings = new JTabbedPane();
					getContentPane().add(jTabbedPanesettings, new GridBagConstraints(0, 2, 10, 8, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jTabbedPanesettings.setBorder(BorderFactory.createTitledBorder(LangTools.getWordLanguage("Conversion settings", "convertergui.settings")));
					{
						jPanelvideo = new JPanel();
						videoconfs = new VideoConfigScrollPanel(mainframe);
						jTabbedPanesettings.addTab(LangTools.getResourceBundleWordLanguage(rb,"Video","general.video"), null, videoconfs, null);
					}
					{
						jPanelaudio = new JPanel();
						audioconfs = new AudioConfigSrollPanel(mainframe);
						
						jTabbedPanesettings.addTab(LangTools.getResourceBundleWordLanguage(rb,"Audio","general.audio"), null, audioconfs, null);
					}
					{
						jPanelsubs = new JPanel();
						subconfs = new SubtitlesConfigScrollPanel(this);
						jTabbedPanesettings.addTab(LangTools.getResourceBundleWordLanguage(rb,"Subtitles","general.subtitles"), null, subconfs, null);
					}
				}
			}
			{
				//this.setSize(595, 646);
				this.setSize(700, 700);
				//this.setPreferredSize(new Dimension(645, 800));
			}
		} catch(Exception e) {
		    Logger.error(e);
		}
	}




	/* (non-Javadoc)
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		
		VideoContainers vcontainerInVideoConf = videoconfs.getSelectedVideoContainer();
		
        if(jTabbedPanesettings.getSelectedComponent().equals(this.audioconfs)){
        	
        	VideoContainers vcontainerInAudioConf = audioconfs.getVideoContainer();
        	
        	if(!vcontainerInAudioConf.equals(vcontainerInVideoConf)){
        		this.audioconfs.setUsedVideoContainer(vcontainerInVideoConf);
        		this.audioconfs.changeComponents();	
        	}
        	audioconfs.usingFileSizeInEncoding(this.videoconfs.isUsedFileZise());
            
        }
        else if(jTabbedPanesettings.getSelectedComponent().equals(this.subconfs)){
        	subconfs.setVideoContainer(vcontainerInVideoConf);
        }
        	
		
	}
	
	
	
   /**
    * Adds the components action listeners.
    */
   private void addComponentsActionListeners(){
		
		jComboBoxprofiles.addActionListener(this);
		jButtonok.addActionListener(this);
		jButtoncancel.addActionListener(this);
		jButtonchangepath.addActionListener(this);
		subconfs.setExternalActionListeners(this);
	}
   
   /**
    * Adds the action listener from main gui.
    *
    * @param l the l
    */
   public void addActionListenerFromMainGUI(ActionListener l){
	   jButtonapplyall.addActionListener(l);
   }


	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if(cmd.equals(CHOOSEPROFILE))
			setChoosedProfile();
		else if (cmd.equals(VideoConfigScrollPanel.CHOOSECODEC))
			setActionCodecSelection();
		else if(cmd.equals(CHANGEPATHFOLDERSINGLEMOVIE))
			changesavepathfolder();
		else if(cmd.equals(SAVEANDCLOSECONVCONF)){
			saveNewEncodingConfigurations();
			setConverterConfigurationsFireEvents();
			this.dispose(); 
			
		}
		else if(cmd.equals(SubtitlesConfigScrollPanel.CONFIGURESOFTSUBS))
			LaunchSoftSubsConfigPanel();
		
		else if(cmd.equals(CANCELCONVCONF)){
			setConverterConfigurationsFireEvents();
			this.dispose();
			
		}
	}
	
	
	/**
	 * Sets the action codec selection.
	 */
	private void setActionCodecSelection(){
		//System.out.println(usingProfile);
		if(!usingProfile){
			VideoCodecs codec = videoconfs.getSelectedCodec();

			if(!codec.toString().equals(this.codecInGeneralEncInfoCont.toString())){
				IGeneralVideoEncInfoContainer newcont = ProcessFilesAux.copyEncInfoConFromOtherInfoCont(this.usinginfocontainer,codec);
				sendGeneralEncodingInfoToComponents(newcont);  

			}
			else {

				if(this.changedinfoincontainer && (codec.equals(infocontainer.getVideocodec()))){
					IGeneralVideoEncInfoContainer modcont = ProcessFilesAux.copyEncInfoConFromOtherInfoCont(this.usinginfocontainer,codec);
					sendGeneralEncodingInfoToComponents(modcont);

				}
				else{

					sendGeneralEncodingInfoToComponents(this.infocontainer);

				}
			}
		}
	}
	
//	private void changeAllowedAudioCodecs() {
//		VideoContainers container=videoconfs.getSelectedVideoContainer();
//		audioconfs.setUsedVideoContainer(container);
//		audioconfs.changeListSupportedCodecs();
//	}
	
	
	
	
	/**
	 * Sets the choosed profile.
	 */
	private void setChoosedProfile(){
		
		int pos=jComboBoxprofiles.getSelectedIndex();
		String profname=(String) jComboBoxprofiles.getSelectedItem();
		if(pos==0){
			sendOriginalvideoVBRQuality(this.infocontainer);
			sendGeneralEncodingInfoToComponents(this.infocontainer);
		    videoconfs.setVideoCodecEditable(true);
		    this.usingProfile=false;
		}
		else{
			
			IGeneralVideoEncInfoContainer profinfo=this.profilemanager.getEncodingInfoContainerbyname(profname);
			if(profinfo!=null){
			profinfo.setOutputFolder(pathtosaveMovie);
			this.usingProfile=true;
			sendOriginalvideoVBRQuality(profinfo);
			sendGeneralEncodingInfoToComponents(profinfo);
			videoconfs.setVideoCodecEditable(false);
		   }
		}
	}
	
	/**
	 * Change save path folder.
	 */
	private void changesavepathfolder(){
		
		String folder = ListFiles.getFolderpathFileChooser(this);
		if(folder!=null){
			this.outputfolderchanged=true;
			pathtosaveMovie=folder;
			jTextFieldpath.setText(folder);
			this.usinginfocontainer.setOutputFolder(folder);
		}
		
	}
	
	
	/**
	 * Gets the video file extension.
	 *
	 * @return the video file extension
	 */
	private String getVideoFileExtension(){
		return this.movie.getExtension();

	}
	
	/**
	 * Save new encoding configurations.
	 */
	private void saveNewEncodingConfigurations(){
		
		this.videoconfs.saveSelectedInformationIntoContainer();
		this.audioconfs.saveSelectedInformationIntoContainer();
		this.subconfs.saveSelectedInformationIntoContainer();
		this.movie.setEncodingInfoContainer(this.usinginfocontainer);
	}
	
	/**
	 * Launch soft subs config panel.
	 */
	private void LaunchSoftSubsConfigPanel(){
		
		
		try{
			SoftSubtitlesConfigPanel softsubpanel = new SoftSubtitlesConfigPanel();
			
			softsubpanel.setSubtitleConfigPanel(subtitlespanel, this.movie);
			softsubpanel.setLocationRelativeTo(this);
			softsubpanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			softsubpanel.setVisible(true);
		    } catch (Exception e) {
			Logger.error(e);
	       }
	}
	
	/**
	 * Gets the common encoding info container.
	 *
	 * @return the common encod info container
	 */
	public IGeneralVideoEncInfoContainer getCommonEncodInfoContainer(){
		saveNewEncodingConfigurations();
		return this.usinginfocontainer;
	}
	


}
