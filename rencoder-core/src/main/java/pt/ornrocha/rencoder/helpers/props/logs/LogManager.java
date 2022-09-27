package pt.ornrocha.rencoder.helpers.props.logs;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.tinylog.Level;
import org.tinylog.provider.ProviderRegistry;

import pt.ornrocha.rencoder.helpers.osystem.OSystem;
import pt.ornrocha.rencoder.helpers.props.fields.StaticGlobalFields;
import pt.ornrocha.rencoder.helpers.props.readwrite.PropertiesWorker;

public class LogManager {

	public static void initialiseLogger() {
		
		RencoderLoggingProvider logger = (RencoderLoggingProvider) ProviderRegistry.getLoggingProvider();
		logger.setLevel(getRencoderLogLevel());
	
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
	
	public static boolean showFFmpegAtRencoderLog() {
		PropertiesConfiguration prop = PropertiesWorker.loadPropertiesRelativePath(StaticGlobalFields.RENCODERCONFIGFILE);
		return PropertiesWorker.getBooleanPropertyWithDefault(prop, StaticGlobalFields.LOGFFMPEGRENCODER, false);

	}

	public static Level getLogLevel(String loglevel) {
		loglevel = loglevel.toLowerCase();

		switch (loglevel) {
		case "error":
			return Level.ERROR;
		case "info":
			return Level.INFO;
		case "trace":
			return Level.TRACE;
		case "debug":
			return Level.DEBUG;
		case "off":
			return Level.OFF;	
		default:
			return Level.INFO;
		}

	}

}
