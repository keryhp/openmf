package uk.ac.openmf.model.nosql;

import uk.ac.openmf.model.OpenMFLoanProduct;
import uk.ac.openmf.utils.OpenMFConstants;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

/**
 * User entity class for NoSQL.
 *
 */
public class OpenMFLoanProductNoSql extends OpenMFEntityNoSql  implements OpenMFLoanProduct {

	public OpenMFLoanProductNoSql(Entity entity) {
		super(entity);
	}

	public OpenMFLoanProductNoSql(Key parentKey, String kind) {
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
	public String getLoancode() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_LOANCODE);
	}

	@Override
	public void setLoancode(String loancode) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_LOANCODE, loancode);				
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
	public String getRepaymentperiod() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_REPAYMENTPERIOD);
	}

	@Override
	public void setRepaymentperiod(String repaymentperiod) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_REPAYMENTPERIOD, repaymentperiod);		
	}

	@Override
	public String getRepaymentfrequency() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_REPAYMENTFREQUENCY);
	}

	@Override
	public void setRepaymentfrequency(String repaymentfrequency) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_REPAYMENTFREQUENCY, repaymentfrequency);		
	}
	
	@Override
	public String getLoanamount() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_LOANAMOUNT);
	}

	@Override
	public void setLoanamount(String loanamount) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_LOANAMOUNT, loanamount);		
	}
	
	@Override
	public Long getId() {
		return entity.getKey().getId();
	}
	
	@Override
	public String getLoantype() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_LOANTYPE);
	}

	@Override
	public void setLoantype(String loantype) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_LOANTYPE, loantype);		
	}

}
