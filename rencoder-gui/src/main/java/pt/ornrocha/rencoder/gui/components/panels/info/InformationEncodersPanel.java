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
package pt.ornrocha.rencoder.gui.components.panels.info;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.configurations.FFmpegParametersChecker;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.general.EncoderType;
import pt.ornrocha.rencoder.gui.components.tables.GenericTableViewerModel;
import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.helpers.lang.LangTools;

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
public class InformationEncodersPanel extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The j tabbed panemain. */
	private JTabbedPane jTabbedPanemain;

	/** The j scroll panesubsencinfo. */
	private JScrollPane jScrollPanesubsencinfo;

	/** The j scroll paneaudioencinfo. */
	private JScrollPane jScrollPaneaudioencinfo;

	/** The j scroll panevideoencinfo. */
	private JScrollPane jScrollPanevideoencinfo;

	/** The j buttonok. */
	private JButton jButtonok;

	/** The j panelsubtitles. */
	private JPanel jPanelsubtitles;

	/** The j panelaudio. */
	private JPanel jPanelaudio;

	/** The j panelvideo. */
	private JPanel jPanelvideo;

	/** The rb. */
	private ResourceBundle rb;

	/** The videoencoders. */
	private IndexedHashMap<String, String> videoencoders = null;

	/** The audioencoders. */
	private IndexedHashMap<String, String> audioencoders = null;

	/** The subsencoders. */
	private IndexedHashMap<String, String> subsencoders = null;

	/** The closeencodersinfopanel. */
	private static String CLOSEENCODERSINFOPANEL = "closeencodersinfopanel";

	/**
	 * Instantiates a new information encoders panel.
	 */
	public InformationEncodersPanel() {
		rb = ResourceBundle.getBundle("lang", LangTools.getDefinedLanguage(), LangTools.loadLanguagesPath());
		initGUI();
		this.setModal(true);
		videoencoders = FFmpegParametersChecker.getFFmpegEncoders(EncoderType.VIDEO);
		audioencoders = FFmpegParametersChecker.getFFmpegEncoders(EncoderType.AUDIO);
		subsencoders = FFmpegParametersChecker.getFFmpegEncoders(EncoderType.SUBTITLES);
		fillvideoencoderstable();
		fillaudioencoderstable();
		fillsubsencoderstable();
	}

	/**
	 * Fill video encoders table.
	 */
	private void fillvideoencoderstable() {
		String[] tableheader = { LangTools.getResourceBundleWordLanguage(rb, "Encoder", "ffmpegconfgui.encoder"),
				LangTools.getResourceBundleWordLanguage(rb, "Description", "ffmpegconfgui.description") };
		GenericTableViewerModel videoenctable = null;

		if (videoencoders != null) {

			ArrayList<Object[]> list = new ArrayList<>();
			for (int i = 0; i < videoencoders.size(); i++) {
				Object[] obj = new Object[2];

				obj[0] = videoencoders.getKeyAt(i);
				obj[1] = videoencoders.getValueAt(i);
				list.add(obj);
			}
			videoenctable = new GenericTableViewerModel(list, tableheader, false);

		} else
			videoenctable = new GenericTableViewerModel(tableheader, false);

		JTable table = new JTable();

		table.setModel(videoenctable);
		setColumnWidth(table);
		jScrollPanevideoencinfo.setViewportView(table);

	}

	/**
	 * Fill audio encoders table.
	 */
	private void fillaudioencoderstable() {
		String[] tableheader = { LangTools.getResourceBundleWordLanguage(rb, "Encoder", "ffmpegconfgui.encoder"),
				LangTools.getResourceBundleWordLanguage(rb, "Description", "ffmpegconfgui.description") };
		GenericTableViewerModel audioenctable = null;

		if (audioencoders != null) {

			ArrayList<Object[]> list = new ArrayList<>();
			for (int i = 0; i < audioencoders.size(); i++) {
				Object[] obj = new Object[2];

				obj[0] = audioencoders.getKeyAt(i);
				obj[1] = audioencoders.getValueAt(i);
				list.add(obj);
			}
			audioenctable = new GenericTableViewerModel(list, tableheader, false);

		} else
			audioenctable = new GenericTableViewerModel(tableheader, false);

		JTable table = new JTable();

		table.setModel(audioenctable);
		setColumnWidth(table);
		jScrollPaneaudioencinfo.setViewportView(table);

	}

	/**
	 * Fill subtitles encoders table.
	 */
	private void fillsubsencoderstable() {
		String[] tableheader = { LangTools.getResourceBundleWordLanguage(rb, "Encoder", "ffmpegconfgui.encoder"),
				LangTools.getResourceBundleWordLanguage(rb, "Description", "ffmpegconfgui.description") };
		GenericTableViewerModel subsenctable = null;

		if (subsencoders != null) {

			ArrayList<Object[]> list = new ArrayList<>();
			for (int i = 0; i < subsencoders.size(); i++) {
				Object[] obj = new Object[2];

				obj[0] = subsencoders.getKeyAt(i);
				obj[1] = subsencoders.getValueAt(i);
				list.add(obj);
			}
			subsenctable = new GenericTableViewerModel(list, tableheader, false);

		} else
			subsenctable = new GenericTableViewerModel(tableheader, false);

		JTable table = new JTable();

		table.setModel(subsenctable);
		setColumnWidth(table);
		jScrollPanesubsencinfo.setViewportView(table);

	}

	/**
	 * Sets the column width.
	 *
	 * @param table the new column width
	 */
	private void setColumnWidth(JTable table) {
		TableColumnModel model = table.getColumnModel();
		double factor = table.getPreferredScrollableViewportSize().getWidth();
		model.getColumn(0).setPreferredWidth((int) (factor * 0.2));
		model.getColumn(1).setPreferredWidth((int) (factor * 0.8));

	}

	/**
	 * Inits the gui.
	 */
	private void initGUI() {
		try {
			{
				GridBagLayout thisLayout = new GridBagLayout();
				thisLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1,
						0.1, 0.1 };
				thisLayout.rowHeights = new int[] { 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7 };
				thisLayout.columnWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1 };
				thisLayout.columnWidths = new int[] { 7, 7, 7, 7, 7, 7, 7, 7, 7, 7 };
				getContentPane().setLayout(thisLayout);
				this.setTitle(LangTools.getResourceBundleWordLanguage(rb, "FFmpeg encoders information/configuration",
						"ffmpegconfgui.panel"));
				{
					jTabbedPanemain = new JTabbedPane();
					getContentPane().add(jTabbedPanemain, new GridBagConstraints(0, 0, 10, 14, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					{
						jPanelvideo = new JPanel();
						GridBagLayout jPanelvideoLayout = new GridBagLayout();
						jTabbedPanemain.addTab(LangTools.getResourceBundleWordLanguage(rb, "Video", "general.video"),
								null, jPanelvideo, null);
						jPanelvideoLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1,
								0.1, 0.1, 0.1, 0.1 };
						jPanelvideoLayout.rowHeights = new int[] { 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7 };
						jPanelvideoLayout.columnWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1,
								0.1 };
						jPanelvideoLayout.columnWidths = new int[] { 7, 7, 7, 7, 7, 7, 7, 7, 7, 7 };
						jPanelvideo.setLayout(jPanelvideoLayout);
						{
							jScrollPanevideoencinfo = new JScrollPane();
							jPanelvideo.add(jScrollPanevideoencinfo, new GridBagConstraints(0, 0, 10, 13, 0.0, 0.0,
									GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
							jScrollPanevideoencinfo.setBorder(
									BorderFactory.createTitledBorder(LangTools.getResourceBundleWordLanguage(rb,
											"FFmpeg video encoders", "ffmpegconfgui.videoencoders")));
						}
					}
					{
						jPanelaudio = new JPanel();
						GridBagLayout jPanelaudioLayout = new GridBagLayout();
						jTabbedPanemain.addTab(LangTools.getResourceBundleWordLanguage(rb, "Audio", "general.audio"),
								null, jPanelaudio, null);
						jPanelaudioLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1,
								0.1, 0.1, 0.1, 0.1 };
						jPanelaudioLayout.rowHeights = new int[] { 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7 };
						jPanelaudioLayout.columnWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1,
								0.1 };
						jPanelaudioLayout.columnWidths = new int[] { 7, 7, 7, 7, 7, 7, 7, 7, 7, 7 };
						jPanelaudio.setLayout(jPanelaudioLayout);
						{
							jScrollPaneaudioencinfo = new JScrollPane();
							jPanelaudio.add(jScrollPaneaudioencinfo, new GridBagConstraints(0, 0, 10, 13, 0.0, 0.0,
									GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
							jScrollPaneaudioencinfo.setBorder(
									BorderFactory.createTitledBorder(LangTools.getResourceBundleWordLanguage(rb,
											"FFmpeg audio encoders", "ffmpegconfgui.audioencoders")));
						}
					}
					{
						jPanelsubtitles = new JPanel();
						GridBagLayout jPanelsubtitlesLayout = new GridBagLayout();
						jTabbedPanemain.addTab(
								LangTools.getResourceBundleWordLanguage(rb, "Subtitles", "general.subtitles"), null,
								jPanelsubtitles, null);
						jPanelsubtitlesLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1 };
						jPanelsubtitlesLayout.rowHeights = new int[] { 7, 7, 7, 7 };
						jPanelsubtitlesLayout.columnWeights = new double[] { 0.1, 0.1, 0.1, 0.1 };
						jPanelsubtitlesLayout.columnWidths = new int[] { 7, 7, 7, 7 };
						jPanelsubtitles.setLayout(jPanelsubtitlesLayout);
						{
							jScrollPanesubsencinfo = new JScrollPane();
							jPanelsubtitles.add(jScrollPanesubsencinfo, new GridBagConstraints(0, 0, 4, 4, 0.0, 0.0,
									GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
							jScrollPanesubsencinfo.setBorder(
									BorderFactory.createTitledBorder(LangTools.getResourceBundleWordLanguage(rb,
											"FFmpeg subtitles encoders", "ffmpegconfgui.subtitlesencoders")));
						}
					}
				}
				{
					jButtonok = new JButton();
					getContentPane().add(jButtonok, new GridBagConstraints(3, 14, 4, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jButtonok.setText(LangTools.getResourceBundleWordLanguage(rb, "Close", "general.close"));
					jButtonok.setActionCommand(CLOSEENCODERSINFOPANEL);
					jButtonok.addActionListener(this);

				}
			}
			{
				this.setSize(576, 441);
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
		String cmd = e.getActionCommand();

		if (cmd.equals(CLOSEENCODERSINFOPANEL)) {
			this.dispose();
		}

	}

}
