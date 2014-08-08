package uk.ac.openmf.model.nosql;

import java.util.ArrayList;

import uk.ac.openmf.model.OpenMFSavingsDeposit;
import uk.ac.openmf.model.OpenMFSavingsDepositManager;
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
 * User manager class for NoSQL.
 *
 */
public class OpenMFSavingsDepositManagerNoSql extends OpenMFEntityManagerNoSql<OpenMFSavingsDeposit> implements OpenMFSavingsDepositManager {

	private final OpenMFUserManagerNoSql userManager;

	public OpenMFSavingsDepositManagerNoSql(OpenMFUserManagerNoSql userManager) {
		super(OpenMFSavingsDeposit.class);
		this.userManager = userManager;
	}

	@Override
	public OpenMFSavingsDeposit getSavingsDeposit(Long savingsactualpaymentid) {
		return getEntity(createSavingsDepositKey(null, savingsactualpaymentid));
	}

	@Override
	public Iterable<OpenMFSavingsDeposit> getAllSavingsDeposits() {
		Query query = new Query(getKind());
		query.addSort(OpenMFConstants.FIELD_NAME_TIMESTAMP, SortDirection.DESCENDING);
		FetchOptions options = FetchOptions.Builder.withLimit(10000);
		return queryEntities(query, options);
	}

	/**
	 * Creates a role entity key.
	 *
	 * @param userId the user id. If null, no parent key is set.
	 * @param savingsactualpaymentid
	 * @return a datastore key object.
	 */
	public Key createSavingsDepositKey(String userId, Long savingsactualpaymentid) {
		if (userId != null) {
			Key parentKey = KeyFactory.createKey(OpenMFConstants.ENTITY_USER_TYPE_GAE, userId);
			return KeyFactory.createKey(parentKey, getKind(), savingsactualpaymentid);
		} else {
			return KeyFactory.createKey(getKind(), savingsactualpaymentid);
		}
	}

	@Override
	public OpenMFSavingsDepositNoSql fromParentKey(Key parentKey) {
		return new OpenMFSavingsDepositNoSql(parentKey, getKind());
	}

	@Override
	protected OpenMFSavingsDepositNoSql fromEntity(Entity entity) {
		return new OpenMFSavingsDepositNoSql(entity);
	}

	@Override
	public OpenMFSavingsDeposit newSavingsDeposit(String userId) {
		return new OpenMFSavingsDepositNoSql(null, getKind());
	}

	@Override
	public Iterable<OpenMFSavingsDeposit> getSavingsDepositsBySavingsAccount(String savingsaccountid) {
		Query qry = new Query(getKind());
		qry.setFilter(FilterOperator.EQUAL.of(OpenMFConstants.FIELD_NAME_SAVINGSACCOUNTID, savingsaccountid));
		qry.addSort(OpenMFConstants.FIELD_NAME_TIMESTAMP, SortDirection.DESCENDING);
		PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(qry);
		ArrayList<OpenMFSavingsDeposit> deposits = new ArrayList<OpenMFSavingsDeposit>();
		for (Entity result : pq.asIterable()) {
			OpenMFSavingsDeposit deposit = new OpenMFSavingsDepositNoSql(result);
			deposits.add(deposit);
		}
		return deposits;
	}
}
