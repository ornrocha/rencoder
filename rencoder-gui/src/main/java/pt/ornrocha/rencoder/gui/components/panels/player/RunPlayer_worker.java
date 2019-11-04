package pt.ornrocha.rencoder.gui.components.panels.player;

import javax.swing.SwingWorker;

public class RunPlayer_worker extends SwingWorker<Boolean, Void>{
	
	
	private VlcJPlayerPanel player=null;
	private String Videofilepath;
	private String SubFilepath;
	private boolean issubpreview=false;
	private String cmd=null;
	
	public static int SUBTITLEPREVIEWACTION=0;
	public static int CROPPREVIEWACTION=1;
	
	private int action=0;
	
	public RunPlayer_worker(String Videofilepath, String SubFilepath, boolean isSubtitlePreview){
		player = new VlcJPlayerPanel();
		this.Videofilepath=Videofilepath;
		this.SubFilepath=SubFilepath;
		this.issubpreview=isSubtitlePreview;
	}
	
	public RunPlayer_worker(String Videofilepath, String cmd, int action){
		player = new VlcJPlayerPanel();
		this.Videofilepath=Videofilepath;
		this.cmd=cmd;
		this.action=action;
	}
	

	@Override
	public Boolean doInBackground() {
		player.setVisible(true);
	    player.toFront();
	    if(action==SUBTITLEPREVIEWACTION)
		   player.setVideoAndSubtitlesToPlay(Videofilepath, SubFilepath, issubpreview);
	    else if(action==CROPPREVIEWACTION)
	    	player.setVideoAndCropGeopmetry(Videofilepath, cmd);
	    
	    return true;
	}

}
