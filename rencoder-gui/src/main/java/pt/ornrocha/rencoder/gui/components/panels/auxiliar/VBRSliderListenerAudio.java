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
package pt.ornrocha.rencoder.gui.components.panels.auxiliar;

import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioChannels;

// TODO: Auto-generated Javadoc
/**
 * The Class VBRSliderListenerAudio.
 */
public class VBRSliderListenerAudio extends VBRSliderListener{
    
	/** The j combo box channels. */
	private JComboBox jComboBoxChannels;
	
	/** The bitrateperchannellabel. */
	private JLabel bitrateperchannellabel;
	
	/**
	 * Instantiates a new VBR slider listener audio.
	 *
	 * @param vbrmap the vbrmap
	 * @param label the label
	 * @param usemappping the usemappping
	 * @param jComboBoxChannels the j combo box channels
	 * @param bitperchan the bitperchan
	 */
	public VBRSliderListenerAudio(HashMap<Integer, String> vbrmap,JLabel label, boolean usemappping, JComboBox jComboBoxChannels, JLabel bitperchan) {
		super(vbrmap, label, usemappping);
		this.jComboBoxChannels=jComboBoxChannels;
		this.bitrateperchannellabel=bitperchan;
	}
	
	
	/* (non-Javadoc)
	 * @see gui.components.panels.auxiliar.VBRSliderListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		  JSlider slider = (JSlider) e.getSource();

          if (!slider.getValueIsAdjusting()) {
              int value = slider.getValue();
              String bitrate=this.vbrtable.get(value);
              this.valueLabel.setText(bitrate);
              setValueBitratePerChannel(bitrate);
          }
	}
	
	/**
	 * Sets the value bitrate per channel.
	 *
	 * @param bitratevalue the new value bitrate per channel
	 */
	private void setValueBitratePerChannel(String bitratevalue){
		
		  AudioChannels channels = (AudioChannels)jComboBoxChannels.getSelectedItem();

		  int nchannels= channels.getchannels();
		  
		  int bitrateperchannel= Integer.valueOf(bitratevalue)/nchannels;
		  
		  bitrateperchannellabel.setText(String.valueOf(bitrateperchannel));
	}

}
