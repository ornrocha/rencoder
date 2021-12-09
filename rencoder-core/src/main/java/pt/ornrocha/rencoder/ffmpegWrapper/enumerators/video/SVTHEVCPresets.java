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

public enum SVTHEVCPresets {

  P0{
    @Override
    public String getValue() {
      return "0";
    }
  },
  
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
  },
  
  P5{
    @Override
    public String getValue() {
      return "5";
    }
  },
  
  P6{
    @Override
    public String getValue() {
      return "6";
    }
  },
  
  P7{
    @Override
    public String getValue() {
      return "7";
    }
  },
  
  P8{
    @Override
    public String getValue() {
      return "8";
    }
  },
  
  P9{
    @Override
    public String getValue() {
      return "9";
    }
  };
  
  
  
  public String getValue() {
    return getValue();
  }
  
  

  public static SVTHEVCPresets getAVTPresetsFromString(String preset) {
    if (preset != null) {
      for (SVTHEVCPresets t : SVTHEVCPresets.values()) {
        if (t.toString().toLowerCase().equals(preset.toString().toLowerCase()))
          return t;
      }
    }
    return SVTHEVCPresets.P7;
  }

}
