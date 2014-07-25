package uk.ac.openmf.model.nosql;

import uk.ac.openmf.model.OpenMFRoles;
import uk.ac.openmf.utils.OpenMFConstants;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

/**
 * Roles entity for NoSQL.
 *
 */
public class OpenMFRolesNoSql extends OpenMFEntityNoSql implements OpenMFRoles {

  public OpenMFRolesNoSql(Entity entity) {
    super(entity);
  }

  public OpenMFRolesNoSql(Key parentKey, String kind) {
    super(parentKey, kind);
  }
  
  @Override
  public long getTimestamp() {
    Long timestamp = (Long) entity.getProperty(OpenMFConstants.FIELD_NAME_TIMESTAMP);
    return timestamp == null ? 0 : timestamp;
  }

  @Override
  public void setTimestamp(long timestamp) {
    entity.setProperty(OpenMFConstants.FIELD_NAME_TIMESTAMP, timestamp);
  }
  
  @Override
  public Long getId() {
    return entity.getKey().getId();
  }

@Override
public String getRoleId() {
    return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_ROLEID);
}

@Override
public void setRoleId(String roleId) {
	entity.setProperty(OpenMFConstants.FIELD_NAME_ROLEID, roleId);
}

@Override
public String getCreatedById() {
	return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_CREATEDBY);
}

@Override
public void setCreatedById(String userId) {
	entity.setProperty(OpenMFConstants.FIELD_NAME_CREATEDBY, userId);
}

@Override
public String getDescription() {
	return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_DESCRIPTION);
}

@Override
public void setDescription(String description) {
	entity.setProperty(OpenMFConstants.FIELD_NAME_DESCRIPTION, description);
}

}
