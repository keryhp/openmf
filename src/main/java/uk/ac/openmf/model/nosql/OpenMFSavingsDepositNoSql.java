package uk.ac.openmf.model.nosql;

import uk.ac.openmf.model.OpenMFSavingsDeposit;
import uk.ac.openmf.utils.OpenMFConstants;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

/**
 * User entity class for NoSQL.
 *
 */
public class OpenMFSavingsDepositNoSql extends OpenMFEntityNoSql  implements OpenMFSavingsDeposit {

	public OpenMFSavingsDepositNoSql(Entity entity) {
		super(entity);
	}

	public OpenMFSavingsDepositNoSql(Key parentKey, String kind) {
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
	public String getDateofpayment() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_PAIDDATE);

	}

	@Override
	public void setDateofpayment(String dateofpayment) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_PAIDDATE, dateofpayment);

	}

	@Override
	public String getTransactionreference() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_TRANSACTIONREFERENCE);
	}

	@Override
	public void setTransactionreference(String transactionreference) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_TRANSACTIONREFERENCE, transactionreference);

	}

	@Override
	public String getTransactionnote() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_TRANSACTIONNOTE);
	}

	@Override
	public void setTransactionnote(String transactionnote) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_TRANSACTIONNOTE, transactionnote);

	}

	@Override
	public String getPostedby() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_POSTEDBY);
	}

	@Override
	public void setPostedby(String postedby) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_POSTEDBY, postedby);

	}

	@Override
	public String getAmountpaid() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_PAIDAMOUNT);
	}

	@Override
	public void setAmountpaid(String amountpaid) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_PAIDAMOUNT, amountpaid);

	}

	@Override
	public boolean isStatus() {
		if(entity.getProperty(OpenMFConstants.FIELD_NAME_STATUS) == null)
			return false;
		else 
			return (boolean) entity.getProperty(OpenMFConstants.FIELD_NAME_STATUS);	
	}

	@Override
	public void setStatus(boolean status) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_STATUS, status);

	}


}
