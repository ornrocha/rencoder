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
package pt.ornrocha.rencoder.mediafiles.files.containers.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import pt.ornrocha.rencoder.ffmpegWrapper.configurations.FFmpegManager;
import pt.ornrocha.rencoder.helpers.lang.LangTools;

// TODO: Auto-generated Javadoc
/**
 * The Class AudioStreamInfo.
 */
public class AudioStreamInfo extends StreamInfo {

  /** The audiofrequency. */
  protected String audiofrequency;

  /** The audiochanneltype. */
  protected String audiochanneltype;


  protected String language;

  protected boolean defaultstream = false;



  /** The rb. */
  private ResourceBundle rb;

  /**
   * Instantiates a new audio stream info.
   */
  public AudioStreamInfo() {
    super();
  }

  /**
   * Gets the audio frequency.
   *
   * @return the audiofrequency
   */
  public String getAudiofrequency() {
    return audiofrequency;
  }

  /**
   * Sets the audio frequency.
   *
   * @param audiofrequency the new audiofrequency
   */
  public void setAudiofrequency(String audiofrequency) {
    this.audiofrequency = audiofrequency;
  }

  /**
   * Gets the audio channel type.
   *
   * @return the audiochanneltype
   */
  public String getAudiochanneltype() {
    return audiochanneltype;
  }

  /**
   * Sets the audio channel type.
   *
   * @param audiochanneltype the new audiochanneltype
   */
  public void setAudiochanneltype(String audiochanneltype) {
    this.audiochanneltype = audiochanneltype;
  }



  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }



  public boolean isDefaultstream() {
    return defaultstream;
  }

  public void setDefaultstream(boolean defaultstream) {
    this.defaultstream = defaultstream;
  }

  /**
   * Gets the audio info to table.
   *
   * @return the audio info to table
   */
  public List<Object[]> getAudioInfoToTable() {

    // Object[] Stream = { LangTools.getWordLanguage("Stream", "general.Stream"), this.numberstream
    // };
    Object[] lang = {LangTools.getWordLanguage("Language", "general.language"),
        this.language != null ? FFmpegManager.getInstance().getLanguageFromISO3Code(this.language)
            : "Unknown"};
    Object[] codec = {LangTools.getWordLanguage("Codec", "videoaudiogui.codec"), this.codectype};
    Object[] bitrate =
        {LangTools.getWordLanguage("Bit rate (kb/s)", "videoaudiogui.bitrate"), this.bitrate};
    Object[] freq =
        {LangTools.getWordLanguage("Frequency (Hz)", "audiogui.frequency"), this.audiofrequency};
    Object[] chan =
        {LangTools.getWordLanguage("Channels", "general.channels"), this.audiochanneltype};

    ArrayList<Object[]> info = new ArrayList<>();
    info.add(lang);
    info.add(codec);
    info.add(bitrate);
    info.add(freq);
    info.add(chan);

    return info;

  }

}
