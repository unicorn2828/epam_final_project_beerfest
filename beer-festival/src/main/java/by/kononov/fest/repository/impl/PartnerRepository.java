package by.kononov.fest.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.kononov.fest.connection.ConnectionPool;
import by.kononov.fest.entity.Entity;
import by.kononov.fest.entity.Partner;
import by.kononov.fest.entity.Entity.EntityType;
import by.kononov.fest.exception.RepositoryException;
import by.kononov.fest.factory.EntityFactory;
import by.kononov.fest.specification.FestSpecification;

public class PartnerRepository extends BaseRepository{
	private final static BaseRepository INSTANCE = new PartnerRepository();
	static final Logger logger = LogManager.getLogger();
	public static final String SQL_INSERT_PARTNER =
			"INSERT INTO fest.partner (partner_id, name, description, partner_bar) VALUES (?, ?, ?, ?)";
	public static final String SQL_DELETE_PARTNER = "DELETE FROM fest.user WHERE user_id = ?";
	public static final String SQL_UPDATE_PARTNER =
			"UPDATE fest.partner SET name = ?, description = ?, partner_bar = ? WHERE partner_id = ?";

	private PartnerRepository() {
	}

	public static BaseRepository getInstance() {
		return INSTANCE;
	}

	@Override
	public void addEntity(Entity partner) throws RepositoryException {
		int defaultBar = 0;
		try (Connection connection = ConnectionPool.INSTANCE.takeConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_INSERT_PARTNER)) {
			statement.setLong(1, ((Partner) partner).getPartnerId());
			statement.setString(2, ((Partner) partner).getName());
			statement.setString(3, ((Partner) partner).getDescription());
			statement.setLong(4, defaultBar);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RepositoryException("creating partner failed ", e);
		}
	}

	@Override
	public void deleteEntity(Entity partner) throws RepositoryException {
		throw new UnsupportedOperationException("deleteEntity method shouldn't do anything");
	}

	@Override
	public void updateEntity(Entity partner) throws RepositoryException {
		try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_PARTNER)) {
			statement.setString(1, ((Partner) partner).getName());
			statement.setString(2, ((Partner) partner).getDescription());
			statement.setLong(3, ((Partner) partner).getBar());
			statement.setLong(4, ((Partner) partner).getPartnerId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RepositoryException("update partner failed ", e);
		}
	}

	@Override
	public List<Entity> query(FestSpecification specification) throws RepositoryException {
		List<Entity> partnerList = new ArrayList<>();
		try (Connection connection = ConnectionPool.INSTANCE.takeConnection();
				PreparedStatement statement = specification.specified(connection);
				ResultSet resultSet = statement.executeQuery()) {
			EntityFactory.getInstance().produce(resultSet, partnerList, EntityType.PARTNER);
		} catch (SQLException e) {
			throw new RepositoryException("select user from db failed ", e);
		}
		return partnerList;
	}
}