package uk.ac.openmf.model;


/**
 * Entity manager class to support roles datastore operations.
 *
 */
public interface OpenMFRolesManager extends OpenMFEntityManager<OpenMFRoles> {
  
  OpenMFRoles getRole(Long roleId);
  
  Iterable<OpenMFRoles> getAllRoles();

  /**
   * Creates a new role object.
   *
   * @return a role entity.
   */
  OpenMFRoles newRole(String userId);
}
