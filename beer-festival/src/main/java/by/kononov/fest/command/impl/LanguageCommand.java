package by.kononov.fest.command.impl;

import static by.kononov.fest.command.PageReceiver.PageType.HOME_PAGE;

import java.util.Optional;

import by.kononov.fest.command.BaseCommand;
import by.kononov.fest.command.PagePath;
import by.kononov.fest.command.PageReceiver;
import by.kononov.fest.controller.SessionRequestContent;
import by.kononov.fest.entity.User;

/**
 * The LanguageCommand class; it implements the BaseCommand interface;
 * it is used to choose the language of the interface.
 * <p>
 * {@link by.kononov.fest.command.BaseCommand}
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-10
 */
public class LanguageCommand implements BaseCommand{
	private static final String USER = "user";
	private static final String PARAMETER_LANGUAGE = "language";
	private static final String DEFAULT_LANGUAGE = "en";

	@Override
	public PagePath execute(SessionRequestContent content) {
		User user = (User) content.getSessionAttributes().get(USER);
		Optional<String> optional = content.getRequestParameter(PARAMETER_LANGUAGE);
		if (optional.isPresent()) {
			String language = optional.get();
			content.getSessionAttributes().put(PARAMETER_LANGUAGE, language);
		} else {
			content.getSessionAttributes().put(PARAMETER_LANGUAGE, DEFAULT_LANGUAGE);
		}
		String url = PageReceiver.receivePage(user, HOME_PAGE);
		return new PagePath(url);
	}
}
