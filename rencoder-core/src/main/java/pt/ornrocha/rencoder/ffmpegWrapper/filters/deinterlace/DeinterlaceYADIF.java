package pt.ornrocha.rencoder.ffmpegWrapper.filters.deinterlace;

public class DeinterlaceYADIF extends DeinterlaceFilter {

	private int mode = 0;
	private int parity = -1;
	private int deint = 1;
	private static String YADIFTAG = "yadif=";

	public DeinterlaceYADIF() {

	}

	public DeinterlaceYADIF(int deint) {
		this.deint = deint;
	}

	public DeinterlaceYADIF(int mode, int parity, int deint) {
		this.mode = mode;
		this.parity = parity;
		this.deint = deint;
	}

	public int getMode() {
		return mode;
	}

	public int getParity() {
		return parity;
	}

	public int getDeint() {
		return deint;
	}

	@Override
	public String getFFmpegCMD() {
		// TODO Auto-generated method stub
		return YADIFTAG + mode + ":" + parity + ":" + deint;
	}

}
