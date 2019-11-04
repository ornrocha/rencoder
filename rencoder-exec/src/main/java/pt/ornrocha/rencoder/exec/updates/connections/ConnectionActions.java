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
package pt.ornrocha.rencoder.exec.updates.connections;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import pt.ornrocha.rencoder.exec.execute.RencoderExec;
import pt.ornrocha.rencoder.exec.updates.auxiliar.ConfigurationPropertyNotDefinedException;
import pt.ornrocha.rencoder.exec.updates.auxiliar.ConstantsUpdater;
import pt.ornrocha.rencoder.exec.updates.auxiliar.StaticFunctionsUpdater;

public class ConnectionActions {

    public static String sepchar = "/";



    public static File getVersionFile() throws ConfigurationPropertyNotDefinedException, IOException{

	File f = null;



	Properties props = StaticFunctionsUpdater.loadPropertiesRelativePath(ConstantsUpdater.RENCODERCONFIGFILE);
	String versionfilename = null;


	if(props.containsKey(ConstantsUpdater.VERSIONCHECKFILEURL)){
	    if(!props.getProperty(ConstantsUpdater.VERSIONCHECKFILEURL).isEmpty()){
		versionfilename=(String) props.getProperty(ConstantsUpdater.VERSIONCHECKFILEURL);
	    }
	    else 
		throw new ConfigurationPropertyNotDefinedException("Version file is not defined");
	}
	else 
	    throw new ConfigurationPropertyNotDefinedException("Version file is not defined");


	String checkversionfile = RencoderExec.getCurrentDir()+RencoderExec.getSystemSeparator()+ConstantsUpdater.RENCODERUPDATECHECKVERSIONFILE;
	f = new File(checkversionfile);

	String serverurl=getServerRepositoryURL();
	String versionurl=serverurl+versionfilename;
	ConnectionManager conn = new ConnectionManager(versionurl);
	String inputLine;
	BufferedReader br = new BufferedReader( new InputStreamReader(conn.getConnection().getInputStream()));

	FileWriter fw = new FileWriter(f.getAbsoluteFile());
	BufferedWriter bw = new BufferedWriter(fw);

	while ((inputLine = br.readLine()) != null) {
	    bw.write(inputLine+"\n");
	}

	bw.close();
	br.close();


	return f;
    }


    public static String getUpdateFileURL(){

	String fileupdateurl=null;
	try {
	    Properties props = StaticFunctionsUpdater.loadPropertiesRelativePath(ConstantsUpdater.RENCODERCONFIGFILE);

	    if(props.containsKey(ConstantsUpdater.UPDATEFILEURL)){
		if(!props.getProperty(ConstantsUpdater.UPDATEFILEURL).isEmpty()){
		    fileupdateurl=(String) props.getProperty(ConstantsUpdater.UPDATEFILEURL);
		} else

		    throw new ConfigurationPropertyNotDefinedException("Servel url is not defined");

	    }
	    else 
		throw new ConfigurationPropertyNotDefinedException("Servel url is not defined");


	} catch (ConfigurationPropertyNotDefinedException e) {
	    e.printStackTrace();
	}

	return getServerRepositoryURL()+fileupdateurl;

    }



    public static String getServerRepositoryURL() {
	String updserv=null;
	try {
	    Properties props = StaticFunctionsUpdater.loadPropertiesRelativePath(ConstantsUpdater.RENCODERCONFIGFILE);

	    if(props.containsKey(ConstantsUpdater.UPDATESERVERURL)){
		if(!props.getProperty(ConstantsUpdater.UPDATESERVERURL).isEmpty()){
		    updserv=(String) props.getProperty(ConstantsUpdater.UPDATESERVERURL);
		} else

		    throw new ConfigurationPropertyNotDefinedException("Servel url is not defined");

	    }
	    else 
		throw new ConfigurationPropertyNotDefinedException("Servel url is not defined");

	    String lastchar = updserv.substring(updserv.length()-1);
	    if(updserv!=null){
		if(!lastchar.equals(sepchar))
		    updserv=updserv+"/";
	    }

	} catch (ConfigurationPropertyNotDefinedException e) {
	    e.printStackTrace();
	}

	return updserv;
    }

}
