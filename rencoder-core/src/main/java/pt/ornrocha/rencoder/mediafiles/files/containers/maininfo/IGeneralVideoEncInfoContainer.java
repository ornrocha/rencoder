/*
 * Copyright 2014
 *
 * This is free software: you can redistribute it and/or modify it under the terms of the GNU Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 * 
 * This code is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Public License for more details.
 * 
 * You should have received a copy of the GNU Public License along with this code. If not, see
 * http://www.gnu.org/licenses/
 * 
 * Created by Orlando Rocha
 */
package pt.ornrocha.rencoder.mediafiles.files.containers.maininfo;

import java.util.ArrayList;

import org.apache.commons.configuration.PropertiesConfiguration;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioChannels;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioSampleRates;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.AspectRatio;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.FPS;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.HWAccel;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoContainers;
import pt.ornrocha.rencoder.ffmpegWrapper.othercomponents.AspectComponent;
import pt.ornrocha.rencoder.helpers.IndexedHashMap;

// TODO: Auto-generated Javadoc
/**
 * The Interface IGeneralVideoEncInfoContainer.
 */
public interface IGeneralVideoEncInfoContainer {

  String getContainerName();

  /**
   * Checks if is overwritefile.
   *
   * @return true, if is overwritefile
   */
  boolean isOverwritefile();

  /**
   * Sets the overwrite file.
   *
   * @param overwritefile the new overwritefile
   */
  void setOverwritefile(boolean overwritefile);

  /**
   * Gets the video codec.
   *
   * @return the videocodec
   */
  VideoCodecs getVideocodec();

  /**
   * Sets the video codec.
   *
   * @param videocodec the new videocodec
   */
  void setVideocodec(VideoCodecs videocodec);

  /**
   * Gets the video container.
   *
   * @return the video container
   */
  VideoContainers getVideoContainer();

  /**
   * Sets the video container.
   *
   * @param videoContainer the new video container
   */
  void setVideoContainer(VideoContainers videoContainer);

  /**
   * Gets the video aspect size component.
   *
   * @return the videoaspectsizecomponent
   */
  AspectComponent getVideoaspectsizecomponent();

  /**
   * Sets the video aspect size component.
   *
   * @param videoaspectsize the new videoaspectsize component
   */
  void setVideoaspectsizeComponent(AspectComponent videoaspectsize);

  /**
   * Gets the video aspect ratio.
   *
   * @return the videoaspectratio
   */
  AspectRatio getVideoaspectratio();

  /**
   * Sets the video aspect ratio.
   *
   * @param videoaspectratio the new videoaspectratio
   */
  void setVideoaspectratio(AspectRatio videoaspectratio);

  /**
   * Gets the number threads.
   *
   * @return the number threads
   */
  int getNumberThreads();

  /**
   * Sets the number threads.
   *
   * @param numberThreads the new number threads
   */
  void setNumberThreads(int numberThreads);

  /**
   * Gets the video frame rate.
   *
   * @return the videoframerate
   */
  FPS getVideoframerate();

  /**
   * Sets the video frame rate.
   *
   * @param videoframerate the new videoframerate
   */
  void setVideoframerate(FPS videoframerate);

  /**
   * Checks if is use video encoding cbr.
   *
   * @return true, if is use video encoding cbr
   */
  boolean isUseVideoEncodingCBR();

  /**
   * Sets the use video encoding cbr.
   *
   * @param useVideoEncodingCBR the new use video encoding cbr
   */
  void setUseVideoEncodingCBR(boolean useVideoEncodingCBR);

  /**
   * Gets the video constant bitrate.
   *
   * @return the video const bitrate
   */
  String getVideoConstBitrate();

  /**
   * Sets the video constant bitrate.
   *
   * @param videoConstBitrate the new video const bitrate
   */
  void setVideoConstBitrate(String videoConstBitrate);

  /**
   * Checks if is use video encoding vbr.
   *
   * @return true, if is use video encoding vbr
   */
  boolean isUseVideoEncodingVBR();

  /**
   * Sets the use video encoding vbr.
   *
   * @param useVideoEncodingVBR the new use video encoding vbr
   */
  void setUseVideoEncodingVBR(boolean useVideoEncodingVBR);

  /**
   * Gets the video vbr quality.
   *
   * @return the video vbr quality
   */
  IndexedHashMap<String, Integer> getVideoVBRQuality();

  /**
   * Gets the video vbr quality value.
   *
   * @return the video vbr quality value
   */
  int getVideoVBRQualityValue();

  /**
   * Sets the video vbr quality.
   *
   * @param codec the codec
   * @param videoVBRQuality the video vbr quality
   */
  void setVideoVBRQuality(VideoCodecs codec, Integer videoVBRQuality);

  /**
   * Sets the video vbr quality.
   *
   * @param videoVbrQual the video vbr qual
   */
  void setVideoVBRQuality(IndexedHashMap<String, Integer> videoVbrQual);

  /**
   * Checks if is use video encoding file size.
   *
   * @return true, if is use video encoding file size
   */
  boolean isUseVideoEncodingFileSize();

  /**
   * Sets the use video encoding file size.
   *
   * @param useVideoEncodingFileSize the new use video encoding file size
   */
  void setUseVideoEncodingFileSize(boolean useVideoEncodingFileSize);
  
  
  boolean isUseVideoFileSizeAsReference();
  
  void setUseVideoFileSizeAsReference(boolean useVideoFileSizeAsReference);

  /**
   * Gets the video encoding file size.
   *
   * @return the video encoding file size
   */
  int getVideoEncodingFileSize();

  /**
   * Sets the video encoding file size.
   *
   * @param videoEncodingFileSize the new video encoding file size
   */
  void setVideoEncodingFileSize(int videoEncodingFileSize);

  /**
   * Gets the extra video ffmpeg cmd.
   *
   * @return the extravideoffmpegcmd
   */
  // String getExtravideoffmpegcmd();

  /**
   * Sets the extra video ffmpeg cmd.
   *
   * @param extravideoffmpegcmd the new extravideoffmpegcmd
   */
  // void setExtravideoffmpegcmd(String extravideoffmpegcmd);

  /**
   * Checks if is use only extra video ffmpeg cmd.
   *
   * @return true, if is useonlyextravideoffmpegcmd
   */
  // boolean isUseonlyextravideoffmpegcmd();

  /**
   * Sets the use only extra video ffmpeg cmd.
   *
   * @param useonlyextravideoffmpegcmd the new useonlyextravideoffmpegcmd
   */
  // void setUseonlyextravideoffmpegcmd(boolean useonlyextravideoffmpegcmd);

  /**
   * Gets the file extension.
   *
   * @return the fileextension
   */
  String getFileextension();

  /**
   * Sets the file extension.
   *
   * @param fileextension the new fileextension
   */
  void setFileextension(String fileextension);

  /**
   * Checks if is two pass encoding.
   *
   * @return true, if is twopass encoding
   */
  boolean isTwopassEncoding();

  /**
   * Sets the two pass encoding.
   *
   * @param twopassEncoding the new twopass encoding
   */
  void setTwopassEncoding(boolean twopassEncoding);

  /**
   * Gets the output folder.
   *
   * @return the output folder
   */
  String getOutputFolder();

  /**
   * Sets the output folder.
   *
   * @param outputFolder the new output folder
   */
  void setOutputFolder(String outputFolder);

  /**
   * Gets the audio codec.
   *
   * @return the audiocodec
   */
  AudioCodecs getAudiocodec();

  /**
   * Sets the audio codec.
   *
   * @param audiocodec the new audiocodec
   */
  void setAudiocodec(AudioCodecs audiocodec);

  /**
   * Checks if is use audio cbr.
   *
   * @return true, if is use audio cbr
   */
  boolean isUseAudioCBR();

  /**
   * Sets the use audio cbr.
   *
   * @param useAudioCBR the new use audio cbr
   */
  void setUseAudioCBR(boolean useAudioCBR);

  /**
   * Gets the audio constant bitrate value.
   *
   * @return the audio constant bitrate value
   */
  String getAudioConstantBitrateValue();

  /**
   * Sets the audio constant bitrate value.
   *
   * @param audioConstantBitrateValue the new audio constant bitrate value
   */
  void setAudioConstantBitrateValue(String audioConstantBitrateValue);

  /**
   * Gets the audio variable bitrate values.
   *
   * @return the audio variable bitrate values
   */
  int getAudioVariableBitrateValues();

  /**
   * Sets the audio variable bitrate values.
   *
   * @param audioVariableBitrateValues the new audio variable bitrate values
   */
  void setAudioVariableBitrateValues(int audioVariableBitrateValues);

  /**
   * Gets the audio sample rate.
   *
   * @return the audiosamplerate
   */
  AudioSampleRates getAudiosamplerate();

  /**
   * Sets the audio sample rate.
   *
   * @param audiosamplerate the new audiosamplerate
   */
  void setAudiosamplerate(AudioSampleRates audiosamplerate);

  /**
   * Gets the max allowed audio sample rate.
   *
   * @return the max allowed audiosamplerate
   */
  AudioSampleRates getMaxAllowedAudiosamplerate();

  /**
   * Sets the max allowed audio sample rate.
   *
   * @param audiosamplerate the new max allowed audiosamplerate
   */
  void setMaxAllowedAudiosamplerate(AudioSampleRates audiosamplerate);

  /**
   * Gets the audio channels.
   *
   * @return the audiochannels
   */
  AudioChannels getAudiochannels();

  /**
   * Sets the audio channels.
   *
   * @param audiochannels the new audiochannels
   */
  void setAudiochannels(AudioChannels audiochannels);

  /**
   * Gets the max allowed audio channels.
   *
   * @return the max allowed audiochannels
   */
  AudioChannels getMaxAllowedAudiochannels();

  /**
   * Sets the max allowed audio channels.
   *
   * @param audiochannels the new max allowed audiochannels
   */
  void setMaxAllowedAudiochannels(AudioChannels audiochannels);

  /**
   * Checks if is ac3 passthrough.
   *
   * @return true, if is ac3passthrough
   */
  boolean isAc3passthrough();

  /**
   * Sets the ac3 passthrough.
   *
   * @param ac3passthrough the new ac3passthrough
   */
  void setAc3passthrough(boolean ac3passthrough);

  /**
   * Gets the extra audio ffmpeg cmd.
   *
   * @return the extraaudioffmpegcmd
   */
  String getExtraaudioffmpegcmd();

  /**
   * Sets the extra audio ffmpeg cmd.
   *
   * @param extraaudioffmpegcmd the new extraaudioffmpegcmd
   */
  void setExtraaudioffmpegcmd(String extraaudioffmpegcmd);

  /**
   * Checks if is use only extra audio ffmpeg cmd.
   *
   * @return true, if is useonlyextraaudioffmpegcmd
   */
  boolean isUseonlyextraaudioffmpegcmd();

  /**
   * Sets the use only extra audio ffmpeg cmd.
   *
   * @param useonlyextraaudioffmpegcmd the new useonlyextraaudioffmpegcmd
   */
  void setUseonlyextraaudioffmpegcmd(boolean useonlyextraaudioffmpegcmd);

  SubtitleInfoContainer getSubtitleInfoContainer();

  void setSubtitleInfoContainer(SubtitleInfoContainer subinfo);

  /**
   * Checks if is modified tag.
   *
   * @return true, if is modifiedtag
   */
  boolean isModifiedtag();

  /**
   * Sets the modified tag.
   *
   * @param modifiedtag the new modifiedtag
   */
  void setModifiedtag(boolean modifiedtag);

  String getProfileTypeTag();

  void setProfileTypeTag(String typetag);

  FiltersInfoContainer getFilters();

  void setFilters(FiltersInfoContainer filters);

  void setHardwareAccelerationDecoder(HWAccel decoder);

  HWAccel getHardwareAccelerationDecoder();

  void setConfigurations(PropertiesConfiguration props);

  void saveConfigurations(String filepath, String nameprofile, boolean isprofile);

  boolean useSpecificVideoEncodingParameters();

  ArrayList<String> getSpecificVideoEncoderParameters(ArrayList<String> appendcmds);

  void setMaxMuxingQueueSize(int size);

  int getMaxMuxingQueueSize();

  /**
   * Copy common info of container.
   *
   * @param newcont the newcont
   * @return the i general video enc info container
   */
  IGeneralVideoEncInfoContainer copyCommoninfoofcontainer(IGeneralVideoEncInfoContainer newcont);

  /**
   * Copy specific info container.
   *
   * @param newcont the newcont
   * @return the i general video enc info container
   */
  IGeneralVideoEncInfoContainer copySpecificInfocontainer(IGeneralVideoEncInfoContainer newcont);

  /**
   * Clone.
   *
   * @return the main video encoding info container
   */
  DefaultEncodingInfoContainer clone();

  /**
   * Prints the.
   */
  void print();

}
