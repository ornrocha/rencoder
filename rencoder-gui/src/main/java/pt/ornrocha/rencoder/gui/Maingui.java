/*
 * Copyright 2014
 *
 * This is free software: you can redistribute it and/or modify it under the terms of the GNU Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 * 
 * This code is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Public License for more details.
 * 
 * You should have received a copy of the GNU Public License along with this code. If not, see
 * http://www.gnu.org/licenses/
 * 
 * Created by Orlando Rocha
 */
package pt.ornrocha.rencoder.gui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.io.FilenameUtils;
import org.jdesktop.swingx.JXBusyLabel;
import org.tinylog.Logger;
import pt.ornrocha.rencoder.ffmpegWrapper.commands.EncoderControlCenter;
import pt.ornrocha.rencoder.ffmpegWrapper.commands.FileInformationIOException;
import pt.ornrocha.rencoder.ffmpegWrapper.configurations.FFmpegManager;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.execution.progress.FFmpegCodecInfoAnalyser;
import pt.ornrocha.rencoder.ffmpegWrapper.utilities.FFmpegUtils;
import pt.ornrocha.rencoder.gui.components.panels.VideoMainPanel;
import pt.ornrocha.rencoder.gui.components.panels.auxiliar.Looktypes;
import pt.ornrocha.rencoder.gui.components.panels.auxiliar.StaticGuiFieldNames;
import pt.ornrocha.rencoder.gui.components.panels.auxiliar.StaticVlcMethods;
import pt.ornrocha.rencoder.gui.components.panels.auxiliar.VideoPanelUtils;
import pt.ornrocha.rencoder.gui.components.panels.configurations.ConverterConfigurations;
import pt.ornrocha.rencoder.gui.components.panels.configurations.LookPanelGui;
import pt.ornrocha.rencoder.gui.components.panels.configurations.ffmpegexecutable.BackgroundFFmpegFontsConfig;
import pt.ornrocha.rencoder.gui.components.panels.configurations.ffmpegexecutable.ConfigurationFFmpegExecutable;
import pt.ornrocha.rencoder.gui.components.panels.configurations.filters.FiltersPanel;
import pt.ornrocha.rencoder.gui.components.panels.configurations.generalencoding.MainGeneralConfigPanel;
import pt.ornrocha.rencoder.gui.components.panels.configurations.logs.RencoderLogsPanel;
import pt.ornrocha.rencoder.gui.components.panels.info.AboutInformationPanel;
import pt.ornrocha.rencoder.gui.components.panels.info.AutoCheckForUpdates;
import pt.ornrocha.rencoder.gui.components.panels.info.EncodingProgressPanel;
import pt.ornrocha.rencoder.gui.components.panels.info.InformationEncodersPanel;
import pt.ornrocha.rencoder.gui.components.panels.info.ProfileComboxRender;
import pt.ornrocha.rencoder.gui.components.panels.info.SimpleTextMSGPanel;
import pt.ornrocha.rencoder.gui.components.panels.info.WarnJTextPanePanel;
import pt.ornrocha.rencoder.gui.components.panels.merge.MergeVideosPanel;
import pt.ornrocha.rencoder.gui.components.panels.player.RunPlayer;
import pt.ornrocha.rencoder.gui.execute.RestartRencoder;
import pt.ornrocha.rencoder.gui.execute.ShutdownRencoder;
import pt.ornrocha.rencoder.gui.updates.CheckForUpdates;
import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.helpers.osystem.OSystem;
import pt.ornrocha.rencoder.helpers.props.fields.StaticGlobalFields;
import pt.ornrocha.rencoder.helpers.props.fields.StaticGlobalMethods;
import pt.ornrocha.rencoder.helpers.props.fields.StaticVideoEncoderFields;
import pt.ornrocha.rencoder.helpers.props.managers.EncodingProfileManager;
import pt.ornrocha.rencoder.helpers.props.managers.GeneralEncodingPropertiesManager;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;
import pt.ornrocha.rencoder.mediafiles.files.auxiliar.ProcessFilesAux;
import pt.ornrocha.rencoder.mediafiles.files.containers.base.Subtitlefile;
import pt.ornrocha.rencoder.mediafiles.files.containers.base.Videofile;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoH264EncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoMPEG4EncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;
import pt.ornrocha.rencoder.mediafiles.setfiles.foldersandfiles.FileTypeFilter;
import pt.ornrocha.rencoder.mediafiles.setfiles.foldersandfiles.ListFiles;
import pt.ornrocha.rencoder.mediafiles.setfiles.processfiles.ProcessFiles;

// TODO: Auto-generated Javadoc
/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI Builder, which is free
 * for non-commercial use. If Jigloo is being used commercially (ie, by a corporation, company or
 * business for any purpose whatever) then you should purchase a license for each developer using
 * Jigloo. Please visit www.cloudgarden.com for details. Use of Jigloo implies acceptance of these
 * licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS
 * CODE CANNOT BE USED LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class Maingui extends javax.swing.JFrame implements ActionListener, PropertyChangeListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The j menu itemencoders. */
	private JMenuItem jMenuItemencoders;

	/** The j menu itemffmpeg. */
	private JMenuItem jMenuItemffmpeg;

	/** The j menu item encod config. */
	private JMenuItem jMenuItemEncodConfig;

	/** The Constant VIDEOSINGLESETTINGS. */
	private static final String VIDEOSINGLESETTINGS = "videosinglesettings";

	/** The j menu bar1. */
	private JMenuBar jMenuBar1;

	/** The j menu itemvideos. */
	private JMenuItem jMenuItemvideos;

	/** The j menu itemfolderandsubfolders. */
	private JMenuItem jMenuItemfolderandsubfolders;

	/** The j menu item1. */
	private JMenuItem jMenuItem1;

	/** The j menu1. */
	private JMenu jMenu1;

	/** The j menu item look. */
	private JMenuItem jMenuItemLook;

	/** The j menu about. */
	private JMenu jMenuAbout;

	/** The j menugeneraloptions. */
	private JMenu jMenugeneraloptions;

	/** The j menuquit. */
	private JMenu jMenuquit;

	/** The j menu itemlogs. */
	private JMenuItem jMenuItemlogs;

	/** The j buttonconvert. */
	private JButton jButtonconvert;

	/** The video panel. */
	private VideoMainPanel videoPanel;

	// components
	/** The p. */
	private LookPanelGui lookfeelconfpanel;

	private WarnJTextPanePanel msgPanel = null;

	/** The vidconfset. */
	private ConverterConfigurations vidconfset;

	/** The j buttonchangesavepath. */
	private JButton jButtonchangesavepath;

	/** The j text fieldsave path. */
	private JTextField jTextFieldsavePath;

	/** The j panelsavepath. */
	private JPanel jPanelsavepath;

	/** The j panelsettings. */
	private JPanel jPanelsettings;

	/** The j buttonvideo settings. */
	private JButton jButtonvideoSettings;

	/** The profilemanager. */
	private EncodingProfileManager profilemanager = null;

	/** The genencinfomanager. */
	private GeneralEncodingPropertiesManager genencinfomanager = null;

	/** The encodconfigpanel. */
	private MainGeneralConfigPanel encodconfigpanel = null;

	/** The configffmpegpanel. */
	private ConfigurationFFmpegExecutable configffmpegpanel = null;

	/** The encoderspanel. */
	private InformationEncodersPanel encoderspanel = null;

	/** The aboutpanel. */
	private AboutInformationPanel aboutpanel = null;

	/** The logspanel. */
	private RencoderLogsPanel logspanel = null;

	// Action Commands
	/** The Constant LOOK. */
	private static final String LOOK = "changelook";

	/** The Constant LOADFILESFROMFOLDER. */
	private static final String LOADFILESFROMFOLDER = "loadfilesfromfolder";
	private JComboBox<String> jComboBoxprofiles;
	private JButton jButtonmerge;
	private JPanel jPanelprofiles;
	private JButton jButtonselectedmoviessets;
	private JButton jButton1;
	private JButton jButtonplay;
	private JButton jButtonfilters;
	private JButton jButtonchapters;
	private JButton jButtonsets;
	private JPanel jPanelOptions;

	/** The Constant LOADFILESFROMFOLDERANDSUBFOLDERS. */
	private static final String LOADFILESFROMFOLDERANDSUBFOLDERS = "loadfilesfromfolderandsubfolders";

	/** The Constant ADDMOVIESMENU. */
	private static final String ADDMOVIESMENU = "addmoviesmenu";

	/** The Constant OPENFOLDER. */
	private static final String OPENFOLDER = "openfolder";

	/** The Constant OPENMANUAL. */
	private static final String OPENMANUAL = "openmanual";

	/** The Constant OPENLOGS. */
	private static final String OPENLOGS = "openlogs";

	/** The Constant TITLEAPP. */
	private static final String TITLEAPP = "Rencoder";

	/** The j buttonopenfolder. */
	private JButton jButtonopenfolder;

	/** The j panelsavefolder. */
	private JPanel jPanelsavefolder;

	/** The j menu item help. */
	private JMenuItem jMenuItemHelp;

	/** The j menu itemshowabout. */
	private JMenuItem jMenuItemshowabout;

	private JMenuItem jMenuItemCheckUpdates;

	/** The Constant CHECKENCODERS. */
	private static final String CHECKENCODERS = "checkencoders";

	/** The Constant QUIT. */
	private static final String QUIT = "quit";

	/** The Constant CONVERT. */
	private static final String CONVERT = "convert";

	/** The Constant CHANGEOUTPUTFOLDERFILES. */
	private static final String CHANGEOUTPUTFOLDERFILES = "changeoutputfolderfiles";

	/** The Constant SETUPENCODCONFIGS. */
	private static final String SETUPENCODCONFIGS = "setupencodconfigs";

	/** The Constant SETUPFFMPEG. */
	private static final String SETUPFFMPEG = "setupffmpeg";

	/** The Constant SHOWABOUTPANEL. */
	private static final String SHOWABOUTPANEL = "showaboutpanel";

	private static final String CHECKUPDATESPANEL = "showupdatespanel";

	private static final String PLAYMEDIA = "playmedia";

	private static final String APPLYFILTERS = "applyfilter";

	private static final String APPLYPROFILEALLMOVIES = "applyprofileallmovies";
	private static final String APPLYPROFILESELECTEDMOVIES = "applyprofileselectedmovies";
	private static final String CHANGEPROFILECOMBOX = "changeprofilecombox";

	private static final String MERGEFILES = "mergefiles";

	private static final String AUTOOUTDIR = "autooutputdir";
	private static final String CUSTOMOUTDIR = "customoutputdir";

	/** The allowed file types. */
	private IndexedHashMap<String, FileTypeFilter> allowedFileTypes =
			ProcessFilesAux.getAllowedExtensionsForVideo();

	/** The rb. */
	private ResourceBundle rb;

	private ShutdownRencoder shutdown = null;

	private RestartRencoder restart = null;

	private EncodingProgressPanel encodingPanel = null;

	private LinkedHashSet<String> moviesalreadyinconvproc = new LinkedHashSet<>();

	private static final String RESTART = "restartapp";

	private AutoCheckForUpdates macupdatespanel = null;

	private AutoCheckForUpdates newupdatespanel = null;
	private FiltersPanel filters = null;

	private static Maingui instance;
	private JPanel panel_1;
	private JPanel panel_2;
	private JCheckBox chckbxsameorigin;
	private JCheckBox chckbxletmechoose;
	private JPanel panel_layer;
	private JLabel lblLabelInfo;
	private JXBusyLabel panelBusylabel;


	public static Maingui getInstance() {
		if (instance == null)
			instance = new Maingui();
		return instance;
	}

	/**
	 * Instantiates a new maingui.
	 */
	private Maingui() {
		super();
		Logger.info("Starting Rencoder");
		this.setIconImage(new ImageIcon(getClass().getResource("/icons/rencoderbig.png")).getImage());
		this.setTitle(TITLEAPP);
		setLookandFeel();

		rb = ResourceBundle.getBundle("lang", LangTools.getDefinedLanguage(),
				LangTools.loadLanguagesPath());
		initGUI();
		setToolTips();
		setActionListenersGuiComponents();
		setCloseWindowListener();

	}

	public void addWarnFlag() {

		panelBusylabel.setVisible(true);
		panelBusylabel.getBusyPainter().setHighlightColor(new Color(44, 61, 146).darker()); 
		panelBusylabel.getBusyPainter().setBaseColor(new Color(168, 204, 241).brighter()); 
		panelBusylabel.setBusy(true);
		lblLabelInfo.setVisible(true);
		panel_layer.updateUI();
	}

	public void removeWarnFlag() {
		panelBusylabel.setBusy(false);
		panelBusylabel.setVisible(false);
		lblLabelInfo.setVisible(false);
		panel_layer.updateUI();
	}
	
	
	
	public void loadRequirements() {
		
		new SwingWorker<Boolean, Object>() {

			@Override
			protected Boolean doInBackground() throws Exception {
				long startTime = System.currentTimeMillis();
				checkFFmpegExecutable();
				long duration = (System.currentTimeMillis() - startTime);
				Logger.debug(
						"FFmpeg information was checked in : " + PropertiesWorker.millisToShortDHMS(duration));
				configureFFmpegFontsInbackground();
		
				startTime = System.currentTimeMillis();
				LoadProfiles();
				LoadGeneralEncodingInfo();
				duration = (System.currentTimeMillis() - startTime);
				Logger.debug("Profiles were loaded in : " + PropertiesWorker.millisToShortDHMS(duration));
				populateProfileComboBox();
				setGeneralOutputFolder();
				FFmpegUtils.checkLogsFolder();
		
				videoPanel.getMoviesPAnel().setEncodingPropertiesManager(genencinfomanager);
		
				if (OSystem.isMacOS() || OSystem.isWindows()) {
					StaticVlcMethods.setVLCNOTFOUNDTAG();
				} else
					checkVLC();
		
				setInitProfileComboxSettings();
				jComboBoxprofiles.setRenderer(new ProfileComboxRender(genencinfomanager, profilemanager));
		
				removeWarnFlag();
		
				boolean hasupdate=CheckForUpdates.updateIsNeeded();
				if (!CheckForUpdates.isCheckUpdatesBlocked() && hasupdate)
					launchSearchForUpdatesPanel(hasupdate);
				return null;
			}
			
		}.execute();
		
		
	}
	



	private void setCloseWindowListener() {
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (encodingPanel != null)
					encodingPanel.stopAllProcessesAndClose();
				StaticVlcMethods.removeVLCNOTFOUNDTAG();
				StaticGlobalMethods.removeRestartTag();
				OSystem.deleteTempFolder();
				dispose();
			}
		});
	}

	/**
	 * Inits the gui.
	 */
	private void initGUI() {
		try {
			GridBagLayout thisLayout = new GridBagLayout();
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			thisLayout.rowWeights =
					new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.0, 0.1};
			thisLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
			thisLayout.columnWeights =
					new double[] {0.0, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
			thisLayout.columnWidths = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
			getContentPane().setLayout(thisLayout);
			{

				videoPanel = new VideoMainPanel(this);

				getContentPane().add(videoPanel, new GridBagConstraints(1, 0, 11, 11, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
			}
			{
				jButtonconvert = new JButton();
				getContentPane().add(jButtonconvert, new GridBagConstraints(9, 11, 3, 3, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				jButtonconvert.setText(LangTools.getResourceBundleWordLanguage(rb, "Start conversion",
						"maingui.startconversion"));
				jButtonconvert.setActionCommand(CONVERT);
				jButtonconvert.addActionListener(this);
			}

			{
				jPanelsavepath = new JPanel();
				GridBagLayout jPanelsavepathLayout = new GridBagLayout();
				getContentPane().add(jPanelsavepath, new GridBagConstraints(3, 11, 6, 3, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));
				jPanelsavepath.setBorder(BorderFactory.createTitledBorder(
						LangTools.getResourceBundleWordLanguage(rb, "Output folder", "general.outputfolder")));
				jPanelsavepathLayout.rowWeights = new double[] {1.0, 1.0, 1.0};
				jPanelsavepathLayout.rowHeights = new int[] {7, 7, 7};
				jPanelsavepathLayout.columnWeights = new double[] {1.0};
				jPanelsavepathLayout.columnWidths = new int[] {7};
				jPanelsavepath.setLayout(jPanelsavepathLayout);
				{

					DropTarget dragdropfolder = new DropTarget() {

						private static final long serialVersionUID = 1L;

						@Override
						public synchronized void drop(DropTargetDropEvent evt) {
							try {
								evt.acceptDrop(DnDConstants.ACTION_COPY);
								@SuppressWarnings("unchecked")
								List<File> droppedFiles = (List<File>) evt.getTransferable()
								.getTransferData(DataFlavor.javaFileListFlavor);

								if (droppedFiles.size() > 0) {
									File f = droppedFiles.get(0);
									if (f.isDirectory())
										changeSaveFolderToVideoFiles(f.getAbsolutePath());

								}

							} catch (Exception ex) {
								Maingui.getInstance().showErrorMessage(ex.getMessage(),
										"Error in changing output folder");
								Logger.error(ex);

							}
						}
					};

				}
				{
					panel_2 = new JPanel();
					GridBagConstraints gbc_panel_2 = new GridBagConstraints();
					gbc_panel_2.insets = new Insets(0, 0, 5, 0);
					gbc_panel_2.fill = GridBagConstraints.BOTH;
					gbc_panel_2.gridx = 0;
					gbc_panel_2.gridy = 0;
					jPanelsavepath.add(panel_2, gbc_panel_2);
					GridBagLayout gbl_panel_2 = new GridBagLayout();
					gbl_panel_2.columnWidths = new int[] {7, 7};
					gbl_panel_2.rowHeights = new int[] {7};
					gbl_panel_2.columnWeights = new double[] {1.0, 1.0};
					gbl_panel_2.rowWeights = new double[] {1.0};
					panel_2.setLayout(gbl_panel_2);
					{
						chckbxsameorigin = new JCheckBox(LangTools.getResourceBundleWordLanguage(rb,
								"Same as original files", "maingui.autosavefolder"));
						chckbxsameorigin.setActionCommand(AUTOOUTDIR);
						chckbxsameorigin.addActionListener(this);

						GridBagConstraints gbc_chckbxsameorigin = new GridBagConstraints();
						gbc_chckbxsameorigin.fill = GridBagConstraints.VERTICAL;
						gbc_chckbxsameorigin.insets = new Insets(0, 0, 0, 5);
						gbc_chckbxsameorigin.gridx = 0;
						gbc_chckbxsameorigin.gridy = 0;
						panel_2.add(chckbxsameorigin, gbc_chckbxsameorigin);
					}
					{
						chckbxletmechoose = new JCheckBox(LangTools.getResourceBundleWordLanguage(rb,
								"Let me choose", "maingui.customsavefolder"));
						chckbxletmechoose.setActionCommand(CUSTOMOUTDIR);
						chckbxletmechoose.addActionListener(this);
						GridBagConstraints gbc_chckbxletmechoose = new GridBagConstraints();
						gbc_chckbxletmechoose.gridx = 1;
						gbc_chckbxletmechoose.gridy = 0;
						panel_2.add(chckbxletmechoose, gbc_chckbxletmechoose);
					}
				}
				{
					jPanelsavefolder = new JPanel();
					GridBagLayout jPanelsavefolderLayout = new GridBagLayout();
					jPanelsavepath.add(jPanelsavefolder, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
					jPanelsavefolderLayout.rowWeights = new double[] {0.1};
					jPanelsavefolderLayout.rowHeights = new int[] {7};
					jPanelsavefolderLayout.columnWeights = new double[] {0.1};
					jPanelsavefolderLayout.columnWidths = new int[] {7};
					jPanelsavefolder.setLayout(jPanelsavefolderLayout);
					jTextFieldsavePath = new JTextField();
					GridBagConstraints gbc_jTextFieldsavePath = new GridBagConstraints();
					gbc_jTextFieldsavePath.fill = GridBagConstraints.BOTH;
					gbc_jTextFieldsavePath.gridx = 0;
					gbc_jTextFieldsavePath.gridy = 0;
					jPanelsavefolder.add(jTextFieldsavePath, gbc_jTextFieldsavePath);

					DropTarget dragdropfolder = new DropTarget() {
						public synchronized void drop(DropTargetDropEvent evt) {
							try {
								evt.acceptDrop(DnDConstants.ACTION_COPY);
								List<File> droppedFiles = (List<File>) evt.getTransferable()
										.getTransferData(DataFlavor.javaFileListFlavor);

								if (droppedFiles.size() > 0) {
									File f = droppedFiles.get(0);
									if (f.isDirectory())
										changeSaveFolderToVideoFiles(f.getAbsolutePath());

								}

							} catch (Exception ex) {
								Logger.error(ex);
							}
						}
					};

					jTextFieldsavePath.setDropTarget(dragdropfolder);
					{
						{
							panel_1 = new JPanel();
							GridBagConstraints gbc_panel_1 = new GridBagConstraints();
							gbc_panel_1.fill = GridBagConstraints.BOTH;
							gbc_panel_1.gridx = 0;
							gbc_panel_1.gridy = 2;
							jPanelsavepath.add(panel_1, gbc_panel_1);
							GridBagLayout gbl_panel_1 = new GridBagLayout();
							gbl_panel_1.columnWidths = new int[] {7, 7};
							gbl_panel_1.rowHeights = new int[] {7};
							gbl_panel_1.columnWeights = new double[] {1.0, 1.0};
							gbl_panel_1.rowWeights = new double[] {1.0};
							panel_1.setLayout(gbl_panel_1);
							{
								jButtonchangesavepath = new JButton();
								GridBagConstraints gbc_jButtonchangesavepath = new GridBagConstraints();
								gbc_jButtonchangesavepath.fill = GridBagConstraints.HORIZONTAL;
								gbc_jButtonchangesavepath.insets = new Insets(0, 0, 0, 5);
								gbc_jButtonchangesavepath.gridx = 0;
								gbc_jButtonchangesavepath.gridy = 0;
								panel_1.add(jButtonchangesavepath, gbc_jButtonchangesavepath);
								jButtonchangesavepath.setText(LangTools.getResourceBundleWordLanguage(rb,
										"Change Folder", "maingui.changefolder"));
								jButtonchangesavepath.setActionCommand(CHANGEOUTPUTFOLDERFILES);
								jButtonopenfolder = new JButton();
								GridBagConstraints gbc_jButtonopenfolder = new GridBagConstraints();
								gbc_jButtonopenfolder.fill = GridBagConstraints.HORIZONTAL;
								gbc_jButtonopenfolder.gridx = 1;
								gbc_jButtonopenfolder.gridy = 0;
								panel_1.add(jButtonopenfolder, gbc_jButtonopenfolder);
								jButtonopenfolder.setText(LangTools.getResourceBundleWordLanguage(rb,
										"Output Folder", "general.open.outputfolder"));
								jButtonopenfolder.setActionCommand(OPENFOLDER);
								jButtonopenfolder.addActionListener(this);
								jButtonchangesavepath.addActionListener(this);
							}
						}
					}
				}
			}
			{
				jPanelOptions = new JPanel();
				GridBagLayout jPanelOptionsLayout = new GridBagLayout();
				getContentPane().add(jPanelOptions, new GridBagConstraints(0, 1, 1, 10, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
				jPanelOptionsLayout.rowWeights =
						new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				jPanelOptionsLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
				jPanelOptionsLayout.columnWeights = new double[] {0.1};
				jPanelOptionsLayout.columnWidths = new int[] {7};
				jPanelOptions.setLayout(jPanelOptionsLayout);
				jPanelOptions.setBorder(BorderFactory.createTitledBorder(
						LangTools.getResourceBundleWordLanguage(rb, "Options:", "maingui.editoptions")));
				{
					jButtonsets = new JButton();
					jButtonsets.setPreferredSize(new Dimension(100, 50));
					jPanelOptions.add(jButtonsets, new GridBagConstraints(0, 3, 1, 2, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
					jButtonsets.setText(LangTools.getResourceBundleWordLanguage(rb,
							"<html><center>Conversion<br />Settings</center></html>", "maingui.convertsettings"));
					jButtonsets.setActionCommand(VIDEOSINGLESETTINGS);
					jButtonsets.addActionListener(this);
				}

				{
					jButtonfilters = new JButton();
					jButtonfilters.setPreferredSize(new Dimension(100, 50));
					jPanelOptions.add(jButtonfilters, new GridBagConstraints(0, 5, 1, 2, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
					jButtonfilters
					.setText(LangTools.getResourceBundleWordLanguage(rb, "Filters", "maingui.filters"));
					jButtonfilters.setActionCommand(APPLYFILTERS);
					jButtonfilters.addActionListener(this);
				}
				{
					if (!OSystem.isMacOS()) {
						jButtonplay = new JButton();
						jButtonplay.setPreferredSize(new Dimension(100, 50));
						jPanelOptions.add(jButtonplay, new GridBagConstraints(0, 1, 1, 2, 0.0, 0.0,
								GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
						jButtonplay.setText(LangTools.getResourceBundleWordLanguage(rb,
								"<html><center>Play<br/>Media</center></html>", "maingui.playmedia"));
						jButtonplay.setActionCommand(PLAYMEDIA);
						jButtonplay.addActionListener(this);

					}
					{
						String mergelabel = "<html><center>Merge<br/>Videos</center></html>";
						jButtonmerge = new JButton();
						jButtonmerge.setPreferredSize(new Dimension(100, 50));
						jPanelOptions.add(jButtonmerge, new GridBagConstraints(0, 7, 1, 2, 0.0, 0.0,
								GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
						jButtonmerge.setText(LangTools.getWordLanguage(mergelabel, "maingui.mergevideos"));
						jButtonmerge.setActionCommand(MERGEFILES);
						jButtonmerge.addActionListener(this);
					}
				}
			}
			{
				jPanelprofiles = new JPanel();
				GridBagLayout jPanelprofilesLayout = new GridBagLayout();
				getContentPane().add(jPanelprofiles, new GridBagConstraints(1, 11, 2, 3, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));
				jPanelprofilesLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
				jPanelprofilesLayout.rowHeights = new int[] {7, 7, 7, 7};
				jPanelprofilesLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
				jPanelprofilesLayout.columnWidths = new int[] {7, 7, 7, 7};
				jPanelprofiles.setLayout(jPanelprofilesLayout);
				jPanelprofiles
				.setBorder(BorderFactory.createTitledBorder(LangTools.getResourceBundleWordLanguage(rb,
						"Conversion profiles", "maingui.conversionprofiles")));
				{

					jComboBoxprofiles = new JComboBox();
					jPanelprofiles.add(jComboBoxprofiles,
							new GridBagConstraints(0, 0, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER,
									GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					jComboBoxprofiles.setActionCommand(CHANGEPROFILECOMBOX);
					jComboBoxprofiles.addActionListener(this);
				}
				{
					jButton1 = new JButton();
					jPanelprofiles.add(jButton1,
							new GridBagConstraints(0, 1, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER,
									GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					jButton1.setText(LangTools.getResourceBundleWordLanguage(rb,
							"Set this profile to all movies", "maingui.profileall"));
					jButton1.setActionCommand(APPLYPROFILEALLMOVIES);
					jButton1.addActionListener(this);
				}
				{
					jButtonselectedmoviessets = new JButton();
					jPanelprofiles.add(jButtonselectedmoviessets,
							new GridBagConstraints(0, 2, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER,
									GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					jButtonselectedmoviessets.setText(LangTools.getResourceBundleWordLanguage(rb,
							"Set this profile to selected movies", "maingui.profileselected"));
					jButtonselectedmoviessets.setActionCommand(APPLYPROFILESELECTEDMOVIES);
					{
						panel_layer = new JPanel();
						GridBagConstraints gbc_panel_layer = new GridBagConstraints();
						gbc_panel_layer.gridheight = 3;
						gbc_panel_layer.insets = new Insets(0, 0, 5, 5);
						gbc_panel_layer.fill = GridBagConstraints.BOTH;
						gbc_panel_layer.gridx = 0;
						gbc_panel_layer.gridy = 11;
						getContentPane().add(panel_layer, gbc_panel_layer);
						GridBagLayout gbl_panel_layer = new GridBagLayout();
						gbl_panel_layer.columnWidths = new int[] {1};
						gbl_panel_layer.rowHeights = new int[] {1,1};
						gbl_panel_layer.columnWeights = new double[] {1.0};
						gbl_panel_layer.rowWeights = new double[] {1.0,0.2};
						panel_layer.setLayout(gbl_panel_layer);
						// panel_layer.setLayout(gbl_panel_layer);
						{
							panelBusylabel = new JXBusyLabel(new Dimension(50, 50));
							GridBagConstraints gbc_panelBusylabel = new GridBagConstraints();
							gbc_panelBusylabel.insets = new Insets(0, 0, 5, 0);
							gbc_panelBusylabel.fill = GridBagConstraints.VERTICAL;
							gbc_panelBusylabel.gridx = 0;
							gbc_panelBusylabel.gridy = 0;
							panel_layer.add(panelBusylabel, gbc_panelBusylabel);
							panelBusylabel.setVisible(false);
						}
						{

								lblLabelInfo = new JLabel("Initializing...");
								GridBagConstraints gbc_lblLabelInfo = new GridBagConstraints();
								gbc_lblLabelInfo.insets = new Insets(0, 0, 5, 0);
								gbc_lblLabelInfo.gridx = 0;
								gbc_lblLabelInfo.gridy =1;
								panel_layer.add(lblLabelInfo, gbc_lblLabelInfo);

								lblLabelInfo.setVisible(false);
						
						}
					}
					jButtonselectedmoviessets.addActionListener(this);
				}
			}
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				jMenuBar1.setOpaque(false);
				{
					jMenu1 = new JMenu();
					jMenuBar1.add(jMenu1);
					jMenu1.setIcon(new ImageIcon(getClass().getResource("/icons/videofolder32x32.png")));
					jMenu1.setText(
							LangTools.getResourceBundleWordLanguage(rb, "Load Files", "maingui.loadfiles"));

					{
						jMenuItem1 = new JMenuItem();
						jMenu1.add(jMenuItem1);
						jMenuItem1
						.setText(LangTools.getResourceBundleWordLanguage(rb, "Folder", "maingui.folder"));
						jMenuItem1.setIcon(new ImageIcon(getClass().getResource("/icons/folder24x24.png")));
						jMenuItem1.setToolTipText(LangTools.getResourceBundleWordLanguage(rb,
								"Load all video files and matching subtitles within a folder",
								"tip.loadallvideosinfolder"));
						jMenuItem1.setActionCommand(LOADFILESFROMFOLDER);
						jMenuItem1.addActionListener(this);

					}
					{
						jMenuItemfolderandsubfolders = new JMenuItem();
						jMenu1.add(jMenuItemfolderandsubfolders);
						jMenuItemfolderandsubfolders.setText(rb.getString("maingui.folderandsubfolders"));
						jMenuItemfolderandsubfolders
						.setIcon(new ImageIcon(getClass().getResource("/icons/folder24x24.png")));
						jMenuItemfolderandsubfolders.setToolTipText(LangTools.getResourceBundleWordLanguage(rb,
								"<html>Load all video files and matching subtitles within<br> a folder and its sub-folders</html>",
								"tip.loadallvideosinfolderandsubfolders"));
						jMenuItemfolderandsubfolders.setActionCommand(LOADFILESFROMFOLDERANDSUBFOLDERS);
						jMenuItemfolderandsubfolders.addActionListener(this);
					}
					{
						jMenuItemvideos = new JMenuItem();
						jMenu1.add(jMenuItemvideos);
						jMenuItemvideos
						.setText(LangTools.getResourceBundleWordLanguage(rb, "Video(s)", "maingui.videos"));
						jMenuItemvideos.setIcon(new ImageIcon(getClass().getResource("/icons/video24x24.png")));
						jMenuItemvideos.setToolTipText(LangTools.getResourceBundleWordLanguage(rb,
								"Load selected video and subtitle files within a folder",
								"tip.loadselectedvideos"));
						jMenuItemvideos.setActionCommand(ADDMOVIESMENU);
						jMenuItemvideos.addActionListener(this);
					}
				}
				{
					jMenugeneraloptions = new JMenu();
					jMenuBar1.add(jMenugeneraloptions);

					jMenugeneraloptions
					.setIcon(new ImageIcon(getClass().getResource("/icons/settings_32x32.png")));
					jMenugeneraloptions.setText(
							LangTools.getResourceBundleWordLanguage(rb, "Settings", "maingui.configurations"));
					{
						jMenuItemEncodConfig = new JMenuItem();
						jMenugeneraloptions.add(jMenuItemEncodConfig);
						jMenuItemEncodConfig.setText(
								LangTools.getResourceBundleWordLanguage(rb, "Conversion", "maingui.conversion"));
						jMenuItemEncodConfig.setToolTipText(LangTools.getResourceBundleWordLanguage(rb,
								"Definition of settings for the general user video conversion profile",
								"tip.conversiontip"));
						jMenuItemEncodConfig
						.setIcon(new ImageIcon(getClass().getResource("/icons/conv_32x32.png")));
						jMenuItemEncodConfig.setActionCommand(SETUPENCODCONFIGS);
						jMenuItemEncodConfig.addActionListener(this);
					}
					{
						jMenuItemffmpeg = new JMenuItem();
						jMenugeneraloptions.add(jMenuItemffmpeg);
						jMenuItemffmpeg.setText(LangTools.getResourceBundleWordLanguage(rb, "FFmpeg settings",
								"ffmpegconfgui.configure"));
						jMenuItemffmpeg.setToolTipText(LangTools.getResourceBundleWordLanguage(rb,
								"Definition of FFmpeg executable", "tip.ffmpegconfigtip"));
						jMenuItemffmpeg
						.setIcon(new ImageIcon(getClass().getResource("/icons/ffmpeg_24x24.png")));
						jMenuItemffmpeg.setActionCommand(SETUPFFMPEG);
						jMenuItemffmpeg.addActionListener(this);
					}
					{
						jMenuItemLook = new JMenuItem();
						jMenugeneraloptions.add(jMenuItemLook);
						jMenuItemLook
						.setText(LangTools.getResourceBundleWordLanguage(rb, "Look", "maingui.look"));
						jMenuItemLook.setToolTipText(LangTools.getResourceBundleWordLanguage(rb,
								"Definition of Look and Feel", "tip.lookandfeeltip"));
						jMenuItemLook.setIcon(new ImageIcon(getClass().getResource("/icons/look_24x24.png")));
						jMenuItemLook.setActionCommand(LOOK);
						jMenuItemLook.addActionListener(this);
					}
					{
						jMenuItemencoders = new JMenuItem();
						jMenugeneraloptions.add(jMenuItemencoders);
						jMenuItemencoders.setText(
								LangTools.getResourceBundleWordLanguage(rb, "Encoders", "maingui.encoders"));
						jMenuItemencoders.setToolTipText(LangTools.getResourceBundleWordLanguage(rb,
								"Settings of FFmpeg encoders", "tip.encoderstip"));
						jMenuItemencoders
						.setIcon(new ImageIcon(getClass().getResource("/icons/encoder24x24.png")));
						jMenuItemencoders.setActionCommand(CHECKENCODERS);
						jMenuItemencoders.addActionListener(this);
					}
					{
						jMenuItemlogs = new JMenuItem();
						jMenugeneraloptions.add(jMenuItemlogs);
						jMenuItemlogs
						.setText(LangTools.getResourceBundleWordLanguage(rb, "Logs", "maingui.logs"));
						jMenuItemlogs.setIcon(new ImageIcon(getClass().getResource("/icons/log.png")));
						jMenuItemlogs.setActionCommand(OPENLOGS);
						jMenuItemlogs.addActionListener(this);
					}
				}
				{
					jMenuAbout = new JMenu();
					jMenuBar1.add(jMenuAbout);
					jMenuAbout.setText(LangTools.getResourceBundleWordLanguage(rb, "About", "maingui.about"));
					jMenuAbout.setIcon(new ImageIcon(getClass().getResource("/icons/about_32x32.png")));
					{
						jMenuItemshowabout = new JMenuItem();
						jMenuAbout.add(jMenuItemshowabout);
						jMenuItemshowabout
						.setText(LangTools.getResourceBundleWordLanguage(rb, "Show", "aboutgui.show"));
						jMenuItemshowabout
						.setIcon(new ImageIcon(getClass().getResource("/icons/show24x24.png")));
						jMenuItemshowabout.setActionCommand(SHOWABOUTPANEL);
						jMenuItemshowabout.addActionListener(this);
					}
//					{
//						jMenuItemHelp = new JMenuItem();
//						jMenuAbout.add(jMenuItemHelp);
//						jMenuItemHelp
//						.setText(LangTools.getResourceBundleWordLanguage(rb, "Help", "maingui.help"));
//						jMenuItemHelp.setIcon(new ImageIcon(getClass().getResource("/icons/help24x24.png")));
//						jMenuItemHelp.setActionCommand(OPENMANUAL);
//						jMenuItemHelp.addActionListener(this);
//					}
					{
						jMenuItemCheckUpdates = new JMenuItem();
						jMenuAbout.add(jMenuItemCheckUpdates);
						jMenuItemCheckUpdates.setText(LangTools.getResourceBundleWordLanguage(rb,
								"Check updates", "updates.checkupdates"));
						jMenuItemCheckUpdates
						.setIcon(new ImageIcon(getClass().getResource("/icons/update24x24.png")));
						jMenuItemCheckUpdates.setActionCommand(CHECKUPDATESPANEL);
						jMenuItemCheckUpdates.addActionListener(this);
					}
				}
				{
					jMenuquit = new JMenu();
					jMenuBar1.add(jMenuquit);
					jMenuquit.setMnemonic(KeyEvent.VK_F);
					jMenuquit.setIcon(new ImageIcon(getClass().getResource("/icons/quit_32x32.png")));

					JMenuItem exit = new JMenuItem(
							LangTools.getResourceBundleWordLanguage(rb, "Exit", "maingui.exit") + "?");
					exit.setIcon(new ImageIcon(getClass().getResource("/icons/out24x24.png")));
					jMenuquit.add(exit);
					exit.setActionCommand(QUIT);
					exit.addActionListener(this);

					if (!isAppImageInstallation()) {
						JMenuItem restart = new JMenuItem(
								LangTools.getResourceBundleWordLanguage(rb, "Restart", "general.restart"));
						restart.setIcon(new ImageIcon(getClass().getResource("/icons/restart24x24.png")));

						jMenuquit.add(restart);
						restart.setActionCommand(RESTART);
						restart.addActionListener(this);
					}
				}
			}
			pack();
			setSize(1250, 650);
			setPreferredSize(new Dimension(1250, 650));
		} catch (Exception e) {
			Logger.error(e);
		}
	}

	/**
	 * Sets the look and feel.
	 */
	private void setLookandFeel() {
		// http://insubstantial.github.io/insubstantial/substance/learn.html
		String lookfeelname = PropertiesWorker.getStringProperty(StaticGlobalFields.RENCODERCONFIGFILE,
				StaticGuiFieldNames.fileLookandFeelPropkey);
		Looktypes look = Looktypes.getLookAndFeel(lookfeelname);

		try {

			if (look.usesTheme())
				UIManager.setLookAndFeel(look.getLookAndFeel());
			else
				UIManager.setLookAndFeel(look.getNamespace());

			if (look.usesSkin())
				look.setSkin();

			SwingUtilities.updateComponentTreeUI(this);
			

		} catch (Exception e) {
			Logger.error(e);
			showErrorMessage(
					"It was not possible to load the theme previously selected in the rencoder, the default theme will be used.",
					null);
			try {
				UIManager.setLookAndFeel(Looktypes.PGSLOOKANDFEEL.getNamespace());
				PropertiesWorker.ChangePropertiesParam(StaticGlobalFields.RENCODERCONFIGFILE,
						StaticGuiFieldNames.fileLookandFeelPropkey, Looktypes.PGSLOOKANDFEEL.name());
				SwingUtilities.updateComponentTreeUI(this);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e1) {
				Logger.error(e1.getMessage());
			}
		}

	}

	public void setShutdownProcess(ShutdownRencoder off) {
		this.shutdown = off;
	}

	public void setRestartProcess(RestartRencoder restart) {
		this.restart = restart;
	}

	public boolean checkAdminRightsWindows() {
		return OSystem.isAdmin();
	}

	public void checkUpdatesOnlyMacOS() {
		if (OSystem.isMacOS() && !CheckForUpdates.isCheckUpdatesBlocked()) {
			boolean existupdate = CheckForUpdates.updateIsNeeded();
			if (existupdate)
				launchUpdateWarningPanel();
		}
	}

	/**
	 * Sets the tool tips.
	 */
	private void setToolTips() {
		boolean state = PropertiesWorker.getBooleanPropertyWithDefault(
				StaticGlobalFields.RENCODERCONFIGFILE, StaticGuiFieldNames.GuiToolTips, true);
		ToolTipManager.sharedInstance().setEnabled(state);
		ToolTipManager.sharedInstance().setInitialDelay(0);
	}

	private void checkVLC() {
		StaticVlcMethods.checkVLCinstalled();
	}

	/**
	 * Load profiles.
	 */
	private void LoadProfiles() {
		this.profilemanager = EncodingProfileManager.getInstance();
	}

	/**
	 * Load general encoding info.
	 */
	private void LoadGeneralEncodingInfo() {
		this.genencinfomanager = new GeneralEncodingPropertiesManager();
	}

	public void resetGeneralEncodingPropertiesToDefault() {
		IGeneralVideoEncInfoContainer defaultcont = null;
		if (FFmpegManager.getInstance().isCodecSupported(VideoCodecs.H264.toString(),
				VideoCodecs.H264.getFFmpegID(), false))
			defaultcont = new VideoH264EncodingInfoContainer();
		else
			defaultcont = new VideoMPEG4EncodingInfoContainer();
		defaultcont.saveConfigurations(StaticVideoEncoderFields.GENERALSETTINGSFILEPATH, null, false);
		LoadGeneralEncodingInfo();
	}

	/**
	 * Sets the general output folder.
	 * 
	 * @throws ConfigurationException
	 */
	private void setGeneralOutputFolder() {
		String Outputfolder = ProcessFilesAux.getOutputFilesFolderPath();
		if (Outputfolder == null) {
			chckbxsameorigin.setSelected(true);
			setAutomaticOutputDir();
			Logger.info("Output Folder: " + "Automatic");
		} else {

			setCustomOutputDir();
			jTextFieldsavePath.setText(Outputfolder);
			jTextFieldsavePath.setEditable(false);
			Logger.info("Output Folder: " + Outputfolder);
		}
	}

	public void checkFFmpegExecutable() {

		boolean initffmpeg;
		try {
			initffmpeg = FFmpegManager.getInstance().setListener(this).LoadFFmpeg();
			if (!initffmpeg)
				setupFFMPEG();
		} catch (InterruptedException | IOException e) {
			showErrorMessage(e.getMessage(), null);
		}

	}

	/**
	 * Setup ffmpeg.
	 */
	protected void setupFFMPEG() {

		try {
			FFmpegUtils.removeFFmpegFontsConfiguredTagFile();
			configffmpegpanel = new ConfigurationFFmpegExecutable(this);
			configffmpegpanel.setLocationRelativeTo(this);
			configffmpegpanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			configffmpegpanel.setVisible(true);
		} catch (Exception e) {
			Logger.error(e);
		}
	}

	public void configureFFmpegFontsInbackground() {
		String path = FFmpegUtils.getFFmpegExePath();
		if (path != null) {

			File confcheck = new File(FFmpegUtils.getFFmpegFontsConfiguredTagFile());
			if (!confcheck.exists()) {
				SwingUtilities.invokeLater(new BackgroundFFmpegFontsConfig(this));

			}
		}
	}

	private void populateProfileComboBox() {

		String[] profnames = null;
		if (this.profilemanager != null) {
			profnames = new String[this.profilemanager.getnameProfiles().length + 1];
			profnames[0] = LangTools.getResourceBundleWordLanguage(rb, "User default profile",
					"profilegui.userdefault");

			ArrayList<String> profilesnames = this.profilemanager.getArrayListOfProfileNames();
			Collections.sort(profilesnames);
			for (int i = 0; i < profilesnames.size(); i++) {
				profnames[i + 1] = profilesnames.get(i);
			}

		} else {
			profnames = new String[1];
			profnames[0] = LangTools.getResourceBundleWordLanguage(rb, "User default profile",
					"profilegui.userdefault");
		}

		DefaultComboBoxModel<String> profilenames = new DefaultComboBoxModel<>(profnames);
		jComboBoxprofiles.setModel(profilenames);
	}

	public void reloadProfiles() {

		EncodingProfileManager.getInstance().reloadProfiles();
		jComboBoxprofiles.removeAllItems();
		populateProfileComboBox();

	}

	private void setInitProfileComboxSettings() {
		jButton1.setEnabled(false);
		jButtonselectedmoviessets.setEnabled(false);
	}

	private void changeProfileCombox() {
		jButton1.setEnabled(true);
		jButtonselectedmoviessets.setEnabled(true);
	}

	/**
	 * Sets the action listeners gui components.
	 */
	private void setActionListenersGuiComponents() {
		this.videoPanel.addActionsListenersToVideoPanel(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();

		if (action.equals(LOOK)) {
			showlookpanel();
		} else if (action.equals(LookPanelGui.LOOK_PANEL_OK)) {
			try {
				lookfeelconfpanel.setLook();
				lookfeelconfpanel.setNewTipsState();
				lookfeelconfpanel.changelocale();
				if (lookfeelconfpanel.doRestart())
					restartApp();
			} catch (Exception e2) {
				restartApp();
			}
		} else if (action.equals(LookPanelGui.LOOK_PANEL_CANCEL)) {
			lookfeelconfpanel.close();
		} else if (action.equals(LOADFILESFROMFOLDER))
			LoadFilesInFolder();

		else if (action.equals(LOADFILESFROMFOLDERANDSUBFOLDERS))
			LoadFilesInFolderandsubFolders();

		else if (action.equals(VideoMainPanel.RESETMOVIETABLE))
			this.videoPanel.resetTableList();

		else if (action.equals(VideoMainPanel.REMOVESELECTEDROWS)) {

			if (this.videoPanel.getMovieFiles() == null)
				showWarningMessage(
						LangTools.getResourceBundleWordLanguage(rb, "Movie list its empty",
								"warngui.emptylist"),
						LangTools.getResourceBundleWordLanguage(rb, "Warning", "warngui.tag"));
			else
				this.videoPanel.RemoveSelectedRowsFromMovieTable();

		}

		else if (action.equals(VideoMainPanel.ADDMOVIES))
			LoadSelectedFiles();
		else if (action.equals(ADDMOVIESMENU))
			LoadSelectedFiles();

		else if (action.equals(VideoMainPanel.ADDSUBTITLE))
			addSelectedSubtitleToMovie();

		else if (action.equals(VideoMainPanel.CONFIGURESUBTITLES))
			this.videoPanel.LaunchSoftSubsConfigPanel();

		else if (action.equals(VideoMainPanel.SELECTALLMOVIES))
			this.videoPanel.selectDeselectAllMovies();

		else if (action.equals(VIDEOSINGLESETTINGS))
			showEncoderVideoSettings();

		else if (action.equals(VideoMainPanel.REMOVESUBTITLE))
			removeSelectedSubtitleToMovie();

		else if (action.equals(CHANGEOUTPUTFOLDERFILES))
			changesavepathfolder();

		else if (action.equals(CHECKENCODERS))
			checkEncoders();

		else if (action.equals(ConverterConfigurations.SAVECONFIGTOALLMOVIES))
			setSameEncodingConfigToAllMovies();

		else if (action.equals(MainGeneralConfigPanel.SAVEMAINENCOCONFIGS)) {
			encodconfigpanel.saveNewEncodingProperties();
			this.videoPanel.setSameConvConfigInAllMovies(encodconfigpanel.getEncoInfoContainer());
			encodconfigpanel.dispose();
		}

		else if (action.equals(SETUPENCODCONFIGS))
			setupEncodingConfigurations();

		else if (action.equals(SETUPFFMPEG))
			setupFFMPEG();

		else if (action.equals(QUIT)) {
			this.dispose();
			StaticVlcMethods.removeVLCNOTFOUNDTAG();
			StaticGlobalMethods.removeRestartTag();
			OSystem.deleteTempFolder();
			if (encodingPanel != null)
				encodingPanel.stopAllProcessesAndClose();
			this.shutdown.run();
			System.exit(0);
		}

		else if (action.equals(CONVERT))
			startConvert();

		else if (action.equals(SHOWABOUTPANEL))
			showAboutPanel();
		else if (action.equals(OPENFOLDER))
			OpenFolderSaveConverted();
		else if (action.equals(OPENMANUAL))
			openManual();
		else if (action.equals(OPENLOGS))
			openLogsPanel();
		else if (action.equals(CHECKUPDATESPANEL)) {	
			launchSearchForUpdatesPanel(CheckForUpdates.updateIsNeeded());
		} else if (action.equals(PLAYMEDIA)) {
			playMedia();
		} else if (action.equals(APPLYFILTERS)) {
			launchFiltersPanel();
		}

		else if (action.equals(APPLYPROFILEALLMOVIES)) {
			setProfileForAllMovies();
		} else if (action.equals(APPLYPROFILESELECTEDMOVIES)) {
			setProfileForSelectedMovies();
		} else if (action.equals(CHANGEPROFILECOMBOX)) {
			changeProfileCombox();
		} else if (action.equals(MERGEFILES)) {
			MergeVideos();
		}

		else if (action.equals(RESTART)) {
			restartApp();
		} else if (action.equals(AUTOOUTDIR)) {
			setAutomaticOutputDir();
			changeSaveFolderToVideoFiles(null);
		} else if (action.equals(CUSTOMOUTDIR)) {
			setCustomOutputDir();
			changeSaveFolderToVideoFiles(OSystem.getandSetDefaultSaveFolder());
		}

	}

	private void setAutomaticOutputDir() {
		chckbxletmechoose.setSelected(false);
		jTextFieldsavePath.setEnabled(false);
		jButtonchangesavepath.setEnabled(false);
		jButtonopenfolder.setEnabled(false);
	}

	private void setCustomOutputDir() {
		chckbxsameorigin.setSelected(false);
		jTextFieldsavePath.setEnabled(true);
		jButtonchangesavepath.setEnabled(true);
		jButtonopenfolder.setEnabled(true);
	}

	private void restartApp() {
		if (OSystem.isLinux())
			checkIfAppImageInstallationOnRestart();

		if (!isAppImageInstallation()) {
			Thread t = new Thread(this.restart);
			t.run();
		}
		System.exit(0);
	}

	protected void setProfileForAllMovies() {

		if (this.videoPanel.getMovieFiles() != null) {
			if (jComboBoxprofiles.getSelectedIndex() == 0) {
				this.videoPanel
				.setSameConvConfigInAllMovies(this.genencinfomanager.getGenEncInfoContainer());
			} else {
				String profname = (String) jComboBoxprofiles.getSelectedItem();
				IGeneralVideoEncInfoContainer profinfo =
						this.profilemanager.getEncodingInfoContainerbyname(profname);
				profinfo.setProfileTypeTag(profname);
				this.videoPanel.setSameConvConfigInAllMovies(profinfo);
			}
		} else {
			showWarningMessage(
					LangTools.getResourceBundleWordLanguage(rb, "Movie list its empty", "warngui.emptylist"),
					LangTools.getResourceBundleWordLanguage(rb, "Warning", "warngui.tag"));
		}

	}

	protected void setProfileForSelectedMovies() {

		if (this.videoPanel.getMovieFiles() != null) {
			if (this.videoPanel.getSelectedMovieIndex() > -1
					&& this.videoPanel.getSelectedMovies().length > 0) {
				if (jComboBoxprofiles.getSelectedIndex() == 0) {
					this.videoPanel
					.setSameConvConfigforSelecteMovies(this.genencinfomanager.getGenEncInfoContainer());
				} else {
					String profname = (String) jComboBoxprofiles.getSelectedItem();
					IGeneralVideoEncInfoContainer profinfo =
							this.profilemanager.getEncodingInfoContainerbyname(profname);
					profinfo.setProfileTypeTag(profname);
					this.videoPanel.setSameConvConfigforSelecteMovies(profinfo);
				}
			} else
				showWarningMessage(
						LangTools.getResourceBundleWordLanguage(rb, "Please select a movie",
								"warngui.selectmovie"),
						LangTools.getResourceBundleWordLanguage(rb, "Warning", "warngui.tag"));
		} else {
			showWarningMessage(
					LangTools.getResourceBundleWordLanguage(rb, "Movie list its empty", "warngui.emptylist"),
					LangTools.getResourceBundleWordLanguage(rb, "Warning", "warngui.tag"));

		}

	}

	/**
	 * Load files in folder.
	 */
	protected void LoadFilesInFolder() {

		ArrayList<String> folderfiles = ListFiles.getitemsInFolderWithFileChooser(this);
		if (folderfiles != null) {
			ProcessFiles proc = null;
			try {
				SimpleTextMSGPanel panel = VideoPanelUtils.getImportFilesWarningPanel(folderfiles, this);
				proc = new ProcessFiles(folderfiles, this.genencinfomanager);

				if (panel != null) {
					VideoPanelUtils.RunProcessFilesInBackground(panel, proc);
				} else {
					try {
						proc.processVideoFiles();
					} catch (CloneNotSupportedException e) {
						Logger.error(e);
					}
				}

			} catch (IOException e) {
				if (e instanceof FileInformationIOException) {
					String msg = ((FileInformationIOException) e).getErrorFile();
					msg = LangTools.getResourceBundleWordLanguage(rb, "The following file it is corrupted:",
							"warngui.errorinputfile") + " " + msg;
					showErrorMessage(msg, "Corrupted File");
				}

				else {
					String msg = LangTools.getResourceBundleWordLanguage(rb, "Please check FFmpeg settings",
							"warngui.errorffmpegfile");
					showErrorMessage(msg, null);
				}
			}
			if (proc != null) {
				ArrayList<Videofile> videos = proc.getFilteredVideoFiles();
				this.videoPanel.addVideoFiles(videos);
			}
		}

	}

	/**
	 * Load files in folderandsub folders.
	 */
	protected void LoadFilesInFolderandsubFolders() {

		ArrayList<String> folderfiles =
				ListFiles.getListofItemsInFolderAndSubFoldersWithFilechooser(this);
		if (folderfiles != null) {
			ProcessFiles proc = null;
			try {
				SimpleTextMSGPanel panel = VideoPanelUtils.getImportFilesWarningPanel(folderfiles, this);
				proc = new ProcessFiles(folderfiles, this.genencinfomanager);
				if (panel != null) {
					VideoPanelUtils.RunProcessFilesInBackground(panel, proc);
				} else {
					try {
						proc.processVideoFiles();
					} catch (CloneNotSupportedException e) {
						Logger.error(e);
					}
				}
			} catch (IOException e) {
				if (e instanceof FileInformationIOException) {
					String msg = ((FileInformationIOException) e).getErrorFile();
					msg = LangTools.getResourceBundleWordLanguage(rb, "The following file it is corrupted:",
							"warngui.errorinputfile") + " " + msg;
					showErrorMessage(msg, null);
					Logger.error(msg);
				}

				else {
					String msg = LangTools.getResourceBundleWordLanguage(rb, "Please check FFmpeg settings",
							"warngui.errorffmpegfile");
					showWarningMessage(msg, null);
					Logger.error(msg);
				}
			}
			if (proc != null) {
				ArrayList<Videofile> videos = proc.getFilteredVideoFiles();
				this.videoPanel.addVideoFiles(videos);
			}
		}
	}

	/**
	 * Load selected files.
	 */
	protected void LoadSelectedFiles() {

		ArrayList<String> files =
				ListFiles.getSelectedItemsTypeInFolderWithFilechooser(allowedFileTypes, this);
		ProcessFiles proc = null;
		try {

			SimpleTextMSGPanel panel = VideoPanelUtils.getImportFilesWarningPanel(files, this);

			proc = new ProcessFiles(files, this.genencinfomanager);
			if (panel != null) {
				VideoPanelUtils.RunProcessFilesInBackground(panel, proc);
			} else {
				try {
					proc.processVideoFiles();
				} catch (CloneNotSupportedException e) {
					Logger.error(e);
				}
			}
		} catch (IOException e) {
			if (e instanceof FileInformationIOException) {
				String msg = ((FileInformationIOException) e).getErrorFile();
				msg = LangTools.getResourceBundleWordLanguage(rb, "The following file it is corrupted:",
						"warngui.errorinputfile") + " " + msg;
				showErrorMessage(msg, null);
			}

			else {
				String msg = LangTools.getResourceBundleWordLanguage(rb, "Please check FFmpeg settings",
						"warngui.errorffmpegfile");
				showWarningMessage(msg, null);
			}

		}
		if (proc != null) {
			ArrayList<Videofile> videos = proc.getFilteredVideoFiles();
			this.videoPanel.addVideoFiles(videos);
		}

	}

	/**
	 * Changesavepathfolder.
	 */
	private void changesavepathfolder() {

		String folder = ListFiles.getFolderpathFileChooser(this);
		changeSaveFolderToVideoFiles(folder);
	}

	private void changeSaveFolderToVideoFiles(String folderpath) {

		jTextFieldsavePath.setText(folderpath == null ? "" : folderpath);

		if (this.videoPanel.getMovieFiles() != null) {
			IndexedHashMap<String, Videofile> movies = this.videoPanel.getMovieFiles();
			for (int i = 0; i < movies.size(); i++) {
				movies.getValueAt(i).getEncodingInfoContainer().setOutputFolder(folderpath);
			}
		}

		this.genencinfomanager.getGenEncInfoContainer().setOutputFolder(folderpath);

		PropertiesWorker.ChangePropertiesParam(StaticGlobalFields.RENCODERCONFIGFILE,
				StaticVideoEncoderFields.SAVECONVERTEDFILEPATH, folderpath == null ? "" : folderpath);

		Logger.debug("Output folder was changed to: " + folderpath);

	}

	/**
	 * Open folder save converted.
	 */
	private void OpenFolderSaveConverted() {
		String folder = ProcessFilesAux.getOutputFilesFolderPath();

		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().open(new File(folder));
			} catch (IOException e) {

				ListFiles.OpenSystemFolder(folder);
			}
		} else {
			ListFiles.OpenSystemFolder(folder);
		}

	}

	/**
	 * Adds the selected subtitle to movie.
	 */
	protected void addSelectedSubtitleToMovie() {

		if (this.videoPanel.getMovieFiles() != null) {
			if (this.videoPanel.getSelectedMovieIndex() > -1) {
				IndexedHashMap<String, FileTypeFilter> allowedSubtitles =
						ProcessFilesAux.getAllowedSubtitlesExtension();
				String subfilepath =
						ListFiles.getSingleItemTypeInFolderWithFilechooser(allowedSubtitles, this);
				Subtitlefile subfile =
						ProcessFiles.FilterAllowedSubtitleFile(subfilepath, allowedSubtitles);
				if (subfile != null)
					this.videoPanel.addSubtitleFile(subfile);
			} else
				showWarningMessage(
						LangTools.getResourceBundleWordLanguage(rb, "Please select a movie",
								"warngui.selectmovie"),
						LangTools.getResourceBundleWordLanguage(rb, "Warning", "warngui.tag"));
		} else
			showWarningMessage(
					LangTools.getResourceBundleWordLanguage(rb, "Movie list its empty", "warngui.emptylist"),
					LangTools.getResourceBundleWordLanguage(rb, "Warning", "warngui.tag"));
	}

	/**
	 * Removes the selected subtitle to movie.
	 */
	protected void removeSelectedSubtitleToMovie() {

		if (this.videoPanel.getMovieFiles() != null) {
			if (this.videoPanel.getSelectedMovieIndex() > -1) {
				this.videoPanel.RemoveSelectedSubtitle();
			} else
				showWarningMessage(
						LangTools.getResourceBundleWordLanguage(rb, "Please select a movie",
								"warngui.selectmovie"),
						LangTools.getResourceBundleWordLanguage(rb, "Warning", "warngui.tag"));
		} else
			showWarningMessage(
					LangTools.getResourceBundleWordLanguage(rb, "Movie list its empty", "warngui.emptylist"),
					LangTools.getResourceBundleWordLanguage(rb, "Warning", "warngui.tag"));
	}

	/**
	 * Showlookpanel.
	 */
	protected void showlookpanel() {
		try {
			lookfeelconfpanel = new LookPanelGui(this);
			lookfeelconfpanel.setRestarter(this.restart);
			lookfeelconfpanel.setLocationRelativeTo(this);
			lookfeelconfpanel.addListeners(this);
			lookfeelconfpanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			lookfeelconfpanel.setVisible(true);
		} catch (Exception e) {
			Logger.error(e);
		}

	}

	protected void playMedia() {

		boolean vlcinstalled = StaticVlcMethods.isVLCFOUND();
		if (vlcinstalled) {
			Videofile movie = this.videoPanel.getSelectedMovie();
			if (movie != null) {
				String moviepath = movie.getFilePath();
				Subtitlefile sub = movie.getFirstSelectedSubtitleFile();
				String subpath = null;
				if (sub != null)
					subpath = sub.getFilePath();

				RunPlayer player = new RunPlayer(moviepath, subpath, false);
				player.setParentComponent(this);
				SwingUtilities.invokeLater(player);

			} else {
				if (this.videoPanel.getMovieFiles() == null)
					showWarningMessage(
							LangTools.getResourceBundleWordLanguage(rb, "Movie list its empty",
									"warngui.emptylist"),
							LangTools.getResourceBundleWordLanguage(rb, "Warning", "warngui.tag"));
				else
					showWarningMessage(
							LangTools.getResourceBundleWordLanguage(rb, "Please select a movie",
									"warngui.selectmovie"),
							LangTools.getResourceBundleWordLanguage(rb, "Warning", "warngui.tag"));

			}
		} else {
			showWarningMessage(
					LangTools.getWordLanguage("Please install VLC Player to enable this feature",
							"warngui.vlcerror"),
					LangTools.getResourceBundleWordLanguage(rb, "Warning", "warngui.tag"));
		}
	}

	/**
	 * Show encoder video settings.
	 */
	protected void showEncoderVideoSettings() {
		try {
			if (this.vidconfset == null) {
				Videofile movie = this.videoPanel.getSelectedMovie();
				if (movie != null) {
					vidconfset =
							new ConverterConfigurations(this.profilemanager, this.genencinfomanager, movie, this);
					vidconfset.addActionListenerFromMainGUI(this);
					vidconfset.addProcessesPropertyChangeListener(this);
					vidconfset.setSubtitlePanel(this.videoPanel.getSubtitlesPanel());
					vidconfset.setLocationRelativeTo(this);
					vidconfset.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					vidconfset.setVisible(true);
				} else {
					if (this.videoPanel.getMovieFiles() == null)
						showWarningMessage(
								LangTools.getResourceBundleWordLanguage(rb, "Movie list its empty",
										"warngui.emptylist"),
								LangTools.getResourceBundleWordLanguage(rb, "Warning", "warngui.tag"));
					else
						showWarningMessage(
								LangTools.getResourceBundleWordLanguage(rb, "Please select a movie",
										"warngui.selectmovie"),
								LangTools.getResourceBundleWordLanguage(rb, "Warning", "warngui.tag"));
				}

			} else
				vidconfset.toFront();

		} catch (Exception e) {
			Logger.error(e);
		}

	}

	/**
	 * Sets the same encoding config to all movies.
	 */
	protected void setSameEncodingConfigToAllMovies() {
		IGeneralVideoEncInfoContainer config = this.vidconfset.getCommonEncodInfoContainer();
		this.videoPanel.setSameConvConfigInAllMovies(config);
	}

	/**
	 * Start convert.
	 */
	protected void startConvert() {

		try {

			IndexedHashMap<String, Videofile> files = this.videoPanel.getMovieFilesToConvert();

			if (files != null && files.size() > 0) {
				IndexedHashMap<String, Videofile> convertvideos = filteralreadyaddedToPipeline(files);

				EncoderControlCenter controlcenter = new EncoderControlCenter(convertvideos);

				if (encodingPanel == null) {
					encodingPanel = new EncodingProgressPanel(controlcenter.getInfoToConvertFiles());
					encodingPanel.addProcessesPropertyChangeListener(this);
					encodingPanel.setLocationRelativeTo(this);

					encodingPanel.setVisible(true);
					Thread t = new Thread(encodingPanel);
					t.run();
					jButtonconvert.setText(LangTools.getResourceBundleWordLanguage(rb,
							"<html><center>Add to running<br />conversions</center></html>",
							"maingui.addtoconversion"));
				} else {
					this.encodingPanel.addProcessesToConvert(controlcenter.getInfoToConvertFiles());
				}
			} else {
				if (files == null) {
					showWarningMessage(
							LangTools.getResourceBundleWordLanguage(rb, "Movie list its empty",
									"warngui.emptylist"),
							LangTools.getResourceBundleWordLanguage(rb, "Warning", "warngui.tag"));
				} else
					showWarningMessage(
							LangTools.getResourceBundleWordLanguage(rb,
									"Please choose at least a movie to convert", "warngui.movietoconv"),
							LangTools.getResourceBundleWordLanguage(rb, "Warning", "warngui.tag"));
			}

		} catch (Exception e) {
			Logger.error(e);
		}

	}

	protected void MergeVideos() {

		ArrayList<Videofile> movfiles = this.videoPanel.getListMovieFile();
		MergeVideosPanel panel =
				new MergeVideosPanel(movfiles, this.profilemanager, this.genencinfomanager);
		panel.setLocationRelativeTo(this);
		panel.setVisible(true);

	}

	private IndexedHashMap<String, Videofile> filteralreadyaddedToPipeline(
			IndexedHashMap<String, Videofile> newfiles) {
		IndexedHashMap<String, Videofile> res = new IndexedHashMap<>();

		for (int i = 0; i < newfiles.size(); i++) {
			String filename = newfiles.getKeyAt(i);
			if (!moviesalreadyinconvproc.contains(filename)) {
				res.put(filename, newfiles.getValueAt(i));
				moviesalreadyinconvproc.add(filename);
			}

		}

		return res;

	}

	/**
	 * Setup encoding configurations.
	 */
	protected void setupEncodingConfigurations() {

		try {
			encodconfigpanel =
					new MainGeneralConfigPanel(this.profilemanager, this.genencinfomanager, this);
			encodconfigpanel.setJTextFieldOfOutputfolderMainPanel(jTextFieldsavePath);
			encodconfigpanel.addSaveButtonActionListener(this);
			encodconfigpanel.setLocationRelativeTo(this);
			encodconfigpanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			encodconfigpanel.setVisible(true);
		} catch (Exception e) {
			Logger.error(e);
		}
	}

	/**
	 * Check encoders.
	 */
	protected void checkEncoders() {

		try {
			encoderspanel = new InformationEncodersPanel();
			encoderspanel.setLocationRelativeTo(this);
			encoderspanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			encoderspanel.setVisible(true);
		} catch (Exception e) {
			Logger.error(e);
		}
	}

	/**
	 * Show about panel.
	 */
	protected void showAboutPanel() {

		try {
			aboutpanel = new AboutInformationPanel();
			aboutpanel.setLocationRelativeTo(this);
			aboutpanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			aboutpanel.setVisible(true);
		} catch (Exception e) {
			Logger.error(e);
		}
	}

	/**
	 * Open manual.
	 */
	protected void openManual() {
		String manualfolderpath = new File(StaticGlobalFields.MANUALFOLDER).getAbsolutePath();
		String manualpath =
				manualfolderpath + OSystem.getSystemSeparator() + StaticGlobalFields.MANUALNAME;

		ListFiles.OpenFileInSystem(manualpath, false);
	}

	/**
	 * Open logs panel.
	 */
	protected void openLogsPanel() {
		try {
			logspanel = new RencoderLogsPanel();
			logspanel.setLocationRelativeTo(this);
			logspanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			logspanel.setVisible(true);
		} catch (Exception e) {
			Logger.error(e);
		}

	}

	protected void launchAdminWarnPanel() {
		msgPanel = new WarnJTextPanePanel(LangTools.getResourceBundleWordLanguage(rb,
				"Please start Rencoder with administrator rights", "warngui.adminrights"), this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
	  
	  
		if ("closeencodingprocesses" == evt.getPropertyName()) {
			this.encodingPanel = null;
			this.moviesalreadyinconvproc = new LinkedHashSet<>();
			jButtonconvert.setText(LangTools.getResourceBundleWordLanguage(rb, "Start conversion",
					"maingui.startconversion"));
		} else if ("closeconverterconfigurations" == evt.getPropertyName()) {
			this.vidconfset = null;
		} else if ("closefilterspanel" == evt.getPropertyName()) {
			this.filters = null;
		}else if(FFmpegCodecInfoAnalyser.PROGRESSMSG == evt.getPropertyName()) {
		  lblLabelInfo.setText((String) evt.getNewValue());
	    }

	}

	protected void launchUpdateWarningPanel() {
		this.macupdatespanel = new AutoCheckForUpdates(false);
		macupdatespanel.setLocationRelativeTo(this);
		macupdatespanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		macupdatespanel.setModal(true);
		macupdatespanel.setVisible(true);
	}

	protected void launchSearchForUpdatesPanel(boolean updatestatus) {
		newupdatespanel = new AutoCheckForUpdates(updatestatus);
		newupdatespanel.setLocationRelativeTo(this);
		newupdatespanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		newupdatespanel.setModal(true);
		newupdatespanel.setVisible(true);
	}

	protected void launchFiltersPanel() {
		try {
			if (this.filters == null) {
				Videofile movie = this.videoPanel.getSelectedMovie();
				if (movie != null) {
					filters = new FiltersPanel(movie, this);
					filters.addpanelPropertyChangeListener(this);
					filters.setLocationRelativeTo(this);
					filters.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					filters.setVisible(true);
				} else {
					if (this.videoPanel.getMovieFiles() == null)
						showWarningMessage(
								LangTools.getResourceBundleWordLanguage(rb, "Movie list its empty",
										"warngui.emptylist"),
								LangTools.getResourceBundleWordLanguage(rb, "Warning", "warngui.tag"));
					else
						showWarningMessage(
								LangTools.getResourceBundleWordLanguage(rb, "Please select a movie",
										"warngui.selectmovie"),
								LangTools.getResourceBundleWordLanguage(rb, "Warning", "warngui.tag"));

				}
			} else
				filters.toFront();

		} catch (Exception e) {
			Logger.error(e);
		}
	}

	public void showErrorMessage(String errormsg, String title) {
		JOptionPane.showMessageDialog(this, errormsg,
				title == null ? LangTools.getResourceBundleWordLanguage(rb, "Warning", "warngui.tag")
						: title,
						JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/icons/error.png")));
	}

	public void showWarningMessage(String warningmsg, String title) {
		JOptionPane.showMessageDialog(this, warningmsg,
				title == null ? LangTools.getResourceBundleWordLanguage(rb, "Warning", "warngui.tag")
						: title,
						JOptionPane.INFORMATION_MESSAGE,
						new ImageIcon(getClass().getResource("/icons/warning64x64.png")));
	}

	private boolean isAppImageInstallation() {
		String appimageflag =
				FilenameUtils.concat(OSystem.getCurrentDir(), StaticGlobalFields.APPIMAGEINSTALL);

		if (new File(appimageflag).exists())
			return true;
		return false;
	}

	private void checkIfAppImageInstallationOnRestart() {
		if (isAppImageInstallation())
			showWarningMessage(
					LangTools.getResourceBundleWordLanguage(rb,
							"rencoder needs to restart, please open the application again after it closes.",
							"warngui.restart.needed"),
					LangTools.getResourceBundleWordLanguage(rb, "Rencoder needs to be restarted",
							"warngui.restart.info"));
	}

}
