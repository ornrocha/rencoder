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
package pt.ornrocha.rencoder.gui.components.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.tinylog.Logger;

import pt.ornrocha.rencoder.gui.components.panels.Scroll.SubtitleScrollPanel;
import pt.ornrocha.rencoder.gui.components.panels.Scroll.VideoScrollPanel;
import pt.ornrocha.rencoder.gui.components.panels.configurations.SoftSubtitlesConfigPanel;
import pt.ornrocha.rencoder.gui.components.panels.info.VideoMediaInfoPanel2;
import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.mediafiles.files.auxiliar.ProcessFilesAux;
import pt.ornrocha.rencoder.mediafiles.files.containers.base.Subtitlefile;
import pt.ornrocha.rencoder.mediafiles.files.containers.base.Videofile;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;
import pt.ornrocha.rencoder.mediafiles.setfiles.foldersandfiles.ListFiles;

// TODO: Auto-generated Javadoc
/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI Builder, which is free
 * for non-commercial use. If Jigloo is being used commercially (ie, by a corporation, company or
 * business for any purpose whatever) then you should purchase a license for each developer using
 * Jigloo. Please visit www.cloudgarden.com for details. Use of Jigloo implies acceptance of these
 * licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS
 * CODE CANNOT BE USED LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class VideoMainPanel extends JPanel implements ListSelectionListener {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The j panelvideotable. */
  private JPanel jPanelvideotable;

  /** The moviepanel. */
  private VideoScrollPanel moviepanel;

  /** The j panelbuttons. */
  private JPanel jPanelbuttons;

  /** The j button clear. */
  private JButton jButtonClear;

  /** The j buttonremovesub. */
  private JButton jButtonremovesub;

  /** The j buttonaddsub. */
  private JButton jButtonaddsub;

  /** The j panelsubscroll. */
  private JPanel jPanelsubscroll;

  /** The j panelsubtitles. */
  private JPanel jPanelsubtitles;

  /** The j buttonremoveselect. */
  private JButton jButtonremoveselect;

  /** The j buttonloadfiles. */
  private JButton jButtonloadfiles;

  /** The j panelinfo. */
  private VideoMediaInfoPanel2 jPanelinfo;

  /** The subtitles panel. */
  private SubtitleScrollPanel subtitlesPanel;

  /** The resetmovietable. */
  public static String RESETMOVIETABLE = "resetmovietable";

  /** The removeselectedrows. */
  public static String REMOVESELECTEDROWS = "removeselectedrows";

  /** The addmovies. */
  public static String ADDMOVIES = "addmovies";

  /** The addsubtitle. */
  public static String ADDSUBTITLE = "addsubtitle";

  /** The removesubtitle. */
  public static String REMOVESUBTITLE = "removesubtitle";

  /** The configuresubtitles. */
  public static String CONFIGURESUBTITLES = "configuresubtitles";

  /** The selectallmovies. */
  public static String SELECTALLMOVIES = "selectallmovies";

  /** The moviefiles. */
  protected IndexedHashMap<String, Videofile> moviefiles = null;

  /** The selected movie index. */
  protected int selectedMovieIndex = -1;

  /** The selected movie. */
  protected Videofile selectedMovie = null;

  /** The reseting. */
  private boolean reseting = false;

  /** The loading. */
  private boolean loading = false;

  /** The j panelsubsconf. */
  private JPanel jPanelsubsconf;

  /** The j check box selectall. */
  private JCheckBox jCheckBoxSelectall;

  /** The j buttonsubconfig. */
  private JButton jButtonsubconfig;

  /** The rb. */
  private ResourceBundle rb;

  private JFrame mainframe;

  /**
   * Instantiates a new video main panel.
   */
  public VideoMainPanel(JFrame mainframe) {
    this.mainframe = mainframe;
    rb = ResourceBundle.getBundle("lang", LangTools.getDefinedLanguage(),
        LangTools.loadLanguagesPath());
    initGUI();
    addListSelectionListeners(this);

  }


  /**
   * Inits the gui.
   */
  private void initGUI() {
    try {
      {
        GridBagLayout thisLayout = new GridBagLayout();
        this.setPreferredSize(new java.awt.Dimension(915, 334));
        thisLayout.rowWeights =
            new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
        thisLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
        thisLayout.columnWeights =
            new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.3};
        thisLayout.columnWidths = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
        this.setLayout(thisLayout);

        {
          jPanelvideotable = new JPanel();
          moviepanel = new VideoScrollPanel(this);
          this.add(moviepanel, new GridBagConstraints(0, 0, 8, 11, 0.0, 0.0,
              GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        }
        {
          jPanelbuttons = new JPanel();
          GridBagLayout jPanelbuttonsLayout = new GridBagLayout();
          this.add(jPanelbuttons, new GridBagConstraints(0, 11, 8, 1, 0.0, 0.0,
              GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
          jPanelbuttonsLayout.rowWeights = new double[] {0.1};
          jPanelbuttonsLayout.rowHeights = new int[] {7};
          jPanelbuttonsLayout.columnWeights =
              new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
          jPanelbuttonsLayout.columnWidths = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7};
          jPanelbuttons.setLayout(jPanelbuttonsLayout);
          {
            jButtonloadfiles = new JButton();
            jPanelbuttons.add(jButtonloadfiles,
                new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
            jButtonloadfiles.setText(LangTools.getWordLanguage("Add files", "videogui.addmovies"));
            jButtonloadfiles.setActionCommand(ADDMOVIES);
            jButtonloadfiles.setToolTipText(LangTools.getResourceBundleWordLanguage(rb,
                "Load selected video and subtitle files within a folder",
                "tip.loadselectedvideos"));
          }
          {
            jButtonremoveselect = new JButton();
            jPanelbuttons.add(jButtonremoveselect,
                new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
            jButtonremoveselect
                .setText(LangTools.getWordLanguage("Remove selected", "videogui.removeselected"));
            jButtonremoveselect.setActionCommand(REMOVESELECTEDROWS);
          }
          {
            jButtonClear = new JButton();
            jPanelbuttons.add(jButtonClear,
                new GridBagConstraints(3, 0, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
            jButtonClear.setText(LangTools.getWordLanguage("Delete list", "videogui.clear"));
            jButtonClear.setActionCommand(RESETMOVIETABLE);
          }
          {
            jCheckBoxSelectall = new JCheckBox();
            jCheckBoxSelectall
                .setText(LangTools.getResourceBundleWordLanguage(rb, "Select", "files.select"));
            jPanelbuttons.add(jCheckBoxSelectall,
                new GridBagConstraints(6, 0, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                    GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
            jCheckBoxSelectall.setActionCommand(SELECTALLMOVIES);

          }
        }
        {
          jPanelsubtitles = new JPanel();
          GridBagLayout jPanelsubtitlesLayout = new GridBagLayout();
          this.add(jPanelsubtitles, new GridBagConstraints(8, 0, 4, 5, 0.0, 0.0,
              GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
          jPanelsubtitlesLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
          jPanelsubtitlesLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7, 7};
          jPanelsubtitlesLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
          jPanelsubtitlesLayout.columnWidths = new int[] {7, 7, 7, 7};
          jPanelsubtitles.setLayout(jPanelsubtitlesLayout);
          jPanelsubtitles.setBorder(BorderFactory
              .createTitledBorder(LangTools.getWordLanguage("Subtitles", "subtitlesgui.label")));
          {
            jPanelsubscroll = new JPanel();
            subtitlesPanel = new SubtitleScrollPanel(this);
            jPanelsubtitles.add(subtitlesPanel, new GridBagConstraints(0, 0, 4, 6, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
          }
          {
            jPanelsubsconf = new JPanel();
            GridBagLayout jPanelsubsconfLayout = new GridBagLayout();
            jPanelsubtitles.add(jPanelsubsconf, new GridBagConstraints(0, 6, 4, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
            jPanelsubsconfLayout.rowWeights = new double[] {0.1};
            jPanelsubsconfLayout.rowHeights = new int[] {7};
            jPanelsubsconfLayout.columnWeights = new double[] {0.1, 0.1, 0.1};
            jPanelsubsconfLayout.columnWidths = new int[] {7, 7, 7};
            jPanelsubsconf.setLayout(jPanelsubsconfLayout);
            {
              jButtonaddsub = new JButton();
              jPanelsubsconf.add(jButtonaddsub,
                  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                      GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
              jButtonaddsub.setText(
                  LangTools.getResourceBundleWordLanguage(rb, "Add subtitle", "subtitlesgui.add"));
              jButtonaddsub.setActionCommand(ADDSUBTITLE);
            }
            {
              jButtonremovesub = new JButton();
              jPanelsubsconf.add(jButtonremovesub,
                  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                      GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
              jButtonremovesub.setText(LangTools.getResourceBundleWordLanguage(rb,
                  "Remove Subtitle", "subtitlesgui.remove"));
              jButtonremovesub.setActionCommand(REMOVESUBTITLE);
            }
            {
              jButtonsubconfig = new JButton();
              jPanelsubsconf.add(jButtonsubconfig,
                  new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                      GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
              jButtonsubconfig.setText(
                  LangTools.getResourceBundleWordLanguage(rb, "Configure", "general.configure"));
              jButtonsubconfig.setActionCommand(CONFIGURESUBTITLES);
            }
          }
        }
        {
          jPanelinfo = new VideoMediaInfoPanel2();
          this.add(jPanelinfo, new GridBagConstraints(8, 5, 4, 7, 0.0, 0.0,
              GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
          this.setBorder(BorderFactory.createTitledBorder(null,
              LangTools.getWordLanguage("List of movies to convert", "videogui.listtoconvert"),
              TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION));
        }
      }

    } catch (Exception e) {
      Logger.error(e);
    }
  }

  public JFrame getMainframe() {
    return mainframe;
  }

  /**
   * Gets the movie file.
   *
   * @return the movie file
   */
  public IndexedHashMap<String, Videofile> getMovieFile() {
    return this.moviefiles;
  }

  /**
   * Gets the movie j table.
   *
   * @return the movie j table
   */
  public JTable getMovieJTable() {
    return moviepanel.getMoviesJTable();
  }

  /**
   * Gets the subtitles panel.
   *
   * @return the subtitles panel
   */
  public SubtitleScrollPanel getSubtitlesPanel() {
    return this.subtitlesPanel;
  }

  public VideoScrollPanel getMoviesPAnel() {
    return this.moviepanel;
  }

  /**
   * Adds the video files.
   *
   * @param videos the videos
   */
  public void addVideoFiles(ArrayList<Videofile> videos) {
    this.loading = true;

    ArrayList<Videofile> addtotables = new ArrayList<>();
    if (videos != null && videos.size() > 0) {
      if (this.moviefiles == null) {
        this.moviefiles = new IndexedHashMap<>();
        jCheckBoxSelectall.setSelected(true);
      }

      for (Videofile videofile : videos) {
        if (!moviefiles.containsKey(videofile.getFilePath())) {
          this.moviefiles.put(videofile.getFilePath(), videofile);
          addtotables.add(videofile);
        }
      }

      if (addtotables.size() > 0) {
        this.moviepanel.addMoviesToTable(addtotables);
      }

    }
    this.loading = false;
    if (moviefiles != null && moviefiles.size() > 0)
      setToolTipText(null);
  }

  /**
   * Gets the selected movie.
   *
   * @return the selected movie
   */
  public Videofile getSelectedMovie() {
    return this.selectedMovie;
  }

  /**
   * Adds the subtitle file.
   *
   * @param sub the sub
   */
  public void addSubtitleFile(Subtitlefile sub) {
    this.loading = true;
    this.selectedMovie.addSubtitleObject(sub);
    this.subtitlesPanel.addSingleSubtitleToTable(sub);
    this.moviepanel.checkIfVideoContainsSubtitle(this.selectedMovie, selectedMovieIndex);
    this.loading = false;

  }

  /**
   * Reset table list.
   */
  public void resetTableList() {
    this.reseting = true;
    this.moviepanel.resetMovieTable();
    this.jPanelinfo.resetTables();
    this.subtitlesPanel.resetSubtitleTable();
    this.moviefiles = null;
    this.selectedMovie = null;
    this.selectedMovieIndex = -1;
    jCheckBoxSelectall.setSelected(false);
    this.reseting = false;
  }

  /**
   * Adds the actions listeners to video panel.
   *
   * @param listener the listener
   */
  public void addActionsListenersToVideoPanel(ActionListener listener) {
    jButtonClear.addActionListener(listener);
    jButtonremoveselect.addActionListener(listener);
    jButtonloadfiles.addActionListener(listener);
    jButtonaddsub.addActionListener(listener);
    jButtonremovesub.addActionListener(listener);
    jButtonsubconfig.addActionListener(listener);
    jCheckBoxSelectall.addActionListener(listener);
  }

  /**
   * Adds the list selection listeners.
   *
   * @param listlistener the listlistener
   */
  public void addListSelectionListeners(ListSelectionListener listlistener) {
    this.moviepanel.getMoviesJTable().getSelectionModel().addListSelectionListener(listlistener);

  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.
   * ListSelectionEvent)
   */
  @Override
  public void valueChanged(ListSelectionEvent e) {
    int row = getMovieJTable().getSelectedRow();
    if (row > -1)
      if (!reseting && !loading) {
        this.selectedMovieIndex = row;
        this.selectedMovie = this.moviefiles.getValueAt(row);
        addSelectedMovieInfoToTables();
      }
  }

  /**
   * Adds the selected movie info to tables.
   */
  private void addSelectedMovieInfoToTables() {

    ArrayList<Subtitlefile> subtitles = this.selectedMovie.getSubtitles();
    this.subtitlesPanel.resetSubtitleTable();
    this.subtitlesPanel.setSelectedVideo(this.selectedMovie);
    if (subtitles != null) {

      this.subtitlesPanel.addSubtitlesToTable(subtitles);
    }

    this.jPanelinfo.addMovieFile(this.selectedMovie);
  }

  /**
   * Removes the selected rows from movie table.
   */
  public void RemoveSelectedRowsFromMovieTable() {
    this.reseting = true;
    int[] rows = this.getMovieJTable().getSelectedRows();
    if (rows.length > 0) {
      this.moviepanel.getMovieTableModel().removeRowsAtPos(rows);
      removeElementsofMovieFilesControler(rows);
      this.jPanelinfo.resetTables();
      this.selectedMovie = null;
      this.subtitlesPanel.resetSubtitleTable();
    } else
      JOptionPane.showMessageDialog(mainframe,
          LangTools.getResourceBundleWordLanguage(rb, "Please select a movie",
              "warngui.selectmovie"),
          LangTools.getResourceBundleWordLanguage(rb, "Warning", "warngui.tag"),
          JOptionPane.INFORMATION_MESSAGE,
          new ImageIcon(ListFiles.getIconAbsolutePath("icons/warning64x64.png")));
    this.reseting = false;

  }

  public int[] getSelectedMovies() {
    return this.getMovieJTable().getSelectedRows();
  }

  /**
   * Removes the selected subtitle.
   */
  public void RemoveSelectedSubtitle() {
    this.reseting = true;
    this.subtitlesPanel.removeSubtitleInTable();
    this.moviepanel.checkIfVideoContainsSubtitle(this.selectedMovie, selectedMovieIndex);
    this.reseting = false;
  }

  /**
   * Gets the selected movie index.
   *
   * @return the selected movie index
   */
  public int getSelectedMovieIndex() {
    return this.selectedMovieIndex;
  }

  /**
   * Gets the movie files.
   *
   * @return the movie files
   */
  public IndexedHashMap<String, Videofile> getMovieFiles() {
    return this.moviefiles;
  }

  /**
   * Sets the same conv config in all movies.
   *
   * @param convconfig the new same conv config in all movies
   */
  public void setSameConvConfigInAllMovies(IGeneralVideoEncInfoContainer convconfig) {

    if (this.moviefiles != null) {
      for (int i = 0; i < moviefiles.size(); i++) {

        moviefiles.getValueAt(i).setEncodingInfoContainer(convconfig);
      }
    }
  }

  public void setSameConvConfigforSelecteMovies(IGeneralVideoEncInfoContainer convconfig) {

    int[] rows = this.getMovieJTable().getSelectedRows();

    if (this.moviefiles != null) {
      if (rows.length > 0) {
        for (int i = 0; i < rows.length; i++) {
          moviefiles.getValueAt(rows[i]).setEncodingInfoContainer(convconfig);
        }
      }
    }
  }

  /**
   * Gets the movie files to convert.
   *
   * @return the movie files to convert
   */
  public IndexedHashMap<String, Videofile> getMovieFilesToConvert() {

    if (moviefiles != null && moviefiles.size() > 0) {
      IndexedHashMap<String, Videofile> convert = new IndexedHashMap<>();

      IndexedHashMap<Integer, Boolean> toconvert = moviepanel.getMoviesToConvert();

      for (int i = 0; i < this.moviefiles.size(); i++) {
        if (toconvert.get(i)) {
          convert.put(this.moviefiles.getKeyAt(i), this.moviefiles.getValueAt(i));
        }
      }
      return convert;
    }

    return null;
  }

  public ArrayList<Videofile> getListMovieFile() {
    ArrayList<Videofile> movlist = new ArrayList<>();
    if (this.moviefiles != null)
      for (int i = 0; i < this.moviefiles.size(); i++) {
        movlist.add(this.moviefiles.getValueAt(i));
      }
    return movlist;
  }

  /**
   * Removes the elementsof movie files controler.
   *
   * @param rows the rows
   */
  private void removeElementsofMovieFilesControler(int[] rows) {

    IndexedHashMap<String, Videofile> changedMap = new IndexedHashMap<>();

    HashSet<Integer> remove = ProcessFilesAux.convertArrayToHasSet(rows);

    for (int i = 0; i < this.moviefiles.size(); i++) {
      if (!remove.contains(i)) {
        changedMap.put(this.moviefiles.getKeyAt(i), this.moviefiles.getValueAt(i));
      }
    }

    this.moviefiles = changedMap;
  }

  /**
   * Launch soft subs config panel.
   */
  public void LaunchSoftSubsConfigPanel() {

    try {

      if (moviefiles != null) {

        if (selectedMovie == null) {

          JOptionPane.showMessageDialog(mainframe,
              LangTools.getResourceBundleWordLanguage(rb, "Please select a movie",
                  "warngui.selectmovie"),
              LangTools.getResourceBundleWordLanguage(rb, "Warning", "warngui.tag"),
              JOptionPane.INFORMATION_MESSAGE,
              new ImageIcon(ListFiles.getIconAbsolutePath("icons/warning64x64.png")));
        }

        else {
          if (selectedMovie.getSubtitles() != null) {
            SoftSubtitlesConfigPanel softsubpanel = new SoftSubtitlesConfigPanel();

            softsubpanel.setSubtitleConfigPanel(this.subtitlesPanel, this.selectedMovie);
            softsubpanel.setLocationRelativeTo(this);
            softsubpanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            softsubpanel.setVisible(true);
          } else
            JOptionPane.showMessageDialog(mainframe,
                LangTools.getResourceBundleWordLanguage(rb, "Movie without associated subtitles",
                    "warngui.nolinkedsubtitles"),
                LangTools.getResourceBundleWordLanguage(rb, "Warning", "warngui.tag"),
                JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon(ListFiles.getIconAbsolutePath("icons/warning64x64.png")));

        }
      }

      else {
        if (moviefiles == null)
          JOptionPane.showMessageDialog(mainframe,
              LangTools.getResourceBundleWordLanguage(rb, "Movie list its empty",
                  "warngui.emptylist"),
              LangTools.getResourceBundleWordLanguage(rb, "Warning", "warngui.tag"),
              JOptionPane.INFORMATION_MESSAGE,
              new ImageIcon(ListFiles.getIconAbsolutePath("icons/warning64x64.png")));

      }

    } catch (Exception e) {
      Logger.error(e);
    }
  }

  /**
   * Select deselect all movies.
   */
  public void selectDeselectAllMovies() {
    if (this.moviefiles != null) {
      if (this.jCheckBoxSelectall.isSelected())
        this.moviepanel.ChangeStateSelectedMovies(true);
      else
        this.moviepanel.ChangeStateSelectedMovies(false);
    }
  }



}


