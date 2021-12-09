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
 * The Enum SubtiltesAlignment.
 */
public enum SubtiltesAlignment {

  /** The bottom. */
  BOTTOM {

    public String getAlignmentID() {
      return "bottom";
    }

    public String getAlignmentFFmpegID() {
      return "2";
    }

    public String toString() {
      return LangTools.getWordLanguage("Bottom", "subtitlesinfo.bottomalignment");
    }

  },

  /** The top. */
  TOP {

    public String getAlignmentID() {
      return "top";
    }

    public String getAlignmentFFmpegID() {
      return "8";
    }

    public String toString() {
      return LangTools.getWordLanguage("Top", "subtitlesinfo.topalignment");
    }

  };

  /**
   * Gets the alignment id.
   *
   * @return the alignment id
   */
  public String getAlignmentID() {
    return this.getAlignmentID();
  }

  /**
   * Gets the alignment ffmpeg id.
   *
   * @return the alignment f fmpeg id
   */
  public String getAlignmentFFmpegID() {
    return this.getAlignmentFFmpegID();
  }

}
