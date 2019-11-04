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

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;

// TODO: Auto-generated Javadoc
/**
 * The Class BitrateComboBoxModel.
 *
 * @param <String> the generic type
 */
public class BitrateComboBoxModel<String> extends DefaultComboBoxModel<String> implements Comparator<Integer>{


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The invertelementevalution. */
	private boolean invertelementevalution=false;
	
	
	
	/**
	 * Instantiates a new bitrate combo box model.
	 *
	 * @param items the items
	 */
	public BitrateComboBoxModel(final String items[]) {
        super(items);
    }

    /**
     * Invert elements evaluation.
     *
     * @param lowertouppervalues the lowertouppervalues
     */
    public void invertElementsEvaluation(boolean lowertouppervalues){
    	this.invertelementevalution=lowertouppervalues;
    }


	/* (non-Javadoc)
	 * @see javax.swing.DefaultComboBoxModel#addElement(java.lang.Object)
	 */
	@Override
	public void addElement(String element)
	{
        int size=getSize();
        for(int i=0;i<size;i++)
             
        	if(compare(getint(getElementAt(i)),getint(element ))>0){
        		super.insertElementAt(element, i);
        		return;
        	}

        super.addElement(element);
   }
	
	


	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Integer o1, Integer o2) {
		if(invertelementevalution)
			return o2-o1;
		else
		return o1-o2;
	}

    
	/**
	 * Gets the int.
	 *
	 * @param s the s
	 * @return the int
	 */
	protected int getint(String s){
		int res=0;
		if(s!=null){
			Pattern sizepat = Pattern.compile("(\\d+)k");
		    Matcher m = sizepat.matcher((CharSequence) s);
		    if(m.find()){
		    	res=Integer.parseInt((java.lang.String) m.group(1));
		     
		    }
		  }
		return res;
	}





}
