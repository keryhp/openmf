package uk.ac.openmf.model.nosql;

import uk.ac.openmf.model.OpenMFTask;
import uk.ac.openmf.utils.OpenMFConstants;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

/**
 * Task entity for NoSQL.
 *
 */
public class OpenMFTasksNoSql extends OpenMFEntityNoSql implements OpenMFTask {

	public OpenMFTasksNoSql(Entity entity) {
		super(entity);
	}

	public OpenMFTasksNoSql(Key parentKey, String kind) {
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
	public String getDescription() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_DESCRIPTION);
	}

	@Override
	public void setDescription(String description) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_DESCRIPTION, description);
	}

	@Override
	public String getTaskId() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_TASKID);
	}

	@Override
	public void setTaskId(String taskId) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_TASKID, taskId);

	}

	@Override
	public String getAssignedto() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_ASSIGNEDTO);
	}

	@Override
	public void setAssignedto(String assignedto) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_ASSIGNEDTO, assignedto);

	}

	@Override
	public String getDateassigned() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_DATEASSIGNED);
	}

	@Override
	public void setDateassigned(String dateassigned) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_DATEASSIGNED, dateassigned);

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
	public String getAmount() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_AMOUNT);
	}

	@Override
	public void setAmount(String amount) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_AMOUNT, amount);

	}

	@Override
	public String getCashier() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_CASHIER);
	}

	@Override
	public void setCashier(String cashier) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_CASHIER, cashier);

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
	public String getCollectiontype() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_COLLECTIONTYPE);
	}

	@Override
	public void setCollectiontype(String collectiontype) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_COLLECTIONTYPE, collectiontype);

	}

	@Override
	public boolean isNewclient() {
		return (boolean) entity.getProperty(OpenMFConstants.FIELD_NAME_NEWCLIENT);
	}

	@Override
	public void setNewclient(boolean newclient) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_NEWCLIENT, newclient);

	}

	@Override
	public String getDatecompleted() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_DATECOMPLETED);
	}

	@Override
	public void setDatecompleted(String datecompleted) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_DATECOMPLETED, datecompleted);

	}

}
