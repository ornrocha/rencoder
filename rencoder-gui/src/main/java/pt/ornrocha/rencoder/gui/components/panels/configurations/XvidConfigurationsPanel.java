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
package pt.ornrocha.rencoder.gui.components.panels.configurations;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.XvidFlags;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.XvidMBD;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.XvidMEMethod;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoXvidEncodingInfoContainer;

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
public class XvidConfigurationsPanel extends JDialog implements ActionListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The j buttonok. */
	private JButton jButtonok;

	/** The j buttonclose. */
	private JButton jButtonclose;

	/** The close. */
	private static String CLOSE = "close";

	/** The saveclose. */
	private static String SAVECLOSE = "saveclose";

	/** The infocont. */
	private VideoXvidEncodingInfoContainer infocont = null;

	/** The rb. */
	private ResourceBundle rb;

	private JComboBox<XvidMBD> comboBoxmbd;
	private JComboBox<XvidMEMethod> comboBoxmemethod;

	private JSpinner spinnermequla;
	private JSpinner spinnerqmin;
	private JSpinner spinnerqmax;
	private JSpinner spinnertrellis;
	private JCheckBox chckbxLumiaq;
	private JCheckBox chckbxVarianceaq;
	private JCheckBox chckbxGmc;
	private JCheckBox chckbxMpegquant;

	private JRadioButton rdbtnmv4;
	private JRadioButton rdbtnGray;
	private JRadioButton rdbtnCgop;
	private JRadioButton rdbtnGlobalheader;
	private JRadioButton rdbtnAic;
	private JRadioButton rdbtnGmc;
	private JRadioButton rdbtnQpel;

	/**
	 * Instantiates a new h264 configurations panel.
	 *
	 * @param infocont the infocont
	 */
	public XvidConfigurationsPanel(VideoXvidEncodingInfoContainer infocont) {
		rb = ResourceBundle.getBundle("lang", LangTools.getDefinedLanguage(), LangTools.loadLanguagesPath());
		initGUI();
		this.setModal(true);
		initializeComponents();
		this.infocont = infocont;
		setInfoFromEncodingContainer();

	}

	/**
	 * Initialize components.
	 */
	private void initializeComponents() {

		comboBoxmbd.setModel(new DefaultComboBoxModel<>(XvidMBD.values()));
		comboBoxmemethod.setModel(new DefaultComboBoxModel<>(XvidMEMethod.values()));

		SpinnerNumberModel modelmequal = new SpinnerNumberModel(4, 0, 6, 1);
		spinnermequla.setModel(modelmequal);

		SpinnerNumberModel modelqmin = new SpinnerNumberModel(0, 0, 31, 1);
		spinnerqmin.setModel(modelqmin);

		SpinnerNumberModel modelqmax = new SpinnerNumberModel(0, 0, 31, 1);
		spinnerqmax.setModel(modelqmax);

		SpinnerNumberModel modeltrellis = new SpinnerNumberModel(0, 0, 50, 1);
		spinnertrellis.setModel(modeltrellis);

	}

	

	/**
	 * Inits the gui.
	 */
	private void initGUI() {
		try {
			{
				GridBagLayout thisLayout = new GridBagLayout();
				thisLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.0, 0.0, 0.1 };
				thisLayout.rowHeights = new int[] { 7, 7, 7, 7, 7, 7, 7, 7 };
				thisLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.1, 0.0, 0.0, 0.0, 0.0, 0.1, 0.1 };

				thisLayout.columnWidths = new int[] { 7, 7, 7, 7, 7, 7, 7, 7, 7, 7 };
				getContentPane().setLayout(thisLayout);
				this.setTitle(LangTools.getResourceBundleWordLanguage(rb, "Xvid codec settings", "xvid.panel"));

				JLabel lblNewLabel = new JLabel("mbd     ");
				lblNewLabel.setToolTipText("Set macroblock decision algorithm");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel.gridx = 0;
				gbc_lblNewLabel.gridy = 0;
				getContentPane().add(lblNewLabel, gbc_lblNewLabel);

				comboBoxmbd = new JComboBox<XvidMBD>();
				comboBoxmbd.setToolTipText("Set macroblock decision algorithm");
				GridBagConstraints gbc_comboBoxmbd = new GridBagConstraints();
				gbc_comboBoxmbd.gridwidth = 8;
				gbc_comboBoxmbd.insets = new Insets(0, 0, 5, 5);
				gbc_comboBoxmbd.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBoxmbd.gridx = 1;
				gbc_comboBoxmbd.gridy = 0;
				getContentPane().add(comboBoxmbd, gbc_comboBoxmbd);

				JLabel lblMeMethod = new JLabel("me method");
				lblMeMethod.setToolTipText("Set motion estimation method");
				GridBagConstraints gbc_lblMeMethod = new GridBagConstraints();
				gbc_lblMeMethod.anchor = GridBagConstraints.EAST;
				gbc_lblMeMethod.insets = new Insets(0, 0, 5, 5);
				gbc_lblMeMethod.gridx = 0;
				gbc_lblMeMethod.gridy = 1;
				getContentPane().add(lblMeMethod, gbc_lblMeMethod);

				comboBoxmemethod = new JComboBox<XvidMEMethod>();
				comboBoxmemethod.setToolTipText("Set motion estimation method");
				GridBagConstraints gbc_comboBoxmemethod = new GridBagConstraints();
				gbc_comboBoxmemethod.gridwidth = 8;
				gbc_comboBoxmemethod.insets = new Insets(0, 0, 5, 5);
				gbc_comboBoxmemethod.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBoxmemethod.gridx = 1;
				gbc_comboBoxmemethod.gridy = 1;
				getContentPane().add(comboBoxmemethod, gbc_comboBoxmemethod);

				chckbxLumiaq = new JCheckBox("lumi_aq");
				chckbxLumiaq.setToolTipText("Lumi masking adaptive quantization");
				GridBagConstraints gbc_chckbxLumiaq = new GridBagConstraints();
				gbc_chckbxLumiaq.gridwidth = 2;
				gbc_chckbxLumiaq.insets = new Insets(0, 0, 5, 5);
				gbc_chckbxLumiaq.gridx = 0;
				gbc_chckbxLumiaq.gridy = 2;
				getContentPane().add(chckbxLumiaq, gbc_chckbxLumiaq);

				chckbxVarianceaq = new JCheckBox("variance_aq");
				chckbxVarianceaq.setToolTipText("Variance adaptive quantization");
				GridBagConstraints gbc_chckbxVarianceaq = new GridBagConstraints();
				gbc_chckbxVarianceaq.gridwidth = 2;
				gbc_chckbxVarianceaq.insets = new Insets(0, 0, 5, 5);
				gbc_chckbxVarianceaq.gridx = 2;
				gbc_chckbxVarianceaq.gridy = 2;
				getContentPane().add(chckbxVarianceaq, gbc_chckbxVarianceaq);

				chckbxGmc = new JCheckBox("gmc    ");
				chckbxGmc.setToolTipText("Global motion compensation");
				GridBagConstraints gbc_chckbxGmc = new GridBagConstraints();
				gbc_chckbxGmc.gridwidth = 2;
				gbc_chckbxGmc.insets = new Insets(0, 0, 5, 5);
				gbc_chckbxGmc.gridx = 4;
				gbc_chckbxGmc.gridy = 2;
				getContentPane().add(chckbxGmc, gbc_chckbxGmc);

				chckbxMpegquant = new JCheckBox("mpeg_quant");
				chckbxMpegquant.setToolTipText("Use MPEG quantizers instead of H.263");
				GridBagConstraints gbc_chckbxMpegquant = new GridBagConstraints();
				gbc_chckbxMpegquant.gridwidth = 2;
				gbc_chckbxMpegquant.insets = new Insets(0, 0, 5, 5);
				gbc_chckbxMpegquant.gridx = 6;
				gbc_chckbxMpegquant.gridy = 2;
				getContentPane().add(chckbxMpegquant, gbc_chckbxMpegquant);

				JLabel lblQmin = new JLabel("qmin    ");
				GridBagConstraints gbc_lblQmin = new GridBagConstraints();
				gbc_lblQmin.anchor = GridBagConstraints.EAST;
				gbc_lblQmin.insets = new Insets(0, 0, 5, 5);
				gbc_lblQmin.gridx = 0;
				gbc_lblQmin.gridy = 3;
				getContentPane().add(lblQmin, gbc_lblQmin);

				spinnerqmin = new JSpinner();
				spinnerqmin.setToolTipText("Minimum quantisation value");
				GridBagConstraints gbc_spinnerqmin = new GridBagConstraints();
				gbc_spinnerqmin.fill = GridBagConstraints.HORIZONTAL;
				gbc_spinnerqmin.gridwidth = 3;
				gbc_spinnerqmin.insets = new Insets(0, 0, 5, 5);
				gbc_spinnerqmin.gridx = 1;
				gbc_spinnerqmin.gridy = 3;
				getContentPane().add(spinnerqmin, gbc_spinnerqmin);

				JLabel lblQmax = new JLabel("qmax    ");
				GridBagConstraints gbc_lblQmax = new GridBagConstraints();
				gbc_lblQmax.insets = new Insets(0, 0, 5, 5);
				gbc_lblQmax.gridx = 5;
				gbc_lblQmax.gridy = 3;
				getContentPane().add(lblQmax, gbc_lblQmax);

				spinnerqmax = new JSpinner();
				spinnerqmax.setToolTipText("Maximum quantisation value");
				GridBagConstraints gbc_spinnerqmax = new GridBagConstraints();
				gbc_spinnerqmax.fill = GridBagConstraints.HORIZONTAL;
				gbc_spinnerqmax.gridwidth = 3;
				gbc_spinnerqmax.insets = new Insets(0, 0, 5, 5);
				gbc_spinnerqmax.gridx = 6;
				gbc_spinnerqmax.gridy = 3;
				getContentPane().add(spinnerqmax, gbc_spinnerqmax);

				JLabel lblMeQuality = new JLabel("me quality");
				GridBagConstraints gbc_lblMeQuality = new GridBagConstraints();
				gbc_lblMeQuality.anchor = GridBagConstraints.EAST;
				gbc_lblMeQuality.insets = new Insets(0, 0, 5, 5);
				gbc_lblMeQuality.gridx = 0;
				gbc_lblMeQuality.gridy = 4;
				getContentPane().add(lblMeQuality, gbc_lblMeQuality);

				spinnermequla = new JSpinner();
				spinnermequla.setToolTipText("Motion estimation quality");
				GridBagConstraints gbc_spinnermequla = new GridBagConstraints();
				gbc_spinnermequla.fill = GridBagConstraints.HORIZONTAL;
				gbc_spinnermequla.gridwidth = 3;
				gbc_spinnermequla.insets = new Insets(0, 0, 5, 5);
				gbc_spinnermequla.gridx = 1;
				gbc_spinnermequla.gridy = 4;
				getContentPane().add(spinnermequla, gbc_spinnermequla);

				JLabel lblTrellis = new JLabel("trellis");
				GridBagConstraints gbc_lblTrellis = new GridBagConstraints();
				gbc_lblTrellis.insets = new Insets(0, 0, 5, 5);
				gbc_lblTrellis.gridx = 5;
				gbc_lblTrellis.gridy = 4;
				getContentPane().add(lblTrellis, gbc_lblTrellis);

				spinnertrellis = new JSpinner();
				spinnertrellis.setToolTipText("Rate-distortion optimal quantization");
				GridBagConstraints gbc_spinnertrellis = new GridBagConstraints();
				gbc_spinnertrellis.fill = GridBagConstraints.HORIZONTAL;
				gbc_spinnertrellis.gridwidth = 3;
				gbc_spinnertrellis.insets = new Insets(0, 0, 5, 5);
				gbc_spinnertrellis.gridx = 6;
				gbc_spinnertrellis.gridy = 4;
				getContentPane().add(spinnertrellis, gbc_spinnertrellis);

				JPanel panel = new JPanel();
				panel.setToolTipText("Specific encoding flags");
				panel.setBorder(new TitledBorder(null, "Flags", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				GridBagConstraints gbc_panel = new GridBagConstraints();
				gbc_panel.gridwidth = 10;
				gbc_panel.insets = new Insets(0, 0, 5, 0);
				gbc_panel.fill = GridBagConstraints.BOTH;
				gbc_panel.gridx = 0;
				gbc_panel.gridy = 6;
				getContentPane().add(panel, gbc_panel);
				GridBagLayout gbl_panel = new GridBagLayout();
				gbl_panel.columnWidths = new int[] { 1, 1, 1, 1 };
				gbl_panel.rowHeights = new int[] { 1, 1 };
				gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
				gbl_panel.rowWeights = new double[] { 1.0, 1.0 };
				panel.setLayout(gbl_panel);

				rdbtnmv4 = new JRadioButton("mv4      ");
				rdbtnmv4.setToolTipText("Four motion vector by macroblock");
				GridBagConstraints gbc_rdbtnmv4 = new GridBagConstraints();
				gbc_rdbtnmv4.insets = new Insets(0, 0, 5, 5);
				gbc_rdbtnmv4.gridx = 0;
				gbc_rdbtnmv4.gridy = 0;
				panel.add(rdbtnmv4, gbc_rdbtnmv4);

				rdbtnGray = new JRadioButton("gray");
				rdbtnGray.setToolTipText("Only encode grayscale");
				GridBagConstraints gbc_rdbtnGray = new GridBagConstraints();
				gbc_rdbtnGray.anchor = GridBagConstraints.WEST;
				gbc_rdbtnGray.insets = new Insets(0, 0, 5, 5);
				gbc_rdbtnGray.gridx = 1;
				gbc_rdbtnGray.gridy = 0;
				panel.add(rdbtnGray, gbc_rdbtnGray);

				rdbtnCgop = new JRadioButton("cgop");
				rdbtnCgop.setToolTipText("Enable closed GOP");
				GridBagConstraints gbc_rdbtnCgop = new GridBagConstraints();
				gbc_rdbtnCgop.anchor = GridBagConstraints.WEST;
				gbc_rdbtnCgop.insets = new Insets(0, 0, 5, 5);
				gbc_rdbtnCgop.gridx = 2;
				gbc_rdbtnCgop.gridy = 0;
				panel.add(rdbtnCgop, gbc_rdbtnCgop);

				rdbtnGlobalheader = new JRadioButton("global_header");
				rdbtnGlobalheader.setToolTipText("Place global headers in extradata instead of every keyframe");
				GridBagConstraints gbc_rdbtnGlobalheader = new GridBagConstraints();
				gbc_rdbtnGlobalheader.anchor = GridBagConstraints.WEST;
				gbc_rdbtnGlobalheader.insets = new Insets(0, 0, 5, 0);
				gbc_rdbtnGlobalheader.gridx = 3;
				gbc_rdbtnGlobalheader.gridy = 0;
				panel.add(rdbtnGlobalheader, gbc_rdbtnGlobalheader);

				rdbtnAic = new JRadioButton("aic");
				rdbtnAic.setToolTipText("Enable high quality AC prediction");
				GridBagConstraints gbc_rdbtnAic = new GridBagConstraints();
				gbc_rdbtnAic.anchor = GridBagConstraints.WEST;
				gbc_rdbtnAic.insets = new Insets(0, 0, 0, 5);
				gbc_rdbtnAic.gridx = 0;
				gbc_rdbtnAic.gridy = 1;
				panel.add(rdbtnAic, gbc_rdbtnAic);

				rdbtnGmc = new JRadioButton("gmc    ");
				rdbtnGmc.setToolTipText("Enable the use of global motion compensation");
				GridBagConstraints gbc_rdbtnGmc = new GridBagConstraints();
				gbc_rdbtnGmc.anchor = GridBagConstraints.WEST;
				gbc_rdbtnGmc.insets = new Insets(0, 0, 0, 5);
				gbc_rdbtnGmc.gridx = 1;
				gbc_rdbtnGmc.gridy = 1;
				panel.add(rdbtnGmc, gbc_rdbtnGmc);

				rdbtnQpel = new JRadioButton("qpel");
				rdbtnQpel.setToolTipText("Enable quarter-pixel motion compensation");
				GridBagConstraints gbc_rdbtnQpel = new GridBagConstraints();
				gbc_rdbtnQpel.anchor = GridBagConstraints.WEST;
				gbc_rdbtnQpel.insets = new Insets(0, 0, 0, 5);
				gbc_rdbtnQpel.gridx = 2;
				gbc_rdbtnQpel.gridy = 1;
				panel.add(rdbtnQpel, gbc_rdbtnQpel);

				{
					jButtonclose = new JButton();
					getContentPane().add(jButtonclose, new GridBagConstraints(0, 7, 5, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 5), 0, 0));
					jButtonclose.setText(LangTools.getResourceBundleWordLanguage(rb, "Cancel", "general.cancel"));
					jButtonclose.setActionCommand(CLOSE);
					jButtonclose.addActionListener(this);
				}
				{
					jButtonok = new JButton();
					getContentPane().add(jButtonok, new GridBagConstraints(5, 7, 5, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					jButtonok.setText(LangTools.getResourceBundleWordLanguage(rb, "Save", "general.save"));
					jButtonok.setActionCommand(SAVECLOSE);
					jButtonok.addActionListener(this);
				}

			}
			{
				this.setSize(599, 392);
			}
		} catch (Exception e) {
		    Logger.error(e);
		}
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

		if (action.equals(CLOSE)) {
			this.dispose();
		}

		else if (action.equals(SAVECLOSE)) {
			saveChoosenOptionsToContainer();
			this.dispose();
		}
	}
	
	/**
	 * Sets the info from encoding container.
	 */
	private void setInfoFromEncodingContainer() {

	    ArrayList<XvidFlags> listflags=infocont.getFlags();
	    for (XvidFlags xvidFlags : listflags) {
		if(xvidFlags.equals(XvidFlags.mv4))
		    rdbtnmv4.setSelected(true);
		if(xvidFlags.equals(XvidFlags.aic))
		    rdbtnAic.setSelected(true);
		if(xvidFlags.equals(XvidFlags.cgop))
		    rdbtnCgop.setSelected(true);
		if(xvidFlags.equals(XvidFlags.global_header))
		    rdbtnGlobalheader.setSelected(true);
		if(xvidFlags.equals(XvidFlags.gmc))
		    rdbtnGmc.setSelected(true);
		if(xvidFlags.equals(XvidFlags.gray))
		    rdbtnGray.setSelected(true);
		if(xvidFlags.equals(XvidFlags.qpel))
		    rdbtnQpel.setSelected(true);
	    }
	    
	    comboBoxmbd.setSelectedItem(infocont.getMbd());
	    comboBoxmemethod.setSelectedItem(infocont.getMemethod());
	    if(infocont.isLumi_aq()) chckbxLumiaq.setSelected(true);
	    if(infocont.isGmc()) chckbxGmc.setSelected(true);
	    if(infocont.isMpeg_quant()) chckbxMpegquant.setSelected(true);
	    if(infocont.isVariance_aq()) chckbxVarianceaq.setSelected(true);
	    
	    spinnermequla.setValue(infocont.getMe_quality());
	    spinnerqmin.setValue(infocont.getQmin());
	    spinnerqmax.setValue(infocont.getQmax());
	    spinnertrellis.setValue(infocont.getTrellis());
	    
	}

	/**
	 * Save choosen options to container.
	 */
	private void saveChoosenOptionsToContainer() {

	    ArrayList<XvidFlags> listflags= new ArrayList<>();
	    if(rdbtnmv4.isSelected())
		listflags.add(XvidFlags.mv4);
	    if(rdbtnAic.isSelected())
		listflags.add(XvidFlags.aic);
	    if(rdbtnCgop.isSelected())
		listflags.add(XvidFlags.cgop);
	    if(rdbtnGlobalheader.isSelected())
		listflags.add(XvidFlags.global_header);
	    if(rdbtnGmc.isSelected())
		listflags.add(XvidFlags.gmc);
	    if(rdbtnGray.isSelected())
		listflags.add(XvidFlags.gray);
	    if(rdbtnQpel.isSelected())
		listflags.add(XvidFlags.qpel);
	    
	    infocont.setFlag(listflags);
	    infocont.setMbd((XvidMBD) comboBoxmbd.getSelectedItem());
	    infocont.setMemethod((XvidMEMethod) comboBoxmemethod.getSelectedItem());
	    
	    infocont.setLumi_aq(chckbxLumiaq.isSelected());
	    infocont.setGmc(chckbxGmc.isSelected());
	    infocont.setMpeg_quant(chckbxMpegquant.isSelected());
	    infocont.setVariance_aq(chckbxVarianceaq.isSelected());
	    
	    infocont.setMe_quality((int) spinnermequla.getValue());
	    infocont.setTrellis((int) spinnertrellis.getValue());
	    infocont.setQmin((int) spinnerqmin.getValue());
	    infocont.setQmax((int) spinnerqmax.getValue());
	}

}
