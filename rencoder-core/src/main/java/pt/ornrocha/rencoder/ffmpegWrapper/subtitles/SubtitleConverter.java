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
package pt.ornrocha.rencoder.ffmpegWrapper.subtitles;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import org.apache.any23.encoding.TikaEncodingDetector;
import org.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.configurations.FFmpegInputErrorChecker;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.subtitles.SubEncodings;
import pt.ornrocha.rencoder.ffmpegWrapper.utilities.FFmpegUtils;
import pt.ornrocha.rencoder.helpers.osystem.OSystem;
import pt.ornrocha.rencoder.helpers.props.fields.StaticFFmpegFields;
import pt.ornrocha.rencoder.helpers.props.fields.StaticGlobalFields;
import pt.ornrocha.rencoder.mediafiles.files.auxiliar.ProcessFilesAux;
import pt.ornrocha.rencoder.mediafiles.files.containers.base.Subtitlefile;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.SubtitleInfoContainer;
import pt.ornrocha.rencoder.mediafiles.setfiles.processfiles.ProcessFiles;

// TODO: Auto-generated Javadoc
/**
 * The Class SubtitleConverter.
 */
public class SubtitleConverter {

  /** The subtitle. */
  private Subtitlefile subtitle = null;

  /** The encoding settings. */
  private SubtitleInfoContainer encodingSettings = null;

  /** The tempsubpath. */
  private String tempsubpath;

  private static String DEFAULTSECONDARYCOLOUR = "&H000000FF";
  private static String DEFAULTCOLOUR = "&H00000000";

  /**
   * Instantiates a new subtitle converter.
   *
   * @param sub the sub
   * @param encosettings the encosettings
   */
  public SubtitleConverter(Subtitlefile sub, SubtitleInfoContainer encosettings) {
    this.subtitle = sub;
    this.encodingSettings = encosettings;
  }

  /**
   * Gets the temp sub path.
   *
   * @return the temp sub path
   */
  public String getTempSubPath() {
    return this.tempsubpath;
  }

  /**
   * Gets the ffmpeg subtitle encoding commands.
   *
   * @return the ffmpeg subtitle encoding commands
   */
  public ArrayList<String> getFFmpegSubtitleEncodingCommands(String alternativedir) {

    if (alternativedir != null)
      setAlternativeOutSubDir(alternativedir);
    else
      setTempSubDirectory();

    ArrayList<String> subcommand = null;
    try {
      checkOriginalSubtitleCharEncoding();

      if (!subtitle.getExtension().toLowerCase().equals("ass")) {
        convertSubtitletoAss();
        changeSubtitleProperties();
      } else {
        copyOriginalSub();
        changeSubtitleProperties();
      }

      subcommand = new ArrayList<>();
      subcommand.add(StaticFFmpegFields.FILTERFFMPEGCMD);
      if (OSystem.isWindows())
        subcommand.add(StaticGlobalFields.QUOTE + "ass=" + convertTempSubPathInWindows(tempsubpath)
            + StaticGlobalFields.QUOTE);
      else
        subcommand.add("ass='" + convertTempSubPathInWindows(tempsubpath) + "'");
    } catch (Exception e) {
      Logger.error(e);
    }

    return subcommand;
  }

  /**
   * Convert temp sub path in windows.
   *
   * @param tempsubpath the tempsubpath
   * @return the string
   */
  private String convertTempSubPathInWindows(String tempsubpath) {

    String newstr = "";
    String dots = ":";
    String newformatdots = "\\\\\\\\:";

    String slash = "\\\\";
    String newSlash = "\\\\\\\\\\\\\\\\";

    tempsubpath = tempsubpath.replaceAll(slash, newSlash);
    newstr = tempsubpath.replaceAll(dots, newformatdots);

    return newstr;

  }

  /**
   * Sets the temp sub directory.
   */
  private void setTempSubDirectory() {
    String tempsubsfolder = OSystem.getSubtitlesTempFolder();

    this.tempsubpath = tempsubsfolder + OSystem.getSystemSeparator()
        + subtitle.getNameWithoutExtension() + "_temp.ass";

    ProcessFilesAux.deleteIfExistTempFile(this.tempsubpath);
  }

  private void setAlternativeOutSubDir(String outdir) {

    this.tempsubpath = ProcessFiles.checkIfFileExistsandReturnNewname(outdir,
        subtitle.getNameWithoutExtension(), subtitle.getExtension(), 0, false);
  }

  /**
   * Copy original subtitle.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   */
  private void copyOriginalSub() throws IOException {
    File f = new File(tempsubpath);
    if (f.exists()) {
      f.delete();
    }
    Files.copy(new File(subtitle.getFilePath()).toPath(), new File(tempsubpath).toPath());
  }

  /**
   * Convert subtitle to ass format.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   * @throws InterruptedException the interrupted exception
   */
  private void convertSubtitletoAss() throws IOException, InterruptedException {

    ProcessBuilder proc = SubtitlesUtilities.convertToAss(
        encodingSettings.getSubsEncoding().toString(), subtitle.getFilePath(), tempsubpath);
    Process process = proc.start();
    InputStream inStream = process.getErrorStream();
    ArrayList<String> check = new ArrayList<>();
    check.add("Unable to recode subtitle event");
    FFmpegInputErrorChecker errorchecker = new FFmpegInputErrorChecker(inStream, check, false);
    Thread t = new Thread(errorchecker);

    t.run();

    boolean existerrors = errorchecker.existerrors();
    if (existerrors) {
      subtitle.selectToUse(false);
      process.destroyForcibly();
      throw new InterruptedException("Unable to recode subtitle due to encoding incompatibility.");
    } else
      process.waitFor();

    /*
     * final BufferedReader reader = new BufferedReader( new
     * InputStreamReader(process.getErrorStream())); String line = null; while ((line =
     * reader.readLine()) != null) { System.out.println(line); }
     * 
     * reader.close();
     */

  }

  private void checkOriginalSubtitleCharEncoding() {

    try {

      if (encodingSettings.getSubsEncoding().toString().toLowerCase()
          .equals(SubEncodings.AUTO.toString().toLowerCase())) {

        String encodingname = new TikaEncodingDetector()
            .guessEncoding(new FileInputStream(new File(subtitle.getFilePath())));
        encodingSettings.setSubsEncoding(encodingname.toUpperCase());
      }

    } catch (Exception e) {
      Logger.error(e);
    }
  }

  /**
   * Change subtitle properties.
   */
  private void changeSubtitleProperties() {

    try {
      String assprops = SubtitlesUtilities.FindAssSubtitleProperties(tempsubpath);

      // Name, Fontname, Fontsize, PrimaryColour, SecondaryColour, OutlineColour,
      // BackColour, Bold, Italic, Underline, StrikeOut, ScaleX, ScaleY, Spacing,
      // Angle, BorderStyle, Outline, Shadow, Alignment, MarginL, MarginR, MarginV,
      // Encoding
      String[] arrayprops = assprops.split(",");

      String newassprops = setNewAssProps(arrayprops);
      SubtitlesUtilities.ReplaceAssSubtitleProperties(tempsubpath, tempsubpath,
          encodingSettings.getSubsEncoding().toString(), assprops, newassprops);
    } catch (Exception e) {
      Logger.error(e);
    }
  }

  private String setNewAssProps(String[] oldprops) {
    String newprops = null;
    if (oldprops.length == 19)
      newprops = setAssPropsForOldLibass(oldprops);
    else if (oldprops.length == 23)
      newprops = setAssPropsForNewLibass(oldprops);

    return newprops;
  }

  /**
   * Sets the new ass properties.
   *
   * @param oldprops the oldprops
   * @return the string
   */
  private String setAssPropsForNewLibass(String[] oldprops) {

    String newprops = "";

    newprops += oldprops[0] + ","; // Name
    newprops += encodingSettings.getSubsFontName() + ","; // Fontname
    newprops += String.valueOf(encodingSettings.getSubsFontSize()) + ","; // Fontsize
    newprops += encodingSettings.getSubsColor().getSubtitleColorCode() + ","; // PrimaryColour

    // newprops+=oldprops[4]+","; // SecondaryColour
    newprops += DEFAULTSECONDARYCOLOUR + ","; // SecondaryColour
    // newprops+=oldprops[5]+","; // OutlineColour
    newprops += DEFAULTCOLOUR + ","; // OutlineColour

    // newprops+=oldprops[6]+","; // BackColour;
    newprops += DEFAULTCOLOUR + ","; // BackColour;
    // newprops+="FFFFFF"+",";
    if (encodingSettings.isSubsUseBold())
      newprops += 1 + ",";
    else
      newprops += 0 + ",";
    if (encodingSettings.isSubsUseItalic())
      newprops += 1 + ",";
    else
      newprops += 0 + ",";

    newprops += oldprops[9] + ","; // Underline
    newprops += oldprops[10] + ",";// StrikeOut
    newprops += oldprops[11] + ",";// ScaleX
    newprops += oldprops[12] + ",";// ScaleY
    newprops += oldprops[13] + ",";// Spacing
    newprops += oldprops[14] + ",";// Angle
    newprops += encodingSettings.getSubsBorderStyle().getBorderStyleID() + ","; // BorderStyle
    newprops += encodingSettings.getSubsOutline() + ","; // Outline
    newprops += encodingSettings.getSubsShadow() + ","; // Shadow
    newprops += encodingSettings.getSubsAlignment().getAlignmentFFmpegID() + ","; // Alignment
    newprops += oldprops[19] + ","; // MarginL
    newprops += oldprops[20] + ","; // MarginR
    newprops += oldprops[21] + ","; // MarginV
    newprops += oldprops[22]; // Encoding

    return newprops;
  }

  private String setAssPropsForOldLibass(String[] oldprops) {

    String newprops = "";

    newprops += oldprops[0] + ","; // Name
    newprops += encodingSettings.getSubsFontName() + ","; // Fontname
    newprops += String.valueOf(encodingSettings.getSubsFontSize()) + ","; // Fontsize
    newprops += encodingSettings.getSubsColor().getSubtitleColorCode() + ","; // PrimaryColour
    newprops += oldprops[4] + ","; // SecondaryColour
    newprops += oldprops[5] + ","; // OutlineColour
    newprops += oldprops[6] + ","; // BackColour;
    if (encodingSettings.isSubsUseBold())
      newprops += 1 + ",";
    else
      newprops += 0 + ",";
    if (encodingSettings.isSubsUseItalic())
      newprops += 1 + ",";
    else
      newprops += 0 + ",";

    newprops += oldprops[9] + ","; // Underline
    newprops += encodingSettings.getSubsBorderStyle().getBorderStyleID() + ","; // BorderStyle
    newprops += encodingSettings.getSubsOutline() + ","; // Outline
    newprops += encodingSettings.getSubsShadow() + ","; // Shadow
    newprops += encodingSettings.getSubsAlignment().getAlignmentFFmpegID() + ","; // Alignment
    newprops += oldprops[14] + ","; // MarginL
    newprops += oldprops[15] + ","; // MarginR
    newprops += oldprops[16] + ","; // MarginV
    newprops += oldprops[17] + ","; // AlphaLevel
    newprops += oldprops[18]; // Encoding

    return newprops;
  }

  public static boolean containsAssParameter(ArrayList<String> cmds) {

    for (String string : cmds) {
      if (string.contains("ass="))
        return true;
    }

    return false;
  }

  public static void makeSubtitlePicturePreview(String moviepath, ArrayList<String> subcmd,
      String outpictpath) {
    ProcessBuilder proc = null;

    try {

      ArrayList<String> cmd = new ArrayList<>();
      cmd.add(FFmpegUtils.getFFmpegExePath());
      cmd.add(StaticFFmpegFields.input);
      if (OSystem.isWindows()) {
        cmd.add(StaticGlobalFields.QUOTE + moviepath + StaticGlobalFields.QUOTE);
      } else {
        cmd.add(moviepath);
      }

      cmd.add(StaticFFmpegFields.TIMESTARTAG);
      cmd.add("00:00:05");
      cmd.addAll(subcmd);
      cmd.add(StaticFFmpegFields.overwrite_output_file);
      cmd.add(StaticFFmpegFields.VFRAMESTAG);
      cmd.add(String.valueOf(1));
      if (OSystem.isWindows()) {
        cmd.add(StaticGlobalFields.QUOTE + outpictpath + StaticGlobalFields.QUOTE);
      } else {
        cmd.add(outpictpath);
      }

      proc = new ProcessBuilder(cmd);

    } catch (Exception e) {
      // TODO Auto-generated catch block
      Logger.error(e);
    }

    Process process = null;
    try {
      if (OSystem.isMacOS())
        setMacFontconfigPath(proc);

      process = proc.start();
      process.waitFor();
    } catch (IOException | InterruptedException e) {
      Logger.error(e);
    }

  }

  static void setMacFontconfigPath(ProcessBuilder pb) {
    String path = FFmpegUtils.getFFmpegExePath();
    String p = Paths.get(path).getParent().toString();
    String fc = p + OSystem.getSystemSeparator() + "fontconfig";
    Map<String, String> env = pb.environment();
    env.put("FONTCONFIG_PATH", fc);

  }

}
