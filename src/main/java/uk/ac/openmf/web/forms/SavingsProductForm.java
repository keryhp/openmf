package uk.ac.openmf.web.forms;


/**
 * @author harish
 */
public class SavingsProductForm {
	
	private String productname;
	private String savingscode;
	private String savingstype;
	private String description;
	private String startdate;
	private String closedate;
	private String rateofinterest;
	private String interestmethod;
	private String interestcalcperiod;
	private String tenure;
	private String depositfrequency;
	private String savingsamount;	
	private boolean active;
	
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getSavingscode() {
		return savingscode;
	}
	public void setSavingscode(String savingscode) {
		this.savingscode = savingscode;
	}
	public String getSavingstype() {
		return savingstype;
	}
	public void setSavingstype(String savingstype) {
		this.savingstype = savingstype;
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
	public String getTenure() {
		return tenure;
	}
	public void setTenure(String tenure) {
		this.tenure = tenure;
	}
	public String getDepositfrequency() {
		return depositfrequency;
	}
	public void setDepositfrequency(String depositfrequency) {
		this.depositfrequency = depositfrequency;
	}
	public String getSavingsamount() {
		return savingsamount;
	}
	public void setSavingsamount(String savingsamount) {
		this.savingsamount = savingsamount;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
}
