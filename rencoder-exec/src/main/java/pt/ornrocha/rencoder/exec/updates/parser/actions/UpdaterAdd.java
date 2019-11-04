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
package pt.ornrocha.rencoder.exec.updates.parser.actions;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.exec.execute.RencoderExec;
import pt.ornrocha.rencoder.exec.updates.auxiliar.StaticFunctionsUpdater;

public class UpdaterAdd implements UpdaterAction{

    private String updatesrootfolder;
    private String destrootfolder;
    private String addwithfilename;
    private String addfilename;
    private String updatessubfolders=null;
    private String destsubfolders=null;
    private boolean iscreatefolder=false;

    public UpdaterAdd(String action, String sourcefolder, String destfolder){
	this.updatesrootfolder=sourcefolder;
	this.destrootfolder=destfolder;
	filterFiles(action);
    }

    public UpdaterAdd(String action, String destfolder){

	this.destrootfolder=destfolder;
	filterFiles(action);
    }



    private void filterFiles(String action){
	Pattern pat = Pattern.compile("add:(/*\\w+/(\\w+/)*)*(\\w+(([._-])*\\w+)*)->(/*\\w+/(\\w+/)*)*(\\w+(([._-])*\\w+)*)");
	Matcher match = pat.matcher(action);
	String updatessubfolderstemp=null;
	String destsubfolderstemp=null;
	if(match.find()){
	    updatessubfolderstemp=match.group(1);
	    addfilename=match.group(3);
	    destsubfolderstemp=match.group(6);
	    addwithfilename=match.group(8);
	}
	else{
	    Pattern patdir = Pattern.compile("add:dir:(/*\\w+/(\\w+/)*)*(\\w+(([_-])*\\w+)*)");
	    Matcher matchdir = patdir.matcher(action);

	    if(matchdir.find()){
		destsubfolderstemp=matchdir.group(1);
		addwithfilename=matchdir.group(3);
		this.iscreatefolder=true;
	    }
	}


	if(updatessubfolderstemp!=null)
	    updatessubfolders=StaticFunctionsUpdater.convertToSystemPath(updatessubfolderstemp);
	if(destsubfolderstemp!=null)
	    destsubfolders=StaticFunctionsUpdater.convertToSystemPath(destsubfolderstemp);

    }



    @Override
    public String getDestinationFilePath() {
	if(destsubfolders!=null)
	    return destrootfolder+destsubfolders+addwithfilename;
	else
	    return destrootfolder+RencoderExec.getSystemSeparator()+addwithfilename;
    }

    @Override
    public String getUpdateFilePath() {
	if(updatessubfolders!=null)
	    return updatesrootfolder+updatessubfolders+addfilename;
	else
	    return updatesrootfolder+RencoderExec.getSystemSeparator()+addfilename;
    }



    @Override
    public boolean isAllAction() {
	// TODO Auto-generated method stub
	return false;
    }



    @Override
    public void executeAction() throws IOException {
	File addto = new File(getDestinationFilePath());

	if(iscreatefolder){
	    if(!addto.exists()){
		if(destsubfolders!=null)
		    addto.mkdirs();
		else
		    addto.mkdir();
	    }

	}		
	else{
	    File addfrom = new File(getUpdateFilePath());

	    if(addto.exists())
		addto.delete();
	    Logger.info("Adding: "+addto.getAbsolutePath());
	    Files.copy(addfrom.toPath(), addto.toPath());

	}
    }

    public static void main(String[] args) {
	//UpdaterAdd ad = new UpdaterAdd("add:dir:teste1/sub1/sub2", "/home/orocha/eclipse_workspace/VideoConverter");
	//ad.executeAction();

    }

}
