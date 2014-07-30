package uk.ac.openmf.model;

/**
 * OpenMFClientManager interface.
 *
 */
public interface OpenMFSavingsScheduledDepositManager extends OpenMFEntityManager<OpenMFSavingsScheduledDeposit> {
	
	 OpenMFSavingsScheduledDeposit getSavingsScheduledDepositScheduleItem(Long savingsscheduleddepositId);
	  
	  Iterable<OpenMFSavingsScheduledDeposit> getAllSavingsScheduledDeposits();
	  Iterable<OpenMFSavingsScheduledDeposit> getSavingsScheduledDepositBySavingsAccount(String savingsaccountid);

	  /**
	   * Creates a new Client object.
	   *
	   * @return a Client entity.
	   */
	  OpenMFSavingsScheduledDeposit newSavingsScheduledDeposits(String userId);
}
