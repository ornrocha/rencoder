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
package pt.ornrocha.rencoder.gui.components.panels.Scroll.generalencodingconfig;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import pt.ornrocha.rencoder.gui.components.panels.Scroll.VideoConfigScrollPanel;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.helpers.props.fields.StaticGlobalFields;
import pt.ornrocha.rencoder.helpers.props.managers.auxiliar.EncodingPropsAuxiliar;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;

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
public class GeneralVideoConfigScrollPanel extends VideoConfigScrollPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The numberprocesses. */
	private JSpinner numberprocesses = null;

	/** The ffmpegthreads. */
	private JSpinner ffmpegthreads;

	/** The j labelnumberproc. */
	private JLabel jLabelnumberproc;

	/** The j labelffmpegthreads. */
	private JLabel jLabelffmpegthreads;
	
	private JLabel maxmuxsizelabel;
	
	private JSpinner maxmuxsizespinner;

	/**
	 * Instantiates a new general video config scroll panel.
	 */
	public GeneralVideoConfigScrollPanel(JFrame mainframe) {
		super(mainframe);
		addnewcomponents();
		InitializeNewComponents();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gui.components.panels.Scroll.VideoConfigScrollPanel#setMainGridBagLayout()
	 */
	@Override
	protected void setMainGridBagLayout() {

		jPanelmain = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		layout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1,
				0.1 };
		layout.rowHeights = new int[] { 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7 };
		layout.columnWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1 };
		layout.columnWidths = new int[] { 7, 7, 7, 7, 7, 7, 7, 7, 7, 7 };
		jPanelmain.setLayout(layout);
		this.setViewportView(jPanelmain);

	}

	/**
	 * Add new components.
	 */
	protected void addnewcomponents() {

		{
			jLabelffmpegthreads = new JLabel();
			jPanelmain.add(jLabelffmpegthreads, new GridBagConstraints(0, 10, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			jLabelffmpegthreads.setText(
					LangTools.getResourceBundleWordLanguage(rb, "FFmpeg threads", "videoaudiogui.ffmpegthreads"));
		}
		{
			ffmpegthreads = new JSpinner();
			jPanelmain.add(ffmpegthreads, new GridBagConstraints(2, 10, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			jLabelnumberproc = new JLabel();
			jPanelmain.add(jLabelnumberproc, new GridBagConstraints(0, 11, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			jLabelnumberproc.setText(LangTools.getResourceBundleWordLanguage(rb, "Simultaneous conversions",
					"videoaudiogui.simultaneousconversions"));
		}
		{
			numberprocesses = new JSpinner();
			jPanelmain.add(numberprocesses, new GridBagConstraints(2, 11, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(5, 0, 0, 0), 0, 0));
		}
		{
			maxmuxsizelabel = new JLabel();
			jPanelmain.add(maxmuxsizelabel, new GridBagConstraints(0, 12, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			maxmuxsizelabel.setText(LangTools.getResourceBundleWordLanguage(rb, "Max. muxing queue size",
					"videoaudiogui.maxmuxingsize"));
		}
		{
			maxmuxsizespinner = new JSpinner();
			jPanelmain.add(maxmuxsizespinner, new GridBagConstraints(2, 12, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(5, 0, 0, 0), 0, 0));
		}
	}

	/**
	 * Initialize new components.
	 */
	protected void InitializeNewComponents() {
		int cores = Runtime.getRuntime().availableProcessors();

		SpinnerNumberModel modelnumberproc = new SpinnerNumberModel(1, 1, cores, 1);
		numberprocesses.setModel(modelnumberproc);
		
		SpinnerNumberModel modelmaxmuxsize = new SpinnerNumberModel(0, 0, 9999, 100);
		maxmuxsizespinner.setModel(modelmaxmuxsize);

		SpinnerNumberModel modelffmpegthreads = new SpinnerNumberModel(0, 0, cores, 1);
		ffmpegthreads.setModel(modelffmpegthreads);
		ffmpegthreads.setToolTipText(LangTools.getResourceBundleWordLanguage(rb,
				"0 value -> uses all available threads", "tip.ffmpegthreads"));
	}

	/**
	 * Save number simultaneous processes.
	 */
	public void saveConfigurationsToFile() {
		PropertiesWorker.ChangePropertiesParam(StaticGlobalFields.RENCODERCONFIGFILE,
				StaticGlobalFields.NUMBERSIMULTANEOUSCONVERSIONS, String.valueOf(numberprocesses.getValue()));
		
		PropertiesWorker.ChangePropertiesParam(StaticGlobalFields.RENCODERCONFIGFILE,
				StaticGlobalFields.MAXMUXINGQUEUESIZE, String.valueOf(maxmuxsizespinner.getValue()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gui.components.panels.Scroll.VideoConfigScrollPanel#
	 * setParametersInsideEncodingInfoContainer()
	 */
	@Override
	protected void setParametersInsideEncodingInfoContainer() {
		super.setParametersInsideEncodingInfoContainer();
		if (steelCheckBoxquality.isSelected())
			setValueIntoSlider();
		ffmpegthreads.setValue(infocont.getNumberThreads());
		numberprocesses.setValue(EncodingPropsAuxiliar.getNumberSimultaneousConversions());
		maxmuxsizespinner.setValue(EncodingPropsAuxiliar.getMaxMuxingQueueSize());
		setOutputFormat();
		jComboBoxcontainer.setSelectedItem(infocont.getVideoContainer());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gui.components.panels.Scroll.VideoConfigScrollPanel#
	 * saveSelectedInformationIntoContainer()
	 */
	@Override
	public void saveSelectedInformationIntoContainer() {
		super.saveSelectedInformationIntoContainer();
		infocont.setNumberThreads((int) ffmpegthreads.getValue());
		infocont.setMaxMuxingQueueSize((int) maxmuxsizespinner.getValue());

	}

}
