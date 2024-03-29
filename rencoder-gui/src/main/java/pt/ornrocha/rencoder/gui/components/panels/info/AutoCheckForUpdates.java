package pt.ornrocha.rencoder.gui.components.panels.info;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.tinylog.Logger;

import pt.ornrocha.rencoder.gui.components.panels.auxiliar.VideoPanelUtils;
import pt.ornrocha.rencoder.gui.updates.CheckForUpdates;
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
public class AutoCheckForUpdates extends JDialog implements ActionListener{


	private static final long serialVersionUID = 1L;
	protected JButton jButtongetupdate;
	protected JCheckBox jCheckBoxhide;
	protected JLabel jLabelupdate;
	protected JButton jButtonignore;
	protected ResourceBundle rb;
	protected boolean newUpdate=false;
	
	protected static String GETUPDATE="getupdate";
	protected static String IGNOREUPDATE="ignoreupdate";
	protected static String RENCODERURL="https://github.com/ornrocha/rencoder/releases";
	

	public AutoCheckForUpdates(boolean newupdatefound){
		this.newUpdate=newupdatefound;
		initGUI();
		addButtonsActionlisteners();
		setInitialMSG();
	}
	
	protected void addButtonsActionlisteners(){
		jButtonignore.addActionListener(this);
		jButtonignore.setActionCommand(IGNOREUPDATE);
		jButtongetupdate.addActionListener(this);
		jButtongetupdate.setActionCommand(GETUPDATE);
	}
	
    protected void setInitialMSG(){
    	rb = ResourceBundle.getBundle("lang",LangTools.getDefinedLanguage(), LangTools.loadLanguagesPath());
		

    	jButtonignore.setText(LangTools.getResourceBundleWordLanguage(rb,"Close","general.close"));
    	
    	
    	jButtongetupdate.setText(LangTools.getResourceBundleWordLanguage(rb,"Get update","updates.getupdate"));
    	jCheckBoxhide.setText(LangTools.getResourceBundleWordLanguage(rb,"Do not show this next time","updates.dontshowupdate"));
  
    	if (newUpdate)
    		jLabelupdate.setText(LangTools.getResourceBundleWordLanguage(rb,"A new update was found","updates.updatefound"));
    	else
    		jLabelupdate.setText(LangTools.getResourceBundleWordLanguage(rb,"Rencoder is in the latest version","updates.lastversion"));
    	
    	jLabelupdate .setFont(new Font("Sherif", Font.BOLD, 20));
    	jLabelupdate .setHorizontalAlignment(SwingConstants.CENTER);
		String iconpath = new File("icons/rencoderblue.png").getAbsolutePath();
		jLabelupdate .setIcon(new ImageIcon(((new ImageIcon(iconpath)).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
		
		if(CheckForUpdates.isCheckUpdatesBlocked()){
			jCheckBoxhide.setSelected(true);
		}
		
	}
	
	private void initGUI() {
		try {
			{   
				
				GridBagLayout thisLayout = new GridBagLayout();
				thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				thisLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7, 7};
				thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
				
				thisLayout.columnWidths = new int[] {7, 7, 7, 7};
				getContentPane().setLayout(thisLayout);
				this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/rencoderbig.png")));
				{
					jButtonignore = new JButton();
					getContentPane().add(jButtonignore, new GridBagConstraints(0, 5, 2, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					
				}
				{
					jButtongetupdate = new JButton();
					getContentPane().add(jButtongetupdate, new GridBagConstraints(2, 5, 2, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					if(!newUpdate)
						jButtongetupdate.setEnabled(false);
					
				}
				{
					jCheckBoxhide = new JCheckBox();
					getContentPane().add(jCheckBoxhide, new GridBagConstraints(0, 4, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 30, 0, 0), 0, 0));
					
				}
				{
					jLabelupdate = new JLabel();
					getContentPane().add(jLabelupdate, new GridBagConstraints(0, 0, 4, 4, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					
				}
			}
			{
				this.setSize(518, 140);
			}
		} catch(Exception e) {
		    Logger.error(e);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals(IGNOREUPDATE)){
			checkhideupdates();
			this.dispose();
		}
		else if(cmd.equals(GETUPDATE)){
			VideoPanelUtils.openInBrowser(RENCODERURL);
			this.dispose();
		}
		
	}
	
	protected void checkhideupdates(){
		if(jCheckBoxhide.isSelected()){
			CheckForUpdates.setCheckUpdatesBlock();
		}
		else
			CheckForUpdates.deleteCheckUpdatesBlock();
		
	}

}
