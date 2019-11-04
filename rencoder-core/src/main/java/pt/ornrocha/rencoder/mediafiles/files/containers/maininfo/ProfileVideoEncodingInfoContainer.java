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
package pt.ornrocha.rencoder.mediafiles.files.containers.maininfo;

// TODO: Auto-generated Javadoc
/**
 * The Class ProfileVideoEncodingInfoContainer.
 */
public class ProfileVideoEncodingInfoContainer {

	/** The encoding info container. */
	private IGeneralVideoEncInfoContainer encinfoContainer = null;

	/** The profile name. */
	private String profilename;

	/**
	 * Instantiates a new profile video encoding info container.
	 *
	 * @param profilename   the profilename
	 * @param infocontainer the infocontainer
	 */
	public ProfileVideoEncodingInfoContainer(String profilename, IGeneralVideoEncInfoContainer infocontainer) {

		this.encinfoContainer = infocontainer;
		this.profilename = profilename;

	}

	/**
	 * Gets the encoding info container.
	 *
	 * @return the encinfo container
	 */
	public IGeneralVideoEncInfoContainer getEncinfoContainer() {
		return encinfoContainer;
	}

	/**
	 * Gets the profilename.
	 *
	 * @return the profilename
	 */
	public String getProfilename() {
		return profilename;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return profilename;
	}

}
