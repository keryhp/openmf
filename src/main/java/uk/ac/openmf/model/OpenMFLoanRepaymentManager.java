package uk.ac.openmf.model;

/**
 * OpenMFClientManager interface.
 *
 */
public interface OpenMFLoanRepaymentManager extends OpenMFEntityManager<OpenMFLoanRepayment> {
	
	 OpenMFLoanRepayment getLoanRepaymentScheduleItem(Long loanrepaymentscheduleId);
	  
	  Iterable<OpenMFLoanRepayment> getAllLoanRepaymentSchedules();
	  Iterable<OpenMFLoanRepayment> getLoanRepaymentSchedulesByLoanAccount(String loanaccountid);

	  /**
	   * Creates a new Client object.
	   *
	   * @return a Client entity.
	   */
	  OpenMFLoanRepayment newLoanRepaymentSchedule(String userId);
}
