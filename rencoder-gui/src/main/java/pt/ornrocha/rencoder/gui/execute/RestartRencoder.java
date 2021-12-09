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

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;

import pt.ornrocha.rencoder.helpers.osystem.OSystem;

public class RestartRencoder implements Runnable {


  public RestartRencoder() {}

  @Override
  public void run() {

    StringBuilder cmd = new StringBuilder();
    if (OSystem.isLinux())
      cmd.append(
          System.getProperty("java.home") + File.separator + "bin" + File.separator + "java ");
    else
      cmd.append("java ");
    for (String jvmArg : ManagementFactory.getRuntimeMXBean().getInputArguments()) {
      cmd.append(jvmArg + " ");
    }

    cmd.append("-Duser.dir=" + OSystem.getCurrentDir() + " ");

    cmd.append("-cp ").append(ManagementFactory.getRuntimeMXBean().getClassPath()).append(" ");
    cmd.append("pt.ornrocha.rencoder.gui.execute.ExecRencoder");

    try {
      Runtime.getRuntime().exec(cmd.toString());
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    System.exit(0);
  }



}
