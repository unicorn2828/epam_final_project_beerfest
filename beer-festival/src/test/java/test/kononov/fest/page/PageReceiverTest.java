package test.kononov.fest.page;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import by.kononov.fest.command.PageReceiver;
import by.kononov.fest.command.PageReceiver.PageType;
import by.kononov.fest.entity.User;
import by.kononov.fest.entity.User.UserRole;
import by.kononov.fest.entity.User.UserStatus;

public class PageReceiverTest{
	private static final String EXPECTED = "/jsp/administrator/admin_home.jsp";
	private static final String UNEXPECTED = "/jsp/administrator/partner.jsp";
	private User user;

	@BeforeClass
	void setUp() {
		user = new User(0, null, null, null, UserRole.ADMINISTRATOR, UserStatus.VALID, null, null);
	}

	@AfterClass
	public void tierDown() {
		user = null;
	}

	@Test(description = "receivePage method positive test")
	public void receivePagePositiveTest() {
		String actual = PageReceiver.receivePage(user, PageType.HOME_PAGE);
		assertEquals(actual, EXPECTED);
	}

	@Test(description = "receivePage method negative test")
	public void receivePageNegativeTest() {
		String actual = PageReceiver.receivePage(user, PageType.HOME_PAGE);
		assertNotEquals(actual, UNEXPECTED);
	}
}