package uk.ac.openmf.model;

/**
 * Savings Account manager interface.
 *
 */
public interface OpenMFSavingsAccountManager extends OpenMFEntityManager<OpenMFSavingsAccount> {
	
	OpenMFSavingsAccount getSavingsAccount(Long savingsAccountId);

	Iterable<OpenMFSavingsAccount> getAllSavingsAccounts();

	Iterable<OpenMFSavingsAccount> getAllSavingsAccountsByClient(String clientId);

	Iterable<OpenMFSavingsAccount> getAllSavingsAccountByProduct(String savingscode);
	/**
	 * Creates a new SavingsAccount object.
	 *
	 * @return a SavingsAccount entity.
	 */
	OpenMFSavingsAccount newSavingsAccount(String userId);
}
