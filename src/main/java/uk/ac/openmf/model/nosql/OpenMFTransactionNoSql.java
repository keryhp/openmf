package uk.ac.openmf.model.nosql;

import uk.ac.openmf.model.OpenMFTransaction;
import uk.ac.openmf.utils.OpenMFConstants;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

/**
 * Transaction entity for NoSQL.
 *
 */
public class OpenMFTransactionNoSql extends OpenMFEntityNoSql implements OpenMFTransaction {

	public OpenMFTransactionNoSql(Entity entity) {
		super(entity);
	}

	public OpenMFTransactionNoSql(Key parentKey, String kind) {
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
	public Long getId() {
		return entity.getKey().getId();
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
	public String getTransactionid() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_TRANSACTIONID);
	}

	@Override
	public void setTransactionid(String transactionid) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_TRANSACTIONID, transactionid);
		
	}

	@Override
	public String getFromaccountid() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_FROMACCOUNTID);
	}

	@Override
	public void setFromaccountid(String fromaccountid) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_FROMACCOUNTID, fromaccountid);
		
	}

	@Override
	public String getToaccountid() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_TOACCOUNTID);
	}

	@Override
	public void setToaccountid(String toaccountid) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_TOACCOUNTID, toaccountid);
		
	}

	@Override
	public String getDateoftransaction() {
		return (String)entity.getProperty(OpenMFConstants.FIELD_NAME_DATEOFTRANSACTION);
	}

	@Override
	public void setDateoftransaction(String dateoftransaction) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_DATEOFTRANSACTION, dateoftransaction);
		
	}

	@Override
	public String getProductid() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_PRODUCTID);
	}

	@Override
	public void setProductid(String productid) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_PRODUCTID, productid);
		
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
	public String getPostedby() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_POSTEDBY);
	}

	@Override
	public void setPostedby(String postedby) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_POSTEDBY, postedby);		
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
	public String getTransactiontype() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_TRANSACTIONTYPE);
	}

	@Override
	public void setTransactiontype(String transactiontype) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_TRANSACTIONTYPE, transactiontype);
	}

	@Override
	public String getClientId() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_CLIENTID);
	}

	@Override
	public void setClientId(String clientId) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_CLIENTID, clientId);
	}

}
