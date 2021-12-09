package pt.ornrocha.rencoder.gui.components.panels.configurations;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import org.tinylog.Logger;
import eu.hansolo.custom.SteelCheckBox;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.H264NvencRateControlPreset;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.NvencPresets;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.PixelFormatNvenc;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.PixelFormatSVTAV1;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.ProfilesHEVCNvenc;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.SVTAV1Hielevel;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.SVTTier;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.SVTAV1BitRateMode;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.SVTAV1Presets;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoLevel;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoHEVCNvencEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoSVTAV1EncodingInfoContainer;
import javax.swing.JPanel;
import javax.swing.JButton;

public class SVTAV1ConfigurationsPanel extends JDialog implements ActionListener {
  
  private static final long serialVersionUID = 1L;
  private VideoSVTAV1EncodingInfoContainer infocont;
  private JComboBox comboBoxPixelFormat;
  private JComboBox comboBoxPreset;
  private JComboBox comboBoxHielevel;
  private JComboBox comboBoxTier;
  private JComboBox comboBoxRateMode;
  private JSpinner spinnerLaDepth;
  private JSpinner spinnerQuantitezer;
  private JSpinner spinnerTileColumns;
  private JSpinner spinnerTileRows;
  private SteelCheckBox chckbxSc;
  private JLabel lblNewLabel;
  private JLabel lblPreset;
  private JLabel lblHielevel;
  private JLabel lblTier;
  private JLabel lblRateMode;
  private JLabel lblLaDepth;
  private JLabel lblQuantitizer;
  private JLabel lblTileColumns;
  private JLabel lblTileRows;
  private JPanel panel;
  private JButton btnCancel;
  private JButton btnApply;

  private static String CLOSE = "close";
  private static String SAVECLOSE = "saveclose";
  
  public SVTAV1ConfigurationsPanel(VideoSVTAV1EncodingInfoContainer cont) {
        this.infocont=cont;
		initGUI();
		initializeComponents();
		setInfoFromEncodingContainer();
	}
  
  
  
	private void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1,1};
		gridBagLayout.rowHeights = new int[]{1,1,1,1,1,1,1,1,1,1,1};
		gridBagLayout.columnWeights = new double[]{1.0,1.0,1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		getContentPane().setLayout(gridBagLayout);
		
		lblNewLabel = new JLabel("Pixel format");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		comboBoxPixelFormat = new JComboBox();
		GridBagConstraints gbc_comboBoxPixelFormat = new GridBagConstraints();
		gbc_comboBoxPixelFormat.gridwidth = 2;
		gbc_comboBoxPixelFormat.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxPixelFormat.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPixelFormat.gridx = 1;
		gbc_comboBoxPixelFormat.gridy = 0;
		getContentPane().add(comboBoxPixelFormat, gbc_comboBoxPixelFormat);
		
		lblPreset = new JLabel("Preset");
		GridBagConstraints gbc_lblPreset = new GridBagConstraints();
		gbc_lblPreset.insets = new Insets(0, 0, 5, 5);
		gbc_lblPreset.anchor = GridBagConstraints.EAST;
		gbc_lblPreset.gridx = 0;
		gbc_lblPreset.gridy = 1;
		getContentPane().add(lblPreset, gbc_lblPreset);
		
		comboBoxPreset = new JComboBox();
		GridBagConstraints gbc_comboBoxPreset = new GridBagConstraints();
		gbc_comboBoxPreset.gridwidth = 2;
		gbc_comboBoxPreset.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxPreset.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPreset.gridx = 1;
		gbc_comboBoxPreset.gridy = 1;
		getContentPane().add(comboBoxPreset, gbc_comboBoxPreset);
	  
	  lblHielevel = new JLabel("Hielevel");
	  GridBagConstraints gbc_lblHielevel = new GridBagConstraints();
	  gbc_lblHielevel.insets = new Insets(0, 0, 5, 5);
	  gbc_lblHielevel.anchor = GridBagConstraints.EAST;
	  gbc_lblHielevel.gridx = 0;
	  gbc_lblHielevel.gridy = 2;
	  getContentPane().add(lblHielevel, gbc_lblHielevel);
	  
	  comboBoxHielevel = new JComboBox();
	  GridBagConstraints gbc_comboBoxHielevel = new GridBagConstraints();
	  gbc_comboBoxHielevel.gridwidth = 2;
	  gbc_comboBoxHielevel.insets = new Insets(0, 0, 5, 0);
	  gbc_comboBoxHielevel.fill = GridBagConstraints.HORIZONTAL;
	  gbc_comboBoxHielevel.gridx = 1;
	  gbc_comboBoxHielevel.gridy = 2;
	  getContentPane().add(comboBoxHielevel, gbc_comboBoxHielevel);
	  
	  lblTier = new JLabel("Tier");
	  GridBagConstraints gbc_lblTier = new GridBagConstraints();
	  gbc_lblTier.insets = new Insets(0, 0, 5, 5);
	  gbc_lblTier.anchor = GridBagConstraints.EAST;
	  gbc_lblTier.gridx = 0;
	  gbc_lblTier.gridy = 3;
	  getContentPane().add(lblTier, gbc_lblTier);
	  
	  comboBoxTier = new JComboBox();
	  GridBagConstraints gbc_comboBoxTier = new GridBagConstraints();
	  gbc_comboBoxTier.gridwidth = 2;
	  gbc_comboBoxTier.insets = new Insets(0, 0, 5, 0);
	  gbc_comboBoxTier.fill = GridBagConstraints.HORIZONTAL;
	  gbc_comboBoxTier.gridx = 1;
	  gbc_comboBoxTier.gridy = 3;
	  getContentPane().add(comboBoxTier, gbc_comboBoxTier);
	  
	  lblRateMode = new JLabel("Rate mode");
	  GridBagConstraints gbc_lblRateMode = new GridBagConstraints();
	  gbc_lblRateMode.insets = new Insets(0, 0, 5, 5);
	  gbc_lblRateMode.anchor = GridBagConstraints.EAST;
	  gbc_lblRateMode.gridx = 0;
	  gbc_lblRateMode.gridy = 4;
	  getContentPane().add(lblRateMode, gbc_lblRateMode);
	  
	  comboBoxRateMode = new JComboBox();
	  GridBagConstraints gbc_comboBoxRateMode = new GridBagConstraints();
	  gbc_comboBoxRateMode.gridwidth = 2;
	  gbc_comboBoxRateMode.insets = new Insets(0, 0, 5, 0);
	  gbc_comboBoxRateMode.fill = GridBagConstraints.HORIZONTAL;
	  gbc_comboBoxRateMode.gridx = 1;
	  gbc_comboBoxRateMode.gridy = 4;
	  getContentPane().add(comboBoxRateMode, gbc_comboBoxRateMode);
	  
	  lblQuantitizer = new JLabel("Quantizer");
	  GridBagConstraints gbc_lblQuantitizer = new GridBagConstraints();
	  gbc_lblQuantitizer.anchor = GridBagConstraints.EAST;
	  gbc_lblQuantitizer.insets = new Insets(0, 0, 5, 5);
	  gbc_lblQuantitizer.gridx = 0;
	  gbc_lblQuantitizer.gridy = 5;
	  getContentPane().add(lblQuantitizer, gbc_lblQuantitizer);
	  
	  spinnerQuantitezer = new JSpinner();
	  GridBagConstraints gbc_spinnerQuantitezer = new GridBagConstraints();
	  gbc_spinnerQuantitezer.fill = GridBagConstraints.HORIZONTAL;
	  gbc_spinnerQuantitezer.insets = new Insets(0, 0, 5, 5);
	  gbc_spinnerQuantitezer.gridx = 1;
	  gbc_spinnerQuantitezer.gridy = 5;
	  getContentPane().add(spinnerQuantitezer, gbc_spinnerQuantitezer);
	  
	  lblLaDepth = new JLabel("La depth");
	  GridBagConstraints gbc_lblLaDepth = new GridBagConstraints();
	  gbc_lblLaDepth.anchor = GridBagConstraints.EAST;
	  gbc_lblLaDepth.insets = new Insets(0, 0, 5, 5);
	  gbc_lblLaDepth.gridx = 0;
	  gbc_lblLaDepth.gridy = 6;
	  getContentPane().add(lblLaDepth, gbc_lblLaDepth);
	  
	  spinnerLaDepth = new JSpinner();
	  GridBagConstraints gbc_spinnerLaDepth = new GridBagConstraints();
	  gbc_spinnerLaDepth.fill = GridBagConstraints.HORIZONTAL;
	  gbc_spinnerLaDepth.insets = new Insets(0, 0, 5, 5);
	  gbc_spinnerLaDepth.gridx = 1;
	  gbc_spinnerLaDepth.gridy = 6;
	  getContentPane().add(spinnerLaDepth, gbc_spinnerLaDepth);
	  
	  lblTileColumns = new JLabel("Tile columns");
	  GridBagConstraints gbc_lblTileColumns = new GridBagConstraints();
	  gbc_lblTileColumns.anchor = GridBagConstraints.EAST;
	  gbc_lblTileColumns.insets = new Insets(0, 0, 5, 5);
	  gbc_lblTileColumns.gridx = 0;
	  gbc_lblTileColumns.gridy = 7;
	  getContentPane().add(lblTileColumns, gbc_lblTileColumns);
	  
	  spinnerTileColumns = new JSpinner();
	  GridBagConstraints gbc_spinnerTileColumns = new GridBagConstraints();
	  gbc_spinnerTileColumns.fill = GridBagConstraints.HORIZONTAL;
	  gbc_spinnerTileColumns.insets = new Insets(0, 0, 5, 5);
	  gbc_spinnerTileColumns.gridx = 1;
	  gbc_spinnerTileColumns.gridy = 7;
	  getContentPane().add(spinnerTileColumns, gbc_spinnerTileColumns);
	  
	  lblTileRows = new JLabel("Tile rows");
	  GridBagConstraints gbc_lblTileRows = new GridBagConstraints();
	  gbc_lblTileRows.anchor = GridBagConstraints.EAST;
	  gbc_lblTileRows.insets = new Insets(0, 0, 5, 5);
	  gbc_lblTileRows.gridx = 0;
	  gbc_lblTileRows.gridy = 8;
	  getContentPane().add(lblTileRows, gbc_lblTileRows);
	  
	  spinnerTileRows = new JSpinner();
	  GridBagConstraints gbc_spinnerTileRows = new GridBagConstraints();
	  gbc_spinnerTileRows.fill = GridBagConstraints.HORIZONTAL;
	  gbc_spinnerTileRows.insets = new Insets(0, 0, 5, 5);
	  gbc_spinnerTileRows.gridx = 1;
	  gbc_spinnerTileRows.gridy = 8;
	  getContentPane().add(spinnerTileRows, gbc_spinnerTileRows);
	  
	  chckbxSc = new SteelCheckBox();
	  chckbxSc.setText("Sc detection");
	  GridBagConstraints gbc_chckbxSc = new GridBagConstraints();
	  gbc_chckbxSc.fill = GridBagConstraints.HORIZONTAL;
	  gbc_chckbxSc.gridwidth = 2;
	  gbc_chckbxSc.insets = new Insets(0, 0, 5, 5);
	  gbc_chckbxSc.gridx = 1;
	  gbc_chckbxSc.gridy = 9;
	  getContentPane().add(chckbxSc, gbc_chckbxSc);
	  
	  panel = new JPanel();
	  GridBagConstraints gbc_panel = new GridBagConstraints();
	  gbc_panel.gridwidth = 3;
	  gbc_panel.insets = new Insets(0, 0, 0, 5);
	  gbc_panel.fill = GridBagConstraints.BOTH;
	  gbc_panel.gridx = 0;
	  gbc_panel.gridy = 10;
	  getContentPane().add(panel, gbc_panel);
	  GridBagLayout gbl_panel = new GridBagLayout();
	  gbl_panel.columnWidths = new int[]{1,1};
	  gbl_panel.rowHeights = new int[]{1};
	  gbl_panel.columnWeights = new double[]{1.0,1.0};
	  gbl_panel.rowWeights = new double[]{1.0};
	  panel.setLayout(gbl_panel);
	  
	  btnCancel = new JButton(LangTools.getWordLanguage("Cancel", "general.cancel"));
	  btnCancel.setActionCommand(CLOSE);
	  btnCancel.addActionListener(this);
	  GridBagConstraints gbc_btnCancel = new GridBagConstraints();
	  gbc_btnCancel.fill = GridBagConstraints.BOTH;
	  gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
	  gbc_btnCancel.gridx = 0;
	  gbc_btnCancel.gridy = 0;
	  panel.add(btnCancel, gbc_btnCancel);
	  
	  btnApply = new JButton(LangTools.getWordLanguage("Save", "general.save"));
	  btnApply.setActionCommand(SAVECLOSE);
	  btnApply.addActionListener(this);
	  GridBagConstraints gbc_btnApply = new GridBagConstraints();
	  gbc_btnApply.fill = GridBagConstraints.BOTH;
	  gbc_btnApply.gridx = 1;
	  gbc_btnApply.gridy = 0;
	  panel.add(btnApply, gbc_btnApply);
	  
	  setSize(400, 500);
	}

	   private void initializeComponents() {
	     comboBoxPixelFormat.setModel(new DefaultComboBoxModel<>(PixelFormatSVTAV1.values()));
	     comboBoxPreset.setModel(new DefaultComboBoxModel<>(SVTAV1Presets.values()));
	     comboBoxPreset.setSelectedIndex(8);
	     comboBoxHielevel.setModel(new DefaultComboBoxModel<>(SVTAV1Hielevel.values()));
	     comboBoxHielevel.setSelectedIndex(1);
	     comboBoxTier.setModel(new DefaultComboBoxModel<>(SVTTier.values()));
	     comboBoxRateMode.setModel(new DefaultComboBoxModel<>(SVTAV1BitRateMode.values()));
         
	     
	      SpinnerNumberModel modelquantizer = new SpinnerNumberModel(50, 0, 63, 1);
	      spinnerQuantitezer.setModel(modelquantizer);
	      
	      SpinnerNumberModel modelladepth = new SpinnerNumberModel(-1, -1, 120, 1);
	      spinnerLaDepth.setModel(modelladepth);
	      
	      SpinnerNumberModel modeltilecolumns = new SpinnerNumberModel(0, 0, 4, 1);
	      spinnerTileColumns.setModel(modeltilecolumns);
	      
	       SpinnerNumberModel modeltilerows = new SpinnerNumberModel(0, 0, 6, 1);
	       spinnerTileRows.setModel(modeltilerows);
	       
	       chckbxSc.setSelected(false);

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
		
		
		private void saveChoosenOptionsToContainer() {

          infocont.setPixformat((PixelFormatSVTAV1) comboBoxPixelFormat.getSelectedItem());
          infocont.setPreset((SVTAV1Presets) comboBoxPreset.getSelectedItem());
          infocont.setHielevel((SVTAV1Hielevel) comboBoxHielevel.getSelectedItem());
          infocont.setTier((SVTTier) comboBoxTier.getSelectedItem());
          infocont.setRatemode((SVTAV1BitRateMode) comboBoxRateMode.getSelectedItem());
          infocont.setQuantizer((int) spinnerQuantitezer.getValue());
          infocont.setLa_depth((int) spinnerLaDepth.getValue());
		  infocont.setTile_columns((int) spinnerTileColumns.getValue());
		  infocont.setTile_rows((int) spinnerTileRows.getValue());
		  infocont.setSc_detection(chckbxSc.isSelected());
		  
		}
		
		private void setInfoFromEncodingContainer() {
		  comboBoxPixelFormat.setSelectedItem(infocont.getPixformat());
		  comboBoxPreset.setSelectedItem(infocont.getPreset());
		  comboBoxHielevel.setSelectedItem(infocont.getHielevel());
		  comboBoxTier.setSelectedItem(infocont.getTier());
		  comboBoxRateMode.setSelectedItem(infocont.getRatemode());
		  spinnerQuantitezer.setValue(infocont.getQuantizer());
		  spinnerLaDepth.setValue(infocont.getLa_depth());
		  spinnerTileColumns.setValue(infocont.getTile_columns());
		  spinnerTileRows.setValue(infocont.getTile_rows());
		  chckbxSc.setSelected(infocont.isSc_detection());
		  
		}
	
	
	public static void main(String[] args) {
	  EventQueue.invokeLater(new Runnable() {
        @Override
        public void run() {
            try {
                //VideoHEVCNvencEncodingInfoContainer cont = new VideoHEVCNvencEncodingInfoContainer();
              VideoSVTAV1EncodingInfoContainer cont=new VideoSVTAV1EncodingInfoContainer();
              SVTAV1ConfigurationsPanel dialog = new SVTAV1ConfigurationsPanel(cont);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            } catch (Exception e) {
                Logger.error(e);
            }
        }
    });

	}



}
