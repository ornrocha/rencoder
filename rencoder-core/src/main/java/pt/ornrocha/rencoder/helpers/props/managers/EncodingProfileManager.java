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
package pt.ornrocha.rencoder.helpers.props.managers;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FilenameUtils;
import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.configurations.FFmpegManager;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCodecs;
import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.helpers.props.fields.StaticFFmpegFields;
import pt.ornrocha.rencoder.helpers.props.fields.StaticVideoEncoderFields;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoAV1EncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoCopyEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoH264EncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoH264NvencEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoH264QSVEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoH264VAAPIEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoH265EncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoHEVCNvencEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoHEVCQsvEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoHEVCVaapiEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoKvazaarEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoMPEG2EncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoMPEG4EncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoOpenH264EncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoTheoraEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoVP8EncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoVP9EncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoXvidEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.ProfileVideoEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.setfiles.foldersandfiles.ListFiles;

// TODO: Auto-generated Javadoc
/**
 * The Class EncodingProfileManager.
 */
public class EncodingProfileManager {

	private static EncodingProfileManager manager = null;

	/** The profiles. */
	IndexedHashMap<String, ProfileVideoEncodingInfoContainer> profiles = null;

	/**
	 * Instantiates a new encoding profile manager.
	 */
	private EncodingProfileManager() {
		this.profiles = getListOfProfiles();
	}

	public static EncodingProfileManager getInstance() {
		if (manager == null) {
			manager = new EncodingProfileManager();
		}

		return manager;
	}

	public void reloadProfiles() {
		profiles = getListOfProfiles();
	}

	/**
	 * Gets the profiles.
	 *
	 * @return the profiles
	 */
	public IndexedHashMap<String, ProfileVideoEncodingInfoContainer> getProfiles() {
		return profiles;
	}

	/**
	 * Gets the list of profiles.
	 *
	 * @return the list of profiles
	 */

	private IndexedHashMap<String, ProfileVideoEncodingInfoContainer> getListOfProfiles() {

		String folderprofiles = StaticVideoEncoderFields.PROFILESDIRPATH;

		IndexedHashMap<String, ProfileVideoEncodingInfoContainer> profilemap = null;

		String folder = new File(folderprofiles).getAbsolutePath();
		ArrayList<String> files = ListFiles.getFilesInDirectory(folder, true);
		for (String file : files) {
			String ext = FilenameUtils.getExtension(file);
			if (ext.toLowerCase().equals("profile")) {
				PropertiesConfiguration prop = PropertiesWorker.loadPropertiesRelativePath(file);
				if (prop.containsKey(StaticVideoEncoderFields.PROFILENAME))
					if (!prop.getProperty(StaticVideoEncoderFields.PROFILENAME).toString().isEmpty()) {
						if (profilemap == null)
							profilemap = new IndexedHashMap<>();
						String profilename = (String) prop.getProperty(StaticVideoEncoderFields.PROFILENAME);
						if (CheckifVideoCodecisValid(prop))
							if (!profilemap.containsKey(profilename)) {
								IGeneralVideoEncInfoContainer info = extractVideoEncInformation(prop, false);
								if (info != null) {
									ProfileVideoEncodingInfoContainer profile = new ProfileVideoEncodingInfoContainer(
											profilename, info);
									profilemap.put(profilename, profile);
								}
							}

					}
			}
		}
		return profilemap;
	}

	/**
	 * Gets the name profiles.
	 *
	 * @return the name profiles
	 */
	public String[] getnameProfiles() {

		if (profiles != null) {
			String[] names = new String[profiles.size()];
			for (int i = 0; i < profiles.size(); i++) {
				names[i] = profiles.getKeyAt(i);
			}
			return names;
		} else
			return null;
	}

	/**
	 * Gets the array list of profile names.
	 *
	 * @return the array list of profile names
	 */
	public ArrayList<String> getArrayListOfProfileNames() {
		ArrayList<String> arrayprofilename = null;
		if (profiles != null) {
			arrayprofilename = new ArrayList<>(profiles.size());

			for (int i = 0; i < profiles.size(); i++) {
				arrayprofilename.add(profiles.getKeyAt(i));
			}
		}

		return arrayprofilename;

	}

	/**
	 * Gets the profiles array.
	 *
	 * @return the profiles array
	 */
	public ProfileVideoEncodingInfoContainer[] getProfilesArray() {
		ProfileVideoEncodingInfoContainer[] arrayprofile = null;
		if (profiles != null) {
			arrayprofile = new ProfileVideoEncodingInfoContainer[profiles.size()];

			for (int i = 0; i < profiles.size(); i++) {
				arrayprofile[i] = profiles.getValueAt(i);
			}
		}

		return arrayprofile;
	}

	/**
	 * Gets the array list of profiles.
	 *
	 * @return the array list of profiles
	 */
	public ArrayList<ProfileVideoEncodingInfoContainer> getArrayListOfProfiles() {
		ArrayList<ProfileVideoEncodingInfoContainer> arrayprofile = null;
		if (profiles != null) {
			arrayprofile = new ArrayList<>(profiles.size());

			for (int i = 0; i < profiles.size(); i++) {
				arrayprofile.add(profiles.getValueAt(i));
			}
		}

		return arrayprofile;
	}

	/**
	 * Gets the encoding info container.
	 *
	 * @param index the index
	 * @return the encoding info container
	 */
	public IGeneralVideoEncInfoContainer getEncodingInfoContainer(int index) {
		return this.profiles.getValueAt(index).getEncinfoContainer();
	}

	/**
	 * Gets the encoding info container by name.
	 *
	 * @param name the name
	 * @return the encoding info container by name
	 */
	public IGeneralVideoEncInfoContainer getEncodingInfoContainerbyname(String name) {
		for (Map.Entry<String, ProfileVideoEncodingInfoContainer> map : this.profiles.entrySet()) {
			if (map.getKey().equals(name))
				return map.getValue().getEncinfoContainer();
		}
		return null;
	}

	public static IGeneralVideoEncInfoContainer extractVideoEncInformation(PropertiesConfiguration props,
			boolean forcedefaultcontainer) {

		IGeneralVideoEncInfoContainer InfoContainer = null;
		boolean defaultinfocontainer = false;

		String vcodec = PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOCODEC);

		if (vcodec != null) {
			InfoContainer = initVideoEncodingContainer(vcodec);
			if (InfoContainer == null && forcedefaultcontainer) {
				InfoContainer = new VideoH264EncodingInfoContainer();
				defaultinfocontainer = true;
			}
		} else {
			InfoContainer = initVideoEncodingContainer(StaticFFmpegFields.H264);
			defaultinfocontainer = true;
		}

		if (!defaultinfocontainer && InfoContainer != null) {

			InfoContainer.setConfigurations(props);
		}

		Logger.debug("Loaded profile using " + vcodec + " container");

		return InfoContainer;

	}

	private static IGeneralVideoEncInfoContainer initVideoEncodingContainer(String videocodec) {

		IGeneralVideoEncInfoContainer InfoContainer = null;
		switch (videocodec.toLowerCase()) {

		case StaticFFmpegFields.H264:
			InfoContainer = new VideoH264EncodingInfoContainer();
			break;
		case StaticFFmpegFields.OPENH264:
			InfoContainer = new VideoOpenH264EncodingInfoContainer();
			break;	
		case StaticFFmpegFields.H264nvenc:
			InfoContainer = new VideoH264NvencEncodingInfoContainer();
			break;
		case StaticFFmpegFields.H264vaapi:
			InfoContainer = new VideoH264VAAPIEncodingInfoContainer();
			break;
		case StaticFFmpegFields.H264qsv:
			InfoContainer = new VideoH264QSVEncodingInfoContainer();
			break;	
		case StaticFFmpegFields.x265:
			InfoContainer = new VideoH265EncodingInfoContainer();
			break;
		case StaticFFmpegFields.Hevcnvenc:
			InfoContainer = new VideoHEVCNvencEncodingInfoContainer();
			break;
		case StaticFFmpegFields.HEVCvaapi:
			InfoContainer = new VideoHEVCVaapiEncodingInfoContainer();
			break;
		case StaticFFmpegFields.HEVCqsv:
			InfoContainer = new VideoHEVCQsvEncodingInfoContainer();
			break;
		case StaticFFmpegFields.Kvazaar:
			InfoContainer = new VideoKvazaarEncodingInfoContainer();
			break;
		case StaticFFmpegFields.Vp8:
			InfoContainer = new VideoVP8EncodingInfoContainer();
			break;
		case StaticFFmpegFields.Vp9:
			InfoContainer = new VideoVP9EncodingInfoContainer();
			break;
		case StaticFFmpegFields.AV1:
			InfoContainer = new VideoAV1EncodingInfoContainer();
			break;
		case StaticFFmpegFields.Theora:
			InfoContainer = new VideoTheoraEncodingInfoContainer();
			break;
		case StaticFFmpegFields.mpeg2:
			InfoContainer = new VideoMPEG2EncodingInfoContainer();
			break;
		case StaticFFmpegFields.copy:
			InfoContainer = new VideoCopyEncodingInfoContainer();
			break;

		case StaticFFmpegFields.Mpeg4:
			InfoContainer = new VideoMPEG4EncodingInfoContainer();
			break;

		default:
			InfoContainer = new VideoXvidEncodingInfoContainer();
			break;
		}
		return InfoContainer;
	}

	public static boolean CheckifVideoCodecisValid(PropertiesConfiguration prop) {

		if (prop.containsKey(StaticVideoEncoderFields.VIDEOCODEC))
			if (!prop.getProperty(StaticVideoEncoderFields.VIDEOCODEC).toString().isEmpty()) {
				String vcodec = (String) prop.getProperty(StaticVideoEncoderFields.VIDEOCODEC);

				for (VideoCodecs codec : VideoCodecs.values()) {
					boolean allowed = false;
					if (vcodec.toLowerCase().equals(codec.toString().toLowerCase()))
						allowed = true;
					else if (vcodec.toLowerCase().equals(codec.getFFmpegID().toLowerCase()))
						allowed = true;
					if (allowed && FFmpegManager.getInstance().isCodecSupported(codec.getFFmpegID(),
							codec.needsSupportVerification()))
						return true;
				}
			}

		return false;
	}

}
