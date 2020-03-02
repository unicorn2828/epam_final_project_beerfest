package by.kononov.fest.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.kononov.fest.connection.ConnectionPool;
import by.kononov.fest.entity.Entity;
import by.kononov.fest.entity.Order;
import by.kononov.fest.entity.Entity.EntityType;
import by.kononov.fest.exception.RepositoryException;
import by.kononov.fest.factory.EntityFactory;
import by.kononov.fest.specification.FestSpecification;

/**
 * The OrderRepositoryImpl class; it extends BaseRepository.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-02
 */
public class OrderRepository extends BaseRepository{
	public static final String SQL_INSERT_ORDER =
			"INSERT INTO fest.order (order_id, order_bar, order_seats, order_comment) VALUES (?, ?, ?, ?)";
	public static final String SQL_DELETE_ORDER = "DELETE FROM fest.order WHERE order_id = ?";
	public static final String SQL_UPDATE_ORDER =
			"UPDATE fest.order SET order_bar = ?, order_seats = ?, order_comment = ? WHERE order_id = ?";

	@Override
	public void addEntity(Entity order) throws RepositoryException {
		try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_ORDER)) {
			statement.setLong(1, ((Order) order).getOrderId());
			statement.setLong(2, ((Order) order).getBar().getBarId());
			statement.setInt(3, ((Order) order).getSeats());
			statement.setString(4, ((Order) order).getComment());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RepositoryException("update order failed ", e);
		}
	}

	@Override
	public void deleteEntity(Entity order) throws RepositoryException {
		try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ORDER)) {
			statement.setLong(1, ((Order) order).getOrderId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RepositoryException("removing order failed ", e);
		}
	}

	@Override
	public void updateEntity(Entity order) throws RepositoryException {
		try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ORDER)) {
			statement.setLong(1, ((Order) order).getBar().getBarId());
			statement.setInt(2, ((Order) order).getSeats());
			statement.setString(3, ((Order) order).getComment());
			statement.setLong(4, ((Order) order).getOrderId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RepositoryException("update order failed ", e);
		}
	}

	@Override
	public List<Entity> query(FestSpecification specification) throws RepositoryException {
		List<Entity> orderList = new ArrayList<>();
		try (Connection connection = ConnectionPool.INSTANCE.takeConnection();
				PreparedStatement statement = specification.specified(connection);
				ResultSet resultSet = statement.executeQuery()) {
			EntityFactory.getInstance().produce(resultSet, orderList, EntityType.ORDER);
		} catch (SQLException e) {
			throw new RepositoryException("select order from db failed ", e);
		}
		return orderList;
	}
}
