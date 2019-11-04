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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.PixelFormatAV1;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoAV1EncodingInfoContainer;

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
public class AV1ConfigurationsPanel extends JDialog implements ActionListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The j labelpreset. */
	private JLabel jLabelPixelformat;

	/** The j buttonok. */
	private JButton jButtonok;

	/** The j buttonclose. */
	private JButton jButtonclose;

	// private JTextField jTextFieldffmegcommand;

	/** The j labelprofile. */
	private JLabel jLabellaginframes;

	/** The j combo boxpreset. */
	private JComboBox<PixelFormatAV1> jComboBoxpixelformat;

	/** The close. */
	private static String CLOSE = "close";

	/** The saveclose. */
	private static String SAVECLOSE = "saveclose";

	/** The infocont. */
	private VideoAV1EncodingInfoContainer infocont = null;

	/** The rb. */
	private ResourceBundle rb;
	private JSpinner spinnerlaginframes;
	private JLabel lbltilecolumns;
	private JSpinner spinnertilecolumns;
	private JLabel lblstaticthresh;
	private JSpinner spinnerrstaticthresh;
	private JLabel lbltiles;
	private JSpinner spinnertiles;
	private JLabel lblcpuused;
	private JSpinner spinnercpuused;
	private JLabel lblTilerows;
	private JSpinner spinnertilerows;
	private JLabel lblNoisesensitivity;
	private JSpinner spinnerNoisesensitivity;

	/**
	 * Instantiates a new h264 configurations panel.
	 *
	 * @param infocont the infocont
	 */
	public AV1ConfigurationsPanel(VideoAV1EncodingInfoContainer infocont) {
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
		jComboBoxpixelformat.setModel(new DefaultComboBoxModel<>(PixelFormatAV1.values()));
		SpinnerNumberModel modelotilecolumns = new SpinnerNumberModel(-1, -1, 6, 1);
		spinnertilecolumns.setModel(modelotilecolumns);
		SpinnerNumberModel modelotilerows = new SpinnerNumberModel(-1, -1, 6, 1);
		spinnertilerows.setModel(modelotilerows);
		SpinnerNumberModel modellaginframes= new SpinnerNumberModel(-1, -1, Integer.MAX_VALUE, 1);
		spinnerlaginframes.setModel(modellaginframes);
		SpinnerNumberModel modelcpuused = new SpinnerNumberModel(1, 0, 8, 1);
		spinnercpuused.setModel(modelcpuused);
		SpinnerNumberModel modelstaticthresh = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
		spinnerrstaticthresh.setModel(modelstaticthresh);
		SpinnerNumberModel modeltiles = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
		spinnertiles.setModel(modeltiles);
		SpinnerNumberModel modelnoise = new SpinnerNumberModel(0, 0, 4, 1);
		spinnerNoisesensitivity.setModel(modelnoise );
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
				this.setTitle(LangTools.getWordLanguage("AV1 codec settings", "av1.confpanel"));
				{
					jLabelPixelformat = new JLabel();
					getContentPane().add(jLabelPixelformat, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0,
							GridBagConstraints.EAST, GridBagConstraints.VERTICAL, new Insets(0, 10, 5, 5), 0, 0));
					jLabelPixelformat.setText("Pixel Format");
				}
				{

					jComboBoxpixelformat = new JComboBox<PixelFormatAV1>();
					getContentPane().add(jComboBoxpixelformat, new GridBagConstraints(2, 1, 8, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));

				}
				{
					lblcpuused = new JLabel("cpu-used");
					lblcpuused.setToolTipText("Quality/Speed ratio modifier");
					GridBagConstraints gbc_lblcpuused = new GridBagConstraints();
					gbc_lblcpuused.anchor = GridBagConstraints.EAST;
					gbc_lblcpuused.insets = new Insets(0, 0, 5, 5);
					gbc_lblcpuused.gridx = 1;
					gbc_lblcpuused.gridy = 2;
					getContentPane().add(lblcpuused, gbc_lblcpuused);
				}
				{
					spinnercpuused = new JSpinner();
					spinnercpuused.setToolTipText("Quality/Speed ratio modifier");
					GridBagConstraints gbc_spinnercpuused = new GridBagConstraints();
					gbc_spinnercpuused.gridwidth = 2;
					gbc_spinnercpuused.fill = GridBagConstraints.HORIZONTAL;
					gbc_spinnercpuused.insets = new Insets(0, 0, 5, 5);
					gbc_spinnercpuused.gridx = 2;
					gbc_spinnercpuused.gridy = 2;
					getContentPane().add(spinnercpuused, gbc_spinnercpuused);
				}
				{
					jLabellaginframes = new JLabel();
					jLabellaginframes.setToolTipText("Number of frames to look ahead at for alternate reference frame selection");
					getContentPane().add(jLabellaginframes, new GridBagConstraints(4, 2, 2, 1, 0.0, 0.0,
							GridBagConstraints.EAST, GridBagConstraints.VERTICAL, new Insets(0, 10, 5, 5), 0, 0));
					jLabellaginframes.setText("lag-in-frames");
				}
				{
					spinnerlaginframes = new JSpinner();
					spinnerlaginframes.setToolTipText("Number of frames to look ahead at for alternate reference frame selection");
					GridBagConstraints gbc_spinnerlaginframes = new GridBagConstraints();
					gbc_spinnerlaginframes.fill = GridBagConstraints.HORIZONTAL;
					gbc_spinnerlaginframes.gridwidth = 2;
					gbc_spinnerlaginframes.insets = new Insets(0, 0, 5, 5);
					gbc_spinnerlaginframes.gridx = 6;
					gbc_spinnerlaginframes.gridy = 2;
					getContentPane().add(spinnerlaginframes, gbc_spinnerlaginframes);
				}
				{
					lblstaticthresh = new JLabel("static-thresh");
					lblstaticthresh.setToolTipText("A change threshold on blocks below which they will be skipped by the encoder");
					GridBagConstraints gbc_lblstaticthresh = new GridBagConstraints();
					gbc_lblstaticthresh.anchor = GridBagConstraints.EAST;
					gbc_lblstaticthresh.insets = new Insets(0, 0, 5, 5);
					gbc_lblstaticthresh.gridx = 1;
					gbc_lblstaticthresh.gridy = 3;
					getContentPane().add(lblstaticthresh, gbc_lblstaticthresh);
				}
				{
					spinnerrstaticthresh = new JSpinner();
					spinnerrstaticthresh.setToolTipText("A change threshold on blocks below which they will be skipped by the encoder");
					GridBagConstraints gbc_spinnerrstaticthresh = new GridBagConstraints();
					gbc_spinnerrstaticthresh.gridwidth = 2;
					gbc_spinnerrstaticthresh.fill = GridBagConstraints.HORIZONTAL;
					gbc_spinnerrstaticthresh.insets = new Insets(0, 0, 5, 5);
					gbc_spinnerrstaticthresh.gridx = 2;
					gbc_spinnerrstaticthresh.gridy = 3;
					getContentPane().add(spinnerrstaticthresh, gbc_spinnerrstaticthresh);
				}
				{
					lbltiles = new JLabel("tiles");
					lbltiles.setToolTipText("Tile columns x rows");
					GridBagConstraints gbc_lbltiles = new GridBagConstraints();
					gbc_lbltiles.gridwidth = 2;
					gbc_lbltiles.anchor = GridBagConstraints.EAST;
					gbc_lbltiles.insets = new Insets(0, 0, 5, 5);
					gbc_lbltiles.gridx = 4;
					gbc_lbltiles.gridy = 3;
					getContentPane().add(lbltiles, gbc_lbltiles);
				}
				{
					spinnertiles = new JSpinner();
					spinnertiles.setToolTipText("Tile columns x rows");
					GridBagConstraints gbc_spinnertiles = new GridBagConstraints();
					gbc_spinnertiles.fill = GridBagConstraints.HORIZONTAL;
					gbc_spinnertiles.gridwidth = 2;
					gbc_spinnertiles.insets = new Insets(0, 0, 5, 5);
					gbc_spinnertiles.gridx = 6;
					gbc_spinnertiles.gridy = 3;
					getContentPane().add(spinnertiles, gbc_spinnertiles);
				}
				{
					lbltilecolumns = new JLabel("tile-columns ");
					lbltilecolumns.setToolTipText("Log2 of number of tile columns to use ");
					GridBagConstraints gbc_lbltilecolumns = new GridBagConstraints();
					gbc_lbltilecolumns.anchor = GridBagConstraints.EAST;
					gbc_lbltilecolumns.fill = GridBagConstraints.VERTICAL;
					gbc_lbltilecolumns.insets = new Insets(0, 0, 5, 5);
					gbc_lbltilecolumns.gridx = 1;
					gbc_lbltilecolumns.gridy = 4;
					getContentPane().add(lbltilecolumns, gbc_lbltilecolumns);
				}
				{
					spinnertilecolumns = new JSpinner();
					spinnertilecolumns.setToolTipText("Log2 of number of tile columns to use ");
					GridBagConstraints gbc_spinnertilecolumns = new GridBagConstraints();
					gbc_spinnertilecolumns.gridwidth = 2;
					gbc_spinnertilecolumns.fill = GridBagConstraints.HORIZONTAL;
					gbc_spinnertilecolumns.insets = new Insets(0, 0, 5, 5);
					gbc_spinnertilecolumns.gridx = 2;
					gbc_spinnertilecolumns.gridy = 4;
					getContentPane().add(spinnertilecolumns, gbc_spinnertilecolumns);
				}
				{
					lblTilerows = new JLabel("tile-rows");
					lblTilerows.setToolTipText("Log2 of number of tile rows to use");
					GridBagConstraints gbc_lblTilerows = new GridBagConstraints();
					gbc_lblTilerows.anchor = GridBagConstraints.EAST;
					gbc_lblTilerows.gridwidth = 2;
					gbc_lblTilerows.insets = new Insets(0, 0, 5, 5);
					gbc_lblTilerows.gridx = 4;
					gbc_lblTilerows.gridy = 4;
					getContentPane().add(lblTilerows, gbc_lblTilerows);
				}
				{
					spinnertilerows = new JSpinner();
					spinnertilerows.setToolTipText("Log2 of number of tile rows to use");
					GridBagConstraints gbc_spinnertilerows = new GridBagConstraints();
					gbc_spinnertilerows.fill = GridBagConstraints.HORIZONTAL;
					gbc_spinnertilerows.gridwidth = 2;
					gbc_spinnertilerows.insets = new Insets(0, 0, 5, 5);
					gbc_spinnertilerows.gridx = 6;
					gbc_spinnertilerows.gridy = 4;
					getContentPane().add(spinnertilerows, gbc_spinnertilerows);
				}
				{
					lblNoisesensitivity = new JLabel("noise-sensitivity");
					lblNoisesensitivity.setToolTipText("Noise sensitivity");
					GridBagConstraints gbc_lblNoisesensitivity = new GridBagConstraints();
					gbc_lblNoisesensitivity.insets = new Insets(0, 0, 5, 5);
					gbc_lblNoisesensitivity.gridx = 1;
					gbc_lblNoisesensitivity.gridy = 5;
					getContentPane().add(lblNoisesensitivity, gbc_lblNoisesensitivity);
				}
				{
					spinnerNoisesensitivity = new JSpinner();
					spinnerNoisesensitivity.setToolTipText("Noise sensitivity");
					GridBagConstraints gbc_spinnerNoisesensitivity = new GridBagConstraints();
					gbc_spinnerNoisesensitivity.fill = GridBagConstraints.HORIZONTAL;
					gbc_spinnerNoisesensitivity.gridwidth = 2;
					gbc_spinnerNoisesensitivity.insets = new Insets(0, 0, 5, 5);
					gbc_spinnerNoisesensitivity.gridx = 2;
					gbc_spinnerNoisesensitivity.gridy = 5;
					getContentPane().add(spinnerNoisesensitivity, gbc_spinnerNoisesensitivity);
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
				this.setSize(579, 414);
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
		infocont.setPixformat((PixelFormatAV1) jComboBoxpixelformat.getSelectedItem());
		infocont.setTilecolumns((int) spinnertilecolumns.getValue());
		infocont.setTilerows((int) spinnertilerows.getValue());
		infocont.setLaginframes((int) spinnerlaginframes.getValue());
		infocont.setCpuused((int) spinnercpuused.getValue());
		infocont.setStaticthresh((int) spinnerrstaticthresh.getValue());
		infocont.setTiles((int) spinnertiles.getValue());
		infocont.setNoisesensitivity((int) spinnerNoisesensitivity.getValue());

	}

	private void setInfoFromEncodingContainer() {
		jComboBoxpixelformat.setSelectedItem(infocont.getPixformat());
		spinnertilecolumns.setValue(infocont.getTilecolumns());
		spinnertilerows.setValue(infocont.getTilerows());
		spinnerlaginframes.setValue(infocont.getLaginframes());
		spinnercpuused.setValue(infocont.getCpuused());
		spinnerrstaticthresh.setValue(infocont.getStaticthresh());
		spinnertiles.setValue(infocont.getTiles());
		spinnerNoisesensitivity.setValue(infocont.getNoisesensitivity());
	}

}
