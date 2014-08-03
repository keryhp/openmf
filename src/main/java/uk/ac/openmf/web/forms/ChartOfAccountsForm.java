package uk.ac.openmf.web.forms;


/**
 * @author harish
 */
public class ChartOfAccountsForm {
	
    private String coaid;
    private String coaname;
    private String funds;    
    private String mfiaccounttype;
    private String office;
	
    public String getCoaid() {
		return coaid;
	}
	public void setCoaid(String coaid) {
		this.coaid = coaid;
	}
	public String getMfiaccounttype() {
		return mfiaccounttype;
	}
	public void setMfiaccounttype(String mfiaccounttype) {
		this.mfiaccounttype = mfiaccounttype;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public String getCoaname() {
		return coaname;
	}
	public void setCoaname(String coaname) {
		this.coaname = coaname;
	}
	public String getFunds() {
		return funds;
	}
	public void setFunds(String funds) {
		this.funds = funds;
	}
}
