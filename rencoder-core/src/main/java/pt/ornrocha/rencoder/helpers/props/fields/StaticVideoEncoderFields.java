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

// TODO: Auto-generated Javadoc
/**
 * The Class VideoEncPropertiesConstants.
 */
public class StaticVideoEncoderFields {

	/** The Constant GENERALSETTINGSFILEPATH. */
	public static final String GENERALSETTINGSFILEPATH = "settings/GeneralUserProfile.conf";

	/** The Constant PROFILESDIRPATH. */
	public static final String PROFILESDIRPATH = "profiles/";

	/** The Constant PROFILENAME. */
	public static final String PROFILENAME = "profile.name";

	// video encoding properties reader fields

	/** The Constant VIDEOCODEC. */
	public static final String VIDEOCODEC = "video.codec";

	/** The Constant VIDEOCONTAINER. */
	public static final String VIDEOCONTAINER = "video.container";

	/** The Constant VIDEOASPECTSIZE. */
	public static final String VIDEOASPECTSIZE = "video.aspect.size";

	/** The Constant VIDEOASPECTRATIO. */
	public static final String VIDEOASPECTRATIO = "video.aspect.ratio";

	/** The Constant VIDEOFRAMERATE. */
	public static final String VIDEOFRAMERATE = "video.frame.rate";

	/** The Constant VIDEOUSECBR. */
	public static final String VIDEOUSECBR = "video.enc.is.CBR";

	/** The Constant VIDEOCBRVALUE. */
	public static final String VIDEOCBRVALUE = "video.CBR.value";

	/** The Constant VIDEOUSEVBR. */
	public static final String VIDEOUSEVBR = "video.enc.is.VBR";

	/** The Constant VIDEOVBRVALUE. */
	public static final String VIDEOVBRVALUE = "video.VBR.value";

	/** The Constant VIDEOUSEFILESIZE. */
	public static final String VIDEOUSEFILESIZE = "video.enc.is.filesize";

	/** The Constant VIDEOFILESIZE. */
	public static final String VIDEOFILESIZE = "video.filesize.valueMB";

	/** The Constant TWOPASSENCODING. */
	public static final String TWOPASSENCODING = "video.two.pass.encoding";

	/** The Constant VIDEOEXTRAFFMPEGCMD. */
	public static final String VIDEOEXTRAFFMPEGCMD = "video.ffmpeg.commandline";

	/** The Constant VIDEOUSEONLYEXTRAFFMPEGCMD. */
	public static final String VIDEOUSEONLYEXTRAFFMPEGCMD = "video.use.only.ffmpeg.commandline";

	/** The Constant VIDEOFILEEXTENSION. */
	public static final String VIDEOFILEEXTENSION = "video.extension.with.ffmpeg.commandline";

	/** The Constant FFMPEGTHREADS. */
	public static final String FFMPEGTHREADS = "video.ffmpeg.threads";

	/** The Constant VIDEOOVERWRITE. */
	public static final String VIDEOOVERWRITE = "overwrite.video.file";

	/** The Constant AUDIOCODEC. */
	public static final String AUDIOCODEC = "audio.codec";

	/** The Constant AUDIOUSECBR. */
	public static final String AUDIOUSECBR = "audio.enc.is.CBR";

	/** The Constant AUDIOCBRVALUE. */
	public static final String AUDIOCBRVALUE = "audio.cbr.value";

	/** The Constant AUDIOUSEVBR. */
	public static final String AUDIOUSEVBR = "audio.enc.is.VBR";

	/** The Constant AUDIOVBRVALUE. */
	public static final String AUDIOVBRVALUE = "audio.vbr.value";

	/** The Constant AUDIOPREFFREQ. */
	public static final String AUDIOPREFFREQ = "audio.preferred.frequency";

	/** The Constant AUDIOPREFCHANNELS. */
	public static final String AUDIOPREFCHANNELS = "audio.preferred.channels";

	/** The Constant AUDIOAC3PASSTHROUGH. */
	public static final String AUDIOAC3PASSTHROUGH = "audio.ac3.passthrough.if.possible";

	/** The Constant AUDIOEXTRAFFMPEGCMD. */
	public static final String AUDIOEXTRAFFMPEGCMD = "audio.ffmpeg.commandline";

	/** The Constant AUDIOUSEONLYEXTRAFFMPEGCMD. */
	public static final String AUDIOUSEONLYEXTRAFFMPEGCMD = "audio.use.only.ffmpeg.commandline";

	/** The Constant H264PRESET. */
	// public static final String H264PRESET="h264.video.preset";

	public static final String VIDEOPRESET = "video.preset";

	/** The Constant H264PROFILE. */
	public static final String VIDEOPROFILE = "video.profile";

	public static final String VIDEOSLICEMODE = "video.slice.mode";

	public static final String VIDEONUMBERSLICES = "video.number.slices";

	/** The Constant H264FASTFIRSTPASS. */
	public static final String H264FASTFIRSTPASS = "h264.video.fastfirstpass";

	/** The Constant H264CONSTQUANT. */
	public static final String VIDEOCONSTQUANT = "video.constant.quantization";

	public static final String VIDEOQUANTPARAM = "video.quantization.parameter";

	public static final String VIDEOLEVEL = "video.level";

	/** The Constant H264CUSTOMCMD. */
	public static final String H264CUSTOMCMD = "h264.video.ffmpeg.custom.cmd";

	public static final String NVENCRATECONTROL = "nvenc.rate.control";

	// public static final String H264VAAPIPROFILE="h264.vaapi.profile";

	public static final String VAAPIQUALITY = "vaapi.quality";

	/** The Constant H265PRESET. */
	// public static final String H265PRESET="h265.video.preset";

	/** The Constant H265TUNE. */
	public static final String VIDEOTUNE = "video.tune";

	/** The Constant VIDEOPIXELFORMAT. */
	public static final String VIDEOPIXELFORMAT = "video.pixel.format";

	/** The Constant MPG2GOPSIZE. */
	public static final String MPG2GOPSIZE = "mpeg2.gop.size";

	/** The Constant MPEG2BFRAME. */
	public static final String MPEG2BFRAME = "mpeg2.bframe";

	/** The Constant MPEG2TRELLIS. */
	public static final String MPEG2TRELLIS = "mpeg2.trellis";

	/** The Constant MPEG2PELME. */
	public static final String MPEG2PELME = "mpeg2.pel.me";

	/** The Constant VP8QUALPROFILE. */
	public static final String QUALPROFILE = "quality.profile";

	/** The Constant VP8USECPU. */
	public static final String VP8USECPU = "vp8.use.cpu";

	/** The Constant VP8MINQUANT. */
	public static final String VP8MINQUANT = "vp8.min.quant";

	/** The Constant VP8MAXQUANT. */
	public static final String VP8MAXQUANT = "vp8.max.quant";

	/** The Constant VP8CPUTHREADS. */
	public static final String VP8CPUTHREADS = "vp8.cpu.threads";

	/** The Constant SAVECONVERTEDFILEPATH. */
	public static final String SAVECONVERTEDFILEPATH = "save.converted.files.on";

	/** The Constant SUBSUSEHARDSUBS. */
	public static final String SUBSUSEHARDSUBS = "subs.use.hardsubs";

	/** The Constant SUBSUSESOFTSUBS. */
	public static final String SUBSUSESOFTSUBS = "subs.use.softsubs";

	/** The Constant SUBSOUTLINE. */
	public static final String SUBSOUTLINE = "subs.outline";

	/** The Constant SUBSSHADOW. */
	public static final String SUBSSHADOW = "subs.shadow";

	/** The Constant SUBSBORDERSTYLE. */
	public static final String SUBSBORDERSTYLE = "subs.borderstyle";

	/** The Constant SUBSALIGNMENT. */
	public static final String SUBSALIGNMENT = "subs.alignment";

	/** The Constant SUBSCOLOR. */
	public static final String SUBSCOLOR = "subs.color";

	/** The Constant SUBSENCODING. */
	public static final String SUBSENCODING = "subs.encoding";

	/** The Constant SUBSALTERNATIVEENCODING. */
	public static final String SUBSALTERNATIVEENCODING = "subs.alternative.encoding";

	/** The Constant SUBSFONT. */
	public static final String SUBSFONT = "subs.font";

	/** The Constant SUBSUSEBOLD. */
	public static final String SUBSUSEBOLD = "subs.usebold";

	/** The Constant SUBSUSEITALIC. */
	public static final String SUBSUSEITALIC = "subs.useitalic";

	/** The Constant SUBSFONTSIZE. */
	public static final String SUBSFONTSIZE = "subs.fontsize";

	///////////////////// kvazaar /////////////////////
	public static final String KVAZAARPERIOD = "kvazaar.period";
	public static final String KVAZAAROWF = "kvazaar.owf";
	public static final String KVAZAARTILES = "kvazaar.tiles";
	public static final String KVAZAARWPP = "kvazaar.wpp";
	public static final String KVAZAARREF = "kvazaar.ref";
	public static final String KVAZAARSUBME = "kvazaar.subme";
	public static final String KVAZAARCMDS = "kvazaar.other.cmds";

	public static final String H265CMDS = "h265.other.cmds";

	public static final String OPENH264LOOPFILTER = "openh264.loopfilter";
	public static final String OPENH264MAXNAL = "openh264.max.nal.size";
	public static final String OPENH264SKIPFRAMES = "openh264.skip.frames";
	public static final String OPENH264CABAC = "openh264.cabac";
	public static final String OPENH264GOPSIZE = "openh264.gopsize";
	public static final String OPENH264MAXRATE = "openh264.max.rate";

	public static final String XVIDMPEGQUANT = "xvid.mpeg.quant";
	public static final String XVIDMEQUALITY = "xvid.me.quality";
	public static final String XVIDLUMIAQ = "xvid.lumi.aq";
	public static final String XVIDVARIANCEAQ = "xvid.variance.aq";
	public static final String XVIDGMC = "xvid.gmc";
	public static final String XVIDFLAGS = "xvid.flag";
	public static final String XVIDMEMETHOD = "xvid.me.method";
	public static final String XVIDMBD = "xvid.mbd";
	public static final String XVIDQMIN = "xvid.qmin";
	public static final String XVIDQMAX = "xvid.qmax";
	public static final String XVIDTRELLIS = "xvid.trellis";
	
	
	public static final String H264NVENCRCLOOKEAD = "h264nvenc.rc.lookahead";
	public static final String H264NVENCSURFACES = "h264nvenc.surfaces";
	public static final String H264NVENCDELAY = "h264nvenc.delay";
	public static final String H264NVENCNOSCENECUT = "h264nvenc.no.scenecut";
	public static final String H264NVENCFORCEDIDR = "h264nvenc.forced.idr";
	public static final String H264NVENCBADAPT = "h264nvenc.b.adapt";
	public static final String H264NVENCSPATIALAQ = "h264nvenc.spatial.aq ";
	public static final String H264NVENCTEMPORALAQ = "h264nvenc.temporal.aq";
	public static final String H264NVENCZEROLATENCY = "h264nvenc.zerolatency";
	public static final String H264NVENCNONREFQ = "h264nvenc.nonref.p";
	public static final String H264NVENCSTRICTGOP = "h264nvenc.strict.gop";
	public static final String H264NVENCAQSTRNGTH = "h264nvenc.aq.strength";
	public static final String H264NVENCCODER = "h264nvenc.coder";
	public static final String H264NVENCINITQPP = "h264nvenc.init.qpP";
	public static final String H264NVENCINITQPB = "h264nvenc.init.qpB";
	public static final String H264NVENCINITQPI = "h264nvenc.init.qpI";
	public static final String H264NVENCRATECONTROL = "h264nvenc.rate.control";
	//public static final String H264NVENC = "h264nvenc";
	
	public static final String H264VAAPICODER = "h264vaapi.coder";
	
	
	public static final String AV1CPUSED = "av1.cpu.used";
	public static final String AV1LAGINFRAMES = "av1.lag.in.frames";
	public static final String AV1STATICTRESH = "av1.static.thresh";
	public static final String AV1NOISESENS = "av1.noise.sensitivity";
	public static final String AV1TILES= "av1.tiles";
	public static final String AV1TILECOLUMNS= "av1.tile.columns";
	public static final String AV1TILEROWS= "av1.tile.rows";

}
