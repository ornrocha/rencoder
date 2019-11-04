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
 * The Enum ProfilesH264.
 */
public enum ProfilesH264Nvenc {

    /** The baseline. */
    baseline,

    /** The main. */
    main,

    /** The high. */
    high,

    /** The high444. */
    high444p;

    public static ProfilesH264Nvenc getProfilesH264NvencFromString(String profile) {
	if (profile != null) {
	    for (ProfilesH264Nvenc t : ProfilesH264Nvenc.values()) {
		if (t.toString().toLowerCase().equals(profile.toString().toLowerCase()))
		    return t;
	    }
	}
	return ProfilesH264Nvenc.main;
    }

}
