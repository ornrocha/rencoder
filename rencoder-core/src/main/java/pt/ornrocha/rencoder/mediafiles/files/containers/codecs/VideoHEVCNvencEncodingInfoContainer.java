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
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.ProfilesHEVCNvenc;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoContainers;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoLevel;
import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.helpers.props.fields.StaticFFmpegFields;
import pt.ornrocha.rencoder.helpers.props.fields.StaticVideoEncoderFields;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;
import pt.ornrocha.rencoder.mediafiles.files.containers.extrainfocontainer.HevcNvencExtraInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.DefaultEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;

// TODO: Auto-generated Javadoc
/**
 * The Class VideoH264EncodingInfoContainer.
 */
public class VideoHEVCNvencEncodingInfoContainer extends DefaultEncodingInfoContainer {

	/** The preset. */
	protected NvencPresets preset = NvencPresets.none;

	/** The profile. */
	protected ProfilesHEVCNvenc profile = ProfilesHEVCNvenc.main;

	/** The pixelformat. */
	protected PixelFormatNvenc pixelformat = PixelFormatNvenc.yuv420p;

	protected H264NvencRateControlPreset ratecontrolpreset = H264NvencRateControlPreset.none;

	protected VideoLevel level = VideoLevel.NONE;

	// protected H264NvencExtraInfoContainer extrainfocont=null;

	/**
	 * Instantiates a new video h264 encoding info container.
	 */
	public VideoHEVCNvencEncodingInfoContainer() {
		super();
		setDefaultInfo();

	}

	@Override
	public void setDefaultInfo() {
		this.videocodec = VideoCodecs.HEVCNVENC;
		this.videoContainer = VideoContainers.MKV;
		this.videoVBRQuality = new IndexedHashMap<String, Integer>() {
			{
				put(VideoCodecs.HEVCNVENC.toString(), 23);
			}
		};
		;
		this.audiocodec = AudioCodecs.LCAAC;
		this.useAudioCBR = true;
		this.audioConstantBitrateValue = "128k";
		// this.extrainfocont=null;
	}

	// Threading capabilities: none
	// Supported pixel formats: yuv420p nv12 p010le yuv444p p016le yuv444p16le bgr0
	// rgb0 cuda d3d11
	// hevc_nvenc AVOptions:
	// -preset <int> E..V..... Set the encoding preset (from 0 to 11) (default
	// medium)
	// default E..V.....
	// slow E..V..... hq 2 passes
	// medium E..V..... hq 1 pass
	// fast E..V..... hp 1 pass
	// hp E..V.....
	// hq E..V.....
	// bd E..V.....
	// ll E..V..... low latency
	// llhq E..V..... low latency hq
	// llhp E..V..... low latency hp
	// lossless E..V..... lossless
	// losslesshp E..V..... lossless hp
	// -profile <int> E..V..... Set the encoding profile (from 0 to 4) (default
	// main)
	// main E..V.....
	// main10 E..V.....
	// rext E..V.....
	// -level <int> E..V..... Set the encoding level restriction (from 0 to 186)
	// (default auto)
	// auto E..V.....
	// 1 E..V.....
	// 1.0 E..V.....
	// 2 E..V.....
	// 2.0 E..V.....
	// 2.1 E..V.....
	// 3 E..V.....
	// 3.0 E..V.....
	// 3.1 E..V.....
	// 4 E..V.....
	// 4.0 E..V.....
	// 4.1 E..V.....
	// 5 E..V.....
	// 5.0 E..V.....
	// 5.1 E..V.....
	// 5.2 E..V.....
	// 6 E..V.....
	// 6.0 E..V.....
	// 6.1 E..V.....
	// 6.2 E..V.....
	// -tier <int> E..V..... Set the encoding tier (from 0 to 1) (default main)
	// main E..V.....
	// high E..V.....
	// -rc <int> E..V..... Override the preset rate-control (from -1 to INT_MAX)
	// (default -1)
	// constqp E..V..... Constant QP mode
	// vbr E..V..... Variable bitrate mode
	// cbr E..V..... Constant bitrate mode
	// vbr_minqp E..V..... Variable bitrate mode with MinQP (deprecated)
	// ll_2pass_quality E..V..... Multi-pass optimized for image quality
	// (deprecated)
	// ll_2pass_size E..V..... Multi-pass optimized for constant frame size
	// (deprecated)
	// vbr_2pass E..V..... Multi-pass variable bitrate mode (deprecated)
	// cbr_ld_hq E..V..... Constant bitrate low delay high quality mode
	// cbr_hq E..V..... Constant bitrate high quality mode
	// vbr_hq E..V..... Variable bitrate high quality mode
	// -rc-lookahead <int> E..V..... Number of frames to look ahead for rate-control
	// (from 0 to INT_MAX) (default 0)
	// -surfaces <int> E..V..... Number of concurrent surfaces (from 0 to 64)
	// (default 0)
	// -cbr <boolean> E..V..... Use cbr encoding mode (default false)
	// -2pass <boolean> E..V..... Use 2pass encoding mode (default auto)
	// -gpu <int> E..V..... Selects which NVENC capable GPU to use. First GPU is 0,
	// second is 1, and so on. (from -2 to INT_MAX) (default any)
	// any E..V..... Pick the first device available
	// list E..V..... List the available devices
	// -delay <int> E..V..... Delay frame output by the given amount of frames (from
	// 0 to INT_MAX) (default INT_MAX)
	// -no-scenecut <boolean> E..V..... When lookahead is enabled, set this to 1 to
	// disable adaptive I-frame insertion at scene cuts (default false)
	// -forced-idr <boolean> E..V..... If forcing keyframes, force them as IDR
	// frames. (default false)
	// -spatial_aq <boolean> E..V..... set to 1 to enable Spatial AQ (default false)
	// -temporal_aq <boolean> E..V..... set to 1 to enable Temporal AQ (default
	// false)
	// -zerolatency <boolean> E..V..... Set 1 to indicate zero latency operation (no
	// reordering delay) (default false)
	// -nonref_p <boolean> E..V..... Set this to 1 to enable automatic insertion of
	// non-reference P-frames (default false)
	// -strict_gop <boolean> E..V..... Set 1 to minimize GOP-to-GOP rate
	// fluctuations (default false)
	// -aq-strength <int> E..V..... When Spatial AQ is enabled, this field is used
	// to specify AQ strength. AQ strength scale is from 1 (low) - 15 (aggressive)
	// (from 1 to 15) (default 8)
	// -cq <float> E..V..... Set target quality level (0 to 51, 0 means automatic)
	// for constant quality mode in VBR rate control (from 0 to 51) (default 0)
	// -aud <boolean> E..V..... Use access unit delimiters (default false)
	// -bluray-compat <boolean> E..V..... Bluray compatibility workarounds (default
	// false)
	// -init_qpP <int> E..V..... Initial QP value for P frame (from -1 to 51)
	// (default -1)
	// -init_qpB <int> E..V..... Initial QP value for B frame (from -1 to 51)
	// (default -1)
	// -init_qpI <int> E..V..... Initial QP value for I frame (from -1 to 51)
	// (default -1)
	// -qp <int> E..V..... Constant quantization parameter rate control method (from
	// -1 to 51) (default -1)
	// -weighted_pred <int> E..V..... Set 1 to enable weighted prediction (from 0 to
	// 1) (default 0)
	// -b_ref_mode <int> E..V..... Use B frames as references (from 0 to 2) (default
	// disabled)
	// disabled E..V..... B frames will not be used for reference
	// each E..V..... Each B frame will be used for reference
	// middle E..V..... Only (number of B frames)/2 will be used for reference

	public ProfilesHEVCNvenc getProfile() {
		return profile;
	}

	public void setProfile(ProfilesHEVCNvenc profile) {
		this.profile = profile;
	}

	public PixelFormatNvenc getPixelformat() {
		return pixelformat;
	}

	public void setPixelformat(PixelFormatNvenc pixelformat) {
		this.pixelformat = pixelformat;
	}

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

	public H264NvencRateControlPreset getRatecontrolpreset() {
		return ratecontrolpreset;
	}

	public void setRatecontrolpreset(H264NvencRateControlPreset ratecontrolpreset) {
		this.ratecontrolpreset = ratecontrolpreset;
	}

	@Override
	public VideoHEVCNvencEncodingInfoContainer clone() {

		VideoHEVCNvencEncodingInfoContainer clone = new VideoHEVCNvencEncodingInfoContainer();
		clone = (VideoHEVCNvencEncodingInfoContainer) this.copyCommoninfoofcontainer(clone);
		clone = (VideoHEVCNvencEncodingInfoContainer) this.copySpecificInfocontainer(clone);

		return clone;
	}

	@Override
	public IGeneralVideoEncInfoContainer copySpecificInfocontainer(IGeneralVideoEncInfoContainer cont) {

		((VideoHEVCNvencEncodingInfoContainer) cont).setPreset(this.preset);
		((VideoHEVCNvencEncodingInfoContainer) cont).setProfile(this.profile);
		((VideoHEVCNvencEncodingInfoContainer) cont).setPixelformat(this.pixelformat);
		((VideoHEVCNvencEncodingInfoContainer) cont).setLevel(level);
		((VideoHEVCNvencEncodingInfoContainer) cont).setRatecontrolpreset(this.ratecontrolpreset);
		if (this.extrainfocont != null) {
			((VideoHEVCNvencEncodingInfoContainer) cont).setExtrainfocont(extrainfocont.clone());
		}

		return cont;
	}

	@Override
	protected ArrayList<VideoContainers> getSupportedFormats() {
		return Arrays.stream(VideoCodecs.HEVCNVENC.supportsOutputFormats())
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
		setProfile(ProfilesHEVCNvenc
				.getProfileFromString(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOPROFILE)));
		setPixelformat(PixelFormatNvenc.getPixelFormatNvencFromString(
				PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOPIXELFORMAT)));
		setRatecontrolpreset(H264NvencRateControlPreset.getH264NvencRateControlPresetFromString(
				PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.NVENCRATECONTROL)));

		setLevel(VideoLevel
				.getVideoLevelFromString(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOLEVEL)));

		setExtrainfocont(HevcNvencExtraInfoContainer.loadHevcNvencExtraInfoContainer(props));

		setAudioConfigurations(props);
		setSubtitleConfigurations(props);
	}

	@Override
	protected void saveSpecificCodecProperties(PropertiesConfiguration prop) {
		prop.setProperty(StaticVideoEncoderFields.VIDEOPRESET, getPreset().toString());
		prop.setProperty(StaticVideoEncoderFields.VIDEOPROFILE, getProfile().toString());
		prop.setProperty(StaticVideoEncoderFields.VIDEOPIXELFORMAT, getPixelformat().toString());
		prop.setProperty(StaticVideoEncoderFields.VIDEOLEVEL, getLevel().toString());
		prop.setProperty(StaticVideoEncoderFields.NVENCRATECONTROL, getRatecontrolpreset().toString());

		if (getExtrainfocont() != null) {
			getExtrainfocont().saveConfigurationToFileProperties(prop);
		}

	}

	@Override
	public ArrayList<String> getSpecificVideoEncoderParameters(ArrayList<String> appendcmds) {
		ArrayList<String> hevcnvencparam = new ArrayList<>();
		if (!getPreset().equals(NvencPresets.none)) {
			hevcnvencparam.add(StaticFFmpegFields.PRESET);
			hevcnvencparam.add(getPreset().toString());
		}

		if (!getProfile().equals(ProfilesHEVCNvenc.main)) {
			hevcnvencparam.add(StaticFFmpegFields.PROFILE);
			hevcnvencparam.add(getProfile().toString());
		}

		if (!getLevel().equals(VideoLevel.NONE)) {
			hevcnvencparam.addAll(getLevel().getffmpegcommand());
		}

		if (!getRatecontrolpreset().equals(H264NvencRateControlPreset.none)) {
			hevcnvencparam.add(StaticFFmpegFields.h264nvencratecontrol);
			hevcnvencparam.add(getRatecontrolpreset().toString());
		}

		if (!getPixelformat().equals(PixelFormatNvenc.yuv420p)) {
			hevcnvencparam.add(StaticFFmpegFields.pixelformat);
			hevcnvencparam.add(getPixelformat().toString());
		}

		if (getExtrainfocont() != null)
			hevcnvencparam.addAll(getExtrainfocont().getFFmpegCmds());

		return hevcnvencparam;
	}

	@Override
	public boolean useSpecificVideoEncodingParameters() {
		return true;
	}

}
