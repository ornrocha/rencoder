package pt.ornrocha.rencoder.gui.components.panels.info;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.pmw.tinylog.Logger;

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
public class BlinkMessagePanel extends JPanel implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel jLabel1;
	private boolean stop=false;
	
	public BlinkMessagePanel(String msg){
		initGUI();
		jLabel1.setText(msg);
		Font font = new Font("Verdana", Font.BOLD, 14);
		jLabel1.setFont(font);
	}
	
	public void stop(){
		this.stop=true;
	}

	private void initGUI() {
		try {
			{
				this.setPreferredSize(new java.awt.Dimension(460, 22));
				{
					jLabel1 = new JLabel();
					this.add(jLabel1);
					jLabel1.setText(LangTools.getWordLanguage("Message","warngui.blinkpanelmsg"));
				}
			}
		} catch(Exception e) {
		    Logger.error(e);
		}
	}

	@Override
	public void run() {
		while(!stop){
			try {
			this.setBackground(Color.BLUE);
			Thread.sleep(1000);
			this.setBackground(Color.white);

				Thread.sleep(1000);
			} catch (InterruptedException e) {
			    Logger.error(e);
			}
		}
		
		this.setVisible(false);
		
	}

}
