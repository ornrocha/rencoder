package pt.ornrocha.rencoder.gui;

import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.gui.execute.RestartRencoder;
import pt.ornrocha.rencoder.gui.execute.ShutdownRencoder;
import pt.ornrocha.rencoder.helpers.osystem.OSystem;

public class MainGUIExec implements Runnable{

	private ShutdownRencoder shutdown=null;
	private RestartRencoder restart=null;

	public MainGUIExec(ShutdownRencoder off, RestartRencoder restart){
		this.shutdown=off;
		this.restart=restart;
	}

	@Override
	public void run() {
		Maingui inst = Maingui.getInstance();
		inst.setShutdownProcess(this.shutdown);
		inst.setRestartProcess(this.restart);

		deletePreviousUpdate();

		inst.backupCheckRencoderConf();
		inst.checkIfExistUpdateForExecutor();
		inst.setLocationRelativeTo(null);
		inst.setVisible(true);

		inst.addWarnFlag(new JLabel("<html><center><font color='red'>Loading<br>FFmpeg</font><center/><html/>"));
		SwingUtilities.invokeLater(new SwingWorker<Void,Void>(){

			@Override
			protected Void doInBackground() throws Exception {
				inst.loadRequirements();
				return null;
			}
		});

	}


	private void deletePreviousUpdate() {
		File f = new File(FilenameUtils.concat(OSystem.getCurrentDir(), "update.zip"));
		if(f.exists())
			try {
				FileUtils.forceDelete(f);
			} catch (IOException e) {
				Logger.error(e);
			}
	}




}
