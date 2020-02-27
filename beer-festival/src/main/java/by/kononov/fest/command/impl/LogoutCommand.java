package by.kononov.fest.command.impl;

import by.kononov.fest.command.BaseCommand;
import by.kononov.fest.command.PagePath;
import by.kononov.fest.controller.SessionRequestContent;
import by.kononov.fest.manager.ConfigurationManager;

/**
 * The LogoutCommand class; it implements the BaseCommand interface;
 * it is used to log out and redirect to index page.
 * <p>
 * {@link by.kononov.fest.command.BaseCommand}
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-10
 */
public class LogoutCommand implements BaseCommand{
	private static final String PAGE_INDEX = "path.page.index";

	@Override
	public PagePath execute(SessionRequestContent content) {
		content.invalidateSession();
		String url = ConfigurationManager.getProperty(PAGE_INDEX);
		PagePath page = new PagePath(url);
		page.setRedirect();
		return page;
	}
}