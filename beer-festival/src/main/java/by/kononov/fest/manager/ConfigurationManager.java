package by.kononov.fest.manager;

import java.util.ResourceBundle;

/**
 * The ConfigurationManager class.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-17
 */
public class ConfigurationManager{
	private final static ResourceBundle bundle = ResourceBundle.getBundle("properties.config");

	/**
	 * The private class constructor is uses to hide the implicit public one.
	 */
	private ConfigurationManager() {
	}

	/**
	 * This method returns page path by key from configuration properties
	 * file.
	 *
	 * @param key - the bundle key;
	 * @return page - the string with page path;
	 */
	public static String getProperty(String key) {
		return bundle.getString(key);
	}
}
