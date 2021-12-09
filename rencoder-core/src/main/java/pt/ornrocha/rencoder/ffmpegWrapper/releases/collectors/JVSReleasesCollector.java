package pt.ornrocha.rencoder.ffmpegWrapper.releases.collectors;

import java.io.IOException;

import pt.ornrocha.rencoder.ffmpegWrapper.releases.containers.FFmpegReleaseInfo;
import pt.ornrocha.rencoder.ffmpegWrapper.releases.containers.ListOfFFmpegReleases;

public class JVSReleasesCollector implements IReleasesCollector {

  static JVSReleasesCollector instance;
  private ListOfFFmpegReleases listreleases = null;
  private static String PROVIDER = "johnvansickle";
  private static String PROVIDERURL = "https://johnvansickle.com/ffmpeg/";

  public static JVSReleasesCollector getInstance() throws Exception {
    if (instance == null)
      instance = new JVSReleasesCollector();
    instance.collect();
    return instance;
  }

  public JVSReleasesCollector() {}

  @Override
  public void collect() throws Exception {
    listreleases = new ListOfFFmpegReleases();
    processReleases();
  }

  private void processReleases() throws IOException {

    FFmpegReleaseInfo master =
        FFmpegReleaseInfo.createLinuxRelease("ffmpeg-release-master-amd64-static",
            "https://johnvansickle.com/ffmpeg/builds/ffmpeg-git-amd64-static.tar.xz", 41104179,
            PROVIDER, PROVIDERURL);
    FFmpegReleaseInfo r441 =
        FFmpegReleaseInfo.createLinuxRelease("ffmpeg-release-4.4.1-amd64-static",
            "https://johnvansickle.com/ffmpeg/releases/ffmpeg-release-amd64-static.tar.xz",
            39845888, PROVIDER, PROVIDERURL);

    listreleases.add(master);
    listreleases.add(r441);

  }


  @Override
  public ListOfFFmpegReleases getListOfReleases() {
    return listreleases;
  }


  public static void main(String[] args) throws Exception {
    JVSReleasesCollector.getInstance();
  }



}
