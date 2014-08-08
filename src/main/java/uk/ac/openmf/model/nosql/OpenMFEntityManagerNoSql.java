package uk.ac.openmf.model.nosql;

import static com.google.appengine.api.datastore.DatastoreServiceFactory.getDatastoreService;

import java.util.Iterator;
import java.util.logging.Logger;

import uk.ac.openmf.model.OpenMFClient;
import uk.ac.openmf.model.OpenMFEntity;
import uk.ac.openmf.model.OpenMFEntityManager;
import uk.ac.openmf.utils.OMFUtils;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

/**
 * Base class for entity managers for NoSQL implementation.
 *
 *
 * @param <T> type extends {@code Entity}
 */
public abstract class OpenMFEntityManagerNoSql<T extends OpenMFEntity> implements OpenMFEntityManager<T> {
	protected static final Logger logger =
			Logger.getLogger(OpenMFEntityManagerNoSql.class.getCanonicalName());
	private final Class<T> entityClass;

	protected OpenMFEntityManagerNoSql(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public Iterable<T> getEntities() {
		Query query = new Query(getKind());
		FetchOptions options = FetchOptions.Builder.withLimit(10000);
		return queryEntities(query, options);
	}

	@Override
	public int entityCount() {
		Query qry = new Query(getKind());
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
		return datastoreService.prepare(qry).countEntities(FetchOptions.Builder.withDefaults());
	}

	@Override
	public T deleteEntity(T demoEntity) {
		OMFUtils.assertTrue(demoEntity != null, "entity cannot be null");
		OpenMFEntityNoSql entityNoSql = downCastEntity(demoEntity);
		DatastoreService ds = getDatastoreService();
		Transaction txn = ds.beginTransaction();
		try {
			if (checkEntityForDelete(ds, entityNoSql)) {
				ds.delete(entityNoSql.getEntity().getKey());
				txn.commit();
				logger.info("entity deleted.");
				return demoEntity;
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

	@Override
	public T upsertEntity(T esEntity) {
		OMFUtils.assertTrue(esEntity != null, "demoEntity cannot be null");
		DatastoreService ds = getDatastoreService();
		MemcacheService memcache = MemcacheServiceFactory.getMemcacheService();
		OpenMFEntityNoSql entityNoSql = downCastEntity(esEntity);
		Entity entity = entityNoSql.getEntity();
		ds.put(entity);
		memcache.put(entity.getKey(), entity);
		return esEntity;
	}

	/**
	 * Gets the entity class.
	 *
	 * @return entity class.
	 */
	protected Class<T> getEntityClass() {
		return this.entityClass;
	}

	/**
	 * Gets the entity kind as a string.
	 *
	 * @return the entity kind string.
	 */
	protected String getKind() {
		return entityClass.getSimpleName();
	}

	/**
	 * Looks up a demo entity by key.
	 *
	 * @param key the entity key.
	 * @return the demo entity; null if the key could not be found.
	 */
	protected T getEntity(Key key) {
		DatastoreService ds = getDatastoreService();
		Entity entity = getDatastoreEntity(ds, key);
		if (entity != null) {
			return fromEntity(entity);
		}
		return null;
	}

	/**
	 * Looks up an entity by key.
	 *
	 * @param ds the datastore service objct.
	 * @param key the entity key.
	 * @return the entity; null if the key could not be found.
	 */
	protected Entity getDatastoreEntity(DatastoreService ds, Key key) {
		try {
			MemcacheService memcache = MemcacheServiceFactory.getMemcacheService();
			Entity entity =(Entity) memcache.get(key);
			if(entity != null){
				System.out.println("Cache Hit!\n");
				return entity;
			}else{
				return ds.get(key);
			}
		} catch (EntityNotFoundException e) {
			logger.fine("No entity found:" + key.toString());
		}
		return null;
	}
	/**
	 * Queries the datastore for an {@code Iterable} collection of entities.
	 *
	 * @param query datastore query object.
	 * @param options query options.
	 *
	 * @return an {@code Iterable} collection of datastore entities.
	 */
	protected Iterable<T> queryEntities(Query query, FetchOptions options) {
		PreparedQuery preparedQuery = getDatastoreService().prepare(query);
		final Iterable<Entity> iterable = preparedQuery.asIterable(options);
		Iterable<T> iterableWrapper = new Iterable<T>() {
			@Override
			public Iterator<T> iterator() {
				final Iterator<Entity> iterator = iterable.iterator();
				return new Iterator<T>() {
					@Override
					public void remove() {
						iterator.remove();
					}

					@Override
					public T next() {
						return fromEntity(iterator.next());
					}

					@Override
					public boolean hasNext() {
						return iterator.hasNext();
					}
				};
			}
		};
		return iterableWrapper;
	}

	/**
	 * Down casts the entity to a NoSQL base entity. The method makes sure the entity is created by
	 * NoSQL datastore module.
	 *
	 * @param demoEntity the model entity.
	 *
	 * @return the downcast NoSQL base entity.
	 */
	private OpenMFEntityNoSql downCastEntity(T demoEntity) {
		OMFUtils.assertTrue(
				demoEntity instanceof OpenMFEntityNoSql, "entity has to be a valid NoSQL entity");
		OpenMFEntityNoSql entityNoSql = (OpenMFEntityNoSql) demoEntity;
		return entityNoSql;
	}

	/**
	 * Callback before entity is deleted. Checks if the entity exists.
	 *
	 * @param ds the datastore service object.
	 * @param demoEntity the entity to be deleted.
	 *
	 * @return true if the entity should be deleted; otherwise, false.
	 */
	protected boolean checkEntityForDelete(DatastoreService ds, OpenMFEntityNoSql demoEntity) {
		if (demoEntity != null) {
			Entity entity = demoEntity.getEntity();
			if (entity != null) {
				return getDatastoreEntity(ds, entity.getKey()) != null;
			}
		}
		return false;
	}

	/**
	 * Creates a model entity based on parent key.
	 *
	 * @param parentKey the parent key.
	 *
	 * @return an model entity.
	 */
	protected abstract T fromParentKey(Key parentKey);

	/**
	 * Creates a model the entity based on datastore entity.
	 *
	 * @param entity datastore entity.
	 *
	 * @return an model entity.
	 */
	protected abstract T fromEntity(Entity entity);
}
