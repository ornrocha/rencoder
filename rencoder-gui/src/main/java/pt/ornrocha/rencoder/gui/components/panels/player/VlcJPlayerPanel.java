package pt.ornrocha.rencoder.gui.components.panels.player;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.pmw.tinylog.Logger;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import pt.ornrocha.rencoder.gui.components.panels.info.BlinkMessagePanel;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.helpers.osystem.OSystem;
import pt.ornrocha.rencoder.helpers.props.fields.StaticGlobalFields;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;
import pt.ornrocha.rencoder.mediafiles.setfiles.foldersandfiles.ListFiles;
import uk.co.caprica.vlcj.binding.LibC;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.binding.internal.libvlc_media_t;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventListener;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

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
public class VlcJPlayerPanel extends JFrame implements ActionListener, MediaPlayerEventListener, ChangeListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jPanelplayer;
	private JButton jButton1;
	private JSlider jSlidervideo;
	private JButton jButtonclose;
	private JButton jButtonStop;
	private JButton jButtonplay;
	private JButton jButtonrewind;
	private JPanel jPanelCommands;
	private BlinkMessagePanel jPanelcheckfontsvlc;
	private JLabel jLabeltime;
	private EmbeddedMediaPlayerComponent mediaPlayerComponent;
	private long videoduration=0;
	
	
	private static String PLAY="play";
	private static String STOP="stop";
	private static String REWIND="rewind";
	private static String FORWARD="forward";
	private static String CLOSEPLAYER="closeplayer";
	
	private boolean isonlyvideo=false;
	private boolean issubpreview=false;
	
	private String subtitlepath=null;
	private String cropgeometry=null;
	
	
	public VlcJPlayerPanel(){
		initGUI();

		this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mediaPlayerComponent.release();
                if(issubpreview)
                	deleteTempSub();
                dispose();
               }
            });
		
		mediaPlayerComponent.getMediaPlayer().addMediaPlayerEventListener(this);
		jSlidervideo.addChangeListener(this);
		initComponents();
	}
	
    private void initComponents(){
    	jLabeltime.setHorizontalAlignment(SwingConstants.CENTER);
    	jLabeltime.setFont(new Font("Courier New", Font.BOLD, 13));
    }
	
    private void deleteTempSub(){
    	if(this.subtitlepath!=null){
    		File f = new File(subtitlepath);
    		if(f.exists())
    			f.delete();
    		this.subtitlepath=null;
    	}
    }
    
	
	public void setVideoAndSubtitlesToPlay(String videofilepath, String subfilepath, boolean issubpreview){	
		if(subfilepath!=null){
		   File sub=new File(subfilepath);
		   this.subtitlepath=subfilepath;
		   this.issubpreview=issubpreview;

		   mediaPlayerComponent.getMediaPlayer().playMedia(videofilepath);
		   mediaPlayerComponent.getMediaPlayer().setSubTitleFile(sub.getAbsolutePath());
		}
		else{
		   mediaPlayerComponent.getMediaPlayer().playMedia(videofilepath);
		   isonlyvideo=true;
		}
	}
	
	public void setVideoAndCropGeopmetry(String videofilepath, String cropgeometry){
		this.cropgeometry=cropgeometry;
		mediaPlayerComponent.getMediaPlayer().playMedia(videofilepath);
		isonlyvideo=true;
	}
	
	
	
	public void setSubtitleFile(String filepath){
		mediaPlayerComponent.getMediaPlayer().setSubTitleFile(filepath);
	}
	

	private void initGUI() {
		try {
			{   
				GridBagLayout thisLayout = new GridBagLayout();
				thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				thisLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7};
				thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
				
				thisLayout.columnWidths = new int[] {7, 7, 7, 7};
				getContentPane().setLayout(thisLayout);
				this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/rencoderbig.png")));
				{
					//jPanelplayer = new JPanel();
					
					
					if(OSystem.isMacOS()){
						
						  mediaPlayerComponent = new EmbeddedMediaPlayerComponent(){


					            @Override
					            protected String[] onGetMediaPlayerFactoryArgs() {
					                return new String[] {"--vout=macosx", "--no-plugins-cache", "--no-video-title-show", "--no-snapshot-preview", "--quiet", "--quiet-synchro", "--intf", "dummy"}; 
					            }

					        };
					}
					else
						mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
					
					getContentPane().add(mediaPlayerComponent, new GridBagConstraints(0, 0, 4, 5, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					jPanelCommands = new JPanel();
					GridBagLayout jPanelCommandsLayout = new GridBagLayout();
					getContentPane().add(jPanelCommands, new GridBagConstraints(0, 5, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPanelCommandsLayout.rowWeights = new double[] {0.1, 0.1, 0.1};
					jPanelCommandsLayout.rowHeights = new int[] {7, 7, 7};
					jPanelCommandsLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
					jPanelCommandsLayout.columnWidths = new int[] {7, 7, 7, 7, 7, 7, 7, 7};
					jPanelCommands.setLayout(jPanelCommandsLayout);
					{
						jButtonrewind = new JButton();
						jPanelCommands.add(jButtonrewind, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jButtonrewind.setText(LangTools.getWordLanguage("Rewind","playergui.rewind"));
						jButtonrewind.setIcon(new ImageIcon(ListFiles.getIconAbsolutePath("icons/rewind.png")));
						jButtonrewind.setActionCommand(REWIND);
						jButtonrewind.addActionListener(this);
					}
					{
						jButtonplay = new JButton();
						jPanelCommands.add(jButtonplay, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jButtonplay.setText(LangTools.getWordLanguage("Play","playergui.play"));
						jButtonplay.setIcon(new ImageIcon(ListFiles.getIconAbsolutePath("icons/play.png")));
						jButtonplay.setActionCommand(PLAY);
						jButtonplay.addActionListener(this);
					}
					{
						jButtonStop = new JButton();
						jPanelCommands.add(jButtonStop, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jButtonStop.setText(LangTools.getWordLanguage("Stop","playergui.stop"));
						jButtonStop.setIcon(new ImageIcon(ListFiles.getIconAbsolutePath("icons/stop.png")));
						jButtonStop.setActionCommand(STOP);
						jButtonStop.addActionListener(this);
					}
					{
						jButton1 = new JButton();
						jPanelCommands.add(jButton1, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jButton1.setText(LangTools.getWordLanguage("Forward","playergui.forward"));
						jButton1.setHorizontalTextPosition(SwingConstants.LEFT);
						jButton1.setIcon(new ImageIcon(ListFiles.getIconAbsolutePath("icons/forward.png")));
						jButton1.setActionCommand(FORWARD);
						jButton1.addActionListener(this);
					}
					{
						jButtonclose = new JButton();
						jPanelCommands.add(jButtonclose, new GridBagConstraints(6, 1, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jButtonclose.setText(LangTools.getWordLanguage("Close","general.close"));
						jButtonclose.setHorizontalTextPosition(SwingConstants.LEFT);
						jButtonclose.setIcon(new ImageIcon(ListFiles.getIconAbsolutePath("icons/logout.png")));
						jButtonclose.setActionCommand(CLOSEPLAYER);
						jButtonclose.addActionListener(this);
					}
					{
						jSlidervideo = new JSlider();
						jPanelCommands.add(jSlidervideo, new GridBagConstraints(0, 0, 6, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						jSlidervideo.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
					}
					{
						jLabeltime = new JLabel();
						jPanelCommands.add(jLabeltime, new GridBagConstraints(6, 0, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jLabeltime.setText(LangTools.getWordLanguage("time","playergui.time"));
						jLabeltime.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
					}
					{
						jPanelcheckfontsvlc = new BlinkMessagePanel(LangTools.getWordLanguage("Building vlc fonts","playergui.msg"));
						Thread t = new Thread(jPanelcheckfontsvlc);
						jPanelCommands.add(jPanelcheckfontsvlc, new GridBagConstraints(1, 2, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						t.start();
					}
				}
			}
			{
				this.setSize(601, 456);
			}
		} catch(Exception e) {
		    Logger.error(e);
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals(PLAY)){
			mediaPlayerComponent.getMediaPlayer().play();
		}
		else if(cmd.equals(STOP)){
			mediaPlayerComponent.getMediaPlayer().stop();
		}
		else if(cmd.equals(REWIND)){
			mediaPlayerComponent.getMediaPlayer().skip(-10000);
		}
		else if(cmd.equals(FORWARD)){
			mediaPlayerComponent.getMediaPlayer().skip(10000);
		}
		else if(cmd.equals(CLOSEPLAYER)){
			mediaPlayerComponent.release();
			if(issubpreview)
            	deleteTempSub();
			this.dispose();
		}
	}

    private String  convertTime(long time){
    	return String.format("%d:%d:%d", time/(1000*60*60), (time%(1000*60*60))/(1000*60), ((time%(1000*60*60))%(1000*60))/1000);
	}
	
    private void updateShowedTimeMovie(long time){
    	jLabeltime.setText(convertTime(time));
    }
	
	


	@Override
	public void mediaChanged(MediaPlayer mediaPlayer, libvlc_media_t media,
			String mrl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void opening(MediaPlayer mediaPlayer) {
		if(cropgeometry!=null)
		   mediaPlayer.setCropGeometry(cropgeometry);
		
	}

	@Override
	public void buffering(MediaPlayer mediaPlayer, float newCache) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playing(MediaPlayer mediaPlayer) {
		if(isonlyvideo){
			   mediaPlayerComponent.getMediaPlayer().setSpu(-1);
		   }
		videoduration =mediaPlayerComponent.getMediaPlayer().getLength();
        updateShowedTimeMovie(0);
		jSlidervideo.setMinimum(0);
		jSlidervideo.setMaximum((int)videoduration);
	}

	@Override
	public void paused(MediaPlayer mediaPlayer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopped(MediaPlayer mediaPlayer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forward(MediaPlayer mediaPlayer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void backward(MediaPlayer mediaPlayer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finished(MediaPlayer mediaPlayer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void timeChanged(MediaPlayer mediaPlayer, long newTime) {
		this.jPanelcheckfontsvlc.stop();
		jSlidervideo.setValue((int)newTime);
		updateShowedTimeMovie(newTime);
		
	}

	@Override
	public void positionChanged(MediaPlayer mediaPlayer, float newPosition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void seekableChanged(MediaPlayer mediaPlayer, int newSeekable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pausableChanged(MediaPlayer mediaPlayer, int newPausable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void titleChanged(MediaPlayer mediaPlayer, int newTitle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void snapshotTaken(MediaPlayer mediaPlayer, String filename) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lengthChanged(MediaPlayer mediaPlayer, long newLength) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void videoOutput(MediaPlayer mediaPlayer, int newCount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scrambledChanged(MediaPlayer mediaPlayer, int newScrambled) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void elementaryStreamAdded(MediaPlayer mediaPlayer, int type, int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void elementaryStreamDeleted(MediaPlayer mediaPlayer, int type,
			int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void elementaryStreamSelected(MediaPlayer mediaPlayer, int type,
			int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(MediaPlayer mediaPlayer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mediaMetaChanged(MediaPlayer mediaPlayer, int metaType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mediaSubItemAdded(MediaPlayer mediaPlayer,
			libvlc_media_t subItem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mediaDurationChanged(MediaPlayer mediaPlayer, long newDuration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mediaParsedChanged(MediaPlayer mediaPlayer, int newStatus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mediaFreed(MediaPlayer mediaPlayer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mediaStateChanged(MediaPlayer mediaPlayer, int newState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mediaSubItemTreeAdded(MediaPlayer mediaPlayer,
			libvlc_media_t item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newMedia(MediaPlayer mediaPlayer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void subItemPlayed(MediaPlayer mediaPlayer, int subItemIndex) {
		
	}

	@Override
	public void subItemFinished(MediaPlayer mediaPlayer, int subItemIndex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endOfSubItems(MediaPlayer mediaPlayer) {
		// TODO Auto-generated method stub
		
	}

	
	
	@Override
	public void stateChanged(ChangeEvent e) {

		Object source = e.getSource();
		
		if (source instanceof JSlider) {
			JSlider myJSlider = (JSlider)source;
			if(myJSlider.getValueIsAdjusting()){
				if(mediaPlayerComponent.getMediaPlayer().isPlaying()){
					mediaPlayerComponent.getMediaPlayer().setTime(new Long(myJSlider.getValue()));
				}
			}
		}
	}
	
	
	@Override
	public void audioDeviceChanged(MediaPlayer arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chapterChanged(MediaPlayer arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void corked(MediaPlayer arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void muted(MediaPlayer arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void volumeChanged(MediaPlayer arg0, float arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
	public static void checkVLCPluginFolder(){
		if(OSystem.isMacOS()){
			
	        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "/Applications/VLC.app/Contents/MacOS/lib");
	        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);

			
			String vlcpluginpath=null;
			try{
				vlcpluginpath=PropertiesWorker.getStringProperty(StaticGlobalFields.RENCODERCONFIGFILE, StaticGlobalFields.VLCPLUGINSPATH);
			}
			catch(Exception ex) {
				Logger.error(ex);
			}
            if(vlcpluginpath!=null && !vlcpluginpath.isEmpty()){
               LibC.INSTANCE.setenv("VLC_PLUGIN_PATH", vlcpluginpath, 1);
               Logger.debug("VLC_PLUGIN_PATH="+vlcpluginpath);
            }
            else{
			  LibC.INSTANCE.setenv("VLC_PLUGIN_PATH", "/Applications/VLC.app/Contents/MacOS/plugins", 1);
			  Logger.debug("VLC_PLUGIN_PATH=/Applications/VLC.app/Contents/MacOS/plugins");
            }
		}
	}

	@Override
	public void mediaPlayerReady(MediaPlayer mediaPlayer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mediaParsedStatus(MediaPlayer mediaPlayer, int newStatus) {
		// TODO Auto-generated method stub
		
	}

	

	
}
