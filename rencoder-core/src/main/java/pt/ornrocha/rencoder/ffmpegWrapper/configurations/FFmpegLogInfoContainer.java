/*
 * Copyright 2014
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
package pt.ornrocha.rencoder.ffmpegWrapper.configurations;

import java.io.File;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.general.FFmpegLogLevel;
import pt.ornrocha.rencoder.helpers.props.fields.StaticGlobalFields;

public class FFmpegLogInfoContainer {

  protected String logfilename;
  protected String logfilepath;
  protected FFmpegLogLevel loglevel;
  protected boolean secondpassonly = false;
  protected boolean active = false;

  public FFmpegLogInfoContainer(String logfilename) {

    this.logfilename = logfilename;
    this.logfilepath = new File(StaticGlobalFields.LOGSFOLDER).getAbsolutePath();
    this.loglevel = FFmpegLogLevel.DEBUG;
  }

  public FFmpegLogInfoContainer(String logfilename, FFmpegLogLevel loglevel) {

    this.logfilename = logfilename;
    this.logfilepath = new File(StaticGlobalFields.LOGSFOLDER).getAbsolutePath();
    this.loglevel = loglevel;
  }

  public FFmpegLogInfoContainer(String logfilename, String logfilepath, FFmpegLogLevel loglevel) {

    this.logfilename = logfilename;
    this.logfilepath = logfilepath;
    this.loglevel = loglevel;
  }

  public boolean isSecondpassonly() {
    return secondpassonly;
  }

  public void setSecondpassonly(boolean secondpassonly) {
    this.secondpassonly = secondpassonly;
  }

  public String getLogfilename() {
    return logfilename + ".log";
  }

  public void setLogfilename(String logfilename) {
    this.logfilename = logfilename;
  }

  public String getLogfilepath() {
    return logfilepath;
  }

  public void setLogfilepath(String logfilepath) {
    this.logfilepath = logfilepath;
  }

  public FFmpegLogLevel getLoglevel() {
    return loglevel;
  }

  public void setLoglevel(FFmpegLogLevel loglevel) {
    this.loglevel = loglevel;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

}
