package by.kononov.fest.repository;

import java.sql.Connection;
import java.util.List;

import by.kononov.fest.entity.Entity;
import by.kononov.fest.exception.RepositoryException;
import by.kononov.fest.specification.FestSpecification;

/**
 * The Repository interface.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-15
 */
public interface Repository{

	/**
	 * This method is used to add an entity to the database.
	 *
	 * @param entity - the entity for add;
	 * @throws RepositoryException if adding an entity failed;
	 */
	void addEntity(Entity entity) throws RepositoryException;

	/**
	 * This method is used to delete an entity from the database.
	 *
	 * @param entity - the entity for delete;
	 * @throws RepositoryException if entity deletion failed;
	 */
	void deleteEntity(Entity entity) throws RepositoryException;

	/**
	 * This method is used to update an entity in the database.
	 *
	 * @param entity - the entity for update;
	 * @throws RepositoryException, if updating the entity failed;
	 */
	void updateEntity(Entity entity) throws RepositoryException;

	/**
	 * This method is used to query to the database.
	 *
	 * @param specification - the specification for query to db
	 * @return List<Entity> - the list of entity
	 * @throws RepositoryException, if query to db failed;
	 */
	List<Entity> query(FestSpecification specification) throws RepositoryException;

	/**
	 * This method is used to set the connection to the repository.
	 *
	 * @param connection - the connection to db
	 */
	void setConnection(Connection connection);
}