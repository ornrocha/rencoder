package pt.ornrocha.rencoder.ffmpegWrapper.configurations;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import org.apache.commons.io.FilenameUtils;
import org.tinylog.Logger;
import com.neovisionaries.i18n.LanguageAlpha3Code;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.general.EncoderType;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.HWAccel;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoContainers;
import pt.ornrocha.rencoder.ffmpegWrapper.execution.progress.FFmpegCodecInfoAnalyser;
import pt.ornrocha.rencoder.ffmpegWrapper.utilities.FFmpegUtils;
import pt.ornrocha.rencoder.helpers.osystem.OSystem;
import pt.ornrocha.rencoder.helpers.props.fields.StaticGlobalFields;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;

public class FFmpegManager {

	static FFmpegManager instance;
	private RencoderFFmpegInfoContainer ffmpegInfoContainer = null;

	private String ffmpegPath = null;
	protected HashMap<String, String> maplangtocode = null;
	protected HashMap<String, String> mapcodetolang = null;
	private ArrayList<String> appffmpegversions;
	private PropertyChangeListener currentlistener=null;

	public static FFmpegManager getInstance() {
		if (instance == null)
			instance = new FFmpegManager();
		return instance;
	}

	private FFmpegManager() {

		loadLanguageCodes();
	}

	private void loadLanguageCodes() {

		maplangtocode = new HashMap<>();
		mapcodetolang = new HashMap<>();
		for (LanguageAlpha3Code code : LanguageAlpha3Code.values()) {
			maplangtocode.put(code.getName(), code.toString());
			mapcodetolang.put(code.toString(), code.getName());
		}
	}

	public FFmpegManager setFFmpegPath(String ffmpegpath) {
		this.ffmpegPath = ffmpegpath;
		PropertiesWorker.ChangePropertiesParam(StaticGlobalFields.RENCODERCONFIGFILE,
				StaticGlobalFields.FFMPEGPATH, ffmpegpath);
		return this;
	}
	
	

	public FFmpegManager setListener(PropertyChangeListener currentlistener) {
    this.currentlistener = currentlistener;
    return this;
  }

  public String getFFmpegPath() {
		return this.ffmpegPath;
	}
	
	
	public ArrayList<HWAccel> getSupportedHwaccelDecoders(VideoCodecs codec) {
		
		if(ffmpegInfoContainer.getHwacceldecoders(codec).size()>0) {
		  ArrayList<HWAccel> supported=new ArrayList<HWAccel>(ffmpegInfoContainer.getHwacceldecoders(codec));
		  
		  if(supported.size()>1)
			  supported.add(HWAccel.NONE);
		  
		  return supported;
		}
		
		return new ArrayList<HWAccel>();
	}



	public boolean isCustomFFmpeg(String path) {
		if (appffmpegversions.contains(path))
			return true;
		return false;
	}

	public String getLanguageFromISO3Code(String code) {
		if (mapcodetolang.containsKey(code))
			return mapcodetolang.get(code);
		return code;
	}

	public String getISO3CodeFromLanguage(String language) {
		if (maplangtocode.containsKey(language))
			return maplangtocode.get(language);
		return "eng";
	}

	public ArrayList<String> getOrderedCountryList() {
		ArrayList<String> countries = new ArrayList<String>(this.maplangtocode.keySet());
		Collections.sort(countries);
		return countries;
	}



	public HashMap<String, String> getMaplangtocode() {
		return maplangtocode;
	}


	public HashMap<String, String> getMapcodetolang() {
		return mapcodetolang;
	}

	public boolean LoadFFmpeg() throws InterruptedException, IOException {

		if (ffmpegPath == null)
			ffmpegPath = FFmpegUtils.getFFmpegExePath();

		if (ffmpegPath == null)
			ffmpegPath = FFmpegUtils.getDefaultFFmpegExe();

		if (ffmpegPath != null && isFFmpegValidExecutable(ffmpegPath)) {

			String ffmpegversion = FFmpegParametersChecker.getVersionFFmpeg();
			ffmpegInfoContainer = RencoderFFmpegInfoContainer.getSavedFFmpegInfo();

			if (ffmpegInfoContainer == null
					|| !ffmpegversion.equals(ffmpegInfoContainer.getFFmpegVersion())) {
				checkNewFFmpegVersion(ffmpegversion);
				RencoderFFmpegInfoContainer.writeRencoderFFmpegInfo(ffmpegInfoContainer);
			}

			return true;
		}

		return false;
	}

	private void checkNewFFmpegVersion(String ffmpegversion)
			throws InterruptedException, IOException {

		ffmpegInfoContainer = new RencoderFFmpegInfoContainer();

		ffmpegInfoContainer
		.setFFmpegVideoEncoders(FFmpegParametersChecker.getFFmpegEncoders(EncoderType.VIDEO));
		ffmpegInfoContainer
		.setFFmpegAudioEncoders(FFmpegParametersChecker.getFFmpegEncoders(EncoderType.AUDIO));

		checkSupport();

		ffmpegInfoContainer.setFFmpegVersion(ffmpegversion);

	}


	
	
	private void checkSupport() throws InterruptedException, IOException {
	  
	  FFmpegCodecInfoAnalyser analyser= new FFmpegCodecInfoAnalyser(ffmpegInfoContainer);
	  if(currentlistener!=null)
	    analyser.addPropertyChangeListener(currentlistener);
	  
	  Thread t = new Thread(analyser);
      t.run();
	  
	}

	


	public VideoCodecs[] getAllowedVideoCodecs() {
		return ffmpegInfoContainer.getRencoderSupportedVideoCodecs();
	}


	public boolean isCodecSupported(String codecname, String ffmpegid, boolean needscheck) {

		// Logger.debug("checking if " + codec + " is supported");
		if (needscheck) {
			if (ffmpegInfoContainer.getHwaccelSupportedVideoCodecs().contains(ffmpegid)) {
				Logger.debug(codecname + " is supported: true");
				return true;
			} else {
				Logger.debug(codecname + " is supported: false");
				return false;
			}
		} else {
			if (ffmpegInfoContainer.getFFmpegVideoEncoders().containsKey(ffmpegid)) {
				Logger.debug(codecname + " is supported: true");
				return true;
			} else if (ffmpegInfoContainer.getFFmpegAudioEncoders().containsKey(ffmpegid)) {
				Logger.debug(codecname + " is supported: true");
				return true;
			} else {
				Logger.debug(codecname + " is supported: false");
				return false;
			}
		}
	}


	public ArrayList<String> findSystemFFmpegVersions() {
		ArrayList<String> found = getSystemInstalledFFmpeg();
		appffmpegversions = FFmpegUtils.getFFmpegVersionsInternal();
		if (found == null)
			found = new ArrayList<>();
		found.addAll(appffmpegversions);
		return filterByOS(found);
	}

	private ArrayList<String> filterByOS(ArrayList<String> input) {
		ArrayList<String> filtered = new ArrayList<>();
		for (String path : input) {

			String ext = FilenameUtils.getExtension(path);

			if (OSystem.isWindows() && ext.toLowerCase().equals("exe"))
				filtered.add(path);
			else if (OSystem.isLinux() && ext.isEmpty())
				filtered.add(path);
		}
		return filtered;
	}

	public ArrayList<String> getSystemInstalledFFmpeg() {
		ArrayList<String> res = null;
		if (OSystem.isLinux() || OSystem.isMacOS()) {
			res = OSystem.executeSystemCommand("whereis ffmpeg");
			res = filterLinuxPaths(res);
		} else if (OSystem.isWindows()) {
			res = OSystem.executeSystemCommand("where ffmpeg");

		}

		return res;
	}

	private ArrayList<String> filterLinuxPaths(ArrayList<String> pathslist) {
		if (pathslist != null && pathslist.size() > 0) {
			ArrayList<String> res = new ArrayList<>();
			for (int i = 0; i < pathslist.size(); i++) {
				String[] paths = pathslist.get(i).split("\\s+");
				for (int j = 0; j < paths.length; j++) {
					String path = paths[j];
					if (!path.equals("ffmpeg:") && !path.endsWith(".gz") && !path.contains("share")
							&& !path.contains("X11"))
						res.add(path);
				}
			}
			return res;
		}
		return null;
	}

	public AudioCodecs[] getSupportAudioCodec(VideoContainers videocontainer) {

		ArrayList<AudioCodecs> filteredcodecs = new ArrayList<AudioCodecs>();

		ArrayList<AudioCodecs> supportedbycontainer = videocontainer.getAudioFormatsSupported();

		for (AudioCodecs audioCodec : supportedbycontainer) {
			if (audioCodec.isCodecSupported())
				filteredcodecs.add(audioCodec);
		}
		filteredcodecs.add(AudioCodecs.COPY);
		return filteredcodecs.toArray(new AudioCodecs[filteredcodecs.size()]);
	}


	public String getDefaultSoftSubtitleLanguage() {
		String sublang = PropertiesWorker.getStringProperty(StaticGlobalFields.RENCODERCONFIGFILE,
				StaticGlobalFields.DEFAULTSOFTSUBLANG);

		if (sublang == null)
			return "eng";
		else
			return sublang;
	}

	public void setDefaultSoftSubtitleLanguage(String langcode) {
		PropertiesWorker.ChangePropertiesParam(StaticGlobalFields.RENCODERCONFIGFILE,
				StaticGlobalFields.DEFAULTSOFTSUBLANG, langcode);
	}

	public static boolean isFFmpegValidExecutable(String ffmpegpath) {
		File ffmpeg = new File(ffmpegpath);
		if (ffmpeg.exists()) {
			ffmpeg.setExecutable(true);
			ffmpeg.setWritable(true);
			ffmpeg.setReadable(true);

			String ffmpegexepath = ffmpeg.getAbsolutePath();
			ProcessBuilder pb = new ProcessBuilder(ffmpegexepath, "-i");
			Process p = null;
			try {
				p = pb.start();
			} catch (IOException e) {
				Logger.error(e);
				return false;
			}
			return true;
		} else
			return false;

	}

}
