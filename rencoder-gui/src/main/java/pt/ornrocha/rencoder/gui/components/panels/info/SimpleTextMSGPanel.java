package pt.ornrocha.rencoder.gui.components.panels.info;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

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
public class SimpleTextMSGPanel extends JDialog implements Runnable{

	private static final long serialVersionUID = 1L;
	private JTextPane jTextPanemsg;
	private boolean setVisible=true;
	
	public SimpleTextMSGPanel(String message,Component comp){
	    initGUI();
	    setMessage(message);
	    setTextAtributes();
        this.setLocationRelativeTo(comp);
	    this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    this.setModal(true);	
	}
	

	
	
	public void setMessage(String msg){
		this.jTextPanemsg.setText(msg);
	}
	
	public boolean maintainVisible(){
		return setVisible;
	}
	
	public void close(){
		this.setVisible=false;
	}

	private void setTextAtributes(){
		StyledDocument doc = jTextPanemsg.getStyledDocument();

		SimpleAttributeSet bSet = new SimpleAttributeSet();
	
	    StyleConstants.setAlignment(bSet, StyleConstants.ALIGN_CENTER);
	    StyleConstants.setFontFamily(bSet, "Courier");
	    StyleConstants.setFontSize(bSet, 18);
	    StyleConstants.setBold(bSet, true);

	    doc.setParagraphAttributes(0, doc.getLength()-1, bSet, false);

	}
	
	@Override
	public void run() {
		this.setVisible(true);	
	}
	
	private void initGUI() {
		try {
			{
			}
			BorderLayout thisLayout = new BorderLayout();
			getContentPane().setLayout(thisLayout);
			{
				jTextPanemsg = new JTextPane();
				getContentPane().add(jTextPanemsg, BorderLayout.CENTER);
			}
			{
				this.setSize(391, 82);
			}
		} catch(Exception e) {
		    Logger.error(e);
		}
	}


	
}
