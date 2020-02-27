package by.kononov.fest.entity;

/**
 * The User class; it represents User; it extends the Entity class.
 * <p>
 * Please see the {@link by.kononov.fest.entity.Entity} class for true
 * identity.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-02
 */
public class User extends Entity{
	private static final long serialVersionUID = 1L;

	/**
	 * This is an inner class; it is enum of user roles.
	 */
	public enum UserRole {
		USER, PARTNER, ADMINISTRATOR, DEFAULT
	}

	/**
	 * This is an inner class; it is enum of user statuses.
	 */
	public enum UserStatus {
		VALID, BLOCK
	}

	private long userId;
	private String login;
	private String password;
	private String email;
	/**
	 * Represents the user’s role.
	 */
	private UserRole role;
	/**
	 * Represents the user’s status.
	 */
	private UserStatus status;
	/**
	 * Represents the user’s order.
	 */
	private Order order;
	private String avatarUrl;

	/**
	 * Class constructor; it creates a new User with initial values.
	 */
	public User(long userId, String login, String password, String email, UserRole role, UserStatus status, Order order,
			String avatarUrl) {
		this.userId = userId;
		this.login = login;
		this.password = password;
		this.email = email;
		this.role = role;
		this.status = status;
		this.order = order;
		this.avatarUrl = avatarUrl;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = (int) (prime * result + (userId ^ (userId >>> 32)));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login)) {
			return false;
		}
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role)) {
			return false;
		}
		if (status != other.status || userId != other.userId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [userId=");
		builder.append(userId);
		builder.append(", login=");
		builder.append(login);
		builder.append(", password=");
		builder.append(password);
		builder.append(", email=");
		builder.append(email);
		builder.append(", role=");
		builder.append(role);
		builder.append(", status=");
		builder.append(status);
		builder.append(", order=");
		builder.append(order);
		builder.append(", avatarUrl=");
		builder.append(avatarUrl);
		builder.append("]");
		return builder.toString();
	}
}