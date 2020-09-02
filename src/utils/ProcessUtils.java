package utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

/**
 * Utilities to interact with outside processes
 * 
 * @author gokuafrica
 *
 */
public class ProcessUtils {

	/**
	 * Returns an empty process
	 * 
	 * @return
	 */
	private Process giveDefaultProcess() {
		return new Process() {

			@Override
			public int waitFor() throws InterruptedException {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public OutputStream getOutputStream() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public InputStream getInputStream() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public InputStream getErrorStream() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int exitValue() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public void destroy() {
				// TODO Auto-generated method stub

			}
		};
	}

	/**
	 * Run shell/bash command
	 * 
	 * @param cmd
	 * @return
	 * @throws java.io.IOException
	 */
	public Process execCmd(String[] cmd) {
		Process p = giveDefaultProcess();
		try {
			return Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			System.out.println("Something went wrong in execCmd");
			e.printStackTrace();
		}
		return p;
	}

	/**
	 * Wait for ten minutes or the process to end, whichever is first
	 * 
	 * @param p
	 */
	public void waitForProcessToEndInTenMinutes(Process p) {
		try {
			p.waitFor(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			System.out.println("Exception ocurred while waiting for process to end");
			e.printStackTrace();
		}
	}

}
