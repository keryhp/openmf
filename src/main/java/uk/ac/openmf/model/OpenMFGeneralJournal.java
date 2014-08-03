package uk.ac.openmf.model;

/**
 * The roles entity interface.
 *
 */
public interface OpenMFGeneralJournal extends OpenMFEntity {
	Long getId();
	long getTimestamp();
	void setTimestamp(long timestamp);
	String getCreatedById();
	void setCreatedById(String userId);
	String getGeneraljournalid();
	void setGeneraljournalid(String generaljournalid);
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
	String getTransactionamount();
	void setTransactionamount(String transactionamount);
}
