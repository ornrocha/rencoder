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
package pt.ornrocha.rencoder.ffmpegWrapper.execution.progress;

import java.util.ArrayList;

import javax.swing.SwingWorker;

import org.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.configurations.FFmpegManager;
import pt.ornrocha.rencoder.ffmpegWrapper.configurations.FFmpegParametersChecker;
import pt.ornrocha.rencoder.ffmpegWrapper.configurations.RencoderFFmpegInfoContainer;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.HWAccel;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCodecs;

// TODO: Auto-generated Javadoc
/**
 * The Class ProgressBarUpdate.
 */
public class FFmpegCodecInfoAnalyser extends SwingWorker<Integer, String> {

	
	private RencoderFFmpegInfoContainer ffmpegInfoContainer = null;
	int pos = 0;
	
	public static String PROGRESSDOUBLE = "progressdouble";
	public static String PROGRESSMSG = "progresserrormsg";
	


	public FFmpegCodecInfoAnalyser(RencoderFFmpegInfoContainer ffmpegInfoContainer) {
		this.ffmpegInfoContainer=ffmpegInfoContainer;
	}

	public FFmpegCodecInfoAnalyser addPosition(int pos) {
		this.pos = pos;
		return this;
	}


	private void setProgressDouble(double newval) {
		getPropertyChangeSupport().firePropertyChange(PROGRESSDOUBLE, this.pos, newval);
	}

	private void setStringMsg(String msg) {
		getPropertyChangeSupport().firePropertyChange(PROGRESSMSG, this.pos, msg);
	}
//

	
	@Override
	protected Integer doInBackground() throws Exception {
	  
	    double mprogress = 0.0;
		
		Logger.debug("");
		Logger.debug("########## checking video codec support ##########\n");
		ArrayList<String> failed = new ArrayList<String>();

		ArrayList<VideoCodecs> supportedvideocodecs = new ArrayList<>();
		
		double total=(double)VideoCodecs.values().length;
		setProgressDouble(mprogress);
		
		int analysedcodecs=0;
		
		for (VideoCodecs vcodec : VideoCodecs.values()) {
            setStringMsg("Checking "+vcodec.toString());

			if (vcodec.needsSupportVerification()) {
				if (FFmpegParametersChecker.isSupportedCodec(vcodec, null, true))
					ffmpegInfoContainer.addHwaccelSupportedVideoCodec(vcodec.getFFmpegID());
				else
					failed.add(vcodec.getFFmpegID());
				if ((!failed.contains(vcodec.getFFmpegID()) || vcodec.needsDecodingFilter())
						&& vcodec.supportsDecodingHardwareAccel()) {

					HWAccel[] hwacceldecoders=vcodec.getSupportedHWAccelDecoders();
					for (HWAccel hwa : hwacceldecoders) {

						if (FFmpegParametersChecker.isSupportedCodec(vcodec, hwa, true)) {
							if (!ffmpegInfoContainer.getHwaccelSupportedVideoCodecs().contains(vcodec.getFFmpegID()))
								ffmpegInfoContainer.addHwaccelSupportedVideoCodec(vcodec.getFFmpegID());
							ffmpegInfoContainer.mapHwaccelDecoderToVideoCodec(vcodec, hwa);
						}
					}
				}
			}



			if (FFmpegManager.getInstance().isCodecSupported(vcodec.toString(), vcodec.getFFmpegID(),
					vcodec.needsSupportVerification()))
				supportedvideocodecs.add(vcodec);
			
			analysedcodecs++;
			mprogress=((double)analysedcodecs/total)*100;
            setProgressDouble(mprogress);

		}


		supportedvideocodecs.add(VideoCodecs.COPY);
		ffmpegInfoContainer.setRencoderSupportedVideoCodecs(
				supportedvideocodecs.toArray(new VideoCodecs[supportedvideocodecs.size()]));
		
		return 0;
		
	}
	

}
