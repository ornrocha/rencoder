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
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.commands.FileInformationIOException;
import pt.ornrocha.rencoder.gui.components.panels.auxiliar.ListContainer;
import pt.ornrocha.rencoder.gui.components.panels.info.WarnJTextPanePanel;
import pt.ornrocha.rencoder.gui.components.tables.MovieTable;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.helpers.props.managers.GeneralEncodingPropertiesManager;
import pt.ornrocha.rencoder.mediafiles.files.containers.base.Videofile;
import pt.ornrocha.rencoder.mediafiles.setfiles.foldersandfiles.ListFiles;
import pt.ornrocha.rencoder.mediafiles.setfiles.processfiles.ProcessFiles;


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
public class MergeVideosScrollpanel extends JScrollPane implements DropTargetListener, ListSelectionListener{
	
	
	private DropTarget dropTarget; 
	private MovieTable movietable;
	protected String movietablelabel=LangTools.getWordLanguage("Name of File", "mergegui.nameoffile");
	protected JTable table;
	private GeneralEncodingPropertiesManager propsmanager=null;
	private ListContainer<Videofile> movieslist = null;
	private WarnJTextPanePanel warn=null;
	private boolean sameextall=true;
	private String lastmovieext=null;
	private PropertyChangeSupport changelst =null;
	
	private int selectedrow =-1;
	private int[] selectedrows=null;
	
	public static final String NEWMOVIESADDED="newmoviesadded";
	
	public MergeVideosScrollpanel(){
		initGUI();
		initVideoTable();
		this.table.getSelectionModel().addListSelectionListener(this);
		dropTarget = new DropTarget(this,DnDConstants.ACTION_COPY_OR_MOVE, this, true);
	}
	
	
	
	
	
	private void initGUI() {
		try {
			{
				this.setPreferredSize(new java.awt.Dimension(375, 352));
			}
		} catch(Exception e) {
		    Logger.error(e);
		}
	}
	
	
	public void addProcessPropertyChangeListener(PropertyChangeListener listener) {
		this.changelst= new PropertyChangeSupport(this);
		this.changelst.addPropertyChangeListener(listener);
     }
	
	private void setNewAddedMoviesFireEvents(){
		   changelst.firePropertyChange(NEWMOVIESADDED,false,true);
	    }

	public void setEncodingPropertiesManager(GeneralEncodingPropertiesManager manager){
		this.propsmanager=manager;
	}
	
	protected void initVideoTable(){
		String[] colnames = {movietablelabel}; 
		this.movietable = new MovieTable(colnames, true);
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
		
		
		//this.table.add
		 this.getViewport().add(table);
	}
	
	
	
	public void addMovies(ArrayList<Videofile> moviefiles){
		List<Object[]> listmovies = new ArrayList<>();
		for (Videofile videofile : moviefiles) {
			Object[] obj = new Object[1];
			obj[0]= videofile.getName();
			listmovies.add(obj);
			checkExtension(videofile);
		 }
		movietable.insertListOfData(listmovies);
		if(movieslist!=null){
			movieslist.insertItems(moviefiles);
		}
		else
			this.movieslist= new ListContainer<>(moviefiles);
			
		setNewAddedMoviesFireEvents();	
	}

    
	
	private void checkExtension(Videofile mov){
		if(sameextall){
			String ext = mov.getExtension();
			if(lastmovieext==null)
				lastmovieext=ext;
			else{
				if(!lastmovieext.equals(ext))
					this.sameextall=false;
					
			}	
		}
	}
	
	public void moveUp(){
		if(selectedrow>0){
			this.movietable.moveElementUp(selectedrow);
			this.movieslist.moveElementUp(selectedrow);
			selectedrow--;
		}	
	}
	
	public void moveDown(){
		if(selectedrow>-1 && selectedrow<movieslist.getListSize()-1){
			this.movietable.moveElementDown(selectedrow);
			this.movieslist.moveElementDown(selectedrow);
			selectedrow++;
		}
	}
	
	public void deleteRows(){
		if(selectedrow>-1){
			this.movietable.removeRowAtPos(selectedrow);
			this.movieslist.removeElem(selectedrow);
		}
		else if(selectedrows!=null){
			int[] selected = selectedrows;
			this.movietable.removeRowsAtPos(selected);
			this.movieslist.removeElems(selected);
		}
			
	}
	
	public void ClearList(){
		if(this.movieslist!=null && this.movieslist.getListSize()>0){
			this.movietable.resetTable();
			this.movieslist.reset();
		}
	}
	
	
	public boolean isextensionconsistent(){
		return this.sameextall;
	}
	
	public String getLastMovieExt(){
		return this.lastmovieext;
	}
	
	
	public ArrayList<String> getMoviesFilePaths(){
		ArrayList<String> list = new ArrayList<>();
		if(this.movieslist!=null){
			if(movieslist.getListSize()>0){
				for (Videofile f : movieslist.getList()) {
					list.add(f.getFilePath());
				}
			}
		}
		return list;
	}
	
	public ArrayList<Videofile> getListOfMovies(){
		return (ArrayList<Videofile>) movieslist.getList();
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
              
              ArrayList<String> filestoinput= new ArrayList<>();
              
              for(int i = 0; i < files.size(); i++){ 
              	File f = files.get(i);
              	if(f.isDirectory()){
              		ArrayList<String> filesin =ListFiles.getitemsInFolder(f.getAbsolutePath());
              		
              		if(filesin!=null){
              			addFileFromDragAndDrop(filesin);
              		}
              	}
              	else
              		filestoinput.add(f.getAbsolutePath()); 
              }
              if(filestoinput.size()>0){
              	ArrayList<String> filesin =ListFiles.getFromSelecteditems(filestoinput);
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
	            String msg = ((FileInformationIOException)e).getErrorFile();
	            String errorinput="The following file it is corrupted:";
	            msg=LangTools.getWordLanguage(errorinput, "warngui.errorinputfile")+" "+msg;
	    		warn=new WarnJTextPanePanel(msg,LangTools.getWordLanguage("Corrupted File","warngui.filecorrupt"), this, new Dimension(600, 120));
	            
	            
	    	}
	    	else if(e instanceof IOException){
	    		String errorffmpeg="Please check FFmpeg settings";
	    		String msg =LangTools.getWordLanguage(errorffmpeg, "warngui.errorffmpegfile");
	    		warn=new WarnJTextPanePanel(msg, this);
	    	}
	    		
	    	
	      dtde.rejectDrop();
	    }
		
	}
	
	private void addFileFromDragAndDrop(ArrayList<String> files) throws IOException{

		ProcessFiles proc = new ProcessFiles(files, this.propsmanager);

		if(proc!=null){
		   ArrayList<Videofile> videos = proc.getFilteredVideoFiles();
		   addMovies(videos);
		}
	}





	@Override
	public void valueChanged(ListSelectionEvent e) {

		  int[] rows = table.getSelectedRows();
		 if(rows.length>-1 && rows.length==1){
			selectedrow=rows[0];
			selectedrows=null;
		 }
		 else if(rows.length>-1 && rows.length>1){
			selectedrows=rows;
			selectedrow=-1; 
		 }
		
	}

}
