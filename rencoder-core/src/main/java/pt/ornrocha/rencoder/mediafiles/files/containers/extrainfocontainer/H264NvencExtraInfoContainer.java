package pt.ornrocha.rencoder.mediafiles.files.containers.extrainfocontainer;

import java.util.ArrayList;

import org.apache.commons.configuration.PropertiesConfiguration;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.H264NvencCoder;
import pt.ornrocha.rencoder.helpers.props.fields.StaticFFmpegFields;
import pt.ornrocha.rencoder.helpers.props.fields.StaticVideoEncoderFields;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;

public class H264NvencExtraInfoContainer implements IExtraInfoContainer {

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

  private boolean badapt = true;

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

  // Constant quantization parameter rate control method (from -1 to 51) (default
  // -1)
  // private int qp=-1;

  // Coder type (from -1 to 2) (default default)
  private H264NvencCoder coder = H264NvencCoder.Ddefault;

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

  public boolean isBadapt() {
    return badapt;
  }

  public void setBadapt(boolean badapt) {
    this.badapt = badapt;
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

  public H264NvencCoder getCoder() {
    return coder;
  }

  public void setCoder(H264NvencCoder coder) {
    this.coder = coder;
  }

  public H264NvencExtraInfoContainer() {}

  private H264NvencExtraInfoContainer(int rclookahead, int surfaces, int delay, int aqstrength,
      boolean noscenecut, boolean forcedidr, boolean badapt, boolean spatialaq, boolean temporalaq,
      boolean zerolatency, boolean nonrefp, boolean strictgop, int init_qpP, int init_qpB,
      int init_qpI, H264NvencCoder coder) {
    super();
    this.rclookahead = rclookahead;
    this.surfaces = surfaces;
    this.delay = delay;
    this.aqstrength = aqstrength;
    this.noscenecut = noscenecut;
    this.forcedidr = forcedidr;
    this.badapt = badapt;
    this.spatialaq = spatialaq;
    this.temporalaq = temporalaq;
    this.zerolatency = zerolatency;
    this.nonrefp = nonrefp;
    this.strictgop = strictgop;
    this.init_qpP = init_qpP;
    this.init_qpB = init_qpB;
    this.init_qpI = init_qpI;
    this.coder = coder;
  }

  @Override
  public IExtraInfoContainer clone() {
    return new H264NvencExtraInfoContainer(rclookahead, surfaces, delay, aqstrength, noscenecut,
        forcedidr, badapt, spatialaq, temporalaq, zerolatency, nonrefp, strictgop, init_qpP,
        init_qpB, init_qpI, coder);
  }

  public static H264NvencExtraInfoContainer getDefaulPropertiesH264ExtraInfoContainer() {
    return new H264NvencExtraInfoContainer();
  }

  @Override
  public void saveConfigurationToFileProperties(PropertiesConfiguration prop) {

    if (rclookahead != 0)
      prop.setProperty(StaticVideoEncoderFields.H264NVENCRCLOOKEAD,
          String.valueOf(getRclookahead()));
    if (surfaces != 0)
      prop.setProperty(StaticVideoEncoderFields.H264NVENCSURFACES, String.valueOf(getSurfaces()));
    if (delay != 0)
      prop.setProperty(StaticVideoEncoderFields.H264NVENCDELAY, String.valueOf(getDelay()));
    if (aqstrength != 8)
      prop.setProperty(StaticVideoEncoderFields.H264NVENCAQSTRNGTH,
          String.valueOf(getAqstrength()));
    if (noscenecut)
      prop.setProperty(StaticVideoEncoderFields.H264NVENCNOSCENECUT,
          String.valueOf(isNoscenecut()));
    if (forcedidr)
      prop.setProperty(StaticVideoEncoderFields.H264NVENCFORCEDIDR, String.valueOf(isForcedidr()));
    if (!badapt)
      prop.setProperty(StaticVideoEncoderFields.H264NVENCBADAPT, String.valueOf(isBadapt()));
    if (spatialaq)
      prop.setProperty(StaticVideoEncoderFields.H264NVENCSPATIALAQ, String.valueOf(isSpatialaq()));
    if (temporalaq)
      prop.setProperty(StaticVideoEncoderFields.H264NVENCTEMPORALAQ,
          String.valueOf(isTemporalaq()));
    if (zerolatency)
      prop.setProperty(StaticVideoEncoderFields.H264NVENCZEROLATENCY,
          String.valueOf(isZerolatency()));
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
    if (!coder.equals(H264NvencCoder.Ddefault))
      prop.setProperty(StaticVideoEncoderFields.H264NVENCCODER, coder.toString());

  }

  @Override
  public ArrayList<String> getFFmpegCmds() {

    ArrayList<String> cmds = new ArrayList<>();

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
    if (!badapt) {
      cmds.add(StaticFFmpegFields.H264NVENCBADAPT);
      cmds.add(String.valueOf(badapt));
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
    if (!coder.equals(H264NvencCoder.Ddefault)) {
      cmds.add(StaticFFmpegFields.H264NVENCCODER);
      cmds.add(coder.toString());
    }

    return cmds;
  }

  public static H264NvencExtraInfoContainer loadH264NvencExtraInfoContainer(
      PropertiesConfiguration props) {

    H264NvencExtraInfoContainer newextracont = null;

    if (props.containsKey(StaticVideoEncoderFields.H264NVENCRCLOOKEAD)
        || props.containsKey(StaticVideoEncoderFields.H264NVENCSURFACES)
        || props.containsKey(StaticVideoEncoderFields.H264NVENCDELAY)
        || props.containsKey(StaticVideoEncoderFields.H264NVENCNOSCENECUT)
        || props.containsKey(StaticVideoEncoderFields.H264NVENCFORCEDIDR)
        || props.containsKey(StaticVideoEncoderFields.H264NVENCBADAPT)
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

      newextracont = getDefaulPropertiesH264ExtraInfoContainer();

      newextracont.setRclookahead(PropertiesWorker.checkProperty(props,
          StaticVideoEncoderFields.H264NVENCRCLOOKEAD, 0, Integer.MAX_VALUE, 0));
      newextracont.setSurfaces(PropertiesWorker.checkProperty(props,
          StaticVideoEncoderFields.H264NVENCSURFACES, 0, 64, 0));
      newextracont.setDelay(PropertiesWorker.checkProperty(props,
          StaticVideoEncoderFields.H264NVENCDELAY, 0, Integer.MAX_VALUE, 0));
      newextracont.setAqstrength(PropertiesWorker.checkProperty(props,
          StaticVideoEncoderFields.H264NVENCAQSTRNGTH, 1, 15, 8));
      newextracont.setNoscenecut(PropertiesWorker.checkProperty(props,
          StaticVideoEncoderFields.H264NVENCNOSCENECUT, false));
      newextracont.setForcedidr(PropertiesWorker.checkProperty(props,
          StaticVideoEncoderFields.H264NVENCFORCEDIDR, false));
      newextracont.setBadapt(
          PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.H264NVENCBADAPT, true));
      newextracont.setSpatialaq(PropertiesWorker.checkProperty(props,
          StaticVideoEncoderFields.H264NVENCSPATIALAQ, false));
      newextracont.setTemporalaq(PropertiesWorker.checkProperty(props,
          StaticVideoEncoderFields.H264NVENCTEMPORALAQ, false));
      newextracont.setZerolatency(PropertiesWorker.checkProperty(props,
          StaticVideoEncoderFields.H264NVENCZEROLATENCY, false));
      newextracont.setNonrefp(
          PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.H264NVENCNONREFQ, false));
      newextracont.setStrictgop(PropertiesWorker.checkProperty(props,
          StaticVideoEncoderFields.H264NVENCSTRICTGOP, false));
      newextracont.setInit_qpP(PropertiesWorker.checkProperty(props,
          StaticVideoEncoderFields.H264NVENCINITQPP, -1, 51, -1));
      newextracont.setInit_qpB(PropertiesWorker.checkProperty(props,
          StaticVideoEncoderFields.H264NVENCINITQPB, -1, 51, -1));
      newextracont.setInit_qpI(PropertiesWorker.checkProperty(props,
          StaticVideoEncoderFields.H264NVENCINITQPI, -1, 51, -1));
      newextracont.setCoder(H264NvencCoder.getCoderFromString(
          PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.H264NVENCCODER)));
    }

    return newextracont;

  }

}
