package by.kononov.fest.command;

import by.kononov.fest.entity.User;
import by.kononov.fest.manager.ConfigurationManager;

/**
 * The ReceivingPage class.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-10
 */
public class PageReceiver{
	private static final String PAGE_LOGIN = "path.page.login";
	private static final String PAGE_BLOCK = "path.page.block";
	private static final String PAGE_HOME = "path.page.home";
	private static final String PAGE_ADMIN_HOME = "path.page.admin";
	private static final String PAGE_USER_BAR = "path.page.user_bar";
	private static final String PAGE_PARTNER_BAR = "path.page.partner_bar";
	private static final String PAGE_ADMIN_BAR = "path.page.admin_bar";

	/**
	 * The private class constructor to hide the implicit public one.
	 */
	private PageReceiver() {
	}

	/**
	 * This is inner class; it is enum of page type.
	 */
	public enum PageType {
		HOME_PAGE, BAR_PAGE
	}

	/**
	 * This method returns the path to the jsp page.
	 *
	 * @param user - User.class object to get the role
	 * @param type - PageType.class object to get the page type
	 * @return the string with the path to jsp
	 */
	public static String receivePage(User user, PageType type) {
		switch (type) {
			case HOME_PAGE:
				return receiveHomePage(user);
			case BAR_PAGE:
				return receiveBarPage(user);
			default:
				return PAGE_LOGIN;
		}
	}

	/**
	 * This method returns the path to the jsp page. It uses
	 * ConfigurationManager to get the correct path from config file
	 * {@link package by.konanau.fest.manager.ConfigurationManager}
	 *
	 * @param user - User.class object to get the role
	 * @return page - the string with the path to jsp
	 */
	private static String receiveHomePage(User user) {
		String page = ConfigurationManager.getProperty(PAGE_LOGIN);
		if (user != null) {
			if (User.UserStatus.VALID.equals(user.getStatus())) {
				User.UserRole role = user.getRole();
				switch (role) {
					case ADMINISTRATOR:
						page = ConfigurationManager.getProperty(PAGE_ADMIN_HOME);
						break;
					case USER:
					case PARTNER:
						page = ConfigurationManager.getProperty(PAGE_HOME);
						break;
					default:
						page = ConfigurationManager.getProperty(PAGE_LOGIN);
						break;
				}
			} else {
				page = ConfigurationManager.getProperty(PAGE_BLOCK);
			}
		}
		return page;
	}

	/**
	 * This method returns the path to the jsp page. It uses
	 * ConfigurationManager to get the correct path from config file
	 * {@link package by.konanau.fest.manager.ConfigurationManager}
	 *
	 * @param user - User.class object to get the role
	 * @return page - the string with the path to jsp
	 */
	private static String receiveBarPage(User user) {
		String page = ConfigurationManager.getProperty(PAGE_LOGIN);
		if (user != null) {
			if (User.UserStatus.VALID.equals(user.getStatus())) {
				User.UserRole role = user.getRole();
				switch (role) {
					case ADMINISTRATOR:
						page = ConfigurationManager.getProperty(PAGE_ADMIN_BAR);
						break;
					case PARTNER:
						page = ConfigurationManager.getProperty(PAGE_PARTNER_BAR);
						break;
					case USER:
						page = ConfigurationManager.getProperty(PAGE_USER_BAR);
						break;
					default:
						page = ConfigurationManager.getProperty(PAGE_LOGIN);
						break;
				}
			} else {
				page = ConfigurationManager.getProperty(PAGE_BLOCK);
			}
		}
		return page;
	}
}