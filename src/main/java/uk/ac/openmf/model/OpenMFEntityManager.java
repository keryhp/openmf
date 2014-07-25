package uk.ac.openmf.model;

/**
 * Base class for entity managers. Each entity manager contains
 * query and update operations for one type of entity.
 *
 *
 * @param <T> type extends {@code Entity}
 */
public interface OpenMFEntityManager<T extends OpenMFEntity> {
  public static final int INDEX_NOT_SPECIFIED = -1;

  /**
   * Retrieves all the entities.
   *
   * @return an {@code Iterable} collection of all entities.
   */
  Iterable<T> getEntities();

  /**
   * Deletes an entity.
   *
   * @param entity the entity to be deleted.
   *
   * @return the deleted entity; return null if the entity does not exist.
   */
  T deleteEntity(T entity);

  /**
   * Updates or insert an entity.
   *
   * @param demoEntity the entity object.
   *
   * @return the entity object after upserted.
   */
  T upsertEntity(T demoEntity);
  
  int entityCount();

}
