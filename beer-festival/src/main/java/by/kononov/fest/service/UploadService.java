package by.kononov.fest.service;

import javax.servlet.http.Part;

import by.kononov.fest.entity.User;
import by.kononov.fest.exception.ServiceException;

/**
 * The UploadService interface.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-25
 */
public interface UploadService{

	/**
	 * This method is used to receive a file of avatar.
	 *
	 * @return - the string with file name of avatar;
	 */
	String receiveFileName(Part part);

	/**
	 * This method is used to update the user - adds a new avatar.
	 *
	 * @throws ServiceException if query to repository failed;
	 */
	void updateUser(User user, String filePath) throws ServiceException;
}