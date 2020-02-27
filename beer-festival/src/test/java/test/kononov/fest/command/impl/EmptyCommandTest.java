package test.kononov.fest.command.impl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import by.kononov.fest.command.BaseCommand;
import by.kononov.fest.command.PagePath;
import by.kononov.fest.command.impl.EmptyCommand;
import by.kononov.fest.controller.SessionRequestContent;

public class EmptyCommandTest{
	private static final String UNEXPECTED = "/jsp/login.jsp";
	private static final String EXPECTED = "/index.jsp";
	private BaseCommand command;

	@Mock
	private SessionRequestContent content;

	@BeforeTest
	void setUp() {
		MockitoAnnotations.initMocks(this);
		command = new EmptyCommand();
	}

	@AfterClass
	public void tierDown() {
		content = null;
		command = null;
	}

	@Test(description = "empty command positive test")
	public void executePositiveTest() {
		PagePath actual = command.execute(content);
		assertEquals(actual.getUrl(), EXPECTED);
	}

	@Test(description = "empty command negative test")
	public void executeNegativeTest() {
		PagePath actual = command.execute(content);
		assertNotEquals(actual.getUrl(), UNEXPECTED);
	}
}