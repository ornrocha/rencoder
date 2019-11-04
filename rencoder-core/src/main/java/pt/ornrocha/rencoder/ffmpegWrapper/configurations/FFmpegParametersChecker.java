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
package pt.ornrocha.rencoder.ffmpegWrapper.configurations;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.commands.FFmpegInfoPatterns;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.general.EncoderType;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.utilities.FFmpegUtils;
import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.helpers.osystem.OSystem;
import pt.ornrocha.rencoder.helpers.props.fields.StaticFFmpegFields;
import pt.ornrocha.rencoder.helpers.props.fields.StaticGlobalFields;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;

// TODO: Auto-generated Javadoc
/**
 * The Class FFmpegParametersChecker.
 */
public class FFmpegParametersChecker {

    /**
     * Gets the ffmpeg info scanner from command.
     *
     * @param checkcmds  the checkcmds
     * @param typeoutput the typeoutput
     * @return the ffmpeg info scanner from command
     * @throws IOException
     */
    public static Scanner getFFmpegInfoScannerFromCommand(ArrayList<String> checkcmds, String typeoutput)
	    throws IOException {

	Scanner sc = null;
	String ffmpegpath = FFmpegManager.getInstance().getFFmpegPath();

	ArrayList<String> inputcmds = new ArrayList<>();
	inputcmds.add(ffmpegpath);

	if (checkcmds != null)
	    inputcmds.addAll(checkcmds);

	if (ffmpegpath != null) {
	    try {
		ProcessBuilder pb = new ProcessBuilder(inputcmds);
		Process p = pb.start();

		if (typeoutput.equals(FFmpegInfoPatterns.GETERRORSTREAM))
		    sc = new Scanner(p.getErrorStream());
		else if (typeoutput.equals(FFmpegInfoPatterns.GETINPUTSTREAM))
		    sc = new Scanner(p.getInputStream());

	    } catch (IOException e) {
		Logger.error(e);
		throw e;
	    }
	}
	return sc;
    }

    /**
     * Gets the version ffmpeg.
     *
     * @return the version ffmpeg
     */
    public static String getVersionFFmpeg() {

	String version = null;

	try {

	    Scanner sc = getFFmpegInfoScannerFromCommand(null, FFmpegInfoPatterns.GETERRORSTREAM);
	    if (sc != null)
		version = FFmpegInfoPatterns.getFFmpegVersion(sc);

	} catch (Exception e) {
	    Logger.error(e);
	}

	return version;
    }

    /**
     * Gets the ffmpeg encoders.
     *
     * @param encodertype the encodertype
     * @return the ffmpeg encoders
     */
    public static IndexedHashMap<String, String> getFFmpegEncoders(EncoderType encodertype) {

	IndexedHashMap<String, String> encoders = null;
	ArrayList<String> checkcmd = new ArrayList<>();
	checkcmd.add("-encoders");
	Scanner sc = null;
	try {
	    sc = getFFmpegInfoScannerFromCommand(checkcmd, FFmpegInfoPatterns.GETINPUTSTREAM);
	} catch (IOException e) {
	    Logger.error(e);
	}
	encoders = FFmpegInfoPatterns.getExistingEncoderTypeInfo(encodertype, sc);
	
	return encoders;
    }

    public static void setFFmpegExecutable(String path) {
	if (path != null) {
	    File ffmpeg = new File(path);
	    ffmpeg.setExecutable(true);
	    ffmpeg.setWritable(true);
	    ffmpeg.setReadable(true);
	} else {
	    String file = PropertiesWorker.getStringProperty(StaticGlobalFields.RENCODERCONFIGFILE,
		    StaticGlobalFields.ENCODERPATH);
	    File ffmpeg = new File(file);
	    if (!ffmpeg.canExecute())
		ffmpeg.setExecutable(true);
	}
    }

    public static boolean isSupportedCodec(VideoCodecs codec, boolean checkhwaccel) {
	ArrayList<String> checkcmd = new ArrayList<>();

	if (checkhwaccel)
	    checkcmd.addAll(codec.getDecodingHWAcceleration());

	checkcmd.add(StaticFFmpegFields.input);
	checkcmd.add(FFmpegUtils.getMovieDemo());
	if (codec.needsDecodingFilter())
	    checkcmd.addAll(codec.getCmdDecodingFilter());
	checkcmd.add(StaticFFmpegFields.encodevideocodec);
	checkcmd.add(codec.getFFmpegID());
	checkcmd.add(StaticFFmpegFields.encodeaudiocodec);
	checkcmd.add(AudioCodecs.COPY.getFFmpegID());
	checkcmd.add("-ss");
	checkcmd.add("00:00:00");
	checkcmd.add("-t");
	checkcmd.add("00:00:05");
	checkcmd.add("-y");
	checkcmd.add(getTMPDemoTestFilePath());

	Scanner sc = null;

	try {
	    sc = getFFmpegInfoScannerFromCommand(checkcmd, FFmpegInfoPatterns.GETERRORSTREAM);
	} catch (IOException e) {
	    Logger.error(e);
	}

	Pattern pat = Pattern.compile("Conversion failed!| " + "*Unknown decoder* | " + "*Unknown encoder* | "
		+ "*No NVENC capable devices found* | " + "*No NVENC capable devices* | " + "*Unrecognized option* | "
		+ "Cannot load cuvidGetDecodeStatus| " + "Failed to initialise VAAPI connection:* | "
		+ "*Cannot load nvcuda.dll*| " + "*No VA display found for device:* | "
		+ "*Function not implemented* | *Cannot init CUDA|" + "Failed setup for format cuda:* | "
		+ "*Error reinitializing* ");

	Logger.info("Analysing codec: " + codec.toString());
	while (sc.hasNextLine()) {
	    String currentline = sc.nextLine();
	    Matcher m = pat.matcher(currentline);
	    Logger.debug(currentline);
	    // System.out.println(currentline);
	    if (m.find()) {
		if (checkhwaccel)
		    Logger.info(codec.toString() + " with " + codec.getDecodingHWACCType()
		    + " HWAcceleration is not supported -> will be deactivated\n");
		else
		    Logger.info(codec.toString() + " is not supported -> will be deactivated\n");
		return false;
	    }
	}

	return true;

    }

    private static String getTMPDemoTestFilePath() {
	return FilenameUtils.concat(OSystem.getSystemTemporaryFolder(), "democonv.mp4");
    }

}
