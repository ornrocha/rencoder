package pt.ornrocha.rencoder.helpers.props.logs;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.writers.ConsoleWriter;
import org.pmw.tinylog.writers.FileWriter;

import pt.ornrocha.rencoder.helpers.osystem.OSystem;
import pt.ornrocha.rencoder.helpers.props.fields.StaticGlobalFields;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;

public class LogManager {

	public static void initialiseLogger() {
		Configurator.currentConfig().writer(new ConsoleWriter(), getRencoderLogLevel(), "{level}: {message}")
				.addWriter(new FileWriter(getlogPath()), getRencoderLogLevel(), "{level}: {message}").activate();
	}

	public static String getlogPath() {
		return OSystem.getCurrentDir() + OSystem.getSystemSeparator() + StaticGlobalFields.RENCODERLOGPATH;
	}

	public static Level getRencoderLogLevel() {
		PropertiesConfiguration prop = PropertiesWorker.loadPropertiesRelativePath(StaticGlobalFields.RENCODERCONFIGFILE);

		String level = PropertiesWorker.checkProperty(prop, StaticGlobalFields.LOGLEVELRENCODER);
		if (level != null) {
			return getLogLevel(level);
		}
		return Level.INFO;
	}

	public static Level getLogLevel(String loglevel) {
		loglevel = loglevel.toLowerCase();
		
		switch (loglevel) {
		case "error":
			return Level.ERROR;
		case "warning":
			return Level.WARNING;
		case "info":
			return Level.INFO;
		case "trace":
			return Level.TRACE;
		case "debug":
			return Level.DEBUG;

		default:
			return Level.INFO;
		}

	}

}
