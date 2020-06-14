/*
 * Copyright 2014
 *
 * This is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. 
 * 
 * This code is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the 
 * GNU Public License for more details. 
 * 
 * You should have received a copy of the GNU Public License 
 * along with this code. If not, see http://www.gnu.org/licenses/ 
 * 
 * Created by Orlando Rocha
 */
package pt.ornrocha.rencoder.mediafiles.files.containers.codecs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.configuration.PropertiesConfiguration;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.audio.AudioCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoContainers;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.XvidFlags;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.XvidMBD;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.XvidMEMethod;
import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.helpers.props.fields.StaticVideoEncoderFields;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.DefaultEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;

// TODO: Auto-generated Javadoc
/**
 * The Class VideoXvidEncodingInfoContainer.
 */
public class VideoXvidEncodingInfoContainer extends DefaultEncodingInfoContainer {

	private boolean mpeg_quant = false;
	private boolean lumi_aq = false;
	private boolean variance_aq = false;
	private boolean gmc = false;
	private int me_quality = 4;
	private ArrayList<XvidFlags> flags = new ArrayList<XvidFlags>();
	private XvidMEMethod memethod = XvidMEMethod.none;
	private XvidMBD mbd = XvidMBD.none;
	private int qmin = 0;
	private int qmax = 0;
	private int trellis = 0;

	/**
	 * Instantiates a new video xvid encoding info container.
	 */
	public VideoXvidEncodingInfoContainer() {
		super();
		setDefaultInfo();
	}

	@Override
	public void setDefaultInfo() {
		this.videocodec = VideoCodecs.XVID;
		this.videoContainer = VideoContainers.AVI;
		this.audiocodec = AudioCodecs.MP3;
		this.useAudioCBR = true;
		this.audioConstantBitrateValue = "128k";
		this.videoVBRQuality = new IndexedHashMap<String, Integer>() {

			private static final long serialVersionUID = 1L;

			{
				put(VideoCodecs.XVID.toString(), 4);
			}
		};
	}

	public boolean isMpeg_quant() {
		return mpeg_quant;
	}

	public void setMpeg_quant(boolean mpeg_quant) {
		this.mpeg_quant = mpeg_quant;
	}

	public boolean isLumi_aq() {
		return lumi_aq;
	}

	public void setLumi_aq(boolean lumi_aq) {
		this.lumi_aq = lumi_aq;
	}

	public boolean isVariance_aq() {
		return variance_aq;
	}

	public void setVariance_aq(boolean variance_aq) {
		this.variance_aq = variance_aq;
	}

	public boolean isGmc() {
		return gmc;
	}

	public void setGmc(boolean gmc) {
		this.gmc = gmc;
	}

	public int getMe_quality() {
		return me_quality;
	}

	public void setMe_quality(int me_quality) {
		this.me_quality = me_quality;
	}

	public ArrayList<XvidFlags> getFlags() {
		return flags;
	}

	public void setFlag(ArrayList<XvidFlags> flags) {
		this.flags = flags;
	}

	public XvidMEMethod getMemethod() {
		return memethod;
	}

	public void setMemethod(XvidMEMethod memethod) {
		this.memethod = memethod;
	}

	public XvidMBD getMbd() {
		return mbd;
	}

	public void setMbd(XvidMBD mbd) {
		this.mbd = mbd;
	}

	public int getQmin() {
		return qmin;
	}

	public void setQmin(int qmin) {
		this.qmin = qmin;
	}

	public int getQmax() {
		return qmax;
	}

	public void setQmax(int qmax) {
		this.qmax = qmax;
	}

	public int getTrellis() {
		return trellis;
	}

	public void setTrellis(int trellis) {
		this.trellis = trellis;
	}

	@Override
	public VideoXvidEncodingInfoContainer clone() {
		VideoXvidEncodingInfoContainer clone = new VideoXvidEncodingInfoContainer();
		clone = (VideoXvidEncodingInfoContainer) this.copyCommoninfoofcontainer(clone);
		clone = (VideoXvidEncodingInfoContainer) this.copySpecificInfocontainer(clone);
		return clone;
	}

	@Override
	public IGeneralVideoEncInfoContainer copySpecificInfocontainer(IGeneralVideoEncInfoContainer cont) {

		((VideoXvidEncodingInfoContainer) cont).setMpeg_quant(this.mpeg_quant);
		((VideoXvidEncodingInfoContainer) cont).setLumi_aq(this.lumi_aq);
		((VideoXvidEncodingInfoContainer) cont).setVariance_aq(this.variance_aq);
		((VideoXvidEncodingInfoContainer) cont).setGmc(this.gmc);
		((VideoXvidEncodingInfoContainer) cont).setFlag(this.flags);
		((VideoXvidEncodingInfoContainer) cont).setMemethod(this.memethod);
		((VideoXvidEncodingInfoContainer) cont).setMbd(this.mbd);
		((VideoXvidEncodingInfoContainer) cont).setQmin(this.qmin);
		((VideoXvidEncodingInfoContainer) cont).setQmax(this.qmax);
		((VideoXvidEncodingInfoContainer) cont).setTrellis(trellis);
		return cont;
	}

	@Override
	protected ArrayList<VideoContainers> getSupportedFormats() {
		return Arrays.stream(VideoCodecs.XVID.supportsOutputFormats()).collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	protected VideoContainers getpreferredFormat() {
		return VideoContainers.AVI;
	}

	@Override
	public void setConfigurations(PropertiesConfiguration props) {
		setVideoConfigurations(props);

		setMpeg_quant(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.XVIDMPEGQUANT, false));
		setLumi_aq(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.XVIDLUMIAQ, false));
		setVariance_aq(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.XVIDVARIANCEAQ, false));
		setGmc(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.XVIDGMC, false));
		setMe_quality(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.XVIDMEQUALITY, 0, 6, 4));
		setFlagsArrayFromSavedString((PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.XVIDFLAGS)));
		setMemethod(XvidMEMethod.getXvidMEMethodFromString(
				PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.XVIDMEMETHOD)));
		setMbd(XvidMBD.getXvidMBDFromString(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.XVIDMBD)));
		setQmin(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.XVIDQMIN, 0, 31, 0));
		setQmax(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.XVIDQMAX, 0, 31, 0));
		setTrellis(PropertiesWorker.checkProperty(props, StaticVideoEncoderFields.XVIDTRELLIS, 0, 50, 0));

		setAudioConfigurations(props);
		setSubtitleConfigurations(props);

	}

	@Override
	protected void saveSpecificCodecProperties(PropertiesConfiguration prop) {

		prop.setProperty(StaticVideoEncoderFields.XVIDMPEGQUANT, String.valueOf(mpeg_quant));
		prop.setProperty(StaticVideoEncoderFields.XVIDLUMIAQ, String.valueOf(lumi_aq));
		prop.setProperty(StaticVideoEncoderFields.XVIDVARIANCEAQ, String.valueOf(variance_aq));
		prop.setProperty(StaticVideoEncoderFields.XVIDGMC, String.valueOf(gmc));
		prop.setProperty(StaticVideoEncoderFields.XVIDMEQUALITY, String.valueOf(me_quality));
		if (flags.size() > 0)
			prop.setProperty(StaticVideoEncoderFields.XVIDFLAGS, convertArrayToSave());
		prop.setProperty(StaticVideoEncoderFields.XVIDMEMETHOD, memethod.toString());
		prop.setProperty(StaticVideoEncoderFields.XVIDMBD, mbd.toString());
		prop.setProperty(StaticVideoEncoderFields.XVIDQMIN, String.valueOf(qmin));
		prop.setProperty(StaticVideoEncoderFields.XVIDQMAX, String.valueOf(qmax));
		prop.setProperty(StaticVideoEncoderFields.XVIDTRELLIS, String.valueOf(trellis));

	}

	@Override
	public ArrayList<String> getSpecificVideoEncoderParameters(ArrayList<String> appendcmds) {
	
	        ArrayList<String> params=new ArrayList<>();
	        if(!memethod.equals(XvidMEMethod.none)){
	            params.add("-me_method");
	            params.add(memethod.toString());
	        }
	        if(!mbd.equals(XvidMBD.none)){
	            params.add("-mbd");
	            params.add(mbd.toString());
	        }
	        if(me_quality!=4) {
	            params.add("-me_quality");
	            params.add(String.valueOf(me_quality));
	        }
	        if(trellis!=0) {
	            params.add("-trellis");
	            params.add(String.valueOf(trellis));
	        }
	        if(qmin!=0) {
	            params.add("-qmin");
	            params.add(String.valueOf(qmin));
	        }
	        if(qmax!=0) {
	            params.add("-qmax");
	            params.add(String.valueOf(qmax));
	        }
	        if(mpeg_quant) {
	            params.add("-mpeg_quant");
	            params.add(String.valueOf(1));
	        }
	        if(lumi_aq) {
	            params.add("-lumi_aq");
	            params.add(String.valueOf(1));
	        }
	        if(variance_aq) {
	            params.add("-variance_aq");
	            params.add(String.valueOf(1));
	        }
	        if(gmc) {
	            params.add("-gmc");
	            params.add(String.valueOf(1));
	        }
	            
	        if(flags.size()>0) {
	            params.add("-flags");
	            params.add(convertArrayFlagstoCmd());
	        }
	            
	        
		return params;
	}

	@Override
	public boolean useSpecificVideoEncodingParameters() {
		return true;
	}

	private String convertArrayFlagstoCmd() {
		StringBuilder str = new StringBuilder();

		for (XvidFlags xvidFlags : flags) {
			str.append("+" + xvidFlags.toString());
		}

		return str.toString();
	}
	
	private String convertArrayToSave() {
	    StringBuilder str = new StringBuilder();
            str.append("[");
            int n=0;
	    for (XvidFlags xvidFlags : flags) {
		str.append(xvidFlags.toString());
		if(n<flags.size()-1)
		    str.append(",");
		n++;
	    }
	    str.append("]");

	    return str.toString();
	}
	
	private void setFlagsArrayFromSavedString(String cmds) {
	    if(cmds!=null) {
		String saved=cmds.substring(2, cmds.length() - 2);
		String[] listcmds = saved.split(",");
		ArrayList<XvidFlags> flagzz=new ArrayList<>();
		for (String flagstr : listcmds) {
		    XvidFlags flag=XvidFlags.getXvidFlagsFromString(flagstr.trim());
		    if(!flagzz.contains(flag))
			flagzz.add(flag);
		}
		flags=flagzz;
	    }
	    else {
		flags=new ArrayList<>();
	    }
	}

	@Override
	public String getContainerName() {
		// TODO Auto-generated method stub
		return "Xvid";
	}

}
