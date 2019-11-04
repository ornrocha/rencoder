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
package pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioCodecs;

// TODO: Auto-generated Javadoc
/**
 * The Enum VideoContainers.
 */
public enum VideoContainers {

	/** The avi. */
	AVI {
		@Override
		public String toString() {
			return "avi";
		}

		@Override
		public ArrayList<AudioCodecs> getAudioFormatsSupported() {
			return (ArrayList<AudioCodecs>) Stream
					.of(AudioCodecs.MP3, AudioCodecs.LCAAC, AudioCodecs.MP2, AudioCodecs.AC3)
					.collect(Collectors.toList());
		}

		@Override
		public AudioCodecs getDefaultAudioCodec() {
			return AudioCodecs.MP3;
		}
	},

	/** The mkv. */
	MKV {
		@Override
		public String toString() {
			return "mkv";
		}

		@Override
		public ArrayList<AudioCodecs> getAudioFormatsSupported() {
			return (ArrayList<AudioCodecs>) Stream.of(AudioCodecs.LCAAC, AudioCodecs.HEAAC, AudioCodecs.MP3,
					AudioCodecs.VORBIS, AudioCodecs.OPUS, AudioCodecs.MP2, AudioCodecs.AC3, AudioCodecs.EAC3)
					.collect(Collectors.toList());
		}

		@Override
		public AudioCodecs getDefaultAudioCodec() {
			return AudioCodecs.LCAAC;
		}
	},

	/** The MP4. */
	MP4 {

		@Override
		public String toString() {
			return "mp4";
		}

		@Override
		public ArrayList<AudioCodecs> getAudioFormatsSupported() {
			return (ArrayList<AudioCodecs>) Stream
					.of(AudioCodecs.LCAAC, AudioCodecs.HEAAC, AudioCodecs.MP3, AudioCodecs.MP2, AudioCodecs.AC3)
					.collect(Collectors.toList());
		}

		@Override
		public AudioCodecs getDefaultAudioCodec() {
			return AudioCodecs.LCAAC;
		}
	},

	/** The flv. */
	FLV {

		@Override
		public String toString() {
			return "flv";
		}

		@Override
		public ArrayList<AudioCodecs> getAudioFormatsSupported() {
			return (ArrayList<AudioCodecs>) Stream.of(AudioCodecs.LCAAC, AudioCodecs.HEAAC, AudioCodecs.MP3)
					.collect(Collectors.toList());
		}

		@Override
		public AudioCodecs getDefaultAudioCodec() {
			return AudioCodecs.LCAAC;
		}
	},

	/** The 3gp. */
	E3GP {

		@Override
		public String toString() {
			return "3gp";
		}

		@Override
		public ArrayList<AudioCodecs> getAudioFormatsSupported() {
			return (ArrayList<AudioCodecs>) Stream.of(AudioCodecs.LCAAC, AudioCodecs.HEAAC)
					.collect(Collectors.toList());
		}

		@Override
		public AudioCodecs getDefaultAudioCodec() {
			return AudioCodecs.LCAAC;
		}
	},

	/** The mpeg. */
	MPEG {
		@Override
		public String toString() {
			return "mpeg";
		}

		@Override
		public ArrayList<AudioCodecs> getAudioFormatsSupported() {
			return (ArrayList<AudioCodecs>) Stream.of(AudioCodecs.MP3, AudioCodecs.MP2).collect(Collectors.toList());
		}

		@Override
		public AudioCodecs getDefaultAudioCodec() {
			return AudioCodecs.MP3;
		}

	},

	/** The vob. */
	VOB {

		@Override
		public String toString() {
			return "vob";
		}

		@Override
		public ArrayList<AudioCodecs> getAudioFormatsSupported() {
			return (ArrayList<AudioCodecs>) Stream.of(AudioCodecs.MP2, AudioCodecs.AC3).collect(Collectors.toList());
		}

		@Override
		public AudioCodecs getDefaultAudioCodec() {
			return AudioCodecs.MP2;
		}
	},

	TS {

		@Override
		public String toString() {
			return "ts";
		}

		@Override
		public ArrayList<AudioCodecs> getAudioFormatsSupported() {
			return (ArrayList<AudioCodecs>) Stream
					.of(AudioCodecs.LCAAC, AudioCodecs.HEAAC, AudioCodecs.MP3, AudioCodecs.MP2, AudioCodecs.AC3)
					.collect(Collectors.toList());
		}

		@Override
		public AudioCodecs getDefaultAudioCodec() {
			return AudioCodecs.LCAAC;
		}
	},

	/** The webm. */
	WEBM {

		@Override
		public String toString() {
			return "webm";
		}

		@Override
		public ArrayList<AudioCodecs> getAudioFormatsSupported() {
			return (ArrayList<AudioCodecs>) Stream.of(AudioCodecs.VORBIS, AudioCodecs.OPUS)
					.collect(Collectors.toList());
		}

		@Override
		public AudioCodecs getDefaultAudioCodec() {
			return AudioCodecs.VORBIS;
		}
	},

	OGG {

		@Override
		public String toString() {
			return "ogg";
		}

		@Override
		public ArrayList<AudioCodecs> getAudioFormatsSupported() {
			return (ArrayList<AudioCodecs>) Stream.of(AudioCodecs.VORBIS, AudioCodecs.OPUS)
					.collect(Collectors.toList());
		}

		@Override
		public AudioCodecs getDefaultAudioCodec() {
			return AudioCodecs.VORBIS;
		}

	},

	/** The sameassource. */
	SAMEASSOURCE {

		@Override
		public String toString() {
			return "Same as source";
		}

		@Override
		public ArrayList<AudioCodecs> getAudioFormatsSupported() {
			return (ArrayList<AudioCodecs>) Stream.of(AudioCodecs.COPY).collect(Collectors.toList());
		}

		@Override
		public AudioCodecs getDefaultAudioCodec() {
			return AudioCodecs.COPY;
		}
	};

	public ArrayList<AudioCodecs> getAudioFormatsSupported() {
		return getAudioFormatsSupported();
	}

	public AudioCodecs getDefaultAudioCodec() {
		return getDefaultAudioCodec();
	}

	public static AudioCodecs ValidateandGetAudioCodec(String audiocodecname, VideoContainers videocontainer) {

		AudioCodecs codec = AudioCodecs.getAudioCodecFromString(audiocodecname);
		if (codec != null) {

			ArrayList<AudioCodecs> supportedcodecs = videocontainer.getAudioFormatsSupported();
			if (supportedcodecs.contains(codec) && codec.isCodecSupported())
				return codec;
			else
				return AudioCodecs.COPY;

		} else
			return AudioCodecs.COPY;

	}

}
