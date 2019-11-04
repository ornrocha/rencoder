/*
 * Copyright 2015
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
package pt.ornrocha.rencoder.exec.updates.auxiliar;


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
public class WarnJTextPanePanel extends JDialog implements ActionListener{

    private static final long serialVersionUID = 1L;
    private JButton jButtonclose;
    private JTextPane jTextPanemsg;
    private static String CLOSETHISWARNPANEL="WarnJTextPanePanelclose";



    public WarnJTextPanePanel(String message,Window comp){
	initGUI();
	addCloseButtonListener(this, CLOSETHISWARNPANEL);
	setMessage(message);
	setTextAtributes();
	setLanguage();
	this.setLocationRelativeTo(comp);
	this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	this.setModal(true);
	this.setVisible(true);	
    }

    public WarnJTextPanePanel(String message, Window comp,Dimension d){
	initGUI();
	addCloseButtonListener(this, CLOSETHISWARNPANEL);
	setMessage(message);
	setTextAtributes();
	setLanguage();
	this.setLocationRelativeTo(comp);
	this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	this.setSize(d);
	this.setModal(true);
	this.setVisible(true);	
    }

    public WarnJTextPanePanel(String message,Window comp, String title){
	initGUI();
	addCloseButtonListener(this, CLOSETHISWARNPANEL);
	setMessage(message);
	setTextAtributes();
	setLanguage();
	this.setTitle(title);
	this.setLocationRelativeTo(comp);
	this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	this.setModal(true);
	this.setVisible(true);	
    }




    @Override
    public void actionPerformed(ActionEvent e) {
	String cmd=e.getActionCommand();

	if(cmd.equals(CLOSETHISWARNPANEL))
	    this.dispose();

    }


    private void setTextAtributes(){
	StyledDocument doc = jTextPanemsg.getStyledDocument();

	SimpleAttributeSet bSet = new SimpleAttributeSet();

	StyleConstants.setAlignment(bSet, StyleConstants.ALIGN_CENTER);
	StyleConstants.setFontFamily(bSet, "Courier");
	// StyleConstants.setFontFamily(bSet, "Monospace");
	StyleConstants.setFontSize(bSet, 15);
	StyleConstants.setBold(bSet, true);

	doc.setParagraphAttributes(0, doc.getLength()-1, bSet, false);

	jTextPanemsg.setEditable(false);
    }


    private void addCloseButtonListener(ActionListener l, String actioncmd){
	this.jButtonclose.addActionListener(l);
	this.jButtonclose.setActionCommand(actioncmd);
    }


    public void setMessage(String msg){
	this.jTextPanemsg.setText(msg);
    }


    private void setLanguage(){
	//ResourceBundle rb = ResourceBundle.getBundle("lang",LangTools.getDefinedLanguage(), LangTools.loadLanguagesPath());	
	jButtonclose.setText("close");
	this.setTitle("warn");	
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
	    {
		jButtonclose = new JButton();
		getContentPane().add(jButtonclose, new GridBagConstraints(1, 4, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		jButtonclose.setText("Close");
	    }
	    {
		jTextPanemsg = new JTextPane();
		getContentPane().add(jTextPanemsg, new GridBagConstraints(0, 0, 4, 4, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		jTextPanemsg.setText(" ");
		jTextPanemsg.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	    }
	    }
	    {
		this.setSize(441, 116);
	    }
	} catch(Exception e) {
	    e.printStackTrace();
	}
    }

}
