/*
 * Copyright 2014
 *
 * This is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. 
 * 
 * This code is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the 
 * GNU Public License for more details. 
 * 
 * You should have received a copy of the GNU Public License 
 * along with this code. If not, see http://www.gnu.org/licenses/ 
 * 
 * Created by Orlando Rocha
 */
package pt.ornrocha.rencoder.ffmpegWrapper.enumerators.subtitles;

import java.awt.Font;

// TODO: Auto-generated Javadoc
/**
 * The Enum SubtitlesFontStyle.
 */
public enum SubtitlesFontStyle {

	/** The plain. */
	PLAIN {

		public int getPreviewID() {
			return Font.PLAIN;
		}

		public String toString() {
			return "Plain";
		}

	},

	/** The bold. */
	BOLD {

		public int getPreviewID() {
			return Font.BOLD;
		}

		public String toString() {
			return "Bold";
		}

	},

	/** The italic. */
	ITALIC {

		public int getPreviewID() {
			return Font.ITALIC;
		}

		public String toString() {
			return "Italic";
		}

	},

	/** The Bold italic. */
	BoldItalic {

		public int getPreviewID() {
			return Font.BOLD | Font.ITALIC;
		}

		public String toString() {
			return "Bold and Italic";
		}

	};

	/**
	 * Gets the preview id.
	 *
	 * @return the preview id
	 */
	public int getPreviewID() {
		return this.getPreviewID();
	}

}
