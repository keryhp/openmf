package uk.ac.openmf.model.nosql;

import java.util.ArrayList;

import uk.ac.openmf.model.OpenMFSavingsScheduledDeposit;
import uk.ac.openmf.model.OpenMFSavingsScheduledDepositManager;
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
public class OpenMFSavingsScheduledDepositManagerNoSql extends OpenMFEntityManagerNoSql<OpenMFSavingsScheduledDeposit> implements OpenMFSavingsScheduledDepositManager {

	private final OpenMFUserManagerNoSql userManager;

	public OpenMFSavingsScheduledDepositManagerNoSql(OpenMFUserManagerNoSql userManager) {
		super(OpenMFSavingsScheduledDeposit.class);
		this.userManager = userManager;
	}

	@Override
	public OpenMFSavingsScheduledDeposit getSavingsScheduledDepositScheduleItem(Long clientId) {
		return getEntity(createSavingsScheduledDepositKey(null, clientId));
	}

	@Override
	public Iterable<OpenMFSavingsScheduledDeposit> getAllSavingsScheduledDeposits() {
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
	public Key createSavingsScheduledDepositKey(String userId, Long clientId) {
		if (userId != null) {
			Key parentKey = KeyFactory.createKey(OpenMFConstants.ENTITY_USER_TYPE_GAE, userId);
			return KeyFactory.createKey(parentKey, getKind(), clientId);
		} else {
			return KeyFactory.createKey(getKind(), clientId);
		}
	}

	@Override
	public OpenMFSavingsScheduledDepositNoSql fromParentKey(Key parentKey) {
		return new OpenMFSavingsScheduledDepositNoSql(parentKey, getKind());
	}

	@Override
	protected OpenMFSavingsScheduledDepositNoSql fromEntity(Entity entity) {
		return new OpenMFSavingsScheduledDepositNoSql(entity);
	}

	@Override
	public OpenMFSavingsScheduledDeposit newSavingsScheduledDeposits(String userId) {
		return new OpenMFSavingsScheduledDepositNoSql(null, getKind());
	}

	@Override
	public Iterable<OpenMFSavingsScheduledDeposit> getSavingsScheduledDepositBySavingsAccount(String savingsaccountid) {
		Query qry = new Query(getKind());
		qry.setFilter(FilterOperator.EQUAL.of(OpenMFConstants.FIELD_NAME_SAVINGSACCOUNTID, savingsaccountid));
		qry.addSort(OpenMFConstants.FIELD_NAME_SERIALNUMBER, SortDirection.ASCENDING);
		FetchOptions options = FetchOptions.Builder.withLimit(100);
		PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(qry);
		ArrayList<OpenMFSavingsScheduledDeposit> schedules = new ArrayList<OpenMFSavingsScheduledDeposit>();
		for (Entity result : pq.asIterable(options)) {
			OpenMFSavingsScheduledDeposit savingsdepsch = new OpenMFSavingsScheduledDepositNoSql(result);
			schedules.add(savingsdepsch);
		}
		return schedules;
	}
}
