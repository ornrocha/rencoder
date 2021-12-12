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
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import org.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.configurations.FFmpegLogInfoContainer;
import pt.ornrocha.rencoder.ffmpegWrapper.execution.ExecFilesInfoContainer;
import pt.ornrocha.rencoder.ffmpegWrapper.execution.FFmpegProcess;
import pt.ornrocha.rencoder.ffmpegWrapper.execution.KillFFmpegProcess;
import pt.ornrocha.rencoder.ffmpegWrapper.execution.ShutdownProcess;
import pt.ornrocha.rencoder.ffmpegWrapper.execution.progress.FFmpegProcessProgressBar;
import pt.ornrocha.rencoder.ffmpegWrapper.execution.progress.ProgressBarUpdate;
import pt.ornrocha.rencoder.gui.components.panels.merge.ConversionMergeListener;
import pt.ornrocha.rencoder.gui.components.tables.FFmpegProgressTable;
import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.helpers.osystem.OSystem;
import pt.ornrocha.rencoder.helpers.props.managers.auxiliar.EncodingPropsAuxiliar;


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
public class EncodingProgressPanel extends JFrame implements ListSelectionListener,ActionListener,Runnable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The j panelmain. */
	private JPanel jPanelmain;
	
	/** The j scroll panemain. */
	private JScrollPane jScrollPanemain;
	
	/** The j check boxshutdown. */
	private JCheckBox jCheckBoxshutdown;
	
	/** The j buttoncancel all. */
	private JButton jButtoncancelAll;
	
	/** The numbthreads. */
	private int numbthreads=1;
	
	/** The table. */
	private  JTable table;
	
	/** The tmodel. */
	private FFmpegProgressTable tmodel =null;
	
	/** The executor. */
	private ThreadPoolExecutor executor = null;
	
	/** The numberof process. */
	private int numberofProcess=0;
	
	/** The processmapcontroller. */
	private IndexedHashMap<Integer, FFmpegProcess> processmapcontroller=new IndexedHashMap<>();
	
	/** The selectedrows. */
	private int[] selectedrows=null;
	
	/** The selectedrow. */
	private int selectedrow=0;
	
	/** The j buttonclose. */
	private JButton jButtonclose;

	/** The moviesencinfo. */
	private IndexedHashMap<String, ArrayList<ArrayList<String>>> moviesencinfo=null;
	
	/** The tempsubs file path. */
	private HashMap<String, String> tempsubsFilePath=null;
	
	/** The temp log file paths. */
	private HashMap<String, String> tempLogFilePaths=null;
	
	/** The outputmoviefilepaths. */
	private IndexedHashMap<String, String> outputmoviefilepaths=null;
	
	
	private ArrayList<FFmpegLogInfoContainer> logsinfo=null;
	

	private ArrayList<ShutdownProcess> shutdownProc=null;
	private int nshutdowns=0;
	
	/** The rb. */
	private ResourceBundle rb;
	
	
	/** The cancelprocesses. */
	private static String CANCELPROCESSES="cancelprocesses";
	
	/** The cancelandcloseprocesses. */
	private static String CANCELANDCLOSEPROCESSES="cancelandcloseprocesses";
	
	public static String CLOSEPROCESSES="closeencodingprocesses";
	
	/** The debug. */
	private boolean debug=false;
	
	private static String SHUTDOWNTAG="shutdowntag";
	
	private  WarnJTextPanePanel warnPanel=null;
	
	private PropertyChangeSupport changelst =null;
	
	private ConversionMergeListener convlistener=null;
	
	
	
  public EncodingProgressPanel(ExecFilesInfoContainer encodinginfocontainer){
		
		this.moviesencinfo=encodinginfocontainer.getMoviesencinfo();
		this.tempsubsFilePath=encodinginfocontainer.getTempsubspath();
		this.tempLogFilePaths=encodinginfocontainer.getTemplogpaths();
		this.outputmoviefilepaths=encodinginfocontainer.getMoviepaths();
		this.logsinfo=encodinginfocontainer.getLogsinfo();
		this.numbthreads=EncodingPropsAuxiliar.getNumberSimultaneousConversions();
		rb = ResourceBundle.getBundle("lang",LangTools.getDefinedLanguage(), LangTools.loadLanguagesPath());
		String iconpath = new File("icons/rencoderblue.png").getAbsolutePath();
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(iconpath));
		initGUI();

		InitializeTableEncodingProgress();
		setEncodingProgressRenderer();
		setCloseWindowListener();

	}
  
  public EncodingProgressPanel(ExecFilesInfoContainer encodinginfocontainer, ConversionMergeListener convlistener, boolean mergeafter){
		
		this.moviesencinfo=encodinginfocontainer.getMoviesencinfo();
		this.tempsubsFilePath=encodinginfocontainer.getTempsubspath();
		this.tempLogFilePaths=encodinginfocontainer.getTemplogpaths();
		this.outputmoviefilepaths=encodinginfocontainer.getMoviepaths();
		this.logsinfo=encodinginfocontainer.getLogsinfo();
		this.numbthreads=EncodingPropsAuxiliar.getNumberSimultaneousConversions();
		rb = ResourceBundle.getBundle("lang",LangTools.getDefinedLanguage(), LangTools.loadLanguagesPath());
		String iconpath = new File("icons/rencoderblue.png").getAbsolutePath();
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(iconpath));
		initGUI();
		InitializeTableEncodingProgress();
		setEncodingProgressRenderer();
		setCloseWindowListener();
		this.convlistener=convlistener;
		if(mergeafter){
			this.jCheckBoxshutdown.setVisible(false);
			this.jButtoncancelAll.setVisible(false);
		}

	}
  
  
  private void setCloseWindowListener(){
	  this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	checkifexistrunningprocesses();
		    	shutdownExecutor();
		    	setCloseEncodindProcessesFireEvents();
		        dispose();
		    }
		});
  }
	
    public void addProcessesPropertyChangeListener(PropertyChangeListener listener) {
		this.changelst= new PropertyChangeSupport(this);
		this.changelst.addPropertyChangeListener(listener);
     }
  
    private void setCloseEncodindProcessesFireEvents(){
	   changelst.firePropertyChange(CLOSEPROCESSES,false,true);
    }
	
	/**
	 * Sets the thread pool executor.
	 */
	private void setThreadPoolExecutor(){
		this.executor=(ThreadPoolExecutor) Executors.newFixedThreadPool(numbthreads);
		
	}
	
	public ThreadPoolExecutor getexecutor(){
		return this.executor;
	}
	
	/**
	 * Initialize table encoding progress.
	 */
	private void InitializeTableEncodingProgress(){
		String[] colname = {LangTools.getWordLanguage("Movie", "progtable.movie"),LangTools.getWordLanguage("Progress", "progtable.progress")};
		tmodel=new FFmpegProgressTable(colname, false);
		table=new JTable(tmodel){
          
            public String getToolTipText(MouseEvent e) {
                String tip = null;
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);
                int realColumnIndex = convertColumnIndexToModel(colIndex);

                if (realColumnIndex == 0) {
                	TableModel model = getModel();
                	tip=((FFmpegProgressTable)model).getTipIndex(rowIndex);
                   }
                return tip;
            }
        };
     
		
		jScrollPanemain.getViewport().add(table);
		setColumnWidth();
	
	}
	
	/**
	 * Sets the column width.
	 */
	private void setColumnWidth(){
		TableColumnModel model = table.getColumnModel();
		double factor = table.getPreferredScrollableViewportSize().getWidth();
		model.getColumn(0).setPreferredWidth((int) (factor*0.6));
	    model.getColumn(1).setPreferredWidth((int) (factor*0.4));
  
	}
	
	
	/**
	 * Sets the encoding progress renderer.
	 */
	private void setEncodingProgressRenderer(){
        TableColumn column  = table.getColumnModel().getColumn(1);
        column.setCellRenderer(new FFmpegProcessProgressBar());
	}
	


	
	/**
	 * Execute f fmpeg process.
	 *
	 * @param origmoviepath the movie name
	 * @param cmd1pass the cmd1pass
	 * @param cmd2pass the cmd2pass
	 * @param outmoviefilepath the moviefilepath
	 */
	private void executeFFmpegProcess(String origmoviepath, ArrayList<String> cmd1pass,ArrayList<String> cmd2pass, String outmoviefilepath, FFmpegLogInfoContainer movloginfo){
		FFmpegProcess proc =null;
		ProgressBarUpdate update1pass = new ProgressBarUpdate();
		ProgressBarUpdate update2pass = null;
		if(debug)
			System.out.println(cmd1pass);
		if(cmd2pass==null){
		    proc = new FFmpegProcess(cmd1pass, new KillFFmpegProcess(), update1pass, outmoviefilepath, movloginfo);
		}
		else{
			if(debug)
				System.out.println(cmd2pass);
			update2pass= new ProgressBarUpdate();
			proc = new FFmpegProcess(cmd1pass,cmd2pass, new KillFFmpegProcess(), update1pass,update2pass, outmoviefilepath, movloginfo);
		}
			
		
		if(this.tempsubsFilePath.containsKey(origmoviepath))
			proc.setTempSubPath(this.tempsubsFilePath.get(origmoviepath));
		
		if(update2pass!=null){
			if(this.tempLogFilePaths.containsKey(origmoviepath))
				proc.setTempLogPath(this.tempLogFilePaths.get(origmoviepath));
		}
			
		
		this.processmapcontroller.put(numberofProcess, proc);
		
		String moviename=Paths.get(origmoviepath).getFileName().toString();
		if(update2pass!=null)
		   tmodel.setNewProcessInTable(moviename,origmoviepath, update1pass,update2pass);
		else
		   tmodel.setNewProcessInTable(moviename,origmoviepath, update1pass,null);
		this.executor.execute(proc);
		numberofProcess++;
		
	}
	
	
	/**
	 * Execute f fmpeg processes.
	 */
	private void executeFFmpegProcesses(){
	
		for (int i = 0; i < moviesencinfo.size(); i++) {
			ArrayList<ArrayList<String>> cmds = moviesencinfo.getValueAt(i);
			if(cmds.size()==1)
				executeFFmpegProcess(moviesencinfo.getKeyAt(i), cmds.get(0),null, outputmoviefilepaths.getValueAt(i), logsinfo.get(i));
			else if (cmds.size()==2){
				executeFFmpegProcess(moviesencinfo.getKeyAt(i), cmds.get(0),cmds.get(1), outputmoviefilepaths.getValueAt(i),logsinfo.get(i));	
				
			}
			setShutdownProcessListener();

		}
	}
	
	public void addProcessesToConvert(ExecFilesInfoContainer encodinginfocontainer){
		IndexedHashMap<String, ArrayList<ArrayList<String>>> newmoviesencinfo=encodinginfocontainer.getMoviesencinfo();
		HashMap<String, String> newtempsubsFilePath=encodinginfocontainer.getTempsubspath();
		HashMap<String, String> newtempLogFilePaths=encodinginfocontainer.getTemplogpaths();
		IndexedHashMap<String, String> newoutputmoviefilepaths=encodinginfocontainer.getMoviepaths();
		ArrayList<FFmpegLogInfoContainer> newlogsinfo=encodinginfocontainer.getLogsinfo();
		
		this.tempsubsFilePath.putAll(newtempsubsFilePath);
		this.tempLogFilePaths.putAll(newtempLogFilePaths);
		
		for (int i = 0; i < newmoviesencinfo.size(); i++) {
			ArrayList<ArrayList<String>> cmds = newmoviesencinfo.getValueAt(i);
			if(cmds.size()==1)
				executeFFmpegProcess(newmoviesencinfo.getKeyAt(i), cmds.get(0),null, newoutputmoviefilepaths.getValueAt(i), newlogsinfo.get(i));
			else if (cmds.size()==2){
				executeFFmpegProcess(newmoviesencinfo.getKeyAt(i), cmds.get(0),cmds.get(1), newoutputmoviefilepaths.getValueAt(i),newlogsinfo.get(i));	
				
			}
		}
		
		setShutdownProcessListener();
	}
	
	private void setShutdownProcessListener(){
		
		ShutdownProcess shutdownprocess=new ShutdownProcess();
		
		if(shutdownProc==null){
			this.shutdownProc=new ArrayList<>();
			jCheckBoxshutdown.addActionListener(shutdownprocess.getListener());
			this.shutdownProc.add(shutdownprocess);
		}
		else{
			ShutdownProcess oldshutdown= shutdownProc.get(nshutdowns-1);
			boolean isrunoldshutdown = oldshutdown.isRunShutdown();
			ActionListener rmlistener = oldshutdown.getListener();
			jCheckBoxshutdown.removeActionListener(rmlistener);
			oldshutdown.setCancel(true);
			
			
			jCheckBoxshutdown.addActionListener(shutdownprocess.getListener());
			if(isrunoldshutdown)
				shutdownprocess.setRunShutdown(true);
			this.shutdownProc.add(shutdownprocess);
		}

		jCheckBoxshutdown.setActionCommand(SHUTDOWNTAG);

		this.nshutdowns++;
		this.executor.execute(shutdownprocess);	
		
	}
	

	/* (non-Javadoc)
	 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		int rows = this.table.getSelectedRow();

		  this.selectedrow=rows;

		}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	     String action = e.getActionCommand();
	     
	     if(action.equals(CANCELPROCESSES)){
	    	 checkifexistrunningprocesses();
	    	 shutdownExecutor();
	    	 deleteTempSubsIfExist();
	     }
	     else if(action.equals(CANCELANDCLOSEPROCESSES)){
	    	 checkifexistrunningprocesses();
	    	 shutdownExecutor();
	    	 deleteTempSubsIfExist();
	    	 setCloseEncodindProcessesFireEvents();
	    	 this.dispose();
	     }
	   else if(action.equals(SHUTDOWNTAG)){
		   if(OSystem.isLinux() || OSystem.isMacOS()){
	    	 if(!OSystem.isAdmin()){
	    		 jCheckBoxshutdown.setSelected(false);
	    		 String adminrights="This feature needs administrator (root) privileges, please start Rencoder using administrator rights";
	    		 warnPanel=new WarnJTextPanePanel(LangTools.getResourceBundleWordLanguage(rb,adminrights,"warngui.shutdownrightslinux"), this);
	    	 }
		   }
	     }
		
	}
	
	/**
	 * Checkifexistrunningprocesses.
	 */
	private void checkifexistrunningprocesses(){
		
		int ntasks = (int) this.executor.getTaskCount();
		int completetasks = (int) this.executor.getCompletedTaskCount();
		
		if(ntasks>completetasks)
			cancelAllFFmpegProcesses();
	}
	
	public boolean isprocessesnotfinished(){
		boolean notfinished = true;
		if(this.executor!=null){
		int ntasks = (int) this.executor.getTaskCount();
		int completetasks = (int) this.executor.getCompletedTaskCount();
		if(completetasks==ntasks)
			notfinished=false;
		}

		return notfinished;
	}
	
	/**
	 * Shutdown executor.
	 */
	private void shutdownExecutor(){
		executor.shutdown();
		try {
			while (!executor.awaitTermination(100, TimeUnit.SECONDS)) {
				Thread.sleep(100);
			}
			

		} catch (InterruptedException e) {
		    Logger.error(e);
		}
	}
	
    public void stopAllProcessesAndClose(){
    	checkifexistrunningprocesses();
    	shutdownExecutor();
    	deleteTempSubsIfExist();
    	this.dispose();
    }

    
    
    public void deleteTempSubsIfExist(){
    	try {
			OSystem.deleteSubtitlesTempFolder();
		} catch (IOException e) {
		    Logger.error(e);
		}

    }
    
    

	
	/**
	 * Cancel all ffmpeg processes.
	 */
	public void cancelAllFFmpegProcesses(){
		for (int i = 0; i < processmapcontroller.size(); i++) {
			if(OSystem.isWindows())
				processmapcontroller.get(i).getKiller().setIscanceloperation(true);
			processmapcontroller.get(i).stopProcess();
		}
		
		if(this.shutdownProc!=null){
			for (ShutdownProcess shut : shutdownProc) {
				shut.setCancel(true);
			}
		}

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
				jPanelmainLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				jPanelmainLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
				jPanelmainLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				jPanelmainLayout.columnWidths = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
				jPanelmain.setLayout(jPanelmainLayout);
				this.setTitle(LangTools.getResourceBundleWordLanguage(rb, "Current processes in execution","videogui.processpanel"));
				{
					jScrollPanemain = new JScrollPane();
					jPanelmain.add(jScrollPanemain, new GridBagConstraints(0, 1, 12, 11, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					jButtoncancelAll = new JButton();
					jPanelmain.add(jButtoncancelAll, new GridBagConstraints(4, 12, 4, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jButtoncancelAll.setText(LangTools.getResourceBundleWordLanguage(rb,"Cancel","general.cancel"));
					jButtoncancelAll.setActionCommand(CANCELPROCESSES);
					jButtoncancelAll.addActionListener(this);
					
				}
				{
					jCheckBoxshutdown = new JCheckBox();
					jPanelmain.add(jCheckBoxshutdown, new GridBagConstraints(0, 13, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					jCheckBoxshutdown.setText(LangTools.getResourceBundleWordLanguage(rb,"Shutdown computer after finish","progtable.shutdowncomputer"));
					jCheckBoxshutdown.setActionCommand(SHUTDOWNTAG);
					jCheckBoxshutdown.addActionListener(this);
					
				}
				{
					jButtonclose = new JButton();
					jPanelmain.add(jButtonclose, new GridBagConstraints(8, 12, 4, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jButtonclose.setText(LangTools.getResourceBundleWordLanguage(rb,"Close","general.close"));
					jButtonclose.setActionCommand(CANCELANDCLOSEPROCESSES);
					jButtonclose.addActionListener(this);
				}
			}
			{
				this.setSize(562, 386);
			}
		} catch(Exception e) {
		    Logger.error(e);
		}
	}

	@Override
	public void run() {
		setThreadPoolExecutor();
		executeFFmpegProcesses();
		if(this.convlistener!=null){
			convlistener.setExecutor(executor);
			convlistener.setNumberTotalProcesses(numberofProcess);
			Thread t = new Thread(convlistener);
			t.run();
		}
		
		
	}


    public void hideShutdownTag(){
    	this.jCheckBoxshutdown.setVisible(false);
    }

	
	
	


}
