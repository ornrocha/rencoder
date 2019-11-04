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

import java.awt.Color;

import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import pt.ornrocha.rencoder.helpers.osystem.OSystem;

// TODO: Auto-generated Javadoc
/**
 * The Class StyleUtilities.
 */
public class StyleUtilities {
	
	
	
	/**
	 * Gets the default style.
	 *
	 * @param doc the doc
	 * @return the default style
	 */
	public static Style getDefaultStyle(StyledDocument doc){
		return doc.getStyle( StyleContext.DEFAULT_STYLE );
	}
	
	
	/**
	 * Gets the regular default style.
	 *
	 * @param doc the doc
	 * @return the regular default style
	 */
	public static Style getRegularDefaultStyle(StyledDocument doc){
		Style regular = doc.addStyle("regular", getDefaultStyle(doc));
		if(OSystem.isLinux())
		     StyleConstants.setFontFamily(regular, "Dialog");
		    else
		    	StyleConstants.setFontFamily(regular, "Arial");
		return regular;
	}
	
	
	/**
	 * Gets the regular mod size and color.
	 *
	 * @param doc the doc
	 * @param stylename the stylename
	 * @param fontsize the fontsize
	 * @param color the color
	 * @return the regular modsizeandcolor
	 */
	public static Style  getRegularModsizeandcolor(StyledDocument doc,String stylename, int fontsize, Color color){
		Style regmod= doc.addStyle("regular_"+stylename, getRegularDefaultStyle(doc));
		StyleConstants.setFontSize(regmod, fontsize);
		StyleConstants.setForeground(regmod, color); 
		return regmod;
	}
	
	/**
	 * Gets the bold default style.
	 *
	 * @param doc the doc
	 * @return the bold default style
	 */
	public static Style getBoldDefaultStyle(StyledDocument doc){
		Style bold = doc.addStyle("bold", getRegularDefaultStyle(doc));
		StyleConstants.setBold( bold, true );
		return bold;
	}
	
	/**
	 * Gets the bold mod size and color.
	 *
	 * @param doc the doc
	 * @param stylename the stylename
	 * @param fontsize the fontsize
	 * @param color the color
	 * @return the bold modsizeandcolor
	 */
	public static Style  getBoldModsizeandcolor(StyledDocument doc, String stylename, int fontsize, Color color){
		Style boldmod= doc.addStyle("bold_"+stylename, getBoldDefaultStyle(doc));
		StyleConstants.setFontSize(boldmod, fontsize);
		StyleConstants.setForeground(boldmod, color); 
		return boldmod;
	}
	
	
	public static boolean checkallowedlookandfeelskin(String looktag, String skintag){
		boolean allowed=false;
		
		for (Looktypes looks : Looktypes.values()) {
			if(looks.getType().equals(looktag)){
				if(looks.haveSkinTag()){
						allowed=true;
				}
			}
		}
		return allowed;
	}
	
	public static Looktypes getLookAndFeel(String looktag){
		Looktypes definedlook =null;
		for (Looktypes looks : Looktypes.values()) {
			if(looks.getType().equals(looktag)){
				definedlook=looks;
			}
		}
		if(definedlook!=null)
		  return definedlook;
		else
		  return Looktypes.NIMBUS;	
	}

}
