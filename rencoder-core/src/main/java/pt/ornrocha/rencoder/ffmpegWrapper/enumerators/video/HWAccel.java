package pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import pt.ornrocha.rencoder.helpers.props.fields.StaticFFmpegFields;

public enum HWAccel {




	NVDEC {
		@Override
		public String toString() {
			return "Nvdec";
		}

		@Override
		public String getFFmpegID() {
			return StaticFFmpegFields.NVDEC;
		}

		@Override
		public ArrayList<String> getFFmpegCMD() {
			return (ArrayList<String>) Stream
					.of(StaticFFmpegFields.DECODERHWACCEL, StaticFFmpegFields.NVDEC)
					.collect(Collectors.toList());
		}

		@Override
		public String getHardSubsFilter(String asscmd) {
			return asscmd.replace("'", "");
		}

		@Override
		public boolean joinDecodingAndSubtitleFilters() {
			return true;
		}
	},	
	VSYNC {
		@Override
		public String toString() {
			return "Vsync";
		}

		@Override
		public String getFFmpegID() {
			return StaticFFmpegFields.VSYNC;
		}

		@Override
		public ArrayList<String> getFFmpegCMD() {
			return (ArrayList<String>) Stream
					.of("-"+StaticFFmpegFields.VSYNC, "0")
					.collect(Collectors.toList());
		}

		@Override
		public String getHardSubsFilter(String asscmd) {
			return asscmd.replace("'", "");
		}

		@Override
		public boolean joinDecodingAndSubtitleFilters() {
			return true;
		}
	},	
	
	CUDA {
		@Override
		public String toString() {
			return "CUDA";
		}

		@Override
		public String getFFmpegID() {
			return StaticFFmpegFields.CUDA;
		}

		@Override
		public ArrayList<String> getFFmpegCMD() {
			return (ArrayList<String>) Stream
					.of(    "-"+StaticFFmpegFields.VSYNC, "0",
							StaticFFmpegFields.DECODERHWACCEL, StaticFFmpegFields.CUDA,
							StaticFFmpegFields.DECODERHWACCELOUTFORMAT, StaticFFmpegFields.CUDA)
					.collect(Collectors.toList());
		}

		@Override
		public String getHardSubsFilter(String asscmd) {
			return "hwdownload,format=nv12" + "," + asscmd.replace("'", "");
		}

		@Override
		public boolean joinDecodingAndSubtitleFilters() {
			return true;
		}
	},
	MPEG4CUVID {
		@Override
		public String toString() {
			return "MPEG-4 Cuvid";
		}

		@Override
		public String getFFmpegID() {
			return StaticFFmpegFields.MPEG4CUVID;
		}

		@Override
		public ArrayList<String> getFFmpegCMD() {
			return (ArrayList<String>) Stream
					.of(StaticFFmpegFields.DECODERHWACCEL, StaticFFmpegFields.CUVID,
							StaticFFmpegFields.encodevideocodec, StaticFFmpegFields.MPEG4CUVID)
					.collect(Collectors.toList());
		}

		@Override
		public String getHardSubsFilter(String asscmd) {
			return "hwdownload,format=nv12" + "," + asscmd.replace("'", "");
		}

		@Override
		public boolean joinDecodingAndSubtitleFilters() {
			return true;
		}
	},
//	HEVCCUVID {
//		@Override
//		public String toString() {
//			return "HEVC Cuvid";
//		}
//
//		@Override
//		public String getFFmpegID() {
//			return StaticFFmpegFields.HEVCCUVID;
//		}
//
//		@Override
//		public ArrayList<String> getFFmpegCMD() {
//			return (ArrayList<String>) Stream
//					.of(StaticFFmpegFields.DECODERHWACCEL, StaticFFmpegFields.CUVID,
//							StaticFFmpegFields.encodevideocodec, StaticFFmpegFields.HEVCCUVID)
//					.collect(Collectors.toList());
//		}
//
//		@Override
//		public String getHardSubsFilter(String asscmd) {
//			return "hwdownload,format=nv12" + "," + asscmd.replace("'", "");
//		}
//
//		@Override
//		public boolean joinDecodingAndSubtitleFilters() {
//			return true;
//		}
//	},
	MPEG2CUVID {
		@Override
		public String toString() {
			return "MPEG-2 Cuvid";
		}

		@Override
		public String getFFmpegID() {
			return StaticFFmpegFields.MPEG2CUVID;
		}

		@Override
		public ArrayList<String> getFFmpegCMD() {
			return (ArrayList<String>) Stream
					.of(StaticFFmpegFields.DECODERHWACCEL, StaticFFmpegFields.CUVID,
							StaticFFmpegFields.encodevideocodec, StaticFFmpegFields.MPEG2CUVID)
					.collect(Collectors.toList());
		}

		@Override
		public String getHardSubsFilter(String asscmd) {
			return "hwdownload,format=nv12" + "," + asscmd.replace("'", "");
		}

		@Override
		public boolean joinDecodingAndSubtitleFilters() {
			return true;
		}
	},


	VP8CUVID {
		@Override
		public String toString() {
			return "VP8 Cuvid";
		}

		@Override
		public String getFFmpegID() {
			return StaticFFmpegFields.VP8CUVID;
		}

		@Override
		public ArrayList<String> getFFmpegCMD() {
			return (ArrayList<String>) Stream
					.of(StaticFFmpegFields.DECODERHWACCEL, StaticFFmpegFields.CUVID,
							StaticFFmpegFields.encodevideocodec, StaticFFmpegFields.VP8CUVID)
					.collect(Collectors.toList());
		}

		@Override
		public String getHardSubsFilter(String asscmd) {
			return "hwdownload,format=nv12" + "," + asscmd.replace("'", "");
		}

		@Override
		public boolean joinDecodingAndSubtitleFilters() {
			return true;
		}
	},

	VP9CUVID {
		@Override
		public String toString() {
			return "VP9 Cuvid";
		}

		@Override
		public String getFFmpegID() {
			return StaticFFmpegFields.VP9CUVID;
		}

		@Override
		public ArrayList<String> getFFmpegCMD() {
			return (ArrayList<String>) Stream
					.of(StaticFFmpegFields.DECODERHWACCEL, StaticFFmpegFields.CUVID,
							StaticFFmpegFields.encodevideocodec, StaticFFmpegFields.VP9CUVID)
					.collect(Collectors.toList());
		}

		@Override
		public String getHardSubsFilter(String asscmd) {
			return "hwdownload,format=nv12" + "," + asscmd.replace("'", "");
		}

		@Override
		public boolean joinDecodingAndSubtitleFilters() {
			return true;
		}
	},

	VAAPI {
		@Override
		public String toString() {
			return "Vaapi";
		}

		@Override
		public String getFFmpegID() {
			return StaticFFmpegFields.VAAPI;
		}

		@Override
		public ArrayList<String> getFFmpegCMD() {
			return VideoCodecs.H264VAAPI.getDecodingHWAcceleration();
		}

		@Override
		public String getHardSubsFilter(String asscmd) {
			return "scale_vaapi,hwmap=mode=read+write+direct,format=nv12" + "," + asscmd.replace("'", "")
			+ ",hwmap";
		}

		@Override
		public boolean joinDecodingAndSubtitleFilters() {
			return true;
		}

	},
	QSV {
		@Override
		public String toString() {
			return "Qsv";
		}

		@Override
		public String getFFmpegID() {
			return StaticFFmpegFields.QSV;
		}

		@Override
		public ArrayList<String> getFFmpegCMD() {
			return VideoCodecs.H264QSV.getDecodingHWAcceleration();
		}

		@Override
		public String getHardSubsFilter(String asscmd) {
			// ArrayList<String> cmds=VideoCodecs.HEVCQSV.getCmdDecodingFilter();
			return "";
		}

		@Override
		public boolean joinDecodingAndSubtitleFilters() {
			return false;
		}
	},
	NONE {
		@Override
		public String toString() {
			return "None";
		}

		@Override
		public String getFFmpegID() {
			return null;
		}

		@Override
		public ArrayList<String> getFFmpegCMD() {
			return new ArrayList<>();
		}
	};

	public String getFFmpegID() {
		return getFFmpegID();
	}

	public ArrayList<String> getFFmpegCMD() {
		return getFFmpegCMD();
	}

	public String getHardSubsFilter(String asscmd) {
		return getHardSubsFilter(asscmd);
	}

	public boolean joinDecodingAndSubtitleFilters() {
		return joinDecodingAndSubtitleFilters();
	}

	public static HWAccel[] getNvidiaHWAccel() {
		HWAccel[] res = {HWAccel.NVDEC,HWAccel.VSYNC, HWAccel.CUDA,
				HWAccel.MPEG4CUVID, HWAccel.MPEG2CUVID, HWAccel.NONE};
		return res;
	}
	
	
	  public static HWAccel getHWAccelFromString(String hwaccel) {
		    if (hwaccel != null) {
		      for (HWAccel accel : HWAccel.values()) {
		        if (accel.name().toLowerCase().equals(hwaccel.toLowerCase()))
		          return accel;
		      }
		    }
		    return HWAccel.NONE;
		  }

}
