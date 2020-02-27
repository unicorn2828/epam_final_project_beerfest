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
import by.kononov.fest.command.impl.OrderCommand;
import by.kononov.fest.controller.SessionRequestContent;

public class OrderCommandTest{
	private static final String EXPECTED = "/jsp/administrator/order.jsp";

	private BaseCommand command;
	@Mock
	Map<String, Object> map;
	@Mock
	private SessionRequestContent content;

	@BeforeTest
	void setUp() {
		MockitoAnnotations.initMocks(this);
		command = new OrderCommand();

	}

	@AfterClass
	public void tierDown() {
		content = null;
		command = null;
		map = null;
	}

	@Test(description = "'order' command positive test")
	public void executeTest() {
		Mockito.when(content.getSessionAttributes()).thenReturn(map);
		PagePath actual = command.execute(content);
		assertEquals(actual.getUrl(), EXPECTED);
	}
}