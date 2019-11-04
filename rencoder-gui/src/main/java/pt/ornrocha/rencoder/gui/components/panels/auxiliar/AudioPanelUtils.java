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
package pt.ornrocha.rencoder.gui.components.panels.auxiliar;

import java.util.ArrayList;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioChannels;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioSampleRates;

// TODO: Auto-generated Javadoc
/**
 * The Class AudioPanelUtils.
 */
public class AudioPanelUtils {

	// https://trac.ffmpeg.org/wiki/GuidelinesHighQualityAudio

	// http://en.wikipedia.org/wiki/Comparison_of_container_formats#Audio_formats_supported

	/**
	 * Allowed audio sample rates.
	 *
	 * @param audiofrequency the audiofrequency
	 * @return the audio sample rates[]
	 */
	public static AudioSampleRates[] allowedAudioSampleRates(String audiofrequency) {

		ArrayList<AudioSampleRates> temp = new ArrayList<>();

		int audiofreq = Integer.parseInt(audiofrequency);

		for (AudioSampleRates sprate : AudioSampleRates.values()) {
			if (sprate.getaudiosamplerate() <= audiofreq)
				temp.add(sprate);
		}

		AudioSampleRates[] res = new AudioSampleRates[temp.size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = temp.get(i);
		}

		return res;

	}

	/**
	 * Allowed audio channels.
	 *
	 * @param audiochannels the audiochannels
	 * @param usingcodec    the usingcodec
	 * @return the audio channels[]
	 */
	public static AudioChannels[] allowedAudioChannels(String audiochannels, AudioCodecs usingcodec) {

		AudioChannels[] res = null;
		if (!codecsupportmultichannel(usingcodec)) {
			if (audiochannels.equals(AudioChannels.SURROUND.toString()))
				audiochannels = AudioChannels.STEREO.toString();
		}

		if (audiochannels.equals(AudioChannels.SURROUND.toString())) {
			res = new AudioChannels[3];
			res[0] = AudioChannels.SURROUND;
			res[1] = AudioChannels.STEREO;
			res[2] = AudioChannels.MONO;
		} else if (audiochannels.equals(AudioChannels.STEREO.toString())) {
			res = new AudioChannels[2];
			res[0] = AudioChannels.STEREO;
			res[1] = AudioChannels.MONO;
		}

		else if (audiochannels.equals(AudioChannels.MONO.toString())) {
			res = new AudioChannels[1];
			res[0] = AudioChannels.MONO;
		}

		return res;

	}

	/**
	 * Codec support multichannel.
	 *
	 * @param codec the codec
	 * @return true, if successful
	 */
	public static boolean codecsupportmultichannel(AudioCodecs codec) {

		boolean support = true;

		if (codec.equals(AudioCodecs.MP3) || codec.equals(AudioCodecs.MP2))
			support = false;

		return support;

	}

}
