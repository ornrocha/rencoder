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
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;

import eu.hansolo.custom.SteelCheckBox;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioChannels;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioSampleRates;
import pt.ornrocha.rencoder.gui.components.panels.Scroll.AudioConfigSrollPanel;
import pt.ornrocha.rencoder.gui.components.panels.auxiliar.AudioPanelUtils;
import pt.ornrocha.rencoder.helpers.lang.LangTools;

// TODO: Auto-generated Javadoc
/**
 * The Class GeneralAudioConfigScrollPanel.
 */
public class GeneralAudioConfigScrollPanel extends AudioConfigSrollPanel{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** The use ac3 passthrough. */
	protected SteelCheckBox useac3passthrough;
	
	/**
	 * Instantiates a new general audio config scroll panel.
	 */
	public GeneralAudioConfigScrollPanel(JFrame mainframe){
		super(mainframe);
		changeGuiComponents();
		populateInitialComponents();
		
	}
	
	
	/**
	 * Change gui components.
	 */
	protected void changeGuiComponents(){
		jLabelfrequency.setText(LangTools.getResourceBundleWordLanguage(rb, "Preferred frequency (HZ)", "audiogui.preferredfrequency"));
		jLabelchannels.setText(LangTools.getResourceBundleWordLanguage(rb, "Preferred channel", "audiogui.preferredchannel"));
		
		useac3passthrough = new SteelCheckBox();
		useac3passthrough.setText(LangTools.getResourceBundleWordLanguage(rb, "Use AC3 passthrough (if possible)", "audiogui.passthrough"));
		jPanelmain.add(useac3passthrough, new GridBagConstraints(0, 12, 8, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 0, 0), 0, 0));
		
		useac3passthrough.setColored(true);
		useac3passthrough.setSelected(false);
	}
	
	
	
	/* (non-Javadoc)
	 * @see gui.components.panels.Scroll.AudioConfigSrollPanel#setAudioSampleRate()
	 */
	@Override
	protected void setAudioSampleRate(){
	
		asamplerates=new DefaultComboBoxModel<>(AudioSampleRates.values());
		
		jComboBoxfrequency.setModel(asamplerates);

	}
	
	
	/* (non-Javadoc)
	 * @see gui.components.panels.Scroll.AudioConfigSrollPanel#setAudioChannels(ffmpegWrapper.enumerators.AudioCodecs)
	 */
	@Override
	protected void setAudioChannels(AudioCodecs codec){
		  if(this.infocont!=null){
			
			achannels=new DefaultComboBoxModel<>(AudioPanelUtils.allowedAudioChannels(AudioChannels.SURROUND.toString(), codec));
			}

		else
			achannels=new DefaultComboBoxModel<>(AudioPanelUtils.allowedAudioChannels(AudioChannels.STEREO.toString(), codec));
			
		  jComboBoxChannels.removeAllItems();		
		  jComboBoxChannels.setModel(achannels);	
			
		}
	
	
	/* (non-Javadoc)
	 * @see gui.components.panels.Scroll.AudioConfigSrollPanel#setParametersInsideEncodingInfoContainer()
	 */
	@Override
	protected void setParametersInsideEncodingInfoContainer(){
		super.setParametersInsideEncodingInfoContainer();
		useac3passthrough.setSelected(infocont.isAc3passthrough());
		AudioChannels maxAudiochannel = infocont.getMaxAllowedAudiochannels();
		jComboBoxChannels.setSelectedItem(maxAudiochannel);
		
	}
	
	
	/* (non-Javadoc)
	 * @see gui.components.panels.Scroll.AudioConfigSrollPanel#saveSelectedInformationIntoContainer()
	 */
	@Override
	public void saveSelectedInformationIntoContainer(){
		super.saveSelectedInformationIntoContainer();
		infocont.setAc3passthrough(useac3passthrough.isSelected());
		infocont.setMaxAllowedAudiochannels((AudioChannels) jComboBoxChannels.getSelectedItem());
	}
	
	/* (non-Javadoc)
	 * @see gui.components.panels.Scroll.AudioConfigSrollPanel#checkuseonlyextraffmpegcmds()
	 */
	@Override
	 protected void checkuseonlyextraffmpegcmds(){
		  if(jTextFieldAudioffmpegcmd.getText().isEmpty()){
			  jCheckBoxffmpegcmd.setSelected(false);
		  }
		  else if(jCheckBoxffmpegcmd.isSelected()){
			  jSlidervbr.setEnabled(false);
		      CheckBoxvbr.setEnabled(false);
		      CheckBoxcbr.setEnabled(false);
		      jComboBoxbitrate.setEnabled(false);
		      jComboBoxfrequency.setEnabled(false);
		      jComboBoxChannels.setEnabled(false);
		      jLabelusedvbr.setEnabled(false);
		      jComboBoxcodec.setEnabled(false);
		      jLabelbitperchannel.setEnabled(false);
		      useac3passthrough.setSelected(false);
		      useac3passthrough.setEnabled(false);
		  }
		  else{
			  deactivateuseonlyextracmd();
		  }
	  }
	
	/* (non-Javadoc)
	 * @see gui.components.panels.Scroll.AudioConfigSrollPanel#deactivateuseonlyextracmd()
	 */
	@Override
	protected void deactivateuseonlyextracmd(){
		super.deactivateuseonlyextracmd();
		if(useac3passthrough!=null)
		   useac3passthrough.setEnabled(true);
	}
	
}
