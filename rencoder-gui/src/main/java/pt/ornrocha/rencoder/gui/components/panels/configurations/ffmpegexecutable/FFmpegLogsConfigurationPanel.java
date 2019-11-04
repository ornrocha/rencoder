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
package pt.ornrocha.rencoder.gui.components.panels.configurations.ffmpegexecutable;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FilenameUtils;
import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.general.FFmpegLogLevel;
import pt.ornrocha.rencoder.ffmpegWrapper.utilities.FFmpegUtils;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.helpers.props.fields.StaticGlobalFields;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;
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
public class FFmpegLogsConfigurationPanel extends JDialog implements ActionListener{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The j panelmainpanel. */
	private JPanel jPanelmainpanel;
	
	/** The j panelconfigs. */
	private JPanel jPanelconfigs;
	
	/** The j panelsavedlogs. */
	private JPanel jPanelsavedlogs;
	
	/** The j buttonrefresh. */
	private JButton jButtonrefresh;
	
	/** The j buttonclearall. */
	private JButton jButtonclearall;
	
	/** The j buttonremlog. */
	private JButton jButtonremlog;
	
	/** The j buttonopenfolder. */
	private JButton jButtonopenfolder;
	
	/** The j combo boxloglevel. */
	private JComboBox<FFmpegLogLevel> jComboBoxloglevel;
	
	/** The j buttonclose. */
	private JButton jButtonclose;
	
	/** The j labelloglevel. */
	private JLabel jLabelloglevel;
	
	/** The j buttonopen. */
	private JButton jButtonopen;
	
	/** The j list logs. */
	private JList<String> jListLogs;
	
	/** The j check boxsavelogs. */
	private JCheckBox jCheckBoxsavelogs;
	
	/** The j scroll panelistlogs. */
	private JScrollPane jScrollPanelistlogs;
	
	/** The listlogs. */
	private ArrayList<String> listlogs=null;
	
	
	/** The closelogspanel. */
	private static String CLOSELOGSPANEL="closelogspanel";
	
	/** The savelogscmd. */
	private static String SAVELOGSCMD="savelogscmd";
	
	/** The openlogsfolder. */
	private static String OPENLOGSFOLDER="openlogsfolder";
	
	/** The openlog. */
	private static String OPENLOG="openlog";
	
	/** The removelog. */
	private static String REMOVELOG="removelog";
	
	/** The REMOVEALLO gs. */
	private static String REMOVEALLOGs="removealllogs";
	
	/** The refreshlogs. */
	private static String REFRESHLOGS="refreshlogs";
	
	/** The rb. */
	private ResourceBundle rb;
	
	/**
	 * Instantiates a new f fmpeg logs configuration panel.
	 */
	public FFmpegLogsConfigurationPanel (){
		rb = ResourceBundle.getBundle("lang",LangTools.getDefinedLanguage(), LangTools.loadLanguagesPath());
		initGUI();
		this.setModal(true);
		initLogLevelCombobox();
		initsavedparameters();
		checkLogsInFolder();
	}
	
	
	/**
	 * Inits the log level combobox.
	 */
	private void initLogLevelCombobox(){
		
		ComboBoxModel<FFmpegLogLevel> jComboBoxloglevelModel =new DefaultComboBoxModel<FFmpegLogLevel>(FFmpegLogLevel.values());
		jComboBoxloglevel.setModel(jComboBoxloglevelModel);
	}
	
	/**
	 * Initsavedparameters.
	 */
	private void initsavedparameters(){
		PropertiesConfiguration prop = PropertiesWorker.loadPropertiesRelativePath(StaticGlobalFields.RENCODERCONFIGFILE);
		checkSaveParam(prop);
		checkLoglevelParam();
	}
	
	
	/**
	 * Check save param.
	 *
	 * @param prop the prop
	 */
	private void checkSaveParam(PropertiesConfiguration prop){
		if(prop.containsKey(StaticGlobalFields.LOGSAVEPROKEY)){
	    	if(!prop.getProperty(StaticGlobalFields.LOGSAVEPROKEY).toString().isEmpty()){
	    		String save=(String) prop.getProperty(StaticGlobalFields.LOGSAVEPROKEY);
	    		if(save.toLowerCase().equals("true"))
	    			jCheckBoxsavelogs.setSelected(true);
	    		else
	    			jCheckBoxsavelogs.setSelected(false);
	    	}
	    	else
	    		jCheckBoxsavelogs.setSelected(false);
	    }
	    else
	    	jCheckBoxsavelogs.setSelected(false);
		
	}
	
	/**
	 * Check loglevel param.
	 *
	 * @param prop the prop
	 */
	private void checkLoglevelParam(){
		
		//Logger.getLevel();
		FFmpegLogLevel log = FFmpegUtils.getSettingsFFmpegLogLevel();
		if(log!=null)
			jComboBoxloglevel.setSelectedItem(log);
		else
			jComboBoxloglevel.setSelectedItem(FFmpegLogLevel.INFO);
	}
	
	
	
	
	/**
	 * Check logs in folder.
	 */
	private void checkLogsInFolder(){
		String folderlogs= new File(StaticGlobalFields.LOGSFOLDER).getAbsolutePath();
		listlogs=ListFiles.getFilesInDirectory(folderlogs, true);
		DefaultListModel<String> listmodel = new DefaultListModel<String>();
		
		for (String log : listlogs) {
			String name = FilenameUtils.getBaseName(log);
			listmodel.addElement(name);
		}
       
		jListLogs.setModel(listmodel);
		
	}

	
	/**
	 * Inits the gui.
	 */
	private void initGUI() {
		try {
			{
				jPanelmainpanel = new JPanel();
				getContentPane().add(jPanelmainpanel, BorderLayout.CENTER);
				GridBagLayout jPanelmainpanelLayout = new GridBagLayout();
				jPanelmainpanelLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				jPanelmainpanelLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
				jPanelmainpanelLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				jPanelmainpanelLayout.columnWidths = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
				jPanelmainpanel.setLayout(jPanelmainpanelLayout);
				this.setTitle("Logs");
				{
					jPanelconfigs = new JPanel();
					GridBagLayout jPanelconfigsLayout = new GridBagLayout();
					jPanelconfigs.setLayout(jPanelconfigsLayout);
					jPanelmainpanel.add(jPanelconfigs, new GridBagConstraints(0, 0, 10, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPanelconfigs.setBorder(BorderFactory.createTitledBorder(LangTools.getResourceBundleWordLanguage(rb,"FFmpeg Log configurations","log.config")));
					{
						jCheckBoxsavelogs = new JCheckBox();
						jPanelconfigs.add(jCheckBoxsavelogs, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jCheckBoxsavelogs.setText(LangTools.getResourceBundleWordLanguage(rb,"Save FFmpeg logs","log.save"));
						jCheckBoxsavelogs.setActionCommand(SAVELOGSCMD);
						jCheckBoxsavelogs.addActionListener(this);
					}
					{
						jLabelloglevel = new JLabel();
						jPanelconfigs.add(jLabelloglevel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jLabelloglevel.setText(LangTools.getResourceBundleWordLanguage(rb,"Log level:","log.level"));
					}
					{
						
						jComboBoxloglevel = new JComboBox<FFmpegLogLevel>();
						jPanelconfigs.add(jComboBoxloglevel, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						
					}
					jPanelconfigsLayout.rowWeights = new double[] {0.1, 0.1};
					jPanelconfigsLayout.rowHeights = new int[] {7, 7};
					jPanelconfigsLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
					jPanelconfigsLayout.columnWidths = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
				}
				{
					jPanelsavedlogs = new JPanel();
					GridBagLayout jPanelsavedlogsLayout = new GridBagLayout();
					jPanelmainpanel.add(jPanelsavedlogs, new GridBagConstraints(0, 2, 10, 7, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPanelsavedlogsLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
					jPanelsavedlogsLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7};
					jPanelsavedlogsLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
					jPanelsavedlogsLayout.columnWidths = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
					jPanelsavedlogs.setLayout(jPanelsavedlogsLayout);
					jPanelsavedlogs.setBorder(BorderFactory.createTitledBorder(LangTools.getResourceBundleWordLanguage(rb,"Output FFmpeg Logs","log.outlogs")));
					{
						jScrollPanelistlogs = new JScrollPane();
						jPanelsavedlogs.add(jScrollPanelistlogs, new GridBagConstraints(0, 0, 9, 6, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						{
						
							jListLogs = new JList<String>();
							jScrollPanelistlogs.setViewportView(jListLogs);
						
						}
					}
					{
						jButtonopen = new JButton();
						jPanelsavedlogs.add(jButtonopen, new GridBagConstraints(9, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						jButtonopen.setText(LangTools.getResourceBundleWordLanguage(rb,"Open log","log.openlog"));
						jButtonopen.setActionCommand(OPENLOG);
						jButtonopen.addActionListener(this);
					}
					{
						jButtonremlog = new JButton();
						jPanelsavedlogs.add(jButtonremlog, new GridBagConstraints(9, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						jButtonremlog.setText(LangTools.getResourceBundleWordLanguage(rb,"Remove log","log.remlog"));
						jButtonremlog.setActionCommand(REMOVELOG);
						jButtonremlog.addActionListener(this);
					}
					{
						jButtonclearall = new JButton();
						jPanelsavedlogs.add(jButtonclearall, new GridBagConstraints(9, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						jButtonclearall.setText(LangTools.getResourceBundleWordLanguage(rb,"Clear all logs","log.clearalllogs"));
						jButtonclearall.setActionCommand(REMOVEALLOGs);
						jButtonclearall.addActionListener(this);
					}
					{
						jButtonrefresh = new JButton();
						jPanelsavedlogs.add(jButtonrefresh, new GridBagConstraints(9, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						jButtonrefresh.setText(LangTools.getResourceBundleWordLanguage(rb,"Refresh logs","log.refresh"));
						jButtonrefresh.setActionCommand(REFRESHLOGS);
						jButtonrefresh.addActionListener(this);
					}
					{
						jButtonopenfolder = new JButton();
						jPanelsavedlogs.add(jButtonopenfolder, new GridBagConstraints(9, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						jButtonopenfolder.setText(LangTools.getResourceBundleWordLanguage(rb,"Open folder","general.open.outputfolder"));
						jButtonopenfolder.setActionCommand(OPENLOGSFOLDER);
						jButtonopenfolder.addActionListener(this);
					}
				}
				{
					jButtonclose = new JButton();
					jPanelmainpanel.add(jButtonclose, new GridBagConstraints(3, 9, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jButtonclose.setText(LangTools.getResourceBundleWordLanguage(rb,"Close","general.close"));
					jButtonclose.setActionCommand(CLOSELOGSPANEL);
					jButtonclose.addActionListener(this);
				}
			}
			{
				this.setSize(611, 394);
			}
		} catch(Exception e) {
		    Logger.error(e);
		}
	}
	
	
	/**
	 * Refreshlogs.
	 */
	private void refreshlogs(){
		checkLogsInFolder();
	}
	
	/**
	 * Removes the logs.
	 */
	private void removeLogs(){
		if(listlogs!=null){
			if(listlogs.size()>0)
				ListFiles.removeFiles(listlogs);
			
			refreshlogs();
		}
	}
	
	/**
	 * Removeselectedlog.
	 */
	private void removeselectedlog(){
		int selectedItem = jListLogs.getSelectedIndex();
		if(listlogs!=null && selectedItem>=0){
		String filepath=listlogs.get(selectedItem);
		ArrayList<String> list = new ArrayList<>();
		list.add(filepath);
		ListFiles.removeFiles(list);
		refreshlogs();
		}
	}
	
	/**
	 * Openlogfile.
	 */
	private void openlogfile(){
		int selectedItem = jListLogs.getSelectedIndex();
		if(listlogs!=null && selectedItem>=0){
			String filepath=listlogs.get(selectedItem);
		   try {
			    File logfile = new File(filepath);
			    if(Desktop.isDesktopSupported()){
			    	Desktop.getDesktop().open(logfile);
			    }
			    else{
			    	ListFiles.OpenSystemFolder(FilenameUtils.getPath(filepath));
			    }
		   } catch (Exception e) {
		       Logger.error(e);
		   }
		}
	}
	
	
	/**
	 * Write config settings.
	 */
	private void writeConfigSettings(){
		boolean writelogs=false;
		String loglevel="";
		String pathconf=new File(StaticGlobalFields.RENCODERCONFIGFILE).getAbsolutePath();
		
		if(jCheckBoxsavelogs.isSelected()){
			writelogs=true;
			loglevel=((FFmpegLogLevel)jComboBoxloglevel.getSelectedItem()).toString();
            PropertiesWorker.ChangePropertiesParam(pathconf, StaticGlobalFields.LOGLEVELKEY, loglevel);
		}
		
		PropertiesWorker.ChangePropertiesParam(pathconf, StaticGlobalFields.LOGSAVEPROKEY, String.valueOf(writelogs));
		
	}
	
	/**
	 * Open logs folder.
	 */
	private void openLogsFolder(){
		String folder = new File(StaticGlobalFields.LOGSFOLDER).getAbsolutePath();
		ListFiles.OpenFileInSystem(folder, true);
	}
	
	

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals(CLOSELOGSPANEL)){
			writeConfigSettings();
			this.dispose();
		}
		else if (cmd.equals(REFRESHLOGS)) {
			refreshlogs();
		}
		else if(cmd.equals(REMOVEALLOGs))
			removeLogs();
		else if (cmd.equals(REMOVELOG))
			removeselectedlog();
		else if(cmd.equals(OPENLOG))
			openlogfile();
		else if(cmd.equals(OPENLOGSFOLDER))
			openLogsFolder();
		
	}



/**
 * The main method.
 *
 * @param args the arguments
 */
public static void main(String[] args){
	String dir = "/home/orocha/eclipse_workspace/VideoConverter/Logs/";
	for (int i = 0; i < 1000; i++) {
		
		File fl = new File(dir+"log_"+i+".log");
		try {
			fl.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		    Logger.error(e);
		}
	}
	
}

}