package pt.ornrocha.rencoder.gui.components.panels.configurations;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.tinylog.Logger;

import eu.hansolo.custom.SteelCheckBox;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.H264NvencCoder;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.H264NvencRateControlPreset;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.NvencPresets;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.PixelFormatNvenc;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.ProfilesH264Nvenc;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoLevel;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoH264NvencEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.extrainfocontainer.H264NvencExtraInfoContainer;

public class H264NvencConfigurationsPanel extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VideoH264NvencEncodingInfoContainer infocont;
	private JPanel panel_extra;
	private JPanel panel_main;
	private JPanel rootpanel;
	private SteelCheckBox stlchckbxUseAdvancedOptions;
	private JLabel lblPreset;
	private JComboBox<NvencPresets> comboBoxpresets;
	private JLabel lblProfile;
	private JComboBox<ProfilesH264Nvenc> comboBoxprofiles;
	private JLabel lblPixelFormat;
	private JLabel lblLevel;
	private JComboBox<PixelFormatNvenc> comboBoxpixelformat;
	private JComboBox<VideoLevel> comboBoxlevel;
	private JLabel lblRatecontrolPreset;
	private JComboBox<H264NvencRateControlPreset> comboBoxratecontrol;
	private JButton btnNewButton;
	private JButton btnSave;

	private JScrollPane scrollPane;
	private JPanel panel;
	private JSpinner spinnerrclookhead;
	private JLabel lblNewLabel_1;
	private JSpinner spinnersurfaces;
	private JLabel lblSurfaces;
	private JSpinner spinnerdelay;
	private JLabel lblDelay;
	private JSpinner spinneraqstrenght;
	private JLabel lblNewLabel_2;
	private JSpinner spinnerinitqpp;
	private JLabel lblNewLabel_3;
	private JSpinner spinnerinitqpb;
	private JLabel lblInitQpb;
	private JSpinner spinnerintqpl;
	private JLabel lblInitQpi;
	private JCheckBox chckbxNoscenecut;
	private JCheckBox chckbxForcedidr;
	private JCheckBox chckbxBadapt;
	private JCheckBox chckbxSpatialaq;
	private JCheckBox chckbxTemporalaq;
	private JCheckBox chckbxZerolatency;
	private JCheckBox chckbxNonrefp;
	private JCheckBox chckbxStrictgop;
	private JComboBox<H264NvencCoder> comboBoxcoder;
	private JLabel lblCoder;

	private static String CLOSE = "close";
	private static String SAVECLOSE = "saveclose";
	private static String OPENEXTENDEDPANEL = "extendedpanel";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					VideoH264NvencEncodingInfoContainer cont = new VideoH264NvencEncodingInfoContainer();

					H264NvencConfigurationsPanel dialog = new H264NvencConfigurationsPanel(cont);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
				    Logger.error(e);
				}
			}
		});
	}

	public H264NvencConfigurationsPanel(VideoH264NvencEncodingInfoContainer infocont) {
		initGUI();
		this.infocont = infocont;
		initializeComponents();
		setInfoFromEncodingContainer();

	}

	/**
	 * Create the dialog.
	 */
	private void initGUI() {
		setBounds(100, 100, 807, 363);

		rootpanel = new JPanel();
		getContentPane().add(rootpanel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 1, 1 };
		gbl_panel.rowHeights = new int[] { 1 };
		gbl_panel.columnWeights = new double[] { 1.0, 1.0 };
		gbl_panel.rowWeights = new double[] { 1.0 };
		rootpanel.setLayout(gbl_panel);

		panel_main = new JPanel();
		this.panel_main.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), LangTools.getWordLanguage("H264 Nvenc settings", "h264nvenc.settings"),
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		GridBagConstraints gbc_panel_main = new GridBagConstraints();
		gbc_panel_main.insets = new Insets(0, 0, 0, 5);
		gbc_panel_main.fill = GridBagConstraints.BOTH;
		gbc_panel_main.gridx = 0;
		gbc_panel_main.gridy = 0;
		rootpanel.add(panel_main, gbc_panel_main);
		GridBagLayout gbl_panel_main = new GridBagLayout();
		gbl_panel_main.columnWidths = new int[] { 1, 1 };
		gbl_panel_main.rowHeights = new int[] { 1, 1, 1, 1, 1, 1, 1, 1 };
		gbl_panel_main.columnWeights = new double[] { 0.0, 1.0 };
		gbl_panel_main.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		this.panel_main.setLayout(gbl_panel_main);

		this.stlchckbxUseAdvancedOptions = new SteelCheckBox();
		// stlchckbxUseAdvancedOptions.setEnabled(false);
		stlchckbxUseAdvancedOptions.addActionListener(this);
		stlchckbxUseAdvancedOptions.setActionCommand(OPENEXTENDEDPANEL);

		this.stlchckbxUseAdvancedOptions.setText(LangTools.getWordLanguage("Show advanced options", "videogui.advancedoptions"));
		this.stlchckbxUseAdvancedOptions.setColored(true);
		GridBagConstraints gbc_stlchckbxUseAdvancedOptions = new GridBagConstraints();
		gbc_stlchckbxUseAdvancedOptions.gridwidth = 2;
		gbc_stlchckbxUseAdvancedOptions.fill = GridBagConstraints.HORIZONTAL;
		gbc_stlchckbxUseAdvancedOptions.insets = new Insets(0, 20, 5, 0);
		gbc_stlchckbxUseAdvancedOptions.gridx = 0;
		gbc_stlchckbxUseAdvancedOptions.gridy = 0;
		this.panel_main.add(this.stlchckbxUseAdvancedOptions, gbc_stlchckbxUseAdvancedOptions);

		this.lblPreset = new JLabel("Preset:");
		GridBagConstraints gbc_lblPreset = new GridBagConstraints();
		gbc_lblPreset.anchor = GridBagConstraints.EAST;
		gbc_lblPreset.insets = new Insets(0, 20, 5, 5);
		gbc_lblPreset.gridx = 0;
		gbc_lblPreset.gridy = 1;
		this.panel_main.add(this.lblPreset, gbc_lblPreset);

		this.comboBoxpresets = new JComboBox<NvencPresets>();
		comboBoxpresets.setToolTipText("Encoding preset");
		GridBagConstraints gbc_comboBoxpresets = new GridBagConstraints();
		gbc_comboBoxpresets.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxpresets.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxpresets.gridx = 1;
		gbc_comboBoxpresets.gridy = 1;
		this.panel_main.add(this.comboBoxpresets, gbc_comboBoxpresets);

		this.lblProfile = new JLabel("Profile:");
		GridBagConstraints gbc_lblProfile = new GridBagConstraints();
		gbc_lblProfile.anchor = GridBagConstraints.EAST;
		gbc_lblProfile.insets = new Insets(0, 20, 5, 5);
		gbc_lblProfile.gridx = 0;
		gbc_lblProfile.gridy = 2;
		this.panel_main.add(this.lblProfile, gbc_lblProfile);

		this.comboBoxprofiles = new JComboBox<ProfilesH264Nvenc>();
		comboBoxprofiles.setToolTipText("Encoding profile");
		GridBagConstraints gbc_comboBoxprofiles = new GridBagConstraints();
		gbc_comboBoxprofiles.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxprofiles.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxprofiles.gridx = 1;
		gbc_comboBoxprofiles.gridy = 2;
		this.panel_main.add(this.comboBoxprofiles, gbc_comboBoxprofiles);

		this.lblPixelFormat = new JLabel("Pixel Format:");
		GridBagConstraints gbc_lblPixelFormat = new GridBagConstraints();
		gbc_lblPixelFormat.anchor = GridBagConstraints.EAST;
		gbc_lblPixelFormat.insets = new Insets(0, 20, 5, 5);
		gbc_lblPixelFormat.gridx = 0;
		gbc_lblPixelFormat.gridy = 3;
		this.panel_main.add(this.lblPixelFormat, gbc_lblPixelFormat);

		this.comboBoxpixelformat = new JComboBox<PixelFormatNvenc>();
		GridBagConstraints gbc_comboBoxpixelformat = new GridBagConstraints();
		gbc_comboBoxpixelformat.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxpixelformat.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxpixelformat.gridx = 1;
		gbc_comboBoxpixelformat.gridy = 3;
		this.panel_main.add(this.comboBoxpixelformat, gbc_comboBoxpixelformat);

		this.lblLevel = new JLabel("Level:");
		GridBagConstraints gbc_lblLevel = new GridBagConstraints();
		gbc_lblLevel.anchor = GridBagConstraints.EAST;
		gbc_lblLevel.insets = new Insets(0, 0, 5, 5);
		gbc_lblLevel.gridx = 0;
		gbc_lblLevel.gridy = 4;
		this.panel_main.add(this.lblLevel, gbc_lblLevel);

		this.comboBoxlevel = new JComboBox<VideoLevel>();
		comboBoxlevel.setToolTipText("Encoding level");
		GridBagConstraints gbc_comboBoxlevel = new GridBagConstraints();
		gbc_comboBoxlevel.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxlevel.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxlevel.gridx = 1;
		gbc_comboBoxlevel.gridy = 4;
		this.panel_main.add(this.comboBoxlevel, gbc_comboBoxlevel);

		this.lblRatecontrolPreset = new JLabel("Rate-control preset:");
		GridBagConstraints gbc_lblRatecontrolPreset = new GridBagConstraints();
		gbc_lblRatecontrolPreset.anchor = GridBagConstraints.EAST;
		gbc_lblRatecontrolPreset.insets = new Insets(0, 20, 5, 5);
		gbc_lblRatecontrolPreset.gridx = 0;
		gbc_lblRatecontrolPreset.gridy = 5;
		this.panel_main.add(this.lblRatecontrolPreset, gbc_lblRatecontrolPreset);

		this.comboBoxratecontrol = new JComboBox<H264NvencRateControlPreset>();
		GridBagConstraints gbc_comboBoxratecontrol = new GridBagConstraints();
		gbc_comboBoxratecontrol.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxratecontrol.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxratecontrol.gridx = 1;
		gbc_comboBoxratecontrol.gridy = 5;
		this.panel_main.add(this.comboBoxratecontrol, gbc_comboBoxratecontrol);

		/*
		 * this.lblNewLabel = new JLabel("Constant quantization:"); GridBagConstraints
		 * gbc_lblNewLabel = new GridBagConstraints(); gbc_lblNewLabel.insets = new
		 * Insets(0, 0, 5, 5); gbc_lblNewLabel.gridx = 0; gbc_lblNewLabel.gridy = 6;
		 * this.panel_main.add(this.lblNewLabel, gbc_lblNewLabel);
		 * 
		 * this.spinnerconstquant = new JSpinner(); GridBagConstraints
		 * gbc_spinnerconstquant = new GridBagConstraints(); gbc_spinnerconstquant.fill
		 * = GridBagConstraints.HORIZONTAL; gbc_spinnerconstquant.insets = new Insets(0,
		 * 0, 5, 0); gbc_spinnerconstquant.gridx = 1; gbc_spinnerconstquant.gridy = 6;
		 * this.panel_main.add(this.spinnerconstquant, gbc_spinnerconstquant);
		 */

		this.btnNewButton = new JButton(LangTools.getWordLanguage("Cancel", "general.cancel"));
		btnNewButton.setActionCommand(CLOSE);
		btnNewButton.addActionListener(this);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.SOUTH;
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 7;
		this.panel_main.add(this.btnNewButton, gbc_btnNewButton);

		this.btnSave = new JButton(LangTools.getWordLanguage("Save", "general.save"));
		btnSave.setActionCommand(SAVECLOSE);
		btnSave.addActionListener(this);
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.anchor = GridBagConstraints.SOUTH;
		gbc_btnSave.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = 7;
		this.panel_main.add(this.btnSave, gbc_btnSave);

		panel_extra = new JPanel();
		this.panel_extra.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), LangTools.getWordLanguage("Additional settings", "h264nvenc.othersettings"),
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		GridBagConstraints gbc_panel_extra = new GridBagConstraints();
		gbc_panel_extra.fill = GridBagConstraints.BOTH;
		gbc_panel_extra.gridx = 1;
		gbc_panel_extra.gridy = 0;
		rootpanel.add(panel_extra, gbc_panel_extra);
		GridBagLayout gbl_panel_extra = new GridBagLayout();
		gbl_panel_extra.columnWidths = new int[] { 1, 1 };
		gbl_panel_extra.rowHeights = new int[] { 1, 1, 1, 1, 1, 1, 1, 1 };
		gbl_panel_extra.columnWeights = new double[] { 1.0, 1.0 };
		gbl_panel_extra.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		this.panel_extra.setLayout(gbl_panel_extra);

		this.scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 8;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		this.panel_extra.add(this.scrollPane, gbc_scrollPane);

		this.panel = new JPanel();
		this.scrollPane.setViewportView(this.panel);
		GridBagLayout gbl_panel1 = new GridBagLayout();
		gbl_panel1.columnWidths = new int[] { 1, 1 };
		gbl_panel1.rowHeights = new int[] { 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
		gbl_panel1.columnWeights = new double[] { 1.0, 1.0 };
		gbl_panel1.rowWeights = new double[] { 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				1.0, 1.0, 1.0 };
		this.panel.setLayout(gbl_panel1);

		this.lblNewLabel_1 = new JLabel("rc-lookahead");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		this.panel.add(this.lblNewLabel_1, gbc_lblNewLabel_1);

		this.spinnerrclookhead = new JSpinner();
		spinnerrclookhead.setToolTipText("Number of frames to look ahead for rate-control");
		GridBagConstraints gbc_spinnerrclookhead = new GridBagConstraints();
		gbc_spinnerrclookhead.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerrclookhead.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerrclookhead.gridx = 1;
		gbc_spinnerrclookhead.gridy = 1;
		this.panel.add(this.spinnerrclookhead, gbc_spinnerrclookhead);

		this.lblSurfaces = new JLabel("surfaces");
		GridBagConstraints gbc_lblSurfaces = new GridBagConstraints();
		gbc_lblSurfaces.anchor = GridBagConstraints.EAST;
		gbc_lblSurfaces.insets = new Insets(0, 0, 5, 5);
		gbc_lblSurfaces.gridx = 0;
		gbc_lblSurfaces.gridy = 2;
		this.panel.add(this.lblSurfaces, gbc_lblSurfaces);

		this.spinnersurfaces = new JSpinner();
		spinnersurfaces.setToolTipText("Number of concurrent surfaces");
		GridBagConstraints gbc_spinnersurfaces = new GridBagConstraints();
		gbc_spinnersurfaces.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnersurfaces.insets = new Insets(0, 0, 5, 0);
		gbc_spinnersurfaces.gridx = 1;
		gbc_spinnersurfaces.gridy = 2;
		this.panel.add(this.spinnersurfaces, gbc_spinnersurfaces);

		this.lblDelay = new JLabel("delay");
		GridBagConstraints gbc_lblDelay = new GridBagConstraints();
		gbc_lblDelay.anchor = GridBagConstraints.EAST;
		gbc_lblDelay.insets = new Insets(0, 0, 5, 5);
		gbc_lblDelay.gridx = 0;
		gbc_lblDelay.gridy = 3;
		this.panel.add(this.lblDelay, gbc_lblDelay);

		this.spinnerdelay = new JSpinner();
		spinnerdelay.setToolTipText("Delay frame output by the given amount of frames");
		GridBagConstraints gbc_spinnerdelay = new GridBagConstraints();
		gbc_spinnerdelay.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerdelay.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerdelay.gridx = 1;
		gbc_spinnerdelay.gridy = 3;
		this.panel.add(this.spinnerdelay, gbc_spinnerdelay);

		this.lblNewLabel_2 = new JLabel("Aq-strength");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 4;
		this.panel.add(this.lblNewLabel_2, gbc_lblNewLabel_2);

		this.spinneraqstrenght = new JSpinner();
		spinneraqstrenght.setToolTipText("When Spatial AQ is enabled, this field is used to specify AQ strength");
		GridBagConstraints gbc_spinneraqstrenght = new GridBagConstraints();
		gbc_spinneraqstrenght.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinneraqstrenght.insets = new Insets(0, 0, 5, 0);
		gbc_spinneraqstrenght.gridx = 1;
		gbc_spinneraqstrenght.gridy = 4;
		this.panel.add(this.spinneraqstrenght, gbc_spinneraqstrenght);

		this.lblNewLabel_3 = new JLabel("init qpP");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 5;
		this.panel.add(this.lblNewLabel_3, gbc_lblNewLabel_3);

		this.spinnerinitqpp = new JSpinner();
		spinnerinitqpp.setToolTipText("Initial QP value for P frame");
		GridBagConstraints gbc_spinnerinitqpp = new GridBagConstraints();
		gbc_spinnerinitqpp.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerinitqpp.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerinitqpp.gridx = 1;
		gbc_spinnerinitqpp.gridy = 5;
		this.panel.add(this.spinnerinitqpp, gbc_spinnerinitqpp);

		this.lblInitQpb = new JLabel("init qpB");
		GridBagConstraints gbc_lblInitQpb = new GridBagConstraints();
		gbc_lblInitQpb.anchor = GridBagConstraints.EAST;
		gbc_lblInitQpb.insets = new Insets(0, 0, 5, 5);
		gbc_lblInitQpb.gridx = 0;
		gbc_lblInitQpb.gridy = 6;
		this.panel.add(this.lblInitQpb, gbc_lblInitQpb);

		this.spinnerinitqpb = new JSpinner();
		spinnerinitqpb.setToolTipText("Initial QP value for B frame");
		GridBagConstraints gbc_spinnerinitqpb = new GridBagConstraints();
		gbc_spinnerinitqpb.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerinitqpb.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerinitqpb.gridx = 1;
		gbc_spinnerinitqpb.gridy = 6;
		this.panel.add(this.spinnerinitqpb, gbc_spinnerinitqpb);

		this.lblInitQpi = new JLabel("init qpI");
		GridBagConstraints gbc_lblInitQpi = new GridBagConstraints();
		gbc_lblInitQpi.anchor = GridBagConstraints.EAST;
		gbc_lblInitQpi.insets = new Insets(0, 0, 5, 5);
		gbc_lblInitQpi.gridx = 0;
		gbc_lblInitQpi.gridy = 7;
		this.panel.add(this.lblInitQpi, gbc_lblInitQpi);

		this.spinnerintqpl = new JSpinner();
		spinnerintqpl.setToolTipText("Initial QP value for I frame");
		GridBagConstraints gbc_spinnerintqpl = new GridBagConstraints();
		gbc_spinnerintqpl.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerintqpl.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerintqpl.gridx = 1;
		gbc_spinnerintqpl.gridy = 7;
		this.panel.add(this.spinnerintqpl, gbc_spinnerintqpl);

		this.chckbxNoscenecut = new JCheckBox("no-scenecut");
		chckbxNoscenecut.setToolTipText(
				"When lookahead is enabled,  enable this to disable adaptive I-frame insertion at scene cuts");
		GridBagConstraints gbc_chckbxNoscenecut = new GridBagConstraints();
		gbc_chckbxNoscenecut.anchor = GridBagConstraints.WEST;
		gbc_chckbxNoscenecut.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNoscenecut.gridx = 0;
		gbc_chckbxNoscenecut.gridy = 8;
		this.panel.add(this.chckbxNoscenecut, gbc_chckbxNoscenecut);

		this.chckbxForcedidr = new JCheckBox("forced-idr");
		chckbxForcedidr.setToolTipText("If forcing keyframes, force them as IDR frames");
		GridBagConstraints gbc_chckbxForcedidr = new GridBagConstraints();
		gbc_chckbxForcedidr.anchor = GridBagConstraints.WEST;
		gbc_chckbxForcedidr.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxForcedidr.gridx = 1;
		gbc_chckbxForcedidr.gridy = 8;
		this.panel.add(this.chckbxForcedidr, gbc_chckbxForcedidr);

		this.chckbxBadapt = new JCheckBox("b_adapt");
		chckbxBadapt
				.setToolTipText("When lookahead is enabled,  set this to false to disable adaptive B-frame decision");
		GridBagConstraints gbc_chckbxBadapt = new GridBagConstraints();
		gbc_chckbxBadapt.anchor = GridBagConstraints.WEST;
		gbc_chckbxBadapt.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxBadapt.gridx = 0;
		gbc_chckbxBadapt.gridy = 9;
		this.panel.add(this.chckbxBadapt, gbc_chckbxBadapt);

		this.chckbxSpatialaq = new JCheckBox("spatial-aq");
		chckbxSpatialaq.setToolTipText("Enable Spatial AQ");
		GridBagConstraints gbc_chckbxSpatialaq = new GridBagConstraints();
		gbc_chckbxSpatialaq.anchor = GridBagConstraints.WEST;
		gbc_chckbxSpatialaq.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxSpatialaq.gridx = 1;
		gbc_chckbxSpatialaq.gridy = 9;
		this.panel.add(this.chckbxSpatialaq, gbc_chckbxSpatialaq);

		this.chckbxTemporalaq = new JCheckBox("temporal-aq");
		chckbxTemporalaq.setToolTipText("Enable Temporal AQ");
		GridBagConstraints gbc_chckbxTemporalaq = new GridBagConstraints();
		gbc_chckbxTemporalaq.anchor = GridBagConstraints.WEST;
		gbc_chckbxTemporalaq.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxTemporalaq.gridx = 0;
		gbc_chckbxTemporalaq.gridy = 10;
		this.panel.add(this.chckbxTemporalaq, gbc_chckbxTemporalaq);

		this.chckbxZerolatency = new JCheckBox("zerolatency");
		chckbxZerolatency.setToolTipText("If enabled, indicate zero latency operation (no reordering delay)");
		GridBagConstraints gbc_chckbxZerolatency = new GridBagConstraints();
		gbc_chckbxZerolatency.anchor = GridBagConstraints.WEST;
		gbc_chckbxZerolatency.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxZerolatency.gridx = 1;
		gbc_chckbxZerolatency.gridy = 10;
		this.panel.add(this.chckbxZerolatency, gbc_chckbxZerolatency);

		this.chckbxNonrefp = new JCheckBox("nonref_p");
		chckbxNonrefp.setToolTipText("Enable automatic insertion of non-reference P-frames");
		GridBagConstraints gbc_chckbxNonrefp = new GridBagConstraints();
		gbc_chckbxNonrefp.anchor = GridBagConstraints.WEST;
		gbc_chckbxNonrefp.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNonrefp.gridx = 0;
		gbc_chckbxNonrefp.gridy = 11;
		this.panel.add(this.chckbxNonrefp, gbc_chckbxNonrefp);

		this.chckbxStrictgop = new JCheckBox("strict_gop");
		chckbxStrictgop.setToolTipText("Minimize GOP-to-GOP rate fluctuations");
		GridBagConstraints gbc_chckbxStrictgop = new GridBagConstraints();
		gbc_chckbxStrictgop.anchor = GridBagConstraints.WEST;
		gbc_chckbxStrictgop.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxStrictgop.gridx = 1;
		gbc_chckbxStrictgop.gridy = 11;
		this.panel.add(this.chckbxStrictgop, gbc_chckbxStrictgop);

		/*
		 * this.chckbxCbr = new JCheckBox("CBR"); GridBagConstraints gbc_chckbxCbr = new
		 * GridBagConstraints(); gbc_chckbxCbr.anchor = GridBagConstraints.WEST;
		 * gbc_chckbxCbr.insets = new Insets(0, 0, 5, 5); gbc_chckbxCbr.gridx = 0;
		 * gbc_chckbxCbr.gridy = 12; this.panel.add(this.chckbxCbr, gbc_chckbxCbr);
		 */

		this.lblCoder = new JLabel("Coder");
		GridBagConstraints gbc_lblCoder = new GridBagConstraints();
		gbc_lblCoder.insets = new Insets(0, 0, 5, 5);
		gbc_lblCoder.anchor = GridBagConstraints.EAST;
		gbc_lblCoder.gridx = 0;
		gbc_lblCoder.gridy = 13;
		this.panel.add(this.lblCoder, gbc_lblCoder);

		this.comboBoxcoder = new JComboBox<H264NvencCoder>();
		comboBoxcoder.setToolTipText("Coder type");
		GridBagConstraints gbc_comboBoxcoder = new GridBagConstraints();
		gbc_comboBoxcoder.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxcoder.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxcoder.gridx = 1;
		gbc_comboBoxcoder.gridy = 13;
		this.panel.add(this.comboBoxcoder, gbc_comboBoxcoder);

		setSize(800, 500);

	}

	private void ShowOnlyMainPanel() {

		panel_extra.setVisible(false);

		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 1 };
		gbl_panel.rowHeights = new int[] { 1 };
		gbl_panel.columnWeights = new double[] { 1.0 };
		gbl_panel.rowWeights = new double[] { 1.0 };
		rootpanel.setLayout(gbl_panel);

		GridBagConstraints gbc_panel_main = new GridBagConstraints();
		gbc_panel_main.insets = new Insets(0, 0, 0, 5);
		gbc_panel_main.fill = GridBagConstraints.BOTH;
		gbc_panel_main.gridx = 0;
		gbc_panel_main.gridy = 0;
		rootpanel.add(panel_main, gbc_panel_main);

		setSize(400, 500);
	}

	private void showExtendedPanel() {

		panel_extra.setVisible(true);

		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 1, 1 };
		gbl_panel.rowHeights = new int[] { 1 };
		gbl_panel.columnWeights = new double[] { 1.0, 1.0 };
		gbl_panel.rowWeights = new double[] { 1.0 };
		rootpanel.setLayout(gbl_panel);

		GridBagConstraints gbc_panel_main = new GridBagConstraints();
		gbc_panel_main.insets = new Insets(0, 0, 0, 5);
		gbc_panel_main.fill = GridBagConstraints.BOTH;
		gbc_panel_main.gridx = 0;
		gbc_panel_main.gridy = 0;
		rootpanel.add(panel_main, gbc_panel_main);

		GridBagConstraints gbc_panel_extra = new GridBagConstraints();
		gbc_panel_extra.fill = GridBagConstraints.BOTH;
		gbc_panel_extra.gridx = 1;
		gbc_panel_extra.gridy = 0;
		rootpanel.add(panel_extra, gbc_panel_extra);

		setSize(800, 500);
	}

	private void initializeComponents() {
		comboBoxpresets.setModel(new DefaultComboBoxModel<>(NvencPresets.values()));
		comboBoxprofiles.setModel(new DefaultComboBoxModel<>(ProfilesH264Nvenc.values()));
		comboBoxprofiles.setSelectedIndex(1);
		comboBoxpixelformat.setModel(new DefaultComboBoxModel<>(PixelFormatNvenc.values()));
		comboBoxlevel.setModel(new DefaultComboBoxModel<>(VideoLevel.getH264NvencLevels()));
		comboBoxratecontrol.setModel(new DefaultComboBoxModel<>(H264NvencRateControlPreset.values()));
		initExtendeComponents();

	}

	private void initExtendeComponents() {

		comboBoxcoder.setModel(new DefaultComboBoxModel<>(H264NvencCoder.values()));
		SpinnerNumberModel modelrclookhead = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
		spinnerrclookhead.setModel(modelrclookhead);
		SpinnerNumberModel modelspinnersurfaces = new SpinnerNumberModel(0, 0, 64, 1);
		spinnersurfaces.setModel(modelspinnersurfaces);
		SpinnerNumberModel modelspinnerdelay = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
		spinnerdelay.setModel(modelspinnerdelay);
		SpinnerNumberModel modelaqstrenght = new SpinnerNumberModel(8, 1, 15, 1);
		spinneraqstrenght.setModel(modelaqstrenght);
		SpinnerNumberModel modelinitqpp = new SpinnerNumberModel(-1, -1, 51, 1);
		spinnerinitqpp.setModel(modelinitqpp);
		SpinnerNumberModel modelinitqpb = new SpinnerNumberModel(-1, -1, 51, 1);
		spinnerinitqpb.setModel(modelinitqpb);
		SpinnerNumberModel modelinitqpl = new SpinnerNumberModel(-1, -1, 51, 1);
		spinnerintqpl.setModel(modelinitqpl);

		chckbxNoscenecut.setSelected(false);
		chckbxForcedidr.setSelected(false);
		chckbxBadapt.setSelected(true);
		chckbxSpatialaq.setSelected(false);
		chckbxTemporalaq.setSelected(false);
		chckbxZerolatency.setSelected(false);
		chckbxNonrefp.setSelected(false);
		chckbxStrictgop.setSelected(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (cmd.equals(CLOSE)) {
			this.dispose();
		}

		else if (cmd.equals(SAVECLOSE)) {
			saveChoosenOptionsToContainer();
			this.dispose();

		} else if (cmd.equals(OPENEXTENDEDPANEL))
			checkExtendedPanelOption();

	}

	private void checkExtendedPanelOption() {
		if (stlchckbxUseAdvancedOptions.isSelected())
			showExtendedPanel();
		else
			ShowOnlyMainPanel();
	}

	private void saveChoosenOptionsToContainer() {

		infocont.setPreset((NvencPresets) comboBoxpresets.getSelectedItem());
		infocont.setProfile((ProfilesH264Nvenc) comboBoxprofiles.getSelectedItem());
		infocont.setPixelformat((PixelFormatNvenc) comboBoxpixelformat.getSelectedItem());
		infocont.setLevel((VideoLevel) comboBoxlevel.getSelectedItem());
		infocont.setRateControlPreset((H264NvencRateControlPreset) comboBoxratecontrol.getSelectedItem());
		// infocont.setConstantQuantization((int) spinnerconstquant.getValue());
		if (stlchckbxUseAdvancedOptions.isSelected())
			saveExtraOptions();
	}

	private void setInfoFromEncodingContainer() {

		comboBoxpresets.setSelectedItem(infocont.getPreset());
		comboBoxprofiles.setSelectedItem(infocont.getProfile());
		comboBoxpixelformat.setSelectedItem(infocont.getPixelformat());
		comboBoxlevel.setSelectedItem(infocont.getLevel());
		comboBoxratecontrol.setSelectedItem(infocont.getRateControlPreset());

		if (infocont.getExtrainfocont() != null) {
			H264NvencExtraInfoContainer extra = (H264NvencExtraInfoContainer) infocont.getExtrainfocont();
			spinnerrclookhead.setValue(extra.getRclookahead());
			spinnersurfaces.setValue(extra.getSurfaces());
			spinnerdelay.setValue(extra.getDelay());
			spinneraqstrenght.setValue(extra.getAqstrength());
			spinnerinitqpp.setValue(extra.getInit_qpP());
			spinnerinitqpb.setValue(extra.getInit_qpB());
			spinnerintqpl.setValue(extra.getInit_qpI());
			chckbxNoscenecut.setSelected(extra.isNoscenecut());
			chckbxBadapt.setSelected(extra.isBadapt());
			chckbxForcedidr.setSelected(extra.isForcedidr());
			chckbxNonrefp.setSelected(extra.isNonrefp());
			chckbxSpatialaq.setSelected(extra.isSpatialaq());
			chckbxTemporalaq.setSelected(extra.isTemporalaq());
			chckbxZerolatency.setSelected(extra.isZerolatency());
			chckbxStrictgop.setSelected(extra.isStrictgop());
			comboBoxcoder.setSelectedItem(extra.getCoder());
			stlchckbxUseAdvancedOptions.setSelected(true);
			showExtendedPanel();
		} else
			ShowOnlyMainPanel();
	}

	private void saveExtraOptions() {
		H264NvencExtraInfoContainer extrainfo = new H264NvencExtraInfoContainer();

		extrainfo.setRclookahead((int) spinnerrclookhead.getValue());
		extrainfo.setSurfaces((int) spinnersurfaces.getValue());
		extrainfo.setDelay((int) spinnerdelay.getValue());
		extrainfo.setAqstrength((int) spinneraqstrenght.getValue());
		extrainfo.setInit_qpP((int) spinnerinitqpp.getValue());
		extrainfo.setInit_qpB((int) spinnerinitqpb.getValue());
		extrainfo.setInit_qpI((int) spinnerintqpl.getValue());
		extrainfo.setNoscenecut(chckbxNoscenecut.isSelected());
		extrainfo.setBadapt(chckbxBadapt.isSelected());
		extrainfo.setForcedidr(chckbxForcedidr.isSelected());
		extrainfo.setNonrefp(chckbxNonrefp.isSelected());
		extrainfo.setSpatialaq(chckbxSpatialaq.isSelected());
		extrainfo.setTemporalaq(chckbxTemporalaq.isSelected());
		extrainfo.setZerolatency(chckbxZerolatency.isSelected());
		extrainfo.setStrictgop(chckbxStrictgop.isSelected());
		extrainfo.setCoder((H264NvencCoder) comboBoxcoder.getSelectedItem());

		infocont.setExtrainfocont(extrainfo);

	}
}
