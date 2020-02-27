package by.kononov.fest.manager;

import java.util.ResourceBundle;

/**
 * The MessageManager class.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-17
 */
public class MessageManager{
	private final static ResourceBundle bundle = ResourceBundle.getBundle("properties.messages");

	/**
	 * The private class constructor is uses to hide the implicit public one.
	 */
	private MessageManager() {
	}

	/**
	 * This method returns message by key from message properties file.
	 *
	 * @param key - the bundle key
	 * @return message - the string with message
	 */
	public static String getProperty(String key) {
		return bundle.getString(key);
	}
}
