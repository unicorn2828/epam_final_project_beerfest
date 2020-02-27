package by.kononov.fest.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.kononov.fest.connection.ConnectionPool;
import by.kononov.fest.entity.Entity;
import by.kononov.fest.entity.User;
import by.kononov.fest.entity.Entity.EntityType;
import by.kononov.fest.exception.RepositoryException;
import by.kononov.fest.factory.EntityFactory;
import by.kononov.fest.security.Encription;
import by.kononov.fest.specification.FestSpecification;
import by.kononov.fest.specification.impl.ReceivingAmountAllUsersSpecification;

public class UserRepository extends BaseRepository{
	static final Logger logger = LogManager.getLogger();
	private final static BaseRepository INSTANCE = new UserRepository();
	public static final String ROW_COUNT = "rowcount";
	public static final String SQL_INSERT_USER =
			"INSERT INTO fest.user (login, password, email, user_role, user_status, user_order, avatar_url) VALUES (?, ?, ?, ?, ?, ?, ?)";
	public static final String SQL_DELETE_USER = "DELETE FROM fest.user WHERE user_id = ?";
	public static final String SQL_UPDATE_USER =
			"UPDATE fest.user SET login = ?, user_role = ?, user_status = ?, user_order = ?, avatar_url = ? WHERE user_id = ?";

	private UserRepository() {
	}

	public static BaseRepository getInstance() {
		return INSTANCE;
	}

	@Override
	public void addEntity(Entity user) throws RepositoryException {
		try (Connection connection = ConnectionPool.INSTANCE.takeConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER)) {
			statement.setString(1, ((User) user).getLogin());
			statement.setString(2, Encription.encode(((User) user).getPassword()));
			statement.setString(3, ((User) user).getEmail());
			statement.setInt(4, ((User) user).getRole().ordinal());
			statement.setInt(5, User.UserStatus.VALID.ordinal());
			statement.setLong(6, ((User) user).getOrder().getOrderId());
			statement.setString(7, ((User) user).getAvatarUrl());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RepositoryException("creating user failed ", e);
		}
	}

	@Override
	public void deleteEntity(Entity user) throws RepositoryException {
		throw new UnsupportedOperationException("deleteEntity method shouldn't do anything");
	}

	@Override
	public void updateEntity(Entity user) throws RepositoryException {
		try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER)) {
			statement.setString(1, ((User) user).getLogin());
			statement.setInt(2, ((User) user).getRole().ordinal());
			statement.setInt(3, ((User) user).getStatus().ordinal());
			statement.setLong(4, ((User) user).getOrder().getOrderId());
			statement.setString(5, ((User) user).getAvatarUrl());
			statement.setLong(6, ((User) user).getUserId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RepositoryException("update user failed ", e);
		}
	}

	@Override
	public List<Entity> query(FestSpecification specification) throws RepositoryException {
		List<Entity> userList = new ArrayList<>();
		try (Connection connection = ConnectionPool.INSTANCE.takeConnection();
				PreparedStatement statement = specification.specified(connection);
				ResultSet resultSet = statement.executeQuery()) {
			if (specification instanceof ReceivingAmountAllUsersSpecification) {
				resultSet.next();
				int count = resultSet.getInt(ROW_COUNT);
				userList = Arrays.asList(new Entity[count]);
			} else {
				EntityFactory.getInstance().produce(resultSet, userList, EntityType.USER);
			}
		} catch (

		SQLException e) {
			throw new RepositoryException("select user from db failed ", e);
		}
		return userList;
	}
}