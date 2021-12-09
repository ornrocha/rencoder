package pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video;

public enum H264VaapiCoder {

  Ddefault {

    public String toString() {
      return "default";
    }
  },
  CABAC {

    public String toString() {
      return "cabac";
    }
  },
  CAVLC {

    public String toString() {
      return "cavlc";
    }
  },
  AC {

    public String toString() {
      return "ac";
    }
  },
  VLC {

    public String toString() {
      return "vlc";
    }
  };


  public static H264VaapiCoder getCoderFromString(String coder) {
    if (coder != null) {
      for (H264VaapiCoder c : H264VaapiCoder.values()) {
        if (c.toString().equalsIgnoreCase(coder))
          return c;
      }
    }
    return H264VaapiCoder.Ddefault;
  }


}
