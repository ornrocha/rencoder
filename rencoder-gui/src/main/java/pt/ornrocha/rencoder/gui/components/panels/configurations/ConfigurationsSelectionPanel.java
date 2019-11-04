package pt.ornrocha.rencoder.gui.components.panels.configurations;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.pmw.tinylog.Logger;


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
public class ConfigurationsSelectionPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JFrame parent=null;
	
	public ConfigurationsSelectionPanel(JFrame parent){
		this.parent=parent;
		initGUI();
		this.setVisible(true);
	}
	
	
	public Dimension getDimension(){
		Dimension d = parent.getSize();
		
		Dimension newd = new Dimension((int)(d.getWidth()/2), (int)d.getHeight());
		return newd;
	}
	
	private void initGUI() {
		try {
			{
				this.setSize(getDimension());
			}
		} catch(Exception e) {
		    Logger.error(e);
		}
	}

}
