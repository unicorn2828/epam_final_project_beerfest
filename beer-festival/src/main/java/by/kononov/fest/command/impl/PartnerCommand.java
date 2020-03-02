package by.kononov.fest.command.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.kononov.fest.command.BaseCommand;
import by.kononov.fest.command.PagePath;
import by.kononov.fest.controller.SessionRequestContent;
import by.kononov.fest.entity.Entity;
import by.kononov.fest.entity.Partner;
import by.kononov.fest.exception.ServiceException;
import by.kononov.fest.manager.ConfigurationManager;
import by.kononov.fest.service.AdministratorService;
import by.kononov.fest.service.impl.AdministratorServiceImpl;

/**
 * The PartnerCommand class; it implements the BaseCommand interface;
 * it is used to receive the list of partners.
 * <p>
 * {@link by.kononov.fest.command.BaseCommand}
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-10
 */
public class PartnerCommand implements BaseCommand{
	final static Logger logger = LogManager.getLogger();
	private static final String MESSAGE_TEXT = "message.error";
	private static final String PARTNER_LIST = "partner_list";
	private static final String PAGE_PARTNER = "path.page.admin_partner";
	private AdministratorService service;

	public PartnerCommand(){
		service = new AdministratorServiceImpl();
	}

	@Override
	public PagePath execute(SessionRequestContent content) {
		List<Entity> list = new ArrayList<>();
		try {
			list = service.receivePartner();
			list.sort(Comparator.comparingLong(e -> ((Partner) e).getPartnerId()));
		} catch (ServiceException e) {
			logger.error("receive partners failed ", e);
			content.getRequestAttributes().put(MESSAGE, MessageManager.getProperty(MESSAGE_TEXT));
		}
		content.getSessionAttributes().put(PARTNER_LIST, list);
		String url = ConfigurationManager.getProperty(PAGE_PARTNER);
		return new PagePath(url);
	}
}
