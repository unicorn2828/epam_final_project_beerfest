package by.kononov.fest.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.kononov.fest.connection.ConnectionPool;
import by.kononov.fest.entity.Bar;
import by.kononov.fest.entity.Entity;
import by.kononov.fest.entity.Entity.EntityType;
import by.kononov.fest.exception.RepositoryException;
import by.kononov.fest.factory.EntityFactory;
import by.kononov.fest.specification.FestSpecification;

/**
 * The BarRepositoryImpl class; it extends BaseRepository.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-02
 */
public class BarRepository extends BaseRepository{
	private final static BaseRepository INSTANCE = new BarRepository();
	public static final String SQL_INSERT_BAR =
			"INSERT INTO fest.bar (bar_id, available_seats, bar_description, bar_status, bar_name) VALUES (?, ?, ?, ?, ?)";
	public static final String SQL_DELETE_BAR = "DELETE FROM fest.bar WHERE user_id = ?";
	public static final String SQL_UPDATE_BAR =
			"UPDATE fest.bar SET available_seats = ?, bar_description = ?, bar_status = ?, bar_name = ? WHERE bar_id = ?";

	private BarRepository() {
	}

	public static BaseRepository getInstance() {
		return INSTANCE;
	}

	@Override
	public void addEntity(Entity entity) throws RepositoryException {
		throw new UnsupportedOperationException("addEntity method shouldn't do anything");
	}

	@Override
	public void deleteEntity(Entity entity) throws RepositoryException {
		throw new UnsupportedOperationException("deleteEntity method shouldn't do anything");
	}

	@Override
	public void updateEntity(Entity bar) throws RepositoryException {
		try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_BAR)) {
			statement.setInt(1, ((Bar) bar).getSeats());
			statement.setString(2, ((Bar) bar).getDescription());
			statement.setInt(3, ((Bar) bar).getStatus().ordinal());
			statement.setString(4, ((Bar) bar).getName());
			statement.setLong(5, ((Bar) bar).getBarId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RepositoryException("update bar failed ", e);
		}
	}

	@Override
	public List<Entity> query(FestSpecification specification) throws RepositoryException {
		List<Entity> barList = new ArrayList<>();
		try (Connection connection = ConnectionPool.INSTANCE.takeConnection();
				PreparedStatement statement = specification.specified(connection);
				ResultSet resultSet = statement.executeQuery()) {
			EntityFactory.getInstance().produce(resultSet, barList, EntityType.BAR);
		} catch (SQLException e) {
			throw new RepositoryException("select bar from db failed ", e);
		}
		return barList;
	}
}