package uk.ac.openmf.web.forms;


/**
 * @author harish
 */
public class SavingsAccountForm {
	
	private String clientId;
	private String savingscode;
	private String savingsaccountnumber;
	private String savingspurpose;
	private String savingsstartdate;
	private String savingsclosedate;
	private String interestcalcperiod;
	private boolean active;
	private String totalnumdeposits;
	private String submittedon;
	private String approvedon;
	private String matureson;
	private String approvedby;
	private String savingsofficer;	
	private String totalprincipaldeposit;
	private String availablebalance;
	private String totalinterestgain;
	private boolean scheduledepositmissed;
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getSavingscode() {
		return savingscode;
	}
	public void setSavingscode(String savingscode) {
		this.savingscode = savingscode;
	}
	public String getSavingsaccountnumber() {
		return savingsaccountnumber;
	}
	public void setSavingsaccountnumber(String savingsaccountnumber) {
		this.savingsaccountnumber = savingsaccountnumber;
	}
	public String getSavingspurpose() {
		return savingspurpose;
	}
	public void setSavingspurpose(String savingspurpose) {
		this.savingspurpose = savingspurpose;
	}
	public String getSavingsstartdate() {
		return savingsstartdate;
	}
	public void setSavingsstartdate(String savingsstartdate) {
		this.savingsstartdate = savingsstartdate;
	}
	public String getSavingsclosedate() {
		return savingsclosedate;
	}
	public void setSavingsclosedate(String savingsclosedate) {
		this.savingsclosedate = savingsclosedate;
	}
	public String getInterestcalcperiod() {
		return interestcalcperiod;
	}
	public void setInterestcalcperiod(String interestcalcperiod) {
		this.interestcalcperiod = interestcalcperiod;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getTotalnumdeposits() {
		return totalnumdeposits;
	}
	public void setTotalnumdeposits(String totalnumdeposits) {
		this.totalnumdeposits = totalnumdeposits;
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
	public String getSavingsofficer() {
		return savingsofficer;
	}
	public void setSavingsofficer(String savingsofficer) {
		this.savingsofficer = savingsofficer;
	}
	public String getTotalprincipaldeposit() {
		return totalprincipaldeposit;
	}
	public void setTotalprincipaldeposit(String totalprincipaldeposit) {
		this.totalprincipaldeposit = totalprincipaldeposit;
	}
	public String getTotalinterestgain() {
		return totalinterestgain;
	}
	public void setTotalinterestgain(String totalinterestgain) {
		this.totalinterestgain = totalinterestgain;
	}
	public String getAvailablebalance() {
		return availablebalance;
	}
	public void setAvailablebalance(String availablebalance) {
		this.availablebalance = availablebalance;
	}
	public boolean isScheduledepositmissed() {
		return scheduledepositmissed;
	}
	public void setScheduledepositmissed(boolean scheduledepositmissed) {
		this.scheduledepositmissed = scheduledepositmissed;
	}
}
