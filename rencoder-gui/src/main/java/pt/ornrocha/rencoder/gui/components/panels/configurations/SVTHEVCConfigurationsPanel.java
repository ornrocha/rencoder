package pt.ornrocha.rencoder.gui.components.panels.configurations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import eu.hansolo.custom.SteelCheckBox;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.PixelFormatSVTAHEVC;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.PixelFormatSVTAV1;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.SVTAV1BitRateMode;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.SVTAV1Hielevel;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.SVTAV1Presets;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.SVTHEVCBitRateMode;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.SVTHEVCHielevel;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.SVTHEVCPresets;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.SVTHEVCProfile;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.SVTHEVCTune;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.SVTTier;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoSVTAV1EncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoSVTHEVCEncodingInfoContainer;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import org.tinylog.Logger;

import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class SVTHEVCConfigurationsPanel extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private VideoSVTHEVCEncodingInfoContainer infocont;
	private JComboBox comboBoxhielevel;
	private JLabel lblHielevel;
	private JComboBox comboBoxpixelformat;
	private JLabel lblNewLabel;
	private JComboBox comboBoxPreset;
	private JLabel lblPreset;
	private JComboBox comboBoxProfile;
	private JLabel lblProfile;
	private JComboBox comboBoxRatemode;
	private JLabel lblRateMode;
	private JComboBox comboBoxTier;
	private JLabel lblTier;
	private JComboBox comboBoxTune;
	private JLabel lblTune;
	private SteelCheckBox stlchckbxAsm;
	private SteelCheckBox stlchckbxAud;
	private SteelCheckBox stlchckbxBlmode;
	private SteelCheckBox stlchckbxScdetection;
	private SteelCheckBox stlchckbxHdr;
	private SteelCheckBox stlchckbxUmv;
	private SteelCheckBox stlchckbxSlicemode;
	private SteelCheckBox stlchckbxVidinfo;
	private JSpinner spinnerforcedir;
	private JSpinner spinnerladepth;
	private JLabel lblNewLabel_1;
	private JLabel lblLadepth;
	private JSpinner spinnerlevel;
	private JSpinner spinnersocket;
	private JLabel lblLevel;
	private JLabel lblSocket;
	private JSpinner spinnerthreadcount;
	private JLabel lblThreadcount;
	private JSpinner spinnertile_row_cnt;
	private JSpinner spinnertile_col_cnt;
	private JSpinner spinnerpred_struct;
	private JLabel lblTilerowcnt;
	private JLabel lblTilecolcnt;
	private JLabel lblPredstruct;
	private JPanel panel;
	private JButton btnCancel;
	private JButton btnSave;
	
	  private static String CLOSE = "close";
	  private static String SAVECLOSE = "saveclose";
	
	
	public SVTHEVCConfigurationsPanel(VideoSVTHEVCEncodingInfoContainer cont) {
		infocont=cont;
		initGUI();
		initializeComponents();
		setInfoFromEncodingContainer();
	}
	
	
	
	private void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1,1,1,1,1};
		gridBagLayout.rowHeights = new int[]{1,1,1,1,1,1,1,1,1,1};
		gridBagLayout.columnWeights = new double[]{1.0,1.0,1.0,0.0,1.0,1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		getContentPane().setLayout(gridBagLayout);
		
		lblNewLabel = new JLabel("Pixel format");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		comboBoxpixelformat = new JComboBox();
		GridBagConstraints gbc_comboBoxpixelformat = new GridBagConstraints();
		gbc_comboBoxpixelformat.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxpixelformat.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxpixelformat.gridx = 1;
		gbc_comboBoxpixelformat.gridy = 0;
		getContentPane().add(comboBoxpixelformat, gbc_comboBoxpixelformat);
		
		lblNewLabel_1 = new JLabel("forced-idr");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 0;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		spinnerforcedir = new JSpinner();
		GridBagConstraints gbc_spinnerforcedir = new GridBagConstraints();
		gbc_spinnerforcedir.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerforcedir.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerforcedir.gridx = 3;
		gbc_spinnerforcedir.gridy = 0;
		getContentPane().add(spinnerforcedir, gbc_spinnerforcedir);
		
		stlchckbxAsm = new SteelCheckBox();
		stlchckbxAsm.setText("asm type");
		stlchckbxAsm.setColored(true);
		GridBagConstraints gbc_stlchckbxAsm = new GridBagConstraints();
		gbc_stlchckbxAsm.gridwidth = 2;
		gbc_stlchckbxAsm.fill = GridBagConstraints.HORIZONTAL;
		gbc_stlchckbxAsm.insets = new Insets(0, 3, 5, 0);
		gbc_stlchckbxAsm.gridx = 4;
		gbc_stlchckbxAsm.gridy = 0;
		getContentPane().add(stlchckbxAsm, gbc_stlchckbxAsm);
		
		lblPreset = new JLabel("Preset");
		GridBagConstraints gbc_lblPreset = new GridBagConstraints();
		gbc_lblPreset.anchor = GridBagConstraints.EAST;
		gbc_lblPreset.insets = new Insets(0, 0, 5, 5);
		gbc_lblPreset.gridx = 0;
		gbc_lblPreset.gridy = 1;
		getContentPane().add(lblPreset, gbc_lblPreset);
		
		comboBoxPreset = new JComboBox();
		GridBagConstraints gbc_comboBoxPreset = new GridBagConstraints();
		gbc_comboBoxPreset.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPreset.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPreset.gridx = 1;
		gbc_comboBoxPreset.gridy = 1;
		getContentPane().add(comboBoxPreset, gbc_comboBoxPreset);
		
		lblLadepth = new JLabel("la_depth");
		GridBagConstraints gbc_lblLadepth = new GridBagConstraints();
		gbc_lblLadepth.anchor = GridBagConstraints.EAST;
		gbc_lblLadepth.insets = new Insets(0, 0, 5, 5);
		gbc_lblLadepth.gridx = 2;
		gbc_lblLadepth.gridy = 1;
		getContentPane().add(lblLadepth, gbc_lblLadepth);
		
		spinnerladepth = new JSpinner();
		GridBagConstraints gbc_spinnerladepth = new GridBagConstraints();
		gbc_spinnerladepth.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerladepth.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerladepth.gridx = 3;
		gbc_spinnerladepth.gridy = 1;
		getContentPane().add(spinnerladepth, gbc_spinnerladepth);
		
		stlchckbxAud = new SteelCheckBox();
		stlchckbxAud.setText("aud");
		stlchckbxAud.setColored(true);
		GridBagConstraints gbc_stlchckbxAud = new GridBagConstraints();
		gbc_stlchckbxAud.gridwidth = 2;
		gbc_stlchckbxAud.fill = GridBagConstraints.HORIZONTAL;
		gbc_stlchckbxAud.insets = new Insets(0, 3, 5, 0);
		gbc_stlchckbxAud.gridx = 4;
		gbc_stlchckbxAud.gridy = 1;
		getContentPane().add(stlchckbxAud, gbc_stlchckbxAud);
		
		lblRateMode = new JLabel("Rate mode");
		GridBagConstraints gbc_lblRateMode = new GridBagConstraints();
		gbc_lblRateMode.anchor = GridBagConstraints.EAST;
		gbc_lblRateMode.insets = new Insets(0, 0, 5, 5);
		gbc_lblRateMode.gridx = 0;
		gbc_lblRateMode.gridy = 2;
		getContentPane().add(lblRateMode, gbc_lblRateMode);
		
		comboBoxRatemode = new JComboBox();
		GridBagConstraints gbc_comboBoxRatemode = new GridBagConstraints();
		gbc_comboBoxRatemode.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxRatemode.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxRatemode.gridx = 1;
		gbc_comboBoxRatemode.gridy = 2;
		getContentPane().add(comboBoxRatemode, gbc_comboBoxRatemode);
		
		lblLevel = new JLabel("level");
		GridBagConstraints gbc_lblLevel = new GridBagConstraints();
		gbc_lblLevel.anchor = GridBagConstraints.EAST;
		gbc_lblLevel.insets = new Insets(0, 0, 5, 5);
		gbc_lblLevel.gridx = 2;
		gbc_lblLevel.gridy = 2;
		getContentPane().add(lblLevel, gbc_lblLevel);
		
		spinnerlevel = new JSpinner();
		GridBagConstraints gbc_spinnerlevel = new GridBagConstraints();
		gbc_spinnerlevel.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerlevel.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerlevel.gridx = 3;
		gbc_spinnerlevel.gridy = 2;
		getContentPane().add(spinnerlevel, gbc_spinnerlevel);
		
		stlchckbxBlmode = new SteelCheckBox();
		stlchckbxBlmode.setText("bl mode");
		stlchckbxBlmode.setColored(true);
		GridBagConstraints gbc_stlchckbxBlmode = new GridBagConstraints();
		gbc_stlchckbxBlmode.gridwidth = 2;
		gbc_stlchckbxBlmode.fill = GridBagConstraints.HORIZONTAL;
		gbc_stlchckbxBlmode.insets = new Insets(0, 3, 5, 0);
		gbc_stlchckbxBlmode.gridx = 4;
		gbc_stlchckbxBlmode.gridy = 2;
		getContentPane().add(stlchckbxBlmode, gbc_stlchckbxBlmode);
		
		lblTune = new JLabel("Tune");
		GridBagConstraints gbc_lblTune = new GridBagConstraints();
		gbc_lblTune.anchor = GridBagConstraints.EAST;
		gbc_lblTune.insets = new Insets(0, 0, 5, 5);
		gbc_lblTune.gridx = 0;
		gbc_lblTune.gridy = 3;
		getContentPane().add(lblTune, gbc_lblTune);
		
		comboBoxTune = new JComboBox();
		GridBagConstraints gbc_comboBoxTune = new GridBagConstraints();
		gbc_comboBoxTune.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxTune.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxTune.gridx = 1;
		gbc_comboBoxTune.gridy = 3;
		getContentPane().add(comboBoxTune, gbc_comboBoxTune);
		
		lblSocket = new JLabel("socket");
		GridBagConstraints gbc_lblSocket = new GridBagConstraints();
		gbc_lblSocket.anchor = GridBagConstraints.EAST;
		gbc_lblSocket.insets = new Insets(0, 0, 5, 5);
		gbc_lblSocket.gridx = 2;
		gbc_lblSocket.gridy = 3;
		getContentPane().add(lblSocket, gbc_lblSocket);
		
		spinnersocket = new JSpinner();
		GridBagConstraints gbc_spinnersocket = new GridBagConstraints();
		gbc_spinnersocket.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnersocket.insets = new Insets(0, 0, 5, 5);
		gbc_spinnersocket.gridx = 3;
		gbc_spinnersocket.gridy = 3;
		getContentPane().add(spinnersocket, gbc_spinnersocket);
		
		stlchckbxScdetection = new SteelCheckBox();
		stlchckbxScdetection.setText("sc detection");
		stlchckbxScdetection.setColored(true);
		GridBagConstraints gbc_stlchckbxScdetection = new GridBagConstraints();
		gbc_stlchckbxScdetection.gridwidth = 2;
		gbc_stlchckbxScdetection.fill = GridBagConstraints.HORIZONTAL;
		gbc_stlchckbxScdetection.insets = new Insets(0, 3, 5, 0);
		gbc_stlchckbxScdetection.gridx = 4;
		gbc_stlchckbxScdetection.gridy = 3;
		getContentPane().add(stlchckbxScdetection, gbc_stlchckbxScdetection);
		
		lblHielevel = new JLabel("hielevel");
		GridBagConstraints gbc_lblHielevel = new GridBagConstraints();
		gbc_lblHielevel.anchor = GridBagConstraints.EAST;
		gbc_lblHielevel.insets = new Insets(0, 0, 5, 5);
		gbc_lblHielevel.gridx = 0;
		gbc_lblHielevel.gridy = 4;
		getContentPane().add(lblHielevel, gbc_lblHielevel);
		
		comboBoxhielevel = new JComboBox();
		GridBagConstraints gbc_comboBoxhielevel = new GridBagConstraints();
		gbc_comboBoxhielevel.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxhielevel.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxhielevel.gridx = 1;
		gbc_comboBoxhielevel.gridy = 4;
		getContentPane().add(comboBoxhielevel, gbc_comboBoxhielevel);
		
		lblThreadcount = new JLabel("thread count");
		GridBagConstraints gbc_lblThreadcount = new GridBagConstraints();
		gbc_lblThreadcount.anchor = GridBagConstraints.EAST;
		gbc_lblThreadcount.insets = new Insets(0, 0, 5, 5);
		gbc_lblThreadcount.gridx = 2;
		gbc_lblThreadcount.gridy = 4;
		getContentPane().add(lblThreadcount, gbc_lblThreadcount);
		
		spinnerthreadcount = new JSpinner();
		GridBagConstraints gbc_spinnerthreadcount = new GridBagConstraints();
		gbc_spinnerthreadcount.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerthreadcount.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerthreadcount.gridx = 3;
		gbc_spinnerthreadcount.gridy = 4;
		getContentPane().add(spinnerthreadcount, gbc_spinnerthreadcount);
		
		stlchckbxHdr = new SteelCheckBox();
		stlchckbxHdr.setText("hdr");
		stlchckbxHdr.setColored(true);
		GridBagConstraints gbc_stlchckbxHdr = new GridBagConstraints();
		gbc_stlchckbxHdr.gridwidth = 2;
		gbc_stlchckbxHdr.fill = GridBagConstraints.HORIZONTAL;
		gbc_stlchckbxHdr.insets = new Insets(0, 3, 5, 0);
		gbc_stlchckbxHdr.gridx = 4;
		gbc_stlchckbxHdr.gridy = 4;
		getContentPane().add(stlchckbxHdr, gbc_stlchckbxHdr);
		
		lblProfile = new JLabel("Profile");
		GridBagConstraints gbc_lblProfile = new GridBagConstraints();
		gbc_lblProfile.anchor = GridBagConstraints.EAST;
		gbc_lblProfile.insets = new Insets(0, 0, 5, 5);
		gbc_lblProfile.gridx = 0;
		gbc_lblProfile.gridy = 5;
		getContentPane().add(lblProfile, gbc_lblProfile);
		
		comboBoxProfile = new JComboBox();
		GridBagConstraints gbc_comboBoxProfile = new GridBagConstraints();
		gbc_comboBoxProfile.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxProfile.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxProfile.gridx = 1;
		gbc_comboBoxProfile.gridy = 5;
		getContentPane().add(comboBoxProfile, gbc_comboBoxProfile);
		
		lblTilerowcnt = new JLabel("tile_row_cnt");
		GridBagConstraints gbc_lblTilerowcnt = new GridBagConstraints();
		gbc_lblTilerowcnt.anchor = GridBagConstraints.EAST;
		gbc_lblTilerowcnt.insets = new Insets(0, 0, 5, 5);
		gbc_lblTilerowcnt.gridx = 2;
		gbc_lblTilerowcnt.gridy = 5;
		getContentPane().add(lblTilerowcnt, gbc_lblTilerowcnt);
		
		spinnertile_row_cnt = new JSpinner();
		GridBagConstraints gbc_spinnertile_row_cnt = new GridBagConstraints();
		gbc_spinnertile_row_cnt.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnertile_row_cnt.insets = new Insets(0, 0, 5, 5);
		gbc_spinnertile_row_cnt.gridx = 3;
		gbc_spinnertile_row_cnt.gridy = 5;
		getContentPane().add(spinnertile_row_cnt, gbc_spinnertile_row_cnt);
		
		stlchckbxUmv = new SteelCheckBox();
		stlchckbxUmv.setText("umv");
		stlchckbxUmv.setColored(true);
		GridBagConstraints gbc_stlchckbxUmv = new GridBagConstraints();
		gbc_stlchckbxUmv.gridwidth = 2;
		gbc_stlchckbxUmv.fill = GridBagConstraints.HORIZONTAL;
		gbc_stlchckbxUmv.insets = new Insets(0, 3, 5, 0);
		gbc_stlchckbxUmv.gridx = 4;
		gbc_stlchckbxUmv.gridy = 5;
		getContentPane().add(stlchckbxUmv, gbc_stlchckbxUmv);
		
		lblTier = new JLabel("Tier");
		GridBagConstraints gbc_lblTier = new GridBagConstraints();
		gbc_lblTier.anchor = GridBagConstraints.EAST;
		gbc_lblTier.insets = new Insets(0, 0, 5, 5);
		gbc_lblTier.gridx = 0;
		gbc_lblTier.gridy = 6;
		getContentPane().add(lblTier, gbc_lblTier);
		
		comboBoxTier = new JComboBox();
		GridBagConstraints gbc_comboBoxTier = new GridBagConstraints();
		gbc_comboBoxTier.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxTier.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxTier.gridx = 1;
		gbc_comboBoxTier.gridy = 6;
		getContentPane().add(comboBoxTier, gbc_comboBoxTier);
		
		lblTilecolcnt = new JLabel("tile_col_cnt");
		GridBagConstraints gbc_lblTilecolcnt = new GridBagConstraints();
		gbc_lblTilecolcnt.anchor = GridBagConstraints.EAST;
		gbc_lblTilecolcnt.insets = new Insets(0, 0, 5, 5);
		gbc_lblTilecolcnt.gridx = 2;
		gbc_lblTilecolcnt.gridy = 6;
		getContentPane().add(lblTilecolcnt, gbc_lblTilecolcnt);
		
		spinnertile_col_cnt = new JSpinner();
		GridBagConstraints gbc_spinnertile_col_cnt = new GridBagConstraints();
		gbc_spinnertile_col_cnt.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnertile_col_cnt.insets = new Insets(0, 0, 5, 5);
		gbc_spinnertile_col_cnt.gridx = 3;
		gbc_spinnertile_col_cnt.gridy = 6;
		getContentPane().add(spinnertile_col_cnt, gbc_spinnertile_col_cnt);
		
		stlchckbxSlicemode = new SteelCheckBox();
		stlchckbxSlicemode.setText("tile slice mode");
		stlchckbxSlicemode.setColored(true);
		GridBagConstraints gbc_stlchckbxSlicemode = new GridBagConstraints();
		gbc_stlchckbxSlicemode.gridwidth = 2;
		gbc_stlchckbxSlicemode.fill = GridBagConstraints.HORIZONTAL;
		gbc_stlchckbxSlicemode.insets = new Insets(0, 3, 5, 0);
		gbc_stlchckbxSlicemode.gridx = 4;
		gbc_stlchckbxSlicemode.gridy = 6;
		getContentPane().add(stlchckbxSlicemode, gbc_stlchckbxSlicemode);
		
		lblPredstruct = new JLabel("pred_struct");
		GridBagConstraints gbc_lblPredstruct = new GridBagConstraints();
		gbc_lblPredstruct.anchor = GridBagConstraints.EAST;
		gbc_lblPredstruct.insets = new Insets(0, 0, 5, 5);
		gbc_lblPredstruct.gridx = 2;
		gbc_lblPredstruct.gridy = 7;
		getContentPane().add(lblPredstruct, gbc_lblPredstruct);
		
		spinnerpred_struct = new JSpinner();
		GridBagConstraints gbc_spinnerpred_struct = new GridBagConstraints();
		gbc_spinnerpred_struct.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerpred_struct.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerpred_struct.gridx = 3;
		gbc_spinnerpred_struct.gridy = 7;
		getContentPane().add(spinnerpred_struct, gbc_spinnerpred_struct);
		
		stlchckbxVidinfo = new SteelCheckBox();
		stlchckbxVidinfo.setText("vid info");
		GridBagConstraints gbc_stlchckbxVidinfo = new GridBagConstraints();
		gbc_stlchckbxVidinfo.gridwidth = 2;
		gbc_stlchckbxVidinfo.fill = GridBagConstraints.HORIZONTAL;
		gbc_stlchckbxVidinfo.insets = new Insets(0, 3, 5, 0);
		gbc_stlchckbxVidinfo.gridx = 4;
		gbc_stlchckbxVidinfo.gridy = 7;
		getContentPane().add(stlchckbxVidinfo, gbc_stlchckbxVidinfo);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 6;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 9;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{1,1};
		gbl_panel.rowHeights = new int[]{1};
		gbl_panel.columnWeights = new double[]{1.0,1.0};
		gbl_panel.rowWeights = new double[]{1.0};
		panel.setLayout(gbl_panel);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setActionCommand(CLOSE);
		btnCancel.addActionListener(this);
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.fill = GridBagConstraints.BOTH;
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 0;
		panel.add(btnCancel, gbc_btnCancel);
		
		btnSave = new JButton("Save");
		btnSave.setActionCommand(SAVECLOSE);
		btnSave.addActionListener(this);
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.fill = GridBagConstraints.BOTH;
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = 0;
		panel.add(btnSave, gbc_btnSave);
		
		setSize(694, 400);
	}
	

	
	   private void initializeComponents() {
		   comboBoxpixelformat.setModel(new DefaultComboBoxModel<>(PixelFormatSVTAHEVC.values()));
		   comboBoxPreset.setModel(new DefaultComboBoxModel<>(SVTHEVCPresets.values()));
		   comboBoxPreset.setSelectedIndex(8);
		   comboBoxRatemode.setModel(new DefaultComboBoxModel<>(SVTHEVCBitRateMode.values()));
		   comboBoxTune.setModel(new DefaultComboBoxModel<>(SVTHEVCTune.values()));
		   comboBoxTune.setSelectedIndex(1);
		   comboBoxhielevel.setModel(new DefaultComboBoxModel<>(SVTHEVCHielevel.values()));
		   comboBoxhielevel.setSelectedIndex(3);
		   comboBoxProfile.setModel(new DefaultComboBoxModel<>(SVTHEVCProfile.values()));
		   comboBoxTier.setModel(new DefaultComboBoxModel<>(SVTTier.values()));

		     
		  SpinnerNumberModel modelforcedir = new SpinnerNumberModel(-1, -1, Integer.MAX_VALUE, 1);
		  spinnerforcedir.setModel(modelforcedir); 
		  
		  SpinnerNumberModel modelladepth = new SpinnerNumberModel(-1, -1, 256, 1);
		  spinnerladepth.setModel(modelladepth); 
		  
		  SpinnerNumberModel modellevel = new SpinnerNumberModel(0, 0, 255, 1);
		  spinnerlevel.setModel(modellevel); 
		  
		  SpinnerNumberModel modelsocket = new SpinnerNumberModel(-1, -1, 1, 1);
		  spinnersocket.setModel(modelsocket); 
		  
		  SpinnerNumberModel modelthreadcount = new SpinnerNumberModel(0,0,Integer.MAX_VALUE, 1);
		  spinnerthreadcount.setModel(modelthreadcount); 
		  
		  SpinnerNumberModel modeltile_row_cnt = new SpinnerNumberModel(1,1,16, 1);
		  spinnertile_row_cnt.setModel(modeltile_row_cnt); 
		  
		  SpinnerNumberModel modeltile_col_cnt = new SpinnerNumberModel(1,1,16, 1);
		  spinnertile_col_cnt.setModel(modeltile_col_cnt); 
		     
		  SpinnerNumberModel modelpred_struct = new SpinnerNumberModel(2,0,2, 1);
		  spinnerpred_struct.setModel(modelpred_struct); 
		  
		  stlchckbxAsm.setSelected(true);
		  stlchckbxAud.setSelected(false);
		  stlchckbxBlmode.setSelected(false);
		  stlchckbxScdetection.setSelected(true);
		  stlchckbxHdr.setSelected(false);
		  stlchckbxUmv.setSelected(true);
		  stlchckbxSlicemode.setSelected(false);

		   }

	
		private void saveChoosenOptionsToContainer() {

	          infocont.setPixformat((PixelFormatSVTAHEVC) comboBoxpixelformat.getSelectedItem());
	          infocont.setPreset((SVTHEVCPresets) comboBoxPreset.getSelectedItem());
	          infocont.setRatemode((SVTHEVCBitRateMode) comboBoxRatemode.getSelectedItem());
	          infocont.setHielevel((SVTHEVCHielevel) comboBoxhielevel.getSelectedItem());
	          infocont.setTune((SVTHEVCTune) comboBoxTune.getSelectedItem());
	          infocont.setTier((SVTTier) comboBoxTier.getSelectedItem());
	          infocont.setProfile((SVTHEVCProfile) comboBoxProfile.getSelectedItem());
	          
	          infocont.setForcedidr((int) spinnerforcedir.getValue());
	          infocont.setLa_depth((int) spinnerladepth.getValue());
	          infocont.setLevel((int) spinnerlevel.getValue());
	          infocont.setSocket((int) spinnersocket.getValue());
	          infocont.setThreadcount((int) spinnerthreadcount.getValue());
	          infocont.setTile_row_cnt((int) spinnertile_row_cnt.getValue());
	          infocont.setTile_col_cnt((int) spinnertile_col_cnt.getValue());
	          infocont.setPred_struct((int) spinnerpred_struct.getValue());
	          
	          infocont.setAsmtype(stlchckbxAsm.isSelected());
	          infocont.setAud(stlchckbxAud.isSelected());
	          infocont.setBlmode(stlchckbxBlmode.isSelected());
	          infocont.setSc_detection(stlchckbxScdetection.isSelected());
	          infocont.setHdr(stlchckbxHdr.isSelected()?1:0);
	          infocont.setUmv(stlchckbxUmv.isSelected());
	          infocont.setTile_slice_mode(stlchckbxSlicemode.isSelected());

	
			  
			}
			
			private void setInfoFromEncodingContainer() {
				
				
				comboBoxpixelformat.setSelectedItem(infocont.getPixformat());
				 comboBoxPreset.setSelectedItem(infocont.getPreset());
				 comboBoxRatemode.setSelectedItem(infocont.getRatemode());
				 comboBoxTune.setSelectedItem(infocont.getTune());
				 comboBoxhielevel.setSelectedItem(infocont.getHielevel());
				 comboBoxProfile.setSelectedItem(infocont.getProfile());
				 comboBoxTier.setSelectedItem(infocont.getTier());
				 
				 spinnerforcedir.setValue(infocont.getForcedidr());
				 spinnerladepth.setValue(infocont.getLa_depth());
				 spinnerlevel.setValue(infocont.getLevel());
				 spinnersocket.setValue(infocont.getSocket());
				 spinnerthreadcount.setValue(infocont.getThreadcount());
				 spinnertile_row_cnt.setValue(infocont.getTile_row_cnt());
				 spinnertile_col_cnt.setValue(infocont.getTile_col_cnt());
				 spinnerpred_struct.setValue(infocont.getPred_struct());
				 
				  stlchckbxAsm.setSelected(infocont.isAsmtype());
				  stlchckbxAud.setSelected(infocont.isAud());
				  stlchckbxBlmode.setSelected(infocont.isBlmode());
				  stlchckbxScdetection.setSelected(infocont.isSc_detection());
				  stlchckbxHdr.setSelected(infocont.getHdr()==1?true:false);
				  stlchckbxUmv.setSelected(infocont.isUmv());
				  stlchckbxSlicemode.setSelected(infocont.isTile_slice_mode());
				
			  
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
			  }
		
	}

	
	

	public static void main(String[] args) {
		  EventQueue.invokeLater(new Runnable() {
		        @Override
		        public void run() {
		            try {
		            	VideoSVTHEVCEncodingInfoContainer cont = new VideoSVTHEVCEncodingInfoContainer();
		              //VideoSVTAV1EncodingInfoContainer cont=new VideoSVTAV1EncodingInfoContainer();
		              SVTHEVCConfigurationsPanel dialog = new SVTHEVCConfigurationsPanel(cont);
		                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		                dialog.setVisible(true);
		            } catch (Exception e) {
		                Logger.error(e);
		            }
		        }
		    });

	}


}
