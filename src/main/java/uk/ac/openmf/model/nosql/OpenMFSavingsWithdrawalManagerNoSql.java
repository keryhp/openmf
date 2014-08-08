package uk.ac.openmf.model.nosql;

import java.util.ArrayList;

import uk.ac.openmf.model.OpenMFSavingsWithdrawal;
import uk.ac.openmf.model.OpenMFSavingsWithdrawalManager;
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
public class OpenMFSavingsWithdrawalManagerNoSql extends OpenMFEntityManagerNoSql<OpenMFSavingsWithdrawal> implements OpenMFSavingsWithdrawalManager {

	private final OpenMFUserManagerNoSql userManager;

	public OpenMFSavingsWithdrawalManagerNoSql(OpenMFUserManagerNoSql userManager) {
		super(OpenMFSavingsWithdrawal.class);
		this.userManager = userManager;
	}

	@Override
	public OpenMFSavingsWithdrawal getSavingsWithdrawal(Long savingsactualpaymentid) {
		return getEntity(createSavingsWithdrawalKey(null, savingsactualpaymentid));
	}

	@Override
	public Iterable<OpenMFSavingsWithdrawal> getAllSavingsWithdrawals() {
		Query query = new Query(getKind());
		query.addSort(OpenMFConstants.FIELD_NAME_TIMESTAMP, SortDirection.DESCENDING);
		FetchOptions options = FetchOptions.Builder.withLimit(100000);
		return queryEntities(query, options);
	}

	/**
	 * Creates a role entity key.
	 *
	 * @param userId the user id. If null, no parent key is set.
	 * @param savingsactualpaymentid
	 * @return a datastore key object.
	 */
	public Key createSavingsWithdrawalKey(String userId, Long savingsactualpaymentid) {
		if (userId != null) {
			Key parentKey = KeyFactory.createKey(OpenMFConstants.ENTITY_USER_TYPE_GAE, userId);
			return KeyFactory.createKey(parentKey, getKind(), savingsactualpaymentid);
		} else {
			return KeyFactory.createKey(getKind(), savingsactualpaymentid);
		}
	}

	@Override
	public OpenMFSavingsWithdrawalNoSql fromParentKey(Key parentKey) {
		return new OpenMFSavingsWithdrawalNoSql(parentKey, getKind());
	}

	@Override
	protected OpenMFSavingsWithdrawalNoSql fromEntity(Entity entity) {
		return new OpenMFSavingsWithdrawalNoSql(entity);
	}

	@Override
	public OpenMFSavingsWithdrawal newSavingsWithdrawal(String userId) {
		return new OpenMFSavingsWithdrawalNoSql(null, getKind());
	}

	@Override
	public Iterable<OpenMFSavingsWithdrawal> getSavingsWithdrawalsBySavingsAccount(String savingsaccountid) {
		Query qry = new Query(getKind());
		qry.setFilter(FilterOperator.EQUAL.of(OpenMFConstants.FIELD_NAME_SAVINGSACCOUNTID, savingsaccountid));
		qry.addSort(OpenMFConstants.FIELD_NAME_TIMESTAMP, SortDirection.DESCENDING);
		PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(qry);
		ArrayList<OpenMFSavingsWithdrawal> schedules = new ArrayList<OpenMFSavingsWithdrawal>();
		for (Entity result : pq.asIterable()) {
			OpenMFSavingsWithdrawal actualpayment = new OpenMFSavingsWithdrawalNoSql(result);
			schedules.add(actualpayment);
		}
		return schedules;
	}
}
