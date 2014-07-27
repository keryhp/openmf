package uk.ac.openmf.model.nosql;

import uk.ac.openmf.model.OpenMFLoanAccount;
import uk.ac.openmf.model.OpenMFLoanAccountManager;
import uk.ac.openmf.utils.OpenMFConstants;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.SortDirection;

/**
 * User manager class for NoSQL.
 *
 */
public class OpenMFLoanAccountManagerNoSql extends OpenMFEntityManagerNoSql<OpenMFLoanAccount> implements OpenMFLoanAccountManager {

	private final OpenMFUserManagerNoSql userManager;

	public OpenMFLoanAccountManagerNoSql(OpenMFUserManagerNoSql userManager) {
		super(OpenMFLoanAccount.class);
		this.userManager = userManager;
	}

	@Override
	public OpenMFLoanAccount getLoanAccount(Long loanAccountId) {
		return getEntity(createLoanAccountKey(null, loanAccountId));
	}

	@Override
	public Iterable<OpenMFLoanAccount> getAllLoanAccounts() {
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
	public Key createLoanAccountKey(String userId, Long loanaccountid) {
		if (userId != null) {
			Key parentKey = KeyFactory.createKey(OpenMFConstants.ENTITY_USER_TYPE_GAE, userId);
			return KeyFactory.createKey(parentKey, getKind(), loanaccountid);
		} else {
			return KeyFactory.createKey(getKind(), loanaccountid);
		}
	}

	@Override
	public OpenMFLoanAccountNoSql fromParentKey(Key parentKey) {
		return new OpenMFLoanAccountNoSql(parentKey, getKind());
	}

	@Override
	public OpenMFLoanAccountNoSql newLoanAccount(String userId) {
		return new OpenMFLoanAccountNoSql(null, getKind());
	}

	@Override
	protected OpenMFLoanAccountNoSql fromEntity(Entity entity) {
		return new OpenMFLoanAccountNoSql(entity);
	}

	@Override
	public Iterable<OpenMFLoanAccount> getAllLoanAccountsByClient(String clientId) {
		Query qry = new Query(getKind());
		qry.setFilter(FilterOperator.EQUAL.of(OpenMFConstants.FIELD_NAME_CLIENTID, clientId));
		qry.addSort(OpenMFConstants.FIELD_NAME_TIMESTAMP, SortDirection.DESCENDING);
		FetchOptions options = FetchOptions.Builder.withLimit(100);
		return queryEntities(qry, options);
	}

	@Override
	public Iterable<OpenMFLoanAccount> getAllLoanAccountByProduct(String loancode) {
		Query qry = new Query(getKind());
		qry.setFilter(FilterOperator.EQUAL.of(OpenMFConstants.FIELD_NAME_LOANCODE, loancode));
		qry.addSort(OpenMFConstants.FIELD_NAME_TIMESTAMP, SortDirection.DESCENDING);
		FetchOptions options = FetchOptions.Builder.withLimit(100);
		return queryEntities(qry, options);
	}
}
