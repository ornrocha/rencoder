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
import java.util.ArrayList;

public class MultiFileInformationIOException extends IOException {

	private static final long serialVersionUID = 1L;
	private ArrayList<FileInformationIOException> exceptions = null;

	public MultiFileInformationIOException() {

	}

	public MultiFileInformationIOException(FileInformationIOException exception) {
		this.exceptions = new ArrayList<>();
		exceptions.add(exception);
	}

	public void addException(FileInformationIOException exception) {
		if (this.exceptions == null)
			this.exceptions = new ArrayList<>();
		exceptions.add(exception);
	}

	public ArrayList<String> getErrorsInFiles() {
		ArrayList<String> res = new ArrayList<>();
		if (exceptions != null) {
			for (FileInformationIOException exc : exceptions) {
				res.add(exc.getErrorFile());
			}
		}
		return res;
	}

	@Override
	public String getMessage() {
		if (exceptions != null) {
			String msg = "Errors ";
			for (FileInformationIOException exc : exceptions) {
				msg = msg + exc.getErrorFile() + " ";
			}
			return msg;
		} else
			return "Error reading files";
	}

}
