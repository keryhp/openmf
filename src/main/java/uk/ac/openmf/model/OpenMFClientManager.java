package uk.ac.openmf.model;

/**
 * OpenMFClientManager interface.
 *
 */
public interface OpenMFClientManager extends OpenMFEntityManager<OpenMFClient> {
	
	 OpenMFClient getClient(Long clientId);
	  
	  Iterable<OpenMFClient> getAllClients();

	  /**
	   * Creates a new Client object.
	   *
	   * @return a Client entity.
	   */
	  OpenMFClient newClient(String userId);
}
