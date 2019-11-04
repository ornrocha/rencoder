package pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video;

public enum X264Trellis {
	// disabled
	DISABLE {
		public String toString() {
			return "0";
		}
	},
	// enabled only on the final encode of a MB
	EOFE {
		public String toString() {
			return "1";
		}
	},
	// enabled on all mode decisions
	EAMD {
		public String toString() {
			return "2";
		}
	};

}
