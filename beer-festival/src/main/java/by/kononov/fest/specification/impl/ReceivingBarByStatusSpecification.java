package by.kononov.fest.specification.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import by.kononov.fest.specification.FestSpecification;

/**
 * The ReceivingByStatusSpecification class; it implements the
 * FestSpecification interface.
 * <p>
 * {@link by.kononov.fest.specification.FestSpecification}
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-10
 */
public class ReceivingBarByStatusSpecification implements FestSpecification{
	private final static String SQL_QUERY =
			"SELECT bar_id, available_seats, bar_description, bar_status, bar_name FROM fest.bar WHERE bar_status = ?";
	private String status;

	/**
	 * Class constructor; it creates a new
	 * ReceivingBarByStatusSpecification with the given status of bar.
	 */
	public ReceivingBarByStatusSpecification(String status) {
		this.status = status;
	}

	/**
	 * This method returns a PreparedStatement with the string of sql
	 * query to data base.
	 * 
	 * @param connection - the connection from connection pool
	 *                   {@link by.kononov.fest.connection.ConnectionPool};
	 * @throws SQLException, if connection or statement failed;
	 * @return statement - the PreparedStatement object with the string of
	 *         sql query;
	 */
	@Override
	public PreparedStatement specified(Connection connection) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(SQL_QUERY);
		statement.setString(1, status);
		return statement;
	}
}