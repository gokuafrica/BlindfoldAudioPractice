package core;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import constants.Files;

/**
 * Used to generate file list to merge
 * 
 * @author gokuafrica
 *
 */
public class FileListGenerator {

	private int convertTimeToInt(String maxTime) {
		// default time of 60 minutes
		int num = 60;

		try {
			num = Integer.parseInt(maxTime);
		} catch (NullPointerException | NumberFormatException e) {
			System.out.println(
					"Number format exception ocurred for input: " + maxTime + "\nSetting default value of 60 minutes");
			e.printStackTrace();
		}
		return num;
	}

	/**
	 * @param maxTime in minutes
	 * @return
	 */
	public ArrayList<File> getFileList(String maxT) {

		int maxTime = convertTimeToInt(maxT);
		
		// 8 seconds is the maximum clip size so dividing by it guarantees the final
		// file duration is less than the max time provided here
		int fileSize = 60 * maxTime / 8;
		ArrayList<File> f = new ArrayList<>(fileSize);
		// to prevent infinite loops and make sure the files are actually there, hitrate
		// will check for empty hits for 4 million times after which we'll stop the loop
		// with a warning
		int hitRate = 0;
		int counter = 0;
		while (counter < fileSize) {
			if (hitRate > 1000000) {
				System.out
						.println("Hit rate crossed a million. Please check the sound files are prseent and run again");
				break;
			}
			int a = ThreadLocalRandom.current().nextInt(1, 25);
			int b = ThreadLocalRandom.current().nextInt(1, 25);
			File temp = new File(Files.SOUND_DIRECTORY + a + "-" + b + Files.PREPROCESSING_EXTENSION);
			if (temp.exists()) {
				// reset hitRate
				hitRate = 0;
				f.add(temp);
				++counter;
			} else
				++hitRate;
		}
		System.out.println("Generated files");
		return f;
	}
}
