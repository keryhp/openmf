package uk.ac.openmf.model;

/**
 * The roles entity interface.
 *
 */
public interface OpenMFTask extends OpenMFEntity {
	
	Long getId();
	long getTimestamp();
	void setTimestamp(long timestamp);
	String getCreatedById();
	void setCreatedById(String userId);
	String getTaskId();
	void setTaskId(String taskId);
	String getDescription();
	void setDescription(String description);
	String getAssignedto();
	void setAssignedto(String assignedto);
	String getDateassigned();
	void setDateassigned(String dateassigned);
	boolean isStatus();
	void setStatus(boolean status);
	String getAmount();
	void setAmount(String amount);
	String getCashier();
	void setCashier(String cashier);
	String getAccountnumber();
	void setAccountnumber(String accountnumber);
	String getCollectiontype();
	void setCollectiontype(String collectiontype);
	boolean isNewclient();
	void setNewclient(boolean newclient);
	String getDatecompleted();
	void setDatecompleted(String datecompleted);

}
