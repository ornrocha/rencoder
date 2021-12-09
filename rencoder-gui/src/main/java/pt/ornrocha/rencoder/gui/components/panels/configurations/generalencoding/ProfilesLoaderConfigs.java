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
package pt.ornrocha.rencoder.gui.components.panels.configurations.generalencoding;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.othercomponents.AspectComponent;
import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.helpers.props.managers.EncodingProfileManager;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.ProfileVideoEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.setfiles.foldersandfiles.ListFiles;

// TODO: Auto-generated Javadoc
/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class ProfilesLoaderConfigs extends JDialog implements ListSelectionListener, ActionListener {

	/** The j buttoncancel. */
	private JButton jButtoncancel;

	/** The j buttonload. */
	private JButton jButtonload;

	/** The j scroll panelist. */
	private JScrollPane jScrollPanelist;

	/** The j buttonloadfile. */
	private JButton jButtonloadfile;

	/** The j text panesettings. */
	private JTextPane jTextPanesettings;

	/** The j scroll pane profile settings. */
	private JScrollPane jScrollPaneProfileSettings;

	/** The j listprofiles. */
	private JList jListprofiles;

	/** The doc. */
	private StyledDocument doc;

	/** The regular. */
	private Style regular = null;

	/** The bold. */
	private Style bold = null;

	/** The bold2. */
	private Style bold2 = null;

	/** The profilemanager. */
	private EncodingProfileManager profilemanager = null;

	/** The titlelabel. */
	private String titlelabel;

	/** The j buttonloadtext. */
	private String jButtonloadtext;

	/** The j buttoncanceltext. */
	private String jButtoncanceltext;

	/** The rb. */
	private ResourceBundle rb;

	/** The cancelprofileschooser. */
	private static String CANCELPROFILESCHOOSER = "cancelprofileschooser";

	/** The loadchoosenprofile. */
	public static String LOADCHOOSENPROFILE = "loadchoosenprofile";

	/** The loadprofilefromfile. */
	public static String LOADPROFILEFROMFILE = "loadprofilefromfile";

	/**
	 * Instantiates a new profiles loader configs.
	 *
	 * @param profiles the profiles
	 */
	public ProfilesLoaderConfigs(EncodingProfileManager profiles) {
		this.profilemanager = profiles;
		rb = ResourceBundle.getBundle("lang", LangTools.getDefinedLanguage(), LangTools.loadLanguagesPath());
		initGUI();
		this.setModal(true);
		setDocandStyles();
		initComponents();

	}

	/**
	 * Inits the components.
	 */
	private void initComponents() {

		ProfileVideoEncodingInfoContainer[] profiles = SortListProfiles(profilemanager.getProfiles());
		if (profiles != null) {
			ListModel profilesz = new DefaultComboBoxModel<>(profiles);
			jListprofiles.setModel(profilesz);
			jListprofiles.setSelectedIndex(0);
			displaySettingsofContainer(
					((ProfileVideoEncodingInfoContainer) jListprofiles.getSelectedValue()).getEncinfoContainer());

		}
	}

	/**
	 * Sort list profiles.
	 *
	 * @param profilesin the profilesin
	 * @return the profile video encoding info container[]
	 */
	private ProfileVideoEncodingInfoContainer[] SortListProfiles(
			IndexedHashMap<String, ProfileVideoEncodingInfoContainer> profilesin) {
		ProfileVideoEncodingInfoContainer[] profs = new ProfileVideoEncodingInfoContainer[profilesin.size()];

		ArrayList<String> profileNames = new ArrayList<>();
		for (int i = 0; i < profilesin.size(); i++) {
			profileNames.add(profilesin.getKeyAt(i));
		}

		Collections.sort(profileNames);

		for (int i = 0; i < profileNames.size(); i++) {
			profs[i] = profilesin.get(profileNames.get(i));
		}

		return profs;

	}

	/**
	 * Inits the gui.
	 */
	private void initGUI() {
		try {
			{

				GridBagLayout thisLayout = new GridBagLayout();
				thisLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1,
						0.1 };
				thisLayout.rowHeights = new int[] { 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7 };
				thisLayout.columnWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1,
						0.1, 0.1 };

				thisLayout.columnWidths = new int[] { 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7 };
				getContentPane().setLayout(thisLayout);
				this.setTitle(LangTools.getResourceBundleWordLanguage(rb, "Load profile", "general.loadprofile"));
				{
					jButtonload = new JButton();
					getContentPane().add(jButtonload, new GridBagConstraints(9, 13, 3, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jButtonload.setText(LangTools.getResourceBundleWordLanguage(rb, "Load", "general.load"));
					jButtonload.setActionCommand(LOADCHOOSENPROFILE);
				}
				{
					jButtoncancel = new JButton();
					getContentPane().add(jButtoncancel, new GridBagConstraints(6, 13, 3, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jButtoncancel.setText(LangTools.getResourceBundleWordLanguage(rb, "Cancel", "general.cancel"));
					jButtoncancel.setActionCommand(CANCELPROFILESCHOOSER);
					jButtoncancel.addActionListener(this);
				}
				{
					jScrollPaneProfileSettings = new JScrollPane();
					getContentPane().add(jScrollPaneProfileSettings, new GridBagConstraints(4, 0, 10, 13, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jScrollPaneProfileSettings.setBorder(BorderFactory.createTitledBorder(
							LangTools.getResourceBundleWordLanguage(rb, "Settings", "general.settings")));
					{
						jTextPanesettings = new JTextPane();
						jScrollPaneProfileSettings.setViewportView(jTextPanesettings);

						jTextPanesettings.setEditable(false);
					}
				}
				{
					jButtonloadfile = new JButton();
					getContentPane().add(jButtonloadfile, new GridBagConstraints(1, 13, 3, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

					jButtonloadfile.setText(
							LangTools.getResourceBundleWordLanguage(rb, "Load from file", "profilegui.loadfromfile"));
					jButtonloadfile.setActionCommand(LOADPROFILEFROMFILE);
					// jButtonloadfile.addActionListener(this);
				}
				{
					jScrollPanelist = new JScrollPane();
					jListprofiles = new JList();
					jScrollPanelist.setViewportView(jListprofiles);
					jListprofiles.setBorder(BorderFactory.createTitledBorder(
							LangTools.getResourceBundleWordLanguage(rb, "Profiles", "general.profiles")));
					jListprofiles.addListSelectionListener(this);
					getContentPane().add(jScrollPanelist, new GridBagConstraints(0, 0, 4, 13, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				}
			}
			{
				this.setSize(656, 426);
			}
		} catch (Exception e) {
		    Logger.error(e);
		}
	}

	/**
	 * Adds the ok action listener.
	 *
	 * @param l the l
	 */
	public void addOkActionListener(ActionListener l) {
		jButtonload.addActionListener(l);
	}

	/**
	 * Adds the load from file action listener.
	 *
	 * @param l the l
	 */
	public void addLoadFromFileActionListener(ActionListener l) {
		jButtonloadfile.addActionListener(l);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.
	 * ListSelectionEvent)
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		boolean adjust = e.getValueIsAdjusting();
		if (!adjust) {
			JList list = (JList) e.getSource();
			ProfileVideoEncodingInfoContainer profile = (ProfileVideoEncodingInfoContainer) list.getSelectedValue();
			displaySettingsofContainer(profile.getEncinfoContainer());
			// int selections[] = list.getSelectedIndices();
			// Object selectionValues[] = list.getSelectedValues();
		}
	}

	/**
	 * Display settings of container.
	 *
	 * @param cont the cont
	 */
	private void displaySettingsofContainer(IGeneralVideoEncInfoContainer cont) {

		try {
			doc.remove(0, doc.getLength());

			setVideoInformation(cont);
			doc.insertString(doc.getLength(), "\n", regular);
			setAudioInformation(cont);
			if (cont.getSubtitleInfoContainer().isUseHardSubs() || cont.getSubtitleInfoContainer().isUseSoftSubs()) {
				doc.insertString(doc.getLength(), "\n", regular);
				setSubtitlesInformation(cont);
			}

		} catch (BadLocationException e) {
		    Logger.error(e);
		}

	}

	/**
	 * Sets the video information.
	 *
	 * @param cont the new video information
	 */
	private void setVideoInformation(IGeneralVideoEncInfoContainer cont) {
		try {

			doc.insertString(0,
					LangTools.getResourceBundleWordLanguage(rb, "Video settings", "videoinfo.settings") + "\n\n", bold);
			doc.insertString(doc.getLength(),
					LangTools.getResourceBundleWordLanguage(rb, "Video codec", "videoinfo.codec") + ":\t", bold2);
			doc.insertString(doc.getLength(), cont.getVideocodec().toString() + "\n", regular);

			doc.insertString(doc.getLength(),
					LangTools.getResourceBundleWordLanguage(rb, "Container", "videoinfo.container") + ":\t", bold2);
			doc.insertString(doc.getLength(), cont.getVideoContainer().toString() + "\n", regular);

			if (cont.isUseVideoEncodingCBR() || cont.isUseVideoEncodingFileSize()) {
				doc.insertString(doc.getLength(),
						LangTools.getResourceBundleWordLanguage(rb, "videoaudiogui.constantbitrate", "Constant bitrate")
								+ ":\t",
						bold2);
				doc.insertString(doc.getLength(), cont.getVideoConstBitrate() + "\n", regular);
			}

			else if (cont.isUseVideoEncodingVBR()) {
				doc.insertString(doc.getLength(),
						LangTools.getResourceBundleWordLanguage(rb, "Variable bitrate", "videoaudiogui.variablebitrate")
								+ ":\t",
						bold2);
				doc.insertString(doc.getLength(), cont.getVideoVBRQualityValue() + "\n", regular);
			}

			if (cont.isUseVideoEncodingFileSize()) {
				doc.insertString(doc.getLength(),
						LangTools.getResourceBundleWordLanguage(rb, "Video file size", "videogui.filesize") + ":\t",
						bold2);
				doc.insertString(doc.getLength(), cont.getVideoEncodingFileSize() + "\n", regular);
			}

			doc.insertString(doc.getLength(),
					LangTools.getResourceBundleWordLanguage(rb, "Video aspect", "videogui.aspect") + ":\t", bold2);
			if (!cont.getVideoaspectsizecomponent().getNameAspect().equals(AspectComponent.SAMEASSOURCE))
				doc.insertString(doc.getLength(), cont.getVideoaspectsizecomponent().getSizemesu() + "\n", regular);
			else
				doc.insertString(doc.getLength(), "Same as source" + "\n", regular);

			doc.insertString(doc.getLength(),
					LangTools.getResourceBundleWordLanguage(rb, "Aspect ratio", "videoinfo.ratio") + ":\t", bold2);
			doc.insertString(doc.getLength(), cont.getVideoaspectratio() + "\n", regular);

			doc.insertString(doc.getLength(),
					LangTools.getResourceBundleWordLanguage(rb, "Framerate", "general.framerate") + ":\t", bold2);
			doc.insertString(doc.getLength(), cont.getVideoframerate() + "\n", regular);

			doc.insertString(doc.getLength(),
					LangTools.getResourceBundleWordLanguage(rb, "Step number", "videoinfo.pass") + ":\t", bold2);
			if (cont.isTwopassEncoding())
				doc.insertString(doc.getLength(), "2" + "\n", regular);
			else
				doc.insertString(doc.getLength(), "1" + "\n", regular);

//		if(cont.getExtravideoffmpegcmd()!=null){
//			doc.insertString( doc.getLength(), LangTools.getResourceBundleWordLanguage(rb, "Custom FFmpeg commands", "videoaudiogui.extracmds")+ ":\n", bold2 );
//			doc.insertString( doc.getLength(), cont.getExtravideoffmpegcmd()+"\n", regular );
//		}

		} catch (BadLocationException e) {
		    Logger.error(e);
		}
	}

	/**
	 * Sets the audio information.
	 *
	 * @param cont the new audio information
	 */
	private void setAudioInformation(IGeneralVideoEncInfoContainer cont) {
		try {

			doc.insertString(doc.getLength(),
					LangTools.getResourceBundleWordLanguage(rb, "Audio settings", "audioinfo.settings") + "\n\n", bold);
			doc.insertString(doc.getLength(),
					LangTools.getResourceBundleWordLanguage(rb, "Audio codec", "audioinfo.codec") + ":\t", bold2);
			doc.insertString(doc.getLength(), cont.getAudiocodec().toString() + "\n", regular);

			if (cont.isUseAudioCBR()) {
				doc.insertString(doc.getLength(),
						LangTools.getResourceBundleWordLanguage(rb, "Constant bitrate", "videoaudiogui.constantbitrate")
								+ ":\t",
						bold2);
				doc.insertString(doc.getLength(), cont.getAudioConstantBitrateValue() + "\n", regular);
			}

			else {
				doc.insertString(doc.getLength(),
						LangTools.getResourceBundleWordLanguage(rb, "Variable bitrate", "videoaudiogui.variablebitrate")
								+ ":\t",
						bold2);
				doc.insertString(doc.getLength(), cont.getAudioVariableBitrateValues() + "\n", regular);
			}

			doc.insertString(doc.getLength(),
					LangTools.getResourceBundleWordLanguage(rb, "Channels", "audioinfo.channels") + ":\t\t", bold2);
			doc.insertString(doc.getLength(), cont.getAudiochannels().toString() + "\n", regular);

			doc.insertString(doc.getLength(),
					LangTools.getResourceBundleWordLanguage(rb, "Frequency", "audioinfo.frequency") + ":\t", bold2);
			doc.insertString(doc.getLength(), cont.getAudiosamplerate().toString() + "\n", regular);

			if (cont.getExtraaudioffmpegcmd() != null) {
				doc.insertString(doc.getLength(),
						LangTools.getResourceBundleWordLanguage(rb, "Custom FFmpeg commands", "videoaudiogui.extracmds")
								+ ":\n",
						bold2);
				doc.insertString(doc.getLength(), cont.getExtraaudioffmpegcmd() + "\n", regular);
			}

		} catch (BadLocationException e) {
		    Logger.error(e);
		}
	}

	/**
	 * Sets the subtitles information.
	 *
	 * @param cont the new subtitles information
	 */
	private void setSubtitlesInformation(IGeneralVideoEncInfoContainer cont) {
		try {

			doc.insertString(doc.getLength(),
					LangTools.getResourceBundleWordLanguage(rb, "Subtitle settings", "subtitlesinfo.settings") + "\n\n",
					bold);

			if (cont.getSubtitleInfoContainer().isUseHardSubs()) {

				doc.insertString(doc.getLength(),
						LangTools.getResourceBundleWordLanguage(rb, "HardSubtitles", "subtitlesgui.hardsubtitles")
								+ "\n\n",
						bold2);

				doc.insertString(doc.getLength(),
						LangTools.getResourceBundleWordLanguage(rb, "Encoding", "subtitlesinfo.encoding") + ":\t",
						bold2);
				if (cont.getSubtitleInfoContainer().getSubsAlternativeEncoding() != null)
					doc.insertString(doc.getLength(),
							cont.getSubtitleInfoContainer().getSubsAlternativeEncoding() + "\n", regular);
				else
					doc.insertString(doc.getLength(), cont.getSubtitleInfoContainer().getSubsEncoding() + "\n",
							regular);

				doc.insertString(doc.getLength(),
						LangTools.getResourceBundleWordLanguage(rb, "Font name", "subtitlesinfo.fontname") + ":\t",
						bold2);
				doc.insertString(doc.getLength(), cont.getSubtitleInfoContainer().getSubsFontName() + "\n", regular);

				doc.insertString(doc.getLength(),
						LangTools.getResourceBundleWordLanguage(rb, "Font size", "subtitlesinfo.fontsize") + ":\t\t",
						bold2);
				doc.insertString(doc.getLength(),
						String.valueOf(cont.getSubtitleInfoContainer().getSubsFontSize()) + "\n", regular);

				doc.insertString(doc.getLength(),
						LangTools.getResourceBundleWordLanguage(rb, "Bold style", "subtitlesinfo.boldstyle") + ":\t",
						bold2);
				if (cont.getSubtitleInfoContainer().isSubsUseBold())
					doc.insertString(doc.getLength(),
							LangTools.getResourceBundleWordLanguage(rb, "Yes", "general.yes") + "\n", regular);
				else
					doc.insertString(doc.getLength(),
							LangTools.getResourceBundleWordLanguage(rb, "No", "general.no") + "\n", regular);

				doc.insertString(doc.getLength(),
						LangTools.getResourceBundleWordLanguage(rb, "Italic style", "subtitlesinfo.italicstyle")
								+ ":\t",
						bold2);
				if (cont.getSubtitleInfoContainer().isSubsUseItalic())
					doc.insertString(doc.getLength(),
							LangTools.getResourceBundleWordLanguage(rb, "Yes", "general.yes") + "\n", regular);
				else
					doc.insertString(doc.getLength(),
							LangTools.getResourceBundleWordLanguage(rb, "No", "general.no") + "\n", regular);

				doc.insertString(doc.getLength(),
						LangTools.getResourceBundleWordLanguage(rb, "Color", "subtitlesinfo.color") + ":\t\t", bold2);
				doc.insertString(doc.getLength(), cont.getSubtitleInfoContainer().getSubsColor().toString() + "\n",
						regular);

				doc.insertString(doc.getLength(),
						LangTools.getResourceBundleWordLanguage(rb, "Alignment", "subtitlesinfo.alignment") + ":\t",
						bold2);
				doc.insertString(doc.getLength(), cont.getSubtitleInfoContainer().getSubsAlignment().toString() + "\n",
						regular);

				doc.insertString(doc.getLength(),
						LangTools.getResourceBundleWordLanguage(rb, "Outline", "subtitlesinfo.outline") + ":\t\t",
						bold2);
				doc.insertString(doc.getLength(), cont.getSubtitleInfoContainer().getSubsOutline() + "\n", regular);

				doc.insertString(doc.getLength(),
						LangTools.getResourceBundleWordLanguage(rb, "Shadow", "subtitlesinfo.shadow") + ":\t\t", bold2);
				doc.insertString(doc.getLength(), cont.getSubtitleInfoContainer().getSubsShadow() + "\n", regular);

				doc.insertString(doc.getLength(),
						LangTools.getResourceBundleWordLanguage(rb, "Border style", "subtitlesinfo.borderstyle")
								+ ":\t",
						bold2);
				doc.insertString(doc.getLength(),
						cont.getSubtitleInfoContainer().getSubsBorderStyle().toString() + "\n", regular);
			}

			else if (cont.getSubtitleInfoContainer().isUseSoftSubs()) {

				doc.insertString(doc.getLength(),
						LangTools.getResourceBundleWordLanguage(rb, "SoftSubtitles", "subtitlesgui.softsubtitles")
								+ "\n\n",
						bold2);
			}

		} catch (BadLocationException e) {
		    Logger.error(e);
		}
	}

	/**
	 * Sets the docand styles.
	 */
	private void setDocandStyles() {

		doc = jTextPanesettings.getStyledDocument();

		Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
		regular = doc.addStyle("regular", def);
		StyleConstants.setForeground(regular, Color.BLUE);

		// Create a bold style
		bold = doc.addStyle("bold", def);
		StyleConstants.setBold(bold, true);
		StyleConstants.setFontFamily(bold, "serif");
		StyleConstants.setFontSize(bold, 16);

		bold2 = doc.addStyle("bold2", def);
		StyleConstants.setBold(bold2, true);
		StyleConstants.setFontFamily(bold2, "serif");
		StyleConstants.setFontSize(bold2, 12);
		// StyleConstants.setForeground(bold2, Color.BLUE);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (cmd.equals(CANCELPROFILESCHOOSER))
			this.dispose();

	}

	/**
	 * Gets the choosen profile encoding configs.
	 *
	 * @return the choosen profile encoding configs
	 */
	public IGeneralVideoEncInfoContainer getChoosenProfileEncodingConfigs() {
		ProfileVideoEncodingInfoContainer profile = (ProfileVideoEncodingInfoContainer) jListprofiles
				.getSelectedValue();
		return profile.getEncinfoContainer();

	}

	/**
	 * Gets the profile from file config.
	 *
	 * @return the profile from file config
	 */
	public IGeneralVideoEncInfoContainer getProfileFromFileConfig() {
		String filepath = ListFiles.getSingleFilepathFileChooser(this);
		IGeneralVideoEncInfoContainer container = null;
		if (filepath != null) {

			PropertiesConfiguration props = PropertiesWorker.loadPropertiesRelativePath(filepath);
			container = EncodingProfileManager.extractVideoEncInformation(props, false);

		}
		return container;
	}

}
