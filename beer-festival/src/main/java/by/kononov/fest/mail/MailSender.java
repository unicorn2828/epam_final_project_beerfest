package by.kononov.fest.mail;

import static by.kononov.fest.mail.MailData.MAIL_BODY;
import static by.kononov.fest.mail.MailData.MAIL_SUBJECT;
import static by.kononov.fest.mail.MailData.SMTP_AUTH;
import static by.kononov.fest.mail.MailData.SMTP_HOST;
import static by.kononov.fest.mail.MailData.SMTP_PASSWORD;
import static by.kononov.fest.mail.MailData.SMTP_PORT;
import static by.kononov.fest.mail.MailData.SMTP_START_TLS;
import static by.kononov.fest.mail.MailData.SMTP_USER;
import static by.kononov.fest.mail.MailData.TRANSPORT;
import static by.kononov.fest.mail.MailData.USER_NAME;
import static by.kononov.fest.mail.MailData.USER_PASSWORD;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.kononov.fest.exception.ServiceException;

/**
 * The MailSender class.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-17
 */
public class MailSender{
	final static Logger logger = LogManager.getLogger();

	/**
	 * The private class constructor to hide the implicit public one.
	 */
	private MailSender() {
	}

	/**
	 * This method sends the default email to user.
	 *
	 * @param email - the string with email address;
	 * @throws ServiceException, if sending mail failed;
	 */
	public static void sendMail(String email) throws ServiceException {
		String from = MailManager.getProperty(USER_NAME);
		String password = MailManager.getProperty(USER_PASSWORD);
		String subject = MailManager.getProperty(MAIL_SUBJECT);
		String body = MailManager.getProperty(MAIL_BODY);
		String host = MailManager.getProperty(SMTP_HOST);
		String port = MailManager.getProperty(SMTP_PORT);
		String starttls = MailManager.getProperty(SMTP_START_TLS);
		String auth = MailManager.getProperty(SMTP_AUTH);
		String to = email;
		Properties properties = System.getProperties();
		properties.put(SMTP_START_TLS, starttls);
		properties.put(SMTP_HOST, host);
		properties.put(SMTP_USER, from);
		properties.put(SMTP_PASSWORD, password);
		properties.put(SMTP_PORT, port);
		properties.put(SMTP_AUTH, auth);
		Session session = Session.getDefaultInstance(properties);
		MimeMessage message = new MimeMessage(session);
		Transport transport = null;
		try {
			message.setFrom(new InternetAddress(from));
			InternetAddress toAddress = new InternetAddress(to);
			message.addRecipient(Message.RecipientType.TO, toAddress);
			message.setSubject(subject);
			message.setText(body);
			transport = session.getTransport(TRANSPORT);
			transport.connect(host, from, password);
			transport.sendMessage(message, message.getAllRecipients());
		} catch (MessagingException e) {
			throw new ServiceException("can't send mail ", e);
		} finally {
			try {
				if (transport != null) {
					transport.close();
				}
			} catch (MessagingException e) {
				logger.error("can't close mail transport ", e);
			}
		}
	}
}