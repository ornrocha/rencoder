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
package pt.ornrocha.rencoder.ffmpegWrapper.commands;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.utilities.FFmpegUtils;
import pt.ornrocha.rencoder.mediafiles.files.containers.streams.AudioStreamInfo;
import pt.ornrocha.rencoder.mediafiles.files.containers.streams.SubtitleStreamInfo;
import pt.ornrocha.rencoder.mediafiles.files.containers.streams.VideoStreamInfo;

// TODO: Auto-generated Javadoc
/**
 * The Class FileInformationChecker.
 */
public class FileInformationChecker {

  /** The videostream. */
  protected VideoStreamInfo videostream = null;

  /** The audiostreams. */
  protected ArrayList<AudioStreamInfo> audiostreams = null;

  protected ArrayList<SubtitleStreamInfo> substreams = null;

  /**
   * Instantiates a new file information checker.
   *
   * @param filepath the filepath
   * @throws FileInformationIOException
   * @throws IOException
   */
  public FileInformationChecker(String filepath) throws IOException {
    getFileInformation(filepath);

  }

  /**
   * Gets the file information.
   *
   * @param filepath the filepath
   * @return the file information
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public void getFileInformation(String filepath) throws IOException {

    String ffmpegexepath = FFmpegUtils.getFFmpegExePath();

    ProcessBuilder pb = new ProcessBuilder(ffmpegexepath, "-i", filepath);
    Process p = null;

    p = pb.start();

    InputStream ffmpegout = new ReusableInputStream(p.getErrorStream());

    StringWriter writer = new StringWriter();
    IOUtils.copy(ffmpegout, writer, StandardCharsets.UTF_8);
    Logger.debug("Checking file: " + filepath);
    Logger.debug(writer.toString());

    @SuppressWarnings("resource")
    Scanner checkerrors = new Scanner(ffmpegout);
    Pattern checkerrorsPattern =
        Pattern.compile("Invalid data found when processing input", Pattern.CASE_INSENSITIVE);
    String checkstream = checkerrors.findWithinHorizon(checkerrorsPattern, 0);
    if (checkstream != null)
      throw new FileInformationIOException(filepath, "Invalid data:");

    @SuppressWarnings("resource")
    Scanner checkpermission = new Scanner(ffmpegout);
    Pattern checkpermissionpat = Pattern.compile("Permission denied", Pattern.CASE_INSENSITIVE);
    String checkpermerror = checkpermission.findWithinHorizon(checkpermissionpat, 0);
    if (checkpermerror != null)
      throw new FileInformationIOException(filepath, "File without access permission:");

    Scanner sc = new Scanner(ffmpegout);
    this.videostream = new VideoStreamInfo();
    this.videostream.setVideototalduration(FFmpegInfoPatterns.getDuration(sc));
    this.videostream.setBitrate(FFmpegInfoPatterns.getBitrate(sc));

    Pattern streamPattern = Pattern.compile("Stream #.*");
    String stream;

    Logger.debug("Stream information: " + FilenameUtils.getName(filepath) + "\n");
    while (null != (stream = sc.findWithinHorizon(streamPattern, 0))) {
      Logger.debug(stream);

      if (stream.contains("Video:"))
        getVideoFields(stream);

      if (stream.contains("Audio:"))
        getAudioFields(stream);

      if (stream.contains("Subtitle:"))
        getSubtitleFields(stream);
    }
    Logger.debug("\n");


  }

  /**
   * Gets the video fields.
   *
   * @param stream the stream
   * @return the video fields
   */
  private void getVideoFields(String stream) {

    this.videostream.setCodectype(FFmpegInfoPatterns.getVideoCodecInfo(stream));
    this.videostream.setVideoaspectsize(FFmpegInfoPatterns.getAspect(stream));
    String videobit = FFmpegInfoPatterns.getBitrateStream(stream);
    if (videobit != null)
      this.videostream.setBitrate(videobit);

    this.videostream.setVideofps(FFmpegInfoPatterns.getFPS(stream));
    this.videostream.setNumberstream(FFmpegInfoPatterns.getStreamNumber(stream));

  }

  /**
   * Gets the audio fields.
   *
   * @param stream the stream
   * @return the audio fields
   */
  private void getAudioFields(String stream) {

    if (this.audiostreams == null)
      this.audiostreams = new ArrayList<>();

    AudioStreamInfo ainfo = new AudioStreamInfo();
    ainfo.setLanguage(FFmpegInfoPatterns.getLanguage(stream));
    ainfo.setNumberstream(FFmpegInfoPatterns.getStreamNumber(stream));
    ainfo.setCodectype(FFmpegInfoPatterns.getAudioCodecInfo(stream));
    ainfo.setBitrate(FFmpegInfoPatterns.getAudioBitrate(stream));
    ainfo.setAudiofrequency(FFmpegInfoPatterns.getAudioFrequency(stream));
    ainfo.setAudiochanneltype(FFmpegInfoPatterns.getaudioChannelstype(stream));

    this.audiostreams.add(ainfo);

  }

  private void getSubtitleFields(String stream) {

    if (this.substreams == null)
      this.substreams = new ArrayList<>();

    SubtitleStreamInfo subinfo = new SubtitleStreamInfo();
    subinfo.setNumberstream(FFmpegInfoPatterns.getStreamNumber(stream));
    subinfo.setCodectype(FFmpegInfoPatterns.getSubtitleCodecInfo(stream));
    subinfo.setLanguage(FFmpegInfoPatterns.getLanguage(stream));

    this.substreams.add(subinfo);
  }

  /**
   * Convert time to seconds.
   *
   * @param time the time
   * @return the double
   */
  public static double convertTimetoseconds(String time) {
    String[] hms = time.split(":");
    double totalSecs = Integer.parseInt(hms[0]) * 3600 + Integer.parseInt(hms[1]) * 60
        + Double.parseDouble(hms[2]);
    // System.out.println("Total duration: " + totalSecs + " seconds.");
    return totalSecs;

  }

  /**
   * Gets the video stream info.
   *
   * @return the video stream info
   */
  public VideoStreamInfo getVideoStreamInfo() {
    return this.videostream;
  }

  /**
   * Gets the audio streams info.
   *
   * @return the audio streams info
   */
  public ArrayList<AudioStreamInfo> getAudioStreamsInfo() {
    return this.audiostreams;
  }


  /**
   * Gets the audio stream number.
   *
   * @param n the n
   * @return the audio stream number
   * @throws Exception the exception
   */
  public AudioStreamInfo getAudioStreamNumber(int n) throws Exception {

    if (n > this.audiostreams.size())
      throw new Exception("Audio Stream not found");

    else
      return audiostreams.get(n);

  }



  public ArrayList<SubtitleStreamInfo> getSubtitlestreams() {
    return substreams;
  }

  // public static boolean validFFmpegExec(String ffmpegpath) {
  // File ffmpeg = new File(ffmpegpath);
  // if (ffmpeg.exists()) {
  // ffmpeg.setExecutable(true);
  // ffmpeg.setWritable(true);
  // ffmpeg.setReadable(true);
  //
  // String ffmpegexepath = ffmpeg.getAbsolutePath();
  // ProcessBuilder pb = new ProcessBuilder(ffmpegexepath, "-i");
  // Process p = null;
  // try {
  // p = pb.start();
  // } catch (IOException e) {
  // Logger.error(e);
  // return false;
  // }
  // return true;
  // } else
  // return false;
  //
  // }

}
