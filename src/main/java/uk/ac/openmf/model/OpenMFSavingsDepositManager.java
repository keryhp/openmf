package uk.ac.openmf.model;

/**
 * OpenMFClientManager interface.
 *
 */
public interface OpenMFSavingsDepositManager extends OpenMFEntityManager<OpenMFSavingsDeposit> {
	
	 OpenMFSavingsDeposit getSavingsDeposit(Long savingsdepositId);
	  
	  Iterable<OpenMFSavingsDeposit> getAllSavingsDeposits();
	  Iterable<OpenMFSavingsDeposit> getSavingsDepositsBySavingsAccount(String savingsaccountid);

	  /**
	   * Creates a new Client object.
	   *
	   * @return a Client entity.
	   */
	  OpenMFSavingsDeposit newSavingsDeposit(String userId);
}
