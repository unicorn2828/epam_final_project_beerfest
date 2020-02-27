package by.kononov.fest.specification.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import by.kononov.fest.specification.FestSpecification;

/**
 * The ReceivingUserByIdSpecification class; it implements
 * FestSpecification interface.
 * <p>
 * {@link by.kononov.fest.specification.FestSpecification}
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-02
 */
public class ReceivingUserByIdSpecification implements FestSpecification{
	private final static String SQL_QUERY =
			"SELECT user_id, login, password, email, user_role, user_status, order_id, bar_id, bar_name, "
					+ "bar_description, bar_status, available_seats, order_seats, order_comment, avatar_url "
					+ "FROM fest.user LEFT JOIN fest.order ON user.user_order = order.order_id "
					+ "LEFT JOIN fest.bar ON bar.bar_id = order.order_bar WHERE user.user_id = ?";
	private String userId;

	/**
	 * Class constructor; it creates a new ReceivingUserByIdSpecification
	 * with the given id of user.
	 */
	public ReceivingUserByIdSpecification(String userId) {
		this.userId = userId;
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
		statement.setLong(1, Long.parseLong(userId));
		return statement;
	}
}