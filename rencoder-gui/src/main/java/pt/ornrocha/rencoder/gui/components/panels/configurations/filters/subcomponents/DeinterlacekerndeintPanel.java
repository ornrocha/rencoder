package pt.ornrocha.rencoder.gui.components.panels.configurations.filters.subcomponents;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.filters.deinterlace.DeinterlaceFilter;
import pt.ornrocha.rencoder.ffmpegWrapper.filters.deinterlace.DeinterlaceKERNDEINT;

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
public class DeinterlacekerndeintPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JPanel jPanel1;
	private JComboBox<String> jComboBoxtwoway;
	private JComboBox<String> jComboBoxsharp;
	private JPanel jPaneltwoway;
	private JPanel jPanelsharp;
	private JComboBox<String> jComboBoxorder;
	private JPanel jPanelorder;
	private JComboBox<String> jComboBoxmap;
	private JPanel jPanelmap;
	private JSpinner jSpinnerthres;
	
	public DeinterlacekerndeintPanel(){
		initGUI();
		initComp();
	}
	
	private void initComp(){
		SpinnerNumberModel threshold = new SpinnerNumberModel(10, 0, 255, 1);
		jSpinnerthres.setModel(threshold);
	}
	
	public void setParameters(DeinterlaceKERNDEINT filter){
		jSpinnerthres.setValue(filter.getThresh());
		jComboBoxmap.setSelectedIndex(filter.getMap());
		jComboBoxorder.setSelectedIndex(filter.getOrder());
		jComboBoxsharp.setSelectedIndex(filter.getSharp());
		jComboBoxtwoway.setSelectedIndex(filter.getTwoway());
	}

	private void initGUI() {
		try {
			{
				GridBagLayout thisLayout = new GridBagLayout();
				this.setPreferredSize(new java.awt.Dimension(412, 210));
				thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				thisLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7};
				thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
				thisLayout.columnWidths = new int[] {7, 7, 7, 7};
				this.setLayout(thisLayout);
				{
					jPanel1 = new JPanel();
					GridLayout jPanel1Layout = new GridLayout(1, 1);
					jPanel1Layout.setColumns(1);
					jPanel1Layout.setHgap(5);
					jPanel1Layout.setVgap(5);
					jPanel1.setLayout(jPanel1Layout);
					this.add(jPanel1, new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					jPanel1.setBorder(BorderFactory.createTitledBorder("Threshold"));
					{

						jSpinnerthres = new JSpinner();
						jPanel1.add(jSpinnerthres);
						
					}
				}
				{
					jPanelmap = new JPanel();
					GridLayout jPanelmapLayout = new GridLayout(1, 1);
					jPanelmapLayout.setColumns(1);
					jPanelmapLayout.setHgap(5);
					jPanelmapLayout.setVgap(5);
					jPanelmap.setLayout(jPanelmapLayout);
					this.add(jPanelmap, new GridBagConstraints(2, 4, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPanelmap.setBorder(BorderFactory.createTitledBorder("Map"));
					{
						ComboBoxModel<String> jComboBoxmapModel = 
								new DefaultComboBoxModel<String>(
										new String[] { "off", "on" });
						jComboBoxmap = new JComboBox<String>();
						jPanelmap.add(jComboBoxmap);
						jComboBoxmap.setModel(jComboBoxmapModel);
					}
				}
				{
					jPanelorder = new JPanel();
					GridLayout jPanelorderLayout = new GridLayout(1, 1);
					jPanelorderLayout.setColumns(1);
					jPanelorderLayout.setHgap(5);
					jPanelorderLayout.setVgap(5);
					jPanelorder.setLayout(jPanelorderLayout);
					this.add(jPanelorder, new GridBagConstraints(0, 4, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPanelorder.setBorder(BorderFactory.createTitledBorder("Order"));
					{
						ComboBoxModel<String> jComboBoxorderModel = 
								new DefaultComboBoxModel<String>(
										new String[] { "off", "on" });
						jComboBoxorder = new JComboBox<String>();
						jPanelorder.add(jComboBoxorder);
						jComboBoxorder.setModel(jComboBoxorderModel);
					}
				}
				{
					jPanelsharp = new JPanel();
					GridLayout jPanelsharpLayout = new GridLayout(1, 1);
					jPanelsharpLayout.setColumns(1);
					jPanelsharpLayout.setHgap(5);
					jPanelsharpLayout.setVgap(5);
					jPanelsharp.setLayout(jPanelsharpLayout);
					this.add(jPanelsharp, new GridBagConstraints(0, 5, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPanelsharp.setBorder(BorderFactory.createTitledBorder("Sharp"));
					{
						ComboBoxModel<String> jComboBoxsharpModel = 
								new DefaultComboBoxModel<String>(
										new String[] { "off", "on" });
						jComboBoxsharp = new JComboBox<String>();
						jPanelsharp.add(jComboBoxsharp);
						jComboBoxsharp.setModel(jComboBoxsharpModel);
					}
				}
				{
					jPaneltwoway = new JPanel();
					GridLayout jPaneltwowayLayout = new GridLayout(1, 1);
					jPaneltwowayLayout.setColumns(1);
					jPaneltwowayLayout.setHgap(5);
					jPaneltwowayLayout.setVgap(5);
					jPaneltwoway.setLayout(jPaneltwowayLayout);
					this.add(jPaneltwoway, new GridBagConstraints(2, 5, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPaneltwoway.setBorder(BorderFactory.createTitledBorder("Twoway"));
					{
						ComboBoxModel<String> jComboBoxtwowayModel = 
								new DefaultComboBoxModel<String>(
										new String[] { "off", "on" });
						jComboBoxtwoway = new JComboBox<String>();
						jPaneltwoway.add(jComboBoxtwoway);
						jComboBoxtwoway.setModel(jComboBoxtwowayModel);
					}
				}
			}
		} catch(Exception e) {
		    Logger.error(e);
		}
	}
	
	public DeinterlaceFilter getFilter(){
		return new DeinterlaceKERNDEINT((int) jSpinnerthres.getValue(), jComboBoxmap.getSelectedIndex(), jComboBoxorder.getSelectedIndex(), jComboBoxsharp.getSelectedIndex(), jComboBoxtwoway.getSelectedIndex());
	}

}
