package uk.ac.openmf.web.forms;


/**
 * @author harish
 */
public class SavingsDepositSchedule {

	private String clientId;
	private String savingsaccountid;
	private String serialnumber;
	private String scheduledate;
	private String paiddate;
	private boolean paid;
	private String interestamount;
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
	public String getSavingsaccountid() {
		return savingsaccountid;
	}
	public void setSavingsaccountid(String savingsaccountid) {
		this.savingsaccountid = savingsaccountid;
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
	public String getInterestamount() {
		return interestamount;
	}
	public void setInterestamount(String interestamount) {
		this.interestamount = interestamount;
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
