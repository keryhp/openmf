package uk.ac.openmf.model;


/**
 * Entity manager class to support roles datastore operations.
 *
 */
public interface OpenMFGeneralJournalManager extends OpenMFEntityManager<OpenMFGeneralJournal> {

	OpenMFGeneralJournal getGeneralJournal(Long generaljournalId);

	Iterable<OpenMFGeneralJournal> getAllGeneralJournal();
	Iterable<OpenMFGeneralJournal> getGeneralJournalByDate(String mfiaccounttype, String date);
	OpenMFGeneralJournal getGeneralJournalByCoAandDate(String coaid, String date);
	/**
	 * Creates a new role object.
	 *
	 * @return a role entity.
	 */
	OpenMFGeneralJournal newGeneralJournal(String userId);
}
