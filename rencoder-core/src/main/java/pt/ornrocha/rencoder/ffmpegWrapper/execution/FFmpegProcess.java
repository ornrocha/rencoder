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
package pt.ornrocha.rencoder.ffmpegWrapper.execution;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.commands.ReusableInputStream;
import pt.ornrocha.rencoder.ffmpegWrapper.configurations.FFmpegLogInfoContainer;
import pt.ornrocha.rencoder.ffmpegWrapper.execution.progress.ProgressBarUpdate;
import pt.ornrocha.rencoder.ffmpegWrapper.execution.progress.ProgressLogRegister;
import pt.ornrocha.rencoder.ffmpegWrapper.subtitles.SubtitleConverter;
import pt.ornrocha.rencoder.ffmpegWrapper.utilities.FFmpegUtils;
import pt.ornrocha.rencoder.helpers.osystem.OSystem;
import pt.ornrocha.rencoder.helpers.props.logs.LogManager;

// TODO: Auto-generated Javadoc
/**
 * The Class FFmpegProcess.
 */
public class FFmpegProcess implements Runnable {

	/** The cmd1pass. */
	protected ArrayList<String> cmd1pass = null;

	/** The cmd2pass. */
	protected ArrayList<String> cmd2pass = null;

	/** The pbuilder. */
	protected ProcessBuilder pbuilder = null;

	/** The process. */
	protected Process process = null;

	/** The inputstr. */
	protected InputStream inputstr = null;

	/** The ostream. */
	protected OutputStream ostream = null;

	/** The br. */
	protected BufferedReader br = null;

	/** The scan. */
	protected Scanner scan = null;

	/** The killer. */
	protected KillFFmpegProcess killer = null;

	/** The progbarupdater1pass. */
	protected ProgressBarUpdate progbarupdater1pass = null;

	/** The progbarupdater2pass. */
	protected ProgressBarUpdate progbarupdater2pass = null;

	/** The running. */
	protected boolean running = false;

	/** The cancelprocess. */
	protected boolean cancelprocess = false;

	/** The tempsubpath. */
	protected String tempsubpath = null;

	/** The temp log path. */
	protected String tempLogPath = null;

	/** The outputmoviefilepath. */
	protected String outputmoviefilepath = null;

	/** The progressbar. */
	protected Thread progressbar = null;

	protected boolean debug = false;

	protected FFmpegLogInfoContainer logsave = null;

	/**
	 * Instantiates a new ffmpeg encoder process.
	 *
	 * @param cmds the cmds
	 */
	public FFmpegProcess(ArrayList<String> cmds) {
		this.cmd1pass = cmds;

	}

	/**
	 * Instantiates a new ffmpeg encoder process.
	 *
	 * @param cmds the cmds
	 * @param kill the kill
	 */
	public FFmpegProcess(ArrayList<String> cmds, KillFFmpegProcess kill) {
		this.cmd1pass = cmds;
		this.killer = kill;
	}

	/**
	 * Instantiates a new ffmpeg encoder process.
	 *
	 * @param cmds      the cmds
	 * @param kill      the kill
	 * @param barprog   the barprog
	 * @param moviepath the moviepath
	 */
	public FFmpegProcess(ArrayList<String> cmds, KillFFmpegProcess kill, ProgressBarUpdate barprog, String moviepath,
			FFmpegLogInfoContainer savelog) {
		this.cmd1pass = cmds;
		this.killer = kill;
		this.progbarupdater1pass = barprog;
		this.outputmoviefilepath = moviepath;
		this.logsave = savelog;
	}

	/**
	 * Instantiates a new ffmpeg encoder process.
	 *
	 * @param cmd1pass  the cmd1pass
	 * @param cmd2pass  the cmd2pass
	 * @param kill      the kill
	 * @param bar1prog  the bar1prog
	 * @param bar2prog  the bar2prog
	 * @param moviepath the moviepath
	 */
	public FFmpegProcess(ArrayList<String> cmd1pass, ArrayList<String> cmd2pass, KillFFmpegProcess kill,
			ProgressBarUpdate bar1prog, ProgressBarUpdate bar2prog, String moviepath, FFmpegLogInfoContainer savelog) {
		this.cmd1pass = cmd1pass;
		this.cmd2pass = cmd2pass;
		this.killer = kill;
		this.progbarupdater1pass = bar1prog;
		this.progbarupdater2pass = bar2prog;
		this.outputmoviefilepath = moviepath;
		this.logsave = savelog;

	}

	/**
	 * Sets the temp sub path.
	 *
	 * @param subpath the new temp sub path
	 */
	public void setTempSubPath(String subpath) {
		this.tempsubpath = subpath;
	}

	/**
	 * Sets the temp log path.
	 *
	 * @param logpath the new temp log path
	 */
	public void setTempLogPath(String logpath) {
		this.tempLogPath = logpath;
	}

	/**
	 * Kill processes.
	 */
	public void KillProcesses() {
		this.killer.run();
	}

	/**
	 * Gets the killer.
	 *
	 * @return the killer
	 */
	public KillFFmpegProcess getKiller() {
		return this.killer;
	}

	/**
	 * Cancel process.
	 */
	public void cancelprocess() {
		this.cancelprocess = true;
	}

	/**
	 * Stop process.
	 */
	public void stopProcess() {
		if (running)
			KillProcesses();
		else
			cancelprocess();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

		try {
			int exitValue = 0;
			running = true;

			if (!cancelprocess) {
				String lcmds = String.join(" ", cmd1pass);
				Logger.debug("FFmpeg 1st pass encoding cmd: " + lcmds);
				pbuilder = new ProcessBuilder(this.cmd1pass);
				pbuilder.redirectErrorStream(true);
				if (this.cmd2pass == null) {
					setFFREPORT(pbuilder);

					if (OSystem.isMacOS())
						setMacFontconfigPath(pbuilder, cmd1pass);

				}

				process = pbuilder.start();

				if (this.killer != null)
					this.killer.setProcessOnepasstoKill(process);

				ostream = process.getOutputStream();
				inputstr = new ReusableInputStream(process.getInputStream());
				

				this.progbarupdater1pass.setInputStream(inputstr);

				if (this.cmd2pass != null)
					this.progbarupdater1pass.set2PassFlagInProcess();
				

				if(LogManager.showFFmpegAtRencoderLog()) {
					this.progbarupdater1pass.writeLog(true);
				}

				progressbar = new Thread(this.progbarupdater1pass);
				progressbar.run();
				
				
				
					

				Runtime.getRuntime().addShutdownHook(new Thread() {
					@Override
					public void run() {
						boolean delete=false;
							if(process.exitValue()!=0)
								delete=true;
						process.destroy();
						
						if(delete)
							deleteOutputMovieFile();
					}
				});

				exitValue = process.waitFor();

				if (exitValue == 1) {
					this.progbarupdater1pass.setErrorFlagInProcess();
					deleteOutputMovieFile();
					// deleteTempFiles();

				} else if (exitValue == 141) {
					this.progbarupdater1pass.setCancelFlagInProcess();
					deleteOutputMovieFile();
					// deleteTempFiles();
				}

				if (cmd2pass != null && exitValue == 0) {
					Logger.debug("FFmpeg 2sd pass encoding cmd: " + lcmds);
					pbuilder = new ProcessBuilder(this.cmd2pass);
					pbuilder.redirectErrorStream(true);
					if (this.cmd2pass != null) {
						setFFREPORT(pbuilder);

						if (OSystem.isMacOS())
							setMacFontconfigPath(pbuilder, cmd2pass);

					}
					Process process2pass = pbuilder.start();

					if (this.killer != null)
						this.killer.setProcessSecondpasstoKill(process2pass);

					ostream = process2pass.getOutputStream();
					inputstr = process2pass.getInputStream();

					this.progbarupdater2pass.setInputStream(inputstr);
					Thread t = new Thread(this.progbarupdater2pass);
					t.run();

					Runtime.getRuntime().addShutdownHook(new Thread() {
						@Override
						public void run() {
							boolean delete=false;
							if(process2pass.exitValue()!=0)
								delete=true;
							process2pass.destroy();

							if(delete)
								deleteOutputMovieFile();
						}
					});

					exitValue = process2pass.waitFor();

					if (exitValue == 1) {
						this.progbarupdater2pass.setErrorFlagInProcess();
						deleteOutputMovieFile();
						// deleteTempFiles();
					} else if (exitValue == 141) {
						this.progbarupdater2pass.setCancelFlagInProcess();
						deleteOutputMovieFile();
						// deleteTempFiles();
					}

				}

			}

			else {
				this.progbarupdater1pass.setCancelFlagInProcess();
				deleteOutputMovieFile();
				deleteTempFiles();
			}

			deleteTempFiles();

			// deleteTempSubFile();
			// deleteTempLogFile();

		} catch (IOException | InterruptedException e) {
			Logger.error(e);
		}
	}

	private void deleteTempFiles() {
		deleteTempSubFile();
		deleteTempLogFile();
	}

	private void setFFREPORT(ProcessBuilder pb) {
		if (logsave.isActive()) {
			Map<String, String> env = pb.environment();
			String msg = null;
			if (OSystem.isWindows())
				msg = logsave.getLoglevel().getLogLevelString() + ":" + "file='" + logsave.getLogfilepath()
						+ OSystem.getSystemSeparator() + logsave.getLogfilename() + "'";
			else
				msg = logsave.getLoglevel().getLogLevelString() + ":" + "file=" + logsave.getLogfilepath()
						+ OSystem.getSystemSeparator() + logsave.getLogfilename();
			env.put("FFREPORT", msg);
			// env.put("FFREPORT",
			// "level=-8:file=/home/orocha/ffmpeg_teste/ffmpeg_movie.log");
		}
	}

	private void setMacFontconfigPath(ProcessBuilder pb, ArrayList<String> cmds) {
		if (SubtitleConverter.containsAssParameter(cmds)) {
			String path = FFmpegUtils.getFFmpegExePath();
			String p = Paths.get(path).getParent().toString();
			String fc = p + OSystem.getSystemSeparator() + "fontconfig";
			Map<String, String> env = pb.environment();
			env.put("FONTCONFIG_PATH", fc);
		}
	}

	/**
	 * Delete temp sub file.
	 */
	private void deleteTempSubFile() {
		if (this.tempsubpath != null) {
			File sub = new File(tempsubpath);
			if (sub.exists())
				sub.delete();
		}
	}

	/**
	 * Delete temp log file.
	 */
	private void deleteTempLogFile() {
		if (this.tempLogPath != null) {
			File log = new File(tempLogPath);
			if (log.exists())
				log.delete();

			String mbtree = tempLogPath + ".mbtree";
			deleteExtraTempLogFilembtree(mbtree);
		}
	}

	/**
	 * Delete extra temp log file mbtree.
	 *
	 * @param path the path
	 */
	private void deleteExtraTempLogFilembtree(String path) {
		File mbtree = new File(path);
		if (mbtree.exists())
			mbtree.delete();

	}

	/**
	 * Delete output movie file.
	 */
	private void deleteOutputMovieFile() {
		if (this.outputmoviefilepath != null) {
			File movie = new File(outputmoviefilepath);
			if (movie.exists())
				movie.delete();
		}

	}

	/**
	 * Gets the out stream.
	 *
	 * @return the out stream
	 */
	public OutputStream getOutStream() {
		return this.ostream;
	}

}
