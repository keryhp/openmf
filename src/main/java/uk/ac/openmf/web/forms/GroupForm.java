package uk.ac.openmf.web.forms;


/**
 * @author harish
 */
public class GroupForm {
	
    private String office;
    private String groupname;
    private String leadclientname;
    private String contact;
    private String externalId;
    private String supervisor;
    private boolean active;
    private String activationdate;
    private String submittedon;
    private String accountnumber;
    private String address;
    private boolean eligible;
    private boolean blacklisted;
    
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public String getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	public String getActivationdate() {
		return activationdate;
	}
	public void setActivationdate(String activationdate) {
		this.activationdate = activationdate;
	}
	public String getSubmittedon() {
		return submittedon;
	}
	public void setSubmittedon(String submittedon) {
		this.submittedon = submittedon;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isEligible() {
		return eligible;
	}
	public void setEligible(boolean eligible) {
		this.eligible = eligible;
	}
	public boolean isBlacklisted() {
		return blacklisted;
	}
	public void setBlacklisted(boolean blacklisted) {
		this.blacklisted = blacklisted;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getLeadclientname() {
		return leadclientname;
	}
	public void setLeadclientname(String leadclientname) {
		this.leadclientname = leadclientname;
	}
}
