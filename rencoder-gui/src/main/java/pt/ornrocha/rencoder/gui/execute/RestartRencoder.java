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
package pt.ornrocha.rencoder.gui.execute;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;
import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.helpers.osystem.OSystem;
import pt.ornrocha.rencoder.helpers.props.fields.StaticGlobalMethods;

public class RestartRencoder implements Runnable{

	private ShutdownRencoder closeprevious = null;
	private boolean runspecial=false;
	private File executorupdatefile=null;

	public RestartRencoder(ShutdownRencoder shutdown){
		closeprevious=shutdown;
	}

	@Override
	public void run() {
		if(this.runspecial){
			restartRencoderSpecial();
		}
		else{
			
				if(!StaticGlobalMethods.existRestartTag())
					try {
						StaticGlobalMethods.setRestartTag();
					} catch (IOException e) {
						Logger.error(e);
					}
				Thread t = new Thread(closeprevious);
				t.run();
				System.exit(0);
			
		}

	}

	public void setRunSpecial(File execupdate){
		this.runspecial=true;
		this.executorupdatefile=execupdate;
	}

	public void restartRencoderSpecial(){
		Logger.info("Starting update process [rencoderexec.jar]");
		File replace = new File(OSystem.getCurrentDir()+OSystem.getSystemSeparator()+"rencoderexec.jar");
		StringBuilder cmd = new StringBuilder();
		if(OSystem.isLinux())
			cmd.append(System.getProperty("java.home") + File.separator + "bin" + File.separator + "java ");
		else
			cmd.append("java ");
		for (String jvmArg : ManagementFactory.getRuntimeMXBean().getInputArguments()) {
			cmd.append(jvmArg + " ");
		}

		cmd.append("-Duser.dir="+OSystem.getCurrentDir()+" ");

		cmd.append("-cp ").append(ManagementFactory.getRuntimeMXBean().getClassPath()).append(" ");
		cmd.append("pt.ornrocha.rencoder.gui.execute.ExecRencoder");

		Logger.info("running process: "+cmd);
		try {
			Runtime.getRuntime().exec(cmd.toString());
			if(this.executorupdatefile!=null && executorupdatefile.exists() && replace.exists()){

				Logger.info("Replacing file...");
				boolean deleting=replace.delete();

				if(!deleting)
					FileUtils.forceDelete(replace);
				Logger.info("Old rencoderexec.jar was deleted");
				Files.copy(executorupdatefile.toPath(), replace.toPath());
				Logger.info("rencoderexec_update.jar was copied");
				executorupdatefile.delete();
			}

		} catch (IOException e) {
			Logger.info("rencoderexec.jar replacing was failed");
			Logger.error(e);
		}
		Logger.info("rencoderexec.jar was replaced successfully");

		System.exit(0);
	}



//	public void restartMacOS() {
//		StringBuilder cmd = new StringBuilder();
//
//		cmd.append("java ");
//		for (String jvmArg : ManagementFactory.getRuntimeMXBean().getInputArguments()) {
//			cmd.append(jvmArg + " ");
//		}
//		cmd.append("-Duser.dir="+StaticGlobalMethods.getUserDirMacOS()+" ");
//		cmd.append("-cp ").append(ManagementFactory.getRuntimeMXBean().getClassPath()).append(" ");
//		cmd.append("execute.ExecORSVC");
//
//		try {
//			Runtime.getRuntime().exec(cmd.toString());
//		} catch (IOException e) {
//			Logger.error(e);
//		}
//		System.exit(0);
//	}



}
