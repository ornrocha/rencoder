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
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.pmw.tinylog.Logger;

import eu.hansolo.custom.SteelCheckBox;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.PixelFormat;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoMPEG2EncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;

// TODO: Auto-generated Javadoc
/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class MPEG2ConfigurationsPanel extends JDialog implements ActionListener{


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The j labelcontainer. */
	private JLabel jLabelcontainer;
	
	/** The Check boxyestrellis. */
	private SteelCheckBox CheckBoxyestrellis;
	
	/** The Check boxpelemon. */
	private SteelCheckBox CheckBoxpelemon;
	
	/** The Check boxyesbframes. */
	private SteelCheckBox CheckBoxyesbframes;
	
	/** The j text fieldgopsize. */
	private JTextField jTextFieldgopsize;
	
	/** The Fieldgopsize. */
	private JSpinner Fieldgopsize;
	
	/** The j labelgop. */
	private JLabel jLabelgop;
	
	/** The j combo boxpixelformat. */
	private JComboBox jComboBoxpixelformat;
	
	/** The j labelpixelformat. */
	private JLabel jLabelpixelformat;
	
	/** The j buttonclose. */
	private JButton jButtonclose;
	
	/** The j buttonsaveclose. */
	private JButton jButtonsaveclose;

	/** The use gopsize. */
	private SteelCheckBox useGopsize;
	
	
	/** The CLOSEMPE g2 confpanel. */
	private static String CLOSEMPEG2CONFPANEL="closempeg2confpanel";
	
	/** The SAVEANDCLOSEMPE g2 confpanel. */
	private static String SAVEANDCLOSEMPEG2CONFPANEL="saveandclosempeg2confpanel";
	
	/** The usegopsizempeg. */
	private static String USEGOPSIZEMPEG="usegopsizempeg";


	
	/** The infocont. */
	private IGeneralVideoEncInfoContainer infocont=null;
	
	/** The rb. */
	private ResourceBundle rb;

	/**
	 * Instantiates a new MPEG2 configurations panel.
	 *
	 * @param infocont the infocont
	 */
	public MPEG2ConfigurationsPanel(IGeneralVideoEncInfoContainer infocont){
		rb = ResourceBundle.getBundle("lang",LangTools.getDefinedLanguage(), LangTools.loadLanguagesPath());
		this.infocont=infocont;
		initGUI();
		this.setModal(true);
		initializeComponents();
		setInfoFromEncodingContainer();
		
	}
	
	
	
	/**
	 * Initialize components.
	 */
	private void initializeComponents(){


		jComboBoxpixelformat.setModel(new DefaultComboBoxModel<>(PixelFormat.values()));
		jComboBoxpixelformat.setSelectedIndex(1);
		CheckBoxyesbframes.setSelected(true);
		CheckBoxyestrellis.setSelected(true);
		
		SpinnerNumberModel model = new SpinnerNumberModel(45, 0, 100, 1);
		Fieldgopsize.setModel(model);
		
		useGopsize.setColored(true);
		CheckBoxpelemon.setColored(true);
		CheckBoxyesbframes.setColored(true);
		CheckBoxyestrellis.setColored(true);
	}
	
	
	/**
	 * Sets the info from encoding container.
	 */
	private void setInfoFromEncodingContainer(){
		
		
		jComboBoxpixelformat.setSelectedItem(((VideoMPEG2EncodingInfoContainer)infocont).getPixelformat());
		
		CheckBoxyesbframes.setSelected(((VideoMPEG2EncodingInfoContainer)infocont).isUsebFrames());
	
	
		
		CheckBoxyestrellis.setSelected(((VideoMPEG2EncodingInfoContainer)infocont).isUseTrellis());
	
		
		CheckBoxpelemon.setSelected(((VideoMPEG2EncodingInfoContainer)infocont).isUsePelMe());

		
		int gopsize = ((VideoMPEG2EncodingInfoContainer)infocont).getGopsize();
		if(gopsize!=-1){
			useGopsize.setSelected(true);
			Fieldgopsize.setValue(gopsize);
		  }
		else{
			useGopsize.setSelected(false);
		}
		checkgopsizestate();
		
			
		
	}
	
	
	/**
	 * Inits the gui.
	 */
	private void initGUI() {
		try {
			{   
				
				GridBagLayout thisLayout = new GridBagLayout();
				thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				thisLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7};
				thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				
				thisLayout.columnWidths = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
				getContentPane().setLayout(thisLayout);
				this.setTitle(LangTools.getResourceBundleWordLanguage(rb,"MPEG2 codec settings","mpeg2gui.confpanel"));
				{
					jLabelcontainer = new JLabel();
					getContentPane().add(jLabelcontainer, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 0, 0), 0, 0));
					jLabelcontainer.setText(LangTools.getResourceBundleWordLanguage(rb,"Video container","general.videocontainer"));
				}
				{
					jButtonsaveclose = new JButton();
					getContentPane().add(jButtonsaveclose, new GridBagConstraints(3, 5, 7, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					jButtonsaveclose.setText(LangTools.getResourceBundleWordLanguage(rb,"Save","general.save"));
					jButtonsaveclose.setActionCommand(SAVEANDCLOSEMPEG2CONFPANEL);
					jButtonsaveclose.addActionListener(this);
				}
				{
					jButtonclose = new JButton();
					getContentPane().add(jButtonclose, new GridBagConstraints(0, 5, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					jButtonclose.setText(LangTools.getResourceBundleWordLanguage(rb,"Cancel","general.cancel"));
					jButtonclose.setActionCommand(CLOSEMPEG2CONFPANEL);
					jButtonclose.addActionListener(this);
					
				}
				{
					jLabelpixelformat = new JLabel();
					getContentPane().add(jLabelpixelformat, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 0, 0), 0, 0));
					jLabelpixelformat.setText(LangTools.getResourceBundleWordLanguage(rb,"Pixel Format","general.pixelformat"));
				}
				{
					
					jComboBoxpixelformat = new JComboBox();
					getContentPane().add(jComboBoxpixelformat, new GridBagConstraints(1, 1, 8, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					
				}
				{
					
					useGopsize = new SteelCheckBox();
					getContentPane().add(useGopsize, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 0, 0), 0, 0));
					useGopsize.setText(LangTools.getResourceBundleWordLanguage(rb, "GOP size", "mpeg2gui.gopsize"));
					useGopsize.setActionCommand(USEGOPSIZEMPEG);
					useGopsize.addActionListener(this);
				}
				{
					
					Fieldgopsize= new JSpinner();
					getContentPane().add(Fieldgopsize, new GridBagConstraints(1, 3, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					
				}
				{
					
					CheckBoxyesbframes=new SteelCheckBox();
					getContentPane().add(CheckBoxyesbframes, new GridBagConstraints(0, 4, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 0, 0), 0, 0));
					CheckBoxyesbframes.setText("B-Frames");

				}
				{
				
					CheckBoxyestrellis=new SteelCheckBox();
					getContentPane().add(CheckBoxyestrellis, new GridBagConstraints(2, 4, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					CheckBoxyestrellis.setText("Trellis");
			
				}
				{
					CheckBoxpelemon= new SteelCheckBox();
					getContentPane().add(CheckBoxpelemon, new GridBagConstraints(4, 4, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					CheckBoxpelemon.setText("Pel ME");
				
				}
			}
			{
				this.setSize(419, 328);
			}
		} catch(Exception e) {
		    Logger.error(e);
		}
	}



	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
       
		if(action.equals(CLOSEMPEG2CONFPANEL))
			this.dispose();
		else if(action.equals(USEGOPSIZEMPEG))
			checkgopsizestate();
		else if (action.equals(SAVEANDCLOSEMPEG2CONFPANEL)){
			saveChoosenOptionsToContainer();
			this.dispose();
		}
	}
	
	/**
	 * Check gop size state.
	 */
	private void checkgopsizestate(){
		if(useGopsize.isSelected())
			Fieldgopsize.setEnabled(true);
		else
			Fieldgopsize.setEnabled(false);
		
	}
	
	
	/**
	 * Save chosen options to container.
	 */
	private void saveChoosenOptionsToContainer(){
		
		if(useGopsize.isSelected())
		((VideoMPEG2EncodingInfoContainer)infocont).setGopsize((int) Fieldgopsize.getValue());
		else
			((VideoMPEG2EncodingInfoContainer)infocont).setGopsize(-1);	
		
		((VideoMPEG2EncodingInfoContainer)infocont).setUsebFrames(CheckBoxyesbframes.isSelected());
		
		
		((VideoMPEG2EncodingInfoContainer)infocont).setUseTrellis(CheckBoxyestrellis.isSelected());
		
		((VideoMPEG2EncodingInfoContainer)infocont).setUsePelMe(CheckBoxpelemon.isSelected());
		
		((VideoMPEG2EncodingInfoContainer)infocont).setPixelformat((PixelFormat) jComboBoxpixelformat.getSelectedItem());
		
		
	}
	

}
