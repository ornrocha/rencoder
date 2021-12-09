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
package pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio;

import java.util.ArrayList;

import pt.ornrocha.rencoder.helpers.props.fields.StaticFFmpegFields;

// TODO: Auto-generated Javadoc
/**
 * The Enum AudioChannels.
 */
public enum AudioChannels {

  /** The surround. */
  SURROUND {

    public int getchannels() {
      return 6;
    }

    public ArrayList<String> getffmpegcommand() {
      ArrayList<String> cmd = new ArrayList<>();
      cmd.add(StaticFFmpegFields.audiochannels);
      cmd.add("6");
      return cmd;
    }

    public int getLevelTag() {
      return 0;
    }

    public String toString() {
      return "5.1";
    }

  },

  /** The stereo. */
  STEREO {

    public int getchannels() {
      return 2;
    }

    public ArrayList<String> getffmpegcommand() {
      ArrayList<String> cmd = new ArrayList<>();
      cmd.add(StaticFFmpegFields.audiochannels);
      cmd.add("2");
      return cmd;
    }

    public int getLevelTag() {
      return 1;
    }

    public String toString() {
      return "stereo";
    }

  },

  /** The mono. */
  MONO {

    public int getchannels() {
      return 1;
    }

    public ArrayList<String> getffmpegcommand() {
      ArrayList<String> cmd = new ArrayList<>();
      cmd.add(StaticFFmpegFields.audiochannels);
      cmd.add("1");
      return cmd;
    }

    public int getLevelTag() {
      return 2;
    }

    public String toString() {
      return "mono";
    }

  };

  /**
   * Gets the channels.
   *
   * @return the channels
   */
  public int getchannels() {
    return this.getchannels();
  }

  /**
   * Gets the level tag.
   *
   * @return the level tag
   */
  public int getLevelTag() {
    return this.getLevelTag();
  }

  /**
   * Gets the ffmpeg commandline.
   *
   * @return the ffmpegcommand
   */
  public ArrayList<String> getffmpegcommand() {
    return this.getffmpegcommand();
  }

}
