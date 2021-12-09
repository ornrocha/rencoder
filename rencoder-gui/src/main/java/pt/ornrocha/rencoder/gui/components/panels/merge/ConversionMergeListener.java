package pt.ornrocha.rencoder.gui.components.panels.merge;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.concurrent.ThreadPoolExecutor;

import javax.swing.SwingWorker;

import org.tinylog.Logger;

public class ConversionMergeListener extends SwingWorker<Void, Void>{

	
	private ThreadPoolExecutor executor=null;
	private PropertyChangeSupport changelst =new PropertyChangeSupport(this);
	private boolean processfinished=false;
	private int numberprocesses=0;
	
	public static String FINISHEDENCODING="finishedencodingprocesses";
	public static String COMPLETEDENCODINGPROCESS="Completeencodingprocesses";
	public static String STARTENCODING="startencodingprocesses";
	
	public ConversionMergeListener(PropertyChangeListener listener){
		this.changelst.addPropertyChangeListener(listener);
	}
	
	private void setFinishedEncodindProcessesFireEvents(){
		   changelst.firePropertyChange(FINISHEDENCODING,false,true);
	    }
	
	private void setstartEncodindProcessesFireEvents(){
		   changelst.firePropertyChange(STARTENCODING,false,true);
	    }
	
	private void setCompleteEncodindProcessesFireEvents(int oldval, int newval){
		   changelst.firePropertyChange(COMPLETEDENCODINGPROCESS,oldval,newval);
	    }
	
	
	public void setExecutor(ThreadPoolExecutor executor){
		this.executor=executor;
	}
	
	public void setNumberTotalProcesses(int n){
		this.numberprocesses=n;
	}
	
	public int getNumberTotalProcesses(){
		return this.numberprocesses;
	}
	
	@Override
	protected Void doInBackground() {
		
		int remain=numberprocesses;
		int exec = 1;
		
		setstartEncodindProcessesFireEvents();

		while (!processfinished) {
			try {
                    executor.shutdown();
					while(!executor.isTerminated()){
						Thread.sleep(1000);
						int queued = executor.getQueue().size();
						int active = executor.getActiveCount();
						int notCompleted = queued + active;
						if(notCompleted!=remain){
							setCompleteEncodindProcessesFireEvents(exec,exec+1);
							exec++;
							remain=notCompleted;
							
						}
					
					}
				
				processfinished=true;

			} catch (InterruptedException e) {
				Logger.error(e);
			}
		}
		setFinishedEncodindProcessesFireEvents();
		
		return null;
	}



}
