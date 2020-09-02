package app;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import constants.EnvironmentPropertyKeyNames;
import core.AudioProcessing;
import core.FileListGenerator;
import tailer.TailThread;
import utils.EnvironmentVarUtils;
import utils.FileUtils;

/**
 * Main application
 * 
 * @author gokuafrica
 *
 */
public class MainApp {

	/**
	 * Initialize all services
	 */
	public static void init() {
		processAudio = new AudioProcessing();
		fileListGenerator = new FileListGenerator();
		fileUtils = new FileUtils();
		evUtils = new EnvironmentVarUtils();
		System.out.println("Initiated app");
	}

	/**
	 * Services and Utilities
	 */
	private static AudioProcessing processAudio;
	private static FileListGenerator fileListGenerator;
	private static FileUtils fileUtils;
	private static EnvironmentVarUtils evUtils;
	
	/**
	 * Start the tailing thread
	 */
	private static ExecutorService startTailing() {
		ExecutorService thread = Executors.newSingleThreadExecutor();
		thread.submit(new TailThread());
		return thread;
	}
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		
		MainApp.init();

		fileUtils.reset();

		List<File> files = fileListGenerator
				.getFileList(evUtils.getProperty(EnvironmentPropertyKeyNames.MAX_TIME.toString()));
		
		processAudio.createMergedFile(files);
		
		ExecutorService thread = startTailing();
		
		processAudio.startConversion(thread);
		
	}

}
