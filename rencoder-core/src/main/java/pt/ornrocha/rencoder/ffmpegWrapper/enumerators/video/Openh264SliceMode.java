package pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video;

public enum Openh264SliceMode {

	auto, fixed, rowmb, dyn;

	public static Openh264SliceMode getSliceModeFromString(String mode) {

		if (mode != null)
			for (Openh264SliceMode m : Openh264SliceMode.values()) {
				if (m.toString().equalsIgnoreCase(mode))
					return m;
			}

		return Openh264SliceMode.auto;
	}

}
