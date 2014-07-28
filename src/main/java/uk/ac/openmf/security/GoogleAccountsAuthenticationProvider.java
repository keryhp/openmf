package uk.ac.openmf.security;

import com.google.appengine.api.users.User;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import uk.ac.openmf.model.OpenMFUser;
import uk.ac.openmf.users.GaeUser;
import uk.ac.openmf.users.UserRegistry;
import uk.ac.openmf.web.AppContext;

/**
 * @author harish
 */
public class GoogleAccountsAuthenticationProvider implements AuthenticationProvider, MessageSourceAware {
    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    	User googleUser = (User) authentication.getPrincipal();
        OpenMFUser openMFUser = AppContext.getAppContext().getUserManager().getUserByEmail(googleUser.getEmail());
        if (openMFUser == null) {
            // User not a registered user. Needs to be registered by the institution
			openMFUser = AppContext.getAppContext().getUserManager().newUser(googleUser.getUserId());
			openMFUser.setEmail(googleUser.getEmail());
			openMFUser.setUsername(googleUser.getNickname());
			openMFUser.setRole(AppRole.USER.toString());
			openMFUser.setEnabled(true);
        }

        if (!openMFUser.isEnabled()) {
            throw new DisabledException("Account is disabled");
        }
        return new OpenMFUserAuthentication(openMFUser, authentication.getDetails());
    }

    /**
     * Indicate that this provider only supports PreAuthenticatedAuthenticationToken (sub)classes.
     */
    public final boolean supports(Class<?> authentication) {
        return PreAuthenticatedAuthenticationToken.class.isAssignableFrom(authentication);
    }


    public void setMessageSource(MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }
}
