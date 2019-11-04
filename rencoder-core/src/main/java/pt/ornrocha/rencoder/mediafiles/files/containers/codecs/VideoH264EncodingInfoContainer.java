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
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.PixelFormat;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.ProfilesH264;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoContainers;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoLevel;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.X26xPresets;
import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.helpers.props.fields.StaticFFmpegFields;
import pt.ornrocha.rencoder.helpers.props.fields.StaticVideoEncoderFields;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;
import pt.ornrocha.rencoder.mediafiles.files.containers.extrainfocontainer.H264ExtraInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.DefaultEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;

// TODO: Auto-generated Javadoc
/**
 * The Class VideoH264EncodingInfoContainer.
 */
public class VideoH264EncodingInfoContainer extends DefaultEncodingInfoContainer {

    /** The preset. */
    protected X26xPresets preset = X26xPresets.none;

    /** The profile. */
    protected ProfilesH264 profile = ProfilesH264.none;

    /** The pixelformat. */
    protected PixelFormat pixelformat = PixelFormat.auto;

    /** The constant quantization. */
    protected int constantQuantization = -1;

    /** The fastfirstpass. */
    protected boolean fastfirstpass = false;

    protected VideoLevel level = VideoLevel.NONE;

   // protected H264ExtraInfoContainer extrainfocont = null;

    /**
     * Instantiates a new video h264 encoding info container.
     */
    public VideoH264EncodingInfoContainer() {
	super();
	setDefaultInfo();

    }

    @Override
    public void setDefaultInfo() {
	this.videocodec = VideoCodecs.H264;
	this.videoContainer = VideoContainers.MKV;
	this.videoVBRQuality = new IndexedHashMap<String, Integer>() {
	    {
		put(VideoCodecs.H264.toString(), 23);
	    }
	};
	this.audiocodec = AudioCodecs.LCAAC;
	this.useAudioCBR = true;
	this.audioConstantBitrateValue = "128k";
	this.extrainfocont = null;
    }

    public X26xPresets getPreset() {
	return preset;
    }

    public void setPreset(X26xPresets preset) {
	this.preset = preset;
    }

    public ProfilesH264 getProfile() {
	return profile;
    }

    public void setProfile(ProfilesH264 profile) {
	this.profile = profile;
    }

    public PixelFormat getPixelformat() {
	return pixelformat;
    }

    public void setPixelformat(PixelFormat pixelformat) {
	this.pixelformat = pixelformat;
    }

    public int getConstantQuantization() {
	return constantQuantization;
    }

    public void setConstantQuantization(int constantQuantization) {
	this.constantQuantization = constantQuantization;
    }

    public VideoLevel getLevel() {
	return level;
    }

    public void setLevel(VideoLevel level) {
	this.level = level;
    }

    public boolean isFastfirstpass() {
	return fastfirstpass;
    }

    public void setFastfirstpass(boolean fastfirstpass) {
	this.fastfirstpass = fastfirstpass;
    }

//    public H264ExtraInfoContainer getExtrainfocont() {
//	return (H264ExtraInfoContainer) extrainfocont;
//    }
//
//    public void setExtrainfocont(H264ExtraInfoContainer extrainfocont) {
//	this.extrainfocont = extrainfocont;
//    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
	VideoH264EncodingInfoContainer v = new VideoH264EncodingInfoContainer();

    }

    @Override
    public VideoH264EncodingInfoContainer clone() {

	VideoH264EncodingInfoContainer clone = new VideoH264EncodingInfoContainer();
	clone = (VideoH264EncodingInfoContainer) this.copyCommoninfoofcontainer(clone);
	clone = (VideoH264EncodingInfoContainer) this.copySpecificInfocontainer(clone);

	return clone;
    }

    @Override
    public IGeneralVideoEncInfoContainer copySpecificInfocontainer(IGeneralVideoEncInfoContainer cont) {

	((VideoH264EncodingInfoContainer) cont).setPreset(this.preset);
	((VideoH264EncodingInfoContainer) cont).setProfile(this.profile);
	((VideoH264EncodingInfoContainer) cont).setPixelformat(this.pixelformat);
	((VideoH264EncodingInfoContainer) cont).setConstantQuantization(this.constantQuantization);
	((VideoH264EncodingInfoContainer) cont).setFastfirstpass(this.fastfirstpass);
	((VideoH264EncodingInfoContainer) cont).setLevel(this.level);
	if (this.extrainfocont != null) {
	    ((VideoH264EncodingInfoContainer) cont).setExtrainfocont((H264ExtraInfoContainer) extrainfocont.clone());
	}

	return cont;
    }

    @Override
    protected ArrayList<VideoContainers> getSupportedFormats() {
	return Arrays.stream(VideoCodecs.H264.supportsOutputFormats()).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    protected VideoContainers getpreferredFormat() {
	return VideoContainers.MKV;
    }

    @Override
    public void setConfigurations(PropertiesConfiguration props) {

	setPreset(X26xPresets
		.getX26xPresetsFromString(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOPRESET)));
	setProfile(ProfilesH264.getProfilesH264FromString(
		PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOPROFILE)));
	setFastfirstpass(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.H264FASTFIRSTPASS, false));
	setPixelformat(PixelFormat.getPixelFormatFromString(
		PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOPIXELFORMAT)));

	Integer qp = (Integer) PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOCONSTQUANT,
		null);
	if (qp != null)
	    setConstantQuantization(qp);

	setLevel(VideoLevel.getVideoLevelFromString(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOLEVEL)));
	setExtrainfocont(H264ExtraInfoContainer.loadH264ExtraInfoContainer(props));

	setVideoConfigurations(props);

	setAudioConfigurations(props);
	setSubtitleConfigurations(props);

    }

    @Override
    protected void saveSpecificCodecProperties(PropertiesConfiguration prop) {

	prop.setProperty(StaticVideoEncoderFields.VIDEOCONSTQUANT, String.valueOf(getConstantQuantization()));
	if (isFastfirstpass()) {
	    prop.setProperty(StaticVideoEncoderFields.H264FASTFIRSTPASS, "true");
	} else
	    prop.setProperty(StaticVideoEncoderFields.H264FASTFIRSTPASS, "false");

	prop.setProperty(StaticVideoEncoderFields.VIDEOPRESET, getPreset().toString());
	prop.setProperty(StaticVideoEncoderFields.VIDEOPROFILE, getProfile().toString());
	prop.setProperty(StaticVideoEncoderFields.VIDEOPIXELFORMAT, getPixelformat().toString());
	prop.setProperty(StaticVideoEncoderFields.VIDEOLEVEL, getLevel().toString());

	if (getExtrainfocont() != null) {
	    getExtrainfocont().saveConfigurationToFileProperties(prop);
	}

    }

    @Override
    public ArrayList<String> getSpecificVideoEncoderParameters(ArrayList<String> appendcmds) {
	ArrayList<String> params = new ArrayList<>();

	if (!getPreset().equals(X26xPresets.none)) {
		params.add(StaticFFmpegFields.PRESET);
		params.add(getPreset().toString());
	}

	if (!getProfile().equals(ProfilesH264.none)) {
		params.add(StaticFFmpegFields.PROFILE);
		params.add(getProfile().toString());
	}

	if (!getLevel().equals(VideoLevel.NONE)) {
		params.addAll(getLevel().getffmpegcommand());
	}

	if (!getPixelformat().equals(PixelFormat.auto)) {
		params.add(StaticFFmpegFields.pixelformat);
		params.add(getPixelformat().toString());
	}

	if (getConstantQuantization() != -1) {
		params.add(StaticFFmpegFields.x264constquant);
		params.add(String.valueOf(getConstantQuantization()));
	}

	params.addAll(appendcmds);

	if (getExtrainfocont() != null)
		params.addAll(getExtrainfocont().getFFmpegCmds());

	return params;
    }

    @Override
    public boolean useSpecificVideoEncodingParameters() {
	return true;
    }

}
