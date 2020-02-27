package by.kononov.fest.exception;

/**
 * This is the ServiceException class; it is used to create custom
 * service level exceptions.
 * 
 * @author Vitaly Kononov
 * @since 2019-12-28
 */
public class ServiceException extends Exception{
	private static final long serialVersionUID = 1L;

	public ServiceException() {
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
}