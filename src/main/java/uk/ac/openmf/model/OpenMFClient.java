package uk.ac.openmf.model;


/**
 * OpenMFClient entity interface.
 *
 */
public interface OpenMFClient extends OpenMFEntity {

	Long getId();
	String getOffice();
	void setOffice(String office);
	String getForename();
	void setForename(String forename);
	String getMidname();
	void setMidname(String midname);
	String getSurname();
	void setSurname(String surname) ;
	String getContact() ;
	void setContact(String contact);
	String getDateofbirth() ;
	void setDateofbirth(String dateofbirth) ;
	String getClienttype();
	void setClienttype(String clienttype) ;
	String getExternalId() ;
	void setExternalId(String externalId);
	String getSupervisor() ;
	void setSupervisor(String supervisor);
	String getGender();
	void setGender(String gender) ;
	String getClientclassification() ;
	void setClientclassification(String clientclassification);
	String getActivationdate() ;
	void setActivationdate(String activationdate) ;
	String getSubmittedon();
	void setSubmittedon(String submittedon); 
	long getTimestamp();
	void setTimestamp(long timestamp);
	String getCreatedById();
	void setCreatedById(String userId);
	boolean isActive() ;
	void setActive(boolean active);
	String getAccountNumber();
	void setAccountNumber(String accountnumber);
	String getAddress();
	void setAddress(String address);
	boolean isEligible();
	void setEligible(boolean eligible);
	boolean isBlacklisted();
	void setBlacklisted(boolean blacklisted);
	String getBalance();
	void setBalance(String balance);
}
