package uk.ac.openmf.model.nosql;

import uk.ac.openmf.model.OpenMFPhoto;
import uk.ac.openmf.utils.OpenMFConstants;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

/**
 * Photo entity for NoSQL.
 *
 */
public class OpenMFPhotoNoSql extends OpenMFEntityNoSql implements OpenMFPhoto {

	public OpenMFPhotoNoSql(Entity entity) {
		super(entity);
	}

	public OpenMFPhotoNoSql(Key parentKey, String kind) {
		super(parentKey, kind);
		setActive(true);
	}

	@Override
	public BlobKey getBlobKey() {
		return (BlobKey) entity.getProperty(OpenMFConstants.FIELD_NAME_BLOB_KEY);
	}

	@Override
	public void setBlobKey(BlobKey blobKey) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_BLOB_KEY, blobKey);
	}

	@Override
	public Long getId() {
		return entity.getKey().getId();
	}

	@Override
	public boolean isActive() {
		Boolean active = (Boolean) entity.getProperty(OpenMFConstants.FIELD_NAME_ACTIVE);
		// By default, if not set false, a photo is active.
		return active != null && active;
	}

	@Override
	public void setActive(boolean active) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_ACTIVE, active);
	}

	@Override
	public String getType() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_TYPE);
	}

	@Override
	public void setType(String type) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_TYPE, type);

	}

	@Override
	public long getTypeId() {
		return (long) entity.getProperty(OpenMFConstants.FIELD_NAME_TYPEID);
	}

	@Override
	public void setTypeId(long typeId) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_TYPEID, typeId);

	}

	@Override
	public long getTimestamp() {
		return (long) entity.getProperty(OpenMFConstants.FIELD_NAME_TIMESTAMP);
	}

	@Override
	public void setTimestamp(long timestamp) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_TIMESTAMP, timestamp);

	}

	@Override
	public String getCreatedById() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_CREATEDBY);
	}

	@Override
	public void setCreatedById(String userId) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_CREATEDBY, userId);

	}

}
