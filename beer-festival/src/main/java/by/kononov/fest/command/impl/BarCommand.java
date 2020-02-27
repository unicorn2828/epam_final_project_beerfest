package by.kononov.fest.command.impl;

import static by.kononov.fest.command.PageReceiver.PageType.BAR_PAGE;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.kononov.fest.command.BaseCommand;
import by.kononov.fest.command.PagePath;
import by.kononov.fest.command.PageReceiver;
import by.kononov.fest.controller.SessionRequestContent;
import by.kononov.fest.entity.Entity;
import by.kononov.fest.entity.User;
import by.kononov.fest.exception.ServiceException;
import by.kononov.fest.service.AdministratorService;
import by.kononov.fest.service.impl.AdministratorServiceImpl;

/**
 * The BarCommand class; it implements the BaseCommand interface; it
 * is used to recieve the list of bars.
 * <p>
 * {@link by.kononov.fest.command.BaseCommand}
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-10
 */
public class BarCommand implements BaseCommand{
	final static Logger logger = LogManager.getLogger();
	private static final String BAR_LIST = "bar_list";
	private static final String USER = "user";

	@Override
	public PagePath execute(SessionRequestContent content) {
		User user = (User) content.getSessionAttributes().get(USER);
		List<Entity> list = new ArrayList<>();
		AdministratorService service = new AdministratorServiceImpl();
		try {
			list = service.receiveBar();
		} catch (ServiceException e) {
			logger.error("receive bar failed ", e);
		}
		content.getSessionAttributes().put(BAR_LIST, list);
		String url = PageReceiver.receivePage(user, BAR_PAGE);
		return new PagePath(url);
	}
}