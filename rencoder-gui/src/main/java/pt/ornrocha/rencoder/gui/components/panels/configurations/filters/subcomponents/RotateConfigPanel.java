package pt.ornrocha.rencoder.gui.components.panels.configurations.filters.subcomponents;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import org.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.filters.RotateFilter;
import pt.ornrocha.rencoder.gui.components.panels.auxiliar.CopyPasteJTextField;
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
public class RotateConfigPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JToggleButton jToggleButtonactivate;
	private JCheckBox jCheckBox180degr;
	private JCheckBox jCheckBox90degcounter;
	private JCheckBox jCheckBox90clock;
	
	private static String ACTIVEDEACTIVE="activedeactive";
	private static String CHECKBOXACTION="checkboxaction";
	private static String CLOCK90="clock90";
	private static String CLOCKCONT90="clockcont90";
	private CopyPasteJTextField jTextFieldcustompi;
	private JCheckBox jCheckBoxcustompi;
	private static String CLOCK180="clock180";
	private ResourceBundle rb;
	
	public RotateConfigPanel(){
		rb = ResourceBundle.getBundle("lang",LangTools.getDefinedLanguage(), LangTools.loadLanguagesPath());
		initGUI();
	}
	
	public void setParameters(RotateFilter filter){
		if(filter!=null){
			jToggleButtonactivate.setSelected(true);
			setactivebutton();
			if(filter.getAction()==0){
				jCheckBox90clock.setSelected(true);
				setCheckBoxCLOCK90Action();
			}
			else if(filter.getAction()==1){
				jCheckBox90degcounter.setSelected(true);
				setCheckBoxCLOCKCONT90Action();
			}
			else if(filter.getAction()==2){
				jCheckBox180degr.setSelected(true);
				setCheckBoxCLOCKCLOCK180Action();
			}
			else if(filter.getAction()==3){
				jCheckBoxcustompi.setSelected(true);
				setCheckBoxPIAction();
				jTextFieldcustompi.setText(filter.getRotatecmd());
			}
		}
	}

	private void initGUI() {
		try {
			{
				GridBagLayout thisLayout = new GridBagLayout();
				this.setPreferredSize(new java.awt.Dimension(384, 167));
				thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1};
				thisLayout.rowHeights = new int[] {7, 7, 7, 7, 7};
				thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
				thisLayout.columnWidths = new int[] {7, 7, 7, 7};
				this.setLayout(thisLayout);
				{
					jToggleButtonactivate = new JToggleButton();
					this.add(jToggleButtonactivate, new GridBagConstraints(0, 0, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jToggleButtonactivate.setText(LangTools.getResourceBundleWordLanguage(rb,"Activate","filters.activate"));
					jToggleButtonactivate.setActionCommand(ACTIVEDEACTIVE);
					jToggleButtonactivate.addActionListener(this);
				}
				{
					jCheckBox90clock = new JCheckBox();
					this.add(jCheckBox90clock, new GridBagConstraints(0, 1, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 40, 0, 0), 0, 0));
					jCheckBox90clock.setText(LangTools.getResourceBundleWordLanguage(rb,"90 degrees clockwise","filters.degrees90clockwise"));
					jCheckBox90clock.setEnabled(false);
					jCheckBox90clock.setActionCommand(CLOCK90);
					jCheckBox90clock.addActionListener(this);
				}
				{
					jCheckBox90degcounter = new JCheckBox();
					this.add(jCheckBox90degcounter, new GridBagConstraints(0, 2, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 40, 0, 0), 0, 0));
					jCheckBox90degcounter.setText(LangTools.getResourceBundleWordLanguage(rb,"90 degrees counterclockwise","filters.degrees90counterclockwise"));
					jCheckBox90degcounter.setEnabled(false);
					jCheckBox90degcounter.setActionCommand(CLOCKCONT90);
					jCheckBox90degcounter.addActionListener(this);
				}
				{
					jCheckBox180degr = new JCheckBox();
					this.add(jCheckBox180degr, new GridBagConstraints(0, 3, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 40, 0, 0), 0, 0));
					jCheckBox180degr.setText(LangTools.getResourceBundleWordLanguage(rb,"180 degrees","filters.degrees180"));
					jCheckBox180degr.setEnabled(false);
					jCheckBox180degr.setActionCommand(CLOCK180);
					jCheckBox180degr.addActionListener(this);
				}
				{
					jCheckBoxcustompi = new JCheckBox();
					this.add(jCheckBoxcustompi, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 40, 0, 0), 0, 0));
					jCheckBoxcustompi.setText(LangTools.getResourceBundleWordLanguage(rb,"Custom rotate PI rule","filters.custompirule"));
					jCheckBoxcustompi.setEnabled(false);
					jCheckBoxcustompi.setActionCommand(CHECKBOXACTION);
					jCheckBoxcustompi.addActionListener(this);
				}
				{
					jTextFieldcustompi = new CopyPasteJTextField();
					this.add(jTextFieldcustompi, new GridBagConstraints(1, 4, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					jTextFieldcustompi.setText(" ");
					jTextFieldcustompi.setEnabled(false);
					
				}
			}
		} catch(Exception e) {
		    Logger.error(e);
		}
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals(ACTIVEDEACTIVE)){
			setactivebutton();
		}
		else if(cmd.equals(CLOCK90))
			setCheckBoxCLOCK90Action();
		else if(cmd.equals(CLOCKCONT90))
			setCheckBoxCLOCKCONT90Action();
		else if(cmd.equals(CLOCK180))
			setCheckBoxCLOCKCLOCK180Action();
		else if(cmd.equals(CHECKBOXACTION))
			setCheckBoxPIAction();
		
	}
	
	private void setactivebutton(){
		if(jToggleButtonactivate.isSelected()){
			jToggleButtonactivate.setText(LangTools.getResourceBundleWordLanguage(rb,"Deactivate","filters.deactivate"));
			jCheckBox90clock.setEnabled(true);
			jCheckBox90degcounter.setEnabled(true);
			jCheckBox180degr.setEnabled(true);
			jCheckBoxcustompi.setEnabled(true);
		}
		else{
			jToggleButtonactivate.setText(LangTools.getResourceBundleWordLanguage(rb,"Activate","filters.activate"));
			jCheckBox90clock.setEnabled(false);
			jCheckBox90degcounter.setEnabled(false);
			jCheckBox180degr.setEnabled(false);
			jCheckBoxcustompi.setEnabled(false);
			jTextFieldcustompi.setEnabled(false);
		}
	}
	
	
	
	private void setCheckBoxCLOCK90Action(){
		if(jCheckBox90clock.isSelected()){
			jCheckBox90degcounter.setSelected(false);
			jCheckBox180degr.setSelected(false);
			jCheckBoxcustompi.setSelected(false);
			jTextFieldcustompi.setEnabled(false);
	     }
	 }
	
	private void setCheckBoxCLOCKCONT90Action(){
		if(jCheckBox90degcounter.isSelected()){
			jCheckBox90clock.setSelected(false);
			jCheckBox180degr.setSelected(false);
			jCheckBoxcustompi.setSelected(false);
			jTextFieldcustompi.setEnabled(false);
		}
	}
	
	private void setCheckBoxCLOCKCLOCK180Action(){
		if(jCheckBox180degr.isSelected()){
			jCheckBox90clock.setSelected(false);
			jCheckBox90degcounter.setSelected(false);
			jCheckBoxcustompi.setSelected(false);
			jTextFieldcustompi.setEnabled(false);
		}
	}
	
	private void setCheckBoxPIAction(){
		if(jCheckBoxcustompi.isSelected()){
			jCheckBox90clock.setSelected(false);
			jCheckBox90degcounter.setSelected(false);
			jCheckBox180degr.setSelected(false);
			jTextFieldcustompi.setEnabled(true);
		}
	}
	
	public RotateFilter getFilter(){
		
	  if(jToggleButtonactivate.isSelected()){	
		if(jCheckBox90clock.isSelected())
			return new RotateFilter(0);
		else if(jCheckBox90degcounter.isSelected())
			return new RotateFilter(1);
		else if(jCheckBox180degr.isSelected())
			return new RotateFilter(2);
		else if(jCheckBoxcustompi.isSelected()){
			if(!jTextFieldcustompi.getText().isEmpty()){
				return new RotateFilter(jTextFieldcustompi.getText());
			}	
		  }
	 
		return null;
	   }
	  
	  else
		return null;
	 }
	

}
