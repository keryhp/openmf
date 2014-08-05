package uk.ac.openmf.model;


/**
 * OpenMFClient entity interface.
 *
 */
public interface OpenMFLoanRepayment extends OpenMFEntity {

	Long getId();
	long getTimestamp();
	void setTimestamp(long timestamp);
	String getCreatedById();
	void setCreatedById(String userId);
	boolean isActive() ;
	void setActive(boolean active);
	public String getClientId();
	public void setClientId(String clientId);
	public String getLoanaccountid();
	public void setLoanaccountid(String loanaccountid);
	public String getSerialnumber();
	public void setSerialnumber(String serialnumber);
	public String getScheduledate();
	public void setScheduledate(String scheduledate);
	public String getPaiddate();
	public void setPaiddate(String paiddate);
	public boolean isPaid();
	public void setPaid(boolean paid);
	public String getPrincipaldue();
	public void setPrincipaldue(String principaldue);
	public String getInterestamount();
	public void setInterestamount(String interestamount);
	public String getFees();
	public void setFees(String fees);
	public String getPenalties();
	public void setPenalties(String penalties);
	public String getDueamount();
	public void setDueamount(String dueamount);
	public String getPaidamount();
	public void setPaidamount(String paidamount);
	public String getBalanceoutstandingamount();
	public void setBalanceoutstandingamount(String balanceoutstandingamount);
	String getSupervisor();
	void setSupervisor(String supervisor);
}
