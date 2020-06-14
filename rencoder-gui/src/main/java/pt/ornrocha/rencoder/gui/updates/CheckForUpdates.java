package pt.ornrocha.rencoder.gui.updates;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.pmw.tinylog.Logger;

import com.vdurmont.semver4j.Semver;

import pt.ornrocha.rencoder.helpers.osystem.OSystem;
import pt.ornrocha.rencoder.helpers.props.fields.StaticGlobalFields;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;

public class CheckForUpdates {
	
	
	public static final String RENCODERCURRENTVERSIONFILE="settings/Version.txt";
	public static final String RENCODERUPDATECHECKVERSIONFILE="CheckVersion.txt";
	public static final String UPDATESERVERURL="Update.server.url";
	public static final String VERSIONCHECKFILEURL="Version.file.url";
	public static final String SETTINGSFOLDER="settings";
	public static final String BLOCKUPDATESTAG=".blockupdates";
	
	
	public static boolean updateIsNeeded(){
		return compareVersionFile();
	}
	
	
	public static String getBlockUptdatesFilePath(){
		return OSystem.getCurrentDir()+OSystem.getSystemSeparator()+BLOCKUPDATESTAG;
	}
	
	public static File getBlockUptdatesFile(){
		return new File(getBlockUptdatesFilePath());
	}
	
	
	public static boolean isCheckUpdatesBlocked(){
		File f = getBlockUptdatesFile();
		if(f.exists())
			return true;
		else
			return false;
	}
	
	public static void setCheckUpdatesBlock(){
		File f= getBlockUptdatesFile();
		try {
			if(!f.exists())
			   f.createNewFile();
		} catch (IOException e) {
		    Logger.error(e);
		}
	}
	
	public static void deleteCheckUpdatesBlock(){
		if(getBlockUptdatesFile().exists())
			getBlockUptdatesFile().delete();
	}
	
	
	
    public static boolean compareVersionFile(){
		boolean needsupdate=false;
		File tempcheckversion = null;
		URLConnection conn=null;
		String appversionfile = OSystem.getCurrentDir()+OSystem.getSystemSeparator()+RENCODERCURRENTVERSIONFILE;
		
	
            PropertiesConfiguration props = PropertiesWorker.loadPropertiesRelativePath(StaticGlobalFields.RENCODERCONFIGFILE);
			String serverurl=null;
			String versionfilename = null;
			File appversion = new File(appversionfile);
			
		  try {
			if(!appversion.exists())
					throw new IOException("Missing local version file");

			

			if(props.containsKey(UPDATESERVERURL))
				serverurl=(String) props.getProperty(UPDATESERVERURL);
			
			if(serverurl==null && serverurl.isEmpty())
				throw new IOException("Missing server url");
			
			
			if(props.containsKey(VERSIONCHECKFILEURL))
					versionfilename=(String) props.getProperty(VERSIONCHECKFILEURL);
	        
			if(versionfilename==null && versionfilename.isEmpty())
				throw new IOException("Missing version filename");


			
	
			String checkversionfile = OSystem.getCurrentDir()+OSystem.getSystemSeparator()+RENCODERUPDATECHECKVERSIONFILE;
		    tempcheckversion = new File(checkversionfile);
		    
		    
		    String lastchar = serverurl.substring(serverurl.length()-1);
			if(serverurl!=null){
			if(!lastchar.equals("/"))
				serverurl=serverurl+"/";
			}
			
			String serverversionurl=null;
			
			if(serverurl!=null && versionfilename!=null){
			    serverversionurl=serverurl+versionfilename;

			    URL link = new URL(serverversionurl);
					conn= link.openConnection();
			    
			    
			    String inputLine;
				BufferedReader br = new BufferedReader( new InputStreamReader(conn.getInputStream()));
	
				
				FileWriter fw = new FileWriter(tempcheckversion.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
	 
				while ((inputLine = br.readLine()) != null) {
					bw.write(inputLine+"\n");
				}
	 
				bw.close();
				br.close();

				
				
				String currentversion= checkVersionInFile(appversion);
				String futureversion=checkVersionInFile(tempcheckversion);
				
				Semver sem = new Semver(futureversion);
		
				
				if(sem.isGreaterThan(currentversion)){
					needsupdate=true;
				}
				
				if(tempcheckversion.exists())
					tempcheckversion.delete();
			    

             } 
		    }catch (IOException e) {
		          needsupdate=false;
	          }
		
	   return needsupdate;
	}
	
	
  
    public static String checkVersionInFile(File file){
		String res="1.0.0";
		try {
			for(String line: FileUtils.readLines(file)){
				if(line.matches("(\\s+)*[Vv][Ee][Rr][Ss][Ii][Oo][Nn]:\\d+.\\d+.\\d+(\\s+)*"))
				   res =extractversion(line);
			}
		} catch (IOException e) {
		    Logger.error(e);
		}
		return res;
	 }
    
    
    public static String extractversion(String line){
		Pattern pat = Pattern.compile("(\\s+)*version:(\\d+.\\d+.\\d+)(\\s+)*", Pattern.CASE_INSENSITIVE);
		Matcher m = pat.matcher(line);
		if(m.find())
			return m.group(2);
		else
			return "1.0.0";
	}
    
    public static void main(String[] args){
    	System.out.println(CheckForUpdates.updateIsNeeded());
    }

}
