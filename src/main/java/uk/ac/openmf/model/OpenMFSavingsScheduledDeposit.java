package uk.ac.openmf.model;


/**
 * OpenMFClient entity interface.
 *
 */
public interface OpenMFSavingsScheduledDeposit extends OpenMFEntity {

	Long getId();
	long getTimestamp();
	void setTimestamp(long timestamp);
	String getCreatedById();
	void setCreatedById(String userId);
	String getClientId();
	void setClientId(String clientId);
	String getSavingsaccountid();
	void setSavingsaccountid(String savingsaccountid);
	String getSerialnumber();
	void setSerialnumber(String serialnumber);
	String getScheduledate();
	void setScheduledate(String scheduledate);
	String getPaiddate();
	void setPaiddate(String paiddate);
	boolean isPaid();
	void setPaid(boolean paid);
	String getInterestamount();
	void setInterestamount(String interestamount);
	String getPaidamount();
	void setPaidamount(String paidamount);
	String getBalanceoutstandingamount();
	void setBalanceoutstandingamount(String balanceoutstandingamount);
	boolean isActive();
	void setActive(boolean active);
}
