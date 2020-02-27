package by.kononov.fest.command;

import static by.kononov.fest.entity.User.UserRole.ADMINISTRATOR;
import static by.kononov.fest.entity.User.UserRole.DEFAULT;
import static by.kononov.fest.entity.User.UserRole.PARTNER;
import static by.kononov.fest.entity.User.UserRole.USER;

import by.kononov.fest.command.impl.BarCommand;
import by.kononov.fest.command.impl.BarRentCommand;
import by.kononov.fest.command.impl.BlockUserCommand;
import by.kononov.fest.command.impl.DeleteOrderCommand;
import by.kononov.fest.command.impl.DeleteRentCommand;
import by.kononov.fest.command.impl.EmptyCommand;
import by.kononov.fest.command.impl.GalleryCommand;
import by.kononov.fest.command.impl.HomeCommand;
import by.kononov.fest.command.impl.LanguageCommand;
import by.kononov.fest.command.impl.LoginCommand;
import by.kononov.fest.command.impl.LogoutCommand;
import by.kononov.fest.command.impl.OrderCommand;
import by.kononov.fest.command.impl.OrderRegistrationCommand;
import by.kononov.fest.command.impl.PartnerCommand;
import by.kononov.fest.command.impl.PartnerRegistrationCommand;
import by.kononov.fest.command.impl.RegistrationCommand;
import by.kononov.fest.command.impl.UserCommand;
import by.kononov.fest.entity.User.UserRole;

/**
 * The CommandType enum class.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-10
 */
public enum CommandType {
	BAR(new BarCommand(), ADMINISTRATOR, USER, PARTNER),
	BAR_RENT(new BarRentCommand(), PARTNER),
	BLOCK_USER(new BlockUserCommand(), ADMINISTRATOR),
	DELETE_ORDER(new DeleteOrderCommand(), PARTNER, USER),
	DELETE_RENT(new DeleteRentCommand(), PARTNER),
	EMPTY(new EmptyCommand(), DEFAULT),
	GALLERY(new GalleryCommand(), ADMINISTRATOR, USER, PARTNER),
	HOME(new HomeCommand(), ADMINISTRATOR, USER, PARTNER),
	LANGUAGE(new LanguageCommand(), UserRole.values()),
	LOGIN(new LoginCommand(), UserRole.values()),
	LOGOUT(new LogoutCommand(), ADMINISTRATOR, USER, PARTNER),
	ORDER(new OrderCommand(), ADMINISTRATOR),
	ORDER_REGISTER(new OrderRegistrationCommand(), USER, PARTNER),
	PARTNER_REGISTER(new PartnerRegistrationCommand(), PARTNER),
	PARTNERS(new PartnerCommand(), ADMINISTRATOR),
	REGISTER(new RegistrationCommand(), DEFAULT),
	USERS(new UserCommand(), ADMINISTRATOR);

	private BaseCommand command;
	private UserRole[] role;

	/**
	 * Class constructor with initial values.
	 */
	private CommandType(BaseCommand command, UserRole... role) {
		this.command = command;
		this.role = role;
	}

	/**
	 * This method returns a BaseCommand object, depending on the
	 * "command" parameter.
	 *
	 * @return command - the BaseCommand object;
	 */
	public BaseCommand getCommand() {
		return command;
	}

	/**
	 * This method returns list of roles for current command.
	 *
	 * @return role - the array of roles;
	 */
	public UserRole[] getRole() {
		return role;
	}
}