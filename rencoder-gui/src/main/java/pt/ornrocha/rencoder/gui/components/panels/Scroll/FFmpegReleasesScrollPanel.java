package pt.ornrocha.rencoder.gui.components.panels.Scroll;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import org.apache.commons.io.FileUtils;

import pt.ornrocha.rencoder.ffmpegWrapper.releases.collectors.FFmpegReleasesCollector;
import pt.ornrocha.rencoder.ffmpegWrapper.releases.containers.FFmpegReleaseInfo;
import pt.ornrocha.rencoder.ffmpegWrapper.releases.containers.FFmpegReleaseOsType;
import pt.ornrocha.rencoder.ffmpegWrapper.releases.containers.ListOfFFmpegReleases;
import pt.ornrocha.rencoder.gui.components.tables.FFmpegReleasesTableModel;

public class FFmpegReleasesScrollPanel extends JScrollPane{


	private static final long serialVersionUID = 1L;
	private FFmpegReleasesTableModel tablemodel;
	private JTable table;
	private FFmpegReleaseOsType ostype;
	private ArrayList<FFmpegReleaseInfo> listreleases;
	
	
	public FFmpegReleasesScrollPanel(FFmpegReleaseOsType ostype) {
		this.ostype=ostype;
		initGUI();
	}
	
	
	
	
	private void initGUI() {
	    this.setPreferredSize(new java.awt.Dimension(276, 145));
	    iniTable();
	    setColumnWidth();
	    getReleases();
	}
	
	
	private void iniTable(){
		
		

		String[] colnames = {"Name","Size","Provider"}; 
		this.tablemodel = new FFmpegReleasesTableModel (colnames, true);
		this.table = new JTable(){
          
 
			private static final long serialVersionUID = 1L;

			public String getToolTipText(MouseEvent e) {
                String tip = null;
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);
                int realColumnIndex = convertColumnIndexToModel(colIndex);

                if (realColumnIndex == 0) {
                	TableModel model = getModel();
                	tip=(String) ((FFmpegReleasesTableModel)model).getValueAt(rowIndex,0);
                   }
                return tip;
            }
        };

		this.table.setModel(this.tablemodel);
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.getViewport().add(table);
	
	}
	
	private void setColumnWidth(){
		TableColumnModel model = table.getColumnModel();
		double factor = table.getPreferredScrollableViewportSize().getWidth();
		model.getColumn(0).setPreferredWidth((int) (factor*0.6));
	    model.getColumn(1).setPreferredWidth((int) (factor*0.2));
	    model.getColumn(2).setPreferredWidth((int) (factor*0.2));
	}
	
	private void getReleases() {
		
		
	      SwingWorker<Void,Void> backgroundworker = new SwingWorker<Void,Void>()
					{
	    	           List<Object[]> listinputreleases = new ArrayList<>();
	    	            
					    @Override
					    protected Void doInBackground()
					    {

					    	tablemodel.addRow(new String[] { "Collecting FFmpeg Releases...", "", ""});
					    	
							FFmpegReleasesCollector collector= FFmpegReleasesCollector.defaultCollector();
							ListOfFFmpegReleases releases= collector.collect();
							
							if(ostype.equals(FFmpegReleaseOsType.Windows))
								listreleases=releases.getWindowsReleases();
							else
								listreleases=releases.getLinuxReleases();
							
							for (FFmpegReleaseInfo fFmpegReleaseInfo : listreleases) {
								Object[] obj = new Object[3];
								obj[0]=fFmpegReleaseInfo.getName();
								obj[1]=FileUtils.byteCountToDisplaySize(fFmpegReleaseInfo.getSize());
								obj[2]=fFmpegReleaseInfo.getProvider();
								listinputreleases.add(obj);
							}

					        return null;
					    }
					 
					    @Override
					    protected void done()
					    {
					    	tablemodel.resetTable();
					    	tablemodel.insertListOfData(listinputreleases);
					    }
					};
					backgroundworker.execute();

	}


	public ArrayList<FFmpegReleaseInfo> getListreleases() {
		return listreleases;
	}
	
	
	


}
