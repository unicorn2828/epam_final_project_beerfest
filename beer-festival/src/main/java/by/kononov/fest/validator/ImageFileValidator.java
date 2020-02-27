package by.kononov.fest.validator;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Part;

/**
 * The FileValidator class.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-17
 */
public class ImageFileValidator{
	private static final int MAX_FILE_SIZE = 524288;
	private static final String JPEG = ".JPEG";
	private static final List<String> IMAGE_FORMAT = Arrays.asList(".JPG", ".PNG", ".GIF");

	/**
	 * The private class constructor to hide the implicit public one.
	 */
	private ImageFileValidator() {
	}

	/**
	 * This method checks if the file matches size and image file formats.
	 *
	 * @param fileName - the fileName to be check format
	 * @param filePart - the filePart to be check size
	 * @return - <code>true</code> if the string completely matches;
	 *         <code>false</code> otherwise
	 */
	public static boolean isImageFile(String fileName, Part filePart) {
		String lastThree = fileName.substring(fileName.length() - 4).toUpperCase();
		String lastFour = fileName.substring(fileName.length() - 5).toUpperCase();
		boolean isImage = IMAGE_FORMAT.stream().anyMatch(lastThree::equals) || JPEG.equals(lastFour);
		boolean isRightSize = filePart.getSize() <= MAX_FILE_SIZE;
		return isImage && isRightSize;
	}
}