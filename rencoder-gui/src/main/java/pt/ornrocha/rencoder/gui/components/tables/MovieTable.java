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

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class MovieTable.
 */
public class MovieTable extends GenericTableViewerModel{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	
	
	/**
	 * Instantiates a new movie table.
	 *
	 * @param colNames the col names
	 * @param isValueCellsEditable the is value cells editable
	 */
	public MovieTable(String[] colNames,boolean isValueCellsEditable){
		super(colNames, isValueCellsEditable);
	}

	/**
	 * Instantiates a new movie table.
	 *
	 * @param listobj the listobj
	 * @param colNames the col names
	 * @param isValueCellsEditable the is value cells editable
	 */
	public MovieTable(List<Object[]> listobj, String[] colNames,boolean isValueCellsEditable) {
		super(listobj, colNames, isValueCellsEditable);

	}
	
	

}
