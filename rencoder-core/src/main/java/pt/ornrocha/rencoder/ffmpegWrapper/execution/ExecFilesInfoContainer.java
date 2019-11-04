package pt.ornrocha.rencoder.ffmpegWrapper.execution;

import java.util.ArrayList;
import java.util.HashMap;

import pt.ornrocha.rencoder.ffmpegWrapper.configurations.FFmpegLogInfoContainer;
import pt.ornrocha.rencoder.helpers.IndexedHashMap;

public class ExecFilesInfoContainer {

	private IndexedHashMap<String, ArrayList<ArrayList<String>>> moviesencinfo;
	private HashMap<String, String> tempsubspath;
	private HashMap<String, String> templogpaths;
	private IndexedHashMap<String, String> moviepaths;
	private ArrayList<FFmpegLogInfoContainer> logsinfo;

	public ExecFilesInfoContainer(IndexedHashMap<String, ArrayList<ArrayList<String>>> moviesencinfo,
			HashMap<String, String> tempsubspath, HashMap<String, String> templogpaths,
			IndexedHashMap<String, String> moviepaths, ArrayList<FFmpegLogInfoContainer> logsinfo) {
		this.moviesencinfo = moviesencinfo;
		this.tempsubspath = tempsubspath;
		this.templogpaths = templogpaths;
		this.moviepaths = moviepaths;
		this.logsinfo = logsinfo;
	}

	public IndexedHashMap<String, ArrayList<ArrayList<String>>> getMoviesencinfo() {
		return moviesencinfo;
	}

	public HashMap<String, String> getTempsubspath() {
		return tempsubspath;
	}

	public HashMap<String, String> getTemplogpaths() {
		return templogpaths;
	}

	public IndexedHashMap<String, String> getMoviepaths() {
		return moviepaths;
	}

	public ArrayList<FFmpegLogInfoContainer> getLogsinfo() {
		return logsinfo;
	}

}
