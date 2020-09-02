package tailer;

import org.apache.commons.io.input.TailerListenerAdapter;

/**
 * Custom tailing logic implementation
 * @author gokuafrica
 *
 */
public class MyTailerListener extends TailerListenerAdapter {
	@Override
	public void handle(String line) {
		// TODO Auto-generated method stub
		super.handle(line);
		System.out.println(line);
	}
}
