package by.kononov.fest.mail;

/**
 * The MailData class.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-17
 */
class MailData{
	static final String MAIL_BODY = "mail.body";
	static final String MAIL_SUBJECT = "mail.subject";
	static final String SMTP_AUTH = "mail.smtp.auth";
	static final String SMTP_HOST = "mail.smtp.host";
	static final String SMTP_PASSWORD = "mail.smtp.password";
	static final String SMTP_PORT = "mail.smtp.port";
	static final String SMTP_START_TLS = "mail.smtp.starttls.enable";
	static final String SMTP_USER = "mail.smtp.user";
	static final String TRANSPORT = "smtp";
	static final String USER_NAME = "mail.name";
	static final String USER_PASSWORD = "mail.password";

	/**
	 * The private class constructor to hide the implicit public one.
	 */
	private MailData() {
	}
}