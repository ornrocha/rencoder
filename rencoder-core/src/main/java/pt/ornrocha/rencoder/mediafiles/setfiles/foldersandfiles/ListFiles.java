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
package pt.ornrocha.rencoder.mediafiles.setfiles.foldersandfiles;

import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;

import org.apache.commons.io.FilenameUtils;
import org.tinylog.Logger;

import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.helpers.osystem.OSystem;
import pt.ornrocha.rencoder.mediafiles.files.auxiliar.ProcessFilesAux;

// TODO: Auto-generated Javadoc
/**
 * The Class ListFiles.
 */
public class ListFiles {

  /**
   * Gets the items in folder.
   *
   * @param path the path
   * @return the items in folder
   */
  public static ArrayList<String> getitemsInFolder(String path) {
    IndexedHashMap<String, FileTypeFilter> allowedfiles = ProcessFilesAux.getAllowedExtensions();
    ArrayList<String> items = new ArrayList<>();
    File folder = new File(path);
    File[] listOfFiles = folder.listFiles();
    for (File file : listOfFiles) {
      if (file.isFile()) {
        String ext = FilenameUtils.getExtension(file.getAbsolutePath());
        if (allowedfiles.containsKey(ext.toLowerCase()))
          items.add(file.getAbsolutePath());
      }
    }
    return items;

  }

  public static ArrayList<String> getFromSelecteditems(ArrayList<String> filespath) {
    IndexedHashMap<String, FileTypeFilter> allowedfiles = ProcessFilesAux.getAllowedExtensions();
    ArrayList<String> items = new ArrayList<>();
    for (String path : filespath) {
      File file = new File(path);
      if (file.isFile()) {
        String ext = FilenameUtils.getExtension(file.getAbsolutePath());
        if (allowedfiles.containsKey(ext.toLowerCase()))
          items.add(file.getAbsolutePath());
      }
    }
    return items;

  }

  /**
   * Gets the list of items in folder and sub folders.
   *
   * @param foldername the foldername
   * @return the listof items in folder and sub folders
   */
  public static ArrayList<String> getListofItemsInFolderAndSubFolders(String foldername) {
    IndexedHashMap<String, FileTypeFilter> allowedfiles = ProcessFilesAux.getAllowedExtensions();
    ArrayList<String> list = new ArrayList<String>();
    File directory = new File(foldername);
    File[] listindir = directory.listFiles();
    if (listindir != null)
      for (File file : listindir) {
        if (file.isFile()) {
          String ext = FilenameUtils.getExtension(file.getAbsolutePath());
          if (allowedfiles.containsKey(ext.toLowerCase()))
            list.add(file.getAbsolutePath());
        } else if (file.isDirectory())
          list.addAll(getListofItemsInFolderAndSubFolders(file.getAbsolutePath()));
      }

    return list;
  }

  public static boolean checkIfFoldersContainsSubFolders(ArrayList<File> files) {

    for (File file : files) {
      if (CheckIfFolderContainsSubFolders(file.getAbsolutePath()))
        return true;
    }
    return false;
  }

  /**
   * Gets the folder path file chooser.
   *
   * @return the folderpath file chooser
   */
  public static String getFolderpathFileChooser(Component parent) {
    JFileChooser chooser = new JFileChooser();
    chooser = changeFileText(chooser);
    chooser.setDialogTitle(LangTools.getWordLanguage("Select folder", "files.selectdirectory"));
    String path = null;
    // File workingDirectory = new File(System.getProperty("user.dir"));
    // chooser.setCurrentDirectory(workingDirectory);
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    chooser.setAcceptAllFileFilterUsed(false);

    int returnVal = chooser.showOpenDialog(parent != null ? parent : null);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      path = chooser.getSelectedFile().getAbsolutePath();
    }
    return path;

  }

  /**
   * Gets the single file path file chooser.
   *
   * @return the single filepath file chooser
   */
  public static String getSingleFilepathFileChooser(Component parent) {
    JFileChooser chooser = new JFileChooser();
    chooser = changeFileText(chooser);
    chooser.setDialogTitle(LangTools.getWordLanguage("Select File", "files.selectfile"));
    String path = null;
    // File workingDirectory = new File(System.getProperty("user.dir"));
    // chooser.setCurrentDirectory(workingDirectory);
    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    chooser.setMultiSelectionEnabled(false);
    // chooser.setAcceptAllFileFilterUsed(false);
    int returnVal = chooser.showOpenDialog(parent != null ? parent : null);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      path = chooser.getSelectedFile().getAbsolutePath();
    }
    return path;
  }

  public static String getSingleFileSavePath(Component parent) {
    String saveas = null;
    JFileChooser fileChooser = new JFileChooser();
    fileChooser = changeFileText(fileChooser);
    String savelabel = "Specify a file to save";
    fileChooser.setDialogTitle(LangTools.getWordLanguage(savelabel, "files.outputfilesave"));

    int userSelection = fileChooser.showSaveDialog(parent != null ? parent : null);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
      File fileToSave = fileChooser.getSelectedFile();
      saveas = fileToSave.getAbsolutePath();
    }
    return saveas;
  }

  public static boolean CheckIfFolderContainsSubFolders(String dirpath) {

    File[] files = new File(dirpath).listFiles();

    if (files != null) {
      for (File file : files) {
        if (file.isDirectory()) {
          return true;
        }
      }
      return false;
    }
    return false;
  }

  /**
   * Gets the items in folder with file chooser.
   *
   * @return the items in folder with file chooser
   */
  public static ArrayList<String> getitemsInFolderWithFileChooser(Component parent) {
    String folderpath = getFolderpathFileChooser(parent);
    if (folderpath != null)
      return getitemsInFolder(folderpath);
    else
      return null;
  }

  /**
   * Gets the list of items in folder and sub folders with filechooser.
   *
   * @return the listof items in folder and sub folders with filechooser
   */
  public static ArrayList<String> getListofItemsInFolderAndSubFoldersWithFilechooser(
      Component parent) {
    String folderpath = getFolderpathFileChooser(parent);
    if (folderpath != null)
      return getListofItemsInFolderAndSubFolders(folderpath);
    else
      return null;

  }

  /**
   * Gets the items type in folder with filechooser.
   *
   * @param filters the filters
   * @return the items type in folder with filechooser
   */
  public static ArrayList<String> getItemsTypeInFolderWithFilechooser(ArrayList<FileFilter> filters,
      Component parent) {

    ArrayList<String> items = new ArrayList<>();
    JFileChooser chooser = new JFileChooser();
    chooser = changeFileText(chooser);
    chooser.setDialogTitle(LangTools.getWordLanguage("Select Files", "files.selectfiles"));
    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

    if (filters != null) {
      for (FileFilter filter : filters) {
        chooser.addChoosableFileFilter(filter);
      }
    }

    chooser.setMultiSelectionEnabled(true);

    int returnVal = chooser.showOpenDialog(parent != null ? parent : null);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File[] files = chooser.getSelectedFiles();
      for (File file : files) {
        items.add(file.getAbsolutePath());
      }
    }
    return items;
  }

  /**
   * Gets the single item type in folder with filechooser.
   *
   * @param filters the filters
   * @return the single item type in folder with filechooser
   */
  public static String getSingleItemTypeInFolderWithFilechooser(
      IndexedHashMap<String, FileTypeFilter> filters, Component parent) {

    String item = null;
    JFileChooser chooser = new JFileChooser();
    chooser = changeFileText(chooser);
    chooser.setDialogTitle(LangTools.getWordLanguage("Select Files", "files.selectfiles"));
    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

    ArrayList<FileTypeFilter> filterset = ProcessFilesAux.getOnlyAllowedFilters(filters);

    if (filters != null) {
      for (FileFilter filter : filterset) {
        chooser.addChoosableFileFilter(filter);
      }
    }

    int returnVal = chooser.showOpenDialog(parent != null ? parent : null);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = null;
      file = chooser.getSelectedFile();
      item = file.getAbsolutePath();
    }
    return item;
  }

  /**
   * Gets the selected items type in folder with filechooser.
   *
   * @param allowedfiles the allowedfiles
   * @return the selected items type in folder with filechooser
   */
  public static ArrayList<String> getSelectedItemsTypeInFolderWithFilechooser(
      IndexedHashMap<String, FileTypeFilter> allowedfiles, Component parent) {
    ArrayList<FileFilter> filters = new ArrayList<>();
    for (int i = 0; i < allowedfiles.size(); i++) {
      filters.add(allowedfiles.getValueAt(i));
    }

    ArrayList<String> res = getItemsTypeInFolderWithFilechooser(filters, parent);

    return res;
  }

  /**
   * Change file text.
   *
   * @param chooser the chooser
   * @return the j file chooser
   */
  public static JFileChooser changeFileText(JFileChooser chooser) {
    ResourceBundle rb = ResourceBundle.getBundle("lang", LangTools.getDefinedLanguage(),
        LangTools.loadLanguagesPath());
    String loc = rb.getLocale().getISO3Language();
    if (!loc.equals("eng")) {
      UIManager.put("FileChooser.openDialogTitleText",
          rb.getString("filechooser.openDialogTitleText"));
      UIManager.put("FileChooser.saveInLabelText", rb.getString("general.savein"));
      UIManager.put("FileChooser.lookInLabelText", rb.getString("filechooser.lookInLabelText"));
      UIManager.put("FileChooser.openButtonText", rb.getString("filechooser.openButtonText"));
      UIManager.put("FileChooser.cancelButtonText", rb.getString("filechooser.cancelButtonText"));
      UIManager.put("FileChooser.fileNameLabelText", rb.getString("filechooser.fileNameLabelText"));
      UIManager.put("FileChooser.saveButtonText", rb.getString("general.save"));
      UIManager.put("FileChooser.saveButtonToolTipText", rb.getString("files.saveas"));
      UIManager.put("FileChooser.saveDialogTitleText", rb.getString("general.savein"));
      UIManager.put("FileChooser.filesOfTypeLabelText",
          rb.getString("filechooser.filesOfTypeLabelText"));
      UIManager.put("FileChooser.openButtonToolTipText",
          rb.getString("filechooser.openButtonToolTipText"));
      UIManager.put("FileChooser.cancelButtonToolTipText",
          rb.getString("filechooser.cancelButtonToolTipText"));
      UIManager.put("FileChooser.fileNameHeaderText",
          rb.getString("filechooser.fileNameHeaderText"));
      UIManager.put("FileChooser.upFolderToolTipText",
          rb.getString("filechooser.upFolderToolTipText"));
      UIManager.put("FileChooser.homeFolderToolTipText",
          rb.getString("filechooser.homeFolderToolTipText"));
      UIManager.put("FileChooser.newFolderToolTipText",
          rb.getString("filechooser.newFolderToolTipText"));
      UIManager.put("FileChooser.listViewButtonToolTipText",
          rb.getString("filechooser.listViewButtonToolTipText"));
      UIManager.put("FileChooser.newFolderButtonText",
          rb.getString("filechooser.newFolderButtonText"));
      UIManager.put("FileChooser.renameFileButtonText",
          rb.getString("filechooser.renameFileButtonText"));
      UIManager.put("FileChooser.deleteFileButtonText",
          rb.getString("filechooser.deleteFileButtonText"));
      UIManager.put("FileChooser.filterLabelText", rb.getString("filechooser.filterLabelText"));
      UIManager.put("FileChooser.detailsViewButtonToolTipText",
          rb.getString("filechooser.detailsViewButtonToolTipText"));
      UIManager.put("FileChooser.fileSizeHeaderText",
          rb.getString("filechooser.fileSizeHeaderText"));
      UIManager.put("FileChooser.fileDateHeaderText",
          rb.getString("filechooser.fileDateHeaderText"));

      SwingUtilities.updateComponentTreeUI(chooser);
    }
    return chooser;
  }

  /**
   * Gets the files in directory.
   *
   * @param dirpath the dirpath
   * @param getabsolutefilepath the getabsolutefilepath
   * @return the files in directory
   */
  public static ArrayList<String> getFilesInDirectory(String dirpath, boolean getabsolutefilepath) {

    dirpath = new File(dirpath).getAbsolutePath();
    ArrayList<String> filesresults = new ArrayList<String>();

    File[] files = new File(dirpath).listFiles();

    for (File file : files) {
      if (file.isFile()) {
        if (!getabsolutefilepath)
          filesresults.add(file.getName());
        else
          filesresults.add(file.getAbsolutePath());
      }
    }

    return filesresults;
  }

  /**
   * Removes the files.
   *
   * @param filepaths the filepaths
   */
  public static void removeFiles(ArrayList<String> filepaths) {
    for (String fl : filepaths) {
      File f = new File(fl);
      f.delete();
    }
  }

  /**
   * Checkif exists file name in directory.
   *
   * @param dirpath the dirpath
   * @param filename the filename
   * @return true, if successful
   */
  public static boolean checkifExistsFileNameInDirectory(String dirpath, String filename) {
    ArrayList<String> files = getFilesInDirectory(dirpath, true);

    for (String name : files) {
      name = FilenameUtils.getBaseName(name);
      if (name.equals(filename))
        return true;
    }
    return false;
  }

  /**
   * Class pathloader.
   *
   * @param path the path
   * @return the class loader
   */
  public static ClassLoader classPathloader(String path) {
    ClassLoader loader = null;
    try {
      File file = new File(path);
      URL[] urls;

      urls = new URL[] {file.toURI().toURL()};

      loader = new URLClassLoader(urls);

    } catch (MalformedURLException e) {
      Logger.error(e);
    }

    return loader;
  }

  /**
   * Load properties relative path.
   *
   * @param propsName the props name
   * @return the properties
   * @throws Exception the exception
   */
  public static Properties loadPropertiesRelativePath(String propsName) throws Exception {
    String absolutepath = new File(propsName).getAbsolutePath();
    Properties props = new Properties();
    URL url = ClassLoader.getSystemResource(absolutepath);
    props.load(url.openStream());
    return props;
  }

  /**
   * Gets the icon absolute path.
   *
   * @param relativepath the relativepath
   * @return the icon absolute path
   */
  public static String getIconAbsolutePath(String relativepath) {
    return new File(relativepath).getAbsolutePath();
  }

  /**
   * Open system folder.
   *
   * @param folder the folder
   */
  public static void OpenSystemFolder(String folder) {

    try {
      if (OSystem.isLinux()) {
        Logger.debug("Opening folder " + folder + " with xdg-open");
        Runtime.getRuntime().exec("xdg-open " + folder);
      } else if (OSystem.isWindows())
        Runtime.getRuntime().exec("explorer /select,  " + folder);
      else if (OSystem.isMacOS())
        Runtime.getRuntime().exec("usr/bin/open" + folder);

    } catch (Exception e) {
      Logger.error(e);
    }
  }

  /**
   * Open file in system.
   *
   * @param filepath the filepath
   * @param isfolder the isfolder
   */
  public static void OpenFileInSystem(String filepath, boolean isfolder) {
    // filepath=new File(filepath).getAbsolutePath();
    String folder = null;
    if (!isfolder)
      folder = FilenameUtils.getPath(filepath);
    else
      folder = filepath;

    File file = new File(filepath);
    try {
      if (Desktop.isDesktopSupported()) {
        Desktop.getDesktop().open(file);
      } else {
        OpenSystemFolder(folder);
      }
    } catch (Exception e) {
      OpenSystemFolder(folder);
    }
  }

}
