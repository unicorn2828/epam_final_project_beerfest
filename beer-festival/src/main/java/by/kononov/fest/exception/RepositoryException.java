package by.kononov.fest.exception;

/**
 * This is the RepositoryException class; it is used to create custom
 * repository level exceptions.
 * 
 * @author Vitaly Kononov
 * @since 2019-12-28
 */
public class RepositoryException extends Exception{
	private static final long serialVersionUID = 1L;

	public RepositoryException() {
	}

	public RepositoryException(String message) {
		super(message);
	}

	public RepositoryException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepositoryException(Throwable cause) {
		super(cause);
	}
}