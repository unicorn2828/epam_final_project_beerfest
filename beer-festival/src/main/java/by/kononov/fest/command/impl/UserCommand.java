package by.kononov.fest.command.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.kononov.fest.command.BaseCommand;
import by.kononov.fest.command.PagePath;
import by.kononov.fest.controller.SessionRequestContent;
import by.kononov.fest.entity.Entity;
import by.kononov.fest.exception.ServiceException;
import by.kononov.fest.manager.ConfigurationManager;
import by.kononov.fest.service.AdministratorService;
import by.kononov.fest.service.impl.AdministratorServiceImpl;

/**
 * The UserCommand class; it implements the BaseCommand interface; it
 * is used to receive the list of users.
 * <p>
 * {@link by.kononov.fest.command.BaseCommand}
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-10
 */
public class UserCommand implements BaseCommand{
	final static Logger logger = LogManager.getLogger();
	private static final String ATTRIBUTE_MAX_VALUE = "maxValue";
	private static final String PARAMETER_PAGE_NUMBER = "pageNumber";
	private static final String USER_LIST = "user_list";
	private static final String PAGE_USER = "path.page.admin_user";

	@Override
	public PagePath execute(SessionRequestContent content) {
		List<Entity> list = new ArrayList<>();
		AdministratorService service = new AdministratorServiceImpl();
		try {
			int pagesCount = service.countPages();
			content.setAttribute(ATTRIBUTE_MAX_VALUE, pagesCount);
			Optional<String> optional = content.getRequestParameter(PARAMETER_PAGE_NUMBER);
			String pageNumber = null;
			if (optional.isPresent()) {
				pageNumber = optional.get();
			}
			list = service.receiveUser(pageNumber);
		} catch (ServiceException e) {
			logger.error("receive users failed ", e);
		}
		content.getSessionAttributes().put(USER_LIST, list);
		String url = ConfigurationManager.getProperty(PAGE_USER);
		return new PagePath(url);
	}
}
