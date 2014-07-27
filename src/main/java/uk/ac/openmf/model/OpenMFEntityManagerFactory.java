package uk.ac.openmf.model;

import uk.ac.openmf.web.ConfigManager;


/**
 * The entity manager factory interface.
 *
 * @author harish
 */
public interface OpenMFEntityManagerFactory {

	/**
	 * Initiates the factory object.
	 *
	 * @param configManager the configuration manager.
	 */
	
	void init(ConfigManager configManager);
	/**
	 * Gets the user manager.
	 *
	 * @return the user manager object.
	 */
	OpenMFUserManager getUserManager();

	OpenMFRolesManager getRolesManager();
	
	OpenMFLoanProductManager getLoanProductManager();
	
	OpenMFSavingsProductManager getSavingsProductManager();
	
	OpenMFClientManager getClientManager();
	
	OpenMFLoanAccountManager getLoanAccountManager();
}
