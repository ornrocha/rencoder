package pt.ornrocha.rencoder.gui.components.panels.info;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import pt.ornrocha.rencoder.gui.components.panels.auxiliar.VideoPanelUtils;
import pt.ornrocha.rencoder.gui.execute.RestartRencoder;
import pt.ornrocha.rencoder.gui.updates.CheckForUpdates;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.helpers.osystem.OSystem;

public class UserCheckForNewUpdates extends AutoCheckForUpdatesMacOSPanel{


	private static final long serialVersionUID = 1L;
	private boolean foundupdates=false;
	
	private RestartRencoder restart=null;

	public UserCheckForNewUpdates(){
		super();
		ChangeSettings();
	}
	
	
	private void ChangeSettings(){
		
		jCheckBoxhide.setText(LangTools.getResourceBundleWordLanguage(rb,"Check for updates automatically","updates.checkautomupdates"));
		jButtonignore.setText(LangTools.getResourceBundleWordLanguage(rb,"Close","general.close"));
		jButtongetupdate.setText(LangTools.getResourceBundleWordLanguage(rb,"Update","updates.update"));
		if(!CheckForUpdates.isCheckUpdatesBlocked()){
			jCheckBoxhide.setSelected(true);
		}
	}
	
	
	public void setNeedOfUpdates(boolean existupdates, RestartRencoder restart){
		this.restart=restart;
		this.foundupdates=existupdates;
		setUpdateMSG();
		if(!existupdates){
			jButtongetupdate.setEnabled(false);
		}
		else{
			if(OSystem.isLinux() || OSystem.isWindows())
				jButtongetupdate.setText(LangTools.getResourceBundleWordLanguage(rb,"Apply now","updates.applyupdate"));
			else
				jButtongetupdate.setText(LangTools.getResourceBundleWordLanguage(rb,"Get update","updates.getupdate"));
		}
	}
	
    protected void setUpdateMSG(){
		
    	
    	
    	if(foundupdates)
    	    jLabelupdate.setText(LangTools.getResourceBundleWordLanguage(rb,"A new update was found","updates.updatefound"));
    	else
    		jLabelupdate.setText(LangTools.getResourceBundleWordLanguage(rb,"No updates were found","updates.noupdatefound"));
    	
    	
    	
    	jLabelupdate .setFont(new Font("Sherif", Font.BOLD, 20));
    	jLabelupdate .setHorizontalAlignment(SwingConstants.CENTER);
		String iconpath = new File("icons/rencoderblue.png").getAbsolutePath();
		jLabelupdate .setIcon(new ImageIcon(((new ImageIcon(iconpath)).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
		
	}
    
    @Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals(IGNOREUPDATE)){
			checkhideupdates();
			this.dispose();
		}
		else if(cmd.equals(GETUPDATE)){
			if(OSystem.isMacOS())
			   VideoPanelUtils.openInBrowser(RENCODERURL);
			else{
				CheckForUpdates.deleteCheckUpdatesBlock();
				Thread t = new Thread(this.restart);
				t.run();
				System.exit(0);
			}
				
			this.dispose();
		}
		
	}
    
    @Override
    protected void checkhideupdates(){
		if(jCheckBoxhide.isSelected()){
			CheckForUpdates.deleteCheckUpdatesBlock();
		}
		else
			CheckForUpdates.setCheckUpdatesBlock();
	}
	
}
