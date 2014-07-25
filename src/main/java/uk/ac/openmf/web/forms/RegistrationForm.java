package uk.ac.openmf.web.forms;

import uk.ac.openmf.validation.Password;
import uk.ac.openmf.validation.Username;

/**
 * @author harish
 */
public class RegistrationForm {
    @Username
    private String username;
    @Password
    private String password;

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

}
