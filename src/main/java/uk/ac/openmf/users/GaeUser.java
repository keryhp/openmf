package uk.ac.openmf.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import uk.ac.openmf.security.AppRole;

/**
 * Custom user object for the application.
 *
 * @author harish
 */
public class GaeUser implements Serializable {
    private final String userId;
    private final String email;
    private final String nickname;
    private final String forename;
    private final String surname;
    private final String main_office;
    private final String contact;
    private final String password;
    /*can later change to address object*/
    private final String address;
    private final String supervisor;
    private final Set<AppRole> authorities;
    private final boolean enabled;

    /**
     * Pre-registration constructor.
     *
     * Assigns the user the "NEW_USER" role only.
     */
    public GaeUser(String userId, String nickname, String email) {
        this.userId = userId;
        this.nickname = nickname;
        this.authorities = EnumSet.of(AppRole.NEW_USER);
        this.forename = null;
        this.surname = null;
        this.email = email;
        this.enabled = true;
        this.main_office = null;
        this.contact = null;
        this.password = null;
        this.address = null;
        this.supervisor = null;
    }

    /**
     * Post-registration constructor
     */
    public GaeUser(String userId, String nickname, String email, String forename, String surname, Set<AppRole> authorities, boolean enabled) {
        this.userId = userId;
        this.nickname = nickname;
        this.email = email;
        this.authorities = authorities;
        this.forename = forename;
        this.surname = surname;
        this.enabled= enabled;
        this.main_office = null;
        this.contact = null;
        this.password = null;
        this.address = null;
        this.supervisor = null;
    }

    public String getUserId() {
        return userId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Collection<AppRole> getAuthorities() {
        return authorities;
    }
    
    public String getMain_office() {
		return main_office;
	}

	public String getContact() {
		return contact;
	}

	public String getPassword() {
		return password;
	}

	public String getAddress() {
		return address;
	}

	public String getSupervisor() {
		return supervisor;
	}

	@Override
	public String toString() {
		return "GaeUser [userId=" + userId + ", email=" + email + ", nickname="
				+ nickname + ", forename=" + forename + ", surname=" + surname
				+ ", main_office=" + main_office + ", contact=" + contact
				+ ", password=" + password + ", address=" + address + ", supervisor=" + supervisor
				+ ", authorities=" + authorities + ", enabled=" + enabled + "]";
	}

}
