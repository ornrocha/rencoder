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
package pt.ornrocha.rencoder.mediafiles.files.containers.codecs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.configuration.PropertiesConfiguration;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VP8QualityProfiles;
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
 * The Class VideoVP8EncodingInfoContainer.
 */
public class VideoVP8EncodingInfoContainer extends DefaultEncodingInfoContainer {// implements
                                                                                 // IVideoVPxEncInfo{

  /** The quality profile. */
  protected VP8QualityProfiles qualityprofile = VP8QualityProfiles.none;

  /** The cpu used. */
  protected int cpuUsed = 3;

  /** The min quant. */
  protected int minquant = -1;

  /** The max quant. */
  protected int maxquant = -1;

  /** The cpu threads. */
  protected int cputhreads = 1;

  /**
   * Instantiates a new video v p8 encoding info container.
   */
  public VideoVP8EncodingInfoContainer() {
    super();
    setDefaultInfo();

  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.MainVideoEncodingInfoContainer#setDefaultInfo( )
   */
  public void setDefaultInfo() {
    this.videocodec = VideoCodecs.VP8;
    this.videoContainer = VideoContainers.WEBM;
    this.videoVBRQuality = new IndexedHashMap<String, Integer>() {
      {
        put(VideoCodecs.VP8.toString(), 20);
      }
    };;
    this.audiocodec = AudioCodecs.VORBIS;
    this.useAudioCBR = true;
    this.audioConstantBitrateValue = "128k";
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IVideoVP8EncInfo#getQualityprofile( )
   */
  public VP8QualityProfiles getQualityprofile() {
    return qualityprofile;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IVideoVP8EncInfo#setQualityprofile(
   * ffmpegWrapper.enumerators.VP8QualityProfiles)
   */
  public void setQualityprofile(VP8QualityProfiles qualityprofile) {
    this.qualityprofile = qualityprofile;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IVideoVP8EncInfo#getCpuUsed()
   */
  public int getCpuUsed() {
    return cpuUsed;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IVideoVP8EncInfo#setCpuUsed(int)
   */
  public void setCpuUsed(int cpuUsed) {
    this.cpuUsed = cpuUsed;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IVideoVP8EncInfo#getMinquant()
   */
  public int getMinquant() {
    return minquant;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IVideoVP8EncInfo#setMinquant(int)
   */
  public void setMinquant(int minquant) {
    this.minquant = minquant;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IVideoVP8EncInfo#getMaxquant()
   */
  public int getMaxquant() {
    return maxquant;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IVideoVP8EncInfo#setMaxquant(int)
   */
  public void setMaxquant(int maxquant) {
    this.maxquant = maxquant;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IVideoVP8EncInfo#getCputhreads()
   */
  public int getCputhreads() {
    return cputhreads;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IVideoVP8EncInfo#setCputhreads(int)
   */
  public void setCputhreads(int cputhreads) {
    this.cputhreads = cputhreads;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.MainVideoEncodingInfoContainer#clone()
   */
  @Override
  public DefaultEncodingInfoContainer clone() {
    VideoVP8EncodingInfoContainer clone = new VideoVP8EncodingInfoContainer();
    clone = (VideoVP8EncodingInfoContainer) this.copyCommoninfoofcontainer(clone);
    clone = (VideoVP8EncodingInfoContainer) this.copySpecificInfocontainer(clone);

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
  public IGeneralVideoEncInfoContainer copySpecificInfocontainer(
      IGeneralVideoEncInfoContainer newcont) {

    ((VideoVP8EncodingInfoContainer) newcont).setQualityprofile(this.qualityprofile);

    ((VideoVP8EncodingInfoContainer) newcont).setCpuUsed(this.cpuUsed);

    ((VideoVP8EncodingInfoContainer) newcont).setMinquant(this.minquant);

    ((VideoVP8EncodingInfoContainer) newcont).setMaxquant(this.maxquant);

    ((VideoVP8EncodingInfoContainer) newcont).setCputhreads(this.cputhreads);

    return newcont;
  }

  @Override
  protected ArrayList<VideoContainers> getSupportedFormats() {
    return Arrays.stream(VideoCodecs.VP8.supportsOutputFormats())
        .collect(Collectors.toCollection(ArrayList::new));
  }

  @Override
  protected VideoContainers getpreferredFormat() {
    return VideoContainers.WEBM;
  }

  @Override
  public void setConfigurations(PropertiesConfiguration props) {

    setQualityprofile(VP8QualityProfiles.getVP8QualityProfilesFromString(
        PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.QUALPROFILE)));
    setCpuUsed(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VP8USECPU, 1,
        Runtime.getRuntime().availableProcessors(), 1));
    setCputhreads(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VP8CPUTHREADS, 1,
        Runtime.getRuntime().availableProcessors() * 2, 1));
    setMinquant(
        PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VP8MINQUANT, -1, 10, -1));
    setMaxquant(
        PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VP8MINQUANT, -1, 42, -1));
    
    setVideoConfigurations(props);
    setAudioConfigurations(props);
    setSubtitleConfigurations(props);
  }

  @Override
  protected void saveSpecificCodecProperties(PropertiesConfiguration prop) {

    prop.setProperty(StaticVideoEncoderFields.VP8CPUTHREADS, String.valueOf(getCputhreads()));
    prop.setProperty(StaticVideoEncoderFields.QUALPROFILE, getQualityprofile().toString());
    prop.setProperty(StaticVideoEncoderFields.VP8USECPU, String.valueOf(getCpuUsed()));
    prop.setProperty(StaticVideoEncoderFields.VP8MINQUANT, String.valueOf(getMinquant()));
    prop.setProperty(StaticVideoEncoderFields.VP8MAXQUANT, String.valueOf(getMaxquant()));

  }

  @Override
  public ArrayList<String> getSpecificVideoEncoderParameters(ArrayList<String> appendcmds) {
    ArrayList<String> vp8param = new ArrayList<>();

    if (!getQualityprofile().equals(VP8QualityProfiles.none)) {
      vp8param.add(StaticFFmpegFields.vp8profile);
      vp8param.add(getQualityprofile().toString());
    }
    int minq = getMinquant();
    int maxq = getMaxquant();
    if (minq > -1 && maxq > -1 && minq < maxq) {

      vp8param.add(StaticFFmpegFields.vp8minquant);
      vp8param.add(String.valueOf(getMinquant()));
      vp8param.add(StaticFFmpegFields.vp8maxquant);
      vp8param.add(String.valueOf(getMaxquant()));

    }

    vp8param.add(StaticFFmpegFields.vp8cpuused);
    vp8param.add(String.valueOf(getCpuUsed()));

    if (getNumberThreads() < getCputhreads()) {
      vp8param.add(StaticFFmpegFields.cputhreads);
      vp8param.add(String.valueOf(getCputhreads()));
    }

    return vp8param;
  }

  @Override
  public boolean useSpecificVideoEncodingParameters() {
    return true;
  }

  @Override
  public String getContainerName() {
    // TODO Auto-generated method stub
    return "VP8";
  }



}
