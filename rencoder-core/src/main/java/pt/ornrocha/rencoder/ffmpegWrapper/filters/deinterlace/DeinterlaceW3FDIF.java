package pt.ornrocha.rencoder.ffmpegWrapper.filters.deinterlace;

public class DeinterlaceW3FDIF extends DeinterlaceFilter {

	private static String W3FDIFTAG = "w3fdif=";
	private String filter = "simple";
	private String deint = "all";

	public DeinterlaceW3FDIF() {

	}

	public DeinterlaceW3FDIF(String filter, String deint) {
		if (filter.equals("simple") || filter.equals("complex"))
			this.filter = filter;
		if (deint.equals("all") || deint.equals("interlaced"))
			this.deint = deint;
	}

	public String getFilter() {
		return filter;
	}

	public String getDeint() {
		return deint;
	}

	@Override
	public String getFFmpegCMD() {
		// TODO Auto-generated method stub
		return W3FDIFTAG + "filter=" + filter + ":deint=" + deint;
	}

}
