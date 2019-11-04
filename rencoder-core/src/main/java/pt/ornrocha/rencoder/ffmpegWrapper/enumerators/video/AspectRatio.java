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
package pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video;

// TODO: Auto-generated Javadoc
/**
 * The Enum AspectRatio.
 */
public enum AspectRatio {

	/** The sameassource. */
	SAMEASSOURCE {

		public String getVideoRatioID() {
			return "Same as source";
		}

		public String toString() {
			return "Same as source";
		}

	},

	/** The A4_3. */
	A4_3 {

		public String getVideoRatioID() {
			return "4:3";
		}

		public String toString() {
			return "4:3";
		}

	},

	/** The A16_9. */
	A16_9 {

		public String getVideoRatioID() {
			return "16:9";
		}

		public String toString() {
			return "16:9";
		}

	};

	/**
	 * Gets the video ratio id.
	 *
	 * @return the video ratio id
	 */
	public String getVideoRatioID() {
		return this.getVideoRatioID();
	}

}
