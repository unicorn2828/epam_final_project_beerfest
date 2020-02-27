package by.kononov.fest.command;

/**
 * The PagePath class.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-20
 */
public class PagePath{
	/**
	 * This is an inner class; it is enum of transition types (request
	 * type).
	 */
	public enum TransitionType {
		FORWARD, REDIRECT
	}

	/**
	 * Represents the transition’s type.
	 */
	private TransitionType type;
	private String url;

	/**
	 * Class constructor; it creates a new PagePath with the given url and
	 * default type - Forward.
	 */
	public PagePath(String url) {
		this.url = url;
		this.type = TransitionType.FORWARD;
	}

	public TransitionType getTransitionType() {
		return type;
	}

	public String getUrl() {
		return url;
	}

	public void setRedirect() {
		this.type = TransitionType.REDIRECT;
	}
}