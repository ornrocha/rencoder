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
import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.DefaultEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;

// TODO: Auto-generated Javadoc
/**
 * The Class VideoXvidEncodingInfoContainer.
 */
public class VideoTheoraEncodingInfoContainer extends DefaultEncodingInfoContainer {

	/**
	 * Instantiates a new video xvid encoding info container.
	 */
	public VideoTheoraEncodingInfoContainer() {
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
		this.videocodec = VideoCodecs.THEORA;
		this.videoContainer = VideoContainers.OGG;
		this.audiocodec = AudioCodecs.VORBIS;
		this.useAudioCBR = true;
		this.audioConstantBitrateValue = "128k";
		this.videoVBRQuality = new IndexedHashMap<String, Integer>() {
			{
				put(VideoCodecs.THEORA.toString(), 4);
			}
		};
		;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.MainVideoEncodingInfoContainer#clone()
	 */
	@Override
	public VideoTheoraEncodingInfoContainer clone() {
		VideoTheoraEncodingInfoContainer clone = new VideoTheoraEncodingInfoContainer();
		clone = (VideoTheoraEncodingInfoContainer) this.copyCommoninfoofcontainer(clone);

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
		return Arrays.stream(VideoCodecs.THEORA.supportsOutputFormats())
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	protected VideoContainers getpreferredFormat() {
		return VideoContainers.OGG;
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
		return "Theora";
	}

}
