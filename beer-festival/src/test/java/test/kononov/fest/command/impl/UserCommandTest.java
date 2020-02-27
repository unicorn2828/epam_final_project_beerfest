package test.kononov.fest.command.impl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.util.Map;
import java.util.Optional;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import by.kononov.fest.command.BaseCommand;
import by.kononov.fest.command.PagePath;
import by.kononov.fest.command.impl.UserCommand;
import by.kononov.fest.controller.SessionRequestContent;

public class UserCommandTest{
	private static final String EXPECTED = "/jsp/administrator/user.jsp";
	private static final String UNEXPECTED = "/jsp/administrator/partner.jsp";
	private static final String PARAMETER_PAGE_NUMBER = "pageNumber";
	private static final Optional<String> OPTIONAL = Optional.of("1");
	private BaseCommand command;
	@Mock
	Map<String, Object> map;
	@Mock
	private SessionRequestContent content;

	@BeforeTest
	void setUp() {
		MockitoAnnotations.initMocks(this);
		command = new UserCommand();

	}

	@AfterClass
	public void tierDown() {
		content = null;
		command = null;
		map = null;
	}

	@Test(description = "'user' command positive test")
	public void executeTest() {
		Mockito.when(content.getSessionAttributes()).thenReturn(map);
		Mockito.when(content.getRequestParameter(PARAMETER_PAGE_NUMBER)).thenReturn(OPTIONAL);
		PagePath actual = command.execute(content);
		assertEquals(actual.getUrl(), EXPECTED);
	}

	@Test(description = "'user' command negative test")
	public void executeNegativeTest() {
		Mockito.when(content.getSessionAttributes()).thenReturn(map);
		Mockito.when(content.getRequestParameter(PARAMETER_PAGE_NUMBER)).thenReturn(OPTIONAL);
		PagePath actual = command.execute(content);
		assertNotEquals(actual.getUrl(), UNEXPECTED);
	}
}