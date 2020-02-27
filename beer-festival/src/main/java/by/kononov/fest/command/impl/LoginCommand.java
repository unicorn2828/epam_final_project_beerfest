package by.kononov.fest.command.impl;

import static by.kononov.fest.command.PageReceiver.PageType.HOME_PAGE;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.kononov.fest.command.BaseCommand;
import by.kononov.fest.command.PagePath;
import by.kononov.fest.command.PageReceiver;
import by.kononov.fest.controller.SessionRequestContent;
import by.kononov.fest.entity.Partner;
import by.kononov.fest.entity.User;
import by.kononov.fest.entity.User.UserRole;
import by.kononov.fest.exception.ServiceException;
import by.kononov.fest.manager.ConfigurationManager;
import by.kononov.fest.manager.MessageManager;
import by.kononov.fest.service.UserService;
import by.kononov.fest.service.impl.UserServiceImpl;

/**
 * The LoginCommand class; it implements the BaseCommand interface; it
 * is used for login and password confirmation.
 * <p>
 * {@link by.kononov.fest.command.BaseCommand}
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-10
 */
public class LoginCommand implements BaseCommand{
	static final Logger logger = LogManager.getLogger();
	private static final String MESSAGE_TEXT = "message.login_error";
	private static final String MESSAGE = "message";
	private static final String PAGE_LOGIN = "path.page.login";
	private static final String PAGE_BLOCK = "/jsp/block.jsp";
	private static final String PARAM_LOGIN = "login";
	private static final String PARAM_PASSWORD = "password";
	private static final String PARTNER = "partner";
	private static final String USER = "user";

	@Override
	public PagePath execute(SessionRequestContent content) {
		String page = ConfigurationManager.getProperty(PAGE_LOGIN);
		Optional<String> login = content.getRequestParameter(PARAM_LOGIN);
		Optional<String> password = content.getRequestParameter(PARAM_PASSWORD);
		UserService service = new UserServiceImpl();
		try {
			if (service.checkLogin(login, password)) {
				User user = service.getUser();
				content.getSessionAttributes().put(USER, user);
				if (UserRole.PARTNER.equals(user.getRole())) {
					Partner partner = service.getPartner();
					content.getSessionAttributes().put(PARTNER, partner);
				}
				page = PageReceiver.receivePage(user, HOME_PAGE);
				if (PAGE_BLOCK.equals(page)) {
					content.invalidateSession();
				}
			} else {
				content.getRequestAttributes().put(MESSAGE, MessageManager.getProperty(MESSAGE_TEXT));
			}
		} catch (ServiceException e) {
			logger.error("log in command failed ", e);
		}
		return new PagePath(page);
	}
}
