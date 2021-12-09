package pt.ornrocha.rencoder.ffmpegWrapper.execution;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.configurations.FFmpegLogInfoContainer;
import pt.ornrocha.rencoder.ffmpegWrapper.execution.progress.MergeProgressBarUpdater;
import pt.ornrocha.rencoder.helpers.osystem.OSystem;

public class FFmpegMergeProcess implements Runnable {

  protected ArrayList<String> cmds = null;
  protected ProcessBuilder pbuilder = null;
  protected Process process = null;
  protected MergeProgressBarUpdater procbar = null;
  protected KillFFmpegProcess killer = null;
  protected FFmpegLogInfoContainer logsave = null;

  public FFmpegMergeProcess(ArrayList<String> cmds) {
    this.cmds = cmds;

    Logger.info("Merge Commands: " + cmds);

  }

  public FFmpegMergeProcess(ArrayList<String> cmds, KillFFmpegProcess kill) {
    this.cmds = cmds;
    this.killer = kill;
  }

  public FFmpegMergeProcess(ArrayList<String> cmds, KillFFmpegProcess kill,
      FFmpegLogInfoContainer loginfo) {
    this.cmds = cmds;
    this.killer = kill;
    this.logsave = loginfo;
  }

  public void setLogInfo(FFmpegLogInfoContainer log) {
    this.logsave = log;
  }

  public void setProgressBarUpdater(MergeProgressBarUpdater procbar) {
    this.procbar = procbar;
  }

  @Override
  public void run() {
    int exitValue = 0;
    pbuilder = new ProcessBuilder(this.cmds);
    pbuilder.redirectErrorStream(true);
    try {

      setFFREPORT(pbuilder);

      process = pbuilder.start();

      if (this.killer != null)
        killer.setProcessOnepasstoKill(process);

      procbar.setInputStream(process.getInputStream());

      Thread barcheck = new Thread(procbar);

      /*
       * Thread barcheck = new Thread() { public void run() { SwingUtilities.invokeLater(procbar); }
       * };
       */
      barcheck.run();
      // SwingUtilities.invokeLater(procbar);

      exitValue = process.waitFor();

    } catch (IOException | InterruptedException e) {
      Logger.error(e);
    }

  }

  private void setFFREPORT(ProcessBuilder pb) {
    if (logsave != null) {
      if (logsave.isActive()) {
        Map<String, String> env = pb.environment();
        String msg = null;
        if (OSystem.isWindows())
          msg =
              logsave.getLoglevel().getLogLevelString() + ":" + "file='" + logsave.getLogfilepath()
                  + OSystem.getSystemSeparator() + logsave.getLogfilename() + "'";
        else
          msg = logsave.getLoglevel().getLogLevelString() + ":" + "file=" + logsave.getLogfilepath()
              + OSystem.getSystemSeparator() + logsave.getLogfilename();
        env.put("FFREPORT", msg);

      }
    }
  }
}
