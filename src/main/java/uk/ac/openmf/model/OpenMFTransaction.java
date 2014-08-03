package uk.ac.openmf.model;

/**
 * The roles entity interface.
 *
 */
public interface OpenMFTransaction extends OpenMFEntity {
	Long getId();
	long getTimestamp();
	void setTimestamp(long timestamp);
	String getCreatedById();
	void setCreatedById(String userId);
	String getTransactionid();
	void setTransactionid(String transactionid);
	String getFromaccountid();
	void setFromaccountid(String fromaccountid);
	String getToaccountid();
	void setToaccountid(String toaccountid);
	String getDateoftransaction();
	void setDateoftransaction(String dateoftransaction);
	String getProductid();
	void setProductid(String productid);
	boolean isStatus();
	void setStatus(boolean status);
	String getPostedby();
	void setPostedby(String postedby);
	String getApprovedby();
	void setApprovedby(String approvedby);
	String getTransactiontype();
	void setTransactiontype(String transactiontype);
	String getClientId();
	void setClientId(String clientId);
}
