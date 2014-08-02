package uk.ac.openmf.model.nosql;

import uk.ac.openmf.model.OpenMFClient;
import uk.ac.openmf.utils.OpenMFConstants;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

/**
 * User entity class for NoSQL.
 *
 */
public class OpenMFClientNoSql extends OpenMFEntityNoSql  implements OpenMFClient {

	public OpenMFClientNoSql(Entity entity) {
		super(entity);
	}

	public OpenMFClientNoSql(Key parentKey, String kind) {
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
		if(entity.getProperty(OpenMFConstants.FIELD_NAME_ACTIVE) == null)
			return false;
		else 
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
	public String getForename() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_FORENAME);
	}

	@Override
	public void setForename(String forename) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_FORENAME, forename);
	}

	@Override
	public String getMidname() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_MIDNAME);
	}

	@Override
	public void setMidname(String midname) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_MIDNAME, midname);
	}

	@Override
	public String getSurname() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_SURNAME);
	}

	@Override
	public void setSurname(String surname) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_SURNAME, surname);
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
	public String getDateofbirth() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_DATEOFBIRTH);
	}

	@Override
	public void setDateofbirth(String dateofbirth) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_DATEOFBIRTH, dateofbirth);
	}

	@Override
	public String getClienttype() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_CLIENTTYPE);
	}

	@Override
	public void setClienttype(String clienttype) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_CLIENTTYPE, clienttype);
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
	public String getGender() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_GENDER);
	}

	@Override
	public void setGender(String gender) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_GENDER, gender);
	}

	@Override
	public String getClientclassification() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_CLIENTCLASSIFICATION);
	}

	@Override
	public void setClientclassification(String clientclassification) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_CLIENTCLASSIFICATION, clientclassification);
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
	public String getAccountNumber() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_ACCOUNTNUMBER);
	}

	@Override
	public void setAccountNumber(String accountnumber) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_ACCOUNTNUMBER, accountnumber);
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
	public String getBalance() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_BALANCE);
	}

	@Override
	public void setBalance(String balance) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_BALANCE, balance);
	}

	@Override
	public String getGroupid() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_GROUPID);
	}

	@Override
	public void setGroupid(String groupid) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_GROUPID, groupid);
	}

}
