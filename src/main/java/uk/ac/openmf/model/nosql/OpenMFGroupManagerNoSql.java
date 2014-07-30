package uk.ac.openmf.model.nosql;

import uk.ac.openmf.model.OpenMFGroup;
import uk.ac.openmf.model.OpenMFGroupManager;
import uk.ac.openmf.utils.OpenMFConstants;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;

/**
 * User manager class for NoSQL.
 *
 */
public class OpenMFGroupManagerNoSql extends OpenMFEntityManagerNoSql<OpenMFGroup> implements OpenMFGroupManager {

	  private final OpenMFUserManagerNoSql userManager;

	  public OpenMFGroupManagerNoSql(OpenMFUserManagerNoSql userManager) {
	    super(OpenMFGroup.class);
	    this.userManager = userManager;
	  }

	  @Override
	  public OpenMFGroup getGroup(Long GroupId) {
	    return getEntity(createGroupKey(null, GroupId));
	  }

	  @Override
	  public Iterable<OpenMFGroup> getAllGroups() {
	    Query query = new Query(getKind());
	    query.addSort(OpenMFConstants.FIELD_NAME_TIMESTAMP, SortDirection.DESCENDING);
	    FetchOptions options = FetchOptions.Builder.withLimit(100);
	    return queryEntities(query, options);
	  }

	  /**
	   * Creates a role entity key.
	   *
	   * @param userId the user id. If null, no parent key is set.
	   * @param GroupId
	   * @return a datastore key object.
	   */
	  public Key createGroupKey(String userId, Long GroupId) {
	    if (userId != null) {
	      Key parentKey = KeyFactory.createKey(OpenMFConstants.ENTITY_USER_TYPE_GAE, userId);
	      return KeyFactory.createKey(parentKey, getKind(), GroupId);
	    } else {
	      return KeyFactory.createKey(getKind(), GroupId);
	    }
	  }

	  @Override
	  public OpenMFGroupNoSql fromParentKey(Key parentKey) {
	    return new OpenMFGroupNoSql(parentKey, getKind());
	  }

	  @Override
	  public OpenMFGroupNoSql newGroup(String userId) {
	    return new OpenMFGroupNoSql(null, getKind());
	  }

	  @Override
	  protected OpenMFGroupNoSql fromEntity(Entity entity) {
	    return new OpenMFGroupNoSql(entity);
	  }
}
