package pt.ornrocha.rencoder.gui.components.panels.configurations.logs;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FilenameUtils;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.general.FFmpegLogLevel;
import pt.ornrocha.rencoder.ffmpegWrapper.utilities.FFmpegUtils;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.helpers.props.fields.StaticGlobalFields;
import pt.ornrocha.rencoder.helpers.props.logs.LogManager;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;
import pt.ornrocha.rencoder.mediafiles.setfiles.foldersandfiles.ListFiles;

public class RencoderLogsPanel extends JDialog implements ActionListener{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JPanel jPanelmainpanel;
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

    private JComboBox<Level> comboBoxorsvclog;
    private JLabel lblLogLevel;
    private JButton btnNewButtonorsvclog;

    /** The closelogspanel. */
    private static String CLOSELOGSPANEL="closelogspanel";

    /** The savelogscmd. */
    private static String SAVELOGSCMD="savelogscmd";

    /** The openlogsfolder. */
    private static String OPENLOGSFOLDER="openlogsfolder";

    /** The openlog. */
    private static String OPENFFMPEGLOG="openffmpeglog";

    private static String OPENRENCODERLOG="openorsvclog";

    /** The removelog. */
    private static String REMOVELOG="removelog";

    /** The REMOVEALLO gs. */
    private static String REMOVEALLOGs="removealllogs";

    /** The refreshlogs. */
    private static String REFRESHLOGS="refreshlogs";

    /** The rb. */
    private ResourceBundle rb;

    private Level initlevel=null;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	try {
	    RencoderLogsPanel dialog = new RencoderLogsPanel();
	    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    dialog.setVisible(true);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public RencoderLogsPanel() {
	rb = ResourceBundle.getBundle("lang",LangTools.getDefinedLanguage(), LangTools.loadLanguagesPath());
	initGui();
	this.setModal(true);
	initLogLevelCombobox();
	initsavedparameters();
	checkLogsInFolder();
    }

    /**
     * Create the dialog.
     */
    private void initGui() {
	setBounds(100, 100, 596, 403);
	getContentPane().setLayout(new BorderLayout());
	contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	getContentPane().add(contentPanel, BorderLayout.CENTER);
	GridBagLayout gbl_contentPanel = new GridBagLayout();
	gbl_contentPanel.columnWidths = new int[]{0, 0};
	gbl_contentPanel.rowHeights = new int[]{0, 0};
	gbl_contentPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
	gbl_contentPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
	contentPanel.setLayout(gbl_contentPanel);
	{
	    JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	    GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
	    gbc_tabbedPane.fill = GridBagConstraints.BOTH;
	    gbc_tabbedPane.gridx = 0;
	    gbc_tabbedPane.gridy = 0;
	    contentPanel.add(tabbedPane, gbc_tabbedPane);
	    {
		jPanelmainpanel = new JPanel();
		tabbedPane.addTab("FFmpeg", null, jPanelmainpanel, null);
		//getContentPane().add(jPanelmainpanel, BorderLayout.CENTER);
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
			jPanelconfigs.add(jComboBoxloglevel, new GridBagConstraints(1, 1, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

		    }
		    jPanelconfigsLayout.rowWeights = new double[] {0.1, 0.1};
		    jPanelconfigsLayout.rowHeights = new int[] {7, 7};
		    jPanelconfigsLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
		    jPanelconfigsLayout.columnWidths = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
		}
		{
		    jPanelsavedlogs = new JPanel();
		    GridBagLayout jPanelsavedlogsLayout = new GridBagLayout();
		    jPanelmainpanel.add(jPanelsavedlogs, new GridBagConstraints(0, 2, 10, 8, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
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
			jButtonopen.setActionCommand(OPENFFMPEGLOG);
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
	    }
	    {
		JPanel panelapplog = new JPanel();
		tabbedPane.addTab("App", null, panelapplog, null);
		GridBagLayout gbl_panelapplog = new GridBagLayout();
		gbl_panelapplog.columnWidths = new int[]{1,1,1,1,1,1,1};
		gbl_panelapplog.rowHeights = new int[]{1,1,1,1,1,1,1};
		gbl_panelapplog.columnWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		gbl_panelapplog.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		panelapplog.setLayout(gbl_panelapplog);
		{
		    lblLogLevel = new JLabel(LangTools.getResourceBundleWordLanguage(rb,"Log level:","log.level"));
		    GridBagConstraints gbc_lblLogLevel = new GridBagConstraints();
		    gbc_lblLogLevel.anchor = GridBagConstraints.EAST;
		    gbc_lblLogLevel.gridwidth = 2;
		    gbc_lblLogLevel.insets = new Insets(0, 0, 5, 5);
		    gbc_lblLogLevel.gridx = 0;
		    gbc_lblLogLevel.gridy = 1;
		    panelapplog.add(lblLogLevel, gbc_lblLogLevel);
		}
		{
		    comboBoxorsvclog = new JComboBox();
		    GridBagConstraints gbc_comboBoxorsvclog = new GridBagConstraints();
		    gbc_comboBoxorsvclog.gridwidth = 4;
		    gbc_comboBoxorsvclog.insets = new Insets(0, 0, 5, 5);
		    gbc_comboBoxorsvclog.fill = GridBagConstraints.HORIZONTAL;
		    gbc_comboBoxorsvclog.gridx = 2;
		    gbc_comboBoxorsvclog.gridy = 1;
		    panelapplog.add(comboBoxorsvclog, gbc_comboBoxorsvclog);
		}
		{
		    btnNewButtonorsvclog = new JButton(LangTools.getResourceBundleWordLanguage(rb,"Open log","log.openlog"));
		    btnNewButtonorsvclog.setActionCommand(OPENRENCODERLOG);
		    btnNewButtonorsvclog.addActionListener(this);
		    GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		    gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		    gbc_btnNewButton.gridwidth = 4;
		    gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		    gbc_btnNewButton.gridx = 2;
		    gbc_btnNewButton.gridy = 2;
		    panelapplog.add(btnNewButtonorsvclog, gbc_btnNewButton);
		}
	    }
	}
	{
	    JPanel buttonPane = new JPanel();
	    buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
	    getContentPane().add(buttonPane, BorderLayout.SOUTH);
	    {
		JButton okButton = new JButton(LangTools.getResourceBundleWordLanguage(rb,"Close","general.close"));
		okButton.setActionCommand(CLOSELOGSPANEL);
		okButton.addActionListener(this);
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	    }
	    /*  {
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	    }*/
	}
    }


    private void initLogLevelCombobox(){

	ComboBoxModel<FFmpegLogLevel> jComboBoxloglevelModel =new DefaultComboBoxModel<FFmpegLogLevel>(FFmpegLogLevel.values());
	jComboBoxloglevel.setModel(jComboBoxloglevelModel);
	ComboBoxModel<Level> jComboBoxorsvcloglevelModel =new DefaultComboBoxModel<Level>(Level.values());
	comboBoxorsvclog.setModel(jComboBoxorsvcloglevelModel);
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

	Level orsvclog = LogManager.getRencoderLogLevel();
	this.initlevel=orsvclog;
	comboBoxorsvclog.setSelectedItem(orsvclog);
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
    private void openlogfile(String filepath){
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

    private void openFFmpegLog() {
	int selectedItem = jListLogs.getSelectedIndex();
	if(listlogs!=null && selectedItem>=0){
	    String filepath=listlogs.get(selectedItem);
	    openlogfile(filepath);
	}
    }

    private void openRencoderLog() {
	String filepath=LogManager.getlogPath();
	openlogfile(filepath);
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

	Level newlevel=(Level)comboBoxorsvclog.getSelectedItem();
	PropertiesWorker.ChangePropertiesParam(pathconf, StaticGlobalFields.LOGLEVELRENCODER, newlevel.toString().toLowerCase());
	PropertiesWorker.ChangePropertiesParam(pathconf, StaticGlobalFields.LOGSAVEPROKEY, String.valueOf(writelogs));

	if(!newlevel.equals(initlevel))
	    LogManager.initialiseLogger();

    }

    /**
     * Open logs folder.
     */
    private void openLogsFolder(){
	String folder = new File(StaticGlobalFields.LOGSFOLDER).getAbsolutePath();
	ListFiles.OpenFileInSystem(folder, true);
    }

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
	else if(cmd.equals(OPENFFMPEGLOG))
	    openFFmpegLog();
	else if(cmd.equals(OPENLOGSFOLDER))
	    openLogsFolder();
	else if(cmd.equals(OPENRENCODERLOG))
	    openRencoderLog();

    }

}
