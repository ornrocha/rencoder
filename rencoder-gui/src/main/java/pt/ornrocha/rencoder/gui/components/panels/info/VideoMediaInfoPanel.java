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
package pt.ornrocha.rencoder.gui.components.panels.info;


import java.util.ResourceBundle;

import javax.swing.JTabbedPane;

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.gui.components.panels.Scroll.MediaInfoScrollPanel;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.mediafiles.files.containers.base.Videofile;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.MediaInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.streams.AudioStreamInfo;
import pt.ornrocha.rencoder.mediafiles.files.containers.streams.VideoStreamInfo;

// TODO: Auto-generated Javadoc
/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class VideoMediaInfoPanel extends JTabbedPane {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The j panelvideo. */
	private MediaInfoScrollPanel jPanelvideo;
	
	/** The j panelaudio. */
	private MediaInfoScrollPanel jPanelaudio;
	
	/** The selectedmovie. */
	protected Videofile selectedmovie=null;
	
	/** The rb. */
	private ResourceBundle rb;
	
	
	
	/**
	 * Instantiates a new video media info panel.
	 */
	public VideoMediaInfoPanel(){
		rb = ResourceBundle.getBundle("lang",LangTools.getDefinedLanguage(), LangTools.loadLanguagesPath());
		initGUI();
		
	}

	/**
	 * Inits the gui.
	 */
	private void initGUI() {
		try {
			{
				this.setPreferredSize(new java.awt.Dimension(345, 182));
				{   
					String[] videocolnames={LangTools.getResourceBundleWordLanguage(rb, "Parameter","videoaudiogui.parameter"),LangTools.getResourceBundleWordLanguage(rb, "Value","videoaudiogui.value")};
					jPanelvideo = new MediaInfoScrollPanel(videocolnames,false);
					this.addTab(LangTools.getResourceBundleWordLanguage(rb, "Video Stream Info","videoinfo.streaminfo"), null, jPanelvideo, null);
					jPanelvideo.setPreferredSize(new java.awt.Dimension(340, 180));
				}
				{   
					String[] audiocolnames={LangTools.getResourceBundleWordLanguage(rb, "Parameter","videoaudiogui.parameter"),LangTools.getResourceBundleWordLanguage(rb, "Value","videoaudiogui.value")};
					jPanelaudio = new MediaInfoScrollPanel(audiocolnames,false);
					this.addTab(LangTools.getResourceBundleWordLanguage(rb, "Audio Stream info","audioinfo.streaminfo"), null, jPanelaudio, null);
				}

			}
		} catch(Exception e) {
		    Logger.error(e);
		}
	}
	
	
	/**
	 * Adds the movie file.
	 *
	 * @param moviefile the moviefile
	 */
	public void addMovieFile(Videofile moviefile){
	     this.selectedmovie=moviefile;
	     addnewvideoinfo(this.selectedmovie.getMovieinfocontainer());
	 }
	
	
	
	/**
	 * Add new video info.
	 *
	 * @param mediavideoinfo the mediavideoinfo
	 */
	private void addnewvideoinfo(MediaInfoContainer mediavideoinfo){
		resetTables();
		VideoStreamInfo videoinfo=mediavideoinfo.getVideoinfo();
		if(videoinfo!=null)
		   this.jPanelvideo.insertData(mediavideoinfo.getVideoinfo().getVideoInfoToTable());
		
		AudioStreamInfo audioinfo=mediavideoinfo.getfirstAudioStream();
		if(audioinfo!=null)
		   this.jPanelaudio.insertData(mediavideoinfo.getAudiostreamsinfo().get(0).getAudioInfoToTable());
		
			
	}
	
     /**
      * Reset tables.
      */
     public void resetTables(){
    	 this.jPanelaudio.reset();
    	 this.jPanelvideo.reset();
     }

}
