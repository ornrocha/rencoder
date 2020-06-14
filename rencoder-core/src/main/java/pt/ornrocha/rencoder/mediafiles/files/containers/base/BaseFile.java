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
package pt.ornrocha.rencoder.mediafiles.files.containers.base;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FilenameUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseFile.
 */
public abstract class BaseFile {
	
	/** The file path. */
	protected String filepath;
	
	/** The name. */
	protected String name;
	
	/** The parent dirs. */
	protected String parentdir;
	
	/** The extension. */
	protected String extension;
	
	/** The path. */
	protected Path path = null;
	
	/** The Base name. */
	protected String BaseName;
	
	/**
	 * Instantiates a new base file.
	 *
	 * @param filepath the filepath
	 */
	public BaseFile(String filepath){
		if(filepath!=null) {
			this.filepath = filepath;
			this.path = Paths.get(filepath);
			this.name= this.path.getFileName().toString();
			this.parentdir = this.path.getParent().toString();
			this.extension=FilenameUtils.getExtension(this.filepath);
			this.BaseName=FilenameUtils.getBaseName(this.filepath);
		}

	}
	
	/**
	 * Gets the file path.
	 *
	 * @return the file path
	 */
	public String getFilePath(){
		return this.filepath;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Gets the name without extension.
	 *
	 * @return the name without extension
	 */
	public String getNameWithoutExtension(){
		return this.BaseName;
	}
	
	 /**
 	 * Gets the parent dirs.
 	 *
 	 * @return the parentdirs
 	 */
 	public String getParentdir() {
		return parentdir;
	}

	/**
	 * Gets the extension.
	 *
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public Path getPath() {
		return path;
	}

	/**
	 * Gets the file type.
	 *
	 * @return the filetype
	 */
	public abstract String getfiletype();
     
	 
}
