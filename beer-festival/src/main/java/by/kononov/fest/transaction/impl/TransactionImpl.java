package by.kononov.fest.transaction.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.kononov.fest.connection.ConnectionPool;
import by.kononov.fest.repository.Repository;
import by.kononov.fest.transaction.Transaction;

/**
 * The Transaction class.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-20
 */
public class TransactionImpl implements Transaction{
	static final Logger logger = LogManager.getLogger();
	private static Lock lock = new ReentrantLock();
	private Connection connection;

	@Override
	public void beginTransaction(Repository... repositories) {
		lock.lock();
		if (connection == null) {
			connection = ConnectionPool.INSTANCE.takeConnection();
		}
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			logger.error("set auto commit failed ", e);
		}
		Arrays.asList(repositories).forEach(repository -> repository.setConnection(connection));
		lock.unlock();
	}

	@Override
	public void commit() {
		lock.lock();
		try {
			connection.commit();
		} catch (SQLException e) {
			logger.error("commit failed ", e);
		}
		lock.unlock();
	}

	@Override
	public void endTransaction() {
		lock.lock();
		if (connection != null) {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				logger.error("set auto commit failed ", e);
			}
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error("connection close failed ", e);
			}
			connection = null;
		}
		lock.unlock();
	}

	@Override
	public void rollback() {
		lock.lock();
		try {
			connection.rollback();
		} catch (SQLException e) {
			logger.error("rollback failed ", e);
		}
		lock.unlock();
	}
}
