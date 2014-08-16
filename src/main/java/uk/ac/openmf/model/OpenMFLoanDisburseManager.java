package uk.ac.openmf.model;

/**
 * OpenMFClientManager interface.
 *
 */
public interface OpenMFLoanDisburseManager extends OpenMFEntityManager<OpenMFLoanDisburse> {
	
	 OpenMFLoanDisburse getLoanDisburse(Long loandisburseId);
	  
	  Iterable<OpenMFLoanDisburse> getAllLoanDisburses();
	  Iterable<OpenMFLoanDisburse> getLoanDisbursesByLoanAccount(String loanaccountid);

	  /**
	   * Creates a new Client object.
	   *
	   * @return a Client entity.
	   */
	  OpenMFLoanDisburse newLoanDisburse(String userId);
	  String getTotalLoanDisburseAmtByDates(String fromdate, String todate);
}
