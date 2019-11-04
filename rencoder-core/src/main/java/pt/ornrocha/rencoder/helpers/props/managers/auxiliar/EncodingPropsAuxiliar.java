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
package pt.ornrocha.rencoder.helpers.props.managers.auxiliar;

import java.io.File;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.configuration.PropertiesConfiguration;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioChannels;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioSampleRates;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.general.FFmpegLogLevel;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.subtitles.SubEncodings;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.subtitles.SubtitlesColor;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.subtitles.SubtitlesFontStyle;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.AspectRatio;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.FPS;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoContainers;
import pt.ornrocha.rencoder.ffmpegWrapper.othercomponents.AspectComponent;
import pt.ornrocha.rencoder.ffmpegWrapper.othercomponents.VideoAspectSizes;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.helpers.props.fields.StaticFFmpegFields;
import pt.ornrocha.rencoder.helpers.props.fields.StaticGlobalFields;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.MediaInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.streams.AudioStreamInfo;

// TODO: Auto-generated Javadoc
/**
 * The Class EncodingPropsAuxiliar.
 */
public class EncodingPropsAuxiliar {

    /**
     * Gets the video size aspect.
     *
     * @param aspect the aspect
     * @return the video size aspect
     */
    public static AspectComponent getVideoSizeAspect(String aspect) {

	VideoAspectSizes container = new VideoAspectSizes();

	if (aspect != null) {
	    for (AspectComponent asp : container.values()) {
		if (asp.getNameAspect().toLowerCase().equals(aspect.toLowerCase()))
		    return asp;
	    }

	    if (checkifisvalidAspectsize(aspect))
		return new AspectComponent("Custom", aspect);
	    else
		return new AspectComponent();

	}
	return new AspectComponent();

    }

    /**
     * Check if is valid aspect size.
     *
     * @param s the s
     * @return true, if successful
     */
    public static boolean checkifisvalidAspectsize(String s) {

	Pattern aspect = Pattern.compile("\\d+[Xx]\\d+");
	Matcher m = aspect.matcher(s);
	if (m.find())
	    return true;

	return false;
    }

    /**
     * Gets the video aspect ratio.
     *
     * @param ratio the ratio
     * @return the video aspect ratio
     */
    public static AspectRatio getVideoAspectRatio(String ratio) {

	if (ratio != null) {
	    if (ratio.equals(AspectRatio.A16_9.getVideoRatioID()))
		return AspectRatio.A16_9;
	    else if (ratio.equals(AspectRatio.A4_3.getVideoRatioID()))
		return AspectRatio.A4_3;
	    else
		return AspectRatio.SAMEASSOURCE;
	} else
	    return AspectRatio.SAMEASSOURCE;

    }

    /**
     * Gets the video frame rate.
     *
     * @param fps the fps
     * @return the video frame rate
     */
    public static FPS getVideoFrameRate(String fps) {

	if (fps != null) {

	    for (FPS fpsvalue : FPS.values()) {
		if (fpsvalue.getVideoFrameRateID().equals(fps))
		    return fpsvalue;
	    }
	    return FPS.SAMEASSOURCE;
	}

	return FPS.SAMEASSOURCE;

    }

    /**
     * Gets the video quality value.
     *
     * @param s         the s
     * @param codectype the codectype
     * @return the video quality value
     */
    public static int getVideoQualityValue(String s, VideoCodecs codectype) {

	int val = -1;
	Pattern qualpat = null;

	if (s != null) {
	    if (codectype.equals(VideoCodecs.H264) || codectype.equals(VideoCodecs.H265))
		qualpat = Pattern.compile("(^[0-9]$|^[1-4][0-9]$|^5[01]$)");
	    else if (codectype.equals(VideoCodecs.VP8) || codectype.equals(VideoCodecs.VP9))
		qualpat = Pattern.compile("(^[4-9]$|^[1-5][0-9]$|^6[0-3]$)");
	    else
		qualpat = Pattern.compile("(^[1-9]$|^[1-2][0-9]$|^3[01])");
	    Matcher m = qualpat.matcher(s);
	    if (m.find()) {
		String res = m.group(1);

		val = Integer.parseInt(res);
	    }
	}

	return val;
    }

    /**
     * Gets the default video codec quality.
     *
     * @param codec the codec
     * @return the default video codec quality
     */
    public static int getDefaultVideoCodecQuality(VideoCodecs codec) {

	if (codec.equals(VideoCodecs.H264))
	    return 23;
	else if (codec.equals(VideoCodecs.H265))
	    return 28;
	else if (codec.equals(VideoCodecs.VP8) || codec.equals(VideoCodecs.VP9))
	    return 20;
	else
	    return 4;

    }

    /**
     * Gets the bitrate value.
     *
     * @param s the s
     * @return the bitrate value
     */
    public static String getBitrateValue(String s) {

	String res = null;

	if (s != null) {
	    Pattern aspect = Pattern.compile("(^\\d+k$|^\\d+$)");
	    Matcher m = aspect.matcher(s);
	    if (m.find()) {
		res = m.group(1);

		if (!res.contains("k"))
		    res = res + "k";
	    }

	}

	return res;
    }

    /**
     * Gets the bitrate value integer.
     *
     * @param s the s
     * @return the bitrate value integer
     */
    public static int getBitrateValueInteger(String s) {

	int res = -1;

	if (s != null) {
	    Pattern aspect = Pattern.compile("(^(\\d+)k$)");
	    Matcher m = aspect.matcher(s);
	    if (m.find()) {
		res = Integer.parseInt(m.group(2));
	    }

	}

	return res;
    }

    /**
     * Gets the video file size.
     *
     * @param s the s
     * @return the video file size
     */
    public static int getVideoFileSize(String s) {

	int filesize = -1;
	if (s != null) {
	    Pattern sizepat = Pattern.compile("^\\d+$");
	    Matcher m = sizepat.matcher(s);
	    if (m.find()) {
		String res = m.group();
		filesize = Integer.parseInt(res);
	    }
	}
	return filesize;
    }

 
    /**
     * Gets the audio vbr value to audio codec.
     *
     * @param s          the s
     * @param audiocodec the audiocodec
     * @return the audio vbr value to audio codec
     */
    public static int getAudioVBRValueToAudioCodec(String s, AudioCodecs audiocodec) {

	int val = -1;
	Pattern qualpat = null;

	if (s != null) {
	    if (audiocodec.equals(AudioCodecs.MP3))
		qualpat = Pattern.compile("(^[0-9]$)");
	    else if (audiocodec.equals(AudioCodecs.VORBIS))
		qualpat = Pattern.compile("(^[0-9]$|10)");
	    else if (audiocodec.equals(AudioCodecs.LCAAC))
		qualpat = Pattern.compile("(^[0-9]$|10)");
	    else
		qualpat = Pattern.compile("(^[0-9]$)");

	    Matcher m = qualpat.matcher(s);
	    if (m.find()) {
		String res = m.group(1);

		val = Integer.parseInt(res);
	    }
	}

	if (val == -1)
	    val = getDefaultAudioCodecQuality(audiocodec);

	return val;
    }

    /**
     * Gets the default audio codec quality.
     *
     * @param codec the codec
     * @return the default audio codec quality
     */
    public static int getDefaultAudioCodecQuality(AudioCodecs codec) {

	if (codec.equals(AudioCodecs.MP3))
	    return 5;
	else if (codec.equals(AudioCodecs.VORBIS))
	    return 4;
	else if (codec.equals(AudioCodecs.LCAAC))
	    return 5;
	else
	    return 4;

    }

    /**
     * Gets the h264 qp.
     *
     * @param s the s
     * @return the h264 qp
     */
    public static int getH264QP(String s) {

	int qp = -1;

	if (s != null) {

	    Pattern pat = Pattern.compile("(^[1-9]$|^[1-9][0-9]$|^1[0-9][0-9]$|^2[0-4][0-9]$|250)");

	    Matcher m = pat.matcher(s);
	    if (m.find()) {
		String res = m.group(0);

		qp = Integer.parseInt(res);
	    }
	}
	return qp;
    }

    /**
     * Gets the GOP size.
     *
     * @param s the s
     * @return the GOP size
     */
    public static int getGOPSize(String s) {

	int gp = -1;

	if (s != null) {

	    Pattern pat = Pattern.compile("(^\\d$|^[1-9][0-9]$|^100$)");

	    Matcher m = pat.matcher(s);
	    if (m.find()) {
		String res = m.group(0);

		gp = Integer.parseInt(res);
	    }
	}
	return gp;
    }


    /**
     * Gets the theads number.
     *
     * @param s the s
     * @return the theads number
     */
    public static int getTheadsNumber(String s) {

	int res = 1;

	if (s != null) {

	    Pattern pat = Pattern.compile("(^[1-9]$|^[1-9][0-9]$)");

	    Matcher m = pat.matcher(s);
	    if (m.find()) {
		String r = m.group(0);

		res = Integer.parseInt(r);
	    }
	}

	return res;
    }


    /**
     * Check if is valid folder.
     *
     * @param s the s
     * @return true, if successful
     */
    public static boolean CheckIfisValidFolder(String s) {

	if (s != null && !s.isEmpty()) {
	    File folder = new File(s);

	    if (folder.exists() && folder.isDirectory())
		return true;
	}

	return false;
    }

    /**
     * Gets the video container from movie extension.
     *
     * @param ext the ext
     * @return the video container from movie extension
     */
    public static VideoContainers getVideoContainerFromMovieExtension(String ext) {

	VideoContainers cont = null;

	for (VideoContainers vcont : VideoContainers.values()) {
	    if (vcont.toString().equals(ext.toLowerCase()))
		cont = vcont;
	}

	return cont;
    }

    /**
     * Gets the name using resource bundle.
     *
     * @param key the key
     * @return the name using resource bundle
     */
    public static String getNameUsingResourceBundle(String key) {
	ResourceBundle rb = ResourceBundle.getBundle("lang", LangTools.getDefinedLanguage(),
		LangTools.loadLanguagesPath());
	String value = rb.getString(key);
	if (value != null)
	    return value;

	return null;
    }

    /**
     * Gets the sub encoding by name.
     *
     * @param s the s
     * @return the sub encoding by name
     */
    public static SubEncodings getSubEncodingByName(String s) {

	SubEncodings subenc = SubEncodings.ISO88591;

	for (SubEncodings sec : SubEncodings.values()) {
	    if (sec.toString().toLowerCase().equals(s.toLowerCase()))
		subenc = sec;
	}
	return subenc;
    }

    /**
     * Gets the sub color.
     *
     * @param s the s
     * @return the sub color
     */
    public static SubtitlesColor getSubColor(String s) {

	SubtitlesColor subcol = SubtitlesColor.WHITE;

	for (SubtitlesColor sc : SubtitlesColor.values()) {
	    if (sc.getColorID().toLowerCase().equals(s.toLowerCase()))
		subcol = sc;
	}
	return subcol;
    }

    /**
     * Gets the subtitles font style.
     *
     * @param bold   the bold
     * @param italic the italic
     * @return the subtitles font style
     */
    public static SubtitlesFontStyle getSubtitlesFontStyle(boolean bold, boolean italic) {

	if (bold && !italic)
	    return SubtitlesFontStyle.BOLD;
	else if (!bold && italic)
	    return SubtitlesFontStyle.ITALIC;
	else if (bold && italic)
	    return SubtitlesFontStyle.BoldItalic;

	return SubtitlesFontStyle.PLAIN;
    }

    /**
     * Gets the number simultaneous conversions.
     *
     * @return the number simultaneous conversions
     */
    public static int getNumberSimultaneousConversions() {
	int numberproc = 1;
	PropertiesConfiguration prop = PropertiesWorker.loadPropertiesRelativePath(StaticGlobalFields.RENCODERCONFIGFILE);

	if (prop.containsKey(StaticGlobalFields.NUMBERSIMULTANEOUSCONVERSIONS)) {
	    if (!prop.getProperty(StaticGlobalFields.NUMBERSIMULTANEOUSCONVERSIONS).toString().isEmpty()) {
		String nproc = (String) prop.getProperty(StaticGlobalFields.NUMBERSIMULTANEOUSCONVERSIONS);
		numberproc = Integer.valueOf(nproc);
	    }
	}
	return numberproc;
    }

    /**
     * Gets the audio sample rate from freq string.
     *
     * @param freq the freq
     * @return the audio sample rate from freq string
     */
    public static AudioSampleRates getAudioSampleRateFromFreqString(String freq) {

	for (AudioSampleRates audiofreq : AudioSampleRates.values()) {
	    if (audiofreq.toString().equals(freq))
		return audiofreq;
	}
	return null;
    }

    /**
     * Gets the audio channel from string.
     *
     * @param channel the channel
     * @return the audio channel from string
     */
    public static AudioChannels getAudioChannelFromString(String channel) {

	for (AudioChannels audiochannel : AudioChannels.values()) {
	    if (audiochannel.toString().equals(channel))
		return audiochannel;
	    else if (String.valueOf(audiochannel.getchannels()).equals(channel))
		return audiochannel;
	}
	return null;
    }

    /**
     * Sets the max allowed audio settings.
     *
     * @param encodcontainer the encodcontainer
     * @param movieInfo      the movie info
     * @return the i general video enc info container
     */
    public static IGeneralVideoEncInfoContainer setMaxAllowedAudioSettings(IGeneralVideoEncInfoContainer encodcontainer,
	    MediaInfoContainer movieInfo) {

	AudioStreamInfo audio = movieInfo.getfirstAudioStream();

	if (audio != null) {
	    String audiofreq = audio.getAudiofrequency();
	    AudioSampleRates freq = getAudioSampleRateFromFreqString(audiofreq);
	    if (freq != null) {
		encodcontainer.setMaxAllowedAudiosamplerate(freq);

		int leveltag = encodcontainer.getAudiosamplerate().getLevelTag();
		if (!(leveltag > freq.getLevelTag()))
		    encodcontainer.setAudiosamplerate(freq);
	    }

	    String audiochannel = movieInfo.getfirstAudioStream().getAudiochanneltype();
	    AudioChannels channels = getAudioChannelFromString(audiochannel);

	    AudioChannels preferredaudiochannel = encodcontainer.getMaxAllowedAudiochannels();

	    if (channels != null) {
		if (preferredaudiochannel.getLevelTag() >= channels.getLevelTag()) {
		    encodcontainer.setMaxAllowedAudiochannels(preferredaudiochannel);
		    encodcontainer.setAudiochannels(preferredaudiochannel);
		} else {
		    encodcontainer.setMaxAllowedAudiochannels(channels);
		    encodcontainer.setAudiochannels(channels);
		}

	    }

	    encodcontainer = setAudioChannel(encodcontainer, movieInfo);
	}
	return encodcontainer;
    }

    /**
     * Sets the audio channel.
     *
     * @param cont      the cont
     * @param movieInfo the movie info
     * @return the i general video enc info container
     */
    public static IGeneralVideoEncInfoContainer setAudioChannel(IGeneralVideoEncInfoContainer cont,
	    MediaInfoContainer movieInfo) {

	if (cont.getMaxAllowedAudiochannels().equals(AudioChannels.SURROUND)) {
	    if (cont.isAc3passthrough()) {
		if (movieInfo.getfirstAudioStream().getCodectype().toLowerCase()
			.equals(AudioCodecs.AC3.toString().toLowerCase())) {
		    cont.setAudiocodec(AudioCodecs.COPY);
		} else {
		    cont.setAudiocodec(AudioCodecs.AC3);
		    cont.setUseAudioCBR(true);
		    cont.setAudioConstantBitrateValue("448k");
		}

		cont.setModifiedtag(true);
	    } else {

		AudioCodecs codec = cont.getAudiocodec();
		if (codec.equals(AudioCodecs.MP3) || codec.equals(AudioCodecs.MP2))
		    cont.setAudiochannels(AudioChannels.STEREO);

	    }
	}

	return cont;
    }

    /**
     * Gets the extra f fmpeg cm ds.
     *
     * @param cmdline the cmdline
     * @return the extra f fmpeg cm ds
     */
    public static ArrayList<String> getExtraFFmpegCMDs(String cmdline) {
	ArrayList<String> cmd = new ArrayList<>();

	String[] cmds = cmdline.split("\\s+");

	for (int i = 0; i < cmds.length; i++) {
	    cmd.add(cmds[i]);
	}

	return cmd;
    }

    /**
     * Gets the extra video f fmpeg cm ds h265.
     *
     * @param cmdline the cmdline
     * @param isVBR   the is vbr
     * @return the extra video f fmpeg cm ds h265
     */
    public static ArrayList<String> getExtraVideoFFmpegCMDsH265(String cmdline, boolean isVBR) {

	String res = null;
	Pattern pat = Pattern.compile("(" + StaticFFmpegFields.x265params + "\\s+" + "(\\w+([=:.]\\w+)*))");

	Matcher m = pat.matcher(cmdline);
	if (m.find()) {

	    if (isVBR)
		res = m.group(2);
	    else
		res = m.group(1);
	}

	ArrayList<String> cmd = getExtraFFmpegCMDs(res);

	return cmd;
    }

    /**
     * Gets the log level.
     *
     * @param loglevel the loglevel
     * @return the log level
     */
    public static FFmpegLogLevel getLogLevel(String loglevel) {
	loglevel = loglevel.toLowerCase();

	switch (loglevel) {
	case "panic":
	    return FFmpegLogLevel.PANIC;
	case "fatal":
	    return FFmpegLogLevel.FATAL;
	case "error":
	    return FFmpegLogLevel.ERROR;
	case "warning":
	    return FFmpegLogLevel.WARNING;
	case "info":
	    return FFmpegLogLevel.INFO;
	case "verbose":
	    return FFmpegLogLevel.VERBOSE;
	case "debug":
	    return FFmpegLogLevel.DEBUG;

	default:
	    return null;
	}

    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
	System.out.println(getExtraVideoFFmpegCMDsH265("-x265-params level=1.3:reg=hj:ty=3.9", false));
    }

}
