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
package pt.ornrocha.rencoder.mediafiles.setfiles.processfiles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.io.FilenameUtils;

import pt.ornrocha.rencoder.ffmpegWrapper.commands.FileInformationIOException;
import pt.ornrocha.rencoder.ffmpegWrapper.commands.MultiFileInformationIOException;
import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.helpers.osystem.OSystem;
import pt.ornrocha.rencoder.helpers.props.managers.GeneralEncodingPropertiesManager;
import pt.ornrocha.rencoder.mediafiles.files.auxiliar.ProcessFilesAux;
import pt.ornrocha.rencoder.mediafiles.files.containers.base.Subtitlefile;
import pt.ornrocha.rencoder.mediafiles.files.containers.base.Videofile;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;
import pt.ornrocha.rencoder.mediafiles.setfiles.foldersandfiles.FileTypeFilter;

// TODO: Auto-generated Javadoc
/**
 * The Class ProcessFiles.
 */
public class ProcessFiles {


  /** The all files. */
  protected ArrayList<String> allfiles;

  /** The video files. */
  protected ArrayList<Videofile> videoFiles = null;

  /** The genencinfomanager. */
  protected GeneralEncodingPropertiesManager genencinfomanager = null;

  protected boolean executing = true;

  /**
   * Instantiates a new process files.
   */
  public ProcessFiles() {}



  /**
   * Instantiates a new process files.
   *
   * @param files the files
   * @param generalinfo the generalinfo
   * @throws MultiFileInformationIOException
   * @throws FileInformationIOException
   * @7+throws IOException
   */
  public ProcessFiles(ArrayList<String> files, GeneralEncodingPropertiesManager generalinfo)
      throws IOException {
    this.allfiles = checkforSpecialCharsInFileNames(files);
    this.genencinfomanager = generalinfo;

  }

  /**
   * Sets the files to treat.
   *
   * @param files the files
   */
  public void SetFilesToTreat(ArrayList<String> files) {
    this.allfiles = files;
  }

  /**
   * Gets the filtered video files.
   *
   * @return the filtered video files
   */
  public ArrayList<Videofile> getFilteredVideoFiles() {
    return this.videoFiles;
  }

  public boolean isExecuting() {
    return executing;
  }

  /**
   * Process video files.
   *
   * @throws CloneNotSupportedException the clone not supported exception
   * @throws MultiFileInformationIOException
   * @throws FileInformationIOException
   * @throws IOException
   */
  public void processVideoFiles() throws CloneNotSupportedException, IOException {

    if (allfiles != null) {
      ArrayList<Subtitlefile> subtitles = filterallsubtitlefiles(allfiles);
      this.videoFiles = filterallVideofiles(allfiles);
      checkandsetifexistmatchingSubtitles(subtitles);
    }
    this.executing = false;
    // else
    // throw new Error("None suported file was found");

  }



  /**
   * Filter all subtitle files.
   *
   * @param listfiles the listfiles
   * @return the array list
   */
  protected ArrayList<Subtitlefile> filterallsubtitlefiles(ArrayList<String> listfiles) {
    ArrayList<Subtitlefile> subfiles = new ArrayList<>();
    IndexedHashMap<String, FileTypeFilter> allowedsubs =
        ProcessFilesAux.getAllowedSubtitlesExtension();

    for (String filepath : listfiles) {
      String ext = FilenameUtils.getExtension(filepath);
      if (allowedsubs.containsKey(ext.toLowerCase()))
        subfiles.add(new Subtitlefile(filepath));
    }

    return subfiles;
  }


  public static ArrayList<Subtitlefile> filterallsubtitlesfiles(ArrayList<File> files) {
    ArrayList<Subtitlefile> subfiles = null;
    IndexedHashMap<String, FileTypeFilter> allowedsubs =
        ProcessFilesAux.getAllowedSubtitlesExtension();

    for (File f : files) {
      String filepath = f.getAbsolutePath();
      String ext = FilenameUtils.getExtension(filepath);
      if (allowedsubs.containsKey(ext.toLowerCase())) {
        if (subfiles == null)
          subfiles = new ArrayList<>();

        subfiles.add(new Subtitlefile(filepath));

      }
    }

    return subfiles;
  }

  /**
   * Filter all video files.
   *
   * @param listfiles the listfiles
   * @return the array list
   * @throws CloneNotSupportedException the clone not supported exception
   * @throws IOException
   * @throws MultiFileInformationIOException
   * @throws FileInformationIOException
   * @throws @throws IOException
   */
  protected ArrayList<Videofile> filterallVideofiles(ArrayList<String> listfiles)
      throws CloneNotSupportedException, IOException {
    ArrayList<Videofile> vidfiles = new ArrayList<>();
    IndexedHashMap<String, FileTypeFilter> allowedvideos =
        ProcessFilesAux.getAllowedVideoExtension();


    for (String filepath : listfiles) {
      String ext = FilenameUtils.getExtension(filepath);
      if (allowedvideos.containsKey(ext.toLowerCase())) {

        Videofile toadd =
            new Videofile(filepath, (IGeneralVideoEncInfoContainer) this.genencinfomanager
                .getGenEncInfoContainer().clone());

        if (toadd != null)
          vidfiles.add(toadd);
      }
    }

    return vidfiles;

  }


  /**
   * Check and set if exist matching subtitles.
   *
   * @param subs the subs
   */
  protected void checkandsetifexistmatchingSubtitles(ArrayList<Subtitlefile> subs) {

    if (this.videoFiles != null) {
      for (Videofile vfile : this.videoFiles) {
        for (Subtitlefile subtitlefile : subs) {
          String namewithoutext = subtitlefile.getNameWithoutExtension();
          String name = subtitlefile.getName();


          if (namewithoutext.equals(vfile.getNameWithoutExtension())) {
            if (!vfile.containsSubtitle(name))
              vfile.addSubtitleObject(subtitlefile);
          }
        }
      }
    }
  }


  /**
   * Filter allowed subtitle file.
   *
   * @param filepath the filepath
   * @param allowedSubtitles the allowed subtitles
   * @return the subtitlefile
   */
  public static Subtitlefile FilterAllowedSubtitleFile(String filepath,
      IndexedHashMap<String, FileTypeFilter> allowedSubtitles) {
    Subtitlefile sub = null;
    String ext = FilenameUtils.getExtension(filepath);
    if (allowedSubtitles.containsKey(ext))
      sub = new Subtitlefile(filepath);


    return sub;

  }


  /**
   * Check for special chars in file names.
   *
   * @param files the files
   * @return the array list
   */
  public static ArrayList<String> checkforSpecialCharsInFileNames(ArrayList<String> files) {

    ArrayList<String> newnames = new ArrayList<>(files.size());
    for (int i = 0; i < files.size(); i++) {
      String newname = getFileNameWithoutSpecialChar(files.get(i));
      if (newname != null)
        newnames.add(newname);
      else
        newnames.add(files.get(i));
    }


    return newnames;
  }


  /**
   * Gets the file name without special char.
   *
   * @param filepath the filepath
   * @return the file name without special char
   */
  public static String getFileNameWithoutSpecialChar(String filepath) {

    Path path = Paths.get(filepath);
    String parentdirs = path.getParent().toString();
    String extension = FilenameUtils.getExtension(filepath);
    String BaseName = FilenameUtils.getBaseName(filepath);



    if (BaseName.contains("'")) {
      String newBaseName = BaseName.replace("'", "");

      String newfilepath =
          parentdirs + OSystem.getSystemSeparator() + newBaseName + "." + extension;

      File oldfile = new File(filepath);
      File newfile = new File(newfilepath);

      oldfile.renameTo(newfile);
      return newfilepath;
    }

    return null;
  }


  /**
   * Check if file exists and return new name.
   *
   * @param directory the directory
   * @param filenamewithoutext the filenamewithoutext
   * @param extension the extension
   * @param numb the numb
   * @param recurs the recurs
   * @return the string
   */
  public static String checkIfFileExistsandReturnNewname(String directory,
      String filenamewithoutext, String extension, int numb, boolean recurs) {

    int num = numb;
    String filepath = null;
    if (!recurs)
      filepath = directory + OSystem.getSystemSeparator() + filenamewithoutext + "." + extension;
    else
      filepath =
          directory + OSystem.getSystemSeparator() + filenamewithoutext + num + "." + extension;


    if (new File(filepath).exists()) {

      return checkIfFileExistsandReturnNewname(directory, filenamewithoutext, extension, (num + 1),
          true);

    }

    return filepath;
  }


  public static String checkIfFileExistsOrInPipelineandReturnNewname(String directory,
      Videofile origfile, String extension, int numb, boolean recurs,
      IndexedHashMap<String, String> processed) {

    int num = numb;
    String filepath = null;

    if (!recurs)
      filepath = directory + OSystem.getSystemSeparator()
          + origfile.getNameWithoutExtensionAndFFmpegInvChars() + "." + extension;
    else
      filepath = directory + OSystem.getSystemSeparator()
          + origfile.getNameWithoutExtensionAndFFmpegInvChars() + num + "." + extension;


    if (processed.containsValue(filepath)) {
      filepath = checkIfFileExistsOrInPipelineandReturnNewname(directory, origfile, extension,
          (numb + 1), true, processed);

    }

    if (new File(filepath).exists()) {

      return checkIfFileExistsOrInPipelineandReturnNewname(directory, origfile, extension,
          (num + 1), true, processed);
    }


    return filepath;
  }


  public static void setSameConvConfigInAllMovies(IGeneralVideoEncInfoContainer convconfig,
      ArrayList<Videofile> moviefiles) {

    if (moviefiles != null) {
      for (int i = 0; i < moviefiles.size(); i++) {
        moviefiles.get(i).setEncodingInfoContainer(convconfig);
      }
    }
  }

  public static IndexedHashMap<String, Videofile> makeconvertList(ArrayList<Videofile> movlist) {
    IndexedHashMap<String, Videofile> list = new IndexedHashMap<>();
    for (Videofile videofile : movlist) {
      list.put(videofile.getName(), videofile);
    }
    return list;
  }



}
