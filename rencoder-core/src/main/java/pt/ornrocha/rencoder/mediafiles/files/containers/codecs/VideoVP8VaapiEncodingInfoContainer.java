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
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.DefaultEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;

// TODO: Auto-generated Javadoc
/**
 * The Class VideoVP8EncodingInfoContainer.
 */
public class VideoVP8VaapiEncodingInfoContainer extends DefaultEncodingInfoContainer {


    protected boolean low_power = false;


    protected int loop_filter_level = 16;


    protected int loop_filter_sharpness = 4;


    /**
     * Instantiates a new video v p8 encoding info container.
     */
    public VideoVP8VaapiEncodingInfoContainer() {
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
    public void setDefaultInfo() {
	this.videocodec = VideoCodecs.VP8VAAPI;
	this.videoContainer = VideoContainers.WEBM;
	this.videoVBRQuality = new IndexedHashMap<String, Integer>() {
	    {
		put(VideoCodecs.VP8VAAPI.toString(), 20);
	    }
	};
	;
	this.audiocodec = AudioCodecs.VORBIS;
	this.useAudioCBR = true;
	this.audioConstantBitrateValue = "128k";
    }





    public boolean isLow_power() {
	return low_power;
    }

    public void setLow_power(boolean low_power) {
	this.low_power = low_power;
    }

    public int getLoop_filter_level() {
	return loop_filter_level;
    }

    public void setLoop_filter_level(int loop_filter_level) {
	this.loop_filter_level = loop_filter_level;
    }

    public int getLoop_filter_sharpness() {
	return loop_filter_sharpness;
    }

    public void setLoop_filter_sharpness(int loop_filter_sharpness) {
	this.loop_filter_sharpness = loop_filter_sharpness;
    }

    /*
     * (non-Javadoc)
     * 
     * @see filetreatment.files.containers.MainVideoEncodingInfoContainer#clone()
     */
    @Override
    public DefaultEncodingInfoContainer clone() {
	VideoVP8VaapiEncodingInfoContainer clone = new VideoVP8VaapiEncodingInfoContainer();
	clone = (VideoVP8VaapiEncodingInfoContainer) this.copyCommoninfoofcontainer(clone);
	clone = (VideoVP8VaapiEncodingInfoContainer) this.copySpecificInfocontainer(clone);

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

	((VideoVP8VaapiEncodingInfoContainer) newcont).setLow_power(low_power);

	((VideoVP8VaapiEncodingInfoContainer) newcont).setLoop_filter_level(loop_filter_level);

	((VideoVP8VaapiEncodingInfoContainer) newcont).setLoop_filter_sharpness(loop_filter_sharpness);

	return newcont;
    }

    @Override
    protected ArrayList<VideoContainers> getSupportedFormats() {
	return Arrays.stream(VideoCodecs.VP8.supportsOutputFormats()).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    protected VideoContainers getpreferredFormat() {
	return VideoContainers.WEBM;
    }

    @Override
    public void setConfigurations(PropertiesConfiguration props) {

	setVideoConfigurations(props);

	setLow_power(PropertiesWorker.checkProperty(props, "vpxvaapi.low.power", false));
	setLoop_filter_level(PropertiesWorker.checkProperty(props, "vpxvaapi.loop.filter.level",0, 63, 16));
	setLoop_filter_sharpness(PropertiesWorker.checkProperty(props, "vpxvaapi.loop.filter.sharpness", 0, 15, 4));


	setAudioConfigurations(props);
	setSubtitleConfigurations(props);
    }

    @Override
    protected void saveSpecificCodecProperties(PropertiesConfiguration prop) {

	prop.setProperty("vpxvaapi.low.power", String.valueOf(low_power));
	prop.setProperty("vpxvaapi.loop.filter.level", String.valueOf(loop_filter_level));
	prop.setProperty("vpxvaapi.loop.filter.sharpness", String.valueOf(loop_filter_sharpness));

    }

    @Override
    public ArrayList<String> getSpecificVideoEncoderParameters(ArrayList<String> appendcmds) {
	ArrayList<String> vpxparam = new ArrayList<>();

	if(low_power) {
	    vpxparam.add("low_power");
	    vpxparam.add(String.valueOf(true));
	}
	if (loop_filter_level > -1 && loop_filter_level !=16 && loop_filter_level < 64) {
	    vpxparam.add("-loop_filter_level");
	    vpxparam.add(String.valueOf(loop_filter_level));

	}
	if (loop_filter_sharpness > -1 && loop_filter_sharpness !=4 && loop_filter_sharpness < 16) {
	    vpxparam.add("-loop_filter_sharpness");
	    vpxparam.add(String.valueOf(loop_filter_sharpness));

	}

	return vpxparam;
    }

    @Override
    public boolean useSpecificVideoEncodingParameters() {
	return true;
    }



}
