package pt.ornrocha.rencoder.gui.components.panels.configurations.ffmpegexecutable;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.kamranzafar.jddl.DirectDownloader;
import org.kamranzafar.jddl.DownloadTask;
import org.rauschig.jarchivelib.ArchiveFormat;
import org.rauschig.jarchivelib.Archiver;
import org.rauschig.jarchivelib.ArchiverFactory;
import org.rauschig.jarchivelib.CompressionType;
import org.tinylog.Logger;

import pt.ornrocha.rencoder.ffmpegWrapper.releases.containers.FFmpegReleaseOsType;
import pt.ornrocha.rencoder.gui.components.panels.configurations.ffmpegexecutable.panels.FFmpegRelease;
import pt.ornrocha.rencoder.gui.components.panels.configurations.ffmpegexecutable.panels.FFmpegReleaseCollector;
import pt.ornrocha.rencoder.gui.components.panels.configurations.ffmpegexecutable.panels.ProgressBarUpdater;
import pt.ornrocha.rencoder.helpers.lang.LangTools;
import pt.ornrocha.rencoder.helpers.osystem.OSystem;
import pt.ornrocha.rencoder.helpers.props.fields.StaticGlobalFields;
import pt.ornrocha.rencoder.mediafiles.setfiles.foldersandfiles.ListFiles;

public class DownloadFFmpegRelease extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private FFmpegRelease ffmpegpanel = null;
	private JProgressBar progressBar = null;
	private ResourceBundle rb;
	private JLabel lblMessages;
	private String ffmpegbinpath = null;

	private static String DOWNLOAD = "download";
	public static String OK = "ok";
	public static String CANCEL = "cancel";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DownloadFFmpegRelease dialog = new DownloadFFmpegRelease();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
		    Logger.error(e);
		}
	}

	/**
	 * Create the dialog.
	 */
	public DownloadFFmpegRelease() {
		rb = ResourceBundle.getBundle("lang", LangTools.getDefinedLanguage(), LangTools.loadLanguagesPath());
		setBounds(100, 100, 596, 265);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 1, 1, 1 };
		gbl_contentPanel.rowHeights = new int[] { 1, 1, 1, 1, 1 };
		gbl_contentPanel.columnWeights = new double[] { 1.0, 1.0, 1.0 };
		gbl_contentPanel.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 0.0 };
		contentPanel.setLayout(gbl_contentPanel);
		{
			if (OSystem.isWindows())
				ffmpegpanel = new FFmpegReleaseCollector(FFmpegReleaseOsType.Windows);
			else
				ffmpegpanel = new FFmpegReleaseCollector(FFmpegReleaseOsType.Linux);
			
            ffmpegpanel.setSize(600, 500);
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridheight = 3;
			gbc_panel.gridwidth = 3;
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 0;
			contentPanel.add(ffmpegpanel, gbc_panel);

		}
		{
			JButton btnDownload = new JButton("Download");
			btnDownload.addActionListener(this);
			btnDownload.setActionCommand(DOWNLOAD);
			GridBagConstraints gbc_btnDownload = new GridBagConstraints();
			gbc_btnDownload.gridwidth = 3;
			gbc_btnDownload.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnDownload.insets = new Insets(0, 0, 5, 0);
			gbc_btnDownload.gridx = 0;
			gbc_btnDownload.gridy = 3;
			contentPanel.add(btnDownload, gbc_btnDownload);
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Status", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 0, 5);
			gbc_panel.fill = GridBagConstraints.HORIZONTAL;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 4;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] { 1 };
			gbl_panel.rowHeights = new int[] { 1 };
			gbl_panel.columnWeights = new double[] { 1.0 };
			gbl_panel.rowWeights = new double[] { 1.0 };
			panel.setLayout(gbl_panel);
			{
				lblMessages = new JLabel(LangTools.getResourceBundleWordLanguage(rb, "Waiting...", "ffmpegconfgui.release.wait"));
				GridBagConstraints gbc_lblWaiting = new GridBagConstraints();
				gbc_lblWaiting.gridx = 0;
				gbc_lblWaiting.gridy = 0;
				panel.add(lblMessages, gbc_lblWaiting);
			}
		}
		{
			progressBar = new JProgressBar();
			GridBagConstraints gbc_progressBar = new GridBagConstraints();
			gbc_progressBar.fill = GridBagConstraints.BOTH;
			gbc_progressBar.gridwidth = 2;
			gbc_progressBar.gridx = 1;
			gbc_progressBar.gridy = 4;
			contentPanel.add(progressBar, gbc_progressBar);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Close");
				okButton.setActionCommand(OK);
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand(CANCEL);
				cancelButton.addActionListener(this);
				// buttonPane.add(cancelButton);
			}
		}
		
		this.setSize(500, 400);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (cmd.equals(DOWNLOAD)) {
			try {
				String url=ffmpegpanel.getURLSelectedRelease();
				if(url!=null) {
					if(url.toLowerCase().equals("selectrow"))
						throw new FileNotFoundException(LangTools.getResourceBundleWordLanguage(rb, "Select a release", "ffmpegconfgui.selectrow.error"));
					else
						downloadFFmpegRelease(url);
				}
				else
					throw new FileNotFoundException(LangTools.getResourceBundleWordLanguage(rb, "Could not get information about ffmpeg versions", "ffmpegconfgui.release.error"));
				
			} catch (InterruptedException | MalformedURLException | FileNotFoundException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(),
						LangTools.getResourceBundleWordLanguage(rb, "Warning", "warngui.tag"),
						JOptionPane.INFORMATION_MESSAGE,
						new ImageIcon(ListFiles.getIconAbsolutePath("icons/warning64x64.png")));
			}
		} else if (cmd.equals(OK))
			this.dispose();

	}

	private String getOutputFolder(String url) {
		String filename = FilenameUtils.getName(url);
		return FilenameUtils.concat(OSystem.getTempFolder(), filename);
	}

	private void downloadFFmpegRelease(String url)
			throws InterruptedException, MalformedURLException, FileNotFoundException {

		final DirectDownloader fd = new DirectDownloader();
		fd.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0");
		String fname = getOutputFolder(url);
		DownloadTask dt = new DownloadTask(new URL(url), new FileOutputStream(fname));
		dt.addListener(new ProgressBarUpdater(fname, progressBar));
		fd.download(dt);
		// lblMessages.setText("Downloading...");
		Thread t = new Thread(fd);
		t.start();

		new SwingWorker<Boolean, Object>() {

			@Override
			protected Boolean doInBackground() throws Exception {
				int count = 0;
				lblMessages.setText(LangTools.getResourceBundleWordLanguage(rb, "Downloading...", "ffmpegconfgui.release.download"));
				while (progressBar.getValue() != progressBar.getMaximum()) {
					Thread.sleep(1000);
					if (progressBar.getValue() == 0)
						count++;
					if (count > 10)
						break;
				}

				lblMessages.setText(LangTools.getResourceBundleWordLanguage(rb, "Configuring...", "ffmpegconfgui.release.configure"));
				lblMessages.repaint();

				return true;
			}

			@Override
			protected void done() {
				String searchdir = null;

				try {
					searchdir = decompressFile(fname);
				} catch (Exception e) {
					Logger.error(e);
					lblMessages.setText(LangTools.getResourceBundleWordLanguage(rb, "Installation failed", "ffmpegconfgui.release.error.install"));
					JOptionPane.showMessageDialog(null, "Could not download file from: \n" + url,
							// LangTools.getResourceBundleWordLanguage(rb,"Warning","warngui.tag"),
							"ERROR", JOptionPane.INFORMATION_MESSAGE,
							new ImageIcon(ListFiles.getIconAbsolutePath("icons/warning64x64.png")));
				}

				if (searchdir != null) {
					String ffmpegpath = findFFmpegBin(searchdir);
					if (ffmpegpath != null) {
						String parentpath = FilenameUtils.concat(OSystem.getCurrentDir(),
								StaticGlobalFields.FFMPEGFOLDERPATH);
						String destpath = FilenameUtils.concat(parentpath, FilenameUtils.getName(ffmpegpath));
						boolean success = true;
						try {
							FileUtils.copyFile(new File(ffmpegpath), new File(destpath));
						} catch (IOException e) {
							Logger.error(e);
							success = false;
							lblMessages.setText("Install failed");
						}

						if (success) {
							lblMessages.setText(LangTools.getResourceBundleWordLanguage(rb, "Successfully installed", "ffmpegconfgui.release.success.install"));
							ffmpegbinpath = destpath;
						}
					}

				}

			}

		}.execute();

	}

	private String decompressFile(String filepath) throws IOException {

		String filename = FilenameUtils.getBaseName(filepath);
		String dirname = FilenameUtils.getFullPath(filepath);
		String extension = FilenameUtils.getExtension(filepath);

		String newdirname = FilenameUtils.concat(filename, dirname);
		Archiver archiver = null;

		if (extension.toLowerCase().equals("gz"))
			archiver = ArchiverFactory.createArchiver(ArchiveFormat.TAR, CompressionType.GZIP);
		else if (extension.toLowerCase().equals("xz"))
			archiver = ArchiverFactory.createArchiver(ArchiveFormat.TAR, CompressionType.XZ);
		else if (extension.toLowerCase().equals("bz2"))
			archiver = ArchiverFactory.createArchiver(ArchiveFormat.TAR, CompressionType.BZIP2);
		else if (extension.toLowerCase().equals("zip"))
			archiver = ArchiverFactory.createArchiver(ArchiveFormat.ZIP);

		archiver.extract(new File(filepath), new File(newdirname));

		return newdirname;
	}

	private String findFFmpegBin(String rootdir) {

		Collection<File> files = FileUtils.listFiles(new File(rootdir), null, true);
		for (File file : files) {
			String filename = FilenameUtils.getName(file.getAbsolutePath());
			if (OSystem.isLinux() && (filename.toLowerCase().equals("ffmpeg")
					|| filename.toLowerCase().equalsIgnoreCase("ffmpeg_rencoder")))
				return file.getAbsolutePath();
			else if (OSystem.isWindows() && filename.toLowerCase().equals("ffmpeg.exe"))
				return file.getAbsolutePath();

		}

		return null;
	}

	public String getFFmpegBinPath() {
		return ffmpegbinpath;
	}

	public static String showDownloadFFmpegPanel() {

		return null;
	}

}
