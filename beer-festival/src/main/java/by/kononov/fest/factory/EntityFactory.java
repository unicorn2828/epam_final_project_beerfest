package by.kononov.fest.factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import by.kononov.fest.entity.Entity;
import by.kononov.fest.entity.Entity.EntityType;

/**
 * The EntityFactory class; it implements the singleton pattern.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-17
 */
public class EntityFactory{
	private static final EntityFactory INSTANCE = new EntityFactory();

	/**
	 * The private class constructor is uses to hide the implicit public one.
	 */
	private EntityFactory() {
	}

	/**
	 * This method returns the only one instance of the class.
	 *
	 * @return INSTANCE - the single instance of this class;
	 */
	public static EntityFactory getInstance() {
		return INSTANCE;
	}

	/**
	 * This method returns the List of Entities.
	 *
	 * @param resultSet  - the ResultSet object;
	 * @param entityList - the empty list of Entities;
	 * @param type       - the type of Entity;
	 * @throws SQLException, if producing an entity failed;
	 * @return List<Entity> - the List of Entities;
	 */
	public List<Entity> produce(ResultSet resultSet, List<Entity> entityList, EntityType type) throws SQLException {
		switch (type) {
			case BAR:
				return BarProducer.getInstance().produceBar(resultSet, entityList);
			case ORDER:
				return OrderProducer.getInstance().produceOrder(resultSet, entityList);
			case PARTNER:
				return PartnerProducer.getInstance().producePartner(resultSet, entityList);
			case USER:
				return UserProducer.getInstance().produceUser(resultSet, entityList);
			default:
				throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
		}
	}

	Entity produce(ResultSet resultSet, EntityType type) throws SQLException {
		switch (type) {
			case BAR:
				return BarProducer.getInstance().produceBar(resultSet);
			case ORDER:
				return OrderProducer.getInstance().produceOrder(resultSet);
			default:
				throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
		}
	}
}
