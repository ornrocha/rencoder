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
package pt.ornrocha.rencoder.helpers.props.managers;

import org.apache.commons.configuration.PropertiesConfiguration;

import pt.ornrocha.rencoder.helpers.props.fields.StaticVideoEncoderFields;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;
import pt.ornrocha.rencoder.mediafiles.files.auxiliar.ProcessFilesAux;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;

// TODO: Auto-generated Javadoc
/**
 * The Class GeneralEncodingPropertiesManager.
 */
public class GeneralEncodingPropertiesManager {
	
	
	/** The configs. */
	protected PropertiesConfiguration configs=null;
	
	/** The info container. */
	protected IGeneralVideoEncInfoContainer infocontainer=null;
	
	
	
	/**
	 * Instantiates a new general encoding properties manager.
	 */
	public GeneralEncodingPropertiesManager(){
		
		this.configs=PropertiesWorker.loadPropertiesRelativePath(StaticVideoEncoderFields.GENERALSETTINGSFILEPATH);
		this.infocontainer=EncodingProfileManager.extractVideoEncInformation(configs,true);
		this.infocontainer.setOutputFolder(ProcessFilesAux.getOutputFilesFolderPath());
		
	}
	
	
	/**
	 * Gets the general encoding info container.
	 *
	 * @return the gen enc info container
	 */
	public IGeneralVideoEncInfoContainer getGenEncInfoContainer(){
		return this.infocontainer;
	}
	
	
	
	/**
	 * Gets the output save path.
	 *
	 * @return the output save path
	 */
	public String getOutputSavePath(){
		
		return this.infocontainer.getOutputFolder();
	}
	
	/**
	 * Sets the new output save path.
	 *
	 * @param path the new new output save path
	 */
	public void setNewOutputSavePath(String path){
		this.infocontainer.setOutputFolder(path);
	}
	
    /**
     * Sets the general video encoding info container.
     *
     * @param container the new i general video enc info container
     */
    public void setIGeneralVideoEncInfoContainer(IGeneralVideoEncInfoContainer container){
    	this.infocontainer=container;
    }
	
	

	

}
