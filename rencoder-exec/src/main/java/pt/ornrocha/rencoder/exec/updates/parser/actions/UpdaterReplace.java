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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.exec.execute.RencoderExec;
import pt.ornrocha.rencoder.exec.updates.auxiliar.StaticFunctionsUpdater;
import pt.ornrocha.rencoder.exec.updates.parser.UpdateConfigurationsChecker;

public class UpdaterReplace implements UpdaterAction{

	private String originalfilename;
	private String replacefilename;
	private String updatesrootfolder;
	private String updatessubfolders=null;
	private String destrootfolder;
	private String destsubfolders=null;
	private boolean replaceall=false;


	public UpdaterReplace(String action, String sourcefolder, String destfolder){
		this.updatesrootfolder=sourcefolder;
		this.destrootfolder=destfolder;
		filterFiles(action);
	}

	private void filterFiles(String action){
		Pattern pat = Pattern.compile("replace:(/*\\w+/(\\w+/)*)*(\\w+(([._-])*\\w+)*)->(/*\\w+/(\\w+/)*)*(\\w+(([._-])*\\w+)*)");
		Matcher match = pat.matcher(action);
		String updatessubfolderstemp=null;
		String destsubfolderstemp=null;
		if(match.find()){
			updatessubfolderstemp=match.group(1);
			replacefilename=match.group(3);
			destsubfolderstemp=match.group(6);
			originalfilename=match.group(8);

		}
		else{
			Pattern patall = Pattern.compile("(\\s+)*replace:(/*\\w+/(\\w+/)*)*all(\\s+)*", Pattern.CASE_INSENSITIVE);
			Matcher matchall = patall.matcher(action);
			if(matchall.find()){
				updatessubfolderstemp=matchall.group(2);
				destsubfolderstemp=matchall.group(2);
				replaceall=true;
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
			return destrootfolder+destsubfolders+originalfilename;
		else
			return destrootfolder+RencoderExec.getSystemSeparator()+originalfilename;
	}

	@Override
	public String getUpdateFilePath() {
		if(updatessubfolders!=null)
			return updatesrootfolder+updatessubfolders+replacefilename;
		else
			return updatesrootfolder+RencoderExec.getSystemSeparator()+replacefilename;
	}


	public String getNewReplaceFilePath() {
		if(destsubfolders!=null)
			return destrootfolder+destsubfolders+replacefilename;
		else
			return destrootfolder+RencoderExec.getSystemSeparator()+replacefilename;
	}

	public String getNewReplaceFilePath(String filename, String subfoldname) {
		if(destsubfolders!=null){
			if(subfoldname!=null)
				return destrootfolder+destsubfolders+RencoderExec.getSystemSeparator()+subfoldname+RencoderExec.getSystemSeparator()+filename;
			else
				return destrootfolder+destsubfolders+filename;
		}	
		else{
			if(subfoldname!=null)
				return destrootfolder+RencoderExec.getSystemSeparator()+subfoldname+RencoderExec.getSystemSeparator()+filename;
			else
				return destrootfolder+RencoderExec.getSystemSeparator()+filename;
		}

	}




	@Override
	public boolean isAllAction() {

		return replaceall;
	}


	@Override
	public void executeAction() throws IOException {

		if(!isAllAction()){

			File update = new File(getUpdateFilePath());
			File old = new File(getDestinationFilePath());

			if(isRencoderConfFile(getUpdateFilePath()))
				checkIfConfigurationFileAndChangeFiels(getUpdateFilePath(),getDestinationFilePath());

			if(old.exists())
				old.delete();

			String newname= getNewReplaceFilePath();
			File newfilename= new File(newname);
			if(newfilename.exists())
				newfilename.delete();
			Files.copy(update.toPath(), newfilename.toPath());

		}
		else if(isAllAction()){
			File updatefold=null;
			if(updatessubfolders!=null)
				updatefold=new File(updatesrootfolder+updatessubfolders);
			else
				updatefold=new File(updatesrootfolder);

			replaceFilesAllFilesInFolder(updatefold, null);
		}

	}


	private void replaceFilesAllFilesInFolder(File folder, String subfoldername) throws IOException{

		File[] listindir = folder.listFiles();

		for (File filep : listindir) {

			Path fp = Paths.get(filep.getAbsolutePath());
			String name=fp.getFileName().toString();
			String newfilepath = getNewReplaceFilePath(name,subfoldername);
			File checkex = new File(newfilepath);


			if(filep.isDirectory()){
				if(!checkex.exists())
					checkex.mkdir();

				replaceFilesAllFilesInFolder(filep, name);
			}

			else{
				if(checkex.exists())
					FileUtils.forceDelete(checkex);
				Logger.info("Replacing: "+checkex.getAbsolutePath());
				
				String parentdir= FilenameUtils.getFullPath(checkex.getAbsolutePath());
				if(!new File(parentdir).exists())
					new File(parentdir).mkdirs();
				
				Files.copy(filep.toPath(), checkex.toPath());
			}


		}

	}


	private void checkIfConfigurationFileAndChangeFiels(String updatefilepath, String originalFilePath){
		UpdateConfigurationsChecker upchange = new UpdateConfigurationsChecker(updatefilepath, originalFilePath);
		upchange.changeUpdateToExistingProps();

	}


	public boolean isRencoderConfFile(String filepath){

		//Pattern pattern = Pattern.compile(".*(.conf)$",Pattern.CASE_INSENSITIVE);
		String basename=FilenameUtils.getName(filepath);
		Pattern pattern = Pattern.compile("rencoder.conf",Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(basename);

		if (matcher.find()) {
			return true;
		}
		return false;
	}

	//	public boolean isOrsvcConfFile(String filepath){
	//
	//		//Pattern pattern = Pattern.compile(".*(.conf)$",Pattern.CASE_INSENSITIVE);
	//		String basename=FilenameUtils.getName(filepath);
	//		Pattern pattern = Pattern.compile("orsvc.conf",Pattern.CASE_INSENSITIVE);
	//		Matcher matcher = pattern.matcher(basename);
	//
	//		if (matcher.find()) {
	//			String ext = matcher.group(1);
	//			if(ext!=null){
	//				if(ext.toLowerCase().equals(".conf"))
	//					return true;
	//			}
	//		}
	//		return false;
	//	}



}
