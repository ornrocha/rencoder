package pt.ornrocha.rencoder.ffmpegWrapper.filters.deinterlace;

public class DeinterlaceKERNDEINT extends DeinterlaceFilter {

	private int thresh = 10;
	private int map = 0;
	private int order = 0;
	private int sharp = 0;
	private int twoway = 0;
	private static String KERNDEINTTAG = "kerndeint=";

	public DeinterlaceKERNDEINT() {

	}

	public DeinterlaceKERNDEINT(int thresh) {
		this.thresh = thresh;
	}

	public DeinterlaceKERNDEINT(int thresh, int map, int order, int sharp, int twoway) {
		this.thresh = thresh;
		this.map = map;
		this.order = order;
		this.sharp = sharp;
		this.twoway = twoway;
	}

	public int getThresh() {
		return thresh;
	}

	public int getMap() {
		return map;
	}

	public int getOrder() {
		return order;
	}

	public int getSharp() {
		return sharp;
	}

	public int getTwoway() {
		return twoway;
	}

	@Override
	public String getFFmpegCMD() {
		// TODO Auto-generated method stub
		return KERNDEINTTAG + "thresh=" + thresh + ":map=" + map + ":order=" + order + ":sharp=" + sharp + ":twoway="
				+ twoway;
	}

}
