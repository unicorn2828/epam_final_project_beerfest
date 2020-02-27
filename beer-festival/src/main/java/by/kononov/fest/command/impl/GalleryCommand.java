package by.kononov.fest.command.impl;

import by.kononov.fest.command.BaseCommand;
import by.kononov.fest.command.PagePath;
import by.kononov.fest.controller.SessionRequestContent;
import by.kononov.fest.manager.ConfigurationManager;

/**
 * The GalleryCommand class; it implements the BaseCommand interface;
 * it is used to redirect to gallery page.
 * <p>
 * {@link by.kononov.fest.command.BaseCommand}
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-10
 */
public class GalleryCommand implements BaseCommand{
	public static final String PAGE_GALLERY = "path.page.gallery";

	@Override
	public PagePath execute(SessionRequestContent content) {
		String url = ConfigurationManager.getProperty(PAGE_GALLERY);
		return new PagePath(url);
	}
}