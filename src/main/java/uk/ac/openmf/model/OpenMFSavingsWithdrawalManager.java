package uk.ac.openmf.model;

/**
 * OpenMFClientManager interface.
 *
 */
public interface OpenMFSavingsWithdrawalManager extends OpenMFEntityManager<OpenMFSavingsWithdrawal> {
	
	 OpenMFSavingsWithdrawal getSavingsWithdrawal(Long savingswithdrawalId);
	  
	  Iterable<OpenMFSavingsWithdrawal> getAllSavingsWithdrawals();
	  Iterable<OpenMFSavingsWithdrawal> getSavingsWithdrawalsBySavingsAccount(String savingsaccountid);

	  /**
	   * Creates a new Client object.
	   *
	   * @return a Client entity.
	   */
	  OpenMFSavingsWithdrawal newSavingsWithdrawal(String userId);
}
