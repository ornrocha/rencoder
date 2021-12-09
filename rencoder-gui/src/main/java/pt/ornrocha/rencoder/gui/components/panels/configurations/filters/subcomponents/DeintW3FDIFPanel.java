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

import org.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.filters.deinterlace.DeinterlaceFilter;
import pt.ornrocha.rencoder.ffmpegWrapper.filters.deinterlace.DeinterlaceW3FDIF;


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
public class DeintW3FDIFPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jPanelfilter;
	private JComboBox<String> jComboBox1;
	private JComboBox<String> jComboBoxdeint;
	private JPanel jPaneldeint;
	
	public DeintW3FDIFPanel(){
		initGUI();
	}
	
	public void setParameters(DeinterlaceW3FDIF filter){
		jComboBox1.setSelectedItem(filter.getFilter());
		jComboBoxdeint.setSelectedItem(filter.getDeint());
	}

	private void initGUI() {
		try {
			{
				GridBagLayout thisLayout = new GridBagLayout();
				this.setPreferredSize(new java.awt.Dimension(338, 156));
				thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				thisLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7};
				thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
				thisLayout.columnWidths = new int[] {7, 7, 7, 7};
				this.setLayout(thisLayout);
				{
					jPanelfilter = new JPanel();
					GridLayout jPanelfilterLayout = new GridLayout(1, 1);
					jPanelfilterLayout.setColumns(1);
					jPanelfilterLayout.setHgap(5);
					jPanelfilterLayout.setVgap(5);
					jPanelfilter.setLayout(jPanelfilterLayout);
					this.add(jPanelfilter, new GridBagConstraints(0, 1, 4, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPanelfilter.setBorder(BorderFactory.createTitledBorder("Filter"));
					{
						ComboBoxModel<String> jComboBox1Model = 
								new DefaultComboBoxModel<String>(
										new String[] { "simple", "complex" });
						jComboBox1 = new JComboBox<String>();
						jPanelfilter.add(jComboBox1);
						jComboBox1.setModel(jComboBox1Model);
					}
				}
				{
					jPaneldeint = new JPanel();
					GridLayout jPaneldeintLayout = new GridLayout(1, 1);
					jPaneldeintLayout.setColumns(1);
					jPaneldeintLayout.setHgap(5);
					jPaneldeintLayout.setVgap(5);
					jPaneldeint.setLayout(jPaneldeintLayout);
					this.add(jPaneldeint, new GridBagConstraints(0, 3, 4, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPaneldeint.setBorder(BorderFactory.createTitledBorder("Frames to deinterlace"));
					{
						ComboBoxModel<String> jComboBoxdeintModel = 
								new DefaultComboBoxModel<String>(
										new String[] { "all", "interlaced" });
						jComboBoxdeint = new JComboBox<String>();
						jPaneldeint.add(jComboBoxdeint);
						jComboBoxdeint.setModel(jComboBoxdeintModel);
					}
				}
			}
		} catch(Exception e) {
		    Logger.error(e);
		}
	}
	
	public DeinterlaceFilter getFilter(){
		return new DeinterlaceW3FDIF((String)jComboBox1.getSelectedItem(), (String)jComboBoxdeint.getSelectedItem());
	}

}
