package tailer;

import java.io.File;

import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;

import constants.Files;

/**
 * Thread that triggers and maintains lifecycle of tailing thread. Tailing
 * thread is a child of this thread.
 * 
 * @author gokuafrica
 *
 */
public class TailThread implements Runnable {

	@Override
	public void run() {
		Tailer tailer = null;
		try {
			// keep sleeping until log file exists
			File f1 = new File(Files.LOG_FILE);
			while (!f1.exists())
				Thread.sleep(1000);

			// create tailing objects using apache commons io
			TailerListener listener = new MyTailerListener();
			tailer = Tailer.create(f1, listener, 1000);

			// wake up every two seconds to check if someone has interrupted this thread,
			// which would mean we have to close the child tailing thread too
			while (!Thread.currentThread().isInterrupted()) {
				Thread.sleep(2000);
			}
			System.out.println("Shutting down");

		} catch (InterruptedException e) { // we expect a interrupted exception when someone interrupts this thread,
											// hence no need to log or print stack trace
		} finally {
			// stop tailing child thread
			tailer.stop();
		}
	}

}
