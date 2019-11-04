package pt.ornrocha.rencoder.gui.components.panels.player;

import java.awt.Component;

public class RunPlayer implements Runnable{
	
	
	private VlcJPlayerPanel player=null;
	private String Videofilepath;
	private String SubFilepath;
	private boolean issubpreview=false;
	private String cmd=null;
	private Component parent=null;
	
	public static int SUBTITLEPREVIEWACTION=0;
	public static int CROPPREVIEWACTION=1;
	private int action=0;
	
	public RunPlayer(String Videofilepath, String SubFilepath, boolean isSubtitlePreview){
		player = new VlcJPlayerPanel();
		this.Videofilepath=Videofilepath;
		this.SubFilepath=SubFilepath;
		this.issubpreview=isSubtitlePreview;
	}
	
	public RunPlayer(String Videofilepath, String cmd, int action){
		player = new VlcJPlayerPanel();
		this.Videofilepath=Videofilepath;
		this.cmd=cmd;
		this.action=action;
	}
	
	public void setParentComponent(Component parent) {
		this.parent=parent;
	}

	@Override
	public void run() {
		player.setVisible(true);
		if(parent!=null)
			player.setLocationRelativeTo(parent);
	    player.toFront();
	    if(action==SUBTITLEPREVIEWACTION) 
		   player.setVideoAndSubtitlesToPlay(Videofilepath, SubFilepath, issubpreview);
	    else if(action==CROPPREVIEWACTION)
	    	player.setVideoAndCropGeopmetry(Videofilepath, cmd);
	    	
	}

}
