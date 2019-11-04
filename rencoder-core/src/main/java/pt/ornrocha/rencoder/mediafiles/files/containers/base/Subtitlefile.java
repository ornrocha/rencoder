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

import java.io.File;


// TODO: Auto-generated Javadoc
/**
 * The Class Subtitlefile.
 */
public class Subtitlefile extends BaseFile{
    
	/** The Constant type. */
	private static final String type = "subtitle";
	
	/** The selected to use. */
	private boolean selectedToUse=false;
	
	/** The language. */
	private String language="eng";
	
	/**
	 * Instantiates a new subtitlefile.
	 *
	 * @param path the path
	 */
	public Subtitlefile(String path) {
		super(path);
		
	}
	
	
	public Subtitlefile(File file){
		super(file.getAbsolutePath());
	}
	
	
	/**
	 * Select to use.
	 *
	 * @param bol the bol
	 */
	public void selectToUse(boolean bol){
		this.selectedToUse=bol;
	}
	
	/**
	 * Checks if is to use.
	 *
	 * @return true, if is to use
	 */
	public boolean isToUse(){
		return selectedToUse;
	}
	
	
	

	/**
	 * Gets the language.
	 *
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}


	/**
	 * Sets the language.
	 *
	 * @param language the new language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}


	/* (non-Javadoc)
	 * @see filetreatment.files.BaseFile#getfiletype()
	 */
	public String getfiletype() {
		// TODO Auto-generated method stub
		return type;
	}
	
	
	
	

}
