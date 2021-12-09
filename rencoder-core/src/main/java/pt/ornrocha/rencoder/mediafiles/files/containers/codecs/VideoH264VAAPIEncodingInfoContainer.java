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
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.H264VaapiCoder;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.H264VaapiProfile;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.HWAccel;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoContainers;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoLevel;
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
public class VideoH264VAAPIEncodingInfoContainer extends DefaultEncodingInfoContainer {

  /** The profile. */
  private H264VaapiProfile profile = H264VaapiProfile.main;

  /** The constant quantization. */
  private int quality = -1;

  private VideoLevel level = VideoLevel.NONE;

  private H264VaapiCoder coder = H264VaapiCoder.Ddefault;

  /**
   * Instantiates a new video h264 encoding info container.
   */
  public VideoH264VAAPIEncodingInfoContainer() {
    super();
    setDefaultInfo();

  }

  @Override
  public void setDefaultInfo() {
    this.videocodec = VideoCodecs.H264VAAPI;
    this.videoContainer = VideoContainers.MKV;
    this.videoVBRQuality = new IndexedHashMap<String, Integer>() {
      /**
       * 
       */
      private static final long serialVersionUID = 1L;

      {
        put(VideoCodecs.H264VAAPI.toString(), 20);
      }
    };
    this.audiocodec = AudioCodecs.LCAAC;
    this.useAudioCBR = true;
    this.audioConstantBitrateValue = "128k";
    // this.extrainfocont=null;
  }

  public H264VaapiProfile getProfile() {
    return profile;
  }

  public void setProfile(H264VaapiProfile profile) {
    this.profile = profile;
  }


  public VideoLevel getLevel() {
    return level;
  }

  public void setLevel(VideoLevel level) {
    this.level = level;
  }

  public int getQuality() {
    return quality;
  }

  public void setQuality(int quality) {
    this.quality = quality;
  }



  public H264VaapiCoder getCoder() {
    return coder;
  }

  public void setCoder(H264VaapiCoder coder) {
    this.coder = coder;
  }

  @Override
  public HWAccel getHardwareAccelerationDecoder() {
    return HWAccel.VAAPI;
  }

  /**
   * The main method.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    VideoH264VAAPIEncodingInfoContainer v = new VideoH264VAAPIEncodingInfoContainer();
    System.out.println(v.getVideocodec().toString());

  }

  @Override
  public VideoH264VAAPIEncodingInfoContainer clone() {

    VideoH264VAAPIEncodingInfoContainer clone = new VideoH264VAAPIEncodingInfoContainer();
    clone = (VideoH264VAAPIEncodingInfoContainer) this.copyCommoninfoofcontainer(clone);
    clone = (VideoH264VAAPIEncodingInfoContainer) this.copySpecificInfocontainer(clone);

    return clone;
  }

  @Override
  public IGeneralVideoEncInfoContainer copySpecificInfocontainer(
      IGeneralVideoEncInfoContainer cont) {

    ((VideoH264VAAPIEncodingInfoContainer) cont).setProfile(this.profile);
    ((VideoH264VAAPIEncodingInfoContainer) cont).setLevel(this.level);
    ((VideoH264VAAPIEncodingInfoContainer) cont).setQuality(quality);
    ((VideoH264VAAPIEncodingInfoContainer) cont).setCoder(coder);
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

    setProfile(H264VaapiProfile
        .getProfile(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOPROFILE)));

    setLevel(VideoLevel.getVideoLevelFromString(
        PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOLEVEL)));

    Integer qual =
        PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VAAPIQUALITY, null);
    if (qual != null)
      setQuality(qual);

    setCoder(H264VaapiCoder.getCoderFromString(
        PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.H264VAAPICODER)));

    setVideoConfigurations(props);
    setAudioConfigurations(props);
    setSubtitleConfigurations(props);

  }

  @Override
  protected void saveSpecificCodecProperties(PropertiesConfiguration prop) {

    prop.setProperty(StaticVideoEncoderFields.VIDEOPROFILE, getProfile().toString());
    prop.setProperty(StaticVideoEncoderFields.VIDEOLEVEL, getLevel().toString());
    prop.setProperty(StaticVideoEncoderFields.VAAPIQUALITY, String.valueOf(getQuality()));
    prop.setProperty(StaticVideoEncoderFields.H264VAAPICODER, getCoder().toString());
  }

  @Override
  public ArrayList<String> getSpecificVideoEncoderParameters(ArrayList<String> appendcmds) {
    ArrayList<String> x264vaapiparam = new ArrayList<>();

    if (!getProfile().equals(H264VaapiProfile.main)) {
      x264vaapiparam.add(StaticFFmpegFields.PROFILE);
      x264vaapiparam.add(getProfile().toString());
    }
    if (!level.equals(VideoLevel.NONE)) {
      x264vaapiparam.add(StaticFFmpegFields.LEVEL);
      x264vaapiparam.add(getLevel().toString());
    }
    if (!coder.equals(H264VaapiCoder.Ddefault)) {
      x264vaapiparam.add(StaticFFmpegFields.H264NVENCCODER);
      x264vaapiparam.add(getCoder().toString());
    }
    if (quality != -1) {
      x264vaapiparam.add("-quality");
      x264vaapiparam.add(String.valueOf(quality));
    }
    return x264vaapiparam;
  }

  @Override
  public boolean useSpecificVideoEncodingParameters() {
    return true;
  }

  @Override
  public String getContainerName() {
    // TODO Auto-generated method stub
    return "H264VAAPI";
  }

}
