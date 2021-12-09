package pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video;

public enum SVTAV1Hielevel {
  
  h3level{
    public String toString() {
      return "3level";
    }
    
    @Override
    public String getValue() {
      return "3";
    }
  },
  h4level{
    public String toString() {
      return "4level";
    }
    
    @Override
    public String getValue() {
      return "4";
    }
    
  };
  
  public String getValue() {
    return getValue();
  }
  
  public static SVTAV1Hielevel getSVTAV1HielevelFromString(String Hielevel) {
    if (Hielevel != null) {
      for (SVTAV1Hielevel t : SVTAV1Hielevel.values()) {
        if (t.toString().toLowerCase().equals(Hielevel.toString().toLowerCase()))
          return t;
      }
    }
    return SVTAV1Hielevel.h4level;
  }

}
