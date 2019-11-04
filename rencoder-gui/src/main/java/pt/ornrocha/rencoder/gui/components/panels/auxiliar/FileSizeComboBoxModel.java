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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: Auto-generated Javadoc
/**
 * The Class FileSizeComboBoxModel.
 */
public class FileSizeComboBoxModel extends BitrateComboBoxModel<String>{


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new file size combo box model.
	 *
	 * @param items the items
	 */
	public FileSizeComboBoxModel(String[] items) {
		super(items);

	}
	
	
/* (non-Javadoc)
 * @see gui.components.panels.auxiliar.BitrateComboBoxModel#getint(java.lang.Object)
 */
@Override	
protected int getint(String s){
		int res=0;
		if(s!=null){
			Pattern sizepat = Pattern.compile("^\\d+$");
		    Matcher m = sizepat.matcher((CharSequence) s);
		    if(m.find()){
		    	res=Integer.parseInt((java.lang.String) m.group());
		     
		    }
		  }
		return res;
	}

}
