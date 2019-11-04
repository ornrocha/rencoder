package pt.ornrocha.rencoder.gui.components.panels.configurations.filters.subcomponents;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import pt.ornrocha.rencoder.ffmpegWrapper.filters.ScalingFilter;
import pt.ornrocha.rencoder.helpers.lang.LangTools;

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
public class ScalingConfigPanel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JCheckBox jCheckBoxreduce;
	private JCheckBox jCheckBoxscale;
	private JSpinner jSpinnerheight;
	private JSpinner jSpinnerwidth;
	private JSpinner jSpinnerreduce;
	private JCheckBox jCheckBoxautoheight;
	private JCheckBox jCheckBoxautowidth;
	private JLabel jLabelauto;
	private JLabel jLabelheight;
	private JLabel jLabelwidth;
	
	private static String AUTOMATICSCALE="autoscale";
	private static String MANUALSCALE="manualcale";
	private static String AUTOWIDTH="autowidth";
	private static String AUTOHEIGHT="autoheight";
	
	private ResourceBundle rb;
	
	
	public ScalingConfigPanel(){
		rb = ResourceBundle.getBundle("lang",LangTools.getDefinedLanguage(), LangTools.loadLanguagesPath());
		initGUI();
		initComp();
	}
	
	private void initComp(){
		jCheckBoxreduce.setSelected(false);
		jCheckBoxscale.setSelected(false);
		jSpinnerwidth.setEnabled(false);
		jSpinnerheight.setEnabled(false);
		jCheckBoxautowidth.setEnabled(false);
		jCheckBoxautoheight.setEnabled(false);
		jSpinnerreduce.setEnabled(false);
		
		SpinnerModel modelwidth= new SpinnerNumberModel(0,0,10000,1);
		jSpinnerwidth.setModel(modelwidth);
		
		SpinnerModel modelheight= new SpinnerNumberModel(0,0,10000,1);
		jSpinnerheight.setModel(modelheight);
		
	}
	
	public void fillScalePanelSettings(ScalingFilter scale){
		if(scale!=null){
			if(scale.getAutoScaleValue()>1){
				jCheckBoxreduce.setSelected(true);
				setautoscaleCMD();
				jSpinnerreduce.setValue(scale.getAutoScaleValue());
			}
			else if(scale.getWidth()!=-1 || scale.getHeight()!=-1){
				jCheckBoxscale.setSelected(true);
				setmanualscaleCMD();
				if(scale.getWidth()>-1){
					jSpinnerwidth.setValue(scale.getWidth());
					jCheckBoxautowidth.setSelected(false);
				}
				else if(scale.getWidth()==-1){
					jCheckBoxautowidth.setSelected(true);
				}
				
				if(scale.getHeight()>-1){
					jSpinnerheight.setValue(scale.getHeight());
					jCheckBoxautoheight.setSelected(false);
				}
				else if(scale.getHeight()==-1){
					jCheckBoxautoheight.setSelected(true);
				}
			}
			
		}
	}

	public void initGUI() {
		{
			GridBagLayout thisLayout = new GridBagLayout();
			this.setPreferredSize(new java.awt.Dimension(429, 119));
			thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
			thisLayout.rowHeights = new int[] {7, 7, 7, 7};
			thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
			thisLayout.columnWidths = new int[] {7, 7, 7, 7};
			this.setLayout(thisLayout);
			this.setBorder(BorderFactory.createTitledBorder(LangTools.getResourceBundleWordLanguage(rb,"Scaling Video","filters.scalingvideo")));
			{
				jLabelwidth = new JLabel();
				this.add(jLabelwidth, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jLabelwidth.setText(LangTools.getResourceBundleWordLanguage(rb,"Width","filters.width"));
			}
			{
				jLabelheight = new JLabel();
				this.add(jLabelheight, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jLabelheight.setText(LangTools.getResourceBundleWordLanguage(rb,"Height","filters.height"));
			}
			{
				jLabelauto = new JLabel();
				this.add(jLabelauto, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jLabelauto.setText(LangTools.getResourceBundleWordLanguage(rb,"Automatic","filters.automatic"));
			}
			{
				jCheckBoxautowidth = new JCheckBox();
				this.add(jCheckBoxautowidth, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jCheckBoxautowidth.setText(LangTools.getResourceBundleWordLanguage(rb,"Width","filters.width"));
				jCheckBoxautowidth.setActionCommand(AUTOWIDTH);
				jCheckBoxautowidth.addActionListener(this);
			
			}
			{
				jCheckBoxautoheight = new JCheckBox();
				this.add(jCheckBoxautoheight, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jCheckBoxautoheight.setText(LangTools.getResourceBundleWordLanguage(rb,"Height","filters.height"));
				jCheckBoxautoheight.setActionCommand(AUTOHEIGHT);
				jCheckBoxautoheight.addActionListener(this);
			
			}
			{
				SpinnerModel model1 = new SpinnerNumberModel(2,2,10,1);
				jSpinnerreduce = new JSpinner();
				this.add(jSpinnerreduce, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 16, 0, 0), 0, 0));
				jSpinnerreduce.setModel(model1);
			}
			{
				
				jSpinnerwidth = new JSpinner();
				this.add(jSpinnerwidth, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
				
			}
			{
				
				jSpinnerheight = new JSpinner();
				this.add(jSpinnerheight, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
				
				
				jCheckBoxreduce = new JCheckBox();
				jCheckBoxreduce.setText(LangTools.getResourceBundleWordLanguage(rb,"Reduce to n times of the original","filters.reduceoriginal"));
				this.add(jCheckBoxreduce, new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 20, 0, 0), 0, 0));
				jCheckBoxreduce.setActionCommand(AUTOMATICSCALE);
				jCheckBoxreduce.addActionListener(this);
				
				jCheckBoxscale = new JCheckBox();
				jCheckBoxscale.setText(LangTools.getResourceBundleWordLanguage(rb,"Scale","filters.scale"));
				this.add(jCheckBoxscale, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 20, 0, 0), 0, 0));
				jCheckBoxscale.setActionCommand(MANUALSCALE);
				jCheckBoxscale.addActionListener(this);
			}
		   this.setSize(429, 119);
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals(AUTOMATICSCALE)){
			setautoscaleCMD();
		}
		else if(cmd.equals(MANUALSCALE)){
			setmanualscaleCMD();
		}
		else if(cmd.equals(AUTOWIDTH)){
			if(jCheckBoxscale.isSelected()){
				if(jCheckBoxautowidth.isSelected()){
				    jSpinnerwidth.setEnabled(false);
				}
				else
					jSpinnerwidth.setEnabled(true);
				jSpinnerheight.setEnabled(true);
				jCheckBoxautoheight.setSelected(false);
			}
		}
		else if(cmd.equals(AUTOHEIGHT)){
			if(jCheckBoxscale.isSelected()){
				if(jCheckBoxautoheight.isSelected())
					jSpinnerheight.setEnabled(false);
				else
					jSpinnerheight.setEnabled(true);
				
				jSpinnerwidth.setEnabled(true);
				jCheckBoxautowidth.setSelected(false);
			}
			
		}
		
	}
	
	private void setautoscaleCMD(){
		if(jCheckBoxreduce.isSelected()){
		   jCheckBoxscale.setSelected(false);
		   jSpinnerwidth.setEnabled(false);
		   jSpinnerheight.setEnabled(false);
		   jCheckBoxautowidth.setSelected(false);
		   jCheckBoxautoheight.setSelected(false);
		   jCheckBoxautowidth.setEnabled(false);
		   jCheckBoxautoheight.setEnabled(false);
		   jSpinnerreduce.setEnabled(true);
		}
		else{
			   jSpinnerreduce.setEnabled(false);
		}
	}
	private void setmanualscaleCMD(){
		if(jCheckBoxscale.isSelected()){
			jCheckBoxreduce.setSelected(false);
		    jSpinnerwidth.setEnabled(true);
		    jSpinnerheight.setEnabled(true);
		    jCheckBoxautowidth.setEnabled(true);
		    jCheckBoxautoheight.setEnabled(true);
		    jSpinnerreduce.setEnabled(false);
		}
		else{
			jCheckBoxreduce.setSelected(false);
			jSpinnerwidth.setEnabled(false);
			jSpinnerheight.setEnabled(false);
			jCheckBoxautowidth.setEnabled(false);
			jCheckBoxautowidth.setSelected(false);
			jCheckBoxautoheight.setEnabled(false);
			jCheckBoxautoheight.setSelected(false);
			jSpinnerreduce.setEnabled(false);
		}
		
	}
	
	public ScalingFilter getFilter(){
		ScalingFilter filter=null;
		if(jCheckBoxscale.isSelected()){
			if(!jCheckBoxautowidth.isSelected() && !jCheckBoxautoheight.isSelected())
				return new ScalingFilter((int)jSpinnerwidth.getValue(),(int)jSpinnerheight.getValue());
			else if(!jCheckBoxautowidth.isSelected() && jCheckBoxautoheight.isSelected()){
				filter=new ScalingFilter();
				filter.setMaxWidth((int)jSpinnerwidth.getValue());
				return filter;
			}
			else if(jCheckBoxautowidth.isSelected() && !jCheckBoxautoheight.isSelected()){
				filter=new ScalingFilter();
				filter.setMaxHeight((int)jSpinnerheight.getValue());
				return filter;
			}
			else
				return null;
		}
		else if(jCheckBoxreduce.isSelected()){
			filter= new ScalingFilter();
			filter.scaleToXSizeOfOriginal((int)jSpinnerreduce.getValue());
			return filter;
		}
		else 
			return null;
	}

	/*@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
	      if (!((c >= '0') && (c <= '9') ||
	         (c == KeyEvent.VK_BACK_SPACE) ||
	         (c == KeyEvent.VK_DELETE))) {
	        getToolkit().beep();
	        e.consume();
	      }
	}*/


	
	public static void main(String[]args){
		JFrame f = new JFrame( );
		f.setContentPane(new ScalingConfigPanel());
		 f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	    f.pack();
	    f.setVisible(true);
	}
	
	

}
