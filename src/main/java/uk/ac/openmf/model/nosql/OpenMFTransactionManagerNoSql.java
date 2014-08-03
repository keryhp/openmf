package uk.ac.openmf.model.nosql;

import java.util.ArrayList;

import uk.ac.openmf.model.OpenMFTransaction;
import uk.ac.openmf.model.OpenMFTransactionManager;
import uk.ac.openmf.utils.OpenMFConstants;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.SortDirection;


/**
 * Entity manager class to support role datastore operations.
 *
 */
public class OpenMFTransactionManagerNoSql extends OpenMFEntityManagerNoSql<OpenMFTransaction>
implements OpenMFTransactionManager {

	private final OpenMFUserManagerNoSql userManager;

	public OpenMFTransactionManagerNoSql(OpenMFUserManagerNoSql userManager) {
		super(OpenMFTransaction.class);
		this.userManager = userManager;
	}

	@Override
	public OpenMFTransaction getTransaction(Long chartofaccountsId) {
		return getEntity(createRoleKey(null, chartofaccountsId));
	}

	@Override
	public Iterable<OpenMFTransaction> getAllTransaction() {
		Query query = new Query(getKind());
		query.addSort(OpenMFConstants.FIELD_NAME_TIMESTAMP, SortDirection.DESCENDING);
		FetchOptions options = FetchOptions.Builder.withLimit(100);
		return queryEntities(query, options);
	}

	/**
	 * Creates a role entity key.
	 *
	 * @param userId the user id. If null, no parent key is set.
	 * @param chartofaccountsId
	 * @return a datastore key object.
	 */
	public Key createRoleKey(Long userId, Long chartofaccountsId) {
		if (userId != null) {
			Key parentKey = KeyFactory.createKey(OpenMFConstants.ENTITY_USER_TYPE_GAE, userId);
			return KeyFactory.createKey(parentKey, getKind(), chartofaccountsId);
		} else {
			return KeyFactory.createKey(getKind(), chartofaccountsId);
		}
	}

	@Override
	public OpenMFTransactionNoSql fromParentKey(Key parentKey) {
		return new OpenMFTransactionNoSql(parentKey, getKind());
	}

	@Override
	public OpenMFTransactionNoSql newTransaction(String userId) {
		return new OpenMFTransactionNoSql(null, getKind());
	}

	@Override
	protected OpenMFTransactionNoSql fromEntity(Entity entity) {
		return new OpenMFTransactionNoSql(entity);
	}

	@Override
	public Iterable<OpenMFTransaction> getTransactionsByClientId(String clientId) {
		Query query = new Query(getKind());
		query.setFilter(FilterOperator.EQUAL.of(OpenMFConstants.FIELD_NAME_CLIENTID, clientId));
		query.addSort(OpenMFConstants.FIELD_NAME_TIMESTAMP, SortDirection.DESCENDING);
		FetchOptions options = FetchOptions.Builder.withLimit(100);
		PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(query);
		ArrayList<OpenMFTransaction> entries = new ArrayList<OpenMFTransaction>();
		for (Entity result : pq.asIterable(options)) {
			OpenMFTransaction entry = new OpenMFTransactionNoSql(result);
			entries.add(entry);
		}
		return entries;
	}
}
