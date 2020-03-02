package by.kononov.fest.command.impl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.kononov.fest.command.BaseCommand;
import by.kononov.fest.command.PagePath;
import by.kononov.fest.command.PageReceiver;
import by.kononov.fest.command.PageReceiver.PageType;
import by.kononov.fest.controller.SessionRequestContent;
import by.kononov.fest.entity.Partner;
import by.kononov.fest.entity.User;
import by.kononov.fest.exception.ServiceException;
import by.kononov.fest.mail.MailSender;
import by.kononov.fest.manager.ConfigurationManager;
import by.kononov.fest.manager.MessageManager;
import by.kononov.fest.service.PartnerService;
import by.kononov.fest.service.impl.PartnerServiceImpl;
import by.kononov.fest.validator.RequestContentDataValidator;

/**
 * The PartnerRegistrationCommand class; it implements the BaseCommand
 * interface; it is used to register the partner.
 * <p>
 * {@link by.kononov.fest.command.BaseCommand}
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-10
 */
public class PartnerRegistrationCommand implements BaseCommand{
	 static final Logger logger = LogManager.getLogger();
    private static final String MESSAGE = "message";
    private static final String MESSAGE_TEXT = "message.partner_error";
    private static final String USER = "user";
    private static final String PARTNER = "partner";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_DESCRIPTION = "description";
    private static final String PAGE_LOGIN = "path.page.login";
    private static final String SAME_PAGE = "path.page.partner_register";
    private PartnerService service;

    public PartnerRegistrationCommand() {
        service = new PartnerServiceImpl();
    }
	@Override
	public PagePath execute(SessionRequestContent content) {
		String page = ConfigurationManager.getProperty(PAGE_LOGIN);
		User user = (User) content.getSessionAttributes().get(USER);
		long partnerId = user.getUserId();
		Optional<String> optionalName = content.getRequestParameter(PARAM_NAME);
		Optional<String> optionalDescription = content.getRequestParameter(PARAM_DESCRIPTION);
		if (RequestContentDataValidator.isDataExist(optionalName, optionalDescription)) {
			String name = optionalName.get();
			String description = optionalDescription.get();
			try {
				if (service.checkRegistration(name)) {
					Partner partner = service.registerPartner(partnerId, name, description);
					content.getSessionAttributes().put(PARTNER, partner);
					page = PageReceiver.receivePage(user, PageType.HOME_PAGE);
					if (!user.getEmail().isEmpty()) {
						MailSender.sendMail(user.getEmail());
					}
				} else {
					content.getRequestAttributes().put(MESSAGE, MessageManager.getProperty(MESSAGE_TEXT));
					page = ConfigurationManager.getProperty(SAME_PAGE);
				}
			} catch (ServiceException e) {
				logger.error("registration failed ", e);
				page = ConfigurationManager.getProperty(PAGE_LOGIN);
			}
		}
		return new PagePath(page);
	}
}
