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
package pt.ornrocha.rencoder.mediafiles.files.containers.maininfo;

import java.io.IOException;
import java.util.ArrayList;

import pt.ornrocha.rencoder.ffmpegWrapper.commands.FileInformationChecker;
import pt.ornrocha.rencoder.ffmpegWrapper.commands.FileInformationIOException;
import pt.ornrocha.rencoder.mediafiles.files.containers.streams.AudioStreamInfo;
import pt.ornrocha.rencoder.mediafiles.files.containers.streams.VideoStreamInfo;

// TODO: Auto-generated Javadoc
/**
 * The Class MediaInfoContainer.
 */
public class MediaInfoContainer {
	
      
	/** The video info. */
	protected VideoStreamInfo videoinfo=null;
	
	/** The audio streams info. */
	protected ArrayList<AudioStreamInfo> audiostreamsinfo =null;
	
    
	/**
	 * Instantiates a new media info container.
	 *
	 * @param filepath the filepath
	 * @throws FileInformationIOException 
	 * @throws IOException 
	 */
	public MediaInfoContainer(String filepath) throws IOException{
		getAllMovieInformation(filepath);
		
	}
	
	
	
	/**
	 * Gets the all movie information.
	 *
	 * @param filepath the filepath
	 * @return the all movie information
	 * @throws FileInformationIOException 
	 * @throws IOException 
	 */
	protected void getAllMovieInformation(String filepath) throws IOException {
		
		FileInformationChecker infochecker = new FileInformationChecker(filepath);
		this.videoinfo=infochecker.getVideoStreamInfo();
		this.audiostreamsinfo=infochecker.getAudioStreamsInfo();

	}



	/**
	 * Gets the video info.
	 *
	 * @return the videoinfo
	 */
	public VideoStreamInfo getVideoinfo() {
		return videoinfo;
	}



	/**
	 * Gets the audio streams info.
	 *
	 * @return the audiostreamsinfo
	 */
	public ArrayList<AudioStreamInfo> getAudiostreamsinfo() {
		return audiostreamsinfo;
	}
	
	
	/**
	 * Gets the first audio stream.
	 *
	 * @return the first audio stream
	 */
	public AudioStreamInfo getfirstAudioStream(){
		if(this.audiostreamsinfo!=null)
		  return this.audiostreamsinfo.get(0);
		else
			return null;
	}
	
	
	
	
	
	
	

}
