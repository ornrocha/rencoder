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
package pt.ornrocha.rencoder.ffmpegWrapper.commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.configurations.FFmpegLogInfoContainer;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioSampleRates;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.general.FFmpegLogLevel;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.AspectRatio;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.FPS;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.HWAccel;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoContainers;
import pt.ornrocha.rencoder.ffmpegWrapper.execution.ExecFilesInfoContainer;
import pt.ornrocha.rencoder.ffmpegWrapper.othercomponents.AspectComponent;
import pt.ornrocha.rencoder.ffmpegWrapper.subtitles.SubtitleConverter;
import pt.ornrocha.rencoder.ffmpegWrapper.utilities.FFmpegUtils;
import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.helpers.osystem.OSystem;
import pt.ornrocha.rencoder.helpers.props.fields.StaticFFmpegFields;
import pt.ornrocha.rencoder.helpers.props.fields.StaticGlobalFields;
import pt.ornrocha.rencoder.helpers.props.managers.auxiliar.EncodingPropsAuxiliar;
import pt.ornrocha.rencoder.mediafiles.files.auxiliar.ProcessFilesAux;
import pt.ornrocha.rencoder.mediafiles.files.containers.base.Subtitlefile;
import pt.ornrocha.rencoder.mediafiles.files.containers.base.Videofile;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoH264EncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.FiltersInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.SubtitleInfoContainer;
import pt.ornrocha.rencoder.mediafiles.setfiles.processfiles.ProcessFiles;

// TODO: Auto-generated Javadoc
/**
 * The Class EncoderControlCenter.
 */
public class EncoderControlCenter {

	/** The moviefiles. */
	private IndexedHashMap<String, Videofile> moviefiles = null;

	/** The moviesencodingcmds. */
	private IndexedHashMap<String, ArrayList<String>> moviesencodingcmds = new IndexedHashMap<>();

	/** The movieencodingcmds. */
	private IndexedHashMap<String, ArrayList<ArrayList<String>>> allmovieencodingcmds = new IndexedHashMap<>();

	/** The dot. */
	private final String DOT = ".";

	/** The deletetempsubscontroler. */
	private HashMap<String, String> deletetempsubscontroler = new HashMap<>();

	/** The delete temp log files. */
	private HashMap<String, String> deleteTempLogFiles = new HashMap<>();

	/** The outputmoviefilepaths. */
	private IndexedHashMap<String, String> outputmoviefilepaths = new IndexedHashMap<>();

	/** The isused soft subtitles. */
	private boolean isusedSoftSubtitles = false;

	/** The isused two pass encoding. */
	private boolean isusedTwoPassEncoding = false;

	/** The firstpassconfigurationstep. */
	private boolean firstpassconfigurationstep = false;

	private boolean saveffmpeglogs = false;

	private ArrayList<FFmpegLogInfoContainer> loginfos = new ArrayList<>();

	/**
	 * Instantiates a new encoder control center.
	 *
	 * @param moviedata the moviedata
	 */
	public EncoderControlCenter(IndexedHashMap<String, Videofile> moviedata) {
		this.moviefiles = moviedata;
		processMoviesInfoToEncoder();
	}

	/**
	 * Gets the movies encoding commands.
	 *
	 * @return the movies encoding commands
	 */
	public IndexedHashMap<String, ArrayList<String>> getMoviesEncodingCommands() {
		return this.moviesencodingcmds;
	}

	/**
	 * Gets the all movies encoding commands.
	 *
	 * @return the all movies encoding commands
	 */
	public IndexedHashMap<String, ArrayList<ArrayList<String>>> getAllMoviesEncodingCommands() {
		return this.allmovieencodingcmds;
	}

	/**
	 * Gets the temp subtitles file paths.
	 *
	 * @return the temp subs file paths
	 */
	public HashMap<String, String> getTempSubsFilePaths() {
		return this.deletetempsubscontroler;
	}

	/**
	 * Gets the temp log file paths.
	 *
	 * @return the temp log file paths
	 */
	public HashMap<String, String> getTempLogFilePaths() {
		return this.deleteTempLogFiles;
	}

	/**
	 * Gets the output movie file paths.
	 *
	 * @return the output movie file paths
	 */
	public IndexedHashMap<String, String> getOutputMovieFilePaths() {
		return this.outputmoviefilepaths;
	}

	public ArrayList<FFmpegLogInfoContainer> getFFmpegLogInfoContainer() {
		return this.loginfos;
	}

	public ExecFilesInfoContainer getInfoToConvertFiles() {

		return new ExecFilesInfoContainer(getAllMoviesEncodingCommands(), getTempSubsFilePaths(), getTempLogFilePaths(),
				getOutputMovieFilePaths(), getFFmpegLogInfoContainer());

	}

	/**
	 * Process movies info to encoder.
	 */
	public void processMoviesInfoToEncoder() {
		this.saveffmpeglogs = FFmpegUtils.isFFmpegWriteLog();
		int nfiles = this.moviefiles.size();
		try {

			for (int i = 0; i < nfiles; i++) {

				ArrayList<ArrayList<String>> moviecmd = new ArrayList<>();

				Videofile currentvideo = moviefiles.getValueAt(i);
				checkTypeOfEncoding(currentvideo);
				checkTypeSubtitlesEncoding(currentvideo);

				if (isusedTwoPassEncoding) {
					ArrayList<String> firstpass = new ArrayList<>();
					firstpass.addAll(setupFirstPassEncoding(currentvideo));
					moviecmd.add(firstpass);

					ArrayList<String> secondpass = new ArrayList<>();
					secondpass.addAll(setupSecondPassEncoding(currentvideo));
					moviecmd.add(secondpass);
				}

				else {
					ArrayList<String> onepass = new ArrayList<>();
					onepass.addAll(setupOnePassEncoding(currentvideo));
					moviecmd.add(onepass);

				}

				allmovieencodingcmds.put(currentvideo.getFilePath(), moviecmd);

			}

		} catch (Exception e) {
			Logger.error(e);
			
		}

	}

	/**
	 * Setup one pass encoding.
	 *
	 * @param currentvideo the currentvideo
	 * @return the array list
	 */
	private ArrayList<String> setupOnePassEncoding(Videofile currentvideo) {
		ArrayList<String> onepass = new ArrayList<>();

		try {

			setFFmpegLogInfoToVideoFile(currentvideo);

			onepass.addAll(getInitialFFmpegCMDs(currentvideo));

			if (isusedSoftSubtitles)
				onepass.addAll(getSubtitlesInputFilesFFmpegCMDs(currentvideo));

			if (currentvideo.getEncodingInfoContainer().getFilters() != null) {
				onepass.addAll(getFiltersCMD(currentvideo));
			}

			onepass.addAll(processVideoEncodingInfo(currentvideo));
			onepass.addAll(processAudioEncodingInfo(currentvideo));

			ArrayList<String> subencodcmd = processSubtitleInfo(currentvideo);
			if (subencodcmd.size() > 0) {
				VideoCodecs codec = currentvideo.getEncodingInfoContainer().getVideocodec();
				HWAccel decodercodec = currentvideo.getEncodingInfoContainer().getHardwareAccelerationDecoder();

				if (codec.supportsDecodingHardwareAccel() && !decodercodec.equals(HWAccel.NONE)
						&& currentvideo.getEncodingInfoContainer().getSubtitleInfoContainer().isUseHardSubs()) {

					int indexvf = onepass.indexOf(StaticFFmpegFields.FILTERFFMPEGCMD);

					if (indexvf > 0) {
						String cmd = onepass.get(indexvf + 1);
						cmd = decodercodec.getHardSubsFilter(subencodcmd.get(1));
						onepass.set(indexvf + 1, cmd);

					} else if (decodercodec.joinDecodingAndSubtitleFilters()) {
						String cmd = decodercodec.getHardSubsFilter(subencodcmd.get(1));
						onepass.add("-vf");
						onepass.add(cmd);
					} else {
						onepass.addAll(subencodcmd);
					}

				} else
					onepass.addAll(subencodcmd);
			}

			onepass.addAll(processFileOutput(currentvideo));

		} catch (Exception e) {
			Logger.error(e);
		}

		return onepass;
	}

	/**
	 * Setup first pass encoding.
	 *
	 * @param currentvideo the currentvideo
	 * @return the array list
	 */
	private ArrayList<String> setupFirstPassEncoding(Videofile currentvideo) {
		ArrayList<String> firstpass = new ArrayList<>();

		try {
			this.firstpassconfigurationstep = true;
			firstpass.addAll(getInitialFFmpegCMDs(currentvideo));

			if (!currentvideo.getEncodingInfoContainer().getVideocodec().equals(VideoCodecs.COPY)) {
				firstpass.addAll(processVideoEncodingInfo(currentvideo));
			} else
				throw new Exception("Video Copy is not allowed");

			firstpass.addAll(getFirstpassFFmpegCMD(currentvideo));

			this.firstpassconfigurationstep = false;
		} catch (Exception e) {
			Logger.error(e);
		}

		return firstpass;
	}

	/**
	 * Setup second pass encoding.
	 *
	 * @param currentvideo the currentvideo
	 * @return the array list
	 */
	private ArrayList<String> setupSecondPassEncoding(Videofile currentvideo) {
		ArrayList<String> secondpass = new ArrayList<>();

		try {

			setFFmpegLogInfoToVideoFile(currentvideo);

			secondpass.addAll(getInitialFFmpegCMDs(currentvideo));

			if (isusedSoftSubtitles)
				secondpass.addAll(getSubtitlesInputFilesFFmpegCMDs(currentvideo));

			if (currentvideo.getEncodingInfoContainer().getFilters() != null) {
				secondpass.addAll(getFiltersCMD(currentvideo));
			}

			secondpass.addAll(processVideoEncodingInfo(currentvideo));
			secondpass.add(StaticFFmpegFields.passnumber);
			secondpass.add(String.valueOf(2));
			secondpass.add(StaticFFmpegFields.videopasslog);
			secondpass.add(getTempLogDirectory(currentvideo));
			secondpass.addAll(processAudioEncodingInfo(currentvideo));

			ArrayList<String> subencodcmd = processSubtitleInfo(currentvideo);
			if (subencodcmd.size() > 0)
				secondpass.addAll(subencodcmd);

			secondpass.addAll(processFileOutput(currentvideo));

		} catch (Exception e) {
			Logger.error(e);
		}

		return secondpass;
	}

	/**
	 * Gets the initial ffmpeg commands.
	 *
	 * @param currentvideo the currentvideo
	 * @return the initial ffmpeg cmds
	 */
	private ArrayList<String> getInitialFFmpegCMDs(Videofile currentvideo) {
		ArrayList<String> init = new ArrayList<>();
		try {

			init.add(getEncoderExePath());

			if (saveffmpeglogs && !firstpassconfigurationstep)
				init.add("-report");

			HWAccel decodercodec = currentvideo.getEncodingInfoContainer().getHardwareAccelerationDecoder();
			if (decodercodec != null)
				init.addAll(decodercodec.getFFmpegCMD());

			init.add(StaticFFmpegFields.input);
			if (OSystem.isWindows())
				init.add(StaticGlobalFields.QUOTE + currentvideo.getFilePath() + StaticGlobalFields.QUOTE);
			else
				init.add(currentvideo.getFilePath());

			if (currentvideo.getEncodingInfoContainer().getVideocodec().needsDecodingFilter())
				init.addAll(currentvideo.getEncodingInfoContainer().getVideocodec().getCmdDecodingFilter());

			if (!firstpassconfigurationstep) {
				int threads = currentvideo.getEncodingInfoContainer().getNumberThreads();
				if (threads > 0) {
					init.add(StaticFFmpegFields.cputhreads);
					init.add(String.valueOf(currentvideo.getEncodingInfoContainer().getNumberThreads()));
				}
			}
		} catch (Exception e) {
			Logger.error(e);
		}

		return init;
	}

	/**
	 * Gets the ffmpeg exe path.
	 *
	 * @return the ffmpeg exe path
	 * @throws Exception the exception
	 */
	public static String getEncoderExePath() {
		return FFmpegUtils.getFFmpegExePath();
	}

	/**
	 * Check type subtitles encoding.
	 *
	 * @param currentvideo the currentvideo
	 */
	private void checkTypeSubtitlesEncoding(Videofile currentvideo) {
		IGeneralVideoEncInfoContainer encodinfo = currentvideo.getEncodingInfoContainer();

		if (encodinfo.getSubtitleInfoContainer().isUseSoftSubs()) {
			if (currentvideo.getSelectedSubtitleFiles() != null)
				this.isusedSoftSubtitles = true;
			else
				this.isusedSoftSubtitles = false;
		} else
			this.isusedSoftSubtitles = false;

	}

	/**
	 * Check type of encoding.
	 *
	 * @param currentvideo the currentvideo
	 */
	private void checkTypeOfEncoding(Videofile currentvideo) {
		if (currentvideo.getEncodingInfoContainer().isTwopassEncoding())
			this.isusedTwoPassEncoding = true;
		else
			this.isusedTwoPassEncoding = false;

	}

	/**
	 * Gets the firstpass ffmpeg cmd.
	 *
	 * @param currentfile the currentfile
	 * @return the firstpass ffmpeg cmd
	 */
	private ArrayList<String> getFirstpassFFmpegCMD(Videofile currentfile) {
		ArrayList<String> firstcmd = new ArrayList<>();

		firstcmd.add(StaticFFmpegFields.passnumber);
		firstcmd.add(String.valueOf(1));
		firstcmd.add(StaticFFmpegFields.videopasslog);
		firstcmd.add(getTempLogDirectory(currentfile));
		firstcmd.add(StaticFFmpegFields.disableaudio);
		firstcmd.add(StaticFFmpegFields.force_video_format);
		firstcmd.add("rawvideo");
		firstcmd.add(StaticFFmpegFields.overwrite_output_file);
		firstcmd.add(OSystem.getNullCommand());

		return firstcmd;
	}

	/**
	 * Gets the subtitles input files ffmpeg cmds.
	 *
	 * @param currentfile the currentfile
	 * @return the subtitles input files f fmpeg cm ds
	 */
	private ArrayList<String> getSubtitlesInputFilesFFmpegCMDs(Videofile currentfile) {

		ArrayList<String> subsinputcmds = new ArrayList<>();
		ArrayList<Subtitlefile> subs = currentfile.getSelectedSubtitleFiles();

		for (int i = 0; i < subs.size(); i++) {
			subsinputcmds.add(StaticFFmpegFields.SUBCHARENCODING);
			subsinputcmds.add(
					currentfile.getEncodingInfoContainer().getSubtitleInfoContainer().getSubsEncoding().toString());
			subsinputcmds.add(StaticFFmpegFields.input);
			if (OSystem.isWindows())
				subsinputcmds.add(StaticGlobalFields.QUOTE + subs.get(i).getFilePath() + StaticGlobalFields.QUOTE);
			else
				subsinputcmds.add(subs.get(i).getFilePath());
		}

		return subsinputcmds;
	}

	public ArrayList<String> processVideoEncodingInfo(Videofile movie) {

		ArrayList<String> videocmds = new ArrayList<>();

		IGeneralVideoEncInfoContainer encodinfo = movie.getEncodingInfoContainer();

		VideoCodecs codec = encodinfo.getVideocodec();

		if (!codec.equals(VideoCodecs.COPY)) {

			ArrayList<String> appendcmds = new ArrayList<>();
			boolean checkcodecsettings = true;

			if (isusedSoftSubtitles) {

				if ((!codec.equals(VideoCodecs.MPEG2) || !codec.equals(VideoCodecs.MPEG4)
						|| !codec.equals(VideoCodecs.XVID) || !codec.equals(VideoCodecs.THEORA))
						&& (encodinfo.getVideoContainer().equals(VideoContainers.MKV)
								|| encodinfo.getVideoContainer().equals(VideoContainers.MP4))) {
					videocmds.add(StaticFFmpegFields.MAPFILE);
					videocmds.add("0:v");
				}

			}

			if (codec.equals(VideoCodecs.H264)) {
				if (isusedTwoPassEncoding && firstpassconfigurationstep) {
					if (((VideoH264EncodingInfoContainer) encodinfo).isFastfirstpass()) {
						appendcmds.add(StaticFFmpegFields.x264fastfirstpass);
						appendcmds.add("1");
					} else {
						appendcmds.add(StaticFFmpegFields.x264fastfirstpass);
						appendcmds.add("0");
					}
				}
			}

			videocmds.addAll(encodinfo.getVideocodec().getMainCodecCmd());
			if (encodinfo.useSpecificVideoEncodingParameters() && checkcodecsettings)
				videocmds.addAll(encodinfo.getSpecificVideoEncoderParameters(appendcmds));

			videocmds.addAll(processVideoBitrateValues(encodinfo, movie));
			ArrayList<String> aspectparam = processVideoAspectRelatedParameters(encodinfo);
			if (aspectparam.size() > 0)
				videocmds.addAll(aspectparam);

		}

		else {
			videocmds.add(StaticFFmpegFields.encodevideocodec);
			videocmds.add(StaticFFmpegFields.copy);
		}

		return videocmds;
	}

	/**
	 * Process video bitrate values.
	 *
	 * @param encoinfo the encoinfo
	 * @param file     the file
	 * @return the array list
	 */
	private ArrayList<String> processVideoBitrateValues(IGeneralVideoEncInfoContainer encoinfo, Videofile file) {

		VideoCodecs codec = encoinfo.getVideocodec();
		ArrayList<String> bitratecmds = new ArrayList<>();

		if (encoinfo.isUseVideoEncodingCBR()) {
			bitratecmds.add(StaticFFmpegFields.video_bitrate);
			bitratecmds.add(encoinfo.getVideoConstBitrate());

		} else if (encoinfo.isUseVideoEncodingVBR()) {

			if (codec.equals(VideoCodecs.XVID) || codec.equals(VideoCodecs.MPEG4)) {
				bitratecmds.add(StaticFFmpegFields.video_quality);
				bitratecmds.add(String.valueOf(encoinfo.getVideoVBRQualityValue()));
			} else if (codec.equals(VideoCodecs.H264)) {
				bitratecmds.add(StaticFFmpegFields.Hvariablebitrate);
				bitratecmds.add(String.valueOf(encoinfo.getVideoVBRQualityValue()));
			} else if (codec.equals(VideoCodecs.H264NVENC)) {
				bitratecmds.add(StaticFFmpegFields.h264nvencvariablebitrate);
				bitratecmds.add(String.valueOf(encoinfo.getVideoVBRQualityValue()));
			} else if (codec.equals(VideoCodecs.H264VAAPI)) {
				bitratecmds.add(StaticFFmpegFields.x264constquant);
				bitratecmds.add(String.valueOf(encoinfo.getVideoVBRQualityValue()));
			} else if (codec.equals(VideoCodecs.H265)) {
				bitratecmds.add(StaticFFmpegFields.Hvariablebitrate);
				bitratecmds.add(String.valueOf(encoinfo.getVideoVBRQualityValue()));
			} else if (codec.equals(VideoCodecs.VP8) || codec.equals(VideoCodecs.VP9)) {
				bitratecmds.add(StaticFFmpegFields.vpxvariablebitrate);
				bitratecmds.add(String.valueOf(encoinfo.getVideoVBRQualityValue()));
			} else if (codec.equals(VideoCodecs.AV1)) {
				bitratecmds.add(StaticFFmpegFields.Hvariablebitrate);
				bitratecmds.add(String.valueOf(encoinfo.getVideoVBRQualityValue()));
				bitratecmds.add("-b:v");
				bitratecmds.add("0");
			} else if (codec.equals(VideoCodecs.MPEG2)) {
				bitratecmds.add(StaticFFmpegFields.video_qualityshortterm);
				bitratecmds.add(String.valueOf(encoinfo.getVideoVBRQualityValue()));
			}
		} else if (encoinfo.isUseVideoEncodingFileSize()) {

			String videotime = file.getMovieinfocontainer().getVideoinfo().getVideototalduration();
			double videotimeseconds = FFmpegUtils.convertTimetoseconds(videotime);

			int videobitrate = (encoinfo.getVideoEncodingFileSize() * 8192) / (int) videotimeseconds;

			String audiobitrate = encoinfo.getAudioConstantBitrateValue();
			String audiobitratenumber = null;
			Pattern aspect = Pattern.compile("(\\d+)k");
			Matcher m = aspect.matcher(audiobitrate);
			if (m.find()) {
				audiobitratenumber = m.group(1);
			}
			int audiocbr = Integer.parseInt(audiobitratenumber);

			String outputvideoCBR = String.valueOf(videobitrate - audiocbr) + "k";
			bitratecmds.add(StaticFFmpegFields.video_bitrate);
			bitratecmds.add(outputvideoCBR);

		}

		if (codec.isExperimental()) {
			bitratecmds.add("-strict");
			bitratecmds.add("-2");
		}

		return bitratecmds;
	}

	/**
	 * Process video aspect related parameters.
	 *
	 * @param encodinfo the encodinfo
	 * @return the array list
	 */
	private ArrayList<String> processVideoAspectRelatedParameters(IGeneralVideoEncInfoContainer encodinfo) {
		ArrayList<String> aspectparam = new ArrayList<>();
		boolean videoratioinaspectsizecomponent = false;

		if (!encodinfo.getVideoaspectsizecomponent().getNameAspect().equals(AspectComponent.SAMEASSOURCE)) {
			aspectparam.add(StaticFFmpegFields.video_resolution);
			aspectparam.add(encodinfo.getVideoaspectsizecomponent().getSizemesu());
			if (encodinfo.getVideoaspectsizecomponent().getAspectRatio() != null) {
				aspectparam.add(StaticFFmpegFields.video_aspect);
				aspectparam.add(encodinfo.getVideoaspectsizecomponent().getAspectRatio().toString());
				videoratioinaspectsizecomponent = true;
			}
		}

		if (!videoratioinaspectsizecomponent) {
			if (!encodinfo.getVideoaspectratio().equals(AspectRatio.SAMEASSOURCE)) {
				aspectparam.add(StaticFFmpegFields.video_aspect);
				aspectparam.add(encodinfo.getVideoaspectratio().toString());
			}
		}

		if (!encodinfo.getVideoframerate().equals(FPS.SAMEASSOURCE)) {
			aspectparam.add(StaticFFmpegFields.framerate);
			aspectparam.add(encodinfo.getVideoframerate().toString());
		}

		return aspectparam;
	}

	/**
	 * Process audio encoding info.
	 *
	 * @param movie the movie
	 * @return the array list
	 */
	public ArrayList<String> processAudioEncodingInfo(Videofile movie) {

		IGeneralVideoEncInfoContainer audioencinfo = movie.getEncodingInfoContainer();

		ArrayList<String> audioencparam = new ArrayList<>();

		if (this.isusedSoftSubtitles) {
			audioencparam.add(StaticFFmpegFields.MAPFILE);
			audioencparam.add(StaticFFmpegFields.FIRSTAUDIOFILE);
		}

		if (!audioencinfo.isUseonlyextraaudioffmpegcmd()) {
			AudioCodecs audiocodec = audioencinfo.getAudiocodec();

			audioencparam.addAll(audiocodec.getmainencodcommand());

			if (!audiocodec.equals(AudioCodecs.COPY)) {

				audioencparam.addAll(getAudioBitrate(audioencinfo));

				if (audiocodec.useSpecificComands())
					audioencparam.addAll(audiocodec.getSpecificCommands());

				if (!audioencinfo.getAudiosamplerate().equals(AudioSampleRates.SAMEASSOURCE)) {
					audioencparam.addAll(audioencinfo.getAudiosamplerate().getffmpegcommand());
				}

				audioencparam.addAll(audioencinfo.getAudiochannels().getffmpegcommand());
			}
		}

		if (audioencinfo.getExtraaudioffmpegcmd() != null)
			audioencparam.addAll(EncodingPropsAuxiliar.getExtraFFmpegCMDs(audioencinfo.getExtraaudioffmpegcmd()));

		return audioencparam;
	}

	/**
	 * Gets the audio bitrate.
	 *
	 * @param audioencoinfo the audioencoinfo
	 * @return the audio bitrate
	 */
	private ArrayList<String> getAudioBitrate(IGeneralVideoEncInfoContainer audioencoinfo) {
		ArrayList<String> bitrateset = new ArrayList<>();

		if (audioencoinfo.isUseAudioCBR()) {
			bitrateset.add(StaticFFmpegFields.audiocbr);
			bitrateset.add(audioencoinfo.getAudioConstantBitrateValue());
		} else {
			bitrateset.add(StaticFFmpegFields.audiovbr);
			AudioCodecs acodec = audioencoinfo.getAudiocodec();
			if (acodec.equals(AudioCodecs.LCAAC))
				bitrateset.add(String.valueOf(
						AudioCodecs.getAACVariablebitrateMap().get(audioencoinfo.getAudioVariableBitrateValues())));
			else
				bitrateset.add(String.valueOf(audioencoinfo.getAudioVariableBitrateValues()));
		}
		return bitrateset;
	}

	/**
	 * Process subtitle info.
	 *
	 * @param movie the movie
	 * @return the array list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public ArrayList<String> processSubtitleInfo(Videofile movie) throws IOException {

		ArrayList<String> subencodcmd = new ArrayList<>();
		try {
			SubtitleInfoContainer encSettings = movie.getEncodingInfoContainer().getSubtitleInfoContainer();

			if (encSettings.isUseHardSubs()) {
				Subtitlefile selectedSub = movie.getFirstSelectedSubtitleFile();

				if (selectedSub != null) {

					SubtitleConverter subconv = new SubtitleConverter(selectedSub, encSettings);
					subencodcmd.addAll(subconv.getFFmpegSubtitleEncodingCommands(null));
					deletetempsubscontroler.put(movie.getFilePath(), subconv.getTempSubPath());
				}
			} else if (this.isusedSoftSubtitles) {
				ArrayList<Subtitlefile> subs = movie.getSelectedSubtitleFiles();

				for (int i = 0; i < subs.size(); i++) {

					Subtitlefile sub = subs.get(i);
					subencodcmd.add(StaticFFmpegFields.MAPFILE);
					subencodcmd.add(String.valueOf(i + 1) + ":0");
					subencodcmd.add(StaticFFmpegFields.SUBCODEC);

					if (movie.getEncodingInfoContainer().getVideoContainer().equals(VideoContainers.MKV)) {

						subencodcmd.add(sub.getExtension());
					}

					else if (movie.getEncodingInfoContainer().getVideoContainer().equals(VideoContainers.MP4)) {
						subencodcmd.add(StaticFFmpegFields.SUBMP4ENCO);
					}
					String metadata = StaticFFmpegFields.SUBMETADATAORDER + String.valueOf(i);
					subencodcmd.add(metadata);

					String language = StaticFFmpegFields.SUBLANGUAGE + sub.getLanguage();
					subencodcmd.add(language);

				}

			}

		} catch (Exception e) {
			Logger.error(e);
		}

		return subencodcmd;

	}

	/**
	 * Process file output.
	 *
	 * @param file the file
	 * @return the array list
	 */
	private ArrayList<String> processFileOutput(Videofile file) {

		ArrayList<String> output = new ArrayList<>();

		IGeneralVideoEncInfoContainer encodinfo = file.getEncodingInfoContainer();
		VideoContainers filecontainer = encodinfo.getVideoContainer();
		String outputfolder = encodinfo.getOutputFolder();
		String filenamenoext = ProcessFilesAux.replaceInvalidFFmpegChars(file.getNameWithoutExtension());
		String filepath = null;

		if (outputfolder == null) {
			String originpath = file.getParentdir();
			String newpath = FilenameUtils.concat(originpath, "converted");

			if (!new File(newpath).exists())
				new File(newpath).mkdirs();

			outputfolder = newpath;
		}

		if (encodinfo.isOverwritefile()) {
			output.add(StaticFFmpegFields.overwrite_output_file);

			if (filecontainer.equals(VideoContainers.SAMEASSOURCE)) {
				filepath = outputfolder + OSystem.getSystemSeparator() + filenamenoext + DOT + file.getExtension();
			} else {
				filepath = outputfolder + OSystem.getSystemSeparator() + filenamenoext + DOT + filecontainer.toString();
			}
		}

		else {
			if (filecontainer.equals(VideoContainers.SAMEASSOURCE))
				filepath = ProcessFiles.checkIfFileExistsOrInPipelineandReturnNewname(outputfolder, file,
						file.getExtension(), 0, false, outputmoviefilepaths);

			else
				filepath = ProcessFiles.checkIfFileExistsOrInPipelineandReturnNewname(outputfolder, file,
						filecontainer.toString(), 0, false, outputmoviefilepaths);
		}

		if (OSystem.isWindows())
			output.add(StaticGlobalFields.QUOTE + filepath + StaticGlobalFields.QUOTE);
		else
			output.add(filepath);

		outputmoviefilepaths.put(file.getFilePath(), filepath);

		return output;
	}

	/**
	 * Gets the temp log directory.
	 *
	 * @param currentvideo the currentvideo
	 * @return the temp log directory
	 */
	private String getTempLogDirectory(Videofile currentvideo) {
		String tempfolder = OSystem.getSystemTemporaryFolder();
		String tempLogpath = tempfolder + OSystem.getSystemSeparator() + currentvideo.getNameWithoutExtension();
		String tempLogPathtoMap = tempLogpath + "-0.log";

		ProcessFilesAux.deleteIfExistTempFile(tempLogPathtoMap);

		if (!this.deleteTempLogFiles.containsKey(currentvideo.getFilePath()))
			this.deleteTempLogFiles.put(currentvideo.getFilePath(), tempLogPathtoMap);

		if (OSystem.isWindows())
			tempLogpath = StaticGlobalFields.QUOTE + tempLogpath + StaticGlobalFields.QUOTE;

		return tempLogpath;
	}

	private void setFFmpegLogInfoToVideoFile(Videofile currentvideo) {
		String filename = currentvideo.getNameWithoutExtension();
		FFmpegLogLevel loglevel = FFmpegUtils.getSettingsFFmpegLogLevel();
		FFmpegLogInfoContainer loginfo = null;

		if (loglevel != null)
			loginfo = new FFmpegLogInfoContainer(filename, loglevel);
		else
			loginfo = new FFmpegLogInfoContainer(filename);

		if (isusedTwoPassEncoding)
			loginfo.setSecondpassonly(true);
		if (saveffmpeglogs)
			loginfo.setActive(true);

		loginfos.add(loginfo);

	}

	private ArrayList<String> getFiltersCMD(Videofile currentvideo) {
		FiltersInfoContainer filters = currentvideo.getEncodingInfoContainer().getFilters();
		ArrayList<String> filterscmds = filters.getFFmpegCmds();
		ArrayList<String> output = new ArrayList<>();

		if (filterscmds != null) {
			if (filterscmds.size() > 0) {

				output.add(StaticFFmpegFields.FILTERFFMPEGCMD);

				String cmd = "";
				for (int i = 0; i < filterscmds.size(); i++) {
					cmd = cmd + filterscmds.get(i);

					if (i < filterscmds.size() - 1)
						cmd = cmd + ", ";

				}

				output.add(cmd);
				Logger.trace(cmd);
			}
		}
		return output;
	}

}
