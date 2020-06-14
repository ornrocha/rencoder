package pt.ornrocha.rencoder.ffmpegWrapper.filters;

public class CropFilter implements IFFmpegFilter {

	private static String IWTAG = "iw";
	private static String IHTAG = "ih";
	private static String ENDTAG = "\"";
	private static String SEP = ":";

	private static String CROPTAG = "crop=";
	private static String CROPDETECTTAG = "\"cropdetect=";
	private boolean isCropDetect = false;

	private String out_width = null;
	private String out_height = null;
	private String xbegin = null;
	private String ybegin = null;

	// only for cropdetect
	private int limit = 24;
	private int round = 16;
	private int reset_count = 0;

	public CropFilter() {

	}

	public CropFilter(String out_width, String out_height) {
		this.out_width = out_width;
		this.out_height = out_height;
	}

	public CropFilter(String out_width, String out_height, String xbegin, String ybegin) {
		this.out_width = out_width;
		this.out_height = out_height;
		this.xbegin = xbegin;
		this.ybegin = ybegin;
	}

	public CropFilter(int limit, int round, int reset) {
		this.isCropDetect = true;
		this.limit = limit;
		this.round = round;
		this.reset_count = reset;

	}

	public String getOut_width() {
		if (out_width == null)
			return "0";
		else
			return out_width;
	}

	public String getOut_height() {
		if (out_height == null)
			return "0";
		else
			return out_height;
	}

	public String getXbegin() {
		if (xbegin == null)
			return "0";
		else
			return xbegin;
	}

	public String getYbegin() {
		if (ybegin == null)
			return "0";
		else
			return ybegin;
	}

	public int getLimit() {
		return limit;
	}

	public int getRound() {
		return round;
	}

	public int getReset_count() {
		return reset_count;
	}

	public void setcropleftright(String x) {
		this.xbegin = x;
	}

	public void setcroptopbottom(String y) {
		this.ybegin = y;
	}

	public void setWindowSize(String width, String height) {
		this.out_width = width;
		this.out_height = height;
	}

	public void setAutoCropDetect(int limit, int round, int reset) {
		this.isCropDetect = true;
		this.limit = limit;
		this.round = round;
		this.reset_count = reset;
	}

	public void setAutoCropDetect(int limit, int round) {
		this.isCropDetect = true;
		this.limit = limit;
		this.round = round;
	}

	public void setAutoCropDetect() {
		this.isCropDetect = true;
	}

	public boolean isCropDetect() {
		return isCropDetect;
	}

	private String doubleValue(String val) {
		int initval = Integer.valueOf(val);
		int outvl = initval * 2;
		return String.valueOf(outvl);
	}

	public void reset() {
		out_width = null;
		out_height = null;
		xbegin = null;
		ybegin = null;
		this.isCropDetect = false;
		limit = 24;
		round = 16;
		reset_count = 0;
	}

	@Override
	public String getFFmpegCMD() {

		if (isCropDetect) {
			return CROPDETECTTAG + String.valueOf(limit) + SEP + String.valueOf(round) + SEP
					+ String.valueOf(reset_count);
		} else {
			if (out_width == null && out_height == null) {
				if (xbegin != null && ybegin != null) {
					return CROPTAG + IWTAG + "-" + doubleValue(xbegin) + SEP + IHTAG + "-" + doubleValue(ybegin) + SEP
							+ xbegin + SEP + ybegin;
				} else if (xbegin == null && ybegin != null)
					return CROPTAG + IWTAG + SEP + IHTAG + "-" + doubleValue(ybegin);
				else if (xbegin != null && ybegin == null)
					return CROPTAG + IWTAG + "-" + doubleValue(xbegin) + SEP + IHTAG;

				else
					return null;
			} else {
				if (xbegin != null && ybegin != null) {
					return CROPTAG + out_width + SEP + out_height + SEP + xbegin + SEP + ybegin;
				} else if (xbegin == null && ybegin != null)
					return CROPTAG + out_width + SEP + out_height + SEP + "0" + SEP + ybegin;
				else if (xbegin != null && ybegin == null)
					return CROPTAG + out_width + SEP + out_height + SEP + xbegin + SEP + "0";
				else if (xbegin == null && ybegin == null)
					return CROPTAG + out_width + SEP + out_height + SEP + "0" + SEP + "0";

				else
					return null;
			}
		}
	}

}
