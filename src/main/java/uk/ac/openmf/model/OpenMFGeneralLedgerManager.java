package uk.ac.openmf.model;


/**
 * Entity manager class to support roles datastore operations.
 *
 */
public interface OpenMFGeneralLedgerManager extends OpenMFEntityManager<OpenMFGeneralLedger> {
  
  OpenMFGeneralLedger getGeneralLedger(Long generalledgerId);
  
  Iterable<OpenMFGeneralLedger> getAllGeneralLedger();
  //Iterable<OpenMFGeneralLedger> getGeneralLedgerByDate(String mfiaccounttype, String date);
  Iterable<OpenMFGeneralLedger> getGeneralLedgerByDate(String coaid, String date); 
  OpenMFGeneralLedger getGeneralLedgerByCoAandDate(String coaid, String date);
  /**
   * Creates a new role object.
   *
   * @return a role entity.
   */
  OpenMFGeneralLedger newGeneralLedger(String userId);
}
