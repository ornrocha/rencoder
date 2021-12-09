package pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video;

public enum H264NvencCoder {

  Ddefault {

    public String toString() {
      return "default";
    }
  },
  AUTO {

    public String toString() {
      return "auto";
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


  public static H264NvencCoder getCoderFromString(String coder) {
    if (coder != null) {
      for (H264NvencCoder c : H264NvencCoder.values()) {
        if (c.toString().equalsIgnoreCase(coder))
          return c;
      }
    }
    return H264NvencCoder.Ddefault;
  }


}
