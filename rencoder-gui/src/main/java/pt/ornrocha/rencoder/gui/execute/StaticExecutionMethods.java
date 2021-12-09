package pt.ornrocha.rencoder.gui.execute;

import java.io.File;
import java.io.IOException;

import pt.ornrocha.rencoder.helpers.osystem.OSystem;
import pt.ornrocha.rencoder.helpers.props.fields.StaticGlobalFields;

public final class StaticExecutionMethods {


  public static String getUserDirMacOS() {
    String currentpath = OSystem.getCurrentDir();
    String usrpath = currentpath.replace("Java", "Resources");
    return usrpath;
  }


  public static boolean existRestartTag() {
    boolean exist = false;

    String tagpath =
        OSystem.getCurrentDir() + OSystem.getSystemSeparator() + StaticGlobalFields.RESTARTTAG;
    File f = new File(tagpath);
    if (f.exists())
      exist = true;

    return exist;
  }


  public static void setRestartTag() throws IOException {
    String tagpath =
        OSystem.getCurrentDir() + OSystem.getSystemSeparator() + StaticGlobalFields.RESTARTTAG;
    File f = new File(tagpath);
    f.createNewFile();
  }

  public static void removeRestartTag() {
    if (existRestartTag()) {
      String tagpath =
          OSystem.getCurrentDir() + OSystem.getSystemSeparator() + StaticGlobalFields.RESTARTTAG;
      File f = new File(tagpath);
      f.delete();
    }

  }

  public static int checkRestartTag() {
    boolean restart = existRestartTag();
    if (restart)
      return 1;
    else
      return 0;
  }


}
