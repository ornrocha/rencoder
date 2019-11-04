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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.exec.execute.RencoderExec;
import pt.ornrocha.rencoder.exec.updates.auxiliar.ConfigurationPropertyNotDefinedException;
import pt.ornrocha.rencoder.exec.updates.auxiliar.ConstantsUpdater;
import pt.ornrocha.rencoder.exec.updates.auxiliar.StaticFunctionsUpdater;
import pt.ornrocha.rencoder.exec.updates.parser.ParseUpdateFileInfo;
import pt.ornrocha.rencoder.exec.updates.parser.actions.UpdaterAction;

public class UpdateExecutor implements Runnable{

	private PropertyChangeSupport changelst =null;
	private ConnectionManager conn =null;
	public static final String UPDATEZIP= RencoderExec.getCurrentDir()+RencoderExec.getSystemSeparator()+ConstantsUpdater.UPDATEFILE;
	private static final String DESTFOLDER=RencoderExec.getCurrentDir()+RencoderExec.getSystemSeparator()+ConstantsUpdater.UPDATEFOLDER;

	private static final String UPDATEFILECONF= DESTFOLDER+RencoderExec.getSystemSeparator()+ConstantsUpdater.UPDATECONFIGFILE;

	public UpdateExecutor(ConnectionManager conn){
		this.conn=conn;
	}


	public void addUpdaterPropertyChangeListener(PropertyChangeListener listener) {
		this.changelst= new PropertyChangeSupport(this);
		this.changelst.addPropertyChangeListener(listener);
	}

	private void setProgressBarFireEvents(int newval){
		int oldval=0;
		if(oldval==0)
			changelst.firePropertyChange("downprogressbar", 0, newval);
		else
			changelst.firePropertyChange("downprogressbar", oldval, newval);
		oldval=newval;
	}

	private void setinstallBarFireEvents(int newval){
		int oldval=0;
		if(oldval==0)
			changelst.firePropertyChange("installprogressbar", 0, newval);
		else
			changelst.firePropertyChange("installprogressbar", oldval, newval);
		oldval=newval;
	}

	private void setStartAppFireEvents(){
		changelst.firePropertyChange("startapp",false,true);
	}

	private void setInstallErrorFireEvents(){
		changelst.firePropertyChange("installerror",false,true);
	}

	private void downloadUpdate() throws ConfigurationPropertyNotDefinedException, IOException
	{   

		String gettodir=RencoderExec.getCurrentDir()+RencoderExec.getSystemSeparator()+ConstantsUpdater.UPDATEFILE;

		InputStream is = conn.getConnection().getInputStream();
		int size = conn.getConnection().getContentLength();

		BufferedOutputStream fOut = new BufferedOutputStream(new FileOutputStream(new File(gettodir)));
		byte[] buffer = new byte[32 * 1024];
		int bytesRead = 0;
		int in = 0;
		float val=0;

		while ((bytesRead = is.read(buffer)) != -1) {
			in += bytesRead;
			fOut.write(buffer, 0, bytesRead);
			val=((float)in/(float)size)*100;
			setProgressBarFireEvents((int)val);

		}
		fOut.flush();
		fOut.close();
		is.close();

	}



	private void unzipUpdate(){

		File filezip = new File(UPDATEZIP);
		File destdir=new File(DESTFOLDER);

		try
		{
			int BUFFER = 2048;

			ZipFile zip = new ZipFile(filezip);
			destdir.mkdir();

			Enumeration<? extends ZipEntry> zipFileEntries = zip.entries();


			while (zipFileEntries.hasMoreElements())
			{

				ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
				String currentEntry = entry.getName();

				File destFile = new File(destdir, currentEntry);

				File destinationParent = destFile.getParentFile();

				destinationParent.mkdirs();

				if (!entry.isDirectory())
				{
					BufferedInputStream is = new BufferedInputStream(zip.getInputStream(entry));
					int currentByte;
					byte data[] = new byte[BUFFER];

					FileOutputStream fos = new FileOutputStream(destFile);
					BufferedOutputStream dest = new BufferedOutputStream(fos,BUFFER);

					while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
						dest.write(data, 0, currentByte);
					}
					dest.flush();
					dest.close();
					is.close();
				}
			}
		}
		catch (Exception e) 
		{
			setInstallErrorFireEvents();
			Logger.error(e);
			e.printStackTrace();
		}

		Logger.info("Updates.zip was unzipped");
	}


	private void ApplyUpdate() throws IOException{

		File confs = new File(UPDATEFILECONF);
		if(!confs.exists()){
			setInstallErrorFireEvents();
			throw new IOException("Missing the Update Configuration File");
		}
		else{
			ParseUpdateFileInfo updateoper = new ParseUpdateFileInfo(UPDATEFILECONF);
			ArrayList<UpdaterAction> exec = updateoper.getUpdateactions();
			int total = exec.size();

			float val=0;
			for (int i = 0; i < exec.size(); i++) {
				UpdaterAction updaction = exec.get(i);
				updaction.executeAction();
				val = (int) (((float)i/(float)total)*100);
				setinstallBarFireEvents((int) val);

			}
			setinstallBarFireEvents(100);


		}

	}

	private void deleteUpdateFolder(){
		File zip = new File(UPDATEZIP);
		if(zip.exists())
			zip.delete();

		File updatefolder = new File(DESTFOLDER);
		if(updatefolder.exists())
			StaticFunctionsUpdater.deleteFolder(updatefolder);
	}





	@Override
	public void run() {
		boolean runok=true;
		try {
			downloadUpdate();
		} catch (ConfigurationPropertyNotDefinedException | IOException e) {
			runok=false;
			setInstallErrorFireEvents();
			e.printStackTrace();
		}

		unzipUpdate();
		try {
			ApplyUpdate();
		} catch (IOException e) {
			runok=false;
			setInstallErrorFireEvents();
			Logger.error(e);
		}
		deleteUpdateFolder();

		if(runok){
			setStartAppFireEvents();  
		}

	}



}
