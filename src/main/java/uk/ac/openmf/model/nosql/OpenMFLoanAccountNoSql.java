package uk.ac.openmf.model.nosql;

import uk.ac.openmf.model.OpenMFLoanAccount;
import uk.ac.openmf.utils.OpenMFConstants;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

/**
 * User entity class for NoSQL.
 *
 */
public class OpenMFLoanAccountNoSql extends OpenMFEntityNoSql  implements OpenMFLoanAccount {

	public OpenMFLoanAccountNoSql(Entity entity) {
		super(entity);
	}

	public OpenMFLoanAccountNoSql(Key parentKey, String kind) {
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
	public String getLoancode() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_LOANCODE);
	}

	@Override
	public void setLoancode(String loancode) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_LOANCODE, loancode);				
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
	public void setClientId(String clientaccountid) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_CLIENTID, clientaccountid);

	}

	@Override
	public String getLoanaccountnumber() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_LOANACCOUNTNUMBER);
	}

	@Override
	public void setLoanaccountnumber(String loanaccountnumber) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_LOANACCOUNTNUMBER, loanaccountnumber);

	}

	@Override
	public String getDisbursedon() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_DISBURSEDON);
	}

	@Override
	public void setDisbursedon(String disbursedon) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_DISBURSEDON, disbursedon);

	}

	@Override
	public String getLoanpurpose() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_LOANPURPOSE);
	}

	@Override
	public void setLoanpurpose(String loanpurpose) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_LOANPURPOSE, loanpurpose);

	}

	@Override
	public String getApprovedamount() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_APPROVEDAMOUNT);
	}

	@Override
	public void setApprovedamount(String approvedamount) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_APPROVEDAMOUNT, approvedamount);

	}

	@Override
	public String getDisbursedamount() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_DISBURSEDAMOUNT);
	}

	@Override
	public void setDisbursedamount(String disbursedamount) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_DISBURSEDAMOUNT, disbursedamount);

	}

	@Override
	public String getArrearsby() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_ARREARSBY);
	}

	@Override
	public void setArrearsby(String arrearsby) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_ARREARSBY, arrearsby);

	}

	@Override
	public String getLoanstartdate() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_LOANSTARTDATE);
	}

	@Override
	public void setLoanstartdate(String loanstartdate) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_LOANSTARTDATE, loanstartdate);

	}

	@Override
	public String getLoanclosedate() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_LOANCLOSEDATE);
	}

	@Override
	public void setLoanclosedate(String loanclosedate) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_LOANCLOSEDATE, loanclosedate);

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
	public String getBalanceoutstandingamount() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_BALANCEOUTSTANDINGAMOUNT);
	}

	@Override
	public void setBalanceoutstandingamount(String balanceoutstandingamount) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_BALANCEOUTSTANDINGAMOUNT, balanceoutstandingamount);

	}

	@Override
	public String getPrincipaldueamount() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_PRINCIPALDUEAMOUNT);
	}

	@Override
	public void setPrincipaldueamount(String principaldueamount) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_PRINCIPALDUEAMOUNT, principaldueamount);

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
	public String getTotalnumrepayments() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_TOTALNUMREPAYMENTS);
	}

	@Override
	public void setTotalnumrepayments(String totalnumrepayments) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_TOTALNUMREPAYMENTS, totalnumrepayments);

	}

	@Override
	public String getNumrepaymentsmade() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_NUMREPAYMENTSMADE);
	}

	@Override
	public void setNumrepaymentsmade(String numrepaymentsmade) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_NUMREPAYMENTSMADE, numrepaymentsmade);

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
	public String getFieldofficer() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_FIELDOFFICER);
	}

	@Override
	public void setFieldofficer(String fieldofficer) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_FIELDOFFICER, fieldofficer);

	}

	@Override
	public String getLoanofficer() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_LOANOFFICER);
	}

	@Override
	public void setLoanofficer(String loanofficer) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_LOANOFFICER, loanofficer);

	}

	@Override
	public String getTotalrepaymentamount() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_TOTALREPAYMENTAMOUNT);
	}

	@Override
	public void setTotalrepaymentamount(String totalrepaymentamount) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_TOTALREPAYMENTAMOUNT, totalrepaymentamount);

	}

	@Override
	public String getInterestrepaymentamount() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_INTERESTREPAYMENTAMOUNT);
	}

	@Override
	public void setInterestrepaymentamount(String interestrepaymentamount) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_INTERESTREPAYMENTAMOUNT, interestrepaymentamount);

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
	public String getFees() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_FEES);
	}

	@Override
	public void setFees(String fees) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_FEES, fees);

	}

	@Override
	public String getNumpaymentsmissed(){
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_NUMPAYMENTSMISSED);
	}
	@Override
	public void setNumpaymentsmissed(String numpaymentsmissed){
		entity.setProperty(OpenMFConstants.FIELD_NAME_NUMPAYMENTSMISSED, numpaymentsmissed);
	}
	@Override
	public boolean isDefaulted(){
		if(entity.getProperty(OpenMFConstants.FIELD_NAME_DEFAULTED) == null)
			return false;
		else 
			return (boolean) entity.getProperty(OpenMFConstants.FIELD_NAME_DEFAULTED);
	}
	@Override
	public void setDefaulted(boolean defaulted){
		entity.setProperty(OpenMFConstants.FIELD_NAME_DEFAULTED, defaulted);
	}

	@Override
	public String getGroupId() {
		return (String) entity.getProperty(OpenMFConstants.FIELD_NAME_GROUPID);
	}

	@Override
	public void setGroupId(String groupId) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_GROUPID, groupId);
	}

	@Override
	public boolean isGrouploan() {
		return (boolean) entity.getProperty(OpenMFConstants.FIELD_NAME_GROUPLOAN);
	}

	@Override
	public void setGrouploan(boolean grouploan) {
		entity.setProperty(OpenMFConstants.FIELD_NAME_GROUPLOAN, grouploan);
	}

}
