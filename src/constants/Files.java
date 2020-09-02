package constants;

/**
 * Central place to keep all files involved
 * 
 * @author gokuafrica
 *
 */
public class Files {
	public static final String SCRIPT_DIRECTORY = "./scripts/";
	public static final String SOUND_DIRECTORY = "./sound/";
	public static final String PREPROCESSING_EXTENSION = ".wav";
	public static final String POSTPROCESSING_EXTENSION = ".mp3";
	public static final String LOG_FILE = SCRIPT_DIRECTORY + "log.log";
	public static final String SCRIPT_FILE = SCRIPT_DIRECTORY + "script.sh";
	public static final String MERGED_FILE = SOUND_DIRECTORY + "merge"+PREPROCESSING_EXTENSION;
	public static final String OUTPUT_FILE = SOUND_DIRECTORY + "output"+POSTPROCESSING_EXTENSION;
}
