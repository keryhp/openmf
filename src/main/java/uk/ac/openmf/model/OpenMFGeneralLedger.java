package uk.ac.openmf.model;

/**
 * The roles entity interface.
 *
 */
public interface OpenMFGeneralLedger extends OpenMFEntity {
	Long getId();
	long getTimestamp();
	void setTimestamp(long timestamp);
	String getCreatedById();
	void setCreatedById(String userId);
	String getCoaid();
	void setCoaid(String coaid);
	String getTransactiontype();
	void setTransactiontype(String transactiontype);
	String getClientaccountid();
	void setClientaccountid(String clientaccountid);
	String getPostedby();
	void setPostedby(String postedby);
	String getApprovedby();
	void setApprovedby(String approvedby);
	boolean getStatus();
	void setStatus(boolean status);
	String getGeneralledgerid();
	void setGeneralledgerid(String generalledgerid);
	String getBalanceavailable();
	void setBalanceavailable(String balanceavailable);
	String getBalancePending();
	void setBalancePending(String balancePending);
	String getForDate();
	void setForDate(String fordate);
}
