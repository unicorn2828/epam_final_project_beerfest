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
import by.kononov.fest.exception.ServiceException;
import by.kononov.fest.manager.MessageManager;
import by.kononov.fest.service.PartnerService;
import by.kononov.fest.service.impl.PartnerServiceImpl;
import by.kononov.fest.validator.RequestContentDataValidator;

/**
 * The BarRentCommand class; it implements the BaseCommand interface;
 * it is used to rent bar by a partner.
 * <p>
 * {@link by.kononov.fest.command.BaseCommand}
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-10
 */
public class BarRentCommand implements BaseCommand{
	final static Logger logger = LogManager.getLogger();
	private static final String MESSAGE_TEXT = "message.rent";
	private static final String MESSAGE = "message";
	private static final String USER = "user";
	private static final String PARTNER = "partner";
	private static final String COMMENT = "comment";
	private static final String BAR_ID = "bar";
	private static final String BAR_NAME = "bar_name";
	private PartnerService service;

	public BarRentCommand(){
		service = new PartnerServiceImpl();
	}

	@Override
	public PagePath execute(SessionRequestContent content) {
		User user = (User) content.getSessionAttributes().get(USER);
		Partner partner = (Partner) content.getSessionAttributes().get(PARTNER);
		Optional<String> optionalComment = content.getRequestParameter(COMMENT);
		Optional<String> optionalBarName = content.getRequestParameter(BAR_NAME);
		Optional<String> optionalBarId = content.getRequestParameter(BAR_ID);
		if (RequestContentDataValidator.isDataExist(optionalComment, optionalBarName, optionalBarId)) {
			String comment = optionalComment.get();
			String barName = optionalBarName.get();
			String barId = optionalBarId.get();
			try {
				if (!service.rentRegister(user, partner, barName, comment, barId)) {
					content.getRequestAttributes().put(MESSAGE, MessageManager.getProperty(MESSAGE_TEXT));
				}
			} catch (ServiceException e) {
				logger.error("rent registraion failed ", e);
			}
		}
		String url = PageReceiver.receivePage(user, HOME_PAGE);
		return new PagePath(url);
	}
}
