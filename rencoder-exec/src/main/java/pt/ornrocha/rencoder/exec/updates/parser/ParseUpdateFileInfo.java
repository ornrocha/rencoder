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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pt.ornrocha.rencoder.exec.execute.RencoderExec;
import pt.ornrocha.rencoder.exec.updates.auxiliar.ConstantsUpdater;
import pt.ornrocha.rencoder.exec.updates.parser.actions.UpdaterAction;
import pt.ornrocha.rencoder.exec.updates.parser.actions.UpdaterAdd;
import pt.ornrocha.rencoder.exec.updates.parser.actions.UpdaterDelete;
import pt.ornrocha.rencoder.exec.updates.parser.actions.UpdaterReplace;

public class ParseUpdateFileInfo {

	private ArrayList<String> filelines=null;
	private LinkedHashMap<String, ArrayList<String>> filteredactions=new LinkedHashMap<>();

	private String updatefolder=RencoderExec.getCurrentDir()+RencoderExec.getSystemSeparator()+ConstantsUpdater.UPDATEFOLDER+RencoderExec.getSystemSeparator();
	private String updatefoldernosep=RencoderExec.getCurrentDir()+RencoderExec.getSystemSeparator()+ConstantsUpdater.UPDATEFOLDER;

	private String rootfolder=RencoderExec.getCurrentDir()+RencoderExec.getSystemSeparator();
	private String rootfoldernosep=RencoderExec.getCurrentDir();

	private ArrayList<UpdaterAction> updateactions=null;


	private static String DETECTDEFAULTFOLDERPATTERN="(\\s)*folder:(\\w+->\\w+)(\\s+)*";
	private static String DETECTSINGLEFOLDERPATTERN="(\\s)*folder:(\\w+)(\\s+)*";
	private static String FOLDEREXITPATTERN="(\\s)*folder:exit(\\s+)*";
	private static String FOLDERMAPPINGPATTERN="(\\w+)->(\\w+)";
	private static String ACTIONTAG="^(replace|delete|add):(.*)";

	public ParseUpdateFileInfo(String filepath){
		readFile( filepath);
		getInfo();
	}




	public ArrayList<UpdaterAction> getUpdateactions() {
		return updateactions;
	}




	private void getInfo(){
		filterfolderActions();
		setUpdateActions();
	}

	public void filterfolderActions(){

		boolean foundnewfolder=false;
		ArrayList<String> in=null;
		String mapfolders=null;
		boolean singlefolder=false;

		for (int i = 0; i < filelines.size(); i++) {
			String line= filelines.get(i);


			if(!foundnewfolder && (line.matches(DETECTDEFAULTFOLDERPATTERN) ||line.matches(DETECTSINGLEFOLDERPATTERN))){
				in=new ArrayList<>();
				if(line.matches(DETECTDEFAULTFOLDERPATTERN))
					mapfolders=getValueFromString(line,DETECTDEFAULTFOLDERPATTERN,2,true);
				else if(line.matches(DETECTSINGLEFOLDERPATTERN)){
					mapfolders=getValueFromString(line,DETECTSINGLEFOLDERPATTERN,2,true);
					singlefolder=true;	
				}
				foundnewfolder=true;
			}
			else if(foundnewfolder && !line.matches(FOLDEREXITPATTERN) && !line.isEmpty()){
				if(in!=null){
					if(singlefolder){
						if(line.matches("(\\s+)*[Dd][Ee][Ll][Ee][Tt][Ee]:(.*)(\\s+)*"))
							in.add(line);
						else if(line.matches("(\\s+)*add:dir:(.*)(\\s+)*"))
							in.add(line);
					}
					else
						in.add(line);
				}
			}
			else if (line.matches(FOLDEREXITPATTERN)){
				foundnewfolder=false;
				singlefolder=false;
				if(in!=null && mapfolders!=null)
					filteredactions.put(mapfolders, in);
			}

		}


	}


	private void setUpdateActions(){

		updateactions= new ArrayList<>();
		if(filteredactions.size()>0){

			for (Map.Entry<String, ArrayList<String>> elem : filteredactions.entrySet()) {
				makeActionContainers(elem.getKey(), elem.getValue());
			}

		}	
	}



	private void makeActionContainers(String mapfolder, ArrayList<String> actions){

		boolean singlefolder=false;
		String[] mapoffolders =null;
		String updatesource=null;
		String updatedest=null;

		if(mapfolder.matches("\\w+")){
			singlefolder=true;
			updatedest=mapfolder;
		}
		else
			mapoffolders = getMapElements(mapfolder, FOLDERMAPPINGPATTERN, 1, 2,false);


		if(singlefolder){
			if(updatedest.matches("[Rr][Oo][Oo][Tt]"))
				updatedest=rootfoldernosep;
			else
				updatedest=rootfolder+updatedest;

		}		
		else{
			if(mapoffolders[0].matches("[Rr][Oo][Oo][Tt]"))
				updatesource=updatefoldernosep;
			else
				updatesource=updatefolder+mapoffolders[0];

			if(mapoffolders[1].matches("[Rr][Oo][Oo][Tt]"))
				updatedest=rootfoldernosep;
			else
				updatedest=rootfolder+mapoffolders[1];
		}

		for (String in : actions) {
			String tag=getValueFromString(in, ACTIONTAG,1,true);

			if(singlefolder){
				if(tag.toLowerCase().equals("delete"))
					updateactions.add(new UpdaterDelete(in,updatedest));
				if(tag.toLowerCase().equals("add")){
					if(in.contains("add:dir:"))
						updateactions.add(new UpdaterAdd(in, updatedest));
				}
			}
			else{	
				if(tag.toLowerCase().equals("replace"))
					updateactions.add(new UpdaterReplace(in, updatesource,updatedest));
				else if(tag.toLowerCase().equals("add"))
					updateactions.add(new UpdaterAdd(in, updatesource, updatedest));
				else if(tag.toLowerCase().equals("delete"))
					updateactions.add(new UpdaterDelete(in,updatedest));
			}

		}
	}


	private String getValueFromString(String text, String pattern, int group, boolean caseins){
		Pattern pat =null;
		if(caseins)
			pat = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		else
			pat = Pattern.compile(pattern);

		Matcher match = pat.matcher(text);
		if(match.find())
			return match.group(group);
		else
			return null;
	}

	private String[] getMapElements(String text, String pattern, int group1, int group2,boolean caseins){
		String[] res = null;
		Pattern pat =null;
		if(caseins)
			pat = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		else
			pat = Pattern.compile(pattern);

		Matcher match = pat.matcher(text);
		if(match.find()){
			res=new String[2];
			res[0]=match.group(group1);
			res[1]=match.group(group2);
		}
		return res;
	}


	private void readFile(String filepath){
		try {
			File file = new File(filepath);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			this.filelines=new ArrayList<>();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				this.filelines.add(line);
			}
			fileReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}



}
