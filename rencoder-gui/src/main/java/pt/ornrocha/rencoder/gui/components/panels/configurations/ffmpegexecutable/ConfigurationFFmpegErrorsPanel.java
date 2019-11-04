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
package pt.ornrocha.rencoder.gui.components.panels.configurations.ffmpegexecutable;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.helpers.lang.LangTools;

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
public class ConfigurationFFmpegErrorsPanel extends JDialog{
	
	/** The j buttonclose. */
	private JButton jButtonclose;
	
	/** The j text area1. */
	private JTextArea jTextArea1;
	
	/** The rb. */
	private ResourceBundle rb;
	
	/** The closeconfigerrorpanel. */
	public static String CLOSECONFIGERRORPANEL="closeconfigerrorpanel";
	
	/**
	 * Instantiates a new configuration ffmpeg errors panel.
	 *
	 * @param errors the errors
	 */
	public ConfigurationFFmpegErrorsPanel(ArrayList<String> errors){
		rb = ResourceBundle.getBundle("lang",LangTools.getDefinedLanguage(), LangTools.loadLanguagesPath());
		initGUI();
		this.setModal(true);
		addErrorsToPanel(errors);
	}
	
	
	/**
	 * Adds the errors to panel.
	 *
	 * @param errors the errors
	 */
	private void addErrorsToPanel(ArrayList<String> errors){
		for (int i = 0; i < errors.size(); i++) {
			jTextArea1.append(errors.get(i));
		}
		jTextArea1.setEditable(false);
	}

	/**
	 * Inits the gui.
	 */
	private void initGUI() {
		try {
			{   
				GridBagLayout thisLayout = new GridBagLayout();
				thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				thisLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
				thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
				
				thisLayout.columnWidths = new int[] {7, 7, 7, 7};
				getContentPane().setLayout(thisLayout);
				{
					jButtonclose = new JButton();
					getContentPane().add(jButtonclose, new GridBagConstraints(1, 9, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jButtonclose.setText(LangTools.getResourceBundleWordLanguage(rb,"Close","general.close"));
					jButtonclose.setActionCommand(CLOSECONFIGERRORPANEL);
				}
				{
					jTextArea1 = new JTextArea();
					getContentPane().add(jTextArea1, new GridBagConstraints(0, 0, 4, 9, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jTextArea1.setBorder(BorderFactory.createTitledBorder(null, LangTools.getResourceBundleWordLanguage(rb,"Errors","ffmpegconfgui.errors"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION));
				}
			}
			{
				this.setSize(505, 179);
			}
		} catch(Exception e) {
		    Logger.error(e);
		}
	}



   /**
    * Adds the close button action listener.
    *
    * @param l the l
    */
   public void addCloseButtonActionListener(ActionListener l){
	     jButtonclose.addActionListener(l);
      }
}
