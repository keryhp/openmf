package uk.ac.openmf.model;


/**
 * OpenMFLoanProduct entity interface.
 *
 */
public interface OpenMFSavingsAccount extends OpenMFEntity {

	Long getId();
	long getTimestamp();
	void setTimestamp(long timestamp);
	String getCreatedById();
	void setCreatedById(String userId);
	String getClientId();
	void setClientId(String clientId);
	String getSavingscode();
	void setSavingscode(String savingscode);
	String getSavingsaccountnumber();
	void setSavingsaccountnumber(String savingsaccountnumber);
	String getSavingspurpose();
	void setSavingspurpose(String savingspurpose);
	String getSavingsstartdate();
	void setSavingsstartdate(String savingsstartdate);
	String getSavingsclosedate();
	void setSavingsclosedate(String savingsclosedate);
	String getInterestcalcperiod();
	void setInterestcalcperiod(String interestcalcperiod);
	boolean isActive();
	void setActive(boolean active);
	String getTotalnumdeposits();
	void setTotalnumdeposits(String totalnumdeposits);
	String getSubmittedon();
	void setSubmittedon(String submittedon);
	String getApprovedon();
	void setApprovedon(String approvedon);
	String getMatureson();
	void setMatureson(String matureson);
	String getApprovedby();
	void setApprovedby(String approvedby);
	String getSavingsofficer();
	void setSavingsofficer(String savingsofficer);
	String getTotalprincipaldeposit();
	void setTotalprincipaldeposit(String totalprincipaldeposit);
	String getTotalinterestgain();
	void setTotalinterestgain(String totalinterestgain);
	String getAvailablebalance();
	void setAvailablebalance(String availablebalance);
	boolean isScheduledepositmissed();
	void setScheduledepositmissed(boolean scheduledepositmissed);
}
