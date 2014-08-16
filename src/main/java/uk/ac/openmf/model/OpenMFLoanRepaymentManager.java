package uk.ac.openmf.model;

/**
 * OpenMFClientManager interface.
 *
 */
public interface OpenMFLoanRepaymentManager extends OpenMFEntityManager<OpenMFLoanRepayment> {
	
	 OpenMFLoanRepayment getLoanRepaymentScheduleItem(Long loanrepaymentscheduleId);
	  
	  Iterable<OpenMFLoanRepayment> getAllLoanRepaymentSchedules();
	  Iterable<OpenMFLoanRepayment> getLoanRepaymentSchedulesByLoanAccount(String loanaccountid);
	  Iterable<OpenMFLoanRepayment> getScheduledRepaymentBySupervisorAndDate(String supervisor, String date);
	  Iterable<OpenMFLoanRepayment> getAllScheduledRepaymentByDate(String date);

	  /**
	   * Creates a new Client object.
	   *
	   * @return a Client entity.
	   */
	  OpenMFLoanRepayment newLoanRepaymentSchedule(String userId);
	  String getTotalScheduledRepmntAmtByDates(String fromdate, String todate);
}
