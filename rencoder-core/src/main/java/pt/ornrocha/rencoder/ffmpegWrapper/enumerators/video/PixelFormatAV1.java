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
package pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video;

// TODO: Auto-generated Javadoc
/**
 * The Enum PixelFormat.
 */
public enum PixelFormatAV1 {

    yuv420p, yuv422p, yuv444p, yuv420p10le, yuv422p10le, yuv444p10le, yuv420p12le, yuv422p12le, yuv444p12le;

	public static PixelFormatAV1 getPixelFormatFromString(String pixelformat) {
		if (pixelformat != null) {
			for (PixelFormatAV1 pformat : PixelFormatAV1.values()) {
				if (pformat.toString().toLowerCase().equals(pixelformat.toLowerCase()))
					return pformat;
			}
		}
		return PixelFormatAV1.yuv420p;
	}

}
