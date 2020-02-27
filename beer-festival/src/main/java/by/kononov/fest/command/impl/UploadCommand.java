package by.kononov.fest.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.kononov.fest.entity.User;
import by.kononov.fest.exception.ServiceException;
import by.kononov.fest.manager.ConfigurationManager;
import by.kononov.fest.manager.MessageManager;
import by.kononov.fest.service.UploadService;
import by.kononov.fest.service.impl.UploadServiceImpl;
import by.kononov.fest.validator.ImageFileValidator;

/**
 * The UploadCommand class; it implements the BaseCommand interface;
 * it is used to upload the new avatar of the user.
 * <p>
 * {@link by.kononov.fest.command.BaseCommand}
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-10
 */
public class UploadCommand{
	final static Logger logger = LogManager.getLogger();
	private static final String MESSAGE_TEXT = "message.upload_error";
	private static final String MESSAGE = "message";
	private static final String PAGE = "path.page.home";
	private static final String ATTRIBUTE_FILE = "file";
	private static final String ATTRIBUTE_USER = "user";

	public String execute(HttpServletRequest request, String realPath) throws ServletException {
		User user = (User) request.getSession().getAttribute(ATTRIBUTE_USER);
		UploadService uploadLogic;
		try {
			Part filePart = request.getPart(ATTRIBUTE_FILE);
			uploadLogic = new UploadServiceImpl(realPath, filePart);
			String fileName = uploadLogic.receiveFileName(filePart);
			if (ImageFileValidator.isImageFile(fileName, filePart)) {
				uploadLogic.updateUser(user, fileName);
			} else {
				request.setAttribute(MESSAGE, MessageManager.getProperty(MESSAGE_TEXT));
			}
		} catch (ServiceException | IOException e) {
			logger.error("can't write file", e);
		}
		return ConfigurationManager.getProperty(PAGE);
	}
}