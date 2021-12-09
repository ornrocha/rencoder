package pt.ornrocha.rencoder.ffmpegWrapper.releases.containers;

import java.util.ArrayList;
import java.util.Collection;

public class ListOfFFmpegReleases extends ArrayList<FFmpegReleaseInfo> {

  private static final long serialVersionUID = 1L;

  public ListOfFFmpegReleases() {
    super();
  }

  public ListOfFFmpegReleases(Collection<? extends FFmpegReleaseInfo> c) {
    super(c);
  }

  public ListOfFFmpegReleases(int initialCapacity) {
    super(initialCapacity);
  }


  public ArrayList<FFmpegReleaseInfo> getWindowsReleases() {
    ArrayList<FFmpegReleaseInfo> windowsreleases = new ArrayList<FFmpegReleaseInfo>();
    for (FFmpegReleaseInfo element : this) {
      if (element.getOstype().equals(FFmpegReleaseOsType.Windows))
        windowsreleases.add(element);
    }
    return windowsreleases;
  }

  public ArrayList<FFmpegReleaseInfo> getLinuxReleases() {
    ArrayList<FFmpegReleaseInfo> windowsreleases = new ArrayList<FFmpegReleaseInfo>();
    for (FFmpegReleaseInfo element : this) {
      if (element.getOstype().equals(FFmpegReleaseOsType.Linux))
        windowsreleases.add(element);
    }
    return windowsreleases;
  }


}
