package core;

import java.io.File;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicLong;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFileFormat.Type;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import constants.Files;
import constants.ScriptCommand;
import utils.ProcessUtils;

/**
 * Primary class for audio processing
 * 
 * @author gokuafrica
 *
 */
public class AudioProcessing {

	/**
	 * Services
	 */
	private ProcessUtils processUtils;

	/**
	 * Constructor to initialise services
	 */
	public AudioProcessing() {
		processUtils = new ProcessUtils();
	}

	/**
	 * Sum and return total frame length of merged file
	 * 
	 * @param list
	 * @return
	 */
	private long calculateTotalFrameLength(List<AudioInputStream> list) {
		AtomicLong totalFrameLength = new AtomicLong(0);
		list.forEach((L) -> {
			totalFrameLength.addAndGet(L.getFrameLength());
		});
		return totalFrameLength.get();
	}

	/**
	 * Take audio files and merge into one single file
	 * 
	 * @param file list
	 */
	public void createMergedFile(List<File> f) {
		try {

			List<AudioInputStream> list = new LinkedList<>();
			for (File i : f) {
				list.add(AudioSystem.getAudioInputStream(i));
			}

			long totalFrameLength = calculateTotalFrameLength(list);

			AudioInputStream appendedFiles = new AudioInputStream(
					new SequenceInputStream(Collections.enumeration(list)), list.get(0).getFormat(), totalFrameLength);
			AudioSystem.write(appendedFiles, getFormat(), new File(Files.MERGED_FILE));

			System.out.println("Created merged file");
		} catch (IOException | UnsupportedAudioFileException e) {
			System.out.println("Something went wrong while doing audio processing");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Some unexpected error ocurred while merging audio");
			e.printStackTrace();
		}
	}

	/**
	 * Return accurate format based on preprocessing audio file extension
	 * 
	 * @return
	 */
	private Type getFormat() throws Exception {
		if (Files.PREPROCESSING_EXTENSION.equals(".wav"))
			return AudioFileFormat.Type.WAVE;
		else
			throw new Exception("Preprocessing exception not found in expected list.\nPreprocessing Format: "
					+ Files.PREPROCESSING_EXTENSION);
	}

	/**
	 * Used to convert merged file to post processed compressed audio format
	 */
	public void startConversion(ExecutorService thread) {
		// start conversion
		Process p = processUtils.execCmd(ScriptCommand.cmd);
		// wait until conversion finishes
		processUtils.waitForProcessToEndInTenMinutes(p);
		// stop log tailing thread
		thread.shutdownNow();
		
		System.out.println("Created output");
	}
}
