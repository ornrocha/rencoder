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

import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.commands.FileInformationIOException;
import pt.ornrocha.rencoder.gui.components.panels.VideoMainPanel;
import pt.ornrocha.rencoder.gui.components.panels.auxiliar.VideoPanelUtils;
import pt.ornrocha.rencoder.gui.components.panels.info.SimpleTextMSGPanel;
import pt.ornrocha.rencoder.gui.components.panels.info.WarnJTextPanePanel;
import pt.ornrocha.rencoder.gui.components.tables.MovieTable;
import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.helpers.props.managers.GeneralEncodingPropertiesManager;
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
public class VideoScrollPanel extends JScrollPane implements DropTargetListener{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The movietable. */
    protected MovieTable movietable;
    //protected ArrayList<>

    /** The table. */
    protected JTable table;

    /** The movielabel. */
    protected String movielabel;

    /** The subtitlelabel. */
    protected String subtitlelabel;

    /** The convertlabel. */
    protected String convertlabel;

    private VideoMainPanel outpanel=null;

    private GeneralEncodingPropertiesManager propsmanager=null;

    private DropTarget dropTarget; 

    private boolean notproceed=true;

    private boolean checksubfolders=false;


    private ResourceBundle rb;

    private WarnJTextPanePanel warn=null;

    private JFrame mainframe;

    /**
     * Instantiates a new video scroll panel.
     */
    public VideoScrollPanel(VideoMainPanel mainpanel){
	this.outpanel=mainpanel;
	this.mainframe=outpanel.getMainframe();
	initGUI();
	try {
	    setLanguage();
	    initVideoTable();
	    setColumnWidth();
	    dropTarget = new DropTarget(this,DnDConstants.ACTION_COPY_OR_MOVE, this, true);
	    
	    this.setToolTipText(
			"<html><font size=\"4\", color=\"red\"><b>" + LangTools.getResourceBundleWordLanguage(rb,
			"Drop your files over the table", "maingui.dragdrop") + "</b></font></html>");

	} catch (MalformedURLException e) {
	    Logger.error(e);
	}



    }


    public void setEncodingPropertiesManager(GeneralEncodingPropertiesManager manager){
	this.propsmanager=manager;
    }


    /**
     * Sets the language.
     *
     * @throws MalformedURLException the malformed url exception
     */
    protected void setLanguage() throws MalformedURLException{
	this.rb = ResourceBundle.getBundle("lang",LangTools.getDefinedLanguage(), LangTools.loadLanguagesPath());

	this.movielabel = LangTools.getWordLanguage("Filename", "general.filename");
	this.subtitlelabel = LangTools.getWordLanguage("Subtitle", "videogui.subtitlefile");
	this.convertlabel = LangTools.getWordLanguage("Convert", "videogui.convert");
    }


    /**
     * Sets the column width.
     */
    protected void setColumnWidth(){
	TableColumnModel model = table.getColumnModel();
	double factor = table.getPreferredScrollableViewportSize().getWidth();
	model.getColumn(0).setPreferredWidth((int) (factor*0.6));
	model.getColumn(1).setPreferredWidth((int) (factor*0.2));
	model.getColumn(2).setPreferredWidth((int) (factor*0.2));

    }


    /**
     * Inits the video table.
     */
    protected void initVideoTable(){
	String[] colnames = {movielabel,subtitlelabel,convertlabel}; 
	this.movietable = new MovieTable(colnames, true);
	this.movietable.seteditfromcolumn(1);
	this.table = new JTable(){

	    public String getToolTipText(MouseEvent e) {
		String tip = null;
		java.awt.Point p = e.getPoint();
		int rowIndex = rowAtPoint(p);
		int colIndex = columnAtPoint(p);
		int realColumnIndex = convertColumnIndexToModel(colIndex);

		if (realColumnIndex == 0) {
		    TableModel model = getModel();
		    tip=(String) ((MovieTable)model).getValueAt(rowIndex,0);
		}

		return tip;
	    }
	};

	this.table.setModel(movietable);

	this.getViewport().add(table);
    }

    /**
     * Inits the gui.
     */
    private void initGUI() {
	try {
	    {
		this.setPreferredSize(new java.awt.Dimension(300, 260));
	    }
	} catch(Exception e) {
	    Logger.error(e);
	}
    }



    /**
     * Reset movie table.
     */
    public void resetMovieTable(){
	this.movietable.resetTable();

    }




    /**
     * Adds the movies to table.
     *
     * @param moviefiles the moviefiles
     */
    public void addMoviesToTable(ArrayList<Videofile> moviefiles){
	List<Object[]> listmovies = new ArrayList<>();
	for (Videofile videofile : moviefiles) {
	    Object[] obj = new Object[3];
	    obj[0]= videofile.getName();
	    obj[1]= videofile.haveSubtitle();
	    obj[2] = true;
	    listmovies.add(obj);
	}
	movietable.insertListOfData(listmovies);

    }

    /**
     * Gets the movie table.
     *
     * @return the movie table
     */
    public MovieTable getMovieTableModel(){
	return this.movietable;
    }

    /**
     * Gets the j tableof movies.
     *
     * @return the j tableof movies
     */
    public JTable getMoviesJTable(){
	return this.table;
    }

    /**
     * Sets the movie converted state to true.
     *
     * @param pos the new movie converted state to true
     */
    public void setMovieConvertedStateToTrue(int pos){
	movietable.setValueAt(true,pos,2);	
    }


    /**
     * Check if video contains subtitle.
     *
     * @param movie the movie
     * @param indexvideo the indexvideo
     */
    public void checkIfVideoContainsSubtitle(Videofile movie, int indexvideo){
	if(movie.getSubtitles()!=null){
	    if(movie.getSubtitles().size()>0)
		setStateofSubtitle(indexvideo, true);
	    else
		setStateofSubtitle(indexvideo, false);
	}
    }

    /**
     * Sets the stateof subtitle.
     *
     * @param index the index
     * @param state the state
     */
    private void setStateofSubtitle(int index, boolean state){
	movietable.setValueAt(state,index,1);
    }

    /**
     * Gets the movies to convert.
     *
     * @return the movies to convert
     */
    public IndexedHashMap<Integer, Boolean> getMoviesToConvert(){
	IndexedHashMap<Integer, Boolean> convmovies=new IndexedHashMap<>();
	int nrows = movietable.getRowCount();

	for (int i = 0; i < nrows; i++) {
	    boolean conv = (boolean) movietable.getValueAt(i, 2);
	    convmovies.put(i, conv);
	}
	return convmovies;
    }


    /**
     * Change state selected movies.
     *
     * @param bol the bol
     */
    public void ChangeStateSelectedMovies(boolean bol){
	int nrows = movietable.getRowCount();
	for (int i = 0; i < nrows; i++) {
	    movietable.setValueAt(bol, i, 2);
	}
    }



    @Override
    public void dragOver(DropTargetDragEvent dtde) {

    }

    private void addFileFromDragAndDrop(ArrayList<String> files) throws IOException{

	SimpleTextMSGPanel panel = VideoPanelUtils.getImportFilesWarningPanel(files,this.outpanel);
	ProcessFiles proc = new ProcessFiles(files, this.propsmanager);

	if(panel!=null){
	    VideoPanelUtils.RunProcessFilesInBackground(panel,proc);
	}
	else{
	    try {
		proc.processVideoFiles();
	    } catch (CloneNotSupportedException e) {
		Logger.error(e);
	    }
	}


	if(proc!=null){
	    ArrayList<Videofile> videos = proc.getFilteredVideoFiles();
	    this.outpanel.addVideoFiles(videos);
	}
    }



    @Override
    public void dragEnter(DropTargetDragEvent dtde) {

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


		boolean foundsubfolders = ListFiles.checkIfFoldersContainsSubFolders(files);
		if(foundsubfolders){

		    String msg = "Load files that are in subdirectories";
		    Object[] options = {LangTools.getResourceBundleWordLanguage(rb,"Yes","general.yes"),
			    LangTools.getResourceBundleWordLanguage(rb,"No","general.no")};
		    int n = JOptionPane.showOptionDialog(
			    mainframe,
			    LangTools.getWordLanguage(msg,"files.yesnopanelsubdirs"),
			    LangTools.getWordLanguage("Choose option","warngui.choose"),
			    JOptionPane.YES_NO_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,     
			    options, 
			    options[0]);

		    if(n==0){
			checksubfolders=true;
			notproceed=false;
		    }
		    else{
			checksubfolders=false;
			notproceed=false;
		    }

		}

		ArrayList<String> filestoinput= new ArrayList<>();

		for(int i = 0; i < files.size(); i++){ 
		    File f = files.get(i);
		    if(f.isDirectory()){
			ArrayList<String> filesin =null;

			if(checksubfolders){
			    filesin=ListFiles.getListofItemsInFolderAndSubFolders(f.getAbsolutePath()); 
			}
			else
			    filesin=ListFiles.getitemsInFolder(f.getAbsolutePath());

			if(filesin!=null){
			    filestoinput.addAll(filesin);
			}
		    }
		    else
			filestoinput.add(f.getAbsolutePath()); 
		}
		if(filestoinput.size()>0){
		    ArrayList<String> filesin =ListFiles.getFromSelecteditems(filestoinput);
		    ;
		    addFileFromDragAndDrop(filesin);

		}
	    }
	    else{
		dtde.rejectDrop();
	    }
	}
	catch(UnsupportedFlavorException | IOException e)
	{

	    if(e instanceof FileInformationIOException){
		String msg = ((FileInformationIOException)e).getMessage();
		warn=new WarnJTextPanePanel(msg,"Error reading file", this, new Dimension(600, 120));
	    }
	    else if(e instanceof IOException){
		String msg =rb.getString("errorffmpegfile");
		warn=new WarnJTextPanePanel(msg, this);
	    }
	    Logger.error(e);
	    dtde.rejectDrop();
	}

	resetStateDragAndDrop();
    }




    private void resetStateDragAndDrop(){
	this.notproceed=true;
	this.checksubfolders=false;
    }



}
