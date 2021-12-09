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
 * The Enum X264Presets.
 */
public enum ProfileHEVCQsv {

  unknown, main, main10, mainsp;

  public static ProfileHEVCQsv getProfile(String name) {
    if (name != null) {
      for (ProfileHEVCQsv profile : ProfileHEVCQsv.values()) {
        if (name.toLowerCase().equals(profile.toString()))
          return profile;
      }
    }
    return ProfileHEVCQsv.unknown;
  }

}
