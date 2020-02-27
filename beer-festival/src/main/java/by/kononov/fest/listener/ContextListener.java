package by.kononov.fest.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.kononov.fest.connection.ConnectionPool;

/**
 * The ContextListener class; it implements the ServletContextListener
 * interface.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-10
 */
@WebListener
public class ContextListener implements ServletContextListener{
	static final Logger logger = LogManager.getLogger();

	/**
	 * This method uses for initialize the FestConnectionPool.
	 * <p>
	 * {@link by.kononov.fest.connection.ConnectionPool}
	 *
	 * @param event - this parameter is not used
	 * 
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ConnectionPool pool = ConnectionPool.INSTANCE;
		logger.info("connection pool {} is created and ready to work ", pool.getClass().getSimpleName());
	}

	/**
	 * This method uses for destroy the FestConnectionPool.
	 * <p>
	 * {@link by.kononov.fest.connection.ConnectionPool}
	 * 
	 * @param event - this parameter is not used
	 * 
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		ConnectionPool.INSTANCE.destroyPool();
		logger.info("connection pool is destroyed");
	}
}
