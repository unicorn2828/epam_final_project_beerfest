package by.kononov.fest.connection;

import static by.kononov.fest.connection.ConnectionData.KEY_DRIVER;
import static by.kononov.fest.connection.ConnectionData.KEY_URL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class ConnectionProvider{
	static final Logger logger = LogManager.getLogger();

	static {
		String driver = ConnectionManager.getDriver(KEY_DRIVER);
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			logger.error("can't get connection driver ", e);
			throw new ExceptionInInitializerError("can't get connection driver ");
		}
	}

	private ConnectionProvider() {
	}

	static Connection getConnection() {
		Properties properties = ConnectionManager.getProperty();
		String url = ConnectionManager.getURL(KEY_URL);
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, properties);
		} catch (SQLException e) {
			logger.error("can't get connection ", e);
		}
		return connection;
	}
}
