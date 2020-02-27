package by.kononov.fest.connection;

import static by.kononov.fest.connection.ConnectionData.KEY_POOL_SIZE;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Enumeration;
import java.util.Optional;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The ConnectionPool enum class.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-10
 */
public enum ConnectionPool {
	INSTANCE;

	static final Logger logger = LogManager.getLogger();
	private static final long MINUTES_10 = 600_000L;
	private BlockingQueue<ProxyConnection> availableConnections;
	private Queue<ProxyConnection> givenAwayConnections;
	private TimerTask repeatedTask;
	private int poolSize;

	/**
	 * The private class constructor; it calls the init method.
	 */
	private ConnectionPool() {
		init();
	}

	/**
	 * The init method; it initializes the connection pool.
	 *
	 * @return proxyConnection - the connection from connection pool
	 */
	private void init() {
		poolSize = Integer.parseInt(ConnectionManager.getPoolSize(KEY_POOL_SIZE));
		availableConnections = new LinkedBlockingQueue<>(poolSize);
		givenAwayConnections = new ArrayDeque<>();
		for (int i = 0; i < poolSize; i++) {
			Optional<Connection> optional = ConnectionProvider.getConnection();
			if (optional.isPresent()) {
				ProxyConnection proxyConnection = new ProxyConnection(optional.get());
				availableConnections.offer(proxyConnection);
			}
		}
		if (!availableConnections.isEmpty()) {
			checkPoolWholenessObserver();
		} else {
			throw new ExceptionInInitializerError("connection pool doesn't exist");
		}
	}

	/**
	 * This method takes the connection from the connection pool.
	 *
	 * @return proxyConnection - the connection from connection pool
	 */
	public Connection takeConnection() {
		ProxyConnection proxyConnection = null;
		try {
			proxyConnection = availableConnections.take();
			givenAwayConnections.offer(proxyConnection);
		} catch (InterruptedException e) {
			logger.error("the thread can't sleep: ", e);
			Thread.currentThread().interrupt();
		}
		return proxyConnection;
	}

	/**
	 * This method returns the connection to the connection pool.
	 *
	 * @param connection - the connection for return
	 */
	public boolean releaseConnection(Connection connection) {
		boolean isReleased = false;
		if (connection instanceof ProxyConnection) {
			try {
				if (!connection.getAutoCommit()) {
					connection.setAutoCommit(true);
				}
			} catch (SQLException e) {
				logger.error("can't set auto commit ", e);
			}
			ProxyConnection proxyConnection = (ProxyConnection) connection;
			givenAwayConnections.remove(proxyConnection);
			isReleased = availableConnections.offer(proxyConnection);
		} else {
			logger.warn("incorrect type of connection {}", connection);
		}
		return isReleased;
	}

	/**
	 * This method destroys the connection pool. It calls only one time
	 * then the program is ended.
	 */
	public void destroyPool() {
		for (int i = 0; i < poolSize; i++) {
			try {
				availableConnections.take().relevantClose();
			} catch (SQLException e) {
				logger.error("can't close connection ", e);
			} catch (InterruptedException e) {
				logger.error("the thread can't sleep ", e);
				Thread.currentThread().interrupt();
			}
		}
		repeatedTask.cancel();
		deregisterDrivers();
	}

	/**
	 * This method unregisters the drivers of db. It calls only one time
	 * then the program is ended.
	 */
	private void deregisterDrivers() {
		Enumeration<Driver> driverManager = DriverManager.getDrivers();
		while (driverManager.hasMoreElements()) {
			Driver driver = driverManager.nextElement();
			try {
				DriverManager.deregisterDriver(driver);
			} catch (SQLException e) {
				logger.error("the driver manager is not deregisted: ", e);
			}
		}
	}

	/**
	 * This method checks every 10 minutes the wholeness of connection
	 * pool.
	 */
	private void checkPoolWholenessObserver() {
		if (repeatedTask == null) {
			repeatedTask = new TimerTask(){
				@Override
				public void run() {
					if ((givenAwayConnections.size() + availableConnections.size()) < poolSize) {
						Optional<Connection> optional = ConnectionProvider.getConnection();
						if (optional.isPresent()) {
							ProxyConnection proxyConnection = new ProxyConnection(optional.get());
							availableConnections.add(proxyConnection);
						}
					}
				}
			};
			Timer timer = new Timer();
			long delay = MINUTES_10;
			long period = MINUTES_10;
			timer.scheduleAtFixedRate(repeatedTask, delay, period);
		}
	}
}