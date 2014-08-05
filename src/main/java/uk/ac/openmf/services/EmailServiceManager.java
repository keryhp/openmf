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
import uk.ac.openmf.utils.OMFUtils;
import uk.ac.openmf.web.AppContext;
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

	public void sendEmail(OpenMFUser user, StringBuilder sb, String subject, OpenMFUser ccuser) throws UnsupportedEncodingException{
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);		
		String appid = System.getProperty("com.google.appengine.application.id");
		String senderAddress = "admin@" + appid + ".appspotmail.com";
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(senderAddress, "OpenMFI Admin"));
			msg.addRecipient(Message.RecipientType.TO,
					new InternetAddress(user.getEmail(), user.getUsername()));
			if(ccuser != null)
				msg.addRecipient(Message.RecipientType.CC, new InternetAddress(ccuser.getEmail(), ccuser.getUsername()));
			msg.setSubject(subject);
			msg.setContent(sb.toString(), "text/html");
			Transport.send(msg);

		} catch (AddressException e) {
			logger.severe("Error sending email 2\n");
			e.printStackTrace();
		} catch (MessagingException e) {
			// ...
			logger.severe("Error sending email 3\n");
			e.printStackTrace();

		}
	}
}
