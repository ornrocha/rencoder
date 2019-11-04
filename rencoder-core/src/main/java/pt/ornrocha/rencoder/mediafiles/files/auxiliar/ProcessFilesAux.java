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
package pt.ornrocha.rencoder.mediafiles.files.auxiliar;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.configuration.PropertiesConfiguration;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCodecs;
import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.helpers.props.fields.StaticGlobalFields;
import pt.ornrocha.rencoder.helpers.props.fields.StaticVideoEncoderFields;
import pt.ornrocha.rencoder.helpers.props.managers.auxiliar.EncodingPropsAuxiliar;
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
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoVP8VaapiEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoVP9EncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoVP9VaapiEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoXvidEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;
import pt.ornrocha.rencoder.mediafiles.setfiles.foldersandfiles.FileTypeFilter;

// TODO: Auto-generated Javadoc
/**
 * The Class ProcessFilesAux.
 */
public class ProcessFilesAux {

    /**
     * Gets the allowed video extension.
     *
     * @return the allowed video extension
     */
    public static IndexedHashMap<String, FileTypeFilter> getAllowedVideoExtension() {

	IndexedHashMap<String, FileTypeFilter> filters = new IndexedHashMap<>();

	PropertiesConfiguration propvideo = PropertiesWorker
		.loadPropertiesRelativePath(StaticGlobalFields.RENCODERCONFIGFILE);
	String ext = (String) propvideo.getProperty(StaticGlobalFields.VIDEOEXTENSIONS);

	String[] sp = ext.split(";");

	Pattern r = Pattern.compile("(\\s*)(\\w+)(\\s*):(\\s*)(\\w+(\\s*\\w+)*)(\\s*)");
	for (int i = 0; i < sp.length; i++) {
	    Matcher m = r.matcher(sp[i]);
	    if (m.find()) {
		String extname = m.group(2);
		String descrip = m.group(5);
		FileTypeFilter filt = new FileTypeFilter(extname, descrip);
		filters.put(extname, filt);
	    }

	}

	return filters;

    }

    /**
     * Gets the allowed subtitles extension.
     *
     * @return the allowed subtitles extension
     */
    public static IndexedHashMap<String, FileTypeFilter> getAllowedSubtitlesExtension() {

	IndexedHashMap<String, FileTypeFilter> filters = new IndexedHashMap<>();

	PropertiesConfiguration propvideo = PropertiesWorker
		.loadPropertiesRelativePath(StaticGlobalFields.RENCODERCONFIGFILE);
	String ext = (String) propvideo.getProperty(StaticGlobalFields.SUBTITLESEXTENSIONS);

	String[] sp = ext.split(";");

	Pattern r = Pattern.compile("(\\s*)(\\w+)(\\s*):(\\s*)(\\w+(\\s*\\w+)*)(\\s*)");
	for (int i = 0; i < sp.length; i++) {
	    Matcher m = r.matcher(sp[i]);
	    if (m.find()) {
		String extname = m.group(2);
		String descrip = m.group(5);
		FileTypeFilter filt = new FileTypeFilter(extname, descrip);
		filters.put(extname, filt);
	    }

	}

	return filters;

    }

    /**
     * Gets the allowed extensions.
     *
     * @return the allowed extensions
     */
    public static IndexedHashMap<String, FileTypeFilter> getAllowedExtensions() {

	IndexedHashMap<String, FileTypeFilter> filters = new IndexedHashMap<>();

	PropertiesConfiguration propvideo = PropertiesWorker
		.loadPropertiesRelativePath(StaticGlobalFields.RENCODERCONFIGFILE);
	String extvideo = (String) propvideo.getProperty(StaticGlobalFields.VIDEOEXTENSIONS);
	CheckFilterFiles(extvideo, filters);

	String extsubs = (String) propvideo.getProperty(StaticGlobalFields.SUBTITLESEXTENSIONS);
	CheckFilterFiles(extsubs, filters);

	return filters;
    }

    /**
     * Gets the allowed extensions.
     *
     * @return the allowed extensions
     */
    public static IndexedHashMap<String, FileTypeFilter> getAllowedExtensionsForVideo() {

	IndexedHashMap<String, FileTypeFilter> filters = new IndexedHashMap<>();
	PropertiesConfiguration propvideo = PropertiesWorker
		.loadPropertiesRelativePath(StaticGlobalFields.RENCODERCONFIGFILE);
	String extvideo = (String) propvideo.getProperty(StaticGlobalFields.VIDEOEXTENSIONS);
	String extsubs= (String) propvideo.getProperty(StaticGlobalFields.SUBTITLESEXTENSIONS);
	CheckFilterFiles(extvideo, filters);
	CheckFilterFiles(extsubs, filters);

	return filters;
    }

    /**
     * Gets the only allowed filters.
     *
     * @param allowedfiltersmap the allowedfiltersmap
     * @return the only allowed filters
     */
    public static ArrayList<FileTypeFilter> getOnlyAllowedFilters(
	    IndexedHashMap<String, FileTypeFilter> allowedfiltersmap) {

	ArrayList<FileTypeFilter> filters = new ArrayList<>();
	for (Map.Entry<String, FileTypeFilter> map : allowedfiltersmap.entrySet()) {
	    filters.add(map.getValue());
	}
	return filters;
    }

    /**
     * Check filter files.
     *
     * @param parsestr the parsestr
     * @param map      the map
     * @return the indexed hash map
     */
    private static IndexedHashMap<String, FileTypeFilter> CheckFilterFiles(String parsestr,
	    IndexedHashMap<String, FileTypeFilter> map) {

	String[] sp = parsestr.split(";");

	Pattern r = Pattern.compile("(\\s*)(\\w+)(\\s*):(\\s*)(\\w+(\\s*\\w+)*)(\\s*)");
	for (int i = 0; i < sp.length; i++) {
	    Matcher m = r.matcher(sp[i]);
	    if (m.find()) {
		String extname = m.group(2);
		String descrip = m.group(5);
		FileTypeFilter filt = new FileTypeFilter(extname, descrip);
		map.put(extname, filt);
	    }
	}

	return map;
    }

    /**
     * Convert array to hashset.
     *
     * @param rows the rows
     * @return the hash set
     */
    public static HashSet<Integer> convertArrayToHasSet(int[] rows) {
	HashSet<Integer> res = new HashSet<>(rows.length);

	for (int i = 0; i < rows.length; i++) {
	    res.add(rows[i]);
	}

	return res;
    }

    /**
     * Copy an encoder info container from other info container.
     *
     * @param olddinfoCont the olddinfo cont
     * @param codec        the codec
     * @return the i general video enc info container
     */
    public static IGeneralVideoEncInfoContainer copyEncInfoConFromOtherInfoCont(
	    IGeneralVideoEncInfoContainer olddinfoCont, VideoCodecs codec) {

	IGeneralVideoEncInfoContainer newcont = null;

	if (codec.equals(VideoCodecs.H264)) {
	    newcont = new VideoH264EncodingInfoContainer();
	}
	else if (codec.equals(VideoCodecs.OPENH264))
	    newcont = new VideoOpenH264EncodingInfoContainer();
	else if (codec.equals(VideoCodecs.H264NVENC))
	    newcont = new VideoH264NvencEncodingInfoContainer();
	else if (codec.equals(VideoCodecs.H264VAAPI))
	    newcont = new VideoH264VAAPIEncodingInfoContainer();
	else if (codec.equals(VideoCodecs.H264QSV))
	    newcont = new VideoH264QSVEncodingInfoContainer();
	else if (codec.equals(VideoCodecs.H265))
	    newcont = new VideoH265EncodingInfoContainer();
	else if (codec.equals(VideoCodecs.HEVCNVENC))
	    newcont = new VideoHEVCNvencEncodingInfoContainer();
	else if (codec.equals(VideoCodecs.HEVCVAAPI))
	    newcont = new VideoHEVCVaapiEncodingInfoContainer();
	else if (codec.equals(VideoCodecs.HEVCQSV))
	    newcont = new VideoHEVCQsvEncodingInfoContainer();
	else if (codec.equals(VideoCodecs.KVAZAAR))
	    newcont = new VideoKvazaarEncodingInfoContainer();
	else if (codec.equals(VideoCodecs.VP8))
	    newcont = new VideoVP8EncodingInfoContainer();
	else if (codec.equals(VideoCodecs.VP8VAAPI))
	    newcont = new VideoVP8VaapiEncodingInfoContainer();
	else if (codec.equals(VideoCodecs.VP9))
	    newcont = new VideoVP9EncodingInfoContainer();
	else if (codec.equals(VideoCodecs.VP9VAAPI))
	    newcont = new VideoVP9VaapiEncodingInfoContainer();
	else if (codec.equals(VideoCodecs.AV1))
	    newcont = new VideoAV1EncodingInfoContainer();
	else if (codec.equals(VideoCodecs.THEORA))
	    newcont = new VideoTheoraEncodingInfoContainer();
	else if (codec.equals(VideoCodecs.MPEG2))
	    newcont = new VideoMPEG2EncodingInfoContainer();
	else if (codec.equals(VideoCodecs.XVID))
	    newcont = new VideoXvidEncodingInfoContainer();
	else if (codec.equals(VideoCodecs.MPEG4))
	    newcont = new VideoMPEG4EncodingInfoContainer();
	else
	    newcont = new VideoCopyEncodingInfoContainer();

	newcont = olddinfoCont.copyCommoninfoofcontainer(newcont);

	if (!newcont.getClass().equals(olddinfoCont.getClass())) {
	    if (newcont instanceof VideoH264EncodingInfoContainer)
		((VideoH264EncodingInfoContainer) newcont).setDefaultInfo();
	    else if (newcont instanceof VideoOpenH264EncodingInfoContainer)
		((VideoOpenH264EncodingInfoContainer) newcont).setDefaultInfo();
	    else if (newcont instanceof VideoH264NvencEncodingInfoContainer)
		((VideoH264NvencEncodingInfoContainer) newcont).setDefaultInfo();
	    else if (newcont instanceof VideoH264VAAPIEncodingInfoContainer)
		((VideoH264VAAPIEncodingInfoContainer) newcont).setDefaultInfo();
	    else if (newcont instanceof VideoH264QSVEncodingInfoContainer)
			((VideoH264QSVEncodingInfoContainer) newcont).setDefaultInfo();
	    else if (newcont instanceof VideoH265EncodingInfoContainer)
		((VideoH265EncodingInfoContainer) newcont).setDefaultInfo();
	    else if (newcont instanceof VideoHEVCNvencEncodingInfoContainer)
		((VideoHEVCNvencEncodingInfoContainer) newcont).setDefaultInfo();
	    else if (newcont instanceof VideoHEVCVaapiEncodingInfoContainer)
		((VideoHEVCVaapiEncodingInfoContainer) newcont).setDefaultInfo();
	    else if (newcont instanceof VideoHEVCQsvEncodingInfoContainer)
			((VideoHEVCQsvEncodingInfoContainer) newcont).setDefaultInfo();
	    else if (newcont instanceof VideoKvazaarEncodingInfoContainer)
		((VideoKvazaarEncodingInfoContainer) newcont).setDefaultInfo();
	    else if (newcont instanceof VideoVP8EncodingInfoContainer)
		((VideoVP8EncodingInfoContainer) newcont).setDefaultInfo();
	    else if (newcont instanceof VideoVP8VaapiEncodingInfoContainer)
		((VideoVP8VaapiEncodingInfoContainer) newcont).setDefaultInfo();
	    else if (newcont instanceof VideoVP9EncodingInfoContainer)
		((VideoVP9EncodingInfoContainer) newcont).setDefaultInfo();
	    else if (newcont instanceof VideoVP9VaapiEncodingInfoContainer)
		((VideoVP9VaapiEncodingInfoContainer) newcont).setDefaultInfo();
	    else if (newcont instanceof VideoAV1EncodingInfoContainer)
		((VideoAV1EncodingInfoContainer) newcont).setDefaultInfo();
	    else if (newcont instanceof VideoTheoraEncodingInfoContainer)
		((VideoTheoraEncodingInfoContainer) newcont).setDefaultInfo();
	    else if (newcont instanceof VideoMPEG2EncodingInfoContainer)
		((VideoMPEG2EncodingInfoContainer) newcont).setDefaultInfo();
	    else if (newcont instanceof VideoXvidEncodingInfoContainer)
		((VideoXvidEncodingInfoContainer) newcont).setDefaultInfo();
	    else if (newcont instanceof VideoMPEG4EncodingInfoContainer)
		((VideoMPEG4EncodingInfoContainer) newcont).setDefaultInfo();
	    else
		((VideoCopyEncodingInfoContainer) newcont).setDefaultInfo();
	}

	else {
	    newcont = olddinfoCont.copySpecificInfocontainer(newcont);
	}

	return newcont;
    }

    /**
     * Delete if exist temp file.
     *
     * @param path the path
     */
    public static void deleteIfExistTempFile(String path) {
	File f = new File(path);
	if (f.exists())
	    f.delete();
    }

    /**
     * Gets the absolute file path.
     *
     * @param relativepath the relativepath
     * @return the absolute file path
     */
    public static String getAbsoluteFilePath(String relativepath) {
	return new File(relativepath).getAbsolutePath();
    }

    public static String getOutputFilesFolderPath() {
	PropertiesConfiguration propfolder = PropertiesWorker
		.loadPropertiesRelativePath(StaticGlobalFields.RENCODERCONFIGFILE);



	String outputfolder = null;
	String savepath = (String) propfolder.getProperty(StaticVideoEncoderFields.SAVECONVERTEDFILEPATH);
	if (EncodingPropsAuxiliar.CheckIfisValidFolder(savepath))
	    outputfolder = savepath;

	return outputfolder;

    }



    public static String replaceInvalidFFmpegChars(String name) {
	ArrayList<String> invchars = new ArrayList<>(Arrays.asList(",", "[", "]"));
	String checkchars = name;
	for (int i = 0; i < invchars.size(); i++) {
	    String ch = invchars.get(i);
	    if (checkchars.contains(ch))
		checkchars = checkchars.replace(ch, "");
	}

	return checkchars;
    }

}
