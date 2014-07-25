package uk.ac.openmf.model.nosql;

import uk.ac.openmf.model.OpenMFUser;
import uk.ac.openmf.utils.OpenMFConstants;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

/**
 * User entity class for NoSQL.
 *
 */
public class OpenMFUserNoSql extends OpenMFEntityNoSql  implements OpenMFUser {

	public OpenMFUserNoSql(Entity entity) {
		super(entity);
	}

	public OpenMFUserNoSql(Key parentKey, String kind) {
	    super(parentKey, kind);
	  }
	
	/*public OpenMFUserNoSql(String kind, String userId) {
		super(kind, userId);
	}*/

	@Override
	public String getUserId() {
		return entity.getKey().getName();
	}

	@Override
	public String getEmail() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_EMAIL);
	}

	@Override
	public String getUsername() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_USERNAME);
	}

	@Override
	public String getForename() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_FORENAME);
	}

	@Override
	public String getSurname() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_SURNAME);
	}

	@Override
	public boolean isEnabled() {
		return (boolean) entity.getProperty(OpenMFConstants.FIELD_NAME_ENABLED);
	}

	@Override
	public String getMain_office() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_MAIN_OFFICE);
	}

	@Override
	public String getContact() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_CONTACT);
	}

	@Override
	public String getPassword() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_PASSWORD);	}

	@Override
	public String getAddress() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_ADDRESS);	}

	@Override
	public String getSupervisor() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_SUPERVISOR);	
	}

	@Override
	public void setUsername(String username) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_USERNAME, username);		
	}

	@Override
	public void setEmail(String email) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_EMAIL, email);		
	}

	@Override
	public void setForename(String forename) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_FORENAME, forename);		
	}

	@Override
	public void setSurname(String surname) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_SURNAME, surname);		
	}

	@Override
	public void setEnabled(boolean enabled) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_ENABLED, enabled);		
	}

	@Override
	public void setMain_office(String mainoffice) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_MAIN_OFFICE, mainoffice);		
	}

	@Override
	public void setContact(String contact) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_CONTACT, contact);		
	}

	@Override
	public void setPassword(String password) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_PASSWORD, password);		
	}

	@Override
	public void setAddress(String address) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_ADDRESS, address);
	}

	@Override
	public void setSupervisor(String supervisor) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_SUPERVISOR, supervisor);

	}

	@Override
	public String getRole() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_ROLE);
	}

	@Override
	public void setRole(String role) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_ROLE, role);
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
	public String getCreatedById() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_CREATEDBY);
	}

	@Override
	public void setCreatedById(String userId) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_CREATEDBY, userId);
	}
	
	@Override
	public Long getId() {
		return entity.getKey().getId();
	}
}
