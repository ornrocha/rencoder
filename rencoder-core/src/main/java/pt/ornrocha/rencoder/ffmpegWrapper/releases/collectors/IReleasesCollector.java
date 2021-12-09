package pt.ornrocha.rencoder.ffmpegWrapper.releases.collectors;

import pt.ornrocha.rencoder.ffmpegWrapper.releases.containers.ListOfFFmpegReleases;

public interface IReleasesCollector {


  void collect() throws Exception;

  ListOfFFmpegReleases getListOfReleases();

}
