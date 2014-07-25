package uk.ac.openmf.model;


/**
 * User entity interface.
 *
 */
public interface OpenMFUser extends OpenMFEntity {

	Long getId();
	String getUserId();

	String getUsername();

	String getEmail();

	String getForename();

	String getSurname() ;

	boolean isEnabled() ;

	//    Collection<AppRole> getAuthorities() ;

	String getMain_office() ;

	String getContact() ;

	String getPassword() ;

	String getAddress() ;

	String getSupervisor() ;

	String getRole() ;

	void setUsername(String username);

	void setEmail(String email);

	void setForename(String forename);

	void setSurname(String surname) ;

	void setEnabled(boolean enabled) ;

	//    void setAuthorities(Collection<AppRole> authorities) ;

	void setMain_office(String mainoffice) ;

	void setContact(String contact) ;

	void setPassword(String password) ;

	void setAddress(String address) ;

	void setSupervisor(String supervisor) ;

	void setRole(String role) ;

	long getTimestamp();

	void setTimestamp(long timestamp);

	String getCreatedById();

	void setCreatedById(String userId);

}
