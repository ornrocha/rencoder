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

import java.awt.Component;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.gui.components.tables.MediaInfoTable;


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
public class MediaInfoScrollPanel extends JScrollPane{
	
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The table. */
	protected JTable table;
	
	/** The infotable. */
	protected MediaInfoTable infotable;
	
	
	/**
	 * Instantiates a new media info scroll panel.
	 *
	 * @param colnames the colnames
	 * @param iseditable the iseditable
	 */
	public MediaInfoScrollPanel(String[] colnames, boolean iseditable) {
		
		initGUI();
		this.infotable=new MediaInfoTable(colnames, iseditable);
		this.table=new JTable();
		this.table.setModel(this.infotable);
		this.getViewport().add(table);
		CenterDataTableColumns();
		
		
	}
	
	
	
	/**
	 * Inits the gui.
	 */
	private void initGUI() {
		try {
			{
				this.setPreferredSize(new java.awt.Dimension(272, 130));
			}
		} catch(Exception e) {
		    Logger.error(e);
		}
	}
	
	
	/**
	 * Insert data.
	 *
	 * @param listdata the listdata
	 */
	public void insertData(List<Object[]> listdata){
		if(this.infotable.getRowCount()>0)
			this.infotable.resetTable();
		
		this.infotable.insertListOfData(listdata);
		
	}
	
	/**
	 * Reset.
	 */
	public void reset(){
		this.infotable.resetTable();
	}
	
	/**
	 * Center data table columns.
	 */
	private void CenterDataTableColumns(){
		
		for (int i = 0; i < this.table.getModel().getColumnCount(); i++) {
			this.table.getColumnModel().getColumn(i).setCellRenderer(r);
		}
		
		
	}
	
	 /** The r. */
 	DefaultTableCellRenderer r = new DefaultTableCellRenderer() {

		private static final long serialVersionUID = 1L;

		@Override
         public Component getTableCellRendererComponent(JTable table, Object
             value, boolean isSelected, boolean hasFocus, int row, int column) {
             super.getTableCellRendererComponent(
                 table, value, isSelected, hasFocus, row, column);
             setHorizontalAlignment(SwingConstants.LEFT);
             return this;
         }
     };

}
