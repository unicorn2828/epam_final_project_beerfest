package by.kononov.fest.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Encription class.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-17
 */
public class Encription{
	final static Logger logger = LogManager.getLogger();
	private static final StringBuilder ZERO = new StringBuilder("0");
	private static final String MD5 = "MD5";

	/**
	 * The private class constructor to hide the implicit public one.
	 */
	private Encription() {
	}

	/**
	 * This method encodes the string; it uses the MD5 hash function.
	 *
	 * @param string - the string for encoding;
	 * @throws CustomException, if encription failed;
	 * @return the string after encoding;
	 */
	public static String encode(String string) {
		MessageDigest messageDigest = null;
		byte[] digest = new byte[0];
		try {
			messageDigest = MessageDigest.getInstance(MD5);
			messageDigest.reset();
			messageDigest.update(string.getBytes());
			digest = messageDigest.digest();
		} catch (NoSuchAlgorithmException e) {
			logger.error("encription password failed ", e);
		}
		BigInteger bigInt = new BigInteger(1, digest);
		StringBuilder encodedString = new StringBuilder(bigInt.toString(16));
		while (encodedString.length() < 32) {
			encodedString = ZERO.append(encodedString);
		}
		return encodedString.toString();
	}
}