package pt.ornrocha.rencoder.gui.components.panels.info;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.helpers.props.fields.StaticGlobalFields;
import pt.ornrocha.rencoder.helpers.props.managers.auxiliar.EncodingPropsAuxiliar;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;

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
public class SubtitlesImagePreview extends JDialog implements ComponentListener, ActionListener, Runnable{

	private static final long serialVersionUID = 1L;
	private JButton jButtonclose;
	private JPanel jPanelimage;
	private JLabel jLabelpicture;
	private String imagepath=null;
	private int width, height =0;
	private ImageIcon imgIcon;


	private static String CLOSESUBPREVIEWPANEL="closesubpreview";
	
	public SubtitlesImagePreview(String imagepath){
		this.imagepath=imagepath;
		initGUI();
		setInitSettings();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	
	private void setInitSettings(){
		BufferedImage bufimg=null;
		try {
			bufimg = ImageIO.read(new File(this.imagepath));
		} catch (IOException e) {
		    Logger.error(e);
		}
        width= bufimg.getWidth();
        height= bufimg.getHeight();
  
        
        int newwidth=0;
        int newheight=0;
        boolean rescale=false;
        
        String demoaspect =PropertiesWorker.getStringProperty(StaticGlobalFields.RENCODERCONFIGFILE, StaticGlobalFields.USEDEMOSIZEPICTURE);
        if(demoaspect!=null && !demoaspect.isEmpty())
        	if(EncodingPropsAuxiliar.checkifisvalidAspectsize(demoaspect)){
        		Pattern aspect = Pattern.compile("(\\d+)[Xx](\\d+)");
        	    Matcher m = aspect.matcher(demoaspect);
        	    if(m.find()){
        	    	int extwidth=Integer.valueOf(m.group(1));
        	    	int extheight=Integer.valueOf(m.group(2));
        	    	
        	    	
        	    	if(extwidth!=width && extwidth>0 && extheight==height){
        	    		newwidth=extwidth;
        	    		newheight=height;
        	    	    rescale=true;
        	    	}
        	    	else if(extwidth==width && extheight!=height && extheight>0){
        	    		newwidth=width;
        	    		newheight=extheight;
        	    		rescale=true;
        	    	}
        	    	else if(extwidth!=width && extheight!=height && extwidth>0 && extheight>0){
        	    		newwidth=extwidth;
        	    		newheight=extheight;
        	    		rescale=true;
        	    	}
        	    	
        	    }
        	}
        
        imgIcon = new ImageIcon(this.imagepath);
       
        if(rescale){
           Image scaleImage = imgIcon.getImage().getScaledInstance(newwidth, newheight,Image.SCALE_SMOOTH);
           imgIcon = new ImageIcon(scaleImage);
           width=newwidth;
           height=newheight;
        }
        
        jLabelpicture.setIcon(imgIcon);
        jPanelimage.setPreferredSize(new Dimension(width, height));
        this.setSize(width+5, height+25);
        this.addComponentListener(this);
  
	}
	
	
	
	@Override
	public void componentResized(ComponentEvent e) {
		int newwidth = ((SubtitlesImagePreview)e.getSource()).getWidth();
		int newheight = ((SubtitlesImagePreview)e.getSource()).getHeight();
        Image scaleImage = imgIcon.getImage().getScaledInstance(newwidth-5, newheight-25,Image.SCALE_DEFAULT);
        ImageIcon ed= new ImageIcon(scaleImage);
        jLabelpicture.setIcon(ed);
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void initGUI() {
		try {
			GridBagLayout thisLayout = new GridBagLayout();
			thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
			thisLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
			thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
			thisLayout.columnWidths = new int[] {7, 7, 7, 7};
			getContentPane().setLayout(thisLayout);
			{
				jButtonclose = new JButton();
				getContentPane().add(jButtonclose, new GridBagConstraints(0, 11, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
				jButtonclose.setText(LangTools.getWordLanguage("Close","general.close"));
				jButtonclose.setActionCommand(CLOSESUBPREVIEWPANEL);
				jButtonclose.addActionListener(this);
			}
			{
				jPanelimage = new JPanel();
				GridBagLayout jPanelimageLayout = new GridBagLayout();
				getContentPane().add(jPanelimage, new GridBagConstraints(0, 0, 4, 11, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				jPanelimageLayout.rowWeights = new double[] {0.1};
				jPanelimageLayout.rowHeights = new int[] {7};
				jPanelimageLayout.columnWeights = new double[] {0.1};
				jPanelimageLayout.columnWidths = new int[] {7};
				jPanelimage.setLayout(jPanelimageLayout);
				{
					jLabelpicture = new JLabel();
					jPanelimage.add(jLabelpicture, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				}
			}
			{
				//this.setSize(564, 402);
			}
		} catch(Exception e) {
		    Logger.error(e);
		}
	}
 
	
	


	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals(CLOSESUBPREVIEWPANEL)){
			removeImage();
			this.dispose();
		}
		
	}
	
	
	private void removeImage(){
		if(this.imagepath!=null){
			File f = new File(imagepath);
			if(f.exists())
				f.delete();
		}
	}
	
	public static void main(String args[]){
		SubtitlesImagePreview subp = new SubtitlesImagePreview("/home/orocha/ORSimpleVideoConverter/movie.png");
		subp.setVisible(true);
	}


	@Override
	public void run() {
		this.setVisible(true);
		
	}

}
