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
 * The Enum ProfilesH265.
 */
public enum ProfilesH265 {


  main {
    @Override
    public String toString() {
      return "main";
    }
  },

  mainintra {

    @Override
    public String toString() {
      return "main-intra";
    }
  },

  mainstillpicture {
    @Override
    public String toString() {
      return "mainstillpicture";
    }
  },

  main4448 {
    @Override
    public String toString() {
      return "main444-8";
    }
  },

  main444intra {

    @Override
    public String toString() {
      return "main444-intra";
    }
  },

  main444stillpicture {
    @Override
    public String toString() {
      return "main444-stillpicture";
    }
  };

  public static ProfilesH265 getProfilesH264FromString(String profile) {
    if (profile != null) {
      for (ProfilesH265 t : ProfilesH265.values()) {
        if (t.toString().toLowerCase().equals(profile.toString().toLowerCase()))
          return t;
      }
    }
    return ProfilesH265.main;
  }

}
