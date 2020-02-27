package test.kononov.fest.command;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import by.kononov.fest.command.CommandType;

public class CommandTypeTest{

	@DataProvider
	public Object[][] correctCommand() {
		return new Object[][] {
				{ "bar", CommandType.BAR },
				{ "delete_order", CommandType.DELETE_ORDER },
				{ "users", CommandType.USERS }, };
	}

	@Test(dataProvider = "correctCommand", description = "positive command type test")
	public void positiveCommandTypeTest(String in, CommandType expected) {
		CommandType actual = CommandType.valueOf(in.toUpperCase());
		assertEquals(actual, expected);
	}

	@DataProvider
	public Object[][] incorrectCommand() {
		return new Object[][] { { "users", CommandType.BAR }, { "bar", CommandType.USERS }, };
	}

	@Test(dataProvider = "incorrectCommand", description = "negative command type test")
	public void negativeCommandTypeTest(String in, CommandType expected) {
		CommandType actual = CommandType.valueOf(in.toUpperCase());
		assertNotEquals(actual, expected);
	}
}