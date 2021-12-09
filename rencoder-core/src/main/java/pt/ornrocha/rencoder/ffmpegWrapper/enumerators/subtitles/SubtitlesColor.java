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
package pt.ornrocha.rencoder.ffmpegWrapper.enumerators.subtitles;

import pt.ornrocha.rencoder.helpers.lang.LangTools;

// TODO: Auto-generated Javadoc
/**
 * The Enum SubtitlesColor.
 */
public enum SubtitlesColor {

  /** The white. */
  WHITE {

    public String getSubtitleColorCode() {
      // return "HFFFFFF";
      return "&H00FFFFFF";
    }

    public String getColorID() {
      return "white";
    }

    public String toString() {
      return LangTools.getWordLanguage("White", "subtitlesinfo.whitecolor");
    }

  },

  /** The yellow. */
  YELLOW {

    public String getSubtitleColorCode() {
      return "&H2FFAEE";
    }

    public String getColorID() {
      return "yellow";
    }

    public String toString() {
      return LangTools.getWordLanguage("Yellow", "subtitlesinfo.yellowcolor");
    }

  },

  /** The orange. */
  ORANGE {

    public String getSubtitleColorCode() {
      return "&H329EF9";
    }

    public String getColorID() {
      return "orange";
    }

    public String toString() {
      return LangTools.getWordLanguage("Orange", "subtitlesinfo.orangecolor");
    }

  },

  /** The green. */
  GREEN {
    public String getSubtitleColorCode() {
      return "&H32F934";
    }

    public String getColorID() {
      return "green";
    }

    public String toString() {
      return LangTools.getWordLanguage("Green", "subtitlesinfo.greencolor");
    }

  },

  /** The blue. */
  BLUE {
    public String getSubtitleColorCode() {
      return "&HF9C532";
    }

    public String getColorID() {
      return "blue";
    }

    public String toString() {
      return LangTools.getWordLanguage("Blue", "subtitlesinfo.bluecolor");
    }

  },

  /** The red. */
  RED {
    public String getSubtitleColorCode() {
      return "&H323CF9";
    }

    public String getColorID() {
      return "red";
    }

    public String toString() {
      return LangTools.getWordLanguage("Red", "subtitlesinfo.redcolor");
    }

  };

  /**
   * Gets the subtitle color code.
   *
   * @return the subtitle color code
   */
  public String getSubtitleColorCode() {
    return this.getSubtitleColorCode();
  }

  /**
   * Gets the color id.
   *
   * @return the color id
   */
  public String getColorID() {
    return this.getColorID();
  }

}
