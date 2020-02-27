package by.kononov.fest.connection;

/**
 * The ConnectionData class.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-17
 */
class ConnectionData{
	static final String AUTO_RECONNECT = "autoReconnect";
	static final String ENCODING = "characterEncoding";
	static final String KEY_AUTO_RECONNECT = "db.autoReconnect";
	static final String KEY_DRIVER = "db.driver";
	static final String KEY_ENCODING = "db.encoding";
	static final String KEY_PASS = "db.password";
	static final String KEY_POOL_SIZE = "db.poolsize";
	static final String KEY_URL = "db.url";
	static final String KEY_USER = "db.user";
	static final String KEY_USER_UNICODE = "db.userUnicode";
	static final String PASS = "password";
	static final String USER = "user";
	static final String USER_UNICODE = "userUnicode";

	/**
	 * The private class constructor to hide the implicit public one.
	 */
	private ConnectionData() {
	}
}