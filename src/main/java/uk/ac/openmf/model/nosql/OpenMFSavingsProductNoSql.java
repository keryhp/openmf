package uk.ac.openmf.model.nosql;

import uk.ac.openmf.model.OpenMFSavingsProduct;
import uk.ac.openmf.utils.OpenMFConstants;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

/**
 * User entity class for NoSQL.
 *
 */
public class OpenMFSavingsProductNoSql extends OpenMFEntityNoSql  implements OpenMFSavingsProduct {

	public OpenMFSavingsProductNoSql(Entity entity) {
		super(entity);
	}

	public OpenMFSavingsProductNoSql(Key parentKey, String kind) {
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
	public String getProductname() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_PRODUCTNAME);
	}

	@Override
	public void setProductname(String productname) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_PRODUCTNAME, productname);			
	}

	@Override
	public String getSavingscode() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_SAVINGSCODE);
	}

	@Override
	public void setSavingscode(String Savingscode) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_SAVINGSCODE, Savingscode);				
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
	public String getStartdate() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_STARTDATE);
	}

	@Override
	public void setStartdate(String startdate) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_STARTDATE, startdate);		

	}

	@Override
	public String getClosedate() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_CLOSEDATE);
	}

	@Override
	public void setClosedate(String closedate) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_CLOSEDATE, closedate);		

	}

	@Override
	public String getRateofinterest() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_RATEOFINTEREST);
	}

	@Override
	public void setRateofinterest(String rateofinterest) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_RATEOFINTEREST, rateofinterest);		

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
	public String getTenure() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_TENURE);
	}

	@Override
	public void setTenure(String tenure) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_TENURE, tenure);		
	}

	@Override
	public String getDepositfrequency() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_DEPOSITFREQUENCY);
	}

	@Override
	public void setDepositfrequency(String depositfrequency) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_DEPOSITFREQUENCY, depositfrequency);		
	}
	
	@Override
	public String getSavingsamount() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_SAVINGSAMOUNT);
	}

	@Override
	public void setSavingsamount(String savingsamount) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_SAVINGSAMOUNT, savingsamount);		
	}
	
	@Override
	public Long getId() {
		return entity.getKey().getId();
	}

	@Override
	public String getSavingstype() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_SAVINGSTYPE);
	}

	@Override
	public void setSavingstype(String savingstype) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_SAVINGSTYPE, savingstype);		
	}
}
