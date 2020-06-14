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
package pt.ornrocha.rencoder.gui.components.panels.configurations;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.table.TableColumn;

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.configurations.FFmpegManager;
import pt.ornrocha.rencoder.gui.components.panels.Scroll.SoftSubtitlesConfigScrollPanel;
import pt.ornrocha.rencoder.gui.components.panels.Scroll.SubtitleScrollPanel;
import pt.ornrocha.rencoder.gui.components.tables.SubtitleTable;
import pt.ornrocha.rencoder.gui.components.tables.auxiliar.SubComboBoxRender;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.mediafiles.files.containers.base.Subtitlefile;
import pt.ornrocha.rencoder.mediafiles.files.containers.base.Videofile;

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
public class SoftSubtitlesConfigPanel extends JDialog implements ActionListener, Comparator<String>{
	
	/** The j panelmain. */
	private JPanel jPanelmain;
	
	/** The j buttonok. */
	private JButton jButtonok;
	
	/** The j buttoncancel. */
	private JButton jButtoncancel;
	
	/** The j panelscr. */
	private JPanel jPanelscr;
	
	/** The j buttondown. */
	private JButton jButtondown;
	
	/** The j buttonup. */
	private JButton jButtonup;
	
	/** The j panelchangeorder. */
	private JPanel jPanelchangeorder;
	
	/** The sub panel. */
	private SoftSubtitlesConfigScrollPanel subPanel;
	
	/** The mainsubtable. */
	private SubtitleTable mainsubtable=null; 
	
	/** The languages iso. */
	protected String[] languagesISO = null;
	
	/** The maplangtocode. */
	protected HashMap<String, String> maplangtocode=null;
	
	/** The mapcodetolang. */
	protected HashMap<String, String> mapcodetolang=null;
	
	/** The comparator. */
	private Comparator comparator=Collator.getInstance();
	
	/** The col. */
	private TableColumn col=null;
	
	/** The rb. */
	private ResourceBundle rb;
	
	/** The submoveup. */
	private static String SUBMOVEUP="submoveup";
	
	/** The submovedown. */
	private static String SUBMOVEDOWN="submovedown";
	
	/** The cancelsubssoftconfig. */
	private static String CANCELSUBSSOFTCONFIG="cancelsubssoftconf";
	
	/** The oksoftsubsconfig. */
	private static String OKSOFTSUBSCONFIG="oksoftsubsconfig";
	
	/**
	 * Instantiates a new soft subtitles config panel.
	 */
	public SoftSubtitlesConfigPanel(){
		rb = ResourceBundle.getBundle("lang",LangTools.getDefinedLanguage(), LangTools.loadLanguagesPath());
		initGUI();
		this.setModal(true);
	}
	
	/**
	 * Sets the subtitle config panel.
	 *
	 * @param subconfigpanel the subconfigpanel
	 * @param movie the movie
	 */
	public void setSubtitleConfigPanel(SubtitleScrollPanel subconfigpanel, Videofile movie){
		this.mainsubtable=subconfigpanel.getSubsTable();
		subPanel.setSelectedVideo(movie);
		
		setlanguagecodes();
		setLangComboboxConfigs();
		setLanguageToSoftSubsPanel();
		subPanel.setSubsFromOtherSubsConfigPanel(subconfigpanel);
	}
	
	 /**
 	 * Set language codes.
 	 */
// 	private void setlanguagecodes(){
//	    	String[] languages = Locale.getISOLanguages();
//	    	this.languagesISO=new String[languages.length];
//
//	    	ArrayList<String> countries = new ArrayList<>();
//	    	
//			for (String language : languages) {
//			    Locale locale = new Locale(language);
//			    String isocode = locale.getISO3Language();
//			    String lang = locale.getDisplayLanguage();
//			    countries.add(lang);
//			    maplangtocode.put(lang, isocode);
//			    mapcodetolang.put(isocode, lang);
//			}
//			
//			Collections.sort(countries, this.comparator);
//			
//			for (int i = 0; i < countries.size(); i++) {
//				languagesISO[i]=countries.get(i);
//			}
//			
//	 }
 	
	private void setlanguagecodes(){
		ArrayList<String> countries=FFmpegManager.getInstance().getOrderedCountryList();
		this.languagesISO=new String[countries.size()];
		for (int i = 0; i < countries.size(); i++) {
			languagesISO[i]=countries.get(i);
		}
		
		this.mapcodetolang=FFmpegManager.getInstance().getMapcodetolang();
		this.maplangtocode=FFmpegManager.getInstance().getMaplangtocode();
	}
	
	
	/**
	 * Sets the lang combobox configs.
	 */
	public void setLangComboboxConfigs(){
		col = this.subPanel.getTable().getColumnModel().getColumn(1);
		col.setCellEditor(new MyComboBoxEditor(languagesISO));
		col.setCellRenderer(new SubComboBoxRender(languagesISO));
	}
	
	/**
	 * Sets the language to soft subs panel.
	 */
	private void setLanguageToSoftSubsPanel(){
		this.subPanel.setLanguageAndCodes(maplangtocode, mapcodetolang);
	}

	/**
	 * Inits the gui.
	 */
	private void initGUI() {
		try {
			{
				jPanelmain = new JPanel();
				GridBagLayout jPanelmainLayout = new GridBagLayout();
				getContentPane().add(jPanelmain, BorderLayout.CENTER);
				jPanelmainLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				jPanelmainLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
				jPanelmainLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				jPanelmainLayout.columnWidths = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
				jPanelmain.setLayout(jPanelmainLayout);
				{
					jButtonok = new JButton();
					jPanelmain.add(jButtonok, new GridBagConstraints(9, 8, 3, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jButtonok.setText(LangTools.getResourceBundleWordLanguage(rb, "OK","general.ok"));
					jButtonok.setActionCommand(OKSOFTSUBSCONFIG);
					jButtonok.addActionListener(this);
				}
				{
					jButtoncancel = new JButton();
					jPanelmain.add(jButtoncancel, new GridBagConstraints(6, 8, 3, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jButtoncancel.setText(LangTools.getResourceBundleWordLanguage(rb, "Cancel","general.cancel"));
					jButtoncancel.setActionCommand(CANCELSUBSSOFTCONFIG);
					jButtoncancel.addActionListener(this);
				}
				{
					jPanelscr = new JPanel();
					subPanel= new SoftSubtitlesConfigScrollPanel();
					jPanelmain.add(subPanel, new GridBagConstraints(0, 0, 12, 8, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					jPanelchangeorder = new JPanel();
					GridBagLayout jPanelchangeorderLayout = new GridBagLayout();
					jPanelmain.add(jPanelchangeorder, new GridBagConstraints(0, 8, 6, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPanelchangeorderLayout.rowWeights = new double[] {0.1, 0.1};
					jPanelchangeorderLayout.rowHeights = new int[] {7, 7};
					jPanelchangeorderLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
					jPanelchangeorderLayout.columnWidths = new int[] {7, 7, 7, 7, 7, 7};
					jPanelchangeorder.setLayout(jPanelchangeorderLayout);
					jPanelchangeorder.setBorder(BorderFactory.createTitledBorder(LangTools.getResourceBundleWordLanguage(rb, "Change order","general.changeorder")));
					{
						jButtonup = new JButton();
						jPanelchangeorder.add(jButtonup, new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						jButtonup.setText(LangTools.getResourceBundleWordLanguage(rb, "Up","general.up"));
						jButtonup.setActionCommand(SUBMOVEUP);
						jButtonup.addActionListener(this);
					}
					{
						jButtondown = new JButton();
						jPanelchangeorder.add(jButtondown, new GridBagConstraints(3, 0, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						jButtondown.setText(LangTools.getResourceBundleWordLanguage(rb, "Down","general.down"));
						jButtondown.setActionCommand(SUBMOVEDOWN);
						jButtondown.addActionListener(this);
					}
				}
			}
			{
				this.setSize(697, 297);
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
		String action = e.getActionCommand();
		
		if(action.equals(SUBMOVEUP))
			this.subPanel.moveup();
		else if(action.equals(SUBMOVEDOWN))
			this.subPanel.movedown();
		else if(action.equals(CANCELSUBSSOFTCONFIG))
			this.dispose();
		else if (action.equals(OKSOFTSUBSCONFIG)){
			setCodesToSubtitles();
			this.dispose();
		}
			
		
	}
	

	
	/**
	 * The Class MyComboBoxEditor.
	 */
	class MyComboBoxEditor extends DefaultCellEditor {
		  
  		/**
  		 * Instantiates a new my combo box editor.
  		 *
  		 * @param items the items
  		 */
  		public MyComboBoxEditor(String[] items) {
		    super(new JComboBox(items));
		  }
		  
		  /* (non-Javadoc)
  		 * @see javax.swing.DefaultCellEditor#getCellEditorValue()
  		 */
  		@Override 
		  public Object getCellEditorValue() {
			    JComboBox<String> combo = (JComboBox<String>) editorComponent;
			    return combo.getSelectedItem();
			  }
		  
		}



	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(String country1, String country2) {
		// TODO Auto-generated method stub
		return comparator.compare(country1, country2);
	}	
	
	/**
	 * Sets the codes to subtitles.
	 */
	private void setCodesToSubtitles(){
		ArrayList<Subtitlefile> subs = subPanel.getSelectedvideo().getSubtitles();
		for (int i = 0; i < subs.size(); i++) {
			Subtitlefile sub=subs.get(i);
			sub.setLanguage(getCountryCode(i));
		}
	}
	
	
	/**
	 * Gets the country code.
	 *
	 * @param index the index
	 * @return the country code
	 */
	private String getCountryCode(int index){

		String name = (String) this.subPanel.getTable().getModel().getValueAt(index, 1);
		return maplangtocode.get(name);
	}
	

}
