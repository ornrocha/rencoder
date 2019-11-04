/*
 * Copyright 2014
 *
 * This is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. 
 * 
 * This code is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the 
 * GNU Public License for more details. 
 * 
 * You should have received a copy of the GNU Public License 
 * along with this code. If not, see http://www.gnu.org/licenses/ 
 * 
 * Created by Orlando Rocha
 */
package pt.ornrocha.rencoder.helpers.lang;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.configuration.PropertiesConfiguration;

import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.helpers.props.fields.StaticGlobalFields;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;
import pt.ornrocha.rencoder.mediafiles.setfiles.foldersandfiles.ListFiles;

// TODO: Auto-generated Javadoc
/**
 * The Class LangTools.
 */
public class LangTools {

	/**
	 * Gets the defined language.
	 *
	 * @return the defined language
	 */
	public static Locale getDefinedLanguage() {
		Locale loc = null;

		PropertiesConfiguration prop = PropertiesWorker.loadPropertiesRelativePath(StaticGlobalFields.RENCODERCONFIGFILE);
		if (prop.containsKey(StaticGlobalFields.COUNTRY)) {
			if (!prop.getProperty(StaticGlobalFields.COUNTRY).toString().isEmpty()) {
				String country = (String) prop.getProperty(StaticGlobalFields.COUNTRY);
				Locale list[] = DateFormat.getAvailableLocales();
				for (Locale aLocale : list) {
					if (aLocale.toString().equals(country)) {
						loc = aLocale;
					}
				}
			}
		} else
			loc = Locale.getDefault();

		return loc;
	}

	/**
	 * Load languages path.
	 *
	 * @return the class loader
	 */
	public static ClassLoader loadLanguagesPath() {
		String path = new File(StaticGlobalFields.LANGUAGESFOLDER).getAbsolutePath();
		return ListFiles.classPathloader(path);
	}

	/**
	 * Gets the available country codes.
	 *
	 * @return the available country codes
	 */
	public static IndexedHashMap<String, String> getAvailableCountryCodes() {
		IndexedHashMap<String, String> countrycodes = new IndexedHashMap<>();
		ArrayList<String> files = ListFiles.getFilesInDirectory(StaticGlobalFields.LANGUAGESFOLDER, false);
		Pattern pat = Pattern.compile("lang_(\\w+)_(\\w+).properties");

		for (String file : files) {
			Matcher m = pat.matcher(file);
			if (m.find()) {
				String lang = m.group(1);
				String country = m.group(2);
				countrycodes.put(country, lang);
			}

		}
		return countrycodes;
	}

	/**
	 * Gets the list available locales.
	 *
	 * @return the list available locales
	 */
	public static ArrayList<Locale> getListAvailableLocales() {

		IndexedHashMap countries = getAvailableCountryCodes();
		ArrayList<Locale> locales = new ArrayList<>();
		Map<String, String> treeMap = new TreeMap<String, String>(countries);

		for (Map.Entry<String, String> entry : treeMap.entrySet()) {
			Locale loc = new Locale(entry.getValue(), entry.getKey());
			locales.add(loc);
		}

		return locales;
	}

	public static String getWordLanguage(String originalkey, String rbkey) {
		ResourceBundle rb = ResourceBundle.getBundle("lang", LangTools.getDefinedLanguage(),
				LangTools.loadLanguagesPath());
		try {
			String rbvalue = rb.getString(rbkey);
			if (rbvalue != null)
				return rbvalue;
		} catch (Exception e) {
			return originalkey;
		}

		return originalkey;
	}

	public static String getResourceBundleWordLanguage(ResourceBundle rb, String originalkey, String rbkey) {
		try {
			String rbvalue = rb.getString(rbkey);
			if (rbvalue != null)
				return rbvalue;
		} catch (Exception e) {
			return originalkey;
		}

		return originalkey;
	}

}
