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
package pt.ornrocha.rencoder.gui.components.panels.Scroll;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.tinylog.Logger;

import com.jidesoft.swing.ComboBoxSearchable;

import eu.hansolo.custom.SteelCheckBox;
import pt.ornrocha.rencoder.ffmpegWrapper.configurations.FFmpegManager;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.subtitles.SubEncodings;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.subtitles.SubtiltesAlignment;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.subtitles.SubtitlesBorderStyle;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.subtitles.SubtitlesColor;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.subtitles.SubtitlesFontStyle;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoContainers;
import pt.ornrocha.rencoder.ffmpegWrapper.subtitles.SubtitleConverter;
import pt.ornrocha.rencoder.ffmpegWrapper.utilities.FFmpegUtils;
import pt.ornrocha.rencoder.gui.components.panels.auxiliar.StaticVlcMethods;
import pt.ornrocha.rencoder.gui.components.panels.info.FontDisplayPanel;
import pt.ornrocha.rencoder.gui.components.panels.info.SubtitlesImagePreview;
import pt.ornrocha.rencoder.gui.components.panels.player.RunPlayer;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.helpers.osystem.OS;
import pt.ornrocha.rencoder.helpers.osystem.OSystem;
import pt.ornrocha.rencoder.helpers.props.fields.StaticGlobalFields;
import pt.ornrocha.rencoder.helpers.props.managers.auxiliar.EncodingPropsAuxiliar;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;
import pt.ornrocha.rencoder.mediafiles.files.containers.base.Subtitlefile;
import pt.ornrocha.rencoder.mediafiles.files.containers.base.Videofile;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.SubtitleInfoContainer;
import pt.ornrocha.rencoder.mediafiles.setfiles.processfiles.ProcessFiles;

// TODO: Auto-generated Javadoc
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
public class SubtitlesConfigScrollPanel extends JScrollPane implements ActionListener, ChangeListener, MouseListener{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The j panelmain. */
	private JPanel jPanelmain;
	
	/** The steel check box hardsubs. */
	private SteelCheckBox steelCheckBoxHardsubs;
	
	/** The j labeloutline. */
	private JLabel jLabeloutline;
	
	/** The j panelsublimits. */
	private JPanel jPanelsublimits;
	
	/** The j combo box border style. */
	private JComboBox jComboBoxBorderStyle;
	
	/** The j label border style. */
	private JLabel jLabelBorderStyle;
	
	/** The j combo boxalignment. */
	private JComboBox jComboBoxalignment;
	
	/** The j labelalignment. */
	private JLabel jLabelalignment;
	
	/** The j spinnershadow. */
	private JSpinner jSpinnershadow;
	
	/** The j labelshadow. */
	private JLabel jLabelshadow;
	
	/** The j spinneroutline. */
	private JSpinner jSpinneroutline;
	
	/** The j combo boxencoding. */
	private JComboBox jComboBoxencoding;
	
	/** The j labelenc. */
	private JLabel jLabelenc;
	
	/** The j spinner font size. */
	private JSpinner jSpinnerFontSize;
	
	/** The j buttonotherencoding. */
	private JButton jButtonotherencoding;
	
	/** The j combo boxcolor. */
	private JComboBox jComboBoxcolor;
	
	/** The j text fieldotherencoding. */
	private JTextField jTextFieldotherencoding;
	
	/** The j panelpreview. */
	private JPanel jPanelpreview;
	
	/** The j labelsize. */
	private JLabel jLabelsize;
	
	/** The j labelfontstyle. */
	private JLabel jLabelfontstyle;
	
	/** The j labelfont. */
	private JLabel jLabelfont;
	
	/** The j combo boxfontstyle. */
	private JComboBox jComboBoxfontstyle;
	
	/** The j combo box font. */
	private JComboBox jComboBoxFont;
	
	/** The j labelcolor. */
	private JLabel jLabelcolor;
	
	/** The j paneloptions. */
	private JPanel jPaneloptions;
	
	/** The steel check boxsoftsubs. */
	private SteelCheckBox steelCheckBoxsoftsubs;
	
	/** The video container. */
	private VideoContainers videoContainer =null;
	
	/** The infocont. */
	private IGeneralVideoEncInfoContainer infocont=null;
	
	/** The j buttonsubsoftconfigure. */
	private JButton jButtonsubsoftconfigure;
	
	private JComboBox jComboBoxsubsoftlanguage;

	/** The fontpanelpreview. */
	private FontDisplayPanel fontpanelpreview;
	
	/** The alternativefontenc. */
	private String alternativefontenc=null;
	
	/** The rb. */
	private ResourceBundle rb;
	
	/** The changefontstyle. */
	private static String CHANGEFONTSTYLE="changefontstyle";
	
	/** The changefontsize. */
	private static String CHANGEFONTSIZE="changefontsize";
	
	/** The changefontname. */
	private static String CHANGEFONTNAME="changefontname";
	
	/** The changehardsubstate. */
	private static String CHANGEHARDSUBSTATE="changehardsubstate";
	
	/** The changesoftsubstate. */
	private static String CHANGESOFTSUBSTATE="changesoftsubstate";
	
	/** The setalternativeencoding. */
	private static String SETALTERNATIVEENCODING="setalternativeencoding";
	
	/** The configuresoftsubs. */
	public static String CONFIGURESOFTSUBS="configuresoftsubs";
	
	private JButton jButtonPreview;
	
	private static String PREVIEWSUBTITLES="previewsubtitles";
	
	private Videofile movie=null;
	
	private boolean unlockpreview=false;
	
	private JDialog parent=null;
	
	private boolean isGeneralUserProfile=false;
	
	private String langinuse="eng";
	

	/**
	 * Instantiates a new subtitles config scroll panel.
	 */
	public SubtitlesConfigScrollPanel(JDialog parent){
		this.parent=parent;
		rb = ResourceBundle.getBundle("lang",LangTools.getDefinedLanguage(), LangTools.loadLanguagesPath());
		initGUI();
		initComponents();
	}
	
	
	
	/**
	 * Inits the components.
	 */
	@SuppressWarnings("unchecked")
	private void initComponents(){
		steelCheckBoxHardsubs.setColored(true);
		steelCheckBoxsoftsubs.setColored(true);
		
		
		DefaultComboBoxModel<SubtitlesColor> colors = new DefaultComboBoxModel<>(SubtitlesColor.values());
		jComboBoxcolor.setModel(colors);
		
		DefaultComboBoxModel<SubtiltesAlignment> Alignment = new DefaultComboBoxModel<>(SubtiltesAlignment.values());
		jComboBoxalignment.setModel(Alignment);
		
		DefaultComboBoxModel<SubEncodings> encoding = new DefaultComboBoxModel<>(SubEncodings.values());
		jComboBoxencoding.setModel(encoding);
		jComboBoxencoding.setSelectedIndex(0);
		
		DefaultComboBoxModel<SubtitlesBorderStyle> borderstyle = new DefaultComboBoxModel<>(SubtitlesBorderStyle.values());
		jComboBoxBorderStyle.setModel(borderstyle);
		
		DefaultComboBoxModel<SubtitlesFontStyle> fontstyle = new DefaultComboBoxModel<>(SubtitlesFontStyle.values());
		jComboBoxfontstyle.setModel(fontstyle);
		
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    String[] fontFamilyNames = ge.getAvailableFontFamilyNames();
	    
	    DefaultComboBoxModel<String> fontname = new DefaultComboBoxModel<>(fontFamilyNames);
	    jComboBoxFont.setModel(fontname);
	    
	    
	    
	    jComboBoxFont.setSelectedItem("Arial");
	    
	    String checkarial= (String) jComboBoxFont.getSelectedItem();
	    if(!checkarial.equals("Arial")){
	          OS os= OSystem.getOperationSystem();
    	      if(os.equals(OS.LINUX64)|| os.equals(OS.LINUX32))
	              jComboBoxFont.setSelectedItem("DejaVu Sans");
    	      else if(os.equals(OS.MACOS))
    	    	  jComboBoxFont.setSelectedItem("Lucida Grande");
	    }
	    
	    SpinnerNumberModel fontsize = new SpinnerNumberModel(24, 10, 40, 1);
		jSpinnerFontSize.setModel(fontsize);
		
		SpinnerNumberModel modeloutline = new SpinnerNumberModel(1, 0, 5, 1);
		jSpinneroutline.setModel(modeloutline);
		
		SpinnerNumberModel modelshadow = new SpinnerNumberModel(0, 0, 5, 1);
		jSpinnershadow.setModel(modelshadow);
		
		
		ArrayList<String> langs= FFmpegManager.getInstance().getOrderedCountryList();
		String[] arraylangs = langs.stream().toArray(String[]::new);
		DefaultComboBoxModel<String> softsublanguages = new DefaultComboBoxModel<>(arraylangs);
		jComboBoxsubsoftlanguage.setModel(softsublanguages);
		String defaultlangcode=FFmpegManager.getInstance().getDefaultSoftSubtitleLanguage();
		this.langinuse=defaultlangcode;
		String defaultlang=FFmpegManager.getInstance().getLanguageFromISO3Code(defaultlangcode);
		jComboBoxsubsoftlanguage.setSelectedItem(defaultlang);
		
		hardSubSelectionAtion();
		SoftSubSelectionAction();
		
		
	}
	
	
	public void setIsGeneralConfigUserProfile(boolean bol){
		this.isGeneralUserProfile=bol;
	}
	
	
	

	/**
	 * Inits the gui.
	 */
	private void initGUI() {
		try {
			{   
				//this.setPreferredSize(new java.awt.Dimension(564, 448));
				this.setPreferredSize(new java.awt.Dimension(464, 348));
				{
					jPanelmain = new JPanel();
					GridBagLayout jPanelmainLayout = new GridBagLayout();
					this.setViewportView(jPanelmain);
					jPanelmainLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
					jPanelmainLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
					jPanelmainLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
					jPanelmainLayout.columnWidths = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
					jPanelmain.setLayout(jPanelmainLayout);
					{
						steelCheckBoxHardsubs = new SteelCheckBox();
						jPanelmain.add(steelCheckBoxHardsubs, new GridBagConstraints(0, 0, 5, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 0, 0), 0, 0));
						steelCheckBoxHardsubs.setText(LangTools.getResourceBundleWordLanguage(rb, "HardSubtitles", "subtitlesgui.hardsubtitles"));
						steelCheckBoxHardsubs.setActionCommand(CHANGEHARDSUBSTATE);
						steelCheckBoxHardsubs.addActionListener(this);
					}
					{
						steelCheckBoxsoftsubs = new SteelCheckBox();
						jPanelmain.add(steelCheckBoxsoftsubs, new GridBagConstraints(0, 1, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 0, 0), 0, 0));
						steelCheckBoxsoftsubs.setText(LangTools.getResourceBundleWordLanguage(rb, "SoftSubtitles", "subtitlesgui.softsubtitles"));
						steelCheckBoxsoftsubs.setActionCommand(CHANGESOFTSUBSTATE);
						steelCheckBoxsoftsubs.addActionListener(this);
					}
					{
						jPanelsublimits = new JPanel();
						GridBagLayout jPanelsublimitsLayout = new GridBagLayout();
						jPanelmain.add(jPanelsublimits, new GridBagConstraints(0, 2, 12, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jPanelsublimits.setBorder(BorderFactory.createTitledBorder(null, LangTools.getResourceBundleWordLanguage(rb, "Subtitles limits", "subtitlesgui.limits"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION));
						jPanelsublimitsLayout.rowWeights = new double[] {0.1, 0.1, 0.1};
						jPanelsublimitsLayout.rowHeights = new int[] {7, 7, 7};
						jPanelsublimitsLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
						jPanelsublimitsLayout.columnWidths = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
						jPanelsublimits.setLayout(jPanelsublimitsLayout);
						{
							jLabeloutline = new JLabel();
							jPanelsublimits.add(jLabeloutline, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jLabeloutline.setText(LangTools.getResourceBundleWordLanguage(rb, "Outline", "subtitlesinfo.outline"));
							jLabeloutline.setHorizontalAlignment(SwingConstants.CENTER);
						}
						{
							
							jSpinneroutline = new JSpinner();
							jPanelsublimits.add(jSpinneroutline, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						
						}
						{
							jLabelshadow = new JLabel();
							jPanelsublimits.add(jLabelshadow, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jLabelshadow.setText(LangTools.getResourceBundleWordLanguage(rb, "Shadow", "subtitlesinfo.shadow"));
						}
						{
						
							jSpinnershadow = new JSpinner();
							jPanelsublimits.add(jSpinnershadow, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					
						}
						{
							jLabelBorderStyle = new JLabel();
							jPanelsublimits.add(jLabelBorderStyle, new GridBagConstraints(7, 0, 3, 1, 0.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jLabelBorderStyle.setText(LangTools.getResourceBundleWordLanguage(rb, "Border style", "subtitlesinfo.borderstyle"));
						}
						{
							
							jComboBoxBorderStyle = new JComboBox();
							jPanelsublimits.add(jComboBoxBorderStyle, new GridBagConstraints(7, 1, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
							
						}
					}
					{
						jPaneloptions = new JPanel();
						GridBagLayout jPaneloptionsLayout = new GridBagLayout();
						jPanelmain.add(jPaneloptions, new GridBagConstraints(0, 4, 12, 11, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jPaneloptions.setBorder(BorderFactory.createTitledBorder(LangTools.getResourceBundleWordLanguage(rb, "Subtitle settings", "subtitlesinfo.settings")));
						jPaneloptionsLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
						jPaneloptionsLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
						jPaneloptionsLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
						jPaneloptionsLayout.columnWidths = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
						jPaneloptions.setLayout(jPaneloptionsLayout);
						{
							jLabelalignment = new JLabel();
							jPaneloptions.add(jLabelalignment, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
							jLabelalignment.setText(LangTools.getResourceBundleWordLanguage(rb, "Alignment", "subtitlesinfo.alignment"));
						}
						{
						
							jComboBoxalignment = new JComboBox();
							jPaneloptions.add(jComboBoxalignment, new GridBagConstraints(1, 0, 8, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			
						}
						{
							jLabelcolor = new JLabel();
							jPaneloptions.add(jLabelcolor, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
							jLabelcolor.setText(LangTools.getResourceBundleWordLanguage(rb, "Color", "subtitlesinfo.color"));
						}
						{
							
							jComboBoxcolor = new JComboBox();
							jPaneloptions.add(jComboBoxcolor, new GridBagConstraints(1, 1, 8, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
							
						}
						{
							jLabelenc = new JLabel();
							jPaneloptions.add(jLabelenc, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
							jLabelenc.setText(LangTools.getResourceBundleWordLanguage(rb, "Encoding", "subtitlesinfo.encoding"));
						}
						{
						
							jComboBoxencoding = new JComboBox();
							jPaneloptions.add(jComboBoxencoding, new GridBagConstraints(1, 2, 6, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						
						}
						{
							
							jComboBoxFont = new JComboBox();
							jPaneloptions.add(jComboBoxFont, new GridBagConstraints(0, 4, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
							jComboBoxFont.setActionCommand(CHANGEFONTNAME);
							jComboBoxFont.addActionListener(this);
						}
						{
							
							jComboBoxfontstyle = new JComboBox();
							jPaneloptions.add(jComboBoxfontstyle, new GridBagConstraints(4, 4, 5, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
							jComboBoxfontstyle.setActionCommand(CHANGEFONTSTYLE);
							jComboBoxfontstyle.addActionListener(this);
						}
						{
							jLabelfont = new JLabel();
							jPaneloptions.add(jLabelfont, new GridBagConstraints(0, 3, 3, 1, 0.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
							jLabelfont.setText(LangTools.getResourceBundleWordLanguage(rb, "Font name", "subtitlesinfo.fontname"));
							jLabelfont.setHorizontalAlignment(SwingConstants.CENTER);
						}
						{
							jLabelfontstyle = new JLabel();
							jPaneloptions.add(jLabelfontstyle, new GridBagConstraints(5, 3, 4, 1, 0.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
							jLabelfontstyle.setText(LangTools.getResourceBundleWordLanguage(rb, "Font style", "subtitlesinfo.fontstyle"));
							jLabelfontstyle.setHorizontalAlignment(SwingConstants.CENTER);
						}
						{
							jLabelsize = new JLabel();
							jPaneloptions.add(jLabelsize, new GridBagConstraints(9, 3, 3, 1, 0.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
							jLabelsize.setText(LangTools.getResourceBundleWordLanguage(rb, "Font size", "subtitlesinfo.fontsize"));
							jLabelsize.setHorizontalAlignment(SwingConstants.CENTER);
						}
						{
							jPanelpreview = new JPanel();
							fontpanelpreview = new FontDisplayPanel();
							jPaneloptions.add(fontpanelpreview, new GridBagConstraints(0, 5, 10, 5, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
							fontpanelpreview.setBorder(BorderFactory.createTitledBorder(LangTools.getResourceBundleWordLanguage(rb, "Preview", "subtitlesinfo.previewfont")));
							fontpanelpreview.setFontSize(25);
						}
						{
							jTextFieldotherencoding = new JTextField();
							jPaneloptions.add(jTextFieldotherencoding, new GridBagConstraints(7, 2, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
							jTextFieldotherencoding.setText(LangTools.getResourceBundleWordLanguage(rb, "Other encoding?", "subtitlesinfo.otherencoding"));
							jTextFieldotherencoding.addMouseListener(this);
						}
						{
							jButtonotherencoding = new JButton();
							jPaneloptions.add(jButtonotherencoding, new GridBagConstraints(9, 2, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
							jButtonotherencoding.setText(LangTools.getResourceBundleWordLanguage(rb,"Set","general.set"));
							jButtonotherencoding.setActionCommand(SETALTERNATIVEENCODING);
							jButtonotherencoding.addActionListener(this);
						}
						{
							
							jSpinnerFontSize = new JSpinner();
							jPaneloptions.add(jSpinnerFontSize, new GridBagConstraints(9, 4, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
							jSpinnerFontSize.addChangeListener(this);
						}
						{
							if(!OSystem.isMacOS()){
							jButtonPreview = new JButton();
							jPaneloptions.add(jButtonPreview, new GridBagConstraints(10, 5, 2, 5, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
							jButtonPreview.setText(LangTools.getResourceBundleWordLanguage(rb, "<html>Preview <br>Subtitles</html>", "subtitlesgui.previewsubs"));
							jButtonPreview.setHorizontalAlignment(SwingConstants.CENTER);
							jButtonPreview.setActionCommand(PREVIEWSUBTITLES);
							jButtonPreview.addActionListener(this);
							}
						}
					}
					{
						jButtonsubsoftconfigure = new JButton();
						jPanelmain.add(jButtonsubsoftconfigure, new GridBagConstraints(4, 1, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						jButtonsubsoftconfigure.setText(LangTools.getResourceBundleWordLanguage(rb, "Configure", "general.configure"));
						jButtonsubsoftconfigure.setActionCommand(CONFIGURESOFTSUBS);
						jButtonsubsoftconfigure.setEnabled(false);
						//jButtonsubsoftconfigure.addActionListener(this);
						
					}
					{
						jComboBoxsubsoftlanguage= new JComboBox<>();
						ComboBoxSearchable searchable = new ComboBoxSearchable(jComboBoxsubsoftlanguage);
						jPanelmain.add(jComboBoxsubsoftlanguage, new GridBagConstraints(8, 1, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						jComboBoxsubsoftlanguage.setToolTipText("Default language of the subtitle");
						jComboBoxsubsoftlanguage.setEnabled(false);
					}
				}

			}
		} catch(Exception e) {
		    Logger.error(e);
		}
	
	
}
	
	/**
	 * Sets the external action listeners.
	 *
	 * @param l the new external action listeners
	 */
	public void setExternalActionListeners(ActionListener l){
		jButtonsubsoftconfigure.addActionListener(l);
	}
	
	/**
	 * Sets the video container.
	 *
	 * @param cont the new video container
	 */
	public void setVideoContainer(VideoContainers cont){
		this.videoContainer=cont;
		checkVideoContainerAllowedSubs();
	}
	
	
	

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		
		if(action.equals(CHANGEFONTSTYLE))
			changeFontStyle();
		else if(action.equals(CHANGEFONTNAME))
			changeFontName();
		else if(action.equals(CHANGEHARDSUBSTATE))
			hardSubSelectionAtion();
		else if(action.equals(CHANGESOFTSUBSTATE))
			SoftSubSelectionAction();
		else if(action.equals(SETALTERNATIVEENCODING))
			setAlternativeFontEncoding();
		else if(action.equals(PREVIEWSUBTITLES)){
			LaunchSubPreviewPanel();
		}
		
	}
	
	/**
	 * Change font style.
	 */
	private void changeFontStyle(){
		SubtitlesFontStyle st = (SubtitlesFontStyle) jComboBoxfontstyle.getSelectedItem();
		fontpanelpreview.setFontStyle(st);
		fontpanelpreview.repaint();
	}
	
	/**
	 * Change font name.
	 */
	private void changeFontName(){
		String name = (String) jComboBoxFont.getSelectedItem();
		fontpanelpreview.setFontname(name);
		fontpanelpreview.repaint();
		
	}

	
	/**
	 * Sets the alternative font encoding.
	 */
	private void setAlternativeFontEncoding(){
		String str = jTextFieldotherencoding.getText();

		if(str!=null){
			if(!str.isEmpty()){
			
				this.alternativefontenc=str;
				jComboBoxencoding.setEnabled(false);
			}
			else if(str.isEmpty()){
				jComboBoxencoding.setEnabled(true);
				jTextFieldotherencoding.setText(LangTools.getResourceBundleWordLanguage(rb,"Other encoding?","subtitlesinfo.otherencoding"));
				this.alternativefontenc=null;
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		JSpinner sp = (JSpinner) e.getSource();
		if(OSystem.isMacOS()){
		fontpanelpreview.setFontSize((int) sp.getValue());
		fontpanelpreview.repaint();
		}
	}
    
	
	/**
	 * Hard sub selection ation.
	 */
	private void hardSubSelectionAtion(){
		
		if(steelCheckBoxHardsubs.isSelected()){
			jComboBoxBorderStyle.setEnabled(true);
		    jComboBoxalignment.setEnabled(true);
		    jSpinnershadow.setEnabled(true);
			jSpinneroutline.setEnabled(true);
			jComboBoxencoding.setEnabled(true);
			jSpinnerFontSize.setEnabled(true);
			jButtonotherencoding.setEnabled(true);
			jComboBoxcolor.setEnabled(true);
			jTextFieldotherencoding.setEnabled(true);
			jComboBoxfontstyle.setEnabled(true);
			jComboBoxFont.setEnabled(true);
			steelCheckBoxsoftsubs.setSelected(false);
			jButtonsubsoftconfigure.setEnabled(false);
			jComboBoxsubsoftlanguage.setEnabled(false);
			if(jButtonPreview!=null){
			     jButtonPreview.setEnabled(true);	
			}
			  
			
		}
		else{
			
            jComboBoxBorderStyle.setEnabled(false);
		    jComboBoxalignment.setEnabled(false);
		    jSpinnershadow.setEnabled(false);
			jSpinneroutline.setEnabled(false);
			jComboBoxencoding.setEnabled(false);
			jSpinnerFontSize.setEnabled(false);
			jButtonotherencoding.setEnabled(false);
			jComboBoxcolor.setEnabled(false);
			jTextFieldotherencoding.setEnabled(false);
			jComboBoxfontstyle.setEnabled(false);
			jComboBoxFont.setEnabled(false);
			if(jButtonPreview!=null)
			  jButtonPreview.setEnabled(false);
			//steelCheckBoxsoftsubs.setSelected(false);
		}

	  }
	
	/**
	 * Soft sub selection action.
	 */
	private void SoftSubSelectionAction(){
		if(steelCheckBoxsoftsubs.isSelected()){
			steelCheckBoxHardsubs.setSelected(false);
			jComboBoxsubsoftlanguage.setEnabled(true);
			hardSubSelectionAtion();
			if(isGeneralUserProfile)
				jButtonsubsoftconfigure.setEnabled(false);
			else
			   jButtonsubsoftconfigure.setEnabled(true);
		}
		else {
			jButtonsubsoftconfigure.setEnabled(false);
			jComboBoxsubsoftlanguage.setEnabled(false);
		}
	}
	
	/**
	 * Check video container allowed subs.
	 */
	private void checkVideoContainerAllowedSubs(){
		
		if(!videoContainer.equals(VideoContainers.MKV) && !videoContainer.equals(VideoContainers.MP4)){
			steelCheckBoxsoftsubs.setSelected(false);
			steelCheckBoxsoftsubs.setEnabled(false);
		}
		else
			steelCheckBoxsoftsubs.setEnabled(true);
	}
	
	
	  /**
  	 * Sets the parameters from encoding info container.
  	 *
  	 * @param info the new parameters from encoding info container
  	 */
  	public void setParametersFromEncodingInfoContainer(IGeneralVideoEncInfoContainer info, Videofile movie){
		  this.infocont=info;
		  this.movie=movie;
		  this.unlockpreview=true;
		  this.videoContainer=this.infocont.getVideoContainer();
		  setParametersInsideEncodingInfoContainer(); 
	  }
  	
  	public void setParametersFromEncodingInfoContainer(IGeneralVideoEncInfoContainer info){
		  this.infocont=info;
		  this.videoContainer=this.infocont.getVideoContainer();
		  setParametersInsideEncodingInfoContainer(); 
	  }
	
	  
	  /**
  	 * Sets the parameters inside encoding info container.
  	 */
  	private void setParametersInsideEncodingInfoContainer(){
		  
		  checkVideoContainerAllowedSubs();
		  
		  if(unlockpreview){
			  if(jButtonPreview!=null)
			    jButtonPreview.setEnabled(true);
			  
		  }
		  else{
			  if(jButtonPreview!=null)
			    jButtonPreview.setEnabled(false);
			  
		  }
		  
		 SubtitleInfoContainer subinfocont= this.infocont.getSubtitleInfoContainer();
		  
		  if(subinfocont.isUseSoftSubs() && (this.videoContainer.equals(VideoContainers.MKV) || this.videoContainer.equals(VideoContainers.MP4))){
			  this.steelCheckBoxsoftsubs.setSelected(true);
			  SoftSubSelectionAction();
			  String savedlangcode=FFmpegManager.getInstance().getDefaultSoftSubtitleLanguage();
			  String savedlang=FFmpegManager.getInstance().getLanguageFromISO3Code(savedlangcode);
			  jComboBoxsubsoftlanguage.setSelectedItem(savedlang);
			  this.langinuse=savedlangcode;
		  }
		  else if(subinfocont.isUseHardSubs()){
			  this.steelCheckBoxHardsubs.setSelected(true);
			  hardSubSelectionAtion(); 
			  
              jComboBoxBorderStyle.setSelectedItem(subinfocont.getSubsBorderStyle());
             
              jComboBoxencoding.setSelectedItem(subinfocont.getSubsEncoding());
              jComboBoxcolor.setSelectedItem(subinfocont.getSubsColor());
              jSpinnershadow.setValue(subinfocont.getSubsShadow());
              jSpinneroutline.setValue(subinfocont.getSubsOutline());

             
              String alternativeencoding=subinfocont.getSubsAlternativeEncoding();
              
              if(alternativeencoding!=null){
            	  jTextFieldotherencoding.setText(subinfocont.getSubsAlternativeEncoding());
            	  jComboBoxalignment.setEnabled(false);
              }
              else{
            	  jTextFieldotherencoding.setText(LangTools.getResourceBundleWordLanguage(rb,"Other encoding?","subtitlesinfo.otherencoding"));
            	  jComboBoxalignment.setSelectedItem(subinfocont.getSubsAlignment());
              }

              String fontname=subinfocont.getSubsFontName();
              jComboBoxFont.setSelectedItem(fontname);
              int fontsize=subinfocont.getSubsFontSize();
              jSpinnerFontSize.setValue(fontsize);
              SubtitlesFontStyle subfontstyle = EncodingPropsAuxiliar.getSubtitlesFontStyle(subinfocont.isSubsUseBold(), subinfocont.isSubsUseItalic());
              jComboBoxfontstyle.setSelectedItem(subfontstyle);
              fontpanelpreview.setFontname(fontname);
              fontpanelpreview.setFontSize(25);
              fontpanelpreview.setFontStyle(subfontstyle);
    		  fontpanelpreview.repaint();

		  }
		  else{
			  this.steelCheckBoxHardsubs.setSelected(false);
			  hardSubSelectionAtion();
			  this.steelCheckBoxsoftsubs.setSelected(false);
			  SoftSubSelectionAction();
		  }
	  }
	  
	  /**
  	 * Save selected information into container.
  	 */
  	public void saveSelectedInformationIntoContainer(){
		   SubtitleInfoContainer subinfocont= this.infocont.getSubtitleInfoContainer();
  		
		  if(this.steelCheckBoxsoftsubs.isSelected()){
			  
			 subinfocont.setUseHardSubs(false);
			 if(this.videoContainer.equals(VideoContainers.MP4))
				 subinfocont.setUseMp4SubtitleEncCodec(true);
			 else
				 subinfocont.setUseMp4SubtitleEncCodec(false);
			 subinfocont.setUseSoftSubs(true);
			 String currentlang=FFmpegManager.getInstance().getISO3CodeFromLanguage((String) jComboBoxsubsoftlanguage.getSelectedItem());

			 if(!currentlang.equals(langinuse)) {
				 FFmpegManager.getInstance().setDefaultSoftSubtitleLanguage(currentlang);
			 }
		  }
		  else if(this.steelCheckBoxHardsubs.isSelected()){
			  subinfocont.setUseSoftSubs(false);
			  subinfocont.setUseHardSubs(true);
			  subinfocont.setSubsAlignment((SubtiltesAlignment) jComboBoxalignment.getSelectedItem());
			  
			  subinfocont.setSubsBorderStyle((SubtitlesBorderStyle) jComboBoxBorderStyle.getSelectedItem());
			  subinfocont.setSubsColor((SubtitlesColor) jComboBoxcolor.getSelectedItem());
			  
			  subinfocont.setSubsFontName((String) jComboBoxFont.getSelectedItem());
			  subinfocont.setSubsFontSize((int) jSpinnerFontSize.getValue());
			  subinfocont.setSubsOutline((int) jSpinneroutline.getValue());
			  subinfocont.setSubsShadow((int) jSpinnershadow.getValue());
			  
			  SubtitlesFontStyle subfontstyle = (SubtitlesFontStyle) jComboBoxfontstyle.getSelectedItem();
			  if(subfontstyle.equals(SubtitlesFontStyle.BOLD)){
				  subinfocont.setSubsUseBold(true);
				  subinfocont.setSubsUseItalic(false);
			  }
			  else if(subfontstyle.equals(SubtitlesFontStyle.ITALIC)){
				  subinfocont.setSubsUseItalic(true);
				  subinfocont.setSubsUseBold(false);
			  }
			  else if(subfontstyle.equals(SubtitlesFontStyle.BoldItalic)){
				  subinfocont.setSubsUseBold(true);
				  subinfocont.setSubsUseItalic(true);
			  }
			  else{
				  subinfocont.setSubsUseBold(false);
				  subinfocont.setSubsUseItalic(false); 
			  }
             //this.infocont.setSubsAlternativeEncoding(subsAlternativeEncoding);
			  if(this.alternativefontenc==null){
				  subinfocont.setSubsEncoding(((SubEncodings) jComboBoxencoding.getSelectedItem()).toString());
				  subinfocont.setSubsAlternativeEncoding(null);
			  }
			  else if(this.alternativefontenc!=null){
				  subinfocont.setSubsEncoding(null);
				  subinfocont.setSubsAlternativeEncoding(alternativefontenc);
			  }
		  
		  }
		  else{
			  subinfocont.setUseHardSubs(false);
			  subinfocont.setUseSoftSubs(false);
		  }

	  }


	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if(jTextFieldotherencoding.getText().equals(LangTools.getResourceBundleWordLanguage(rb,"Other encoding?","subtitlesinfo.otherencoding")))
		   jTextFieldotherencoding.setText("");
		
	}


	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	private void LaunchSubPreviewPanel(){
		boolean vlcinstalled =  StaticVlcMethods.isVLCFOUND();
		saveSelectedInformationIntoContainer();
		SubtitleInfoContainer subinfo= this.infocont.getSubtitleInfoContainer();

		boolean usedemo = PropertiesWorker.getBooleanPropertyWithDefault(StaticGlobalFields.RENCODERCONFIGFILE, StaticGlobalFields.USEDEMOASDEFAULT, false);
		boolean usevlcj = PropertiesWorker.getBooleanPropertyWithDefault(StaticGlobalFields.RENCODERCONFIGFILE,StaticGlobalFields.USEVLCJPLAYERASPREVIEWER, true);

		Subtitlefile sub =null;
		SubtitleConverter conv =null;
		String subpath =null;
		String moviefilepath=null;
		
		if(vlcinstalled && usevlcj){	

			if(isGeneralUserProfile || movie==null) {
				sub= new Subtitlefile(FFmpegUtils.getDemoSubtitle());
				conv = new SubtitleConverter(sub, subinfo);
				conv.getFFmpegSubtitleEncodingCommands(OSystem.getTempFolder());
				moviefilepath=FFmpegUtils.getMovieDemo();
			}
			else{
				sub = this.movie.getFirstSelectedSubtitleFile();
				if(sub!=null){
					conv = new SubtitleConverter(sub, subinfo);
					conv.getFFmpegSubtitleEncodingCommands(null);
				}
				moviefilepath=this.movie.getFilePath();
			}
             
			if(moviefilepath!=null && sub!=null){
				subpath =conv.getTempSubPath();
				RunPlayer player = new RunPlayer(moviefilepath, subpath, true);
				player.setParentComponent(this);
				SwingUtilities.invokeLater(player);

			}
			else
				LaunchDefaultSubtitlePreviewer(subinfo);
		}
		else{
			LaunchDefaultSubtitlePreviewer(subinfo);
		}

	}
	
	

	
	private void LaunchDefaultSubtitlePreviewer(SubtitleInfoContainer subinfo){
		
		
		Subtitlefile sub= new Subtitlefile(FFmpegUtils.getDemoSubtitle());
		SubtitleConverter conv = new SubtitleConverter(sub, subinfo);
		
		ArrayList<String> subcmds = conv.getFFmpegSubtitleEncodingCommands(OSystem.getTempFolder());
		String tmp=OSystem.getTempFolder();
		String tmppicture= ProcessFiles.checkIfFileExistsandReturnNewname(tmp, "demo", "png", 0, false);
		SubtitleConverter.makeSubtitlePicturePreview(FFmpegUtils.getMovieDemo(), subcmds, tmppicture);
		SubtitlesImagePreview panel = new SubtitlesImagePreview(tmppicture);
		if(isGeneralUserProfile)
			panel.setModal(true);
		Thread t = new Thread(panel);
		t.start();
		
		
	}

}	