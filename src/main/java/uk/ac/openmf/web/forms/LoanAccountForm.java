package uk.ac.openmf.web.forms;


/**
 * @author harish
 */
public class LoanAccountForm {
	
	private String clientId;
	private String groupId;
	private boolean grouploan;
	private String loancode;
	private String loanaccountnumber;
	private String disbursedon;
	private String loanpurpose;
	private String approvedamount;
	private String disbursedamount;
	private String arrearsby;
	private String loanstartdate;
	private String loanclosedate;
	private String interestcalcperiod;
	private String balanceoutstandingamount;
	private String principaldueamount;
	private boolean active;
	private String totalnumrepayments;
	private String numrepaymentsmade;
	private String submittedon;
	private String approvedon;
	private String matureson;
	private String approvedby;
	private String fieldofficer;
	private String loanofficer;	
	private String totalrepaymentamount;//amount paid till date
	private String interestrepaymentamount;
	private String numpaymentsmissed;
	private boolean defaulted;
	private String penalties;
	private String fees;
	
	public String getLoancode() {
		return loancode;
	}
	public void setLoancode(String loancode) {
		this.loancode = loancode;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getLoanaccountnumber() {
		return loanaccountnumber;
	}
	public void setLoanaccountnumber(String loanaccountnumber) {
		this.loanaccountnumber = loanaccountnumber;
	}
	public String getDisbursedon() {
		return disbursedon;
	}
	public void setDisbursedon(String disbursedon) {
		this.disbursedon = disbursedon;
	}
	public String getLoanpurpose() {
		return loanpurpose;
	}
	public void setLoanpurpose(String loanpurpose) {
		this.loanpurpose = loanpurpose;
	}
	public String getApprovedamount() {
		return approvedamount;
	}
	public void setApprovedamount(String approvedamount) {
		this.approvedamount = approvedamount;
	}
	public String getDisbursedamount() {
		return disbursedamount;
	}
	public void setDisbursedamount(String disbursedamount) {
		this.disbursedamount = disbursedamount;
	}
	public String getArrearsby() {
		return arrearsby;
	}
	public void setArrearsby(String arrearsby) {
		this.arrearsby = arrearsby;
	}
	public String getLoanstartdate() {
		return loanstartdate;
	}
	public void setLoanstartdate(String loanstartdate) {
		this.loanstartdate = loanstartdate;
	}
	public String getLoanclosedate() {
		return loanclosedate;
	}
	public void setLoanclosedate(String loanclosedate) {
		this.loanclosedate = loanclosedate;
	}
	public String getInterestcalcperiod() {
		return interestcalcperiod;
	}
	public void setInterestcalcperiod(String interestcalcperiod) {
		this.interestcalcperiod = interestcalcperiod;
	}
	public String getBalanceoutstandingamount() {
		return balanceoutstandingamount;
	}
	public void setBalanceoutstandingamount(String balanceoutstandingamount) {
		this.balanceoutstandingamount = balanceoutstandingamount;
	}
	public String getPrincipaldueamount() {
		return principaldueamount;
	}
	public void setPrincipaldueamount(String principaldueamount) {
		this.principaldueamount = principaldueamount;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getTotalnumrepayments() {
		return totalnumrepayments;
	}
	public void setTotalnumrepayments(String totalnumrepayments) {
		this.totalnumrepayments = totalnumrepayments;
	}
	public String getNumrepaymentsmade() {
		return numrepaymentsmade;
	}
	public void setNumrepaymentsmade(String numrepaymentsmade) {
		this.numrepaymentsmade = numrepaymentsmade;
	}
	public String getSubmittedon() {
		return submittedon;
	}
	public void setSubmittedon(String submittedon) {
		this.submittedon = submittedon;
	}
	public String getApprovedon() {
		return approvedon;
	}
	public void setApprovedon(String approvedon) {
		this.approvedon = approvedon;
	}
	public String getMatureson() {
		return matureson;
	}
	public void setMatureson(String matureson) {
		this.matureson = matureson;
	}
	public String getApprovedby() {
		return approvedby;
	}
	public void setApprovedby(String approvedby) {
		this.approvedby = approvedby;
	}
	public String getFieldofficer() {
		return fieldofficer;
	}
	public void setFieldofficer(String fieldofficer) {
		this.fieldofficer = fieldofficer;
	}
	public String getLoanofficer() {
		return loanofficer;
	}
	public void setLoanofficer(String loanofficer) {
		this.loanofficer = loanofficer;
	}
	public String getTotalrepaymentamount() {
		return totalrepaymentamount;
	}
	public void setTotalrepaymentamount(String totalrepaymentamount) {
		this.totalrepaymentamount = totalrepaymentamount;
	}
	public String getInterestrepaymentamount() {
		return interestrepaymentamount;
	}
	public void setInterestrepaymentamount(String interestrepaymentamount) {
		this.interestrepaymentamount = interestrepaymentamount;
	}
	public String getPenalties() {
		return penalties;
	}
	public void setPenalties(String penalties) {
		this.penalties = penalties;
	}
	public String getFees() {
		return fees;
	}
	public void setFees(String fees) {
		this.fees = fees;
	}
	public String getNumpaymentsmissed() {
		return numpaymentsmissed;
	}
	public void setNumpaymentsmissed(String numpaymentsmissed) {
		this.numpaymentsmissed = numpaymentsmissed;
	}
	public boolean isDefaulted() {
		return defaulted;
	}
	public void setDefaulted(boolean defaulted) {
		this.defaulted = defaulted;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public boolean isGrouploan() {
		return grouploan;
	}
	public void setGrouploan(boolean grouploan) {
		this.grouploan = grouploan;
	}
}
