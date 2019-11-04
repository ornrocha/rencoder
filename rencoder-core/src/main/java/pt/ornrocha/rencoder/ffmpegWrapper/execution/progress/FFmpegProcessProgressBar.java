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
package pt.ornrocha.rencoder.ffmpegWrapper.execution.progress;

import java.awt.Color;
import java.awt.Component;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import pt.ornrocha.rencoder.helpers.lang.LangTools;

// TODO: Auto-generated Javadoc
/**
 * The Class FFmpegProcessProgressBar.
 */
public class FFmpegProcessProgressBar extends JProgressBar implements TableCellRenderer {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The limit values. */
	private int[] limitValues;

	/** The limit colors. */
	private Hashtable limitColors;

	/** The rb. */
	private ResourceBundle rb;

	// private static String
	// builtfontstag=AuxiliarStaticMethods.getCurrentDir()+OSystem.getSystemSeparator()+"ffmpegfolder"+OSystem.getSystemSeparator()+".lockfonts";

	/**
	 * Instantiates a new f fmpeg process progress bar.
	 */
	public FFmpegProcessProgressBar() {
		rb = ResourceBundle.getBundle("lang", LangTools.getDefinedLanguage(), LangTools.loadLanguagesPath());
		setLimitColors();
		this.setStringPainted(true);
		this.setBorderPainted(false);
		this.setMinimum(0);
		this.setMaximum(100);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing
	 * .JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		int progressint = 0;
		double progressdouble = 0.0;
		String progresslabel = "";

		if (value instanceof String) {
			progresslabel = String.valueOf(value);
			return new JLabel(progresslabel);
		}

		if (value instanceof Integer)
			progressint = (int) value;

		if (value instanceof Double) {
			progressdouble = (double) value;
			progressint = (int) progressdouble;
		}

		if (progressdouble > 0 && progressdouble < 100) {
			Color color = getColor((int) progressdouble);
			if (color != null) {
				setForeground(color);
			}
			setValue((int) progressdouble);
			return this;
		}

		else if (progressint == 100) {

			return new JLabel(LangTools.getResourceBundleWordLanguage(rb, "Completed", "progbar.done"));
		} else
			return new JLabel(LangTools.getResourceBundleWordLanguage(rb, "Waiting...", "progbar.waiting"));

	}

	/**
	 * Sets the limit colors.
	 */
	private void setLimitColors() {
		Hashtable limitColors = new Hashtable();
		limitColors.put(new Integer(0), Color.red);
		limitColors.put(new Integer(60), Color.yellow);
		limitColors.put(new Integer(80), Color.green);
		this.setLimits(limitColors);

	}

	/**
	 * Sets the limits.
	 *
	 * @param limitColors the new limits
	 */
	public void setLimits(Hashtable limitColors) {
		this.limitColors = limitColors;
		int i = 0;
		int n = limitColors.size();
		limitValues = new int[n];
		Enumeration e = limitColors.keys();
		while (e.hasMoreElements()) {
			limitValues[i++] = ((Integer) e.nextElement()).intValue();
		}
		sort(limitValues);
	}

	/**
	 * Sort.
	 *
	 * @param a the a
	 */
	private void sort(int[] a) {
		int n = a.length;
		for (int i = 0; i < n - 1; i++) {
			int k = i;
			for (int j = i + 1; j < n; j++) {
				if (a[j] < a[k]) {
					k = j;
				}
			}
			int tmp = a[i];
			a[i] = a[k];
			a[k] = tmp;
		}
	}

	/**
	 * Gets the color.
	 *
	 * @param value the value
	 * @return the color
	 */
	private Color getColor(int value) {
		Color color = null;
		if (limitValues != null) {
			int i;
			for (i = 0; i < limitValues.length; i++) {
				if (limitValues[i] < value) {
					color = (Color) limitColors.get(new Integer(limitValues[i]));
				}
			}
		}
		return color;
	}

}