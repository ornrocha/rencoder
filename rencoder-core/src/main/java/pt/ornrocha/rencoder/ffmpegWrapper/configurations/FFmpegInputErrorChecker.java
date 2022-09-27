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

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import org.tinylog.Logger;

import pt.ornrocha.rencoder.helpers.IndexedHashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class FFmpegInputErrorChecker.
 */
public class FFmpegInputErrorChecker implements Runnable {

  /** The validate param. */
  private boolean validateParam = false;

  /** The listwords. */
  private ArrayList<String> listwords = null;

  /** The instream. */
  private InputStream instream = null;

  /** The founderrors. */
  private boolean founderrors = false;

  /** The valid parameters. */
  private IndexedHashMap<String, Boolean> validParameters = null;

  private ArrayList<String> ffmpegoutput = null;

  /**
   * Instantiates a new ffmpeg input error checker.
   *
   * @param inStream the in stream
   * @param checkwords the checkwords
   * @param validateParameters the validate parameters
   */
  public FFmpegInputErrorChecker(InputStream inStream, ArrayList<String> checkwords,
      boolean validateParameters) {

    this.instream = inStream;
    this.listwords = checkwords;
    this.validateParam = validateParameters;
    if (validateParameters)
      initializeMapParam();

    this.ffmpegoutput = new ArrayList<>();

  }

  /**
   * Initialize map param.
   */
  private void initializeMapParam() {
    validParameters = new IndexedHashMap<>();
    for (String param : listwords) {
      validParameters.put(param, false);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Runnable#run()
   */
  @Override
  public void run() {

	  InputStreamReader reader = new InputStreamReader(instream);

	  Scanner scan = new Scanner(reader);
	  while (scan.hasNextLine()) {
		  String line = scan.nextLine();

		  Logger.debug(line);
		  ffmpegoutput.add(line);
		  for (String word : listwords) {
			  if(line.contains(word)) {
				  Logger.error(line);
			  }
			  if (validateParam) {
				  if (line.contains(word)) {

					  int index = validParameters.getIndexOf(word);
					  validParameters.putAt(index, word, true);
				  }

			  } else {
				  if (line.toLowerCase().contains(word.toLowerCase()))
					  founderrors = true;
			  }
		  }

	  }
  }

  /**
   * Exist errors.
   *
   * @return true, if successful
   */
  public boolean existerrors() {
    return this.founderrors;
  }

  /**
   * Gets the validation of parameters.
   *
   * @return the validation of parameters
   */
  public IndexedHashMap<String, Boolean> getValidationOfParameters() {
    return this.validParameters;
  }

}
