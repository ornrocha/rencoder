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
package pt.ornrocha.rencoder.gui.components.panels.Scroll;

import java.util.HashMap;

import javax.swing.JTable;

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.gui.components.tables.SubtitleTable;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.mediafiles.files.containers.base.Subtitlefile;


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
public class SoftSubtitlesConfigScrollPanel extends SubtitleScrollPanel{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The sub lang. */
	protected String subLang;

	/** The mainsubpanel. */
	protected SubtitleScrollPanel mainsubpanel=null;

	/** The maplangtocode. */
	protected HashMap<String, String> maplangtocode=null;

	/** The mapcodetolang. */
	protected HashMap<String, String> mapcodetolang=null;


	/**
	 * Instantiates a new soft subtitles config scroll panel.
	 */
	public SoftSubtitlesConfigScrollPanel(){
		super(null);
	}



	/* (non-Javadoc)
	 * @see gui.components.panels.Scroll.SubtitleScrollPanel#initGUI()
	 */
	@Override
	protected void initGUI() {
		try {
			{
				this.setPreferredSize(new java.awt.Dimension(602, 165));
			}
		} catch(Exception e) {
			Logger.error(e);
		}
	}

	/* (non-Javadoc)
	 * @see gui.components.panels.Scroll.SubtitleScrollPanel#setLanguage()
	 */
	@Override
	protected void setLanguage(){


		this.sublabel = LangTools.getWordLanguage("Filename", "general.filename");
		this.usethissub = LangTools.getWordLanguage("Use subtitle", "subtitlesgui.use");
		this.subLang= LangTools.getWordLanguage("Language", "subtitlesgui.lang");

	}

	/* (non-Javadoc)
	 * @see gui.components.panels.Scroll.SubtitleScrollPanel#initSubtitleTable()
	 */
	@Override
	protected void initSubtitleTable(){

		String[] colnames = {this.sublabel,this.subLang,this.usethissub}; 
		this.subsTable = new SubtitleTable(colnames, true);
		this.table = new JTable();

		this.table.setModel(this.subsTable);

		this.getViewport().add(table);

	}

	/* (non-Javadoc)
	 * @see gui.components.panels.Scroll.SubtitleScrollPanel#checkselectedSubs()
	 */
	@Override
	protected void checkselectedSubs(){
		int nrows = subsTable.getRowCount();
		if(nrows>0){
			for (int i = 0; i < nrows; i++) {
				Object objval = subsTable.getValueAt(i, 2);

				boolean val = (boolean) objval;
				mainsubpanel.getSubsTable().setValueAt(val, i, 1);
				this.selectedvideo.changeUseSubtitleState(i, val);

			} 
		}
	}


	/**
	 * Sets the language and codes.
	 *
	 * @param maplangcode the maplangcode
	 * @param mapcodelang the mapcodelang
	 */
	public void setLanguageAndCodes(HashMap<String, String> maplangcode, HashMap<String, String> mapcodelang){
		this.maplangtocode=maplangcode;
		this.mapcodetolang=mapcodelang;
	}


	/**
	 * Sets the subs from other subs config panel.
	 *
	 * @param mainsubconfigpanel the new subs from other subs config panel
	 */
	public void setSubsFromOtherSubsConfigPanel(SubtitleScrollPanel mainsubconfigpanel){
		mainsubpanel=mainsubconfigpanel;
		int nrows = mainsubpanel.getSubsTable().getRowCount();
		if(nrows>0){

			for (int i = 0; i < nrows; i++) {
				Subtitlefile sub = this.selectedvideo.getSubtitles().get(i);
				Object[] row= mainsubpanel.getSubsTable().getRowAt(i);
				Object[] newrow = new Object[3];
				String name = (String) row[0];
				newrow[0]=name;
				String langcode=sub.getLanguage();
				newrow[1]=mapcodetolang.get(langcode);
				Object rawstate=row[1];
				boolean state = false;
				if(rawstate!=null)
					state=(boolean) rawstate;
				newrow[2]=state;
				this.subsTable.addRow(newrow);
			}

		}

	}

	/**
	 * Moveup.
	 */
	public void moveup(){
		int index=selectedSubtitleIndex;
		this.subsTable.moveElementUp(index);
		mainsubpanel.moveSubUp(index);
		checkselectedSubs();
	}

	/**
	 * Movedown.
	 */
	public void movedown(){
		int index=selectedSubtitleIndex;
		this.subsTable.moveElementDown(index);
		mainsubpanel.moveSubDown(index);
		checkselectedSubs();
	}




}