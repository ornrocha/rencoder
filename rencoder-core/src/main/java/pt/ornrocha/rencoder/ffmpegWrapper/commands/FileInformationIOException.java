/*
 * Copyright 2015
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
package pt.ornrocha.rencoder.ffmpegWrapper.commands;

import java.io.IOException;

public class FileInformationIOException extends IOException {

	private static final long serialVersionUID = 1L;

	private String fileerror = null;
	private String msg = null;

	public FileInformationIOException(String fileerror) {
		this.fileerror = fileerror;
	}

	public FileInformationIOException(String fileerror, String msg) {
		this.fileerror = fileerror;
		this.msg = msg;
	}

	public String getErrorFile() {
		return this.fileerror;
	}

	@Override
	public String getMessage() {
		if (msg != null)
			return msg + " " + fileerror;
		else
			return fileerror;
	}
}
