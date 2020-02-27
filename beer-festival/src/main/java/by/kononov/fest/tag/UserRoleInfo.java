package by.kononov.fest.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import by.kononov.fest.entity.User.UserRole;

/**
 * The UserRoleInfo class; it extends the TagSupport class.
 * <p>
 * Please see the {@link javax.servlet.jsp.tagext.TagSupport} class
 * for true identity.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-20
 */
public class UserRoleInfo extends TagSupport{
	private static final long serialVersionUID = 1L;
	private static final String COLOR_GREEN = "#ACF94F";
	private static final String COLOR_WHITE = "white";
	private static final String TAG_LEFT = "<div style=\"color: ";
	private static final String TAG_MIDDLE = "\">";
	private static final String TAG_RIGHT = "</div>";
	/**
	 * Represents the user’s role.
	 */
	private UserRole role;

	public void setRole(UserRole role) {
		this.role = role;
	}

	@Override
	public int doStartTag() throws JspException {
		String color = null;
		try {
			UserRole to = null;
			switch (role) {
				default:
				case ADMINISTRATOR:
					to = role;
					color = COLOR_GREEN;
					break;
				case USER:
				case PARTNER:
					to = role;
					color = COLOR_WHITE;
					break;
			}
			StringBuilder customTag = new StringBuilder(TAG_LEFT);
			customTag.append(color);
			customTag.append(TAG_MIDDLE);
			customTag.append(to);
			customTag.append(TAG_RIGHT);
			pageContext.getOut().write(customTag.toString());
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}
		return SKIP_BODY;
	}
}