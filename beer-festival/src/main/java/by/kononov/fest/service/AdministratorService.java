package by.kononov.fest.service;

import java.util.List;
import java.util.Optional;

import by.kononov.fest.entity.Entity;
import by.kononov.fest.exception.ServiceException;

/**
 * The AdministratorService interface.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-25
 */
public interface AdministratorService{

	/**
	 * This method is used to receive the list of bars.
	 *
	 * @throws ServiceException if query to repository failed;
	 * @return - a list of bars;
	 */
	List<Entity> receiveBar() throws ServiceException;

	/**
	 * This method is used to receive the list of orders.
	 *
	 * @throws ServiceException if query to repository failed;
	 * @return - a list of orders;
	 */
	List<Entity> receiveOrder() throws ServiceException;

	/**
	 * This method is used to receive the list of partners.
	 *
	 * @throws ServiceException if query to repository failed;
	 * @return - a list of partners;
	 */
	List<Entity> receivePartner() throws ServiceException;

	/**
	 * This method is used to receive the list of users.
	 *
	 * @throws ServiceException if query to repository failed;
	 * @return - a list of users;
	 */
	List<Entity> receiveUser(String pageNumber) throws ServiceException;

	/**
	 * This method is used to get the amount of pagination pages.
	 *
	 * @throws ServiceException if query to repository failed;
	 * @return - amount of pages;
	 */
	int countPages() throws ServiceException;

	/**
	 * This method is used to block or unblock a user.
	 *
	 * @param optional - the string with userId;
	 * @throws ServiceException if block(unblock) user failed;
	 */
	void blockUnblockUser(Optional<String> optional) throws ServiceException;
}
