package uk.ac.openmf.model;


/**
 * OpenMFLoanProduct entity interface.
 *
 */
public interface OpenMFSavingsProduct extends OpenMFEntity {

	Long getId();
	String getProductname();
	void setProductname(String productname);
	String getSavingscode();
	void setSavingscode(String savingscode);
	String getDescription();
	void setDescription(String description);
	String getStartdate();
	void setStartdate(String startdate);
	String getClosedate() ;
	void setClosedate(String closedate) ;
	String getRateofinterest();
	void setRateofinterest(String rateofinterest);
	String getTenure();
	void setTenure(String tenure);
	String getDepositfrequency();
	void setDepositfrequency(String depositfrequency);	
	long getTimestamp();
	void setTimestamp(long timestamp);
	String getCreatedById();
	void setCreatedById(String userId);
	boolean isActive() ;
	void setActive(boolean active);
	String getSavingsamount();
	void setSavingsamount(String savingsamount);
	String getSavingstype();
	void setSavingstype(String savingstype);
}
