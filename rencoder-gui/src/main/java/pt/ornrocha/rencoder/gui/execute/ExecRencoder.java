/*
 * Copyright 2015
 *
 * This is free software: you can redistribute it and/or modify it under the terms of the GNU Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 * 
 * This code is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Public License for more details.
 * 
 * You should have received a copy of the GNU Public License along with this code. If not, see
 * http://www.gnu.org/licenses/
 * 
 * Created by Orlando Rocha
 */
package pt.ornrocha.rencoder.gui.execute;

import javax.swing.SwingUtilities;

import pt.ornrocha.rencoder.gui.MainGUIExec;
import pt.ornrocha.rencoder.helpers.props.logs.LogManager;


public class ExecRencoder {


  private MainGUIExec runprocess = null;
  private ShutdownRencoder shutdown = null;
  private RestartRencoder restart = null;

  public ExecRencoder() {

    this.shutdown = new ShutdownRencoder();
    this.restart = new RestartRencoder();
    this.runprocess = new MainGUIExec(shutdown, restart);


  }

  public void run() {


    Thread run = new Thread() {
      public void run() {
        SwingUtilities.invokeLater(runprocess);
      }
    };

    this.shutdown.setRunProcess(run);
    run.start();


  }

  public static void main(String[] args) {
    LogManager.initialiseLogger();
    ExecRencoder exec = new ExecRencoder();
    exec.run();


  }


}
