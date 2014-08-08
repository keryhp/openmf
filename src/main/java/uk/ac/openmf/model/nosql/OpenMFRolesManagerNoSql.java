package uk.ac.openmf.model.nosql;

import uk.ac.openmf.model.OpenMFRoles;
import uk.ac.openmf.model.OpenMFRolesManager;
import uk.ac.openmf.utils.OpenMFConstants;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;


/**
 * Entity manager class to support role datastore operations.
 *
 */
public class OpenMFRolesManagerNoSql extends OpenMFEntityManagerNoSql<OpenMFRoles>
    implements OpenMFRolesManager {
	
  private final OpenMFUserManagerNoSql userManager;

  public OpenMFRolesManagerNoSql(OpenMFUserManagerNoSql userManager) {
    super(OpenMFRoles.class);
    this.userManager = userManager;
  }

  @Override
  public OpenMFRoles getRole(Long roleId) {
    return getEntity(createRoleKey(null, roleId));
  }

  @Override
  public Iterable<OpenMFRoles> getAllRoles() {
    Query query = new Query(getKind());
    query.addSort(OpenMFConstants.FIELD_NAME_TIMESTAMP, SortDirection.DESCENDING);
    FetchOptions options = FetchOptions.Builder.withLimit(10000);
    return queryEntities(query, options);
  }

  /**
   * Creates a role entity key.
   *
   * @param userId the user id. If null, no parent key is set.
   * @param roleId
   * @return a datastore key object.
   */
  public Key createRoleKey(Long userId, Long roleId) {
	  if (userId != null) {
	      Key parentKey = KeyFactory.createKey(OpenMFConstants.ENTITY_USER_TYPE_GAE, userId);
	      return KeyFactory.createKey(parentKey, getKind(), roleId);
	    } else {
	      return KeyFactory.createKey(getKind(), roleId);
	    }
  }

  @Override
  public OpenMFRolesNoSql fromParentKey(Key parentKey) {
    return new OpenMFRolesNoSql(parentKey, getKind());
  }

  @Override
  public OpenMFRolesNoSql newRole(String userId) {
    return new OpenMFRolesNoSql(null, getKind());
  }

  @Override
  protected OpenMFRolesNoSql fromEntity(Entity entity) {
    return new OpenMFRolesNoSql(entity);
  }
}
