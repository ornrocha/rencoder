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

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving VBRSlider events.
 * The class that is interested in processing a VBRSlider
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addVBRSliderListener<code> method. When
 * the VBRSlider event occurs, that object's appropriate
 * method is invoked.
 *
 * @see VBRSliderEvent
 */
public class VBRSliderListener implements ChangeListener{

	/** The vbrtable. */
	HashMap<Integer,String> vbrtable=null;
	
	/** The value label. */
	JLabel valueLabel;
	
	/** The usemapvalues. */
	boolean usemapvalues=false;
	
	/**
	 * Instantiates a new VBR slider listener.
	 *
	 * @param vbrmap the vbrmap
	 * @param label the label
	 * @param usemappping the usemappping
	 */
	public VBRSliderListener(HashMap<Integer,String> vbrmap, JLabel label, boolean usemappping){
		if(this.vbrtable!=null)
			this.vbrtable=null;
		this.vbrtable=vbrmap;
		this.valueLabel=label;	
		this.usemapvalues=usemappping;
	}
	
	
	
	/* (non-Javadoc)
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		  JSlider slider = (JSlider) e.getSource();

          if (!slider.getValueIsAdjusting()) {
            int value = slider.getValue();
            if(this.usemapvalues)
              this.valueLabel.setText(this.vbrtable.get(value));
            else
              this.valueLabel.setText(String.valueOf(value));	
            
          }
		
	}

}
