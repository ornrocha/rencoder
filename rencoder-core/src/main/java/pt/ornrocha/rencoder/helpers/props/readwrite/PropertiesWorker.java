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
package pt.ornrocha.rencoder.helpers.props.readwrite;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.pmw.tinylog.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class myProperties.
 */
public class PropertiesWorker {

    /**
     * Load properties relative path.
     *
     * @param propsName the props name
     * @return the properties
     */
    public static PropertiesConfiguration loadPropertiesRelativePath(String propsName) {
	String absolutepath = new File(propsName).getAbsolutePath();
	PropertiesConfiguration props = null;

	try {
	    props = new PropertiesConfiguration(absolutepath);
	    props.setReloadingStrategy(new FileChangedReloadingStrategy());

	} catch (ConfigurationException e1) {
	    Logger.error(e1);
	}

	return props;
    }

    /**
     * Change properties param.
     *
     * @param propfilepath the propfilepath
     * @param key          the key
     * @param value        the value
     */
    public static synchronized void ChangePropertiesParam(String propfilepath, String key, String value) {
	try {
	    PropertiesConfiguration props = loadPropertiesRelativePath(propfilepath);
	    props.setProperty(key, value);
	    props.save();
	} catch (ConfigurationException e) {
	    Logger.error(e);
	}

    }

    public static synchronized String getStringProperty(String propfilepath, String key) {
	PropertiesConfiguration props = loadPropertiesRelativePath(propfilepath);
	if (props.containsKey(key))
	    return String.valueOf(props.getProperty(key));
	return null;

    }

    public static synchronized String getStringPropertyWithDefault(String propfilepath, String key,
	    String defaultvalue) {
	PropertiesConfiguration props = loadPropertiesRelativePath(propfilepath);
	String value = (String) props.getProperty(key);
	if (value != null && !value.isEmpty())
	    return value;

	Logger.error("in parsing "+key+", using default value: "+defaultvalue);
	return defaultvalue;

    }

    public static synchronized boolean getBooleanPropertyWithDefault(String propfilepath, String key,
	    boolean defaultvalue) {
	PropertiesConfiguration props = loadPropertiesRelativePath(propfilepath);
	String value = (String) props.getProperty(key);
	if (value != null && !value.isEmpty()) {
	    if (value.toLowerCase().equals("true"))
		return true;
	    else if (value.toLowerCase().equals("false"))
		return false;
	    else
		return defaultvalue;
	}
	return defaultvalue;

    }

    public static synchronized String checkProperty(PropertiesConfiguration props, String key) {
	if (props.containsKey(key) && !props.getProperty(key).toString().isEmpty()) {
	    return String.valueOf(props.getProperty(key));
	} else
	    return null;
    }


    public static synchronized boolean checkProperty(PropertiesConfiguration props, String key, boolean defaultvalue) {
	boolean res = defaultvalue;
	try {
	    res =props.getBoolean(key, defaultvalue);
	} catch (Exception e) {
	    Logger.error("in parsing "+key+", using default value: "+res);
	}
	return res;
    }
    
    public static synchronized Integer checkProperty(PropertiesConfiguration props, String key, Integer defaultvalue) {
	String valstr=checkProperty(props, key);
	Integer res=defaultvalue;
	if(valstr!=null) {
	    try {
		res=Integer.parseInt(valstr);
	    } catch (Exception e) {
		Logger.error("in parsing "+key+", using default value: "+res);
	    }
	}
	return res;
    }

    public static synchronized int checkProperty(PropertiesConfiguration props, String key, int lowervalue,
	    int highervalue, int defaultvalue) {
	int val=checkProperty(props, key, defaultvalue);
	Logger.trace(key+" --> "+val);
	if (val >= lowervalue && val <= highervalue)
	    return val;
	else
	    return defaultvalue;
    }

    /**
     * Write property file.
     *
     * @param props    the props
     * @param filename the filename
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void writePropertyFile(PropertiesConfiguration props, String filename) throws IOException {
	filename = new File(filename).getAbsolutePath();
	try {
	    props.save(filename);
	} catch (ConfigurationException e) {
	   Logger.error(e);
	}
	// props.store(new FileWriter(filename), "Encoder settings");
    }

    public static void main(String[] args) throws ConfigurationException {
	String file = "/home/orocha/prosps.txt";
	PropertiesConfiguration props = new PropertiesConfiguration(new File(file).getAbsolutePath());
	props.setReloadingStrategy(new FileChangedReloadingStrategy());

	System.out.println(props.getBoolean("bol"));
	System.out.println(props.getInt("numberint"));
	System.out.println(props.getDouble("numberdouble"));
	System.out.println(props.getString("emptyt"));
    }
    
    public static String millisToShortDHMS(long duration) {
	    String res = "";    // java.util.concurrent.TimeUnit;
	    long days       = TimeUnit.MILLISECONDS.toDays(duration);
	    long hours      = TimeUnit.MILLISECONDS.toHours(duration) -
	                      TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(duration));
	    long minutes    = TimeUnit.MILLISECONDS.toMinutes(duration) -
	                      TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration));
	    long seconds    = TimeUnit.MILLISECONDS.toSeconds(duration) -
	                      TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration));
	    long millis     = TimeUnit.MILLISECONDS.toMillis(duration) - 
	                      TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(duration));

	    if (days == 0)      res = String.format("%02d:%02d:%02d.%04d", hours, minutes, seconds, millis);
	    else                res = String.format("%dd %02d:%02d:%02d.%04d", days, hours, minutes, seconds, millis);
	    return res;
	}

}
