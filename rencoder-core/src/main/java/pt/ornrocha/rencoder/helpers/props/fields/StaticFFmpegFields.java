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
 * The Class FFmpegParameters.
 */
public class StaticFFmpegFields {

	// Inputs
	/** The Constant input. */
	public static final String input = "-i";

	// ### video

	/** The Constant encoder_video_codec. */
	public static final String encodevideocodec = "-c:v";

	/** The Constant video_codec. */
	public static final String video_codec = "-vcodec";

	/** The Constant force_video_tag. */
	public static final String force_video_tag = "-vtag";

	/** The Constant force_video_format. */
	public static final String force_video_format = "-f";

	/** The Constant video_bitrate. */
	public static final String video_bitrate = "-vb";

	/** The Constant framerate. */
	public static final String framerate = "-r";

	/** The Constant video_resolution. */
	public static final String video_resolution = "-s";

	/** The Constant video_quality. */
	public static final String video_quality = "-qscale:v";

	/** The Constant video_qualityshortterm. */
	public static final String video_qualityshortterm = "-q:v";

	/** The Constant deinterlace. */
	public static final String deinterlace = "-deinterlace";

	/** The Constant max_bitrate. */
	public static final String max_bitrate = "-maxrate";

	/** The Constant min_bitrate. */
	public static final String min_bitrate = "-minrate";

	/** The Constant max_quantizer_scale. */
	public static final String max_quantizer_scale = "-qmax";

	/** The Constant min_quantizer_scale. */
	public static final String min_quantizer_scale = "-qmin";

	/** The Constant video_aspect. */
	public static final String video_aspect = "-aspect";

	/** The Constant cputhreads. */
	public static final String cputhreads = "-threads";

	public static final String LEVEL = "-level";

	// ### audio
	/** The Constant encodeaudiocodec. */
	public static final String encodeaudiocodec = "-c:a";

	/** The Constant audiocodec. */
	public static final String audiocodec = "-acodec";

	/** The Constant forceaudiotag. */
	public static final String forceaudiotag = "-atag";

	/** The Constant audiocbr. */
	public static final String audiocbr = "-ab";

	/** The Constant audiovbr. */
	public static final String audiovbr = "-q:a";

	/** The Constant audiofrequency. */
	public static final String audiofrequency = "-ar";

	/** The Constant audiochannels. */
	public static final String audiochannels = "-ac";

	/** The Constant disableaudio. */
	public static final String disableaudio = "-an";

	/** The Constant audiosync. */
	public static final String audiosync = "-async";

	/** The Constant copy. */
	public static final String copy = "copy";

	/** The Constant passnumber. */
	public static final String passnumber = "-pass";

	/** The Constant videopasslog. */
	public static final String videopasslog = "-passlogfile";

	// ## aac parameters

	/** The Constant strict. */
	public static final String strict = "-strict";

	// Use hardware acceleration to decode ; auto;vda; vdpau; dxva2
	/** The Constant decodehardwareacceleration. */
	public static final String decodehardwareacceleration = "-hwaccel";

	// parameters

	// codecs video

	/** The Constant x264preset. */
	public static final String PRESET = "-preset";

	/** The Constant x264variablebitrate. */
	public static final String Hvariablebitrate = "-crf";

	public static final String h264nvencvariablebitrate = "-cq";

	/** The Constant x264profile. */
	public static final String PROFILE = "-profile:v";

	/** The Constant x264constquant. */
	public static final String x264constquant = "-qp";

	/** The Constant x264fastfirstpass. */
	public static final String x264fastfirstpass = "-fastfirstpass";

	public static final String h264nvencratecontrol = "-rc";

	///////// VAAPI ///////////////

	public static final String VAAPI = "vaapi";

	public static final String VAAPIDEVICEFLAG = "-vaapi_device";
	public static final String VAAPIDEVICELINUX = "/dev/dri/renderD128";
	public static final String VAAPIDEVICE = ":0";

	public static final String DECODERHWACCEL = "-hwaccel";
	public static final String DECODERHWACCELDEVICE = "-hwaccel_device";
	public static final String HWACCELAUTO = "auto";
	public static final String DECODERHWACCELOUTFORMAT = "-hwaccel_output_format";
    
	public static final String CUDADEFAULTDECODEFILTER = "hwdownload,format=nv12";
	public static final String VAAPIDEFAULTDECODEFILTER = "format=nv12|vaapi,hwupload";
	//public static final String VAAPIDEFAULTDECODEFILTER ="scale_vaapi,hwmap=mode=read+write+direct,format=nv12,hwmap";

	public static final String CUVID = "cuvid";
	
	public static final String MPEG4CUVID = "mpeg4_cuvid";
	public static final String MPEG2CUVID = "mpeg2_cuvid";
	public static final String HEVCCUVID = "hevc_cuvid";
	
	public static final String CUDA = "cuda";
	public static final String NVDEC = "nvdec";
	public static final String VSYNC = "vsync";
	public static final String VP8CUVID = "vp8_cuvid";
	public static final String VP9CUVID = "vp9_cuvid";

	/** The Constant pixelformat. */
	public static final String pixelformat = "-pix_fmt";

	/** The Constant vp8profile. */
	public static final String vp8profile = "-deadline";

	/** The Constant vp8cpuused. */
	public static final String vp8cpuused = "-cpu-used";

	/** The Constant vp8minquant. */
	public static final String vp8minquant = "-qmin";

	/** The Constant vp8maxquant. */
	public static final String vp8maxquant = "-qmax";

	/** The Constant vp8variablebitrate. */
	public static final String vpxvariablebitrate = "-crf";

	/** The Constant mpeg2. */
	public static final String mpeg2 = "mpeg2video";

	/** The Constant mpeg2bframes. */
	public static final String mpeg2bframes = "-bf";

	/** The Constant mpeg2gopsize. */
	public static final String mpeg2gopsize = "-g";

	/** The Constant mpeg2trellis. */
	public static final String mpeg2trellis = "-trellis";

	/** The Constant mpeg2pelmecmp. */
	public static final String mpeg2pelmecmp = "-cmp";

	/** The Constant mpeg2pelmesubcmp. */
	public static final String mpeg2pelmesubcmp = "-subcmp";

	/** The Constant overwrite_output_file. */
	public static final String overwrite_output_file = "-y";

	// ### aspect
	/** The Constant A16_9. */
	public static final String A16_9 = "16:9";

	/** The Constant A4_3. */
	public static final String A4_3 = "4:3";

	public static final String x265params = "-x265-params";
	public static final String x265CRF = "crf=";
	public static final String x265tune = "-tune";

	// codecs video
	/** The Constant xvidencoder. */
	public static final String xvidencoder = "xvid.encoder";

	/** The Constant h264encoder. */
	public static final String H264encoder = "h264.encoder";

	/** The Constant vpxencoder. */
	public static final String vpxencoder = "vpx.encoder";

	// subtitles

	/** The Constant SUBCHARENCODING. */
	public static final String SUBCHARENCODING = "-sub_charenc";

	/** The Constant SUBFFMPEGCMD. */
	public static final String FILTERFFMPEGCMD = "-vf";

	public static String FILTERQUOTETAG = "\"";

	/** The Constant SUBCODEC. */
	public static final String SUBCODEC = "-scodec";

	/** The Constant SUBMETADATAORDER. */
	public static final String SUBMETADATAORDER = "-metadata:s:s:";

	/** The Constant SUBMP4ENCO. */
	public static final String SUBMP4ENCO = "mov_text";

	/** The Constant SUBLANGUAGE. */
	public static final String SUBLANGUAGE = "language=";

	/** The filepath audio codec settings. */
	// public static String FILEPATHAUDIOCODECSETTINGS="settings/audiocodec.conf";

	/** The filepath video codec settings. */
	// public static String FILEPATHVIDEOCODECSETTINGS="settings/videocodec.conf";

	public static String H265ACTIVEKEY = "h265.is.active";

	public static String H264NVENCACTIVEKEY = "h264.nvenc";

	public static String H264CUDADECODING = "h264.hwaccel";

	public static String H264VAAPIACTIVEKEY = "h264.vaapi";

	public static String HEVCNVENCACTIVEKEY = "hevc.nvenc";

	public static String CONCAT = "concat";

	public static String CTAG = "-c";

	public static String TIMESTARTAG = "-ss";

	public static String VFRAMESTAG = "-vframes";

	//////////////////////////////// video codecs
	//////////////////////////////// /////////////////////////////////////
	/** The Constant mpeg4. */
	public static final String Mpeg4 = "mpeg4";

	/** The Constant xvidtag. */
	public static final String xvidtag = "xvid";
	public static final String XVID = "libxvid";

	/** The Constant mpeg4tag. */
	public static final String mpeg4tag = "FMP4";

	/** The Constant x264. */
	public static final String X264 = "libx264";
	public static final String H264 = X264;
	public static final String OPENH264 = "libopenh264";

	public static final String H264nvenc = "h264_nvenc";

	public static final String x265 = "libx265";
	public static final String H265 = x265;

	public static final String AOMAV1 = "libaom-av1";
	
	public static final String SVTAV1 = "libsvtav1";
	
	public static final String SVTHEVC = "libsvt_hevc";

	public static final String H264vaapi = "h264_vaapi";
	public static final String H264qsv = "h264_qsv";
	public static final String HEVCvaapi = "hevc_vaapi";
	public static final String HEVCqsv = "hevc_qsv";

	public static final String Hevcnvenc = "hevc_nvenc";
	public static final String Kvazaar = "libkvazaar";

	public static final String Vp8 = "libvpx";

	/** The Constant vp9. */
	public static final String Vp9 = "libvpx-vp9";

	public static final String Theora = "libtheora";

	//////////////////////////////// Audio Settings
	//////////////////////////////// ////////////////////////////////////

	/** The Constant mp3codec. */
//	public static final String mp3codec ="mp3.encoder";
//	
//	/** The Constant vorbiscodec. */
//	public static final String vorbiscodec ="vorbis.encoder";
//	
//	/** The Constant aaccodec. */
//	public static final String aaccodec ="aac.encoder";
//	
//	/** The Constant ac3codec. */
//	public static final String ac3codec ="ac3.encoder";
//	
//	/** The Constant mp2codec. */
//	public static final String mp2codec ="mp2.encoder";

	public static final String OPUS = "libopus";

	public static final String VORBIS = "libvorbis";

	public static final String LAMEMP3 = "libmp3lame";

	public static final String LAMEMP2 = "libtwolame";

	public static final String SHINEMP3 = "libshine";

	public static final String MP2 = "mp2";

	public static final String AAC = "aac";

	public static final String FDKAAC = "libfdk_aac";

	public static final String AC3 = "ac3";

	public static final String EAC3 = "eac3";

	public static final String TRUEHD = "truehd";

	public static final String SPEEX = "libspeex";
	
	public static final String KVAZAARPARAMETERS = "-kvazaar-params";

	/////////////////////////// FILES MAPPING ///////////////////////
	/** The Constant MAPFILE. */
	public static final String MAPFILE = "-map";
	public static final String FIRSTAUDIOFILE = "0:a";
	
	
	
	////////////////////////// openh264 ///////////////////////////////
	public static final String OPENH264SLICE = "-slice_mode";
	public static final String OPENH264NUMBERSLICE = "-slices";
	public static final String OPENH264LOOPFILTER= "-loopfilter";
	public static final String OPENH264MAXNAL = "-max_nal_size";
	public static final String OPENH264SKIPFRAMES = "-allow_skip_frames";
	public static final String OPENH264CABAC = "-cabac";
	public static final String OPENH264GOPSIZE = "-g";
	public static final String OPENH264MAXRATE = "-maxrate";
	
	
	public static final String H264NVENCRCLOOKEAD = "-rc-lookahead";
	public static final String H264NVENCSURFACES = "-surfaces";
	public static final String H264NVENCDELAY = "-delay";
	public static final String H264NVENCNOSCENECUT = "-no-scenecut";
	public static final String H264NVENCFORCEDIDR = "-forced-idr";
	public static final String H264NVENCBADAPT = "-b_adapt";
	public static final String H264NVENCSPATIALAQ = "-spatial-aq";
	public static final String H264NVENCTEMPORALAQ = "-temporal-aq";
	public static final String H264NVENCZEROLATENCY = "-zerolatency";
	public static final String H264NVENCNONREFQ = "-nonref_p";
	public static final String H264NVENCSTRICTGOP = "-strict_gop";
	public static final String H264NVENCAQSTRNGTH = "-aq-strength";
	public static final String H264NVENCCODER = "-coder";
	public static final String H264NVENCINITQPP = "-init_qpP";
	public static final String H264NVENCINITQPB = "-init_qpB";
	public static final String H264NVENCINITQPI = "-init_qpI";
	public static final String H264NVENCRATECONTROL = "-rc";
	
        ////////////////////////// AV1 ///////////////////////////////


	public static final String AV1CPUUSED = "-cpu-used";
	public static final String AV1LAGINFRAMES = "-lag-in-frames";
	public static final String AV1STATICTHRESH = "-static-thresh";
	public static final String AV1NOISESENS = "-noise-sensitivity";
	public static final String AV1TILES = "-tiles";
	public static final String AV1TILECOLUMNS = "-tile-columns";
	public static final String AV1TILEROWS = "-tile-rows";
	
     ///////////////////////////// STV-AV1 ///////////////////////////////
	public static final String STVAV1HIELEVEL = "-hielevel";
	public static final String STVAV1LADEPTH = "-la_depth";
	public static final String STVAV1TIER = "-tier";
	public static final String STVAV1RC = "-rc";
	public static final String STVAV1QP = "-qp";
	public static final String STVAV1SCDETECT = "-sc_detection";
	public static final String STVAV1TCOLUMNS = "-tile_columns";
	public static final String STVAV1TROWS = "-tile_rows";
	
    ///////////////////////////// STV-HEVC ///////////////////////////////
	
	public static final String STVHEVCASMTYPE = "-asm_type";
	public static final String STVHEVCAUD = "-aud";
	public static final String STVHEVCBLMODE = "-bl_mode";
	public static final String STVHEVCFORCEDIDR = "-forced-idr";
	public static final String STVHEVCHIELEVEL = "-hielevel";
	public static final String STVHEVCLADEPTH = "-la_depth";
	public static final String STVHEVCLEVEL = "-level";
	public static final String STVHEVCPRESET = "-preset";
	public static final String STVHEVCPROFILE = "-profile";
	public static final String STVHEVCQP = "-qp";
	public static final String STVHEVCRC = "-rc";
	public static final String STVHEVCSCDETECT = "-sc_detection";
	public static final String STVHEVCSOCKET = "-socket";
	public static final String STVHEVCTHREAD = "-thread_count";
	public static final String STVHEVCTIER = "-tier";
	public static final String STVHEVCTUNE = "-tune";
	public static final String STVHEVCHDR = "-hdr";
	public static final String STVHEVCUMV = "-umv";
	public static final String STVHEVCTROWS = "-tile_row_cnt";
	public static final String STVHEVCTCOLUMNS = "-tile_col_cnt";
	public static final String STVHEVCTSLICEMODE = "-tile_slice_mode";
	public static final String STVHEVCPREDSTRUCT = "-pred_struct";
	public static final String STVHEVCVIDINFO = "-vid_info";
	
 
	
	
	///////////////////////////// QSV ///////////////////////////////
	
	public static final String QSV = "qsv";
	
	
	
}
