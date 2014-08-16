package uk.ac.openmf.model;

import java.util.List;

/**
 * OpenMFClientManager interface.
 *
 */
public interface OpenMFClientManager extends OpenMFEntityManager<OpenMFClient> {

	OpenMFClient getClient(Long clientId);

	Iterable<OpenMFClient> getAllClients();

	Iterable<OpenMFClient> getClientsByGroupId(String groupid);
	
	Iterable<OpenMFClient> getClientsBySupervisor(String username);
	
	List<String> getClientStat();

	/**
	 * Creates a new Client object.
	 *
	 * @return a Client entity.
	 */
	OpenMFClient newClient(String userId);
}
