package test.kononov.fest.validator;

import static org.testng.Assert.assertEquals;

import javax.servlet.http.Part;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import by.kononov.fest.validator.ImageFileValidator;

public class FileValidatorTest{
	@Mock
	private Part filePart;

	@BeforeTest
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@AfterClass
	public void tierDown() {
		filePart = null;
	}

	@DataProvider
	public Object[][] files() {
		return new Object[][] {
				{ "image1.jpg", true },
				{ "image2.png", true },
				{ "image3.gif", true },
				{ "image4.jpeg", true },
				{ "image5.txt", false },
				{ "image6.bmp", false },
				{ "image7.jjpg", false },
				{ "image8.jdjpg", false },
				{ "image9.pn", false },
				{ "image10.", false }, };
	}

	@Test(dataProvider = "files", description = "file format validator test")
	public void isImageTest(String in, boolean expected) {
		boolean actual = ImageFileValidator.isImageFile(in, filePart);
		assertEquals(actual, expected);
	}
}