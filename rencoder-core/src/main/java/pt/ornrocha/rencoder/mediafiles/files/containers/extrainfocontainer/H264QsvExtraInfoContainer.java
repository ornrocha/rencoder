package pt.ornrocha.rencoder.mediafiles.files.containers.extrainfocontainer;

import java.util.ArrayList;

import org.apache.commons.configuration.PropertiesConfiguration;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.H264NvencCoder;
import pt.ornrocha.rencoder.helpers.props.fields.StaticFFmpegFields;
import pt.ornrocha.rencoder.helpers.props.fields.StaticVideoEncoderFields;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;

public class H264QsvExtraInfoContainer implements IExtraInfoContainer {

  private int async_depth = 4;
  private int rdo = -1;
  private int max_frame_size = -1;
  private int max_slice_size = -1;
  private int bitrate_limit = -1;
  private int mbbrc = -1;
  private int extbrc = -1;
  private int adaptive_i = -1;
  private int adaptive_b = -1;
  private int b_strategy = -1;
  private int idr_interval = 0;
  private int max_dec_frame_buffering = 0;
  private int look_ahead = 0;
  private int look_ahead_depth = 0;
  private int look_ahead_downsampling = 0;
  private int int_ref_type = -1;

  public int getAsync_depth() {
    return async_depth;
  }

  public void setAsync_depth(int async_depth) {
    this.async_depth = async_depth;
  }

  public int getRdo() {
    return rdo;
  }

  public void setRdo(int rdo) {
    this.rdo = rdo;
  }

  public int getMax_frame_size() {
    return max_frame_size;
  }

  public void setMax_frame_size(int max_frame_size) {
    this.max_frame_size = max_frame_size;
  }

  public int getMax_slice_size() {
    return max_slice_size;
  }

  public void setMax_slice_size(int max_slice_size) {
    this.max_slice_size = max_slice_size;
  }

  public int getBitrate_limit() {
    return bitrate_limit;
  }

  public void setBitrate_limit(int bitrate_limit) {
    this.bitrate_limit = bitrate_limit;
  }

  public int getMbbrc() {
    return mbbrc;
  }

  public void setMbbrc(int mbbrc) {
    this.mbbrc = mbbrc;
  }

  public int getExtbrc() {
    return extbrc;
  }

  public void setExtbrc(int extbrc) {
    this.extbrc = extbrc;
  }

  public int getAdaptive_i() {
    return adaptive_i;
  }

  public void setAdaptive_i(int adaptive_i) {
    this.adaptive_i = adaptive_i;
  }

  public int getAdaptive_b() {
    return adaptive_b;
  }

  public void setAdaptive_b(int adaptive_b) {
    this.adaptive_b = adaptive_b;
  }

  public int getB_strategy() {
    return b_strategy;
  }

  public void setB_strategy(int b_strategy) {
    this.b_strategy = b_strategy;
  }

  public int getIdr_interval() {
    return idr_interval;
  }

  public void setIdr_interval(int idr_interval) {
    this.idr_interval = idr_interval;
  }

  public int getMax_dec_frame_buffering() {
    return max_dec_frame_buffering;
  }

  public void setMax_dec_frame_buffering(int max_dec_frame_buffering) {
    this.max_dec_frame_buffering = max_dec_frame_buffering;
  }

  public int getLook_ahead() {
    return look_ahead;
  }

  public void setLook_ahead(int look_ahead) {
    this.look_ahead = look_ahead;
  }

  public int getLook_ahead_depth() {
    return look_ahead_depth;
  }

  public void setLook_ahead_depth(int look_ahead_depth) {
    this.look_ahead_depth = look_ahead_depth;
  }

  public int getLook_ahead_downsampling() {
    return look_ahead_downsampling;
  }

  public void setLook_ahead_downsampling(int look_ahead_downsampling) {
    this.look_ahead_downsampling = look_ahead_downsampling;
  }

  public int getInt_ref_type() {
    return int_ref_type;
  }

  public void setInt_ref_type(int int_ref_type) {
    this.int_ref_type = int_ref_type;
  }

  public H264QsvExtraInfoContainer() {}



  private H264QsvExtraInfoContainer(int async_depth, int rdo, int max_frame_size,
      int max_slice_size, int bitrate_limit, int mbbrc, int extbrc, int adaptive_i, int adaptive_b,
      int b_strategy, int idr_interval, int max_dec_frame_buffering, int look_ahead,
      int look_ahead_depth, int look_ahead_downsampling, int int_ref_type) {
    this.async_depth = async_depth;
    this.rdo = rdo;
    this.max_frame_size = max_frame_size;
    this.max_slice_size = max_slice_size;
    this.bitrate_limit = bitrate_limit;
    this.mbbrc = mbbrc;
    this.extbrc = extbrc;
    this.adaptive_i = adaptive_i;
    this.adaptive_b = adaptive_b;
    this.b_strategy = b_strategy;
    this.idr_interval = idr_interval;
    this.max_dec_frame_buffering = max_dec_frame_buffering;
    this.look_ahead = look_ahead;
    this.look_ahead_depth = look_ahead_depth;
    this.look_ahead_downsampling = look_ahead_downsampling;
    this.int_ref_type = int_ref_type;
  }

  @Override
  public IExtraInfoContainer clone() {
    return new H264QsvExtraInfoContainer(async_depth, rdo, max_frame_size, max_slice_size,
        bitrate_limit, mbbrc, extbrc, adaptive_i, adaptive_b, b_strategy, idr_interval,
        max_dec_frame_buffering, look_ahead, look_ahead_depth, look_ahead_downsampling,
        int_ref_type);
  }

  public static H264QsvExtraInfoContainer getDefaulPropertiesH264ExtraInfoContainer() {
    return new H264QsvExtraInfoContainer();
  }

  @Override
  public void saveConfigurationToFileProperties(PropertiesConfiguration prop) {

    // if (rclookahead != 0)
    prop.setProperty("async_depth", String.valueOf(async_depth));
    prop.setProperty("rdo", String.valueOf(rdo));
    prop.setProperty("max_frame_size", String.valueOf(max_frame_size));
    prop.setProperty("max_slice_size", String.valueOf(max_slice_size));
    prop.setProperty("bitrate_limit", String.valueOf(bitrate_limit));
    prop.setProperty("mbbrc", String.valueOf(mbbrc));
    prop.setProperty("extbrc", String.valueOf(extbrc));
    prop.setProperty("adaptive_i", String.valueOf(adaptive_i));
    prop.setProperty("adaptive_b", String.valueOf(adaptive_b));
    prop.setProperty("b_strategy", String.valueOf(b_strategy));
    prop.setProperty("idr_interval", String.valueOf(idr_interval));
    prop.setProperty("max_dec_frame_buffering", String.valueOf(max_dec_frame_buffering));
    prop.setProperty("look_ahead", String.valueOf(look_ahead));
    prop.setProperty("look_ahead_depth", String.valueOf(look_ahead_depth));
    prop.setProperty("look_ahead_downsampling", String.valueOf(look_ahead_downsampling));
    prop.setProperty("int_ref_type", String.valueOf(int_ref_type));

  }

  @Override
  public ArrayList<String> getFFmpegCmds() {

    ArrayList<String> cmds = new ArrayList<>();

    if (async_depth != 4) {
      cmds.add("-async_depth");
      cmds.add(String.valueOf(async_depth));
    }
    if (rdo != -1) {
      cmds.add("-rdo");
      cmds.add(String.valueOf(rdo));
    }
    if (max_frame_size != -1) {
      cmds.add("-max_frame_size");
      cmds.add(String.valueOf(max_frame_size));
    }
    if (max_slice_size != -1) {
      cmds.add("-max_slice_size");
      cmds.add(String.valueOf(max_slice_size));
    }
    if (bitrate_limit != -1) {
      cmds.add("-bitrate_limit");
      cmds.add(String.valueOf(bitrate_limit));
    }
    if (mbbrc != -1) {
      cmds.add("-mbbrc");
      cmds.add(String.valueOf(mbbrc));
    }
    if (extbrc != -1) {
      cmds.add("-extbrc");
      cmds.add(String.valueOf(extbrc));
    }
    if (adaptive_i != -1) {
      cmds.add("-adaptive_i");
      cmds.add(String.valueOf(adaptive_i));
    }
    if (adaptive_b != -1) {
      cmds.add("-adaptive_b");
      cmds.add(String.valueOf(adaptive_b));
    }
    if (b_strategy != -1) {
      cmds.add("-b_strategy");
      cmds.add(String.valueOf(b_strategy));
    }
    if (idr_interval != 0) {
      cmds.add("-idr_interval");
      cmds.add(String.valueOf(idr_interval));
    }
    if (max_dec_frame_buffering != 0) {
      cmds.add("-max_dec_frame_buffering");
      cmds.add(String.valueOf(max_dec_frame_buffering));
    }
    if (look_ahead != 0) {
      cmds.add("-look_ahead");
      cmds.add(String.valueOf(look_ahead));
    }
    if (look_ahead_depth != -1) {
      cmds.add("-look_ahead_depth");
      cmds.add(String.valueOf(look_ahead_depth));
    }
    if (look_ahead_downsampling != -1) {
      cmds.add("-look_ahead_downsampling");
      cmds.add(String.valueOf(look_ahead_downsampling));
    }
    if (int_ref_type != -1) {
      cmds.add("-int_ref_type");
      cmds.add(String.valueOf(int_ref_type));
    }

    return cmds;
  }

  public static H264QsvExtraInfoContainer loadH264QsvExtraInfoContainer(
      PropertiesConfiguration props) {

    H264QsvExtraInfoContainer newextracont = null;

    // if (props.containsKey(StaticVideoEncoderFields.H264NVENCRCLOOKEAD)
    // || props.containsKey(StaticVideoEncoderFields.H264NVENCSURFACES)
    // || props.containsKey(StaticVideoEncoderFields.H264NVENCDELAY)
    // || props.containsKey(StaticVideoEncoderFields.H264NVENCNOSCENECUT)
    // || props.containsKey(StaticVideoEncoderFields.H264NVENCFORCEDIDR)
    // || props.containsKey(StaticVideoEncoderFields.H264NVENCBADAPT)
    // || props.containsKey(StaticVideoEncoderFields.H264NVENCSPATIALAQ)
    // || props.containsKey(StaticVideoEncoderFields.H264NVENCTEMPORALAQ)
    // || props.containsKey(StaticVideoEncoderFields.H264NVENCZEROLATENCY)
    // || props.containsKey(StaticVideoEncoderFields.H264NVENCNONREFQ)
    // || props.containsKey(StaticVideoEncoderFields.H264NVENCSTRICTGOP)
    // || props.containsKey(StaticVideoEncoderFields.H264NVENCAQSTRNGTH)
    // || props.containsKey(StaticVideoEncoderFields.H264NVENCCODER)
    // || props.containsKey(StaticVideoEncoderFields.H264NVENCINITQPP)
    // || props.containsKey(StaticVideoEncoderFields.H264NVENCINITQPB)
    // || props.containsKey(StaticVideoEncoderFields.H264NVENCINITQPI)) {

    newextracont = getDefaulPropertiesH264ExtraInfoContainer();

    newextracont.setAsync_depth(PropertiesWorker.checkProperty(props, "async_depth", 4));
    newextracont.setRdo(PropertiesWorker.checkProperty(props, "rdo", -1));
    newextracont.setMax_frame_size(PropertiesWorker.checkProperty(props, "max_frame_size", -1));
    newextracont.setMax_slice_size(PropertiesWorker.checkProperty(props, "max_slice_size", -1));
    newextracont.setBitrate_limit(PropertiesWorker.checkProperty(props, "bitrate_limit", -1));
    newextracont.setMbbrc(PropertiesWorker.checkProperty(props, "mbbrc", -1));
    newextracont.setExtbrc(PropertiesWorker.checkProperty(props, "extbrc", -1));
    newextracont.setAdaptive_i(PropertiesWorker.checkProperty(props, "adaptive_i", -1));
    newextracont.setAdaptive_b(PropertiesWorker.checkProperty(props, "adaptive_b", -1));
    newextracont.setB_strategy(PropertiesWorker.checkProperty(props, "b_strategy", -1));
    newextracont.setIdr_interval(PropertiesWorker.checkProperty(props, "idr_interval", 0));
    newextracont.setMax_dec_frame_buffering(
        PropertiesWorker.checkProperty(props, "max_dec_frame_buffering", 0));
    newextracont.setLook_ahead(PropertiesWorker.checkProperty(props, "look_ahead", 0));
    newextracont.setLook_ahead_depth(PropertiesWorker.checkProperty(props, "look_ahead_depth", 0));
    newextracont.setLook_ahead_downsampling(
        PropertiesWorker.checkProperty(props, "look_ahead_downsampling", 0));
    newextracont.setInt_ref_type(PropertiesWorker.checkProperty(props, "int_ref_type", -1));



    return newextracont;

  }

}
