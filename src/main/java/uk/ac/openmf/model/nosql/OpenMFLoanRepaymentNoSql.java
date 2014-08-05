package uk.ac.openmf.model.nosql;

import uk.ac.openmf.model.OpenMFLoanRepayment;
import uk.ac.openmf.utils.OpenMFConstants;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

/**
 * User entity class for NoSQL.
 *
 */
public class OpenMFLoanRepaymentNoSql extends OpenMFEntityNoSql  implements OpenMFLoanRepayment {

	public OpenMFLoanRepaymentNoSql(Entity entity) {
		super(entity);
	}

	public OpenMFLoanRepaymentNoSql(Key parentKey, String kind) {
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
	public String getLoanaccountid() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_LOANACCOUNTID);
	}

	@Override
	public void setLoanaccountid(String loanaccountid) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_LOANACCOUNTID, loanaccountid);
		
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
	public String getPrincipaldue() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_PRINCIPALDUEAMOUNT);

	}

	@Override
	public void setPrincipaldue(String principaldue) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_PRINCIPALDUEAMOUNT, principaldue);
		
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
	public String getFees() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_FEES);
	}

	@Override
	public void setFees(String fees) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_FEES, fees);
		
	}

	@Override
	public String getPenalties() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_PENALTIES);
	}

	@Override
	public void setPenalties(String penalties) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_PENALTIES, penalties);
		
	}

	@Override
	public String getDueamount() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_DUEAMOUNT);
	}

	@Override
	public void setDueamount(String dueamount) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_DUEAMOUNT, dueamount);
		
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
	@Override
	public String getSupervisor() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_SUPERVISOR);
	}

	@Override
	public void setSupervisor(String supervisor) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_SUPERVISOR, supervisor);
	}
}
