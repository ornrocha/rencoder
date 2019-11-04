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
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import pt.ornrocha.rencoder.ffmpegWrapper.configurations.FFmpegManager;
import pt.ornrocha.rencoder.helpers.props.fields.StaticFFmpegFields;

// TODO: Auto-generated Javadoc
/**
 * The Enum AudioCodecs.
 */
public enum AudioCodecs {

	/** The Mp3. */
	MP3 {

		@Override
		public ArrayList<String> getmainencodcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.encodeaudiocodec);
			cmd.add(getFFmpegID());
			return cmd;
		}

		@Override
		public String getFFmpegID() {
			return StaticFFmpegFields.LAMEMP3;
		}

		@Override
		public String toString() {
			return "MP3";
		}

		@Override
		public boolean isCodecSupported() {
			return true;
		}

		@Override
		public boolean useSpecificComands() {
			return false;
		}

		@Override
		public boolean invertScale() {
			return false;
		}

		@Override
		public HashMap<Integer, String> getBitrateTable() {
			return LAMETable();
		}

		@Override
		public boolean supportsVariableBitrate() {
			return true;
		}

	},

	/** The aac. */
	LCAAC {

		@Override
		public ArrayList<String> getmainencodcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.encodeaudiocodec);
			cmd.add(getFFmpegID());
			return cmd;
		}

		@Override
		public String getFFmpegID() {
			return FFmpegManager.getInstance().isCodecSupported(StaticFFmpegFields.FDKAAC, false)
					? StaticFFmpegFields.FDKAAC
					: StaticFFmpegFields.AAC;
		}

		@Override
		public String toString() {
			return "LC-AAC";
		}

		@Override
		public boolean isCodecSupported() {
			return true;
		}

		@Override
		public boolean useSpecificComands() {
			return false;
		}

		@Override
		public boolean invertScale() {
			return false;
		}

		@Override
		public HashMap<Integer, String> getBitrateTable() {
			return AACTable();
		}

		@Override
		public boolean supportsVariableBitrate() {
			return true;
		}
	},

	HEAAC {

		@Override
		public ArrayList<String> getmainencodcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.encodeaudiocodec);
			cmd.add(getFFmpegID());
			return cmd;
		}

		@Override
		public String getFFmpegID() {
			return StaticFFmpegFields.FDKAAC;
		}

		@Override
		public String toString() {
			return "HE-AAC";
		}

		@Override
		public boolean isCodecSupported() {
			return FFmpegManager.getInstance().isCodecSupported(StaticFFmpegFields.FDKAAC, false);
		}

		@Override
		public boolean useSpecificComands() {
			return true;
		}

		@Override
		public ArrayList<String> getSpecificCommands() {
			return (ArrayList<String>) Stream.of("-profile:a", "aac_he").collect(Collectors.toList());
		}

		@Override
		public boolean invertScale() {
			return false;
		}

		@Override
		public HashMap<Integer, String> getBitrateTable() {
			return AACTable();
		}

		@Override
		public boolean supportsVariableBitrate() {
			return true;
		}
	},

	/** The vorbis. */
	VORBIS {

		@Override
		public ArrayList<String> getmainencodcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.encodeaudiocodec);
			cmd.add(getFFmpegID());
			return cmd;
		}

		@Override
		public String getFFmpegID() {
			return StaticFFmpegFields.VORBIS;
		}

		@Override
		public String toString() {
			return "Vorbis";
		}

		@Override
		public boolean isCodecSupported() {
			return FFmpegManager.getInstance().isCodecSupported(StaticFFmpegFields.VORBIS, false);
		}

		@Override
		public boolean useSpecificComands() {
			return false;
		}

		@Override
		public boolean invertScale() {
			return true;
		}

		@Override
		public HashMap<Integer, String> getBitrateTable() {
			return VORBISTable();
		}

		@Override
		public boolean supportsVariableBitrate() {
			return true;
		}

	},

	OPUS {

		@Override
		public ArrayList<String> getmainencodcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.encodeaudiocodec);
			cmd.add(getFFmpegID());
			return cmd;
		}

		@Override
		public String getFFmpegID() {
			return StaticFFmpegFields.OPUS;
		}

		@Override
		public String toString() {
			return "Opus";
		}

		@Override
		public boolean isCodecSupported() {
			return FFmpegManager.getInstance().isCodecSupported(StaticFFmpegFields.OPUS, false);
		}

		@Override
		public boolean useSpecificComands() {
			return false;
		}

		@Override
		public boolean invertScale() {
			return false;
		}

		@Override
		public HashMap<Integer, String> getBitrateTable() {
			return AACTable();
		}

		@Override
		public boolean supportsVariableBitrate() {
			return true;
		}

	},

	/** The Ac3. */
	AC3 {

		@Override
		public ArrayList<String> getmainencodcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.encodeaudiocodec);
			cmd.add(getFFmpegID());
			return cmd;
		}

		@Override
		public String getFFmpegID() {
			return StaticFFmpegFields.AC3;
		}

		@Override
		public String toString() {
			return "AC3";
		}

		@Override
		public boolean isCodecSupported() {
			return true;
		}

		@Override
		public boolean useSpecificComands() {
			return false;
		}

		@Override
		public boolean invertScale() {
			return false;
		}

		@Override
		public HashMap<Integer, String> getBitrateTable() {
			return null;
		}

		@Override
		public boolean supportsVariableBitrate() {
			return false;
		}
	},

	EAC3 {

		@Override
		public ArrayList<String> getmainencodcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.encodeaudiocodec);
			cmd.add(getFFmpegID());
			return cmd;
		}

		@Override
		public String getFFmpegID() {
			return StaticFFmpegFields.EAC3;
		}

		@Override
		public String toString() {
			return "eAC3";
		}

		@Override
		public boolean isCodecSupported() {
			return true;
		}

		@Override
		public boolean useSpecificComands() {
			return false;
		}

		@Override
		public boolean invertScale() {
			return false;
		}

		@Override
		public HashMap<Integer, String> getBitrateTable() {
			return null;
		}

		@Override
		public boolean supportsVariableBitrate() {
			return false;
		}
	},

	/** The Mp2. */
	MP2 {

		@Override
		public ArrayList<String> getmainencodcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.encodeaudiocodec);
			cmd.add(getFFmpegID());
			return cmd;
		}

		@Override
		public String getFFmpegID() {
			return FFmpegManager.getInstance().isCodecSupported(StaticFFmpegFields.LAMEMP2, false)
					? StaticFFmpegFields.LAMEMP2
					: StaticFFmpegFields.MP2;
		}

		@Override
		public String toString() {
			return "MP2";
		}

		@Override
		public boolean isCodecSupported() {
			return true;
		}

		@Override
		public boolean useSpecificComands() {
			return false;
		}

		@Override
		public boolean invertScale() {
			return false;
		}

		@Override
		public HashMap<Integer, String> getBitrateTable() {
			return null;
		}

		@Override
		public boolean supportsVariableBitrate() {
			return false;
		}

	},

	/** The copy. */
	COPY {

		@Override
		public ArrayList<String> getmainencodcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.encodeaudiocodec);
			cmd.add(StaticFFmpegFields.copy);
			return cmd;
		}

		@Override
		public String getFFmpegID() {
			return "copy";
		}

		@Override
		public String toString() {
			return "Copy";
		}

		@Override
		public boolean isCodecSupported() {
			return true;
		}

		@Override
		public boolean useSpecificComands() {
			return false;
		}

		@Override
		public boolean invertScale() {
			return false;
		}

		@Override
		public HashMap<Integer, String> getBitrateTable() {
			return null;
		}

		@Override
		public boolean supportsVariableBitrate() {
			return false;
		}

	};

	/**
	 * Gets the ffmpeg commandline.
	 *
	 * @return the mainencodcommand
	 */
	public ArrayList<String> getmainencodcommand() {
		return this.getmainencodcommand();
	}

	/**
	 * Gets the encode library.
	 *
	 * @return the encode library
	 */
	public String getFFmpegID() {
		return this.getFFmpegID();
	}

	public boolean isCodecSupported() {
		return isCodecSupported();
	}

	public boolean useSpecificComands() {
		return useSpecificComands();
	}

	public ArrayList<String> getSpecificCommands() {
		return getSpecificCommands();
	}

	public HashMap<Integer, String> getBitrateTable() {
		return getBitrateTable();
	}

	public boolean invertScale() {
		return invertScale();
	}

	public boolean supportsVariableBitrate() {
		return supportsVariableBitrate();
	}

	public static AudioCodecs getAudioCodecFromString(String name) {
		if (name != null) {
			for (AudioCodecs acodec : AudioCodecs.values()) {
				if (acodec.toString().equalsIgnoreCase(name))
					return acodec;
			}
		}
		return null;
	}

	private static HashMap<Integer, String> AACTable() {
		HashMap<Integer, String> vbrmap = new HashMap<>();
		vbrmap.put(0, "384");
		vbrmap.put(1, "320");
		vbrmap.put(2, "256");
		vbrmap.put(3, "192");
		vbrmap.put(4, "160");
		vbrmap.put(5, "128");
		vbrmap.put(6, "112");
		vbrmap.put(7, "96");
		vbrmap.put(8, "80");
		vbrmap.put(9, "64");
		vbrmap.put(10, "48");
		return vbrmap;
	}

	private static HashMap<Integer, String> VORBISTable() {
		HashMap<Integer, String> vbrmap = new HashMap<>();
		vbrmap.put(0, "64");
		vbrmap.put(1, "80");
		vbrmap.put(2, "96");
		vbrmap.put(3, "112");
		vbrmap.put(4, "128");
		vbrmap.put(5, "160");
		vbrmap.put(6, "192");
		vbrmap.put(7, "224");
		vbrmap.put(8, "256");
		vbrmap.put(9, "320");
		vbrmap.put(10, "500");
		return vbrmap;
	}

	private static HashMap<Integer, String> LAMETable() {
		HashMap<Integer, String> vbrmap = new HashMap<>();
		vbrmap.put(0, "245");
		vbrmap.put(1, "225");
		vbrmap.put(2, "190");
		vbrmap.put(3, "175");
		vbrmap.put(4, "165");
		vbrmap.put(5, "130");
		vbrmap.put(6, "115");
		vbrmap.put(7, "100");
		vbrmap.put(8, "85");
		vbrmap.put(9, "65");
		return vbrmap;
	}

	public static HashMap<Integer, Double> getAACVariablebitrateMap() {

		HashMap<Integer, Double> vbrmap = new HashMap<>();
		vbrmap.put(0, 10.0);
		vbrmap.put(1, 7.0);
		vbrmap.put(2, 4.3);
		vbrmap.put(3, 1.43);
		vbrmap.put(4, 1.17);
		vbrmap.put(5, 0.86);
		vbrmap.put(6, 0.66);
		vbrmap.put(7, 0.55);
		vbrmap.put(8, 0.43);
		vbrmap.put(9, 0.23);
		vbrmap.put(10, 0.097);
		return vbrmap;
	}
}
