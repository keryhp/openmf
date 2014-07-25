package uk.ac.openmf.model.nosql;

import uk.ac.openmf.model.OpenMFLoanProduct;
import uk.ac.openmf.model.OpenMFLoanProductManager;
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
public class OpenMFLoanProductManagerNoSql extends OpenMFEntityManagerNoSql<OpenMFLoanProduct> implements OpenMFLoanProductManager {

	  private final OpenMFUserManagerNoSql userManager;

	  public OpenMFLoanProductManagerNoSql(OpenMFUserManagerNoSql userManager) {
	    super(OpenMFLoanProduct.class);
	    this.userManager = userManager;
	  }

	  @Override
	  public OpenMFLoanProduct getLoanProduct(Long loanProductId) {
	    return getEntity(createLoanProductKey(null, loanProductId));
	  }

	  @Override
	  public Iterable<OpenMFLoanProduct> getAllLoanProduct() {
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
	  public Key createLoanProductKey(String userId, Long loanproductid) {
		  if (userId != null) {
		      Key parentKey = KeyFactory.createKey(OpenMFConstants.ENTITY_USER_TYPE_GAE, userId);
		      return KeyFactory.createKey(parentKey, getKind(), loanproductid);
		    } else {
		      return KeyFactory.createKey(getKind(), loanproductid);
		    }
	  }

	  @Override
	  public OpenMFLoanProductNoSql fromParentKey(Key parentKey) {
	    return new OpenMFLoanProductNoSql(parentKey, getKind());
	  }

	  @Override
	  public OpenMFLoanProductNoSql newLoanProduct(String userId) {
	    return new OpenMFLoanProductNoSql(null, getKind());
	  }

	  @Override
	  protected OpenMFLoanProductNoSql fromEntity(Entity entity) {
	    return new OpenMFLoanProductNoSql(entity);
	  }
}
