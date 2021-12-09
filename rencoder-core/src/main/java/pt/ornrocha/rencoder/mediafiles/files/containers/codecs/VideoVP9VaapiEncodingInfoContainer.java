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

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoContainers;
import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.DefaultEncodingInfoContainer;

// TODO: Auto-generated Javadoc
/**
 * The Class VideoVP8EncodingInfoContainer.
 */
public class VideoVP9VaapiEncodingInfoContainer extends VideoVP8VaapiEncodingInfoContainer {

  public VideoVP9VaapiEncodingInfoContainer() {
    super();
    setDefaultInfo();

  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.MainVideoEncodingInfoContainer#setDefaultInfo( )
   */
  public void setDefaultInfo() {
    this.videocodec = VideoCodecs.VP9VAAPI;
    this.videoContainer = VideoContainers.WEBM;
    this.videoVBRQuality = new IndexedHashMap<String, Integer>() {
      {
        put(VideoCodecs.VP9VAAPI.toString(), 20);
      }
    };;
    this.audiocodec = AudioCodecs.VORBIS;
    this.useAudioCBR = true;
    this.audioConstantBitrateValue = "128k";
  }

  @Override
  public DefaultEncodingInfoContainer clone() {
    VideoVP9VaapiEncodingInfoContainer clone = new VideoVP9VaapiEncodingInfoContainer();
    clone = (VideoVP9VaapiEncodingInfoContainer) this.copyCommoninfoofcontainer(clone);
    clone = (VideoVP9VaapiEncodingInfoContainer) this.copySpecificInfocontainer(clone);

    return clone;
  }

  @Override
  public String getContainerName() {
    // TODO Auto-generated method stub
    return "VP9 VAAPI";
  }


}
