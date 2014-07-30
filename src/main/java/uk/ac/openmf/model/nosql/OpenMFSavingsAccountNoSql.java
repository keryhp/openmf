package uk.ac.openmf.model.nosql;

import uk.ac.openmf.model.OpenMFSavingsAccount;
import uk.ac.openmf.utils.OpenMFConstants;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

/**
 * SavingsAccount entity class for NoSQL.
 *
 */
public class OpenMFSavingsAccountNoSql extends OpenMFEntityNoSql  implements OpenMFSavingsAccount {

	public OpenMFSavingsAccountNoSql(Entity entity) {
		super(entity);
	}

	public OpenMFSavingsAccountNoSql(Key parentKey, String kind) {
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
	public String getSavingscode() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_SAVINGSCODE);
	}

	@Override
	public void setSavingscode(String savingscode) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_SAVINGSCODE, savingscode);				
	}

	@Override
	public Long getId() {
		return entity.getKey().getId();
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
	public String getSavingsaccountnumber() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_SAVINGSACCOUNTNUMBER);
	}

	@Override
	public void setSavingsaccountnumber(String savingsaccountnumber) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_SAVINGSACCOUNTNUMBER, savingsaccountnumber);

	}

	@Override
	public String getSavingspurpose() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_SAVINGSPURPOSE);
	}

	@Override
	public void setSavingspurpose(String savingspurpose) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_SAVINGSPURPOSE, savingspurpose);

	}

	@Override
	public String getSavingsstartdate() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_SAVINGSSTARTDATE);
	}

	@Override
	public void setSavingsstartdate(String savingsstartdate) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_SAVINGSSTARTDATE, savingsstartdate);

	}

	@Override
	public String getSavingsclosedate() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_SAVINGSCLOSEDATE);
	}

	@Override
	public void setSavingsclosedate(String savingsclosedate) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_SAVINGSCLOSEDATE, savingsclosedate);

	}

	@Override
	public String getInterestcalcperiod() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_INTERESTCALCPERIOD);
	}

	@Override
	public void setInterestcalcperiod(String interestcalcperiod) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_INTERESTCALCPERIOD, interestcalcperiod);

	}

	@Override
	public String getTotalnumdeposits() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_TOTALNUMDEPOSITS);
	}

	@Override
	public void setTotalnumdeposits(String totalnumdeposits) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_TOTALNUMDEPOSITS, totalnumdeposits);

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
	public String getApprovedon() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_APPROVEDON);
	}

	@Override
	public void setApprovedon(String approvedon) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_APPROVEDON, approvedon);

	}

	@Override
	public String getMatureson() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_MATURESON);
	}

	@Override
	public void setMatureson(String matureson) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_MATURESON, matureson);

	}

	@Override
	public String getApprovedby() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_APPROVEDBY);
	}

	@Override
	public void setApprovedby(String approvedby) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_APPROVEDBY, approvedby);

	}

	@Override
	public String getSavingsofficer() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_SAVINGSOFFICER);
	}

	@Override
	public void setSavingsofficer(String savingsofficer) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_SAVINGSOFFICER, savingsofficer);

	}

	@Override
	public String getTotalprincipaldeposit() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_TOTALPRINCIPALDEPOSIT);
	}

	@Override
	public void setTotalprincipaldeposit(String totalprincipaldeposit) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_TOTALPRINCIPALDEPOSIT, totalprincipaldeposit);

	}

	@Override
	public String getTotalinterestgain() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_TOTALINTERESTGAIN);
	}

	@Override
	public void setTotalinterestgain(String totalinterestgain) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_TOTALINTERESTGAIN, totalinterestgain);

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
	public String getAvailablebalance() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_AVAILABLEBALANCE);	}

	@Override
	public void setAvailablebalance(String availablebalance) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_AVAILABLEBALANCE, availablebalance);
	}

	@Override
	public boolean isScheduledepositmissed() {
		if(entity.getProperty(OpenMFConstants.FIELD_NAME_SCHEDULEDEPOSITMISSED) == null)
			return false;
		return (boolean) entity.getProperty(OpenMFConstants.FIELD_NAME_SCHEDULEDEPOSITMISSED);	
	}

	@Override
	public void setScheduledepositmissed(boolean scheduledepositmissed) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_SCHEDULEDEPOSITMISSED, scheduledepositmissed);
	}

}
