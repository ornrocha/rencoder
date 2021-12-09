package pt.ornrocha.rencoder.gui.components.panels.configurations.ffmpegexecutable.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JTable;

import pt.ornrocha.rencoder.ffmpegWrapper.releases.containers.FFmpegReleaseOsType;
import pt.ornrocha.rencoder.gui.components.panels.Scroll.FFmpegReleasesScrollPanel;

public class FFmpegReleaseCollector extends FFmpegRelease{


	private static final long serialVersionUID = 1L;
	private FFmpegReleasesScrollPanel scrollPane;

	public FFmpegReleaseCollector(FFmpegReleaseOsType ostype) {
		super(ostype);
	}


	@Override
	public void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 1, 1,1};
		gridBagLayout.rowHeights = new int[] { 1, 1, 1 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0,1.0};
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0 };
		setLayout(gridBagLayout);

		scrollPane = new FFmpegReleasesScrollPanel(ostype);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);

		setSize(500, 400);

	}

	@Override
	public String getURLSelectedRelease() {
		
		
		JTable table = (JTable)scrollPane.getViewport().getView();
		int row = table.getSelectedRow();
		int nrows=table.getRowCount();
		if(nrows>0 && row>=0) {
			return scrollPane.getListreleases().get(row).getUrl();
		}
		else if(nrows>0) {
			return "selectrow";
		}
		else
		return null;
	}




}
