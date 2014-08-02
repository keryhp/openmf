package uk.ac.openmf.model.nosql;

import java.util.ArrayList;

import uk.ac.openmf.model.OpenMFLoanAccount;
import uk.ac.openmf.model.OpenMFSavingsAccount;
import uk.ac.openmf.model.OpenMFSavingsAccountManager;
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
public class OpenMFSavingsAccountManagerNoSql extends OpenMFEntityManagerNoSql<OpenMFSavingsAccount> implements OpenMFSavingsAccountManager {

	private final OpenMFUserManagerNoSql userManager;

	public OpenMFSavingsAccountManagerNoSql(OpenMFUserManagerNoSql userManager) {
		super(OpenMFSavingsAccount.class);
		this.userManager = userManager;
	}

	@Override
	public OpenMFSavingsAccount getSavingsAccount(Long savingsAccountId) {
		return getEntity(createSavingsAccountKey(null, savingsAccountId));
	}

	@Override
	public Iterable<OpenMFSavingsAccount> getAllSavingsAccounts() {
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
	public Key createSavingsAccountKey(String userId, Long savingsaccountid) {
		if (userId != null) {
			Key parentKey = KeyFactory.createKey(OpenMFConstants.ENTITY_USER_TYPE_GAE, userId);
			return KeyFactory.createKey(parentKey, getKind(), savingsaccountid);
		} else {
			return KeyFactory.createKey(getKind(), savingsaccountid);
		}
	}

	@Override
	public OpenMFSavingsAccountNoSql fromParentKey(Key parentKey) {
		return new OpenMFSavingsAccountNoSql(parentKey, getKind());
	}

	@Override
	public OpenMFSavingsAccountNoSql newSavingsAccount(String userId) {
		return new OpenMFSavingsAccountNoSql(null, getKind());
	}

	@Override
	protected OpenMFSavingsAccountNoSql fromEntity(Entity entity) {
		return new OpenMFSavingsAccountNoSql(entity);
	}

	@Override
	public Iterable<OpenMFSavingsAccount> getAllSavingsAccountsByClient(String clientId) {
		Query qry = new Query(getKind());
		qry.setFilter(FilterOperator.EQUAL.of(OpenMFConstants.FIELD_NAME_CLIENTID, clientId));
		qry.addSort(OpenMFConstants.FIELD_NAME_TIMESTAMP, SortDirection.DESCENDING);
		FetchOptions options = FetchOptions.Builder.withLimit(100);
		return queryEntities(qry, options);
	}

	@Override
	public Iterable<OpenMFSavingsAccount> getAllSavingsAccountByProduct(String savingscode) {
		Query qry = new Query(getKind());
		qry.setFilter(FilterOperator.EQUAL.of(OpenMFConstants.FIELD_NAME_SAVINGSCODE, savingscode));
		qry.addSort(OpenMFConstants.FIELD_NAME_TIMESTAMP, SortDirection.DESCENDING);
		FetchOptions options = FetchOptions.Builder.withLimit(100);
		//return queryEntities(qry, options);
		PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(qry);
		ArrayList<OpenMFSavingsAccount> savingsAccounts = new ArrayList<OpenMFSavingsAccount>();
		for (Entity result : pq.asList(options)) {
			savingsAccounts.add(new OpenMFSavingsAccountNoSql(result));
		}
		return savingsAccounts;
	}
}
