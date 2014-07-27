package uk.ac.openmf.model;


/**
 * OpenMFLoanProduct entity interface.
 *
 */
public interface OpenMFLoanProduct extends OpenMFEntity {

	Long getId();
	String getProductname();
	void setProductname(String productname);
	String getLoancode();
	void setLoancode(String loancode);
	String getDescription();
	void setDescription(String description);
	String getStartdate();
	void setStartdate(String startdate);
	String getClosedate() ;
	void setClosedate(String closedate) ;
	String getRateofinterest();
	void setRateofinterest(String rateofinterest);
	String getRepaymentperiod();
	void setRepaymentperiod(String repaymentperiod);
	String getRepaymentfrequency();
	void setRepaymentfrequency(String repaymentfrequency);	
	long getTimestamp();
	void setTimestamp(long timestamp);
	String getCreatedById();
	void setCreatedById(String userId);
	boolean isActive() ;
	void setActive(boolean active);
	String getLoanamount();
	void setLoanamount(String loanamount);
	public String getLoantype();
	public void setLoantype(String loantype);
}
