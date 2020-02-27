package by.kononov.fest.command.impl;

import static by.kononov.fest.command.PageReceiver.PageType.HOME_PAGE;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.kononov.fest.command.BaseCommand;
import by.kononov.fest.command.PagePath;
import by.kononov.fest.command.PageReceiver;
import by.kononov.fest.controller.SessionRequestContent;
import by.kononov.fest.entity.User;
import by.kononov.fest.exception.ServiceException;
import by.kononov.fest.service.AdministratorService;
import by.kononov.fest.service.impl.AdministratorServiceImpl;

/**
 * The BlockUserCommand class; it implements the BaseCommand
 * interface; it is used to block or unblock any user.
 * <p>
 * {@link by.kononov.fest.command.BaseCommand}
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-10
 */
public class BlockUserCommand implements BaseCommand{
	final static Logger logger = LogManager.getLogger();
	private static final String USER = "user";
	private static final String USER_ID = "userId";

	@Override
	public PagePath execute(SessionRequestContent content) {
		User user = (User) content.getSessionAttributes().get(USER);
		Optional<String> optional = content.getRequestParameter(USER_ID);
		AdministratorService service = new AdministratorServiceImpl();
		try {
			service.blockUnblockUser(optional);
		} catch (ServiceException e) {
			logger.error("can't change status ", e);
		}
		String url = PageReceiver.receivePage(user, HOME_PAGE);
		return new PagePath(url);
	}
}
