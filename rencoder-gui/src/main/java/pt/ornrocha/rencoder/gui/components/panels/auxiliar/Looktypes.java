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
package pt.ornrocha.rencoder.gui.components.panels.auxiliar;

import java.util.Arrays;
import java.util.HashSet;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;

import pt.ornrocha.rencoder.helpers.osystem.OS;

// TODO: Auto-generated Javadoc
/**
 * The Enum Looktypes.
 */
public enum Looktypes {

	/** The metal look and feel */
	METAL {

		@Override
		public String getType() {
			return StaticGuiFieldNames.metallook;
		}

		@Override
		public String toString() {
			return "Metal";
		}

		@Override
		public HashSet<OS> getNotSupportedOS() {
			return null;
		}

		@Override
		public boolean haveSkinTag() {
			return false;
		}

	},

	/** The nimbus look and feel */
	NIMBUS {

		@Override
		public String getType() {
			return StaticGuiFieldNames.nimbuslook;
		}

		@Override
		public String toString() {
			return "Nimbus";
		}

		@Override
		public HashSet<OS> getNotSupportedOS() {
			return null;
		}

		@Override
		public boolean haveSkinTag() {
			return false;
		}

	},

	/** The seaglass look and feel */
	SEAGLASS {

		@Override
		public String getType() {
			return StaticGuiFieldNames.seaglass;
		}

		@Override
		public String toString() {
			return "Seaglass";
		}

		@Override
		public HashSet<OS> getNotSupportedOS() {
			return new HashSet<OS>(Arrays.asList(OS.MACOS, OS.WIN32, OS.WIN64));
		}

		@Override
		public boolean haveSkinTag() {
			return false;
		}

	},
	MaterialUI {

		@Override
		public String getType() {
			return StaticGuiFieldNames.materialui;
		}

		@Override
		public String toString() {
			return "Material UI";
		}

		@Override
		public HashSet<OS> getNotSupportedOS() {
			return new HashSet<OS>(Arrays.asList(OS.MACOS, OS.WIN32));
		}

		@Override
		public boolean haveSkinTag() {
			return false;
		}

	},
	/*
	 * Submin {
	 * 
	 * @Override public String getType() { return StaticGuiFieldNames.submin; }
	 * 
	 * @Override public String toString() { return "Submin"; }
	 * 
	 * @Override public HashSet<OS> getNotSupportedOS() { return new
	 * HashSet<OS>(Arrays.asList(OS.MACOS, OS.WIN32)); }
	 * 
	 * @Override public boolean haveSkinTag() { return false; }
	 * 
	 * },
	 */

	/** The weblaf look and feel */

	WEBLAF {

		@Override
		public String getType() {
			return StaticGuiFieldNames.weblaf;
		}

		@Override
		public String toString() {
			return "WebLaf";
		}

		@Override
		public HashSet<OS> getNotSupportedOS() {
			return null;
		}

		@Override
		public boolean haveSkinTag() {
			return false;
		}

	},

	/** The jtattoo look and feel */
//	JTATTOO {
//
//		@Override
//		public String getType() {
//			return StaticGuiFieldNames.jtatoo;
//		}
//
//		@Override
//		public String toString() {
//			return "JTattoo";
//		}
//
//		@Override
//		public HashSet<OS> getNotSupportedOS() {
//			return null;
//		}
//
//		@Override
//		public boolean haveSkinTag() {
//			return false;
//		}
//
//	},

	TINYLAF {

		@Override
		public String getType() {
			return StaticGuiFieldNames.tinylaf;
		}

		@Override
		public String toString() {
			return "TinyLaF";
		}

		@Override
		public HashSet<OS> getNotSupportedOS() {
			return null;
		}

		@Override
		public boolean haveSkinTag() {
			return false;
		}

	},

	PGSLOOKANDFEEL {

		@Override
		public String getType() {
			return StaticGuiFieldNames.PgsLookAndFeel;
		}

		@Override
		public String toString() {
			return "PgsLookAndFeel";
		}

		@Override
		public HashSet<OS> getNotSupportedOS() {
			return null;
		}

		@Override
		public boolean haveSkinTag() {
			return false;
		}

	},

	/** The jgoodiesxp look and feel */
	JGOODIESXP {

		@Override
		public String getType() {
			return StaticGuiFieldNames.jgoogiesxp;
		}

		@Override
		public String toString() {
			return "Goodies xp";
		}

		@Override
		public HashSet<OS> getNotSupportedOS() {
			return null;
		}

		@Override
		public boolean haveSkinTag() {
			return false;
		}

	},

	/** The jgoodies look and feel */
	JGOODIES {

		@Override
		public String getType() {
			return StaticGuiFieldNames.jgoogies3D;
		}

		@Override
		public String toString() {
			return "Goodies 3D";
		}

		@Override
		public HashSet<OS> getNotSupportedOS() {
			return null;
		}

		@Override
		public boolean haveSkinTag() {
			return false;
		}

	},

	/** The motif look and feel */
//	MOTIF {
//
//		@Override
//		public String getType() {
//			return StaticGuiFieldNames.motiflook;
//		}
//
//		@Override
//		public String toString() {
//			return "Motif";
//		}
//
//		@Override
//		public HashSet<OS> getNotSupportedOS() {
//			return null;
//		}
//
//		@Override
//		public boolean haveSkinTag() {
//			return false;
//		}
//
//	},

	/*
	 * LiquidLnF{
	 * 
	 * 
	 * public String getType(){ return ConstantsProperties.LiquidLnF; }
	 * 
	 * public String toString(){ return "LiquidLnF"; }
	 * 
	 * public HashSet<OS> getNotSupportedOS(){ return null; }
	 * 
	 * public boolean haveSkinTag(){ return false; }
	 * 
	 * 
	 * 
	 * },
	 */

	SUBSTANCENEBULA {

		@Override
		public String getType() {
			return "org.pushingpixels.substance.api.skin.SubstanceNebulaLookAndFeel";
			//return "org.pushingpixels.substance.api.skin.SubstanceGraphiteGlassLookAndFeel";
		}

		@Override
		public String toString() {
			return "Substance Nebula";
		}

		@Override
		public HashSet<OS> getNotSupportedOS() {
			return null;
		}

		@Override
		public boolean haveSkinTag() {
			return true;
		}

		@Override
		public void setSkin() {
			SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.NebulaSkin");
			//SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.GraphiteGlassSkin");
		}

	},

	SUBSTANCEDUST {

		@Override
		public String getType() {
			return "org.pushingpixels.substance.api.skin.SubstanceDustLookAndFeel";
		}

		@Override
		public String toString() {
			return "Substance Dust";
		}

		@Override
		public HashSet<OS> getNotSupportedOS() {
			return null;
		}

		@Override
		public boolean haveSkinTag() {
			return true;
		}

		@Override
		public void setSkin() {
			SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.SubstanceDustLookAndFeel");
		}

	},

	SUBSTANCEMARINER {

		@Override
		public String getType() {
			return "org.pushingpixels.substance.api.skin.SubstanceMarinerLookAndFeel";
		}

		@Override
		public String toString() {
			return "Substance Mariner";
		}

		@Override
		public HashSet<OS> getNotSupportedOS() {
			return null;
		}

		@Override
		public boolean haveSkinTag() {
			return true;
		}

		@Override
		public void setSkin() {
			SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.MarinerSkin");
		}

	},

	SUBSTANCEOFFICESTYLE {

		@Override
		public String getType() {
			return "org.pushingpixels.substance.api.skin.SubstanceOfficeBlue2007LookAndFeel";
		}

		@Override
		public String toString() {
			return "Substance Office Style";
		}

		@Override
		public HashSet<OS> getNotSupportedOS() {
			return null;
		}

		@Override
		public boolean haveSkinTag() {
			return true;
		}

		@Override
		public void setSkin() {
			SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.OfficeBlue2007Skin");
		}

	},

	SUBSTANCEGEMINI {

		@Override
		public String getType() {
			return "org.pushingpixels.substance.api.skin.SubstanceGeminiLookAndFeel";
		}

		@Override
		public String toString() {
			return "Substance Gemini";
		}

		@Override
		public HashSet<OS> getNotSupportedOS() {
			return null;
		}

		@Override
		public boolean haveSkinTag() {
			return true;
		}

		@Override
		public void setSkin() {
			SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.GeminiSkin");
		}

	},

	SUBSTANCECREME {

		@Override
		public String getType() {
			return "org.pushingpixels.substance.api.skin.SubstanceCremeLookAndFeel";
		}

		@Override
		public String toString() {
			return "Substance Creme";
		}

		@Override
		public HashSet<OS> getNotSupportedOS() {
			return null;
		}

		@Override
		public boolean haveSkinTag() {
			return true;
		}

		@Override
		public void setSkin() {
			SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.CremeSkin");
		}

	},

	SUBSTANCESAHARA {

		@Override
		public String getType() {
			return "org.pushingpixels.substance.api.skin.SubstanceSaharaLookAndFeel";
		}

		@Override
		public String toString() {
			return "Substance Sahara";
		}

		@Override
		public HashSet<OS> getNotSupportedOS() {
			return null;
		}

		@Override
		public boolean haveSkinTag() {
			return true;
		}

		@Override
		public void setSkin() {
			SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.SaharaSkin");
		}

	},

	SUBSTANCEMISTAQUA {

		@Override
		public String getType() {
			return "org.pushingpixels.substance.api.skin.SubstanceMistAquaLookAndFeel";
		}

		@Override
		public String toString() {
			return "Substance Mist Aqua";
		}

		@Override
		public HashSet<OS> getNotSupportedOS() {
			return null;
		}

		@Override
		public boolean haveSkinTag() {
			return true;
		}

		@Override
		public void setSkin() {
			SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.MistAquaSkin");
		}

	};

	/**
	 * Gets the type of look and feel.
	 *
	 * @return the type
	 */
	public String getType() {
		return this.getType();
	}

	public HashSet<OS> getNotSupportedOS() {
		return getNotSupportedOS();
	}

	public boolean haveSkinTag() {
		return haveSkinTag();
	}

	public void setSkin() {

	}

}
