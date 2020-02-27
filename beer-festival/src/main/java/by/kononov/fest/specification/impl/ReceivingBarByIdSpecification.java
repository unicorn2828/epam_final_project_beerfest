package by.kononov.fest.specification.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import by.kononov.fest.specification.FestSpecification;

/**
 * The ReceivingByIdSpecification class; it implements the
 * FestSpecification interface.
 * <p>
 * {@link by.kononov.fest.specification.FestSpecification}
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-10
 */
public class ReceivingBarByIdSpecification implements FestSpecification{
	private final static String SQL_QUERY =
			"SELECT bar_id, available_seats, bar_description, bar_status, bar_name FROM fest.bar WHERE bar_id = ?";
	private String barId;

	/**
	 * Class constructor; it creates a new ReceivingBarByIdSpecification
	 * with the given id of bar.
	 */
	public ReceivingBarByIdSpecification(String barId) {
		this.barId = barId;
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
		statement.setLong(1, Long.parseLong(barId));
		return statement;
	}
}