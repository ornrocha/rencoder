package pt.ornrocha.rencoder.mediafiles.files.containers.extrainfocontainer;

import java.util.ArrayList;

import org.apache.commons.configuration.PropertiesConfiguration;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.H264NvencCoder;
import pt.ornrocha.rencoder.helpers.props.fields.StaticFFmpegFields;
import pt.ornrocha.rencoder.helpers.props.fields.StaticVideoEncoderFields;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;

public class HevcNvencExtraInfoContainer implements IExtraInfoContainer {
    
//    Threading capabilities: none
//    Supported pixel formats: yuv420p nv12 p010le yuv444p p016le yuv444p16le bgr0 rgb0 cuda d3d11
//hevc_nvenc AVOptions:
//  -preset            <int>        E..V..... Set the encoding preset (from 0 to 11) (default medium)
//     default                      E..V..... 
//     slow                         E..V..... hq 2 passes
//     medium                       E..V..... hq 1 pass
//     fast                         E..V..... hp 1 pass
//     hp                           E..V..... 
//     hq                           E..V..... 
//     bd                           E..V..... 
//     ll                           E..V..... low latency
//     llhq                         E..V..... low latency hq
//     llhp                         E..V..... low latency hp
//     lossless                     E..V..... lossless
//     losslesshp                   E..V..... lossless hp
//  -profile           <int>        E..V..... Set the encoding profile (from 0 to 4) (default main)
//     main                         E..V..... 
//     main10                       E..V..... 
//     rext                         E..V..... 
//  -level             <int>        E..V..... Set the encoding level restriction (from 0 to 186) (default auto)
//     auto                         E..V..... 
//     1                            E..V..... 
//     1.0                          E..V..... 
//     2                            E..V..... 
//     2.0                          E..V..... 
//     2.1                          E..V..... 
//     3                            E..V..... 
//     3.0                          E..V..... 
//     3.1                          E..V..... 
//     4                            E..V..... 
//     4.0                          E..V..... 
//     4.1                          E..V..... 
//     5                            E..V..... 
//     5.0                          E..V..... 
//     5.1                          E..V..... 
//     5.2                          E..V..... 
//     6                            E..V..... 
//     6.0                          E..V..... 
//     6.1                          E..V..... 
//     6.2                          E..V..... 
//  -tier              <int>        E..V..... Set the encoding tier (from 0 to 1) (default main)
//     main                         E..V..... 
//     high                         E..V..... 
//  -rc                <int>        E..V..... Override the preset rate-control (from -1 to INT_MAX) (default -1)
//     constqp                      E..V..... Constant QP mode
//     vbr                          E..V..... Variable bitrate mode
//     cbr                          E..V..... Constant bitrate mode
//     vbr_minqp                    E..V..... Variable bitrate mode with MinQP (deprecated)
//     ll_2pass_quality              E..V..... Multi-pass optimized for image quality (deprecated)
//     ll_2pass_size                E..V..... Multi-pass optimized for constant frame size (deprecated)
//     vbr_2pass                    E..V..... Multi-pass variable bitrate mode (deprecated)
//     cbr_ld_hq                    E..V..... Constant bitrate low delay high quality mode
//     cbr_hq                       E..V..... Constant bitrate high quality mode
//     vbr_hq                       E..V..... Variable bitrate high quality mode
//  -rc-lookahead      <int>        E..V..... Number of frames to look ahead for rate-control (from 0 to INT_MAX) (default 0)
//  -surfaces          <int>        E..V..... Number of concurrent surfaces (from 0 to 64) (default 0)
//  -cbr               <boolean>    E..V..... Use cbr encoding mode (default false)
//  -2pass             <boolean>    E..V..... Use 2pass encoding mode (default auto)
//  -gpu               <int>        E..V..... Selects which NVENC capable GPU to use. First GPU is 0, second is 1, and so on. (from -2 to INT_MAX) (default any)
//     any                          E..V..... Pick the first device available
//     list                         E..V..... List the available devices
//  -delay             <int>        E..V..... Delay frame output by the given amount of frames (from 0 to INT_MAX) (default INT_MAX)
//  -no-scenecut       <boolean>    E..V..... When lookahead is enabled, set this to 1 to disable adaptive I-frame insertion at scene cuts (default false)
//  -forced-idr        <boolean>    E..V..... If forcing keyframes, force them as IDR frames. (default false)
//  -spatial_aq        <boolean>    E..V..... set to 1 to enable Spatial AQ (default false)
//  -temporal_aq       <boolean>    E..V..... set to 1 to enable Temporal AQ (default false)
//  -zerolatency       <boolean>    E..V..... Set 1 to indicate zero latency operation (no reordering delay) (default false)
//  -nonref_p          <boolean>    E..V..... Set this to 1 to enable automatic insertion of non-reference P-frames (default false)
//  -strict_gop        <boolean>    E..V..... Set 1 to minimize GOP-to-GOP rate fluctuations (default false)
//  -aq-strength       <int>        E..V..... When Spatial AQ is enabled, this field is used to specify AQ strength. AQ strength scale is from 1 (low) - 15 (aggressive) (from 1 to 15) (default 8)
//  -cq                <float>      E..V..... Set target quality level (0 to 51, 0 means automatic) for constant quality mode in VBR rate control (from 0 to 51) (default 0)
//  -aud               <boolean>    E..V..... Use access unit delimiters (default false)
//  -bluray-compat     <boolean>    E..V..... Bluray compatibility workarounds (default false)
//  -init_qpP          <int>        E..V..... Initial QP value for P frame (from -1 to 51) (default -1)
//  -init_qpB          <int>        E..V..... Initial QP value for B frame (from -1 to 51) (default -1)
//  -init_qpI          <int>        E..V..... Initial QP value for I frame (from -1 to 51) (default -1)
//  -qp                <int>        E..V..... Constant quantization parameter rate control method (from -1 to 51) (default -1)
//  -weighted_pred     <int>        E..V..... Set 1 to enable weighted prediction (from 0 to 1) (default 0)
//  -b_ref_mode        <int>        E..V..... Use B frames as references (from 0 to 2) (default disabled)
//     disabled                     E..V..... B frames will not be used for reference
//     each                         E..V..... Each B frame will be used for reference
//     middle                       E..V..... Only (number of B frames)/2 will be used for reference


    private int tier = 0;
	private int rclookahead = 0;
	private int surfaces = 0;
	private int delay = 0;

	// When Spatial AQ is enabled, this field is used to specify AQ strength. AQ
	// strength scale is from 1 (low) - 15 (aggressive) (from 1 to 15) (default 8)
	private int aqstrength = 8;
	// When lookahead is enabled, set this to 1 to disable adaptive I-frame
	// insertion at scene cuts (default false)
	private boolean noscenecut = false;

	// If forcing keyframes, force them as IDR frames. (default false)
	private boolean forcedidr = false;

//	private boolean badapt = true;

	// set to 1 to enable Spatial AQ (default false)
	private boolean spatialaq = false;

	// set to 1 to enable Temporal AQ (default false)
	private boolean temporalaq = false;

	// Set 1 to indicate zero latency operation (no reordering delay) (default
	// false)
	private boolean zerolatency = false;

	// Set this to 1 to enable automatic insertion of non-reference P-frames
	// (default false)
	private boolean nonrefp = false;

	// Set 1 to minimize GOP-to-GOP rate fluctuations (default false)
	private boolean strictgop = false;

	// Initial QP value for P frame (from -1 to 51) (default -1)
	private int init_qpP = -1;

	// Initial QP value for B frame (from -1 to 51) (default -1)
	private int init_qpB = -1;

	// Initial QP value for I frame (from -1 to 51) (default -1)
	private int init_qpI = -1;



	
	
	public int getTier() {
	    return tier;
	}

	public void setTier(int tier) {
	    this.tier = tier;
	}

	public int getRclookahead() {
		return rclookahead;
	}

	public void setRclookahead(int rclookahead) {
		this.rclookahead = rclookahead;
	}

	public int getSurfaces() {
		return surfaces;
	}

	public void setSurfaces(int surfaces) {
		this.surfaces = surfaces;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public int getAqstrength() {
		return aqstrength;
	}

	public void setAqstrength(int aqstrength) {
		this.aqstrength = aqstrength;
	}

	public boolean isNoscenecut() {
		return noscenecut;
	}

	public void setNoscenecut(boolean noscenecut) {
		this.noscenecut = noscenecut;
	}

	public boolean isForcedidr() {
		return forcedidr;
	}

	public void setForcedidr(boolean forcedidr) {
		this.forcedidr = forcedidr;
	}

	public boolean isSpatialaq() {
		return spatialaq;
	}

	public void setSpatialaq(boolean spatialaq) {
		this.spatialaq = spatialaq;
	}

	public boolean isTemporalaq() {
		return temporalaq;
	}

	public void setTemporalaq(boolean temporalaq) {
		this.temporalaq = temporalaq;
	}

	public boolean isZerolatency() {
		return zerolatency;
	}

	public void setZerolatency(boolean zerolatency) {
		this.zerolatency = zerolatency;
	}

	public boolean isNonrefp() {
		return nonrefp;
	}

	public void setNonrefp(boolean nonrefp) {
		this.nonrefp = nonrefp;
	}

	public boolean isStrictgop() {
		return strictgop;
	}

	public void setStrictgop(boolean strictgop) {
		this.strictgop = strictgop;
	}

	public int getInit_qpP() {
		return init_qpP;
	}

	public void setInit_qpP(int init_qpP) {
		this.init_qpP = init_qpP;
	}

	public int getInit_qpB() {
		return init_qpB;
	}

	public void setInit_qpB(int init_qpB) {
		this.init_qpB = init_qpB;
	}

	public int getInit_qpI() {
		return init_qpI;
	}

	public void setInit_qpI(int init_qpI) {
		this.init_qpI = init_qpI;
	}

	public HevcNvencExtraInfoContainer() {
	}

	private HevcNvencExtraInfoContainer(int tier, int rclookahead, int surfaces, int delay, int aqstrength, boolean noscenecut,
			boolean forcedidr, boolean spatialaq, boolean temporalaq, boolean zerolatency,
			boolean nonrefp, boolean strictgop, int init_qpP, int init_qpB, int init_qpI) {
		super();
		this.tier = tier;
		this.rclookahead = rclookahead;
		this.surfaces = surfaces;
		this.delay = delay;
		this.aqstrength = aqstrength;
		this.noscenecut = noscenecut;
		this.forcedidr = forcedidr;
		this.spatialaq = spatialaq;
		this.temporalaq = temporalaq;
		this.zerolatency = zerolatency;
		this.nonrefp = nonrefp;
		this.strictgop = strictgop;
		this.init_qpP = init_qpP;
		this.init_qpB = init_qpB;
		this.init_qpI = init_qpI;
	}

	@Override
	public IExtraInfoContainer clone() {
		return new HevcNvencExtraInfoContainer(tier,rclookahead, surfaces, delay, aqstrength, noscenecut, forcedidr,
				spatialaq, temporalaq, zerolatency, nonrefp, strictgop, init_qpP, init_qpB, init_qpI);
	}

	public static HevcNvencExtraInfoContainer getDefaulPropertiesExtraInfoContainer() {
		return new HevcNvencExtraInfoContainer();
	}

	@Override
	public void saveConfigurationToFileProperties(PropertiesConfiguration prop) {

	        if (tier != 0)
		        prop.setProperty("h264nvenc.tier", String.valueOf(tier));
		if (rclookahead != 0)
			prop.setProperty(StaticVideoEncoderFields.H264NVENCRCLOOKEAD, String.valueOf(getRclookahead()));
		if (surfaces != 0)
			prop.setProperty(StaticVideoEncoderFields.H264NVENCSURFACES, String.valueOf(getSurfaces()));
		if (delay != 0)
			prop.setProperty(StaticVideoEncoderFields.H264NVENCDELAY, String.valueOf(getDelay()));
		if (aqstrength != 8)
			prop.setProperty(StaticVideoEncoderFields.H264NVENCAQSTRNGTH, String.valueOf(getAqstrength()));
		if (noscenecut)
			prop.setProperty(StaticVideoEncoderFields.H264NVENCNOSCENECUT, String.valueOf(isNoscenecut()));
		if (forcedidr)
			prop.setProperty(StaticVideoEncoderFields.H264NVENCFORCEDIDR, String.valueOf(isForcedidr()));
		if (spatialaq)
			prop.setProperty(StaticVideoEncoderFields.H264NVENCSPATIALAQ, String.valueOf(isSpatialaq()));
		if (temporalaq)
			prop.setProperty(StaticVideoEncoderFields.H264NVENCTEMPORALAQ, String.valueOf(isTemporalaq()));
		if (zerolatency)
			prop.setProperty(StaticVideoEncoderFields.H264NVENCZEROLATENCY, String.valueOf(isZerolatency()));
		if (nonrefp)
			prop.setProperty(StaticVideoEncoderFields.H264NVENCNONREFQ, String.valueOf(isNonrefp()));
		if (strictgop)
			prop.setProperty(StaticVideoEncoderFields.H264NVENCSTRICTGOP, String.valueOf(isStrictgop()));
		if (init_qpP != -1)
			prop.setProperty(StaticVideoEncoderFields.H264NVENCINITQPP, String.valueOf(getInit_qpP()));
		if (init_qpB != -1)
			prop.setProperty(StaticVideoEncoderFields.H264NVENCINITQPB, String.valueOf(getInit_qpB()));
		if (init_qpI != -1)
			prop.setProperty(StaticVideoEncoderFields.H264NVENCINITQPI, String.valueOf(getInit_qpI()));

	}

	@Override
	public ArrayList<String> getFFmpegCmds() {

		ArrayList<String> cmds = new ArrayList<>();

		if (tier != 0) {
			cmds.add("-tier");
			cmds.add(String.valueOf(tier));
		}
		if (rclookahead != 0) {
			cmds.add(StaticFFmpegFields.H264NVENCRCLOOKEAD);
			cmds.add(String.valueOf(rclookahead));
		}
		if (surfaces != 0) {
			cmds.add(StaticFFmpegFields.H264NVENCSURFACES);
			cmds.add(String.valueOf(surfaces));
		}
		if (delay != 0) {
			cmds.add(StaticFFmpegFields.H264NVENCDELAY);
			cmds.add(String.valueOf(delay));
		}
		if (aqstrength != 8) {
			cmds.add(StaticFFmpegFields.H264NVENCAQSTRNGTH);
			cmds.add(String.valueOf(aqstrength));
		}
		if (noscenecut) {
			cmds.add(StaticFFmpegFields.H264NVENCNOSCENECUT);
			cmds.add(String.valueOf(noscenecut));
		}
		if (forcedidr) {
			cmds.add(StaticFFmpegFields.H264NVENCFORCEDIDR);
			cmds.add(String.valueOf(forcedidr));
		}
		if (spatialaq) {
			cmds.add(StaticFFmpegFields.H264NVENCSPATIALAQ);
			cmds.add(String.valueOf(spatialaq));
		}
		if (temporalaq) {
			cmds.add(StaticFFmpegFields.H264NVENCTEMPORALAQ);
			cmds.add(String.valueOf(temporalaq));
		}
		if (zerolatency) {
			cmds.add(StaticFFmpegFields.H264NVENCZEROLATENCY);
			cmds.add(String.valueOf(zerolatency));
		}
		if (nonrefp) {
			cmds.add(StaticFFmpegFields.H264NVENCNONREFQ);
			cmds.add(String.valueOf(nonrefp));
		}
		if (strictgop) {
			cmds.add(StaticFFmpegFields.H264NVENCSTRICTGOP);
			cmds.add(String.valueOf(strictgop));
		}
		if (init_qpP != -1) {
			cmds.add(StaticFFmpegFields.H264NVENCINITQPP);
			cmds.add(String.valueOf(init_qpP));
		}
		if (init_qpB != -1) {
			cmds.add(StaticFFmpegFields.H264NVENCINITQPB);
			cmds.add(String.valueOf(init_qpB));
		}
		if (init_qpI != -1) {
			cmds.add(StaticFFmpegFields.H264NVENCINITQPI);
			cmds.add(String.valueOf(init_qpI));
		}


		return cmds;
	}

	public static HevcNvencExtraInfoContainer loadHevcNvencExtraInfoContainer(PropertiesConfiguration props) {

		HevcNvencExtraInfoContainer newextracont = null;

		if (props.containsKey(StaticVideoEncoderFields.H264NVENCRCLOOKEAD)
			        || props.containsKey("h264nvenc.tier")
				|| props.containsKey(StaticVideoEncoderFields.H264NVENCSURFACES)
				|| props.containsKey(StaticVideoEncoderFields.H264NVENCDELAY)
				|| props.containsKey(StaticVideoEncoderFields.H264NVENCNOSCENECUT)
				|| props.containsKey(StaticVideoEncoderFields.H264NVENCFORCEDIDR)
				|| props.containsKey(StaticVideoEncoderFields.H264NVENCSPATIALAQ)
				|| props.containsKey(StaticVideoEncoderFields.H264NVENCTEMPORALAQ)
				|| props.containsKey(StaticVideoEncoderFields.H264NVENCZEROLATENCY)
				|| props.containsKey(StaticVideoEncoderFields.H264NVENCNONREFQ)
				|| props.containsKey(StaticVideoEncoderFields.H264NVENCSTRICTGOP)
				|| props.containsKey(StaticVideoEncoderFields.H264NVENCAQSTRNGTH)
				|| props.containsKey(StaticVideoEncoderFields.H264NVENCCODER)
				|| props.containsKey(StaticVideoEncoderFields.H264NVENCINITQPP)
				|| props.containsKey(StaticVideoEncoderFields.H264NVENCINITQPB)
				|| props.containsKey(StaticVideoEncoderFields.H264NVENCINITQPI)) {

			newextracont = getDefaulPropertiesExtraInfoContainer();

			newextracont.setTier(PropertiesWorker.checkProperty(props,
				"h264nvenc.tier", 0, 1, 0));
			newextracont.setRclookahead(PropertiesWorker.checkProperty(props,
					StaticVideoEncoderFields.H264NVENCRCLOOKEAD, 0, Integer.MAX_VALUE, 0));
			newextracont.setSurfaces(
					PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.H264NVENCSURFACES, 0, 64, 0));
			newextracont.setDelay(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.H264NVENCDELAY, 0,
					Integer.MAX_VALUE, 0));
			newextracont.setAqstrength(
					PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.H264NVENCAQSTRNGTH, 1, 15, 8));
			newextracont.setNoscenecut(
					PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.H264NVENCNOSCENECUT, false));
			newextracont.setForcedidr(
					PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.H264NVENCFORCEDIDR, false));
			newextracont.setSpatialaq(
					PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.H264NVENCSPATIALAQ, false));
			newextracont.setTemporalaq(
					PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.H264NVENCTEMPORALAQ, false));
			newextracont.setZerolatency(
					PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.H264NVENCZEROLATENCY, false));
			newextracont.setNonrefp(
					PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.H264NVENCNONREFQ, false));
			newextracont.setStrictgop(
					PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.H264NVENCSTRICTGOP, false));
			newextracont.setInit_qpP(
					PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.H264NVENCINITQPP, -1, 51, -1));
			newextracont.setInit_qpB(
					PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.H264NVENCINITQPB, -1, 51, -1));
			newextracont.setInit_qpI(
					PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.H264NVENCINITQPI, -1, 51, -1));

		}

		return newextracont;

	}

}
