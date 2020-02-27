package by.kononov.fest.mail;

import java.util.ResourceBundle;

/**
 * The MailManager class.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-21
 */
class MailManager{
	private final static ResourceBundle bundle = ResourceBundle.getBundle("properties.mail");

	/**
	 * The private class constructor to hide the implicit public one.
	 */
	private MailManager() {
	}

	/**
	 * This method return string by key from mail properties file.
	 *
	 * @param key - the bundle key
	 * @return page - the string
	 */
	static String getProperty(String key) {
		return bundle.getString(key);
	}
}