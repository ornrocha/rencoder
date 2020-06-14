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
package pt.ornrocha.rencoder.gui.components.panels.info;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.gui.components.panels.auxiliar.StyleUtilities;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
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
public class AboutInformationPanel extends JDialog implements ActionListener {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;

	/** The j text pane infoauthor. */
	private JTextPane jTextPaneInfoauthor;

	/** The j tabbed paneinfos. */
	private JTabbedPane jTabbedPaneinfos;

	/** The j panellicense. */
	private JPanel jPanellicense;

	/** The j panelcredits. */
	private JPanel jPanelcredits;

	/** The j buttonclose. */
	private JButton jButtonclose;

	/** The j scroll panecredits. */
	private JScrollPane jScrollPanecredits;

	/** The rb. */
	private ResourceBundle rb;

	/** The j scroll panemain. */
	private JScrollPane jScrollPanemain;

	/** The j text panelicense. */
	private JTextPane jTextPanelicense;

	/** The j scroll panelicense. */
	private JScrollPane jScrollPanelicense;

	/** The j text panecredits. */
	private JTextPane jTextPanecredits;

	/** The closeaboutinfopanel. */
	private static String CLOSEABOUTINFOPANEL = "closeaboutinfopanel";

	/**
	 * Instantiates a new about information panel.
	 */
	public AboutInformationPanel() {
		rb = ResourceBundle.getBundle("lang", LangTools.getDefinedLanguage(), LangTools.loadLanguagesPath());
		initGUI();
		this.setModal(true);
		insertdatadevinfo();
		setlicenseText();
		setcreditsText();
	}

	/**
	 * Inits the gui.
	 */
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
				this.setTitle(LangTools.getResourceBundleWordLanguage(rb, "About Rencoder", "aboutgui.title"));
				this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/rencoderbig.png")));
				{
					jTabbedPaneinfos = new JTabbedPane();
					getContentPane().add(jTabbedPaneinfos, new GridBagConstraints(0, 7, 10, 8, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					{
						jPanellicense = new JPanel();
						GridBagLayout jPanellicenseLayout = new GridBagLayout();
						jTabbedPaneinfos.addTab(
								LangTools.getResourceBundleWordLanguage(rb, "License", "aboutgui.license"), null,
								jPanellicense, null);
						jPanellicenseLayout.rowWeights = new double[] { 0.1 };
						jPanellicenseLayout.rowHeights = new int[] { 7 };
						jPanellicenseLayout.columnWeights = new double[] { 0.1 };
						jPanellicenseLayout.columnWidths = new int[] { 7 };
						jPanellicense.setLayout(jPanellicenseLayout);
						{
							jScrollPanelicense = new JScrollPane();
							jPanellicense.add(jScrollPanelicense, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
									GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
							{
								jTextPanelicense = new JTextPane();
								jScrollPanelicense.setViewportView(jTextPanelicense);
								jTextPanelicense.setText("");
							}
						}
					}
					{
						jPanelcredits = new JPanel();
						GridBagLayout jPanelcreditsLayout = new GridBagLayout();
						jTabbedPaneinfos.addTab(
								LangTools.getResourceBundleWordLanguage(rb, "Credits", "aboutgui.credits"), null,
								jPanelcredits, null);
						jPanelcreditsLayout.rowWeights = new double[] { 0.1 };
						jPanelcreditsLayout.rowHeights = new int[] { 7 };
						jPanelcreditsLayout.columnWeights = new double[] { 0.1 };
						jPanelcreditsLayout.columnWidths = new int[] { 7 };
						jPanelcredits.setLayout(jPanelcreditsLayout);
						{
							jScrollPanecredits = new JScrollPane();
							jPanelcredits.add(jScrollPanecredits, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
									GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
							{
								jTextPanecredits = new JTextPane();
								jScrollPanecredits.setViewportView(jTextPanecredits);
								jTextPanecredits.setText("");
							}
						}
					}
				}
				{
					jButtonclose = new JButton();
					getContentPane().add(jButtonclose, new GridBagConstraints(3, 15, 4, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jButtonclose.setText(LangTools.getResourceBundleWordLanguage(rb, "Close", "general.close"));
					jButtonclose.setActionCommand(CLOSEABOUTINFOPANEL);
					jButtonclose.addActionListener(this);
				}
				{
					jScrollPanemain = new JScrollPane();
					getContentPane().add(jScrollPanemain, new GridBagConstraints(0, 0, 10, 7, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					{
						jTextPaneInfoauthor = new JTextPane();
						jScrollPanemain.setViewportView(jTextPaneInfoauthor);
						jTextPaneInfoauthor.setText("");
					}
				}
			}
			{
				this.setSize(551, 476);
			}
		} catch (Exception e) {
		    Logger.error(e);
		}
	}

	/**
	 * Insert data developer info.
	 */
	private void insertdatadevinfo() {
		try {

			StyleContext context = new StyleContext();
			StyledDocument devinfo = new DefaultStyledDocument(context);
			jTextPaneInfoauthor.setStyledDocument(devinfo);

			SimpleAttributeSet alignm = new SimpleAttributeSet();
			StyleConstants.setAlignment(alignm, StyleConstants.ALIGN_CENTER);
			devinfo.setParagraphAttributes(0, devinfo.getLength(), alignm, false);

			Style boldmod1 = StyleUtilities.getBoldModsizeandcolor(devinfo, "mod1", 16, Color.BLUE);
			insertdevinfoIcon(devinfo);
			devinfo.insertString(devinfo.getLength(), "\n", StyleUtilities.getDefaultStyle(devinfo));
			//devinfo.insertString(devinfo.getLength(), "ORSimpleVideoConverter" + "\n", boldmod1);
			devinfo.insertString(devinfo.getLength(), "Version 1.1.0" + "\n\n",
					StyleUtilities.getRegularDefaultStyle(devinfo));

			String text = "Rencoder is a FFmpeg frontend developed in Java language.\n";
			devinfo.insertString(devinfo.getLength(), text,
					StyleUtilities.getRegularModsizeandcolor(devinfo, "regmod1", 14, Color.BLACK));
			devinfo.insertString(devinfo.getLength(), "Copyright \u00a9 2014-2020, Orlando Rocha <ornrocha@gmail.com>",
					StyleUtilities.getRegularModsizeandcolor(devinfo, "regmod2", 11, Color.BLUE));
			jTextPaneInfoauthor.setEditable(false);
		} catch (BadLocationException e) {
		    Logger.error(e);
		}
	}

	/**
	 * Insert developer info icon.
	 *
	 * @param doc the doc
	 * @throws BadLocationException the bad location exception
	 */
	private void insertdevinfoIcon(StyledDocument doc) throws BadLocationException {

		Style styleicon = doc.addStyle("Styleicon", null);
		StyleConstants.setIcon(styleicon, new ImageIcon(ListFiles.getIconAbsolutePath("icons/rencoderbig.png")));
		doc.insertString(0, "ignored text", styleicon);

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

		if (cmd.equals(CLOSEABOUTINFOPANEL))
			this.dispose();

	}

	/**
	 * Set license text.
	 */
	private void setlicenseText() {

		try {
			StyleContext context = new StyleContext();
			StyledDocument licinfo = new DefaultStyledDocument(context);
			jTextPanelicense.setStyledDocument(licinfo);
			SimpleAttributeSet alignfig = new SimpleAttributeSet();
			StyleConstants.setAlignment(alignfig, StyleConstants.ALIGN_CENTER);

			Style styleicon = licinfo.addStyle("Styleicon", null);
			StyleConstants.setIcon(styleicon, new ImageIcon(ListFiles.getIconAbsolutePath("icons/gplv3-127x51.png")));
			licinfo.insertString(0, "ignored text", styleicon);
			licinfo.setParagraphAttributes(0, licinfo.getLength(), alignfig, false);

			SimpleAttributeSet alignm = new SimpleAttributeSet();
			StyleConstants.setAlignment(alignm, StyleConstants.ALIGN_JUSTIFIED);
			licinfo.insertString(licinfo.getLength(), "\n\n", StyleUtilities.getDefaultStyle(licinfo));

			int fig = licinfo.getLength();

			String txt = "Rencoder is free software: you can redistribute it and/or modify "
					+ "it under the terms of the GNU General Public License as published by the Free "
					+ "Software Foundation, either version 3 of the License, or (at your option) any later version."
					+ "\n\n"
					+ "Rencoder is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; "
					+ "without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the "
					+ "GNU General Public License for more details." + "\n\n"
					+ "You should have received a copy of the GNU General Public License along with this program.  If not, see "
					+ "<http://www.gnu.org/licenses/>.";

			licinfo.insertString(licinfo.getLength(), txt, StyleUtilities.getRegularDefaultStyle(licinfo));
			licinfo.setParagraphAttributes(fig + 1, txt.length(), alignm, false);

			jTextPanelicense.setEditable(false);
		} catch (BadLocationException e) {
		    Logger.error(e);
		}

	}

	/**
	 * Setcredits text.
	 */
	private void setcreditsText() {

		try {
			StyleContext context = new StyleContext();
			StyledDocument credinfo = new DefaultStyledDocument(context);
			jTextPanecredits.setStyledDocument(credinfo);
			SimpleAttributeSet aligncenter = new SimpleAttributeSet();
			StyleConstants.setAlignment(aligncenter, StyleConstants.ALIGN_CENTER);

			Style boldmod1 = StyleUtilities.getBoldModsizeandcolor(credinfo, "boldmod1", 18, Color.BLACK);
			Style boldmod2 = StyleUtilities.getBoldModsizeandcolor(credinfo, "boldmod2", 16, Color.BLACK);
			Style boldmod3 = StyleUtilities.getBoldModsizeandcolor(credinfo, "boldmod3", 14, Color.BLACK);
			Style regularmod1 = StyleUtilities.getRegularModsizeandcolor(credinfo, "regmod1", 14, Color.BLUE);

			credinfo.insertString(0, "Third-party components" + "\n\n", boldmod1);
			credinfo.insertString(credinfo.getLength(), "Icons: " + "\n", boldmod2);
			credinfo.insertString(credinfo.getLength(), "Main interface icons: developed by brsev (Token icons)" + "\n",
					regularmod1);
			credinfo.insertString(credinfo.getLength(), "http://brsev.deviantart.com/" + "\n\n", regularmod1);
			credinfo.insertString(credinfo.getLength(), "Uninstall icons: developed by Visual Pharm" + "\n",
					regularmod1);
			credinfo.insertString(credinfo.getLength(), "Under creative commons license" + "\n", regularmod1);
			credinfo.insertString(credinfo.getLength(), "http://creativecommons.org/licenses/by-nd/3.0/" + "\n\n",
					regularmod1);
			credinfo.insertString(credinfo.getLength(), "Exit icon 2: developed by Mandarancio" + "\n", regularmod1);
			credinfo.insertString(credinfo.getLength(), "http://mandarancio.deviantart.com/" + "\n", regularmod1);
			credinfo.insertString(credinfo.getLength(), "Under Creative Commons Attribution Non-commercial" + "\n\n",
					regularmod1);
			credinfo.insertString(credinfo.getLength(), "Restart icon: developed by Designerzbase" + "\n", regularmod1);
			credinfo.insertString(credinfo.getLength(), "https://www.iconfinder.com/Designerzbase" + "\n\n",
					regularmod1);

			credinfo.insertString(credinfo.getLength(), "Look and Feel: " + "\n\n", boldmod2);

			credinfo.insertString(credinfo.getLength(), "Seaglass" + "\n", boldmod3);
			credinfo.insertString(credinfo.getLength(), "Licensed under terms of the Apache Software License 2" + "\n",
					regularmod1);
			credinfo.insertString(credinfo.getLength(), "https://code.google.com/p/seaglass/" + "\n\n", regularmod1);

			credinfo.insertString(credinfo.getLength(), "JTattoo" + "\n", boldmod3);
			credinfo.insertString(credinfo.getLength(), "Developed by MH Software-Entwicklung" + "\n", regularmod1);
			credinfo.insertString(credinfo.getLength(),
					"Licensed under the terms and conditions of the GNU General Public License version 2.0" + "\n",
					regularmod1);
			credinfo.insertString(credinfo.getLength(), "http://www.jtattoo.net" + "\n\n", regularmod1);

			credinfo.insertString(credinfo.getLength(), "TinyLaF" + "\n", boldmod3);
			credinfo.insertString(credinfo.getLength(), "Developed by Hans Bickel" + "\n", regularmod1);
			credinfo.insertString(credinfo.getLength(), "Licensed under the GNU Lesser General Public License" + "\n",
					regularmod1);
			credinfo.insertString(credinfo.getLength(), "http://www.muntjak.de/hans/java/tinylaf/" + "\n\n",
					regularmod1);

			credinfo.insertString(credinfo.getLength(), "PgsLookAndFeel" + "\n", boldmod3);
			credinfo.insertString(credinfo.getLength(), "Copyright \u00a9 by Patrick Gotthardt" + "\n", regularmod1);
			credinfo.insertString(credinfo.getLength(), "Licensed under terms of the Apache Software License 2" + "\n",
					regularmod1);
			credinfo.insertString(credinfo.getLength(), "http://www.pagosoft.com/projects/pgslookandfeel/" + "\n\n",
					regularmod1);

			credinfo.insertString(credinfo.getLength(), "WebLaF" + "\n", boldmod3);
			credinfo.insertString(credinfo.getLength(), "Developed by Mikle Garin" + "\n", regularmod1);
			credinfo.insertString(credinfo.getLength(), "Licensed under the GNU Lesser General Public License" + "\n",
					regularmod1);
			credinfo.insertString(credinfo.getLength(), "http://weblookandfeel.com/" + "\n\n", regularmod1);

			credinfo.insertString(credinfo.getLength(), "Substance" + "\n", boldmod3);
			credinfo.insertString(credinfo.getLength(),
					"Developed originally by Kirill Grouchnikov's and supported by many others contributors" + "\n",
					regularmod1);
			credinfo.insertString(credinfo.getLength(),
					"Licensed under Berkeley Software Distribution (BSD) License" + "\n", regularmod1);
			credinfo.insertString(credinfo.getLength(),
					"http://insubstantial.github.io/insubstantial/substance/" + "\n\n", regularmod1);

			credinfo.insertString(credinfo.getLength(), "LiquidLnF" + "\n", boldmod3);
			credinfo.insertString(credinfo.getLength(), "Developed originally by mikeai and xendren" + "\n",
					regularmod1);
			credinfo.insertString(credinfo.getLength(),
					"Licensed under the GNU Lesser General Public License (LGPLv2)" + "\n", regularmod1);
			credinfo.insertString(credinfo.getLength(), "http://sourceforge.net/projects/liquidlnf/" + "\n\n",
					regularmod1);

			credinfo.insertString(credinfo.getLength(), "Software: " + "\n\n", boldmod2);
			credinfo.insertString(credinfo.getLength(), "SteelCheckBox" + "\n", boldmod3);
			credinfo.insertString(credinfo.getLength(),
					"http://harmoniccode.blogspot.pt/2010/11/friday-fun-component-iii.html" + "\n\n", regularmod1);

			credinfo.insertString(credinfo.getLength(), "Apache Commons" + "\n", boldmod3);
			credinfo.insertString(credinfo.getLength(), "Developed by Apache Software Foundation" + "\n", regularmod1);
			credinfo.insertString(credinfo.getLength(), "http://commons.apache.org/" + "\n\n", regularmod1);

			credinfo.insertString(credinfo.getLength(), "vlcj" + "\n", boldmod3);
			credinfo.insertString(credinfo.getLength(), "Developed by capricasoftware" + "\n", regularmod1);
			credinfo.insertString(credinfo.getLength(), "http://capricasoftware.co.uk/#/projects/vlcj" + "\n\n",
					regularmod1);

			credinfo.insertString(credinfo.getLength(), "FFmpeg" + "\n", boldmod3);
			credinfo.insertString(credinfo.getLength(),
					"Licensed under the GNU Lesser General Public License (LGPL) version 2.1 or later", regularmod1);
			credinfo.insertString(credinfo.getLength(), "https://www.ffmpeg.org/" + "\n\n", regularmod1);


			credinfo.setParagraphAttributes(0, credinfo.getLength(), aligncenter, false);
			jTextPanecredits.setEditable(false);

		} catch (BadLocationException e) {
		    Logger.error(e);
		}
	}

}
