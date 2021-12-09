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

import javax.swing.LookAndFeel;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;

import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.JMarsDarkTheme;
import mdlaf.themes.MaterialLiteTheme;
import mdlaf.themes.MaterialOceanicTheme;
import pt.ornrocha.rencoder.helpers.osystem.OS;

// TODO: Auto-generated Javadoc
/**
 * The Enum Looktypes.
 */
public enum Looktypes {

	/** The metal look and feel */
	METAL {

		@Override
		public String getNamespace() {
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
		public boolean usesSkin() {
			return false;
		}
		
		@Override
		public boolean usesTheme() {
			return false;
		}

	},

	/** The nimbus look and feel */
	NIMBUS {

		@Override
		public String getNamespace() {
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
		public boolean usesSkin() {
			return false;
		}
		
		@Override
		public boolean usesTheme() {
			return false;
		}

	},

	/** The seaglass look and feel */
	SEAGLASS {

		@Override
		public String getNamespace() {
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
		public boolean usesSkin() {
			return false;
		}
		
		@Override
		public boolean usesTheme() {
			return false;
		}

	},
	
	MaterialLiteTheme {

		@Override
		public String getNamespace() {
			return StaticGuiFieldNames.materialui;
		}

		@Override
		public String toString() {
			return "Material Lite";
		}

		@Override
		public HashSet<OS> getNotSupportedOS() {
			return new HashSet<OS>(Arrays.asList(OS.MACOS, OS.WIN32));
		}

		@Override
		public boolean usesSkin() {
			return false;
		}
		
		@Override
		public boolean usesTheme() {
			return true;
		}
		
		@Override
		public LookAndFeel getLookAndFeel() {
			return new MaterialLookAndFeel(new MaterialLiteTheme());
		}

		

	},
	
	MaterialOceanicTheme {

		@Override
		public String getNamespace() {
			return StaticGuiFieldNames.materialui;
		}

		@Override
		public String toString() {
			return "Material Oceanic";
		}

		@Override
		public HashSet<OS> getNotSupportedOS() {
			return new HashSet<OS>(Arrays.asList(OS.MACOS, OS.WIN32));
		}

		@Override
		public boolean usesSkin() {
			return false;
		}
		
		@Override
		public boolean usesTheme() {
			return true;
		}
		
		@Override
		public LookAndFeel getLookAndFeel() {
			return new MaterialLookAndFeel(new MaterialOceanicTheme());
		}

		

	},
	
	MaterialDarkTheme {

		@Override
		public String getNamespace() {
			return StaticGuiFieldNames.materialui;
		}

		@Override
		public String toString() {
			return "Material Dark";
		}

		@Override
		public HashSet<OS> getNotSupportedOS() {
			return new HashSet<OS>(Arrays.asList(OS.MACOS, OS.WIN32));
		}

		@Override
		public boolean usesSkin() {
			return false;
		}
		
		@Override
		public boolean usesTheme() {
			return true;
		}
		
		@Override
		public LookAndFeel getLookAndFeel() {
			return new MaterialLookAndFeel(new JMarsDarkTheme());
		}

		

	},


	/** The weblaf look and feel */

	WEBLAF {

		@Override
		public String getNamespace() {
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
		public boolean usesSkin() {
			return false;
		}
		
		@Override
		public boolean usesTheme() {
			return false;
		}

	},

	/** The jtattoo look and feel */
	JTATTOO {

		@Override
		public String getNamespace() {
			return StaticGuiFieldNames.jtatoo;
		}

		@Override
		public String toString() {
			return "JTattoo";
		}

		@Override
		public HashSet<OS> getNotSupportedOS() {
			return null;
		}

		@Override
		public boolean usesSkin() {
			return false;
		}
		
		@Override
		public boolean usesTheme() {
			return false;
		}

	},
	
	JTATTOOACRYL {

		@Override
		public String getNamespace() {
			return "com.jtattoo.plaf.acryl.AcrylLookAndFeel";
		}

		@Override
		public String toString() {
			return "JTattoo Acryl";
		}

		@Override
		public HashSet<OS> getNotSupportedOS() {
			return null;
		}

		@Override
		public boolean usesSkin() {
			return false;
		}


		@Override
		public boolean usesTheme() {
			return false;
		}

	},
	
	JTATTOOAERO {

		@Override
		public String getNamespace() {
			return "com.jtattoo.plaf.aero.AeroLookAndFeel";
		}

		@Override
		public String toString() {
			return "JTattoo Aero";
		}

		@Override
		public HashSet<OS> getNotSupportedOS() {
			return null;
		}

		@Override
		public boolean usesSkin() {
			return false;
		}


		@Override
		public boolean usesTheme() {
			return false;
		}

	},
	
	JTATTOGRAPHITE {

		@Override
		public String getNamespace() {
			return "com.jtattoo.plaf.graphite.GraphiteLookAndFeel";
		}

		@Override
		public String toString() {
			return "JTattoo Graphite";
		}

		@Override
		public HashSet<OS> getNotSupportedOS() {
			return null;
		}

		@Override
		public boolean usesSkin() {
			return false;
		}


		@Override
		public boolean usesTheme() {
			return false;
		}

	},
	
	JTATTOFAST {

		@Override
		public String getNamespace() {
			return "com.jtattoo.plaf.fast.FastLookAndFeel";
		}

		@Override
		public String toString() {
			return "JTattoo Fast";
		}

		@Override
		public HashSet<OS> getNotSupportedOS() {
			return null;
		}

		@Override
		public boolean usesSkin() {
			return false;
		}


		@Override
		public boolean usesTheme() {
			return false;
		}

	},
	
	JTATTOHIFI {

		@Override
		public String getNamespace() {
			return "com.jtattoo.plaf.hifi.HiFiLookAndFeel";
		}

		@Override
		public String toString() {
			return "JTattoo HiFi";
		}

		@Override
		public HashSet<OS> getNotSupportedOS() {
			return null;
		}

		@Override
		public boolean usesSkin() {
			return false;
		}


		@Override
		public boolean usesTheme() {
			return false;
		}

	},
	
	JTATTOMCWIN {

		@Override
		public String getNamespace() {
			return "com.jtattoo.plaf.mcwin.LookAndFeel";
		}

		@Override
		public String toString() {
			return "JTattoo McWin";
		}

		@Override
		public HashSet<OS> getNotSupportedOS() {
			return null;
		}

		@Override
		public boolean usesSkin() {
			return false;
		}


		@Override
		public boolean usesTheme() {
			return false;
		}

	},

	TINYLAF {

		@Override
		public String getNamespace() {
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
		public boolean usesSkin() {
			return false;
		}
		
		@Override
		public boolean usesTheme() {
			return false;
		}

	},

	PGSLOOKANDFEEL {

		@Override
		public String getNamespace() {
			return StaticGuiFieldNames.PgsLookAndFeel;
		}

		@Override
		public String toString() {
			return "Pgs";
		}

		@Override
		public HashSet<OS> getNotSupportedOS() {
			return null;
		}

		@Override
		public boolean usesSkin() {
			return false;
		}
		
		@Override
		public boolean usesTheme() {
			return false;
		}

	},

	/** The jgoodiesxp look and feel */
	JGOODIESXP {

		@Override
		public String getNamespace() {
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
		public boolean usesSkin() {
			return false;
		}
		
		@Override
		public boolean usesTheme() {
			return false;
		}

	},

	/** The jgoodies look and feel */
	JGOODIES {

		@Override
		public String getNamespace() {
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
		public boolean usesSkin() {
			return false;
		}
		
		@Override
		public boolean usesTheme() {
			return false;
		}

	},

	/** The motif look and feel */
	MOTIF {

		@Override
		public String getNamespace() {
			return StaticGuiFieldNames.motiflook;
		}

		@Override
		public String toString() {
			return "Motif";
		}

		@Override
		public HashSet<OS> getNotSupportedOS() {
			return null;
		}

		@Override
		public boolean usesSkin() {
			return false;
		}
		
		@Override
		public boolean usesTheme() {
			return false;
		}

	},



	SUBSTANCENEBULA {

		@Override
		public String getNamespace() {
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
		public boolean usesSkin() {
			return true;
		}

		@Override
		public void setSkin() {
			SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.NebulaSkin");
			//SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.GraphiteGlassSkin");
		}
		
		@Override
		public boolean usesTheme() {
			return false;
		}

	},

	SUBSTANCEDUST {

		@Override
		public String getNamespace() {
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
		public boolean usesSkin() {
			return true;
		}

		@Override
		public void setSkin() {
			SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.SubstanceDustLookAndFeel");
		}
		
		@Override
		public boolean usesTheme() {
			return false;
		}

	},

	SUBSTANCEMARINER {

		@Override
		public String getNamespace() {
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
		public boolean usesSkin() {
			return true;
		}

		@Override
		public void setSkin() {
			SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.MarinerSkin");
		}
		
		@Override
		public boolean usesTheme() {
			return false;
		}

	},

	SUBSTANCEOFFICESTYLE {

		@Override
		public String getNamespace() {
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
		public boolean usesSkin() {
			return true;
		}

		@Override
		public void setSkin() {
			SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.OfficeBlue2007Skin");
		}
		
		@Override
		public boolean usesTheme() {
			return false;
		}

	},

	SUBSTANCEGEMINI {

		@Override
		public String getNamespace() {
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
		public boolean usesSkin() {
			return true;
		}

		@Override
		public void setSkin() {
			SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.GeminiSkin");
		}
		
		@Override
		public boolean usesTheme() {
			return false;
		}

	},

	SUBSTANCECREME {

		@Override
		public String getNamespace() {
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
		public boolean usesSkin() {
			return true;
		}

		@Override
		public void setSkin() {
			SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.CremeSkin");
		}
		
		@Override
		public boolean usesTheme() {
			return false;
		}

	},

	SUBSTANCESAHARA {

		@Override
		public String getNamespace() {
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
		public boolean usesSkin() {
			return true;
		}

		@Override
		public void setSkin() {
			SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.SaharaSkin");
		}
		
		@Override
		public boolean usesTheme() {
			return false;
		}

	},

	SUBSTANCEMISTAQUA {

		@Override
		public String getNamespace() {
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
		public boolean usesSkin() {
			return true;
		}

		@Override
		public void setSkin() {
			SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.MistAquaSkin");
		}
		
		@Override
		public boolean usesTheme() {
			return false;
		}

	};

	/**
	 * Gets the type of look and feel.
	 *
	 * @return the type
	 */
	public String getNamespace() {
		return this.getNamespace();
	}

	public HashSet<OS> getNotSupportedOS() {
		return getNotSupportedOS();
	}

	public boolean usesSkin() {
		return usesSkin();
	}

	public void setSkin() {}
	
	public boolean usesTheme() {
		return usesTheme();
	}
	
	public LookAndFeel getLookAndFeel() {
		return getLookAndFeel();
	}
	
	
	public static Looktypes getLookAndFeel(String looktype) {
		for (Looktypes type : Looktypes.values()) {
			if(looktype.toLowerCase().equals(type.name().toLowerCase()))
					return type;
		}
		return null;
	}
	
	

}
