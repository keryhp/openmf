package uk.ac.openmf.model;


/**
 * OpenMFLoanProduct entity interface.
 *
 */
public interface OpenMFLoanAccount extends OpenMFEntity {

	Long getId();
	String getLoancode();
	void setLoancode(String loancode);
	String getClientId();
	void setClientId(String clientId);
	String getLoanaccountnumber();
	void setLoanaccountnumber(String loanaccountnumber);
	String getDisbursedon();
	void setDisbursedon(String disbursedon);
	String getLoanpurpose();
	void setLoanpurpose(String loanpurpose);
	String getApprovedamount();
	void setApprovedamount(String approvedamount);
	String getDisbursedamount();
	void setDisbursedamount(String disbursedamount);
	String getArrearsby();
	void setArrearsby(String arrearsby);
	String getLoanstartdate();
	void setLoanstartdate(String loanstartdate);
	String getLoanclosedate();
	void setLoanclosedate(String loanclosedate);
	String getInterestcalcperiod();
	void setInterestcalcperiod(String interestcalcperiod);
	String getBalanceoutstandingamount();
	void setBalanceoutstandingamount(String balanceoutstandingamount);
	String getPrincipaldueamount();
	void setPrincipaldueamount(String principaldueamount);
	boolean isActive();
	void setActive(boolean active);
	String getTotalnumrepayments();
	void setTotalnumrepayments(String totalnumrepayments);
	String getNumrepaymentsmade();
	void setNumrepaymentsmade(String numrepaymentsmade);
	String getSubmittedon();
	void setSubmittedon(String submittedon);
	String getApprovedon();
	void setApprovedon(String approvedon);
	String getMatureson();
	void setMatureson(String matureson);
	String getApprovedby();
	void setApprovedby(String approvedby);
	String getFieldofficer();
	void setFieldofficer(String fieldofficer);
	String getLoanofficer();
	void setLoanofficer(String loanofficer);
	String getTotalrepaymentamount();
	void setTotalrepaymentamount(String totalrepaymentamount);
	String getInterestrepaymentamount();
	void setInterestrepaymentamount(String interestrepaymentamount);
	String getPenalties();
	void setPenalties(String penalties);
	String getFees();
	void setFees(String fees);
	long getTimestamp();
	void setTimestamp(long timestamp);
	String getCreatedById();
	void setCreatedById(String userId);
	String getNumpaymentsmissed();
	void setNumpaymentsmissed(String numpaymentsmissed);
	boolean isDefaulted();
	void setDefaulted(boolean defaulted);
}
