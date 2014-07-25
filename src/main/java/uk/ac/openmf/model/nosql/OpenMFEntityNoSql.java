package uk.ac.openmf.model.nosql;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

/**
 * Base entity class for NoSQL.
 *
 */
public abstract class OpenMFEntityNoSql {
  protected final Entity entity;

  protected OpenMFEntityNoSql() {
	  this.entity = null;
	  }
  protected OpenMFEntityNoSql(Entity entity) {
    this.entity = entity;
  }

  protected OpenMFEntityNoSql(Key parentKey, String kind) {
    this.entity = new Entity(kind, parentKey);
  }

  protected OpenMFEntityNoSql(String kind, String keyName) {
    this.entity = new Entity(kind, keyName);
  }

  public Entity getEntity() {
    return entity;
  }
}
