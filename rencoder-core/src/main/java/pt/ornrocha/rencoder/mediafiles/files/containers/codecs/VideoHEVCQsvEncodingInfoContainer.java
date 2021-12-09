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
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.HWAccel;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.PixelFormatQsv;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.ProfileHEVCQsv;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.QsvPresets;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoContainers;
import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.helpers.props.fields.StaticFFmpegFields;
import pt.ornrocha.rencoder.helpers.props.fields.StaticVideoEncoderFields;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;
import pt.ornrocha.rencoder.mediafiles.files.containers.extrainfocontainer.HEVCQsvExtraInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.DefaultEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;

// TODO: Auto-generated Javadoc
/**
 * The Class VideoH264EncodingInfoContainer.
 */
public class VideoHEVCQsvEncodingInfoContainer extends DefaultEncodingInfoContainer {

  /** The profile. */
  private ProfileHEVCQsv profile = ProfileHEVCQsv.unknown;

  private PixelFormatQsv pixelformat = PixelFormatQsv.nv12;

  private QsvPresets preset = QsvPresets.medium;



  /**
   * Instantiates a new video h264 encoding info container.
   */
  public VideoHEVCQsvEncodingInfoContainer() {
    super();
    setDefaultInfo();

  }

  @Override
  public void setDefaultInfo() {
    this.videocodec = VideoCodecs.HEVCQSV;
    this.videoContainer = VideoContainers.MKV;
    this.videoVBRQuality = new IndexedHashMap<String, Integer>() {
      /**
       * 
       */
      private static final long serialVersionUID = 1L;

      {
        put(VideoCodecs.HEVCQSV.toString(), 20);
      }
    };
    this.audiocodec = AudioCodecs.LCAAC;
    this.useAudioCBR = true;
    this.audioConstantBitrateValue = "128k";
    // this.extrainfocont=null;
  }

  // Encoder hevc_qsv [HEVC (Intel Quick Sync Video acceleration)]:
  // General capabilities: delay hybrid
  // Threading capabilities: none
  // Supported pixel formats: nv12 p010le qsv
  // hevc_qsv encoder AVOptions:
  // -async_depth <int> E..V..... Maximum processing parallelism (from 1 to INT_MAX) (default 4)
  // -avbr_accuracy <int> E..V..... Accuracy of the AVBR ratecontrol (from 0 to INT_MAX) (default 0)
  // -avbr_convergence <int> E..V..... Convergence of the AVBR ratecontrol (from 0 to INT_MAX)
  // (default 0)
  // -preset <int> E..V..... (from 1 to 7) (default medium)
  // veryfast 7 E..V.....
  // faster 6 E..V.....
  // fast 5 E..V.....
  // medium 4 E..V.....
  // slow 3 E..V.....
  // slower 2 E..V.....
  // veryslow 1 E..V.....
  // -rdo <int> E..V..... Enable rate distortion optimization (from -1 to 1) (default -1)
  // -max_frame_size <int> E..V..... Maximum encoded frame size in bytes (from -1 to 65535) (default
  // -1)
  // -max_slice_size <int> E..V..... Maximum encoded slice size in bytes (from -1 to 65535) (default
  // -1)
  // -bitrate_limit <int> E..V..... Toggle bitrate limitations (from -1 to 1) (default -1)
  // -mbbrc <int> E..V..... MB level bitrate control (from -1 to 1) (default -1)
  // -extbrc <int> E..V..... Extended bitrate control (from -1 to 1) (default -1)
  // -adaptive_i <int> E..V..... Adaptive I-frame placement (from -1 to 1) (default -1)
  // -adaptive_b <int> E..V..... Adaptive B-frame placement (from -1 to 1) (default -1)
  // -b_strategy <int> E..V..... Strategy to choose between I/P/B-frames (from -1 to 1) (default -1)
  // -forced_idr <boolean> E..V..... Forcing I frames as IDR frames (default false)
  // -low_power <boolean> E..V..... enable low power mode(experimental: many limitations by mfx
  // version, BRC modes, etc.) (default false)
  // -idr_interval <int> E..V..... Distance (in I-frames) between IDR frames (from -1 to INT_MAX)
  // (default 0)
  // begin_only -1 E..V..... Output an IDR-frame only at the beginning of the stream
  // -load_plugin <int> E..V..... A user plugin to load in an internal session (from 0 to 2)
  // (default hevc_hw)
  // none 0 E..V.....
  // hevc_sw 1 E..V.....
  // hevc_hw 2 E..V.....
  // -load_plugins <string> E..V..... A :-separate list of hexadecimal plugin UIDs to load in an
  // internal session (default "")
  // -profile <int> E..V..... (from 0 to INT_MAX) (default unknown)
  // unknown 0 E..V.....
  // main 1 E..V.....
  // main10 2 E..V.....
  // mainsp 3 E..V.....
  // -gpb <boolean> E..V..... 1: GPB (generalized P/B frame); 0: regular P frame (default true)
  //



  @Override
  public HWAccel getHardwareAccelerationDecoder() {
    return HWAccel.QSV;
  }

  public ProfileHEVCQsv getProfile() {
    return profile;
  }

  public void setProfile(ProfileHEVCQsv profile) {
    this.profile = profile;
  }

  public PixelFormatQsv getPixelformat() {
    return pixelformat;
  }

  public void setPixelformat(PixelFormatQsv pixelformat) {
    this.pixelformat = pixelformat;
  }

  public QsvPresets getPreset() {
    return preset;
  }

  public void setPreset(QsvPresets preset) {
    this.preset = preset;
  }



  @Override
  public VideoHEVCQsvEncodingInfoContainer clone() {

    VideoHEVCQsvEncodingInfoContainer clone = new VideoHEVCQsvEncodingInfoContainer();
    clone = (VideoHEVCQsvEncodingInfoContainer) this.copyCommoninfoofcontainer(clone);
    clone = (VideoHEVCQsvEncodingInfoContainer) this.copySpecificInfocontainer(clone);

    return clone;
  }

  @Override
  public IGeneralVideoEncInfoContainer copySpecificInfocontainer(
      IGeneralVideoEncInfoContainer cont) {

    ((VideoHEVCQsvEncodingInfoContainer) cont).setProfile(this.profile);
    ((VideoHEVCQsvEncodingInfoContainer) cont).setPreset(this.preset);
    ((VideoHEVCQsvEncodingInfoContainer) cont).setPixelformat(this.pixelformat);
    if (this.extrainfocont != null) {
      ((VideoHEVCQsvEncodingInfoContainer) cont).setExtrainfocont(extrainfocont.clone());
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

    

    setProfile(ProfileHEVCQsv
        .getProfile(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOPROFILE)));

    setPixelformat(PixelFormatQsv.getPixelFormatFromString(
        PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOPIXELFORMAT)));
    setPreset(QsvPresets.getQsvPresetsFromString(
        PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOPRESET)));

    setExtrainfocont(HEVCQsvExtraInfoContainer.loadHEVCQsvExtraInfoContainer(props));

    setVideoConfigurations(props);
    setAudioConfigurations(props);
    setSubtitleConfigurations(props);

  }

  @Override
  protected void saveSpecificCodecProperties(PropertiesConfiguration prop) {

    prop.setProperty(StaticVideoEncoderFields.VIDEOPROFILE, getProfile().toString());
    prop.setProperty(StaticVideoEncoderFields.VIDEOPIXELFORMAT, getPixelformat().toString());
    prop.setProperty(StaticVideoEncoderFields.VIDEOPRESET, getPreset().toString());
    if (getExtrainfocont() != null) {
      getExtrainfocont().saveConfigurationToFileProperties(prop);
    }

  }

  @Override
  public ArrayList<String> getSpecificVideoEncoderParameters(ArrayList<String> appendcmds) {
    ArrayList<String> hevcqsvparam = new ArrayList<>();

    if (!getProfile().equals(ProfileHEVCQsv.unknown)) {
      hevcqsvparam.add(StaticFFmpegFields.PROFILE);
      hevcqsvparam.add(getProfile().toString());
    }
    if (!getPreset().equals(QsvPresets.medium)) {
      hevcqsvparam.add(StaticFFmpegFields.PRESET);
      hevcqsvparam.add(getPreset().toString());
    }
    if (!getPixelformat().equals(PixelFormatQsv.nv12)) {
      hevcqsvparam.add(StaticFFmpegFields.PROFILE);
      hevcqsvparam.add(getPixelformat().toString());
    }
    if (getExtrainfocont() != null)
      hevcqsvparam.addAll(getExtrainfocont().getFFmpegCmds());

    return hevcqsvparam;
  }

  @Override
  public boolean useSpecificVideoEncodingParameters() {
    return true;
  }

  @Override
  public String getContainerName() {
    // TODO Auto-generated method stub
    return "HEVCQsv";
  }

}
