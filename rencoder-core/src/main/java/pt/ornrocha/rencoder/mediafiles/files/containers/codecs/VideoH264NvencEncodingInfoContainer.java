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
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.H264NvencRateControlPreset;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.NvencPresets;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.PixelFormatNvenc;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.ProfilesH264Nvenc;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoContainers;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoLevel;
import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.helpers.props.fields.StaticFFmpegFields;
import pt.ornrocha.rencoder.helpers.props.fields.StaticVideoEncoderFields;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;
import pt.ornrocha.rencoder.mediafiles.files.containers.extrainfocontainer.H264NvencExtraInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.DefaultEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;

// TODO: Auto-generated Javadoc
/**
 * The Class VideoH264EncodingInfoContainer.
 */
public class VideoH264NvencEncodingInfoContainer extends DefaultEncodingInfoContainer {

	/** The preset. */
	protected NvencPresets preset = NvencPresets.none;

	/** The profile. */
	protected ProfilesH264Nvenc profile = ProfilesH264Nvenc.main;

	/** The pixelformat. */
	protected PixelFormatNvenc pixelformat = PixelFormatNvenc.yuv420p;

	protected H264NvencRateControlPreset ratecontrolpreset = H264NvencRateControlPreset.none;

	/** The constant quantization. */
	// protected int qp = -1;

	protected VideoLevel level = VideoLevel.NONE;

	//protected H264NvencExtraInfoContainer extrainfocont = null;

	/**
	 * Instantiates a new video h264 encoding info container.
	 */
	public VideoH264NvencEncodingInfoContainer() {
		super();
		setDefaultInfo();

	}

	@Override
	public void setDefaultInfo() {
		this.videocodec = VideoCodecs.H264NVENC;
		this.videoContainer = VideoContainers.MKV;
		this.videoVBRQuality = new IndexedHashMap<String, Integer>() {
			{
				put(VideoCodecs.H264NVENC.toString(), 23);
			}
		};
		;
		this.audiocodec = AudioCodecs.LCAAC;
		this.useAudioCBR = true;
		this.audioConstantBitrateValue = "128k";
		// this.extrainfocont=null;
	}

	public ProfilesH264Nvenc getProfile() {
		return profile;
	}

	public void setProfile(ProfilesH264Nvenc profile) {
		this.profile = profile;
	}

	public PixelFormatNvenc getPixelformat() {
		return pixelformat;
	}

	public void setPixelformat(PixelFormatNvenc pixelformat) {
		this.pixelformat = pixelformat;
	}

//	public int getConstantQuantization() {
//		return qp;
//	}
//
//	public void setConstantQuantization(int constantQuantization) {
//		this.qp = constantQuantization;
//	}

	public NvencPresets getPreset() {
		return preset;
	}

	public void setPreset(NvencPresets preset) {
		this.preset = preset;
	}

	public VideoLevel getLevel() {
		return level;
	}

	public void setLevel(VideoLevel level) {
		this.level = level;
	}

//	public H264NvencExtraInfoContainer getExtrainfocont() {
//		return extrainfocont;
//	}
//
//	public void setExtrainfocont(H264NvencExtraInfoContainer extrainfocont) {
//		this.extrainfocont = extrainfocont;
//	}

	public H264NvencRateControlPreset getRateControlPreset() {
		return ratecontrolpreset;
	}

	public void setRateControlPreset(H264NvencRateControlPreset rcpreset) {
		this.ratecontrolpreset = rcpreset;
	}

	@Override
	public VideoH264NvencEncodingInfoContainer clone() {

		VideoH264NvencEncodingInfoContainer clone = new VideoH264NvencEncodingInfoContainer();
		clone = (VideoH264NvencEncodingInfoContainer) this.copyCommoninfoofcontainer(clone);
		clone = (VideoH264NvencEncodingInfoContainer) this.copySpecificInfocontainer(clone);

		return clone;
	}

	@Override
	public IGeneralVideoEncInfoContainer copySpecificInfocontainer(IGeneralVideoEncInfoContainer cont) {

		((VideoH264NvencEncodingInfoContainer) cont).setPreset(this.preset);
		((VideoH264NvencEncodingInfoContainer) cont).setProfile(this.profile);
		((VideoH264NvencEncodingInfoContainer) cont).setPixelformat(this.pixelformat);
		// ((VideoH264NvencEncodingInfoContainer)
		// cont).setConstantQuantization(this.qp);
		((VideoH264NvencEncodingInfoContainer) cont).setLevel(this.level);
		((VideoH264NvencEncodingInfoContainer) cont).setRateControlPreset(this.ratecontrolpreset);
		if (this.extrainfocont != null) {
			((VideoH264NvencEncodingInfoContainer) cont).setExtrainfocont(extrainfocont.clone());
		}

		return cont;
	}

	@Override
	protected ArrayList<VideoContainers> getSupportedFormats() {
		return Arrays.stream(VideoCodecs.H264NVENC.supportsOutputFormats())
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	protected VideoContainers getpreferredFormat() {
		return VideoContainers.MKV;
	}

	@Override
	public void setConfigurations(PropertiesConfiguration props) {

		setVideoConfigurations(props);
		setPreset(NvencPresets.getNvencPresetsFromString(
				PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOPRESET)));
		setProfile(ProfilesH264Nvenc.getProfilesH264NvencFromString(
				PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOPROFILE)));
		setPixelformat(PixelFormatNvenc.getPixelFormatNvencFromString(
				PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOPIXELFORMAT)));
		setRateControlPreset(H264NvencRateControlPreset.getH264NvencRateControlPresetFromString(
				PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.NVENCRATECONTROL)));


		setLevel(VideoLevel.getVideoLevelFromString(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOLEVEL)));
		

		setExtrainfocont(H264NvencExtraInfoContainer.loadH264NvencExtraInfoContainer(props));

		setAudioConfigurations(props);
		setSubtitleConfigurations(props);

	}

	@Override
	protected void saveSpecificCodecProperties(PropertiesConfiguration prop) {

		prop.setProperty(StaticVideoEncoderFields.VIDEOPRESET, getPreset().toString());
		prop.setProperty(StaticVideoEncoderFields.VIDEOPROFILE, getProfile().toString());
		prop.setProperty(StaticVideoEncoderFields.VIDEOPIXELFORMAT, getPixelformat().toString());
		prop.setProperty(StaticVideoEncoderFields.VIDEOLEVEL, getLevel().toString());
		prop.setProperty(StaticVideoEncoderFields.NVENCRATECONTROL, getRateControlPreset().toString());
		// prop.setProperty(StaticVideoEncoderFields.VIDEOCONSTQUANT,
		// String.valueOf(getConstantQuantization()));

		if (getExtrainfocont() != null) {
			getExtrainfocont().saveConfigurationToFileProperties(prop);
		}

	}

	@Override
	public ArrayList<String> getSpecificVideoEncoderParameters(ArrayList<String> appendcmds) {
		ArrayList<String> h264nvencparam = new ArrayList<>();
		if (!getPreset().equals(NvencPresets.none)) {
			h264nvencparam.add(StaticFFmpegFields.PRESET);
			h264nvencparam.add(getPreset().toString());
		}

		if (!getProfile().equals(ProfilesH264Nvenc.main)) {
			h264nvencparam.add(StaticFFmpegFields.PROFILE);
			h264nvencparam.add(getProfile().toString());
		}

		if (!getLevel().equals(VideoLevel.NONE)) {
			h264nvencparam.addAll(getLevel().getffmpegcommand());
		}

		if (!getRateControlPreset().equals(H264NvencRateControlPreset.none)) {
			h264nvencparam.add(StaticFFmpegFields.h264nvencratecontrol);
			h264nvencparam.add(getRateControlPreset().toString());
		}

		if (!getPixelformat().equals(PixelFormatNvenc.yuv420p)) {
			h264nvencparam.add(StaticFFmpegFields.pixelformat);
			h264nvencparam.add(getPixelformat().toString());
		}

		if (getExtrainfocont() != null)
			h264nvencparam.addAll(getExtrainfocont().getFFmpegCmds());

		return h264nvencparam;
	}

	@Override
	public boolean useSpecificVideoEncodingParameters() {
		return true;
	}

	@Override
	public String getContainerName() {
		// TODO Auto-generated method stub
		return "H264Nvenc";
	}

}
