/*
 * Copyright 2014
 *
 * This is free software: you can redistribute it and/or modify it under the terms of the GNU Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 * 
 * This code is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Public License for more details.
 * 
 * You should have received a copy of the GNU Public License along with this code. If not, see
 * http://www.gnu.org/licenses/
 * 
 * Created by Orlando Rocha
 */
package pt.ornrocha.rencoder.ffmpegWrapper.configurations;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;

import org.tinylog.Logger;

import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.helpers.osystem.OSystem;
import pt.ornrocha.rencoder.helpers.props.fields.StaticGlobalFields;

// TODO: Auto-generated Javadoc
/**
 * The Class ConfigureFFmpegExecutablePath.
 */
public class ConfigureFFmpegExecutablePath {

  /** The ffmpegpath. */
  private String ffmpegpath = null;

  /** The importinside. */
  private boolean importinside = false;
  private boolean issystemversion = false;

  private ArrayList<String> mandatoryffmpegFeatures = new ArrayList(Arrays.asList("ass"));
  /** The errors. */
  private ArrayList<String> errors = null;

  /** The checkedffmpegfeatures. */
  private IndexedHashMap<String, Boolean> checkedffmpegfeatures = null;
  
  private ResourceBundle rb;

  /**
   * Instantiates a new configure ffmpeg executable path.
   *
   * @param filepath the filepath
   * @param importinternal the importinternal
   * @throws InterruptedException
   * @throws IOException
   */
  public ConfigureFFmpegExecutablePath(String filepath, boolean importinternal,
		  boolean issystemversion, ResourceBundle rb) throws InterruptedException, IOException {
	  this.ffmpegpath = filepath;
	  this.importinside = importinternal;
	  this.issystemversion = issystemversion;
	  this.rb=rb;

	  validateAndSetFFmpegExecutable();

  }

  /**
   * Gets the errors.
   *
   * @return the errors
   */
  public ArrayList<String> getErrors() {
    return this.errors;
  }

  /**
   * Validate and set ffmpeg executable.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   * @throws InterruptedException
   */
  private void validateAndSetFFmpegExecutable() throws IOException, InterruptedException {

    if (!isvalidOSystemExecutable()) {
      errors = new ArrayList<>();

      if (rb != null) {
    	  String msg=LangTools.getResourceBundleWordLanguage(rb,"Invalid FFmpeg Executable for this Operating System.","ffmpegconfgui.ffmpeg.invalid");
    	  errors.add(msg);
    	  Logger.error(msg);
      }
      else {
    	 String msg="Invalid FFmpeg Executable for this Operating System" + "\n";
    	 errors.add(msg); 
    	 Logger.error(msg);
      }
      errors.addAll(getURLFFMPEG());
      
    } else if (!validFFmpegExecutable()) {
      errors = new ArrayList<>();
      errors.addAll(declareErrors());
      errors.addAll(getURLFFMPEG());
    } else {
      if (importinside)
        copyFFmpegToInternalDirectory();

      String fontspath = new File(this.ffmpegpath).getAbsolutePath();

      checkifexistFontsConf(fontspath, issystemversion);

      FFmpegManager.getInstance().setFFmpegPath(ffmpegpath);
    }

  }

  /**
   * Copy ffmpeg to internal directory.
   */
  private void copyFFmpegToInternalDirectory() {
    try {
      Path filepath = Paths.get(ffmpegpath);
      String name = filepath.getFileName().toString();
      String newdest = null;

      String internencoderfolder = new File(StaticGlobalFields.FFMPEGFOLDERPATH).getAbsolutePath();
      File ffmpegfuturefolder = new File(internencoderfolder);
      ffmpegfuturefolder.setWritable(true);

      newdest = internencoderfolder + OSystem.getSystemSeparator() + name;

      File orig = new File(ffmpegpath);
      File dest = new File(newdest);

      if (dest.exists())
        dest.delete();

      Files.copy(orig.toPath(), dest.toPath());
      this.ffmpegpath = newdest;
      Logger.info("FFmpeg binary was copied from " + orig.getAbsolutePath() + " to "
          + dest.getAbsolutePath());
    } catch (IOException e) {
      Logger.error(e);
    }

  }

  /**
   * Check if exist the fonts configuration file (only in windows)
   *
   * @param ffmpegfilepath the ffmpegpath
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static void checkifexistFontsConf(String ffmpegfilepath, boolean issystemversion)
      throws IOException {

    Path filepath = Paths.get(ffmpegfilepath);

    String dir = filepath.getParent().toString();
    String fontdir = null;
    if (OSystem.isWindows())
      fontdir = dir + OSystem.getSystemSeparator() + "fonts";
    else
      fontdir = dir + OSystem.getSystemSeparator() + "fontconfig";

    String fontfilepath = fontdir + OSystem.getSystemSeparator() + "fonts.conf";

    File fontsdir = new File(fontdir);
    File fontfile = new File(fontfilepath);
    String fontstocopypath = null;

    fontstocopypath = new File(getFontsConfigFile()).getAbsolutePath();

    if (!fontfile.exists() && !issystemversion) {
      fontsdir.mkdir();
      Files.copy(new File(fontstocopypath).toPath(), fontfile.toPath());
    }
  }

  private static String getFontsConfigFile() {
    String respath = "";
    if (OSystem.isLinux())
      respath = StaticGlobalFields.FONTSCONFIGPATH + OSystem.getSystemSeparator()
          + StaticGlobalFields.LINUXFONTSFILE;
    else if (OSystem.isWindows())
      respath = StaticGlobalFields.FONTSCONFIGPATH + OSystem.getSystemSeparator()
          + StaticGlobalFields.WINDOWSFONTSFILE;
    else if (OSystem.isMacOS())
      respath = StaticGlobalFields.FONTSCONFIGPATH + OSystem.getSystemSeparator()
          + StaticGlobalFields.MACOSFONTSFILE;
    return respath;
  }

  /**
   * Declare errors.
   *
   * @return the array list
   */
  private ArrayList<String> declareErrors() {
    ArrayList<String> errors = new ArrayList<>();
    if (checkedffmpegfeatures.containsValue(false))
      errors.add("The following necessary features are missing:" + "\n");

    for (Map.Entry<String, Boolean> param : checkedffmpegfeatures.entrySet()) {
      if (!param.getValue()) {
        if (param.getKey().equals("libx264"))
          errors.add("libx264" + "\n");
        else if (param.getKey().equals("ass"))
          errors.add("ass" + "\n");
      }

    }

    return errors;

  }

  /**
   * Gets the urlffmpeg.
   *
   * @return the urlffmpeg
   */
  // https://ffmpeg.zeranoe.com/builds/win64/static/ffmpeg-latest-win64-static.zip
  // https://johnvansickle.com/ffmpeg/releases/ffmpeg-release-amd64-static.tar.xz
  private ArrayList<String> getURLFFMPEG() {
    ArrayList<String> errors = new ArrayList<>();
    String basemsg="Get Static Version On:";
    if(rb!=null)
    	basemsg=LangTools.getResourceBundleWordLanguage(rb,"Get Static Version On:","ffmpegconfgui.ffmpeg.get.version");
    if (OSystem.isLinux()) {
      errors.add("\n");
      errors.add(basemsg+" https://johnvansickle.com/ffmpeg/");
    }
    if (OSystem.isWindows()) {
      errors.add("\n");
      errors.add(basemsg+" http://ffmpeg.zeranoe.com/builds/");
    }
    if (OSystem.isMacOS()) {
      errors.add("\n");
      errors.add(basemsg+" https://www.evermeet.cx/ffmpeg/");
    }

    return errors;

  }

  /**
   * Valid f fmpeg executable.
   *
   * @return true, if successful
   * @throws IOException Signals that an I/O exception has occurred.
   * @throws InterruptedException
   */
  private boolean validFFmpegExecutable() throws IOException, InterruptedException {
    boolean valid = true;

    FFmpegParametersChecker.setFFmpegExecutable(ffmpegpath);

    checkedffmpegfeatures =
        FFmpegParametersChecker.checkMandatoryCodecs(ffmpegpath, mandatoryffmpegFeatures);
    if (!checkifallParamTrue(checkedffmpegfeatures))
      valid = false;

    return valid;
  }

  /**
   * Checks if is valid o system executable.
   *
   * @return true, if is valid o system executable
   */
  private boolean isvalidOSystemExecutable() {
	  boolean valid = true;

	  try {

		  FFmpegParametersChecker.setFFmpegExecutable(ffmpegpath);

		  Process proc = Runtime.getRuntime().exec(ffmpegpath);
		  InputStream inStream = proc.getErrorStream();
		  ArrayList<String> check = new ArrayList<>();
		  check.add("cannot");
		  FFmpegInputErrorChecker errorchecker = new FFmpegInputErrorChecker(inStream, check, false);
		  Thread t = new Thread(errorchecker);

		  t.run();

		  boolean existerrors = errorchecker.existerrors();
		  if (existerrors)
			  valid = false;

	  } catch (IOException e) {
		  Logger.error(e);
		  valid = false;
	  }

	  return valid;
  }

  /**
   * Checkifall param true.
   *
   * @param parameters the parameters
   * @return true, if successful
   */
  private boolean checkifallParamTrue(IndexedHashMap<String, Boolean> parameters) {

    for (Map.Entry<String, Boolean> param : parameters.entrySet()) {
      if (!param.getValue())
        return false;
    }

    return true;
  }

}
