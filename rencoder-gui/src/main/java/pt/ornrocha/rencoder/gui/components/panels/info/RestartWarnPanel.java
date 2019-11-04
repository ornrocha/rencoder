package pt.ornrocha.rencoder.gui.components.panels.info;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.gui.execute.RestartRencoder;
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
public class RestartWarnPanel extends JDialog implements ActionListener{

    private static final long serialVersionUID = 1L;
    private JButton jButtonclose;
    private JTextPane jTextPanemsg;
    
    private JButton jButtonrestart;
    private RestartRencoder restart=null;
    
    private static String CLOSERESTARTWARNPANEL="restartPanePanelclose";
    private static String RESTARTACTION="restartorsvc";
    


    public RestartWarnPanel(RestartRencoder restart,String message,Window comp){
    	this.restart=restart;
    	initGUI();
    	
    	addCloseButtonListener(this, CLOSERESTARTWARNPANEL);
    	addRestartButtonListener(this, RESTARTACTION);
    	setMessage(message);
    	setTextAtributes();

		this.setLocationRelativeTo(comp);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setVisible(true);	
    }
    
    public RestartWarnPanel(RestartRencoder restart,String message, Window comp,Dimension d){
    	this.restart=restart;
    	initGUI();

    	addCloseButtonListener(this, CLOSERESTARTWARNPANEL);
    	addRestartButtonListener(this, RESTARTACTION);
    	setMessage(message);
    	setTextAtributes();
    	
		this.setLocationRelativeTo(comp);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(d);
		this.setModal(true);
		this.setVisible(true);	
    }
    
    
    
    
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		
		if(cmd.equals(CLOSERESTARTWARNPANEL))
			this.dispose();
		else if(cmd.equals(RESTARTACTION)){
			Thread t = new Thread(this.restart);
			t.run();
			System.exit(0);
		}
		
	}
	
	
	private void setTextAtributes(){
		StyledDocument doc = jTextPanemsg.getStyledDocument();

		SimpleAttributeSet bSet = new SimpleAttributeSet();
	
	    StyleConstants.setAlignment(bSet, StyleConstants.ALIGN_CENTER);
	    StyleConstants.setFontFamily(bSet, "Courier");
	    StyleConstants.setFontSize(bSet, 15);
	    StyleConstants.setBold(bSet, true);

	    doc.setParagraphAttributes(0, doc.getLength()-1, bSet, false);

	    jTextPanemsg.setEditable(false);
	}
	
	
	private void addCloseButtonListener(ActionListener l, String actioncmd){
		this.jButtonclose.addActionListener(l);
		this.jButtonclose.setActionCommand(actioncmd);
	}
	
	private void addRestartButtonListener(ActionListener l, String actioncmd){
		this.jButtonrestart.addActionListener(l);
		this.jButtonrestart.setActionCommand(actioncmd);
	}
	
	
	public void setMessage(String msg){
		this.jTextPanemsg.setText(msg);
	}
	
	

	
	private void initGUI() {
		try {
			{   GridBagLayout thisLayout = new GridBagLayout();
				thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1};
				thisLayout.rowHeights = new int[] {7, 7, 7, 7, 7};
				thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
				
				thisLayout.columnWidths = new int[] {7, 7, 7, 7};
				getContentPane().setLayout(thisLayout);
				this.setPreferredSize(new java.awt.Dimension(441, 116));
				this.setTitle(LangTools.getWordLanguage("Warn","general.warn"));
				{
					jButtonclose = new JButton();
					getContentPane().add(jButtonclose, new GridBagConstraints(2, 4, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jButtonclose.setText(LangTools.getWordLanguage("Close","general.close"));
				}
				{
					jTextPanemsg = new JTextPane();
					getContentPane().add(jTextPanemsg, new GridBagConstraints(0, 0, 4, 4, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jTextPanemsg.setText(" ");
					jTextPanemsg.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				}
				{
					jButtonrestart = new JButton();
					getContentPane().add(jButtonrestart, new GridBagConstraints(0, 4, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jButtonrestart.setText(LangTools.getWordLanguage("Restart","general.restart"));
				}
			}
			{
				this.setSize(441, 116);
			}
		} catch(Exception e) {
		    Logger.error(e);
		}
	}

}
