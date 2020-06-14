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
package pt.ornrocha.rencoder.gui.components.panels.configurations;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.gui.components.panels.auxiliar.Looktypes;
import pt.ornrocha.rencoder.gui.components.panels.auxiliar.StaticGuiFieldNames;
import pt.ornrocha.rencoder.gui.components.panels.auxiliar.StyleUtilities;
import pt.ornrocha.rencoder.gui.components.panels.info.RestartWarnPanel;
import pt.ornrocha.rencoder.gui.execute.RestartRencoder;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.helpers.osystem.OS;
import pt.ornrocha.rencoder.helpers.osystem.OSystem;
import pt.ornrocha.rencoder.helpers.props.fields.StaticGlobalFields;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;

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
public class LookPanelGui extends JDialog {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The j buttonok. */
	private JButton jButtonok;

	/** The j buttoncancel. */
	private JButton jButtoncancel;

	/** The j combo boxlook. */
	private JComboBox jComboBoxlook;

	/** The j combo boxlang. */
	private JComboBox jComboBoxlang;

	/** The j labelcountry. */
	private JLabel jLabelcountry;

	/** The j check boxtooltipson. */
	private JCheckBox jCheckBoxtooltipson;

	/** The rb. */
	private ResourceBundle rb;

	/** The locales. */
	private ArrayList<Locale> locales = null;

	/** The Constant LOOK_PANEL_CANCEL. */
	public static final String LOOK_PANEL_CANCEL = "look_cancel";

	/** The Constant LOOK_PANEL_OK. */
	public static final String LOOK_PANEL_OK = "look_ok";

	/** The Constant LOOK_PANEL_TIPS_CHANGE. */
	public static final String LOOK_PANEL_TIPS_CHANGE = "tips_change";

	private RestartRencoder restart = null;

	private boolean dorestart = true;
	private boolean lookchanged = false;

	private RestartWarnPanel restartpanel = null;

	private JFrame parent;

	/**
	 * Instantiates a new look panel gui.
	 */
	public LookPanelGui(JFrame parent) {
		this.parent = parent;
		rb = ResourceBundle.getBundle("lang", LangTools.getDefinedLanguage(), LangTools.loadLanguagesPath());
		locales = LangTools.getListAvailableLocales();
		initGUI();
		this.setModal(true);
		populateCombobox();
		jCheckBoxtooltipson.setSelected(checktipsState());
		setLocalesInCombobox();
	}

	/**
	 * Inits the gui.
	 */
	private void initGUI() {
		try {
			{
				GridBagLayout thisLayout = new GridBagLayout();
				thisLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1 };
				thisLayout.rowHeights = new int[] { 7, 7, 7, 7, 7, 7 };
				thisLayout.columnWeights = new double[] { 0.1, 0.1, 0.1, 0.1 };

				thisLayout.columnWidths = new int[] { 7, 7, 7, 7 };
				getContentPane().setLayout(thisLayout);
				this.setTitle(LangTools.getResourceBundleWordLanguage(rb, "Look settings", "profilegui.lookpanel"));
				this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/rencoderbig.png")));
				{
					jButtonok = new JButton();
					getContentPane().add(jButtonok, new GridBagConstraints(2, 5, 2, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jButtonok.setText(LangTools.getResourceBundleWordLanguage(rb, "Ok", "general.ok"));
					jButtonok.setActionCommand(LOOK_PANEL_OK);
				}
				{
					jButtoncancel = new JButton();
					getContentPane().add(jButtoncancel, new GridBagConstraints(0, 5, 2, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jButtoncancel.setText(LangTools.getResourceBundleWordLanguage(rb, "Cancel", "general.cancel"));
					jButtoncancel.setActionCommand(LOOK_PANEL_CANCEL);
				}
				{

					jComboBoxlook = new JComboBox();
					getContentPane().add(jComboBoxlook, new GridBagConstraints(0, 1, 4, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jComboBoxlook.setToolTipText(LangTools.getResourceBundleWordLanguage(rb, "Choose one of the themes",
							"lookfeelgui.theme"));

				}
				{
					jCheckBoxtooltipson = new JCheckBox();
					getContentPane().add(jCheckBoxtooltipson, new GridBagConstraints(0, 2, 4, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 20, 0, 0), 0, 0));
					jCheckBoxtooltipson.setText(LangTools.getResourceBundleWordLanguage(rb,
							"Show tips in Graphical Interface", "lookfeelgui.tips"));
					jCheckBoxtooltipson.setActionCommand(LOOK_PANEL_TIPS_CHANGE);
				}
				{

					jComboBoxlang = new JComboBox();
					getContentPane().add(jComboBoxlang, new GridBagConstraints(1, 3, 3, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

				}
				{
					jLabelcountry = new JLabel();
					getContentPane().add(jLabelcountry, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 20, 0, 0), 0, 0));
					jLabelcountry
							.setText(LangTools.getResourceBundleWordLanguage(rb, "Language", "profilegui.country"));
				}
			}
			{
				this.setSize(412, 157);
			}
		} catch (Exception e) {
		    Logger.error(e);
		}
	}

	/**
	 * Populate combobox.
	 */
	private void populateCombobox() {
		ArrayList<Looktypes> list = new ArrayList<>();

		OS opersys = OSystem.getOperationSystem();
		for (Looktypes type : Looktypes.values()) {
			if (type.getNotSupportedOS() == null) {
				list.add(type);
			} else {
				if (!type.getNotSupportedOS().contains(opersys))
					list.add(type);
			}
		}

		Looktypes[] input = list.toArray(new Looktypes[list.size()]);

		jComboBoxlook.setModel(new DefaultComboBoxModel(input));
		setDefinedLooktype();
	}

	/**
	 * Sets the defined look type.
	 */
	private void setDefinedLooktype() {

		PropertiesConfiguration prop = PropertiesWorker.loadPropertiesRelativePath(StaticGlobalFields.RENCODERCONFIGFILE);
		String look = (String) prop.getProperty(StaticGuiFieldNames.fileLookandFeelPropkey);
		Looktypes definedlook = StyleUtilities.getLookAndFeel(look);
		jComboBoxlook.setSelectedItem(definedlook);
	}

	/**
	 * Adds the listeners.
	 *
	 * @param listener the listener
	 */
	public void addListeners(ActionListener listener) {
		jButtoncancel.addActionListener(listener);
		jButtonok.addActionListener(listener);
		jCheckBoxtooltipson.addActionListener(listener);
	}

	/**
	 * Close.
	 */
	public void close() {
		this.dispose();
	}

	public boolean doRestart() {
		return dorestart;
	}

	/**
	 * Sets the look.
	 *
	 * @param mainframe the new look
	 */
	public void setLook(JFrame mainframe) {

		try {
			PropertiesConfiguration prop = PropertiesWorker
					.loadPropertiesRelativePath(StaticGlobalFields.RENCODERCONFIGFILE);
			String look = (String) prop.getProperty(StaticGuiFieldNames.fileLookandFeelPropkey);

			String value = ((Looktypes) jComboBoxlook.getSelectedItem()).getType();

			if (!look.equals(value)) {
				UIManager.setLookAndFeel(value);
				Looktypes looktype = StyleUtilities.getLookAndFeel(value);
				if (looktype.haveSkinTag())
					looktype.setSkin();
				PropertiesWorker.ChangePropertiesParam(StaticGlobalFields.RENCODERCONFIGFILE,
						StaticGuiFieldNames.fileLookandFeelPropkey, value);
				SwingUtilities.updateComponentTreeUI(mainframe);
				mainframe.pack();
				lookchanged = true;
			} else
				lookchanged = false;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
		    Logger.error(e);
		}
		this.dispose();

	}

	/**
	 * Check tips state.
	 *
	 * @return true, if successful
	 */
	private boolean checktipsState() {
		PropertiesConfiguration prop = PropertiesWorker.loadPropertiesRelativePath(StaticGlobalFields.RENCODERCONFIGFILE);

		String tipstate = (String) prop.getProperty(StaticGuiFieldNames.GuiToolTips);
		if (tipstate.toLowerCase().equals("true"))
			return true;
		else if (tipstate.toLowerCase().equals("false"))
			return false;
		else
			return true;

	}

	/**
	 * Sets the new tips state.
	 */
	public void setNewTipsState() {
		String state = String.valueOf(jCheckBoxtooltipson.isSelected());
		PropertiesWorker.ChangePropertiesParam(StaticGlobalFields.RENCODERCONFIGFILE, StaticGuiFieldNames.GuiToolTips,
				state);
		ToolTipManager.sharedInstance().setEnabled(jCheckBoxtooltipson.isSelected());

	}

	/**
	 * Sets the locales in combobox.
	 */
	private void setLocalesInCombobox() {

		String[] countries = new String[locales.size()];

		int n = 0;
		for (Locale loc : locales) {
			countries[n] = loc.getDisplayCountry();
			n++;
		}

		if (countries.length > 0)
			jComboBoxlang.setModel(new DefaultComboBoxModel(countries));

		Locale currentloc = LangTools.getDefinedLanguage();

		if (countries.length > 0)
			jComboBoxlang.setSelectedItem(currentloc.getDisplayCountry());
	}

	/**
	 * Change locale.
	 */
	public void changelocale() {

		Locale selectedlocale = locales.get(jComboBoxlang.getSelectedIndex());
		String newcountry = selectedlocale.toString();

		PropertiesConfiguration propcountry = PropertiesWorker
				.loadPropertiesRelativePath(StaticGlobalFields.RENCODERCONFIGFILE);
		String selectedcountry = PropertiesWorker.checkProperty(propcountry, StaticGlobalFields.COUNTRY);

		if (selectedcountry != null) {
			if (!selectedcountry.equalsIgnoreCase(newcountry)) {
				PropertiesWorker.ChangePropertiesParam(StaticGlobalFields.RENCODERCONFIGFILE, StaticGlobalFields.COUNTRY,
						newcountry);
				dorestart = true;
			} else if (selectedcountry.equalsIgnoreCase(newcountry) && !lookchanged)
				dorestart = false;
			else if (selectedcountry.equalsIgnoreCase(newcountry) && lookchanged)
				dorestart = true;
		}
	}

	public void setRestarter(RestartRencoder restart) {
		this.restart = restart;
	}

}
