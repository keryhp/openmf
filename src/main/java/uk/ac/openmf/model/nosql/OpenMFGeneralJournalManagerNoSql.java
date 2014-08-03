package uk.ac.openmf.model.nosql;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;

import uk.ac.openmf.model.OpenMFGeneralJournal;
import uk.ac.openmf.model.OpenMFGeneralJournalManager;
import uk.ac.openmf.model.OpenMFGeneralLedger;
import uk.ac.openmf.model.OpenMFUser;
import uk.ac.openmf.utils.OMFDateUtils;
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
public class OpenMFGeneralJournalManagerNoSql extends OpenMFEntityManagerNoSql<OpenMFGeneralJournal>
implements OpenMFGeneralJournalManager {

	private final OpenMFUserManagerNoSql userManager;

	public OpenMFGeneralJournalManagerNoSql(OpenMFUserManagerNoSql userManager) {
		super(OpenMFGeneralJournal.class);
		this.userManager = userManager;
	}

	@Override
	public OpenMFGeneralJournal getGeneralJournal(Long chartofaccountsId) {
		return getEntity(createRoleKey(null, chartofaccountsId));
	}

	@Override
	public Iterable<OpenMFGeneralJournal> getAllGeneralJournal() {
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
	public OpenMFGeneralJournalNoSql fromParentKey(Key parentKey) {
		return new OpenMFGeneralJournalNoSql(parentKey, getKind());
	}

	@Override
	public OpenMFGeneralJournalNoSql newGeneralJournal(String userId) {
		return new OpenMFGeneralJournalNoSql(null, getKind());
	}

	@Override
	protected OpenMFGeneralJournalNoSql fromEntity(Entity entity) {
		return new OpenMFGeneralJournalNoSql(entity);
	}

	@Override
	public Iterable<OpenMFGeneralJournal> getGeneralJournalByDate(
			String mfiaccounttype, String date) {
		Query query = new Query(getKind());
		query.setFilter(FilterOperator.EQUAL.of(OpenMFConstants.FIELD_NAME_MFIACCOUNTTYPE, mfiaccounttype));
		query.setFilter(FilterOperator.GREATER_THAN_OR_EQUAL.of(OpenMFConstants.FIELD_NAME_TIMESTAMP, new Date(date).getTime()));
		query.addSort(OpenMFConstants.FIELD_NAME_TIMESTAMP, SortDirection.DESCENDING);
		FetchOptions options = FetchOptions.Builder.withLimit(100);
		PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(query);
		ArrayList<OpenMFGeneralJournal> entries = new ArrayList<OpenMFGeneralJournal>();
		for (Entity result : pq.asIterable(options)) {
			OpenMFGeneralJournal generalJournalEntry = new OpenMFGeneralJournalNoSql(result);
			entries.add(generalJournalEntry);
		}
		return entries;
	}

	@Override
	public OpenMFGeneralJournal getGeneralJournalByCoAandDate(String coaid,
			String date) {
		Query query = new Query(getKind());
		query.setFilter(FilterOperator.EQUAL.of(OpenMFConstants.FIELD_NAME_COAID, coaid));
		query.setFilter(FilterOperator.GREATER_THAN_OR_EQUAL.of(OpenMFConstants.FIELD_NAME_TIMESTAMP, new Date(date).getTime()));
		query.addSort(OpenMFConstants.FIELD_NAME_TIMESTAMP, SortDirection.DESCENDING);
		FetchOptions options = FetchOptions.Builder.withLimit(100);
		PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(query);
		OpenMFGeneralJournal generalJournalEntry = null;
		for (Entity result : pq.asIterable(options)) {
			if(generalJournalEntry == null){
				generalJournalEntry = new OpenMFGeneralJournalNoSql(result);
				break;
			}
		}
		return generalJournalEntry;
	}
}
