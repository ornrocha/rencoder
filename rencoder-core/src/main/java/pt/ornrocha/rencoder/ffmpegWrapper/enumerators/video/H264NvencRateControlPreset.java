package pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video;

public enum H264NvencRateControlPreset {

  none,

  constqp,
  // Constant QP mode
  vbr,
  // Variable bitrate mode
  cbr,
  // Constant bitrate mode
  cbr_ld_hq,
  // Constant bitrate low delay high quality mode
  cbr_hq,
  // Constant bitrate high quality mode
  vbr_hq;

  public static H264NvencRateControlPreset getH264NvencRateControlPresetFromString(String rc) {
    if (rc != null) {
      for (H264NvencRateControlPreset p : H264NvencRateControlPreset.values()) {
        if (p.toString().toLowerCase().equals(rc.toLowerCase()))
          return p;
      }
    }
    return H264NvencRateControlPreset.none;
  }

}
