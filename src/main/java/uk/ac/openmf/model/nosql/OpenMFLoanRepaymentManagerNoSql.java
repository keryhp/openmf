package uk.ac.openmf.model.nosql;

import java.util.ArrayList;

import uk.ac.openmf.model.OpenMFLoanRepayment;
import uk.ac.openmf.model.OpenMFLoanRepaymentManager;
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
public class OpenMFLoanRepaymentManagerNoSql extends OpenMFEntityManagerNoSql<OpenMFLoanRepayment> implements OpenMFLoanRepaymentManager {

	private final OpenMFUserManagerNoSql userManager;

	public OpenMFLoanRepaymentManagerNoSql(OpenMFUserManagerNoSql userManager) {
		super(OpenMFLoanRepayment.class);
		this.userManager = userManager;
	}

	@Override
	public OpenMFLoanRepayment getLoanRepaymentScheduleItem(Long clientId) {
		return getEntity(createLoanRepaymentKey(null, clientId));
	}

	@Override
	public Iterable<OpenMFLoanRepayment> getAllLoanRepaymentSchedules() {
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
	public Key createLoanRepaymentKey(String userId, Long clientId) {
		if (userId != null) {
			Key parentKey = KeyFactory.createKey(OpenMFConstants.ENTITY_USER_TYPE_GAE, userId);
			return KeyFactory.createKey(parentKey, getKind(), clientId);
		} else {
			return KeyFactory.createKey(getKind(), clientId);
		}
	}

	@Override
	public OpenMFLoanRepaymentNoSql fromParentKey(Key parentKey) {
		return new OpenMFLoanRepaymentNoSql(parentKey, getKind());
	}

	@Override
	protected OpenMFLoanRepaymentNoSql fromEntity(Entity entity) {
		return new OpenMFLoanRepaymentNoSql(entity);
	}

	@Override
	public OpenMFLoanRepayment newLoanRepaymentSchedule(String userId) {
		return new OpenMFLoanRepaymentNoSql(null, getKind());
	}

	@Override
	public Iterable<OpenMFLoanRepayment> getLoanRepaymentSchedulesByLoanAccount(String loanaccountid) {
		Query qry = new Query(getKind());
		qry.setFilter(FilterOperator.EQUAL.of(OpenMFConstants.FIELD_NAME_LOANACCOUNTID, loanaccountid));
		qry.addSort(OpenMFConstants.FIELD_NAME_SERIALNUMBER, SortDirection.ASCENDING);
		FetchOptions options = FetchOptions.Builder.withLimit(100);
		PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(qry);
		ArrayList<OpenMFLoanRepayment> schedules = new ArrayList<OpenMFLoanRepayment>();
		for (Entity result : pq.asIterable(options)) {
			OpenMFLoanRepayment loanrepmntsch = new OpenMFLoanRepaymentNoSql(result);
			schedules.add(loanrepmntsch);
		}
		return schedules;
	}
}
