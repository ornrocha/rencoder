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

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.BoundedRangeModel;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;

import org.tinylog.Logger;

import eu.hansolo.custom.SteelCheckBox;
import pt.ornrocha.rencoder.ffmpegWrapper.configurations.FFmpegManager;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.AspectRatio;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.FPS;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.HWAccel;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCodecs;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoContainers;
import pt.ornrocha.rencoder.ffmpegWrapper.othercomponents.AspectComponent;
import pt.ornrocha.rencoder.ffmpegWrapper.othercomponents.VideoAspectSizes;
import pt.ornrocha.rencoder.gui.Maingui;
import pt.ornrocha.rencoder.gui.components.panels.auxiliar.BitrateComboBoxModel;
import pt.ornrocha.rencoder.gui.components.panels.auxiliar.CopyPasteJTextField;
import pt.ornrocha.rencoder.gui.components.panels.auxiliar.FileSizeComboBoxModel;
import pt.ornrocha.rencoder.gui.components.panels.auxiliar.VBRSliderListener;
import pt.ornrocha.rencoder.gui.components.panels.auxiliar.VideoPanelUtils;
import pt.ornrocha.rencoder.gui.components.panels.configurations.AV1ConfigurationsPanel;
import pt.ornrocha.rencoder.gui.components.panels.configurations.H264ConfigurationsPanel;
import pt.ornrocha.rencoder.gui.components.panels.configurations.H264NvencConfigurationsPanel;
import pt.ornrocha.rencoder.gui.components.panels.configurations.H264QsvConfigurationsPanel;
import pt.ornrocha.rencoder.gui.components.panels.configurations.H264VaapiConfigurationsPanel;
import pt.ornrocha.rencoder.gui.components.panels.configurations.H265ConfigurationsPanel;
import pt.ornrocha.rencoder.gui.components.panels.configurations.HEVCNvencConfigurationsPanel;
import pt.ornrocha.rencoder.gui.components.panels.configurations.HEVCQsvConfigurationsPanel;
import pt.ornrocha.rencoder.gui.components.panels.configurations.HEVCVaapiConfigurationsPanel;
import pt.ornrocha.rencoder.gui.components.panels.configurations.KvazaarConfigurationsPanel;
import pt.ornrocha.rencoder.gui.components.panels.configurations.MPEG2ConfigurationsPanel;
import pt.ornrocha.rencoder.gui.components.panels.configurations.OpenH264ConfigurationsPanel;
import pt.ornrocha.rencoder.gui.components.panels.configurations.SVTAV1ConfigurationsPanel;
import pt.ornrocha.rencoder.gui.components.panels.configurations.SVTHEVCConfigurationsPanel;
import pt.ornrocha.rencoder.gui.components.panels.configurations.VPxConfigurationsPanel;
import pt.ornrocha.rencoder.gui.components.panels.configurations.XvidConfigurationsPanel;
import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.helpers.props.managers.auxiliar.EncodingPropsAuxiliar;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoAV1EncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoH264NvencEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoH264QSVEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoH264VAAPIEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoH265EncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoHEVCNvencEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoHEVCQsvEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoHEVCVaapiEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoKvazaarEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoOpenH264EncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoSVTAV1EncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoSVTHEVCEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoXvidEncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;

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
public class VideoConfigScrollPanel extends JScrollPane implements ActionListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The j panelmain. */
	protected JPanel jPanelmain;

	/** The j combo boxbitrate. */
	protected JComboBox<String> jComboBoxbitrate;

	/** The j combo boxfilesize. */
	protected JComboBox<String> jComboBoxfilesize;
	protected JComboBox<VideoContainers> jComboBoxcontainer;
	private JLabel jLabelContainer;

	/** The j check boxoverwrite. */
	private JCheckBox jCheckBoxoverwrite;

	/** The j buttoncodecsettings. */
	protected JButton jButtoncodecsettings;

	/** The j labelquality. */
	protected JLabel jLabelquality;

	/** The steel check boxvideosize. */
	protected SteelCheckBox steelCheckBoxvideosize;

	/** The j sliderquality. */
	protected JSlider jSliderquality;

	/** The steel check boxquality. */
	protected SteelCheckBox steelCheckBoxquality;

	/** The steel check boxbitrate. */
	protected SteelCheckBox steelCheckBoxbitrate;

	/** The j labelcodec. */
	protected JLabel jLabelcodec;

	/** The j labelfps. */
	protected JLabel jLabelfps;

	/** The j combo boxaspratio. */
	protected JComboBox<AspectRatio> jComboBoxaspratio;

	/** The j radio button2passenc. */
	protected JRadioButton jRadioButton2passenc;

	/** The j radio button1pass. */
	protected JRadioButton jRadioButton1pass;

	/** The j labelaspectratio. */
	protected JLabel jLabelaspectratio;

	/** The j combo boxfps. */
	protected JComboBox<FPS> jComboBoxfps;

	/** The j combo boxaspect. */
	protected JComboBox<AspectComponent> jComboBoxaspect;

	/** The j labelaspect. */
	protected JLabel jLabelaspect;

	/** The j combo boxcodec. */
	protected JComboBox<VideoCodecs> jComboBoxcodec;

	/** The j panelmain layout. */
	protected GridBagLayout jPanelmainLayout = null;

	/** The aspectsizecombomodel. */
	protected DefaultComboBoxModel<AspectComponent> aspectsizecombomodel = null;

	/** The videocbrvalues. */
	@SuppressWarnings("rawtypes")
	protected BitrateComboBoxModel videocbrvalues = null;

	/** The filesizevalues. */
	protected FileSizeComboBoxModel filesizevalues = null;

	/** The aspects. */
	protected VideoAspectSizes aspects = new VideoAspectSizes();

	/** The b range model. */
	protected BoundedRangeModel bRangeModel;

	/** The codecsetpanel. */
	protected H264ConfigurationsPanel codech264setpanel;

	// protected H264NvencConfigurationsPanel codech264nvencsetpanel;

	protected H265ConfigurationsPanel codech265setpanel;

	/** The codecvp8setpanel. */
	protected VPxConfigurationsPanel codecvpxsetpanel;

	/** The codecmpg2setanel. */
	protected MPEG2ConfigurationsPanel codecmpg2setanel;

	/** The copyactivacted. */
	protected boolean copyactivacted = false;

	/** The videocontainer. */
	protected VideoContainers videocontainer = VideoContainers.AVI;

	/** The infocont. */
	protected IGeneralVideoEncInfoContainer infocont = null;

	/** The j text fieldvideoffmpegcmd. */
	protected CopyPasteJTextField jTextFieldvideoffmpegcmd;

	/** The selectedsize. */
	protected static String SELECTEDSIZE = "selectedsize";

	/** The selectedquality. */
	protected static String SELECTEDQUALITY = "selectedquality";

	/** The selectedbitrate. */
	protected static String SELECTEDBITRATE = "selectedbitrate";

	/** The PAS s1. */
	protected static String PASS1 = "1pass";

	/** The PAS s2. */
	protected static String PASS2 = "2pass";

	/** The choosecodec. */
	public static String CHOOSECODEC = "choosecodec";

	public static String CHOOSEOUTFORMAT = "chooseoutputformat";

	/** The codecsettings. */
	protected static String CODECSETTINGS = "codecsettings";

	/** The chooseaspectactions. */
	protected static String CHOOSEASPECTACTIONS = "chooseaspectactions";

	/** The choosevideocbr. */
	protected static String CHOOSEVIDEOCBR = "choosevideocbr";

	/** The choosefilesize. */
	protected static String CHOOSEFILESIZE = "choosefilesize";

	/** The extravideoffmpegcmd. */
	protected static String EXTRAVIDEOFFMPEGCMD = "extravideoffmpegcmd";

	/** The useonlyvideoextracmd. */
	protected static String USEONLYVIDEOEXTRACMD = "useonlyvideoextracmd";

	/** The overwrite. */
	protected static String OVERWRITE = "overwrite";
	// protected JDialog parent=null;
	/** The rb. */
	protected ResourceBundle rb;

	/** The allow slider. */
	protected boolean allowSlider = true;

	/** The qualval. */
	protected IndexedHashMap<String, Integer> qualval = null;

	private JFrame mainframe;
	private JPanel panel;
	private JComboBox<HWAccel> comboBoxHWAccel;

	/**
	 * Instantiates a new video config scroll panel.
	 */
	public VideoConfigScrollPanel(JFrame mainframe) {
		this.mainframe = mainframe;
		rb = ResourceBundle.getBundle("lang", LangTools.getDefinedLanguage(), LangTools.loadLanguagesPath());
		initGUI();
	    initializeComponents();
		setActionsListeners();

	}

	/**
	 * Initialize components.
	 */
	protected void initializeComponents() {

		steelCheckBoxbitrate.setSelected(false);
		steelCheckBoxvideosize.setSelected(true);
		steelCheckBoxquality.setSelected(false);

		jComboBoxfilesize.setEnabled(false);
		jComboBoxbitrate.setEnabled(false);
		jSliderquality.setEnabled(true);

		jRadioButton1pass.setSelected(true);
		jRadioButton2passenc.setSelected(false);

		jButtoncodecsettings.setEnabled(false);

		populateInitialComponents();
		setJSliderSettings(5, 0, 1, 15, 1, 5);

	}

	/**
	 * Populate initial components.
	 */
	@SuppressWarnings("unchecked")
	protected void populateInitialComponents() {

		aspectsizecombomodel = new DefaultComboBoxModel<AspectComponent>(aspects.values());
		jComboBoxaspect.setModel(aspectsizecombomodel);
		jComboBoxaspect.setEditable(true);

		jComboBoxcodec
				.setModel(new DefaultComboBoxModel<VideoCodecs>(FFmpegManager.getInstance().getAllowedVideoCodecs()));

		jComboBoxfps.setModel(new DefaultComboBoxModel<FPS>(FPS.values()));
		jComboBoxaspratio.setModel(new DefaultComboBoxModel<>(AspectRatio.values()));

		filesizevalues = new FileSizeComboBoxModel(VideoPanelUtils.convertFileSizeEnumValuesToArrayString());
		jComboBoxfilesize.setModel(filesizevalues);

		videocbrvalues = new BitrateComboBoxModel<String>(VideoPanelUtils.convertVideoCBRValuesToArrayString());
		jComboBoxbitrate.setModel(videocbrvalues);

		steelCheckBoxquality.setColored(true);
		steelCheckBoxvideosize.setColored(true);
		steelCheckBoxbitrate.setColored(true);

	}

	/**
	 * Sets the j slider settings.
	 *
	 * @param initvalue        the initvalue
	 * @param extent           the extent
	 * @param min              the min
	 * @param max              the max
	 * @param minortickSpacing the minortick spacing
	 * @param majortickSpacing the majortick spacing
	 */
	public void setJSliderSettings(int initvalue, int extent, int min, int max, int minortickSpacing,
			int majortickSpacing) {

		bRangeModel = new DefaultBoundedRangeModel(initvalue, extent, min, max);
		jSliderquality.setModel(bRangeModel);

		jSliderquality.setMajorTickSpacing(majortickSpacing);
		jSliderquality.setMinorTickSpacing(minortickSpacing);
		jSliderquality.setPaintTicks(true);
		jSliderquality.setPaintLabels(true);

		jLabelquality.setText(String.valueOf(initvalue));

		Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
		table.put(min, new JLabel(LangTools.getResourceBundleWordLanguage(rb, "Best", "general.best")));
		table.put(initvalue, new JLabel(LangTools.getResourceBundleWordLanguage(rb, "Average", "general.average")));
		table.put(max, new JLabel(LangTools.getResourceBundleWordLanguage(rb, "Worst", "general.worst")));
		jSliderquality.setLabelTable(table);

		if (jSliderquality.getChangeListeners().length > 0) {
			ChangeListener[] list = jSliderquality.getChangeListeners();
			for (int i = 0; i < list.length; i++) {
				jSliderquality.removeChangeListener(list[i]);
			}
		}

		jSliderquality.addChangeListener(new VBRSliderListener(null, jLabelquality, false));

	}

	/**
	 * Change quality slider.
	 *
	 * @param value the value
	 */
	public void changeQualitySlider(int value) {

		jSliderquality.getModel().setValue(value);
		jLabelquality.setText(String.valueOf(value));

	}

	/**
	 * Sets the main grid bag layout.
	 */
	protected void setMainGridBagLayout() {

		jPanelmain = new JPanel();
		GridBagLayout jPanelmainLayout = new GridBagLayout();
		jPanelmainLayout.rowWeights = new double[] { 0.1, 0.0, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1 };
		jPanelmainLayout.rowHeights = new int[] { 7, 7, 7, 7, 7, 7, 7, 7, 7, 7 };
		jPanelmainLayout.columnWeights = new double[] { 0.1, 0.1, 1.0, 0.1, 0.1, 1.0, 0.1, 1.0, 0.1, 0.1 };
		jPanelmainLayout.columnWidths = new int[] { 7, 7, 7, 7, 7, 7, 7, 7, 7, 7 };
		jPanelmain.setLayout(jPanelmainLayout);
		this.setViewportView(jPanelmain);
	}

	protected void setOutputFormat() {
		VideoCodecs codec = (VideoCodecs) jComboBoxcodec.getSelectedItem();
		DefaultComboBoxModel<VideoContainers> supportedcontainers = getComboboxVideoContModel(codec);
		jComboBoxcontainer.removeAllItems();
		jComboBoxcontainer.setModel(supportedcontainers);

	}

	private DefaultComboBoxModel<VideoContainers> getComboboxVideoContModel(VideoCodecs codec) {
		return new DefaultComboBoxModel<VideoContainers>(codec.supportsOutputFormats());
	}

	/**
	 * Inits the gui.
	 */
	protected void initGUI() {
		try {
			{
				this.setPreferredSize(new java.awt.Dimension(669, 432));
			}
			{
				{

					setMainGridBagLayout();

					{
						jLabelcodec = new JLabel();
						jPanelmain.add(jLabelcodec, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0,
								GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
						jLabelcodec
								.setText(LangTools.getResourceBundleWordLanguage(rb, "Codec", "videoaudiogui.codec"));
						jLabelcodec.setHorizontalAlignment(SwingConstants.LEFT);
					}
					{

						jComboBoxcodec = new JComboBox<VideoCodecs>();
						jPanelmain.add(jComboBoxcodec,
								new GridBagConstraints(2, 0, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER,
										GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));
						jComboBoxcodec.setActionCommand(CHOOSECODEC);
					}

					{
						jLabelaspect = new JLabel();
						jPanelmain.add(jLabelaspect, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0,
								GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
						jLabelaspect.setText(
								LangTools.getResourceBundleWordLanguage(rb, "Video aspect", "general.videosizeaspect"));
					}
					{

						jComboBoxaspect = new JComboBox<AspectComponent>();
						jPanelmain.add(jComboBoxaspect,
								new GridBagConstraints(2, 2, 6, 1, 0.0, 0.0, GridBagConstraints.CENTER,
										GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));
						jComboBoxaspect.setActionCommand(CHOOSEASPECTACTIONS);

					}
					{
						jLabelfps = new JLabel();
						jPanelmain.add(jLabelfps, new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0,
								GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
						jLabelfps
								.setText(LangTools.getResourceBundleWordLanguage(rb, "Framerate", "general.framerate"));
					}
					{

						jComboBoxfps = new JComboBox<FPS>();
						jPanelmain.add(jComboBoxfps,
								new GridBagConstraints(2, 3, 6, 1, 0.0, 0.0, GridBagConstraints.CENTER,
										GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));

					}
					{
						jLabelaspectratio = new JLabel();
						jPanelmain.add(jLabelaspectratio, new GridBagConstraints(0, 4, 2, 1, 0.0, 0.0,
								GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
						jLabelaspectratio.setText(
								LangTools.getResourceBundleWordLanguage(rb, "Aspect ratio", "general.aspectratio"));
					}
					{

						jComboBoxaspratio = new JComboBox<AspectRatio>();
						jPanelmain.add(jComboBoxaspratio,
								new GridBagConstraints(2, 4, 6, 1, 0.0, 0.0, GridBagConstraints.CENTER,
										GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));

					}
					{
						jRadioButton1pass = new JRadioButton();
						jPanelmain.add(jRadioButton1pass,
								new GridBagConstraints(0, 8, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
										GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));
						jRadioButton1pass.setText(LangTools.getWordLanguage("1 pass", "videogui.onepass"));
						jRadioButton1pass.setActionCommand(PASS1);
					}
					{

						steelCheckBoxbitrate = new SteelCheckBox();
						steelCheckBoxbitrate.setText(LangTools.getResourceBundleWordLanguage(rb, "Constant bitrate",
								"general.constantbitrate"));
						steelCheckBoxbitrate.setActionCommand(SELECTEDBITRATE);
						jPanelmain.add(steelCheckBoxbitrate,
								new GridBagConstraints(0, 5, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
										GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));

						jComboBoxbitrate = new JComboBox<String>();
						jPanelmain.add(jComboBoxbitrate,
								new GridBagConstraints(2, 5, 6, 1, 0.0, 0.0, GridBagConstraints.CENTER,
										GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));
						jComboBoxbitrate.setActionCommand(CHOOSEVIDEOCBR);

						steelCheckBoxquality = new SteelCheckBox();
						steelCheckBoxquality.setText(LangTools.getResourceBundleWordLanguage(rb, "Variable bitrate",
								"general.variablebitrate"));
						steelCheckBoxquality.setActionCommand(SELECTEDQUALITY);
						jPanelmain.add(steelCheckBoxquality,
								new GridBagConstraints(0, 7, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
										GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));

						jSliderquality = new JSlider();
						jPanelmain.add(jSliderquality,
								new GridBagConstraints(2, 7, 6, 1, 0.0, 0.0, GridBagConstraints.CENTER,
										GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));

					}
					{
						steelCheckBoxvideosize = new SteelCheckBox();
						jPanelmain.add(steelCheckBoxvideosize,
								new GridBagConstraints(0, 6, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
										GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));
						steelCheckBoxvideosize.setText(LangTools.getWordLanguage("File size(MB)", "videogui.filesize"));
						steelCheckBoxvideosize.setActionCommand(SELECTEDSIZE);
					}
					{

						jComboBoxfilesize = new JComboBox<String>();
						jPanelmain.add(jComboBoxfilesize,
								new GridBagConstraints(2, 6, 6, 1, 0.0, 0.0, GridBagConstraints.CENTER,
										GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));
						jComboBoxfilesize.setActionCommand(CHOOSEFILESIZE);
					}
					{
						jLabelquality = new JLabel();
						jPanelmain.add(jLabelquality,
								new GridBagConstraints(8, 7, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
										GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));

						jLabelquality.setHorizontalAlignment(SwingConstants.CENTER);
						jLabelquality.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
					}
					{
						jButtoncodecsettings = new JButton();
						jPanelmain.add(jButtoncodecsettings, new GridBagConstraints(5, 0, 3, 1, 0.0, 0.0,
								GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
						jButtoncodecsettings
								.setText(LangTools.getResourceBundleWordLanguage(rb, "Settings", "general.settings"));
						jButtoncodecsettings.setActionCommand(CODECSETTINGS);
					}

					jRadioButton2passenc = new JRadioButton();
					jPanelmain.add(jRadioButton2passenc, new GridBagConstraints(2, 8, 2, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));
					jRadioButton2passenc.setText(LangTools.getWordLanguage("2 pass", "videogui.twopass"));
					jRadioButton2passenc.setActionCommand(PASS2);
					{
						jComboBoxcontainer = new JComboBox<VideoContainers>();
						jComboBoxcontainer.setActionCommand(CHOOSEOUTFORMAT);
						jPanelmain.add(jComboBoxcontainer,
								new GridBagConstraints(2, 1, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER,
										GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));
						jLabelContainer = new JLabel();
						jLabelContainer.setText(LangTools.getWordLanguage("Output Format", "general.outputformat"));
						jPanelmain.add(jLabelContainer,
								new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
										GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));
					}
				}
			}
			{
				jCheckBoxoverwrite = new JCheckBox();
				jPanelmain.add(jCheckBoxoverwrite, new GridBagConstraints(4, 8, 6, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));
				jCheckBoxoverwrite.setText(LangTools.getResourceBundleWordLanguage(rb, "Overwrite existing file",
						"videoaudiogui.overwrite"));
				jCheckBoxoverwrite.setActionCommand(OVERWRITE);
				jCheckBoxoverwrite.addActionListener(this);
			}
		} catch (Exception e) {
		    Logger.error(e);
		}
	}

	/**
	 * Sets the external action listener.
	 *
	 * @param l the new external action listener
	 */
	public void setExternalActionListener(ActionListener l) {
		jComboBoxcodec.addActionListener(l);
		jComboBoxcontainer.addActionListener(l);
	}

	private void addHWACCELPAnel(DefaultComboBoxModel<HWAccel> supportedcodecs) {
		panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Decoding HWAccel",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 5;
		gbc_panel.gridy = 1;
		jPanelmain.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 1 };
		gbl_panel.rowHeights = new int[] { 1 };
		gbl_panel.columnWeights = new double[] { 1.0 };
		gbl_panel.rowWeights = new double[] { 0.0 };
		panel.setLayout(gbl_panel);
		{
			comboBoxHWAccel = new JComboBox<HWAccel>();
			comboBoxHWAccel.removeAllItems();
			if (supportedcodecs != null)
				comboBoxHWAccel.setModel(supportedcodecs);
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 0;
			gbc_comboBox.gridy = 0;
			panel.add(comboBoxHWAccel, gbc_comboBox);
		}
	}

	/**
	 * Sets the actions listeners.
	 */
	protected void setActionsListeners() {
		steelCheckBoxvideosize.addActionListener(this);
		steelCheckBoxquality.addActionListener(this);
		steelCheckBoxbitrate.addActionListener(this);
		jRadioButton1pass.addActionListener(this);
		jRadioButton2passenc.addActionListener(this);

		jButtoncodecsettings.addActionListener(this);
		jComboBoxaspect.addActionListener(this);
		jComboBoxbitrate.addActionListener(this);
		jComboBoxfilesize.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (command.equals(SELECTEDBITRATE))
			bitrateselected();
		else if (command.equals(SELECTEDSIZE))
			sizeselected();
		else if (command.equals(SELECTEDQUALITY)) {
			qualityselected();
			setVideoEncodingBitrateType();
		} else if (command.equals(PASS1))
			pass1selected();
		else if (command.equals(PASS2))
			pass2selected();
		else if (command.equals(CODECSETTINGS))
			codecSettings();
		else if (command.equals(CHOOSEASPECTACTIONS))
			setChangesAspectSize();
		else if (command.equals(CHOOSEVIDEOCBR))
			checkChangesjComboBoxbitrate();
		else if (command.equals(CHOOSEFILESIZE))
			checkChangesjComboBoxfilesize();
		else if (command.equals(OVERWRITE))
			setOverWriteAction();

	}

	/**
	 * Bitrateselected.
	 */
	protected void bitrateselected() {
		infocont.setUseVideoEncodingCBR(true);
		steelCheckBoxbitrate.setSelected(true);
		steelCheckBoxvideosize.setSelected(false);
		steelCheckBoxquality.setSelected(false);
		jSliderquality.setEnabled(false);
		jLabelquality.setEnabled(false);
		jComboBoxfilesize.setEnabled(false);

		if (steelCheckBoxbitrate.isEnabled()) {
			jComboBoxbitrate.setEnabled(true);
			jComboBoxbitrate.setEditable(true);
		}

	}

	/**
	 * Sizeselected.
	 */
	protected void sizeselected() {
		infocont.setUseVideoEncodingFileSize(true);
		steelCheckBoxvideosize.setSelected(true);
		steelCheckBoxbitrate.setSelected(false);
		steelCheckBoxquality.setSelected(false);
		jSliderquality.setEnabled(false);
		jLabelquality.setEnabled(false);
		jComboBoxbitrate.setEnabled(false);

		if (steelCheckBoxvideosize.isEnabled()) {
			jComboBoxfilesize.setEnabled(true);
			jComboBoxfilesize.setEditable(true);
		}

	}

	/**
	 * Qualityselected.
	 */
	protected void qualityselected() {
		infocont.setUseVideoEncodingVBR(true);
		steelCheckBoxquality.setSelected(true);
		steelCheckBoxvideosize.setSelected(false);
		steelCheckBoxbitrate.setSelected(false);
		jComboBoxfilesize.setEnabled(false);
		jComboBoxbitrate.setEnabled(false);

		if (steelCheckBoxquality.isEnabled()) {
			jSliderquality.setEnabled(true);
			jLabelquality.setEnabled(true);
		}

	}

	/**
	 * Pass1selected.
	 */
	protected void pass1selected() {
		jRadioButton1pass.setSelected(true);
		jRadioButton2passenc.setSelected(false);

		steelCheckBoxvideosize.setEnabled(true);
		steelCheckBoxbitrate.setEnabled(true);
		steelCheckBoxquality.setEnabled(true);
		allowSlider = true;
		infocont.setTwopassEncoding(false);
	}

	/**
	 * Pass2selected.
	 */
	protected void pass2selected() {

		jRadioButton2passenc.setSelected(true);
		jRadioButton1pass.setSelected(false);

		if (steelCheckBoxquality.isSelected())
			bitrateselected();

		steelCheckBoxvideosize.setEnabled(true);
		steelCheckBoxbitrate.setEnabled(true);
		steelCheckBoxquality.setEnabled(false);

		allowSlider = false;
		infocont.setTwopassEncoding(true);
	}

	/**
	 * Codec copy selected.
	 */
	protected void codecCopySelected() {

		steelCheckBoxvideosize.setEnabled(false);
		steelCheckBoxbitrate.setEnabled(false);
		steelCheckBoxquality.setEnabled(false);
		jSliderquality.setEnabled(false);
		jLabelquality.setEnabled(false);
		jComboBoxfilesize.setEditable(false);
		jComboBoxfilesize.setEnabled(false);
		jComboBoxbitrate.setEnabled(false);
		jComboBoxbitrate.setEditable(false);
		jButtoncodecsettings.setEnabled(false);
		jComboBoxaspect.setEnabled(false);
		jRadioButton1pass.setEnabled(false);
		jRadioButton2passenc.setEnabled(false);
		jComboBoxaspratio.setEnabled(false);
		jComboBoxfps.setEnabled(false);
		this.copyactivacted = true;

	}

	/**
	 * Deactivate copy selected.
	 */
	protected void deactivateCopySelected() {

		if (copyactivacted) {

			steelCheckBoxvideosize.setEnabled(true);
			steelCheckBoxbitrate.setEnabled(true);
			steelCheckBoxquality.setEnabled(true);
			jSliderquality.setEnabled(true);
			jLabelquality.setEnabled(true);
			jComboBoxfilesize.setEnabled(true);
			jComboBoxfilesize.setEditable(true);
			jComboBoxbitrate.setEnabled(true);
			jComboBoxbitrate.setEditable(true);
			jButtoncodecsettings.setEnabled(true);
			jComboBoxaspect.setEnabled(true);
			jRadioButton1pass.setEnabled(true);
			jRadioButton2passenc.setEnabled(true);
			jComboBoxaspratio.setEnabled(true);
			jComboBoxfps.setEnabled(true);
			this.copyactivacted = false;
		}

	}

	/**
	 * Sets the over write action.
	 */
	protected void setOverWriteAction() {
		if (jCheckBoxoverwrite.isSelected())
			infocont.setOverwritefile(true);
		else
			infocont.setOverwritefile(false);
	}

	/**
	 * Check changesj combo boxbitrate.
	 */
	protected void checkChangesjComboBoxbitrate() {

		String cbr = (String) jComboBoxbitrate.getSelectedItem();
		boolean elementofvideocbr = VideoPanelUtils.elementOfVideoCBRValues(cbr);

		if (!elementofvideocbr) {

			String validatedCbr = EncodingPropsAuxiliar.getBitrateValue(cbr);
			if (validatedCbr == null) {
				Maingui.getInstance().showWarningMessage(
						LangTools.getResourceBundleWordLanguage(rb, "Bitrate must have a format similar to: 1000k",
								"videogui.bitratevalue"),
						LangTools.getResourceBundleWordLanguage(rb, "Warning", "warngui.tag"));

				this.jComboBoxbitrate.setSelectedItem("1000k");
			} else
				setConstantBitrateValueInCombobox(validatedCbr);
		}

	}

	/**
	 * Check changesj combo boxfilesize.
	 */
	protected void checkChangesjComboBoxfilesize() {

		String filesize = (String) jComboBoxfilesize.getSelectedItem();
		int existvalue = VideoPanelUtils.indexOfFileSizeValues(filesize, this.filesizevalues);

		if (existvalue == -1) {

			int validatedvalue = EncodingPropsAuxiliar.getVideoFileSize(filesize);
			if (validatedvalue == -1) {
				Maingui.getInstance().showWarningMessage(
						LangTools.getResourceBundleWordLanguage(rb, "File size value must be an integer",
								"videogui.filesizevalue"),
						LangTools.getResourceBundleWordLanguage(rb, "Warning", "warngui.tag"));

				this.jComboBoxfilesize.setSelectedItem("350");

			} else
				setFileSizeValueInCombobox(String.valueOf(validatedvalue));
		}

	}

	/**
	 * Sets the changes aspect size.
	 */
	protected void setChangesAspectSize() {

		Object aspectsizeobj = jComboBoxaspect.getSelectedItem();
		if (aspectsizeobj instanceof String) {
			jComboBoxaspratio.setEnabled(true);
			boolean validinput = EncodingPropsAuxiliar.checkifisvalidAspectsize((String) aspectsizeobj);
			if (!validinput) {
				Maingui.getInstance()
						.showWarningMessage(LangTools.getResourceBundleWordLanguage(rb,
								"Aspect size must have a format similar to: 720X576", "videogui.aspectsizevalue"),
								LangTools.getResourceBundleWordLanguage(rb, "Warning", "warngui.tag"));

				this.jComboBoxaspect.setSelectedItem("same as source");
			} else {
				AspectComponent asp = new AspectComponent((String) aspectsizeobj);
				aspectsizecombomodel.addElement(asp);
				jComboBoxaspect.setSelectedItem(asp);
			}

		} else if (aspectsizeobj instanceof AspectComponent) {
			AspectRatio ratio = ((AspectComponent) aspectsizeobj).getAspectRatio();
			if (ratio != null) {
				jComboBoxaspratio.setSelectedItem(ratio);
				jComboBoxaspratio.setEnabled(false);
			} else {
				jComboBoxaspratio.setEnabled(true);
				jComboBoxaspratio.setSelectedItem(AspectRatio.SAMEASSOURCE);
			}
		}
	}

	/**
	 * Choosecodecaction.
	 */
	public void choosecodecaction() {

		VideoCodecs codec = (VideoCodecs) jComboBoxcodec.getSelectedItem();
		steelCheckBoxquality.setEnabled(codec.supportsVariableBitrate());
		if (panel != null) {
			jPanelmain.remove(panel);
			panel = null;
			comboBoxHWAccel = null;
			jPanelmain.updateUI();
		}

		if (codec.equals(VideoCodecs.H264) || codec.equals(VideoCodecs.H264NVENC) || codec.equals(VideoCodecs.HEVCNVENC)) {
			deactivateCopySelected();
			jButtoncodecsettings.setEnabled(true);
			if (allowSlider && steelCheckBoxquality.isSelected())
				setJSliderSettings(23, 0, 0, 51, 1, 10);

			if (codec.equals(VideoCodecs.H264NVENC) || codec.equals(VideoCodecs.HEVCNVENC)) {
				if (FFmpegManager.getInstance().isCuvidSupported()) {
					addHWACCELPAnel(new DefaultComboBoxModel<>(HWAccel.getNvidiaHWAccel()));
					if (this.infocont != null && infocont.getHardwareAccelerationDecoder() != null)
						comboBoxHWAccel.setSelectedItem(infocont.getHardwareAccelerationDecoder());
					else
						comboBoxHWAccel.setSelectedItem(HWAccel.NVDEC);
					updateUI();
				}
			}
		} else if (codec.equals(VideoCodecs.OPENH264)) {
			deactivateCopySelected();
			jButtoncodecsettings.setEnabled(true);
		} else if (codec.equals(VideoCodecs.H264VAAPI) || codec.equals(VideoCodecs.HEVCVAAPI)) {
			deactivateCopySelected();
			jButtoncodecsettings.setEnabled(true);
			if (allowSlider && steelCheckBoxquality.isSelected())
				setJSliderSettings(20, 0, 0, 52, 1, 10);

		}else if (codec.equals(VideoCodecs.H264QSV) || codec.equals(VideoCodecs.HEVCQSV)) {
			deactivateCopySelected();
			jButtoncodecsettings.setEnabled(true);
			if (allowSlider && steelCheckBoxquality.isSelected())
				setJSliderSettings(20, 0, 0, 52, 1, 10);
		}
		else if (codec.equals(VideoCodecs.H265) || codec.equals(VideoCodecs.KVAZAAR)) {
			deactivateCopySelected();
			jButtoncodecsettings.setEnabled(true);
			if (allowSlider && steelCheckBoxquality.isSelected())
				setJSliderSettings(28, 0, 0, 51, 1, 10);
		} else if (codec.equals(VideoCodecs.AOMAV1)) {
			deactivateCopySelected();
			jButtoncodecsettings.setEnabled(true);
			if (allowSlider && steelCheckBoxquality.isSelected())
				setJSliderSettings(30, 0, 0, 63, 1, 10);

		} else if (codec.equals(VideoCodecs.SVTAV1)) {
          deactivateCopySelected();
          jButtoncodecsettings.setEnabled(true);
          if (allowSlider && steelCheckBoxquality.isSelected())
              setJSliderSettings(50, 0, 0, 63, 1, 10);

      }else if (codec.equals(VideoCodecs.SVTHEVC)) {
          deactivateCopySelected();
          jButtoncodecsettings.setEnabled(true);
          if (allowSlider && steelCheckBoxquality.isSelected())
              setJSliderSettings(32, 0, 0, 51, 1, 10);

      }else if (codec.equals(VideoCodecs.XVID)) {
			deactivateCopySelected();
			if (allowSlider && steelCheckBoxquality.isSelected())
				setJSliderSettings(4, 0, 1, 15, 1, 5);
			jButtoncodecsettings.setEnabled(true);
		} else if (codec.equals(VideoCodecs.MPEG4)) {
			deactivateCopySelected();
			if (allowSlider && steelCheckBoxquality.isSelected())
				setJSliderSettings(5, 0, 1, 15, 1, 5);
			jButtoncodecsettings.setEnabled(false);
		} else if (codec.equals(VideoCodecs.VP8) || codec.equals(VideoCodecs.VP9)) {
			deactivateCopySelected();
			if (allowSlider && steelCheckBoxquality.isSelected())
				setJSliderSettings(20, 0, 4, 63, 1, 10);
			jButtoncodecsettings.setEnabled(true);
		} else if (codec.equals(VideoCodecs.MPEG2)) {
			deactivateCopySelected();
			if (allowSlider && steelCheckBoxquality.isSelected())
				setJSliderSettings(6, 0, 1, 31, 1, 10);
			jButtoncodecsettings.setEnabled(true);
		} else if (codec.equals(VideoCodecs.COPY)) {
			codecCopySelected();
		}

		else {
			deactivateCopySelected();
			jButtoncodecsettings.setEnabled(false);
		}

		setOutputFormat();
	}

	/**
	 * Codec settings.
	 */
	protected void codecSettings() {

		VideoCodecs codec = (VideoCodecs) jComboBoxcodec.getSelectedItem();
		if (codec.equals(VideoCodecs.H264))
			launchX264SettingsPanel();
		else if (codec.equals(VideoCodecs.H264NVENC))
			launchH264NvencSettingsPanel();
		else if (codec.equals(VideoCodecs.H265))
			launchX265SettingsPanel();
		else if (codec.equals(VideoCodecs.KVAZAAR))
			launchKvazaarSettingsPanel();
		else if (codec.equals(VideoCodecs.OPENH264))
			launchOpenH264SettingsPanel();
		else if (codec.equals(VideoCodecs.H264VAAPI))
			launchH264VaapiSettingsPanel();
		else if (codec.equals(VideoCodecs.H264QSV))
			launchH264QsvSettingsPanel();
		else if (codec.equals(VideoCodecs.HEVCNVENC))
			launchHEVCNvencSettingsPanel();
		else if (codec.equals(VideoCodecs.HEVCVAAPI))
			launchHevcVaapiSettingsPanel();
		else if (codec.equals(VideoCodecs.HEVCQSV))
			launchHEVCQsvSettingsPanel();
		else if (codec.equals(VideoCodecs.AOMAV1))
			launchAV1SettingsPanel();
		else if (codec.equals(VideoCodecs.SVTAV1))
		  launchSVTAV1SettingsPanel();
		else if (codec.equals(VideoCodecs.SVTHEVC))
			launchSVTHEVCSettingsPanel();
		else if (codec.equals(VideoCodecs.VP8) || codec.equals(VideoCodecs.VP9))
			launchVPxSettingsPanel();
		else if (codec.equals(VideoCodecs.MPEG2))
			launchMPEG2SettingsPanel();
		else if (codec.equals(VideoCodecs.XVID))
			launchXvidSettingsPanel();

	}

	/**
	 * Launch x264 settings panel.
	 */
	protected void launchX264SettingsPanel() {

		try {
			codech264setpanel = new H264ConfigurationsPanel(this.infocont);
			codech264setpanel.setLocationRelativeTo(this);
			codech264setpanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			codech264setpanel.setVisible(true);
		} catch (Exception e) {
		    Logger.error(e);
		}
	}

	protected void launchH264NvencSettingsPanel() {

		try {
			H264NvencConfigurationsPanel codech264nvencsetpanel = new H264NvencConfigurationsPanel(
					(VideoH264NvencEncodingInfoContainer) this.infocont);
			codech264nvencsetpanel.setLocationRelativeTo(this);
			codech264nvencsetpanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			codech264nvencsetpanel.setModal(true);
			codech264nvencsetpanel.setVisible(true);
		} catch (Exception e) {
		    Logger.error(e);
		}
	}

	protected void launchX265SettingsPanel() {

		try {
			codech265setpanel = new H265ConfigurationsPanel((VideoH265EncodingInfoContainer) this.infocont);
			codech265setpanel.setLocationRelativeTo(this);
			codech265setpanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			codech265setpanel.setVisible(true);
		} catch (Exception e) {
		    Logger.error(e);
		}

	}

	protected void launchKvazaarSettingsPanel() {

		try {
			KvazaarConfigurationsPanel confpanel = new KvazaarConfigurationsPanel(
					(VideoKvazaarEncodingInfoContainer) this.infocont);
			confpanel.setLocationRelativeTo(this);
			confpanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			confpanel.setVisible(true);
		} catch (Exception e) {
		    Logger.error(e);
		}
	}

	protected void launchOpenH264SettingsPanel() {

		try {
			OpenH264ConfigurationsPanel confpanel = new OpenH264ConfigurationsPanel(
					(VideoOpenH264EncodingInfoContainer) this.infocont);
			confpanel.setLocationRelativeTo(this);
			confpanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			confpanel.setVisible(true);
		} catch (Exception e) {
		    Logger.error(e);
		}
	}

	protected void launchH264VaapiSettingsPanel() {

		try {
			H264VaapiConfigurationsPanel confpanel = new H264VaapiConfigurationsPanel(
					(VideoH264VAAPIEncodingInfoContainer) this.infocont);
			confpanel.setLocationRelativeTo(this);
			confpanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			confpanel.setVisible(true);
		} catch (Exception e) {
		    Logger.error(e);
		}
	}

	protected void launchH264QsvSettingsPanel() {

		try {
			H264QsvConfigurationsPanel confpanel = new H264QsvConfigurationsPanel(
					(VideoH264QSVEncodingInfoContainer) this.infocont);
			confpanel.setLocationRelativeTo(this);
			confpanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			confpanel.setModal(true);
			confpanel.setVisible(true);
		} catch (Exception e) {
		    Logger.error(e);
		}
	}
	
	protected void launchHEVCNvencSettingsPanel() {

		try {
			HEVCNvencConfigurationsPanel confpanel = new HEVCNvencConfigurationsPanel(
					(VideoHEVCNvencEncodingInfoContainer) this.infocont);
			confpanel.setLocationRelativeTo(this);
			confpanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			confpanel.setModal(true);
			confpanel.setVisible(true);
		} catch (Exception e) {
		    Logger.error(e);
		}
	}
	
	protected void launchHevcVaapiSettingsPanel() {

		try {
		    HEVCVaapiConfigurationsPanel confpanel = new HEVCVaapiConfigurationsPanel(
					(VideoHEVCVaapiEncodingInfoContainer) this.infocont);
			confpanel.setLocationRelativeTo(this);
			confpanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			confpanel.setVisible(true);
		} catch (Exception e) {
		    Logger.error(e);
		}
	}
	
	protected void launchHEVCQsvSettingsPanel() {

		try {
			HEVCQsvConfigurationsPanel confpanel = new HEVCQsvConfigurationsPanel(
					(VideoHEVCQsvEncodingInfoContainer) this.infocont);
			confpanel.setLocationRelativeTo(this);
			confpanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			confpanel.setModal(true);
			confpanel.setVisible(true);
		} catch (Exception e) {
		    Logger.error(e);
		}
	}

	protected void launchAV1SettingsPanel() {

		try {
			AV1ConfigurationsPanel confpanel = new AV1ConfigurationsPanel(
					(VideoAV1EncodingInfoContainer) this.infocont);
			confpanel.setLocationRelativeTo(this);
			confpanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			confpanel.setVisible(true);
		} catch (Exception e) {
		    Logger.error(e);
		}
	}
	
	   protected void launchSVTAV1SettingsPanel() {

	        try {
	          SVTAV1ConfigurationsPanel confpanel = new SVTAV1ConfigurationsPanel(
	                    (VideoSVTAV1EncodingInfoContainer) this.infocont);
	            confpanel.setLocationRelativeTo(this);
	            confpanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	            confpanel.setVisible(true);
	        } catch (Exception e) {
	            Logger.error(e);
	        }
	    }
	   
	   protected void launchSVTHEVCSettingsPanel() {

	        try {
	        	SVTHEVCConfigurationsPanel confpanel = new SVTHEVCConfigurationsPanel(
	                    (VideoSVTHEVCEncodingInfoContainer) this.infocont);
	            confpanel.setLocationRelativeTo(this);
	            confpanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	            confpanel.setVisible(true);
	        } catch (Exception e) {
	            Logger.error(e);
	        }
	    }
	

	protected void launchXvidSettingsPanel() {

		try {
			XvidConfigurationsPanel confpanel = new XvidConfigurationsPanel(
					(VideoXvidEncodingInfoContainer) this.infocont);
			confpanel.setLocationRelativeTo(this);
			confpanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			confpanel.setVisible(true);
		} catch (Exception e) {
		    Logger.error(e);
		}
	}

	/**
	 * Launch v p8 settings panel.
	 */
	protected void launchVPxSettingsPanel() {

		try {
			codecvpxsetpanel = new VPxConfigurationsPanel(this.infocont, mainframe);
			codecvpxsetpanel.setLocationRelativeTo(this);
			codecvpxsetpanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			codecvpxsetpanel.setVisible(true);
		} catch (Exception e) {
		    Logger.error(e);
		}

	}

	/**
	 * Launch mpe g2 settings panel.
	 */
	protected void launchMPEG2SettingsPanel() {

		try {
			codecmpg2setanel = new MPEG2ConfigurationsPanel(this.infocont);
			codecmpg2setanel.setLocationRelativeTo(this);
			codecmpg2setanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			codecmpg2setanel.setVisible(true);
		} catch (Exception e) {
		    Logger.error(e);
		}
	}

	/**
	 * Gets the selected codec.
	 *
	 * @return the selected codec
	 */
	public VideoCodecs getSelectedCodec() {
		return (VideoCodecs) jComboBoxcodec.getSelectedItem();
	}

	/**
	 * Gets the selected video container.
	 *
	 * @return the selected video container
	 */
	public VideoContainers getSelectedVideoContainer() {
		this.infocont.setVideoContainer((VideoContainers) jComboBoxcontainer.getSelectedItem());
		return this.infocont.getVideoContainer();
	}

	/**
	 * Sets the parameters from encoding info container.
	 *
	 * @param info the new parameters from encoding info container
	 */
	public void setParametersFromEncodingInfoContainer(IGeneralVideoEncInfoContainer info) {
		this.infocont = info;
		setParametersInsideEncodingInfoContainer();
	}

	/**
	 * Sets the parameters inside encoding info container.
	 */
	protected void setParametersInsideEncodingInfoContainer() {

		boolean blockaspectratio = false;

		VideoCodecs vcodec = infocont.getVideocodec();
		jComboBoxcodec.setSelectedItem(vcodec);
		choosecodecaction();

		if (infocont.isOverwritefile())
			jCheckBoxoverwrite.setSelected(true);
		else
			jCheckBoxoverwrite.setSelected(false);

		AspectComponent ascomp = infocont.getVideoaspectsizecomponent();
		int pos = aspects.getIndexOfAspectComponent(ascomp);

		if (pos != -1) {
			jComboBoxaspect.setSelectedIndex(pos);
			AspectRatio ratio = ascomp.getAspectRatio();
			if (ratio != null) {
				jComboBoxaspratio.setSelectedItem(ascomp.getAspectRatio());
				jComboBoxaspratio.setEnabled(false);
				blockaspectratio = true;
			}
		} else {
			aspects.addComponent(ascomp);
			setNewAspectElementjComboBox(ascomp);
			jComboBoxaspratio.setEnabled(true);
			blockaspectratio = false;
		}

		if (!blockaspectratio) {
			jComboBoxaspratio.setSelectedItem(infocont.getVideoaspectratio());
		}

		FPS framerate = infocont.getVideoframerate();
		jComboBoxfps.setSelectedItem(framerate);

		boolean isTwoPassEnc = infocont.isTwopassEncoding();

		if (isTwoPassEnc)
			pass2selected();
		else
			pass1selected();

		VideoContainers cont = infocont.getVideoContainer();

		setVideoEncodingBitrateType();
		jComboBoxcontainer.setSelectedItem(cont);

		// choosecodecaction();

	}

	/**
	 * Sets the video encoding bitrate type.
	 */
	protected void setVideoEncodingBitrateType() {

		checkEncodingPriority();
		choosecodecaction();
		

		if (infocont.isUseVideoEncodingCBR()) {
			bitrateselected();
			setConstantBitrateValueInCombobox(infocont.getVideoConstBitrate());
		} else if (infocont.isUseVideoEncodingVBR() && !infocont.isTwopassEncoding()) {
			qualityselected();
			setValueIntoSlider();

		} else if (infocont.isUseVideoEncodingFileSize()) {
			sizeselected();
			setFileSizeValueInCombobox(String.valueOf(infocont.getVideoEncodingFileSize()));
		}

		if (infocont.isUseVideoEncodingVBR() && infocont.isTwopassEncoding()) {
			infocont.setUseVideoEncodingCBR(true);
			infocont.setVideoConstBitrate("1000k");
			setVideoEncodingBitrateType();
		}
	}

	/**
	 * Sets the value into slider.
	 */
	public void setValueIntoSlider() {

		VideoCodecs codec = (VideoCodecs) jComboBoxcodec.getSelectedItem();
		if (qualval != null) {
			if (qualval.containsKey(codec.toString())) {
				choosecodecaction();
				changeQualitySlider(qualval.get(codec.toString()));
			}
		}
	}

	// Analyze the priority of encoding CBR > VBR > Filesize encoding
	/**
	 * Check encoding priority.
	 */
	protected void checkEncodingPriority() {

		boolean CBRENC = infocont.isUseVideoEncodingCBR();
		boolean VBRENC = infocont.isUseVideoEncodingVBR();
		boolean FILESIZEENC = infocont.isUseVideoEncodingFileSize();

		if (CBRENC) {
			infocont.setUseVideoEncodingFileSize(false);
			infocont.setUseVideoEncodingVBR(false);
		} else if (VBRENC && !CBRENC) {
			infocont.setUseVideoEncodingFileSize(false);
		} else if (!CBRENC && !VBRENC && !FILESIZEENC)
			infocont.setUseVideoEncodingCBR(true);

	}

	/**
	 * Sets the constant bitrate value in combobox.
	 *
	 * @param value the new constant bitrate value in combobox
	 */
	@SuppressWarnings("unchecked")
	protected void setConstantBitrateValueInCombobox(String value) {

		int checkvalueindex = VideoPanelUtils.indexOfCBRValues(value, videocbrvalues);

		if (checkvalueindex != -1) {
			jComboBoxbitrate.setSelectedIndex(checkvalueindex);
		} else {
			videocbrvalues.addElement(value);
			jComboBoxbitrate.setSelectedItem(value);
		}
	}

	/**
	 * Sets the file size value in combobox.
	 *
	 * @param value the new file size value in combobox
	 */
	protected void setFileSizeValueInCombobox(String value) {

		int checkvalueindex = VideoPanelUtils.indexOfFileSizeValues(value, filesizevalues);

		if (checkvalueindex != -1) {
			jComboBoxfilesize.setSelectedIndex(checkvalueindex);
		} else {
			filesizevalues.addElement(value);
			jComboBoxfilesize.setSelectedItem(value);
		}
	}

	/**
	 * Sets the new aspect elementj combo box.
	 *
	 * @param ascomp the new new aspect elementj combo box
	 */
	protected void setNewAspectElementjComboBox(AspectComponent ascomp) {
		if (aspectsizecombomodel.getSize() == 10) {
			aspectsizecombomodel.removeElementAt(9);
			aspectsizecombomodel.addElement(ascomp);
			jComboBoxaspect.setSelectedIndex(9);
			jComboBoxaspect.setEditable(true);
		} else {
			aspectsizecombomodel.addElement(ascomp);
			jComboBoxaspect.setSelectedIndex(9);
			jComboBoxaspect.setEditable(true);
		}
	}

	/**
	 * Sets the video codec editable.
	 *
	 * @param bol the new video codec editable
	 */
	public void setVideoCodecEditable(boolean bol) {
		jComboBoxcodec.setEnabled(bol);
	}

	/**
	 * Checks if is used file zise.
	 *
	 * @return true, if is used file zise
	 */
	public boolean isUsedFileZise() {
		return steelCheckBoxvideosize.isSelected();
	}

	/**
	 * Save selected information into container.
	 */
	public void saveSelectedInformationIntoContainer() {

		this.infocont.setVideocodec((VideoCodecs) jComboBoxcodec.getSelectedItem());
		this.infocont.setVideoaspectsizeComponent((AspectComponent) jComboBoxaspect.getSelectedItem());

		Object aspectsizeobj = jComboBoxaspect.getSelectedItem();
		if (aspectsizeobj instanceof String) {
			AspectComponent videoaspectsize = new AspectComponent("Custom", (String) aspectsizeobj);
			this.infocont.setVideoaspectsizeComponent(videoaspectsize);
		}

		else if (aspectsizeobj instanceof AspectComponent) {
			this.infocont.setVideoaspectsizeComponent((AspectComponent) aspectsizeobj);
		}

		this.infocont.setVideoframerate((FPS) jComboBoxfps.getSelectedItem());
		this.infocont.setVideoaspectratio((AspectRatio) jComboBoxaspratio.getSelectedItem());

		if (steelCheckBoxbitrate.isSelected()) {
			this.infocont.setVideoConstBitrate((String) jComboBoxbitrate.getSelectedItem());
			this.infocont.setUseVideoEncodingCBR(true);
			this.infocont.setUseVideoEncodingFileSize(false);
			this.infocont.setUseVideoEncodingVBR(false);
		} else if (steelCheckBoxvideosize.isSelected()) {
			this.infocont.setVideoEncodingFileSize(Integer.parseInt((String) jComboBoxfilesize.getSelectedItem()));
			this.infocont.setUseVideoEncodingCBR(false);
			this.infocont.setUseVideoEncodingFileSize(true);
			this.infocont.setUseVideoEncodingVBR(false);
		} else if (steelCheckBoxquality.isSelected()) {
			this.infocont.setVideoVBRQuality((VideoCodecs) jComboBoxcodec.getSelectedItem(), jSliderquality.getValue());
			this.infocont.setUseVideoEncodingCBR(false);
			this.infocont.setUseVideoEncodingFileSize(false);
			this.infocont.setUseVideoEncodingVBR(true);
		}

		if (jRadioButton2passenc.isSelected())
			this.infocont.setTwopassEncoding(true);
		else
			this.infocont.setTwopassEncoding(false);

		this.infocont.setModifiedtag(true);

		if (jCheckBoxoverwrite.isSelected())
			this.infocont.setOverwritefile(true);
		else
			this.infocont.setOverwritefile(false);

		if (comboBoxHWAccel != null) {
			this.infocont.setHardwareAccelerationDecoder((HWAccel) comboBoxHWAccel.getSelectedItem());
		}

		this.infocont.setVideoContainer((VideoContainers) jComboBoxcontainer.getSelectedItem());

	}

	/**
	 * Sets the originalvideo vbr quality.
	 *
	 * @param varqual the varqual
	 */

	public void setOriginalvideoVBRQuality(IndexedHashMap<String, Integer> varqual) {
		this.qualval = varqual;
	}

}
