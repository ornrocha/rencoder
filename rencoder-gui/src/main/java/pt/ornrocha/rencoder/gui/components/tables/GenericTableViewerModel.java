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
package pt.ornrocha.rencoder.gui.components.tables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

// TODO: Auto-generated Javadoc
/**
 * The Class GenericTableViewer.
 */
public class GenericTableViewerModel extends AbstractTableModel{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The column names. */
	protected String[] columnNames = null;
	
	/** The is value cells editable. */
	protected boolean isValueCellsEditable=false;
	
	/** The data. */
	protected List<Object[]> data = null;
	
	/** The editfromcolumn. */
	private int editfromcolumn =0;
	
	/**
	 * Instantiates a new generic table viewer.
	 *
	 * @param colNames the col names
	 * @param isValueCellsEditable the is value cells editable
	 */
	public GenericTableViewerModel(String[] colNames,boolean isValueCellsEditable){
		this.columnNames = colNames;
		this.isValueCellsEditable = isValueCellsEditable;
	}
	
	/**
	 * Instantiates a new generic table viewer.
	 *
	 * @param listobj the listobj
	 * @param colNames the col names
	 * @param isValueCellsEditable the is value cells editable
	 */
	public GenericTableViewerModel (List<Object[]> listobj,String[] colNames,boolean isValueCellsEditable){
	    this.data=listobj;
		this.columnNames = colNames;
		this.isValueCellsEditable = isValueCellsEditable;
		
	}
	
	
	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		if(data !=null)
		     return data.size();
		else return 0;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		//System.out.println("rowIndex: "+rowIndex +"  columnIndex:"+columnIndex);
		if(data!=null){
			Object [] objlist= data.get(rowIndex);
			Object obj = objlist[columnIndex];
			return obj;
		}

		return null;
	}
	
	/**
	 * Gets the row at.
	 *
	 * @param rowIndex the row index
	 * @return the row at
	 */
	public Object[] getRowAt(int rowIndex){
			return data.get(rowIndex);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object, int, int)
	 */
	public void setValueAt(Object value, int rowIndex, int columnIndex){
		if(data!=null){
		Object [] objlist= data.get(rowIndex);
		objlist[columnIndex] = value;
		fireTableDataChanged();
		}
	}
	
	/**
	 * Initializetable.
	 *
	 * @param listobj the listobj
	 */
	public void initializetable(List<Object[]> listobj){
		this.data = listobj;
		fireTableDataChanged();		
	}
	
	 /**
 	 * Insert data at row.
 	 *
 	 * @param values the values
 	 * @param row the row
 	 */
	public void insertDataAtRow(Object[] values, int row){ 
		if(data.size()>0){
			data.remove(row-1);
			data.add(row-1, values);
			fireTableDataChanged();
		}
	}
	 
	 /**
 	 * Insert list of data.
 	 *
 	 * @param listobj the listobj
 	 */
 	public void insertListOfData(List<Object[]> listobj){
		 if(data==null)
			 this.data= new ArrayList<Object[]>();
		 for(Object[] objects : listobj){
			 this.data.add(objects); 
		 }
		 fireTableDataChanged();
	 }
	 
	 /**
 	 * Insert single object data.
 	 *
 	 * @param obj the obj
 	 */
 	public void insertSingleObjectData(Object[] obj){
		 if(data==null)
			 this.data= new ArrayList<Object[]>();
		 this.data.add(obj);	 
		 fireTableDataChanged();
	 }
	 
	 /**
 	 * Adds the row.
 	 *
 	 * @param rowData the row data
 	 */
 	public void addRow(Object[] rowData) {
		 insertSingleObjectData(rowData);
	  }
	 
	 
	 /**
 	 * Put all data.
 	 *
 	 * @param listobj the listobj
 	 */
 	public void putalldata(List<Object[]> listobj){
		 int n = 0;
		 for (Object[] objects : listobj) {
			 insertDataAtRow(objects, n);
			n++;
		}
		 
	 }
	 
  /**
   * Add rows.
   *
   * @param nrows the nrows
   */
  public void addrows(int nrows){
		  
		 for (int i = 0; i < nrows; i++) {
		  Object[] valuesarray = new Object [columnNames.length];
		  for (int j = 0; j < valuesarray.length; j++) {
			  valuesarray[j]="";
		  }
		  data.add(valuesarray); 
		 }
		 isValueCellsEditable=true;
		 fireTableDataChanged();  
	  }
   
  /**
   * Adds the row at pos.
   *
   * @param row the row
   */
  public void addRowAtPos(int row){ 
		 if(data.size()>0){
	      Object[] valuesarray = new Object [columnNames.length];
	      for (int j = 0; j < valuesarray.length; j++) {
				  valuesarray[j]="";
			  }
		 data.add(row-1, valuesarray);
		 fireTableDataChanged();
		 }
  }
  


  /**
   * Removes the rows at positions (x).
   *
   * @param indices the indices
   */
  public void removeRowsAtPos(int[] indices) {

	 int a=0;
	  for (int j = 0; j < indices.length; j++) {
		  int b =indices[j]-a; 
		data.remove(b);
		a++;
		fireTableRowsDeleted(b, b);
	}

	  }
	  


   /**
    * Removes the row at position (x).
    *
    * @param n the n
    */
   public void removeRowAtPos(int n) {
	       if(data.size()>0){
	    	data.remove(n);
			fireTableRowsDeleted(n, n);
	       }
		} 


  /**
   * Removes the row.
   */
  public void removeRow(){
	  if (data.size()>0){
		  removeRowAtPos(data.size()-1);
	  }
   }


   /**
    * Sets the column names.
    *
    * @param names the new column names
    */
   public void setColumnNames(String[] names){
	   this.columnNames = names;
      }

   /**
    * Gets the column names size.
    *
    * @return the column names size
    */
   public int getColumnNamesSize(){
	   return this.columnNames.length;
      }
   
   /**
    * Reset table.
    */
   public void resetTable(){
	    if(data!=null){
		int nrows = data.size();
		if(nrows>0){
		for (int i = 0; i < nrows; i++) {
			removeRow();
		  }
		}
	  }
	}
   
   /* (non-Javadoc)
    * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
    */
   @Override
   public Class<?> getColumnClass(int column)
   {
       for (int row = 0; row < getRowCount(); row++)
       {
           Object o = getValueAt(row, column);

           if (o != null)
               return o.getClass();
       }

       return Object.class;
   }
   
   /* (non-Javadoc)
    * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
    */
   public boolean isCellEditable(int row, int col) {
	   if(isValueCellsEditable){
		   if (col > editfromcolumn) {
			   return true;
		   } else {
			   return false;
		   }
	   }
	   return false;
   }
   
   /**
    * Sets the editfromcolumn.
    *
    * @param n the new editfromcolumn
    */
   public void seteditfromcolumn(int n){
		this.editfromcolumn = n;
	}
   
   /**
    * Move element up.
    *
    * @param index the index
    */
   public void moveElementUp(int index){
	   if(index>0 && data.size()>1)
			 Collections.swap(data, index, index-1);
	   fireTableDataChanged();
   }
   
   /**
    * Move element down.
    *
    * @param index the index
    */
   public void moveElementDown(int index){
	   if(index>=0 && index+1<data.size() && data.size()>1)
			 Collections.swap(data, index, index+1);
	   fireTableDataChanged();
   }
   
}
