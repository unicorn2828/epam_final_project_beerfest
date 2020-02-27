package test.kononov.fest.validator;

import static org.testng.Assert.assertEquals;

import java.util.Optional;

import org.testng.annotations.Test;

import by.kononov.fest.validator.RequestContentDataValidator;

public class RequestContentValidatorTest{

	@Test(description = "positive request content validator test")
	public void isDataExistPositiveTest() {
		Optional<String> in = Optional.of("parameter");
		boolean actual = RequestContentDataValidator.isDataExist(in);
		boolean expected = true;
		assertEquals(actual, expected);
	}

	@Test(description = "negative request content validator test")
	public void isDataExistNegativeTest() {
		Optional<String> in = Optional.empty();
		boolean actual = RequestContentDataValidator.isDataExist(in);
		boolean expected = false;
		assertEquals(actual, expected);
	}
}