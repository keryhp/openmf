package uk.ac.openmf.model;

/**
 * The roles entity interface.
 *
 */
public interface OpenMFChartOfAccounts extends OpenMFEntity {
	Long getId();
	long getTimestamp();
	void setTimestamp(long timestamp);
	String getCreatedById();
	void setCreatedById(String userId);
	String getCoaid();
	void setCoaid(String coaid);
	String getMfiaccounttype();
	void setMfiaccounttype(String mfiaccounttype);
	String getOffice();
	void setOffice(String office);
	String getCoaname();
	void setCoaname(String coaname);	
	String getFunds();
	void setFunds(String funds);
}
