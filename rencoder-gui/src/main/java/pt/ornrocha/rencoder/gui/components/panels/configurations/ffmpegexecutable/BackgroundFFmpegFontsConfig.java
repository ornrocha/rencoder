package pt.ornrocha.rencoder.gui.components.panels.configurations.ffmpegexecutable;

import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingWorker;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.subtitles.SubtitlesColor;
import pt.ornrocha.rencoder.ffmpegWrapper.subtitles.SubtitleConverter;
import pt.ornrocha.rencoder.ffmpegWrapper.utilities.FFmpegUtils;
import pt.ornrocha.rencoder.gui.components.panels.info.MessageWarnDialog;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.helpers.osystem.OSystem;
import pt.ornrocha.rencoder.mediafiles.files.containers.base.Subtitlefile;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.SubtitleInfoContainer;
import pt.ornrocha.rencoder.mediafiles.setfiles.foldersandfiles.ListFiles;
import pt.ornrocha.rencoder.mediafiles.setfiles.processfiles.ProcessFiles;

public class BackgroundFFmpegFontsConfig extends SwingWorker<Void, Void>{

	private JFrame mainfraime; 
	
	public BackgroundFFmpegFontsConfig(JFrame mainframe){
		this.mainfraime=mainframe;
	}
	
	@Override
	protected Void doInBackground() throws Exception {
		
		SubtitleInfoContainer subinfo = new SubtitleInfoContainer();
		subinfo.setSubsFontSize(23);
		subinfo.setSubsColor(SubtitlesColor.GREEN);
		subinfo.setUseHardSubs(true);
		Subtitlefile sub= new Subtitlefile(FFmpegUtils.getDemoSubtitle());
		SubtitleConverter conv = new SubtitleConverter(sub, subinfo);
		final ArrayList<String> subcmds = conv.getFFmpegSubtitleEncodingCommands(OSystem.getTempFolder());
		final String tmppicture= ProcessFiles.checkIfFileExistsandReturnNewname(OSystem.getTempFolder(), "forcefontbuild", "png", 0, false);
		
		
		ImageIcon icon =new ImageIcon(ListFiles.getIconAbsolutePath("icons/warning64x64.png"));
		final MessageWarnDialog msgdialog = new MessageWarnDialog(mainfraime, LangTools.getWordLanguage("Configuring FFmpeg fonts\n Please wait...", "warngui.conffonts"), 
				                            LangTools.getWordLanguage("Warning", "warngui.tag"), icon);
	      
	      
	      SwingWorker<Void,Void> worker = new SwingWorker<Void,Void>()
				{
				    @Override
				    protected Void doInBackground()
				    {
				    	SubtitleConverter.makeSubtitlePicturePreview(FFmpegUtils.getMovieDemo(), subcmds, tmppicture);
				        return null;
				    }
				 
				    @Override
				    protected void done()
				    {
				    	msgdialog.dispose();
				    }
				};
				worker.execute();
				msgdialog.setVisible(true);
	      

		FFmpegUtils.createFFmpegFontsConfiguredTagFile();
		
		File subfile=new File(conv.getTempSubPath());
		if(subfile.exists())
			subfile.delete();
		
		File tmpimage =new File(OSystem.getTempFolder()+OSystem.getSystemSeparator()+"forcefontbuild.png");
		if(tmpimage.exists())
			tmpimage.delete();
		
		
		
		
		
		return null;
	}
	
	
	

}
