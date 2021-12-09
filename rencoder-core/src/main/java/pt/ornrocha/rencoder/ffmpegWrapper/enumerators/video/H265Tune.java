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
 * The Enum H265Tune.
 */
public enum H265Tune {

  /** The none. */
  none {
    public String toString() {
      return "none";
    }
  },

  /** The psnr. */
  psnr {
    public String toString() {
      return "psnr";
    }
  },

  /** The ssim. */
  ssim {
    public String toString() {
      return "ssim";
    }
  },

  /** The grain. */
  grain {
    public String toString() {
      return "grain";
    }
  },

  /** The zerolatency. */
  zerolatency {
    public String toString() {
      return "zero-latency";
    }
  },

  /** The fastdecode. */
  fastdecode {
    public String toString() {
      return "fast-decode";
    }
  };

  public static H265Tune getH265TuneFromString(String tune) {
    if (tune != null) {
      for (H265Tune t : H265Tune.values()) {
        if (tune.toString().toLowerCase().equals(t.toString().toLowerCase()))
          return t;
      }
    }
    return H265Tune.none;
  }

}
