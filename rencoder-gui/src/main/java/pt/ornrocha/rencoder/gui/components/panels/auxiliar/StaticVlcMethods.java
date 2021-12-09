package pt.ornrocha.rencoder.gui.components.panels.auxiliar;

import java.io.File;
import java.io.IOException;

import org.tinylog.Logger;

import pt.ornrocha.rencoder.helpers.osystem.OSystem;
import pt.ornrocha.rencoder.helpers.props.fields.StaticGlobalFields;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;


public final class StaticVlcMethods {
	
	
	public static String getVLCNOTFOUNDpath(){
		 return OSystem.getCurrentDir()+OSystem.getSystemSeparator()+StaticGlobalFields.VLCNOTFOUNDTAG;
	 }
	 
	 
	   public static boolean isVLCFOUND(){
		  File f = new File(getVLCNOTFOUNDpath());
		  if(f.exists())
			  return false;
		  else
			  return true;
		}
		
		public static void setVLCNOTFOUNDTAG(){
	
			File f = new File(getVLCNOTFOUNDpath());
			try {
				f.createNewFile();
			} catch (IOException e) {
				Logger.error(e);
			}
		}
		
		public static void removeVLCNOTFOUNDTAG(){
			File f = new File(getVLCNOTFOUNDpath());
			if(f.exists())
				f.delete();
		}
		
		public static void checkVLCinstalled(){
			try {
				boolean vlcinstalled = new NativeDiscovery().discover();
				Logger.debug("VLC is installed: "+vlcinstalled);
				if(!vlcinstalled)
					setVLCNOTFOUNDTAG();
				else
					removeVLCNOTFOUNDTAG();	

			} catch (Exception e) {
				Logger.error(e);
			}

		}

}
