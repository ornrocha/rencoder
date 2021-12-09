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
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.PixelFormatAOMAV1;
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
 * The Class VideoMPEG4EncodingInfoContainer.
 */
public class VideoAV1EncodingInfoContainer extends DefaultEncodingInfoContainer {

  private PixelFormatAOMAV1 pixformat = PixelFormatAOMAV1.yuv420p;
  private int cpuused = 1; // Quality/Speed ratio modifier (from 0 to 8) (default 1)
  private int laginframes = -1; // Number of frames to look ahead at for alternate reference frame
                                // selection
                                // (from -1 to INT_MAX) (default -1)
  private int staticthresh = 0; // A change threshold on blocks below which they will be skipped by
                                // the encoder
                                // (from 0 to INT_MAX) (default 0)
  private int noisesensitivity = 0; // Noise sensitivity (from 0 to 4) (default 0)
  private int tiles = 0;
  private int tilecolumns = -1; // Log2 of number of tile columns to use (from -1 to 6) (default -1)
  private int tilerows = -1; // Log2 of number of tile rows to use (from -1 to 6) (default -1)

  public VideoAV1EncodingInfoContainer() {
    super();
    setDefaultInfo();
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.MainVideoEncodingInfoContainer#setDefaultInfo( )
   */
  @Override
  public void setDefaultInfo() {
    this.videocodec = VideoCodecs.AOMAV1;
    this.videoContainer = VideoContainers.MKV;
    this.audiocodec = AudioCodecs.LCAAC;
    this.useAudioCBR = true;
    this.audioConstantBitrateValue = "128k";
    this.videoVBRQuality = new IndexedHashMap<String, Integer>() {

      private static final long serialVersionUID = 1L;

      {
        put(VideoCodecs.AOMAV1.toString(), 30);
      }
    };

  }

  public PixelFormatAOMAV1 getPixformat() {
    return pixformat;
  }

  public void setPixformat(PixelFormatAOMAV1 pixformat) {
    this.pixformat = pixformat;
  }

  public int getCpuused() {
    return cpuused;
  }

  public void setCpuused(int cpuused) {
    this.cpuused = cpuused;
  }

  public int getLaginframes() {
    return laginframes;
  }

  public void setLaginframes(int laginframes) {
    this.laginframes = laginframes;
  }

  public int getStaticthresh() {
    return staticthresh;
  }

  public void setStaticthresh(int staticthresh) {
    this.staticthresh = staticthresh;
  }

  public int getNoisesensitivity() {
    return noisesensitivity;
  }

  public void setNoisesensitivity(int noisesensitivity) {
    this.noisesensitivity = noisesensitivity;
  }

  public int getTiles() {
    return tiles;
  }

  public void setTiles(int tiles) {
    this.tiles = tiles;
  }

  public int getTilecolumns() {
    return tilecolumns;
  }

  public void setTilecolumns(int tilecolumns) {
    this.tilecolumns = tilecolumns;
  }

  public int getTilerows() {
    return tilerows;
  }

  public void setTilerows(int tilerows) {
    this.tilerows = tilerows;
  }

  @Override
  public DefaultEncodingInfoContainer clone() {
    VideoAV1EncodingInfoContainer clone = new VideoAV1EncodingInfoContainer();
    clone = (VideoAV1EncodingInfoContainer) this.copyCommoninfoofcontainer(clone);
    clone = (VideoAV1EncodingInfoContainer) this.copySpecificInfocontainer(clone);
    return clone;
  }

  @Override
  public IGeneralVideoEncInfoContainer copySpecificInfocontainer(
      IGeneralVideoEncInfoContainer cont) {
    ((VideoAV1EncodingInfoContainer) cont).setPixformat(pixformat);
    ((VideoAV1EncodingInfoContainer) cont).setCpuused(cpuused);
    ((VideoAV1EncodingInfoContainer) cont).setLaginframes(laginframes);
    ((VideoAV1EncodingInfoContainer) cont).setStaticthresh(staticthresh);
    ((VideoAV1EncodingInfoContainer) cont).setNoisesensitivity(noisesensitivity);
    ((VideoAV1EncodingInfoContainer) cont).setTiles(tiles);
    ((VideoAV1EncodingInfoContainer) cont).setTilecolumns(tilecolumns);
    ((VideoAV1EncodingInfoContainer) cont).setTilerows(tilerows);

    return cont;
  }

  @Override
  protected ArrayList<VideoContainers> getSupportedFormats() {
    return Arrays.stream(VideoCodecs.AOMAV1.supportsOutputFormats())
        .collect(Collectors.toCollection(ArrayList::new));
  }

  @Override
  protected VideoContainers getpreferredFormat() {
    return VideoContainers.MKV;
  }

  @Override
  public void setConfigurations(PropertiesConfiguration props) {
    setVideoConfigurations(props);

    setPixformat(PixelFormatAOMAV1.getPixelFormatFromString(
        PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOPIXELFORMAT)));
    setCpuused(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.AV1CPUSED, 0, 8, 1));
    setLaginframes(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.AV1LAGINFRAMES,
        -1, Integer.MAX_VALUE, -1));
    setStaticthresh(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.AV1STATICTRESH,
        0, Integer.MAX_VALUE, 0));
    setNoisesensitivity(
        PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.AV1NOISESENS, 0, 4, 0));
    setTiles(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.AV1TILES, 0,
        Integer.MAX_VALUE, 0));
    setTilecolumns(
        PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.AV1TILECOLUMNS, -1, 6, -1));
    setTilerows(
        PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.AV1TILEROWS, -1, 6, -1));

    setAudioConfigurations(props);
    setSubtitleConfigurations(props);
  }

  @Override
  protected void saveSpecificCodecProperties(PropertiesConfiguration prop) {

    prop.setProperty(StaticVideoEncoderFields.VIDEOPIXELFORMAT, pixformat.toString());
    prop.setProperty(StaticVideoEncoderFields.AV1CPUSED, String.valueOf(cpuused));
    prop.setProperty(StaticVideoEncoderFields.AV1LAGINFRAMES, String.valueOf(laginframes));
    prop.setProperty(StaticVideoEncoderFields.AV1STATICTRESH, String.valueOf(staticthresh));
    prop.setProperty(StaticVideoEncoderFields.AV1NOISESENS, String.valueOf(noisesensitivity));
    prop.setProperty(StaticVideoEncoderFields.AV1TILES, String.valueOf(tiles));
    prop.setProperty(StaticVideoEncoderFields.AV1TILECOLUMNS, String.valueOf(tilecolumns));
    prop.setProperty(StaticVideoEncoderFields.AV1TILEROWS, String.valueOf(tilerows));
  }

  @Override
  public ArrayList<String> getSpecificVideoEncoderParameters(ArrayList<String> appendcmds) {
    ArrayList<String> av1param = new ArrayList<>();

    if (!getPixformat().equals(PixelFormatAOMAV1.yuv420p)) {
      av1param.add(StaticFFmpegFields.pixelformat);
      av1param.add(getPixformat().toString());
    }

    if (getCpuused() != 1 && getCpuused() >= 0 && getCpuused() <= 8) {
      av1param.add(StaticFFmpegFields.AV1CPUUSED);
      av1param.add(String.valueOf(cpuused));
    }

    if (getLaginframes() > -1) {
      av1param.add(StaticFFmpegFields.AV1LAGINFRAMES);
      av1param.add(String.valueOf(laginframes));
    }
    if (getStaticthresh() > 0) {
      av1param.add(StaticFFmpegFields.AV1STATICTHRESH);
      av1param.add(String.valueOf(staticthresh));
    }
    if (getNoisesensitivity() > 0 & getNoisesensitivity() < 5) {
      av1param.add(StaticFFmpegFields.AV1NOISESENS);
      av1param.add(String.valueOf(noisesensitivity));
    }
    if (getTiles() > 0) {
      av1param.add(StaticFFmpegFields.AV1TILES);
      av1param.add(String.valueOf(tiles) + "x" + String.valueOf(tiles));
    }
    if (getTilecolumns() > -1 && getTilecolumns() < 7) {
      av1param.add(StaticFFmpegFields.AV1TILECOLUMNS);
      av1param.add(String.valueOf(tilecolumns));
    }
    if (getTilerows() > -1 && getTilerows() < 7) {
      av1param.add(StaticFFmpegFields.AV1TILEROWS);
      av1param.add(String.valueOf(tilerows));
    }
    return av1param;
  }

  @Override
  public boolean useSpecificVideoEncodingParameters() {
    return true;
  }

  @Override
  public String getContainerName() {
    // TODO Auto-generated method stub
    return "AV1";
  }

}
