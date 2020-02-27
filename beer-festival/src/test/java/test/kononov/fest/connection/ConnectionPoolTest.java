package test.kononov.fest.connection;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import by.kononov.fest.connection.ConnectionPool;

public class ConnectionPoolTest{

	@AfterTest
	public void tierDown() {
		ConnectionPool.INSTANCE.destroyPool();
	}

	@Test(description = "positive releaseConnection method test")
	public void releaseConnectionTest() {
		try (Connection connection = ConnectionPool.INSTANCE.takeConnection()) {
			boolean isReleased = ConnectionPool.INSTANCE.releaseConnection(connection);
			assertTrue(isReleased);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test(description = "positive takeConnection method test")
	public void takeConnectionTest() {
		try (Connection connection = ConnectionPool.INSTANCE.takeConnection()) {
			assertNotNull(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}