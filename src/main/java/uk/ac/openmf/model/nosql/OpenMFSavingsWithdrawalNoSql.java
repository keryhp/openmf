package uk.ac.openmf.model.nosql;

import uk.ac.openmf.model.OpenMFSavingsWithdrawal;
import uk.ac.openmf.utils.OpenMFConstants;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

/**
 * User entity class for NoSQL.
 *
 */
public class OpenMFSavingsWithdrawalNoSql extends OpenMFEntityNoSql  implements OpenMFSavingsWithdrawal {

	public OpenMFSavingsWithdrawalNoSql(Entity entity) {
		super(entity);
	}

	public OpenMFSavingsWithdrawalNoSql(Key parentKey, String kind) {
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
	public boolean isStatus() {
		return (boolean) entity.getProperty(OpenMFConstants.FIELD_NAME_STATUS);
	}

	@Override
	public void setStatus(boolean status) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_STATUS, status);

	}

	@Override
	public String getDateofwithdrawal() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_DATEOFWITHDRAWAL);
	}

	@Override
	public void setDateofwithdrawal(String dateofwithdrawal) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_DATEOFWITHDRAWAL, dateofwithdrawal);
	}

	@Override
	public String getWithdrawalamount() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_WITHDRAWALAMOUNT);
	}

	@Override
	public void setWithdrawalamount(String withdrawalamount) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_WITHDRAWALAMOUNT, withdrawalamount);
	}
}
