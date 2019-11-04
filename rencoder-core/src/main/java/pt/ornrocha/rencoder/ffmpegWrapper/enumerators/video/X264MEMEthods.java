package pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video;

public enum X264MEMEthods {

	NONE {
		public String toString() {
			return "none";
		}
	},
	EPZS {
		public String toString() {
			return "epzs";
		}
	},
	HEX {
		public String toString() {
			return "hex";
		}
	},
	UMH {
		public String toString() {
			return "umh";
		}
	},
	FULL {
		public String toString() {
			return "full";
		}
	};

}
