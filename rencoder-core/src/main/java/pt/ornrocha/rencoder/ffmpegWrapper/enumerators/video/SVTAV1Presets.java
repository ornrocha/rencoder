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

public enum SVTAV1Presets {

  highestquality{
    @Override
    public String getValue() {
      return "0";
    }
  },
  
  highquality{
    @Override
    public String getValue() {
      return "1";
    }
  },
  
  mediumquality{
    @Override
    public String getValue() {
      return "2";
    }
  },
  
  lowquality{
    @Override
    public String getValue() {
      return "3";
    }
  },
  
  medium{
    @Override
    public String getValue() {
      return "4";
    }
  },
  
  lowspeed{
    @Override
    public String getValue() {
      return "5";
    }
  },
  
  fastspeed{
    @Override
    public String getValue() {
      return "6";
    }
  },
  
  highspeed{
    @Override
    public String getValue() {
      return "7";
    }
  },
  
  highestspeed{
    @Override
    public String getValue() {
      return "8";
    }
  };
  
  
  
  public String getValue() {
    return getValue();
  }
  
  

  public static SVTAV1Presets getAVTPresetsFromString(String preset) {
    if (preset != null) {
      for (SVTAV1Presets t : SVTAV1Presets.values()) {
        if (t.toString().toLowerCase().equals(preset.toString().toLowerCase()))
          return t;
      }
    }
    return SVTAV1Presets.highestspeed;
  }

}
