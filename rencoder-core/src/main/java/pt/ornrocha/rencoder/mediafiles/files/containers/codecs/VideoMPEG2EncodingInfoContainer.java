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
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.PixelFormat;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoContainers;
import pt.ornrocha.rencoder.helpers.props.fields.StaticFFmpegFields;
import pt.ornrocha.rencoder.helpers.props.fields.StaticVideoEncoderFields;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.DefaultEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;

// TODO: Auto-generated Javadoc
/**
 * The Class VideoMPEG2EncodingInfoContainer.
 */
public class VideoMPEG2EncodingInfoContainer extends DefaultEncodingInfoContainer {

  /** The pixelformat. */
  protected PixelFormat pixelformat = PixelFormat.auto;

  /** The gopsize. */
  protected int gopsize = -1;

  /** The usegopsize. */
  protected boolean usegopsize = false;

  /** The useb frames. */
  protected boolean usebFrames = true;

  /** The use trellis. */
  protected boolean useTrellis = true;

  /** The use pel me. */
  protected boolean usePelMe = false;

  /** The bframes. */
  protected int bframes = 2;

  /** The trellis. */
  protected int trellis = 2;

  /** The pelme. */
  protected int pelme = 2;

  /**
   * Instantiates a new video mpe g2 encoding info container.
   */
  public VideoMPEG2EncodingInfoContainer() {
    super();
    setDefaultInfo();
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.MainVideoEncodingInfoContainer#setDefaultInfo( )
   */
  public void setDefaultInfo() {
    this.videocodec = VideoCodecs.MPEG2;
    this.videoContainer = VideoContainers.MPEG;
    this.audiocodec = AudioCodecs.MP3;
    this.useAudioCBR = true;
    this.audioConstantBitrateValue = "128k";
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IVideoMPEG2EncInfo#getBframes()
   */
  public int getBframes() {
    return bframes;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IVideoMPEG2EncInfo#setBframes(int)
   */
  public void setBframes(int bframes) {
    this.bframes = bframes;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IVideoMPEG2EncInfo#getTrellis()
   */
  public int getTrellis() {
    return trellis;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IVideoMPEG2EncInfo#setTrellis(int)
   */
  public void setTrellis(int trellis) {
    this.trellis = trellis;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IVideoMPEG2EncInfo#getPelme()
   */
  public int getPelme() {
    return pelme;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IVideoMPEG2EncInfo#setPelme(int)
   */
  public void setPelme(int pelme) {
    this.pelme = pelme;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IVideoMPEG2EncInfo#getPixelformat()
   */
  public PixelFormat getPixelformat() {
    return pixelformat;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IVideoMPEG2EncInfo#setPixelformat(
   * ffmpegWrapper.enumerators.PixelFormat)
   */
  public void setPixelformat(PixelFormat pixelformat) {
    this.pixelformat = pixelformat;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IVideoMPEG2EncInfo#getGopsize()
   */
  public int getGopsize() {
    return gopsize;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IVideoMPEG2EncInfo#setGopsize(int)
   */
  public void setGopsize(int gopsize) {
    this.gopsize = gopsize;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IVideoMPEG2EncInfo#isUsegopsize()
   */
  public boolean isUsegopsize() {
    return usegopsize;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IVideoMPEG2EncInfo#setUsegopsize( boolean)
   */
  public void setUsegopsize(boolean usegopsize) {
    this.usegopsize = usegopsize;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IVideoMPEG2EncInfo#isUsebFrames()
   */
  public boolean isUsebFrames() {
    return usebFrames;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IVideoMPEG2EncInfo#setUsebFrames( boolean)
   */
  public void setUsebFrames(boolean usebFrames) {
    this.usebFrames = usebFrames;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IVideoMPEG2EncInfo#isUseTrellis()
   */
  public boolean isUseTrellis() {
    return useTrellis;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IVideoMPEG2EncInfo#setUseTrellis( boolean)
   */
  public void setUseTrellis(boolean useTrellis) {
    this.useTrellis = useTrellis;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IVideoMPEG2EncInfo#isUsePelMe()
   */
  public boolean isUsePelMe() {
    return usePelMe;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IVideoMPEG2EncInfo#setUsePelMe( boolean)
   */
  public void setUsePelMe(boolean usePelMe) {
    this.usePelMe = usePelMe;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.MainVideoEncodingInfoContainer#clone()
   */
  @Override
  public DefaultEncodingInfoContainer clone() {
    VideoMPEG2EncodingInfoContainer clone = new VideoMPEG2EncodingInfoContainer();
    clone = (VideoMPEG2EncodingInfoContainer) this.copyCommoninfoofcontainer(clone);
    clone = (VideoMPEG2EncodingInfoContainer) this.copySpecificInfocontainer(clone);

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
    // TODO Auto-generated method stub
    ((VideoMPEG2EncodingInfoContainer) newcont).setBframes(this.bframes);
    ((VideoMPEG2EncodingInfoContainer) newcont).setTrellis(this.trellis);
    ((VideoMPEG2EncodingInfoContainer) newcont).setPelme(this.pelme);
    ((VideoMPEG2EncodingInfoContainer) newcont).setPixelformat(this.pixelformat);
    ((VideoMPEG2EncodingInfoContainer) newcont).setGopsize(this.gopsize);
    ((VideoMPEG2EncodingInfoContainer) newcont).setUsegopsize(this.usegopsize);
    ((VideoMPEG2EncodingInfoContainer) newcont).setUsebFrames(this.usebFrames);
    ((VideoMPEG2EncodingInfoContainer) newcont).setUseTrellis(this.useTrellis);
    ((VideoMPEG2EncodingInfoContainer) newcont).setUsePelMe(this.usePelMe);

    return newcont;
  }

  @Override
  protected ArrayList<VideoContainers> getSupportedFormats() {
    return Arrays.stream(VideoCodecs.MPEG2.supportsOutputFormats())
        .collect(Collectors.toCollection(ArrayList::new));
  }

  @Override
  protected VideoContainers getpreferredFormat() {
    return VideoContainers.MPEG;
  }

  @Override
  public void setConfigurations(PropertiesConfiguration props) {

    setPixelformat(PixelFormat.getPixelFormatFromString(
        PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOPIXELFORMAT)));

    Integer gp =
        (Integer) PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.MPG2GOPSIZE, null);
    if (gp != null) {
      if (gp != -1) {
        setUsegopsize(true);
        setGopsize(gp);
      } else
        setUsegopsize(false);
    }

    setUsebFrames(
        PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.MPEG2BFRAME, true));
    setUseTrellis(
        PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.MPEG2TRELLIS, true));
    setUsePelMe(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.MPEG2PELME, false));

    setVideoConfigurations(props);
    setAudioConfigurations(props);
    setSubtitleConfigurations(props);
  }

  @Override
  protected void saveSpecificCodecProperties(PropertiesConfiguration prop) {

    prop.setProperty(StaticVideoEncoderFields.MPG2GOPSIZE, String.valueOf(getGopsize()));
    prop.setProperty(StaticVideoEncoderFields.VIDEOPIXELFORMAT, getPixelformat().toString());

    if (isUsebFrames())
      prop.setProperty(StaticVideoEncoderFields.MPEG2BFRAME, "true");
    else
      prop.setProperty(StaticVideoEncoderFields.MPEG2BFRAME, "false");

    if (isUseTrellis())
      prop.setProperty(StaticVideoEncoderFields.MPEG2TRELLIS, "true");
    else
      prop.setProperty(StaticVideoEncoderFields.MPEG2TRELLIS, "false");

    if (isUsePelMe())
      prop.setProperty(StaticVideoEncoderFields.MPEG2PELME, "true");
    else
      prop.setProperty(StaticVideoEncoderFields.MPEG2PELME, "false");

  }

  @Override
  public ArrayList<String> getSpecificVideoEncoderParameters(ArrayList<String> appendcmds) {
    ArrayList<String> mpeg2param = new ArrayList<>();

    if (!getPixelformat().equals(PixelFormat.auto)) {
      mpeg2param.add(StaticFFmpegFields.pixelformat);
      mpeg2param.add(getPixelformat().toString());
    }
    if (getGopsize() > -1) {
      mpeg2param.add(StaticFFmpegFields.mpeg2gopsize);
      mpeg2param.add(String.valueOf(getGopsize()));
    }
    if (isUsebFrames()) {
      mpeg2param.add(StaticFFmpegFields.mpeg2bframes);
      mpeg2param.add(String.valueOf(2));
    }
    if (isUseTrellis()) {
      mpeg2param.add(StaticFFmpegFields.mpeg2trellis);
      mpeg2param.add(String.valueOf(2));
    }
    if (isUsePelMe()) {
      mpeg2param.add(StaticFFmpegFields.mpeg2pelmecmp);
      mpeg2param.add(String.valueOf(2));
      mpeg2param.add(StaticFFmpegFields.mpeg2pelmesubcmp);
      mpeg2param.add(String.valueOf(2));

    }

    return mpeg2param;
  }

  @Override
  public boolean useSpecificVideoEncodingParameters() {
    return true;
  }

  @Override
  public String getContainerName() {
    // TODO Auto-generated method stub
    return "MPEG2";
  }

}
