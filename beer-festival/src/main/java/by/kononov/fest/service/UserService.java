package by.kononov.fest.service;

import java.util.Optional;

import by.kononov.fest.entity.Partner;
import by.kononov.fest.entity.User;
import by.kononov.fest.exception.ServiceException;

/**
 * The UserService interface.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-25
 */
public interface UserService{

	Partner getPartner() throws ServiceException;

	User deleteOrder(User user) throws ServiceException;

	User getUser();

	boolean checkLogin(Optional<String> login, Optional<String> password) throws ServiceException;

	boolean orderRegister(User user, Optional<String> comment, Optional<String> seats, Optional<String> barId)
			throws ServiceException;

	boolean register(Optional<String> optionalLogin, Optional<String> optionalPassword, Optional<String> optionalEmail,
			Optional<String> optionalRole) throws ServiceException;
}