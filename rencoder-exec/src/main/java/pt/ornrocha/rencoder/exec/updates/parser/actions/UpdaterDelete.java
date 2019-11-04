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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.exec.execute.RencoderExec;
import pt.ornrocha.rencoder.exec.updates.auxiliar.StaticFunctionsUpdater;

public class UpdaterDelete implements UpdaterAction{

    private String deletefilename;
    private String destrootfolder;
    private String destsubfolders=null;
    private boolean deleteall=false;

    public UpdaterDelete(String action, String destfolderfile){
	this.destrootfolder=destfolderfile;
	filterFiles(action);
    }


    private void filterFiles(String action){
	Pattern pat = Pattern.compile("(\\s+)*delete:(/*\\w+/(\\w+/)*)*(all|(\\w+(([._-])*\\w+)*))(\\s+)*",Pattern.CASE_INSENSITIVE);
	Matcher match = pat.matcher(action);
	String destsubfolderstemp=null;
	if(match.find()){
	    destsubfolderstemp=match.group(2);
	    deletefilename=match.group(4);
	    if(deletefilename.toLowerCase().equals("all"))
		deleteall=true;

	}

	if(destsubfolderstemp!=null)
	    destsubfolders=StaticFunctionsUpdater.convertToSystemPath(destsubfolderstemp);

    }


    @Override
    public String getDestinationFilePath() {
	if(destsubfolders!=null){
	    if(deleteall)
		return destrootfolder+destsubfolders;
	    else
		return destrootfolder+destsubfolders+deletefilename;
	}
	else{
	    if(deleteall)
		return destrootfolder;
	    else
		return destrootfolder+RencoderExec.getSystemSeparator()+deletefilename;
	}

    }


    @Override
    public String getUpdateFilePath() {
	// TODO Auto-generated method stub
	return null;
    }


    @Override
    public boolean isAllAction() {
	// TODO Auto-generated method stub
	return deleteall;
    }


    @Override
    public void executeAction() throws IOException {
	File delfile= new File(getDestinationFilePath());
        Logger.info("deleting: "+delfile.getAbsolutePath());
	if(delfile.exists()){
	    if(delfile.isDirectory())
		StaticFunctionsUpdater.deleteFolder(delfile);
	    else
		delfile.delete();
	}		
    }




    public static void main(String[] args) {
	//UpdaterDelete del = new UpdaterDelete("delete:pasta2/pasta3/fich2.jar", "/home/orocha/eclipse_workspace/testedelete/pasta1/");
	//del.executeAction();
    }

}
