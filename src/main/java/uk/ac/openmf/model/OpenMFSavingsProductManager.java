package uk.ac.openmf.model;

/**
 * User manager interface.
 *
 */
public interface OpenMFSavingsProductManager extends OpenMFEntityManager<OpenMFSavingsProduct> {
	 OpenMFSavingsProduct getSavingsProduct(Long savingsProductId);
	  
	  Iterable<OpenMFSavingsProduct> getAllSavingsProduct();

	  /**
	   * Creates a new SavingsProduct object.
	   *
	   * @return a SavingsProduct entity.
	   */
	  OpenMFSavingsProduct newSavingsProduct(String userId);
	  OpenMFSavingsProduct getSavingsProductBySavingsCode(String savingscode);
}
