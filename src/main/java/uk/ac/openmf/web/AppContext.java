package uk.ac.openmf.web;

import java.util.logging.Logger;

import uk.ac.openmf.model.OpenMFChartOfAccountsManager;
import uk.ac.openmf.model.OpenMFClientManager;
import uk.ac.openmf.model.OpenMFEntityManagerFactory;
import uk.ac.openmf.model.OpenMFGeneralJournalManager;
import uk.ac.openmf.model.OpenMFGeneralLedgerManager;
import uk.ac.openmf.model.OpenMFGroupManager;
import uk.ac.openmf.model.OpenMFLoanAccountManager;
import uk.ac.openmf.model.OpenMFLoanActualPaymentManager;
import uk.ac.openmf.model.OpenMFLoanDisburseManager;
import uk.ac.openmf.model.OpenMFLoanProductManager;
import uk.ac.openmf.model.OpenMFLoanRepaymentManager;
import uk.ac.openmf.model.OpenMFPhotoManager;
import uk.ac.openmf.model.OpenMFRolesManager;
import uk.ac.openmf.model.OpenMFSavingsAccountManager;
import uk.ac.openmf.model.OpenMFSavingsDepositManager;
import uk.ac.openmf.model.OpenMFSavingsProductManager;
import uk.ac.openmf.model.OpenMFSavingsScheduledDepositManager;
import uk.ac.openmf.model.OpenMFSavingsWithdrawalManager;
import uk.ac.openmf.model.OpenMFTasksManager;
import uk.ac.openmf.model.OpenMFTransactionManager;
import uk.ac.openmf.model.OpenMFUser;
import uk.ac.openmf.model.OpenMFUserManager;
import uk.ac.openmf.security.AppRole;
import uk.ac.openmf.services.EmailServiceManager;
import uk.ac.openmf.services.PhotoServiceManager;

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

	private PhotoServiceManager photoServiceManager;
	private EmailServiceManager emailServiceManager;
	private OpenMFUser openMFUser = null;
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
		photoServiceManager = new PhotoServiceManager(configManager, getPhotoManager());
		emailServiceManager = new EmailServiceManager(configManager);
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

	public OpenMFGroupManager getGroupManager() {
		return entityManagerFactory.getGroupManager();
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

	public OpenMFSavingsAccountManager getSavingsAccountManager() {
		return entityManagerFactory.getSavingsAccountManager();
	}

	public OpenMFLoanRepaymentManager getLoanRepaymentManager() {
		return entityManagerFactory.getLoanRepaymentManager();
	}

	public OpenMFLoanActualPaymentManager getLoanActualPaymentManager() {
		return entityManagerFactory.getLoanActualPaymentManager();
	}

	public OpenMFLoanDisburseManager getLoanDisburseManager() {
		return entityManagerFactory.getLoanDisburseManager();
	}

	public OpenMFSavingsDepositManager getSavingsDepositManager() {
		return entityManagerFactory.getSavingsDepositManager();
	}

	public OpenMFSavingsWithdrawalManager getSavingsWithdrawalManager() {
		return entityManagerFactory.getSavingsWithdrawalManager();
	}

	public OpenMFSavingsScheduledDepositManager getSavingsScheduledDepositManager() {
		return entityManagerFactory.getSavingsScheduledDepositManager();
	}

	public OpenMFPhotoManager getPhotoManager(){
		return entityManagerFactory.getPhotoManager();
	}

	public OpenMFChartOfAccountsManager getChartOfAccountsManager(){
		return entityManagerFactory.getChartOfAccountsManager();
	}

	public OpenMFGeneralJournalManager getGeneralJournalManager(){
		return entityManagerFactory.getGeneralJournalManager();
	}

	public OpenMFGeneralLedgerManager getGeneralLedgerManager(){
		return entityManagerFactory.getGeneralLedgerManager();
	}

	public OpenMFTransactionManager getTransactionManager(){
		return entityManagerFactory.getTransactionManager();
	}

	public OpenMFTasksManager getTasksManager(){
		return entityManagerFactory.getTasksManager();
	}

	public PhotoServiceManager getPhotoServiceManager(){
		return this.photoServiceManager;
	}

	public EmailServiceManager getEmailServiceManager(){
		return this.emailServiceManager;
	}

	public OpenMFUser getCurrentUser() {
		OpenMFUserManager openMFUserManager = entityManagerFactory.getUserManager();
		User user = UserServiceFactory.getUserService().getCurrentUser();
		if(user == null){
			return null;
		}
		OpenMFUser openMFUser = openMFUserManager.getUserByEmail(user.getEmail());
		if (openMFUser == null) {
			openMFUser = openMFUserManager.newUser();
			openMFUser.setEmail(user.getNickname());
			openMFUser.setRole(AppRole.NEW_USER.toString());			
			//openMFUserManager.upsertEntity(openMFUser);
		}
		return openMFUser;
	}

	public void setCurrentUser(OpenMFUser openMFUser){
		this.openMFUser = openMFUser;
	}
}
