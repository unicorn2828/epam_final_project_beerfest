package test.kononov.fest.command.impl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.util.Map;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import by.kononov.fest.command.BaseCommand;
import by.kononov.fest.command.PagePath;
import by.kononov.fest.command.impl.PartnerCommand;
import by.kononov.fest.controller.SessionRequestContent;

public class PartnerCommandTest{
	private static final String EXPECTED = "/jsp/administrator/partner.jsp";
	private static final String UNEXPECTED = "/jsp/administrator/user.jsp";
	private BaseCommand command;

	@Mock
	private Map<String, Object> map;
	@Mock
	private SessionRequestContent content;

	@BeforeTest
	void setUp() {
		MockitoAnnotations.initMocks(this);
		command = new PartnerCommand();
	}

	@AfterClass
	public void tierDown() {
		content = null;
		command = null;
		map = null;
	}

	@Test(description = "'partner' command positive test")
	public void executeTest() {
		Mockito.when(content.getSessionAttributes()).thenReturn(map);
		PagePath actual = command.execute(content);
		assertEquals(actual.getUrl(), EXPECTED);
	}

	@Test(description = "'partner' command negative test")
	public void executeNegativeTest() {
		Mockito.when(content.getSessionAttributes()).thenReturn(map);
		PagePath actual = command.execute(content);
		assertNotEquals(actual.getUrl(), UNEXPECTED);
	}
}