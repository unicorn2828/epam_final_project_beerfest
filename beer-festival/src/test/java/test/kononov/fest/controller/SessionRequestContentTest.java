package test.kononov.fest.controller;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import by.kononov.fest.controller.SessionRequestContent;

public class SessionRequestContentTest{
	private static final String NAME = "test";
	private static final Object VALUE = "test";
	private Map<String, Object> expected;
	private SessionRequestContent content;

	@BeforeClass
	void setUp() {
		content = new SessionRequestContent();
		expected = new HashMap<>();
		expected.put("test", "test");
	}

	@AfterClass
	public void tierDown() {
		content = null;
		expected = null;
	}

	@Test(description = "getRequestAttributes method positive test")
	public void getRequestAttributesTest() {
		content.setAttribute(NAME, VALUE);
		Map<String, Object> actual = content.getRequestAttributes();
		assertEquals(actual, expected);
	}

	@Test(description = "getRequestAttributes method nagative test")
	public void getRequestAttributesNegativeTest() {
		Map<String, Object> actual = content.getRequestAttributes();
		assertNotEquals(actual, expected);
	}
}