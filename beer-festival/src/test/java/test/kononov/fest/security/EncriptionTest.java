package test.kononov.fest.security;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import by.kononov.fest.security.Encription;

public class EncriptionTest{

	@DataProvider
	public Object[][] correctString() {
		return new Object[][] {
				{ "admin", "21232f297a57a5a743894a0e4a801fc3" },
				{ "0", "cfcd208495d565ef66e7dff9f98764da" },
				{ "", "d41d8cd98f00b204e9800998ecf8427e" }, };
	}

	@Test(dataProvider = "correctString", description = "positive encription test")
	public void positiveEncodeTest(String in, String expected) {
		String actual = Encription.encode(in);
		assertEquals(actual, expected);
	}

	@DataProvider
	public Object[][] incorrectString() {
		return new Object[][] { { "admin", "admin" }, { "0", "0" }, { "", "" }, };
	}

	@Test(dataProvider = "incorrectString", description = "negative encription test")
	public void negativeEncodeTest(String in, String expected) {
		String actual = Encription.encode(in);
		assertNotEquals(actual, expected);
	}
}