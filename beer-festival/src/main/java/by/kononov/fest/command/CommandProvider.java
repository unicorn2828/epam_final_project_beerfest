package by.kononov.fest.command;

import java.util.Arrays;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.kononov.fest.controller.SessionRequestContent;
import by.kononov.fest.entity.User;
import by.kononov.fest.entity.User.UserRole;
import by.kononov.fest.validator.RequestContentDataValidator;

/**
 * The CommandProvider class.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-02
 */
public class CommandProvider{
	final static Logger logger = LogManager.getLogger();
	private static final String PARAMETER_STRING = "command";
	private static final String USER = "user";

	/**
	 * The private class constructor to hide the implicit public one.
	 */
	private CommandProvider() {
	}

	/**
	 * This method returns a Command object, depending on the "command"
	 * parameter.
	 *
	 * @param content - SessionRequestContent object {@link package
	 *                by.konanau.fest.controller.SessionRequestContent}
	 * @return currentCommand - the BaseCommand object
	 *         {@link CommandType#getCommand()}
	 */
	public static BaseCommand defineCommand(SessionRequestContent content) {
		User user = (User) content.getSessionAttributes().get(USER);
		UserRole role = getUserRole(user);
		BaseCommand currentCommand = CommandType.EMPTY.getCommand();
		Optional<String> optional = content.getRequestParameter(PARAMETER_STRING);
		if (RequestContentDataValidator.isDataExist(optional)) {
			String action = optional.get();
			currentCommand = getCurrentCommand(action, currentCommand, role);
		}
		return currentCommand;
	}

	private static BaseCommand getCurrentCommand(String action, BaseCommand currentCommand, UserRole role) {
		try {
			CommandType currentEnum = CommandType.valueOf(action.toUpperCase());
			if (Arrays.stream(currentEnum.getRole()).anyMatch(role::equals)) {
				currentCommand = currentEnum.getCommand();
			}
		} catch (IllegalArgumentException e) {
			currentCommand = CommandType.EMPTY.getCommand();
			logger.error("command not found or wrong! " + action, e);
		}
		return currentCommand;
	}

	private static UserRole getUserRole(User user) {
		return user != null ? user.getRole() : UserRole.DEFAULT;
	}
}