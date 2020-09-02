package utils;

import java.util.Map;
import java.util.Optional;

/**
 * To handle environment properties
 * @author gokuafrica
 *
 */
public class EnvironmentVarUtils {
	
	private Map<String,String> env = System.getenv();
	
	/**
	 * Returns given property and if not present returns empty string
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {
		return Optional.ofNullable(env.get(key)).orElseGet(()->"");
	}
}
