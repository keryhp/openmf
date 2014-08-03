package uk.ac.openmf.model.nosql;

import uk.ac.openmf.model.OpenMFChartOfAccounts;
import uk.ac.openmf.utils.OpenMFConstants;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

/**
 * ChartOfAccounts entity for NoSQL.
 *
 */
public class OpenMFChartOfAccountsNoSql extends OpenMFEntityNoSql implements OpenMFChartOfAccounts {

	public OpenMFChartOfAccountsNoSql(Entity entity) {
		super(entity);
	}

	public OpenMFChartOfAccountsNoSql(Key parentKey, String kind) {
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
	public String getMfiaccounttype() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_MFIACCOUNTTYPE);
	}

	@Override
	public void setMfiaccounttype(String mfiaccounttype) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_MFIACCOUNTTYPE, mfiaccounttype);

	}

	@Override
	public String getOffice() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_OFFICE);
	}

	@Override
	public void setOffice(String office) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_OFFICE, office);
	}

	@Override
	public String getCoaname() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_COANAME);
	}

	@Override
	public void setCoaname(String coaname) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_COANAME, coaname);
	}

	@Override
	public String getFunds() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_FUNDS);
	}

	@Override
	public void setFunds(String funds) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_FUNDS, funds);		
	}
}
