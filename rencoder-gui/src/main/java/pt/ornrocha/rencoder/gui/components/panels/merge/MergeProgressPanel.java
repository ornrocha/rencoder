package pt.ornrocha.rencoder.gui.components.panels.merge;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import org.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.execution.FFmpegMergeProcess;
import pt.ornrocha.rencoder.ffmpegWrapper.execution.progress.MergeProgressBarUpdater;
import pt.ornrocha.rencoder.helpers.lang.LangTools;

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
public class MergeProgressPanel extends JDialog implements ActionListener, PropertyChangeListener, Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JProgressBar jProgressBarprog;
	private JLabel jLabeltag;
	private JLabel jLabelcount;
	private int total=0;
	private JButton jButtoncancel;
    private FFmpegMergeProcess mergeproc =null;
    private boolean finished=false;
    protected JFrame parent=null;
    protected String labelof;

    
    private static String CANCELCLOSE="cancelclose";
	

	public MergeProgressPanel(FFmpegMergeProcess mergeproc, int totalproc, JFrame parent){
		this.total=totalproc;
		this.mergeproc=mergeproc;
		this.parent=parent;
		labelof=LangTools.getWordLanguage("of","mergegui.of");
		initGUI();
		setInitConfigs();
		setInitCount();
		this.setLocationRelativeTo(parent);
	}
	
	
	private void setInitConfigs(){
		MergeProgressBarUpdater barupdater = new MergeProgressBarUpdater(jProgressBarprog, total, this);
		this.mergeproc.setProgressBarUpdater(barupdater);
	}
	
	public void changeLabeltag(String txt){
		jLabeltag.setText(txt);
	}
	
	public void changeLabelCount(int n){
		jLabelcount.setText(String.valueOf(n)+" "+labelof+" "+String.valueOf(total));
		
	}
	
	private void setInitCount(){
		jLabelcount.setText("1 "+labelof+" "+total);
	}
	
	

	private void initGUI() {
		try {
			{
				GridBagLayout thisLayout = new GridBagLayout();
				this.setPreferredSize(new java.awt.Dimension(443, 169));
				thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1};
				thisLayout.rowHeights = new int[] {7, 7, 7, 7, 7};
				thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
				thisLayout.columnWidths = new int[] {7, 7, 7, 7};
				getContentPane().setLayout(thisLayout);
				this.setSize(443, 169);
				{
					jProgressBarprog = new JProgressBar(0,100);
					jProgressBarprog.setStringPainted(true);
					this.add(jProgressBarprog, new GridBagConstraints(0, 2, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					jLabeltag = new JLabel();
					this.add(jLabeltag, new GridBagConstraints(0, 0, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jLabeltag.setText(LangTools.getWordLanguage("Concatenating Movies", "mergegui.concmovies"));
					jLabeltag.setHorizontalAlignment(SwingConstants.CENTER);
					jLabeltag.setFont(new Font("Serif", Font.BOLD, 16));
				}
				{
					jLabelcount = new JLabel();
					this.add(jLabelcount, new GridBagConstraints(0, 1, 4, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jLabelcount.setText("1 "+labelof+" ");
					jLabelcount.setHorizontalAlignment(SwingConstants.CENTER);
					jLabelcount.setFont(new Font("Serif", Font.PLAIN, 16));
				}
				{
					jButtoncancel = new JButton();
					getContentPane().add(jButtoncancel, new GridBagConstraints(1, 4, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jButtoncancel.setText(LangTools.getWordLanguage("Cancel","general.cancel"));
					jButtoncancel.setActionCommand(CANCELCLOSE);
					jButtoncancel.addActionListener(this);
				}
			}
		} catch(Exception e) {
		    Logger.error(e);
		}
	}

	@Override
	public void run() {
		this.setVisible(true);

		Thread t = new Thread(mergeproc);
		t.start();
		
	}
	

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String cmd = evt.getPropertyName();
		
		
		if (cmd.equals(MergeProgressBarUpdater.NUMBEROFMERGEPROCESS)){
			int newvalue = (int) evt.getNewValue();
			changeLabelCount(newvalue+1);
		}
		else if(cmd.equals(MergeProgressBarUpdater.FINISHEDMERGE)){
			this.finished=true;
			jButtoncancel.setText(LangTools.getWordLanguage("Close","general.close"));
			jLabelcount.setText(LangTools.getWordLanguage("Finished","mergegui.finished"));
			jLabelcount.setFont(new Font("Serif", Font.BOLD, 18));
		}
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals(CANCELCLOSE)){
			if(finished){
				parent.dispose();	
			}
			else{
				((MergeVideosPanel)parent).killRunningProcesses();
			}
			
			jLabeltag.setText(LangTools.getWordLanguage("Deleting Temporary Files","mergegui.deltempfiles"));
			((MergeVideosPanel)parent).deleteTempFiles();
			this.dispose();
			
		}
		
	}
	


}
