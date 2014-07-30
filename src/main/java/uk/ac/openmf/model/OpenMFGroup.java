package uk.ac.openmf.model;


/**
 * OpenMFClient entity interface.
 *
 */
public interface OpenMFGroup extends OpenMFEntity {

	Long getId();
	long getTimestamp();
	void setTimestamp(long timestamp);
	String getCreatedById();
	void setCreatedById(String userId);
	String getOffice();
	void setOffice(String office);
	String getContact();
	void setContact(String contact);
	String getExternalId();
	void setExternalId(String externalId);
	String getSupervisor();
	void setSupervisor(String supervisor);
	String getActivationdate();
	void setActivationdate(String activationdate);
	String getSubmittedon();
	void setSubmittedon(String submittedon);
	boolean isActive();
	void setActive(boolean active);
	String getAccountnumber();
	void setAccountnumber(String accountnumber);
	String getAddress();
	void setAddress(String address);
	boolean isEligible();
	void setEligible(boolean eligible);
	boolean isBlacklisted();
	void setBlacklisted(boolean blacklisted);
	String getGroupname();
	void setGroupname(String groupname);
	String getLeadclientname();
	void setLeadclientname(String leadclientname);
}
