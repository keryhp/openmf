package uk.ac.openmf.model.nosql;

import java.util.ArrayList;
import java.util.Date;

import uk.ac.openmf.model.OpenMFGeneralLedger;
import uk.ac.openmf.model.OpenMFGeneralLedgerManager;
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
public class OpenMFGeneralLedgerManagerNoSql extends OpenMFEntityManagerNoSql<OpenMFGeneralLedger>
implements OpenMFGeneralLedgerManager {

	private final OpenMFUserManagerNoSql userManager;

	public OpenMFGeneralLedgerManagerNoSql(OpenMFUserManagerNoSql userManager) {
		super(OpenMFGeneralLedger.class);
		this.userManager = userManager;
	}

	@Override
	public OpenMFGeneralLedger getGeneralLedger(Long generalledgerId) {
		return getEntity(createRoleKey(null, generalledgerId));
	}

	@Override
	public Iterable<OpenMFGeneralLedger> getAllGeneralLedger() {
		Query query = new Query(getKind());
		query.addSort(OpenMFConstants.FIELD_NAME_TIMESTAMP, SortDirection.DESCENDING);
		FetchOptions options = FetchOptions.Builder.withLimit(100);
		return queryEntities(query, options);
	}

	/**
	 * Creates a role entity key.
	 *
	 * @param userId the user id. If null, no parent key is set.
	 * @param generalledgerId
	 * @return a datastore key object.
	 */
	public Key createRoleKey(Long userId, Long generalledgerId) {
		if (userId != null) {
			Key parentKey = KeyFactory.createKey(OpenMFConstants.ENTITY_USER_TYPE_GAE, userId);
			return KeyFactory.createKey(parentKey, getKind(), generalledgerId);
		} else {
			return KeyFactory.createKey(getKind(), generalledgerId);
		}
	}

	@Override
	public OpenMFGeneralLedgerNoSql fromParentKey(Key parentKey) {
		return new OpenMFGeneralLedgerNoSql(parentKey, getKind());
	}

	@Override
	public OpenMFGeneralLedgerNoSql newGeneralLedger(String userId) {
		return new OpenMFGeneralLedgerNoSql(null, getKind());
	}

	@Override
	protected OpenMFGeneralLedgerNoSql fromEntity(Entity entity) {
		return new OpenMFGeneralLedgerNoSql(entity);
	}

	@Override
	public Iterable<OpenMFGeneralLedger> getGeneralLedgerByDate(String mfiaccounttype, String date) {
		Query query = new Query(getKind());
		query.setFilter(FilterOperator.EQUAL.of(OpenMFConstants.FIELD_NAME_MFIACCOUNTTYPE, mfiaccounttype));
		query.setFilter(FilterOperator.GREATER_THAN_OR_EQUAL.of(OpenMFConstants.FIELD_NAME_TIMESTAMP, new Date(date).getTime()));
		query.addSort(OpenMFConstants.FIELD_NAME_TIMESTAMP, SortDirection.DESCENDING);
		FetchOptions options = FetchOptions.Builder.withLimit(100);
		PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(query);
		ArrayList<OpenMFGeneralLedger> entries = new ArrayList<OpenMFGeneralLedger>();
		for (Entity result : pq.asIterable(options)) {
			OpenMFGeneralLedger generalLedgerEntry = new OpenMFGeneralLedgerNoSql(result);
			entries.add(generalLedgerEntry);
		}
		return entries;
	}

	@Override
	public OpenMFGeneralLedger getGeneralLedgerByCoAandDate(String coaid,
			String date) {
		Query query = new Query(getKind());
		query.setFilter(FilterOperator.EQUAL.of(OpenMFConstants.FIELD_NAME_COAID, coaid));
		query.setFilter(FilterOperator.GREATER_THAN_OR_EQUAL.of(OpenMFConstants.FIELD_NAME_TIMESTAMP, new Date(date).getTime()));
		query.addSort(OpenMFConstants.FIELD_NAME_TIMESTAMP, SortDirection.DESCENDING);
		FetchOptions options = FetchOptions.Builder.withLimit(100);
		PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(query);
		OpenMFGeneralLedger generalLedgerEntry = null;
		for (Entity result : pq.asIterable(options)) {
			if(generalLedgerEntry == null){
				generalLedgerEntry = new OpenMFGeneralLedgerNoSql(result);
				break;
			}
		}
		return generalLedgerEntry;
	}
}
