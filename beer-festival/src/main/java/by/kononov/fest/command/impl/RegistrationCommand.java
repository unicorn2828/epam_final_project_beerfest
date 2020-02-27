package by.kononov.fest.command.impl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.kononov.fest.command.BaseCommand;
import by.kononov.fest.command.PagePath;
import by.kononov.fest.command.PageReceiver;
import by.kononov.fest.command.PageReceiver.PageType;
import by.kononov.fest.controller.SessionRequestContent;
import by.kononov.fest.entity.User;
import by.kononov.fest.entity.User.UserRole;
import by.kononov.fest.exception.ServiceException;
import by.kononov.fest.mail.MailSender;
import by.kononov.fest.manager.ConfigurationManager;
import by.kononov.fest.manager.MessageManager;
import by.kononov.fest.service.UserService;
import by.kononov.fest.service.impl.UserServiceImpl;

/**
 * The RegistrationCommand class; it implements the BaseCommand
 * interface; it is used to register a new user.
 * <p>
 * {@link by.kononov.fest.command.BaseCommand}
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-10
 */
public class RegistrationCommand implements BaseCommand{
	static final Logger logger = LogManager.getLogger();
	private static final String PAGE_LOGIN = "path.page.login";
	private static final String USER = "user";
	private static final String PARAM_LOGIN = "login";
	private static final String PARAM_PASSWORD = "password";
	private static final String PARAM_EMAIL = "email";
	private static final String PARAM_ROLE = "role";
	private static final String MESSAGE_REGISTRATION = "message.login_change";
	private static final String MESSAGE = "message";
	private static final String PAGE_REGISTRATION = "path.page.partner_register";

	@Override
	public PagePath execute(SessionRequestContent content) {
		User user = null;
		String page = ConfigurationManager.getProperty(PAGE_LOGIN);
		Optional<String> optionalLogin = content.getRequestParameter(PARAM_LOGIN);
		Optional<String> optionalPassword = content.getRequestParameter(PARAM_PASSWORD);
		Optional<String> optionalEmail = content.getRequestParameter(PARAM_EMAIL);
		Optional<String> optionalRole = content.getRequestParameter(PARAM_ROLE);
		UserService service = new UserServiceImpl();
		try {
			if (service.register(optionalLogin, optionalPassword, optionalEmail, optionalRole)) {
				user = service.getUser();
				content.getSessionAttributes().put(USER, user);
				page = PageReceiver.receivePage(user, PageType.HOME_PAGE);
			} else {
				content.getRequestAttributes().put(MESSAGE, MessageManager.getProperty(MESSAGE_REGISTRATION));
				return new PagePath(page);
			}
			UserRole roler = UserRole.valueOf(optionalRole.get().toUpperCase());
			if (UserRole.PARTNER.equals(roler)) {
				page = ConfigurationManager.getProperty(PAGE_REGISTRATION);
			}
		} catch (ServiceException e) {
			logger.error("registration failed ", e);
			page = ConfigurationManager.getProperty(PAGE_LOGIN);
		}
		if (user != null && UserRole.USER.equals(user.getRole()) && !user.getEmail().isEmpty()) {
			try {
				MailSender.sendMail(user.getEmail());
			} catch (ServiceException e) {
				logger.error("mail sending failed ", e);
			}
		}
		return new PagePath(page);
	}
}