package by.kononov.fest.specification.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import by.kononov.fest.specification.FestSpecification;

/**
 * The ReceivingUserByIdSpecification class; it implements the
 * FestSpecification interface.
 * <p>
 * {@link by.kononov.fest.specification.FestSpecification}
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-02
 */
public class ReceivingAllOrdersSpecification implements FestSpecification{
	private final static String SQL_QUERY =
			"SELECT user_id, login, password, email, user_role, user_status, order_id, bar_id, bar_name, "
					+ "bar_description, bar_status, available_seats, order_seats, order_comment, avatar_url "
					+ "FROM fest.user LEFT JOIN fest.order ON user.user_order = order.order_id "
					+ "LEFT JOIN fest.bar ON bar.bar_id = order.order_bar WHERE user.user_order > 0 ";

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