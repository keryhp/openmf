package uk.ac.openmf.model.nosql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uk.ac.openmf.model.OpenMFTask;
import uk.ac.openmf.model.OpenMFTasksManager;
import uk.ac.openmf.utils.OpenMFConstants;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.SortDirection;


/**
 * Entity manager class to support role datastore operations.
 *
 */
public class OpenMFTasksManagerNoSql extends OpenMFEntityManagerNoSql<OpenMFTask>
implements OpenMFTasksManager {

	private final OpenMFUserManagerNoSql userManager;

	public OpenMFTasksManagerNoSql(OpenMFUserManagerNoSql userManager) {
		super(OpenMFTask.class);
		this.userManager = userManager;
	}

	@Override
	public OpenMFTask getTask(Long taskId) {
		return getEntity(createTaskKey(null, taskId));
	}

	@Override
	public Iterable<OpenMFTask> getAllTasks() {
		Query query = new Query(getKind());
		query.addSort(OpenMFConstants.FIELD_NAME_TIMESTAMP, SortDirection.DESCENDING);
		FetchOptions options = FetchOptions.Builder.withLimit(100000);
		return queryEntities(query, options);
	}

	/**
	 * Creates a role entity key.
	 *
	 * @param userId the user id. If null, no parent key is set.
	 * @param roleId
	 * @return a datastore key object.
	 */
	public Key createTaskKey(Long userId, Long roleId) {
		if (userId != null) {
			Key parentKey = KeyFactory.createKey(OpenMFConstants.ENTITY_USER_TYPE_GAE, userId);
			return KeyFactory.createKey(parentKey, getKind(), roleId);
		} else {
			return KeyFactory.createKey(getKind(), roleId);
		}
	}

	@Override
	public OpenMFTasksNoSql fromParentKey(Key parentKey) {
		return new OpenMFTasksNoSql(parentKey, getKind());
	}

	@Override
	public OpenMFTasksNoSql newTask(String userId) {
		return new OpenMFTasksNoSql(null, getKind());
	}

	@Override
	protected OpenMFTasksNoSql fromEntity(Entity entity) {
		return new OpenMFTasksNoSql(entity);
	}

	@Override
	public Iterable<OpenMFTask> getTasksByUsername(String username,
			boolean status) {
		Query qry = new Query(getKind());
//		qry.setFilter(FilterOperator.EQUAL.of(OpenMFConstants.FIELD_NAME_ASSIGNEDTO, username));
//		qry.setFilter(FilterOperator.EQUAL.of(OpenMFConstants.FIELD_NAME_STATUS, status));
		Query.Filter f1 = new Query.FilterPredicate(OpenMFConstants.FIELD_NAME_ASSIGNEDTO, FilterOperator.EQUAL, username);
		Query.Filter f2 = new Query.FilterPredicate(OpenMFConstants.FIELD_NAME_STATUS, FilterOperator.GREATER_THAN_OR_EQUAL, status);
		List<Filter> filters = Arrays.asList(f1, f2);
		Filter filter = new Query.CompositeFilter(CompositeFilterOperator.AND, filters);
		qry.setFilter(filter);		
		//qry.addSort(OpenMFConstants.FIELD_NAME_TIMESTAMP, SortDirection.DESCENDING);
		FetchOptions options = FetchOptions.Builder.withLimit(10000);
		PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(qry);
		ArrayList<OpenMFTask> tasks = new ArrayList<OpenMFTask>();
		for (Entity result : pq.asList(options)) {
			tasks.add(new OpenMFTasksNoSql(result));
		}
		return tasks;
	}

	@Override
	public Iterable<OpenMFTask> getAllTasksByUsername(String username) {
		Query qry = new Query(getKind());
		qry.setFilter(FilterOperator.EQUAL.of(OpenMFConstants.FIELD_NAME_ASSIGNEDTO, username));
		qry.addSort(OpenMFConstants.FIELD_NAME_TIMESTAMP, SortDirection.DESCENDING);
		FetchOptions options = FetchOptions.Builder.withLimit(10000);
		PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(qry);
		ArrayList<OpenMFTask> tasks = new ArrayList<OpenMFTask>();
		for (Entity result : pq.asList(options)) {
			tasks.add(new OpenMFTasksNoSql(result));
		}
		return tasks;
	}
}
