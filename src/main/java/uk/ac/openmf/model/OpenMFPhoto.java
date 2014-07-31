package uk.ac.openmf.model;

import com.google.appengine.api.blobstore.BlobKey;

/**
 * The photo entity interface.
 *
 */
public interface OpenMFPhoto extends OpenMFEntity {

	Long getId();

	BlobKey getBlobKey();

	void setBlobKey(BlobKey blobKey);

	String getType();

	void setType(String title);

	boolean isActive();

	void setActive(boolean active);

	long getTypeId();

	void setTypeId(long typeId);
	
	long getTimestamp();
	
	void setTimestamp(long timestamp);
	
	String getCreatedById();
	
	void setCreatedById(String userId);
}
