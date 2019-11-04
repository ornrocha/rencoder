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

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.filters.deinterlace.DeinterlaceFilter;
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
public class DeintYADIFPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jPaneldeint;
	private JPanel jPanelparity;
	private JPanel jPanelmode;
	private JComboBox<String> jComboBoxmode;
	private JComboBox<String> jComboBoxdeint;
	private JComboBox<String> jComboBoxparity;
	
	
	public DeintYADIFPanel(){
		initGUI();
	}
	
	public void setParameters(DeinterlaceYADIF filter){
		jComboBoxmode.setSelectedIndex(filter.getMode());
		jComboBoxparity.setSelectedIndex(filter.getParity());
		jComboBoxdeint.setSelectedIndex(filter.getDeint());
	}

	private void initGUI() {
		try {
			{
				GridBagLayout thisLayout = new GridBagLayout();
				this.setPreferredSize(new java.awt.Dimension(343, 131));
				thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				thisLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7};
				thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
				thisLayout.columnWidths = new int[] {7, 7, 7, 7};
				this.setLayout(thisLayout);
				{
					jPanelmode = new JPanel();
					GridLayout jPanelmodeLayout = new GridLayout(1, 1);
					jPanelmodeLayout.setColumns(1);
					jPanelmodeLayout.setHgap(5);
					jPanelmodeLayout.setVgap(5);
					jPanelmode.setLayout(jPanelmodeLayout);
					this.add(jPanelmode, new GridBagConstraints(0, 1, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPanelmode.setBorder(BorderFactory.createTitledBorder("Mode"));
					{
						ComboBoxModel<String> jComboBoxmodeModel = 
								new DefaultComboBoxModel<String>(
										new String[] { "send frame", "send field" , "send frame nospatial" , "send field nospatial" });
						jComboBoxmode = new JComboBox<String>();
						jPanelmode.add(jComboBoxmode);
						jComboBoxmode.setModel(jComboBoxmodeModel);
					}
				}
				{
					jPanelparity = new JPanel();
					GridLayout jPanelparityLayout = new GridLayout(1, 1);
					jPanelparityLayout.setColumns(1);
					jPanelparityLayout.setHgap(5);
					jPanelparityLayout.setVgap(5);
					jPanelparity.setLayout(jPanelparityLayout);
					this.add(jPanelparity, new GridBagConstraints(0, 2, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPanelparity.setBorder(BorderFactory.createTitledBorder("Parity"));
					{
						ComboBoxModel<String> jComboBoxdeintModel = 
								new DefaultComboBoxModel<String>(
										new String[] { "auto", "tff" , "bff" });
						jComboBoxparity = new JComboBox<String>();
						jPanelparity.add(jComboBoxparity);
						jComboBoxparity.setModel(jComboBoxdeintModel);
					}
				}
				{
					jPaneldeint = new JPanel();
					GridLayout jPaneldeintLayout = new GridLayout(1, 1);
					jPaneldeintLayout.setColumns(1);
					jPaneldeintLayout.setHgap(5);
					jPaneldeintLayout.setVgap(5);
					jPaneldeint.setLayout(jPaneldeintLayout);
					this.add(jPaneldeint, new GridBagConstraints(0, 3, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPaneldeint.setBorder(BorderFactory.createTitledBorder("Frames to deinterlace"));
					{
						ComboBoxModel<String> jComboBoxdeintModel = 
								new DefaultComboBoxModel<String>(
										new String[] { "all", "interlaced" });
						jComboBoxdeint= new JComboBox<String>();
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
		return new DeinterlaceYADIF(jComboBoxmode.getSelectedIndex(), jComboBoxparity.getSelectedIndex(), jComboBoxdeint.getSelectedIndex());
	}

}
