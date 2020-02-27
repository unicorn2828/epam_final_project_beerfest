package test.kononov.fest.validator;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import by.kononov.fest.validator.UserDataValidator;

public class UserDataValidatorTest{

	@DataProvider
	public Object[][] email() {
		return new Object[][] {
				{ "123@mail.com", true },
				{ "mail@mail.com", true },
				{ "mail@mail", true },
				{ "mail.pn", false },
				{ "mail10.", false },
				{ "", false }, };
	}

	@Test(dataProvider = "email", description = "user email validator test")
	public void isEmailTest(String in, boolean expected) {
		boolean actual = UserDataValidator.isEmail(in);
		assertEquals(actual, expected);
	}
}