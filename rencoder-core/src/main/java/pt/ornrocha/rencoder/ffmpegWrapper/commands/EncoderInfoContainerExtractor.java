package pt.ornrocha.rencoder.ffmpegWrapper.commands;

import java.util.ArrayList;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioSampleRates;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.AspectRatio;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.FPS;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.H265Tune;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.PixelFormat;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.ProfilesH264;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VP8QualityProfiles;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoContainers;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.X26xPresets;
import pt.ornrocha.rencoder.ffmpegWrapper.othercomponents.AspectComponent;
import pt.ornrocha.rencoder.helpers.props.fields.StaticFFmpegFields;
import pt.ornrocha.rencoder.helpers.props.managers.auxiliar.EncodingPropsAuxiliar;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoCopyEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoH264EncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoH265EncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoMPEG2EncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoMPEG4EncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoVP8EncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoVP9EncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoXvidEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;

public class EncoderInfoContainerExtractor {

  public static ArrayList<String> getVideoInfoCmds(IGeneralVideoEncInfoContainer encodinfo,
      boolean isusedSoftSubtitles, boolean isusedTwoPassEncoding,
      boolean firstpassconfigurationstep) {

    ArrayList<String> videocmds = new ArrayList<>();

    if (!(encodinfo instanceof VideoCopyEncodingInfoContainer)) {

      if (encodinfo instanceof VideoXvidEncodingInfoContainer
          || encodinfo instanceof VideoMPEG4EncodingInfoContainer) {
        videocmds.addAll(encodinfo.getVideocodec().getMainCodecCmd());
      }

      else if (encodinfo instanceof VideoH264EncodingInfoContainer) {
        if (isusedSoftSubtitles) {
          if (encodinfo.getVideoContainer().equals(VideoContainers.MKV)
              || encodinfo.getVideoContainer().equals(VideoContainers.MP4)) {
            videocmds.add(StaticFFmpegFields.MAPFILE);
            videocmds.add("0:v");
          }

        }

        videocmds.addAll(encodinfo.getVideocodec().getMainCodecCmd());
        if (!checkifhaveVPRE(encodinfo))
          videocmds.addAll(processX264EncoderParameters((VideoH264EncodingInfoContainer) encodinfo,
              isusedTwoPassEncoding, firstpassconfigurationstep));

      } else if (encodinfo instanceof VideoH265EncodingInfoContainer) {
        if (isusedSoftSubtitles) {
          if (encodinfo.getVideoContainer().equals(VideoContainers.MKV)
              || encodinfo.getVideoContainer().equals(VideoContainers.MP4)) {
            videocmds.add(StaticFFmpegFields.MAPFILE);
            videocmds.add("0:v");
          }

        }
        videocmds.addAll(encodinfo.getVideocodec().getMainCodecCmd());
        videocmds.addAll(processX265EncoderParameters((VideoH265EncodingInfoContainer) encodinfo));
      }

      else if ((encodinfo instanceof VideoVP8EncodingInfoContainer)
          || (encodinfo instanceof VideoVP9EncodingInfoContainer)) {
        videocmds.addAll(encodinfo.getVideocodec().getMainCodecCmd());
        videocmds.addAll(processVPxEncoderParameters((VideoVP8EncodingInfoContainer) encodinfo));
      }

      else if (encodinfo instanceof VideoMPEG2EncodingInfoContainer) {

        videocmds.addAll(encodinfo.getVideocodec().getMainCodecCmd());
        videocmds
            .addAll(processMpeg2EncoderParameters((VideoMPEG2EncodingInfoContainer) encodinfo));

      }

      videocmds.addAll(processVideoBitrateValues(encodinfo));
      ArrayList<String> aspectparam = processVideoAspectRelatedParameters(encodinfo);
      if (aspectparam.size() > 0)
        videocmds.addAll(aspectparam);

    }

    else {
      videocmds.add(StaticFFmpegFields.encodevideocodec);
      videocmds.add(StaticFFmpegFields.copy);
    }

    return videocmds;
  }

  public static ArrayList<String> getAudioInfoCmds(IGeneralVideoEncInfoContainer audioencinfo,
      boolean isusedSoftSubtitles) {

    ArrayList<String> audioencparam = new ArrayList<>();

    if (isusedSoftSubtitles) {
      audioencparam.add(StaticFFmpegFields.MAPFILE);
      audioencparam.add("0:a");
    }

    if (!audioencinfo.isUseonlyextraaudioffmpegcmd()) {
      AudioCodecs audiocodec = audioencinfo.getAudiocodec();

      if (audiocodec.equals(AudioCodecs.COPY)) {
        audioencparam.addAll(audiocodec.getmainencodcommand());
      } else {
        audioencparam.addAll(audiocodec.getmainencodcommand());
        audioencparam.addAll(getAudioBitrate(audioencinfo));

        if (!audioencinfo.getAudiosamplerate().equals(AudioSampleRates.SAMEASSOURCE)) {
          audioencparam.addAll(audioencinfo.getAudiosamplerate().getffmpegcommand());
        }

        audioencparam.addAll(audioencinfo.getAudiochannels().getffmpegcommand());
      }
    }

    if (audioencinfo.getExtraaudioffmpegcmd() != null)
      audioencparam
          .addAll(EncodingPropsAuxiliar.getExtraFFmpegCMDs(audioencinfo.getExtraaudioffmpegcmd()));

    return audioencparam;
  }

  private static ArrayList<String> getAudioBitrate(IGeneralVideoEncInfoContainer audioencoinfo) {
    ArrayList<String> bitrateset = new ArrayList<>();

    if (audioencoinfo.isUseAudioCBR()) {
      bitrateset.add(StaticFFmpegFields.audiocbr);
      bitrateset.add(audioencoinfo.getAudioConstantBitrateValue());
    } else {
      bitrateset.add(StaticFFmpegFields.audiovbr);
      AudioCodecs acodec = audioencoinfo.getAudiocodec();
      if (acodec.equals(AudioCodecs.LCAAC))
        bitrateset.add(String.valueOf(AudioCodecs.getAACVariablebitrateMap()
            .get(audioencoinfo.getAudioVariableBitrateValues())));
      else
        bitrateset.add(String.valueOf(audioencoinfo.getAudioVariableBitrateValues()));
    }
    return bitrateset;
  }

  private static ArrayList<String> processX264EncoderParameters(
      VideoH264EncodingInfoContainer encodinfo, boolean isusedTwoPassEncoding,
      boolean firstpassconfigurationstep) {

    ArrayList<String> x264param = new ArrayList<>();

    if (!encodinfo.getPreset().equals(X26xPresets.none)) {
      x264param.add(StaticFFmpegFields.PRESET);
      x264param.add(encodinfo.getPreset().toString());
    }

    if (!encodinfo.getProfile().equals(ProfilesH264.none)) {
      x264param.add(StaticFFmpegFields.PROFILE);
      x264param.add(encodinfo.getProfile().toString());
    }

    if (!encodinfo.getPixelformat().equals(PixelFormat.auto)) {
      x264param.add(StaticFFmpegFields.pixelformat);
      x264param.add(encodinfo.getPixelformat().toString());
    }

    if (encodinfo.getConstantQuantization() != -1) {
      x264param.add(StaticFFmpegFields.x264constquant);
      x264param.add(String.valueOf(encodinfo.getConstantQuantization()));
    }

    if (isusedTwoPassEncoding && firstpassconfigurationstep) {
      if (encodinfo.isFastfirstpass()) {
        x264param.add(StaticFFmpegFields.x264fastfirstpass);
        x264param.add("1");
      } else {
        x264param.add(StaticFFmpegFields.x264fastfirstpass);
        x264param.add("0");
      }

    }

    return x264param;
  }

  private static ArrayList<String> processX265EncoderParameters(
      VideoH265EncodingInfoContainer encodinfo) {

    ArrayList<String> x265param = new ArrayList<>();
    if (!encodinfo.getPreset().equals(X26xPresets.none)) {
      x265param.add(StaticFFmpegFields.PRESET);
      x265param.add(encodinfo.getPreset().toString());
    }

    x265param.add(StaticFFmpegFields.pixelformat);
    x265param.add(encodinfo.getPixelformat().toString());

    if (!encodinfo.getTune().equals(H265Tune.none)) {
      x265param.add(StaticFFmpegFields.x265tune);
      x265param.add(encodinfo.getTune().toString());
    }

    return x265param;
  }

  /**
   * Process vpx encoder parameters.
   *
   * @param encodinfo the encodinfo
   * @return the array list
   */
  private static ArrayList<String> processVPxEncoderParameters(
      VideoVP8EncodingInfoContainer encodinfo) {

    ArrayList<String> vp8param = new ArrayList<>();

    if (!encodinfo.getQualityprofile().equals(VP8QualityProfiles.none)) {
      vp8param.add(StaticFFmpegFields.vp8profile);
      vp8param.add(encodinfo.getQualityprofile().toString());
    }
    int minq = encodinfo.getMinquant();
    int maxq = encodinfo.getMaxquant();
    if (minq > -1 && maxq > -1 && minq < maxq) {

      vp8param.add(StaticFFmpegFields.vp8minquant);
      vp8param.add(String.valueOf(encodinfo.getMinquant()));
      vp8param.add(StaticFFmpegFields.vp8maxquant);
      vp8param.add(String.valueOf(encodinfo.getMaxquant()));

    }

    vp8param.add(StaticFFmpegFields.vp8cpuused);
    vp8param.add(String.valueOf(encodinfo.getCpuUsed()));

    if (encodinfo.getNumberThreads() < encodinfo.getCputhreads()) {
      vp8param.add(StaticFFmpegFields.cputhreads);
      vp8param.add(String.valueOf(encodinfo.getCputhreads()));
    }

    return vp8param;
  }

  /**
   * Process mpeg2 encoder parameters.
   *
   * @param encodinfo the encodinfo
   * @return the array list
   */
  private static ArrayList<String> processMpeg2EncoderParameters(
      VideoMPEG2EncodingInfoContainer encodinfo) {

    ArrayList<String> mpeg2param = new ArrayList<>();

    if (!encodinfo.getPixelformat().equals(PixelFormat.auto)) {
      mpeg2param.add(StaticFFmpegFields.pixelformat);
      mpeg2param.add(encodinfo.getPixelformat().toString());
    }
    if (encodinfo.getGopsize() > -1) {
      mpeg2param.add(StaticFFmpegFields.mpeg2gopsize);
      mpeg2param.add(String.valueOf(encodinfo.getGopsize()));
    }
    if (encodinfo.isUsebFrames()) {
      mpeg2param.add(StaticFFmpegFields.mpeg2bframes);
      mpeg2param.add(String.valueOf(2));
    }
    if (encodinfo.isUseTrellis()) {
      mpeg2param.add(StaticFFmpegFields.mpeg2trellis);
      mpeg2param.add(String.valueOf(2));
    }
    if (encodinfo.isUsePelMe()) {
      mpeg2param.add(StaticFFmpegFields.mpeg2pelmecmp);
      mpeg2param.add(String.valueOf(2));
      mpeg2param.add(StaticFFmpegFields.mpeg2pelmesubcmp);
      mpeg2param.add(String.valueOf(2));

    }

    return mpeg2param;
  }

  // Diferente
  private static ArrayList<String> processVideoBitrateValues(
      IGeneralVideoEncInfoContainer encoinfo) {

    VideoCodecs codec = encoinfo.getVideocodec();
    ArrayList<String> bitratecmds = new ArrayList<>();

    if (encoinfo.isUseVideoEncodingCBR()) {
      bitratecmds.add(StaticFFmpegFields.video_bitrate);
      bitratecmds.add(encoinfo.getVideoConstBitrate());

    } else if (encoinfo.isUseVideoEncodingVBR()) {

      if (codec.equals(VideoCodecs.XVID) || codec.equals(VideoCodecs.MPEG4)) {
        bitratecmds.add(StaticFFmpegFields.video_quality);
        bitratecmds.add(String.valueOf(encoinfo.getVideoVBRQualityValue()));
      } else if (codec.equals(VideoCodecs.H264)) {
        bitratecmds.add(StaticFFmpegFields.Hvariablebitrate);
        bitratecmds.add(String.valueOf(encoinfo.getVideoVBRQualityValue()));

      } else if (codec.equals(VideoCodecs.H265)) {
        bitratecmds.add(StaticFFmpegFields.x265params);

        String inputcmd =
            StaticFFmpegFields.x265CRF + String.valueOf(encoinfo.getVideoVBRQualityValue());

        bitratecmds.add(inputcmd);

      } else if (codec.equals(VideoCodecs.VP8) || codec.equals(VideoCodecs.VP9)) {
        bitratecmds.add(StaticFFmpegFields.vpxvariablebitrate);
        bitratecmds.add(String.valueOf(encoinfo.getVideoVBRQualityValue()));
      } else if (codec.equals(VideoCodecs.MPEG2)) {
        bitratecmds.add(StaticFFmpegFields.video_qualityshortterm);
        bitratecmds.add(String.valueOf(encoinfo.getVideoVBRQualityValue()));
      }
    }

    return bitratecmds;
  }

  private static ArrayList<String> processVideoAspectRelatedParameters(
      IGeneralVideoEncInfoContainer encodinfo) {
    ArrayList<String> aspectparam = new ArrayList<>();
    boolean videoratioinaspectsizecomponent = false;

    if (!encodinfo.getVideoaspectsizecomponent().getNameAspect()
        .equals(AspectComponent.SAMEASSOURCE)) {
      aspectparam.add(StaticFFmpegFields.video_resolution);
      aspectparam.add(encodinfo.getVideoaspectsizecomponent().getSizemesu());
      if (encodinfo.getVideoaspectsizecomponent().getAspectRatio() != null) {
        aspectparam.add(StaticFFmpegFields.video_aspect);
        aspectparam.add(encodinfo.getVideoaspectsizecomponent().getAspectRatio().toString());
        videoratioinaspectsizecomponent = true;
      }
    }

    if (!videoratioinaspectsizecomponent) {
      if (!encodinfo.getVideoaspectratio().equals(AspectRatio.SAMEASSOURCE)) {
        aspectparam.add(StaticFFmpegFields.video_aspect);
        aspectparam.add(encodinfo.getVideoaspectratio().toString());
      }
    }

    if (!encodinfo.getVideoframerate().equals(FPS.SAMEASSOURCE)) {
      aspectparam.add(StaticFFmpegFields.framerate);
      aspectparam.add(encodinfo.getVideoframerate().toString());
    }

    return aspectparam;
  }

  private static boolean checkifhaveVPRE(IGeneralVideoEncInfoContainer cont) {
    ArrayList<String> param = null;
    boolean have = false;

    if (param != null)
      if (param.contains("-vpre"))
        have = true;

    return have;
  }

}
