package uk.ac.openmf.model;


/**
 * OpenMFClient entity interface.
 *
 */
public interface OpenMFSavingsDeposit extends OpenMFEntity {

	Long getId();
	long getTimestamp();
	void setTimestamp(long timestamp);
	String getCreatedById();
	void setCreatedById(String userId);
	public String getClientId();
	public void setClientId(String clientId);
	public String getSavingsaccountid();
	public void setSavingsaccountid(String loanaccountid);
	public String getDateofpayment();
	public void setDateofpayment(String dateofpayment);
	public String getTransactionreference();
	public void setTransactionreference(String transactionreference);
	public String getTransactionnote();
	public void setTransactionnote(String transactionnote);
	public String getPostedby();
	public void setPostedby(String postedby);
	public String getAmountpaid();
	public void setAmountpaid(String amountpaid);
	public boolean isStatus();
	public void setStatus(boolean status);
}
