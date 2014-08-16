package uk.ac.openmf.model.nosql;

import java.util.ArrayList;

import uk.ac.openmf.model.OpenMFLoanDisburse;
import uk.ac.openmf.model.OpenMFLoanDisburseManager;
import uk.ac.openmf.model.OpenMFSavingsDeposit;
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
public class OpenMFLoanDisburseManagerNoSql extends OpenMFEntityManagerNoSql<OpenMFLoanDisburse> implements OpenMFLoanDisburseManager {

	private final OpenMFUserManagerNoSql userManager;

	public OpenMFLoanDisburseManagerNoSql(OpenMFUserManagerNoSql userManager) {
		super(OpenMFLoanDisburse.class);
		this.userManager = userManager;
	}

	@Override
	public OpenMFLoanDisburse getLoanDisburse(Long loanDisburseid) {
		return getEntity(createLoanDisburseKey(null, loanDisburseid));
	}

	@Override
	public Iterable<OpenMFLoanDisburse> getAllLoanDisburses() {
		Query query = new Query(getKind());
		query.addSort(OpenMFConstants.FIELD_NAME_TIMESTAMP, SortDirection.DESCENDING);
		FetchOptions options = FetchOptions.Builder.withLimit(10000);
		return queryEntities(query, options);
	}

	/**
	 * Creates a role entity key.
	 *
	 * @param userId the user id. If null, no parent key is set.
	 * @param loanDisburseid
	 * @return a datastore key object.
	 */
	public Key createLoanDisburseKey(String userId, Long loanDisburseid) {
		if (userId != null) {
			Key parentKey = KeyFactory.createKey(OpenMFConstants.ENTITY_USER_TYPE_GAE, userId);
			return KeyFactory.createKey(parentKey, getKind(), loanDisburseid);
		} else {
			return KeyFactory.createKey(getKind(), loanDisburseid);
		}
	}

	@Override
	public OpenMFLoanDisburseNoSql fromParentKey(Key parentKey) {
		return new OpenMFLoanDisburseNoSql(parentKey, getKind());
	}

	@Override
	protected OpenMFLoanDisburseNoSql fromEntity(Entity entity) {
		return new OpenMFLoanDisburseNoSql(entity);
	}

	@Override
	public OpenMFLoanDisburse newLoanDisburse(String userId) {
		return new OpenMFLoanDisburseNoSql(null, getKind());
	}

	@Override
	public Iterable<OpenMFLoanDisburse> getLoanDisbursesByLoanAccount(String loanaccountid) {
		Query qry = new Query(getKind());
		qry.setFilter(FilterOperator.EQUAL.of(OpenMFConstants.FIELD_NAME_LOANACCOUNTID, loanaccountid));
		qry.addSort(OpenMFConstants.FIELD_NAME_TIMESTAMP, SortDirection.DESCENDING);
		PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(qry);
		ArrayList<OpenMFLoanDisburse> schedules = new ArrayList<OpenMFLoanDisburse>();
		for (Entity result : pq.asIterable()) {
			OpenMFLoanDisburse disburse = new OpenMFLoanDisburseNoSql(result);
			schedules.add(disburse);
		}
		return schedules;
	}

	@Override
	public String getTotalLoanDisburseAmtByDates(String fromdate, String todate) {
		Query qry = new Query(getKind());
		qry.setFilter(FilterOperator.GREATER_THAN_OR_EQUAL.of(OpenMFConstants.FIELD_NAME_DISBURSEDON, fromdate));
		qry.setFilter(FilterOperator.LESS_THAN_OR_EQUAL.of(OpenMFConstants.FIELD_NAME_DISBURSEDON, todate));
		FetchOptions options = FetchOptions.Builder.withLimit(10000);
		PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(qry);
		Double total = 0.0;
		for (Entity result : pq.asIterable(options)) {
			OpenMFLoanDisburse savingsdepsch = new OpenMFLoanDisburseNoSql(result);
			if(savingsdepsch.getDisbursedamount() == null || savingsdepsch.getDisbursedamount() == "")
				savingsdepsch.setDisbursedamount("0.00");
			total += new Double(savingsdepsch.getDisbursedamount());
		}
		return total.toString();
	}
}
