package test.kononov.fest.command;

import static org.testng.Assert.assertEquals;

import java.util.Map;
import java.util.Optional;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import by.kononov.fest.command.BaseCommand;
import by.kononov.fest.command.CommandProvider;
import by.kononov.fest.command.CommandType;
import by.kononov.fest.controller.SessionRequestContent;
import by.kononov.fest.entity.User;
import by.kononov.fest.entity.User.UserRole;
import by.kononov.fest.entity.User.UserStatus;

public class CommandProviderTest{
	private BaseCommand expected;
	private static final String PARAMETER_STRING = "command";
	private static final Optional<String> TEST = Optional.empty();
	private static final String USER = "user";

	@Mock
	Map<String, Object> map;
	@Mock
	private SessionRequestContent content;

	@BeforeTest
	void setUp() {
		MockitoAnnotations.initMocks(this);
		expected = CommandType.EMPTY.getCommand();
	}

	@AfterClass
	public void tierDown() {
		expected = null;
		content = null;
	}

	@DataProvider
	public Object[][] correctCommand() {
		return new Object[][] {
				{ "bar", CommandType.BAR.getCommand() },
				{ "home", CommandType.HOME.getCommand() },
				{ "delete_order", CommandType.DELETE_ORDER.getCommand() },
				{ "gallery", CommandType.GALLERY.getCommand() }, };
	}

	@Test(dataProvider = "correctCommand", description = "command provider test")
	public void defineCommandPositiveTest(String in, BaseCommand expected) {
		User user = new User(0, null, null, null, UserRole.USER, UserStatus.VALID, null, null);
		Mockito.when(content.getSessionAttributes()).thenReturn(map);
		Mockito.when(map.get(USER)).thenReturn(user);
		Mockito.when(content.getRequestParameter(PARAMETER_STRING)).thenReturn(Optional.of(in));
		BaseCommand actual = CommandProvider.defineCommand(content);
		System.out.println("actual " + actual);
		System.out.println("expected " + expected);
		assertEquals(actual, expected);
	}

	@Test(description = "command provider test, default command 'EMPTY'")
	public void defineEmptyCommandTest() {
		Mockito.when(content.getRequestParameter(PARAMETER_STRING)).thenReturn(TEST);
		BaseCommand actual = CommandProvider.defineCommand(content);
		assertEquals(actual, expected);
	}
}