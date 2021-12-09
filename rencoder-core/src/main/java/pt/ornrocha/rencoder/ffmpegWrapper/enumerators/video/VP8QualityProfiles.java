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

// TODO: Auto-generated Javadoc
/**
 * The Enum VP8QualityProfiles.
 */
public enum VP8QualityProfiles {

  /** The none. */
  none,

  /** The best. */
  best,

  /** The good. */
  good,

  /** The realtime. */
  realtime;

  public static VP8QualityProfiles getVP8QualityProfilesFromString(String profile) {
    if (profile != null) {
      for (VP8QualityProfiles t : VP8QualityProfiles.values()) {
        if (t.toString().toLowerCase().equals(profile.toString().toLowerCase()))
          return t;
      }
    }
    return VP8QualityProfiles.none;
  }

}
