package uk.ac.openmf.security;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import uk.ac.openmf.model.OpenMFUser;

/**
 * Authentication object representing a fully-authenticated user.
 *
 * @author harish
 */
public class OpenMFUserAuthentication implements Authentication {
	
	private static final long serialVersionUID = 1L;
	private final OpenMFUser principal;
    private final Object details;
    private boolean authenticated;

    public OpenMFUserAuthentication(OpenMFUser principal, Object details) {
        this.principal = principal;
        this.details = details;
        authenticated = true;
    }

    public Collection<GrantedAuthority> getAuthorities() {
    	Collection<AppRole> role = new HashSet<AppRole>();
    	if(principal.getRole() == null)
    		principal.setRole(AppRole.NEW_USER.toString());
    	role.add(AppRole.valueOf(principal.getRole()));
        return new HashSet<GrantedAuthority>(role);
    }

    public Object getCredentials() {
        throw new UnsupportedOperationException();
    }

    public Object getDetails() {
        return null;
    }

    public Object getPrincipal() {
        return principal;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean isAuthenticated) {
        authenticated = isAuthenticated;
    }

    public String getName() {
        return principal.getUserId();
    }

	@Override
	public String toString() {
		return "OpenMFUserAuthentication [principal=" + principal
				+ ", details=" + details + ", authenticated=" + authenticated
				+ "]";
	}
}
