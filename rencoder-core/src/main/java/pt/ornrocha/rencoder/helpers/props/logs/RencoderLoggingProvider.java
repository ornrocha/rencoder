package pt.ornrocha.rencoder.helpers.props.logs;

import org.tinylog.Level;
import org.tinylog.core.TinylogLoggingProvider;
import org.tinylog.format.MessageFormatter;



public class RencoderLoggingProvider extends  TinylogLoggingProvider{


    private Level activeLevel=Level.TRACE;


	@Override
	public boolean isEnabled(final int depth, final String tag, final Level level) {
		return (activeLevel.ordinal() <= level.ordinal()) && super.isEnabled(depth + 1, tag, level);
	}

	@Override
	public void log(final int depth, final String tag, Level level, final Throwable exception, final MessageFormatter formatter,
		final Object obj, final Object... arguments) {

		if (activeLevel.ordinal() <= level.ordinal()) {
			super.log(depth + 1, tag, level, exception, formatter, obj, arguments);
		}
	}

	@Override
	public void log(final String loggerClassName, final String tag, final Level level, final Throwable exception,
		final MessageFormatter formatter, final Object obj, final Object... arguments) {

		 if (activeLevel.ordinal() <= level.ordinal() ){
	            super.log(loggerClassName, tag, level, exception, formatter, obj, arguments);
	        }
	}

	public void setLevel(Level level) {
		this.activeLevel=level;
	}


}

