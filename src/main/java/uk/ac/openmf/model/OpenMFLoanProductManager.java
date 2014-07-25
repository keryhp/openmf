package uk.ac.openmf.model;

/**
 * User manager interface.
 *
 */
public interface OpenMFLoanProductManager extends OpenMFEntityManager<OpenMFLoanProduct> {
	 OpenMFLoanProduct getLoanProduct(Long loanProductId);
	  
	  Iterable<OpenMFLoanProduct> getAllLoanProduct();

	  /**
	   * Creates a new LoanProduct object.
	   *
	   * @return a LoanProduct entity.
	   */
	  OpenMFLoanProduct newLoanProduct(String userId);
}
