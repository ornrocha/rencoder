package pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video;

public enum XvidFlags {

    none,
    mv4,
    aic,
    gray,
    gmc,
    qpel,
    cgop,
    global_header;
    
    public static XvidFlags getXvidFlagsFromString(String flag) {
	if (flag != null) {
	    for (XvidFlags t : XvidFlags.values()) {
		if (t.toString().equalsIgnoreCase(flag))
		    return t;
	    }
	}
	return XvidFlags.none;
    }
}
