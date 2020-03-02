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
import by.kononov.fest.manager.MessageManager;
import by.kononov.fest.service.UserService;
import by.kononov.fest.service.impl.UserServiceImpl;

/**
 * The OrderRegistrationCommand class; it implements the BaseCommand
 * interface; it is used to register the order of user.
 * <p>
 * {@link by.kononov.fest.command.BaseCommand}
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-10
 */
public class OrderRegistrationCommand implements BaseCommand{
	final static Logger logger = LogManager.getLogger();
    private static final String MESSAGE_TEXT = "message.booking_order";
    private static final String MESSAGE = "message";
    private static final String USER = "user";
    private static final String COMMENT = "comment";
    private static final String BAR_ID = "bar";
    private static final String SEATS = "seats";
    private UserService service;

    public OrderRegistrationCommand() {
        service = new UserServiceImpl();
    }

	@Override
	public PagePath execute(SessionRequestContent content) {
		User user = (User) content.getSessionAttributes().get(USER);
		Optional<String> comment = content.getRequestParameter(COMMENT);
		Optional<String> seats = content.getRequestParameter(SEATS);
		Optional<String> barId = content.getRequestParameter(BAR_ID);
		try {
			if (!service.orderRegister(user, comment, seats, barId)) {
				content.getRequestAttributes().put(MESSAGE, MessageManager.getProperty(MESSAGE_TEXT));
			}
		} catch (ServiceException e) {
			logger.error("order registraion failed ", e);
		}
		String url = PageReceiver.receivePage(user, HOME_PAGE);
		return new PagePath(url);
	}
}
