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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumnModel;

import org.apache.commons.io.FilenameUtils;
import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.commands.FileInformationChecker;
import pt.ornrocha.rencoder.ffmpegWrapper.configurations.ConfigureFFmpegExecutablePath;
import pt.ornrocha.rencoder.ffmpegWrapper.configurations.FFmpegManager;
import pt.ornrocha.rencoder.ffmpegWrapper.configurations.FFmpegParametersChecker;
import pt.ornrocha.rencoder.gui.Maingui;
import pt.ornrocha.rencoder.gui.components.tables.GenericTableViewerModel;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.mediafiles.setfiles.foldersandfiles.ListFiles;

// TODO: Auto-generated Javadoc
/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class ConfigurationFFmpegExecutable extends JDialog implements ActionListener {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The j buttonok. */
    private JButton jButtonok;

    /** The j buttoncancel. */
    private JButton jButtoncancel;

    /** The j text fieldcurrent. */
    private JTextField jTextFieldcurrent;

    /** The j buttongetffmpeg. */
    private JButton jButtongetffmpeg;

    /** The j panelcurrentversion. */
    private JPanel jPanelcurrentversion;
    private JScrollPane jScrollPanefindfmmpeg;

    /** The j check boximport. */
    private JCheckBox jCheckBoximport;

    /** The j text fieldpath. */
    private JTextField jTextFieldpath;

    /** The j buttonopen. */
    private JButton jButtonopen;

    /** The errorpanel. */
    private ConfigurationFFmpegErrorsPanel errorpanel = null;

    /** The F fmpeg exe path. */
    private String FFmpegExePath = null;
    // private String FFmpegVersion=null;

    /** The openffmpegexecutable. */
    private static String OPENFFMPEGEXECUTABLE = "openffmpegexecutable";

    /** The cancelffmpegexecutableoperation. */
    private static String CANCELFFMPEGEXECUTABLEOPERATION = "cancelffmpegoperation";

    /** The validateandsetffmpegexecutable. */
    private static String VALIDATEANDSETFFMPEGEXECUTABLE = "validateandsetffmpegexecutable";

    /** The getffmpeg. */
    private static String GETFFMPEG = "getffmpeg";

    /** The rb. */
    private ResourceBundle rb;

    private JTable tableffmpeg;

    private JFrame mainframe;

    // private ArrayList<String> internalversion;

    /**
     * Instantiates a new configuration ffmpeg executable.
     */
    public ConfigurationFFmpegExecutable(JFrame mainframe) {
	this.mainframe = mainframe;
	rb = ResourceBundle.getBundle("lang", LangTools.getDefinedLanguage(), LangTools.loadLanguagesPath());
	initGUI();
	this.setModal(true);
	findFFmpegVersions();
	setCurrentFFmpegPath();

    }

    /**
     * Inits the gui.
     */
    private void initGUI() {
	try {
	    {

		GridBagLayout thisLayout = new GridBagLayout();
		thisLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1 };
		thisLayout.rowHeights = new int[] { 7, 7, 7, 7, 7, 7, 7, 7, 7 };
		thisLayout.columnWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.0, 0.1, 0.1 };

		thisLayout.columnWidths = new int[] { 7, 7, 7, 7, 7, 7, 7, -1, 7, 7 };
		getContentPane().setLayout(thisLayout);
		this.setTitle(LangTools.getResourceBundleWordLanguage(rb, "FFmpeg executable configuration",
			"ffmpegconfgui.configuration"));
		{
		    jButtonok = new JButton();
		    getContentPane().add(jButtonok, new GridBagConstraints(8, 8, 3, 1, 0.0, 0.0,
			    GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		    jButtonok.setText(LangTools.getResourceBundleWordLanguage(rb, "Validate and Set",
			    "ffmpegconfgui.validateandset"));
		    jButtonok.setActionCommand(VALIDATEANDSETFFMPEGEXECUTABLE);
		    jButtonok.addActionListener(this);
		}
		{
		    jButtoncancel = new JButton();
		    getContentPane().add(jButtoncancel, new GridBagConstraints(4, 8, 3, 1, 0.0, 0.0,
			    GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		    jButtoncancel.setText(LangTools.getResourceBundleWordLanguage(rb, "Cancel", "general.cancel"));
		    jButtoncancel.setActionCommand(CANCELFFMPEGEXECUTABLEOPERATION);
		    jButtoncancel.addActionListener(this);
		}
		{
		    jButtonopen = new JButton();
		    getContentPane().add(jButtonopen, new GridBagConstraints(0, 6, 3, 1, 0.0, 0.0,
			    GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		    jButtonopen.setText(LangTools.getResourceBundleWordLanguage(rb, "Load", "general.load"));
		    jButtonopen.setActionCommand(OPENFFMPEGEXECUTABLE);
		    jButtonopen.addActionListener(this);
		}
		{
		    jTextFieldpath = new JTextField();
		    getContentPane().add(jTextFieldpath, new GridBagConstraints(3, 6, 7, 1, 0.0, 0.0,
			    GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		    jTextFieldpath.setText("");
		}
		{
		    jCheckBoximport = new JCheckBox();
		    getContentPane().add(jCheckBoximport, new GridBagConstraints(1, 7, 7, 1, 0.0, 0.0,
			    GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		    jCheckBoximport.setText(LangTools.getResourceBundleWordLanguage(rb,
			    "Import executable into internal folder", "ffmpegconfgui.importinternal"));
		    jCheckBoximport.setSelected(true);
		}
		{
		    jPanelcurrentversion = new JPanel();
		    GridBagLayout jPanelcurrentversionLayout = new GridBagLayout();
		    getContentPane().add(jPanelcurrentversion, new GridBagConstraints(0, 0, 10, 1, 0.0, 0.0,
			    GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		    jPanelcurrentversion.setBorder(BorderFactory.createTitledBorder(LangTools
			    .getResourceBundleWordLanguage(rb, "Current Version", "ffmpegconfgui.currentversion") + ": "
			    + LangTools.getResourceBundleWordLanguage(rb, "Not defined", "ffmpegconfgui.notdefined")));
		    jPanelcurrentversionLayout.rowWeights = new double[] { 0.1 };
		    jPanelcurrentversionLayout.rowHeights = new int[] { 7 };
		    jPanelcurrentversionLayout.columnWeights = new double[] { 0.1 };
		    jPanelcurrentversionLayout.columnWidths = new int[] { 7 };
		    jPanelcurrentversion.setLayout(jPanelcurrentversionLayout);
		    {
			jTextFieldcurrent = new JTextField();
			jPanelcurrentversion.add(jTextFieldcurrent,
				new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			jTextFieldcurrent.setText(
				LangTools.getResourceBundleWordLanguage(rb, "Not defined", "ffmpegconfgui.notdefined"));
		    }
		}
		{
		    jButtongetffmpeg = new JButton();
		    getContentPane().add(jButtongetffmpeg, new GridBagConstraints(0, 8, 2, 1, 0.0, 0.0,
			    GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		    jButtongetffmpeg.setText(
			    LangTools.getResourceBundleWordLanguage(rb, "Get ffmpeg", "ffmpegconfgui.getffmpeg"));
		    jButtongetffmpeg.setActionCommand(GETFFMPEG);
		    jButtongetffmpeg.addActionListener(this);
		}
		{
		    jScrollPanefindfmmpeg = new JScrollPane();
		    getContentPane().add(jScrollPanefindfmmpeg, new GridBagConstraints(0, 1, 10, 5, 0.0, 0.0,
			    GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		    jScrollPanefindfmmpeg
			    .setBorder(BorderFactory.createTitledBorder(LangTools.getResourceBundleWordLanguage(rb,
				    "Path of installed FFmpeg versions", "ffmpegconfgui.pathoffmpeg")));
		}
	    }
	    {
		this.setSize(700, 400);
	    }
	} catch (Exception e) {
	    Logger.error(e);
	}
    }

    /**
     * Sets the current ffmpeg path.
     */
    private void setCurrentFFmpegPath() {
	String currentpath = FFmpegManager.getInstance().getFFmpegPath();
	if (currentpath != null) {
	    if (FileInformationChecker.validFFmpegExec(currentpath)) {
		jTextFieldcurrent.setText(currentpath);
		setCurrentFFmpegVersion();
		Logger.info("Current FFmpeg folder: " + currentpath);
	    } else
		Logger.error("Current FFmpeg version is not valid: " + currentpath);
	} else
	    jTextFieldcurrent
		    .setText(LangTools.getResourceBundleWordLanguage(rb, "Not defined", "ffmpegconfgui.notdefined"));
	jTextFieldcurrent.setEditable(false);
    }

    /**
     * Sets the current ffmpeg version.
     */
    private void setCurrentFFmpegVersion() {
	String version = FFmpegParametersChecker.getVersionFFmpeg();
	if (version != null)
	    jPanelcurrentversion.setBorder(BorderFactory.createTitledBorder(
		    LangTools.getResourceBundleWordLanguage(rb, "Current Version", "ffmpegconfgui.currentversion")
			    + ": " + version));
    }

    /**
     * @return
     */

    private void findFFmpegVersions() {

	ArrayList<String> installed = FFmpegManager.getInstance().findSystemFFmpegVersions();

	String[] columnnames = {
		LangTools.getResourceBundleWordLanguage(rb, "Path to FFmpeg", "ffmpegconfgui.pathtagname"),
		LangTools.getResourceBundleWordLanguage(rb, "Select", "ffmpegconfgui.pathtagselect") };
	GenericTableViewerModel tablemodel = new GenericTableViewerModel(columnnames, true);
	tablemodel.seteditfromcolumn(0);

	String currentpath = FFmpegManager.getInstance().getFFmpegPath();

	if (installed != null && installed.size() > 0) {
	    boolean configured = false;
	    for (int i = 0; i < installed.size(); i++) {
		if (currentpath != null && !currentpath.isEmpty()) {
		    if (installed.get(i).equals(currentpath)) {
			tablemodel.addRow(new Object[] { installed.get(i), true });
			configured = true;
		    } else
			tablemodel.addRow(new Object[] { installed.get(i), false });
		} else
		    tablemodel.addRow(new Object[] { installed.get(i), false });
	    }

	    if (!configured) {
		for (int i = 0; i < installed.size(); i++) {
		    String basename = FilenameUtils.getBaseName(installed.get(i));
		    if (!configured && basename.toLowerCase().startsWith("default")) {
			configured = true;
			FFmpegManager.getInstance().setFFmpegPath(installed.get(i));
			tablemodel.setValueAt(true, i, 1);
		    } else
			tablemodel.setValueAt(false, i, 1);
		}
	    }
	}


	tableffmpeg = new JTable(tablemodel) {

	    private static final long serialVersionUID = 1L;

	    public String getToolTipText(MouseEvent e) {
		String tip = null;
		java.awt.Point p = e.getPoint();
		int rowIndex = rowAtPoint(p);

		try {
		    tip = getValueAt(rowIndex, 0).toString();
		    tip="<html><font size=\"4\", color=\"blue\"><b>"+tip+"</b></font></html>";
		} catch (RuntimeException e1) {
		    Logger.error(e1);
		}

		return tip;
	    }
	};
	jScrollPanefindfmmpeg.getViewport().add(tableffmpeg);
	tableffmpeg.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(final MouseEvent e) {
		if (e.getClickCount() == 1) {
		    final JTable jTable = (JTable) e.getSource();
		    int row = jTable.rowAtPoint(e.getPoint());
		    setUniqueFFmpegBin(row);
		}
	    }
	});

	TableColumnModel model = tableffmpeg.getColumnModel();
	double factor = tableffmpeg.getPreferredScrollableViewportSize().getWidth();
	model.getColumn(0).setPreferredWidth((int) (factor * 0.8));
	model.getColumn(1).setPreferredWidth((int) (factor * 0.2));

    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
	String action = e.getActionCommand();

	if (action.equals(CANCELFFMPEGEXECUTABLEOPERATION))
	    this.dispose();
	else if (action.equals(VALIDATEANDSETFFMPEGEXECUTABLE))
	    validateandsetffmpegexecutable();
	// this.dispose();
	else if (action.equals(OPENFFMPEGEXECUTABLE))
	    openExecutableFFmpeg();

	else if (action.equals(ConfigurationFFmpegErrorsPanel.CLOSECONFIGERRORPANEL))
	    errorpanel.dispose();
	else if (action.equals(GETFFMPEG))
	    getFFmpegBinRelease();
	// openFFmpegWebsite();

    }

    /**
     * Open executable ffmpeg.
     */
    private void openExecutableFFmpeg() {

	String FilePath = ListFiles.getSingleFilepathFileChooser(this);
	if (FilePath != null) {
	    this.FFmpegExePath = FilePath;
	    jTextFieldpath.setText(FilePath);
	    setFFmpegSystemSelectedPathToFalse();
	}
    }

    private void setFFmpegSystemSelectedPathToFalse() {
	int rows = tableffmpeg.getRowCount();

	if (rows > 0) {
	    for (int i = 0; i < rows; i++) {
		boolean value = (boolean) tableffmpeg.getValueAt(i, 1);
		if (value)
		    tableffmpeg.setValueAt(false, i, 1);
	    }
	}
    }

    private void setUniqueFFmpegBin(int index) {

	for (int i = 0; i < tableffmpeg.getRowCount(); i++) {
	    if (i != index)
		tableffmpeg.setValueAt(false, i, 1);
	}
    }

    /**
     * Validate and set ffmpeg executable.
     */
    private void validateandsetffmpegexecutable() {
	boolean valid = true;
	boolean systemversion = false;
	if (tableffmpeg.getRowCount() > 0) {
	    if (validChoosenffmpegpath()) {
		int rows = tableffmpeg.getRowCount();
		for (int i = 0; i < rows; i++) {
		    boolean val = (boolean) tableffmpeg.getValueAt(i, 1);
		    if (val) {
			FFmpegExePath = (String) tableffmpeg.getValueAt(i, 0);
			if (FFmpegManager.getInstance().isCustomFFmpeg(FFmpegExePath))
			    systemversion = false;
			else
			    systemversion = true;
			jCheckBoximport.setSelected(false);
		    }
		}
	    } else
		valid = false;
	}
	if (valid) {
	    ConfigureFFmpegExecutablePath configure = new ConfigureFFmpegExecutablePath(FFmpegExePath,
		    jCheckBoximport.isSelected(), systemversion);
	    ArrayList<String> errors = configure.getErrors();
	    if (errors != null) {
		errorpanel = new ConfigurationFFmpegErrorsPanel(errors);
		errorpanel.addCloseButtonActionListener(this);
		errorpanel.setLocationRelativeTo(this);
		errorpanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		errorpanel.setVisible(true);
	    } else {
		FFmpegManager.getInstance().LoadFFmpeg();
		Maingui.getInstance().resetGeneralEncodingPropertiesToDefault();
		Maingui.getInstance().reloadProfiles();
		this.dispose();
	    }
	}

    }

    private boolean validChoosenffmpegpath() {

	int rows = tableffmpeg.getRowCount();

	int foundtrue = 0;

	if (rows > 0) {
	    for (int i = 0; i < rows; i++) {
		boolean value = (boolean) tableffmpeg.getValueAt(i, 1);
		if (value)
		    foundtrue++;
		if (foundtrue > 1) {
		    JOptionPane.showMessageDialog(mainframe,
			    LangTools.getResourceBundleWordLanguage(rb, "Just one version can be selected",
				    "ffmpegconfgui.selnumb"),
			    LangTools.getResourceBundleWordLanguage(rb, "Warning", "warngui.tag"),
			    JOptionPane.INFORMATION_MESSAGE,
			    new ImageIcon(ListFiles.getIconAbsolutePath("icons/warning64x64.png")));
		    return false;
		}
	    }
	}
	return true;
    }

 

    private void getFFmpegBinRelease() {

	DownloadFFmpegRelease downloader = new DownloadFFmpegRelease();
	downloader.setLocationRelativeTo(this);
	downloader.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	downloader.setModal(true);
	downloader.setVisible(true);

	String filepath = downloader.getFFmpegBinPath();

	if (filepath != null) {
	    findFFmpegVersions();
	    setFFmpegSystemSelectedPathToFalse();

	    int rows = tableffmpeg.getRowCount();

	    if (rows > 0) {
		for (int i = 0; i < rows; i++) {
		    String path = (String) tableffmpeg.getValueAt(i, 0);
		    if (path.equals(filepath)) {
			tableffmpeg.setValueAt(true, i, 1);
			break;
		    }
		}
	    }
	}
    }

}
