package pt.ornrocha.rencoder.gui.components.panels.configurations;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.tinylog.Logger;

import eu.hansolo.custom.SteelCheckBox;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.PixelFormat;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.ProfilesH264;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoLevel;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.X264MEMEthods;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.X264Partitions;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.X264Subq;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.X264Trellis;
import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.X26xPresets;
import pt.ornrocha.rencoder.gui.components.panels.auxiliar.VideoPanelUtils;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.mediafiles.files.containers.codecs.VideoH264EncodingInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.extrainfocontainer.H264ExtraInfoContainer;
import pt.ornrocha.rencoder.mediafiles.files.containers.maininfo.IGeneralVideoEncInfoContainer;

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
public class H264ConfigurationsPanel extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton jButtonclose;
	private JLabel jLabelpreset;
	private SteelCheckBox steelCheckBoxextoptions;
	private SteelCheckBox steelCheckBoxfastfirstpass;
	private JSpinner jSpinnerQP;
	private JComboBox<ProfilesH264> jComboBoxprofile;
	private SteelCheckBox steelCheckBoxQP;
	private JLabel jLabelpixelformat;
	private JComboBox<PixelFormat> jComboBoxpixelformat;
	private JLabel jLabellevel;
	private JComboBox<VideoLevel> jComboBoxlevel;
	private JLabel jLabelprofile;
	private JComboBox<X26xPresets> jComboBoxpreset;
	private JComboBox jComboBoxsubq;
	private JPanel jPanelextrabuttons;
	private JButton jButtondefault;
	private JButton jButtonoptionsmean;
	private JSpinner jSpinnerminrate;
	private JLabel jLabelminrate;
	private JComboBox jComboBoxtrellis;
	private JLabel jLabeltrellis;
	private JLabel jLabelsubq;
	private JComboBox jComboBoxmemethod;
	private JLabel jLabelmemethod;
	private JSpinner jSpinnerdirectpred;
	private JLabel jLabeldirectpred;
	private JComboBox jComboBoxpartitions;
	private JLabel jLabelpartitions;
	private JSpinner jSpinnerqdiff;
	private JLabel jLabelqdiff;
	private JSpinner jSpinnerqmax;
	private JLabel jLabelqmax;
	private JSpinner jSpinnerqmin;
	private JLabel jLabelqmin;
	private JSpinner jSpinnermaxrate;
	private JLabel maxrate;
	private JSpinner jSpinnerbufsize;
	private JPanel jPanelextra;
	private JPanel jPaneldefault;
	private JButton jButtonok;

	private IGeneralVideoEncInfoContainer infocont = null;
	private JSpinner jSpinnerdeblockbeta;
	private JLabel jLabeldeblockbeta;
	private JSpinner jSpinnerdeblockalpha;
	private JLabel jLabeldeblockalpha;
	private JSpinner jSpinnerbframes;
	private SteelCheckBox steelCheckBoxnodeblock;
	private JSpinner jSpinnerref;
	private JLabel jLabelref;
	private JLabel jLabelbufsize;
	private SteelCheckBox steelCheckBoxcabac;
	private SteelCheckBox steelCheckBoxbpyramid;
	private JSpinner jSpinnerbadapt;
	private JLabel jLabelbadapt;
	private JLabel jLabelbframes;
	private JSpinner jSpinnerscenecut;
	private JLabel jLabelscenecut;
	private JSpinner jSpinnerminkeyint;
	private JLabel jLabelminkeyint;
	private JSpinner jSpinnerkeyint;
	private JLabel jLabelkeyint;
	private JPanel jPanelanalysis;
	private JPanel jPanelratecontrol;
	private JPanel jPanelframetype;
	private JTabbedPane jTabbedPaneoptions;

	private static String CLOSE = "close";
	private static String SAVECLOSE = "saveclose";
	private static String EXTRAOPTIONS = "extraoptions";
	private static String QP = "Constantquantization";
	private static String ONOFFFASTFIRSTPASS = "fastfirstpassonoff";

	private static String NODEBLOCK = "nodeblock";
	private static String CABAC = "cabac";
	private static String RESERDEFAULT = "resettodefault";
	private static String CHECKCONFSWEB = "checkconfsweb";

	private static String WEBPAGE = "https://sites.google.com/site/linuxencoding/x264-ffmpeg-mapping";

	public H264ConfigurationsPanel(IGeneralVideoEncInfoContainer infocont) {
		initGUI();
		this.setModal(true);
		initializeComponents();
		this.infocont = infocont;
		setInfoFromEncodingContainer();
	}

	private void initializeComponents() {

		setonlydefaultPanel();
		jComboBoxpreset.setModel(new DefaultComboBoxModel<>(X26xPresets.values()));
		jComboBoxprofile.setModel(new DefaultComboBoxModel<>(ProfilesH264.values()));
		jComboBoxpixelformat.setModel(new DefaultComboBoxModel<>(PixelFormat.values()));
		jComboBoxlevel.setModel(new DefaultComboBoxModel<>(VideoLevel.values()));

		steelCheckBoxfastfirstpass.setColored(true);
		steelCheckBoxQP.setColored(true);
		steelCheckBoxextoptions.setColored(true);
		initializeComponentsState();
	}

	private void initializeComponentsState() {
		steelCheckBoxextoptions.setSelected(false);
		steelCheckBoxfastfirstpass.setSelected(false);
		steelCheckBoxQP.setSelected(false);
		setValuesJSpinner();
		jSpinnerQP.setEnabled(false);

	}

	private void setValuesJSpinner() {

		SpinnerNumberModel model = new SpinnerNumberModel(45, 0, 100, 1);
		jSpinnerQP.setModel(model);
	}

	private void setonlydefaultPanel() {
		jPanelextra.setVisible(false);
		jPaneldefault.setSize(450, 350);
		getContentPane().add(jPaneldefault, new GridBagConstraints(0, 0, 10, 15, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		if (jPanelextrabuttons != null)
			this.remove(jPanelextrabuttons);
		this.setSize(475, 388);
	}

	private void setTwoPanels() {
		jPanelextra.setVisible(true);
		jPaneldefault.setSize(450, 350);
		getContentPane().add(jPaneldefault, new GridBagConstraints(0, 0, 5, 15, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		getContentPane().add(jPanelextra, new GridBagConstraints(5, 0, 10, 14, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		if (jPanelextrabuttons == null)
			setjPanelextrabuttons();
		getContentPane().add(jPanelextrabuttons, new GridBagConstraints(5, 14, 5, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		this.setSize(915, 388);
	}

	private void setjPanelextrabuttons() {
		jPanelextrabuttons = new JPanel();
		GridBagLayout jPanelextrabuttonsLayout = new GridBagLayout();
		getContentPane().add(jPanelextrabuttons, new GridBagConstraints(5, 14, 5, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		jPanelextrabuttonsLayout.rowWeights = new double[] { 0.1 };
		jPanelextrabuttonsLayout.rowHeights = new int[] { 7 };
		jPanelextrabuttonsLayout.columnWeights = new double[] { 0.1, 0.1 };
		jPanelextrabuttonsLayout.columnWidths = new int[] { 7, 7 };
		jPanelextrabuttons.setLayout(jPanelextrabuttonsLayout);
		{
			jButtondefault = new JButton();
			jPanelextrabuttons.add(jButtondefault, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			jButtondefault.setText(LangTools.getWordLanguage("Reset to default", "x264gui.extradefault"));
			jButtondefault.setActionCommand(RESERDEFAULT);
			jButtondefault.addActionListener(this);
		}
		{
			jButtonoptionsmean = new JButton();
			jPanelextrabuttons.add(jButtonoptionsmean, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			jButtonoptionsmean
					.setText(LangTools.getWordLanguage("Check parameter options online", "x264gui.optionsonline"));
			jButtonoptionsmean.setActionCommand(CHECKCONFSWEB);
			jButtonoptionsmean.addActionListener(this);

		}

	}

	private void initGUI() {
		try {
			{

				GridBagLayout thisLayout = new GridBagLayout();
				thisLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1,
						0.1, 0.1, 0.1 };
				thisLayout.rowHeights = new int[] { 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7 };
				thisLayout.columnWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1 };

				thisLayout.columnWidths = new int[] { 7, 7, 7, 7, 7, 7, 7, 7, 7, 7 };
				getContentPane().setLayout(thisLayout);
				{
					jButtonclose = new JButton();
					getContentPane().add(jButtonclose, new GridBagConstraints(0, 15, 5, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jButtonclose.setText(LangTools.getWordLanguage("Cancel", "general.cancel"));
					jButtonclose.setActionCommand(CLOSE);
					jButtonclose.addActionListener(this);
				}
				{
					jButtonok = new JButton();
					getContentPane().add(jButtonok, new GridBagConstraints(5, 15, 5, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jButtonok.setText(LangTools.getWordLanguage("Save", "general.save"));
					jButtonok.setActionCommand(SAVECLOSE);
					jButtonok.addActionListener(this);

				}
				{
					jPaneldefault = new JPanel();
					GridBagLayout jPaneldefaultLayout = new GridBagLayout();
					getContentPane().add(jPaneldefault, new GridBagConstraints(0, 0, 5, 15, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					// getContentPane().add(jPanelextra, new GridBagConstraints(5, 0, 10, 15, 0.0,
					// 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0,
					// 0), 0, 0));
					jPaneldefault.setBorder(BorderFactory
							.createTitledBorder(LangTools.getWordLanguage("H264 settings", "x264gui.settings")));
					jPaneldefaultLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1 };
					jPaneldefaultLayout.rowHeights = new int[] { 7, 7, 7, 7, 7, 7, 7, 7 };
					jPaneldefaultLayout.columnWeights = new double[] { 0.1, 0.1, 0.1, 0.1 };
					jPaneldefaultLayout.columnWidths = new int[] { 7, 7, 7, 7 };
					jPaneldefault.setLayout(jPaneldefaultLayout);
					{

						jComboBoxpreset = new JComboBox<X26xPresets>();
						jPaneldefault.add(jComboBoxpreset,
								new GridBagConstraints(1, 1, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER,
										GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

					}
					{
						jLabelpreset = new JLabel();
						jPaneldefault.add(jLabelpreset,
								new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
										GridBagConstraints.HORIZONTAL, new Insets(0, 20, 0, 0), 0, 0));
						jLabelpreset.setText("Preset");
					}
					{

						jComboBoxprofile = new JComboBox<ProfilesH264>();
						jPaneldefault.add(jComboBoxprofile,
								new GridBagConstraints(1, 2, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER,
										GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

					}
					{
						jLabelprofile = new JLabel();
						jPaneldefault.add(jLabelprofile,
								new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
										GridBagConstraints.HORIZONTAL, new Insets(0, 20, 0, 0), 0, 0));
						jLabelprofile.setText("Profile");
					}
					{

						jComboBoxlevel = new JComboBox<VideoLevel>();
						jPaneldefault.add(jComboBoxlevel,
								new GridBagConstraints(1, 3, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER,
										GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

					}
					{
						jLabellevel = new JLabel();
						jPaneldefault.add(jLabellevel,
								new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
										GridBagConstraints.HORIZONTAL, new Insets(0, 20, 0, 0), 0, 0));
						jLabellevel.setText("Level");
					}
					{
						;
						jComboBoxpixelformat = new JComboBox<PixelFormat>();
						jPaneldefault.add(jComboBoxpixelformat,
								new GridBagConstraints(1, 4, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER,
										GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

					}
					{
						jLabelpixelformat = new JLabel();
						jPaneldefault.add(jLabelpixelformat,
								new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
										GridBagConstraints.HORIZONTAL, new Insets(0, 20, 0, 0), 0, 0));
						jLabelpixelformat.setText("Pixel format");
					}
					{
						steelCheckBoxQP = new SteelCheckBox();
						jPaneldefault.add(steelCheckBoxQP,
								new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
										GridBagConstraints.HORIZONTAL, new Insets(0, 20, 0, 0), 0, 0));
						steelCheckBoxQP.setText("Const. quant.");
						steelCheckBoxQP.setActionCommand(QP);
						steelCheckBoxQP.addActionListener(this);
					}
					{

						jSpinnerQP = new JSpinner();
						jPaneldefault.add(jSpinnerQP,
								new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
										GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

					}
					{
						steelCheckBoxfastfirstpass = new SteelCheckBox();
						jPaneldefault.add(steelCheckBoxfastfirstpass,
								new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
										GridBagConstraints.HORIZONTAL, new Insets(0, 20, 0, 0), 0, 0));
						steelCheckBoxfastfirstpass
								.setText(LangTools.getWordLanguage("Fast first pass", "x264gui.fastfirstpass"));
						steelCheckBoxfastfirstpass.setActionCommand(ONOFFFASTFIRSTPASS);
						steelCheckBoxfastfirstpass.addActionListener(this);
					}
					{
						steelCheckBoxextoptions = new SteelCheckBox();
						jPaneldefault.add(steelCheckBoxextoptions,
								new GridBagConstraints(1, 0, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER,
										GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						steelCheckBoxextoptions
								.setText(LangTools.getWordLanguage("Use Advanced options", "x264gui.advanced"));
						steelCheckBoxextoptions.setActionCommand(EXTRAOPTIONS);
						steelCheckBoxextoptions.addActionListener(this);
					}
				}
				{
					jPanelextra = new JPanel();
					GridBagLayout jPanelextraLayout = new GridBagLayout();
					getContentPane().add(jPanelextra, new GridBagConstraints(5, 0, 10, 15, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPanelextra.setBorder(BorderFactory.createTitledBorder(
							LangTools.getWordLanguage("Advanced H264 options", "x264gui.extraoptions")));
					jPanelextraLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1 };
					jPanelextraLayout.rowHeights = new int[] { 7, 7, 7, 7, 7, 7 };
					jPanelextraLayout.columnWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1 };
					jPanelextraLayout.columnWidths = new int[] { 7, 7, 7, 7, 7, 7, 7, 7, 7, 7 };
					jPanelextra.setLayout(jPanelextraLayout);
					{
						jTabbedPaneoptions = new JTabbedPane();
						jPanelextra.add(jTabbedPaneoptions, new GridBagConstraints(0, 0, 10, 6, 0.0, 0.0,
								GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						{
							jPanelframetype = new JPanel();
							GridBagLayout jPanelframetypeLayout = new GridBagLayout();
							jTabbedPaneoptions.addTab("Frame-type", null, jPanelframetype, null);
							jPanelframetypeLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1 };
							jPanelframetypeLayout.rowHeights = new int[] { 7, 7, 7, 7, 7, 7, 7, 7 };
							jPanelframetypeLayout.columnWeights = new double[] { 0.1, 0.1, 0.1, 0.1 };
							jPanelframetypeLayout.columnWidths = new int[] { 7, 7, 7, 7 };
							jPanelframetype.setLayout(jPanelframetypeLayout);
							{
								jLabelkeyint = new JLabel();
								jPanelframetype.add(jLabelkeyint,
										new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								jLabelkeyint.setText("keyint");
							}
							{

								jSpinnerkeyint = new JSpinner();
								jPanelframetype.add(jSpinnerkeyint,
										new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								jSpinnerkeyint.setPreferredSize(new java.awt.Dimension(90, 30));

							}
							{
								jLabelminkeyint = new JLabel();
								jPanelframetype.add(jLabelminkeyint,
										new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								jLabelminkeyint.setText("min-keyint");
							}
							{

								jSpinnerminkeyint = new JSpinner();
								jPanelframetype.add(jSpinnerminkeyint,
										new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								jSpinnerminkeyint.setPreferredSize(new java.awt.Dimension(90, 30));

							}
							{
								jLabelscenecut = new JLabel();
								jPanelframetype.add(jLabelscenecut,
										new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								jLabelscenecut.setText("scenecut");
							}
							{

								jSpinnerscenecut = new JSpinner();
								jPanelframetype.add(jSpinnerscenecut,
										new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								jSpinnerscenecut.setPreferredSize(new java.awt.Dimension(90, 30));

							}
							{
								jLabelbframes = new JLabel();
								jPanelframetype.add(jLabelbframes,
										new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								jLabelbframes.setText("bframes");
							}
							{

								jSpinnerbframes = new JSpinner();
								jPanelframetype.add(jSpinnerbframes,
										new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								jSpinnerbframes.setPreferredSize(new java.awt.Dimension(90, 30));

							}
							{
								jLabelbadapt = new JLabel();
								jPanelframetype.add(jLabelbadapt,
										new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								jLabelbadapt.setText("b-adapt");
							}
							{

								jSpinnerbadapt = new JSpinner();
								jPanelframetype.add(jSpinnerbadapt,
										new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								jSpinnerbadapt.setSize(100, 22);
								jSpinnerbadapt.setPreferredSize(new java.awt.Dimension(90, 30));

							}
							{
								steelCheckBoxbpyramid = new SteelCheckBox();
								jPanelframetype.add(steelCheckBoxbpyramid,
										new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								steelCheckBoxbpyramid.setText("b-pyramid");
							}
							{
								steelCheckBoxcabac = new SteelCheckBox();
								jPanelframetype.add(steelCheckBoxcabac,
										new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								steelCheckBoxcabac.setText("cabac");
								steelCheckBoxcabac.setActionCommand(CABAC);
								steelCheckBoxcabac.addActionListener(this);
							}
							{
								jLabelref = new JLabel();
								jPanelframetype.add(jLabelref,
										new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								jLabelref.setText("ref");
							}
							{

								jSpinnerref = new JSpinner();
								jPanelframetype.add(jSpinnerref,
										new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								jSpinnerref.setPreferredSize(new java.awt.Dimension(90, 30));

							}
							{
								steelCheckBoxnodeblock = new SteelCheckBox();
								jPanelframetype.add(steelCheckBoxnodeblock,
										new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								steelCheckBoxnodeblock.setText("deblock");
								steelCheckBoxnodeblock.setActionCommand(NODEBLOCK);
								steelCheckBoxnodeblock.addActionListener(this);
							}
							{
								jLabeldeblockalpha = new JLabel();
								jPanelframetype.add(jLabeldeblockalpha,
										new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								jLabeldeblockalpha.setText("deblockalpha");
							}
							{

								jSpinnerdeblockalpha = new JSpinner();
								jPanelframetype.add(jSpinnerdeblockalpha,
										new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								jSpinnerdeblockalpha.setPreferredSize(new java.awt.Dimension(90, 30));

							}
							{
								jLabeldeblockbeta = new JLabel();
								jPanelframetype.add(jLabeldeblockbeta,
										new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								jLabeldeblockbeta.setText("deblockbeta");
							}
							{

								jSpinnerdeblockbeta = new JSpinner();
								jPanelframetype.add(jSpinnerdeblockbeta,
										new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								jSpinnerdeblockbeta.setPreferredSize(new java.awt.Dimension(90, 30));

							}
						}
						{
							jPanelratecontrol = new JPanel();
							GridBagLayout jPanelratecontrolLayout = new GridBagLayout();
							jTabbedPaneoptions.addTab("Ratecontrol", null, jPanelratecontrol, null);
							jPanelratecontrolLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1,
									0.1 };
							jPanelratecontrolLayout.rowHeights = new int[] { 7, 7, 7, 7, 7, 7, 7, 7 };
							jPanelratecontrolLayout.columnWeights = new double[] { 0.1, 0.1, 0.1, 0.1 };
							jPanelratecontrolLayout.columnWidths = new int[] { 7, 7, 7, 7 };
							jPanelratecontrol.setLayout(jPanelratecontrolLayout);
							{
								jLabelbufsize = new JLabel();
								jPanelratecontrol.add(jLabelbufsize,
										new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								jLabelbufsize.setText("bufsize");
							}
							{

								jSpinnerbufsize = new JSpinner();
								jSpinnerbufsize.addChangeListener(new ChangeListener() {

									@Override
									public void stateChanged(ChangeEvent e) {
										SpinnerValueStateChanged(e);
									}

								});

								jPanelratecontrol.add(jSpinnerbufsize,
										new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								jSpinnerbufsize.setPreferredSize(new java.awt.Dimension(90, 30));
							}
							{
								maxrate = new JLabel();
								jPanelratecontrol.add(maxrate,
										new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								maxrate.setText("maxrate");
							}
							{

								jSpinnermaxrate = new JSpinner();
								jPanelratecontrol.add(jSpinnermaxrate,
										new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								jSpinnermaxrate.setPreferredSize(new java.awt.Dimension(90, 30));
							}
							{
								jLabelqmin = new JLabel();
								jPanelratecontrol.add(jLabelqmin,
										new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								jLabelqmin.setText("qmin");
							}
							{

								jSpinnerqmin = new JSpinner();
								jPanelratecontrol.add(jSpinnerqmin,
										new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								jSpinnerqmin.setPreferredSize(new java.awt.Dimension(90, 30));
							}
							{
								jLabelqmax = new JLabel();
								jPanelratecontrol.add(jLabelqmax,
										new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								jLabelqmax.setText("qmax");
							}
							{
								jSpinnerqmax = new JSpinner();
								jPanelratecontrol.add(jSpinnerqmax,
										new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

								jSpinnerqmax.setPreferredSize(new java.awt.Dimension(90, 30));
							}
							{
								jLabelqdiff = new JLabel();
								jPanelratecontrol.add(jLabelqdiff,
										new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								jLabelqdiff.setText("qdiff");
							}
							{

								jSpinnerqdiff = new JSpinner();
								jPanelratecontrol.add(jSpinnerqdiff,
										new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

								jSpinnerqdiff.setPreferredSize(new java.awt.Dimension(90, 30));
							}
							{
								jLabelminrate = new JLabel();
								jPanelratecontrol.add(jLabelminrate,
										new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								jLabelminrate.setText("minrate");
							}
							{
								jSpinnerminrate = new JSpinner();
								jPanelratecontrol.add(jSpinnerminrate,
										new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

								jSpinnerminrate.setSize(90, 30);
								jSpinnerminrate.setPreferredSize(new java.awt.Dimension(90, 30));
							}
						}
						{
							jPanelanalysis = new JPanel();
							GridBagLayout jPanelanalysisLayout = new GridBagLayout();
							jTabbedPaneoptions.addTab("Analysis", null, jPanelanalysis, null);
							jPanelanalysisLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1 };
							jPanelanalysisLayout.rowHeights = new int[] { 7, 7, 7, 7, 7, 7, 7, 7 };
							jPanelanalysisLayout.columnWeights = new double[] { 0.1, 0.1, 0.1, 0.1 };
							jPanelanalysisLayout.columnWidths = new int[] { 7, 7, 7, 7 };
							jPanelanalysis.setLayout(jPanelanalysisLayout);
							{
								jLabelpartitions = new JLabel();
								jPanelanalysis.add(jLabelpartitions,
										new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								jLabelpartitions.setText("partitions");
							}
							{
								jComboBoxpartitions = new JComboBox();
								jPanelanalysis.add(jComboBoxpartitions,
										new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

							}
							{
								jLabeldirectpred = new JLabel();
								jPanelanalysis.add(jLabeldirectpred,
										new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								jLabeldirectpred.setText("directpred");
							}
							{
								jSpinnerdirectpred = new JSpinner();
								jPanelanalysis.add(jSpinnerdirectpred,
										new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

								jSpinnerdirectpred.setPreferredSize(new java.awt.Dimension(90, 30));
							}
							{
								jLabelmemethod = new JLabel();
								jPanelanalysis.add(jLabelmemethod,
										new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								jLabelmemethod.setText("me_method");
							}
							{
								jComboBoxmemethod = new JComboBox();
								jPanelanalysis.add(jComboBoxmemethod,
										new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

							}
							{
								jLabelsubq = new JLabel();
								jPanelanalysis.add(jLabelsubq,
										new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								jLabelsubq.setText("subq");
							}
							{
								jComboBoxsubq = new JComboBox();
								jPanelanalysis.add(jComboBoxsubq,
										new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

							}
							{
								jLabeltrellis = new JLabel();
								jPanelanalysis.add(jLabeltrellis,
										new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								jLabeltrellis.setText("trellis");
							}
							{
								jComboBoxtrellis = new JComboBox();
								jPanelanalysis.add(jComboBoxtrellis,
										new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
												GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

							}
						}
					}
				}
			}
			{
				this.setSize(815, 388);
			}
		} catch (Exception e) {
		    Logger.error(e);
		}
	}

	private void SpinnerValueStateChanged(ChangeEvent e) {
		JSpinner jSpinnerbufsize = (JSpinner) e.getSource();
		SpinnerNumberModel spinnerModel = (SpinnerNumberModel) jSpinnerbufsize.getModel();
		int value = (int) spinnerModel.getValue();

		if (value == 0) {
			jSpinnerminrate.setEnabled(false);
			jSpinnermaxrate.setEnabled(false);
		} else {
			jSpinnerminrate.setEnabled(true);
			jSpinnermaxrate.setEnabled(true);
		}
	}

	public static void main(String args[]) {
		H264ConfigurationsPanel t = new H264ConfigurationsPanel(null);
		t.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (cmd.equals(CLOSE)) {
			this.dispose();
		}

		else if (cmd.equals(SAVECLOSE)) {
			saveChoosenOptionsToContainer();
			this.dispose();

		}

		else if (cmd.equals(EXTRAOPTIONS)) {
			if (steelCheckBoxextoptions.isSelected()) {
				setTwoPanels();
				setDefaultExtraOptions();
				getExtraH264Info();
			} else if (!steelCheckBoxextoptions.isSelected()) {
				setonlydefaultPanel();

			}
		} else if (cmd.equals(RESERDEFAULT))
			setDefaultExtraOptions();
		else if (cmd.equals(QP)) {
			checkQP();
		} else if (cmd.equals(NODEBLOCK)) {
			setNodeblockAction();
		} else if (cmd.equals(CABAC))
			cabacAction();
		else if (cmd.equals(CHECKCONFSWEB))
			VideoPanelUtils.openInBrowser(WEBPAGE);

	}

	private void setNodeblockAction() {
		if (steelCheckBoxnodeblock.isSelected()) {
			jSpinnerdeblockalpha.setEnabled(true);
			jSpinnerdeblockbeta.setEnabled(true);
		} else {
			jSpinnerdeblockalpha.setEnabled(false);
			jSpinnerdeblockbeta.setEnabled(false);
		}
	}

	private void cabacAction() {
		if (steelCheckBoxcabac.isSelected()) {
			jComboBoxtrellis.setEnabled(true);
		} else {
			jComboBoxtrellis.setEnabled(false);
			jComboBoxtrellis.setSelectedIndex(0);
		}
	}

	private void fastfirstpassON() {
		steelCheckBoxfastfirstpass.setSelected(true);

	}

	/**
	 * Fast first pass off.
	 */
	private void fastfirstpassOFF() {
		steelCheckBoxfastfirstpass.setSelected(false);

	}

	/**
	 * Check qp.
	 */
	private void checkQP() {
		if (steelCheckBoxQP.isSelected())
			jSpinnerQP.setEnabled(true);
		if (!steelCheckBoxQP.isSelected())
			jSpinnerQP.setEnabled(false);

	}

	/**
	 * Sets the const quant.
	 *
	 * @param value the new const quant
	 */
	private void setConstQuant(int value) {
		steelCheckBoxQP.setSelected(true);
		steelCheckBoxQP.setColored(true);
		jSpinnerQP.setValue(value);
		checkQP();
	}

	private void setInfoFromEncodingContainer() {

		boolean usefastfirstpass = ((VideoH264EncodingInfoContainer) infocont).isFastfirstpass();
		if (usefastfirstpass)
			fastfirstpassON();
		else
			fastfirstpassOFF();

		int qp = ((VideoH264EncodingInfoContainer) infocont).getConstantQuantization();
		if (qp != -1)
			setConstQuant(qp);

		jComboBoxpreset.setSelectedItem(((VideoH264EncodingInfoContainer) infocont).getPreset());
		jComboBoxpixelformat.setSelectedItem(((VideoH264EncodingInfoContainer) infocont).getPixelformat());
		jComboBoxprofile.setSelectedItem(((VideoH264EncodingInfoContainer) infocont).getProfile());
		jComboBoxlevel.setSelectedItem(((VideoH264EncodingInfoContainer) infocont).getLevel());

		if (((VideoH264EncodingInfoContainer) infocont).getExtrainfocont() != null) {
			steelCheckBoxextoptions.setSelected(true);
			setDefaultExtraOptions();
			getExtraH264Info();
			setTwoPanels();
		}
	}

	private void getExtraH264Info() {
		H264ExtraInfoContainer extracont = null;

		if (((VideoH264EncodingInfoContainer) infocont).getExtrainfocont() != null) {
			extracont = (H264ExtraInfoContainer) ((VideoH264EncodingInfoContainer) infocont).getExtrainfocont();

			jSpinnerkeyint.setValue(extracont.getKeyint());
			;
			jSpinnerminkeyint.setValue(extracont.getMinkeyint());
			jSpinnerscenecut.setValue(extracont.getScenecut());
			jSpinnerbframes.setValue(extracont.getBframes());
			jSpinnerbadapt.setValue(extracont.getBadapt());
			jSpinnerref.setValue(extracont.getRef());

			boolean deblock = extracont.isNodeblock();
			if (!deblock) {
				steelCheckBoxnodeblock.setSelected(true);
				jSpinnerdeblockalpha.setEnabled(true);
				jSpinnerdeblockbeta.setEnabled(true);
				jSpinnerdeblockalpha.setValue(extracont.getDeblockalpha());
				jSpinnerdeblockbeta.setValue(extracont.getDeblockbeta());
			} else {
				steelCheckBoxnodeblock.setSelected(false);
				jSpinnerdeblockalpha.setEnabled(false);
				jSpinnerdeblockbeta.setEnabled(false);
				jSpinnerdeblockalpha.setValue(0);
				jSpinnerdeblockbeta.setValue(0);
			}

			steelCheckBoxbpyramid.setSelected(extracont.isBpyramid());

			boolean cabac = extracont.isNocabac();

			steelCheckBoxcabac.setSelected(!cabac);
			cabacAction();
			if (!cabac) {
				jComboBoxtrellis.setEnabled(true);
				jComboBoxtrellis.setSelectedItem(setjComboBoxtrellis(String.valueOf(extracont.getTrellis())));
			} else
				jComboBoxtrellis.setEnabled(false);

			int bufsize = extracont.getBufsize();

			if (bufsize > 0) {
				jSpinnerminrate.setEnabled(true);
				jSpinnermaxrate.setEnabled(true);

				jSpinnerbufsize.setValue(extracont.getBufsize());
				jSpinnerminrate.setValue(extracont.getMinrate());
				jSpinnermaxrate.setValue(extracont.getMaxrate());
			} else {
				jSpinnerminrate.setEnabled(false);
				jSpinnermaxrate.setEnabled(false);
				jSpinnerbufsize.setValue(0);
				jSpinnerminrate.setValue(0);
				jSpinnermaxrate.setValue(0);
			}

			jSpinnerqdiff.setValue(extracont.getQdiff());
			jSpinnerqmin.setValue(extracont.getQmin());
			jSpinnerqmax.setValue(extracont.getQmax());

			jComboBoxpartitions.setSelectedItem(setjComboBoxpartitions(extracont.getPartitions()));
			jComboBoxmemethod.setSelectedItem(setjComboBoxmemethod(extracont.getMemethod()));
			jComboBoxsubq.setSelectedItem(setjComboBoxsubq(String.valueOf(extracont.getSubq())));
			jSpinnerdirectpred.setValue(extracont.getDirectpred());
		}

	}

	/**
	 * Save choosen options to container.
	 */
	private void saveChoosenOptionsToContainer() {
		boolean fastpass = steelCheckBoxfastfirstpass.isSelected();

		((VideoH264EncodingInfoContainer) infocont).setFastfirstpass(fastpass);

		if (steelCheckBoxQP.isSelected())
			((VideoH264EncodingInfoContainer) infocont).setConstantQuantization((int) jSpinnerQP.getValue());

		((VideoH264EncodingInfoContainer) infocont).setPreset((X26xPresets) jComboBoxpreset.getSelectedItem());
		((VideoH264EncodingInfoContainer) infocont).setProfile((ProfilesH264) jComboBoxprofile.getSelectedItem());
		((VideoH264EncodingInfoContainer) infocont)
				.setPixelformat((PixelFormat) jComboBoxpixelformat.getSelectedItem());
		((VideoH264EncodingInfoContainer) infocont).setLevel((VideoLevel) jComboBoxlevel.getSelectedItem());

		saveExtraOptions();
	}

	private void saveExtraOptions() {
		H264ExtraInfoContainer extracont = null;

		if (((VideoH264EncodingInfoContainer) infocont).getExtrainfocont() != null)
			extracont = (H264ExtraInfoContainer) ((VideoH264EncodingInfoContainer) infocont).getExtrainfocont();
		else
			extracont = new H264ExtraInfoContainer();

		if (steelCheckBoxextoptions.isSelected()) {

			extracont.setKeyint((int) jSpinnerkeyint.getValue());
			extracont.setMinkeyint((int) jSpinnerminkeyint.getValue());
			extracont.setScenecut((int) jSpinnerscenecut.getValue());
			extracont.setBframes((int) jSpinnerbframes.getValue());
			extracont.setBadapt((int) jSpinnerbadapt.getValue());
			extracont.setRef((int) jSpinnerref.getValue());
			if (!steelCheckBoxnodeblock.isSelected()) {
				extracont.setNodeblock(true);
			} else {
				extracont.setDeblockalpha((int) jSpinnerdeblockalpha.getValue());
				extracont.setDeblockbeta((int) jSpinnerdeblockbeta.getValue());
			}
			if (steelCheckBoxbpyramid.isSelected())
				extracont.setBpyramid(true);
			else
				extracont.setBpyramid(false);

			if (steelCheckBoxcabac.isSelected())
				extracont.setNocabac(false);
			else
				extracont.setNocabac(true);

			extracont.setBufsize((int) jSpinnerbufsize.getValue());
			extracont.setMinrate((int) jSpinnerminrate.getValue());
			extracont.setMaxrate((int) jSpinnermaxrate.getValue());

			extracont.setQdiff((int) jSpinnerqdiff.getValue());
			extracont.setQmin((int) jSpinnerqmin.getValue());
			extracont.setQmax((int) jSpinnerqmax.getValue());

			extracont.setPartitions(jComboBoxpartitions.getSelectedItem().toString());
			extracont.setMemethod(jComboBoxmemethod.getSelectedItem().toString());
			extracont.setSubq(Integer.valueOf(jComboBoxsubq.getSelectedItem().toString()));
			extracont.setTrellis(Integer.valueOf(jComboBoxtrellis.getSelectedItem().toString()));

			extracont.setDirectpred((int) jSpinnerdirectpred.getValue());

			((VideoH264EncodingInfoContainer) infocont).setExtrainfocont(extracont);

		} else {
			((VideoH264EncodingInfoContainer) infocont).setExtrainfocont(null);
		}

	}

	private void setDefaultExtraOptions() {
		SpinnerNumberModel modelkeyint = new SpinnerNumberModel(250, 0, Integer.MAX_VALUE, 1);
		jSpinnerkeyint.setModel(modelkeyint);

		SpinnerNumberModel modelminkeyint = new SpinnerNumberModel(25, 0, Integer.MAX_VALUE, 1);
		jSpinnerminkeyint.setModel(modelminkeyint);

		SpinnerNumberModel modelscenecut = new SpinnerNumberModel(40, 0, Integer.MAX_VALUE, 1);
		jSpinnerscenecut.setModel(modelscenecut);

		SpinnerNumberModel jSpinnerbframesModel = new SpinnerNumberModel(16, 0, Integer.MAX_VALUE, 1);
		jSpinnerbframes.setModel(jSpinnerbframesModel);

		SpinnerNumberModel jSpinnerbadaptModel = new SpinnerNumberModel(1, 0, 2, 1);
		jSpinnerbadapt.setModel(jSpinnerbadaptModel);

		SpinnerNumberModel jSpinnerrefModel = new SpinnerNumberModel(6, 0, Integer.MAX_VALUE, 1);
		jSpinnerref.setModel(jSpinnerrefModel);

		SpinnerNumberModel jSpinnerdeblockalphaModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
		jSpinnerdeblockalpha.setModel(jSpinnerdeblockalphaModel);
		jSpinnerdeblockalpha.setEnabled(true);

		SpinnerNumberModel jSpinnerdeblockbetaModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
		jSpinnerdeblockbeta.setModel(jSpinnerdeblockbetaModel);
		jSpinnerdeblockbeta.setEnabled(true);

		steelCheckBoxcabac.setSelected(true);
		steelCheckBoxcabac.setColored(true);
		steelCheckBoxnodeblock.setSelected(true);
		steelCheckBoxnodeblock.setColored(true);
		steelCheckBoxbpyramid.setSelected(false);
		steelCheckBoxbpyramid.setColored(true);

		SpinnerNumberModel jSpinnerbufsizemodel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
		jSpinnerbufsize.setModel(jSpinnerbufsizemodel);

		SpinnerNumberModel jSpinnermaxrateModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
		jSpinnermaxrate.setModel(jSpinnermaxrateModel);

		SpinnerNumberModel jSpinnerminrateModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
		jSpinnerminrate.setModel(jSpinnerminrateModel);

		SpinnerNumberModel jSpinnerqmaxModel = new SpinnerNumberModel(51, 0, Integer.MAX_VALUE, 1);
		jSpinnerqmax.setModel(jSpinnerqmaxModel);

		SpinnerNumberModel jSpinnerqminModel = new SpinnerNumberModel(10, 0, Integer.MAX_VALUE, 1);
		jSpinnerqmin.setModel(jSpinnerqminModel);

		SpinnerNumberModel jSpinnerqdiffModel = new SpinnerNumberModel(4, 0, Integer.MAX_VALUE, 1);
		jSpinnerqdiff.setModel(jSpinnerqdiffModel);

		ComboBoxModel jComboBoxpartitionsModel = new DefaultComboBoxModel(X264Partitions.values());
		jComboBoxpartitions.setModel(jComboBoxpartitionsModel);

		SpinnerNumberModel jSpinnerdirectpredModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
		jSpinnerdirectpred.setModel(jSpinnerdirectpredModel);

		ComboBoxModel jComboBoxmemethodModel = new DefaultComboBoxModel(X264MEMEthods.values());
		jComboBoxmemethod.setModel(jComboBoxmemethodModel);

		ComboBoxModel jComboBoxsubqModel = new DefaultComboBoxModel(X264Subq.values());
		jComboBoxsubq.setModel(jComboBoxsubqModel);
		jComboBoxsubqModel.setSelectedItem("6");

		ComboBoxModel jComboBoxtrellisModel = new DefaultComboBoxModel(X264Trellis.values());
		jComboBoxtrellis.setModel(jComboBoxtrellisModel);

	}

	private X264Partitions setjComboBoxpartitions(String par) {
		for (X264Partitions p : X264Partitions.values()) {
			if (par.toLowerCase().equals(p.toString()))
				return p;
		}
		return X264Partitions.none;
	}

	private X264Subq setjComboBoxsubq(String par) {
		for (X264Subq p : X264Subq.values()) {
			if (par.toLowerCase().equals(p.toString()))
				return p;
		}
		return X264Subq.SIX;
	}

	private X264MEMEthods setjComboBoxmemethod(String par) {
		for (X264MEMEthods p : X264MEMEthods.values()) {
			if (par.toLowerCase().equals(p.toString()))
				return p;
		}
		return X264MEMEthods.NONE;
	}

	private X264Trellis setjComboBoxtrellis(String par) {
		for (X264Trellis p : X264Trellis.values()) {
			if (par.toLowerCase().equals(p.toString()))
				return p;
		}
		return X264Trellis.DISABLE;
	}

}
