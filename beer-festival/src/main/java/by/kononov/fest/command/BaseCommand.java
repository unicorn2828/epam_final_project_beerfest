package by.kononov.fest.command;

import by.kononov.fest.controller.SessionRequestContent;

/**
 * The BaseCommand interface; it is functional interface; it is used
 * to implement the Command pattern.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-10
 */
@FunctionalInterface
public interface BaseCommand{

	/**
	 * This method returns string of path to jsp page.
	 *
	 * @param content - SessionRequestContent object {@link package
	 *                by.konanau.fest.controller.SessionRequestContent};
	 * @return {@code PagePath} which contains the path to jsp and type of
	 *         transition;
	 */
	PagePath execute(SessionRequestContent content);
}