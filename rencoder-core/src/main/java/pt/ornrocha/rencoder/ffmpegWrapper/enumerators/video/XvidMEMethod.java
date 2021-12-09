package pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video;

public enum XvidMEMethod {

  none, zero, phods, x1, log, epzs, full;

  public static XvidMEMethod getXvidMEMethodFromString(String method) {
    if (method != null) {
      for (XvidMEMethod t : XvidMEMethod.values()) {
        if (t.toString().equalsIgnoreCase(method))
          return t;
      }
    }
    return XvidMEMethod.none;
  }
}
