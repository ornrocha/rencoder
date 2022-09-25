package pt.ornrocha.rencoder.ffmpegWrapper.releases.collectors;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.kohsuke.github.GHAsset;
import org.kohsuke.github.GHRelease;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.PagedIterable;

import pt.ornrocha.rencoder.ffmpegWrapper.releases.containers.FFmpegReleaseInfo;
import pt.ornrocha.rencoder.ffmpegWrapper.releases.containers.ListOfFFmpegReleases;

public class BtbnReleasesCollector implements IReleasesCollector {

  static BtbnReleasesCollector instance;
  private ListOfFFmpegReleases listreleases = null;
  private GHRepository repository = null;
  private static String PROVIDER = "BtbN";
  private static String PROVIDERURL = "https://github.com/BtbN/FFmpeg-Builds";

  public static BtbnReleasesCollector getInstance() throws Exception {
    if (instance == null)
      instance = new BtbnReleasesCollector();
    instance.collect();
    return instance;
  }

  public BtbnReleasesCollector() {}

  @Override
  public void collect() throws Exception {
    listreleases = new ListOfFFmpegReleases();
    GitHub gitHub = GitHub.connectAnonymously();
    this.repository = gitHub.getRepository("BtbN/FFmpeg-Builds");
    processReleasesByOS();
  }

  private PagedIterable<GHAsset> getListAssets() throws IOException {
    GHRelease lastrelease = repository.getLatestRelease();
    return lastrelease.listAssets();
  }

  private void processReleasesByOS() throws IOException {
    PagedIterable<GHAsset> listassets = getListAssets();
    for (GHAsset asset : listassets) {
      checkOsRelease(asset.getName(), asset.getBrowserDownloadUrl(), asset.getSize());
    }
  }

  private void checkOsRelease(String name, String url, long size) {

    Pattern pattern = Pattern.compile("(win64|linux64)-(gpl|lgpl)(-\\d.\\d)*.(zip|tar.xz)");
    Matcher m = pattern.matcher(name);

    if (m.find()) {
      if (name.contains("win64"))
        listreleases
            .add(FFmpegReleaseInfo.createWindowsRelease(name, url, size, PROVIDER, PROVIDERURL));
      else if (name.contains("linux64"))
        listreleases
            .add(FFmpegReleaseInfo.createLinuxRelease(name, url, size, PROVIDER, PROVIDERURL));
    }
  }


  @Override
  public ListOfFFmpegReleases getListOfReleases() {
    return listreleases;
  }


  public static void main(String[] args) throws Exception {
    BtbnReleasesCollector.getInstance();
  }



}
