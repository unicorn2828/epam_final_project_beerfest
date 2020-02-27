package by.kononov.fest.validator;

import java.util.Arrays;
import java.util.Optional;

/**
 * The RequestContentValidator class.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-17
 */
public class RequestContentDataValidator{

	/**
	 * The private class constructor is used to hide the implicit public one.
	 */
	private RequestContentDataValidator() {
	}

	/**
	 * This method checks if the data exists.
	 *
	 * @param params - the Optional parameters to verify;
	 * @return - <code>true</code> if the parameter exists;
	 *         <code>false</code> otherwise;
	 */
	@SafeVarargs
	public static boolean isDataExist(Optional<String>... params) {
		return Arrays.asList(params).stream().allMatch(Optional::isPresent);
	}
}
