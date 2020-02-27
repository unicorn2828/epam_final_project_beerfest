package by.kononov.fest.service;

import by.kononov.fest.entity.Partner;
import by.kononov.fest.entity.User;
import by.kononov.fest.exception.ServiceException;

/**
 * The PartnerService interface.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-25
 */
public interface PartnerService{

	boolean checkRegistration(String name) throws ServiceException;

	boolean rentRegister(User user, Partner partner, String barName, String comment, String barId)
			throws ServiceException;

	Partner registerPartner(long partnerId, String name, String description) throws ServiceException;

	User deleteRent(User user, Partner partner) throws ServiceException;
}