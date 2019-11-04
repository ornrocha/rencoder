package pt.ornrocha.rencoder.gui.components.panels.info;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComponent;
import javax.swing.JList;

import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.helpers.props.managers.EncodingProfileManager;
import pt.ornrocha.rencoder.helpers.props.managers.GeneralEncodingPropertiesManager;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.SubtitleInfoContainer;

public class ProfileComboxRender extends DefaultListCellRenderer{


	private static final long serialVersionUID = 1L;
	
	private GeneralEncodingPropertiesManager genencinfomanager;
	private EncodingProfileManager profilemanager=null;
	private static String BREAKTAG="<br>";
	private static String INITTAG="<html>";
	private static String ENDTAG="</html>";
	private static String LISTSTARTAG="<ul>";
	private static String LISTENDTAG="</ul>";
	private static String BULLETSTART="<li>";
	private static String BULLETEND="</li>";
	
	public ProfileComboxRender(GeneralEncodingPropertiesManager genencinfomanager, EncodingProfileManager profilemanager){
		this.genencinfomanager=genencinfomanager;
		this.profilemanager=profilemanager;
	}
	
	
	public Component getListCellRendererComponent( JList list, Object value, int index, boolean isSelected, boolean cellHasFocus ){
        
		JComponent c = (JComponent)super.getListCellRendererComponent( list,value, index, isSelected, cellHasFocus );
         if(c!=null){
		   if (isSelected && value!=null && index>-1) {
			   
              	String msg=null;
        	    if(index==0){
        		   msg= getprofileInfo(genencinfomanager.getGenEncInfoContainer());
        	      }
        	    else{
        		String profilename = (String) value;
        		msg = getprofileInfo(profilemanager.getEncodingInfoContainerbyname(profilename));
        	     }
        	
        	if(msg!=null)
             list.setToolTipText( msg);
           }
         }
        return c;
    }
	
	
	
	private String getprofileInfo(IGeneralVideoEncInfoContainer cont){
		StringBuilder str = new StringBuilder(INITTAG);
		str.append(LangTools.getWordLanguage("Video codec","videoinfo.codec")+": "+cont.getVideocodec().toString());
		str.append(LISTSTARTAG);
		str.append(BULLETSTART);
		str.append(LangTools.getWordLanguage("Video aspect","general.videosizeaspect")+": "+ cont.getVideoaspectsizecomponent().toString());
		str.append(BULLETEND);
		str.append(BULLETSTART);
		str.append(LangTools.getWordLanguage("Framerate","general.framerate")+":"+ cont.getVideoframerate().toString());
		str.append(BULLETEND);
		str.append(BULLETSTART);
		str.append(LangTools.getWordLanguage("Aspect ratio","general.aspectratio")+":"+ cont.getVideoaspectratio().toString());
		str.append(BULLETEND);
		
		if(cont.isUseVideoEncodingVBR()){
			str.append(BULLETSTART);
			str.append(LangTools.getWordLanguage("Variable bitrate","general.variablebitrate")+":"+ cont.getVideoVBRQualityValue());
			str.append(BULLETEND);
		}
		else if(cont.isUseVideoEncodingCBR()){
			str.append(BULLETSTART);
			str.append(LangTools.getWordLanguage("Constant bitrate","general.constantbitrate")+":"+ cont.getVideoConstBitrate());
			str.append(BULLETEND);
		}
        
		if(cont.isTwopassEncoding()){
			str.append(BULLETSTART);
			str.append(LangTools.getWordLanguage("Two Pass encoding","videogui.twopassenc"));
			str.append(BULLETEND);
		}
		else{
			str.append(BULLETSTART);
			str.append(LangTools.getWordLanguage("One Pass encoding","videogui.onepassenc"));
			str.append(BULLETEND);
		}
		
		
		str.append(LISTENDTAG);
		str.append(BREAKTAG);
		
		str.append(LangTools.getWordLanguage("Audio codec","audioinfo.codec")+":"+cont.getAudiocodec().toString());
        str.append(LISTSTARTAG);
		str.append(BULLETSTART);
		str.append(LangTools.getWordLanguage("Channels","audioinfo.channels")+":"+ cont.getAudiochannels().toString());
		str.append(BULLETEND);
		str.append(BULLETSTART);
		str.append(LangTools.getWordLanguage("Frequency","audioinfo.frequency")+":"+ cont.getAudiosamplerate().toString());
		str.append(BULLETEND);
		
		if(cont.isUseAudioCBR()){
		    str.append(BULLETSTART);
		    str.append(LangTools.getWordLanguage("Constant bitrate","general.constantbitrate")+":"+ cont.getAudioConstantBitrateValue());
		    str.append(BULLETEND);
		}
		else{
			    str.append(BULLETSTART);
			    str.append(LangTools.getWordLanguage("Variable bitrate","general.variablebitrate")+":"+ cont.getAudioVariableBitrateValues());
			    str.append(BULLETEND);
		}
		str.append(LISTENDTAG);
		str.append(BREAKTAG);
		
		if(cont.getSubtitleInfoContainer()!=null){
			
			SubtitleInfoContainer subinfo = cont.getSubtitleInfoContainer();
			boolean showsubinfo=false;
			
			if(subinfo.isUseHardSubs()){
				str.append(LangTools.getWordLanguage("Hardcoded Subtitles","subtitlesgui.hardsubtitlestag")+":");
				showsubinfo=true;
			}
			else if(subinfo.isUseSoftSubs()){
				str.append(LangTools.getWordLanguage("Softcoded Subtitles","subtitlesgui.softsubtitlestag")+":");
				showsubinfo=true;
			}
			
			if(showsubinfo){
				str.append(LISTSTARTAG);
				str.append(BULLETSTART);
			    str.append(LangTools.getWordLanguage("Font name","subtitlesinfo.fontname")+":"+ subinfo.getSubsFontName().toString());
			    str.append(BULLETEND);
				str.append(BULLETSTART);
			    str.append(LangTools.getWordLanguage("Font size","subtitlesinfo.fontsize")+":"+ subinfo.getSubsFontSize());
			    str.append(BULLETEND);
			    str.append(BULLETSTART);
			    str.append(LangTools.getWordLanguage("Font Color","subtitlesinfo.fontcolor")+":"+ subinfo.getSubsColor().toString());
			    str.append(BULLETEND);
			    
			   
			    str.append(BULLETSTART);
			    str.append(LangTools.getWordLanguage("Font style","subtitlesinfo.fontstyle")+":");
			         if(subinfo.isSubsUseBold()){
			         str.append("Bold ");
			         }
			         if(subinfo.isSubsUseItalic()){
			        	str.append("Italic "); 
			         }
			         else if(!subinfo.isSubsUseBold())
			        	 str.append("Plain ");  
			    str.append(BULLETEND);
			    
			    
			    str.append(BULLETSTART);
			    str.append(LangTools.getWordLanguage("Outline","subtitlesinfo.outline")+":"+ subinfo.getSubsOutline());
			    str.append(BULLETEND);
			    
			    str.append(LISTENDTAG);
				
			}
			
			
			
		}
		
		str.append(ENDTAG);
		
		return str.toString();
	}

}
