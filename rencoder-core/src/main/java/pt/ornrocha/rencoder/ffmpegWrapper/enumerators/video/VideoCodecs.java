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
package pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import pt.ornrocha.rencoder.helpers.osystem.OSystem;
import pt.ornrocha.rencoder.helpers.props.fields.StaticFFmpegFields;

// TODO: Auto-generated Javadoc
/**
 * The Enum VideoCodecs.
 */
public enum VideoCodecs {

  /** The xvid. */
  XVID {

    @Override
    public ArrayList<String> getMainCodecCmd() {
      ArrayList<String> cmd = new ArrayList<>();
      cmd.add(StaticFFmpegFields.encodevideocodec);
      cmd.add(getFFmpegID());

      return cmd;
    }

    @Override
    public String toString() {
      return "Xvid";
    }

    @Override
    public String getFFmpegID() {
      return StaticFFmpegFields.XVID;
    }

    @Override
    public boolean needsDecodingFilter() {
      return false;
    }

    @Override
    public boolean supportsDecodingHardwareAccel() {
      return false;
    }

    @Override
    public ArrayList<String> getDecodingHWAcceleration() {
      return null;
    }

    @Override
    public VideoContainers[] supportsOutputFormats() {
      VideoContainers[] res = {VideoContainers.AVI};
      return res;
    }

    @Override
    public boolean needsSupportVerification() {
      return false;
    }

    @Override
    public boolean supportsVariableBitrate() {
      return true;
    }

    @Override
    public boolean isExperimental() {
      return false;
    }

  },

  /** The MPEG4. */
  MPEG4 {

    @Override
    public ArrayList<String> getMainCodecCmd() {
      ArrayList<String> cmd = new ArrayList<>();
      cmd.add(StaticFFmpegFields.encodevideocodec);
      cmd.add(StaticFFmpegFields.Mpeg4);
      cmd.add(StaticFFmpegFields.force_video_tag);
      cmd.add(StaticFFmpegFields.mpeg4tag);
      return cmd;
    }

    @Override
    public String toString() {
      return "MPEG-4";
    }

    @Override
    public String getFFmpegID() {
      return StaticFFmpegFields.Mpeg4;
    }

    @Override
    public boolean needsDecodingFilter() {
      return false;
    }

    @Override
    public boolean supportsDecodingHardwareAccel() {
      return false;
    }

    @Override
    public ArrayList<String> getDecodingHWAcceleration() {
      return null;
    }

    @Override
    public VideoContainers[] supportsOutputFormats() {
      VideoContainers[] res = {VideoContainers.AVI};
      return res;
    }

    @Override
    public boolean needsSupportVerification() {
      return false;
    }

    @Override
    public boolean supportsVariableBitrate() {
      return true;
    }

    @Override
    public boolean isExperimental() {
      return false;
    }

  },

  /** The H264. */
  H264 {

    @Override
    public ArrayList<String> getMainCodecCmd() {
      ArrayList<String> cmd = new ArrayList<>();
      cmd.add(StaticFFmpegFields.encodevideocodec);
      cmd.add(getFFmpegID());
      return cmd;
    }

    @Override
    public String toString() {
      return "H264";
    }

    @Override
    public String getFFmpegID() {
      return StaticFFmpegFields.H264;
    }

    @Override
    public boolean needsDecodingFilter() {
      return false;
    }

    @Override
    public boolean supportsDecodingHardwareAccel() {
      return false;
    }

    @Override
    public VideoContainers[] supportsOutputFormats() {
      VideoContainers[] res = {VideoContainers.MKV, VideoContainers.MP4, VideoContainers.TS,
          VideoContainers.FLV, VideoContainers.E3GP};
      return res;
    }

    @Override
    public boolean needsSupportVerification() {
      return false;
    }

    @Override
    public boolean supportsVariableBitrate() {
      return true;
    }

    @Override
    public boolean isExperimental() {
      return false;
    }
  },
  OPENH264 {

    @Override
    public ArrayList<String> getMainCodecCmd() {
      ArrayList<String> cmd = new ArrayList<>();
      cmd.add(StaticFFmpegFields.encodevideocodec);
      cmd.add(getFFmpegID());
      return cmd;
    }

    @Override
    public String toString() {
      return "OpenH264";
    }

    @Override
    public String getFFmpegID() {
      return StaticFFmpegFields.OPENH264;
    }

    @Override
    public boolean needsDecodingFilter() {
      return false;
    }

    @Override
    public boolean supportsDecodingHardwareAccel() {
      return false;
    }

    @Override
    public VideoContainers[] supportsOutputFormats() {
      VideoContainers[] res = {VideoContainers.MKV, VideoContainers.MP4, VideoContainers.TS,
          VideoContainers.FLV, VideoContainers.E3GP};
      return res;
    }

    @Override
    public boolean needsSupportVerification() {
      return false;
    }

    @Override
    public boolean supportsVariableBitrate() {
      return false;
    }

    @Override
    public boolean isExperimental() {
      return false;
    }
  },

  /** The H264. */
  H264NVENC {

    @Override
    public ArrayList<String> getMainCodecCmd() {
      ArrayList<String> cmd = new ArrayList<>();
      cmd.add(StaticFFmpegFields.encodevideocodec);
      cmd.add(StaticFFmpegFields.H264nvenc);
      return cmd;
    }

    @Override
    public String toString() {
      return "H264 NVENC";
    }

    @Override
    public String getFFmpegID() {
      return StaticFFmpegFields.H264nvenc;
    }

    @Override
    public boolean needsDecodingFilter() {
      return true;
    }

    @Override
    public boolean supportsDecodingHardwareAccel() {
      return true;
    }

    @Override
    public ArrayList<String> getCmdDecodingFilter(HWAccel hwaccel) {

    	if (hwaccel!=null && hwaccel.equals(HWAccel.CUDA))
    		return (ArrayList<String>) Stream
    	          .of(StaticFFmpegFields.FILTERFFMPEGCMD, StaticFFmpegFields.CUDADEFAULTDECODEFILTER)
    	          .collect(Collectors.toList());
    	else
    		return new ArrayList<String>();
    }

    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<String> getDecodingHWAcceleration() {
      return (ArrayList<String>) Stream
          .of(StaticFFmpegFields.DECODERHWACCEL, StaticFFmpegFields.CUVID,
              StaticFFmpegFields.encodevideocodec, StaticFFmpegFields.CUDA)
          .collect(Collectors.toList());
    }

    @Override
    public String getDecodingHWACCType() {
      return StaticFFmpegFields.CUVID;
    }

    @Override
    public VideoContainers[] supportsOutputFormats() {
      VideoContainers[] res = {VideoContainers.MKV, VideoContainers.MP4};
      return res;
    }

    @Override
    public boolean needsSupportVerification() {
      return true;
    }

    @Override
    public boolean supportsVariableBitrate() {
      return true;
    }

    @Override
    public boolean isExperimental() {
      return false;
    }
  },

  H264VAAPI {

    @Override
    public ArrayList<String> getMainCodecCmd() {
      return (ArrayList<String>) Stream
          .of(StaticFFmpegFields.encodevideocodec, StaticFFmpegFields.H264vaapi)
          .collect(Collectors.toList());

    }

    @Override
    public String toString() {
      return "H264 VAAPI";
    }

    @Override
    public String getFFmpegID() {
      return StaticFFmpegFields.H264vaapi;
    }

    @Override
    public boolean needsDecodingFilter() {
      return true;
    }

    @Override
    public boolean supportsDecodingHardwareAccel() {
      return true;
    }

    @Override
    public ArrayList<String> getCmdDecodingFilter(HWAccel hwaccel) {
      return (ArrayList<String>) Stream
          .of(StaticFFmpegFields.FILTERFFMPEGCMD, StaticFFmpegFields.VAAPIDEFAULTDECODEFILTER)
          .collect(Collectors.toList());
    }

    @Override
    public ArrayList<String> getDecodingHWAcceleration() {
      return (ArrayList<String>) Stream
          .of(StaticFFmpegFields.DECODERHWACCEL, StaticFFmpegFields.VAAPI,
              StaticFFmpegFields.DECODERHWACCELOUTFORMAT, StaticFFmpegFields.VAAPI,
              StaticFFmpegFields.VAAPIDEVICEFLAG, StaticFFmpegFields.VAAPIDEVICELINUX
              )
          .collect(Collectors.toList());
    }

    @Override
    public String getDecodingHWACCType() {
      return StaticFFmpegFields.VAAPI;
    }

    @Override
    public VideoContainers[] supportsOutputFormats() {
      return VideoCodecs.H264NVENC.supportsOutputFormats();
    }

    @Override
    public boolean needsSupportVerification() {
      return true;
    }

    @Override
    public boolean supportsVariableBitrate() {
      return true;
    }

    @Override
    public boolean isExperimental() {
      return false;
    }
  },

  H264QSV {

    @Override
    public ArrayList<String> getMainCodecCmd() {
      return (ArrayList<String>) Stream
          .of(StaticFFmpegFields.encodevideocodec, StaticFFmpegFields.H264qsv)
          .collect(Collectors.toList());

    }

    @Override
    public String toString() {
      return "H264 QSV";
    }

    @Override
    public String getFFmpegID() {
      return StaticFFmpegFields.H264qsv;
    }

    @Override
    public boolean needsDecodingFilter() {
      return false;
    }

    @Override
    public boolean supportsDecodingHardwareAccel() {
      return true;
    }

    @Override
    public ArrayList<String> getCmdDecodingFilter(HWAccel hwaccel) {
      return null;
      // return (ArrayList<String>) Stream
      // .of(StaticFFmpegFields.FILTERFFMPEGCMD, StaticFFmpegFields.VAAPIDEFAULTDECODEFILTER)
      // .collect(Collectors.toList());
    }

    @Override
    public ArrayList<String> getDecodingHWAcceleration() {
      return (ArrayList<String>) Stream.of(StaticFFmpegFields.DECODERHWACCEL, "qsv")
          .collect(Collectors.toList());
    }

    @Override
    public String getDecodingHWACCType() {
      return StaticFFmpegFields.QSV;
    }

    @Override
    public VideoContainers[] supportsOutputFormats() {
      return VideoCodecs.H264NVENC.supportsOutputFormats();
    }

    @Override
    public boolean needsSupportVerification() {
      return true;
    }

    @Override
    public boolean supportsVariableBitrate() {
      return true;
    }

    @Override
    public boolean isExperimental() {
      return false;
    }
  },

  /** The H265. */
  H265 {

    @Override
    public ArrayList<String> getMainCodecCmd() {
      ArrayList<String> cmd = new ArrayList<>();
      cmd.add(StaticFFmpegFields.encodevideocodec);
      cmd.add(StaticFFmpegFields.x265);
      return cmd;
    }

    @Override
    public String toString() {
      return "HEVC";
    }

    @Override
    public String getFFmpegID() {
      return StaticFFmpegFields.x265;
    }

    @Override
    public boolean needsDecodingFilter() {
      return false;
    }

    @Override
    public boolean supportsDecodingHardwareAccel() {
      return false;
    }

    @Override
    public ArrayList<String> getDecodingHWAcceleration() {
      return null;
    }

    @Override
    public VideoContainers[] supportsOutputFormats() {
      return VideoCodecs.H264.supportsOutputFormats();
    }

    @Override
    public boolean needsSupportVerification() {
      return false;
    }

    @Override
    public boolean supportsVariableBitrate() {
      return true;
    }

    @Override
    public boolean isExperimental() {
      return false;
    }

  },
  HEVCNVENC {

    @Override
    public ArrayList<String> getMainCodecCmd() {
      ArrayList<String> cmd = new ArrayList<>();
      cmd.add(StaticFFmpegFields.encodevideocodec);
      cmd.add(StaticFFmpegFields.Hevcnvenc);
      return cmd;
    }

    @Override
    public String toString() {
      return "HEVC NVENC";
    }

    @Override
    public String getFFmpegID() {
      return StaticFFmpegFields.Hevcnvenc;
    }

    @Override
    public boolean needsDecodingFilter() {
      return false;
    }

    @Override
    public boolean supportsDecodingHardwareAccel() {
      return false;
    }

    @Override
    public ArrayList<String> getDecodingHWAcceleration() {
      return (ArrayList<String>) Stream.of(StaticFFmpegFields.DECODERHWACCEL, "nvdec")
          .collect(Collectors.toList());
    }

    @Override
    public VideoContainers[] supportsOutputFormats() {
      return VideoCodecs.H264NVENC.supportsOutputFormats();
    }

    @Override
    public boolean needsSupportVerification() {
      return true;
    }

    @Override
    public boolean supportsVariableBitrate() {
      return true;
    }

    @Override
    public boolean isExperimental() {
      return false;
    }
  },
  HEVCVAAPI {

    @Override
    public ArrayList<String> getMainCodecCmd() {
      return (ArrayList<String>) Stream
          .of(StaticFFmpegFields.encodevideocodec, StaticFFmpegFields.HEVCvaapi)
          .collect(Collectors.toList());

    }

    @Override
    public String toString() {
      return "HEVC VAAPI";
    }

    @Override
    public String getFFmpegID() {
      return StaticFFmpegFields.HEVCvaapi;
    }

    @Override
    public boolean needsDecodingFilter() {
      return true;
    }

    @Override
    public boolean supportsDecodingHardwareAccel() {
      return true;
    }

    @Override
    public ArrayList<String> getCmdDecodingFilter(HWAccel hwaccel) {
      return (ArrayList<String>) Stream
          .of(StaticFFmpegFields.FILTERFFMPEGCMD, StaticFFmpegFields.VAAPIDEFAULTDECODEFILTER)
          .collect(Collectors.toList());
    }

    @Override
    public ArrayList<String> getDecodingHWAcceleration() {
      return (ArrayList<String>) Stream.of(StaticFFmpegFields.VAAPIDEVICEFLAG,
          OSystem.isLinux() ? StaticFFmpegFields.VAAPIDEVICELINUX : StaticFFmpegFields.VAAPIDEVICE)
          .collect(Collectors.toList());
    }

    @Override
    public String getDecodingHWACCType() {
      return StaticFFmpegFields.VAAPI;
    }

    @Override
    public VideoContainers[] supportsOutputFormats() {
      return VideoCodecs.H264NVENC.supportsOutputFormats();
    }

    @Override
    public boolean needsSupportVerification() {
      return true;
    }

    @Override
    public boolean supportsVariableBitrate() {
      return true;
    }

    @Override
    public boolean isExperimental() {
      return false;
    }
  },
  HEVCQSV {

    @Override
    public ArrayList<String> getMainCodecCmd() {
      return (ArrayList<String>) Stream
          .of(StaticFFmpegFields.encodevideocodec, StaticFFmpegFields.HEVCqsv)
          .collect(Collectors.toList());

    }

    @Override
    public String toString() {
      return "HEVC QSV";
    }

    @Override
    public String getFFmpegID() {
      return StaticFFmpegFields.HEVCqsv;
    }

    @Override
    public boolean needsDecodingFilter() {
      return true;
    }

    @Override
    public boolean supportsDecodingHardwareAccel() {
      return true;
    }

    @Override
    public ArrayList<String> getCmdDecodingFilter(HWAccel hwaccel) {
      // return null;
      return (ArrayList<String>) Stream.of("-load_plugin", "hevc_hw").collect(Collectors.toList());
    }

    @Override
    public ArrayList<String> getDecodingHWAcceleration() {
      return (ArrayList<String>) Stream.of(StaticFFmpegFields.DECODERHWACCEL, "qsv")
          .collect(Collectors.toList());
    }

    @Override
    public String getDecodingHWACCType() {
      return StaticFFmpegFields.QSV;
    }

    @Override
    public VideoContainers[] supportsOutputFormats() {
      return VideoCodecs.H264NVENC.supportsOutputFormats();
    }

    @Override
    public boolean needsSupportVerification() {
      return true;
    }

    @Override
    public boolean supportsVariableBitrate() {
      return true;
    }

    @Override
    public boolean isExperimental() {
      return false;
    }
  },
  
  SVTHEVC{

    @Override
    public ArrayList<String> getMainCodecCmd() {
      ArrayList<String> cmd = new ArrayList<>();
      cmd.add(StaticFFmpegFields.encodevideocodec);
      cmd.add(getFFmpegID());
      return cmd;
    }

    @Override
    public String toString() {
      return "SVT-HEVC";
    }

    @Override
    public String getFFmpegID() {
      return StaticFFmpegFields.SVTHEVC;
    }

    @Override
    public boolean needsDecodingFilter() {
      return false;
    }

    @Override
    public boolean supportsDecodingHardwareAccel() {
      return false;
    }

    @Override
    public VideoContainers[] supportsOutputFormats() {
      VideoContainers[] res = {VideoContainers.MKV, VideoContainers.MP4};
      return res;
    }

    @Override
    public boolean needsSupportVerification() {
      return false;
    }

    @Override
    public boolean supportsVariableBitrate() {
      return true;
    }

    @Override
    public boolean isExperimental() {
      return false;
    }
  },
  
  KVAZAAR {

    @Override
    public ArrayList<String> getMainCodecCmd() {
      ArrayList<String> cmd = new ArrayList<>();
      cmd.add(StaticFFmpegFields.encodevideocodec);
      cmd.add(StaticFFmpegFields.Kvazaar);
      return cmd;
    }

    @Override
    public String toString() {
      return "Kvazaar H265";
    }

    @Override
    public String getFFmpegID() {
      return StaticFFmpegFields.Kvazaar;
    }

    @Override
    public boolean needsDecodingFilter() {
      return false;
    }

    @Override
    public boolean supportsDecodingHardwareAccel() {
      return false;
    }

    @Override
    public ArrayList<String> getDecodingHWAcceleration() {
      return null;
    }

    @Override
    public VideoContainers[] supportsOutputFormats() {
      return VideoCodecs.H264NVENC.supportsOutputFormats();
    }

    @Override
    public boolean needsSupportVerification() {
      return false;
    }

    @Override
    public boolean supportsVariableBitrate() {
      return true;
    }

    @Override
    public boolean isExperimental() {
      return false;
    }

  },
  VP8 {

    @Override
    public ArrayList<String> getMainCodecCmd() {
      ArrayList<String> cmd = new ArrayList<>();
      cmd.add(StaticFFmpegFields.encodevideocodec);
      cmd.add(StaticFFmpegFields.Vp8);
      return cmd;
    }

    @Override
    public String toString() {
      return "VP8";
    }

    @Override
    public String getFFmpegID() {
      return StaticFFmpegFields.Vp8;
    }

    @Override
    public boolean needsDecodingFilter() {
      return false;
    }

    @Override
    public boolean supportsDecodingHardwareAccel() {
      return false;
    }

    @Override
    public VideoContainers[] supportsOutputFormats() {
      VideoContainers[] res = {VideoContainers.WEBM, VideoContainers.MKV};
      return res;
    }

    @Override
    public boolean needsSupportVerification() {
      return false;
    }

    @Override
    public boolean supportsVariableBitrate() {
      return true;
    }

    @Override
    public boolean isExperimental() {
      return false;
    }

  },
  VP8VAAPI {

    @Override
    public ArrayList<String> getMainCodecCmd() {
      return (ArrayList<String>) Stream.of(StaticFFmpegFields.encodevideocodec, "vp8_vaapi")
          .collect(Collectors.toList());

    }

    @Override
    public String toString() {
      return "VP8 VAAPI";
    }

    @Override
    public String getFFmpegID() {
      return "vp8_vaapi";
    }

    @Override
    public boolean needsDecodingFilter() {
      return true;
    }

    @Override
    public boolean supportsDecodingHardwareAccel() {
      return true;
    }

    @Override
    public ArrayList<String> getCmdDecodingFilter(HWAccel hwaccel) {
      return (ArrayList<String>) Stream
          .of(StaticFFmpegFields.FILTERFFMPEGCMD, StaticFFmpegFields.VAAPIDEFAULTDECODEFILTER)
          .collect(Collectors.toList());
    }

    @Override
    public ArrayList<String> getDecodingHWAcceleration() {
      return (ArrayList<String>) Stream
          .of(StaticFFmpegFields.VAAPIDEVICEFLAG, StaticFFmpegFields.VAAPIDEVICELINUX)
          .collect(Collectors.toList());
    }

    @Override
    public String getDecodingHWACCType() {
      return StaticFFmpegFields.VAAPI;
    }

    @Override
    public VideoContainers[] supportsOutputFormats() {
      return VideoCodecs.VP8.supportsOutputFormats();
    }

    @Override
    public boolean needsSupportVerification() {
      return true;
    }

    @Override
    public boolean supportsVariableBitrate() {
      return true;
    }

    @Override
    public boolean isExperimental() {
      return false;
    }
  },

  VP9 {

    @Override
    public ArrayList<String> getMainCodecCmd() {
      ArrayList<String> cmd = new ArrayList<>();
      cmd.add(StaticFFmpegFields.encodevideocodec);
      cmd.add(StaticFFmpegFields.Vp9);
      return cmd;
    }

    @Override
    public String toString() {
      return "VP9";
    }

    @Override
    public String getFFmpegID() {
      return StaticFFmpegFields.Vp9;
    }

    @Override
    public boolean needsDecodingFilter() {
      return false;
    }

    @Override
    public boolean supportsDecodingHardwareAccel() {
      return false;
    }

    @Override
    public VideoContainers[] supportsOutputFormats() {
      return VideoCodecs.VP8.supportsOutputFormats();
    }

    @Override
    public boolean needsSupportVerification() {
      return false;
    }

    @Override
    public boolean supportsVariableBitrate() {
      return true;
    }

    @Override
    public boolean isExperimental() {
      return false;
    }

  },
  VP9VAAPI {

    @Override
    public ArrayList<String> getMainCodecCmd() {
      return (ArrayList<String>) Stream.of(StaticFFmpegFields.encodevideocodec, "vp9_vaapi")
          .collect(Collectors.toList());

    }

    @Override
    public String toString() {
      return "VP9 VAAPI";
    }

    @Override
    public String getFFmpegID() {
      return "vp9_vaapi";
    }

    @Override
    public boolean needsDecodingFilter() {
      return true;
    }

    @Override
    public boolean supportsDecodingHardwareAccel() {
      return true;
    }

    @Override
    public ArrayList<String> getCmdDecodingFilter(HWAccel hwaccel) {
      return (ArrayList<String>) Stream
          .of(StaticFFmpegFields.FILTERFFMPEGCMD, StaticFFmpegFields.VAAPIDEFAULTDECODEFILTER)
          .collect(Collectors.toList());
    }

    @Override
    public ArrayList<String> getDecodingHWAcceleration() {
      return (ArrayList<String>) Stream
          .of(StaticFFmpegFields.VAAPIDEVICEFLAG, StaticFFmpegFields.VAAPIDEVICELINUX)
          .collect(Collectors.toList());
    }

    @Override
    public String getDecodingHWACCType() {
      return StaticFFmpegFields.VAAPI;
    }

    @Override
    public VideoContainers[] supportsOutputFormats() {
      return VideoCodecs.VP8.supportsOutputFormats();
    }

    @Override
    public boolean needsSupportVerification() {
      return true;
    }

    @Override
    public boolean supportsVariableBitrate() {
      return true;
    }

    @Override
    public boolean isExperimental() {
      return false;
    }
  },

  AOMAV1 {

    @Override
    public ArrayList<String> getMainCodecCmd() {
      ArrayList<String> cmd = new ArrayList<>();
      cmd.add(StaticFFmpegFields.encodevideocodec);
      cmd.add(getFFmpegID());
      return cmd;
    }

    @Override
    public String toString() {
      return "AOM-AV1";
    }

    @Override
    public String getFFmpegID() {
      return StaticFFmpegFields.AOMAV1;
    }

    @Override
    public boolean needsDecodingFilter() {
      return false;
    }

    @Override
    public boolean supportsDecodingHardwareAccel() {
      return false;
    }

    @Override
    public VideoContainers[] supportsOutputFormats() {
      VideoContainers[] res = {VideoContainers.MKV, VideoContainers.MP4};
      return res;
    }

    @Override
    public boolean needsSupportVerification() {
      return false;
    }

    @Override
    public boolean supportsVariableBitrate() {
      return true;
    }

    @Override
    public boolean isExperimental() {
      return true;
    }
  },
  
  SVTAV1 {

    @Override
    public ArrayList<String> getMainCodecCmd() {
      ArrayList<String> cmd = new ArrayList<>();
      cmd.add(StaticFFmpegFields.encodevideocodec);
      cmd.add(getFFmpegID());
      return cmd;
    }

    @Override
    public String toString() {
      return "SVT-AV1";
    }

    @Override
    public String getFFmpegID() {
      return StaticFFmpegFields.SVTAV1;
    }

    @Override
    public boolean needsDecodingFilter() {
      return false;
    }

    @Override
    public boolean supportsDecodingHardwareAccel() {
      return false;
    }

    @Override
    public VideoContainers[] supportsOutputFormats() {
      VideoContainers[] res = {VideoContainers.MKV, VideoContainers.MP4};
      return res;
    }

    @Override
    public boolean needsSupportVerification() {
      return false;
    }

    @Override
    public boolean supportsVariableBitrate() {
      return true;
    }

    @Override
    public boolean isExperimental() {
      return false;
    }
  },

  THEORA {

    @Override
    public ArrayList<String> getMainCodecCmd() {
      ArrayList<String> cmd = new ArrayList<>();
      cmd.add(StaticFFmpegFields.encodevideocodec);
      cmd.add(StaticFFmpegFields.Theora);
      return cmd;
    }

    @Override
    public String toString() {
      return "Theora";
    }

    @Override
    public String getFFmpegID() {
      return StaticFFmpegFields.Theora;
    }

    @Override
    public boolean needsDecodingFilter() {
      return false;
    }

    @Override
    public boolean supportsDecodingHardwareAccel() {
      return false;
    }

    @Override
    public VideoContainers[] supportsOutputFormats() {
      VideoContainers[] res = {VideoContainers.OGG};
      return res;
    }

    @Override
    public boolean needsSupportVerification() {
      return false;
    }

    @Override
    public boolean supportsVariableBitrate() {
      return true;
    }

    @Override
    public boolean isExperimental() {
      return false;
    }

  },

  /** The MPEG2. */
  MPEG2 {

    @Override
    public ArrayList<String> getMainCodecCmd() {
      ArrayList<String> cmd = new ArrayList<>();
      cmd.add(StaticFFmpegFields.encodevideocodec);
      cmd.add(StaticFFmpegFields.mpeg2);
      return cmd;
    }

    @Override
    public String toString() {
      return "MPEG2";
    }

    @Override
    public String getFFmpegID() {
      return StaticFFmpegFields.mpeg2;
    }

    @Override
    public boolean needsDecodingFilter() {
      return false;
    }

    @Override
    public boolean supportsDecodingHardwareAccel() {
      return false;
    }

    @Override
    public VideoContainers[] supportsOutputFormats() {
      VideoContainers[] res = {VideoContainers.MPEG, VideoContainers.VOB, VideoContainers.TS};
      return res;
    }

    @Override
    public boolean needsSupportVerification() {
      return false;
    }

    @Override
    public boolean supportsVariableBitrate() {
      return true;
    }

    @Override
    public boolean isExperimental() {
      return false;
    }

  },

  /** The copy. */
  COPY {

    @Override
    public ArrayList<String> getMainCodecCmd() {
      ArrayList<String> cmd = new ArrayList<>();
      cmd.add(StaticFFmpegFields.encodevideocodec);
      cmd.add(StaticFFmpegFields.copy);
      return cmd;
    }

    @Override
    public String toString() {
      return "Copy";
    }

    @Override
    public String getFFmpegID() {
      return StaticFFmpegFields.copy;
    }

    @Override
    public boolean needsDecodingFilter() {
      return false;
    }

    @Override
    public boolean supportsDecodingHardwareAccel() {
      return false;
    }

    @Override
    public VideoContainers[] supportsOutputFormats() {
      VideoContainers[] res = {VideoContainers.SAMEASSOURCE};
      return res;
    }

    @Override
    public boolean needsSupportVerification() {
      return false;
    }

    @Override
    public boolean supportsVariableBitrate() {
      return false;
    }

    @Override
    public boolean isExperimental() {
      return false;
    }
  };

  public boolean needsDecodingFilter() {
    return needsDecodingFilter();
  }

  public ArrayList<String> getCmdDecodingFilter(HWAccel hwaccel) {
    return getCmdDecodingFilter(hwaccel);
  }
  
//  public ArrayList<String> getCmdDecodingFilter() {
//	    return getCmdDecodingFilter();
//  }

  public boolean supportsDecodingHardwareAccel() {
    return supportsDecodingHardwareAccel();
  }

  public ArrayList<String> getDecodingHWAcceleration() {
    return getDecodingHWAcceleration();
  }

  /**
   * Gets the ffmpeg command.
   *
   * @return the mainencodcommand
   */
  public ArrayList<String> getMainCodecCmd() {
    return this.getMainCodecCmd();
  }

  public String getFFmpegID() {
    return getFFmpegID();
  }

  public VideoContainers[] supportsOutputFormats() {
    return supportsOutputFormats();
  }

  public boolean needsSupportVerification() {
    return needsSupportVerification();
  }

  public boolean supportsVariableBitrate() {
    return supportsVariableBitrate();
  }

  public boolean isExperimental() {
    return isExperimental();
  }

  public String getDecodingHWACCType() {
    return getDecodingHWACCType();
  }

  public static void main(String[] args) {
    System.out.println(VideoCodecs.H264VAAPI.getCmdDecodingFilter(null));
  }

}
