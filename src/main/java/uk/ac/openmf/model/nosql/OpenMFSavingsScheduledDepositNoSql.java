package uk.ac.openmf.model.nosql;

import uk.ac.openmf.model.OpenMFSavingsScheduledDeposit;
import uk.ac.openmf.utils.OpenMFConstants;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

/**
 * User entity class for NoSQL.
 *
 */
public class OpenMFSavingsScheduledDepositNoSql extends OpenMFEntityNoSql  implements OpenMFSavingsScheduledDeposit {

	public OpenMFSavingsScheduledDepositNoSql(Entity entity) {
		super(entity);
	}

	public OpenMFSavingsScheduledDepositNoSql(Key parentKey, String kind) {
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
	public Long getId() {
		return entity.getKey().getId();
	}

	@Override
	public String getClientId() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_CLIENTID);
	}

	@Override
	public void setClientId(String clientId) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_CLIENTID, clientId);
	}

	@Override
	public String getSavingsaccountid() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_SAVINGSACCOUNTID);
	}

	@Override
	public void setSavingsaccountid(String savingsaccountid) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_SAVINGSACCOUNTID, savingsaccountid);
		
	}

	@Override
	public String getScheduledate() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_SCHEDULEDATE);
	}

	@Override
	public void setScheduledate(String scheduledate) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_SCHEDULEDATE, scheduledate);
		
	}

	@Override
	public String getPaiddate() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_PAIDDATE);
	}

	@Override
	public void setPaiddate(String paiddate) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_PAIDDATE, paiddate);
		
	}

	@Override
	public boolean isPaid() {
		return (boolean) entity.getProperty(OpenMFConstants.FIELD_NAME_PAID);
	}

	@Override
	public void setPaid(boolean paid) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_PAID, paid);
		
	}

	@Override
	public String getInterestamount() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_INTERESTAMOUNT);
	}

	@Override
	public void setInterestamount(String interestamount) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_INTERESTAMOUNT, interestamount);
		
	}

	@Override
	public String getPaidamount() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_PAIDAMOUNT);
	}

	@Override
	public void setPaidamount(String paidamount) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_PAIDAMOUNT, paidamount);
		
	}

	@Override
	public String getBalanceoutstandingamount() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_BALANCEOUTSTANDINGAMOUNT);
	}

	@Override
	public void setBalanceoutstandingamount(String balanceoutstandingamount) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_BALANCEOUTSTANDINGAMOUNT, balanceoutstandingamount);
	}

	@Override
	public String getSerialnumber() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_SERIALNUMBER);
	}

	@Override
	public void setSerialnumber(String serialnumber) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_SERIALNUMBER, serialnumber);
	}
}
