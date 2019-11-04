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
package pt.ornrocha.rencoder.gui.components.panels.Scroll;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.BoundedRangeModel;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeListener;

import org.pmw.tinylog.Logger;

import eu.hansolo.custom.SteelCheckBox;
import pt.ornrocha.rencoder.ffmpegWrapper.configurations.FFmpegManager;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioChannels;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioSampleRates;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoContainers;
import pt.ornrocha.rencoder.gui.Maingui;
import pt.ornrocha.rencoder.gui.components.panels.auxiliar.AudioPanelUtils;
import pt.ornrocha.rencoder.gui.components.panels.auxiliar.BitrateComboBoxModel;
import pt.ornrocha.rencoder.gui.components.panels.auxiliar.CopyPasteJTextField;
import pt.ornrocha.rencoder.gui.components.panels.auxiliar.VBRSliderListenerAudio;
import pt.ornrocha.rencoder.gui.components.panels.auxiliar.VideoPanelUtils;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.helpers.props.managers.auxiliar.EncodingPropsAuxiliar;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.MediaInfoContainer;

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
public class AudioConfigSrollPanel extends JScrollPane implements ActionListener{

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The j panelmain. */
	protected JPanel jPanelmain;
	
	/** The j labelcodec. */
	private JLabel jLabelcodec;
	
	/** The j labelaverage. */
	private JLabel jLabelaverage;
	
	/** The j labelusedvbr. */
	protected JLabel jLabelusedvbr;
	
	/** The j slidervbr. */
	protected JSlider jSlidervbr;

	/** The Check boxcbr. */
	protected SteelCheckBox CheckBoxcbr;
	
	/** The Check boxvbr. */
	protected SteelCheckBox CheckBoxvbr;
	
	/** The audioffmpegcmd. */
	private SteelCheckBox audioffmpegcmd;
	
	/** The j combo box channels. */
	protected JComboBox<AudioChannels> jComboBoxChannels;
	
	/** The j labelchannels. */
	protected JLabel jLabelchannels;
	
	/** The j combo boxfrequency. */
	protected JComboBox<AudioSampleRates> jComboBoxfrequency;
	
	/** The j labelfrequency. */
	protected JLabel jLabelfrequency;
	
	/** The j combo boxbitrate. */
	protected JComboBox<String> jComboBoxbitrate;
	
	/** The j combo boxcodec. */
	protected JComboBox<AudioCodecs> jComboBoxcodec;
	
	/** The b range model. */
	private  BoundedRangeModel bRangeModel;
	
	/** The audiocbrvalues. */
	private BitrateComboBoxModel<String> audiocbrvalues;
     
	
	/** The video container. */
	private VideoContainers videoContainer =null;
	
	/** The audio info container. */
	private MediaInfoContainer audioInfoContainer=null;
	
	/** The acodecs. */
	private DefaultComboBoxModel<AudioCodecs> acodecs=null;
	
	/** The asamplerates. */
	protected DefaultComboBoxModel<AudioSampleRates> asamplerates=null;
	
	/** The achannels. */
	protected DefaultComboBoxModel<AudioChannels> achannels=null;
	
	/** The infocont. */
	protected IGeneralVideoEncInfoContainer infocont=null;
	
	/** The j check boxffmpegcmd. */
	protected JCheckBox jCheckBoxffmpegcmd;
	//protected JTextField jTextFieldAudioffmpegcmd;
	/** The j text field audioffmpegcmd. */
	protected CopyPasteJTextField jTextFieldAudioffmpegcmd;
	
	/** The j label1. */
	private JLabel jLabel1;
	//private JLabel jLabel1;
	/** The j labelbitperchannel. */
	protected JLabel jLabelbitperchannel;
	
	/** The j labelperchannel. */
	private JLabel jLabelperchannel;
	
	/** The using filesize in video. */
	private boolean usingFilesizeInVideo=false;
	
	
	/** The rb. */
	protected ResourceBundle rb;
	
	/** The Constant USEAUDIOCBR. */
	private static final String USEAUDIOCBR="useaudioCBR"; 
	
	/** The Constant USEAUDIOVBR. */
	private static final String USEAUDIOVBR="useaudioVBR"; 
	
	/** The Constant CHANGEAUDIOCODEC. */
	private static final String CHANGEAUDIOCODEC="changeaudiocodec";
	
	/** The Constant CHANGEAUDIOCONSTBITRATE. */
	private static final String CHANGEAUDIOCONSTBITRATE="changeaudioconstbitrate";
	
	/** The Constant CHANGECHANNELS. */
	private static final String CHANGECHANNELS="changechannels";
	
	/** The extraaudioffmpegcmd. */
	protected static String EXTRAAUDIOFFMPEGCMD="extraaudioffmpegcmd";
	
	/** The useonlyaudioextracmd. */
	protected static String USEONLYAUDIOEXTRACMD="useonlyaudioextracmd";
	// To calculate the bitrate to use for multi-channel audio: (bitrate for stereo) x (channels / 2).
	
	protected JFrame mainframe;
	
	/**
	 * Instantiates a new audio config sroll panel.
	 */
	public AudioConfigSrollPanel(JFrame mainframe){
	    this.mainframe=mainframe;
		rb = ResourceBundle.getBundle("lang",LangTools.getDefinedLanguage(), LangTools.loadLanguagesPath());
		initGUI();
		populateInitialComponents();

	}
	
	
	/**
	 * Change components.
	 */
	public void changeComponents(){
		changeListSupportedCodecs();
	}
	
	
	
	
	
	/**
	 * Change list supported codecs.
	 */
	public void changeListSupportedCodecs(){

		if(videoContainer!=null){
			acodecs = new DefaultComboBoxModel<>(getvalidatedAllowedAudioCodecs());	
			jComboBoxcodec.setModel(acodecs);
		}
	}
	
	
	
	/**
	 * Sets the used video container.
	 *
	 * @param container the new used video container
	 */
	public void setUsedVideoContainer(VideoContainers container){
		this.videoContainer=container;
	}
	

	/**
	 * Sets the video info container.
	 *
	 * @param audioinfo the new video info container
	 */
	public void setVideoInfoContainer(MediaInfoContainer audioinfo){
		this.audioInfoContainer=audioinfo;
	}
	
	/**
	 * Using file size in encoding.
	 *
	 * @param bol the bol
	 */
	public void usingFileSizeInEncoding(boolean bol){
		this.usingFilesizeInVideo=bol;
		if(bol){
		  CheckBoxcbr.setSelected(true);
		  CheckBoxcbrSelectedAction();
		}
		
	}
	
	
	/**
	 * Sets the audio sample rate.
	 */
	protected void setAudioSampleRate(){
		 if(this.infocont!=null){
		 String movieaudioamplerate=this.infocont.getMaxAllowedAudiosamplerate().toString();
		 asamplerates=new DefaultComboBoxModel<>(AudioPanelUtils.allowedAudioSampleRates(movieaudioamplerate));
		 }
	   else
		  asamplerates=new DefaultComboBoxModel<>(AudioPanelUtils.allowedAudioSampleRates("44100"));
		
		jComboBoxfrequency.setModel(asamplerates);
			 
		
	}
	
	/**
	 * Sets the audio channels.
	 *
	 * @param codec the new audio channels
	 */
	protected void setAudioChannels(AudioCodecs codec){
	  if(this.infocont!=null){
		String audiochannel=this.audioInfoContainer.getfirstAudioStream().getAudiochanneltype();
		AudioChannels originalmoviechannel = EncodingPropsAuxiliar.getAudioChannelFromString(audiochannel);
		AudioChannels containermaxallowedchannel=null;
		if(infocont!=null){
		   containermaxallowedchannel = infocont.getMaxAllowedAudiochannels();
		  // System.out.println(infocont.getMaxAllowedAudiochannels());
		   if(containermaxallowedchannel.getLevelTag()>=originalmoviechannel.getLevelTag())
			   audiochannel=containermaxallowedchannel.toString();
		   
		}

		achannels=new DefaultComboBoxModel<>(AudioPanelUtils.allowedAudioChannels(audiochannel, codec));
		//jComboBoxChannels.setModel(achannels);
		}

	else
		achannels=new DefaultComboBoxModel<>(AudioPanelUtils.allowedAudioChannels(AudioChannels.STEREO.toString(), codec));
		
	  jComboBoxChannels.removeAllItems();		
	  jComboBoxChannels.setModel(achannels);	
		
	}
	

  /**
   * Sets the j slider settings.
   *
   * @param initvalue the initvalue
   * @param extent the extent
   * @param min the min
   * @param average the average
   * @param max the max
   * @param minortickSpacing the minortick spacing
   * @param majortickSpacing the majortick spacing
   * @param vbrtable the vbrtable
   * @param invert the invert
   */
	public void setJSliderSettings(int initvalue, int extent, int min,int average, int max, int minortickSpacing, int majortickSpacing, HashMap<Integer,String> vbrtable, boolean invert){



		bRangeModel = new DefaultBoundedRangeModel(initvalue, extent, min, max);
		jSlidervbr.setModel(bRangeModel);

		jSlidervbr.setMajorTickSpacing( majortickSpacing );
		jSlidervbr.setMinorTickSpacing( minortickSpacing );
		jSlidervbr.setPaintTicks( true );
		//jSliderquality.setSnapToTicks(true);
		jSlidervbr.setPaintLabels( true );

		jLabelusedvbr.setText(vbrtable.get(initvalue));

		AudioChannels channels = (AudioChannels)jComboBoxChannels.getSelectedItem();
		int nchannels= channels.getchannels();
		String bitrate=vbrtable.get(initvalue);
		int bitrateperchannel= Integer.valueOf(bitrate)/nchannels;
		jLabelbitperchannel.setText(String.valueOf(bitrateperchannel));

		if(invert){
			Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
			table.put (min, new JLabel(LangTools.getResourceBundleWordLanguage(rb,"Worst","general.worst")));
			table.put (average, new JLabel(LangTools.getResourceBundleWordLanguage(rb,"Average","general.average")));
			table.put (max, new JLabel(LangTools.getResourceBundleWordLanguage(rb,"Best","general.best")));
			jSlidervbr.setLabelTable (table);
		}
		else{
			Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
			table.put (min, new JLabel(LangTools.getResourceBundleWordLanguage(rb,"Best","general.best")));
			table.put (average, new JLabel(LangTools.getResourceBundleWordLanguage(rb,"Average","general.average")));
			table.put (max, new JLabel(LangTools.getResourceBundleWordLanguage(rb,"Worst","general.worst")));
			jSlidervbr.setLabelTable (table);
		}

		if(jSlidervbr.getChangeListeners().length>0){
			ChangeListener[] list = jSlidervbr.getChangeListeners();
			for (int i = 0; i < list.length; i++) {
				jSlidervbr.removeChangeListener(list[i]);
			}		
		}

		jSlidervbr.addChangeListener(new VBRSliderListenerAudio(vbrtable,jLabelusedvbr,true,this.jComboBoxChannels,jLabelbitperchannel));


	}



  
  
    /**
     * Sets the j slider settings to audio codec type.
     */
	private void setJSliderSettingsToAudioCodecType(){
		AudioCodecs acodec= (AudioCodecs) jComboBoxcodec.getSelectedItem();

		if(acodec.getBitrateTable()!=null) {
			jLabelaverage.setText(LangTools.getResourceBundleWordLanguage(rb, "Average(kbit/s)", "audiogui.average"));
			int[] ss=sliderSettings(acodec);
			setJSliderSettings(ss[0],ss[1],ss[2],ss[3],ss[4],ss[5],ss[6],acodec.getBitrateTable(), acodec.invertScale());
		}
	}
    
    private int[] sliderSettings(AudioCodecs codec) {
    	
    	if(codec.equals(AudioCodecs.VORBIS))
    		return new int[] {4,0,0,4,10,1,2};
    	else if (codec.equals(AudioCodecs.LCAAC))
    		return new int[] {5,0,0,5,10,1,2};
    	else
    		return new int[] {5,0,0,5,9,1,2};
    }
	

	/**
	 * Populate initial components.
	 */
	protected void populateInitialComponents(){
		
		audiocbrvalues = new BitrateComboBoxModel<String>(VideoPanelUtils.convertAudioCBRValuesToArrayString());
		audiocbrvalues.invertElementsEvaluation(true);
		jComboBoxbitrate.setModel(audiocbrvalues);
		
		setAudioSampleRate();
		setAudioChannels(AudioCodecs.MP3);
		
		
		
		CheckBoxcbr.setColored(true);
		CheckBoxvbr.setColored(true);
		audioffmpegcmd.setColored(true);
		
		audioffmpegcmd.setSelected(false);
		checkuseExtraaudioFFmpegCMD();
	}
	
	
	/**
	 * Gets the validated allowed audio codecs.
	 *
	 * @return the validated allowed audio codecs
	 */
	private AudioCodecs[] getvalidatedAllowedAudioCodecs(){
		return FFmpegManager.getInstance().getSupportAudioCodec(videoContainer, audioInfoContainer);
	}
	
	
	/**
	 * Inits the gui.
	 */
	private void initGUI() {
		try {
			{
				this.setPreferredSize(new java.awt.Dimension(470, 297));
				{
					jPanelmain = new JPanel();
					GridBagLayout jPanelmainLayout = new GridBagLayout();
					this.setViewportView(jPanelmain);
					jPanelmainLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
					jPanelmainLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
					jPanelmainLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
					jPanelmainLayout.columnWidths = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
					jPanelmain.setLayout(jPanelmainLayout);
					{
						jLabelcodec = new JLabel();
						jPanelmain.add(jLabelcodec, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 0, 0), 0, 0));
						jLabelcodec.setText(LangTools.getResourceBundleWordLanguage(rb,"Codec","videoaudiogui.codec"));
					}
					{
						
						jComboBoxcodec = new JComboBox<AudioCodecs>();
						jPanelmain.add(jComboBoxcodec, new GridBagConstraints(3, 1, 6, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						jComboBoxcodec.setActionCommand(CHANGEAUDIOCODEC);
						jComboBoxcodec.addActionListener(this);
					}
					{
					
						jComboBoxbitrate = new JComboBox<String>();
						jPanelmain.add(jComboBoxbitrate, new GridBagConstraints(3, 4, 6, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						jComboBoxbitrate.setActionCommand(CHANGEAUDIOCONSTBITRATE);
						jComboBoxbitrate.addActionListener(this);
					}
					{
						jLabelfrequency = new JLabel();
						jPanelmain.add(jLabelfrequency, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 0, 0), 0, 0));
						jLabelfrequency.setText(LangTools.getResourceBundleWordLanguage(rb, "Frequency (Hz)", "audiogui.frequency"));
					}
					{
						
						jComboBoxfrequency = new JComboBox<AudioSampleRates>();
						jPanelmain.add(jComboBoxfrequency, new GridBagConstraints(3, 2, 6, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						
					}
					{
						jLabelchannels = new JLabel();
						jPanelmain.add(jLabelchannels, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 0, 0), 0, 0));
						jLabelchannels.setText(LangTools.getResourceBundleWordLanguage(rb,"Channels","general.channels"));
					}
					{
						
						jComboBoxChannels = new JComboBox<AudioChannels>();
						jPanelmain.add(jComboBoxChannels, new GridBagConstraints(3, 3, 6, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						jComboBoxChannels.setActionCommand(CHANGECHANNELS);
						jComboBoxChannels.addActionListener(this);
					}
					{
					
						CheckBoxvbr= new SteelCheckBox();
						jPanelmain.add(CheckBoxvbr, new GridBagConstraints(0, 6, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 0, 0), 0, 0));
						CheckBoxvbr.setText(LangTools.getResourceBundleWordLanguage(rb,"Variable Bitrate","general.variablebitrate"));
						CheckBoxvbr.setActionCommand(USEAUDIOVBR);
						CheckBoxvbr.addActionListener(this);
					}
					{
						
						CheckBoxcbr = new SteelCheckBox();
						jPanelmain.add(CheckBoxcbr, new GridBagConstraints(0, 4, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 0, 0), 0, 0));
						CheckBoxcbr.setText(LangTools.getResourceBundleWordLanguage(rb,"Constant Bitrate","general.constantbitrate"));
						CheckBoxcbr.setActionCommand(USEAUDIOCBR);
						CheckBoxcbr.addActionListener(this);
					}
					{
						jSlidervbr = new JSlider();
						jPanelmain.add(jSlidervbr, new GridBagConstraints(3, 6, 6, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					}
					{
						jLabelusedvbr = new JLabel();
						jPanelmain.add(jLabelusedvbr, new GridBagConstraints(3, 8, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						
						jLabelusedvbr.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
						jLabelusedvbr.setHorizontalAlignment(SwingConstants.CENTER);
					}
					{
						jLabelaverage = new JLabel();
						jPanelmain.add(jLabelaverage, new GridBagConstraints(3, 7, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jLabelaverage.setText(LangTools.getResourceBundleWordLanguage(rb, "Average(kbit/s)", "audiogui.average"));
						jLabelaverage.setFocusTraversalKeysEnabled(false);
						jLabelaverage.setHorizontalAlignment(SwingConstants.RIGHT);
					}
					{
						jLabelperchannel = new JLabel();
						jPanelmain.add(jLabelperchannel, new GridBagConstraints(6, 7, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jLabelperchannel.setText(LangTools.getResourceBundleWordLanguage(rb, "Bitrate per channel", "audiogui.bitrateperchannel"));
					}

					{
						jLabelbitperchannel = new JLabel();
						jPanelmain.add(jLabelbitperchannel, new GridBagConstraints(6, 8, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jLabelbitperchannel.setText(" ");
						jLabelbitperchannel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
						jLabelbitperchannel.setHorizontalAlignment(SwingConstants.CENTER);
					}
					{
						
						audioffmpegcmd= new SteelCheckBox();
						jPanelmain.add(audioffmpegcmd, new GridBagConstraints(0, 10, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 0, 0), 0, 0));
						audioffmpegcmd.setText(LangTools.getResourceBundleWordLanguage(rb,"Custom FFmpeg commands","general.extracmds"));
						String ffmpegtipcmd="Be careful! Wrong FFmpeg parameters can lead to errors in conversion process, please ensure if the commands are appropriate to the used codec";
						audioffmpegcmd.setToolTipText(LangTools.getResourceBundleWordLanguage(rb,ffmpegtipcmd,"tip.ffmpegcmd"));
						audioffmpegcmd.setActionCommand(EXTRAAUDIOFFMPEGCMD);
						audioffmpegcmd.addActionListener(this);
					}
					{
						jTextFieldAudioffmpegcmd = new CopyPasteJTextField();
						jPanelmain.add(jTextFieldAudioffmpegcmd, new GridBagConstraints(3, 10, 7, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					}
					{
						jCheckBoxffmpegcmd = new JCheckBox();
						jPanelmain.add(jCheckBoxffmpegcmd, new GridBagConstraints(0, 11, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 0, 0), 0, 0));
						jCheckBoxffmpegcmd.setText(LangTools.getResourceBundleWordLanguage(rb, "Use only custom cmds", "videoaudiogui.useonlyextracmd"));
						String tipuseaudiocmd="It is necessary to define the codec (example: -acodec aac -ab 128k)";
						jCheckBoxffmpegcmd.setToolTipText(LangTools.getResourceBundleWordLanguage(rb,tipuseaudiocmd,"tip.useonlyffmpegcmdaudio"));
						jCheckBoxffmpegcmd.setActionCommand(USEONLYAUDIOEXTRACMD);
						jCheckBoxffmpegcmd.addActionListener(this);
					}
				}
			}
		} catch(Exception e) {
		    Logger.error(e);
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String action= e.getActionCommand();
		
		if(action.equals(USEAUDIOCBR)){
			CheckBoxcbrSelectedAction();
			changeBitratePerChannel();
		}
		else if(action.equals(USEAUDIOVBR)){
			CheckBoxvbrSelectedAction();
			if(!usingFilesizeInVideo && ((AudioCodecs) jComboBoxcodec.getSelectedItem()).supportsVariableBitrate())
			    setJSliderSettingsToAudioCodecType(); 
		}
		else if(action.equals(CHANGEAUDIOCONSTBITRATE)){
			checkChangesjComboBoxbitrate();
			changeBitratePerChannel();
		}
		
		else if(action.equals(CHANGECHANNELS)){
			changeBitratePerChannel();
		}
		
		else if(action.equals(EXTRAAUDIOFFMPEGCMD)){
			checkuseExtraaudioFFmpegCMD();
		}
		
		else if(action.equals(USEONLYAUDIOEXTRACMD)){
			checkuseonlyextraffmpegcmds();
		}
			
		else if(action.equals(CHANGEAUDIOCODEC)){
			checkCopyCodecAction((AudioCodecs) jComboBoxcodec.getSelectedItem());
			setjComboBoxCodecAction();
			setAudioChannels((AudioCodecs) jComboBoxcodec.getSelectedItem());
			changeBitratePerChannel();
		}

	}
	
	

	  /**
  	 * Sets the parameters from encoding info container.
  	 *
  	 * @param info the new parameters from encoding info container
  	 */
  	public void setParametersFromEncodingInfoContainer(IGeneralVideoEncInfoContainer info){
		  this.infocont=info;
		  this.videoContainer=this.infocont.getVideoContainer();
		  changeListSupportedCodecs();
		  setAudioSampleRate();
		  setAudioChannels(this.infocont.getAudiocodec());
		  setParametersInsideEncodingInfoContainer();
		  
	  }
	
	  
	  /**
  	 * Sets the parameters inside encoding info container.
  	 */
  	protected void setParametersInsideEncodingInfoContainer(){
		  
		  AudioCodecs usecodec=this.infocont.getAudiocodec();
		  jComboBoxcodec.setSelectedItem(usecodec);
		  this.usingFilesizeInVideo=this.infocont.isUseVideoEncodingFileSize();
		  setAudioBitrateTypeFromInfoContainer();
		  
		  
		  copyChecker(usecodec);
		  
        if(!usecodec.equals(AudioCodecs.COPY)){
		  if(infocont.getExtraaudioffmpegcmd()!=null){
			    audioffmpegcmd.setSelected(true);
		    	checkuseExtraaudioFFmpegCMD();
		    	jTextFieldAudioffmpegcmd.setText(infocont.getExtraaudioffmpegcmd());
		    }
		  else{
			    audioffmpegcmd.setSelected(false);
			    jTextFieldAudioffmpegcmd.setText("");
		    	checkuseExtraaudioFFmpegCMD();
		    }
		  
		  
		  boolean useonlycmd=infocont.isUseonlyextraaudioffmpegcmd();
		    if(useonlycmd){
		    	jCheckBoxffmpegcmd.setSelected(true);
		    	checkuseonlyextraffmpegcmds();
		    }
		    else
		    	jCheckBoxffmpegcmd.setSelected(false);
        }
	  }
	  
	  /**
  	 * Copy checker.
  	 *
  	 * @param codec the codec
  	 */
  	private void copyChecker(AudioCodecs codec){
		  if(codec.equals(AudioCodecs.COPY)){
			  infocont.setExtraaudioffmpegcmd(null);
			  infocont.setUseonlyextraaudioffmpegcmd(false);
		  }
		  
	  }
	  
	  
	  /**
  	 * Sets the audio bitrate type from info container.
  	 */
  	private void setAudioBitrateTypeFromInfoContainer(){
		  
		  boolean audiouseCBR=this.infocont.isUseAudioCBR();
		  
		  if(audiouseCBR){
			   CheckBoxcbr.setSelected(true);
			   CheckBoxcbrSelectedAction();
			   setConstantBitrateValueInCombobox(this.infocont.getAudioConstantBitrateValue());
		  }
		  else{
			  if(!usingFilesizeInVideo && ((AudioCodecs) jComboBoxcodec.getSelectedItem()).supportsVariableBitrate()){
			     CheckBoxvbr.setSelected(true);
			     CheckBoxvbrSelectedAction();
			     setJSliderSettingsToAudioCodecType();
			  }
			  else{
				  CheckBoxcbrSelectedAction(); 
			  }
		  }
		  
	   }
	  
	  
	  /**
  	 * Check copy codec action.
  	 *
  	 * @param codec the codec
  	 */
  	protected void checkCopyCodecAction(AudioCodecs codec){
		  
		  if(codec.equals(AudioCodecs.COPY)){
			  jCheckBoxffmpegcmd.setSelected(false);
			  jCheckBoxffmpegcmd.setEnabled(false);
			  audioffmpegcmd.setSelected(false);
		      audioffmpegcmd.setEnabled(false);
			  jSlidervbr.setEnabled(false);
		      CheckBoxvbr.setEnabled(false);
		      CheckBoxcbr.setEnabled(false);
		      jComboBoxbitrate.setEnabled(false);
		      jComboBoxfrequency.setEnabled(false);
		      jComboBoxChannels.setEnabled(false);
		      jLabelusedvbr.setEnabled(false);
		      
		
		  }
		  else{
			  jSlidervbr.setEnabled(true);
		      CheckBoxvbr.setEnabled(true);
		      CheckBoxcbr.setEnabled(true);
		      jComboBoxbitrate.setEnabled(true);
		      jComboBoxfrequency.setEnabled(true);
		      jComboBoxChannels.setEnabled(true);
		      jLabelusedvbr.setEnabled(true);
		      audioffmpegcmd.setEnabled(true);
		  }

	  }
	  
	  
	  /**
  	 * Change bitrate per channel.
  	 */
  	protected void changeBitratePerChannel(){
		  int bit=0;
		  if(CheckBoxcbr.isSelected()){
			 bit= EncodingPropsAuxiliar.getBitrateValueInteger((String) jComboBoxbitrate.getSelectedItem());
		  }
		  else if(CheckBoxvbr.isSelected()){
			  bit=Integer.valueOf(jLabelusedvbr.getText()); 
		  }
			  
		  AudioChannels channels = (AudioChannels)jComboBoxChannels.getSelectedItem();
		  int nchannels=1;
		  if(channels!=null)
		     nchannels= channels.getchannels();
		  
		  int bitrateperchannel= bit/nchannels;
		  
		  jLabelbitperchannel.setText(String.valueOf(bitrateperchannel));
		  
	  }
	  
	  
	  /**
  	 * Check changesj combo boxbitrate.
  	 */
  	private void checkChangesjComboBoxbitrate(){
	    	
	    	String cbr= (String) jComboBoxbitrate.getSelectedItem();
	    	boolean elementofaudiocbr = VideoPanelUtils.elementOfAudioCBRValues(cbr);
	    	
	    	if(!elementofaudiocbr){
	    		
	    		String validatedCbr= EncodingPropsAuxiliar.getBitrateValue(cbr);
	    		if(validatedCbr==null){
                     Maingui.getInstance().showWarningMessage(LangTools.getResourceBundleWordLanguage(rb,"Bitrate must have a format similar to: 128k","audioinfo.warnbitrate"), 
                    		 LangTools.getResourceBundleWordLanguage(rb,"Warning","warngui.tag"));
	    			 jComboBoxbitrate.setSelectedItem("128k");
	    		}
	    		else
	    			setConstantBitrateValueInCombobox(validatedCbr);
	    	}
	    	
	    }
	  
	  
	  /**
  	 * Sets the constant bitrate value in combobox.
  	 *
  	 * @param value the new constant bitrate value in combobox
  	 */
  	private void setConstantBitrateValueInCombobox(String value){
	       
		  
		   int checkvalueindex=VideoPanelUtils.indexOfCBRValues(value, audiocbrvalues);
		   
		   if(checkvalueindex!=-1){
			   jComboBoxbitrate.setSelectedIndex(checkvalueindex);
		   }
		   else{
		   audiocbrvalues.addElement(value);
		   jComboBoxbitrate.setSelectedItem(value);
		   }
	  }
	  
	  
	  /**
  	 * Check boxcbr selected action.
  	 */
  	private void CheckBoxcbrSelectedAction(){
		  
      if((AudioCodecs) jComboBoxcodec.getSelectedItem()!=AudioCodecs.COPY){	  
		  if(usingFilesizeInVideo || !((AudioCodecs) jComboBoxcodec.getSelectedItem()).supportsVariableBitrate()){
			  CheckBoxcbr.setSelected(true);
			  jSlidervbr.setEnabled(false);
			  CheckBoxvbr.setSelected(false);
			  jComboBoxbitrate.setEnabled(true);
			  jComboBoxbitrate.setEditable(true);
			  jLabelusedvbr.setEnabled(false); 
		  }
		  else{
		  jSlidervbr.setEnabled(false);
		  CheckBoxvbr.setSelected(false);
		  jComboBoxbitrate.setEnabled(true);
		  jComboBoxbitrate.setEditable(true);
		  jLabelusedvbr.setEnabled(false);
		  }

        }
	  }
	  
	  /**
  	 * Check boxvbr selected action.
  	 */
  	private void CheckBoxvbrSelectedAction(){
		  
	   if((AudioCodecs) jComboBoxcodec.getSelectedItem()!=AudioCodecs.COPY){	  
		  if(!usingFilesizeInVideo && ((AudioCodecs) jComboBoxcodec.getSelectedItem()).supportsVariableBitrate()){
		      jSlidervbr.setEnabled(true);
		      CheckBoxvbr.setSelected(true);
		      jComboBoxbitrate.setEnabled(false);
		      jComboBoxbitrate.setEditable(false);
		      CheckBoxcbr.setSelected(false);
		      jLabelusedvbr.setEnabled(true);
		    }
		  else
			  CheckBoxcbrSelectedAction();
	   }
			  
		  
	  }
	  
	  /**
  	 * Checkuse extraaudio f fmpeg cmd.
  	 */
  	protected void checkuseExtraaudioFFmpegCMD(){
			if(audioffmpegcmd.isSelected()){
				jTextFieldAudioffmpegcmd.setEnabled(true);
				jTextFieldAudioffmpegcmd.setEditable(true);
				jCheckBoxffmpegcmd.setEnabled(true);
			}
			else{
				jTextFieldAudioffmpegcmd.setEnabled(false);
				jCheckBoxffmpegcmd.setEnabled(false);
				jCheckBoxffmpegcmd.setSelected(false);
				deactivateuseonlyextracmd();
				if(infocont!=null)
					  infocont.setExtraaudioffmpegcmd(null);
			}
		
		}
	  
	  /**
  	 * Checkuseonlyextraffmpegcmds.
  	 */
  	protected void checkuseonlyextraffmpegcmds(){
		  if(jTextFieldAudioffmpegcmd.getText().isEmpty()){
			  jCheckBoxffmpegcmd.setSelected(false);
		  }
		  else if(jCheckBoxffmpegcmd.isSelected()){
			  jSlidervbr.setEnabled(false);
		      CheckBoxvbr.setEnabled(false);
		      CheckBoxcbr.setEnabled(false);
		      jComboBoxbitrate.setEnabled(false);
		      jComboBoxfrequency.setEnabled(false);
		      jComboBoxChannels.setEnabled(false);
		      jLabelusedvbr.setEnabled(false);
		      jComboBoxcodec.setEnabled(false);
		      jLabelbitperchannel.setEnabled(false);
		  }
		  else{
			  deactivateuseonlyextracmd();
		  }
	  }
	  
	  /**
  	 * Deactivateuseonlyextracmd.
  	 */
  	protected void deactivateuseonlyextracmd(){
		  
		  CheckBoxcbr.setEnabled(true);
		  if(CheckBoxcbr.isSelected()){
			  jComboBoxbitrate.setEnabled(true);
			  jComboBoxbitrate.setEditable(true);
		  }
		  CheckBoxvbr.setEnabled(true);
		  if(CheckBoxvbr.isSelected()){
			 jSlidervbr.setEnabled(true); 
			 jLabelusedvbr.setEnabled(true);
		  }
		  
	      jComboBoxfrequency.setEnabled(true);
	      jComboBoxChannels.setEnabled(true);
	      jComboBoxcodec.setEnabled(true);
	      jLabelbitperchannel.setEnabled(true);
		  
	      if(infocont!=null)
	    	  infocont.setUseonlyextraaudioffmpegcmd(false);
	  }
	
	
     /**
  	 * Setj combo box codec action.
  	 */
  	private void setjComboBoxCodecAction(){
    	
    	if(usingFilesizeInVideo){
    		CheckBoxcbr.setSelected(true);
    		CheckBoxcbrSelectedAction();
    	}
    	else{ 
    	 if(CheckBoxvbr.isSelected()){
    		 CheckBoxvbrSelectedAction();
    		 if(((AudioCodecs) jComboBoxcodec.getSelectedItem()).supportsVariableBitrate())
    		    setJSliderSettingsToAudioCodecType(); 
    	 }
    	 else if(CheckBoxcbr.isSelected())
    		 CheckBoxcbrSelectedAction();
    	}
    	
    	checkIfCodecSuportMultichannels();
     }
     
     
     
     /**
      * Gets the video container.
      *
      * @return the video container
      */
     public VideoContainers getVideoContainer(){
    	 return this.videoContainer;
     }
     
     
     
    /**
     * Check if codec suport multichannels.
     */
    protected void checkIfCodecSuportMultichannels(){
    	 
    	 AudioCodecs codec = (AudioCodecs) jComboBoxcodec.getSelectedItem();
    	 
    	 if(codec.equals(AudioCodecs.MP3) || codec.equals(AudioCodecs.MP2))
    		 jComboBoxChannels.setSelectedItem(AudioChannels.STEREO);
    	 
     }
     
     
     /**
      * Save selected information into container.
      */
     public void saveSelectedInformationIntoContainer(){
    	 
    	 this.infocont.setAudiocodec((AudioCodecs) jComboBoxcodec.getSelectedItem());
    	 this.infocont.setAudiosamplerate((AudioSampleRates) jComboBoxfrequency.getSelectedItem());
    	 this.infocont.setAudiochannels((AudioChannels) jComboBoxChannels.getSelectedItem());
    	
    	 if(this.CheckBoxcbr.isSelected()){
    		 this.infocont.setUseAudioCBR(true);
    		 this.infocont.setAudioConstantBitrateValue((String) jComboBoxbitrate.getSelectedItem());
    	 }
    	 else if(this.CheckBoxvbr.isSelected()){
    		 this.infocont.setUseAudioCBR(false);
    		 this.infocont.setAudioVariableBitrateValues(jSlidervbr.getValue());
    	 }
    	 
    	 if(audioffmpegcmd.isSelected()){
    		String cmd = jTextFieldAudioffmpegcmd.getText();
    		if(cmd!=null && !cmd.isEmpty())
   		        this.infocont.setExtraaudioffmpegcmd(jTextFieldAudioffmpegcmd.getText());
    		else
    			this.infocont.setExtraaudioffmpegcmd(null);
    	 }
   	     else
   		     this.infocont.setExtraaudioffmpegcmd(null);
    	 
    	 if(jCheckBoxffmpegcmd.isSelected())
   		  this.infocont.setUseonlyextraaudioffmpegcmd(true);
   	  else
   		  this.infocont.setUseonlyextraaudioffmpegcmd(false);
    	 
    	 this.infocont.setModifiedtag(true);
     }
}
