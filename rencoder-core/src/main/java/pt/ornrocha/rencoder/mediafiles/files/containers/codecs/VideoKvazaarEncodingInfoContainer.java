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
package pt.ornrocha.rencoder.mediafiles.files.containers.codecs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import org.apache.commons.configuration.PropertiesConfiguration;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoContainers;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoLevel;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.X26xPresets;
import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.helpers.props.fields.StaticFFmpegFields;
import pt.ornrocha.rencoder.helpers.props.fields.StaticVideoEncoderFields;
import pt.ornrocha.rencoder.helpers.props.managers.auxiliar.EncodingPropsAuxiliar;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.DefaultEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;

// TODO: Auto-generated Javadoc
/**
 * The Class VideoH265EncodingInfoContainer.
 */
public class VideoKvazaarEncodingInfoContainer extends DefaultEncodingInfoContainer {

	private X26xPresets preset = X26xPresets.none;
	private VideoLevel level = VideoLevel.NONE;
	private int period = 64;
	private int owf = 0;
	private int tiles = 0;
	private boolean wpp = true;
	private int ref = 4;
	private int subme = 4;
	private String othercmds = null;

	/**
	 * Instantiates a new video h265 encoding info container.
	 */
	public VideoKvazaarEncodingInfoContainer() {
		super();
		setDefaultInfo();
	}

	@Override
	public void setDefaultInfo() {
		this.videocodec = VideoCodecs.KVAZAAR;
		this.videoContainer = VideoContainers.MKV;
		this.videoVBRQuality = new IndexedHashMap<String, Integer>() {
			{
				put(VideoCodecs.KVAZAAR.toString(), 28);
			}
		};
		this.audiocodec = AudioCodecs.LCAAC;
		this.useAudioCBR = true;
		this.audioConstantBitrateValue = "128k";
	}

	public X26xPresets getPreset() {
		return preset;
	}

	public void setPreset(X26xPresets preset) {
		this.preset = preset;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public int getOwf() {
		return owf;
	}

	public void setOwf(int owf) {
		this.owf = owf;
	}

	public VideoLevel getLevel() {
		return level;
	}

	public void setLevel(VideoLevel level) {
		this.level = level;
	}

	public int getTiles() {
		return tiles;
	}

	public void setTiles(int tiles) {
		this.tiles = tiles;
	}

	public boolean isWpp() {
		return wpp;
	}

	public void setWpp(boolean wpp) {
		this.wpp = wpp;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getSubme() {
		return subme;
	}

	public void setSubme(int subme) {
		this.subme = subme;
	}

	public String getOthercmds() {
		return othercmds;
	}

	public void setOthercmds(String othercmds) {
		this.othercmds = othercmds;
	}

	@Override
	public IGeneralVideoEncInfoContainer copySpecificInfocontainer(IGeneralVideoEncInfoContainer cont) {
		((VideoKvazaarEncodingInfoContainer) cont).setPreset(this.preset);

		((VideoKvazaarEncodingInfoContainer) cont).setLevel(this.level);
		((VideoKvazaarEncodingInfoContainer) cont).setOwf(this.owf);
		((VideoKvazaarEncodingInfoContainer) cont).setPeriod(this.period);
		((VideoKvazaarEncodingInfoContainer) cont).setTiles(this.tiles);
		((VideoKvazaarEncodingInfoContainer) cont).setWpp(this.wpp);
		((VideoKvazaarEncodingInfoContainer) cont).setRef(this.ref);
		((VideoKvazaarEncodingInfoContainer) cont).setSubme(this.subme);
		((VideoKvazaarEncodingInfoContainer) cont).setOthercmds(this.othercmds);
		return cont;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see filetreatment.files.containers.MainVideoEncodingInfoContainer#clone()
	 */
	@Override
	public VideoKvazaarEncodingInfoContainer clone() {
		VideoKvazaarEncodingInfoContainer clone = new VideoKvazaarEncodingInfoContainer();
		clone = (VideoKvazaarEncodingInfoContainer) this.copyCommoninfoofcontainer(clone);
		clone = (VideoKvazaarEncodingInfoContainer) this.copySpecificInfocontainer(clone);

		return clone;
	}

	@Override
	protected ArrayList<VideoContainers> getSupportedFormats() {
		return Arrays.stream(VideoCodecs.KVAZAAR.supportsOutputFormats())
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	protected VideoContainers getpreferredFormat() {
		return VideoContainers.MKV;
	}

	@Override
	public void setConfigurations(PropertiesConfiguration props) {

		setVideoConfigurations(props);

		setPreset(X26xPresets
				.getX26xPresetsFromString(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOPRESET)));

		setLevel(VideoLevel.getVideoLevelFromString(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.VIDEOLEVEL)));
		setOwf(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.KVAZAAROWF, 0, 200, 0));
		setPeriod(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.KVAZAARPERIOD, 0, 500, 64));
		setTiles(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.KVAZAARTILES, 0, 50, 0));
		setRef(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.KVAZAARREF, 0, 15, 4));
		setSubme(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.KVAZAARSUBME, 0, 4, 4));
		setWpp(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.KVAZAARWPP, true));
		String cmds = PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.KVAZAARCMDS);
		if (cmds != null) {
			setOthercmds(validateOtherCmds(cmds));
		}

		setAudioConfigurations(props);
		setSubtitleConfigurations(props);
	}

	@Override
	protected void saveSpecificCodecProperties(PropertiesConfiguration prop) {
		prop.setProperty(StaticVideoEncoderFields.VIDEOPRESET, getPreset().toString());

		prop.setProperty(StaticVideoEncoderFields.VIDEOLEVEL, getLevel().toString());
		if (period != 64)
			prop.setProperty(StaticVideoEncoderFields.KVAZAARPERIOD, String.valueOf(period));
		if (owf != 0)
			prop.setProperty(StaticVideoEncoderFields.KVAZAAROWF, String.valueOf(owf));
		if (tiles != 0)
			prop.setProperty(StaticVideoEncoderFields.KVAZAARTILES, String.valueOf(tiles));
		if (ref != 4)
			prop.setProperty(StaticVideoEncoderFields.KVAZAARREF, String.valueOf(ref));
		if (subme != 4)
			prop.setProperty(StaticVideoEncoderFields.KVAZAARSUBME, String.valueOf(subme));
		if (!wpp)
			prop.setProperty(StaticVideoEncoderFields.KVAZAARWPP, String.valueOf(wpp));
		if (othercmds != null)
			prop.setProperty(StaticVideoEncoderFields.KVAZAARCMDS, othercmds);

	}

	public String getKvazaarParameters() {
		StringBuilder str = new StringBuilder();
		ArrayList<String> usedcms = new ArrayList<>();
		if (isUseVideoEncodingVBR()) {
			str.append("qp=" + String.valueOf(getVideoVBRQualityValue()));
			usedcms.add("qp");
		}
		if (owf != 0) {
			if (str.length() > 0)
				str.append(",");
			str.append("owf=" + String.valueOf(owf));
			usedcms.add("owf");
		}
		if (period != 64) {
			if (str.length() > 0)
				str.append(",");
			str.append("period=" + String.valueOf(period));
			usedcms.add("period");
		}
		if (!preset.equals(X26xPresets.none)) {
			if (str.length() > 0)
				str.append(",");
			str.append("preset=" + preset.toString());
			usedcms.add("preset");
		}
		if (!level.equals(VideoLevel.NONE)) {
			if (str.length() > 0)
				str.append(",");
			str.append("level=" + level.toString());
			usedcms.add("level");
		}
		if (tiles != 0) {
			if (str.length() > 0)
				str.append(",");
			str.append("tiles=" + String.valueOf(tiles) + "x" + String.valueOf(tiles));
			usedcms.add("tiles");
		}
		if (!wpp) {
			if (str.length() > 0)
				str.append(",");
			str.append("wpp=&");
			usedcms.add("wpp");
		}
		if (ref != 4) {
			if (str.length() > 0)
				str.append(",");
			str.append("ref=" + String.valueOf(ref));
			usedcms.add("ref");
		}
		if (subme != 4) {
			if (str.length() > 0)
				str.append(",");
			str.append("subme=" + String.valueOf(subme));
			usedcms.add("subme");
		}
		if (othercmds != null) {
			String cmds = validateKvazaarCmds(othercmds, usedcms);
			if (cmds != null) {
				if (str.length() > 0)
					str.append(",");
				str.append(cmds);
			}
		}
		return str.length() > 0 ? str.toString() : null;
	}

	private String validateKvazaarCmds(String cmds, ArrayList<String> usedcms) {

		if (usedcms.size() == 0)
			return cmds;
		else {
			LinkedHashMap<String, String> mapcmds = new LinkedHashMap<>();
			String[] listcmds = cmds.split(",");
			for (String str : listcmds) {
				String[] cmd = str.split("=");

				if (!usedcms.contains(cmd[0].trim()))
					mapcmds.put(cmd[0], cmd[1]);
			}

			StringBuilder str = new StringBuilder();
			int n = 0;
			for (String key : mapcmds.keySet()) {
				str.append(key + "=" + mapcmds.get(key).trim());
				if (n < mapcmds.size() - 1)
					str.append(",");
				n++;
			}

			if (str.length() > 0)
				return str.toString();
			else
				return null;
		}
	}

	public static String validateOtherCmds(String cmds) {

		if (cmds.startsWith("[") && cmds.endsWith("]")) {
			cmds = cmds.substring(1, cmds.length() - 1);
			String[] listcmds = cmds.split(",");
			String newcmd = listcmds[0];

			for (int i = 1; i < listcmds.length; i++) {
				newcmd += "," + listcmds[i].trim();
			}

			return newcmd;
		}

		return cmds;
	}

	@Override
	public ArrayList<String> getSpecificVideoEncoderParameters(ArrayList<String> appendcmds) {

		String param = getKvazaarParameters();
		ArrayList<String> params = new ArrayList<>();
		if (param != null) {
			params.add(StaticFFmpegFields.KVAZAARPARAMETERS);
			params.add(param);
		}
		return params;
	}

	@Override
	public boolean useSpecificVideoEncodingParameters() {
		return true;
	}

}
