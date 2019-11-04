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
package pt.ornrocha.rencoder.gui.components.panels.auxiliar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import pt.ornrocha.rencoder.helpers.lang.LangTools;

// TODO: Auto-generated Javadoc
/**
 * The Class CopyPasteJTextField.
 */
public class CopyPasteJTextField extends JTextField implements MouseListener, ActionListener{

	/** The pop menu. */
	private JPopupMenu popMenu = new JPopupMenu();
    
    /** The copy. */
    private JMenuItem copy;
    
    /** The paste. */
    private JMenuItem paste;
    
    /** The clear. */
    private JMenuItem clear;
    
    /** The copyaction. */
    private static String COPYACTION="copyaction";
    
    /** The pasteaction. */
    private static String PASTEACTION="pasteaction";
    
    /** The clearaction. */
    private static String CLEARACTION="clearaction";
    

	/**
	 * Instantiates a new copy paste j text field.
	 */
	public CopyPasteJTextField(){
		super();
		setPopMenu();
		addMouseListener(this);
	}
	
	
	/**
	 * Sets the pop menu.
	 */
	private void setPopMenu(){
        
		copy=new JMenuItem(LangTools.getWordLanguage("Copy","files.copy"));
		copy.setActionCommand(COPYACTION);
		copy.addActionListener(this);
		
		paste=new JMenuItem(LangTools.getWordLanguage("Paste","files.paste"));
		paste.setActionCommand(PASTEACTION);
		paste.addActionListener(this);
		
		clear=new JMenuItem(LangTools.getWordLanguage("Clear","files.clear"));
		clear.setActionCommand(CLEARACTION);
		clear.addActionListener(this);
		
        popMenu.add(copy);
        popMenu.add(paste);
        popMenu.addSeparator();
        popMenu.add(clear);
        popMenu.setInvoker(this);
	}
	
	
	/**
	 * Pop menu.
	 *
	 * @param event the event
	 */
	private void popMenu(MouseEvent event) {

	    popMenu.show(event.getComponent(), event.getX(), event.getY());

	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.isPopupTrigger()) {

			  popMenu(e);

			    }
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		 if (e.isPopupTrigger()) {

			  popMenu(e);

			    }
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals(PASTEACTION)){
			this.paste();	
		}
		else if(cmd.equals(COPYACTION))
			this.copy();
		else if(cmd.equals(CLEARACTION))
			setText("");
		
	}

}
