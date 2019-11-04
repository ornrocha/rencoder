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
package pt.ornrocha.rencoder.gui.components.panels.info;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;

import javax.swing.JPanel;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.subtitles.SubtitlesFontStyle;

// TODO: Auto-generated Javadoc
/**
 * The Class FontDisplayPanel.
 */
public class FontDisplayPanel extends JPanel{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	
	/** The fontname. */
	String fontname;
    
    /** The fontstyle. */
    int fontstyle;
    
    /** The fontsize. */
    int fontsize;

	
    
    /**
     * Instantiates a new font display panel.
     */
    public FontDisplayPanel(){
    	
    	fontname = "Arial";
    	fontstyle = Font.PLAIN;
	    fontsize = 24;
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

    	
    }
    
    
    /* (non-Javadoc)
     * @see javax.swing.JComponent#update(java.awt.Graphics)
     */
    public void update(Graphics g) {
	      g.clearRect(0, 0, getWidth(), getHeight());
	      paintComponent(g);
	    }
    
    
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {
	      super.paintComponent(g);
	      Graphics2D g2D = (Graphics2D) g;
	      g2D.setFont(new Font(fontname, fontstyle, fontsize));
	      g2D.drawString("Subtitle Font Aspect", 100, 45);
	}
	
	/**
	 * Sets the fontname.
	 *
	 * @param fname the new fontname
	 */
	public void setFontname(String fname){
		this.fontname=fname;
	}
	
	/**
	 * Sets the font style.
	 *
	 * @param fontSt the new font style
	 */
	public void setFontStyle(SubtitlesFontStyle fontSt){
		this.fontstyle=fontSt.getPreviewID();
	}
	
	
	/**
	 * Sets the font size.
	 *
	 * @param size the new font size
	 */
	public void setFontSize(int size){
		
		this.fontsize=size;
	}

}
