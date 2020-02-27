package by.kononov.fest.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The UserDataValidator class.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-02
 */
public class UserDataValidator{
	static final Logger logger = LogManager.getLogger();
	private final static Pattern LOGIN_REGEX = Pattern.compile("^[a-zA-Z0-9._-]{3,12}$");
	private final static Pattern PASSWORD_REGEX = Pattern.compile("^[a-zA-Z0-9._-]{3,12}$");

	/**
	 * The private class constructor is used to hide the implicit public one.
	 */
	private UserDataValidator() {
	}

	/**
	 * This method checks if the string 'login' matches the given regular
	 * expression.
	 *
	 * @param login - the login to check;
	 * @return - <code>true</code> if the string completely matches
	 *         regular expression; <code>false</code> otherwise;
	 */
	public static boolean isLogin(String login) {
		Matcher matcher = LOGIN_REGEX.matcher(login);
		return matcher.matches();
	}

	/**
	 * This method checks if the string 'password' matches the given
	 * regular expression.
	 *
	 * @param login - the login to check;
	 * @return - <code>true</code> if the string completely matches
	 *         regular expression; <code>false</code> otherwise;
	 */
	public static boolean isPassword(String password) {
		Matcher matcher = PASSWORD_REGEX.matcher(password);
		return matcher.matches();
	}

	/**
	 * This method checks if the string 'email' matches the given regular
	 * expression. It uses Apache Commons Validator » 1.3.1
	 *
	 * @param login - the login to check;
	 * @return - <code>true</code> if the string completely matches
	 *         regular expression; <code>false</code> otherwise;
	 */
	public static boolean isEmail(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}
}
