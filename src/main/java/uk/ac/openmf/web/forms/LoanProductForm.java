package uk.ac.openmf.web.forms;


/**
 * @author harish
 */
public class LoanProductForm {
	
	private String productname;
	private String loancode;
	private String description;
	private String startdate;
	private String closedate;
	private String rateofinterest;
	private String fund;
	private String amortization;
	private String interestmethod;
	private String interestcalcperiod;
	private String chargesapplicable;
	private String repaymentperiod;
	private String repaymentfrequency;
	private String loanamount;	
	private boolean active;
	
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getLoancode() {
		return loancode;
	}
	public void setLoancode(String loancode) {
		this.loancode = loancode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getClosedate() {
		return closedate;
	}
	public void setClosedate(String closedate) {
		this.closedate = closedate;
	}
	public String getRateofinterest() {
		return rateofinterest;
	}
	public void setRateofinterest(String rateofinterest) {
		this.rateofinterest = rateofinterest;
	}
	public String getFund() {
		return fund;
	}
	public void setFund(String fund) {
		this.fund = fund;
	}
	public String getAmortization() {
		return amortization;
	}
	public void setAmortization(String amortization) {
		this.amortization = amortization;
	}
	public String getInterestmethod() {
		return interestmethod;
	}
	public void setInterestmethod(String interestmethod) {
		this.interestmethod = interestmethod;
	}
	public String getInterestcalcperiod() {
		return interestcalcperiod;
	}
	public void setInterestcalcperiod(String interestcalcperiod) {
		this.interestcalcperiod = interestcalcperiod;
	}
	public String getChargesapplicable() {
		return chargesapplicable;
	}
	public void setChargesapplicable(String chargesapplicable) {
		this.chargesapplicable = chargesapplicable;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getRepaymentperiod() {
		return repaymentperiod;
	}
	public void setRepaymentperiod(String repaymentperiod) {
		this.repaymentperiod = repaymentperiod;
	}
	public String getRepaymentfrequency() {
		return repaymentfrequency;
	}
	public void setRepaymentfrequency(String repaymentfrequency) {
		this.repaymentfrequency = repaymentfrequency;
	}
	public String getLoanamount() {
		return loanamount;
	}
	public void setLoanamount(String loanamount) {
		this.loanamount = loanamount;
	}
}
