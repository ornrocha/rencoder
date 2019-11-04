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
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.Openh264SliceMode;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.ProfilesH264;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoContainers;
import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.helpers.props.fields.StaticFFmpegFields;
import pt.ornrocha.rencoder.helpers.props.fields.StaticVideoEncoderFields;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.DefaultEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;

// TODO: Auto-generated Javadoc
/**
 * The Class VideoH264EncodingInfoContainer.
 */
public class VideoOpenH264EncodingInfoContainer extends DefaultEncodingInfoContainer {

	/** The profile. */
	protected ProfilesH264 profile = ProfilesH264.none;
	private Openh264SliceMode slicemode = Openh264SliceMode.auto;
	private int slicenumber = 0;
	private boolean loopfilter = true;
	private int max_nal_size = 0;
	private boolean allow_skip_frames = false;
	private boolean cabac = false;
	private int gopsize = 0;
	private int maxrate = 0;

	/**
	 * Instantiates a new video h264 encoding info container.
	 */
	public VideoOpenH264EncodingInfoContainer() {
		super();
		setDefaultInfo();

	}

	@Override
	public void setDefaultInfo() {
		this.videocodec = VideoCodecs.OPENH264;
		this.videoContainer = VideoContainers.MKV;
		this.videoVBRQuality = new IndexedHashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put(VideoCodecs.OPENH264.toString(), 23);
			}
		};
		this.audiocodec = AudioCodecs.LCAAC;
		this.useAudioCBR = true;
		this.audioConstantBitrateValue = "128k";
	}

	public ProfilesH264 getProfile() {
		return profile;
	}

	public void setProfile(ProfilesH264 profile) {
		this.profile = profile;
	}

	public Openh264SliceMode getSlicemode() {
		return slicemode;
	}

	public void setSlicemode(Openh264SliceMode slicemode) {
		this.slicemode = slicemode;
	}

	public int getSlicenumber() {
		return slicenumber;
	}

	public void setSlicenumber(int slicenumber) {
		this.slicenumber = slicenumber;
	}

	public boolean isLoopfilter() {
		return loopfilter;
	}

	public void setLoopfilter(boolean loopfilter) {
		this.loopfilter = loopfilter;
	}

	public int getMax_nal_size() {
		return max_nal_size;
	}

	public void setMax_nal_size(int max_nal_size) {
		this.max_nal_size = max_nal_size;
	}

	public boolean isAllow_skip_frames() {
		return allow_skip_frames;
	}

	public void setAllow_skip_frames(boolean allow_skip_frames) {
		this.allow_skip_frames = allow_skip_frames;
	}

	public boolean isCabac() {
		return cabac;
	}

	public void setCabac(boolean cabac) {
		this.cabac = cabac;
	}
	
	

	public int getGopsize() {
	    return gopsize;
	}

	public void setGopsize(int gopsize) {
	    this.gopsize = gopsize;
	}

	public int getMaxrate() {
	    return maxrate;
	}

	public void setMaxrate(int maxrate) {
	    this.maxrate = maxrate;
	}

	@Override
	public VideoOpenH264EncodingInfoContainer clone() {

		VideoOpenH264EncodingInfoContainer clone = new VideoOpenH264EncodingInfoContainer();
		clone = (VideoOpenH264EncodingInfoContainer) this.copyCommoninfoofcontainer(clone);
		clone = (VideoOpenH264EncodingInfoContainer) this.copySpecificInfocontainer(clone);

		return clone;
	}

	@Override
	public IGeneralVideoEncInfoContainer copySpecificInfocontainer(IGeneralVideoEncInfoContainer cont) {

		((VideoOpenH264EncodingInfoContainer) cont).setProfile(this.profile);
		((VideoOpenH264EncodingInfoContainer) cont).setSlicemode(this.slicemode);
		((VideoOpenH264EncodingInfoContainer) cont).setSlicenumber(slicenumber);
		((VideoOpenH264EncodingInfoContainer) cont).setLoopfilter(this.loopfilter);
		((VideoOpenH264EncodingInfoContainer) cont).setMax_nal_size(this.max_nal_size);
		((VideoOpenH264EncodingInfoContainer) cont).setAllow_skip_frames(this.allow_skip_frames);
		((VideoOpenH264EncodingInfoContainer) cont).setCabac(this.cabac);
		((VideoOpenH264EncodingInfoContainer) cont).setGopsize(this.gopsize);
		((VideoOpenH264EncodingInfoContainer) cont).setMaxrate(this.maxrate);
		return cont;
	}

	@Override
	protected ArrayList<VideoContainers> getSupportedFormats() {
		return Arrays.stream(VideoCodecs.OPENH264.supportsOutputFormats())
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	protected VideoContainers getpreferredFormat() {
		return VideoContainers.MKV;
	}

	@Override
	public void setConfigurations(PropertiesConfiguration props) {
		setVideoConfigurations(props);

		setProfile(ProfilesH264.getProfilesH264FromString(
				PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOPROFILE)));
		setSlicemode(Openh264SliceMode.getSliceModeFromString(
				PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOSLICEMODE)));

		if (slicemode.equals(Openh264SliceMode.fixed)) {
			setSlicenumber(
					PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEONUMBERSLICES, 0, 500, 0));
		}
		setLoopfilter(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.OPENH264LOOPFILTER, true));
		setMax_nal_size(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.OPENH264MAXNAL, 0, 1, 0));
		setAllow_skip_frames(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.OPENH264SKIPFRAMES, false));
		setCabac(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.OPENH264CABAC, false));
                setGopsize(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.OPENH264GOPSIZE, 0, 500, 0));
                setMaxrate(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.OPENH264MAXRATE, 0, 50, 0));
		
		setAudioConfigurations(props);
		setSubtitleConfigurations(props);

	}

	@Override
	protected void saveSpecificCodecProperties(PropertiesConfiguration prop) {

		prop.setProperty(StaticVideoEncoderFields.VIDEOPROFILE, getProfile().toString());
		prop.setProperty(StaticVideoEncoderFields.VIDEOSLICEMODE, slicemode.toString());
		if (slicemode.equals(Openh264SliceMode.fixed))
			prop.setProperty(StaticVideoEncoderFields.VIDEONUMBERSLICES, String.valueOf(slicenumber));
		prop.setProperty(StaticVideoEncoderFields.OPENH264LOOPFILTER, String.valueOf(loopfilter));
		prop.setProperty(StaticVideoEncoderFields.OPENH264MAXNAL, String.valueOf(max_nal_size));
		prop.setProperty(StaticVideoEncoderFields.OPENH264SKIPFRAMES, String.valueOf(allow_skip_frames));
		prop.setProperty(StaticVideoEncoderFields.OPENH264CABAC, String.valueOf(cabac));
		if(gopsize>0)
		    prop.setProperty(StaticVideoEncoderFields.OPENH264GOPSIZE, String.valueOf(gopsize));
		if(maxrate>0)
		    prop.setProperty(StaticVideoEncoderFields.OPENH264MAXRATE, String.valueOf(maxrate));
	}

	@Override
	public ArrayList<String> getSpecificVideoEncoderParameters(ArrayList<String> appendcmds) {

		ArrayList<String> params = new ArrayList<>();
		if (!profile.equals(ProfilesH264.none)) {
			params.add(StaticFFmpegFields.PROFILE);
			params.add(profile.toString());
		}
		if (gopsize != 0) {
			params.add(StaticFFmpegFields.OPENH264GOPSIZE);
			params.add(String.valueOf(gopsize));
		}
		if (maxrate != 0) {
			params.add(StaticFFmpegFields.OPENH264MAXRATE);
			params.add(String.valueOf(maxrate)+"M");
		}
		if (!slicemode.equals(Openh264SliceMode.auto)) {
			params.add(StaticFFmpegFields.OPENH264SLICE);
			params.add(slicemode.toString());
			
			if(slicemode.equals(Openh264SliceMode.fixed)) {
			    params.add(StaticFFmpegFields.OPENH264NUMBERSLICE);
				params.add(String.valueOf(slicenumber));
			}
		}
		if (!loopfilter) {
			params.add(StaticFFmpegFields.OPENH264LOOPFILTER);
			params.add(String.valueOf(0));
		}
		if (max_nal_size != 0) {
			params.add(StaticFFmpegFields.OPENH264MAXNAL);
			params.add(String.valueOf(max_nal_size));
		}
		if (allow_skip_frames) {
			params.add(StaticFFmpegFields.OPENH264SKIPFRAMES);
			params.add(String.valueOf(1));
		}
		if (cabac) {
			params.add(StaticFFmpegFields.OPENH264CABAC);
			params.add(String.valueOf(1));
		}
		return params;
	}

	@Override
	public boolean useSpecificVideoEncodingParameters() {
		return true;
	}

}
