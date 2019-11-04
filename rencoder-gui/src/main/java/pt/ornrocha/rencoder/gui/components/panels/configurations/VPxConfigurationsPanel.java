/*
 * Copyright 2014
 *
 * This is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. 
 * 
 * This code is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the 
 * GNU Public License for more details. 
 * 
 * You should have received a copy of the GNU Public License 
 * along with this code. If not, see http://www.gnu.org/licenses/ 
 * 
 * Created by Orlando Rocha
 */
package pt.ornrocha.rencoder.gui.components.panels.configurations;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.BoundedRangeModel;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.pmw.tinylog.Logger;

import eu.hansolo.custom.SteelCheckBox;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VP8QualityProfiles;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoVP8EncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;
import pt.ornrocha.rencoder.mediafiles.setfiles.foldersandfiles.ListFiles;

// TODO: Auto-generated Javadoc
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
public class VPxConfigurationsPanel extends JDialog implements ActionListener{


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The j labelcpuused. */
	private JLabel jLabelcpuused;
	
	/** The j labelmaxquant. */
	private JLabel jLabelmaxquant;
	
	/** The j button1. */
	private JButton jButton1;
	
	/** The j label1. */
	private JLabel jLabel1;
	
	/** The j buttonclose. */
	private JButton jButtonclose;
	
	/** The j sliderthreads. */
	private JSlider jSliderthreads;
	
	/** The j labelcputhreads. */
	private JLabel jLabelcputhreads;
	
	/** The j slidercpu. */
	private JSlider jSlidercpu;
	
	/** The j combo boxquality. */
	private JComboBox<VP8QualityProfiles> jComboBoxquality;
	
	/** The j labelthreads. */
	private JLabel jLabelthreads;
	
	/** The j labelminquant. */
	private JLabel jLabelminquant;
	
	/** The j labelquality. */
	private JLabel jLabelquality;
	
	/** The j spinnerminquant. */
	private JSpinner jSpinnerminquant;
	
	/** The j spinnermaxquant. */
	private JSpinner jSpinnermaxquant;
	
	/** The b range modelcpuused. */
	private  BoundedRangeModel bRangeModelcpuused;
	
	/** The b range modelcputheads. */
	private  BoundedRangeModel bRangeModelcputheads;
	
	
	/** The CLOSEV p8 confs. */
	private static String CLOSEVP8CONFS="closevp8confs";
	
	/** The SAVEANDCLOSEV p8 confs. */
	private static String SAVEANDCLOSEVP8CONFS="saveandclosevp8confs";
	
	/** The setquantitization. */
	private static String SETQUANTITIZATION="setquantitization";
	

	
	
	/** The infocont. */
	private IGeneralVideoEncInfoContainer infocont=null;

	/** The steel check box1. */
	private SteelCheckBox steelCheckBox1;
	
	/** The rb. */
	private ResourceBundle rb;
    
	
	private JFrame mainframe;
	/**
	 * Instantiates a new v p8 configurations panel.
	 *
	 * @param infocont the infocont
	 */
	public VPxConfigurationsPanel(IGeneralVideoEncInfoContainer infocont, JFrame mainframe){
		this.mainframe=mainframe;
		rb = ResourceBundle.getBundle("lang",LangTools.getDefinedLanguage(), LangTools.loadLanguagesPath());
		initGUI();
		this.setModal(true);
		//this.setAlwaysOnTop(true);
		InitializeComponents();
	    this.infocont=infocont;
		setInfoFromEncodingContainer();
		
	}
	
	/**
	 * Initialize components.
	 */
	private void InitializeComponents(){
		
	
		jComboBoxquality.setModel(new DefaultComboBoxModel<>(VP8QualityProfiles.values()));
		
		
		int cores = Runtime.getRuntime().availableProcessors();
		setJSliderSettings(jSliderthreads,bRangeModelcputheads,jLabelcputhreads, 1,0,1,cores,1);
		setJSliderSettings(jSlidercpu,bRangeModelcpuused,jLabel1, 3,0,0,5,1);
		
		SpinnerNumberModel modelmin = new SpinnerNumberModel(10, 4, 63, 1);
		jSpinnerminquant.setModel(modelmin);
		
		SpinnerNumberModel modelmax = new SpinnerNumberModel(42, 4, 63, 1);
		jSpinnermaxquant.setModel(modelmax);
		
		steelCheckBox1.setSelected(false);
		jSpinnerminquant.setEnabled(false);
		jSpinnermaxquant.setEnabled(false);
	}
	
	
	
	/**
	 * Sets the info from encoding container.
	 */
	private void setInfoFromEncodingContainer(){
		

		
		jSliderthreads.setValue(((VideoVP8EncodingInfoContainer)infocont).getCputhreads());
		jSlidercpu.setValue(((VideoVP8EncodingInfoContainer)infocont).getCpuUsed());
		
		int min=((VideoVP8EncodingInfoContainer)infocont).getMinquant();
		int max=((VideoVP8EncodingInfoContainer)infocont).getMaxquant();
	
	
		if(min!=-1 && max!=-1 && min<max){
			steelCheckBox1.setSelected(true);
			setQuantitizationAction();
			jSpinnerminquant.setValue(min);	
			jSpinnermaxquant.setValue(max);
			
		}

		jComboBoxquality.setSelectedItem(((VideoVP8EncodingInfoContainer)infocont).getQualityprofile());
		
	}
	
	
	
   /**
    * Sets the j slider settings.
    *
    * @param slider the slider
    * @param rangeModel the range model
    * @param slidervaluelabel the slidervaluelabel
    * @param initvalue the initvalue
    * @param extent the extent
    * @param min the min
    * @param max the max
    * @param MinorTickSpacing the minor tick spacing
    */
   private void setJSliderSettings(JSlider slider, BoundedRangeModel rangeModel, final JLabel slidervaluelabel, int initvalue, int extent, int min, int max, int MinorTickSpacing){
		
	    rangeModel = new DefaultBoundedRangeModel(initvalue, extent, min, max);
	    rangeModel.setValueIsAdjusting(false);

	    slider.setModel(rangeModel);
	   // slider.setMajorTickSpacing( max );
	    slider.setMinorTickSpacing( MinorTickSpacing );
	    slider.setPaintTicks( true );
		//jSliderquality.setSnapToTicks(true);
	    slider.setPaintLabels( true );
		
	    slidervaluelabel.setText(String.valueOf(initvalue));
		
		 Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
		    table.put (min, new JLabel(String.valueOf(min)));
		    table.put (max, new JLabel(String.valueOf(max)));
		    slider.setLabelTable (table);
		    
		    slider.addChangeListener(new ChangeListener() {
		        public void stateChanged(ChangeEvent evt) {
		          JSlider slider = (JSlider) evt.getSource();
		          if (!slider.getValueIsAdjusting()) {
		            int value = slider.getValue();
		            slidervaluelabel.setText(String.valueOf(value));
		          }
		        }
		      });    
		
		
	   }
	
	

	/**
	 * Inits the gui.
	 */
	private void initGUI() {
		try {
			{   GridBagLayout thisLayout = new GridBagLayout();
				thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				thisLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
				thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				
				thisLayout.columnWidths = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
				getContentPane().setLayout(thisLayout);
				this.setTitle(LangTools.getResourceBundleWordLanguage(rb,"VPx (8/9) codec settings","vpxgui.confpanel"));
				{
					jLabelquality = new JLabel();
					getContentPane().add(jLabelquality, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 10, 5, 5), 0, 0));
					jLabelquality.setText(LangTools.getResourceBundleWordLanguage(rb, "Quality","videocodec.quality"));
				}
				{
					jLabelcpuused = new JLabel();
					getContentPane().add(jLabelcpuused, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 10, 5, 5), 0, 0));
					jLabelcpuused.setText(LangTools.getResourceBundleWordLanguage(rb, "Used CPU","videocodec.cpuused"));
				}
				{
					jLabelminquant = new JLabel();
					getContentPane().add(jLabelminquant, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 10, 5, 5), 0, 0));
					jLabelminquant.setText(LangTools.getResourceBundleWordLanguage(rb, "Min. quantization","videocodec.minquant"));
				}
				{
					jLabelmaxquant = new JLabel();
					getContentPane().add(jLabelmaxquant, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 10, 5, 5), 0, 0));
					jLabelmaxquant.setText(LangTools.getResourceBundleWordLanguage(rb, "Max. quantization","videocodec.maxquant"));
				}
				{
					jLabelthreads = new JLabel();
					getContentPane().add(jLabelthreads, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 10, 5, 5), 0, 0));
					jLabelthreads.setText(LangTools.getResourceBundleWordLanguage(rb, "Threads","videocodec.threads"));
				}
				{
					
					jComboBoxquality = new JComboBox();
					getContentPane().add(jComboBoxquality, new GridBagConstraints(1, 1, 8, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));
					
				}
				{
					jSlidercpu = new JSlider();
					getContentPane().add(jSlidercpu, new GridBagConstraints(1, 2, 8, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));
				}
				{
					
					jSpinnermaxquant = new JSpinner();
					getContentPane().add(jSpinnermaxquant, new GridBagConstraints(1, 5, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));
					
				}
				{
					jSliderthreads = new JSlider();
					getContentPane().add(jSliderthreads, new GridBagConstraints(1, 6, 8, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));
				}
				{
					jButtonclose = new JButton();
					getContentPane().add(jButtonclose, new GridBagConstraints(0, 8, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));
					jButtonclose.setText(LangTools.getResourceBundleWordLanguage(rb, "Cancel","general.cancel"));
					jButtonclose.setActionCommand(CLOSEVP8CONFS);
					jButtonclose.addActionListener(this);
				}
				{
					jButton1 = new JButton();
					getContentPane().add(jButton1, new GridBagConstraints(2, 8, 8, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));
					jButton1.setText(LangTools.getResourceBundleWordLanguage(rb, "Save","general.save"));
					jButton1.setActionCommand(SAVEANDCLOSEVP8CONFS);
					jButton1.addActionListener(this);
				}
				{
					jLabelcputhreads = new JLabel();
					getContentPane().add(jLabelcputhreads, new GridBagConstraints(9, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));
					jLabelcputhreads.setText("1");
					jLabelcputhreads.setHorizontalAlignment(SwingConstants.CENTER);
					jLabelcputhreads.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				}
				{
					jLabel1 = new JLabel();
					getContentPane().add(jLabel1, new GridBagConstraints(9, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));
					jLabel1.setText("1");
					jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
					jLabel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				}
				{
					steelCheckBox1 = new SteelCheckBox();
					getContentPane().add(steelCheckBox1, new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 5, 5), 0, 0));
					steelCheckBox1.setText(LangTools.getResourceBundleWordLanguage(rb, "Use quantization","videocodec.usequantitization"));
					steelCheckBox1.setActionCommand(SETQUANTITIZATION);
					steelCheckBox1.addActionListener(this);
					
				}
				{
					
					jSpinnerminquant = new JSpinner();
					getContentPane().add(jSpinnerminquant, new GridBagConstraints(1, 4, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));

				}

			}
			{
				this.setSize(419, 328);
			}
		} catch(Exception e) {
		    Logger.error(e);
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		
		if (action.equals(CLOSEVP8CONFS)){
			this.dispose();	
		}
		else if(action.equals(SETQUANTITIZATION))
			setQuantitizationAction();
		else if(action.equals(SAVEANDCLOSEVP8CONFS)){
			if(validQuantitizationvalues()){
			saveChoosenOptionsToContainer();
			this.dispose();
			}
		}
	}
	
	

	/**
	 * Sets the quantitization action.
	 */
	private void setQuantitizationAction(){
		if(steelCheckBox1.isSelected()){
			jSpinnerminquant.setEnabled(true);
			jSpinnermaxquant.setEnabled(true);
			steelCheckBox1.setColored(true);
		}
		else{
			jSpinnerminquant.setEnabled(false);
			jSpinnermaxquant.setEnabled(false);
		}
	}
	
	
	/**
	 * Save choosen options to container.
	 */
	private void saveChoosenOptionsToContainer(){
		
		
		((VideoVP8EncodingInfoContainer)infocont).setCputhreads(jSliderthreads.getValue());
		((VideoVP8EncodingInfoContainer)infocont).setCpuUsed(jSlidercpu.getValue());
		
		if(steelCheckBox1.isSelected()){
		    ((VideoVP8EncodingInfoContainer)infocont).setMinquant((int) jSpinnerminquant.getValue());
		    ((VideoVP8EncodingInfoContainer)infocont).setMaxquant((int) jSpinnermaxquant.getValue());
		}
		else{
			((VideoVP8EncodingInfoContainer)infocont).setMinquant(-1);
	        ((VideoVP8EncodingInfoContainer)infocont).setMaxquant(-1);
		}

       ((VideoVP8EncodingInfoContainer)infocont).setQualityprofile((VP8QualityProfiles) jComboBoxquality.getSelectedItem());
      
	}
	
	/**
	 * Valid quantitizationvalues.
	 *
	 * @return true, if successful
	 */
	private boolean validQuantitizationvalues(){
		
		boolean valid=true;
		
		if(steelCheckBox1.isSelected()){
		    int min =(int) jSpinnerminquant.getValue();	
		    int max = (int) jSpinnermaxquant.getValue();
		    
		    if(min > max){
		    	valid=false;
		    	String warn="Please de min quant. must be lower than max quant.";
		    	//this.warnpan= new WarnDialogPanel(LangTools.getResourceBundleWordLanguage(rb,warn,"vpxgui.warn"), this);
		    	 JOptionPane.showMessageDialog(mainframe,
		    			    LangTools.getResourceBundleWordLanguage(rb,warn,"vpxgui.warn"),
							LangTools.getResourceBundleWordLanguage(rb,"Warning","warngui.tag"),
						    JOptionPane.INFORMATION_MESSAGE,
						    new ImageIcon(ListFiles.getIconAbsolutePath("icons/warning64x64.png")));
		    }
		}
		return valid;
	}
	
	
}
