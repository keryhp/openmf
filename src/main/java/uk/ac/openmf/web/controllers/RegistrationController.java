package uk.ac.openmf.web.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.EnumSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.ac.openmf.model.OpenMFUser;
import uk.ac.openmf.security.AppRole;
import uk.ac.openmf.security.OpenMFUserAuthentication;
import uk.ac.openmf.users.GaeUser;
import uk.ac.openmf.users.UserRegistry;
import uk.ac.openmf.utils.PasswordHash;
import uk.ac.openmf.web.AppContext;
import uk.ac.openmf.web.forms.RegistrationForm;

import com.google.appengine.api.users.UserServiceFactory;

/**
 * @author harish
 */
@Controller
@RequestMapping(value="/register.htm")
public class RegistrationController {

	@Autowired
	private UserRegistry registry;

	@RequestMapping(method= RequestMethod.GET)
	public RegistrationForm registrationForm() {
		return new RegistrationForm();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String register(@Valid RegistrationForm form, BindingResult result) throws NoSuchAlgorithmException, InvalidKeySpecException {
		if (result.hasErrors()) {
			return null;
		}

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//GaeUser currentUser = (GaeUser)authentication.getPrincipal();
		OpenMFUser currentUser = (OpenMFUser)authentication.getPrincipal();
		Set<AppRole> roles = EnumSet.of(AppRole.USER);

		if (UserServiceFactory.getUserService().isUserAdmin()) {
			roles.add(AppRole.ADMIN);
		}

		GaeUser user = new GaeUser(currentUser.getUserId(), currentUser.getUsername(), currentUser.getEmail(),
				form.getUsername(), form.getPassword(), roles, true);

		OpenMFUser openMFUser = AppContext.getAppContext().getUserManager().getUserByUsername(form.getUsername());
		//if((openMFUser == null && !("test".equalsIgnoreCase(form.getUsername()))) || ((openMFUser != null && !openMFUser.getPassword().equals(form.getPassword())))){
		if((openMFUser == null && !("test".equalsIgnoreCase(form.getUsername()))) || ((openMFUser != null && !PasswordHash.validatePassword(form.getPassword(), openMFUser.getPassword())))){
			registry.registerUser(user);
			return "redirect:/register.htm";
		}else{
			// Update the context with the full authentication
			SecurityContextHolder.getContext().setAuthentication(new OpenMFUserAuthentication(openMFUser, authentication.getDetails()));
			return "redirect:/";
		}
	}
}
