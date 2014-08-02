package uk.ac.openmf.model.nosql;

import java.util.ArrayList;

import uk.ac.openmf.model.OpenMFLoanActualPayment;
import uk.ac.openmf.model.OpenMFLoanActualPaymentManager;
import uk.ac.openmf.model.OpenMFLoanRepayment;
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
public class OpenMFLoanActualPaymentManagerNoSql extends OpenMFEntityManagerNoSql<OpenMFLoanActualPayment> implements OpenMFLoanActualPaymentManager {

	private final OpenMFUserManagerNoSql userManager;

	public OpenMFLoanActualPaymentManagerNoSql(OpenMFUserManagerNoSql userManager) {
		super(OpenMFLoanActualPayment.class);
		this.userManager = userManager;
	}

	@Override
	public OpenMFLoanActualPayment getLoanActualPayment(Long loanactualpaymentid) {
		return getEntity(createLoanActualPaymentKey(null, loanactualpaymentid));
	}

	@Override
	public Iterable<OpenMFLoanActualPayment> getAllLoanActualPayments() {
		Query query = new Query(getKind());
		query.addSort(OpenMFConstants.FIELD_NAME_TIMESTAMP, SortDirection.DESCENDING);
		FetchOptions options = FetchOptions.Builder.withLimit(100);
		return queryEntities(query, options);
	}

	/**
	 * Creates a role entity key.
	 *
	 * @param userId the user id. If null, no parent key is set.
	 * @param loanactualpaymentid
	 * @return a datastore key object.
	 */
	public Key createLoanActualPaymentKey(String userId, Long loanactualpaymentid) {
		if (userId != null) {
			Key parentKey = KeyFactory.createKey(OpenMFConstants.ENTITY_USER_TYPE_GAE, userId);
			return KeyFactory.createKey(parentKey, getKind(), loanactualpaymentid);
		} else {
			return KeyFactory.createKey(getKind(), loanactualpaymentid);
		}
	}

	@Override
	public OpenMFLoanActualPaymentNoSql fromParentKey(Key parentKey) {
		return new OpenMFLoanActualPaymentNoSql(parentKey, getKind());
	}

	@Override
	protected OpenMFLoanActualPaymentNoSql fromEntity(Entity entity) {
		return new OpenMFLoanActualPaymentNoSql(entity);
	}

	@Override
	public OpenMFLoanActualPayment newLoanActualPayment(String userId) {
		return new OpenMFLoanActualPaymentNoSql(null, getKind());
	}

	@Override
	public Iterable<OpenMFLoanActualPayment> getLoanActualPaymentsByLoanAccount(String loanaccountid) {
		Query qry = new Query(getKind());
		qry.setFilter(FilterOperator.EQUAL.of(OpenMFConstants.FIELD_NAME_LOANACCOUNTID, loanaccountid));
		qry.addSort(OpenMFConstants.FIELD_NAME_TIMESTAMP, SortDirection.DESCENDING);
		PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(qry);
		ArrayList<OpenMFLoanActualPayment> schedules = new ArrayList<OpenMFLoanActualPayment>();
		for (Entity result : pq.asIterable()) {
			OpenMFLoanActualPayment actualpayment = new OpenMFLoanActualPaymentNoSql(result);
			schedules.add(actualpayment);
		}
		return schedules;
	}
}
