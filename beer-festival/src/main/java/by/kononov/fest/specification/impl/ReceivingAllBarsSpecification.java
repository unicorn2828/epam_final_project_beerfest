package by.kononov.fest.specification.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import by.kononov.fest.specification.FestSpecification;

/**
 * The ReceivingAllSpecification class; it implements the
 * FestSpecification interface.
 * <p>
 * {@link by.kononov.fest.specification.FestSpecification}
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-10
 */
public class ReceivingAllBarsSpecification implements FestSpecification{
	private final static String SQL_QUERY =
			"SELECT bar_id, available_seats, bar_description, bar_status, bar_name FROM fest.bar WHERE bar_id > 0";

	/**
	 * This method returns a PreparedStatement with the string of sql
	 * query to data base.
	 * 
	 * @param connection - the connection from connection pool
	 *                   {@link by.kononov.fest.connection.ConnectionPool};
	 * @throws SQLException, if connection or statement failed;
	 * @return the PreparedStatement object with the string of sql query;
	 */
	@Override
	public PreparedStatement specified(Connection connection) throws SQLException {
		return connection.prepareStatement(SQL_QUERY);
	}
}