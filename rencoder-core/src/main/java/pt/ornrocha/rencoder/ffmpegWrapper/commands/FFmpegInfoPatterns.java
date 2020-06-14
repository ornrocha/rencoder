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
package pt.ornrocha.rencoder.ffmpegWrapper.commands;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.general.EncoderType;
import pt.ornrocha.rencoder.helpers.IndexedHashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class FFmpegInfoPatterns.
 */
public class FFmpegInfoPatterns {

	/** The getinputstream. */
	public static String GETINPUTSTREAM = "getinputstream";

	/** The geterrorstream. */
	public static String GETERRORSTREAM = "geterrorstream";

	/**
	 * Gets the aspect.
	 *
	 * @param s the s
	 * @return the aspect
	 */
	public static String getAspect(String s) {
		Pattern aspect = Pattern.compile("(\\d+x\\d+)(,|(\\s+\\[))");
		Matcher m = aspect.matcher(s);
		if (m.find())
			return m.group(1);

		return null;
	}

	/**
	 * Gets the video codec info.
	 *
	 * @param s the s
	 * @return the video codec info
	 */
	public static String getVideoCodecInfo(String s) {
		Pattern aspect = Pattern.compile("Video:\\s*(\\w+(\\s*\\(\\w+\\))*)");
		Matcher m = aspect.matcher(s);
		if (m.find())
			return m.group(1);

		return null;
	}

	/**
	 * Gets the audio codec info.
	 *
	 * @param s the s
	 * @return the audio codec info
	 */
	public static String getAudioCodecInfo(String s) {
		Pattern aspect = Pattern.compile("Audio:\\s*(\\w+)");
		Matcher m = aspect.matcher(s);
		if (m.find())
			return m.group(1);

		return null;
	}
	
	public static String getSubtitleCodecInfo(String s) {
		Pattern aspect = Pattern.compile("Subtitle:\\s*(\\w+)");
		Matcher m = aspect.matcher(s);
		if (m.find())
			return m.group(1);

		return null;
	}

	/**
	 * Gets the fps.
	 *
	 * @param s the s
	 * @return the fps
	 */
	public static String getFPS(String s) {
		Pattern aspect = Pattern.compile("(\\d+(\\.\\d+)*)\\s+fps");
		Matcher m = aspect.matcher(s);
		if (m.find())
			return m.group(1);

		else {
			Pattern aspectfpsmissing = Pattern.compile("(\\d+(\\.\\d+)*)\\s+tbr,");
			Matcher m2 = aspectfpsmissing.matcher(s);
			if (m2.find())
				return m2.group(1);

		}

		return null;
	}

	/**
	 * Gets the stream number.
	 *
	 * @param s the s
	 * @return the stream number
	 */
	public static int getStreamNumber(String s) {

		Pattern aspect = Pattern.compile("#(\\d+:(\\d+))");
		Matcher m = aspect.matcher(s);
		if (m.find())
			return Integer.parseInt(m.group(2));

		return -1;
	}
	
	public static String getLanguage(String s) {

		Pattern aspect = Pattern.compile("Stream\\s+#\\d+:\\d+\\((\\w+)\\):");
		Matcher m = aspect.matcher(s);
		if (m.find())
			return m.group(1);

		return "Unknown";
	}

	/**
	 * Gets the audio bitrate.
	 *
	 * @param s the s
	 * @return the audio bitrate
	 */
	public static String getAudioBitrate(String s) {

		if (s.contains("Audio:"))
			return getBitrateStream(s);

		return null;
	}

	/**
	 * Gets the video bitrate.
	 *
	 * @param s the s
	 * @return the video bitrate
	 */
	public static String getVideoBitrate(String s) {

		if (s.contains("Video:"))
			return getBitrateStream(s);

		return null;
	}

	/**
	 * Gets the bitrate stream.
	 *
	 * @param s the s
	 * @return the bitrate stream
	 */
	public static String getBitrateStream(String s) {

		Pattern aspect = Pattern.compile("(\\d+)\\s*kb\\/s");
		Matcher m = aspect.matcher(s);
		if (m.find())
			return m.group(1);

		return null;

	}

	/**
	 * Gets the audio frequency.
	 *
	 * @param s the s
	 * @return the audio frequency
	 */
	public static String getAudioFrequency(String s) {

		Pattern aspect = Pattern.compile("(\\d+)\\s*Hz");
		Matcher m = aspect.matcher(s);
		if (m.find())
			return m.group(1);

		return null;

	}

	/**
	 * Gets the bitrate.
	 *
	 * @param sc the sc
	 * @return the bitrate
	 */
	public static String getBitrate(Scanner sc) {
		Pattern bitPattern = Pattern.compile("(?<=bitrate: )[^\\s*kb|b\\/s]*");
		String bit = sc.findWithinHorizon(bitPattern, 0);
		return bit;

	}

	/**
	 * Gets the movie duration.
	 *
	 * @param sc the sc
	 * @return the duration
	 */
	public static String getDuration(Scanner sc) {
		Pattern durPattern = Pattern.compile("(?<=Duration: )[^,]*");
		String dur = sc.findWithinHorizon(durPattern, 0);
		return dur;
	}

	/**
	 * Gets the audio channelstype.
	 *
	 * @param s the s
	 * @return the audio channelstype
	 */
	public static String getaudioChannelstype(String s) {
		Pattern aspect = Pattern.compile("Hz,\\s+(\\w+(\\.\\w+)*)");
		Matcher m = aspect.matcher(s);
		if (m.find())
			return m.group(1);

		return null;
	}

	/**
	 * Gets the ffmpeg version.
	 *
	 * @param sc the sc
	 * @return the f fmpeg version
	 */
	public static String getFFmpegVersion(Scanner sc) {
		Pattern versionline = Pattern.compile("version.*");
		String line = sc.findWithinHorizon(versionline, 0);
		if (line != null) {
			Logger.debug("FFmpeg info: ", line);
			Pattern pat = Pattern.compile("version\\s+(.*)\\s+Copyright");
			Matcher m = pat.matcher(line);
			if (m.find()) {
				return m.group(1);
			}
		}

		return null;
	}

	/**
	 * Gets the existing encoder type info.
	 *
	 * @param encodertype the encodertype
	 * @param sc          the sc
	 * @return the existing encoder type info
	 */
	public static IndexedHashMap<String, String> getExistingEncoderTypeInfo(EncoderType encodertype, Scanner sc) {

		IndexedHashMap<String, String> encoders = new IndexedHashMap<>();

		String mainpattern = "\\s+(\\w+([_-]\\w+)*)\\s+(.*)";
		String pattern = null;
		Pattern type = null;
		Pattern patline = null;
		String line = null;

		if (encodertype.equals(EncoderType.VIDEO)) {
			pattern = "V[\\.F][\\.S][\\.X][\\.B][\\.D]";
			type = Pattern.compile(pattern + ".*");
		} else if (encodertype.equals(EncoderType.AUDIO)) {
			pattern = "A[\\.F][\\.S][\\.X][\\.B][\\.D]";
			type = Pattern.compile(pattern + ".*");
		} else if (encodertype.equals(EncoderType.SUBTITLES)) {
			pattern = "S[\\.F][\\.S][\\.X][\\.B][\\.D]";
			type = Pattern.compile(pattern + ".*");
		}

		patline = Pattern.compile(pattern + mainpattern);

		Logger.debug("\n####### Checking "+encodertype.toString()+" encoders ########\n");
		while (null != (line = sc.findWithinHorizon(type, 0))) {
			Matcher m = patline.matcher(line);
			Logger.debug(line);
			if (m.find()) {
				encoders.put(m.group(1), m.group(3));
			}
		}

		return encoders;
	}

	public static int getMergeFileNumber(String s) {
		Pattern aspect = Pattern.compile("file:(\\d+)");
		Matcher m = aspect.matcher(s);
		if (m.find())
			return Integer.valueOf(m.group(1));

		return -1;
	}

}
