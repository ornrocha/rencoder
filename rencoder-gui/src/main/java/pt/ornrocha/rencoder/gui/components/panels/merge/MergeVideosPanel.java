package pt.ornrocha.rencoder.gui.components.panels.merge;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.commands.EncoderControlCenter;
import pt.ornrocha.rencoder.ffmpegWrapper.commands.EncoderInfoContainerExtractor;
import pt.ornrocha.rencoder.ffmpegWrapper.configurations.FFmpegLogInfoContainer;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.general.FFmpegLogLevel;
import pt.ornrocha.rencoder.ffmpegWrapper.execution.FFmpegMergeProcess;
import pt.ornrocha.rencoder.ffmpegWrapper.execution.KillFFmpegProcess;
import pt.ornrocha.rencoder.ffmpegWrapper.utilities.FFmpegUtils;
import pt.ornrocha.rencoder.gui.components.panels.Scroll.MergeVideosScrollpanel;
import pt.ornrocha.rencoder.gui.components.panels.info.EncodingProgressPanel;
import pt.ornrocha.rencoder.gui.components.panels.info.ProfileComboxRender;
import pt.ornrocha.rencoder.gui.components.panels.info.WarnJTextPanePanel;
import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.helpers.osystem.OSystem;
import pt.ornrocha.rencoder.helpers.props.fields.StaticFFmpegFields;
import pt.ornrocha.rencoder.helpers.props.managers.EncodingProfileManager;
import pt.ornrocha.rencoder.helpers.props.managers.GeneralEncodingPropertiesManager;
import pt.ornrocha.rencoder.mediafiles.files.containers.base.Videofile;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoCopyEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;
import pt.ornrocha.rencoder.mediafiles.setfiles.foldersandfiles.ListFiles;
import pt.ornrocha.rencoder.mediafiles.setfiles.processfiles.ProcessFiles;

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
public class MergeVideosPanel extends JFrame implements ActionListener, PropertyChangeListener{

	private static final long serialVersionUID = 1L;
	private MergeVideosScrollpanel jScrollPaneMovies;
	private JButton jButtonmerge;
	private JButton jButtonCancel;
	private JPanel jPanel1;
	private JComboBox<String> jComboBoxprofile;
	private JButton jButtonsave;
	private JButton jButtondelete;
	private JTextField jTextFieldname;
	private JButton jButtondown;
	private JButton jButtonup;

	protected EncodingProfileManager profiles;

	private IGeneralVideoEncInfoContainer infocontainer=null;
	private IGeneralVideoEncInfoContainer choosedinfoContainer= null;
	private boolean extconsistent = false;
	
	private String destfilename=null;
	private String destfileext=null;
	private String outtxtFile=null;
	
	private boolean convertfirst=false;
	
	private EncodingProgressPanel encodingPanel=null;
	private ConversionMergeListener convlistener=null;
	private EncoderControlCenter controlcenter=null;
	
	private MergeProgressPanel progresspanel=null;
	protected KillFFmpegProcess killer=null;

	
	private IndexedHashMap<String, String> convOutputPaths=null;
	private int numberfilestoconv=0;
	
	private static String CANCELMERGE="cancelmerge";
	private JCheckBox jCheckBoxconv;
	private JPanel jPanel2;
	private JButton jButtonclear;
	private String USERPROFTAG=null;
	private WarnJTextPanePanel warnpanel=null;
	
	
	private static String MOVEUP="moveup";
	private static String MOVEDOWN="movedown";
	private static String DELETESELECTED="deletselected";
	private static String CLEARALL="clearall";
	private static String SAVEIN="savein";
	private static String MERGEFILES="mergefiles";
	private static String CHOOSEPROFILE="chooseprofile";
	private static String COPYTAG="Copy";
	private static String CONVERTFIRST="convertfirst";
	
	

	
	public MergeVideosPanel(ArrayList<Videofile> files, EncodingProfileManager profiles, GeneralEncodingPropertiesManager genencinfomanager){
		USERPROFTAG=LangTools.getWordLanguage("User default profile","profilegui.userdefault");
		String iconpath = new File("icons/rencoderblue.png").getAbsolutePath();
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(iconpath));
		initGUI();
		this.profiles=profiles;
		this.infocontainer=genencinfomanager.getGenEncInfoContainer();
		this.jScrollPaneMovies.addProcessPropertyChangeListener(this);
		this.jScrollPaneMovies.setEncodingPropertiesManager(genencinfomanager);
		if(files!=null && files.size()>0){
			jScrollPaneMovies.addMovies(files);
		    extconsistent=jScrollPaneMovies.isextensionconsistent();
		    if(extconsistent)
		    	destfileext=jScrollPaneMovies.getLastMovieExt();
		}
		populateProfileComboBox();
		jComboBoxprofile.setRenderer(new ProfileComboxRender(genencinfomanager, profiles));
		
		setCloseWindowListener();
	}
	
	
	
	  private void setCloseWindowListener(){
		  this.addWindowListener(new java.awt.event.WindowAdapter() {
			    @Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    if(encodingPanel!=null)
                    	encodingPanel.stopAllProcessesAndClose();
                    killRunningProcesses();
        			if(progresspanel!=null)
        				progresspanel.dispose();
        			
        			deleteTempFiles();
        			dispose();
			    }
			});
	  }
	
	
	private void populateProfileComboBox(){
		
		String []profnames=null;
		
		if(extconsistent){
		  if(this.profiles!=null){
			profnames= new String[this.profiles.getnameProfiles().length+2];
			profnames[0]=COPYTAG;
			profnames[1]=USERPROFTAG;
			
			ArrayList<String> profilesnames=this.profiles.getArrayListOfProfileNames();
			Collections.sort(profilesnames);
			for (int i = 0; i < profilesnames.size(); i++) {
				profnames[i+2]=profilesnames.get(i);
			}
			
		}
		else{
			profnames= new String[2];
			profnames[0]=COPYTAG;
		    profnames[1]=USERPROFTAG;
		  }
		  this.choosedinfoContainer=new VideoCopyEncodingInfoContainer();
		}
		else{
			 if(this.profiles!=null){
					profnames= new String[this.profiles.getnameProfiles().length+1];
					profnames[0]=USERPROFTAG;
					
					ArrayList<String> profilesnames=this.profiles.getArrayListOfProfileNames();
					Collections.sort(profilesnames);
					for (int i = 0; i < profilesnames.size(); i++) {
						profnames[i+1]=profilesnames.get(i);
					}
					
				}
				else{
					profnames= new String[1];
				    profnames[0]=USERPROFTAG;
				  }
			this.destfileext=infocontainer.getFileextension();
			this.choosedinfoContainer=this.infocontainer;
			
		}
		jComboBoxprofile.removeAllItems();
		DefaultComboBoxModel<String> profilenames= new DefaultComboBoxModel<>(profnames);
		jComboBoxprofile.setModel(profilenames);
		
	}
	
	

	private void initGUI() {
		try {
			{
				GridBagLayout thisLayout = new GridBagLayout();
				thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				thisLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
				thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				thisLayout.columnWidths = new int[] {7, 7, 7, 7, 7, 7, 7, 7};
				getContentPane().setLayout(thisLayout);
				String panellabel="Concatenate Videos";
				this.setTitle(LangTools.getWordLanguage(panellabel, "mergegui.mergevideostag"));
				this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/rencoderbig.png")));
				{
					jScrollPaneMovies = new MergeVideosScrollpanel();
					getContentPane().add(jScrollPaneMovies, new GridBagConstraints(0, 0, 8, 10, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					jButtonmerge = new JButton();
					getContentPane().add(jButtonmerge, new GridBagConstraints(4, 13, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jButtonmerge.setText(LangTools.getWordLanguage("Merge", "mergegui.merge"));
					jButtonmerge.setActionCommand(MERGEFILES);
					jButtonmerge.addActionListener(this);
				}
				{
					jButtonCancel = new JButton();
					getContentPane().add(jButtonCancel, new GridBagConstraints(0, 13, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jButtonCancel.setText(LangTools.getWordLanguage("Cancel", "general.cancel"));
					jButtonCancel.setActionCommand(CANCELMERGE);
					jButtonCancel.addActionListener(this);
				}
				{
					jPanel1 = new JPanel();
					getContentPane().add(jPanel1, new GridBagConstraints(0, 10, 8, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					GridBagLayout jPanel1Layout = new GridBagLayout();
					jPanel1Layout.rowWeights = new double[] {0.1, 0.1, 0.1};
					jPanel1Layout.rowHeights = new int[] {7, 7, 7};
					jPanel1Layout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
					jPanel1Layout.columnWidths = new int[] {7, 7, 7, 7};
					jPanel1.setLayout(jPanel1Layout);
					{
						jButtonup = new JButton();
						jPanel1.add(jButtonup, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						jButtonup.setText(LangTools.getWordLanguage("Up", "general.up"));
						jButtonup.setActionCommand(MOVEUP);
						jButtonup.addActionListener(this);
					}
					{
						jButtondown = new JButton();
						jPanel1.add(jButtondown, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						jButtondown.setText(LangTools.getWordLanguage("Down", "general.down"));
						jButtondown.setActionCommand(MOVEDOWN);
						jButtondown.addActionListener(this);
					}
					{
						jButtondelete = new JButton();
						jPanel1.add(jButtondelete, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						jButtondelete.setText(LangTools.getWordLanguage("Delete", "mergegui.delete"));
						jButtondelete.setActionCommand(DELETESELECTED);
						jButtondelete.addActionListener(this);
					}
					{
						jTextFieldname = new JTextField();
						jPanel1.add(jTextFieldname, new GridBagConstraints(1, 2, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						jTextFieldname.setText("");
						jTextFieldname.setEditable(false);
					}
					{
						jButtonsave = new JButton();
						jPanel1.add(jButtonsave, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						jButtonsave.setText(LangTools.getWordLanguage("Save as", "mergegui.saveas"));
						jButtonsave.setActionCommand(SAVEIN);
						jButtonsave.addActionListener(this);
					}
					{
						jButtonclear = new JButton();
						jPanel1.add(jButtonclear, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						jButtonclear.setText(LangTools.getWordLanguage("Clear", "mergegui.clear"));
						jButtonclear.setActionCommand(CLEARALL);
						jButtonclear.addActionListener(this);
					}
					{
						jPanel2 = new JPanel();
						GridBagLayout jPanel2Layout = new GridBagLayout();
						jPanel1.add(jPanel2, new GridBagConstraints(0, 1, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jPanel2.setBorder(BorderFactory.createTitledBorder(LangTools.getWordLanguage("Conversion profiles", "maingui.conversionprofiles")));
						jPanel2Layout.rowWeights = new double[] {0.1};
						jPanel2Layout.rowHeights = new int[] {7};
						jPanel2Layout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
						jPanel2Layout.columnWidths = new int[] {7, 7, 7, 7};
						jPanel2.setLayout(jPanel2Layout);
						{
							
							jComboBoxprofile = new JComboBox();
							jPanel2.add(jComboBoxprofile, new GridBagConstraints(0, 0, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
							jComboBoxprofile.setActionCommand(CHOOSEPROFILE);
							jComboBoxprofile.addActionListener(this);
						}
						{
							jCheckBoxconv = new JCheckBox();
							jPanel2.add(jCheckBoxconv, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 20, 0, 0), 0, 0));
							jCheckBoxconv.setText(LangTools.getWordLanguage("Convert First", "mergegui.convertfirst"));
							jCheckBoxconv.setActionCommand(CONVERTFIRST);
							jCheckBoxconv.addActionListener(this);
							String tooltip = "If you have sound synchronization problems, please try with this option on";
							jCheckBoxconv.setToolTipText(LangTools.getWordLanguage(tooltip, "tip.mergeconv"));
						
						}
					}
				}
			}
			{
				this.setSize(438, 493);
			}
		} catch(Exception e) {
		    Logger.error(e);
		}
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals(CANCELMERGE)){
			if(encodingPanel!=null){
				encodingPanel.stopAllProcessesAndClose();
				encodingPanel=null;
			}
			
			killRunningProcesses();
			if(progresspanel!=null)
				progresspanel.dispose();
			
			deleteTempFiles();
			this.dispose();
		}
		else if(cmd.equals(SAVEIN)){
			getSaveFileInFolder();
		}
		else if(cmd.equals(MOVEUP))
			jScrollPaneMovies.moveUp();
		else if(cmd.equals(MOVEDOWN))
			jScrollPaneMovies.moveDown();
		else if(cmd.equals(DELETESELECTED))
			jScrollPaneMovies.deleteRows();
		else if(cmd.equals(CLEARALL)){
			jScrollPaneMovies.ClearList();
		}
		else if(cmd.equals(CONVERTFIRST)){
			String profile = (String) jComboBoxprofile.getSelectedItem();
			if(profile!=null){
			if(profile.equals(COPYTAG)){
				jCheckBoxconv.setSelected(false);
				}
			}
		}
		else if(cmd.equals(MERGEFILES))
			if(jTextFieldname.getText().isEmpty()){
				String defineout = "Please define the output filename";
				warnpanel=new WarnJTextPanePanel(LangTools.getWordLanguage(defineout, "mergegui.defineoutfile"), this);
			}
			else{
			try {
				processMerge();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		else if(cmd.equals(CHOOSEPROFILE))
			 changeProfile();
			
		
	}
	
	
	private void getSaveFileInFolder(){
		String filesave = ListFiles.getSingleFileSavePath(this);
		if(filesave!=null){
			destfilename=filesave;
			changeSaveFilename();
		}
	}
	
	private void changeSaveFilename(){
		String filename=null;
	 if(destfilename!=null){	
		if(destfileext!=null)
		   filename=destfilename+"."+destfileext;
		else
		   filename=destfilename+"."+getExtensionOfProfile();
		jTextFieldname.setText(filename);
	 }
	}
	
	private void changeProfile(){

		String profile = (String) jComboBoxprofile.getSelectedItem();
		if(profile!=null){
		if(profile.equals(COPYTAG)){
			this.destfileext=jScrollPaneMovies.getLastMovieExt();
			changeSaveFilename();
			this.choosedinfoContainer= new VideoCopyEncodingInfoContainer();
			this.jCheckBoxconv.setSelected(false);
		}
		else if(profile.equals(USERPROFTAG)){
			this.destfileext=infocontainer.getVideoContainer().toString();
			changeSaveFilename();
			this.choosedinfoContainer=this.infocontainer;
		}
		else{
			this.destfileext=profiles.getEncodingInfoContainerbyname(profile).getVideoContainer().toString();
			changeSaveFilename();
			this.choosedinfoContainer=profiles.getEncodingInfoContainerbyname(profile);
		   }
		}

	}
	
	
	private String getExtensionOfProfile(){
		if(jComboBoxprofile.getSelectedIndex()==0)
			return infocontainer.getVideoContainer().toString();
		else{
			return profiles.getEncodingInfoContainer(jComboBoxprofile.getSelectedIndex()-1).getVideoContainer().toString();
		}
	}
	

    
	private void checkMovieExtensions(){
		this.extconsistent = jScrollPaneMovies.isextensionconsistent();
		if(extconsistent){
			this.destfileext=jScrollPaneMovies.getLastMovieExt();
		}
		else{
			this.destfileext=getExtensionOfProfile();
		}
		changeSaveFilename();
		populateProfileComboBox();
	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String cmd = evt.getPropertyName();
		
		if(cmd.equals(MergeVideosScrollpanel.NEWMOVIESADDED)){
			checkMovieExtensions();
		}
		else if(cmd.equals(ConversionMergeListener.STARTENCODING)){
	
		}
		else if(cmd.equals(ConversionMergeListener.COMPLETEDENCODINGPROCESS)){
	
		}
		else if (cmd.equals(ConversionMergeListener.FINISHEDENCODING)){
			encodingPanel.dispose();
			encodingPanel=null;
			try {
				MergeAfterConversion();
			} catch (IOException e) {
			    Logger.error(e);
			}
		}
		else if(cmd.equals(EncodingProgressPanel.CLOSEPROCESSES)){
			this.encodingPanel=null;
			deleteTempFiles();
		}
		
		
	}
	

	
	
	private void processMerge() throws InterruptedException{
		
		ArrayList<Videofile> moviefiles = jScrollPaneMovies.getListOfMovies();
		
		if(jCheckBoxconv.isSelected()){
			
			this.choosedinfoContainer.setOutputFolder(OSystem.getTempFolder());
			ProcessFiles.setSameConvConfigInAllMovies(choosedinfoContainer, moviefiles);
			IndexedHashMap<String, Videofile> convertvideos = ProcessFiles.makeconvertList(moviefiles);
			this.convertfirst=true;
			launchConversionProcessPanel(convertvideos);
		}
		else{
			MergePreprocessFiles();
		}
	}
	
	
	private void launchConversionProcessPanel(IndexedHashMap<String, Videofile> convertvideos){
		this.controlcenter=new EncoderControlCenter(convertvideos);
		this.convOutputPaths=controlcenter.getOutputMovieFilePaths();
		convlistener = new ConversionMergeListener(this);
		this.encodingPanel = new EncodingProgressPanel(controlcenter.getInfoToConvertFiles(),convlistener,true);
		encodingPanel.hideShutdownTag();
	    encodingPanel.addProcessesPropertyChangeListener(this);
	    encodingPanel.setLocationRelativeTo(this);
	    encodingPanel.setVisible(true);
		Thread t = new Thread(encodingPanel);
		t.start();
		
	}
	
	
	private void MergeAfterConversion() throws IOException{
		ArrayList<String> listout = convertOutputPaths(convOutputPaths);
		buildFFmpegMergeTxtFile(listout);
		ArrayList<String> cmds = buildMergeCMDs();
		this.killer=new KillFFmpegProcess();
		FFmpegLogInfoContainer log= getFFmpegLogInfoofMergeProcess();
		progresspanel = new MergeProgressPanel(new FFmpegMergeProcess(cmds,killer,log), convOutputPaths.size(), this);
		Thread t = new Thread(progresspanel);
		t.run();
	}
	
   
	
	private void MergePreprocessFiles(){
		ArrayList<String> list = jScrollPaneMovies.getMoviesFilePaths();
		try {
			buildFFmpegMergeTxtFile(list);
			Mergeprocess();
		} catch (IOException e) {
		    Logger.error(e);
		}
	}
	
	private void Mergeprocess(){
		ArrayList<String> cmds = buildMergeCMDs();
		this.killer=new KillFFmpegProcess();
		FFmpegLogInfoContainer log= getFFmpegLogInfoofMergeProcess();
		progresspanel = new MergeProgressPanel(new FFmpegMergeProcess(cmds,killer,log), numberfilestoconv, this);
		Thread t = new Thread(progresspanel);
		t.run();
	}
	
	
	public void killRunningProcesses(){
		if(killer!=null){
			Thread t = new Thread(killer);
			t.run();
		}
		deleteFile(jTextFieldname.getText());
	}
	
	
	private void buildFFmpegMergeTxtFile(ArrayList<String> files) throws IOException{
		ArrayList<String> output = new ArrayList<>();
		numberfilestoconv=0;
		for (String path : files) {
			String f = "file '"+path+"'";
			output.add(f);
			numberfilestoconv++;
		}
		
		String outdir=OSystem.getTempFolder();
		outtxtFile=ProcessFiles.checkIfFileExistsandReturnNewname(outdir, "mergetemp", "txt", 0, false);
		File f = new File(outtxtFile);
		FileUtils.writeLines(f, output);
	}
	
	private ArrayList<String> convertOutputPaths(IndexedHashMap<String, String> paths){
		ArrayList<String> outpaths = new ArrayList<>();
		for (int i = 0; i < paths.size(); i++) {
			outpaths.add(paths.getValueAt(i));
		}
		return outpaths;
	}
	
	
	private ArrayList<String> buildMergeCMDs(){
		ArrayList<String> cmd = new ArrayList<>();
		
		
		cmd.add(EncoderControlCenter.getEncoderExePath());
		cmd.add(StaticFFmpegFields.force_video_format);
		cmd.add(StaticFFmpegFields.CONCAT);
		cmd.add(StaticFFmpegFields.input);
		cmd.add(outtxtFile);
		//cmd.add("-report");
	    cmd.add(StaticFFmpegFields.overwrite_output_file);
	    cmd.add("-loglevel");
		cmd.add("debug");
		
		
		
		if(convertfirst){
			
			cmd.add(StaticFFmpegFields.CTAG);
			cmd.add(StaticFFmpegFields.copy);
			cmd.add(jTextFieldname.getText());
		}
		else{
			
			if(this.choosedinfoContainer instanceof VideoCopyEncodingInfoContainer){
				cmd.add(StaticFFmpegFields.CTAG);
				cmd.add(StaticFFmpegFields.copy);
				cmd.add(jTextFieldname.getText());
			}
			else{
		
				ArrayList<String> videocmds = EncoderInfoContainerExtractor.getVideoInfoCmds(choosedinfoContainer, false, false, false);
				cmd.addAll(videocmds);
				
				ArrayList<String> audiocmds = EncoderInfoContainerExtractor.getAudioInfoCmds(choosedinfoContainer, false);
				cmd.addAll(audiocmds);
				
				cmd.add(jTextFieldname.getText());
			}
		}
		
		return cmd;
	}
	
	public void deleteTempFiles(){
		if(outtxtFile!=null)
		   deleteFile(outtxtFile);
	 
	if(convOutputPaths!=null){	
		if(convertfirst){
			for (Map.Entry<String, String> map : convOutputPaths.entrySet()) {
				deleteFile(map.getValue());
			}
		}
	  }
	}
	
	private void deleteFile(String path){
		File f = new File(path);
		if(f.exists())
			f.delete();
	}
	
	private FFmpegLogInfoContainer getFFmpegLogInfoofMergeProcess(){
		
    	String filename = FilenameUtils.getBaseName(jTextFieldname.getText());
    	FFmpegLogLevel loglevel = FFmpegUtils.getSettingsFFmpegLogLevel();
    	FFmpegLogInfoContainer loginfo = null;
    	
    	if(loglevel!=null)
    		loginfo=new FFmpegLogInfoContainer(filename, loglevel);
    	else
    		loginfo=new FFmpegLogInfoContainer(filename);
 
    	if(FFmpegUtils.isFFmpegWriteLog())
    		loginfo.setActive(true);
    	
    	return loginfo;

	  }


}
