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
	
	OpenMFGroupManager getGroupManager();
	
	OpenMFLoanAccountManager getLoanAccountManager();
	
	OpenMFSavingsAccountManager getSavingsAccountManager();
	
	OpenMFLoanRepaymentManager getLoanRepaymentManager();
	
	OpenMFLoanActualPaymentManager getLoanActualPaymentManager();
	
	OpenMFLoanDisburseManager getLoanDisburseManager();
	
	OpenMFSavingsDepositManager getSavingsDepositManager();
	
	OpenMFSavingsWithdrawalManager getSavingsWithdrawalManager();
	
	OpenMFSavingsScheduledDepositManager getSavingsScheduledDepositManager();
	
	OpenMFPhotoManager getPhotoManager();
	
	OpenMFChartOfAccountsManager getChartOfAccountsManager();
	
	OpenMFGeneralJournalManager getGeneralJournalManager();
	
	OpenMFGeneralLedgerManager getGeneralLedgerManager();
	
	OpenMFTransactionManager getTransactionManager();
}
