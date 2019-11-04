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

import java.util.ArrayList;

import pt.ornrocha.rencoder.helpers.props.fields.StaticFFmpegFields;

// TODO: Auto-generated Javadoc
/**
 * The Enum VideoSize.
 */
public enum VideoSize {

	/** The sameassource. */
	SAMEASSOURCE("Same as source", "Same as source") {

		public ArrayList<String> getvideosizecommand() {
			return null;
		}

		public String getVideoAspectRatio() {
			return "Same as source";
		}

		public String toString() {
			return getVideoAspectSizeID();
		}

	},

	/** The HD720p. */
	HD720p("HD720p", "1280x720") {

		public ArrayList<String> getvideosizecommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.video_resolution);
			cmd.add("1280x720");
			cmd.add(StaticFFmpegFields.video_aspect);
			cmd.add(StaticFFmpegFields.A16_9);
			return cmd;
		}

		public String getVideoAspectRatio() {
			return AspectRatio.A16_9.toString();
		}

		public String getVideoAspectSize() {
			return "1280x720";
		}

	},

	/** The HD1080p_ anamorphic. */
	HD1080p_Anamorphic("HD1080p_Anamorphic", "1440x1080") {

		public ArrayList<String> getvideosizecommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.video_resolution);
			cmd.add("1440x1080");
			cmd.add(StaticFFmpegFields.video_aspect);
			cmd.add(StaticFFmpegFields.A16_9);
			return cmd;
		}

		public String getVideoAspectRatio() {
			return AspectRatio.A16_9.toString();
		}

		public String getVideoAspectSize() {
			return "1440x1080";
		}

	},

	/** The HD1080p. */
	HD1080p("HD1080p", "1920x1080") {

		public ArrayList<String> getvideosizecommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.video_resolution);
			cmd.add("1920x1080");
			cmd.add(StaticFFmpegFields.video_aspect);
			cmd.add(StaticFFmpegFields.A16_9);
			return cmd;
		}

		public String getVideoAspectRatio() {
			return AspectRatio.A16_9.toString();
		}

		public String getVideoAspectSize() {
			return "1920x1080";
		}

	},

	/** The SD. */
	SD("SD", "640x480") {

		public ArrayList<String> getvideosizecommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.video_resolution);
			cmd.add("640x480");
			cmd.add(StaticFFmpegFields.video_aspect);
			cmd.add(StaticFFmpegFields.A4_3);
			return cmd;
		}

		public String getVideoAspectRatio() {
			return AspectRatio.A4_3.toString();
		}

		public String getVideoAspectSize() {
			return "640x480";
		}

	},

	/** The PAL. */
	PAL("PAL", "720x576") {

		public ArrayList<String> getvideosizecommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.video_resolution);
			cmd.add("720x576");
			cmd.add(StaticFFmpegFields.video_aspect);
			cmd.add(StaticFFmpegFields.A4_3);
			return cmd;
		}

		public String getVideoAspectRatio() {
			return AspectRatio.A4_3.toString();
		}

	},

	/** The PAL_ widescreen. */
	PAL_Widescreen("PAL_Widescreen", "720x576") {

		public ArrayList<String> getvideosizecommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.video_resolution);
			cmd.add("720x576");
			cmd.add(StaticFFmpegFields.video_aspect);
			cmd.add(StaticFFmpegFields.A16_9);
			return cmd;
		}

		public String getVideoAspectRatio() {
			return AspectRatio.A16_9.toString();
		}

	},

	/** The NTSC. */
	NTSC("NTSC", "720x480") {

		public ArrayList<String> getvideosizecommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.video_resolution);
			cmd.add("720x480");
			cmd.add(StaticFFmpegFields.video_aspect);
			cmd.add(StaticFFmpegFields.A4_3);
			return cmd;
		}

		public String getVideoAspectRatio() {
			return AspectRatio.A4_3.toString();
		}

	},

	/** The NTSC_ widescreen. */
	NTSC_Widescreen("NTSC_Widescreen", "720x480") {

		public ArrayList<String> getvideosizecommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.video_resolution);
			cmd.add("720x480");
			cmd.add(StaticFFmpegFields.video_aspect);
			cmd.add(StaticFFmpegFields.A16_9);
			return cmd;
		}

		public String getVideoAspectRatio() {
			return AspectRatio.A16_9.toString();
		}

	},

	/** The custom. */
	CUSTOM("", "") {

		public ArrayList<String> getvideosizecommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.video_resolution);
			cmd.add(getVideoAspectSize());

			return cmd;
		}

		public String getVideoAspectRatio() {
			return "";
		}

	};

	/** The aspectsize. */
	private String aspectsize;

	/** The name id. */
	private String nameID;

	/**
	 * Instantiates a new video size.
	 *
	 * @param name the name
	 * @param size the size
	 */
	VideoSize(String name, String size) {
		aspectsize = size;
		nameID = name;
	}

	/**
	 * Change.
	 *
	 * @param na the na
	 * @param s  the s
	 * @return the video size
	 */
	public VideoSize change(String na, String s) {
		nameID = na;
		aspectsize = s;
		return this;
	}

	/**
	 * Sets the size.
	 *
	 * @param s the new size
	 */
	public void setsize(String s) {
		aspectsize = s;
	}

	/**
	 * Gets the video aspect size.
	 *
	 * @return the video aspect size
	 */
	public String getVideoAspectSize() {
		return aspectsize;
	}

	/**
	 * Gets the video aspect size id.
	 *
	 * @return the video aspect size id
	 */
	public String getVideoAspectSizeID() {
		return nameID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return nameID + " " + "(" + aspectsize + ")";
	}

	/**
	 * Gets the video size ffmpeg command.
	 *
	 * @return the videosizecommand
	 */
	public ArrayList<String> getvideosizecommand() {
		return this.getvideosizecommand();
	}

	/**
	 * Gets the video aspect ratio.
	 *
	 * @return the video aspect ratio
	 */
	public String getVideoAspectRatio() {
		return this.getVideoAspectRatio();
	}

}
