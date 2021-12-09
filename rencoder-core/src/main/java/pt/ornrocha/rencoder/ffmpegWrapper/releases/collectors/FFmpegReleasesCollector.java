package pt.ornrocha.rencoder.ffmpegWrapper.releases.collectors;

import java.util.ArrayList;

import pt.ornrocha.rencoder.ffmpegWrapper.releases.containers.ListOfFFmpegReleases;

public class FFmpegReleasesCollector {


  private ArrayList<IReleasesCollector> collectors;
  private ListOfFFmpegReleases collectedreleases;


  public static FFmpegReleasesCollector getInstance() {
    return new FFmpegReleasesCollector();
  }

  public static FFmpegReleasesCollector defaultCollector() {
    FFmpegReleasesCollector collector = getInstance();
    collector.addCollector(new BtbnReleasesCollector());
    collector.addCollector(new JVSReleasesCollector());

    return collector;
  }


  private FFmpegReleasesCollector() {
    collectors = new ArrayList<IReleasesCollector>();
    collectedreleases = new ListOfFFmpegReleases();
  }

  public FFmpegReleasesCollector addCollector(IReleasesCollector collector) {
    if (!collectors.contains(collector))
      collectors.add(collector);
    return this;
  }

  public ListOfFFmpegReleases collect() {

    if (collectors.size() > 0) {
      for (IReleasesCollector collector : collectors) {
        try {
          collector.collect();
          collectedreleases.addAll(collector.getListOfReleases());
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

    }

    return collectedreleases;

  }



}
