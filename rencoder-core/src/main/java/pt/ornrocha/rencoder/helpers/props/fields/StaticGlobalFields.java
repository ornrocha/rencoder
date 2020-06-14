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
package pt.ornrocha.rencoder.helpers.props.fields;

import java.io.File;

// TODO: Auto-generated Javadoc
/**
 * The Class ConstantsProperties.
 */
public class StaticGlobalFields {

	public static final String RENCODERCONFIGFILE = "settings/rencoder.conf";

	/** The Constant LANGUAGESFOLDER. */
	public static final String LANGUAGESFOLDER = "languages/";

	/** The Constant LANGUAGE. */
	public static final String LANGUAGE = "language";

	/** The Constant COUNTRY. */
	public static final String COUNTRY = "country";

	/** The Constant VIDEOEXTENSIONS. */
	public static final String VIDEOEXTENSIONS = "video.extensions";

	/** The Constant SUBTITLESEXTENSIONS. */
	public static final String SUBTITLESEXTENSIONS = "subtitle.extensions";

	/** The Constant CONVERTERFOLDER. */
	public static final String CONVERTERFOLDER = "ORSimpleVideoConverter";

	/** The Constant BOTTOMCENTER. */
	public static final String BOTTOMCENTER = "SubtitlesConfigScrollPanel.BottomAlignment";

	/** The Constant TOPCENTER. */
	public static final String TOPCENTER = "SubtitlesConfigScrollPanel.TopAlignment";

	/** The Constant OUTILINEWITHSHADOW. */
	public static final String OUTILINEWITHSHADOW = "SubtitlesConfigScrollPanel.BorderStyleOutline";

	/** The Constant OPAQUEBOX. */
	public static final String OPAQUEBOX = "SubtitlesConfigScrollPanel.BorderStyleBox";

	/** The Constant BORDERSTYLENONE. */
	public static final String BORDERSTYLENONE = "SubtitlesConfigScrollPanel.BorderStyleNone";

	/** The Constant WHITECOLOR. */
	public static final String WHITECOLOR = "SubtitlesConfigScrollPanel.setWhiteColor";

	/** The Constant YELLOWCOLOR. */
	public static final String YELLOWCOLOR = "SubtitlesConfigScrollPanel.setYellowColor";

	/** The Constant ORANGECOLOR. */
	public static final String ORANGECOLOR = "SubtitlesConfigScrollPanel.setOrangeColor";

	/** The Constant GREENCOLOR. */
	public static final String GREENCOLOR = "SubtitlesConfigScrollPanel.setGreenColor";

	/** The Constant BLUECOLOR. */
	public static final String BLUECOLOR = "SubtitlesConfigScrollPanel.setBlueColor";

	/** The Constant REDCOLOR. */
	public static final String REDCOLOR = "SubtitlesConfigScrollPanel.setRedColor";

	/** The Constant ENCODERFOLDERPATH. */
	public static final String ENCODERFOLDERPATH = "ffmpeg/";

	/** The Constant ENCODERPATH. */
	public static final String ENCODERPATH = "ffmpeg.path";

	/** The Constant NUMBERSIMULTANEOUSCONVERSIONS. */
	public static final String NUMBERSIMULTANEOUSCONVERSIONS = "concurrent.processes";
	public static final String MAXMUXINGQUEUESIZE = "max.muxing.size";

	/** The Constant FONTSFILE. */
	public static final String FONTSCONFIGPATH = "settings/fontsconf/";
	public static final String WINDOWSFONTSFILE = "fonts_windows.conf";
	public static final String LINUXFONTSFILE = "fonts_linux.conf";
	public static final String MACOSFONTSFILE = "fonts_macos.conf";

	/** The Constant QUOTE. */
	public static final String QUOTE = "\"";

	public static final String APOSTH = "'";

	/** The Constant FFMPEGLINUX. */
	public static final String FFMPEGLINUX = "http://johnvansickle.com/ffmpeg/";
	// public static final String FFMPEGLINUX="http://ffmpeg.gusari.org/static/";
	/** The Constant FFMPEGWINDOWS. */
	public static final String FFMPEGWINDOWS = "http://ffmpeg.zeranoe.com/builds/";

	/** The Constant FFMPEGMACOS. */
	public static final String FFMPEGMACOS = "https://www.evermeet.cx/ffmpeg/";

	/** The Constant MANUALFOLDER. */
	public static final String MANUALFOLDER = "manual/";

	/** The Constant MANUALNAME. */
	public static final String MANUALNAME = "manual.pdf";

	/** The Constant LOGSFOLDER. */
	public static final String LOGSFOLDER = "Logs/";

	/** The Constant LOGSAVEPROKEY. */
	public static final String LOGSAVEPROKEY = "save.logs";

	/** The Constant LOGLEVELKEY. */
	public static final String LOGLEVELKEY = "log.level";

	public static final String LOGLEVELRENCODER = "rencoder.log.level";
	public static final String LOGFFMPEGRENCODER = "rencoder.log.ffmpeg";

	/** The Constant ENCODEPANELFIXKEY. */
	public static final String ENCODEPANELFIXKEY = "lock.encoder.gui.panel";

	public static final String VLCNOTFOUNDTAG = ".novlc";

	public static final String VLCPLUGINSPATH = "vlc.plugin.path";

	public static final String NUMBERMINFILESLAUNCHGUI = "number.min.of.file.to.launch.import.info.gui";

	public static final String USEVLCJPLAYERASPREVIEWER = "use.vlcjplayer.as.previewer.if.possible";

	public static final String USEDEMOASDEFAULT = "use.demo.as.default";
	public static final String USEDEMOSIZEPICTURE = "use.demo.size.picture";

	public static final String MOVIEDEMO = "settings/demo/demo.mp4";
	public static final String SUBDEMO = "settings/demo/demo.ass";

	public static final String CONFFFMPEGFONTSTAG = ENCODERFOLDERPATH + ".ffmpegfontsok";

	/*
	 * Temporary field names
	 */

	public final static String TEMPSUBS = "tempsubs";
	public final static String TEMPFOLDER = "tempfolder";

	public static final String RESTARTTAG = ".restart";
	public static final String LOG4JCONFIG = "settings/log4j.properties";
	public static final String RENCODERLOGPATH = "rencoder.log";
	
	public static final String DEFAULTSOFTSUBLANG = "subtitles.default.language";

}
