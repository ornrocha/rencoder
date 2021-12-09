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
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.PixelFormatSVTAV1;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.SVTAV1Hielevel;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.SVTTier;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.SVTAV1BitRateMode;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.SVTAV1Presets;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoContainers;
import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.helpers.props.fields.StaticFFmpegFields;
import pt.ornrocha.rencoder.helpers.props.fields.StaticVideoEncoderFields;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.DefaultEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;


/*
Encoder libsvtav1 [SVT-AV1(Scalable Video Technology for AV1) encoder]:
  General capabilities: delay threads 
  Threading capabilities: other
  Supported pixel formats: yuv420p yuv420p10le
libsvtav1 AVOptions:
-hielevel          <int>        E..V....... Hierarchical prediction levels setting (from 3 to 4) (default 4level)
   3level          3            E..V.......
   4level          4            E..V.......
-la_depth          <int>        E..V....... Look ahead distance [0, 120] (from -1 to 120) (default -1)
-preset            <int>        E..V....... Encoding preset [0, 8] (from 0 to 8) (default 8)
-tier              <int>        E..V....... Set operating point tier (from 0 to 1) (default main)
   main            0            E..V.......
   high            1            E..V.......
-rc                <int>        E..V....... Bit rate control mode (from 0 to 3) (default cqp)
   cqp             0            E..V....... Constant quantizer
   vbr             1            E..V....... Variable Bit Rate, use a target bitrate for the entire stream
   cvbr            2            E..V....... Constrained Variable Bit Rate, use a target bitrate for each GOP
-qp                <int>        E..V....... Quantizer to use with cqp rate control mode (from 0 to 63) (default 50)
-sc_detection      <boolean>    E..V....... Scene change detection (default false)
-tile_columns      <int>        E..V....... Log2 of number of tile columns to use (from 0 to 4) (default 0)
-tile_rows         <int>        E..V....... Log2 of number of tile rows to use (from 0 to 6) (default 0)
*/
/**
 * The Class VideoMPEG4EncodingInfoContainer.
 */
public class VideoSVTAV1EncodingInfoContainer extends DefaultEncodingInfoContainer {

  private PixelFormatSVTAV1 pixformat = PixelFormatSVTAV1.yuv420p;
  private SVTAV1Hielevel hielevel= SVTAV1Hielevel.h4level;
  private int la_depth=-1;
  private SVTAV1Presets preset=SVTAV1Presets.highestspeed;
  private SVTTier tier=SVTTier.main;
  private SVTAV1BitRateMode ratemode= SVTAV1BitRateMode.cqp;
  private int quantizer=50;
  private boolean sc_detection=false;
  private int tile_columns=0;
  private int tile_rows=0;


  public VideoSVTAV1EncodingInfoContainer() {
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
    this.videocodec = VideoCodecs.SVTAV1;
    this.videoContainer = VideoContainers.MP4;
    this.audiocodec = AudioCodecs.LCAAC;
    this.useAudioCBR = true;
    this.audioConstantBitrateValue = "128k";
    this.videoVBRQuality = new IndexedHashMap<String, Integer>() {

      private static final long serialVersionUID = 1L;

      {
        put(VideoCodecs.SVTAV1.toString(), 30);
      }
    };

  }

  

  public PixelFormatSVTAV1 getPixformat() {
    return pixformat;
  }

  public void setPixformat(PixelFormatSVTAV1 pixformat) {
    this.pixformat = pixformat;
  }

  public SVTAV1Hielevel getHielevel() {
    return hielevel;
  }

  public void setHielevel(SVTAV1Hielevel hielevel) {
    this.hielevel = hielevel;
  }

  public int getLa_depth() {
    return la_depth;
  }

  public void setLa_depth(int la_depth) {
    this.la_depth = la_depth;
  }

  public SVTAV1Presets getPreset() {
    return preset;
  }

  public void setPreset(SVTAV1Presets preset) {
    this.preset = preset;
  }

  public SVTTier getTier() {
    return tier;
  }

  public void setTier(SVTTier tier) {
    this.tier = tier;
  }
  
  public SVTAV1BitRateMode getRatemode() {
    return ratemode;
  }

  public void setRatemode(SVTAV1BitRateMode ratemode) {
    this.ratemode = ratemode;
  }

  public int getQuantizer() {
    return quantizer;
  }

  public void setQuantizer(int quantizer) {
    this.quantizer = quantizer;
  }

  public boolean isSc_detection() {
    return sc_detection;
  }

  public void setSc_detection(boolean sc_detection) {
    this.sc_detection = sc_detection;
  }

  public int getTile_columns() {
    return tile_columns;
  }

  public void setTile_columns(int tile_columns) {
    this.tile_columns = tile_columns;
  }

  public int getTile_rows() {
    return tile_rows;
  }

  public void setTile_rows(int tile_rows) {
    this.tile_rows = tile_rows;
  }

  @Override
  public int getVideoVBRQualityValue() {
    return getQuantizer();
  }
  
  @Override
  public void setVideoVBRQuality(VideoCodecs codec, Integer videoVBRQuality) {
    this.videoVBRQuality = new IndexedHashMap<String, Integer>();
    this.videoVBRQuality.put(codec.toString(), videoVBRQuality);
    this.quantizer=videoVBRQuality;
  }

  @Override
  public DefaultEncodingInfoContainer clone() {
    VideoSVTAV1EncodingInfoContainer clone = new VideoSVTAV1EncodingInfoContainer();
    clone = (VideoSVTAV1EncodingInfoContainer) this.copyCommoninfoofcontainer(clone);
    clone = (VideoSVTAV1EncodingInfoContainer) this.copySpecificInfocontainer(clone);
    return clone;
  }

  @Override
  public IGeneralVideoEncInfoContainer copySpecificInfocontainer(
      IGeneralVideoEncInfoContainer cont) {
    ((VideoSVTAV1EncodingInfoContainer) cont).setPixformat(pixformat);
    ((VideoSVTAV1EncodingInfoContainer) cont).setHielevel(hielevel);
    ((VideoSVTAV1EncodingInfoContainer) cont).setLa_depth(la_depth);
    ((VideoSVTAV1EncodingInfoContainer) cont).setPreset(preset);
    ((VideoSVTAV1EncodingInfoContainer) cont).setTier(tier);
    ((VideoSVTAV1EncodingInfoContainer) cont).setRatemode(ratemode);
    ((VideoSVTAV1EncodingInfoContainer) cont).setQuantizer(quantizer);
    ((VideoSVTAV1EncodingInfoContainer) cont).setSc_detection(sc_detection);
    ((VideoSVTAV1EncodingInfoContainer) cont).setTile_columns(tile_columns);
    ((VideoSVTAV1EncodingInfoContainer) cont).setTile_columns(tile_rows);

    return cont;
  }

  @Override
  protected ArrayList<VideoContainers> getSupportedFormats() {
    return Arrays.stream(VideoCodecs.SVTAV1.supportsOutputFormats())
        .collect(Collectors.toCollection(ArrayList::new));
  }

  @Override
  protected VideoContainers getpreferredFormat() {
    return VideoContainers.MKV;
  }

  @Override
  public void setConfigurations(PropertiesConfiguration props) {
    

    setPixformat(PixelFormatSVTAV1.getPixelFormatFromString(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOPIXELFORMAT)));
    setPreset(SVTAV1Presets.getAVTPresetsFromString(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOPRESET)));
    setHielevel(SVTAV1Hielevel.getSVTAV1HielevelFromString(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SVTAV1HIELEVEL)));
    setLa_depth(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SVTAV1LADEPTH, -1, 120, -1));
    setTier(SVTTier.getSVTHEVCHielevelFromString(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SVTAV1TIER)));
    setRatemode(SVTAV1BitRateMode.getAVTPresetsFromString(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SVTAV1RC)));
    setQuantizer(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SVTAV1QP, 0, 63, 50));
    setSc_detection(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SVTAV1SCDECT, false));
    setTile_columns(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SVTAV1TILECOLUMN, 0, 4, 0));
    setTile_rows(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SVTAV1TILEROW, 0, 6, 0));

    setVideoConfigurations(props);
    setAudioConfigurations(props);
    setSubtitleConfigurations(props);
  }

  @Override
  protected void saveSpecificCodecProperties(PropertiesConfiguration prop) {

    prop.setProperty(StaticVideoEncoderFields.VIDEOPIXELFORMAT, pixformat.toString());
    prop.setProperty(StaticVideoEncoderFields.VIDEOPRESET, preset.toString());
    prop.setProperty(StaticVideoEncoderFields.SVTAV1HIELEVEL, hielevel.toString());
    prop.setProperty(StaticVideoEncoderFields.SVTAV1LADEPTH, String.valueOf(la_depth));
    prop.setProperty(StaticVideoEncoderFields.SVTAV1TIER, tier.toString());
    prop.setProperty(StaticVideoEncoderFields.SVTAV1RC, ratemode.toString());
    prop.setProperty(StaticVideoEncoderFields.SVTAV1QP, String.valueOf(quantizer));
    prop.setProperty(StaticVideoEncoderFields.SVTAV1SCDECT, String.valueOf(sc_detection));
    prop.setProperty(StaticVideoEncoderFields.SVTAV1TILECOLUMN, String.valueOf(tile_columns));
    prop.setProperty(StaticVideoEncoderFields.SVTAV1TILEROW, String.valueOf(tile_rows));
  }

  @Override
  public ArrayList<String> getSpecificVideoEncoderParameters(ArrayList<String> appendcmds) {
    ArrayList<String> av1param = new ArrayList<>();


     av1param.add(StaticFFmpegFields.pixelformat);
     av1param.add(getPixformat().toString());
     
     if (!getPreset().equals(SVTAV1Presets.highestspeed)) {
       av1param.add(StaticFFmpegFields.PRESET);
       av1param.add(preset.getValue());
     }


    if (!getHielevel().equals(SVTAV1Hielevel.h4level)) {
      av1param.add(StaticFFmpegFields.STVAV1HIELEVEL);
      av1param.add(hielevel.getValue());
    }

    if (getLa_depth() > -1 && getLa_depth()<121) {
      av1param.add(StaticFFmpegFields.STVAV1LADEPTH);
      av1param.add(String.valueOf(la_depth));
    }
    if (!getTier().equals(SVTTier.main)) {
      av1param.add(StaticFFmpegFields.STVAV1TIER);
      av1param.add(tier.getValue());
    }
    if (!getRatemode().equals(SVTAV1BitRateMode.cqp)) {
      av1param.add(StaticFFmpegFields.STVAV1RC);
      av1param.add(ratemode.getValue());
    }
    if (sc_detection) {
      av1param.add(StaticFFmpegFields.STVAV1SCDETECT);
      av1param.add(String.valueOf(sc_detection));
    }
    if (getTile_columns() > 0 && getTile_columns() < 5) {
      av1param.add(StaticFFmpegFields.STVAV1TCOLUMNS);
      av1param.add(String.valueOf(getTile_columns()));
    }
    if (getTile_rows() > 0 && getTile_rows() < 7) {
      av1param.add(StaticFFmpegFields.STVAV1TROWS);
      av1param.add(String.valueOf(getTile_rows()));
    }
    return av1param;
  }

  @Override
  public boolean useSpecificVideoEncodingParameters() {
    return true;
  }

  @Override
  public String getContainerName() {
    return "SVT-AV1";
  }

}
