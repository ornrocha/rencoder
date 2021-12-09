/*
 * Copyright 2014
 *
 * This is free software: you can redistribute it and/or modify it under the terms of the GNU Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 * 
 * This code is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Public License for more details.
 * 
 * You should have received a copy of the GNU Public License along with this code. If not, see
 * http://www.gnu.org/licenses/
 * 
 * Created by Orlando Rocha
 */
package pt.ornrocha.rencoder.ffmpegWrapper.execution;

// TODO: Auto-generated Javadoc
/**
 * The Class KillFFmpegProcess.
 */
public class KillFFmpegProcess extends Thread {

  /** The ffmpegproc1pass. */
  private Process ffmpegproc1pass = null;

  /** The ffmpegproc2pass. */
  private Process ffmpegproc2pass = null;

  /**
   * Instantiates a new kill f fmpeg process.
   */
  public KillFFmpegProcess() {

  }

  /**
   * Instantiates a new kill ffmpeg process.
   *
   * @param proc the proc
   */
  public KillFFmpegProcess(Process proc) {
    this.ffmpegproc1pass = proc;
  }

  /**
   * Instantiates a new kill ffmpeg process.
   *
   * @param proc1pass the proc1pass
   * @param proc2pass the proc2pass
   */
  public KillFFmpegProcess(Process proc1pass, Process proc2pass) {
    this.ffmpegproc1pass = proc1pass;
    this.ffmpegproc2pass = proc2pass;
  }

  /**
   * Sets the process one pass to kill.
   *
   * @param proc the new process one pass to kill
   */
  public void setProcessOnepasstoKill(Process proc) {
    this.ffmpegproc1pass = proc;
  }

  /**
   * Sets the process second pass to kill.
   *
   * @param proc the new process second pass to kill
   */
  public void setProcessSecondpasstoKill(Process proc) {
    this.ffmpegproc2pass = proc;
  }

  /**
   * Sets the processes to kill.
   *
   * @param proc1pass the proc1pass
   * @param proc2pass the proc2pass
   */
  public void setProcessestoKill(Process proc1pass, Process proc2pass) {
    this.ffmpegproc1pass = proc1pass;
    this.ffmpegproc2pass = proc2pass;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Thread#run()
   */
  @Override
  public void run() {
    this.ffmpegproc1pass.destroy();
    if (this.ffmpegproc2pass != null)
      this.ffmpegproc2pass.destroy();
  }

}
