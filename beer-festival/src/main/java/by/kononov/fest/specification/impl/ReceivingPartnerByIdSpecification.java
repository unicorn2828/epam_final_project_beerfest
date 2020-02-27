package by.kononov.fest.specification.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import by.kononov.fest.specification.FestSpecification;

/**
 * The ReceivingByIdSpecification class; it implements
 * FestSpecification interface.
 * <p>
 * {@link by.kononov.fest.specification.FestSpecification}
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-08
 */
public class ReceivingPartnerByIdSpecification implements FestSpecification{
	private final static String SQL_QUERY =
			"SELECT partner_id, name, description, partner_bar FROM fest.partner WHERE partner_id = ?";
	private String partnerId;

	/**
	 * Class constructor; it creates a new
	 * ReceivingPartnerByIdSpecification with the given id of partner.
	 */
	public ReceivingPartnerByIdSpecification(String partnerId) {
		this.partnerId = partnerId;
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
		statement.setLong(1, Long.parseLong(partnerId));
		return statement;
	}
}