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
package pt.ornrocha.rencoder.exec.updates.parser;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import pt.ornrocha.rencoder.exec.updates.auxiliar.CommentedProperties;

public class UpdateConfigurationsChecker {

    private CommentedProperties updateprops =new CommentedProperties();
    private CommentedProperties originalprops =new CommentedProperties();
    private String updatefile = null;
    private static final ArrayList<String> lockfields = new ArrayList<>(Arrays.asList("Update.server.url", "Version.file.url", "Update.file.zip"));

    public UpdateConfigurationsChecker(String updatefile, String origfile){
	try {
	    this.updatefile=updatefile;
	    FileInputStream update = new FileInputStream(updatefile);
	    FileInputStream orig = new FileInputStream(origfile);
	    updateprops.load(update);
	    originalprops.load(orig);

	    //update.close();
	    //orig.close();

	} catch (IOException e) {
	    e.printStackTrace();
	}

    }


    public void changeUpdateToExistingProps(){

	Set<String> updatekeys = updateprops.stringPropertyNames();
	Set<String> originalkeys = originalprops.stringPropertyNames();
	try {
	    for (String upkey : updatekeys) {
		if(originalkeys.contains(upkey) && !lockfields.contains(upkey)){
		    String original = originalprops.getProperty(upkey);
		    String newprop = updateprops.getProperty(upkey);
		    if(!original.equals(newprop))
			changeProperty(updatefile, upkey, original);

		}	
	    }
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    private void changeProperty(String filepath, String key, String value) throws IOException{
	FileInputStream in = new FileInputStream(filepath);
	CommentedProperties props = new CommentedProperties();
	props.load(in);
	in.close();

	FileOutputStream out = new FileOutputStream(filepath);
	props.setProperty(key, value);
	props.store(out, null);
	out.close();

    }

    public static void main(String[] args){
	/*String toreplace="/home/orocha/Eclipse_Projects/kepler/orsvc/testeupd/toreplace/orsvc.conf";
	 String replacer="/home/orocha/Eclipse_Projects/kepler/orsvc/testeupd/replacer/orsvc.conf";

	 UpdateConfigurationsChecker rep= new UpdateConfigurationsChecker(replacer, toreplace);
	 rep.changeUpdateToExistingProps();*/
    }

}