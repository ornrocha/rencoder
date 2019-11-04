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
package pt.ornrocha.rencoder.mediafiles.files.containers.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import pt.ornrocha.rencoder.helpers.lang.LangTools;

// TODO: Auto-generated Javadoc
/**
 * The Class VideoStreamInfo.
 */
public class VideoStreamInfo extends StreamInfo {

	/** The video fps. */
	protected String videofps;

	/** The video aspect size. */
	protected String videoaspectsize;

	/** The video total duration. */
	protected String videototalduration;

	/** The rb. */
	private ResourceBundle rb;

	/**
	 * Instantiates a new video stream info.
	 */
	public VideoStreamInfo() {
		super();

	}

	/**
	 * Gets the video fps.
	 *
	 * @return the videofps
	 */
	public String getVideofps() {
		return videofps;
	}

	/**
	 * Sets the video fps.
	 *
	 * @param videofps the new videofps
	 */
	public void setVideofps(String videofps) {
		this.videofps = videofps;
	}

	/**
	 * Gets the video aspect size.
	 *
	 * @return the videoaspectsize
	 */
	public String getVideoaspectsize() {
		return videoaspectsize;
	}

	public String getVideoWidth() {
		String[] aspect = videoaspectsize.split("x");
		return aspect[0];
	}

	public String getVideoHeight() {
		String[] aspect = videoaspectsize.split("x");
		return aspect[1];
	}

	/**
	 * Sets the video aspect size.
	 *
	 * @param videoaspectsize the new videoaspectsize
	 */
	public void setVideoaspectsize(String videoaspectsize) {
		this.videoaspectsize = videoaspectsize;
	}

	/**
	 * Gets the video total duration.
	 *
	 * @return the videototalduration
	 */
	public String getVideototalduration() {
		return videototalduration;
	}

	/**
	 * Sets the video total duration.
	 *
	 * @param videototalduration the new videototalduration
	 */
	public void setVideototalduration(String videototalduration) {
		this.videototalduration = videototalduration;
	}

	/**
	 * Gets the video info to table.
	 *
	 * @return the video info to table
	 */
	public List<Object[]> getVideoInfoToTable() {

		Object[] Stream = { LangTools.getWordLanguage("Stream", "general.Stream"), this.numberstream };
		Object[] codec = { LangTools.getWordLanguage("Codec", "videoaudiogui.codec"), this.codectype };
		Object[] bitrate = { LangTools.getWordLanguage("Bit rate (kb/s)", "videoaudiogui.bitrate"), this.bitrate };
		Object[] duration = { LangTools.getWordLanguage("Duration", "videoaudiogui.duration"),
				this.videototalduration };
		Object[] aspect = { LangTools.getResourceBundleWordLanguage(rb, "Video aspect", "videogui.aspect"),
				this.videoaspectsize };
		Object[] fps = { LangTools.getWordLanguage("Framerate", "general.framerate"), this.videofps };

		ArrayList<Object[]> info = new ArrayList<>();
		info.add(Stream);
		info.add(codec);
		info.add(bitrate);
		info.add(duration);
		info.add(aspect);
		info.add(fps);

		return info;

	}

}
