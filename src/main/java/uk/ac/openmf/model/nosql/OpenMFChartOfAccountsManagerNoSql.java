package uk.ac.openmf.model.nosql;

import java.util.ArrayList;

import uk.ac.openmf.model.OpenMFChartOfAccounts;
import uk.ac.openmf.model.OpenMFChartOfAccountsManager;
import uk.ac.openmf.model.OpenMFLoanActualPayment;
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
public class OpenMFChartOfAccountsManagerNoSql extends OpenMFEntityManagerNoSql<OpenMFChartOfAccounts>
implements OpenMFChartOfAccountsManager {

	private final OpenMFUserManagerNoSql userManager;

	public OpenMFChartOfAccountsManagerNoSql(OpenMFUserManagerNoSql userManager) {
		super(OpenMFChartOfAccounts.class);
		this.userManager = userManager;
	}

	@Override
	public OpenMFChartOfAccounts getChartOfAccounts(Long chartofaccountsId) {
		return getEntity(createRoleKey(null, chartofaccountsId));
	}

	@Override
	public Iterable<OpenMFChartOfAccounts> getAllChartOfAccounts() {
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
	public OpenMFChartOfAccountsNoSql fromParentKey(Key parentKey) {
		return new OpenMFChartOfAccountsNoSql(parentKey, getKind());
	}

	@Override
	public OpenMFChartOfAccountsNoSql newChartOfAccounts(String userId) {
		return new OpenMFChartOfAccountsNoSql(null, getKind());
	}

	@Override
	protected OpenMFChartOfAccountsNoSql fromEntity(Entity entity) {
		return new OpenMFChartOfAccountsNoSql(entity);
	}

	@Override
	public OpenMFChartOfAccounts getChartOfAccountsByMFIAccountType(
			String mfiaccounttype) {
		Query qry = new Query(getKind());
		qry.setFilter(FilterOperator.EQUAL.of(OpenMFConstants.FIELD_NAME_MFIACCOUNTTYPE, mfiaccounttype));
		qry.addSort(OpenMFConstants.FIELD_NAME_TIMESTAMP, SortDirection.DESCENDING);
		PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(qry);
		OpenMFChartOfAccounts coa = null;
		for (Entity result : pq.asIterable()) {
			if(coa ==null){
				coa = new OpenMFChartOfAccountsNoSql(result);
			}
		}
		return coa;
	}
}
