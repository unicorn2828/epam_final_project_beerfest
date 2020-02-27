package test.kononov.fest.command.impl;

import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import by.kononov.fest.command.BaseCommand;
import by.kononov.fest.command.PagePath;
import by.kononov.fest.command.impl.HomeCommand;
import by.kononov.fest.controller.SessionRequestContent;
import by.kononov.fest.entity.User;
import by.kononov.fest.entity.User.UserRole;
import by.kononov.fest.entity.User.UserStatus;

public class HomeCommandTest{
	private static final String EXPECTED_ADMININSTRATOR = "/jsp/administrator/admin_home.jsp";
	private static final String EXPECTED_PARTNER = "/jsp/common/home.jsp";
	private static final String EXPECTED_USER = "/jsp/common/home.jsp";
	private static final String EXPECTED_NULL = "/jsp/login.jsp";
	private static final String USER = "user";
	private BaseCommand command;
	@Mock
	Map<String, Object> map;
	@Mock
	private SessionRequestContent content;

	@BeforeTest
	void setUp() {
		MockitoAnnotations.initMocks(this);
		command = new HomeCommand();

	}

	@AfterClass
	public void tierDown() {
		content = null;
		command = null;
		map = null;
	}

	@Test(description = "'home' command positive test, user = 'admin'")
	public void executeAdminTest() {
		User user = new User(0, null, null, null, UserRole.ADMINISTRATOR, UserStatus.VALID, null, null);
		Mockito.when(content.getSessionAttributes()).thenReturn(map);
		Mockito.when(map.get(USER)).thenReturn(user);
		PagePath actual = command.execute(content);
		assertEquals(actual.getUrl(), EXPECTED_ADMININSTRATOR);
	}

	@Test(description = "'home' command positive test, user = 'partner'")
	public void executePartnerTest() {
		User user = new User(0, null, null, null, UserRole.PARTNER, UserStatus.VALID, null, null);
		Mockito.when(content.getSessionAttributes()).thenReturn(map);
		Mockito.when(map.get(USER)).thenReturn(user);
		PagePath actual = command.execute(content);
		assertEquals(actual.getUrl(), EXPECTED_PARTNER);
	}

	@Test(description = "'home' command positive test, user = 'user'")
	public void executeUserTest() {
		User user = new User(0, null, null, null, UserRole.USER, UserStatus.VALID, null, null);
		Mockito.when(content.getSessionAttributes()).thenReturn(map);
		Mockito.when(map.get(USER)).thenReturn(user);
		PagePath actual = command.execute(content);
		assertEquals(actual.getUrl(), EXPECTED_USER);
	}

	@Test(description = "'home' command positive test, user = null")
	public void executeNullTest() {
		User user = null;
		Mockito.when(content.getSessionAttributes()).thenReturn(map);
		Mockito.when(map.get(USER)).thenReturn(user);
		PagePath actual = command.execute(content);
		assertEquals(actual.getUrl(), EXPECTED_NULL);
	}
}