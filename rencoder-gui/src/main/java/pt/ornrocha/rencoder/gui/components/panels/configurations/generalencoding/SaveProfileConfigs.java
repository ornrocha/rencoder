/*
 * Copyright 2014
 *
 * This is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. 
 * 
 * This code is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the 
 * GNU Public License for more details. 
 * 
 * You should have received a copy of the GNU Public License 
 * along with this code. If not, see http://www.gnu.org/licenses/ 
 * 
 * Created by Orlando Rocha
 */
package pt.ornrocha.rencoder.gui.components.panels.configurations.generalencoding;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.gui.Maingui;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.helpers.props.fields.StaticVideoEncoderFields;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;
import pt.ornrocha.rencoder.mediafiles.setfiles.foldersandfiles.ListFiles;

// TODO: Auto-generated Javadoc
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
public class SaveProfileConfigs extends JDialog implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The j labelname. */
	private JLabel jLabelname;
	
	/** The j text fieldname. */
	private JTextField jTextFieldname;
	
	/** The j check box1. */
	private JCheckBox jCheckBox1;
	
	/** The j buttonsave. */
	private JButton jButtonsave;
	
	/** The j buttoncancel. */
	private JButton jButtoncancel;
	
	
	/** The closeok. */
	private boolean closeok=true;
	
	/** The confcontainer. */
	private IGeneralVideoEncInfoContainer confcontainer=null;
	
	/** The saveprofiletofile. */
	private static String SAVEPROFILETOFILE="saveprofiletofile";
	
	/** The cancelsaveprofilepanel. */
	private static String CANCELSAVEPROFILEPANEL="cancelsaveprofilepanel";
	
	/** The titlelabel. */
	private String titlelabel;
	
	/** The j labelnametext. */
	private String jLabelnametext;
	
	/** The j check box1text. */
	private String jCheckBox1text;
	
	/** The j buttoncanceltext. */
	private String jButtoncanceltext;
	
	/** The j buttonsavetext. */
	private String jButtonsavetext;
	
	/** The warnmessage. */
	private String warnmessage;
	
	/** The rb. */
	private ResourceBundle rb;
	
	private JFrame mainframe;

	/**
	 * Instantiates a new save profile configs.
	 *
	 * @param cont the cont
	 */
	public SaveProfileConfigs(IGeneralVideoEncInfoContainer cont, JFrame mainframe){
		this.confcontainer=cont;
		this.mainframe=mainframe;
		rb = ResourceBundle.getBundle("lang",LangTools.getDefinedLanguage(), LangTools.loadLanguagesPath());
		initGUI();
		this.setModal(true);
	}
	

	
	/**
	 * Inits the gui.
	 */
	private void initGUI() {
		try {
			{   GridBagLayout thisLayout = new GridBagLayout();
				thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
				thisLayout.rowHeights = new int[] {7, 7, 7, 7};
				thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				
				thisLayout.columnWidths = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
				getContentPane().setLayout(thisLayout);
				this.setTitle(LangTools.getResourceBundleWordLanguage(rb,"Save as profile","general.saveasprofile"));
				{
					jLabelname = new JLabel();
					getContentPane().add(jLabelname, new GridBagConstraints(0, 1, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 10, 0, 0), 0, 0));
					jLabelname.setText(LangTools.getResourceBundleWordLanguage(rb,"Name","profilegui.name")+":");
				}
				{
					jTextFieldname = new JTextField();
					getContentPane().add(jTextFieldname, new GridBagConstraints(1, 1, 9, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					jCheckBox1 = new JCheckBox();
					getContentPane().add(jCheckBox1, new GridBagConstraints(0, 2, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jCheckBox1.setText(LangTools.getResourceBundleWordLanguage(rb,"Save to file","profilegui.savefile"));
				}
				{
					jButtoncancel = new JButton();
					getContentPane().add(jButtoncancel, new GridBagConstraints(4, 3, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jButtoncancel.setText(rb.getString("general.cancel"));
					jButtoncancel.setActionCommand(CANCELSAVEPROFILEPANEL);
					jButtoncancel.addActionListener(this);
				}
				{
					jButtonsave = new JButton();
					getContentPane().add(jButtonsave, new GridBagConstraints(7, 3, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jButtonsave.setText(rb.getString("general.save"));
					jButtonsave.setActionCommand(SAVEPROFILETOFILE);
					jButtonsave.addActionListener(this);
				}
			}
			{
				this.setSize(511, 119);
			}
		} catch(Exception e) {
		    Logger.error(e);
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals(CANCELSAVEPROFILEPANEL))
			this.dispose();
		else if(cmd.equals(SAVEPROFILETOFILE)){
			saveprofileaction();
			if(closeok) {
				Maingui.getInstance().reloadProfiles();
				this.dispose();
			}
			
		}
	
	}
	
	
	/**
	 * Save profile action.
	 */
	private void saveprofileaction(){
		
		if(!jTextFieldname.getText().isEmpty()){
			if(!jCheckBox1.isSelected()){
			   if(!ListFiles.checkifExistsFileNameInDirectory(StaticVideoEncoderFields.PROFILESDIRPATH, jTextFieldname.getText())){
				   confcontainer.saveConfigurations(StaticVideoEncoderFields.PROFILESDIRPATH, jTextFieldname.getText(), true);
			      
			      closeok=true;
			   }
			   else{
				   
				   JOptionPane.showMessageDialog(mainframe,
						   LangTools.getResourceBundleWordLanguage(rb,"Please select other name, because already exists a profile with this name","profilegui.existprofile"),
							LangTools.getResourceBundleWordLanguage(rb,"Warning","warngui.tag"),
						    JOptionPane.INFORMATION_MESSAGE,
						    new ImageIcon(ListFiles.getIconAbsolutePath("icons/warning64x64.png")));
				   closeok=false;
			   }
			}
			else{
				String dir=ListFiles.getFolderpathFileChooser(this);
				if(dir!=null){
					confcontainer.saveConfigurations(dir, jTextFieldname.getText(), true);
					closeok=true;
				}
			}
            
		}
		else{
			closeok=false;
			JOptionPane.showMessageDialog(mainframe,
					LangTools.getResourceBundleWordLanguage(rb,"Define the name of profile","profilegui.warn"),
					LangTools.getResourceBundleWordLanguage(rb,"Warning","warngui.tag"),
				    JOptionPane.INFORMATION_MESSAGE,
				    new ImageIcon(ListFiles.getIconAbsolutePath("icons/warning64x64.png")));
		}
	}
	
	

}
