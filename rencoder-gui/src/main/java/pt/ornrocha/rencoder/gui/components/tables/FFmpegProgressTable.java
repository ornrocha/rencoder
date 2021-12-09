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
package pt.ornrocha.rencoder.gui.components.tables;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import pt.ornrocha.rencoder.ffmpegWrapper.execution.progress.ProgressBarUpdate;

// TODO: Auto-generated Javadoc
/**
 * The Class FFmpegProgressTable.
 */
public class FFmpegProgressTable extends GenericTableViewerModel implements PropertyChangeListener {


  /** The swmap. */
  private final ConcurrentMap<Integer, Integer> swmap = new ConcurrentHashMap<>();

  /** The procnumber. */
  private int procnumber = 0;

  private ArrayList<String> tips = new ArrayList<>();



  /**
   * Instantiates a new ffmpeg progress table.
   *
   * @param colNames the col names
   * @param isValueCellsEditable the is value cells editable
   */
  public FFmpegProgressTable(String[] colNames, boolean isValueCellsEditable) {
    super(colNames, isValueCellsEditable);
  }


  /**
   * Sets the new process in table.
   *
   * @param name the name
   * @param updater1pass the updater1pass
   * @param updater2pass the updater2pass
   */
  public void setNewProcessInTable(String name, String tip, ProgressBarUpdate updater1pass,
      ProgressBarUpdate updater2pass) {
    updater1pass.addPosition(procnumber).addPropertyChangeListener(this);
    if (updater2pass != null)
      updater2pass.addPosition(procnumber).addPropertyChangeListener(this);
    Object[] obj = {name, 0};
    super.addRow(obj);
    tips.add(tip);
    procnumber++;

  }


  /**
   * Gets the progress bar updater.
   *
   * @param identifier the identifier
   * @return the prog bar updater
   */
  public synchronized Integer getProgBarUpdater(int identifier) {
    Integer key = (Integer) getValueAt(identifier, 0);
    return swmap.get(key);
  }


  /**
   * Update progress value.
   *
   * @param progress the progress
   * @param n the n
   */
  public void updateprogressvalue(Object progress, int n) {
    setValueAt(progress, n, 1);
    fireTableCellUpdated(n, 1);
  }

  public String getTipIndex(int index) {
    return tips.get(index);
  }


  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    String cmd = evt.getPropertyName();
    if (cmd.equals("progress")) {
      updateprogressvalue((int) evt.getNewValue(), (int) evt.getOldValue());
    } else if (cmd.equals(ProgressBarUpdate.PROGRESSDOUBLE)) {
      updateprogressvalue((double) evt.getNewValue(), (int) evt.getOldValue());
    } else if (cmd.equals(ProgressBarUpdate.PROGRESSMSG)) {
      updateprogressvalue((String) evt.getNewValue(), (int) evt.getOldValue());
    }

  }

}
