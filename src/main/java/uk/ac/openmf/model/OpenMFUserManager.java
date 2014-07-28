package uk.ac.openmf.model;

/**
 * User manager interface.
 *
 */
public interface OpenMFUserManager extends OpenMFEntityManager<OpenMFUser> {
  /**
   * Gets the user entity based on user id.
   *
   * @param userId the user id.
   *
   * @return the user entity; return null if user is not found.
   */
  OpenMFUser getUser(Long userId);

  /**
   * Gets all the user entities based.
   *
   * @return the user entity list; return null if user is not found.
   */
  Iterable<OpenMFUser> getAllUsers();

  /**
   * Creates a new user object. The object is not serialized to datastore yet.
   *
   * @param userId the user id.
   * @return the user object.
   */
  OpenMFUser newUser(String userId);
  OpenMFUser newUser();

OpenMFUser getUserByEmail(String email);
OpenMFUser getUserByUsername(String username);
}
