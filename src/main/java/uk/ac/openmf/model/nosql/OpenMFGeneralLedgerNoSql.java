package uk.ac.openmf.model.nosql;

import uk.ac.openmf.model.OpenMFGeneralLedger;
import uk.ac.openmf.utils.OpenMFConstants;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

/**
 * GeneralLedger entity for NoSQL.
 *
 */
public class OpenMFGeneralLedgerNoSql extends OpenMFEntityNoSql implements OpenMFGeneralLedger {

	public OpenMFGeneralLedgerNoSql(Entity entity) {
		super(entity);
	}

	public OpenMFGeneralLedgerNoSql(Key parentKey, String kind) {
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
	public String getCoaid() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_COAID);
	}

	@Override
	public void setCoaid(String coaid) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_COAID, coaid);

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
	public String getClientaccountid() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_CLIENTACCOUNTID);
	}

	@Override
	public void setClientaccountid(String clientaccountid) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_CLIENTACCOUNTID, clientaccountid);

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
	public boolean getStatus() {
		return (boolean) entity.getProperty(OpenMFConstants.FIELD_NAME_STATUS);
	}

	@Override
	public void setStatus(boolean status) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_STATUS, status);

	}

	@Override
	public String getGeneralledgerid() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_GENERALLEDGERID);
	}

	@Override
	public void setGeneralledgerid(String generalledgerid) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_GENERALLEDGERID, generalledgerid);
		
	}

	@Override
	public String getBalanceavailable() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_AVAILABLEBALANCE);
	}

	@Override
	public void setBalanceavailable(String balanceavailable) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_AVAILABLEBALANCE, balanceavailable);
		
	}

	@Override
	public String getBalancePending() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_BALANCEPENDING);
	}

	@Override
	public void setBalancePending(String balancePending) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_BALANCEPENDING, balancePending);
	}

	@Override
	public String getForDate() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_FORDATE);
	}

	@Override
	public void setForDate(String fordate) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_FORDATE, fordate);	
	}

}
