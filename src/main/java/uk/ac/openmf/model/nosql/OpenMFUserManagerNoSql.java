package uk.ac.openmf.model.nosql;

import uk.ac.openmf.model.OpenMFModelException;
import uk.ac.openmf.model.OpenMFUser;
import uk.ac.openmf.model.OpenMFUserManager;
import uk.ac.openmf.utils.OpenMFConstants;
import uk.ac.openmf.utils.OMFUtils;

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
public class OpenMFUserManagerNoSql extends OpenMFEntityManagerNoSql<OpenMFUser> implements OpenMFUserManager {

	public OpenMFUserManagerNoSql() {
		super(OpenMFUser.class);
	}

	public Key createOpenMFUserKey(String userId, Long omfuserid) {
		if (userId != null) {
		      Key parentKey = KeyFactory.createKey(OpenMFConstants.ENTITY_USER_TYPE_GAE, userId);
		      return KeyFactory.createKey(parentKey, getKind(), omfuserid);
		    } else {
		      return KeyFactory.createKey(getKind(), omfuserid);
		    }
	}

	public OpenMFUser getUserByEmail(String email) {
		Query qry = new Query(getKind());
		qry.setFilter(FilterOperator.EQUAL.of(OpenMFConstants.FIELD_NAME_EMAIL, email));
		PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(qry);
		OpenMFUser user = null; 
		//TODO add proper query
		for (Entity result : pq.asIterable()) {
			if(user == null){
				user = new OpenMFUserNoSql(result);
				user.setAddress((String)result.getProperty(OpenMFConstants.FIELD_NAME_ADDRESS));
				user.setContact((String)result.getProperty(OpenMFConstants.FIELD_NAME_CONTACT));
				user.setCreatedById((String)result.getProperty(OpenMFConstants.FIELD_NAME_CREATEDBY));
				user.setEmail(email);
				if(result.getProperty(OpenMFConstants.FIELD_NAME_ENABLED) != null)
					user.setEnabled((boolean)result.getProperty(OpenMFConstants.FIELD_NAME_ENABLED));
				user.setForename((String)result.getProperty(OpenMFConstants.FIELD_NAME_FORENAME));
				user.setMain_office((String)result.getProperty(OpenMFConstants.FIELD_NAME_MAIN_OFFICE));
				//user.setPassword(password);
				user.setRole((String)result.getProperty(OpenMFConstants.FIELD_NAME_ROLE));
				user.setSupervisor((String)result.getProperty(OpenMFConstants.FIELD_NAME_SUPERVISOR));
				user.setSurname((String)result.getProperty(OpenMFConstants.FIELD_NAME_SURNAME));
				if(result.getProperty(OpenMFConstants.FIELD_NAME_TIMESTAMP) != null)
					user.setTimestamp((long)result.getProperty(OpenMFConstants.FIELD_NAME_TIMESTAMP));
				user.setUsername((String)result.getProperty(OpenMFConstants.FIELD_NAME_USERNAME));
				break;
			}
		}
		return user;
	}
	
	public OpenMFUser getUserByUsername(String username) {
		Query qry = new Query(getKind());
		qry.setFilter(FilterOperator.EQUAL.of(OpenMFConstants.FIELD_NAME_USERNAME, username));
		PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(qry);
		OpenMFUser user = null; 
		for (Entity result : pq.asIterable()) {
			if(user == null){
				user = new OpenMFUserNoSql(result);
				user.setAddress((String)result.getProperty(OpenMFConstants.FIELD_NAME_ADDRESS));
				user.setContact((String)result.getProperty(OpenMFConstants.FIELD_NAME_CONTACT));
				user.setCreatedById((String)result.getProperty(OpenMFConstants.FIELD_NAME_CREATEDBY));
				user.setEmail((String)result.getProperty(OpenMFConstants.FIELD_NAME_EMAIL));
				if(result.getProperty(OpenMFConstants.FIELD_NAME_ENABLED) != null)
					user.setEnabled((boolean)result.getProperty(OpenMFConstants.FIELD_NAME_ENABLED));
				user.setForename((String)result.getProperty(OpenMFConstants.FIELD_NAME_FORENAME));
				user.setMain_office((String)result.getProperty(OpenMFConstants.FIELD_NAME_MAIN_OFFICE));
				user.setPassword((String)result.getProperty(OpenMFConstants.FIELD_NAME_PASSWORD));
				user.setRole((String)result.getProperty(OpenMFConstants.FIELD_NAME_ROLE));
				user.setSupervisor((String)result.getProperty(OpenMFConstants.FIELD_NAME_SUPERVISOR));
				user.setSurname((String)result.getProperty(OpenMFConstants.FIELD_NAME_SURNAME));
				if(result.getProperty(OpenMFConstants.FIELD_NAME_TIMESTAMP) != null)
					user.setTimestamp((long)result.getProperty(OpenMFConstants.FIELD_NAME_TIMESTAMP));
				user.setUsername((String)result.getProperty(OpenMFConstants.FIELD_NAME_USERNAME));
				break;
			}
		}
		return user;
	}

	@Override
	protected OpenMFUserNoSql fromParentKey(Key parentKey) {
		throw new OpenMFModelException("Demo User is entity group root, so it cannot have parent key");
	}

	@Override
	protected OpenMFUserNoSql fromEntity(Entity entity) {
		return new OpenMFUserNoSql(entity);
	}

	@Override
	public OpenMFUser getUser(Long userId) {
		OMFUtils.assertTrue(userId != null, "userId is null!");
		return getEntity(createOpenMFUserKey(null, userId));
	}

	@Override
	public OpenMFUser newUser(String userId) {
		return new OpenMFUserNoSql(null, getKind());
	}
	
	public OpenMFUser newUser() {
		return new OpenMFUserNoSql(null, getKind());
	}

	@Override
	public Iterable<OpenMFUser> getAllUsers() {
		Query query = new Query(getKind());
		query.addSort(OpenMFConstants.FIELD_NAME_TIMESTAMP, SortDirection.DESCENDING);
		FetchOptions options = FetchOptions.Builder.withLimit(100);
		return queryEntities(query, options);
	}
}
