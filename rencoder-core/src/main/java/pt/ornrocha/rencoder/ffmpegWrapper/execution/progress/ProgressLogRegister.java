package pt.ornrocha.rencoder.ffmpegWrapper.execution.progress;

import java.io.InputStream;
import java.util.Scanner;

import javax.swing.SwingWorker;

import org.pmw.tinylog.Logger;

public class ProgressLogRegister extends SwingWorker<Integer, String> {
	
	private InputStream stream;
	
	public ProgressLogRegister(InputStream stream) {
		this.stream=stream;
	}

	@Override
	protected Integer doInBackground() throws Exception {
		
		Scanner sc = new Scanner(stream);
		while (sc.hasNextLine()) {
			String currentline = sc.nextLine();
			System.out.println(currentline);
			Logger.debug(currentline);
		}
		return null;
	}
	
	
	
	
	
}
