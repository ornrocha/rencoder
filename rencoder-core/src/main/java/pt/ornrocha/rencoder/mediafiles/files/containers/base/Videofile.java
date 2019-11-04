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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import pt.ornrocha.rencoder.ffmpegWrapper.commands.FileInformationIOException;
import pt.ornrocha.rencoder.helpers.props.managers.auxiliar.EncodingPropsAuxiliar;
import pt.ornrocha.rencoder.mediafiles.files.auxiliar.ProcessFilesAux;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.MediaInfoContainer;


// TODO: Auto-generated Javadoc
/**
 * The Class Videofile.
 */
public class Videofile extends BaseFile{
     
	
	/** The Constant type. */
	private static final String type = "video";
	
	/** The subtitles. */
	protected ArrayList<Subtitlefile> subtitles=null;
	
	/** The movie info container. */
	protected MediaInfoContainer movieinfocontainer=null;
	
	/** The movie encoding info container. */
	protected IGeneralVideoEncInfoContainer movieEncodingInfoContainer=null;
	
	/**
	 * Instantiates a new videofile.
	 *
	 * @param path the path
	 * @throws IOException 
	 */
	public Videofile(String path) throws IOException{
		super(path);
		this.movieinfocontainer=new MediaInfoContainer(path);
		
	}
	
	/**
	 * Instantiates a new videofile.
	 *
	 * @param path the path
	 * @param genInfoCont the gen info cont
	 * @throws FileInformationIOException 
	 * @throws IOException 
	 */
	public Videofile(String path, IGeneralVideoEncInfoContainer genInfoCont) throws IOException {
		super(path);

	    this.movieinfocontainer=new MediaInfoContainer(path);

		if(this.movieinfocontainer!=null)
		   this.movieEncodingInfoContainer=EncodingPropsAuxiliar.setMaxAllowedAudioSettings(genInfoCont,this.movieinfocontainer);
		
		
	}
	
	/**
	 * Instantiates a new video file.
	 *
	 * @param videofilepath the videofilepath
	 * @param subtitlefilepath the subtitlefilepath
	 * @throws IOException 
	 */
	public Videofile(String videofilepath, String subtitlefilepath) throws IOException{
		super(videofilepath);
		this.movieinfocontainer= new MediaInfoContainer(videofilepath);
		this.subtitles = new ArrayList<>();
		this.subtitles.add(new Subtitlefile(subtitlefilepath));
		
	}
	

	/* (non-Javadoc)
	 * @see filetreatment.files.BaseFile#getfiletype()
	 */
	@Override
	public String getfiletype() {

		return type;
	}

	/**
	 * Gets the subtitles.
	 *
	 * @return the subtitles
	 */
	public ArrayList<Subtitlefile> getSubtitles() {
		return this.subtitles;
	}

	/**
	 * Adds the subtitle from path.
	 *
	 * @param subtitlepath the subtitlepath
	 */
	public void addSubtitleFromPath(String subtitlepath) {
		if (this.subtitles==null)
			this.subtitles=new ArrayList<>();
		this.subtitles.add(new Subtitlefile(subtitlepath));	
	}
	
	/**
	 * Adds the subtitle object.
	 *
	 * @param subtitle the subtitle
	 */
	public void addSubtitleObject(Subtitlefile subtitle){
		if (this.subtitles==null)
			this.subtitles=new ArrayList<>();
		if(this.subtitles.size()<1)
			subtitle.selectToUse(true);
		this.subtitles.add(subtitle);	
		
	}
	
	/**
	 * Removes the subtitle.
	 *
	 * @param subindex the subindex
	 */
	public void RemoveSubtitle(int subindex){
		if(this.subtitles!=null)
			this.subtitles.remove(subindex);
	}
	
	/**
	 * Have subtitle.
	 *
	 * @return true, if successful
	 */
	public boolean haveSubtitle(){
		if (subtitles==null)
			return false;
		else if (subtitles!=null && subtitles.size()<1)
			return false;
		else
			return true;
					
	}
	 
	/**
	 * Contains subtitle.
	 *
	 * @param name the name
	 * @return true, if successful
	 */
	public boolean containsSubtitle(String name){
		
		if(this.subtitles!=null){
		for (Subtitlefile sub : this.subtitles) {
			if(sub.getName().equals(name))
				return true;
		  }
		   return false;
		}
		else
			return false;
		
	}
	
	/**
	 * Gets the main subtitle.
	 *
	 * @return the main subtitle
	 */
	public Subtitlefile getMainSubtitle(){
		if(subtitles!=null)
			return subtitles.get(0);
		else
			return null;
	}

	/**
	 * Gets the movie info container.
	 *
	 * @return the movieinfocontainer
	 */
	public MediaInfoContainer getMovieinfocontainer() {
		return movieinfocontainer;
	}
	
	/**
	 * Gets the encoding info container.
	 *
	 * @return the encoding info container
	 */
	public IGeneralVideoEncInfoContainer getEncodingInfoContainer(){
		return this.movieEncodingInfoContainer;
	}
	
	/**
	 * Sets the encoding info container.
	 *
	 * @param encinfocont the new encoding info container
	 */
	public void setEncodingInfoContainer(IGeneralVideoEncInfoContainer encinfocont){
		this.movieEncodingInfoContainer=encinfocont;
	}
	
	
	/**
	 * Change use subtitle state.
	 *
	 * @param subpos the subpos
	 * @param value the value
	 */
	public void changeUseSubtitleState(int subpos, boolean value){
		this.subtitles.get(subpos).selectToUse(value);
	}
	
	/**
	 * Gets the first selected subtitle file.
	 *
	 * @return the first selected subtitle file
	 */
	public Subtitlefile getFirstSelectedSubtitleFile(){
       
	 if(subtitles!=null){	
		for (Subtitlefile sub : this.subtitles) {
			if(sub.isToUse())
				return sub;
		}
	 }	
		return null;
	}
	
	/**
	 * Gets the selected subtitle files.
	 *
	 * @return the selected subtitle files
	 */
	public ArrayList<Subtitlefile> getSelectedSubtitleFiles(){
		ArrayList<Subtitlefile> outputsubs=null;

		if(subtitles!=null) {
			for (int i = 0; i < subtitles.size(); i++) {
				if(subtitles.get(i).isToUse()){
					if(outputsubs==null)
						outputsubs=new ArrayList<>();
					outputsubs.add(subtitles.get(i));
				}
			}
		}

		return outputsubs;

	}
	
	
	 /**
 	 * Move sub index up.
 	 *
 	 * @param index the index
 	 */
 	public void moveSubIndexUp(int index){
			if(index>0 && subtitles.size()>1){
			 Collections.swap(subtitles, index, index-1);
			}
		}
	 
	/**
	 * Move sub index down.
	 *
	 * @param index the index
	 */
	public void moveSubIndexDown(int index){
		 if(index>=0 && index+1<subtitles.size() && subtitles.size()>1){
			 Collections.swap(subtitles, index, index+1);
			}
	} 
	
	
	public String getNameWithoutExtensionAndFFmpegInvChars(){
		return ProcessFilesAux.replaceInvalidFFmpegChars(this.BaseName);
	}

}
