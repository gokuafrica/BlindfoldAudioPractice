package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Various file utilities
 * 
 * @author gokuafrica
 *
 */
public class FileUtils {
	/**
	 * Reset all output files involved
	 */
	public void reset() {
		File f1 = new File("./sound/merge.wav");
		File f2 = new File("./sound/output.mp3");
		File f3 = new File("./scripts/log.log");
		// delete file
		if (f1.exists())
			f1.delete();
		// delete file
		if (f2.exists())
			f2.delete();
		// truncate log file
		if (f3.exists()) {
			try (FileOutputStream fos = new FileOutputStream(f3)) {
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Reset Successfully");
	}
}
