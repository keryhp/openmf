package uk.ac.openmf.model;

/**
 * OpenMFGroupManager interface.
 *
 */
public interface OpenMFGroupManager extends OpenMFEntityManager<OpenMFGroup> {
	
	 OpenMFGroup getGroup(Long GroupId);
	  
	  Iterable<OpenMFGroup> getAllGroups();

	  Iterable<OpenMFGroup> getGroupsBySupervisor(String username);
	  
	  /**
	   * Creates a new Group object.
	   *
	   * @return a Group entity.
	   */
	  OpenMFGroup newGroup(String userId);
	  
}
