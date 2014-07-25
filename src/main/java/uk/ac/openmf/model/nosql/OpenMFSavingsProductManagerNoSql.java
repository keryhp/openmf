package uk.ac.openmf.model.nosql;

import uk.ac.openmf.model.OpenMFSavingsProduct;
import uk.ac.openmf.model.OpenMFSavingsProductManager;
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
public class OpenMFSavingsProductManagerNoSql extends OpenMFEntityManagerNoSql<OpenMFSavingsProduct> implements OpenMFSavingsProductManager {

	  private final OpenMFUserManagerNoSql userManager;

	  public OpenMFSavingsProductManagerNoSql(OpenMFUserManagerNoSql userManager) {
	    super(OpenMFSavingsProduct.class);
	    this.userManager = userManager;
	  }

	  @Override
	  public OpenMFSavingsProduct getSavingsProduct(Long savingsProductId) {
	    return getEntity(createSavingsProductKey(null, savingsProductId));
	  }

	  @Override
	  public Iterable<OpenMFSavingsProduct> getAllSavingsProduct() {
	    Query query = new Query(getKind());
	    query.addSort(OpenMFConstants.FIELD_NAME_TIMESTAMP, SortDirection.DESCENDING);
	    FetchOptions options = FetchOptions.Builder.withLimit(100);
	    return queryEntities(query, options);
	  }

	  /**
	   * Creates a role entity key.
	   *
	   * @param userId the user id. If null, no parent key is set.
	   * @param roleId
	   * @return a datastore key object.
	   */
	  public Key createSavingsProductKey(String userId, Long savingsproductid) {
		  if (userId != null) {
		      Key parentKey = KeyFactory.createKey(OpenMFConstants.ENTITY_USER_TYPE_GAE, userId);
		      return KeyFactory.createKey(parentKey, getKind(), savingsproductid);
		    } else {
		      return KeyFactory.createKey(getKind(), savingsproductid);
		    }
	  }

	  @Override
	  public OpenMFSavingsProductNoSql fromParentKey(Key parentKey) {
	    return new OpenMFSavingsProductNoSql(parentKey, getKind());
	  }

	  @Override
	  public OpenMFSavingsProductNoSql newSavingsProduct(String userId) {
	    return new OpenMFSavingsProductNoSql(null, getKind());
	  }

	  @Override
	  protected OpenMFSavingsProductNoSql fromEntity(Entity entity) {
	    return new OpenMFSavingsProductNoSql(entity);
	  }
}
