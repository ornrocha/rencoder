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


import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.gui.Maingui;
import pt.ornrocha.rencoder.gui.components.panels.VideoMainPanel;
import pt.ornrocha.rencoder.gui.components.tables.SubtitleTable;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.mediafiles.files.containers.base.Subtitlefile;
import pt.ornrocha.rencoder.mediafiles.files.containers.base.Videofile;
import pt.ornrocha.rencoder.mediafiles.setfiles.foldersandfiles.ListFiles;
import pt.ornrocha.rencoder.mediafiles.setfiles.processfiles.ProcessFiles;


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
public class SubtitleScrollPanel extends JScrollPane implements ListSelectionListener,DropTargetListener{


	/** The Constant serialVersionUID. */
	protected static final long serialVersionUID = 1L;
	
	/** The sublabel. */
	protected String sublabel;
	
	/** The usethissub. */
	protected String usethissub;
	
	
	/** The subs table. */
	protected SubtitleTable subsTable;
	
	/** The table. */
	protected JTable table;
    
    /** The selectedvideo. */
    protected Videofile selectedvideo;
	
   // protected String[] languagesISO = null;
	/** The selected subtitle index. */
   protected int selectedSubtitleIndex =-1;
	
	/** The removingprocess. */
	protected boolean removingprocess=false;
	
	protected VideoMainPanel outpanel;
	
	private DropTarget dropTarget;
	
	 
	 private ResourceBundle rb;
	 private JFrame mainframe;
	
	/**
	 * Instantiates a new subtitle scroll panel.
	 */
	public SubtitleScrollPanel(VideoMainPanel mainpanel) {
        this.outpanel=mainpanel;
        if(mainpanel==null)
        	this.mainframe=Maingui.getInstance();
        else
        	this.mainframe=mainpanel.getMainframe();
        rb = ResourceBundle.getBundle("lang",LangTools.getDefinedLanguage(), LangTools.loadLanguagesPath());
		initGUI();
		setLanguage();
		initSubtitleTable();
		setColumnWidth();
		table.getSelectionModel().addListSelectionListener(this);
		dropTarget = new DropTarget(this,DnDConstants.ACTION_COPY_OR_MOVE, this, true);
		
	}
	
	
	/**
	 * Inits the gui.
	 */
	protected void initGUI() {
		try {
			{
				this.setPreferredSize(new java.awt.Dimension(276, 145));
			}
		} catch(Exception e) {
		    Logger.error(e);
		}
	}
	

	
    /**
	 * Gets the subs table.
	 *
	 * @return the subs table
	 */
	public SubtitleTable getSubsTable(){
    	return this.subsTable;
    }
    
    /**
     * Gets the table.
     *
     * @return the table
     */
    public JTable getTable(){
    	return this.table;
    }
	
	/**
	 * Gets the selected subtitle index.
	 *
	 * @return the selected subtitle index
	 */
	public int getSelectedSubtitleIndex(){
		return this.selectedSubtitleIndex;
	}
    
	/**
	 * Sets the selected video.
	 *
	 * @param movie the new selected video
	 */
	public void setSelectedVideo(Videofile movie){
		this.selectedvideo=movie;
	}
	
	/**
	 * Gets the selectedvideo.
	 *
	 * @return the selectedvideo
	 */
	public Videofile getSelectedvideo(){
		return this.selectedvideo;
	}
	
	
	/**
	 * Sets the language.
	 */
	protected void setLanguage(){
		
		this.sublabel = LangTools.getWordLanguage("Filename", "general.filename");
		this.usethissub = LangTools.getWordLanguage("Use subtitle", "subtitlesgui.use");
		
	}

	
	/**
	 * Sets the column width.
	 */
	protected void setColumnWidth(){
		TableColumnModel model = table.getColumnModel();
		double factor = table.getPreferredScrollableViewportSize().getWidth();
		model.getColumn(0).setPreferredWidth((int) (factor*0.7));
	    model.getColumn(1).setPreferredWidth((int) (factor*0.3));

	    
	}
	
	
	/**
	 * Inits the subtitle table.
	 */
	protected void initSubtitleTable(){

		String[] colnames = {this.sublabel,this.usethissub}; 
		this.subsTable = new SubtitleTable(colnames, true);
		this.table = new JTable(){
          
            public String getToolTipText(MouseEvent e) {
                String tip = null;
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);
                int realColumnIndex = convertColumnIndexToModel(colIndex);

                if (realColumnIndex == 0) {
                	TableModel model = getModel();
                	tip=(String) ((SubtitleTable)model).getValueAt(rowIndex,0);
                   }
                return tip;
            }
        };

		this.table.setModel(this.subsTable);
		
		this.getViewport().add(table);
	
	}
	
	
	/**
	 * Reset subtitle table.
	 */
	public void resetSubtitleTable(){
		this.subsTable.resetTable();
	}
	

	
	/**
	 * Adds the subtitles to table.
	 *
	 * @param subtitlefiles the subtitlefiles
	 */
	public void addSubtitlesToTable(ArrayList<Subtitlefile> subtitlefiles){
		boolean subassfound=false;
		boolean subselected=false;
		List<Object[]> listsubs = new ArrayList<>();
		JComboBox<String> box = new JComboBox<>();
		int pos=0;
		for (Subtitlefile sub : subtitlefiles) {
			Object[] obj = new Object[2];
			obj[0]= sub.getName();
			
			if(!subselected){
				if(!sub.isToUse()){
					obj[1] = false;
					subselected=false;
				}
				else if(sub.isToUse()){
					obj[1] = true;
					subselected=true;
				}
				else{
				obj[1] = true;
				subselected=true;
				sub.selectToUse(true);
				}
					
			}
			else if(subselected){
				if(sub.isToUse()){
					obj[1] = true;
				}
			}
			
			pos++;
			listsubs.add(obj);
		 }
		subsTable.insertListOfData(listsubs);	
	}
	
	
	/**
	 * Adds the single subtitle to table.
	 *
	 * @param sub the sub
	 */
	public void addSingleSubtitleToTable(Subtitlefile sub){
		Object[] obj = new Object[2];
		obj[0]= sub.getName();
		if(subsTable.getRowCount()<1)
			obj[1] = true;
		else
			obj[1] = false;
		subsTable.insertSingleObjectData(obj);
		
	}
	
	
	
	/**
	 * Sets the stateforinactive.
	 *
	 * @param listsubs the listsubs
	 * @return the array list
	 */
	private ArrayList<Object[]> setStateforinactive(List<Object[]> listsubs){
		List<Object[]> newlist = new ArrayList<>();
		for (Object[] objects : listsubs) {
			Object[] newobj= new Object[2];
			newobj[0]=objects[0];
			newobj[1]=false;
			newlist.add(newobj);
		}
		return (ArrayList<Object[]>) newlist;
	}
	
	/**
	 * Sets the use subtitles tofalse.
	 *
	 * @param pos the new use subtitles tofalse
	 */
	private void setUseSubtitlesTofalse(int pos){
		ArrayList<Subtitlefile> subs = selectedvideo.getSubtitles();
		for (int i = 0; i < pos; i++) {
			subs.get(i).selectToUse(false);
		}
		
	}
	
	/**
	 * Uselegend.
	 *
	 * @param pos the pos
	 */
	public void uselegend(int pos){
		subsTable.ResetSelectSubtitlesState();
		subsTable.setValueAt(true, pos, 1);
		
	}
	

	
	/* (non-Javadoc)
	 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(!removingprocess){
		int row = table.getSelectedRow();
		this.selectedSubtitleIndex =row;
		checkselectedSubs();
		
		}
		
	}
	
	/**
	 * Removes the subtitle in table.
	 */
	public void removeSubtitleInTable(){
		if(this.selectedSubtitleIndex>-1){
		this.removingprocess=true;	
		this.subsTable.removeRowAtPos(this.selectedSubtitleIndex);
		this.selectedvideo.RemoveSubtitle(this.selectedSubtitleIndex);
		this.removingprocess=false;
		}
	}
	
	
   /**
    * Checkselected subs.
    */
   protected void checkselectedSubs(){
	   int nrows = subsTable.getRowCount();
	 

	   if(subsTable!=null && nrows>0){
		   for (int i = 0; i < nrows; i++) {
			    Object objval = subsTable.getValueAt(i, 1);
			    if(objval!=null){
			      boolean val = (boolean) objval;
			      this.selectedvideo.changeUseSubtitleState(i, val);
			    }
			  
		    } 
	   }
   }
   

   /**
    * Move sub up.
    *
    * @param index the index
    */
   public void moveSubUp(int index){
		ArrayList<Subtitlefile> subs= selectedvideo.getSubtitles();
		if(index>0 && subs.size()>1){
		 selectedvideo.moveSubIndexUp(index);
		 this.subsTable.moveElementUp(index);
		}

	}

	/**
	 * Move sub down.
	 *
	 * @param index the index
	 */
	public void moveSubDown(int index){
		ArrayList<Subtitlefile> subs= selectedvideo.getSubtitles();
		if(index>=0 && index+1<subs.size() && subs.size()>1){
		 selectedvideo.moveSubIndexDown(index);
		 this.subsTable.moveElementDown(index);
		}
	}


	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void dragOver(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void dragExit(DropTargetEvent dte) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void drop(DropTargetDropEvent dtde) {
		
	
		try
	    {
	      if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
              dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
              Transferable t = dtde.getTransferable();

              List<File> data = (List<File>)t.getTransferData(DataFlavor.javaFileListFlavor);
              ArrayList<File> files = new ArrayList<>();
              for (File file : data) {
				 files.add(file);
			   }
              
          	 
              if(this.outpanel.getMovieFiles()!=null){
     		     if(this.outpanel.getSelectedMovieIndex()>-1){
     		        ArrayList<Subtitlefile> subsin=ProcessFiles.filterallsubtitlesfiles(files);
     		        
     		       if(subsin!=null){
     		    	   for (Subtitlefile subfile : subsin) {
						  this.outpanel.addSubtitleFile(subfile);
					  }
     		          
     		       }
     		   }
     		   else{
     			  JOptionPane.showMessageDialog(mainframe,
							LangTools.getResourceBundleWordLanguage(rb,"Please select a movie","warngui.selectmovie"),
							LangTools.getResourceBundleWordLanguage(rb,"Warning","warngui.tag"),
						    JOptionPane.INFORMATION_MESSAGE,
						    new ImageIcon(ListFiles.getIconAbsolutePath("icons/warning64x64.png")));
     			   dtde.rejectDrop();
     		   }
     		}
             else{
            	  
            	 JOptionPane.showMessageDialog(mainframe,
							LangTools.getResourceBundleWordLanguage(rb,"Movie list its empty","warngui.emptylist"),
							LangTools.getResourceBundleWordLanguage(rb,"Warning","warngui.tag"),
						    JOptionPane.INFORMATION_MESSAGE,
						    new ImageIcon(ListFiles.getIconAbsolutePath("icons/warning64x64.png")));
     		   dtde.rejectDrop();
             }
             
	      }
	      else{
	    	  dtde.rejectDrop();
	      }
	    }
	    catch(Exception exception)
	    {
	      Logger.error(exception);
	      dtde.rejectDrop();
	    }
	
		
	}   
   
   



}
