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
package pt.ornrocha.rencoder.helpers.osystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.prefs.Preferences;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.tinylog.Logger;

import pt.ornrocha.rencoder.helpers.props.fields.StaticGlobalFields;

// TODO: Auto-generated Javadoc
/**
 * The Class OSystem.
 */
public class OSystem {

	private final static String TEMPSUBS = "tempsubs";
	private final static String TEMPFOLDER = "tempfolder";

	public static String getCurrentDir() {
		String currentpath = new File(".").getAbsolutePath();
		Path p = Paths.get(currentpath);
		return p.getParent().toString();
	}

	/**
	 * Gets the os name.
	 *
	 * @return the os name
	 */
	public static String getOsName() {
		return System.getProperty("os.name");
	}

	/**
	 * Checks if is linux.
	 *
	 * @return true, if is linux
	 */
	public static boolean isLinux() {

		OS os = getOperationSystem();
		if (os.equals(OS.LINUX64) || os.equals(OS.LINUX32))
			return true;
		else
			return false;
	}

	/**
	 * Checks if is windows.
	 *
	 * @return true, if is windows
	 */
	public static boolean isWindows() {

		OS os = getOperationSystem();
		if (os.equals(OS.WIN32) || os.equals(OS.WIN64))
			return true;
		else
			return false;
	}

	/**
	 * Checks if is mac os.
	 *
	 * @return true, if is mac os
	 */
	public static boolean isMacOS() {

		OS os = getOperationSystem();
		if (os.equals(OS.MACOS))
			return true;
		else
			return false;
	}

	/**
	 * Gets the operation system.
	 *
	 * @return the operation system
	 */
	public static OS getOperationSystem() {
		String os = System.getProperty("os.name").toLowerCase();
		OSbit archversion = getBitsOperationSystem();

		if (os.startsWith("linux")) {
			if (archversion.equals(OSbit.b64))
				return OS.LINUX64;
			else if (archversion.equals(OSbit.b32))
				return OS.LINUX32;
		} else if (os.startsWith("windows")) {
			if (archversion.equals(OSbit.b64))
				return OS.WIN64;
			else if (archversion.equals(OSbit.b32))
				return OS.WIN32;

		} else if (os.toLowerCase().contains("os x")) {
			return OS.MACOS;
		}

		return null;
	}

	/**
	 * Gets the bits operation system.
	 *
	 * @return the bits operation system
	 */
	public static OSbit getBitsOperationSystem() {

		String arch = SystemUtils.OS_ARCH;

		if (arch.equals("amd64") || arch.equals("x86_64")) {
			return OSbit.b64;
		} else if (arch.equals("x86") || arch.equals("i386")) {
			return OSbit.b32;
		} else
			return null;
	}

	/**
	 * Gets the and set default save folder.
	 *
	 * @return the and set default save folder
	 */
	public static String getandSetDefaultSaveFolder() {

		String userHome = SystemUtils.getUserHome().getAbsolutePath();

		String savedir = null;

		OS os = getOperationSystem();
		if (os.equals(OS.LINUX64) || os.equals(OS.LINUX32) || os.equals(OS.MACOS))
			savedir = userHome + "/" + StaticGlobalFields.CONVERTERFOLDER;
		else if (os.equals(OS.WIN32) || os.equals(OS.WIN64))
			savedir = userHome + "\\" + StaticGlobalFields.CONVERTERFOLDER;

		File f = new File(savedir);

		if (f.exists() && f.isDirectory())
			return savedir;
		else
			f.mkdirs();

		return savedir;

	}

	/**
	 * Gets the system temporary folder.
	 *
	 * @return the system temporary folder
	 */
	public static String getSystemTemporaryFolder() {
		String tmpDir = System.getProperty("java.io.tmpdir");
		return tmpDir;
	}

	public static String getSubtitlesTempFolder() {
		String folder = getCurrentDir() + getSystemSeparator() + TEMPSUBS;
		File dir = new File(folder);
		if (!dir.exists())
			dir.mkdir();

		return folder;
	}

	public static void deleteSubtitlesTempFolder() throws IOException {
		String folder = getCurrentDir() + getSystemSeparator() + TEMPSUBS;
		File dir = new File(folder);
		if (dir.exists())
			FileUtils.deleteDirectory(dir);
	}

	public static String getTempFolder() {
		String folder = getCurrentDir() + getSystemSeparator() + TEMPFOLDER;
		File dir = new File(folder);
		if (!dir.exists())
			dir.mkdir();

		return folder;
	}

	public static void deleteTempFolder() {
		String folder = getCurrentDir() + getSystemSeparator() + TEMPFOLDER;
		File dir = new File(folder);
		if (dir.exists())
			try {
				FileUtils.deleteDirectory(dir);
			} catch (IOException e) {
				Logger.error(e);
			}
	}

	// RandomAccessFile raf=new RandomAccessFile(f1,"rw");
	// raf.close();

	/**
	 * Gets the system separator.
	 *
	 * @return the system separator
	 */
	public static String getSystemSeparator() {
		OS os = getOperationSystem();
		if (os.equals(OS.LINUX64) || os.equals(OS.LINUX32) || os.equals(OS.MACOS))
			return "/";
		else if (os.equals(OS.WIN32) || os.equals(OS.WIN64))
			return "\\";

		return null;
	}

	/**
	 * Gets the ffmpeg null command for the 2 pass process.
	 *
	 * @return the null command
	 */
	public static String getNullCommand() {

		OS os = getOperationSystem();
		if (os.equals(OS.LINUX64) || os.equals(OS.LINUX32) || os.equals(OS.MACOS))
			return "/dev/null";
		else if (os.equals(OS.WIN32) || os.equals(OS.WIN64))
			return "NUL";

		return null;
	}

	/**
	 * Shutdown.
	 *
	 * @return the string
	 * @throws RuntimeException the runtime exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 */
	public static String shutdown() throws RuntimeException, IOException {
		String shutdownCommand;
		OS os = getOperationSystem();

		if (os.equals(OS.LINUX64) || os.equals(OS.LINUX32)) {
			shutdownCommand = "sudo shutdown -h now";
		} else if (os.equals(OS.MACOS))
			shutdownCommand = "shutdown -h now";
		else if (os.equals(OS.WIN32) || os.equals(OS.WIN64)) {
			shutdownCommand = "shutdown.exe -s -t 0";
		} else {
			throw new RuntimeException("Unsupported operating system.");
		}
		return shutdownCommand;
	}

	public static boolean isAdmin() {
		Preferences prefs = Preferences.systemRoot();
		if (OSystem.isWindows()) {
			try {

				prefs.put("foo", "bar"); // SecurityException on Windows
				prefs.remove("foo");
				prefs.flush(); // BackingStoreException on Linux
				return true;
			} catch (Exception e) {
				return false;
			}
		} else
			return System.getProperty("user.name").equals("root");

	}

	public static ArrayList<String> executeSystemCommand(String command) {

		ArrayList<String> output = new ArrayList<>();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				output.add(line);
			}

		} catch (Exception e) {
			Logger.error(e);
		}

		if (output.size() == 0)
			return null;

		return output;

	}

}
