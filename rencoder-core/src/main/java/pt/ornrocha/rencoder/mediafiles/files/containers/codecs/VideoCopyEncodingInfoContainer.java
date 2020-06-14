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
package pt.ornrocha.rencoder.mediafiles.files.containers.codecs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.configuration.PropertiesConfiguration;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoContainers;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.DefaultEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;

// TODO: Auto-generated Javadoc
/**
 * The Class VideoCopyEncodingInfoContainer.
 */
public class VideoCopyEncodingInfoContainer extends DefaultEncodingInfoContainer {

	/**
	 * Instantiates a new video copy encoding info container.
	 */
	public VideoCopyEncodingInfoContainer() {
		super();
		setDefaultInfo();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * filetreatment.files.containers.MainVideoEncodingInfoContainer#setDefaultInfo(
	 * )
	 */
	@Override
	public void setDefaultInfo() {
		this.videocodec = VideoCodecs.COPY;
		this.audiocodec = AudioCodecs.COPY;
		this.videoContainer = VideoContainers.SAMEASSOURCE;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.MainVideoEncodingInfoContainer#clone()
	 */
	@Override
	public DefaultEncodingInfoContainer clone() {
		VideoCopyEncodingInfoContainer clone = new VideoCopyEncodingInfoContainer();
		clone = (VideoCopyEncodingInfoContainer) this.copyCommoninfoofcontainer(clone);
		return clone;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * copySpecificInfocontainer(filetreatment.files.containers.interfaces.
	 * IGeneralVideoEncInfoContainer)
	 */
	@Override
	public IGeneralVideoEncInfoContainer copySpecificInfocontainer(IGeneralVideoEncInfoContainer newcont) {
		// TODO Auto-generated method stub
		return newcont;
	}

	@Override
	protected ArrayList<VideoContainers> getSupportedFormats() {
		return Arrays.stream(VideoCodecs.COPY.supportsOutputFormats()).collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	protected VideoContainers getpreferredFormat() {
		return VideoContainers.SAMEASSOURCE;
	}

	@Override
	public void setConfigurations(PropertiesConfiguration props) {
		setVideoConfigurations(props);

		setAudioConfigurations(props);
		setSubtitleConfigurations(props);

	}

	@Override
	protected void saveSpecificCodecProperties(PropertiesConfiguration prop) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<String> getSpecificVideoEncoderParameters(ArrayList<String> appendcmds) {
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public boolean useSpecificVideoEncodingParameters() {
	    return false;
	}

	@Override
	public String getContainerName() {
		// TODO Auto-generated method stub
		return "Copy";
	}

}
