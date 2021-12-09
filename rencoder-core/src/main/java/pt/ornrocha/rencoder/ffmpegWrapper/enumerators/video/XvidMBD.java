package pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video;

public enum XvidMBD {

  none, simple, bits, rd;

  public static XvidMBD getXvidMBDFromString(String mbd) {
    if (mbd != null) {
      for (XvidMBD t : XvidMBD.values()) {
        if (t.toString().equalsIgnoreCase(mbd))
          return t;
      }
    }
    return XvidMBD.none;
  }
}
