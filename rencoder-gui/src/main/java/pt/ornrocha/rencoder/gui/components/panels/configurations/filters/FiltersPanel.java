package pt.ornrocha.rencoder.gui.components.panels.configurations.filters;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.filters.CropFilter;
import pt.ornrocha.rencoder.ffmpegWrapper.filters.RotateFilter;
import pt.ornrocha.rencoder.ffmpegWrapper.filters.ScalingFilter;
import pt.ornrocha.rencoder.ffmpegWrapper.filters.deinterlace.DeinterlaceFilter;
import pt.ornrocha.rencoder.gui.components.panels.configurations.filters.subcomponents.CropConfigPanel;
import pt.ornrocha.rencoder.gui.components.panels.configurations.filters.subcomponents.DeinterlaceConfigPanel;
import pt.ornrocha.rencoder.gui.components.panels.configurations.filters.subcomponents.RotateConfigPanel;
import pt.ornrocha.rencoder.gui.components.panels.configurations.filters.subcomponents.ScalingConfigPanel;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.mediafiles.files.containers.base.Videofile;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.FiltersInfoContainer;

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
public class FiltersPanel extends JDialog implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane jTabbedPanemain;
	private JScrollPane jScrollPanerotate;
	private JButton jButtoncancel;
	private JButton jButtonok;
	private JPanel jPanelrotate;
	private JPanel jPaneldeinterlace;
	private JPanel jPanelscale;
	private JPanel jPanelcrop;
	private JScrollPane jScrollPanescale;
	private ScalingConfigPanel scalepanel;
	private CropConfigPanel croppanel;
	private DeinterlaceConfigPanel deintpanel;
	private RotateConfigPanel rotatepanel;
	private JScrollPane jScrollPanecrop;
	private JScrollPane jScrollPanedeint;
	private Videofile movie=null;
	private PropertyChangeSupport changelst =null;
	private ResourceBundle rb;
	private JFrame mainframe;
    
	private static String CLOSEFILTERSPANEL="closefilterspanel";
	private static String OKANDCLOSEFILTERS="okandclosefilters";
	
	public  FiltersPanel(Videofile video, JFrame mainframe){
		this.mainframe=mainframe;
		this.movie=video;
		rb = ResourceBundle.getBundle("lang",LangTools.getDefinedLanguage(), LangTools.loadLanguagesPath());
		initGUI();
		setCloseWindowListener();
		loadExistingData();
	}
	
	  private void setCloseWindowListener(){
		  this.addWindowListener(new java.awt.event.WindowAdapter() {
			    @Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			    	setCloseFiltersPanelFireEvents();
			        dispose();
			    }
			});
	  }
	  
	    public void addpanelPropertyChangeListener(PropertyChangeListener listener) {
			this.changelst= new PropertyChangeSupport(this);
			this.changelst.addPropertyChangeListener(listener);
	     }
	  
	    private void setCloseFiltersPanelFireEvents(){
		   changelst.firePropertyChange("closefilterspanel",false,true);
	    }
	    
	    
	    private void loadExistingData(){
	    	FiltersInfoContainer filterscont= this.movie.getEncodingInfoContainer().getFilters();
	    	if(filterscont!=null){
	    		CropFilter crop = filterscont.getCrop();
	    		if(crop!=null)
	    			croppanel.fillCropPanelSettings(crop);
	    		
	    		ScalingFilter scale = filterscont.getScaling();
	    		if(scale!=null)
	    			scalepanel.fillScalePanelSettings(scale);
	    		
	    		DeinterlaceFilter deint = filterscont.getDeinterlace();
	    		if(deint!=null)
	    			deintpanel.setFilterPanelSettings(deint);
	    		
	    		RotateFilter rotate = filterscont.getRotate();
	    		if(rotate!=null)
	    			rotatepanel.setParameters(rotate);
	    		
	    	}
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
					jTabbedPanemain = new JTabbedPane();
					getContentPane().add(jTabbedPanemain, new GridBagConstraints(0, 0, 4, 5, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					{
						jPanelcrop = new JPanel();
						BorderLayout jPanelcropLayout = new BorderLayout();
						jPanelcrop.setLayout(jPanelcropLayout);
						jTabbedPanemain.addTab(LangTools.getResourceBundleWordLanguage(rb,"Crop","filters.crop"), null, jPanelcrop, null);
						{
							croppanel= new CropConfigPanel(mainframe);
							jScrollPanecrop = new JScrollPane(croppanel);
							jPanelcrop.add(jScrollPanecrop, BorderLayout.NORTH);
							if(movie!=null)
								croppanel.setVideoFile(movie);
						}
					}
					{
						jPanelscale = new JPanel();
						BorderLayout jPanelscaleLayout = new BorderLayout();
						jPanelscale.setLayout(jPanelscaleLayout);
						jTabbedPanemain.addTab(LangTools.getResourceBundleWordLanguage(rb,"Scale","filters.scale"), null, jPanelscale, null);
						{   
							scalepanel= new ScalingConfigPanel();
							jScrollPanescale = new JScrollPane(scalepanel);
							jPanelscale.add(jScrollPanescale, BorderLayout.NORTH);
						}
					}
					{
						jPaneldeinterlace = new JPanel();
						jTabbedPanemain.addTab(LangTools.getResourceBundleWordLanguage(rb,"Deinterlace","filters.deinterlace"), null, jPaneldeinterlace, null);
						{
							deintpanel= new DeinterlaceConfigPanel();
							deintpanel.setPreferredSize(new java.awt.Dimension(516, 202));
							deintpanel.setBorder(BorderFactory.createTitledBorder(null, LangTools.getResourceBundleWordLanguage(rb,"Filters","filters.tag"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION));
							jScrollPanedeint = new JScrollPane(deintpanel);
							jPaneldeinterlace.add(jScrollPanedeint, BorderLayout.NORTH);
						}
					}
					{
						jPanelrotate = new JPanel();
						GridLayout jPanelrotateLayout = new GridLayout(1, 1);
						jPanelrotateLayout.setColumns(1);
						jPanelrotateLayout.setHgap(5);
						jPanelrotateLayout.setVgap(5);
						jPanelrotate.setLayout(jPanelrotateLayout);
						jTabbedPanemain.addTab(LangTools.getResourceBundleWordLanguage(rb,"Rotate","filters.rotate"), null, jPanelrotate, null);
						{
							rotatepanel= new RotateConfigPanel();
							jScrollPanerotate = new JScrollPane(rotatepanel);
							jPanelrotate.add(jScrollPanerotate, BorderLayout.NORTH);
						}

					}
				}
				{
					jButtonok = new JButton();
					getContentPane().add(jButtonok, new GridBagConstraints(2, 5, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jButtonok.setText(LangTools.getResourceBundleWordLanguage(rb,"Ok","general.ok"));
					jButtonok.setActionCommand(OKANDCLOSEFILTERS);
					jButtonok.addActionListener(this);
				}
				{
					jButtoncancel = new JButton();
					getContentPane().add(jButtoncancel, new GridBagConstraints(0, 5, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jButtoncancel.setText(LangTools.getResourceBundleWordLanguage(rb,"Cancel","general.cancel"));
					jButtoncancel.setActionCommand(CLOSEFILTERSPANEL);
					jButtoncancel.addActionListener(this);
				}
			}
			{
				this.setSize(548, 343);
			}
		} catch(Exception e) {
		    Logger.error(e);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals(CLOSEFILTERSPANEL)){
			setCloseFiltersPanelFireEvents();
			this.dispose();
		}
		else if(cmd.equals(OKANDCLOSEFILTERS)){
			setFiltersToVideoEncInfoContainer();
			setCloseFiltersPanelFireEvents();
			this.dispose();
		}
		
	}
	
	
	private void setFiltersToVideoEncInfoContainer(){
		CropFilter crop = croppanel.getFilter();
		ScalingFilter scale = scalepanel.getFilter();
		DeinterlaceFilter deinterlace = deintpanel.getFilter();
		RotateFilter rotate = rotatepanel.getFilter();

		
		FiltersInfoContainer filterscont= this.movie.getEncodingInfoContainer().getFilters();
		if(filterscont!=null){
			filterscont.setCrop(crop);
			filterscont.setScaling(scale);
			filterscont.setDeinterlace(deinterlace);
			filterscont.setRotate(rotate);
		
		}
		else{
			FiltersInfoContainer newfilterscont = new FiltersInfoContainer();
			  newfilterscont.setCrop(crop);
			  newfilterscont.setScaling(scale);
			  newfilterscont.setDeinterlace(deinterlace);
			  newfilterscont.setRotate(rotate);
			this.movie.getEncodingInfoContainer().setFilters(newfilterscont);
		}
		
		
	}
	


}
