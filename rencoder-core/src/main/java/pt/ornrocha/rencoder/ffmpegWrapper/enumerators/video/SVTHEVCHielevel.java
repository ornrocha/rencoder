package pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video;

public enum SVTHEVCHielevel {
  
  flat{
    public String toString() {
      return "flat";
    }
    
    @Override
    public String getValue() {
      return "0";
    }
  },
  
  h1level{
    public String toString() {
      return "1 level";
    }
    
    @Override
    public String getValue() {
      return "1";
    }
  },
  h2level{
    public String toString() {
      return "2 level";
    }
    
    @Override
    public String getValue() {
      return "2";
    }
    
  },
  h3level{
    public String toString() {
      return "3 level";
    }
    
    @Override
    public String getValue() {
      return "3";
    }
    
  };
  
  public String getValue() {
    return getValue();
  }
  
  public static SVTHEVCHielevel getSVTHEVCHielevelFromString(String Hielevel) {
    if (Hielevel != null) {
      for (SVTHEVCHielevel t : SVTHEVCHielevel.values()) {
        if (t.toString().toLowerCase().equals(Hielevel.toString().toLowerCase()))
          return t;
      }
    }
    return SVTHEVCHielevel.h3level;
  }

}
