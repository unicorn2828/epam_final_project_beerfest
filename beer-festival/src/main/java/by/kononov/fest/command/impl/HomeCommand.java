package by.kononov.fest.command.impl;

import static by.kononov.fest.command.PageReceiver.PageType.HOME_PAGE;

import by.kononov.fest.command.BaseCommand;
import by.kononov.fest.command.PagePath;
import by.kononov.fest.command.PageReceiver;
import by.kononov.fest.controller.SessionRequestContent;
import by.kononov.fest.entity.User;

/**
 * The HomeCommand class; it implements the BaseCommand interface; it
 * is used to redirect to home page defined by the user's role.
 * <p>
 * {@link by.kononov.fest.command.BaseCommand}
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-10
 */
public class HomeCommand implements BaseCommand{
	private static final String USER = "user";

	@Override
	public PagePath execute(SessionRequestContent content) {
		User user = (User) content.getSessionAttributes().get(USER);
		String url = PageReceiver.receivePage(user, HOME_PAGE);
		return new PagePath(url);
	}
}