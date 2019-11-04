package pt.ornrocha.rencoder.ffmpegWrapper.execution;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.swing.SwingWorker;

public class TimeoutSwingWorker extends SwingWorker<Boolean, Void> {

	private SwingWorker torun;
	private long timeout;
	private TimeUnit timeunit;

	public TimeoutSwingWorker(SwingWorker run, long timeout, TimeUnit unit) {
		this.torun = run;
		if (timeout > 0)
			this.timeout = timeout;
		else
			this.timeout = 1000;

		if (unit != null)
			this.timeunit = unit;
		else
			this.timeunit = TimeUnit.MILLISECONDS;
	}

	@Override
	protected Boolean doInBackground() throws TimeoutException, InterruptedException, ExecutionException {

		torun.execute();
		try {
			return (Boolean) torun.get(timeout, timeunit);
		} catch (TimeoutException timeoutException) {
			torun.cancel(true);
			throw timeoutException;
		}
	}

}
