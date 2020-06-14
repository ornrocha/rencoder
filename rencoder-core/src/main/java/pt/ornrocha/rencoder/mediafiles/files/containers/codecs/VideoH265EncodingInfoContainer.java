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
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.H265Tune;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.PixelFormatH265;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.ProfilesH265;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoContainers;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.X26xPresets;
import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.helpers.props.fields.StaticFFmpegFields;
import pt.ornrocha.rencoder.helpers.props.fields.StaticVideoEncoderFields;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.DefaultEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;

// TODO: Auto-generated Javadoc
/**
 * The Class VideoH265EncodingInfoContainer.
 */
public class VideoH265EncodingInfoContainer extends DefaultEncodingInfoContainer {

	/** The preset. */
	protected X26xPresets preset = X26xPresets.none;
	/** The pixelformat. */
	protected PixelFormatH265 pixelformat = PixelFormatH265.yuv420p;

	/** The tune. */
	protected H265Tune tune = H265Tune.none;

	protected ProfilesH265 profile = ProfilesH265.main;

	private String othercmds = null;

	/**
	 * Instantiates a new video h265 encoding info container.
	 */
	public VideoH265EncodingInfoContainer() {
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
		this.videocodec = VideoCodecs.H265;
		this.videoContainer = VideoContainers.MKV;
		this.videoVBRQuality = new IndexedHashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put(VideoCodecs.H265.toString(), 28);
			}
		};
		this.audiocodec = AudioCodecs.LCAAC;
		this.useAudioCBR = true;
		this.audioConstantBitrateValue = "128k";
	}

	public X26xPresets getPreset() {
		return preset;
	}

	public void setPreset(X26xPresets preset) {
		this.preset = preset;
	}

	public PixelFormatH265 getPixelformat() {
		return pixelformat;
	}

	public void setPixelformat(PixelFormatH265 pixelformat) {
		this.pixelformat = pixelformat;
	}

	public H265Tune getTune() {
		return tune;
	}

	public void setTune(H265Tune tune) {
		this.tune = tune;
	}

	public ProfilesH265 getProfile() {
		return profile;
	}

	public void setProfile(ProfilesH265 profile) {
		this.profile = profile;
	}

	public String getOthercmds() {
		return othercmds;
	}

	public void setOthercmds(String othercmds) {
		this.othercmds = othercmds;
	}

	@Override
	public IGeneralVideoEncInfoContainer copySpecificInfocontainer(IGeneralVideoEncInfoContainer cont) {
		((VideoH265EncodingInfoContainer) cont).setPreset(this.preset);
		((VideoH265EncodingInfoContainer) cont).setPixelformat(this.pixelformat);
		((VideoH265EncodingInfoContainer) cont).setTune(this.tune);
		((VideoH265EncodingInfoContainer) cont).setProfile(this.profile);
		((VideoH265EncodingInfoContainer) cont).setOthercmds(this.othercmds);
		return cont;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.MainVideoEncodingInfoContainer#clone()
	 */
	@Override
	public VideoH265EncodingInfoContainer clone() {
		VideoH265EncodingInfoContainer clone = new VideoH265EncodingInfoContainer();
		clone = (VideoH265EncodingInfoContainer) this.copyCommoninfoofcontainer(clone);
		clone = (VideoH265EncodingInfoContainer) this.copySpecificInfocontainer(clone);

		return clone;
	}

	@Override
	protected ArrayList<VideoContainers> getSupportedFormats() {
		return Arrays.stream(VideoCodecs.H265.supportsOutputFormats()).collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	protected VideoContainers getpreferredFormat() {
		return VideoContainers.MKV;
	}

	@Override
	public void setConfigurations(PropertiesConfiguration props) {

		setVideoConfigurations(props);

		setPreset(X26xPresets
				.getX26xPresetsFromString(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOPRESET)));
		setPixelformat(PixelFormatH265.getPixelFormatFromString(
				PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOPIXELFORMAT)));
		setTune(H265Tune
				.getH265TuneFromString(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOTUNE)));

		setProfile(ProfilesH265.getProfilesH264FromString(
				PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOPROFILE)));

		String cmds = PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.H265CMDS);
		if (cmds != null) {
			setOthercmds(VideoKvazaarEncodingInfoContainer.validateOtherCmds(cmds));
		}

		setAudioConfigurations(props);
		setSubtitleConfigurations(props);
	}

	@Override
	protected void saveSpecificCodecProperties(PropertiesConfiguration prop) {

		prop.setProperty(StaticVideoEncoderFields.VIDEOPRESET, getPreset().toString());
		prop.setProperty(StaticVideoEncoderFields.VIDEOPIXELFORMAT, getPixelformat().toString());
		prop.setProperty(StaticVideoEncoderFields.VIDEOTUNE, getTune().toString());
		prop.setProperty(StaticVideoEncoderFields.VIDEOPROFILE, getProfile().toString());
		if (othercmds != null)
			prop.setProperty(StaticVideoEncoderFields.H265CMDS, othercmds);

	}

	@Override
	public ArrayList<String> getSpecificVideoEncoderParameters(ArrayList<String> appendcmds) {

		ArrayList<String> x265param = new ArrayList<>();
		if (!getPreset().equals(X26xPresets.none)) {
			x265param.add(StaticFFmpegFields.PRESET);
			x265param.add(getPreset().toString());
		}

		x265param.add(StaticFFmpegFields.pixelformat);
		x265param.add(getPixelformat().toString());

		if (!getTune().equals(H265Tune.none)) {
			x265param.add(StaticFFmpegFields.x265tune);
			x265param.add(getTune().toString());
		}

		if (othercmds != null) {
			x265param.add(StaticFFmpegFields.x265params);
			x265param.add(othercmds);
		}

		return x265param;
	}

	@Override
	public boolean useSpecificVideoEncodingParameters() {
		return true;
	}

	@Override
	public String getContainerName() {
		// TODO Auto-generated method stub
		return "H265";
	}

}
