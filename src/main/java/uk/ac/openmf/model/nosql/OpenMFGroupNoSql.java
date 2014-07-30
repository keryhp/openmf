package uk.ac.openmf.model.nosql;

import uk.ac.openmf.model.OpenMFGroup;
import uk.ac.openmf.utils.OpenMFConstants;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

/**
 * User entity class for NoSQL.
 *
 */
public class OpenMFGroupNoSql extends OpenMFEntityNoSql  implements OpenMFGroup {

	public OpenMFGroupNoSql(Entity entity) {
		super(entity);
	}

	public OpenMFGroupNoSql(Key parentKey, String kind) {
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
	public String getCreatedById() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_CREATEDBY);
	}

	@Override
	public void setCreatedById(String userId) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_CREATEDBY, userId);
	}

	@Override
	public boolean isActive() {
		return (boolean) entity.getProperty(OpenMFConstants.FIELD_NAME_ACTIVE);
	}

	@Override
	public void setActive(boolean active) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_ACTIVE, active);		
	}

	@Override
	public String getOffice() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_MAIN_OFFICE);
	}

	@Override
	public void setOffice(String office) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_MAIN_OFFICE, office);
	}

	@Override
	public String getContact() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_CONTACT);
	}

	@Override
	public void setContact(String contact) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_CONTACT, contact);
	}

	@Override
	public String getExternalId() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_EXTERNALID);
	}

	@Override
	public void setExternalId(String externalId) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_EXTERNALID, externalId);
	}

	@Override
	public String getSupervisor() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_SUPERVISOR);
	}

	@Override
	public void setSupervisor(String supervisor) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_SUPERVISOR, supervisor);
	}

	@Override
	public String getActivationdate() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_ACTIVATIONDATE);
	}

	@Override
	public void setActivationdate(String activationdate) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_ACTIVATIONDATE, activationdate);
	}

	@Override
	public String getSubmittedon() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_SUBMITTEDON);
	}

	@Override
	public void setSubmittedon(String submittedon) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_SUBMITTEDON, submittedon);
	}

	@Override
	public String getAddress() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_ADDRESS);
	}

	@Override
	public void setAddress(String address) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_ADDRESS, address);
	}

	@Override
	public Long getId() {
		return entity.getKey().getId();
	}

	@Override
	public boolean isEligible() {
		return (boolean) entity.getProperty(OpenMFConstants.FIELD_NAME_ELIGIBLE);
	}

	@Override
	public void setEligible(boolean eligible) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_ELIGIBLE, eligible);		
	}

	@Override
	public boolean isBlacklisted() {
		return (boolean) entity.getProperty(OpenMFConstants.FIELD_NAME_BLACKLISTED);
	}

	@Override
	public void setBlacklisted(boolean blacklisted) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_BLACKLISTED, blacklisted);		
	}

	@Override
	public String getAccountnumber() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_ACCOUNTNUMBER);
	}

	@Override
	public void setAccountnumber(String accountnumber) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_ACCOUNTNUMBER, accountnumber);		

	}

	@Override
	public String getGroupname() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_GROUPNAME);
	}

	@Override
	public void setGroupname(String groupname) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_GROUPNAME, groupname);		

	}

	@Override
	public String getLeadclientname() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_LEADCLIENTNAME);
	}

	@Override
	public void setLeadclientname(String leadclientname) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_LEADCLIENTNAME, leadclientname);		

	}

}
