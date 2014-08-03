package uk.ac.openmf.model;


/**
 * Entity manager class to support roles datastore operations.
 *
 */
public interface OpenMFTransactionManager extends OpenMFEntityManager<OpenMFTransaction> {
  
  OpenMFTransaction getTransaction(Long transactionId);
  
  Iterable<OpenMFTransaction> getAllTransaction();

  Iterable<OpenMFTransaction> getTransactionsByClientId(String clientId);

  /**
   * Creates a new role object.
   *
   * @return a role entity.
   */
  OpenMFTransaction newTransaction(String userId);
}
