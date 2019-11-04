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
package pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio;

import java.util.ArrayList;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.X26xPresets;
import pt.ornrocha.rencoder.helpers.props.fields.StaticFFmpegFields;

// TODO: Auto-generated Javadoc
/**
 * The Enum AudioSampleRates.
 */
public enum AudioSampleRates {

	/** The sameassource. */
	SAMEASSOURCE {

		public int getaudiosamplerate() {
			return -1;
		}

		public ArrayList<String> getffmpegcommand() {
			return null;
		}

		public int getLevelTag() {
			return 4;
		}

		public String toString() {
			return "Same as source";
		}
	},

	/** The Hz48000. */
	HZ48000 {

		public int getaudiosamplerate() {
			return 48000;
		}

		public ArrayList<String> getffmpegcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.audiofrequency);
			cmd.add("48000");
			return cmd;
		}

		public int getLevelTag() {
			return 0;
		}

		public String toString() {
			return "48000";
		}
	},

	/** The Hz44100. */
	HZ44100 {

		public int getaudiosamplerate() {
			return 44100;
		}

		public ArrayList<String> getffmpegcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.audiofrequency);
			cmd.add("44100");
			return cmd;
		}

		public int getLevelTag() {
			return 1;
		}

		public String toString() {
			return "44100";
		}
	},

	/** The Hz22050. */
	HZ22050 {

		public int getaudiosamplerate() {
			return 22050;
		}

		public ArrayList<String> getffmpegcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.audiofrequency);
			cmd.add("22050");
			return cmd;
		}

		public int getLevelTag() {
			return 2;
		}

		public String toString() {
			return "22050";
		}
	},

	/** The Hz11025. */
	HZ11025 {

		public int getaudiosamplerate() {
			return 11025;
		}

		public ArrayList<String> getffmpegcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.audiofrequency);
			cmd.add("11025");
			return cmd;
		}

		public int getLevelTag() {
			return 3;
		}

		public String toString() {
			return "11025";
		}
	};

	/**
	 * Gets the audio samplerate.
	 *
	 * @return the audiosamplerate
	 */
	public int getaudiosamplerate() {
		return this.getaudiosamplerate();
	}

	/**
	 * Gets the level tag.
	 *
	 * @return the level tag
	 */
	public int getLevelTag() {
		return this.getLevelTag();
	}

	/**
	 * Gets the ffmpeg commandline.
	 *
	 * @return the ffmpegcommand
	 */
	public ArrayList<String> getffmpegcommand() {
		return this.getffmpegcommand();
	}

	public static AudioSampleRates getAudioSampleRatesFromString(String rate) {
		if (rate != null) {
			for (AudioSampleRates t : AudioSampleRates.values()) {
				if (t.toString().toLowerCase().equals(rate.toString().toLowerCase()))
					return t;
			}
		}
		return AudioSampleRates.SAMEASSOURCE;
	}
}
