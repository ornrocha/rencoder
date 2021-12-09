package pt.ornrocha.rencoder.ffmpegWrapper.filters;

public class RotateFilter implements IFFmpegFilter {

  private int action = -1;
  private String rotatecmd = null;

  public RotateFilter(int action) {
    this.action = action;
    if (action != 3) {
      rotatecmd = null;
    }
  }

  public RotateFilter(String rotatecmd) {
    this.rotatecmd = rotatecmd;
    this.action = 3;
  }

  public int getAction() {
    return action;
  }

  public String getRotatecmd() {
    return rotatecmd;
  }

  @Override
  public String getFFmpegCMD() {
    // TODO Auto-generated method stub
    // return "hflip,vflip, transpose=2";

    if (this.action == 0)
      return "transpose=1";
    else if (this.action == 1)
      return "transpose=2";
    else if (this.action == 2)
      return "hflip,vflip";
    else if (this.action == 3) {
      if (this.rotatecmd.contains("rotate="))
        return rotatecmd;
      else
        return "rotate=" + rotatecmd;
    }

    else
      return null;
  }

}
