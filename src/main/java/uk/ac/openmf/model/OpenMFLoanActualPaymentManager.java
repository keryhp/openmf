package uk.ac.openmf.model;

/**
 * OpenMFClientManager interface.
 *
 */
public interface OpenMFLoanActualPaymentManager extends OpenMFEntityManager<OpenMFLoanActualPayment> {
	
	 OpenMFLoanActualPayment getLoanActualPayment(Long loanactualpaymentId);
	  
	  Iterable<OpenMFLoanActualPayment> getAllLoanActualPayments();
	  Iterable<OpenMFLoanActualPayment> getLoanActualPaymentsByLoanAccount(String loanaccountid);

	  /**
	   * Creates a new Client object.
	   *
	   * @return a Client entity.
	   */
	  OpenMFLoanActualPayment newLoanActualPayment(String userId);
}
