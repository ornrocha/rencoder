package pt.ornrocha.rencoder.gui.components.panels.configurations.filters.subcomponents;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.filters.deinterlace.DeinterlaceFilter;
import pt.ornrocha.rencoder.ffmpegWrapper.filters.deinterlace.DeinterlaceKERNDEINT;
import pt.ornrocha.rencoder.ffmpegWrapper.filters.deinterlace.DeinterlaceW3FDIF;
import pt.ornrocha.rencoder.ffmpegWrapper.filters.deinterlace.DeinterlaceYADIF;


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
public class DeinterlaceConfigPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> jComboBoxfiltertype;
	private JPanel jPaneldeintfilters;
	private DeintYADIFPanel yadifpanel; 
	private DeintW3FDIFPanel w3fdipanel;
	private DeinterlacekerndeintPanel kerndeintpanel;
	private JPanel currentpanel=null;
	
	private static String CHANGEFILTER="changefilter";
	private static String EDITFILTER="editfilter";
	
	
	public DeinterlaceConfigPanel(){
		initGUI();
	}

	private void initGUI() {
		try {
			{
				GridBagLayout thisLayout = new GridBagLayout();
				this.setPreferredSize(new java.awt.Dimension(446, 206));
				thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				thisLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7, 7, 7};
				thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				thisLayout.columnWidths = new int[] {7, 7, 7, 7, 7, 7};
				this.setLayout(thisLayout);
				{
					ComboBoxModel<String> jComboBoxfiltertypeModel = 
							new DefaultComboBoxModel<String>(
									new String[] { "None","yet another deinterlacing filter (yadif)", "Weston 3 Field Deinterlacing Filter (w3fdif)", "Donald Graft’s adaptive kernel deinterling (kerndeint)" });
					jComboBoxfiltertype = new JComboBox<String>();
					this.add(jComboBoxfiltertype, new GridBagConstraints(0, 0, 6, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					jComboBoxfiltertype.setModel(jComboBoxfiltertypeModel);
					jComboBoxfiltertype.setActionCommand(CHANGEFILTER);
					jComboBoxfiltertype.addActionListener(this);
				}
				{
					jPaneldeintfilters = new JPanel();
					this.currentpanel=jPaneldeintfilters;
					this.add(jPaneldeintfilters, new GridBagConstraints(0, 3, 6, 6, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				}
			}
		} catch(Exception e) {
		    Logger.error(e);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals(CHANGEFILTER))
			changeFilterAction();
		//else if(cmd.equals(EDITFILTER))
		//	managefilters();
		
	}
	
	private void changeFilterAction(){
		if(jComboBoxfiltertype.getSelectedIndex()==0){
			changePanel(jPaneldeintfilters);			
		}
		else if(jComboBoxfiltertype.getSelectedIndex()==1){
			if(yadifpanel==null)
			  yadifpanel= new DeintYADIFPanel();
			changePanel(yadifpanel);
		}
		else if(jComboBoxfiltertype.getSelectedIndex()==2){
			if(w3fdipanel==null)
				w3fdipanel=new DeintW3FDIFPanel();
			changePanel(w3fdipanel);
		}
		else if (jComboBoxfiltertype.getSelectedIndex()==3){
			if(kerndeintpanel==null)
				kerndeintpanel=new DeinterlacekerndeintPanel();
			changePanel(kerndeintpanel);
		}
	}
	
	public void setFilterPanelSettings(DeinterlaceFilter filter){
		if(filter instanceof DeinterlaceYADIF){
			jComboBoxfiltertype.setSelectedIndex(1);
			changeFilterAction();
			yadifpanel.setParameters((DeinterlaceYADIF) filter);
		}
		else if(filter instanceof DeinterlaceW3FDIF){
			jComboBoxfiltertype.setSelectedIndex(2);
			changeFilterAction();
			w3fdipanel.setParameters((DeinterlaceW3FDIF) filter);
		}
		else if(filter instanceof DeinterlaceKERNDEINT){
			jComboBoxfiltertype.setSelectedIndex(3);
			changeFilterAction();
			kerndeintpanel.setParameters((DeinterlaceKERNDEINT) filter);
		}
		else{
			jComboBoxfiltertype.setSelectedIndex(0);
			changeFilterAction();
		}
	}
	
	
	private void changePanel(JPanel panel){
		this.remove(currentpanel);
		repaint();
		this.add(panel, new GridBagConstraints(0, 3, 6, 6, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		this.currentpanel=panel;
		//this.updateUI();
	    this.validate();
	    repaint();
	}
	

	
	
	
	public DeinterlaceFilter getFilter(){
		if(jComboBoxfiltertype.getSelectedIndex()==1)
		   return yadifpanel.getFilter();
		else if(jComboBoxfiltertype.getSelectedIndex()==2)
			return w3fdipanel.getFilter();
		else if(jComboBoxfiltertype.getSelectedIndex()==3)
			return kerndeintpanel.getFilter();
		else
		   return null;
	}

}
