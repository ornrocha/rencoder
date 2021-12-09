package pt.ornrocha.rencoder.ffmpegWrapper.releases.containers;

public class FFmpegReleaseInfo {

  private String name;
  private String url;
  private long size;
  private FFmpegReleaseOsType ostype = FFmpegReleaseOsType.Linux;
  private String provider;
  private String providerurl;



  public FFmpegReleaseInfo() {

  }


  public FFmpegReleaseInfo(String name, String url, long size, FFmpegReleaseOsType ostype,
      String provider, String providerurl) {
    this.name = name;
    this.url = url;
    this.size = size;
    this.ostype = ostype;
    this.provider = provider;
    this.providerurl = providerurl;
  }

  public static FFmpegReleaseInfo createWindowsRelease(String name, String url, long size,
      String provider, String providerurl) {
    return new FFmpegReleaseInfo(name, url, size, FFmpegReleaseOsType.Windows, provider,
        providerurl);
  }

  public static FFmpegReleaseInfo createLinuxRelease(String name, String url, long size,
      String provider, String providerurl) {
    return new FFmpegReleaseInfo(name, url, size, FFmpegReleaseOsType.Linux, provider, providerurl);
  }


  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public String getUrl() {
    return url;
  }


  public void setUrl(String url) {
    this.url = url;
  }


  public long getSize() {
    return size;
  }


  public void setSize(long size) {
    this.size = size;
  }


  public FFmpegReleaseOsType getOstype() {
    return ostype;
  }


  public void setOstype(FFmpegReleaseOsType ostype) {
    this.ostype = ostype;
  }


  public String getProvider() {
    return provider;
  }


  public void setProvider(String provider) {
    this.provider = provider;
  }


  public String getProviderurl() {
    return providerurl;
  }


  public void setProviderurl(String providerurl) {
    this.providerurl = providerurl;
  }



}
