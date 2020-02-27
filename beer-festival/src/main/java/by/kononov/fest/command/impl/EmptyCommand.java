package by.kononov.fest.command.impl;

import by.kononov.fest.command.BaseCommand;
import by.kononov.fest.command.PagePath;
import by.kononov.fest.controller.SessionRequestContent;
import by.kononov.fest.manager.ConfigurationManager;

/**
 * The EmptyCommand class; it implements the BaseCommand interface; it
 * is used to redirect to index page.
 * <p>
 * {@link by.kononov.fest.command.BaseCommand}
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-10
 */
public class EmptyCommand implements BaseCommand{
	public static final String PAGE_INDEX = "path.page.index";

	@Override
	public PagePath execute(SessionRequestContent content) {
		String url = ConfigurationManager.getProperty(PAGE_INDEX);
		return new PagePath(url);
	}
}