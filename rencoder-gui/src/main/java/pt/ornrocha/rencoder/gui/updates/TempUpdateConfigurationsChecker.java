/*
 * Copyright 2015
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
package pt.ornrocha.rencoder.gui.updates;

import java.util.Iterator;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.tinylog.Logger;

import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;

public class TempUpdateConfigurationsChecker {
	
	private PropertiesConfiguration updateprops =new PropertiesConfiguration();
	private PropertiesConfiguration originalprops =new PropertiesConfiguration();

	
	public TempUpdateConfigurationsChecker(String updatefile, String origfile){
		updateprops=PropertiesWorker.loadPropertiesRelativePath(updatefile);
		originalprops=PropertiesWorker.loadPropertiesRelativePath(origfile);
		
	}
	
   
    public void changeUpdateToExistingProps(){
    	
    	
    	Iterator<String> keys = updateprops.getKeys();
        while (keys.hasNext()) {
            String key = keys.next();
    	    if(!originalprops.containsKey(key)){
    	    	String value=(String) updateprops.getProperty(key);
    	    	if(!value.isEmpty())
    	    		originalprops.setProperty(key, value);
    	    	else
    	    		originalprops.setProperty(key, "");
    	        
    	    	if(key.equals("use.vlcjplayer.as.previewer.if.possible"))
    	    	originalprops.getLayout().setComment(key, "\n\n##########################################################################################\n#############################  internal Configurations ###################################\n##########################################################################################");
    	    }   
        }
        try {
			originalprops.save();
		} catch (ConfigurationException e) {
		    Logger.error(e);
		}
 
    }


  
}