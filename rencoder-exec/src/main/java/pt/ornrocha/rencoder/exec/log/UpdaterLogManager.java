package pt.ornrocha.rencoder.exec.log;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.writers.ConsoleWriter;
import org.pmw.tinylog.writers.FileWriter;

import pt.ornrocha.rencoder.exec.execute.RencoderExec;

public class UpdaterLogManager {

	public static void initialiseLogger() {
		
		String currentpath = new File(".").getAbsolutePath();
		Path p = Paths.get(currentpath);
		String parentpath= p.getParent().toString();
		
		String logpath= parentpath + RencoderExec.getSystemSeparator() + "rencoder-exec-log.log";

		Configurator.currentConfig().writer(new ConsoleWriter(), Level.DEBUG, "{level}: {message}")
				.addWriter(new FileWriter(logpath), Level.DEBUG, "{level}: {message}").activate();
	}

	

}
