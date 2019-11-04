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

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.HEVCVaapiProfile;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoLevel;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoHEVCVaapiEncodingInfoContainer;

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
public class HEVCVaapiConfigurationsPanel extends JDialog implements ActionListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The j labelpreset. */
	private JLabel jLabelprofile;

	/** The j buttonok. */
	private JButton jButtonok;

	/** The j buttonclose. */
	private JButton jButtonclose;

	/** The j combo boxpixelformat. */
	private JComboBox<VideoLevel> jComboBoxlevel;

	/** The j labelpixelformat. */
	private JLabel jLabellevel;

	/** The j combo boxpreset. */
	private JComboBox<HEVCVaapiProfile> jComboBoxprofile;

	/** The close. */
	private static String CLOSE = "close";

	/** The saveclose. */
	private static String SAVECLOSE = "saveclose";

	/** The infocont. */
	private VideoHEVCVaapiEncodingInfoContainer infocont = null;

	/** The rb. */
	private ResourceBundle rb;
	private JLabel lbltier;
	private JSpinner spinnertier;
	private JCheckBox chckbxlowpower;
	private JCheckBox chckbxAud;

	/**
	 * Instantiates a new h264 configurations panel.
	 *
	 * @param infocont the infocont
	 */
	public HEVCVaapiConfigurationsPanel(VideoHEVCVaapiEncodingInfoContainer infocont) {
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

		jComboBoxprofile.setModel(new DefaultComboBoxModel<>(HEVCVaapiProfile.values()));
		jComboBoxlevel.setModel(new DefaultComboBoxModel<>(VideoLevel.getH264VaapiLevels()));
		SpinnerNumberModel modeltier = new SpinnerNumberModel(0, 0, 1, 1);
		spinnertier.setModel(modeltier);
		chckbxlowpower.setSelected(false);
		chckbxAud.setSelected(false);
	}

	/**
	 * Sets the info from encoding container.
	 */
	private void setInfoFromEncodingContainer() {

		jComboBoxlevel.setSelectedItem(infocont.getLevel());
		jComboBoxprofile.setSelectedItem(infocont.getProfile());
		spinnertier.setValue(infocont.getTier());
		chckbxlowpower.setSelected(infocont.isLowpower());
		chckbxAud.setSelected(infocont.isAud());
	}

	/**
	 * Inits the gui.
	 */
	private void initGUI() {
		try {
			{
				GridBagLayout thisLayout = new GridBagLayout();
				thisLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				thisLayout.rowHeights = new int[] { 7, 7, 7, 7, 7, 7 };
				thisLayout.columnWeights = new double[] { 0.0, 0.0, 0.1, 0.1, 0.0, 0.0, 0.1, 0.1, 0.1, 0.1 };

				thisLayout.columnWidths = new int[] { 7, 7, 7, 7, 7, 7, 7, 7, 7, 7 };
				getContentPane().setLayout(thisLayout);
				this.setTitle(LangTools.getResourceBundleWordLanguage(rb, "H264 Vaapi codec settings", "h264vaapi.confpanel"));
				{
					jLabelprofile = new JLabel();
					jLabelprofile.setToolTipText("");
					getContentPane().add(jLabelprofile, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
							GridBagConstraints.EAST, GridBagConstraints.VERTICAL, new Insets(0, 10, 5, 5), 0, 0));
					jLabelprofile.setText("Profile");
				}
				{

					jComboBoxprofile = new JComboBox<HEVCVaapiProfile>();
				
					getContentPane().add(jComboBoxprofile, new GridBagConstraints(1, 1, 8, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));

				}

				{
					jLabellevel = new JLabel();
					getContentPane().add(jLabellevel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
							GridBagConstraints.EAST, GridBagConstraints.VERTICAL, new Insets(0, 10, 5, 5), 0, 0));
					jLabellevel.setText(
							"Level");
				}
				{

					jComboBoxlevel = new JComboBox<VideoLevel>();
					getContentPane().add(jComboBoxlevel, new GridBagConstraints(1, 2, 8, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));

				}
				{
					lbltier = new JLabel("Tier");
					GridBagConstraints gbc_lbltier = new GridBagConstraints();
					gbc_lbltier.anchor = GridBagConstraints.EAST;
					gbc_lbltier.insets = new Insets(0, 0, 5, 5);
					gbc_lbltier.gridx = 0;
					gbc_lbltier.gridy = 3;
					getContentPane().add(lbltier, gbc_lbltier);
				}
				{
					spinnertier = new JSpinner();
					spinnertier.setToolTipText("Set encode quality (trades off against speed, higher is faster)");
					GridBagConstraints gbc_spinnertier = new GridBagConstraints();
					gbc_spinnertier.fill = GridBagConstraints.HORIZONTAL;
					gbc_spinnertier.gridwidth = 3;
					gbc_spinnertier.insets = new Insets(0, 0, 5, 5);
					gbc_spinnertier.gridx = 1;
					gbc_spinnertier.gridy = 3;
					getContentPane().add(spinnertier, gbc_spinnertier);
				}
				{
					chckbxlowpower = new JCheckBox("Low_power");
					GridBagConstraints gbc_chckbxlowpower = new GridBagConstraints();
					gbc_chckbxlowpower.anchor = GridBagConstraints.WEST;
					gbc_chckbxlowpower.gridwidth = 2;
					gbc_chckbxlowpower.insets = new Insets(0, 0, 5, 5);
					gbc_chckbxlowpower.gridx = 1;
					gbc_chckbxlowpower.gridy = 4;
					getContentPane().add(chckbxlowpower, gbc_chckbxlowpower);
				}
				{
					chckbxAud = new JCheckBox("Aud");
					GridBagConstraints gbc_chckbxAud = new GridBagConstraints();
					gbc_chckbxAud.anchor = GridBagConstraints.WEST;
					gbc_chckbxAud.gridwidth = 2;
					gbc_chckbxAud.insets = new Insets(0, 0, 5, 5);
					gbc_chckbxAud.gridx = 1;
					gbc_chckbxAud.gridy = 5;
					getContentPane().add(chckbxAud, gbc_chckbxAud);
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
					getContentPane().add(jButtonok, new GridBagConstraints(5, 7, 5, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					jButtonok.setText(LangTools.getResourceBundleWordLanguage(rb, "Save", "general.save"));
					jButtonok.setActionCommand(SAVECLOSE);
					jButtonok.addActionListener(this);
				}

			}
			{
				this.setSize(493, 270);
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
	 * Save choosen options to container.
	 */
	private void saveChoosenOptionsToContainer() {

		infocont.setProfile((HEVCVaapiProfile) jComboBoxprofile.getSelectedItem());
		infocont.setLevel((VideoLevel) jComboBoxlevel.getSelectedItem());
		infocont.setTier((int) spinnertier.getValue());
		infocont.setLowpower(chckbxlowpower.isSelected());
		infocont.setAud(chckbxAud.isSelected());

	}

}
