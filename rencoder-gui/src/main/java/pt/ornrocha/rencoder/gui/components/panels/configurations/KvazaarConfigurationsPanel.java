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
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoLevel;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.X26xPresets;
import pt.ornrocha.rencoder.gui.components.panels.auxiliar.CopyPasteJTextField;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoKvazaarEncodingInfoContainer;

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
public class KvazaarConfigurationsPanel extends JDialog implements ActionListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The j labelpreset. */
	private JLabel jLabelpreset;

	/** The j buttonok. */
	private JButton jButtonok;

	/** The j buttonclose. */
	private JButton jButtonclose;

	// private JTextField jTextFieldffmegcommand;

	/** The j labelprofile. */
	private JLabel jLabelperiod;

	/** The j combo boxpixelformat. */
	private JComboBox<VideoLevel> jComboBoxlevel;

	/** The j labelpixelformat. */
	private JLabel jLabellevel;

	/** The j combo boxpreset. */
	private JComboBox<X26xPresets> jComboBoxpreset;

	/** The close. */
	private static String CLOSE = "close";

	/** The saveclose. */
	private static String SAVECLOSE = "saveclose";

	/** The infocont. */
	private VideoKvazaarEncodingInfoContainer infocont = null;

	/** The rb. */
	private ResourceBundle rb;
	private JSpinner spinnerperiod;
	private JLabel lblNewLabel;
	private JSpinner spinnerofw;
	private JLabel lblRef;
	private JSpinner spinnerref;
	private JLabel lblSubme;
	private JSpinner spinnersubme;
	private JLabel lblTiles;
	private JSpinner spinnertiles;
	private JLabel lblOtherCommands;
	private JTextField textField;
	private JCheckBox chckbxWpp;

	/**
	 * Instantiates a new h264 configurations panel.
	 *
	 * @param infocont the infocont
	 */
	public KvazaarConfigurationsPanel(VideoKvazaarEncodingInfoContainer infocont) {
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
		jComboBoxpreset.setModel(new DefaultComboBoxModel<>(X26xPresets.values()));
		jComboBoxlevel.setModel(new DefaultComboBoxModel<>(VideoLevel.getKvazaarLevels()));
		SpinnerNumberModel modelofw = new SpinnerNumberModel(0, 0, 200, 1);
		spinnerofw.setModel(modelofw);
		SpinnerNumberModel modelperiod = new SpinnerNumberModel(64, 0, 500, 1);
		spinnerperiod.setModel(modelperiod);
		SpinnerNumberModel modeltiles = new SpinnerNumberModel(0, 0, 50, 1);
		spinnertiles.setModel(modeltiles);
		SpinnerNumberModel modelref = new SpinnerNumberModel(4, 0, 15, 1);
		spinnerref.setModel(modelref);
		SpinnerNumberModel modelsubme = new SpinnerNumberModel(4, 0, 4, 1);
		spinnersubme.setModel(modelsubme);
		chckbxWpp.setSelected(true);
	}

	/**
	 * Inits the gui.
	 */
	private void initGUI() {
		try {
			{
				GridBagLayout thisLayout = new GridBagLayout();
				thisLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.0, 0.1 };
				thisLayout.rowHeights = new int[] { 7, 7, 7, 7, 7, 7, 7, 7 };
				thisLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.1, 0.0, 0.0, 0.0, 0.1, 0.0, 0.1, 0.1 };

				thisLayout.columnWidths = new int[] { 7, 7, 7, 7, 7, 7, 7, 7, 0, 7, 7 };
				getContentPane().setLayout(thisLayout);
				this.setTitle(LangTools.getWordLanguage("Kvazaar codec settings", "kvazaar.confpanel"));
				{
					jLabelpreset = new JLabel();
					getContentPane().add(jLabelpreset, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
							GridBagConstraints.EAST, GridBagConstraints.VERTICAL, new Insets(0, 10, 5, 5), 0, 0));
					jLabelpreset.setText(LangTools.getResourceBundleWordLanguage(rb, "Preset", "general.preset"));
				}
				{

					jComboBoxpreset = new JComboBox<X26xPresets>();
					getContentPane().add(jComboBoxpreset, new GridBagConstraints(1, 1, 9, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));

				}

				{
					jLabellevel = new JLabel();
					getContentPane().add(jLabellevel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
							GridBagConstraints.EAST, GridBagConstraints.VERTICAL, new Insets(0, 10, 5, 5), 0, 0));
					jLabellevel.setText(LangTools.getResourceBundleWordLanguage(rb, "Level", "general.level"));
				}
				{

					jComboBoxlevel = new JComboBox<VideoLevel>();
					getContentPane().add(jComboBoxlevel, new GridBagConstraints(1, 2, 9, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));

				}
				{
					lblTiles = new JLabel("tiles");
					lblTiles.setToolTipText("Split picture into width x height uniform tiles");
					GridBagConstraints gbc_lblTiles = new GridBagConstraints();
					gbc_lblTiles.anchor = GridBagConstraints.EAST;
					gbc_lblTiles.insets = new Insets(0, 0, 5, 5);
					gbc_lblTiles.gridx = 1;
					gbc_lblTiles.gridy = 3;
					getContentPane().add(lblTiles, gbc_lblTiles);
				}
				{
					spinnertiles = new JSpinner();
					spinnertiles.setToolTipText("Split picture into width x height uniform tiles");
					GridBagConstraints gbc_spinnertiles = new GridBagConstraints();
					gbc_spinnertiles.gridwidth = 2;
					gbc_spinnertiles.fill = GridBagConstraints.HORIZONTAL;
					gbc_spinnertiles.insets = new Insets(0, 0, 5, 5);
					gbc_spinnertiles.gridx = 2;
					gbc_spinnertiles.gridy = 3;
					getContentPane().add(spinnertiles, gbc_spinnertiles);
				}
				{
					jLabelperiod = new JLabel();
					jLabelperiod.setToolTipText("Period of intra pictures");
					getContentPane().add(jLabelperiod, new GridBagConstraints(4, 3, 2, 1, 0.0, 0.0,
							GridBagConstraints.EAST, GridBagConstraints.VERTICAL, new Insets(0, 10, 5, 5), 0, 0));
					jLabelperiod.setText("period");
				}
				{
					spinnerperiod = new JSpinner();
					spinnerperiod.setToolTipText("Period of intra pictures");
					GridBagConstraints gbc_spinnerperiod = new GridBagConstraints();
					gbc_spinnerperiod.fill = GridBagConstraints.HORIZONTAL;
					gbc_spinnerperiod.gridwidth = 2;
					gbc_spinnerperiod.insets = new Insets(0, 0, 5, 5);
					gbc_spinnerperiod.gridx = 6;
					gbc_spinnerperiod.gridy = 3;
					getContentPane().add(spinnerperiod, gbc_spinnerperiod);
				}
				{
					lblRef = new JLabel("ref");
					lblRef.setToolTipText("Number of reference frames, in range 1..15");
					GridBagConstraints gbc_lblRef = new GridBagConstraints();
					gbc_lblRef.anchor = GridBagConstraints.EAST;
					gbc_lblRef.insets = new Insets(0, 0, 5, 5);
					gbc_lblRef.gridx = 1;
					gbc_lblRef.gridy = 4;
					getContentPane().add(lblRef, gbc_lblRef);
				}
				{
					spinnerref = new JSpinner();
					spinnerref.setToolTipText("Number of reference frames, in range 1..15");
					GridBagConstraints gbc_spinnerref = new GridBagConstraints();
					gbc_spinnerref.gridwidth = 2;
					gbc_spinnerref.fill = GridBagConstraints.HORIZONTAL;
					gbc_spinnerref.insets = new Insets(0, 0, 5, 5);
					gbc_spinnerref.gridx = 2;
					gbc_spinnerref.gridy = 4;
					getContentPane().add(spinnerref, gbc_spinnerref);
				}
				{
					lblSubme = new JLabel("subme");
					lblSubme.setToolTipText("Fractional pixel motion estimation level");
					GridBagConstraints gbc_lblSubme = new GridBagConstraints();
					gbc_lblSubme.gridwidth = 2;
					gbc_lblSubme.anchor = GridBagConstraints.EAST;
					gbc_lblSubme.insets = new Insets(0, 0, 5, 5);
					gbc_lblSubme.gridx = 4;
					gbc_lblSubme.gridy = 4;
					getContentPane().add(lblSubme, gbc_lblSubme);
				}
				{
					spinnersubme = new JSpinner();
					spinnersubme.setToolTipText("Fractional pixel motion estimation level");
					GridBagConstraints gbc_spinnersubme = new GridBagConstraints();
					gbc_spinnersubme.fill = GridBagConstraints.HORIZONTAL;
					gbc_spinnersubme.gridwidth = 2;
					gbc_spinnersubme.insets = new Insets(0, 0, 5, 5);
					gbc_spinnersubme.gridx = 6;
					gbc_spinnersubme.gridy = 4;
					getContentPane().add(spinnersubme, gbc_spinnersubme);
				}
				{
					lblNewLabel = new JLabel("owf    ");
					lblNewLabel.setToolTipText("Frame-level parallelism ( 0 = auto)");
					GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
					gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
					gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
					gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
					gbc_lblNewLabel.gridx = 1;
					gbc_lblNewLabel.gridy = 5;
					getContentPane().add(lblNewLabel, gbc_lblNewLabel);
				}
				{
					spinnerofw = new JSpinner();
					spinnerofw.setToolTipText("Frame-level parallelism ( 0 = auto)");
					GridBagConstraints gbc_spinnerofw = new GridBagConstraints();
					gbc_spinnerofw.gridwidth = 2;
					gbc_spinnerofw.fill = GridBagConstraints.HORIZONTAL;
					gbc_spinnerofw.insets = new Insets(0, 0, 5, 5);
					gbc_spinnerofw.gridx = 2;
					gbc_spinnerofw.gridy = 5;
					getContentPane().add(spinnerofw, gbc_spinnerofw);
				}
				{
					chckbxWpp = new JCheckBox("wpp        ");
					chckbxWpp.setToolTipText("Wavefront parallel processing");
					GridBagConstraints gbc_chckbxWpp = new GridBagConstraints();
					gbc_chckbxWpp.gridwidth = 4;
					gbc_chckbxWpp.insets = new Insets(0, 0, 5, 5);
					gbc_chckbxWpp.gridx = 4;
					gbc_chckbxWpp.gridy = 5;
					getContentPane().add(chckbxWpp, gbc_chckbxWpp);
				}
				{
					lblOtherCommands = new JLabel("<html><center>"+LangTools.getWordLanguage("Other", "general.other")+"<br>"+LangTools.getWordLanguage("Parameters", "general.parameters")+": </center></html>");
					lblOtherCommands.setToolTipText(
							"Other Kvazaar parameters, set a list of name=value pairs separated by commas (,)");
					GridBagConstraints gbc_lblOtherCommands = new GridBagConstraints();
					gbc_lblOtherCommands.anchor = GridBagConstraints.EAST;
					gbc_lblOtherCommands.gridwidth = 2;
					gbc_lblOtherCommands.insets = new Insets(0, 0, 5, 5);
					gbc_lblOtherCommands.gridx = 0;
					gbc_lblOtherCommands.gridy = 6;
					getContentPane().add(lblOtherCommands, gbc_lblOtherCommands);
				}
				{
					textField = new CopyPasteJTextField();
					textField.setToolTipText("Other Kvazaar parameters");
					GridBagConstraints gbc_textField = new GridBagConstraints();
					gbc_textField.gridwidth = 8;
					gbc_textField.insets = new Insets(0, 0, 5, 5);
					gbc_textField.fill = GridBagConstraints.HORIZONTAL;
					gbc_textField.gridx = 2;
					gbc_textField.gridy = 6;
					getContentPane().add(textField, gbc_textField);
					textField.setColumns(10);
				}

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
					getContentPane().add(jButtonok, new GridBagConstraints(5, 7, 6, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					jButtonok.setText(LangTools.getResourceBundleWordLanguage(rb, "Save", "general.save"));
					jButtonok.setActionCommand(SAVECLOSE);
					jButtonok.addActionListener(this);
				}

			}
			{
				this.setSize(501, 348);
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
		}
	}

	/**
	 * Save choosen options to container.
	 */
	private void saveChoosenOptionsToContainer() {
		infocont.setPreset((X26xPresets) jComboBoxpreset.getSelectedItem());
		infocont.setLevel((VideoLevel) jComboBoxlevel.getSelectedItem());
		infocont.setOwf((int) spinnerofw.getValue());
		infocont.setPeriod((int) spinnerperiod.getValue());
		infocont.setTiles((int) spinnertiles.getValue());
		infocont.setRef((int) spinnerref.getValue());
		infocont.setSubme((int) spinnersubme.getValue());
		infocont.setWpp(chckbxWpp.isSelected());
		infocont.setOthercmds(textField.getText().isEmpty() ? null : textField.getText());
	}

	private void setInfoFromEncodingContainer() {
		jComboBoxpreset.setSelectedItem(infocont.getPreset());
		jComboBoxlevel.setSelectedItem(infocont.getLevel());
		spinnerofw.setValue(infocont.getOwf());
		spinnerperiod.setValue(infocont.getPeriod());
		spinnertiles.setValue(infocont.getTiles());
		spinnerref.setValue(infocont.getRef());
		spinnersubme.setValue(infocont.getSubme());
		chckbxWpp.setSelected(infocont.isWpp());
		if (infocont.getOthercmds() != null)
			textField.setText(infocont.getOthercmds());
	}

}
