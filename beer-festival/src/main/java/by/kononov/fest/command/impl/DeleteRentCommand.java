package by.kononov.fest.command.impl;

import static by.kononov.fest.command.PageReceiver.PageType.HOME_PAGE;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.kononov.fest.command.BaseCommand;
import by.kononov.fest.command.PagePath;
import by.kononov.fest.command.PageReceiver;
import by.kononov.fest.controller.SessionRequestContent;
import by.kononov.fest.entity.Partner;
import by.kononov.fest.entity.User;
import by.kononov.fest.exception.ServiceException;
import by.kononov.fest.service.PartnerService;
import by.kononov.fest.service.impl.PartnerServiceImpl;

/**
 * The DeleteRentCommand class; it implements the BaseCommand
 * interface; it is used to early completion of the rent by partner.
 * <p>
 * {@link by.kononov.fest.command.BaseCommand}
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-10
 */
public class DeleteRentCommand implements BaseCommand{
	final static Logger logger = LogManager.getLogger();
	private static final String USER = "user";
	private static final String PARTNER = "partner";
	private PartnerService service;

	public DeleteRentCommand(){
		service = new PartnerServiceImpl();
	}

	@Override
	public PagePath execute(SessionRequestContent content) {
		User user = (User) content.getSessionAttributes().get(USER);
		Partner partner = (Partner) content.getSessionAttributes().get(PARTNER);
		try {
			user = service.deleteRent(user, partner);
			content.getSessionAttributes().put(USER, user);
		} catch (ServiceException e) {
			logger.error("delete rent failed ", e);
		}
		String url = PageReceiver.receivePage(user, HOME_PAGE);
		return new PagePath(url);
	}
}
