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
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.HEVCVaapiProfile;
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
public class VideoHEVCVaapiEncodingInfoContainer extends DefaultEncodingInfoContainer {

  /** The profile. */
  private HEVCVaapiProfile profile = HEVCVaapiProfile.main;

  private VideoLevel level = VideoLevel.NONE;

  /** The constant quantization. */
  private int tier = 0; // 0 or 1

  private boolean lowpower = false;

  private boolean aud = false;



  /**
   * Instantiates a new video h264 encoding info container.
   */
  public VideoHEVCVaapiEncodingInfoContainer() {
    super();
    setDefaultInfo();

  }

  @Override
  public void setDefaultInfo() {
    this.videocodec = VideoCodecs.HEVCVAAPI;
    this.videoContainer = VideoContainers.MKV;
    this.videoVBRQuality = new IndexedHashMap<String, Integer>() {
      /**
       * 
       */
      private static final long serialVersionUID = 1L;

      {
        put(VideoCodecs.HEVCVAAPI.toString(), 20);
      }
    };
    this.audiocodec = AudioCodecs.LCAAC;
    this.useAudioCBR = true;
    this.audioConstantBitrateValue = "128k";
    // this.extrainfocont=null;
  }

  // h265_vaapi AVOptions:
  // -low_power <boolean> E..V..... Use low-power encoding mode (only available on some platforms;
  // may not support all encoding features) (default false)
  // -qp <int> E..V..... Constant QP (for P-frames; scaled by qfactor/qoffset for I/B) (from 0 to
  // 52) (default 25)
  // -aud <boolean> E..V..... Include AUD (default false)
  // -profile <int> E..V..... Set profile (general_profile_idc) (from -99 to 255) (default -99)
  // main E..V.....
  // main10 E..V.....
  // rext E..V.....
  // -tier <int> E..V..... Set tier (general_tier_flag) (from 0 to 1) (default main)
  // main E..V.....
  // high E..V.....
  // -level <int> E..V..... Set level (general_level_idc) (from -99 to 255) (default -99)
  // 1 E..V.....
  // 2 E..V.....
  // 2.1 E..V.....
  // 3 E..V.....
  // 3.1 E..V.....
  // 4 E..V.....
  // 4.1 E..V.....
  // 5 E..V.....
  // 5.1 E..V.....
  // 5.2 E..V.....
  // 6 E..V.....
  // 6.1 E..V.....
  // 6.2 E..V.....
  // -sei <flags> E..V..... Set SEI to include (default hdr)
  // hdr E..V..... Include HDR metadata for mastering display colour volume and content light level
  // information

  public HEVCVaapiProfile getProfile() {
    return profile;
  }

  public void setProfile(HEVCVaapiProfile profile) {
    this.profile = profile;
  }


  public VideoLevel getLevel() {
    return level;
  }

  public void setLevel(VideoLevel level) {
    this.level = level;
  }

  public int getTier() {
    return tier;
  }

  public void setTier(int tier) {
    this.tier = tier;
  }

  public boolean isLowpower() {
    return lowpower;
  }

  public void setLowpower(boolean lowpower) {
    this.lowpower = lowpower;
  }

  public boolean isAud() {
    return aud;
  }

  public void setAud(boolean aud) {
    this.aud = aud;
  }

  @Override
  public HWAccel getHardwareAccelerationDecoder() {
    return HWAccel.VAAPI;
  }



  @Override
  public VideoHEVCVaapiEncodingInfoContainer clone() {

    VideoHEVCVaapiEncodingInfoContainer clone = new VideoHEVCVaapiEncodingInfoContainer();
    clone = (VideoHEVCVaapiEncodingInfoContainer) this.copyCommoninfoofcontainer(clone);
    clone = (VideoHEVCVaapiEncodingInfoContainer) this.copySpecificInfocontainer(clone);

    return clone;
  }

  @Override
  public IGeneralVideoEncInfoContainer copySpecificInfocontainer(
      IGeneralVideoEncInfoContainer cont) {

    ((VideoHEVCVaapiEncodingInfoContainer) cont).setProfile(this.profile);
    ((VideoHEVCVaapiEncodingInfoContainer) cont).setLevel(this.level);
    ((VideoHEVCVaapiEncodingInfoContainer) cont).setTier(this.tier);
    ((VideoHEVCVaapiEncodingInfoContainer) cont).setAud(this.aud);
    ((VideoHEVCVaapiEncodingInfoContainer) cont).setLowpower(this.lowpower);
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

   
    setProfile(HEVCVaapiProfile
        .getProfile(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOPROFILE)));

    setLevel(VideoLevel.getVideoLevelFromString(
        PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOLEVEL)));

    setTier(PropertiesWorker.checkProperty(props, "tier", 0));
    setLowpower(PropertiesWorker.checkProperty(props, "low_power", false));
    setAud(PropertiesWorker.checkProperty(props, "aud", false));

     setVideoConfigurations(props);
    setAudioConfigurations(props);
    setSubtitleConfigurations(props);

  }

  @Override
  protected void saveSpecificCodecProperties(PropertiesConfiguration prop) {

    prop.setProperty(StaticVideoEncoderFields.VIDEOPROFILE, getProfile().toString());
    prop.setProperty(StaticVideoEncoderFields.VIDEOLEVEL, getLevel().toString());
    prop.setProperty("tier", String.valueOf(tier));
    prop.setProperty("low_power", String.valueOf(lowpower));
    prop.setProperty("aud", String.valueOf(aud));

  }

  @Override
  public ArrayList<String> getSpecificVideoEncoderParameters(ArrayList<String> appendcmds) {
    ArrayList<String> hevcvaapiparam = new ArrayList<>();

    if (!getProfile().equals(HEVCVaapiProfile.main)) {
      hevcvaapiparam.add(StaticFFmpegFields.PROFILE);
      hevcvaapiparam.add(getProfile().toString());
    }
    if (!getLevel().equals(VideoLevel.NONE)) {
      hevcvaapiparam.add(StaticFFmpegFields.LEVEL);
      hevcvaapiparam.add(getLevel().toString());
    }
    if (tier != 0) {
      hevcvaapiparam.add("-tier");
      hevcvaapiparam.add(String.valueOf(tier));
    }
    if (lowpower) {
      hevcvaapiparam.add("-low_power");
      hevcvaapiparam.add(String.valueOf(lowpower));
    }
    if (aud) {
      hevcvaapiparam.add("-aud");
      hevcvaapiparam.add(String.valueOf(aud));
    }
    return hevcvaapiparam;
  }

  @Override
  public boolean useSpecificVideoEncodingParameters() {
    return true;
  }

  @Override
  public String getContainerName() {
    // TODO Auto-generated method stub
    return "HEVCVaapi";
  }

}
