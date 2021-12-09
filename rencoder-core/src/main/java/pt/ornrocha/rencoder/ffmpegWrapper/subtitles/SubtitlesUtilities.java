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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.utilities.FFmpegUtils;
import pt.ornrocha.rencoder.helpers.osystem.OSystem;
import pt.ornrocha.rencoder.helpers.props.fields.StaticFFmpegFields;
import pt.ornrocha.rencoder.helpers.props.fields.StaticGlobalFields;

// TODO: Auto-generated Javadoc
/**
 * The Class SubtitlesUtilities.
 */
public class SubtitlesUtilities {

  /**
   * Find ass subtitle properties.
   *
   * @param subpath the subpath
   * @return the string
   */
  public static String FindAssSubtitleProperties(String subpath) {

    String assprop = null;
    FileReader in = null;
    BufferedReader br = null;
    String line = null;
    Pattern regex = Pattern.compile("^\\s*Style:\\s*(.*)");
    Matcher regexMatcher = null;
    try {
      in = new FileReader(subpath);
      br = new BufferedReader(in);
      while ((line = br.readLine()) != null) {
        regexMatcher = regex.matcher(line);

        if (regexMatcher.find()) {
          assprop = regexMatcher.group(1);
        }
      }
      in.close();
      br.close();
    } catch (Exception e) {
      Logger.error(e);
    }
    return assprop;
  }

  /**
   * Replace ass subtitle properties.
   *
   * @param oldsubpath the oldsubpath
   * @param newsubpath the newsubpath
   * @param charencoding the charencoding
   * @param oldProps the old props
   * @param newprops the newprops
   */
  public static void ReplaceAssSubtitleProperties(String oldsubpath, String newsubpath,
      String charencoding, String oldProps, String newprops) {

    try {
      File f = new File(oldsubpath);
      String content = FileUtils.readFileToString(f, charencoding);
      content = content.replaceAll(oldProps, newprops);
      File tempFile = new File(newsubpath);
      FileUtils.writeStringToFile(tempFile, content, charencoding);

    } catch (IOException e) {
      // Simple exception handling, replace with what's necessary for your use case!
      throw new RuntimeException("Generating file failed", e);
    }

  }

  /**
   * Convert to ass.
   *
   * @param subcharencoding the subcharencoding
   * @param origSubpath the orig subpath
   * @param convsubpath the convsubpath
   * @return the process builder
   */
  public static ProcessBuilder convertToAss(String subcharencoding, String origSubpath,
      String convsubpath) {


    ProcessBuilder pro = null;

    try {

      ArrayList<String> cmd = new ArrayList<>();
      cmd.add(FFmpegUtils.getFFmpegExePath());
      cmd.add(StaticFFmpegFields.SUBCHARENCODING);
      cmd.add(subcharencoding);
      cmd.add(StaticFFmpegFields.input);
      if (OSystem.isWindows()) {
        cmd.add(StaticGlobalFields.QUOTE + origSubpath + StaticGlobalFields.QUOTE);
        cmd.add("-y");
        cmd.add(StaticGlobalFields.QUOTE + convsubpath + StaticGlobalFields.QUOTE);
      } else {
        cmd.add(origSubpath);
        cmd.add("-y");
        cmd.add(convsubpath);
      }
      Logger.debug(cmd.toString());
      pro = new ProcessBuilder(cmd);

    } catch (Exception e) {
      Logger.error(e);
    }

    return pro;
  }



}
