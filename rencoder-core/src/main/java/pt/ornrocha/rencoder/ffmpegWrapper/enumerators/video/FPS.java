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
package pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video;

// TODO: Auto-generated Javadoc
/**
 * The Enum FPS.
 */
public enum FPS {

  /** The sameassource. */
  SAMEASSOURCE {

    public String getffmpegFPS() {
      return "Same as source";
    }

    public String getVideoFrameRateID() {
      return "Same as source";
    }

    public String toString() {
      return "Same as source";
    }

  },

  /** The PAl_25. */
  PAL_25 {

    public String getffmpegFPS() {
      return "25";
    }

    public String getVideoFrameRateID() {
      return "25";
    }

    public String toString() {
      return "25";
    }

  },

  /** The PAl_30. */
  PAL_30 {

    public String getffmpegFPS() {
      return "30";
    }

    public String getVideoFrameRateID() {
      return "30";
    }

    public String toString() {
      return "30";
    }

  },

  /** The NTSC_23_976. */
  NTSC_23_976 {

    public String getffmpegFPS() {
      return "24000/1001";
    }

    public String getVideoFrameRateID() {
      return "23.976";
    }

    public String toString() {
      return "23.976";
    }

  },

  /** The NTSC_29_97. */
  NTSC_29_97 {

    public String getffmpegFPS() {
      return "30000/1001";
    }

    public String getVideoFrameRateID() {
      return "29.97";
    }

    public String toString() {
      return "29.97";
    }

  };

  /**
   * Gets the ffmpeg fps.
   *
   * @return the ffmpeg fps
   */
  public String getffmpegFPS() {
    return this.getffmpegFPS();
  }

  /**
   * Gets the video frame rate id.
   *
   * @return the video frame rate id
   */
  public String getVideoFrameRateID() {
    return getVideoFrameRateID();
  }

}
