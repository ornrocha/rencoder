package pt.ornrocha.rencoder.gui.components.panels.configurations.filters.subcomponents;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.filters.CropFilter;
import pt.ornrocha.rencoder.gui.components.panels.player.RunPlayer;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.mediafiles.files.containers.base.Videofile;
import pt.ornrocha.rencoder.mediafiles.setfiles.foldersandfiles.ListFiles;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;


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
public class CropConfigPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jPanelauto;
	private JPanel jPanelnormalcrop;
	private JCheckBox jCheckBoxmanual;
	private JButton jButtonpreview;
	private JCheckBox jCheckBoxedit;
	private JSpinner jSpinnerreset;
	private JLabel jLabelreset;
	private JSpinner jSpinnerround;
	private JLabel jLabelround;
	private JSpinner jSpinnerlimit;
	private JSpinner jSpinnerheight;
	private JLabel jLabellimit;
	private JLabel jLabelheight;
	private JLabel jLabelwidth;
	private JSpinner jSpinnerwidth;
	private JCheckBox jCheckBoxfixeed;
	private JSpinner jSpinnerleftright;
	private JLabel jLabelleftright;
	private JSpinner jSpinnertopbottom;
	private JLabel jLabeltopbottom;
	private JCheckBox jCheckBoxauto;
	private Videofile movie=null;
	private ResourceBundle rb;
	private JFrame mainframe;
    
	
	
	private static String MANUALCROP="manualcrop";
	private static String AUTOCROP="autocrop";
	private static String FIXWINDOW="fixwindow";
	private static String EDIT="edit";
	private static String PREVIEWCROP="previewcrop";
	
	public CropConfigPanel(JFrame mainframe){
		this.mainframe=mainframe;
		rb = ResourceBundle.getBundle("lang",LangTools.getDefinedLanguage(), LangTools.loadLanguagesPath());
		initGUI();
		initComponents();
	}
	
	public void setVideoFile(Videofile video){
		this.movie=video;
	}
	
	private void initComponents(){
		jCheckBoxedit.setSelected(false);
		jCheckBoxedit.addActionListener(this);
		jCheckBoxedit.setActionCommand(EDIT);
		
		jCheckBoxauto.setSelected(false);
		jCheckBoxauto.addActionListener(this);
		jCheckBoxauto.setActionCommand(AUTOCROP);
		
		jCheckBoxmanual.setSelected(false);
		jCheckBoxmanual.addActionListener(this);
		jCheckBoxmanual.setActionCommand(MANUALCROP);
		
		jCheckBoxfixeed.setSelected(false);
		jCheckBoxfixeed.setEnabled(false);
		jCheckBoxfixeed.addActionListener(this);
		jCheckBoxfixeed.setActionCommand(FIXWINDOW);
		jButtonpreview.setEnabled(false);
		
		SpinnerModel modellimit= new SpinnerNumberModel(24,0,255,1);
		jSpinnerlimit.setModel(modellimit);
		jSpinnerlimit.setEnabled(false);
		
		SpinnerModel modelround= new SpinnerNumberModel(16,0,100,1);
		jSpinnerround.setModel(modelround);
		jSpinnerround.setEnabled(false);
		
		SpinnerModel modelreset= new SpinnerNumberModel(0,0,10,1);
		jSpinnerreset.setModel(modelreset);
		jSpinnerreset.setEnabled(false);
		
		
		SpinnerModel modeltopbottom= new SpinnerNumberModel(0,0,10000,1);
		jSpinnertopbottom.setModel(modeltopbottom);
		jSpinnertopbottom.setEnabled(false);
		
		SpinnerModel modelleftright= new SpinnerNumberModel(0,0,10000,1);
		jSpinnerleftright.setModel(modelleftright);
		jSpinnerleftright.setEnabled(false);
		
		SpinnerModel modelwidth= new SpinnerNumberModel(0,0,10000,1);
		jSpinnerwidth.setModel(modelwidth);
		jSpinnerwidth.setEnabled(false);
		
		SpinnerModel modelheight= new SpinnerNumberModel(0,0,10000,1);
		jSpinnerheight.setModel(modelheight);
		jSpinnerheight.setEnabled(false);
	}
	
	public void addPreviewActionListener(ActionListener l){
		jButtonpreview.setActionCommand(PREVIEWCROP);
		jButtonpreview.addActionListener(l);
	}
	
	public void fillCropPanelSettings(CropFilter filter){
		if(filter!=null){
			
			if(filter.isCropDetect()){
				int limit = filter.getLimit();
				int round = filter.getRound();
				int reset = filter.getReset_count();
				
				if(limit!=24 || round!=16 || reset!=0){
					jCheckBoxedit.setSelected(true);
					editevent();
					jSpinnerlimit.setValue(limit);
					jSpinnerround.setValue(round);
					jSpinnerreset.setValue(reset);
				}
				jCheckBoxauto.setSelected(true);
				
			}
			else{
			
			
			int width = Integer.valueOf(filter.getOut_width());
			int height = Integer.valueOf(filter.getOut_height());
			int x = Integer.valueOf(filter.getXbegin());
			int y = Integer.valueOf(filter.getYbegin());
			
			jSpinnerwidth.setValue(width);
			jSpinnerheight.setValue(height);
			jSpinnerleftright.setValue(x);
			jSpinnertopbottom.setValue(y);

			
			if((x>0 && y==0) || (x==0 && y>0) || (x>0 && y>0)){
				jCheckBoxmanual.setSelected(true); 
				manualcropevent();
				if((width>0 && height>0))
				    jCheckBoxfixeed.setSelected(true);
				else
					jCheckBoxfixeed.setSelected(false);
				
				fixwindowevent();
			}
			else{
				if((width>0 && height>0)){
					jCheckBoxmanual.setSelected(true); 
					manualcropevent();
				    jCheckBoxfixeed.setSelected(true);
				    fixwindowevent();
				 
				}
				else{
				   jCheckBoxmanual.setSelected(false);
				   jCheckBoxfixeed.setSelected(false);
				}
			}
		  }
		}
	}

	private void initGUI() {
		try {
			{
				GridBagLayout thisLayout = new GridBagLayout();
				this.setPreferredSize(new java.awt.Dimension(463, 205));
				thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1};
				thisLayout.rowHeights = new int[] {7, 7, 7, 7, 7};
				thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
				thisLayout.columnWidths = new int[] {7, 7, 7, 7};
				this.setLayout(thisLayout);
				{
					jPanelauto = new JPanel();
					GridBagLayout jPanelautoLayout = new GridBagLayout();
					jPanelautoLayout.rowWeights = new double[] {0.1, 0.1};
					jPanelautoLayout.rowHeights = new int[] {7, 7};
					jPanelautoLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
					jPanelautoLayout.columnWidths = new int[] {7, 7, 7, 7};
					jPanelauto.setLayout(jPanelautoLayout);
					this.add(jPanelauto, new GridBagConstraints(0, 0, 4, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPanelauto.setBorder(BorderFactory.createTitledBorder(LangTools.getResourceBundleWordLanguage(rb,"Autocrop black borders","filters.autocropblackborders")));
					{
						jCheckBoxauto = new JCheckBox();
						jPanelauto.add(jCheckBoxauto, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 20, 0, 0), 0, 0));
						jCheckBoxauto.setText(LangTools.getResourceBundleWordLanguage(rb,"Automatic","filters.automatic"));
					}
					{
						jLabellimit = new JLabel();
						jPanelauto.add(jLabellimit, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jLabellimit.setText(LangTools.getResourceBundleWordLanguage(rb,"Limit","filters.limit"));
					}
					{
						
						jSpinnerlimit = new JSpinner();
						jPanelauto.add(jSpinnerlimit, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						
					}
					{
						jLabelround = new JLabel();
						jPanelauto.add(jLabelround, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jLabelround.setText(LangTools.getResourceBundleWordLanguage(rb,"Round","filters.round"));
					}
					{
						
						jSpinnerround = new JSpinner();
						jPanelauto.add(jSpinnerround, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						
					}
					{
						jLabelreset = new JLabel();
						jPanelauto.add(jLabelreset, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jLabelreset.setText(LangTools.getResourceBundleWordLanguage(rb,"Reset","filters.reset"));
					}
					{
						
						jSpinnerreset = new JSpinner();
						jPanelauto.add(jSpinnerreset, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						
					}
					{
						jCheckBoxedit = new JCheckBox();
						jPanelauto.add(jCheckBoxedit, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 20, 0, 0), 0, 0));
						jCheckBoxedit.setText(LangTools.getResourceBundleWordLanguage(rb,"Edit","filters.edit"));
					}
				}
				{
					jPanelnormalcrop = new JPanel();
					GridBagLayout jPanelnormalcropLayout = new GridBagLayout();
					this.add(jPanelnormalcrop, new GridBagConstraints(0, 2, 4, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPanelnormalcrop.setBorder(BorderFactory.createTitledBorder(null, LangTools.getResourceBundleWordLanguage(rb,"Cropping","filters.cropping"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION));
					jPanelnormalcropLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
					jPanelnormalcropLayout.rowHeights = new int[] {7, 7, 7, 7};
					jPanelnormalcropLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
					jPanelnormalcropLayout.columnWidths = new int[] {7, 7, 7, 7};
					jPanelnormalcrop.setLayout(jPanelnormalcropLayout);
					{
						jCheckBoxmanual = new JCheckBox();
						jPanelnormalcrop.add(jCheckBoxmanual, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 20, 0, 0), 0, 0));
						jCheckBoxmanual.setText(LangTools.getResourceBundleWordLanguage(rb,"Manual","filters.manual"));
					}
					{
						jLabeltopbottom = new JLabel();
						jPanelnormalcrop.add(jLabeltopbottom, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jLabeltopbottom.setText(LangTools.getResourceBundleWordLanguage(rb,"Top/Bottom","filters.topbottom"));
					}
					{
						
						jSpinnertopbottom = new JSpinner();
						jPanelnormalcrop.add(jSpinnertopbottom, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						
					}
					{
						jLabelleftright = new JLabel();
						jPanelnormalcrop.add(jLabelleftright, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jLabelleftright.setText(LangTools.getResourceBundleWordLanguage(rb,"Left/Right","filters.leftright"));
					}
					{
						
						jSpinnerleftright = new JSpinner();
						jPanelnormalcrop.add(jSpinnerleftright, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						
					}
					{
						jCheckBoxfixeed = new JCheckBox();
						jPanelnormalcrop.add(jCheckBoxfixeed, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 20, 0, 0), 0, 0));
						jCheckBoxfixeed.setText(LangTools.getResourceBundleWordLanguage(rb,"Fix window size","filters.fixwindowsize"));
					}
					{
						
						jSpinnerwidth = new JSpinner();
						jPanelnormalcrop.add(jSpinnerwidth, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						
					}
					{
						jLabelwidth = new JLabel();
						jPanelnormalcrop.add(jLabelwidth, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jLabelwidth.setText(LangTools.getResourceBundleWordLanguage(rb,"Width","filters.width"));
					}
					{
						
						jSpinnerheight = new JSpinner();
						jPanelnormalcrop.add(jSpinnerheight, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						
					}
					{
						jLabelheight = new JLabel();
						jPanelnormalcrop.add(jLabelheight, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jLabelheight.setText(LangTools.getResourceBundleWordLanguage(rb,"Height","filters.height"));
					}
					{
						jButtonpreview = new JButton();
						jPanelnormalcrop.add(jButtonpreview, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						jButtonpreview.setText(LangTools.getResourceBundleWordLanguage(rb,"Preview","filters.preview"));
						jButtonpreview.setActionCommand(PREVIEWCROP);
						jButtonpreview.addActionListener(this);
					}
				}
			}
		} catch(Exception e) {
		    Logger.error(e);
		}
	}
		
	

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			
			if(cmd.equals(AUTOCROP)){
				autocropevent();
			}
			else if(cmd.equals(MANUALCROP)){
				manualcropevent();
			}
			else if(cmd.equals(FIXWINDOW)){
				fixwindowevent();
			}
			else if(cmd.equals(EDIT)){
				editevent();
				
			}
			else if(cmd.equals(PREVIEWCROP))
				LaunchCropPreviewPanel();
		}
		
		private void autocropevent(){
			if(jCheckBoxauto.isSelected()){
			jCheckBoxedit.setEnabled(true);
			jCheckBoxedit.setSelected(false);
			jSpinnerlimit.setEnabled(false);
			jSpinnerround.setEnabled(false);
			jSpinnerreset.setEnabled(false);
			jSpinnertopbottom.setEnabled(false);
			jSpinnerleftright.setEnabled(false);
			jSpinnerwidth.setEnabled(false);
			jSpinnerheight.setEnabled(false);
			jCheckBoxmanual.setSelected(false);
			jCheckBoxfixeed.setSelected(false);
			jCheckBoxfixeed.setEnabled(false);
			jButtonpreview.setEnabled(false);
			}
			else
				resetState();
		}
		
		
		private void manualcropevent(){
			if(jCheckBoxmanual.isSelected()){
			jCheckBoxauto.setSelected(false);
			jCheckBoxedit.setEnabled(false);
			jCheckBoxedit.setSelected(false);
			jSpinnerlimit.setEnabled(false);
			jSpinnerround.setEnabled(false);
			jSpinnerreset.setEnabled(false);
			jSpinnertopbottom.setEnabled(true);
			jSpinnerleftright.setEnabled(true);
			jSpinnerwidth.setEnabled(false);
			jSpinnerheight.setEnabled(false);
			jCheckBoxfixeed.setSelected(false);
			jCheckBoxfixeed.setEnabled(true);
			if(movie!=null)
			  jButtonpreview.setEnabled(true);
			}
			else{
				resetState();
			}
		}
		
		private void fixwindowevent(){
			if(jCheckBoxfixeed.isSelected()){
				jSpinnerwidth.setEnabled(true);
				jSpinnerheight.setEnabled(true);
				}
				else{
					jSpinnerwidth.setEnabled(false);
					jSpinnerheight.setEnabled(false);
				}
		}
		
		private void editevent(){
			if(jCheckBoxedit.isSelected()){
				jSpinnerlimit.setEnabled(true);
				jSpinnerround.setEnabled(true);
				jSpinnerreset.setEnabled(true);
			}
			else{
				jSpinnerlimit.setEnabled(false);
				jSpinnerround.setEnabled(false);
				jSpinnerreset.setEnabled(false);
			}	
		}
		
		private void resetState(){
			jCheckBoxauto.setSelected(false);
			jCheckBoxmanual.setSelected(false);
			jCheckBoxedit.setEnabled(false);
			jCheckBoxedit.setSelected(false);
			jSpinnerlimit.setEnabled(false);
			jSpinnerround.setEnabled(false);
			jSpinnerreset.setEnabled(false);
			jSpinnertopbottom.setEnabled(false);
			jSpinnerleftright.setEnabled(false);
			jSpinnerwidth.setEnabled(false);
			jSpinnerheight.setEnabled(false);
			jCheckBoxfixeed.setSelected(false);
			jCheckBoxfixeed.setEnabled(false);
			jButtonpreview.setEnabled(false);
		}
		
		
		private void LaunchCropPreviewPanel(){
			boolean vlcinstalled = new NativeDiscovery().discover();
			String cmd = getCropPreviewCmd();
			if(cmd!=null){
			   if(vlcinstalled){ 
			      RunPlayer player = new RunPlayer(this.movie.getFilePath(), cmd, RunPlayer.CROPPREVIEWACTION);
			      SwingUtilities.invokeLater(player);
			    }
			    else{
				   //warn=new WarnDialogPanel(LangTools.getWordLanguage("Please install VLC Player to enable this feature","warngui.vlcerror"), this);
			    	 JOptionPane.showMessageDialog(mainframe,
			    			    LangTools.getWordLanguage("Please install VLC Player to enable this feature","warngui.vlcerror"),
								LangTools.getResourceBundleWordLanguage(rb,"Warning","warngui.tag"),
							    JOptionPane.INFORMATION_MESSAGE,
							    new ImageIcon(ListFiles.getIconAbsolutePath("icons/warning64x64.png")));
			    }
			}
			else
				//warn=new WarnDialogPanel(LangTools.getResourceBundleWordLanguage(rb,"Error in crop settings","filters.croperror"), this);
				 JOptionPane.showMessageDialog(mainframe,
						    LangTools.getResourceBundleWordLanguage(rb,"Error in crop settings","filters.croperror"),
							LangTools.getResourceBundleWordLanguage(rb,"Warning","warngui.tag"),
						    JOptionPane.INFORMATION_MESSAGE,
						    new ImageIcon(ListFiles.getIconAbsolutePath("icons/warning64x64.png")));
		}
		
		private String getCropPreviewCmd(){
			if(jCheckBoxmanual.isSelected() && !jCheckBoxfixeed.isSelected()){
				int topbottomval=(int) jSpinnertopbottom.getValue();
				int leftrightval=(int) jSpinnerleftright.getValue();

				return String.valueOf(leftrightval)+"+"+String.valueOf(topbottomval)+"+"+String.valueOf(leftrightval)+"+"+String.valueOf(topbottomval);
			}
			else if(jCheckBoxmanual.isSelected() && jCheckBoxfixeed.isSelected()){
				int topbottomval=(int) jSpinnertopbottom.getValue();
				int leftrightval=(int) jSpinnerleftright.getValue();
				int width= (int) jSpinnerwidth.getValue();
				int height = (int) jSpinnerheight.getValue();
				String movwidth=movie.getMovieinfocontainer().getVideoinfo().getVideoWidth();
				String movheight=movie.getMovieinfocontainer().getVideoinfo().getVideoHeight();
				
				if(width<1 && height>0)
				    return movwidth+"x"+String.valueOf(height)+"+"+String.valueOf(leftrightval)+"+"+String.valueOf(topbottomval);
				else if(width>0 && height<1)
					return String.valueOf(width)+"x"+movheight+"+"+String.valueOf(leftrightval)+"+"+String.valueOf(topbottomval);
				else if(width>0 && height>0)
					return String.valueOf(width)+"x"+String.valueOf(height)+"+"+String.valueOf(leftrightval)+"+"+String.valueOf(topbottomval);
				else
					return movwidth+"x"+movheight+"+"+String.valueOf(leftrightval)+"+"+String.valueOf(topbottomval);
			}
			else
				return null;
	
		}
		
		public CropFilter getFilter(){
			CropFilter filter=null;
			
			if(jCheckBoxauto.isSelected() && !jCheckBoxedit.isSelected()){
				filter= new CropFilter();
				filter.setAutoCropDetect();
				return filter;
			}
			else if(jCheckBoxauto.isSelected() && jCheckBoxedit.isSelected()){
				filter=new CropFilter((int)jSpinnerlimit.getValue(), (int)jSpinnerround.getValue(), (int)jSpinnerreset.getValue());
				return filter;
			}
			else if(jCheckBoxmanual.isSelected() && !jCheckBoxfixeed.isSelected()){
				int topbottomval=(int) jSpinnertopbottom.getValue();
				int leftrightval=(int) jSpinnerleftright.getValue();
				if(topbottomval>0 && leftrightval>0)
				   filter=new CropFilter(null, null, String.valueOf(leftrightval),String.valueOf(topbottomval) );
				else if(leftrightval>0 && topbottomval==0)
					filter=new CropFilter(null, null, String.valueOf(leftrightval), null);
				else if(leftrightval==0 && topbottomval>0)
					filter=new CropFilter(null, null, null, String.valueOf(topbottomval));
				
				return filter;
			}
			else if(jCheckBoxmanual.isSelected() && jCheckBoxfixeed.isSelected()){
				int topbottomval=(int) jSpinnertopbottom.getValue();
				int leftrightval=(int) jSpinnerleftright.getValue();
				int width= (int) jSpinnerwidth.getValue();
				int height = (int) jSpinnerheight.getValue();
				
				if(topbottomval>0 && leftrightval>0)
					filter=new CropFilter(String.valueOf(width),String.valueOf(height),String.valueOf(leftrightval), String.valueOf(topbottomval));
				else if(leftrightval>0 && topbottomval==0)
					filter=new CropFilter(String.valueOf(width),String.valueOf(height),String.valueOf(leftrightval), null);
				else if(leftrightval==0 && topbottomval>0)
					filter=new CropFilter(String.valueOf(width),String.valueOf(height),null, String.valueOf(topbottomval));
				else if(leftrightval==0 && topbottomval==0)
					filter=new CropFilter(String.valueOf(width),String.valueOf(height),null, null);
				
				return filter;
			}
			else
			  return null;
		}


}
