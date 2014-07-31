package uk.ac.openmf.model.nosql;

import static com.google.appengine.api.datastore.DatastoreServiceFactory.getDatastoreService;

import java.util.logging.Logger;

import uk.ac.openmf.model.OpenMFPhoto;
import uk.ac.openmf.model.OpenMFPhotoManager;
import uk.ac.openmf.utils.OMFUtils;
import uk.ac.openmf.utils.OpenMFConstants;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.Transaction;

/**
 * The photo entity manager class for NoSQL.
 *
 */
public class OpenMFPhotoManagerNoSql extends OpenMFEntityManagerNoSql<OpenMFPhoto> implements OpenMFPhotoManager {
  private static final Logger logger = Logger.getLogger(OpenMFPhotoManagerNoSql.class.getCanonicalName());
  private OpenMFUserManagerNoSql userManager;

  public OpenMFPhotoManagerNoSql(OpenMFUserManagerNoSql userManager) {
    super(OpenMFPhoto.class);
    this.userManager = userManager;
  }

  @Override
  public OpenMFPhoto getPhoto(Long id, long typeId) {
    Key key = createPhotoKey(id, typeId);
    return getEntity(key);
  }
  
  @Override
  public Iterable<OpenMFPhoto> getPhoto(Long typeId) {
	    Query query = new Query(getKind()).setAncestor(userManager.createOpenMFUserKey(null, typeId));
	    Query.Filter filter = new Query.FilterPredicate(OpenMFConstants.FIELD_NAME_ACTIVE,
	        FilterOperator.EQUAL, true);
	    query.addSort(OpenMFConstants.FIELD_NAME_TIMESTAMP, SortDirection.DESCENDING);
	    query.setFilter(filter);
	    FetchOptions options = FetchOptions.Builder.withLimit(100);
	    return queryEntities(query, options);    
  }

  public Key createPhotoKey(Long id, Long typeId) {
    OMFUtils.assertTrue(typeId != null, "id cannot be null");
    if (id != null) {
      Key parentKey = userManager.createOpenMFUserKey(null, id);
      return KeyFactory.createKey(parentKey, getKind(), typeId);
    } else {
      return KeyFactory.createKey(getKind(), typeId);
    }
  }

  @Override
  public Iterable<OpenMFPhoto> getActivePhotos() {
    Query query = new Query(getKind());
    Query.Filter filter = new Query.FilterPredicate(OpenMFConstants.FIELD_NAME_ACTIVE,
        FilterOperator.EQUAL, true);
    query.addSort(OpenMFConstants.FIELD_NAME_TIMESTAMP, SortDirection.DESCENDING);
    query.setFilter(filter);
    FetchOptions options = FetchOptions.Builder.withLimit(100);
    return queryEntities(query, options);    
  }

  @Override
  public Iterable<OpenMFPhoto> getAllPhotos(Long typeId) {
    Query query = new Query(getKind());
    query.setAncestor(userManager.createOpenMFUserKey(null, typeId));
    Query.Filter filter = new Query.FilterPredicate(OpenMFConstants.FIELD_NAME_ACTIVE,
        FilterOperator.EQUAL, true);
    query.setFilter(filter);
    FetchOptions options = FetchOptions.Builder.withLimit(100);
    return queryEntities(query, options);
  }

  @Override
  public Iterable<OpenMFPhoto> getDeactivedPhotos() {
    Query query = new Query(getKind());
    Query.Filter filter = new Query.FilterPredicate(OpenMFConstants.FIELD_NAME_ACTIVE,
        FilterOperator.EQUAL, false);
    query.setFilter(filter);
    FetchOptions options = FetchOptions.Builder.withLimit(100);
    return queryEntities(query, options);
  }

  @Override
  public OpenMFPhotoNoSql fromParentKey(Key parentKey) {
    return new OpenMFPhotoNoSql(parentKey, getKind());
  }

  @Override
  public OpenMFPhotoNoSql newPhoto(Long eventId) {
    return new OpenMFPhotoNoSql(userManager.createOpenMFUserKey(null, eventId), getKind());
  }

  @Override
  protected OpenMFPhotoNoSql fromEntity(Entity entity) {
    return new OpenMFPhotoNoSql(entity);
  }

  @Override
  public OpenMFPhoto deactivePhoto(Long eventId, long id) {
    OMFUtils.assertTrue(eventId != null, "id cannot be null");
    DatastoreService ds = getDatastoreService();
    Transaction txn = ds.beginTransaction();
    try {
      Entity entity = getDatastoreEntity(ds, createPhotoKey(eventId, id));
      if (entity != null) {
        OpenMFPhotoNoSql photo = new OpenMFPhotoNoSql(entity);
        if (photo.isActive()) {
          photo.setActive(false);
          ds.put(entity);
        }
        txn.commit();

        return photo;
      }
    } catch (Exception e) {
      logger.severe("Failed to delete entity from datastore:" + e.getMessage());
    } finally {
      if (txn.isActive()) {
        txn.rollback();
      }
    }
    return null;
  }
}
