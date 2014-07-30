package uk.ac.openmf.model;


/**
 * OpenMFClient entity interface.
 *
 */
public interface OpenMFLoanDisburse extends OpenMFEntity {

	Long getId();
	long getTimestamp();
	void setTimestamp(long timestamp);
	String getCreatedById();
	void setCreatedById(String userId);
	String getClientId();
	void setClientId(String clientId);
	String getLoanaccountid();
	void setLoanaccountid(String loanaccountid);
	String getTransactionreference();
	void setTransactionreference(String transactionreference);
	String getTransactionnote();
	void setTransactionnote(String transactionnote);
	String getPostedby();
	void setPostedby(String postedby);
	boolean isStatus();
	void setStatus(boolean status);
	String getDisbursedon();
	void setDisbursedon(String disbursedon);
	String getDisbursedamount();
	void setDisbursedamount(String disbursedamount);
}
