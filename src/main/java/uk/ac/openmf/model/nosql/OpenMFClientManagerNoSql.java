package uk.ac.openmf.model.nosql;

import uk.ac.openmf.model.OpenMFClient;
import uk.ac.openmf.model.OpenMFClientManager;
import uk.ac.openmf.model.OpenMFUser;
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
public class OpenMFClientManagerNoSql extends OpenMFEntityManagerNoSql<OpenMFClient> implements OpenMFClientManager {

	  private final OpenMFUserManagerNoSql userManager;

	  public OpenMFClientManagerNoSql(OpenMFUserManagerNoSql userManager) {
	    super(OpenMFClient.class);
	    this.userManager = userManager;
	  }

	  @Override
	  public OpenMFClient getClient(Long clientId) {
	    return getEntity(createClientKey(null, clientId));
	  }

	  @Override
	  public Iterable<OpenMFClient> getAllClients() {
	    Query query = new Query(getKind());
	    query.addSort(OpenMFConstants.FIELD_NAME_TIMESTAMP, SortDirection.DESCENDING);
	    FetchOptions options = FetchOptions.Builder.withLimit(100);
	    return queryEntities(query, options);
	  }

	  /**
	   * Creates a role entity key.
	   *
	   * @param userId the user id. If null, no parent key is set.
	   * @param clientId
	   * @return a datastore key object.
	   */
	  public Key createClientKey(String userId, Long clientId) {
	    if (userId != null) {
	      Key parentKey = KeyFactory.createKey(OpenMFConstants.ENTITY_USER_TYPE_GAE, userId);
	      return KeyFactory.createKey(parentKey, getKind(), clientId);
	    } else {
	      return KeyFactory.createKey(getKind(), clientId);
	    }
	  }

	  @Override
	  public OpenMFClientNoSql fromParentKey(Key parentKey) {
	    return new OpenMFClientNoSql(parentKey, getKind());
	  }

	  @Override
	  public OpenMFClientNoSql newClient(String userId) {
	    return new OpenMFClientNoSql(null, getKind());
	  }

	  @Override
	  protected OpenMFClientNoSql fromEntity(Entity entity) {
	    return new OpenMFClientNoSql(entity);
	  }
}
