package by.kononov.fest.command.impl;

import java.util.ArrayList;
import java.util.List;

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
 * The OrderCommand class; it implements the BaseCommand interface; it
 * is used to receive the list of orders.
 * <p>
 * {@link by.kononov.fest.command.BaseCommand}
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-10
 */
public class OrderCommand implements BaseCommand{
	final static Logger logger = LogManager.getLogger();
	private static final String ORDER_LIST = "order_list";
	private static final String PAGE_ORDER = "path.page.admin_order";

	@Override
	public PagePath execute(SessionRequestContent content) {
		List<Entity> list = new ArrayList<>();
		AdministratorService service = new AdministratorServiceImpl();
		try {
			list = service.receiveOrder();
		} catch (ServiceException e) {
			logger.error("receive order failed ", e);
		}
		content.getSessionAttributes().put(ORDER_LIST, list);
		String url = ConfigurationManager.getProperty(PAGE_ORDER);
		return new PagePath(url);
	}
}
