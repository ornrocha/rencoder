/*
 * Copyright 2014
 *
 * This is free software: you can redistribute it and/or modify it under the terms of the GNU Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 * 
 * This code is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Public License for more details.
 * 
 * You should have received a copy of the GNU Public License along with this code. If not, see
 * http://www.gnu.org/licenses/
 * 
 * Created by Orlando Rocha
 */
package pt.ornrocha.rencoder.mediafiles.files.containers.codecs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.configuration.PropertiesConfiguration;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.PixelFormatSVTAHEVC;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.SVTHEVCBitRateMode;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.SVTHEVCHielevel;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.SVTHEVCPresets;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.SVTHEVCProfile;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.SVTHEVCTune;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.SVTTier;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoContainers;
import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.helpers.props.fields.StaticFFmpegFields;
import pt.ornrocha.rencoder.helpers.props.fields.StaticVideoEncoderFields;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.DefaultEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;


/*
Encoder libsvt_hevc [SVT-HEVC(Scalable Video Technology for HEVC) encoder]:
    General capabilities: delay 
    Threading capabilities: none
    Supported pixel formats: yuv420p yuv420p10le yuv422p yuv422p10le yuv444p yuv444p10le
libsvt_hevc AVOptions:
  -asm_type          <boolean>    E..V....... Assembly instruction set type [0: C Only, 1: Auto] (default true)
  -aud               <boolean>    E..V....... Include Access Unit Delimiter (default false)
  -bl_mode           <boolean>    E..V....... Random Access Prediction Structure type setting (default false)
  -forced-idr        <int>        E..V....... If forcing keyframes, force them as IDR frames. (from -1 to INT_MAX) (default -1)
  -hielevel          <int>        E..V....... Hierarchical prediction levels setting (from 0 to 3) (default 3 level)
     flat            0            E..V.......
     1 level         1            E..V.......
     2 level         2            E..V.......
     3 level         3            E..V.......
  -la_depth          <int>        E..V....... Look ahead distance [0, 256] (from -1 to 256) (default -1)
  -level             <int>        E..V....... Set level (level_idc) (from 0 to 255) (default 0)
  -preset            <int>        E..V....... Encoding preset [0, 12] (from 0 to 12) (default 7)
  -profile           <int>        E..V....... Profile setting, Main Still Picture Profile not supported (from 1 to 4) (default 1)
  -qp                <int>        E..V....... QP value for intra frames (from 0 to 51) (default 32)
  -rc                <int>        E..V....... Bit rate control mode (from 0 to 1) (default cqp)
     cqp             0            E..V.......
     vbr             1            E..V.......
  -sc_detection      <boolean>    E..V....... Scene change detection (default true)
  -socket            <int>        E..V....... Target CPU socket to use.  -1 use all available (from -1 to 1) (default -1)
  -thread_count      <int>        E..V....... Number of threads [0: Auto, 96: Min] (from 0 to INT_MAX) (default 0)
  -tier              <int>        E..V....... Set tier (general_tier_flag) (from 0 to 1) (default main)
     main            0            E..V.......
     high            1            E..V.......
  -tune              <int>        E..V....... Quality tuning mode (from 0 to 2) (default oq)
     sq              0            E..V....... Visually optimized mode
     oq              1            E..V....... PSNR / SSIM optimized mode
     vmaf            2            E..V....... VMAF optimized mode
  -hdr               <int>        E..V....... High dynamic range input (HDR10) (from 0 to 1) (default 0)
  -umv               <boolean>    E..V....... Enables or disables unrestricted motion vectors (default true)
  -tile_row_cnt      <int>        E..V....... tile count in the row (from 1 to 16) (default 1)
  -tile_col_cnt      <int>        E..V....... tile count in the column (from 1 to 16) (default 1)
  -tile_slice_mode   <boolean>    E..V....... per slice per tile, only valid for multi-tile (default false)
  -pred_struct       <int>        E..V....... The prediction structure (from 0 to 2) (default 2)
  -vid_info          <boolean>    E..V....... Enables or disables sending a vui structure in the HEVC Elementary bitstream. (default false)

 */
/**
 * The Class VideoMPEG4EncodingInfoContainer.
 */
public class VideoSVTHEVCEncodingInfoContainer extends DefaultEncodingInfoContainer {

	private PixelFormatSVTAHEVC pixformat = PixelFormatSVTAHEVC.yuv420p;
	private boolean asmtype=true;
	private boolean aud=false;
	private boolean blmode=false;
	private int forcedidr=-1;
	private SVTHEVCHielevel hielevel= SVTHEVCHielevel.h3level;
	private int la_depth=-1;
	private int level=0;
	private SVTHEVCPresets preset=SVTHEVCPresets.P7;
	private SVTHEVCProfile profile = SVTHEVCProfile.P1;
	private int quantizer=32;
	private SVTHEVCBitRateMode ratemode= SVTHEVCBitRateMode.cqp;
	private boolean sc_detection=true;
	private int socket=-1;
	private int threadcount=0;
	private SVTTier tier=SVTTier.main;
	private SVTHEVCTune tune = SVTHEVCTune.oq;
	private int hdr=0;
	private boolean umv = true;
	private int tile_row_cnt=1;
	private int tile_col_cnt=1;
	private boolean tile_slice_mode = false;
	private int pred_struct = 2;
	private boolean vid_info=false;



	public VideoSVTHEVCEncodingInfoContainer() {
		super();
		setDefaultInfo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.MainVideoEncodingInfoContainer#setDefaultInfo( )
	 */
	@Override
	public void setDefaultInfo() {
		this.videocodec = VideoCodecs.SVTHEVC;
		this.videoContainer = VideoContainers.MP4;
		this.audiocodec = AudioCodecs.LCAAC;
		this.useAudioCBR = true;
		this.audioConstantBitrateValue = "128k";
		this.videoVBRQuality = new IndexedHashMap<String, Integer>() {

			private static final long serialVersionUID = 1L;

			{
				put(VideoCodecs.SVTHEVC.toString(), 32);
			}
		};

	}





	public PixelFormatSVTAHEVC getPixformat() {
		return pixformat;
	}

	public void setPixformat(PixelFormatSVTAHEVC pixformat) {
		this.pixformat = pixformat;
	}

	public boolean isAsmtype() {
		return asmtype;
	}

	public void setAsmtype(boolean asmtype) {
		this.asmtype = asmtype;
	}

	public boolean isAud() {
		return aud;
	}

	public void setAud(boolean aud) {
		this.aud = aud;
	}

	public boolean isBlmode() {
		return blmode;
	}

	public void setBlmode(boolean blmode) {
		this.blmode = blmode;
	}

	public int getForcedidr() {
		return forcedidr;
	}

	public void setForcedidr(int forcedidr) {
		this.forcedidr = forcedidr;
	}

	public SVTHEVCHielevel getHielevel() {
		return hielevel;
	}

	public void setHielevel(SVTHEVCHielevel hielevel) {
		this.hielevel = hielevel;
	}

	public int getLa_depth() {
		return la_depth;
	}

	public void setLa_depth(int la_depth) {
		this.la_depth = la_depth;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public SVTHEVCPresets getPreset() {
		return preset;
	}

	public void setPreset(SVTHEVCPresets preset) {
		this.preset = preset;
	}

	public SVTHEVCProfile getProfile() {
		return profile;
	}

	public void setProfile(SVTHEVCProfile profile) {
		this.profile = profile;
	}

	public int getQuantizer() {
		return quantizer;
	}

	public void setQuantizer(int quantizer) {
		this.quantizer = quantizer;
	}

	public SVTHEVCBitRateMode getRatemode() {
		return ratemode;
	}

	public void setRatemode(SVTHEVCBitRateMode ratemode) {
		this.ratemode = ratemode;
	}

	public boolean isSc_detection() {
		return sc_detection;
	}

	public void setSc_detection(boolean sc_detection) {
		this.sc_detection = sc_detection;
	}

	public int getSocket() {
		return socket;
	}

	public void setSocket(int socket) {
		this.socket = socket;
	}

	public int getThreadcount() {
		return threadcount;
	}

	public void setThreadcount(int threadcount) {
		this.threadcount = threadcount;
	}

	public SVTTier getTier() {
		return tier;
	}

	public void setTier(SVTTier tier) {
		this.tier = tier;
	}

	public SVTHEVCTune getTune() {
		return tune;
	}

	public void setTune(SVTHEVCTune tune) {
		this.tune = tune;
	}

	public int getHdr() {
		return hdr;
	}

	public void setHdr(int hdr) {
		this.hdr = hdr;
	}

	public boolean isUmv() {
		return umv;
	}

	public void setUmv(boolean umv) {
		this.umv = umv;
	}

	public int getTile_row_cnt() {
		return tile_row_cnt;
	}

	public void setTile_row_cnt(int tile_row_cnt) {
		this.tile_row_cnt = tile_row_cnt;
	}

	public int getTile_col_cnt() {
		return tile_col_cnt;
	}

	public void setTile_col_cnt(int tile_col_cnt) {
		this.tile_col_cnt = tile_col_cnt;
	}

	public boolean isTile_slice_mode() {
		return tile_slice_mode;
	}

	public void setTile_slice_mode(boolean tile_slice_mode) {
		this.tile_slice_mode = tile_slice_mode;
	}

	public int getPred_struct() {
		return pred_struct;
	}

	public void setPred_struct(int pred_struct) {
		this.pred_struct = pred_struct;
	}

	public boolean isVid_info() {
		return vid_info;
	}

	public void setVid_info(boolean vid_info) {
		this.vid_info = vid_info;
	}

	@Override
	public int getVideoVBRQualityValue() {
		return getQuantizer();
	}
	
	  @Override
	  public void setVideoVBRQuality(VideoCodecs codec, Integer videoVBRQuality) {
	    this.videoVBRQuality = new IndexedHashMap<String, Integer>();
	    this.videoVBRQuality.put(codec.toString(), videoVBRQuality);
	    this.quantizer=videoVBRQuality;
	  }

	@Override
	public DefaultEncodingInfoContainer clone() {
		VideoSVTHEVCEncodingInfoContainer clone = new VideoSVTHEVCEncodingInfoContainer();
		clone = (VideoSVTHEVCEncodingInfoContainer) this.copyCommoninfoofcontainer(clone);
		clone = (VideoSVTHEVCEncodingInfoContainer) this.copySpecificInfocontainer(clone);
		return clone;
	}

	@Override
	public IGeneralVideoEncInfoContainer copySpecificInfocontainer(
			IGeneralVideoEncInfoContainer cont) {
		((VideoSVTHEVCEncodingInfoContainer) cont).setPixformat(pixformat);
		((VideoSVTHEVCEncodingInfoContainer) cont).setAsmtype(asmtype);
		((VideoSVTHEVCEncodingInfoContainer) cont).setAud(aud);
		((VideoSVTHEVCEncodingInfoContainer) cont).setBlmode(blmode);
		((VideoSVTHEVCEncodingInfoContainer) cont).setForcedidr(forcedidr);
		((VideoSVTHEVCEncodingInfoContainer) cont).setHielevel(hielevel);
		((VideoSVTHEVCEncodingInfoContainer) cont).setLa_depth(la_depth);
		((VideoSVTHEVCEncodingInfoContainer) cont).setLevel(level);
		((VideoSVTHEVCEncodingInfoContainer) cont).setPreset(preset);
		((VideoSVTHEVCEncodingInfoContainer) cont).setProfile(profile);
		((VideoSVTHEVCEncodingInfoContainer) cont).setRatemode(ratemode);
		((VideoSVTHEVCEncodingInfoContainer) cont).setSc_detection(sc_detection);
		((VideoSVTHEVCEncodingInfoContainer) cont).setSocket(socket);
		((VideoSVTHEVCEncodingInfoContainer) cont).setThreadcount(threadcount);
		((VideoSVTHEVCEncodingInfoContainer) cont).setTier(tier);
		((VideoSVTHEVCEncodingInfoContainer) cont).setTune(tune);
		((VideoSVTHEVCEncodingInfoContainer) cont).setHdr(hdr);
		((VideoSVTHEVCEncodingInfoContainer) cont).setUmv(umv);
		((VideoSVTHEVCEncodingInfoContainer) cont).setTile_row_cnt(tile_row_cnt);
		((VideoSVTHEVCEncodingInfoContainer) cont).setTile_col_cnt(tile_col_cnt);
		((VideoSVTHEVCEncodingInfoContainer) cont).setTile_slice_mode(tile_slice_mode);
		((VideoSVTHEVCEncodingInfoContainer) cont).setPred_struct(pred_struct);
		((VideoSVTHEVCEncodingInfoContainer) cont).setVid_info(vid_info);


		return cont;
	}

	@Override
	protected ArrayList<VideoContainers> getSupportedFormats() {
		return Arrays.stream(VideoCodecs.SVTHEVC.supportsOutputFormats())
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	protected VideoContainers getpreferredFormat() {
		return VideoContainers.MKV;
	}

	@Override
	public void setConfigurations(PropertiesConfiguration props) {
		

		setPixformat(PixelFormatSVTAHEVC.getPixelFormatFromString(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOPIXELFORMAT)));
		setPreset(SVTHEVCPresets.getAVTPresetsFromString(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SVTHEVCPRESET)));
		setAsmtype(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SVTHEVCASMTYPE, true));
		setAud(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SVTHEVCAUD, false));
		setBlmode(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SVTHEVCBLMODE, false));
		setForcedidr(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SVTHEVCFORCEIDR, -1, Integer.MAX_VALUE, -1));
		setHielevel(SVTHEVCHielevel.getSVTHEVCHielevelFromString(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SVTHEVCHIELEVEL)));
		setLa_depth(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SVTHEVCLADEPTH, -1, 256, -1));
		setLevel(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SVTHEVCLEVEL, 0, 255, 0));
		setProfile(SVTHEVCProfile.getSVTHEVCProfileFromString(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SVTHEVCPROFILE)));
		setQuantizer(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOVBRVALUE, 0, 51, 32));
		setRatemode(SVTHEVCBitRateMode.getSVTHEVCRateModeFromString(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SVTHEVCRC)));
		setSc_detection(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SVTHEVCSCDECT, true));
		setSocket(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SVTHEVCSOCKET, -1, 1, -1));
		setThreadcount(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SVTHEVCTHREADCOUNT, 0, Integer.MAX_VALUE, 0));
		setTier(SVTTier.getSVTHEVCHielevelFromString(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SVTHEVCTIER)));
		setTune(SVTHEVCTune.getSVTHEVCTuneFromString(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SVTHEVCTUNE)));
		setHdr(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SVTHEVCHDR, 0, 1, 0));
		setUmv(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SVTHEVCUMV, true));
		setTile_row_cnt(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SVTHEVCTILEROW, 1, 16, 1));
		setTile_col_cnt(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SVTHEVCTILECOLUMN, 1, 16, 1));
		setTile_slice_mode(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SVTHEVCSLICEMODE, false));
		setPred_struct(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SVTHEVCPREDSTRUCT, 0,2, 2));
		setTile_slice_mode(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.SVTHEVCVIDINFO, false));


        setVideoConfigurations(props);
		setAudioConfigurations(props);
		setSubtitleConfigurations(props);
	}

	@Override
	protected void saveSpecificCodecProperties(PropertiesConfiguration prop) {

		prop.setProperty(StaticVideoEncoderFields.VIDEOPIXELFORMAT, pixformat.toString());
		prop.setProperty(StaticVideoEncoderFields.SVTHEVCPRESET, preset.toString());
		prop.setProperty(StaticVideoEncoderFields.SVTHEVCASMTYPE, String.valueOf(asmtype));
		prop.setProperty(StaticVideoEncoderFields.SVTHEVCAUD, String.valueOf(aud));
		prop.setProperty(StaticVideoEncoderFields.SVTHEVCBLMODE, String.valueOf(blmode));
		prop.setProperty(StaticVideoEncoderFields.SVTHEVCFORCEIDR, String.valueOf(forcedidr));
		prop.setProperty(StaticVideoEncoderFields.SVTHEVCHIELEVEL, hielevel.toString());
		prop.setProperty(StaticVideoEncoderFields.SVTHEVCLADEPTH, String.valueOf(la_depth));
		prop.setProperty(StaticVideoEncoderFields.SVTHEVCLEVEL, String.valueOf(level));
		prop.setProperty(StaticVideoEncoderFields.SVTHEVCPROFILE, profile.toString());
		//prop.setProperty(StaticVideoEncoderFields.SVTHEVCQP, String.valueOf(quantizer));
		prop.setProperty(StaticVideoEncoderFields.SVTHEVCRC, ratemode.toString());
		prop.setProperty(StaticVideoEncoderFields.SVTHEVCSCDECT, String.valueOf(sc_detection));
		prop.setProperty(StaticVideoEncoderFields.SVTHEVCSOCKET, String.valueOf(socket));
		prop.setProperty(StaticVideoEncoderFields.SVTHEVCTHREADCOUNT, String.valueOf(threadcount));
		prop.setProperty(StaticVideoEncoderFields.SVTHEVCTIER, tier.toString());
		prop.setProperty(StaticVideoEncoderFields.SVTHEVCTUNE, tune.toString());
		prop.setProperty(StaticVideoEncoderFields.SVTHEVCHDR, String.valueOf(hdr));
		prop.setProperty(StaticVideoEncoderFields.SVTHEVCUMV, String.valueOf(umv));
		prop.setProperty(StaticVideoEncoderFields.SVTHEVCTILEROW, String.valueOf(tile_row_cnt));
		prop.setProperty(StaticVideoEncoderFields.SVTHEVCTILECOLUMN, String.valueOf(tile_col_cnt));
		prop.setProperty(StaticVideoEncoderFields.SVTHEVCSLICEMODE, String.valueOf(tile_slice_mode));
		prop.setProperty(StaticVideoEncoderFields.SVTHEVCPREDSTRUCT, String.valueOf(pred_struct));
		prop.setProperty(StaticVideoEncoderFields.SVTHEVCVIDINFO, String.valueOf(vid_info));

	}

	@Override
	public ArrayList<String> getSpecificVideoEncoderParameters(ArrayList<String> appendcmds) {
		ArrayList<String> hevcparam = new ArrayList<>();


		hevcparam.add(StaticFFmpegFields.pixelformat);
		hevcparam.add(getPixformat().toString());

		if(!isAsmtype()){
			hevcparam.add(StaticFFmpegFields.STVHEVCASMTYPE);
			hevcparam.add(String.valueOf(asmtype));
		}

		if(isAud()){
			hevcparam.add(StaticFFmpegFields.STVHEVCAUD);
			hevcparam.add(String.valueOf(aud));
		}

		if(isBlmode()){
			hevcparam.add(StaticFFmpegFields.STVHEVCBLMODE);
			hevcparam.add(String.valueOf(blmode));
		}

		if (getForcedidr() > -1) {
			hevcparam.add(StaticFFmpegFields.STVHEVCFORCEDIDR);
			hevcparam.add(String.valueOf(forcedidr));
		}


		if (!getHielevel().equals(SVTHEVCHielevel.h3level)) {
			hevcparam.add(StaticFFmpegFields.STVHEVCHIELEVEL);
			hevcparam.add(hielevel.getValue());
		}


		if (getLa_depth() > -1 && getLa_depth()<257) {
			hevcparam.add(StaticFFmpegFields.STVHEVCLADEPTH);
			hevcparam.add(String.valueOf(la_depth));
		}

		if (getLevel() > 0 && getLa_depth()<256) {
			hevcparam.add(StaticFFmpegFields.STVHEVCLEVEL);
			hevcparam.add(String.valueOf(level));
		}

		if (!getPreset().equals(SVTHEVCPresets.P7)) {
			hevcparam.add(StaticFFmpegFields.STVHEVCPRESET);
			hevcparam.add(preset.getValue());
		}

		if (!getProfile().equals(SVTHEVCProfile.P1)) {
			hevcparam.add(StaticFFmpegFields.STVHEVCPROFILE);
			hevcparam.add(profile.getValue());
		}

		/*if (getQuantizer()!=32 && getQuantizer()>-1 && getQuantizer()<52) {
         hevcparam.add(StaticFFmpegFields.STVHEVCQP);
         hevcparam.add(String.valueOf(quantizer));
       }*/

		if (!getRatemode().equals(SVTHEVCBitRateMode.cqp)) {
			hevcparam.add(StaticFFmpegFields.STVHEVCRC);
			hevcparam.add(ratemode.getValue());
		}

		if(!isSc_detection()){
			hevcparam.add(StaticFFmpegFields.STVHEVCSCDETECT);
			hevcparam.add(String.valueOf(sc_detection));
		}

		if (getSocket() > -1 && getSocket()<2) {
			hevcparam.add(StaticFFmpegFields.STVHEVCSOCKET);
			hevcparam.add(String.valueOf(socket));
		}

		if (getThreadcount() > 0) {
			hevcparam.add(StaticFFmpegFields.STVHEVCTHREAD);
			hevcparam.add(String.valueOf(threadcount));
		}

		if (!getTier().equals(SVTTier.main)) {
			hevcparam.add(StaticFFmpegFields.STVHEVCTIER);
			hevcparam.add(tier.getValue());
		}


		if (!getTune().equals(SVTHEVCTune.oq)) {
			hevcparam.add(StaticFFmpegFields.STVHEVCTUNE);
			hevcparam.add(tune.getValue());
		}


		if (getHdr()==1) {
			hevcparam.add(StaticFFmpegFields.STVHEVCHDR);
			hevcparam.add(String.valueOf(hdr));
		}

		if(!isUmv()){
			hevcparam.add(StaticFFmpegFields.STVHEVCUMV);
			hevcparam.add(String.valueOf(umv));
		}

		if (getTile_row_cnt() > 1 && getTile_row_cnt() < 17) {
			hevcparam.add(StaticFFmpegFields.STVHEVCTROWS);
			hevcparam.add(String.valueOf(tile_row_cnt));
		}

		if (getTile_col_cnt() > 1 && getTile_col_cnt() < 17) {
			hevcparam.add(StaticFFmpegFields.STVHEVCTCOLUMNS);
			hevcparam.add(String.valueOf(tile_col_cnt));
		}

		if(isTile_slice_mode()){
			hevcparam.add(StaticFFmpegFields.STVHEVCTSLICEMODE);
			hevcparam.add(String.valueOf(tile_slice_mode));
		}

		if (getPred_struct() >0 && getPred_struct() < 3 && getPred_struct()!=2) {
			hevcparam.add(StaticFFmpegFields.STVHEVCPREDSTRUCT);
			hevcparam.add(String.valueOf(pred_struct));
		}

		if (isVid_info()) {
			hevcparam.add(StaticFFmpegFields.STVHEVCVIDINFO);
			hevcparam.add(String.valueOf(vid_info));
		}

		return hevcparam;
	}

	@Override
	public boolean useSpecificVideoEncodingParameters() {
		return true;
	}

	@Override
	public String getContainerName() {
		return "SVT-HEVC";
	}

}
