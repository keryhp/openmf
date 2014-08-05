package uk.ac.openmf.web.forms;


/**
 * @author harish
 */
public class LoanRepaymentSchedule {
	
	private String clientId;
	private String loanaccountid;
	private String serialnumber;
	private String scheduledate;
	private String paiddate;
	private boolean paid;
	private String principaldue;
	private String interestamount;
	private String fees;
	private String penalties;
	private String dueamount;
	private String paidamount;
	private String balanceoutstandingamount;
	private boolean active;
	private String supervisor;

	public String getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getLoanaccountid() {
		return loanaccountid;
	}
	public void setLoanaccountid(String loanaccountid) {
		this.loanaccountid = loanaccountid;
	}
	public String getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	public String getScheduledate() {
		return scheduledate;
	}
	public void setScheduledate(String scheduledate) {
		this.scheduledate = scheduledate;
	}
	public String getPaiddate() {
		return paiddate;
	}
	public void setPaiddate(String paiddate) {
		this.paiddate = paiddate;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	public String getPrincipaldue() {
		return principaldue;
	}
	public void setPrincipaldue(String principaldue) {
		this.principaldue = principaldue;
	}
	public String getInterestamount() {
		return interestamount;
	}
	public void setInterestamount(String interestamount) {
		this.interestamount = interestamount;
	}
	public String getFees() {
		return fees;
	}
	public void setFees(String fees) {
		this.fees = fees;
	}
	public String getPenalties() {
		return penalties;
	}
	public void setPenalties(String penalties) {
		this.penalties = penalties;
	}
	public String getDueamount() {
		return dueamount;
	}
	public void setDueamount(String dueamount) {
		this.dueamount = dueamount;
	}
	public String getPaidamount() {
		return paidamount;
	}
	public void setPaidamount(String paidamount) {
		this.paidamount = paidamount;
	}
	public String getBalanceoutstandingamount() {
		return balanceoutstandingamount;
	}
	public void setBalanceoutstandingamount(String balanceoutstandingamount) {
		this.balanceoutstandingamount = balanceoutstandingamount;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
}
