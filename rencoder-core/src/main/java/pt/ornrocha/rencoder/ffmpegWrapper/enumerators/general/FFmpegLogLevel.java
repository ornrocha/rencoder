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
package pt.ornrocha.rencoder.ffmpegWrapper.enumerators.general;

// TODO: Auto-generated Javadoc
/**
 * The Enum FFmpegLogLevel.
 */
public enum FFmpegLogLevel {

  /*
   * #define AV_LOG_QUIET -8 Print no output.
   * 
   * #define AV_LOG_PANIC 0 Something went really wrong and we will crash now.
   * 
   * #define AV_LOG_FATAL 8 Something went wrong and recovery is not possible.
   * 
   * #define AV_LOG_ERROR 16 Something went wrong and cannot losslessly be recovered.
   * 
   * #define AV_LOG_WARNING 24 Something somehow does not look correct.
   * 
   * #define AV_LOG_INFO 32 Standard information.
   * 
   * #define AV_LOG_VERBOSE 40 Detailed information.
   * 
   * #define AV_LOG_DEBUG 48
   * 
   */

  /** The panic. */
  PANIC {

    public String getLogLevelString() {
      return "level=0";
    }

    public String toString() {
      return "Panic";
    }

  },

  /** The fatal. */
  FATAL {

    public String getLogLevelString() {
      return "level=8";
    }

    public String toString() {
      return "Fatal";
    }

  },

  /** The error. */
  ERROR {

    public String getLogLevelString() {
      return "level=16";
    }

    public String toString() {
      return "Error";
    }

  },

  /** The warning. */
  WARNING {

    public String getLogLevelString() {
      return "level=24";
    }

    public String toString() {
      return "Warning";
    }

  },

  /** The info. */
  INFO {

    public String getLogLevelString() {
      return "level=32";
    }

    public String toString() {
      return "Info";
    }

  },

  /** The verbose. */
  VERBOSE {

    public String getLogLevelString() {
      return "level=40";
    }

    public String toString() {
      return "Verbose";
    }

  },

  /** The debug. */
  DEBUG {

    public String getLogLevelString() {
      return "level=48";
    }

    public String toString() {
      return "Debug";
    }

  };

  /**
   * Gets the log level string.
   *
   * @return the log level string
   */
  public String getLogLevelString() {
    return this.getLogLevelString();
  }

}
