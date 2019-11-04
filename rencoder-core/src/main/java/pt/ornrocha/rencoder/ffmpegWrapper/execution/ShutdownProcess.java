/*
 * Copyright 2014
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
package pt.ornrocha.rencoder.ffmpegWrapper.execution;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JCheckBox;

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.helpers.osystem.OSystem;

// TODO: Auto-generated Javadoc
/**
 * The Class ShutdownProcess.
 */
public class ShutdownProcess implements Runnable, ActionListener {

	/** The runshutdown. */
	private boolean runshutdown = false;

	/** The cancel. */
	private boolean cancel = false;

	/**
	 * Sets the action listener.
	 *
	 * @param listener the new action listener
	 */
	public void setActionListener(ActionListener listener) {
		this.setActionListener(listener);
	}

	/**
	 * Gets the listener.
	 *
	 * @return the listener
	 */
	public ActionListener getListener() {
		return this;
	}

	public boolean isRunShutdown() {
		return this.runshutdown;
	}

	public void setRunShutdown(boolean bol) {
		runshutdown = bol;
	}

	/**
	 * Sets the cancel.
	 *
	 * @param bol the new cancel
	 */
	public void setCancel(boolean bol) {
		this.cancel = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JCheckBox check = (JCheckBox) e.getSource();
		if (OSystem.isAdmin() || OSystem.isWindows()) {
			if (check.isSelected()) {
				this.runshutdown = true;
			} else
				this.runshutdown = false;
		} else {
			this.runshutdown = false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		if (runshutdown && !cancel) {
			try {
				System.out.println("shutdown now");
				String shutdownCommand = OSystem.shutdown();
				Runtime.getRuntime().exec(shutdownCommand);
				System.exit(0);
			} catch (RuntimeException | IOException e) {
				Logger.error(e);
			}
		}

	}

}
