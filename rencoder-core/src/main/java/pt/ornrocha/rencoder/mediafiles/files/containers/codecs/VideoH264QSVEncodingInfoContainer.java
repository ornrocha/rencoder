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
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.HWAccel;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.PixelFormatQsv;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.ProfileH264Qsv;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.QsvPresets;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoContainers;
import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.helpers.props.fields.StaticFFmpegFields;
import pt.ornrocha.rencoder.helpers.props.fields.StaticVideoEncoderFields;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;
import pt.ornrocha.rencoder.mediafiles.files.containers.extrainfocontainer.H264QsvExtraInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.DefaultEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;

// TODO: Auto-generated Javadoc
/**
 * The Class VideoH264EncodingInfoContainer.
 */
public class VideoH264QSVEncodingInfoContainer extends DefaultEncodingInfoContainer {

	/** The profile. */
	private ProfileH264Qsv profile = ProfileH264Qsv.unknown;

	private QsvPresets preset = QsvPresets.medium;
	
	private PixelFormatQsv pixelfomat = PixelFormatQsv.nv12;
	

	/*
	-async_depth       <int>        E..V..... Maximum processing parallelism (from 1 to INT_MAX) (default 4)
	  -avbr_accuracy     <int>        E..V..... Accuracy of the AVBR ratecontrol (from 0 to INT_MAX) (default 0)
	  -avbr_convergence  <int>        E..V..... Convergence of the AVBR ratecontrol (from 0 to INT_MAX) (default 0)
	  -preset            <int>        E..V..... (from 1 to 7) (default medium)
	     veryfast        7            E..V.....
	     faster          6            E..V.....
	     fast            5            E..V.....
	     medium          4            E..V.....
	     slow            3            E..V.....
	     slower          2            E..V.....
	     veryslow        1            E..V.....
	  -rdo               <int>        E..V..... Enable rate distortion optimization (from -1 to 1) (default -1)
	  -max_frame_size    <int>        E..V..... Maximum encoded frame size in bytes (from -1 to 65535) (default -1)
	  -max_slice_size    <int>        E..V..... Maximum encoded slice size in bytes (from -1 to 65535) (default -1)
	  -bitrate_limit     <int>        E..V..... Toggle bitrate limitations (from -1 to 1) (default -1)
	  -mbbrc             <int>        E..V..... MB level bitrate control (from -1 to 1) (default -1)
	  -extbrc            <int>        E..V..... Extended bitrate control (from -1 to 1) (default -1)
	  -adaptive_i        <int>        E..V..... Adaptive I-frame placement (from -1 to 1) (default -1)
	  -adaptive_b        <int>        E..V..... Adaptive B-frame placement (from -1 to 1) (default -1)
	  -b_strategy        <int>        E..V..... Strategy to choose between I/P/B-frames (from -1 to 1) (default -1)
	  -forced_idr        <boolean>    E..V..... Forcing I frames as IDR frames (default false)
	  -low_power         <boolean>    E..V..... enable low power mode(experimental: many limitations by mfx version, BRC modes, etc.) (default false)
	  -cavlc             <int>        E..V..... Enable CAVLC (from 0 to 1) (default 0)
	  -vcm               <int>        E..V..... Use the video conferencing mode ratecontrol (from 0 to 1) (default 0)
	  -idr_interval      <int>        E..V..... Distance (in I-frames) between IDR frames (from 0 to INT_MAX) (default 0)
	  -pic_timing_sei    <int>        E..V..... Insert picture timing SEI with pic_struct_syntax element (from 0 to 1) (default 1)
	  -single_sei_nal_unit <int>        E..V..... Put all the SEI messages into one NALU (from -1 to 1) (default -1)
	  -max_dec_frame_buffering <int>        E..V..... Maximum number of frames buffered in the DPB (from 0 to 65535) (default 0)
	  -look_ahead        <int>        E..V..... Use VBR algorithm with look ahead (from 0 to 1) (default 0)
	  -look_ahead_depth  <int>        E..V..... Depth of look ahead in number frames (from 0 to 100) (default 0)
	  -look_ahead_downsampling <int>        E..V..... Downscaling factor for the frames saved for the lookahead analysis (from 0 to 3) (default unknown)
	     unknown         0            E..V.....
	     auto            0            E..V.....
	     off             1            E..V.....
	     2x              2            E..V.....
	     4x              3            E..V.....
	  -int_ref_type      <int>        E..V..... Intra refresh type (from -1 to 65535) (default -1)
	     none            0            E..V.....
	     vertical        1            E..V.....
	  -int_ref_cycle_size <int>        E..V..... Number of frames in the intra refresh cycle (from -1 to 65535) (default -1)
	  -int_ref_qp_delta  <int>        E..V..... QP difference for the refresh MBs (from -32768 to 32767) (default -32768)
	  -recovery_point_sei <int>        E..V..... Insert recovery point SEI messages (from -1 to 1) (default -1)
	  -profile           <int>        E..V..... (from 0 to INT_MAX) (default unknown)
	     unknown         0            E..V.....
	     baseline        66           E..V.....
	     main            77           E..V.....
	     high            100          E..V.....
	  -a53cc             <int>        E..V..... Use A53 Closed Captions (if available) (from 0 to 1) (default 1)
	  -aud               <int>        E..V..... Insert the Access Unit Delimiter NAL (from 0 to 1) (default 0)
	  -repeat_pps        <boolean>    E..V..... repeat pps for every frame (default false)
*/
	
	@Override
	public void setDefaultInfo() {
		this.videocodec = VideoCodecs.H264QSV;
		this.videoContainer = VideoContainers.MKV;
		this.videoVBRQuality = new IndexedHashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put(VideoCodecs.H264QSV.toString(), 20);
			}
		};
		this.audiocodec = AudioCodecs.LCAAC;
		this.useAudioCBR = true;
		this.audioConstantBitrateValue = "128k";
		// this.extrainfocont=null;
	}

	/**
	 * Instantiates a new video h264 encoding info container.
	 */
	public VideoH264QSVEncodingInfoContainer() {
		super();
		setDefaultInfo();

	}

	public ProfileH264Qsv getProfile() {
		return profile;
	}

	public void setProfile(ProfileH264Qsv profile) {
		this.profile = profile;
	}

	public QsvPresets getPreset() {
		return preset;
	}

	public void setPreset(QsvPresets preset) {
		this.preset = preset;
	}

	public PixelFormatQsv getPixelfomat() {
		return pixelfomat;
	}

	public void setPixelfomat(PixelFormatQsv pixelfomat) {
		this.pixelfomat = pixelfomat;
	}

	

	
	@Override
	public HWAccel getHardwareAccelerationDecoder() {
		return HWAccel.QSV;
	}



	@Override
	public VideoH264QSVEncodingInfoContainer clone() {

		VideoH264QSVEncodingInfoContainer clone = new VideoH264QSVEncodingInfoContainer();
		clone = (VideoH264QSVEncodingInfoContainer) this.copyCommoninfoofcontainer(clone);
		clone = (VideoH264QSVEncodingInfoContainer) this.copySpecificInfocontainer(clone);

		return clone;
	}

	@Override
	public IGeneralVideoEncInfoContainer copySpecificInfocontainer(IGeneralVideoEncInfoContainer cont) {

		((VideoH264QSVEncodingInfoContainer) cont).setProfile(this.profile);
		((VideoH264QSVEncodingInfoContainer) cont).setPreset(this.preset);
		((VideoH264QSVEncodingInfoContainer) cont).setPixelfomat(this.pixelfomat);

		if (this.extrainfocont != null) {
			((VideoH264QSVEncodingInfoContainer) cont).setExtrainfocont(extrainfocont.clone());
		}
		
		return cont;
	}

	@Override
	protected ArrayList<VideoContainers> getSupportedFormats() {
		return Arrays.stream(VideoCodecs.H264VAAPI.supportsOutputFormats())
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	protected VideoContainers getpreferredFormat() {
		return VideoContainers.MKV;
	}

	@Override
	public void setConfigurations(PropertiesConfiguration props) {

	        setVideoConfigurations(props);
	        
		setProfile(ProfileH264Qsv
				.getProfile(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOPROFILE)));

		setPreset(QsvPresets.getQsvPresetsFromString(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOPRESET)));
		setPixelfomat(PixelFormatQsv.getPixelFormatFromString(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOPIXELFORMAT)));

		
		setExtrainfocont(H264QsvExtraInfoContainer.loadH264QsvExtraInfoContainer(props));

		setAudioConfigurations(props);
		setSubtitleConfigurations(props);

	}

	@Override
	protected void saveSpecificCodecProperties(PropertiesConfiguration prop) {

		prop.setProperty(StaticVideoEncoderFields.VIDEOPROFILE, getProfile().toString());
		prop.setProperty(StaticVideoEncoderFields.VIDEOPRESET, getPreset().toString());
		prop.setProperty(StaticVideoEncoderFields.VIDEOPIXELFORMAT, getPixelfomat().toString());
		
		if (getExtrainfocont() != null) {
			getExtrainfocont().saveConfigurationToFileProperties(prop);
		}
		
	}

	@Override
	public ArrayList<String> getSpecificVideoEncoderParameters(ArrayList<String> appendcmds) {
		ArrayList<String> h264qsvparam = new ArrayList<>();

		if (!getProfile().equals(ProfileH264Qsv.unknown)) {
		    h264qsvparam.add(StaticFFmpegFields.PROFILE);
		    h264qsvparam.add(getProfile().toString());
		}
		if(!getPreset().equals(QsvPresets.medium)) {
		    h264qsvparam.add(StaticFFmpegFields.PRESET);
		    h264qsvparam.add(getPreset().toString());
		}
		if(!getPixelfomat().equals(PixelFormatQsv.nv12)) {
		    h264qsvparam.add(StaticFFmpegFields.pixelformat);
		    h264qsvparam.add(getPixelfomat().toString());
		}
		if (getExtrainfocont() != null)
		    h264qsvparam.addAll(getExtrainfocont().getFFmpegCmds());

	return h264qsvparam;
	}

	@Override
	public boolean useSpecificVideoEncodingParameters() {
		return true;
	}

	@Override
	public String getContainerName() {
		// TODO Auto-generated method stub
		return "H264QSV";
	}

}
