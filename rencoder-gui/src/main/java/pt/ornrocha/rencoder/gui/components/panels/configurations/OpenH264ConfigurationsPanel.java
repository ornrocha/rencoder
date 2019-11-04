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
import java.util.ResourceBundle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.Openh264SliceMode;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.ProfilesH264;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoOpenH264EncodingInfoContainer;

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
public class OpenH264ConfigurationsPanel extends JDialog implements ActionListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The j labelpreset. */
	private JLabel jLabelprofile;

	/** The j buttonok. */
	private JButton jButtonok;

	/** The j buttonclose. */
	private JButton jButtonclose;

	/** The j combo boxpreset. */
	private JComboBox<ProfilesH264> jComboBoxprofile;

	/** The close. */
	private static String CLOSE = "close";

	/** The saveclose. */
	private static String SAVECLOSE = "saveclose";

	private static String CHECKSLICE = "checkslice";

	/** The infocont. */
	private VideoOpenH264EncodingInfoContainer infocont = null;

	/** The rb. */
	private ResourceBundle rb;
	private JCheckBox chckbloopfilter;
	private JLabel lblSliceMode;
	private JComboBox<Openh264SliceMode> comboBoxslicemode;
	private JSpinner spinnernumberslices;
	private JLabel lblNumberSlices;
	private JLabel lblGopSize;
	private JSpinner spinnergopsize;
	private JLabel lblMaxRate;
	private JSpinner spinnermaxrate;
	private JLabel lblMaxNalSize;
	private JSpinner spinnernalsize;
	private JCheckBox chckbxSkipFrames;
	private JCheckBox chckbxCabac;

	/**
	 * Instantiates a new h264 configurations panel.
	 *
	 * @param infocont the infocont
	 */
	public OpenH264ConfigurationsPanel(VideoOpenH264EncodingInfoContainer infocont) {
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
		jComboBoxprofile.setModel(new DefaultComboBoxModel<>(ProfilesH264.values()));
		comboBoxslicemode.setModel(new DefaultComboBoxModel<>(Openh264SliceMode.values()));
		SpinnerNumberModel modelgopsize = new SpinnerNumberModel(0, 0, 500, 1);
		spinnergopsize.setModel(modelgopsize);
		SpinnerNumberModel modelmaxrate = new SpinnerNumberModel(0, 0, 50, 1);
		spinnermaxrate.setModel(modelmaxrate);
		SpinnerNumberModel modelmaxnal = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 10);
		spinnernalsize.setModel(modelmaxnal);
		chckbloopfilter.setSelected(true);
	}

	/**
	 * Inits the gui.
	 */
	private void initGUI() {
		try {
			{
				GridBagLayout thisLayout = new GridBagLayout();
				thisLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
				thisLayout.rowHeights = new int[] { 7, 7, 7, 7, 7, 7, 7, 7 };
				thisLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.1, 0.0, 0.1, 0.1 };

				thisLayout.columnWidths = new int[] { 7, 7, 7, 7, 7, 7, 7, 7, 0, 7, 7 };
				getContentPane().setLayout(thisLayout);
				this.setTitle(LangTools.getWordLanguage("OpenH264 settings", "openh264.panel"));
				{
					jLabelprofile = new JLabel();
					getContentPane().add(jLabelprofile, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
							GridBagConstraints.EAST, GridBagConstraints.VERTICAL, new Insets(0, 10, 5, 5), 0, 0));
					jLabelprofile.setText("Profile");
				}
				{

					jComboBoxprofile = new JComboBox<ProfilesH264>();
					getContentPane().add(jComboBoxprofile, new GridBagConstraints(1, 0, 9, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));

				}

				{
					lblSliceMode = new JLabel("Slice mode");
					GridBagConstraints gbc_lblSliceMode = new GridBagConstraints();
					gbc_lblSliceMode.anchor = GridBagConstraints.EAST;
					gbc_lblSliceMode.insets = new Insets(0, 0, 5, 5);
					gbc_lblSliceMode.gridx = 0;
					gbc_lblSliceMode.gridy = 2;
					getContentPane().add(lblSliceMode, gbc_lblSliceMode);
				}
				{
					comboBoxslicemode = new JComboBox<Openh264SliceMode>();
					comboBoxslicemode.addActionListener(this);
					comboBoxslicemode.setActionCommand(CHECKSLICE);
					GridBagConstraints gbc_comboBoxslicemode = new GridBagConstraints();
					gbc_comboBoxslicemode.gridwidth = 4;
					gbc_comboBoxslicemode.insets = new Insets(0, 0, 5, 5);
					gbc_comboBoxslicemode.fill = GridBagConstraints.HORIZONTAL;
					gbc_comboBoxslicemode.gridx = 1;
					gbc_comboBoxslicemode.gridy = 2;
					getContentPane().add(comboBoxslicemode, gbc_comboBoxslicemode);
					// showSlicenumber();
				}

				{
					lblGopSize = new JLabel("GOP size");
					GridBagConstraints gbc_lblGopSize = new GridBagConstraints();
					gbc_lblGopSize.insets = new Insets(0, 0, 5, 5);
					gbc_lblGopSize.gridx = 0;
					gbc_lblGopSize.gridy = 3;
					getContentPane().add(lblGopSize, gbc_lblGopSize);
				}
				{
					spinnergopsize = new JSpinner();
					GridBagConstraints gbc_spinnergopsize = new GridBagConstraints();
					gbc_spinnergopsize.fill = GridBagConstraints.HORIZONTAL;
					gbc_spinnergopsize.gridwidth = 4;
					gbc_spinnergopsize.insets = new Insets(0, 0, 5, 5);
					gbc_spinnergopsize.gridx = 1;
					gbc_spinnergopsize.gridy = 3;
					getContentPane().add(spinnergopsize, gbc_spinnergopsize);
				}
				{
					chckbloopfilter = new JCheckBox("Loop filter");
					chckbloopfilter.setToolTipText("Wavefront parallel processing");
					GridBagConstraints gbc_chckbloopfilter = new GridBagConstraints();
					gbc_chckbloopfilter.anchor = GridBagConstraints.WEST;
					gbc_chckbloopfilter.gridwidth = 3;
					gbc_chckbloopfilter.insets = new Insets(0, 0, 5, 5);
					gbc_chckbloopfilter.gridx = 6;
					gbc_chckbloopfilter.gridy = 3;
					getContentPane().add(chckbloopfilter, gbc_chckbloopfilter);
				}
				{
					lblMaxRate = new JLabel("Max rate");
					GridBagConstraints gbc_lblMaxRate = new GridBagConstraints();
					gbc_lblMaxRate.insets = new Insets(0, 0, 5, 5);
					gbc_lblMaxRate.gridx = 0;
					gbc_lblMaxRate.gridy = 4;
					getContentPane().add(lblMaxRate, gbc_lblMaxRate);
				}
				{
					spinnermaxrate = new JSpinner();
					GridBagConstraints gbc_spinnermaxrate = new GridBagConstraints();
					gbc_spinnermaxrate.fill = GridBagConstraints.HORIZONTAL;
					gbc_spinnermaxrate.gridwidth = 4;
					gbc_spinnermaxrate.insets = new Insets(0, 0, 5, 5);
					gbc_spinnermaxrate.gridx = 1;
					gbc_spinnermaxrate.gridy = 4;
					getContentPane().add(spinnermaxrate, gbc_spinnermaxrate);
				}
				{
					chckbxSkipFrames = new JCheckBox("Skip frames");
					GridBagConstraints gbc_chckbxSkipFrames = new GridBagConstraints();
					gbc_chckbxSkipFrames.anchor = GridBagConstraints.WEST;
					gbc_chckbxSkipFrames.gridwidth = 3;
					gbc_chckbxSkipFrames.insets = new Insets(0, 0, 5, 5);
					gbc_chckbxSkipFrames.gridx = 6;
					gbc_chckbxSkipFrames.gridy = 4;
					getContentPane().add(chckbxSkipFrames, gbc_chckbxSkipFrames);
				}
				{
					lblMaxNalSize = new JLabel("Max NAL size");
					GridBagConstraints gbc_lblMaxNalSize = new GridBagConstraints();
					gbc_lblMaxNalSize.insets = new Insets(0, 0, 5, 5);
					gbc_lblMaxNalSize.gridx = 0;
					gbc_lblMaxNalSize.gridy = 5;
					getContentPane().add(lblMaxNalSize, gbc_lblMaxNalSize);
				}
				{
					spinnernalsize = new JSpinner();
					GridBagConstraints gbc_spinnernalsize = new GridBagConstraints();
					gbc_spinnernalsize.fill = GridBagConstraints.HORIZONTAL;
					gbc_spinnernalsize.gridwidth = 4;
					gbc_spinnernalsize.insets = new Insets(0, 0, 5, 5);
					gbc_spinnernalsize.gridx = 1;
					gbc_spinnernalsize.gridy = 5;
					getContentPane().add(spinnernalsize, gbc_spinnernalsize);
				}
				{
					chckbxCabac = new JCheckBox("Cabac");
					GridBagConstraints gbc_chckbxCabac = new GridBagConstraints();
					gbc_chckbxCabac.anchor = GridBagConstraints.WEST;
					gbc_chckbxCabac.gridwidth = 4;
					gbc_chckbxCabac.insets = new Insets(0, 0, 5, 5);
					gbc_chckbxCabac.gridx = 6;
					gbc_chckbxCabac.gridy = 5;
					getContentPane().add(chckbxCabac, gbc_chckbxCabac);
				}

				{
					jButtonclose = new JButton();
					getContentPane().add(jButtonclose, new GridBagConstraints(0, 7, 7, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 5), 0, 0));
					jButtonclose.setText(LangTools.getWordLanguage("Cancel", "general.cancel"));
					jButtonclose.setActionCommand(CLOSE);
					jButtonclose.addActionListener(this);
				}
				{
					jButtonok = new JButton();
					getContentPane().add(jButtonok, new GridBagConstraints(7, 7, 4, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					jButtonok.setText(LangTools.getWordLanguage("Save", "general.save"));
					jButtonok.setActionCommand(SAVECLOSE);
					jButtonok.addActionListener(this);
				}

			}
			{
				this.setSize(499, 259);
			}
		} catch (Exception e) {
		    Logger.error(e);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();

		if (action.equals(CLOSE)) {
			this.dispose();
		}

		else if (action.equals(SAVECLOSE)) {
			saveChoosenOptionsToContainer();
			this.dispose();
		} else if (action.equals(CHECKSLICE)) {
			checkslicemode();
		}

	}

	private void checkslicemode() {
		Openh264SliceMode slicem = (Openh264SliceMode) comboBoxslicemode.getSelectedItem();
		if (slicem.equals(Openh264SliceMode.fixed)) {
			showSlicenumber();
		} else {
			removeSliceNumber();
		}
	}

	private void showSlicenumber() {

		if (lblNumberSlices == null && spinnernumberslices == null) {
			lblNumberSlices = new JLabel("number of slices");
			spinnernumberslices = new JSpinner();
		}

		GridBagConstraints gbc_lblNumberSlices = new GridBagConstraints();
		gbc_lblNumberSlices.gridwidth = 4;
		gbc_lblNumberSlices.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberSlices.gridx = 5;
		gbc_lblNumberSlices.gridy = 1;
		getContentPane().add(lblNumberSlices, gbc_lblNumberSlices);

		GridBagConstraints gbc_spinnernumberslices = new GridBagConstraints();
		gbc_spinnernumberslices.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnernumberslices.gridwidth = 2;
		gbc_spinnernumberslices.insets = new Insets(0, 0, 5, 5);
		gbc_spinnernumberslices.gridx = 6;
		gbc_spinnernumberslices.gridy = 2;
		getContentPane().add(spinnernumberslices, gbc_spinnernumberslices);

		getContentPane().validate();
		getContentPane().repaint();
	}

	private void removeSliceNumber() {
		if (lblNumberSlices != null && spinnernumberslices != null) {
			getContentPane().remove(lblNumberSlices);
			getContentPane().remove(spinnernumberslices);
			getContentPane().validate();
			getContentPane().repaint();
		}
	}

	/**
	 * Save choosen options to container.
	 */
	private void saveChoosenOptionsToContainer() {
		infocont.setProfile((ProfilesH264) jComboBoxprofile.getSelectedItem());
		infocont.setSlicemode((Openh264SliceMode) comboBoxslicemode.getSelectedItem());
		if (infocont.getSlicemode().equals(Openh264SliceMode.fixed))
			infocont.setSlicenumber((int) spinnernumberslices.getValue());

		infocont.setGopsize((int) spinnergopsize.getValue());
		infocont.setMaxrate((int) spinnermaxrate.getValue());
		infocont.setMax_nal_size((int) spinnernalsize.getValue());
		infocont.setLoopfilter(chckbloopfilter.isSelected());
		infocont.setCabac(chckbxCabac.isSelected());
		infocont.setAllow_skip_frames(chckbxSkipFrames.isSelected());
	}

	private void setInfoFromEncodingContainer() {

		jComboBoxprofile.setSelectedItem(infocont.getProfile());
		comboBoxslicemode.setSelectedItem(infocont.getSlicemode());

		spinnergopsize.setValue(infocont.getGopsize());
		spinnermaxrate.setValue(infocont.getMaxrate());
		spinnernalsize.setValue(infocont.getMax_nal_size());
		chckbloopfilter.setSelected(infocont.isLoopfilter());
		chckbxCabac.setSelected(infocont.isCabac());
		chckbxSkipFrames.setSelected(infocont.isAllow_skip_frames());

		if (infocont.getSlicemode().equals(Openh264SliceMode.fixed)) {
			showSlicenumber();
			spinnernumberslices.setValue(infocont.getSlicenumber());
		} else
			removeSliceNumber();

	}

}
