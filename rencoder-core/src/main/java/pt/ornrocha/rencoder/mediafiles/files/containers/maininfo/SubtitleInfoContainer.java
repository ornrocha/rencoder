package pt.ornrocha.rencoder.mediafiles.files.containers.maininfo;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.subtitles.SubEncodings;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.subtitles.SubtiltesAlignment;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.subtitles.SubtitlesBorderStyle;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.subtitles.SubtitlesColor;

public class SubtitleInfoContainer {

  // Subtitles
  /** The use hard subs. */
  protected boolean useHardSubs = false;

  /** The use soft subs. */
  protected boolean useSoftSubs = false;

  /** The subtitles font size. */
  protected int subsFontSize = 28;

  /** The subtitles font name. */
  protected String subsFontName = "Arial";

  /** The subtitles outline. */
  protected int subsOutline = 2;

  /** The subtitles shadow. */
  protected int subsShadow = 1;

  /** The subtitles use bold. */
  protected boolean subsUseBold = false;

  /** The subtitles use italic. */
  protected boolean subsUseItalic = false;

  /** The subtitles color. */
  protected SubtitlesColor subsColor = SubtitlesColor.WHITE;

  /** The subtitles encoding. */
  protected String subsEncoding = SubEncodings.AUTO.toString();

  /** The subtitles alternative encoding. */
  protected String subsAlternativeEncoding = null;

  /** The subtitles border style. */
  protected SubtitlesBorderStyle subsBorderStyle = SubtitlesBorderStyle.OUTILINEWITHSHADOW;

  /** The subtitles alignment. */
  protected SubtiltesAlignment subsAlignment = SubtiltesAlignment.BOTTOM;

  /** The use mp4 subtitle encoding codec. */
  protected boolean useMp4SubtitleEncCodec = false;



  public SubtitleInfoContainer() {
    super();
  }


  public SubtitleInfoContainer(boolean useHardSubs, boolean useSoftSubs, int subsFontSize,
      String subsFontName, int subsOutline, int subsShadow, boolean subsUseBold,
      boolean subsUseItalic, SubtitlesColor subsColor, String subsEncoding,
      String subsAlternativeEncoding, SubtitlesBorderStyle subsBorderStyle,
      SubtiltesAlignment subsAlignment, boolean useMp4SubtitleEncCodec) {
    this.useHardSubs = useHardSubs;
    this.useSoftSubs = useSoftSubs;
    this.subsFontSize = subsFontSize;
    this.subsFontName = subsFontName;
    this.subsOutline = subsOutline;
    this.subsShadow = subsShadow;
    this.subsUseBold = subsUseBold;
    this.subsUseItalic = subsUseItalic;
    this.subsColor = subsColor;
    this.subsEncoding = subsEncoding;
    this.subsAlternativeEncoding = subsAlternativeEncoding;
    this.subsBorderStyle = subsBorderStyle;
    this.subsAlignment = subsAlignment;
    this.useMp4SubtitleEncCodec = useMp4SubtitleEncCodec;
  }



  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#isUseHardSubs()
   */
  public boolean isUseHardSubs() {
    return useHardSubs;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#setUseHardSubs(boolean)
   */
  public void setUseHardSubs(boolean useHardSubs) {
    this.useHardSubs = useHardSubs;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#isUseSoftSubs()
   */
  public boolean isUseSoftSubs() {
    return useSoftSubs;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#setUseSoftSubs(boolean)
   */
  public void setUseSoftSubs(boolean useSoftSubs) {
    this.useSoftSubs = useSoftSubs;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#getSubsFontSize()
   */
  public int getSubsFontSize() {
    return subsFontSize;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#setSubsFontSize(int)
   */
  public void setSubsFontSize(int subsFontSize) {
    this.subsFontSize = subsFontSize;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#getSubsFontName()
   */
  public String getSubsFontName() {
    return subsFontName;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#setSubsFontName(java.
   * lang.String)
   */
  public void setSubsFontName(String subsFontName) {
    this.subsFontName = subsFontName;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#getSubsOutline()
   */
  public int getSubsOutline() {
    return subsOutline;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#setSubsOutline(int)
   */
  public void setSubsOutline(int subsOutline) {
    this.subsOutline = subsOutline;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#getSubsShadow()
   */
  public int getSubsShadow() {
    return subsShadow;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#setSubsShadow(int)
   */
  public void setSubsShadow(int subsShadow) {
    this.subsShadow = subsShadow;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#isSubsUseBold()
   */
  public boolean isSubsUseBold() {
    return subsUseBold;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#setSubsUseBold(boolean)
   */
  public void setSubsUseBold(boolean subsUseBold) {
    this.subsUseBold = subsUseBold;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#isSubsUseItalic()
   */
  public boolean isSubsUseItalic() {
    return subsUseItalic;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#setSubsUseItalic(
   * boolean)
   */
  public void setSubsUseItalic(boolean subsUseItalic) {
    this.subsUseItalic = subsUseItalic;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#getSubsColor()
   */
  public SubtitlesColor getSubsColor() {
    return subsColor;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#setSubsColor(
   * ffmpegWrapper.enumerators.SubtitlesColor)
   */
  public void setSubsColor(SubtitlesColor subsColor) {
    this.subsColor = subsColor;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#getSubsEncoding()
   */
  public String getSubsEncoding() {
    return subsEncoding;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#setSubsEncoding(
   * ffmpegWrapper.enumerators.SubEncodings)
   */
  public void setSubsEncoding(String subsEncoding) {
    this.subsEncoding = subsEncoding;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
   * getSubsAlternativeEncoding()
   */
  public String getSubsAlternativeEncoding() {
    return subsAlternativeEncoding;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
   * setSubsAlternativeEncoding(java.lang.String)
   */
  public void setSubsAlternativeEncoding(String subsAlternativeEncoding) {
    this.subsAlternativeEncoding = subsAlternativeEncoding;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#getSubsBorderStyle()
   */
  public SubtitlesBorderStyle getSubsBorderStyle() {
    return subsBorderStyle;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#setSubsBorderStyle(
   * ffmpegWrapper.enumerators.SubtitlesBorderStyle)
   */
  public void setSubsBorderStyle(SubtitlesBorderStyle subsBorderStyle) {
    this.subsBorderStyle = subsBorderStyle;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#getSubsAlignment()
   */
  public SubtiltesAlignment getSubsAlignment() {
    return subsAlignment;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#setSubsAlignment(
   * ffmpegWrapper.enumerators.SubtiltesAlignment)
   */
  public void setSubsAlignment(SubtiltesAlignment subsAlignment) {
    this.subsAlignment = subsAlignment;
  }



  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
   * isUseMp4SubtitleEncCodec()
   */
  public boolean isUseMp4SubtitleEncCodec() {
    return useMp4SubtitleEncCodec;
  }

  /*
   * (non-Javadoc)
   * 
   * @see filetreatment.files.containers.interfaces.IGeneralVideoEncInfoContainer#
   * setUseMp4SubtitleEncCodec(boolean)
   */
  public void setUseMp4SubtitleEncCodec(boolean useMp4SubtitleEncCodec) {
    this.useMp4SubtitleEncCodec = useMp4SubtitleEncCodec;
  }



  public SubtitleInfoContainer copyInfoContainer() {

    return new SubtitleInfoContainer(useHardSubs, useSoftSubs, subsFontSize, subsFontName,
        subsOutline, subsShadow, this.isSubsUseBold(), this.isSubsUseItalic(), subsColor,
        subsEncoding, subsAlternativeEncoding, subsBorderStyle, subsAlignment,
        useMp4SubtitleEncCodec);

  }


  public void print() {
    System.out.println("useHardSubs =" + useHardSubs);
    System.out.println("useSoftSubs =" + useSoftSubs);
    System.out.println("subsFontSize =" + subsFontSize);
    System.out.println("subsFontName =" + subsFontName);
    System.out.println("subsOutline =" + subsOutline);
    System.out.println("subsShadow =" + subsShadow);
    System.out.println("subsUseBold =" + subsUseBold);
    System.out.println("subsUseItalic =" + subsUseItalic);
    System.out.println("subsColor  =" + subsColor);
    System.out.println("subsEncoding =" + subsEncoding);
    System.out.println("subsAlternativeEncoding =" + subsAlternativeEncoding);
    System.out.println("subsBorderStyle =" + subsBorderStyle);
    System.out.println("subsAlignment =" + subsAlignment);
    System.out.println("useMp4SubtitleEncCodec =" + useMp4SubtitleEncCodec);
  }


}
