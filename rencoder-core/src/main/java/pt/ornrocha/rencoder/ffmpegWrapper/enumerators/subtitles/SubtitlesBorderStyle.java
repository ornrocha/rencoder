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

import pt.ornrocha.rencoder.helpers.lang.LangTools;

// TODO: Auto-generated Javadoc
/**
 * The Enum SubtitlesBorderStyle.
 */
public enum SubtitlesBorderStyle {

	/** The none. */
	NONE {

		// Outline with shadow =1
		public int getBorderStyleID() {
			return 0;
		}

		public String toString() {
			return LangTools.getWordLanguage("Deactivated", "subtitlesinfo.borderstylenone");
		}

	},

	/** The outline with shadow. */
	OUTILINEWITHSHADOW {

		// Outline with shadow =1
		public int getBorderStyleID() {
			return 1;
		}

		public String toString() {
			return LangTools.getWordLanguage("Shadow line", "subtitlesinfo.borderstyleoutline");
		}

	},

	/** The opaquebox. */
	OPAQUEBOX {

		// opaque box =3
		public int getBorderStyleID() {
			return 3;
		}

		public String toString() {
			return LangTools.getWordLanguage("Opaque box", "subtitlesinfo.borderstylebox");
		}

	};

	/**
	 * Gets the border style id.
	 *
	 * @return the border style id
	 */
	public int getBorderStyleID() {
		return this.getBorderStyleID();
	}

}
