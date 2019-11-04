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
package pt.ornrocha.rencoder.mediafiles.files.containers.streams;

// TODO: Auto-generated Javadoc
/**
 * The Class StreamInfo.
 */
public abstract class StreamInfo {

	/** The number stream. */
	protected int numberstream;

	/** The codec type. */
	protected String codectype;

	/** The bitrate. */
	protected String bitrate;

	/**
	 * Instantiates a new stream info.
	 */
	public StreamInfo() {

	}

	/**
	 * Gets the number of stream.
	 *
	 * @return the numberstream
	 */
	public int getNumberstream() {
		return numberstream;
	}

	/**
	 * Sets the number of stream.
	 *
	 * @param numberstream the new numberstream
	 */
	public void setNumberstream(int numberstream) {
		this.numberstream = numberstream;
	}

	/**
	 * Gets the type of codec .
	 *
	 * @return the codectype
	 */
	public String getCodectype() {
		return codectype;
	}

	/**
	 * Sets the type of codec.
	 *
	 * @param codectype the new codectype
	 */
	public void setCodectype(String codectype) {
		this.codectype = codectype;
	}

	/**
	 * Gets the bitrate.
	 *
	 * @return the bitrate
	 */
	public String getBitrate() {
		return bitrate;
	}

	/**
	 * Sets the bitrate.
	 *
	 * @param bitrate the new bitrate
	 */
	public void setBitrate(String bitrate) {
		this.bitrate = bitrate;
	}

}
