package pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pt.ornrocha.rencoder.helpers.props.fields.StaticFFmpegFields;

public enum VideoLevel {

    
	NONE {

		public String toString() {
			return "none";
		}
	},
	L1 {

		public ArrayList<String> getffmpegcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.LEVEL);
			cmd.add("1");
			return cmd;
		}

		public String toString() {
			return "1";
		}
	},
	L10 {

		public ArrayList<String> getffmpegcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.LEVEL);
			cmd.add("1.0");
			return cmd;
		}

		public String toString() {
			return "1.0";
		}
	},
	L1b {

		public ArrayList<String> getffmpegcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.LEVEL);
			cmd.add("1b");
			return cmd;
		}

		public String toString() {
			return "1b";
		}
	},
	L1b0 {

		public ArrayList<String> getffmpegcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.LEVEL);
			cmd.add("1.0b");
			return cmd;
		}

		public String toString() {
			return "1.0b";
		}
	},
	L11 {

		public ArrayList<String> getffmpegcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.LEVEL);
			cmd.add("1.1");
			return cmd;
		}

		public String toString() {
			return "1.1";
		}
	},
	L12 {

		public ArrayList<String> getffmpegcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.LEVEL);
			cmd.add("1.2");
			return cmd;
		}

		public String toString() {
			return "1.2";
		}
	},
	L13 {

		public ArrayList<String> getffmpegcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.LEVEL);
			cmd.add("1.3");
			return cmd;
		}

		public String toString() {
			return "1.3";
		}
	},
	L2 {

		public ArrayList<String> getffmpegcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.LEVEL);
			cmd.add("2");
			return cmd;
		}

		public String toString() {
			return "2";
		}
	},
	L20 {

		public ArrayList<String> getffmpegcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.LEVEL);
			cmd.add("2.0");
			return cmd;
		}

		public String toString() {
			return "2.0";
		}
	},
	L21 {

		public ArrayList<String> getffmpegcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.LEVEL);
			cmd.add("2.1");
			return cmd;
		}

		public String toString() {
			return "2.1";
		}
	},
	L22 {

		public ArrayList<String> getffmpegcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.LEVEL);
			cmd.add("2.2");
			return cmd;
		}

		public String toString() {
			return "2.2";
		}
	},
	L3 {

		public ArrayList<String> getffmpegcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.LEVEL);
			cmd.add("3");
			return cmd;
		}

		public String toString() {
			return "3";
		}
	},
	L30 {

		public ArrayList<String> getffmpegcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.LEVEL);
			cmd.add("3.0");
			return cmd;
		}

		public String toString() {
			return "3.0";
		}
	},

	L31 {

		public ArrayList<String> getffmpegcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.LEVEL);
			cmd.add("3.1");
			return cmd;
		}

		public String toString() {
			return "3.1";
		}
	},

	L32 {

		public ArrayList<String> getffmpegcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.LEVEL);
			cmd.add("3.2");
			return cmd;
		}

		public String toString() {
			return "3.2";
		}
	},
	L4 {

		public ArrayList<String> getffmpegcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.LEVEL);
			cmd.add("4");
			return cmd;
		}

		public String toString() {
			return "4";
		}
	},
	L40 {

		public ArrayList<String> getffmpegcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.LEVEL);
			cmd.add("4.0");
			return cmd;
		}

		public String toString() {
			return "4.0";
		}
	},

	L41 {

		public ArrayList<String> getffmpegcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.LEVEL);
			cmd.add("4.1");
			return cmd;
		}

		public String toString() {
			return "4.1";
		}
	},

	L42 {

		public ArrayList<String> getffmpegcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.LEVEL);
			cmd.add("4.2");
			return cmd;
		}

		public String toString() {
			return "4.2";
		}
	},
	L5 {

		public ArrayList<String> getffmpegcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.LEVEL);
			cmd.add("5");
			return cmd;
		}

		public String toString() {
			return "5";
		}
	},
	L50 {

		public ArrayList<String> getffmpegcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.LEVEL);
			cmd.add("5.0");
			return cmd;
		}

		public String toString() {
			return "5.0";
		}
	},

	L51 {

		public ArrayList<String> getffmpegcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.LEVEL);
			cmd.add("5.1");
			return cmd;
		}

		public String toString() {
			return "5.1";
		}
	},

	L52 {

		public ArrayList<String> getffmpegcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.LEVEL);
			cmd.add("5.2");
			return cmd;
		}

		public String toString() {
			return "5.2";
		}
	},
	L60 {

		public ArrayList<String> getffmpegcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.LEVEL);
			cmd.add("6.0");
			return cmd;
		}

		public String toString() {
			return "6.0";
		}
	},

	L61 {

		public ArrayList<String> getffmpegcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.LEVEL);
			cmd.add("6.1");
			return cmd;
		}

		public String toString() {
			return "6.1";
		}
	},

	L62 {

		public ArrayList<String> getffmpegcommand() {
			ArrayList<String> cmd = new ArrayList<>();
			cmd.add(StaticFFmpegFields.LEVEL);
			cmd.add("6.2");
			return cmd;
		}

		public String toString() {
			return "6.2";
		}
	};;

	public ArrayList<String> getffmpegcommand() {
		return this.getffmpegcommand();
	}
	
	public static VideoLevel getVideoLevelFromString(String level) {
	    if(level!=null) {
		for (VideoLevel l : VideoLevel.values()) {
		    if(l.toString().equalsIgnoreCase(level))
			return l;
		}
	    }
	    return VideoLevel.NONE;
	}

	public static VideoLevel[] getAllowedLevels(String... levels) {
		ArrayList<VideoLevel> filtered = new ArrayList<>();
		List<String> listlevels = Arrays.asList(levels);
		for (VideoLevel videoEncLevel : VideoLevel.values()) {
			if (listlevels.contains(videoEncLevel.toString()))
				filtered.add(videoEncLevel);
		}

		return filtered.toArray(new VideoLevel[filtered.size()]);
	}
	
	
	public static VideoLevel[] getH264Levels() {
	    return getAllowedLevels("1");
	}
	
	public static VideoLevel[] getKvazaarLevels() {
	    return getAllowedLevels("none","1", "2", "2.1", "3", "3.1", "4", "4.1", "5", "5.1", "5.2", "6","6.1", "6.2");
	}
	
	public static VideoLevel[] getH264VaapiLevels() {
	    return getAllowedLevels("none","1", "1.1", "1.2", "1.3", "2", "2.1", "2.2", "3", "3.1", "3.2", "4", "4.1", "4.2", "5", "5.1", "5.2", "6", "6.1", "6.2");
	}
	

	public static VideoLevel[] getH264NvencLevels() {
	    return getAllowedLevels("none", "1", "1.0", "1b", "1.0b", "1.1", "1.2", "1.3", "2", "2.0", "2.1", "2.2", "3", "3.0", "3.1", "3.2", "4", "4.0", "4.1", "4.2", "5", "5.0", "5.1");
	}
	

	public static void main(String[] args) {
		VideoLevel[] list = VideoLevel.getAllowedLevels("2.5", "5.2", "4.1", "5.0");
		for (VideoLevel videoEncLevel : list) {
			System.out.println(videoEncLevel.toString());
		}
	}

}
