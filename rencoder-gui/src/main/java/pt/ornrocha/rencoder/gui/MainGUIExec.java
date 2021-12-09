package pt.ornrocha.rencoder.gui;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import pt.ornrocha.rencoder.gui.execute.RestartRencoder;
import pt.ornrocha.rencoder.gui.execute.ShutdownRencoder;

public class MainGUIExec implements Runnable {

  private ShutdownRencoder shutdown = null;
  private RestartRencoder restart = null;

  public MainGUIExec(ShutdownRencoder off, RestartRencoder restart) {
    this.shutdown = off;
    this.restart = restart;
  }

  @Override
  public void run() {
    Maingui inst = Maingui.getInstance();
    inst.setShutdownProcess(this.shutdown);
    inst.setRestartProcess(this.restart);

    inst.setLocationRelativeTo(null);
    inst.setVisible(true);

    inst.addWarnFlag(
        new JLabel("<html><center><font color='red'>Loading<br>FFmpeg</font><center/><html/>"));
    SwingUtilities.invokeLater(new SwingWorker<Void, Void>() {

      @Override
      protected Void doInBackground() throws Exception {
        inst.loadRequirements();
        return null;
      }

    });

  }

}
