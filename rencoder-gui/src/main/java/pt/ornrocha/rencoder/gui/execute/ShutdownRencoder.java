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

import org.pmw.tinylog.Logger;

public class ShutdownRencoder implements Runnable{


	private Thread runningrencoder=null;


	public ShutdownRencoder(){

	}

	public ShutdownRencoder(Thread run){
		this.runningrencoder=run;
	}

	public void setRunProcess(Thread proc){
		this.runningrencoder=proc;
	}

	@Override
	public void run(){
		Logger.info("Shutdown of Rencoder... bye");
		try {
			this.runningrencoder.interrupt();
		} catch (Exception e) {
			Logger.error(e);
		}

	}
}
