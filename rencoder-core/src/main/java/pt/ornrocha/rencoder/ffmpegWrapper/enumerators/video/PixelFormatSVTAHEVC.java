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
 * The Enum PixelFormat.
 */
public enum PixelFormatSVTAHEVC {

  yuv420p, yuv420p10le, yuv422p, yuv422p10le,  yuv444p,  yuv444p10le;

  public static PixelFormatSVTAHEVC getPixelFormatFromString(String pixelformat) {
    if (pixelformat != null) {
      for (PixelFormatSVTAHEVC pformat : PixelFormatSVTAHEVC.values()) {
        if (pformat.toString().toLowerCase().equals(pixelformat.toLowerCase()))
          return pformat;
      }
    }
    return PixelFormatSVTAHEVC.yuv420p;
  }

}
