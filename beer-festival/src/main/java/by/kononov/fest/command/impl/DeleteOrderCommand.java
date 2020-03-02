package by.kononov.fest.command.impl;

import static by.kononov.fest.command.PageReceiver.PageType.HOME_PAGE;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.kononov.fest.command.BaseCommand;
import by.kononov.fest.command.PagePath;
import by.kononov.fest.command.PageReceiver;
import by.kononov.fest.controller.SessionRequestContent;
import by.kononov.fest.entity.User;
import by.kononov.fest.exception.ServiceException;
import by.kononov.fest.service.UserService;
import by.kononov.fest.service.impl.UserServiceImpl;

/**
 * The DeleteOrderCommand class; it implements the BaseCommand
 * interface; it is used to delete user's order.
 * <p>
 * {@link by.kononov.fest.command.BaseCommand}
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-10
 */
public class DeleteOrderCommand implements BaseCommand{
    final static Logger logger = LogManager.getLogger();
    private static final String USER = "user";
    private UserService service;

    public DeleteOrderCommand() {
        service = new UserServiceImpl();
    }

	@Override
	public PagePath execute(SessionRequestContent content) {
		User user = (User) content.getSessionAttributes().get(USER);
		try {
			user = service.deleteOrder(user);
			content.getSessionAttributes().put(USER, user);
		} catch (ServiceException e) {
			logger.error("delete order failed ", e);
		}
		String url = PageReceiver.receivePage(user, HOME_PAGE);
		return new PagePath(url);
	}
}
