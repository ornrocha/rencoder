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
package pt.ornrocha.rencoder.exec.updates.auxiliar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.exec.execute.RencoderExec;

public class StaticFunctionsUpdater {

	public static final String BLOCKUPDATESTAG=".blockupdates";

	public static Properties loadPropertiesRelativePath(String propsName){
		String absolutepath = new File(propsName).getAbsolutePath();
		InputStream in = null;
		Properties props =new Properties();

		try {

			in = new FileInputStream(absolutepath);   
			props.load(in);
			in.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return props;
	}


	public static File getCurrentVersionFile() throws IOException{
		String filepath = new File(ConstantsUpdater.RENCODERCURRENTVERSIONFILE).getAbsolutePath();
		if(filepath==null)
			throw new IOException("Current version file is missing");
		else
			return new File(filepath);
	}


	public static String checkVersionInFile(File file){
		String res="1.0.0";
		if(file!=null){
			try {
				for(String line: readFileLines(file)){
					if(line.matches("(\\s+)*[Vv][Ee][Rr][Ss][Ii][Oo][Nn]:\\d+.\\d+.\\d+(\\s+)*"))
						res =extractversion(line);
				}
			} catch (IOException e) {
				Logger.error(e);
			}
		}
		return res;
	}

	public static ArrayList<String> checkUpdatesInfoInFile(File file){
		ArrayList<String> updates = new ArrayList<>();
		boolean start=false;
		try {
			for(String line: readFileLines(file)){
				if(line.matches("(\\s+)*[Uu][Pp][Dd][Aa][Tt][Ee][Ss]:(\\s+)*"))
					start=true;
				else if(start)
					updates.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return updates;
	}





	public static String extractversion(String line){
		Pattern pat = Pattern.compile("(\\s+)*version:(\\d+.\\d+.\\d+)(\\s+)*", Pattern.CASE_INSENSITIVE);
		Matcher m = pat.matcher(line);
		if(m.find())
			return m.group(2);
		else
			return "1.0.0";
	}



	public static String convertToSystemPath(String pat){
		if(pat.matches("/\\w+/(\\w+/)*"))
			return pat.replace("/", RencoderExec.getSystemSeparator());
		else
			return RencoderExec.getSystemSeparator()+pat.replace("/", RencoderExec.getSystemSeparator());

	}


	public static void deleteFolder(File folder) {
		File[] files = folder.listFiles();
		if(files!=null) {
			for(File f: files) {
				if(f.isDirectory()) {
					deleteFolder(f);
				} else {
					f.delete();
				}
			}
		}
		folder.delete();
	}

	public static ArrayList<String> readFileLines(File f) throws IOException{
		ArrayList<String> res = new ArrayList<>();
		String fpath = f.getAbsolutePath();
		BufferedReader br = new BufferedReader(new FileReader(new File(fpath)));

		String line = null;
		while ((line = br.readLine()) != null) {
			res.add(line);
		}

		br.close();
		return res;
	}

	public static String getBlockUptdatesFilePath(){
		return RencoderExec.getCurrentDir()+RencoderExec.getSystemSeparator()+BLOCKUPDATESTAG;
	}

	public static void setCheckUpdatesBlock(){
		File f= new File(getBlockUptdatesFilePath());
		try {
			if(!f.exists())
				f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean isCheckUpdatesBlocked(){
		File f = new File(getBlockUptdatesFilePath());
		if(f.exists())
			return true;
		else
			return false;
	}

}
