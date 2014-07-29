package uk.ac.openmf.web;

import java.util.logging.Logger;

import uk.ac.openmf.model.OpenMFClientManager;
import uk.ac.openmf.model.OpenMFEntityManagerFactory;
import uk.ac.openmf.model.OpenMFLoanAccountManager;
import uk.ac.openmf.model.OpenMFLoanActualPaymentManager;
import uk.ac.openmf.model.OpenMFLoanProductManager;
import uk.ac.openmf.model.OpenMFLoanRepaymentManager;
import uk.ac.openmf.model.OpenMFRolesManager;
import uk.ac.openmf.model.OpenMFSavingsProductManager;
import uk.ac.openmf.model.OpenMFUser;
import uk.ac.openmf.model.OpenMFUserManager;
import uk.ac.openmf.security.AppRole;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * A convenient singleton context object for the application.
 *
 */
public class AppContext {
	private static final Logger logger = Logger.getLogger(AppContext.class.getCanonicalName());

	private static AppContext instance = new AppContext();

	private ConfigManager configManager;

	private OpenMFEntityManagerFactory entityManagerFactory;

	// Prevent the class being instantiated externally.
	private AppContext() {
		configManager = new ConfigManager();

		String clsName = configManager.getDemoEntityManagerFactory();
		try {
			Class<?> cls = Class.forName(clsName);
			entityManagerFactory = (OpenMFEntityManagerFactory) cls.newInstance();
			entityManagerFactory.init(configManager);
		} catch (ClassNotFoundException e) {
			logger.severe("cannot find demo entity manager factory class:" + e.getMessage());
			throw new RuntimeException("cannot find demo entity manager factory class", e);
		} catch (InstantiationException e) {
			logger.severe("cannot create instance of entity manager factory");
			throw new RuntimeException("cannot create instance of entity manager factory", e);
		} catch (IllegalAccessException e) {
			logger.severe("cannot create instance of entity manager factory");
			throw new RuntimeException("cannot create instance of entity manager factory", e);
		}
//		photoServiceManager = new PhotoServiceManager(configManager, getPhotoManager());
//		emailServiceManager = new EmailServiceManager(configManager, getEventManager(), getBookingManager());
	}

	public static AppContext getAppContext() {
		return instance;
	}

	/**
	 * @return the demoUserManager
	 */
	public OpenMFUserManager getUserManager() {
		return entityManagerFactory.getUserManager();
	}
	
	public OpenMFRolesManager getRolesManager() {
		return entityManagerFactory.getRolesManager();
	}
	
	public OpenMFLoanProductManager getLoanProductManager() {
		return entityManagerFactory.getLoanProductManager();
	}

	public OpenMFClientManager getClientManager() {
		return entityManagerFactory.getClientManager();
	}

	public ConfigManager getConfigManager() {
		return configManager;
	}
	
	public OpenMFSavingsProductManager getSavingsProductManager() {
		return entityManagerFactory.getSavingsProductManager();
	}

	public OpenMFLoanAccountManager getLoanAccountManager() {
		return entityManagerFactory.getLoanAccountManager();
	}
	
	public OpenMFLoanRepaymentManager getLoanRepaymentManager() {
		return entityManagerFactory.getLoanRepaymentManager();
	}
	
	public OpenMFLoanActualPaymentManager getLoanActualPaymentManager() {
		return entityManagerFactory.getLoanActualPaymentManager();
	}


	public OpenMFUser getCurrentUser() {
		OpenMFUserManager openMFUserManager = entityManagerFactory.getUserManager();
		User user = UserServiceFactory.getUserService().getCurrentUser();
		if(user == null){
			return null;
		}
		OpenMFUser openMFUser = openMFUserManager.getUserByEmail(user.getEmail());
		if (openMFUser == null) {
			openMFUser = openMFUserManager.newUser(user.getUserId());
			openMFUser.setEmail(user.getEmail());
			openMFUser.setUsername(user.getNickname());
			openMFUser.setRole(AppRole.NEW_USER.toString());
			//openMFUserManager.upsertEntity(openMFUser);
		}
		return openMFUser;
	}
}
