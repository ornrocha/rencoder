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

import org.apache.commons.configuration.PropertiesConfiguration;
import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioCBRValues;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioChannels;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioSampleRates;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.subtitles.SubtiltesAlignment;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.subtitles.SubtitlesBorderStyle;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.AspectRatio;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.FPS;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.HWAccel;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCBRValues;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoContainers;
import pt.ornrocha.rencoder.ffmpegWrapper.othercomponents.AspectComponent;
import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.helpers.osystem.OSystem;
import pt.ornrocha.rencoder.helpers.props.fields.StaticVideoEncoderFields;
import pt.ornrocha.rencoder.helpers.props.managers.auxiliar.EncodingPropsAuxiliar;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;
import pt.ornrocha.rencoder.mediafiles.files.containers.extrainfocontainer.IExtraInfoContainer;

// TODO: Auto-generated Javadoc
/**
 * The Class MainVideoEncodingInfoContainer.
 */
public abstract class DefaultEncodingInfoContainer implements IGeneralVideoEncInfoContainer {

	/** The overwritefile. */
	protected boolean overwritefile = false;

	/** The videocodec. */
	protected VideoCodecs videocodec = VideoCodecs.XVID;

	/** The video container. */
	protected VideoContainers videoContainer = VideoContainers.AVI;

	/** The videoaspectsize. */
	protected AspectComponent videoaspectsize = new AspectComponent();

	/** The videoaspectratio. */
	protected AspectRatio videoaspectratio = AspectRatio.A4_3;

	/** The videoframerate. */
	protected FPS videoframerate = FPS.PAL_25;

	/** The number threads. */
	protected int numberThreads = 1;

	/** The use video encoding cbr. */
	protected boolean useVideoEncodingCBR = true;

	/** The video const bitrate. */
	protected String videoConstBitrate = VideoCBRValues.CBR1000.toString();

	/** The use video encoding vbr. */
	protected boolean useVideoEncodingVBR = false;

	/** The video vbr quality. */
	protected IndexedHashMap<String, Integer> videoVBRQuality = new IndexedHashMap<String, Integer>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(VideoCodecs.XVID.toString(), 3);
		}
	};;

	/** The use video encoding file size. */
	protected boolean useVideoEncodingFileSize = false;

	/** The video encoding file size. */
	protected int videoEncodingFileSize = 150;

	/** The twopass encoding. */
	protected boolean twopassEncoding = false;

	// Audio
	/** The audiocodec. */
	protected AudioCodecs audiocodec = AudioCodecs.MP3;

	/** The use audio cbr. */
	protected boolean useAudioCBR = false;

	/** The audio constant bitrate value. */
	protected String audioConstantBitrateValue = AudioCBRValues.CBR128.toString();

	/** The audio variable bitrate values. */
	protected int audioVariableBitrateValues = 5;

	/** The audio sample rate. */
	protected AudioSampleRates audiosamplerate = AudioSampleRates.SAMEASSOURCE;

	/** The audio channels. */
	protected AudioChannels audiochannels = AudioChannels.STEREO;

	/** The audio max allowed sample rate. */
	protected AudioSampleRates audiomaxallowedsamplerate = AudioSampleRates.HZ48000;

	/** The audio max allowed channels. */
	protected AudioChannels audiomaxallowedchannels = AudioChannels.SURROUND;

	/** The ac3 passthrough. */
	protected boolean ac3passthrough = false;

	protected SubtitleInfoContainer subtitleInfo = new SubtitleInfoContainer();

	protected FiltersInfoContainer filters = null;

	/** The modified tag. */
	protected boolean modifiedtag = false;

	/** The extra video ffmpeg cmds. */
	protected String extravideoffmpegcmd = null;

	/** The use only extra video ffmpeg cmds. */
	protected boolean useonlyextravideoffmpegcmd = false;

	/** The extra audio ffmpeg cmds. */
	protected String extraaudioffmpegcmd = null;

	/** The use only extra audio ffmpeg cmds. */
	protected boolean useonlyextraaudioffmpegcmd = false;

	/** The file extension. */
	protected String fileextension = null;

	/** The Output folder. */
	protected String OutputFolder;

	protected String profiletypetag = null;

	protected HWAccel decoder = HWAccel.NONE;

	protected IExtraInfoContainer extrainfocont = null;

	/**
	 * Instantiates a new main video encoding info container.
	 */
	public DefaultEncodingInfoContainer() {

	}

	/**
	 * Sets the default info.
	 */
	public abstract void setDefaultInfo();

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * getVideocodec()
	 */
	@Override
	public VideoCodecs getVideocodec() {
		return videocodec;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * isOverwritefile()
	 */
	@Override
	public boolean isOverwritefile() {
		return overwritefile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * setOverwritefile(boolean)
	 */
	@Override
	public void setOverwritefile(boolean overwritefile) {
		this.overwritefile = overwritefile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * setVideocodec(ffmpegWrapper.enumerators.VideoCodecs)
	 */
	@Override
	public void setVideocodec(VideoCodecs videocodec) {
		this.videocodec = videocodec;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * getVideoContainer()
	 */
	@Override
	public VideoContainers getVideoContainer() {
		return videoContainer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * setVideoContainer(ffmpegWrapper.enumerators.VideoContainers)
	 */
	@Override
	public void setVideoContainer(VideoContainers videoContainer) {
		this.videoContainer = videoContainer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * getVideoaspectsizecomponent()
	 */
	@Override
	public AspectComponent getVideoaspectsizecomponent() {
		return videoaspectsize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * setVideoaspectsizeComponent(ffmpegWrapper.othercomponents.AspectComponent)
	 */
	@Override
	public void setVideoaspectsizeComponent(AspectComponent videoaspectsize) {
		this.videoaspectsize = videoaspectsize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * getVideoaspectratio()
	 */
	@Override
	public AspectRatio getVideoaspectratio() {
		return videoaspectratio;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * setVideoaspectratio(ffmpegWrapper.enumerators.AspectRatio)
	 */
	@Override
	public void setVideoaspectratio(AspectRatio videoaspectratio) {
		this.videoaspectratio = videoaspectratio;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * getNumberThreads()
	 */
	@Override
	public int getNumberThreads() {
		return numberThreads;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * setNumberThreads(int)
	 */
	@Override
	public void setNumberThreads(int numberThreads) {
		this.numberThreads = numberThreads;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * getVideoframerate()
	 */
	@Override
	public FPS getVideoframerate() {
		return videoframerate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * setVideoframerate(ffmpegWrapper.enumerators.FPS)
	 */
	@Override
	public void setVideoframerate(FPS videoframerate) {
		this.videoframerate = videoframerate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * isUseVideoEncodingCBR()
	 */
	@Override
	public boolean isUseVideoEncodingCBR() {
		return useVideoEncodingCBR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * setUseVideoEncodingCBR(boolean)
	 */
	@Override
	public void setUseVideoEncodingCBR(boolean useVideoEncodingCBR) {
		this.useVideoEncodingCBR = useVideoEncodingCBR;
		if (useVideoEncodingCBR) {
			this.useVideoEncodingVBR = false;
			this.useVideoEncodingFileSize = false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * getVideoConstBitrate()
	 */
	@Override
	public String getVideoConstBitrate() {
		return videoConstBitrate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * setVideoConstBitrate(java.lang.String)
	 */
	@Override
	public void setVideoConstBitrate(String videoConstBitrate) {
		this.videoConstBitrate = videoConstBitrate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * isUseVideoEncodingVBR()
	 */
	@Override
	public boolean isUseVideoEncodingVBR() {
		return useVideoEncodingVBR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * setUseVideoEncodingVBR(boolean)
	 */
	@Override
	public void setUseVideoEncodingVBR(boolean useVideoEncodingVBR) {
		this.useVideoEncodingVBR = useVideoEncodingVBR;
		if (useVideoEncodingVBR) {
			this.useVideoEncodingCBR = false;
			this.useVideoEncodingFileSize = false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * getVideoVBRQuality()
	 */
	@Override
	public IndexedHashMap<String, Integer> getVideoVBRQuality() {
		return videoVBRQuality;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * getVideoVBRQualityValue()
	 */
	@Override
	public int getVideoVBRQualityValue() {
		return videoVBRQuality.get(this.videocodec.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * setVideoVBRQuality(ffmpegWrapper.enumerators.VideoCodecs, java.lang.Integer)
	 */
	@Override
	public void setVideoVBRQuality(VideoCodecs codec, Integer videoVBRQuality) {
		this.videoVBRQuality = new IndexedHashMap<String, Integer>();
		this.videoVBRQuality.put(codec.toString(), videoVBRQuality);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * setVideoVBRQuality(auxiliar.IndexedHashMap)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void setVideoVBRQuality(IndexedHashMap<String, Integer> videoVbrQual) {
		this.videoVBRQuality = (IndexedHashMap<String, Integer>) videoVbrQual.clone();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * isUseVideoEncodingFileSize()
	 */
	@Override
	public boolean isUseVideoEncodingFileSize() {
		return useVideoEncodingFileSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * setUseVideoEncodingFileSize(boolean)
	 */
	@Override
	public void setUseVideoEncodingFileSize(boolean useVideoEncodingFileSize) {
		this.useVideoEncodingFileSize = useVideoEncodingFileSize;
		if (useVideoEncodingFileSize) {
			this.useVideoEncodingCBR = false;
			this.useVideoEncodingVBR = false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * getVideoEncodingFileSize()
	 */
	@Override
	public int getVideoEncodingFileSize() {
		return videoEncodingFileSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * setVideoEncodingFileSize(int)
	 */
	@Override
	public void setVideoEncodingFileSize(int videoEncodingFileSize) {
		this.videoEncodingFileSize = videoEncodingFileSize;
	}

	@Override
	public String getFileextension() {
		return fileextension;
	}

	@Override
	public void setFileextension(String fileextension) {
		this.fileextension = fileextension;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * isTwopassEncoding()
	 */
	@Override
	public boolean isTwopassEncoding() {
		return twopassEncoding;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * setTwopassEncoding(boolean)
	 */
	@Override
	public void setTwopassEncoding(boolean twopassEncoding) {
		this.twopassEncoding = twopassEncoding;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * getOutputFolder()
	 */
//	@Override
//	public String getOutputFolder() {
//		if (OutputFolder != null)
//			return OutputFolder;
//		else
//			return OSystem.getandSetDefaultSaveFolder();
//	}
	
	@Override
	public String getOutputFolder() {
	    return OutputFolder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * setOutputFolder(java.lang.String)
	 */
	@Override
	public void setOutputFolder(String outputFolder) {
		OutputFolder = outputFolder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * getAudiocodec()
	 */
	@Override
	public AudioCodecs getAudiocodec() {
		return audiocodec;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * setAudiocodec(ffmpegWrapper.enumerators.AudioCodecs)
	 */
	@Override
	public void setAudiocodec(AudioCodecs audiocodec) {
		this.audiocodec = audiocodec;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * isUseAudioCBR()
	 */
	@Override
	public boolean isUseAudioCBR() {
		return useAudioCBR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * setUseAudioCBR(boolean)
	 */
	@Override
	public void setUseAudioCBR(boolean useAudioCBR) {
		this.useAudioCBR = useAudioCBR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * getAudioConstantBitrateValue()
	 */
	@Override
	public String getAudioConstantBitrateValue() {
		return audioConstantBitrateValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * setAudioConstantBitrateValue(java.lang.String)
	 */
	@Override
	public void setAudioConstantBitrateValue(String audioConstantBitrateValue) {
		this.audioConstantBitrateValue = audioConstantBitrateValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * getAudioVariableBitrateValues()
	 */
	@Override
	public int getAudioVariableBitrateValues() {
		return audioVariableBitrateValues;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * setAudioVariableBitrateValues(int)
	 */
	@Override
	public void setAudioVariableBitrateValues(int audioVariableBitrateValues) {
		this.audioVariableBitrateValues = audioVariableBitrateValues;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * getAudiosamplerate()
	 */
	@Override
	public AudioSampleRates getAudiosamplerate() {
		return audiosamplerate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * setAudiosamplerate(ffmpegWrapper.enumerators.AudioSampleRates)
	 */
	@Override
	public void setAudiosamplerate(AudioSampleRates audiosamplerate) {
		this.audiosamplerate = audiosamplerate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * getMaxAllowedAudiosamplerate()
	 */
	@Override
	public AudioSampleRates getMaxAllowedAudiosamplerate() {
		return this.audiomaxallowedsamplerate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * setMaxAllowedAudiosamplerate(ffmpegWrapper.enumerators.AudioSampleRates)
	 */
	@Override
	public void setMaxAllowedAudiosamplerate(AudioSampleRates audiosamplerate) {
		this.audiomaxallowedsamplerate = audiosamplerate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * getAudiochannels()
	 */
	@Override
	public AudioChannels getAudiochannels() {
		return audiochannels;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * setAudiochannels(ffmpegWrapper.enumerators.AudioChannels)
	 */
	@Override
	public void setAudiochannels(AudioChannels audiochannels) {
		this.audiochannels = audiochannels;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * getMaxAllowedAudiochannels()
	 */
	@Override
	public AudioChannels getMaxAllowedAudiochannels() {
		return this.audiomaxallowedchannels;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * setMaxAllowedAudiochannels(ffmpegWrapper.enumerators.AudioChannels)
	 */
	@Override
	public void setMaxAllowedAudiochannels(AudioChannels audiochannels) {
		this.audiomaxallowedchannels = audiochannels;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * getExtraaudioffmpegcmd()
	 */
	@Override
	public String getExtraaudioffmpegcmd() {
		return extraaudioffmpegcmd;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * setExtraaudioffmpegcmd(java.lang.String)
	 */
	@Override
	public void setExtraaudioffmpegcmd(String extraaudioffmpegcmd) {
		this.extraaudioffmpegcmd = extraaudioffmpegcmd;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * isUseonlyextraaudioffmpegcmd()
	 */
	@Override
	public boolean isUseonlyextraaudioffmpegcmd() {
		return useonlyextraaudioffmpegcmd;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * setUseonlyextraaudioffmpegcmd(boolean)
	 */
	@Override
	public void setUseonlyextraaudioffmpegcmd(boolean useonlyextraaudioffmpegcmd) {
		this.useonlyextraaudioffmpegcmd = useonlyextraaudioffmpegcmd;
	}

	@Override
	public SubtitleInfoContainer getSubtitleInfoContainer() {
		return this.subtitleInfo;
	}

	@Override
	public void setSubtitleInfoContainer(SubtitleInfoContainer subinfo) {
		this.subtitleInfo = subinfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * isAc3passthrough()
	 */
	@Override
	public boolean isAc3passthrough() {
		return ac3passthrough;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * setAc3passthrough(boolean)
	 */
	@Override
	public void setAc3passthrough(boolean ac3passthrough) {
		this.ac3passthrough = ac3passthrough;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * isModifiedtag()
	 */
	@Override
	public boolean isModifiedtag() {
		return modifiedtag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * setModifiedtag(boolean)
	 */
	@Override
	public void setModifiedtag(boolean modifiedtag) {
		this.modifiedtag = modifiedtag;
	}

	@Override
	public String getProfileTypeTag() {
		return this.profiletypetag;
	}

	@Override
	public void setProfileTypeTag(String typetag) {
		this.profiletypetag = typetag;
	}

	@Override
	public FiltersInfoContainer getFilters() {
		return filters;
	}

	@Override
	public void setFilters(FiltersInfoContainer filters) {
		this.filters = filters;
	}

	@Override
	public void setHardwareAccelerationDecoder(HWAccel decoder) {
		if (videocodec.supportsDecodingHardwareAccel())
			this.decoder = decoder;
	}

	@Override
	public HWAccel getHardwareAccelerationDecoder() {
		return decoder;
	}
	
	

	public IExtraInfoContainer getExtrainfocont() {
	    return extrainfocont;
	}

	public void setExtrainfocont(IExtraInfoContainer extrainfocont) {
	    this.extrainfocont = extrainfocont;
	}

	@Override
	public abstract DefaultEncodingInfoContainer clone();

	protected abstract ArrayList<VideoContainers> getSupportedFormats();

	protected abstract VideoContainers getpreferredFormat();

	protected VideoContainers getVideoContainerFromString(String cont) {
		if (cont != null) {
			ArrayList<VideoContainers> supported = getSupportedFormats();
			for (VideoContainers container : supported) {
				if (container.toString().equalsIgnoreCase(cont)) {
					return container;
				}
			}
		}
		return getpreferredFormat();
	}

	protected void setVideoConfigurations(PropertiesConfiguration props) {

		setVideoContainer(getVideoContainerFromString(
				PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOCONTAINER)));

		if (props.getProperty(StaticVideoEncoderFields.VIDEOASPECTSIZE) != null)
			setVideoaspectsizeComponent(EncodingPropsAuxiliar
					.getVideoSizeAspect((String) props.getProperty(StaticVideoEncoderFields.VIDEOASPECTSIZE)));
		else
			setVideoaspectsizeComponent(new AspectComponent());

		if (props.getProperty(StaticVideoEncoderFields.VIDEOASPECTRATIO) != null)
			setVideoaspectratio(EncodingPropsAuxiliar
					.getVideoAspectRatio((String) props.getProperty(StaticVideoEncoderFields.VIDEOASPECTRATIO)));
		else
			setVideoaspectratio(AspectRatio.SAMEASSOURCE);

		if (props.getProperty(StaticVideoEncoderFields.VIDEOFRAMERATE) != null)
			setVideoframerate(EncodingPropsAuxiliar
					.getVideoFrameRate((String) props.getProperty(StaticVideoEncoderFields.VIDEOFRAMERATE)));
		else
			setVideoframerate(FPS.SAMEASSOURCE);

		boolean usefileSize = PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOUSEFILESIZE, false);
		if (usefileSize) {
			setUseVideoEncodingFileSize(true);
			setUseVideoEncodingCBR(false);
			setUseVideoEncodingVBR(false);

			setVideoEncodingFileSize(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOFILESIZE, 10,
					Integer.MAX_VALUE, 350));

		} else if (PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOUSEVBR, false)) {

			String videoVBR = PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOVBRVALUE);
			int putVBR = EncodingPropsAuxiliar.getVideoQualityValue(videoVBR, getVideocodec());
			if (putVBR != -1)
				setVideoVBRQuality(getVideocodec(), putVBR);
			else
				setVideoVBRQuality(getVideocodec(), EncodingPropsAuxiliar.getDefaultVideoCodecQuality(getVideocodec()));
		} else if (PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOUSECBR, false)) {

			String videoCBR = PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOCBRVALUE);
			String putCBR = EncodingPropsAuxiliar.getBitrateValue(videoCBR);
			if (putCBR != null)
				setVideoConstBitrate(putCBR);
			else
				setVideoConstBitrate("1200k");
		}

		setFileextension(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOFILEEXTENSION));

		setTwopassEncoding(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.TWOPASSENCODING, false));

		setOverwritefile(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOOVERWRITE, false));

		setNumberThreads(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.FFMPEGTHREADS, 1, 32, 1));

	}

	protected void setAudioConfigurations(PropertiesConfiguration props) {

		String descaudiocodec = PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.AUDIOCODEC);
		setAudiocodec(VideoContainers.ValidateandGetAudioCodec(descaudiocodec, getVideoContainer()));

		String ffmpegcmd = PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.AUDIOEXTRAFFMPEGCMD);
		if (ffmpegcmd != null)
			setExtraaudioffmpegcmd(ffmpegcmd);

		setUseonlyextraaudioffmpegcmd(
				PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.AUDIOUSEONLYEXTRAFFMPEGCMD, false));

		if (!isTwopassEncoding()) {

			if (PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.AUDIOUSECBR, false)) {
				setUseAudioCBR(true);

				String audioCBR = PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.AUDIOCBRVALUE);
				String putCBR = EncodingPropsAuxiliar.getBitrateValue(audioCBR);
				if (putCBR != null)
					setAudioConstantBitrateValue(putCBR);
				else
					setAudioConstantBitrateValue("128k");
			} else if (PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.AUDIOUSEVBR, false)) {
				setUseAudioCBR(false);
				String audioVBR = PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.AUDIOVBRVALUE);
				setAudioVariableBitrateValues(
						EncodingPropsAuxiliar.getAudioVBRValueToAudioCodec(audioVBR, getAudiocodec()));

			} else {

				if (props.containsKey(StaticVideoEncoderFields.AUDIOUSECBR)
						&& !props.containsKey(StaticVideoEncoderFields.AUDIOUSEVBR)) {
					setUseAudioCBR(true);
					setAudioConstantBitrateValue("128k");
				} else if (!props.containsKey(StaticVideoEncoderFields.AUDIOUSECBR)
						&& props.containsKey(StaticVideoEncoderFields.AUDIOUSEVBR)) {

					setUseAudioCBR(false);
					setAudioVariableBitrateValues(EncodingPropsAuxiliar.getDefaultAudioCodecQuality(getAudiocodec()));
				}
			}
		} else {

			setUseAudioCBR(true);
			String audioCBR = PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.AUDIOCBRVALUE);
			if (audioCBR != null) {
				String putCBR = EncodingPropsAuxiliar.getBitrateValue(audioCBR);
				if (putCBR != null)
					setAudioConstantBitrateValue(putCBR);
				else
					setAudioConstantBitrateValue("128k");
			} else
				setAudioConstantBitrateValue("128k");
		}

		setAudiosamplerate(AudioSampleRates.getAudioSampleRatesFromString(
				PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.AUDIOPREFFREQ)));

		String audiochannels = PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.AUDIOPREFCHANNELS);
		if (audiochannels != null) {
			for (AudioChannels cha : AudioChannels.values()) {
				if (cha.toString().toLowerCase().equals(audiochannels.toLowerCase())) {
					setAudiochannels(cha);
					setMaxAllowedAudiochannels(cha);
				}
			}
		}

		setAc3passthrough(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.AUDIOAC3PASSTHROUGH, false));

	}

	protected void setSubtitleConfigurations(PropertiesConfiguration props) {

		getSubtitleInfoContainer()
				.setUseHardSubs(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SUBSUSEHARDSUBS, false));

		// System.out.println("SUBS: "+PropertiesWorker.checkProperty(props,
		// StaticVideoEncoderFields.SUBSUSEHARDSUBS, false));
		if (!getSubtitleInfoContainer().isUseHardSubs())
			getSubtitleInfoContainer().setUseSoftSubs(
					PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SUBSUSESOFTSUBS, false));

		String fontname = PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SUBSFONT);
		if (fontname != null)
			getSubtitleInfoContainer().setSubsFontName(fontname);

		getSubtitleInfoContainer().setSubsFontSize(
				PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SUBSFONTSIZE, 8, 48, 20));

		String fontencoding = PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SUBSENCODING);
		if (fontencoding != null)
			getSubtitleInfoContainer().setSubsEncoding(EncodingPropsAuxiliar.getSubEncodingByName(fontencoding));

		String fontalterencoding = PropertiesWorker.checkProperty(props,
				StaticVideoEncoderFields.SUBSALTERNATIVEENCODING);
		if (fontalterencoding != null) {
			getSubtitleInfoContainer().setSubsEncoding(null);
			getSubtitleInfoContainer().setSubsAlternativeEncoding(fontalterencoding);
		}

		String subalig = PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SUBSALIGNMENT);
		if (subalig != null) {
			if (subalig.toLowerCase().equals(SubtiltesAlignment.TOP.getAlignmentID()))
				getSubtitleInfoContainer().setSubsAlignment(SubtiltesAlignment.TOP);
			else
				getSubtitleInfoContainer().setSubsAlignment(SubtiltesAlignment.BOTTOM);
		}

		int borderstyle = PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SUBSBORDERSTYLE, 0, 3, 0);
		if (borderstyle == 1)
			getSubtitleInfoContainer().setSubsBorderStyle(SubtitlesBorderStyle.OUTILINEWITHSHADOW);
		else if (borderstyle == 3)
			getSubtitleInfoContainer().setSubsBorderStyle(SubtitlesBorderStyle.OPAQUEBOX);
		else
			getSubtitleInfoContainer().setSubsBorderStyle(SubtitlesBorderStyle.NONE);

		String fontcolor = PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SUBSCOLOR);
		if (fontcolor != null)
			getSubtitleInfoContainer().setSubsColor(EncodingPropsAuxiliar.getSubColor(fontcolor));

		int suboutline = PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SUBSOUTLINE, 0, 20, 1);
		if (suboutline <= 5)
			getSubtitleInfoContainer().setSubsOutline(suboutline);

		int subshadow = PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SUBSSHADOW, 0, 20, 1);
		if (subshadow <= 5)
			getSubtitleInfoContainer().setSubsShadow(subshadow);

		getSubtitleInfoContainer()
				.setSubsUseBold(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SUBSUSEBOLD, false));
		getSubtitleInfoContainer()
				.setSubsUseItalic(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SUBSUSEITALIC, false));

	}

	protected abstract void saveSpecificCodecProperties(PropertiesConfiguration prop);

	@Override
	public void saveConfigurations(String filepath, String nameprofile, boolean isprofile) {

		PropertiesConfiguration props = new PropertiesConfiguration();

		if (nameprofile != null) {
			props.setProperty(StaticVideoEncoderFields.PROFILENAME, nameprofile);
		}

		if (!isprofile)
			if (isOverwritefile())
				props.setProperty(StaticVideoEncoderFields.VIDEOOVERWRITE, "true");

		saveVideoProperties(props, isprofile);
		saveAudioProperties(props);
		saveSubtitlesProperties(props);

		if (isprofile) {
			filepath = filepath + OSystem.getSystemSeparator() + nameprofile + ".profile";
		}

		try {
			PropertiesWorker.writePropertyFile(props, filepath);
		} catch (IOException e) {
			Logger.error(e);

		}

	}

	protected void saveVideoProperties(PropertiesConfiguration props, boolean isprofile) {

		props.setProperty(StaticVideoEncoderFields.VIDEOCODEC, getVideocodec().getFFmpegID());

		if (!isprofile)
			props.setProperty(StaticVideoEncoderFields.FFMPEGTHREADS, String.valueOf(getNumberThreads()));

		if (getFileextension() != null) {
			props.setProperty(StaticVideoEncoderFields.VIDEOFILEEXTENSION, getFileextension());
		}

		if (!getVideocodec().equals(VideoCodecs.COPY)) {
			props.setProperty(StaticVideoEncoderFields.VIDEOCONTAINER, getVideoContainer().toString());

			String nameaspect = getVideoaspectsizecomponent().getNameAspect();
			if (nameaspect.toLowerCase().equals("custom"))
				props.setProperty(StaticVideoEncoderFields.VIDEOASPECTSIZE,
						getVideoaspectsizecomponent().getSizemesu());
			else
				props.setProperty(StaticVideoEncoderFields.VIDEOASPECTSIZE,
						getVideoaspectsizecomponent().getNameAspect());

			props.setProperty(StaticVideoEncoderFields.VIDEOASPECTRATIO, getVideoaspectratio().getVideoRatioID());
			props.setProperty(StaticVideoEncoderFields.VIDEOFRAMERATE, getVideoframerate().getVideoFrameRateID());

			saveVideoEncodingBitratetype(props);

			if (isTwopassEncoding())
				props.setProperty(StaticVideoEncoderFields.TWOPASSENCODING, "true");
			else
				props.setProperty(StaticVideoEncoderFields.TWOPASSENCODING, "false");

			saveSpecificCodecProperties(props);
		}
	}

	protected void saveVideoEncodingBitratetype(PropertiesConfiguration prop) {

		if (isUseVideoEncodingCBR()) {
			prop.setProperty(StaticVideoEncoderFields.VIDEOUSECBR, "true");
			prop.setProperty(StaticVideoEncoderFields.VIDEOCBRVALUE, getVideoConstBitrate());
		} else if (isUseVideoEncodingVBR()) {
			prop.setProperty(StaticVideoEncoderFields.VIDEOUSEVBR, "true");
			prop.setProperty(StaticVideoEncoderFields.VIDEOVBRVALUE, String.valueOf(getVideoVBRQualityValue()));
		} else if (isUseVideoEncodingFileSize()) {
			prop.setProperty(StaticVideoEncoderFields.VIDEOUSEFILESIZE, "true");
			prop.setProperty(StaticVideoEncoderFields.VIDEOFILESIZE, String.valueOf(getVideoEncodingFileSize()));
		}
	}

	protected void saveAudioProperties(PropertiesConfiguration prop) {

		if (!isUseonlyextraaudioffmpegcmd())
			prop.setProperty(StaticVideoEncoderFields.AUDIOCODEC, getAudiocodec().toString());

		if (getExtraaudioffmpegcmd() != null)
			prop.setProperty(StaticVideoEncoderFields.AUDIOEXTRAFFMPEGCMD, getExtraaudioffmpegcmd());

		if (isUseonlyextraaudioffmpegcmd())
			prop.setProperty(StaticVideoEncoderFields.AUDIOUSEONLYEXTRAFFMPEGCMD, "true");

		if (!isUseonlyextraaudioffmpegcmd()) {
			if (getAudiocodec() != AudioCodecs.COPY) {
				prop.setProperty(StaticVideoEncoderFields.AUDIOPREFFREQ, getAudiosamplerate().toString());
				prop.setProperty(StaticVideoEncoderFields.AUDIOPREFCHANNELS, getAudiochannels().toString());

				if (isUseAudioCBR()) {
					prop.setProperty(StaticVideoEncoderFields.AUDIOUSECBR, "true");
					prop.setProperty(StaticVideoEncoderFields.AUDIOCBRVALUE, getAudioConstantBitrateValue());
				} else {
					prop.setProperty(StaticVideoEncoderFields.AUDIOUSEVBR, "true");
					prop.setProperty(StaticVideoEncoderFields.AUDIOVBRVALUE,
							String.valueOf(getAudioVariableBitrateValues()));
				}

				prop.setProperty(StaticVideoEncoderFields.AUDIOAC3PASSTHROUGH, String.valueOf(isAc3passthrough()));
			}
		}
	}

	protected void saveSubtitlesProperties(PropertiesConfiguration prop) {

		if (getSubtitleInfoContainer().isUseSoftSubs())
			prop.setProperty(StaticVideoEncoderFields.SUBSUSESOFTSUBS, "true");
		else if (getSubtitleInfoContainer().isUseHardSubs()) {
			prop.setProperty(StaticVideoEncoderFields.SUBSUSEHARDSUBS, "true");
			prop.setProperty(StaticVideoEncoderFields.SUBSALIGNMENT,
					getSubtitleInfoContainer().getSubsAlignment().getAlignmentID());
			if (getSubtitleInfoContainer().getSubsAlternativeEncoding() != null) {
				prop.setProperty(StaticVideoEncoderFields.SUBSALTERNATIVEENCODING,
						getSubtitleInfoContainer().getSubsAlternativeEncoding());
			} else
				prop.setProperty(StaticVideoEncoderFields.SUBSENCODING,
						getSubtitleInfoContainer().getSubsEncoding().toString());

			prop.setProperty(StaticVideoEncoderFields.SUBSBORDERSTYLE,
					String.valueOf(getSubtitleInfoContainer().getSubsBorderStyle().getBorderStyleID()));

			prop.setProperty(StaticVideoEncoderFields.SUBSCOLOR,
					getSubtitleInfoContainer().getSubsColor().getColorID());

			prop.setProperty(StaticVideoEncoderFields.SUBSFONT, getSubtitleInfoContainer().getSubsFontName());
			prop.setProperty(StaticVideoEncoderFields.SUBSFONTSIZE,
					String.valueOf(getSubtitleInfoContainer().getSubsFontSize()));
			prop.setProperty(StaticVideoEncoderFields.SUBSOUTLINE,
					String.valueOf(getSubtitleInfoContainer().getSubsOutline()));
			prop.setProperty(StaticVideoEncoderFields.SUBSSHADOW,
					String.valueOf(getSubtitleInfoContainer().getSubsShadow()));
			prop.setProperty(StaticVideoEncoderFields.SUBSUSEBOLD,
					String.valueOf(getSubtitleInfoContainer().isSubsUseBold()));
			prop.setProperty(StaticVideoEncoderFields.SUBSUSEITALIC,
					String.valueOf(getSubtitleInfoContainer().isSubsUseItalic()));

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
	 * copyCommoninfoofcontainer(filetreatment.files.containers.interfaces.
	 * IGeneralVideoEncInfoContainer)
	 */
	@Override
	public IGeneralVideoEncInfoContainer copyCommoninfoofcontainer(IGeneralVideoEncInfoContainer newcont) {

		newcont.setOverwritefile(this.isOverwritefile());

		newcont.setVideocodec(this.getVideocodec());

		newcont.setVideoContainer(this.getVideoContainer());

		newcont.setVideoaspectsizeComponent(this.getVideoaspectsizecomponent());

		newcont.setVideoaspectratio(this.getVideoaspectratio());

		newcont.setNumberThreads(this.getNumberThreads());

		newcont.setVideoframerate(this.getVideoframerate());

		newcont.setUseVideoEncodingCBR(this.isUseVideoEncodingCBR());

		newcont.setVideoConstBitrate(this.getVideoConstBitrate());

		newcont.setUseVideoEncodingVBR(this.isUseVideoEncodingVBR());

		newcont.setVideoVBRQuality(this.getVideoVBRQuality());

		newcont.setUseVideoEncodingFileSize(this.isUseVideoEncodingFileSize());

		newcont.setVideoEncodingFileSize(this.getVideoEncodingFileSize());

//		newcont.setExtravideoffmpegcmd(this.getExtravideoffmpegcmd());
//
//		newcont.setUseonlyextravideoffmpegcmd(this.isUseonlyextravideoffmpegcmd());

		newcont.setFileextension(this.getFileextension());

		newcont.setTwopassEncoding(this.isTwopassEncoding());

		newcont.setOutputFolder(this.getOutputFolder());

		newcont.setAudiocodec(this.getAudiocodec());

		newcont.setUseAudioCBR(this.isUseAudioCBR());

		newcont.setAudioConstantBitrateValue(this.getAudioConstantBitrateValue());

		newcont.setAudioVariableBitrateValues(this.getAudioVariableBitrateValues());

		newcont.setAudiosamplerate(this.getAudiosamplerate());

		newcont.setMaxAllowedAudiosamplerate(this.getMaxAllowedAudiosamplerate());

		newcont.setAudiochannels(this.getAudiochannels());

		newcont.setMaxAllowedAudiochannels(this.getMaxAllowedAudiochannels());

		newcont.setExtraaudioffmpegcmd(this.getExtraaudioffmpegcmd());

		newcont.setUseonlyextraaudioffmpegcmd(this.isUseonlyextraaudioffmpegcmd());

		newcont.setAc3passthrough(this.isAc3passthrough());

		newcont.setSubtitleInfoContainer(getSubtitleInfoContainer().copyInfoContainer());

		newcont.setModifiedtag(this.isModifiedtag());

		newcont.setFilters(this.getFilters());

		newcont.setHardwareAccelerationDecoder(this.getHardwareAccelerationDecoder());

		return newcont;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#print
	 * ()
	 */
	@Override
	public void print() {
		System.out.println("videocodec =" + videocodec);
		System.out.println("videoContainer  =" + videoContainer);
		System.out.println("videoaspectsize =" + videoaspectsize);
		System.out.println("videoaspectratio =" + videoaspectratio);
		System.out.println("videoframerate =" + videoframerate);

		System.out.println("useVideoEncodingCBR =" + useVideoEncodingCBR);
		System.out.println("videoConstBitrate =" + videoConstBitrate);

		System.out.println("useVideoEncodingVBR =" + useVideoEncodingVBR);
		System.out.println("videoVBRQuality =" + videoVBRQuality);

		System.out.println("useVideoEncodingFileSize =" + useVideoEncodingFileSize);
		System.out.println("videoEncodingFileSize =" + videoEncodingFileSize);

		System.out.println("twopassEncoding =" + twopassEncoding);

		// Audio
		System.out.println("audiocodec =" + audiocodec);
		System.out.println("useAudioCBR =" + useAudioCBR);
		System.out.println("audioConstantBitrateValue =" + audioConstantBitrateValue);
		System.out.println("audioVariableBitrateValues =" + audioVariableBitrateValues);
		System.out.println("audiosamplerate =" + audiosamplerate);
		System.out.println("audiochannels =" + audiochannels);

		// Subtitles
		/*
		 * System.out.println("useHardSubs ="+useHardSubs);
		 * System.out.println("useSoftSubs ="+useSoftSubs);
		 * System.out.println("subsFontSize ="+subsFontSize);
		 * System.out.println("subsFontName ="+subsFontName);
		 * System.out.println("subsOutline ="+subsOutline);
		 * System.out.println("subsShadow ="+subsShadow);
		 * System.out.println("subsUseBold ="+subsUseBold);
		 * System.out.println("subsUseItalic ="+subsUseItalic);
		 * System.out.println("subsColor  ="+subsColor);
		 * System.out.println("subsEncoding ="+subsEncoding);
		 * System.out.println("subsAlternativeEncoding ="+subsAlternativeEncoding);
		 * System.out.println("subsBorderStyle ="+subsBorderStyle);
		 * System.out.println("subsAlignment ="+subsAlignment);
		 * System.out.println("useMp4SubtitleEncCodec ="+useMp4SubtitleEncCodec);
		 */
		System.out.println("Output folder =" + OutputFolder);

	}

}
