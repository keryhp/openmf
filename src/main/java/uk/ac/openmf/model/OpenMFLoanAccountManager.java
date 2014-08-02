package uk.ac.openmf.model;


/**
 * User manager interface.
 *
 */
public interface OpenMFLoanAccountManager extends OpenMFEntityManager<OpenMFLoanAccount> {
	 OpenMFLoanAccount getLoanAccount(Long loanAccountId);
	  
	  Iterable<OpenMFLoanAccount> getAllLoanAccounts();

	  Iterable<OpenMFLoanAccount> getAllLoanAccountsByClient(String clientId);
	  
	  Iterable<OpenMFLoanAccount> getAllLoanAccountByProduct(String loancode);
	  /**
	   * Creates a new LoanAccount object.
	   *
	   * @return a LoanAccount entity.
	   */
	  OpenMFLoanAccount newLoanAccount(String userId);

	Iterable<OpenMFLoanAccount> getAllLoanAccountsByGroup(String groupid);
}
