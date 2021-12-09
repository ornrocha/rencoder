package pt.ornrocha.rencoder.ffmpegWrapper.filters;

public class ScalingFilter implements IFFmpegFilter {

  private static String SCALETAG = "scale=";
  private static String ENDTAG = "\"";
  private int w = -1;
  private int h = -1;
  private String resizefixscale = null;
  private int resizevalue = 0;

  public ScalingFilter() {

  }

  public ScalingFilter(int width, int height) {
    this.w = width;
    this.h = height;

  }

  public void setMaxWidth(int width) {
    this.w = width;
    this.h = -1;
    resizevalue = 0;
    resizefixscale = null;
  }

  public int getWidth() {
    return this.w;
  }

  public void setMaxHeight(int height) {
    this.w = -1;
    this.h = height;
    resizevalue = 0;
    resizefixscale = null;
  }

  public int getHeight() {
    return this.h;
  }

  public void scaleToXSizeOfOriginal(int resize) {
    if (resize > 1)
      this.resizevalue = resize;
    this.resizefixscale = "'iw/" + String.valueOf(resize) + "':-1";
  }

  public int getAutoScaleValue() {
    return this.resizevalue;
  }

  @Override
  public String getFFmpegCMD() {
    if (resizefixscale != null)
      return SCALETAG + resizefixscale;
    else {
      if (w != -1 && h == -1)
        return SCALETAG + String.valueOf(w) + ":-1" + ENDTAG;
      else if (w == -1 && h != -1)
        return SCALETAG + "-1:" + String.valueOf(h) + ENDTAG;
      else if (w != -1 && h != -1)
        return SCALETAG + String.valueOf(w) + ":" + String.valueOf(h) + ENDTAG;
    }

    return null;
  }

}
