package by.kononov.fest.connection;

import static by.kononov.fest.connection.ConnectionData.AUTO_RECONNECT;
import static by.kononov.fest.connection.ConnectionData.ENCODING;
import static by.kononov.fest.connection.ConnectionData.KEY_AUTO_RECONNECT;
import static by.kononov.fest.connection.ConnectionData.KEY_ENCODING;
import static by.kononov.fest.connection.ConnectionData.KEY_PASS;
import static by.kononov.fest.connection.ConnectionData.KEY_USER;
import static by.kononov.fest.connection.ConnectionData.KEY_USER_UNICODE;
import static by.kononov.fest.connection.ConnectionData.PASS;
import static by.kononov.fest.connection.ConnectionData.USER;
import static by.kononov.fest.connection.ConnectionData.USER_UNICODE;

import java.util.Properties;
import java.util.ResourceBundle;

class ConnectionManager{
	private final static ResourceBundle bundle = ResourceBundle.getBundle("properties.connection");

	private ConnectionManager() {
	}

	static Properties getProperty() {
		String user = bundle.getString(KEY_USER);
		String password = bundle.getString(KEY_PASS);
		String encoding = bundle.getString(KEY_ENCODING);
		String userUnicode = bundle.getString(KEY_USER_UNICODE);
		String autoReconnect = bundle.getString(KEY_AUTO_RECONNECT);
		Properties properties = new Properties();
		properties.put(USER, user);
		properties.put(PASS, password);
		properties.put(ENCODING, encoding);
		properties.put(USER_UNICODE, userUnicode);
		properties.put(AUTO_RECONNECT, autoReconnect);
		return properties;
	}

	static String getURL(String key) {
		return bundle.getString(key);
	}

	static String getDriver(String key) {
		return bundle.getString(key);
	}

	static String getPoolSize(String key) {
		return bundle.getString(key);
	}
}