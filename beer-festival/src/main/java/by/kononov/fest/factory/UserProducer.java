package by.kononov.fest.factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import by.kononov.fest.entity.Entity;
import by.kononov.fest.entity.Order;
import by.kononov.fest.entity.User;
import by.kononov.fest.entity.Entity.EntityType;

/**
 * The UserProducer class; it implements the singleton pattern.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-08
 */
class UserProducer{
	private static final UserProducer INSTANCE = new UserProducer();
	private static final String USER_ID = "user_id";
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String EMAIL = "email";
	private static final String USER_ROLE = "user_role";
	private static final String USER_STATUS = "user_status";
	private static final String AVATAR = "avatar_url";

	/**
	 * The private class constructor is uses to hide the implicit public one.
	 */
	private UserProducer() {
	}

	/**
	 * This method returns the instance of class.
	 * 
	 * @return INSTANCE - the single instance of this class;
	 */
	static UserProducer getInstance() {
		return INSTANCE;
	}

	/**
	 * This method creates users by extracting parameters from the
	 * ResultSet and enters them into the list.
	 * 
	 * @param resultSet - the ResultSet object
	 * @param userList  - the empty user list to filling
	 * @throws SQLException - if the sql exception
	 * @return userList - the list of users after produsing
	 */
	List<Entity> produceUser(ResultSet resultSet, List<Entity> userList) throws SQLException {
		while (resultSet.next()) {
			long userId = resultSet.getLong(USER_ID);
			String login = resultSet.getString(LOGIN);
			String password = resultSet.getString(PASSWORD);
			String email = resultSet.getString(EMAIL);
			User.UserRole role = User.UserRole.values()[resultSet.getInt(USER_ROLE)];
			User.UserStatus userStatus = User.UserStatus.values()[resultSet.getInt(USER_STATUS)];
			String avatar = resultSet.getString(AVATAR);
			Order order = (Order) EntityFactory.getInstance().produce(resultSet, EntityType.ORDER);
			User user = new User(userId, login, password, email, role, userStatus, order, avatar);
			userList.add(user);
		}
		return userList;
	}
}
