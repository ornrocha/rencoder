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
package pt.ornrocha.rencoder.ffmpegWrapper.othercomponents;

import java.util.ArrayList;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.AspectRatio;

// TODO: Auto-generated Javadoc
/**
 * The Class VideoAspectSizes.
 */
public class VideoAspectSizes {

	/** The video aspects. */
	ArrayList<AspectComponent> videoAspects = new ArrayList<>();

	/**
	 * Instantiates a new video aspect sizes.
	 */
	public VideoAspectSizes() {

		setStaticAspects();
	}

	/**
	 * Sets the static aspects.
	 */
	private void setStaticAspects() {

		this.videoAspects.add(new AspectComponent("Same as source", ""));
		this.videoAspects.add(new AspectComponent("HD720p", "1280x720", AspectRatio.A16_9));
		this.videoAspects.add(new AspectComponent("HD1080p_Anamorphic", "1440x1080", AspectRatio.A16_9));
		this.videoAspects.add(new AspectComponent("HD1080p", "1920x1080", AspectRatio.A16_9));
		this.videoAspects.add(new AspectComponent("SD", "640x480", AspectRatio.A4_3));
		this.videoAspects.add(new AspectComponent("PAL", "720x576", AspectRatio.A4_3));
		this.videoAspects.add(new AspectComponent("PAL_Widescreen", "720x576", AspectRatio.A16_9));
		this.videoAspects.add(new AspectComponent("NTSC", "720x480", AspectRatio.A4_3));
		this.videoAspects.add(new AspectComponent("NTSC_Widescreen", "720x480", AspectRatio.A16_9));
	}

	/**
	 * Add new aspect size.
	 *
	 * @param name            the name
	 * @param size_AxA_format the size_ ax a_format
	 */
	public void addnewAspectSize(String name, String size_AxA_format) {

		this.videoAspects.add(new AspectComponent(name, size_AxA_format));
	}

	/**
	 * Values.
	 *
	 * @return the aspect component[]
	 */
	public AspectComponent[] values() {

		AspectComponent[] val = new AspectComponent[this.videoAspects.size()];

		for (int i = 0; i < val.length; i++) {
			val[i] = videoAspects.get(i);
		}

		return val;
	}

	/**
	 * Gets the size of aspect in position.
	 *
	 * @param i the i
	 * @return the size of aspect in pos
	 */
	public String getSizeOfAspectInPos(int i) {

		return this.videoAspects.get(i).getSizemesu();
	}

	/**
	 * Gets the name of aspect in position (x).
	 *
	 * @param i the i
	 * @return the nameof aspect in pos
	 */
	public String getNameofAspectInPos(int i) {

		return this.videoAspects.get(i).getNameAspect();
	}

	/**
	 * Gets the aspect name and size in position (x).
	 *
	 * @param i the i
	 * @return the aspect nameand size in pos
	 */
	public String getAspectNameandSizeInPos(int i) {

		return this.videoAspects.get(i).getVideoAspect();
	}

	/**
	 * Gets the list of video aspects.
	 *
	 * @return the list of video aspects
	 */
	public ArrayList<String> getListOfVideoAspects() {
		ArrayList<String> list = new ArrayList<>();
		for (AspectComponent comp : this.videoAspects) {
			list.add(comp.getVideoAspect());
		}

		return list;
	}

	/**
	 * Gets the listof video aspect components.
	 *
	 * @return the listof video aspect components
	 */
	public ArrayList<AspectComponent> getListofVideoAspectComponents() {
		return this.videoAspects;
	}

	/**
	 * Gets the index of aspect component.
	 *
	 * @param comp the comp
	 * @return the index of aspect component
	 */
	public int getIndexOfAspectComponent(AspectComponent comp) {

		for (int i = 0; i < videoAspects.size(); i++) {
			if (getNameofAspectInPos(i).equals(comp.getNameAspect()))
				return i;
		}
		return -1;
	}

	/**
	 * Adds the component.
	 *
	 * @param comp the comp
	 */
	public void addComponent(AspectComponent comp) {
		if (videoAspects.size() > 9)
			videoAspects.remove(10);
		videoAspects.add(comp);
	}

}
