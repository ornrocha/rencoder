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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.H265Tune;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.PixelFormatH265;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.ProfilesH265;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.X26xPresets;
import pt.ornrocha.rencoder.gui.components.panels.auxiliar.CopyPasteJTextField;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoH265EncodingInfoContainer;

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
public class H265ConfigurationsPanel extends JDialog implements ActionListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The j labelpreset. */
	private JLabel jLabelpreset;

	/** The j buttonok. */
	private JButton jButtonok;

	/** The j buttonclose. */
	private JButton jButtonclose;

	/** The j combo boxprofile. */
	private JComboBox<H265Tune> jComboBoxtune;

	/** The j labelprofile. */
	private JLabel jLabeltune;

	/** The j combo boxpixelformat. */
	private JComboBox<PixelFormatH265> jComboBoxpixelformat;

	/** The j labelpixelformat. */
	private JLabel jLabelpixelformat;

	/** The j combo boxpreset. */
	private JComboBox<X26xPresets> jComboBoxpreset;

	/** The close. */
	private static String CLOSE = "close";

	/** The saveclose. */
	private static String SAVECLOSE = "saveclose";

	/** The infocont. */
	private VideoH265EncodingInfoContainer infocont = null;

	/** The rb. */
	private ResourceBundle rb;
	private JLabel lblProfile;
	private JComboBox<ProfilesH265> comboBoxprofile;
	private JLabel lblothercommands;
	private JTextField textFieldcmds;

	/**
	 * Instantiates a new h264 configurations panel.
	 *
	 * @param infocont the infocont
	 */
	public H265ConfigurationsPanel(VideoH265EncodingInfoContainer infocont) {
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
		jComboBoxpixelformat.setModel(new DefaultComboBoxModel<>(PixelFormatH265.values()));
		jComboBoxtune.setModel(new DefaultComboBoxModel<>(H265Tune.values()));
		comboBoxprofile.setModel(new DefaultComboBoxModel<>(ProfilesH265.values()));
	}

	/**
	 * Sets the info from encoding container.
	 */
	private void setInfoFromEncodingContainer() {

		jComboBoxpixelformat.setSelectedItem(infocont.getPixelformat());
		jComboBoxpreset.setSelectedItem(infocont.getPreset());
		jComboBoxtune.setSelectedItem(infocont.getTune());
		comboBoxprofile.setSelectedItem(infocont.getProfile());
		if (infocont.getOthercmds() != null)
			textFieldcmds.setText(infocont.getOthercmds());

	}

	/**
	 * Inits the gui.
	 */
	private void initGUI() {
		try {
			{
				GridBagLayout thisLayout = new GridBagLayout();
				thisLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.0, 0.1, 0.1 };
				thisLayout.rowHeights = new int[] { 7, 7, 7, 7, 7, 7, 7, 7 };
				thisLayout.columnWeights = new double[] { 0.0, 0.0, 0.1, 0.1, 0.0, 0.0, 0.1, 0.1, 0.1, 0.1 };

				thisLayout.columnWidths = new int[] { 7, 7, 7, 7, 7, 7, 7, 7, 7, 7 };
				getContentPane().setLayout(thisLayout);
				this.setTitle(LangTools.getResourceBundleWordLanguage(rb, "H265 codec settings", "x265gui.confpanel"));
				{
					jLabelpreset = new JLabel();
					jLabelpreset.setToolTipText("Set the x265 preset. ");
					getContentPane().add(jLabelpreset, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
							GridBagConstraints.EAST, GridBagConstraints.VERTICAL, new Insets(0, 10, 5, 5), 0, 0));
					jLabelpreset.setText(LangTools.getResourceBundleWordLanguage(rb, "Preset", "general.preset"));
				}
				{

					jComboBoxpreset = new JComboBox<X26xPresets>();
					jComboBoxpreset.setToolTipText("Set the x265 preset. ");
					getContentPane().add(jComboBoxpreset, new GridBagConstraints(1, 1, 8, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));

				}

				{
					jLabelpixelformat = new JLabel();
					getContentPane().add(jLabelpixelformat, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
							GridBagConstraints.EAST, GridBagConstraints.VERTICAL, new Insets(0, 10, 5, 5), 0, 0));
					jLabelpixelformat.setText(
							LangTools.getResourceBundleWordLanguage(rb, "Pixel Format", "general.pixelformat"));
				}
				{

					jComboBoxpixelformat = new JComboBox<PixelFormatH265>();
					getContentPane().add(jComboBoxpixelformat, new GridBagConstraints(1, 2, 8, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));

				}
				{
					jLabeltune = new JLabel();
					jLabeltune.setToolTipText("Set the x265 tune parameter. ");
					getContentPane().add(jLabeltune, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
							GridBagConstraints.EAST, GridBagConstraints.VERTICAL, new Insets(0, 10, 5, 5), 0, 0));
					jLabeltune.setText("Tune");
				}
				{

					jComboBoxtune = new JComboBox<H265Tune>();
					jComboBoxtune.setToolTipText("Set the x265 tune parameter. ");
					getContentPane().add(jComboBoxtune, new GridBagConstraints(1, 3, 8, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));

				}
				{
					lblProfile = new JLabel("Profile");
					lblProfile.setToolTipText("Set profile restrictions. ");
					GridBagConstraints gbc_lblProfile = new GridBagConstraints();
					gbc_lblProfile.anchor = GridBagConstraints.EAST;
					gbc_lblProfile.insets = new Insets(0, 0, 5, 5);
					gbc_lblProfile.gridx = 0;
					gbc_lblProfile.gridy = 4;
					getContentPane().add(lblProfile, gbc_lblProfile);
				}
				{
					comboBoxprofile = new JComboBox<>();
					comboBoxprofile.setToolTipText("Set profile restrictions. ");
					GridBagConstraints gbc_comboBoxprofile = new GridBagConstraints();
					gbc_comboBoxprofile.gridwidth = 8;
					gbc_comboBoxprofile.insets = new Insets(0, 0, 5, 5);
					gbc_comboBoxprofile.fill = GridBagConstraints.HORIZONTAL;
					gbc_comboBoxprofile.gridx = 1;
					gbc_comboBoxprofile.gridy = 4;
					getContentPane().add(comboBoxprofile, gbc_comboBoxprofile);
				}
				{
					lblothercommands = new JLabel("<html><center>"+LangTools.getWordLanguage("Other", "general.other")+"<br>"+LangTools.getWordLanguage("Parameters", "general.parameters")+": </center></html>");
					lblothercommands
							.setToolTipText("Set x265 options using a list of key=value couples separated by \":\"");
					GridBagConstraints gbc_lblothercommands = new GridBagConstraints();
					gbc_lblothercommands.anchor = GridBagConstraints.EAST;
					gbc_lblothercommands.insets = new Insets(0, 0, 5, 5);
					gbc_lblothercommands.gridx = 0;
					gbc_lblothercommands.gridy = 5;
					getContentPane().add(lblothercommands, gbc_lblothercommands);
				}
				{
					textFieldcmds = new CopyPasteJTextField();
					textFieldcmds
							.setToolTipText("Set x265 options using a list of key=value couples separated by \":\"");
					GridBagConstraints gbc_textFieldcomds = new GridBagConstraints();
					gbc_textFieldcomds.gridwidth = 8;
					gbc_textFieldcomds.insets = new Insets(0, 0, 5, 5);
					gbc_textFieldcomds.fill = GridBagConstraints.BOTH;
					gbc_textFieldcomds.gridx = 1;
					gbc_textFieldcomds.gridy = 5;
					getContentPane().add(textFieldcmds, gbc_textFieldcomds);
					textFieldcmds.setColumns(10);
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
				this.setSize(501, 348);
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

		infocont.setPreset((X26xPresets) jComboBoxpreset.getSelectedItem());
		infocont.setPixelformat((PixelFormatH265) jComboBoxpixelformat.getSelectedItem());
		infocont.setTune((H265Tune) jComboBoxtune.getSelectedItem());
		infocont.setProfile((ProfilesH265) comboBoxprofile.getSelectedItem());
		if (!textFieldcmds.getText().isEmpty())
			infocont.setOthercmds(textFieldcmds.getText());

	}

}
