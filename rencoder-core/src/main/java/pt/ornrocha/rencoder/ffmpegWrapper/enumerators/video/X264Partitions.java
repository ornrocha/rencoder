package pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video;

public enum X264Partitions {

	none {
		public String toString() {
			return "none";
		}
	},
	partp8x8 {
		public String toString() {
			return "+partp8x8";
		}
	},
	partp4x4 {
		public String toString() {
			return "+partp4x4";
		}
	},
	partb8x8 {
		public String toString() {
			return "+partb8x8";
		}
	},
	parti8x8 {
		public String toString() {
			return "+parti8x8";
		}
	},
	parti4x4 {
		public String toString() {
			return "+parti4x4";
		}
	};

}
