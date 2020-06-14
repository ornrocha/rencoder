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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import com.vdurmont.semver4j.Semver;

import pt.ornrocha.rencoder.exec.execute.RencoderExec;
import pt.ornrocha.rencoder.exec.log.UpdaterLogManager;
import pt.ornrocha.rencoder.exec.updates.auxiliar.ConfigurationPropertyNotDefinedException;
import pt.ornrocha.rencoder.exec.updates.auxiliar.ConstantsUpdater;
import pt.ornrocha.rencoder.exec.updates.auxiliar.StaticFunctionsUpdater;

public class CheckForUpdates  {

	File currentversionfile = null;
	private UpdateLaunchPanel inst=null;

	public CheckForUpdates(){
		try {
			this.currentversionfile=StaticFunctionsUpdater.getCurrentVersionFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	public boolean checkupdates()  {
		boolean checking=true;
		UpdaterLogManager.initialiseLogger();
		File checkcurrentversion = null;
		try {
			checkcurrentversion = ConnectionActions.getVersionFile();
		} catch (ConfigurationPropertyNotDefinedException | IOException e1) {
			return false;
		}
		
		String futureversion = StaticFunctionsUpdater.checkVersionInFile(checkcurrentversion);
		String currentversion = StaticFunctionsUpdater.checkVersionInFile(currentversionfile);

		ArrayList<String> newupdates = StaticFunctionsUpdater.checkUpdatesInfoInFile(checkcurrentversion);
		
		Semver sem = new Semver(futureversion);

		if(sem.isGreaterThan(currentversion)){
			this.inst= new UpdateLaunchPanel(newupdates);
			launchUpdatePanel();
			while (this.inst.checkifrunning()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		else
			checking=false;


		File deletecheck = new File(RencoderExec.getCurrentDir()+RencoderExec.getSystemSeparator()+ConstantsUpdater.RENCODERUPDATECHECKVERSIONFILE);
		if(deletecheck.exists())
			deletecheck.delete();

		return checking;

	}




	private void launchUpdatePanel(){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//UpdateLaunchPanel inst = new UpdateLaunchPanel();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);

			}
		});

	}


	public static void main(String[] args){
		UpdaterLogManager.initialiseLogger();
		CheckForUpdates up = new CheckForUpdates();
		up.checkupdates();

	}



}
