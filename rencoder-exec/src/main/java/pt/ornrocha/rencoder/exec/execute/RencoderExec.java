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
package pt.ornrocha.rencoder.exec.execute;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import pt.ornrocha.rencoder.exec.updates.auxiliar.StaticFunctionsUpdater;
import pt.ornrocha.rencoder.exec.updates.connections.CheckForUpdates;
import pt.ornrocha.rencoder.exec.updates.connections.UpdateExecutor;





public final class RencoderExec {

	public static final String JARNAME="rencoder.jar";
	public static final String NEWRENCODERJAR="newrencoder.jar";
	public static final String RESTARTTAG=".restart";
	public static final String JREDIR="jre";
	public static final int PTAG=2;

	private enum OSType {
		WINDOWS,LINUX, MACOS
	};



	private static OSType getOperationSystem()  {
		String os = System.getProperty("os.name").toLowerCase();

		if(os.indexOf("nux")>=0 || os.indexOf("nix") >= 0){
			return OSType.LINUX;
		}
		else if(os.indexOf("win") >= 0){
			return OSType.WINDOWS;

		}
		else if(os.indexOf("mac") >= 0){
			return OSType.MACOS;
		}

		return null;
	}



	public static String getSystemSeparator(){
		OSType os= getOperationSystem();
		if(os.equals(OSType.LINUX) || os.equals(OSType.MACOS))
			return "/";
		else if (os.equals(OSType.WINDOWS))
			return "\\";

		return null;
	}


	public static String getCurrentDir(){
		String currentpath = new File(".").getAbsolutePath();
		Path p = Paths.get(currentpath);
		return p.getParent().toString();
	}

	private static String getMacUserDir(){
		String jarpath = getCurrentDir();
		String torepl="Java";
		String byrepl="Resources";
		String res = jarpath.replace(torepl, byrepl);
		System.out.println(res);
		return res;
	}




	private static String getMainJarPath(){
		return getCurrentDir()+getSystemSeparator()+JARNAME;
	}


	private static boolean existRestartTag(){
		boolean exist=false;

		String tagpath = getCurrentDir()+getSystemSeparator()+RESTARTTAG;
		File f = new File(tagpath);
		if(f.exists())
			exist=true;

		return exist;
	}


	private static void removeRestartTag(){
		if(existRestartTag()){
			String tagpath = getCurrentDir()+getSystemSeparator()+RESTARTTAG;
			File f = new File(tagpath);
			f.delete();
		}

	}

	private static int checkRestartTag(){
		boolean restart = existRestartTag();
		if(restart)
			return 0;
		else
			return 2;
	}



	public static void startRencoder(){

		int t = PTAG;
		int p = PTAG;



		do{


			if(!StaticFunctionsUpdater.isCheckUpdatesBlocked()){
				CheckForUpdates check = new CheckForUpdates();
				check.checkupdates();
			}

			String newjar=getCurrentDir()+getSystemSeparator()+NEWRENCODERJAR;
			String jarpath=getCurrentDir()+getSystemSeparator()+JARNAME;
			File rencoder = new File(jarpath);
			File newrencoder = new File(newjar);


			if(newrencoder.exists()){
				rencoder.delete();
				try {
					Files.copy(newrencoder.toPath(), rencoder.toPath());
				} catch (IOException e) {
					e.printStackTrace();
				}	 
			}

			File updatefile=new File(UpdateExecutor.UPDATEZIP);
			if(updatefile.exists())
				updatefile.delete();


			if(rencoder.exists()){
				rencoder.setExecutable(true);
				rencoder.setWritable(true);
				rencoder.setReadable(true);
			}


			String maindir= getCurrentDir();
			String mainjar = getMainJarPath();
			String[] commands = {"java", "-jar", mainjar};
			//if(getOperationSystem().equals(OSType.WINDOWS)) {
				String jrepath = getCurrentDir()+getSystemSeparator()+JREDIR;
				File f = new File(jrepath);
				if(f.exists()) {
					commands = new String[3];
					commands[0]=jrepath+getSystemSeparator()+"bin"+getSystemSeparator()+"java";
					commands[1]="-jar";
					commands[2]=mainjar;
				}
			//}
			
			File dir = new File(maindir);

			removeRestartTag();

			ProcessBuilder pb = new ProcessBuilder(commands);

			pb.directory(dir);

			try {

				Process proc = pb.start();
				t=proc.waitFor();

			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}

			p=checkRestartTag();

		} while(t==p);

		System.exit(t);	

	}




	public static void main(String args[])throws IOException, InterruptedException{
		RencoderExec.startRencoder();
	}


}
