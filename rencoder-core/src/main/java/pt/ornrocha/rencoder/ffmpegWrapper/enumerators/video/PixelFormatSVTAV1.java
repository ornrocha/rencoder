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
public enum PixelFormatSVTAV1 {

  yuv420p, yuv420p10le;

  public static PixelFormatSVTAV1 getPixelFormatFromString(String pixelformat) {
    if (pixelformat != null) {
      for (PixelFormatSVTAV1 pformat : PixelFormatSVTAV1.values()) {
        if (pformat.toString().toLowerCase().equals(pixelformat.toLowerCase()))
          return pformat;
      }
    }
    return PixelFormatSVTAV1.yuv420p;
  }

}
