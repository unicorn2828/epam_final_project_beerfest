package by.kononov.fest.specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The FestSpecification interface.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-10
 */
@FunctionalInterface
public interface FestSpecification{

	/**
	 * This method returns a PreparedStatement with the string of sql
	 * query to data base.
	 *
	 * @param connection - the connection from connection pool
	 *                   {@link by.kononov.fest.connection.ConnectionPool};
	 * @throws SQLException, if connection or statement failed;
	 * @return preparedStatement - the statement object with the string of
	 *         sql query;
	 */
	PreparedStatement specified(Connection connection) throws SQLException;
}