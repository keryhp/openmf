package uk.ac.openmf.services;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import uk.ac.openmf.model.OpenMFUser;
import uk.ac.openmf.web.ConfigManager;
/**
 * The main service class providing some helper method for sending Emails.
 *
 */
public class EmailServiceManager {
	private static final Logger logger =
			Logger.getLogger(EmailServiceManager.class.getCanonicalName());

	private ConfigManager configManager;

	public EmailServiceManager(ConfigManager configManager) {
		this.configManager = configManager;
	}

	public void sendEmail(OpenMFUser user) throws UnsupportedEncodingException{
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		//String msgBody = "Dear "+ user.getName() +",\n please find the list of your events and their bookings.";
		StringBuilder sb = new StringBuilder();
		//TODO get the reporting work here
		sb.append("</table></body></html>");
		
		String appid = System.getProperty("com.google.appengine.application.id");
		String senderAddress = "admin@" + appid + ".appspotmail.com";
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(senderAddress, "OpenMFI Admin"));
			msg.addRecipient(Message.RecipientType.TO,
					new InternetAddress(user.getEmail(), user.getUsername()));
			msg.setSubject("[Daily mail] Your task list");
			msg.setContent(sb.toString(), "text/html");
			Transport.send(msg);

		} catch (AddressException e) {
			System.out.println("Error sending email 2\n");
			e.printStackTrace();
		} catch (MessagingException e) {
			// ...
			System.out.println("Error sending email 3\n");
			e.printStackTrace();

		}
	}
}
