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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.pmw.tinylog.Logger;

import eu.hansolo.custom.SteelCheckBox;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.PixelFormatQsv;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.ProfileH264Qsv;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.QsvPresets;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoH264QSVEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.extrainfocontainer.H264QsvExtraInfoContainer;

public class H264QsvConfigurationsPanel extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VideoH264QSVEncodingInfoContainer infocont;
	private JPanel panel_extra;
	private JPanel panel_main;
	private JPanel rootpanel;
	private SteelCheckBox stlchckbxUseAdvancedOptions;
	private JLabel lblPreset;
	private JComboBox<QsvPresets> comboBoxpresets;
	private JLabel lblProfile;
	private JComboBox<ProfileH264Qsv> comboBoxprofiles;
	private JLabel lblPixelFormat;
	private JComboBox<PixelFormatQsv> comboBoxpixelformat;
	private JButton btnNewButton;
	private JButton btnSave;

	private JScrollPane scrollPane;
	private JPanel panel;
	private JSpinner spinnerasync_depth;
	private JLabel lblasync_depth;
	private JSpinner spinnerrdo;
	private JLabel lblrdo;
	private JSpinner spinnermax_frame_size;
	private JLabel lblmax_frame_size;
	private JSpinner spinnermax_slice_size;
	private JLabel lblmax_slice_size;
	private JSpinner spinnerbitrate_limit;
	private JLabel lblbitrate_limit;
	private JSpinner spinnermbbrc;
	private JLabel lblmbbrc;
	private JSpinner spinnerextbrc;
	private JLabel lblextbrc;

	private static String CLOSE = "close";
	private static String SAVECLOSE = "saveclose";
	private static String OPENEXTENDEDPANEL = "extendedpanel";
	private JLabel lbladaptive_i;
	private JLabel lbladaptive_b;
	private JLabel lblb_strategy;
	private JLabel lblidr_interval;
	private JLabel lblmax_dec_frame_buffering;
	private JLabel lblLookahead;
	private JLabel lblLookaheaddepth;
	private JLabel lblLookaheaddownsampling;
	private JLabel lblIntreftype;
	private JSpinner spinneradaptive_i;
	private JSpinner spinneradaptive_b;
	private JSpinner spinnerb_strategy;
	private JSpinner spinneridr_interval;
	private JSpinner spinnermax_dec_frame_buffering;
	private JSpinner spinnerlook_ahead;
	private JSpinner spinnerlook_ahead_downsampling;
	private JSpinner spinnerlook_ahead_depth;
	private JSpinner spinnerint_ref_type;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					VideoH264QSVEncodingInfoContainer cont = new VideoH264QSVEncodingInfoContainer();

					H264QsvConfigurationsPanel dialog = new H264QsvConfigurationsPanel(cont);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
				    Logger.error(e);
				}
			}
		});
	}

	public H264QsvConfigurationsPanel(VideoH264QSVEncodingInfoContainer infocont) {
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
		this.panel_main.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),
				LangTools.getWordLanguage("H264 Nvenc settings", "h264nvenc.settings"), TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(51, 51, 51)));
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

		this.stlchckbxUseAdvancedOptions
				.setText(LangTools.getWordLanguage("Show advanced options", "videogui.advancedoptions"));
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

		this.comboBoxpresets = new JComboBox<QsvPresets>();
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

		this.comboBoxprofiles = new JComboBox<ProfileH264Qsv>();
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

		this.comboBoxpixelformat = new JComboBox<PixelFormatQsv>();
		GridBagConstraints gbc_comboBoxpixelformat = new GridBagConstraints();
		gbc_comboBoxpixelformat.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxpixelformat.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxpixelformat.gridx = 1;
		gbc_comboBoxpixelformat.gridy = 3;
		this.panel_main.add(this.comboBoxpixelformat, gbc_comboBoxpixelformat);

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
		this.panel_extra.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),
				LangTools.getWordLanguage("Additional settings", "h264nvenc.othersettings"), TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(51, 51, 51)));
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

		this.lblasync_depth = new JLabel("async_depth");
		GridBagConstraints gbc_lblasync_depth = new GridBagConstraints();
		gbc_lblasync_depth.anchor = GridBagConstraints.EAST;
		gbc_lblasync_depth.insets = new Insets(0, 0, 5, 5);
		gbc_lblasync_depth.gridx = 0;
		gbc_lblasync_depth.gridy = 1;
		this.panel.add(this.lblasync_depth, gbc_lblasync_depth);

		this.spinnerasync_depth = new JSpinner();
		spinnerasync_depth.setToolTipText("Number of frames to look ahead for rate-control");
		GridBagConstraints gbc_spinnerasync_depth = new GridBagConstraints();
		gbc_spinnerasync_depth.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerasync_depth.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerasync_depth.gridx = 1;
		gbc_spinnerasync_depth.gridy = 1;
		this.panel.add(this.spinnerasync_depth, gbc_spinnerasync_depth);

		this.lblrdo = new JLabel("rdo");
		GridBagConstraints gbc_lblrdo = new GridBagConstraints();
		gbc_lblrdo.anchor = GridBagConstraints.EAST;
		gbc_lblrdo.insets = new Insets(0, 0, 5, 5);
		gbc_lblrdo.gridx = 0;
		gbc_lblrdo.gridy = 2;
		this.panel.add(this.lblrdo, gbc_lblrdo);

		this.spinnerrdo = new JSpinner();
		spinnerrdo.setToolTipText("Number of concurrent surfaces");
		GridBagConstraints gbc_spinnerrdo = new GridBagConstraints();
		gbc_spinnerrdo.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerrdo.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerrdo.gridx = 1;
		gbc_spinnerrdo.gridy = 2;
		this.panel.add(this.spinnerrdo, gbc_spinnerrdo);

		this.lblmax_frame_size = new JLabel("max_frame_size");
		GridBagConstraints gbc_lblmax_frame_size = new GridBagConstraints();
		gbc_lblmax_frame_size.anchor = GridBagConstraints.EAST;
		gbc_lblmax_frame_size.insets = new Insets(0, 0, 5, 5);
		gbc_lblmax_frame_size.gridx = 0;
		gbc_lblmax_frame_size.gridy = 3;
		this.panel.add(this.lblmax_frame_size, gbc_lblmax_frame_size);

		this.spinnermax_frame_size = new JSpinner();
		spinnermax_frame_size.setToolTipText("Delay frame output by the given amount of frames");
		GridBagConstraints gbc_spinnermax_frame_size = new GridBagConstraints();
		gbc_spinnermax_frame_size.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnermax_frame_size.insets = new Insets(0, 0, 5, 0);
		gbc_spinnermax_frame_size.gridx = 1;
		gbc_spinnermax_frame_size.gridy = 3;
		this.panel.add(this.spinnermax_frame_size, gbc_spinnermax_frame_size);

		this.lblmax_slice_size = new JLabel("max_slice_size");
		GridBagConstraints gbc_lblmax_slice_size = new GridBagConstraints();
		gbc_lblmax_slice_size.anchor = GridBagConstraints.EAST;
		gbc_lblmax_slice_size.insets = new Insets(0, 0, 5, 5);
		gbc_lblmax_slice_size.gridx = 0;
		gbc_lblmax_slice_size.gridy = 4;
		this.panel.add(this.lblmax_slice_size, gbc_lblmax_slice_size);

		this.spinnermax_slice_size = new JSpinner();
		spinnermax_slice_size.setToolTipText("When Spatial AQ is enabled, this field is used to specify AQ strength");
		GridBagConstraints gbc_spinnermax_slice_size = new GridBagConstraints();
		gbc_spinnermax_slice_size.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnermax_slice_size.insets = new Insets(0, 0, 5, 0);
		gbc_spinnermax_slice_size.gridx = 1;
		gbc_spinnermax_slice_size.gridy = 4;
		this.panel.add(this.spinnermax_slice_size, gbc_spinnermax_slice_size);

		this.lblbitrate_limit = new JLabel("bitrate_limit");
		GridBagConstraints gbc_lblbitrate_limit = new GridBagConstraints();
		gbc_lblbitrate_limit.anchor = GridBagConstraints.EAST;
		gbc_lblbitrate_limit.insets = new Insets(0, 0, 5, 5);
		gbc_lblbitrate_limit.gridx = 0;
		gbc_lblbitrate_limit.gridy = 5;
		this.panel.add(this.lblbitrate_limit, gbc_lblbitrate_limit);

		this.spinnerbitrate_limit = new JSpinner();
		spinnerbitrate_limit.setToolTipText("Initial QP value for P frame");
		GridBagConstraints gbc_spinnerbitrate_limit = new GridBagConstraints();
		gbc_spinnerbitrate_limit.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerbitrate_limit.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerbitrate_limit.gridx = 1;
		gbc_spinnerbitrate_limit.gridy = 5;
		this.panel.add(this.spinnerbitrate_limit, gbc_spinnerbitrate_limit);

		this.lblmbbrc = new JLabel("mbbrc");
		GridBagConstraints gbc_lblmbbrc = new GridBagConstraints();
		gbc_lblmbbrc.anchor = GridBagConstraints.EAST;
		gbc_lblmbbrc.insets = new Insets(0, 0, 5, 5);
		gbc_lblmbbrc.gridx = 0;
		gbc_lblmbbrc.gridy = 6;
		this.panel.add(this.lblmbbrc, gbc_lblmbbrc);

		this.spinnermbbrc = new JSpinner();
		spinnermbbrc.setToolTipText("Initial QP value for B frame");
		GridBagConstraints gbc_spinnermbbrc = new GridBagConstraints();
		gbc_spinnermbbrc.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnermbbrc.insets = new Insets(0, 0, 5, 0);
		gbc_spinnermbbrc.gridx = 1;
		gbc_spinnermbbrc.gridy = 6;
		this.panel.add(this.spinnermbbrc, gbc_spinnermbbrc);

		this.lblextbrc = new JLabel("extbrc");
		GridBagConstraints gbc_lblextbrc = new GridBagConstraints();
		gbc_lblextbrc.anchor = GridBagConstraints.EAST;
		gbc_lblextbrc.insets = new Insets(0, 0, 5, 5);
		gbc_lblextbrc.gridx = 0;
		gbc_lblextbrc.gridy = 7;
		this.panel.add(this.lblextbrc, gbc_lblextbrc);

		this.spinnerextbrc = new JSpinner();
		spinnerextbrc.setToolTipText("Initial QP value for I frame");
		GridBagConstraints gbc_spinnerextbrc = new GridBagConstraints();
		gbc_spinnerextbrc.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerextbrc.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerextbrc.gridx = 1;
		gbc_spinnerextbrc.gridy = 7;
		this.panel.add(this.spinnerextbrc, gbc_spinnerextbrc);

		/*
		 * this.chckbxCbr = new JCheckBox("CBR"); GridBagConstraints gbc_chckbxCbr = new
		 * GridBagConstraints(); gbc_chckbxCbr.anchor = GridBagConstraints.WEST;
		 * gbc_chckbxCbr.insets = new Insets(0, 0, 5, 5); gbc_chckbxCbr.gridx = 0;
		 * gbc_chckbxCbr.gridy = 12; this.panel.add(this.chckbxCbr, gbc_chckbxCbr);
		 */

		lbladaptive_i = new JLabel("adaptive_i");
		GridBagConstraints gbc_lbladaptive_i = new GridBagConstraints();
		gbc_lbladaptive_i.anchor = GridBagConstraints.EAST;
		gbc_lbladaptive_i.insets = new Insets(0, 0, 5, 5);
		gbc_lbladaptive_i.gridx = 0;
		gbc_lbladaptive_i.gridy = 8;
		panel.add(lbladaptive_i, gbc_lbladaptive_i);

		spinneradaptive_i = new JSpinner();
		GridBagConstraints gbc_spinneradaptive_i = new GridBagConstraints();
		gbc_spinneradaptive_i.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinneradaptive_i.insets = new Insets(0, 0, 5, 0);
		gbc_spinneradaptive_i.gridx = 1;
		gbc_spinneradaptive_i.gridy = 8;
		panel.add(spinneradaptive_i, gbc_spinneradaptive_i);

		lbladaptive_b = new JLabel("adaptive_b");
		GridBagConstraints gbc_lbladaptive_b = new GridBagConstraints();
		gbc_lbladaptive_b.anchor = GridBagConstraints.EAST;
		gbc_lbladaptive_b.insets = new Insets(0, 0, 5, 5);
		gbc_lbladaptive_b.gridx = 0;
		gbc_lbladaptive_b.gridy = 9;
		panel.add(lbladaptive_b, gbc_lbladaptive_b);

		spinneradaptive_b = new JSpinner();
		GridBagConstraints gbc_spinneradaptive_b = new GridBagConstraints();
		gbc_spinneradaptive_b.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinneradaptive_b.insets = new Insets(0, 0, 5, 0);
		gbc_spinneradaptive_b.gridx = 1;
		gbc_spinneradaptive_b.gridy = 9;
		panel.add(spinneradaptive_b, gbc_spinneradaptive_b);

		lblb_strategy = new JLabel("b_strategy");
		GridBagConstraints gbc_lblb_strategy = new GridBagConstraints();
		gbc_lblb_strategy.anchor = GridBagConstraints.EAST;
		gbc_lblb_strategy.insets = new Insets(0, 0, 5, 5);
		gbc_lblb_strategy.gridx = 0;
		gbc_lblb_strategy.gridy = 10;
		panel.add(lblb_strategy, gbc_lblb_strategy);

		spinnerb_strategy = new JSpinner();
		GridBagConstraints gbc_spinnerb_strategy = new GridBagConstraints();
		gbc_spinnerb_strategy.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerb_strategy.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerb_strategy.gridx = 1;
		gbc_spinnerb_strategy.gridy = 10;
		panel.add(spinnerb_strategy, gbc_spinnerb_strategy);

		lblidr_interval = new JLabel("idr_interval");
		GridBagConstraints gbc_lblidr_interval = new GridBagConstraints();
		gbc_lblidr_interval.anchor = GridBagConstraints.EAST;
		gbc_lblidr_interval.insets = new Insets(0, 0, 5, 5);
		gbc_lblidr_interval.gridx = 0;
		gbc_lblidr_interval.gridy = 11;
		panel.add(lblidr_interval, gbc_lblidr_interval);

		spinneridr_interval = new JSpinner();
		GridBagConstraints gbc_spinneridr_interval = new GridBagConstraints();
		gbc_spinneridr_interval.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinneridr_interval.insets = new Insets(0, 0, 5, 0);
		gbc_spinneridr_interval.gridx = 1;
		gbc_spinneridr_interval.gridy = 11;
		panel.add(spinneridr_interval, gbc_spinneridr_interval);

		lblmax_dec_frame_buffering = new JLabel("max_dec_frame_buffering");
		GridBagConstraints gbc_lblmax_dec_frame_buffering = new GridBagConstraints();
		gbc_lblmax_dec_frame_buffering.anchor = GridBagConstraints.EAST;
		gbc_lblmax_dec_frame_buffering.insets = new Insets(0, 0, 5, 5);
		gbc_lblmax_dec_frame_buffering.gridx = 0;
		gbc_lblmax_dec_frame_buffering.gridy = 12;
		panel.add(lblmax_dec_frame_buffering, gbc_lblmax_dec_frame_buffering);

		spinnermax_dec_frame_buffering = new JSpinner();
		GridBagConstraints gbc_spinnermax_dec_frame_buffering = new GridBagConstraints();
		gbc_spinnermax_dec_frame_buffering.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnermax_dec_frame_buffering.insets = new Insets(0, 0, 5, 0);
		gbc_spinnermax_dec_frame_buffering.gridx = 1;
		gbc_spinnermax_dec_frame_buffering.gridy = 12;
		panel.add(spinnermax_dec_frame_buffering, gbc_spinnermax_dec_frame_buffering);

		lblLookahead = new JLabel("look_ahead");
		GridBagConstraints gbc_lblLookahead = new GridBagConstraints();
		gbc_lblLookahead.anchor = GridBagConstraints.EAST;
		gbc_lblLookahead.insets = new Insets(0, 0, 5, 5);
		gbc_lblLookahead.gridx = 0;
		gbc_lblLookahead.gridy = 13;
		panel.add(lblLookahead, gbc_lblLookahead);

		spinnerlook_ahead = new JSpinner();
		GridBagConstraints gbc_spinnerlook_ahead = new GridBagConstraints();
		gbc_spinnerlook_ahead.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerlook_ahead.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerlook_ahead.gridx = 1;
		gbc_spinnerlook_ahead.gridy = 13;
		panel.add(spinnerlook_ahead, gbc_spinnerlook_ahead);

		lblLookaheaddepth = new JLabel("look_ahead_depth");
		GridBagConstraints gbc_lblLookaheaddepth = new GridBagConstraints();
		gbc_lblLookaheaddepth.anchor = GridBagConstraints.EAST;
		gbc_lblLookaheaddepth.insets = new Insets(0, 0, 5, 5);
		gbc_lblLookaheaddepth.gridx = 0;
		gbc_lblLookaheaddepth.gridy = 14;
		panel.add(lblLookaheaddepth, gbc_lblLookaheaddepth);

		spinnerlook_ahead_depth = new JSpinner();
		GridBagConstraints gbc_spinnerlook_ahead_depth = new GridBagConstraints();
		gbc_spinnerlook_ahead_depth.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerlook_ahead_depth.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerlook_ahead_depth.gridx = 1;
		gbc_spinnerlook_ahead_depth.gridy = 14;
		panel.add(spinnerlook_ahead_depth, gbc_spinnerlook_ahead_depth);

		lblLookaheaddownsampling = new JLabel("look_ahead_downsampling");
		GridBagConstraints gbc_lblLookaheaddownsampling = new GridBagConstraints();
		gbc_lblLookaheaddownsampling.anchor = GridBagConstraints.EAST;
		gbc_lblLookaheaddownsampling.insets = new Insets(0, 0, 5, 5);
		gbc_lblLookaheaddownsampling.gridx = 0;
		gbc_lblLookaheaddownsampling.gridy = 15;
		panel.add(lblLookaheaddownsampling, gbc_lblLookaheaddownsampling);

		spinnerlook_ahead_downsampling = new JSpinner();
		GridBagConstraints gbc_spinnerlook_ahead_downsampling = new GridBagConstraints();
		gbc_spinnerlook_ahead_downsampling.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerlook_ahead_downsampling.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerlook_ahead_downsampling.gridx = 1;
		gbc_spinnerlook_ahead_downsampling.gridy = 15;
		panel.add(spinnerlook_ahead_downsampling, gbc_spinnerlook_ahead_downsampling);

		lblIntreftype = new JLabel("int_ref_type");
		GridBagConstraints gbc_lblIntreftype = new GridBagConstraints();
		gbc_lblIntreftype.anchor = GridBagConstraints.EAST;
		gbc_lblIntreftype.insets = new Insets(0, 0, 0, 5);
		gbc_lblIntreftype.gridx = 0;
		gbc_lblIntreftype.gridy = 16;
		panel.add(lblIntreftype, gbc_lblIntreftype);

		spinnerint_ref_type = new JSpinner();
		GridBagConstraints gbc_spinnerint_ref_type = new GridBagConstraints();
		gbc_spinnerint_ref_type.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerint_ref_type.gridx = 1;
		gbc_spinnerint_ref_type.gridy = 16;
		panel.add(spinnerint_ref_type, gbc_spinnerint_ref_type);

		setSize(916, 486);

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

		setSize(800, 700);
	}

	private void initializeComponents() {
		comboBoxpresets.setModel(new DefaultComboBoxModel<>(QsvPresets.values()));
		comboBoxprofiles.setModel(new DefaultComboBoxModel<>(ProfileH264Qsv.values()));
		comboBoxprofiles.setSelectedIndex(0);
		comboBoxpixelformat.setModel(new DefaultComboBoxModel<>(PixelFormatQsv.values()));
		initExtendeComponents();

	}

	private void initExtendeComponents() {
		SpinnerNumberModel modelasync_depth = new SpinnerNumberModel(4, 1, Integer.MAX_VALUE, 1);
		spinnerasync_depth.setModel(modelasync_depth);
		SpinnerNumberModel modelspinnerrdo = new SpinnerNumberModel(-1, -1, 1, 1);
		spinnerrdo.setModel(modelspinnerrdo);
		SpinnerNumberModel modelspinnermax_frame_size = new SpinnerNumberModel(-1, -1, 65535, 1);
		spinnermax_frame_size.setModel(modelspinnermax_frame_size);
		SpinnerNumberModel modelmax_slice_size = new SpinnerNumberModel(-1, -1, 65535, 1);
		spinnermax_slice_size.setModel(modelmax_slice_size);
		SpinnerNumberModel modelbitrate_limit = new SpinnerNumberModel(-1, -1, 1, 1);
		spinnerbitrate_limit.setModel(modelbitrate_limit);
		SpinnerNumberModel modelmbbrc = new SpinnerNumberModel(-1, -1, 1, 1);
		spinnermbbrc.setModel(modelmbbrc);
		SpinnerNumberModel modelextbrc = new SpinnerNumberModel(-1, -1, 1, 1);
		spinnerextbrc.setModel(modelextbrc);
		SpinnerNumberModel modeladaptive_i = new SpinnerNumberModel(-1, -1, 1, 1);
		spinneradaptive_i.setModel(modeladaptive_i);
		SpinnerNumberModel modelspinneradaptive_b = new SpinnerNumberModel(-1, -1, 1, 1);
		spinneradaptive_b.setModel(modelspinneradaptive_b);
		SpinnerNumberModel modelspinnerb_strategy = new SpinnerNumberModel(-1, -1, 1, 1);
		spinnerb_strategy.setModel(modelspinnerb_strategy);
		SpinnerNumberModel modelspinneridr_interval = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
		spinneridr_interval.setModel(modelspinneridr_interval);
		SpinnerNumberModel modelspinnermax_dec_frame_buffering = new SpinnerNumberModel(0, 0, 65535, 1);
		spinnermax_dec_frame_buffering.setModel(modelspinnermax_dec_frame_buffering);
		SpinnerNumberModel modelspinnerlook_ahead = new SpinnerNumberModel(0, 0, 1, 1);
		spinnerlook_ahead.setModel(modelspinnerlook_ahead);
		SpinnerNumberModel modelspinnerlook_ahead_downsampling = new SpinnerNumberModel(0, 0, 3, 1);
		spinnerlook_ahead_downsampling.setModel(modelspinnerlook_ahead_downsampling);
		SpinnerNumberModel modelspinnerlook_ahead_depth = new SpinnerNumberModel(0, 0, 100, 1);
		spinnerlook_ahead_depth.setModel(modelspinnerlook_ahead_depth);
		SpinnerNumberModel modelspinnerint_ref_type = new SpinnerNumberModel(-1, -1, 65535, 1);
		spinnerint_ref_type.setModel(modelspinnerint_ref_type);

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

		infocont.setPreset((QsvPresets) comboBoxpresets.getSelectedItem());
		infocont.setProfile((ProfileH264Qsv) comboBoxprofiles.getSelectedItem());
		infocont.setPixelfomat((PixelFormatQsv) comboBoxpixelformat.getSelectedItem());
		// infocont.setConstantQuantization((int) spinnerconstquant.getValue());
		if (stlchckbxUseAdvancedOptions.isSelected())
			saveExtraOptions();
	}

	private void setInfoFromEncodingContainer() {

		comboBoxpresets.setSelectedItem(infocont.getPreset());
		comboBoxprofiles.setSelectedItem(infocont.getProfile());
		comboBoxpixelformat.setSelectedItem(infocont.getPixelfomat());

		if (infocont.getExtrainfocont() != null) {
			H264QsvExtraInfoContainer extra = (H264QsvExtraInfoContainer) infocont.getExtrainfocont();
			spinnerasync_depth.setValue(extra.getAsync_depth());
			spinnerrdo.setValue(extra.getRdo());
			spinnermax_frame_size.setValue(extra.getMax_frame_size());
			spinnermax_slice_size.setValue(extra.getMax_slice_size());
			spinnerbitrate_limit.setValue(extra.getBitrate_limit());
			spinnermbbrc.setValue(extra.getMbbrc());
			spinnerextbrc.setValue(extra.getExtbrc());
			spinneradaptive_i.setValue(extra.getAdaptive_i());
			spinneradaptive_b.setValue(extra.getAdaptive_b());
			spinnerb_strategy.setValue(extra.getB_strategy());
			spinneridr_interval.setValue(extra.getIdr_interval());
			spinnermax_dec_frame_buffering.setValue(extra.getMax_dec_frame_buffering());
			spinnerlook_ahead.setValue(extra.getLook_ahead());
			spinnerlook_ahead_depth.setValue(extra.getLook_ahead_depth());
			spinnerlook_ahead_downsampling.setValue(extra.getLook_ahead_downsampling());
			spinnerint_ref_type.setValue(extra.getInt_ref_type());

			stlchckbxUseAdvancedOptions.setSelected(true);
			showExtendedPanel();
		} else
			ShowOnlyMainPanel();
	}

	private void saveExtraOptions() {
		H264QsvExtraInfoContainer extrainfo = new H264QsvExtraInfoContainer();

		extrainfo.setAsync_depth((int) spinnerasync_depth.getValue());
		extrainfo.setRdo((int) spinnerrdo.getValue());
		extrainfo.setMax_frame_size((int) spinnermax_frame_size.getValue());
		extrainfo.setMax_slice_size((int) spinnermax_slice_size.getValue());
		extrainfo.setBitrate_limit((int) spinnerbitrate_limit.getValue());
		extrainfo.setMbbrc((int) spinnermbbrc.getValue());
		extrainfo.setExtbrc((int) spinnerextbrc.getValue());
		extrainfo.setAdaptive_i((int) spinneradaptive_i.getValue());
		extrainfo.setAdaptive_b((int) spinneradaptive_b.getValue());
		extrainfo.setB_strategy((int) spinnerb_strategy.getValue());
		extrainfo.setIdr_interval((int) spinneridr_interval.getValue());
		extrainfo.setMax_dec_frame_buffering((int) spinnermax_dec_frame_buffering.getValue());
		extrainfo.setLook_ahead((int) spinnerlook_ahead.getValue());
		extrainfo.setLook_ahead_depth((int) spinnerlook_ahead_depth.getValue());
		extrainfo.setLook_ahead_downsampling((int) spinnerlook_ahead_downsampling.getValue());
		extrainfo.setInt_ref_type((int) spinnerint_ref_type.getValue());

		infocont.setExtrainfocont(extrainfo);

	}
}
