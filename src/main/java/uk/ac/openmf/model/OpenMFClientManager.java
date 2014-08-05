package uk.ac.openmf.model;

/**
 * OpenMFClientManager interface.
 *
 */
public interface OpenMFClientManager extends OpenMFEntityManager<OpenMFClient> {

	OpenMFClient getClient(Long clientId);

	Iterable<OpenMFClient> getAllClients();

	Iterable<OpenMFClient> getClientsByGroupId(String groupid);
	
	Iterable<OpenMFClient> getClientsBySupervisor(String username);

	/**
	 * Creates a new Client object.
	 *
	 * @return a Client entity.
	 */
	OpenMFClient newClient(String userId);
}
