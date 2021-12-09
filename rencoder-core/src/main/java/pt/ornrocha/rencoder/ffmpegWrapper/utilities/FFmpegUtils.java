package pt.ornrocha.rencoder.ffmpegWrapper.utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.filechooser.FileSystemView;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.configurations.ConfigureFFmpegExecutablePath;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.general.FFmpegLogLevel;
import pt.ornrocha.rencoder.helpers.osystem.OSystem;
import pt.ornrocha.rencoder.helpers.props.fields.StaticGlobalFields;
import pt.ornrocha.rencoder.helpers.props.managers.auxiliar.EncodingPropsAuxiliar;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;
import pt.ornrocha.rencoder.mediafiles.setfiles.foldersandfiles.ListFiles;

public final class FFmpegUtils {

  /**
   * Gets the defined codec library to encode audio format.
   *
   * @param format the format
   * @return the codec librarytoencodeaudioformat
   */
  public static String getCodecLibrarytoencodeaudioformat(String format) {

    PropertiesConfiguration prop =
        PropertiesWorker.loadPropertiesRelativePath(StaticGlobalFields.RENCODERCONFIGFILE);
    return (String) prop.getProperty(format);

  }

  /**
   * Gets the defined codec library to encode video format.
   *
   * @param format the format
   * @param defaultcodec the defaultcodec
   * @return the codec librarytoencodevideoformat
   */
  public static String getCodecLibrarytoencodevideoformat(String format, String defaultcodec) {

    PropertiesConfiguration prop =
        PropertiesWorker.loadPropertiesRelativePath(StaticGlobalFields.RENCODERCONFIGFILE);
    if (prop.containsKey(format)) {
      if (!prop.getProperty(format).toString().isEmpty()) {

        return (String) prop.getProperty(format);
      } else
        return defaultcodec;
    } else
      return defaultcodec;

  }

  /**
   * Gets the vorbis vbr bitratre table.
   *
   * @return the vorbis vbr bitratre table
   */
  public static HashMap<Integer, String> getVorbisVBRbitratreTable() {

    HashMap<Integer, String> vbrmap = new HashMap<>();
    vbrmap.put(0, "64");
    vbrmap.put(1, "80");
    vbrmap.put(2, "96");
    vbrmap.put(3, "112");
    vbrmap.put(4, "128");
    vbrmap.put(5, "160");
    vbrmap.put(6, "192");
    vbrmap.put(7, "224");
    vbrmap.put(8, "256");
    vbrmap.put(9, "320");
    vbrmap.put(10, "500");
    return vbrmap;
  }

  /**
   * Gets the ffmpeg exe path.
   *
   * @return the ffmpeg exe path
   */
  public static String getFFmpegExePath() {
    return PropertiesWorker.getStringProperty(StaticGlobalFields.RENCODERCONFIGFILE,
        StaticGlobalFields.FFMPEGPATH);
  }

  public static String getDefaultFFmpegExe() {

    String fullpath = new File(StaticGlobalFields.FFMPEGFOLDERPATH).getAbsolutePath();
    File defaultffmpegfolder = new File(fullpath);

    if (!defaultffmpegfolder.exists())
      defaultffmpegfolder.mkdir();
    defaultffmpegfolder.setReadable(true);
    defaultffmpegfolder.setWritable(true);
    ArrayList<String> files = ListFiles.getFilesInDirectory(fullpath, true);
    String ffmpegfilepath = null;
    for (int i = 0; i < files.size(); i++) {
      String filename = FilenameUtils.getName(files.get(i).toLowerCase());
      if (OSystem.isWindows() && filename.equals("default_ffmpeg.exe")) {
        ffmpegfilepath = files.get(i);
        setDefaultFFmpegBin(ffmpegfilepath);
        break;
      } else if (OSystem.isLinux() && filename.equals("default_ffmpeg")) {
        ffmpegfilepath = files.get(i);
        setDefaultFFmpegBin(ffmpegfilepath);
        break;
      } else
        Logger.error("The default ffmpeg bin was not found!!!");

    }

    return ffmpegfilepath;
  }

  private static void setDefaultFFmpegBin(String ffmpegfilepath) {
    File ffmpeg = new File(ffmpegfilepath);
    ffmpeg.setExecutable(true);
    ffmpeg.setWritable(true);
    ffmpeg.setReadable(true);

    checkForFontConfigFolders(ffmpegfilepath, false);
    PropertiesWorker.ChangePropertiesParam(StaticGlobalFields.RENCODERCONFIGFILE,
        StaticGlobalFields.FFMPEGPATH, ffmpegfilepath);
  }

  public static ArrayList<String> getFFmpegVersionsInternal() {
    ArrayList<String> respaths = new ArrayList<>();
    String fullpath = new File(StaticGlobalFields.FFMPEGFOLDERPATH).getAbsolutePath();
    File defaultffmpegfolder = new File(fullpath);
    if (defaultffmpegfolder.exists()) {
      ArrayList<String> files = ListFiles.getFilesInDirectory(fullpath, true);

      for (int i = 0; i < files.size(); i++) {
        String path = files.get(i).toLowerCase();
        String basename = FilenameUtils.getBaseName(path);
        if (basename.contains("ffmpeg")) {
          respaths.add(files.get(i));
        }
      }
    }
    return respaths;
  }

  public static String getVideoSampleFilepath() {

    String videodemopath = PropertiesWorker.getStringProperty(StaticGlobalFields.RENCODERCONFIGFILE,
        StaticGlobalFields.VIDEOSAMPLEPATH);
    if (videodemopath != null) {
      File demomoviefile = new File(videodemopath);
      if (demomoviefile.exists())
        return videodemopath;
      else {
        PropertiesWorker.ChangePropertiesParam(StaticGlobalFields.RENCODERCONFIGFILE,
            StaticGlobalFields.VIDEOSAMPLEPATH, getMovieDemo());
        return getMovieDemo();
      }

    } else {
      PropertiesWorker.ChangePropertiesParam(StaticGlobalFields.RENCODERCONFIGFILE,
          StaticGlobalFields.VIDEOSAMPLEPATH, getMovieDemo());
      return getMovieDemo();
    }
  }


  public static String getSubtitleSampleFilepath() {

    String subdemopath = PropertiesWorker.getStringProperty(StaticGlobalFields.RENCODERCONFIGFILE,
        StaticGlobalFields.SUBTITLESAMPLEPATH);
    if (subdemopath != null) {
      File submoviefile = new File(subdemopath);
      if (submoviefile.exists())
        return subdemopath;
      else {
        PropertiesWorker.ChangePropertiesParam(StaticGlobalFields.RENCODERCONFIGFILE,
            StaticGlobalFields.SUBTITLESAMPLEPATH, getDemoSubtitle().getAbsolutePath());
        return getDemoSubtitle().getAbsolutePath();
      }

    } else {
      PropertiesWorker.ChangePropertiesParam(StaticGlobalFields.RENCODERCONFIGFILE,
          StaticGlobalFields.SUBTITLESAMPLEPATH, getDemoSubtitle().getAbsolutePath());
      return getDemoSubtitle().getAbsolutePath();
    }
  }


  public static void checkForFontConfigFolders(String ffmpegfilepath, boolean issystemversion) {
    try {
      ConfigureFFmpegExecutablePath.checkifexistFontsConf(ffmpegfilepath, issystemversion);
    } catch (IOException e) {
      Logger.error(e);
    }
  }

  public static FFmpegLogLevel getSettingsFFmpegLogLevel() {
    PropertiesConfiguration prop =
        PropertiesWorker.loadPropertiesRelativePath(StaticGlobalFields.RENCODERCONFIGFILE);
    if (prop.containsKey(StaticGlobalFields.LOGLEVELKEY)) {
      if (!prop.getProperty(StaticGlobalFields.LOGLEVELKEY).toString().isEmpty()) {
        String level = (String) prop.getProperty(StaticGlobalFields.LOGLEVELKEY);
        return EncodingPropsAuxiliar.getLogLevel(level);
      }
    }

    return null;
  }

  public static boolean isFFmpegWriteLog() {
    PropertiesConfiguration prop =
        PropertiesWorker.loadPropertiesRelativePath(StaticGlobalFields.RENCODERCONFIGFILE);
    if (prop.containsKey(StaticGlobalFields.LOGSAVEPROKEY)) {
      if (!prop.getProperty(StaticGlobalFields.LOGSAVEPROKEY).toString().isEmpty()) {
        String save = (String) prop.getProperty(StaticGlobalFields.LOGSAVEPROKEY);
        if (save.toLowerCase().equals("true"))
          return true;
      } else
        return false;
    }
    return false;
  }

  public static void checkLogsFolder() {
    String logfolder = new File(StaticGlobalFields.LOGSFOLDER).getAbsolutePath();

    File folder = new File(logfolder);
    if (!folder.exists())
      folder.mkdir();
  }



  public static String getFFmpegFontsConfiguredTagFile() {
    return new File(StaticGlobalFields.CONFFFMPEGFONTSTAG).getAbsolutePath();

  }

  public static void createFFmpegFontsConfiguredTagFile() throws IOException {
    File f = new File(getFFmpegFontsConfiguredTagFile());
    if (!f.exists()) {
      f.createNewFile();
    }
  }

  public static void removeFFmpegFontsConfiguredTagFile() throws IOException {
    File f = new File(getFFmpegFontsConfiguredTagFile());
    if (f.exists()) {
      f.delete();
    }
  }

  public static double convertTimetoseconds(String time) {
    String[] hms = time.split(":");
    double totalSecs = Integer.parseInt(hms[0]) * 3600 + Integer.parseInt(hms[1]) * 60
        + Double.parseDouble(hms[2]);
    return totalSecs;

  }

  public static String getMovieDemo() {

    String filepath = FilenameUtils.concat(OSystem.getCurrentDir(), StaticGlobalFields.MOVIEDEMO);
    if (new File(filepath).exists())
      return filepath;
    return null;
  }



  public static File getDemoSubtitle() {
    String subpath = new File(StaticGlobalFields.SUBDEMO).getAbsolutePath();
    return new File(subpath);
  }

  public static String copyDemoToTempFolder(String filename) throws IOException {
    // String outfilepath=getDemoAlternativePath(filename);
    String outfilepath = FilenameUtils
        .concat(FileSystemView.getFileSystemView().getDefaultDirectory().getPath(), filename);
    FileUtils.copyFile(new File(getMovieDemo()), new File(outfilepath));
    PropertiesWorker.ChangePropertiesParam(StaticGlobalFields.RENCODERCONFIGFILE,
        StaticGlobalFields.VIDEOSAMPLEPATH, outfilepath);
    return outfilepath;
  }

  public static void configVideoDemoAlternativeFolder() throws IOException {
    String filepath = copyDemoToTempFolder("demo.mp4");
  }


  public static String getDemoAlternativePath(String filename) {
    String tempdir = FilenameUtils
        .concat(FileSystemView.getFileSystemView().getDefaultDirectory().getPath(), ".rencoder");
    File dirfile = new File(tempdir);
    if (!dirfile.exists()) {
      dirfile.mkdirs();
    }
    return FilenameUtils.concat(tempdir, filename);
  }

}
