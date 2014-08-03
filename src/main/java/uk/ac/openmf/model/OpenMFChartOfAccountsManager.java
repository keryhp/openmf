package uk.ac.openmf.model;


/**
 * Entity manager class to support roles datastore operations.
 *
 */
public interface OpenMFChartOfAccountsManager extends OpenMFEntityManager<OpenMFChartOfAccounts> {
  
  OpenMFChartOfAccounts getChartOfAccounts(Long chartofaccountsId);

  OpenMFChartOfAccounts getChartOfAccountsByMFIAccountType(String mfiaccounttype);

  Iterable<OpenMFChartOfAccounts> getAllChartOfAccounts();

  /**
   * Creates a new role object.
   *
   * @return a role entity.
   */
  OpenMFChartOfAccounts newChartOfAccounts(String userId);
}
