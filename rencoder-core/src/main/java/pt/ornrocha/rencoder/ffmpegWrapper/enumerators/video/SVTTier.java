package pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video;

public enum SVTTier {
  
  main{
    @Override
    public String getValue() {
      return "0";
    }
  },
  high{

    @Override
    public String getValue() {
      return "1";
    }
    
  };
  
  public String getValue() {
    return getValue();
  }
  
  public static SVTTier getSVTHEVCHielevelFromString(String tier) {
    if (tier != null) {
      for (SVTTier t : SVTTier.values()) {
        if (t.toString().toLowerCase().equals(tier.toString().toLowerCase()))
          return t;
      }
    }
    return SVTTier.main;
  }

}
