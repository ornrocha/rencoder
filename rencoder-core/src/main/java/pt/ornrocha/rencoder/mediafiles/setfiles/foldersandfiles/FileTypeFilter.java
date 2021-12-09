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

import java.io.File;

import javax.swing.filechooser.FileFilter;

// TODO: Auto-generated Javadoc
/**
 * The Class FileTypeFilter.
 */
public class FileTypeFilter extends FileFilter {

  /** The extension. */
  private String extension;

  /** The description. */
  private String description;

  /**
   * Instantiates a new file type filter.
   *
   * @param extension the extension
   * @param description the description
   */
  public FileTypeFilter(String extension, String description) {
    this.extension = extension;
    this.description = description;
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
   */
  public boolean accept(File file) {
    if (file.isDirectory()) {
      return true;
    }
    return file.getName().endsWith(extension);
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.filechooser.FileFilter#getDescription()
   */
  public String getDescription() {
    return description + String.format(" (*%s)", extension);
  }
}
