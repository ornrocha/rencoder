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

public enum SVTHEVCProfile {


  P1{
    @Override
    public String getValue() {
      return "1";
    }
  },
  
  P2{
    @Override
    public String getValue() {
      return "2";
    }
  },
  
 P3{
    @Override
    public String getValue() {
      return "3";
    }
  },
  
  P4{
    @Override
    public String getValue() {
      return "4";
    }
  };
  
  
  
  public String getValue() {
    return getValue();
  }
  
  

  public static SVTHEVCProfile getSVTHEVCProfileFromString(String preset) {
    if (preset != null) {
      for (SVTHEVCProfile t : SVTHEVCProfile.values()) {
        if (t.toString().toLowerCase().equals(preset.toString().toLowerCase()))
          return t;
      }
    }
    return SVTHEVCProfile.P1;
  }

}
