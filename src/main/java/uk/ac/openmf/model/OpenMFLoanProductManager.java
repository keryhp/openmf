package uk.ac.openmf.model;

/**
 * User manager interface.
 *
 */
public interface OpenMFLoanProductManager extends OpenMFEntityManager<OpenMFLoanProduct> {

	  OpenMFLoanProduct getLoanProduct(Long loanProductId);
	  
	  Iterable<OpenMFLoanProduct> getAllLoanProduct();

	  OpenMFLoanProduct newLoanProduct(String userId);
	  
	  OpenMFLoanProduct getLoanProductByLoanCode(String loancode);
}
