package pt.ornrocha.rencoder.ffmpegWrapper.configurations;

import java.util.ArrayList;

import org.apache.commons.io.FilenameUtils;
import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.commands.FileInformationChecker;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.general.EncoderType;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoContainers;
import pt.ornrocha.rencoder.ffmpegWrapper.utilities.FFmpegUtils;
import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.helpers.osystem.OSystem;
import pt.ornrocha.rencoder.helpers.props.fields.StaticFFmpegFields;
import pt.ornrocha.rencoder.helpers.props.fields.StaticGlobalFields;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.MediaInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.streams.AudioStreamInfo;

public class FFmpegManager {

    static FFmpegManager instance;
    private String ffmpegPath = null;
    private IndexedHashMap<String, String> videoencoders;
    private IndexedHashMap<String, String> audioencoders;
    private IndexedHashMap<String, String> codecsmap;
    private ArrayList<String> allowedcodecs;
    private ArrayList<String> decodershwaccel;
    private ArrayList<String> appffmpegversions;
    private VideoCodecs[] allowedvideocodecs = null;
    private AudioCodecs[] allowedaudiocodecs = null;

    public static FFmpegManager getInstance() {
	if (instance == null)
	    instance = new FFmpegManager();
	return instance;
    }

    private FFmpegManager() {
    }

    public FFmpegManager setFFmpegPath(String ffmpegpath) {
	this.ffmpegPath = ffmpegpath;
	PropertiesWorker.ChangePropertiesParam(StaticGlobalFields.RENCODERCONFIGFILE, StaticGlobalFields.ENCODERPATH,
		ffmpegpath);
	return this;
    }

    public String getFFmpegPath() {
	return this.ffmpegPath;
    }

    public boolean isCuvidSupported() {
	return decodershwaccel.contains(StaticFFmpegFields.CUVID);
    }

    public boolean isVaapiSupported() {
	return decodershwaccel.contains(StaticFFmpegFields.VAAPI);
    }

    public boolean isQsvSupported() {
	return decodershwaccel.contains(StaticFFmpegFields.QSV);
    }

    public boolean isCustomFFmpeg(String path) {
	if (appffmpegversions.contains(path))
	    return true;
	return false;
    }

    public boolean LoadFFmpeg() {

	if (ffmpegPath == null)
	    ffmpegPath = FFmpegUtils.getFFmpegExePath();

	if (ffmpegPath == null)
	    ffmpegPath = FFmpegUtils.getDefaultFFmpegExe();

	if (ffmpegPath != null && FileInformationChecker.validFFmpegExec(ffmpegPath)) {
	    reset();
	    FFmpegParametersChecker.setFFmpegExecutable(ffmpegPath);
	    Logger.info("FFmpeg Path: " + ffmpegPath);
	    videoencoders = FFmpegParametersChecker.getFFmpegEncoders(EncoderType.VIDEO);
	    audioencoders = FFmpegParametersChecker.getFFmpegEncoders(EncoderType.AUDIO);

	    checkSupport();
	    return true;
	}

	return false;
    }

    private void reset() {
	codecsmap = new IndexedHashMap<>();
	allowedcodecs = new ArrayList<>();
	decodershwaccel = new ArrayList<>();
	allowedvideocodecs = null;
	allowedaudiocodecs = null;
	//loadRegister();
    }

    private void addEncoderVideoCodec(VideoCodecs codec) {
	if (!allowedcodecs.contains(codec.getFFmpegID()))
	    allowedcodecs.add(codec.getFFmpegID());
    }

    private void addDecoderHWAccel(VideoCodecs codec) {
	if (!decodershwaccel.contains(codec.getDecodingHWACCType()))
	    decodershwaccel.add(codec.getDecodingHWACCType());
    }

    private void loadRegister() {
	String xvid = PropertiesWorker.getStringProperty(StaticGlobalFields.RENCODERCONFIGFILE,
		StaticFFmpegFields.xvidencoder);
	if (xvid != null)
	    codecsmap.put(StaticFFmpegFields.xvidencoder, xvid);

	String h264 = PropertiesWorker.getStringProperty(StaticGlobalFields.RENCODERCONFIGFILE,
		StaticFFmpegFields.H264encoder);
	if (h264 != null)
	    codecsmap.put(StaticFFmpegFields.H264encoder, h264);

    }

    private void checkSupport() {

	Logger.debug("checking codec support");
	ArrayList<String> failed = new ArrayList<String>();

	for (VideoCodecs vcodec : VideoCodecs.values()) {

	    if (vcodec.needsSupportVerification()) {

		if (FFmpegParametersChecker.isSupportedCodec(vcodec, false))
		    allowedcodecs.add(vcodec.getFFmpegID());
		else
		    failed.add(vcodec.getFFmpegID());
		if ((!failed.contains(vcodec.getFFmpegID()) || vcodec.needsDecodingFilter())
			&& vcodec.supportsDecodingHardwareAccel())
		    if (FFmpegParametersChecker.isSupportedCodec(vcodec, true)) {
			addEncoderVideoCodec(vcodec);
			addDecoderHWAccel(vcodec);
		    }

	    }
	}
    }

    public VideoCodecs[] getAllowedVideoCodecs() {

	Logger.debug("checking allowed codecs.");

	if (allowedvideocodecs == null) {

	    ArrayList<VideoCodecs> allowed = new ArrayList<>();

	    for (VideoCodecs codec : VideoCodecs.values()) {

		if (isCodecSupported(codec.getFFmpegID(), codec.needsSupportVerification()))
		    allowed.add(codec);
	    }

	    allowed.add(VideoCodecs.COPY);
	    allowedvideocodecs = allowed.toArray(new VideoCodecs[allowed.size()]);
	}

	return allowedvideocodecs;

    }

    public boolean isCodecSupported(String codec, boolean needscheck) {

	Logger.debug("checking if " + codec + " is supported");
	if (needscheck) {
	    if (allowedcodecs.contains(codec))
		return true;
	    else
		return false;
	} else {
	    if (videoencoders.containsKey(codec))
		return true;
	    else if (audioencoders.containsKey(codec))
		return true;
	    else
		return false;
	}
    }

    public String getRegisteredCodec(String keymap) {
	return codecsmap.get(keymap);
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

    public AudioCodecs[] getSupportAudioCodec(VideoContainers videocontainer, MediaInfoContainer audioInfoContainer) {

	AudioStreamInfo sourceaudio = null;

	if (allowedaudiocodecs == null) {

	    if (audioInfoContainer != null)
		sourceaudio = audioInfoContainer.getfirstAudioStream();

	    ArrayList<AudioCodecs> filteredcodecs = new ArrayList<AudioCodecs>();

	    ArrayList<AudioCodecs> supportedbycontainer = videocontainer.getAudioFormatsSupported();

	    for (AudioCodecs audioCodec : supportedbycontainer) {
		if (audioCodec.isCodecSupported())
		    filteredcodecs.add(audioCodec);
	    }
	    filteredcodecs.add(AudioCodecs.COPY);
	    allowedaudiocodecs = filteredcodecs.toArray(new AudioCodecs[filteredcodecs.size()]);
	}

	return allowedaudiocodecs;
    }

}