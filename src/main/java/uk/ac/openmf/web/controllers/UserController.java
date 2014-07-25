package uk.ac.openmf.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.ac.openmf.model.OpenMFUser;
import uk.ac.openmf.model.OpenMFUserManager;
import uk.ac.openmf.users.GaeUser;
import uk.ac.openmf.utils.ServletUtils;
import uk.ac.openmf.web.AppContext;
import uk.ac.openmf.web.forms.UserForm;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {


	@RequestMapping(value = "/users.htm", method= RequestMethod.GET)
	public String users() {
		return "users";
	}

	@RequestMapping(value = "/viewuser.htm", method= RequestMethod.GET)
	public String viewuser(HttpServletRequest req) {
		String omfuId = req.getParameter("omfuId");
		req.setAttribute("omfuId", omfuId);
		OpenMFUser omfuser = null;
		if (omfuId != null) {
			omfuser = AppContext.getAppContext().getUserManager().getUser(ServletUtils.validateEventId(omfuId));
		}
		req.setAttribute("omfuser", omfuser);
		return "viewuser";
	}

	@RequestMapping(value="/createuser.htm", method= RequestMethod.GET)
	public UserForm userForm() {
		return new UserForm();
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value="/createuser.htm", method = RequestMethod.POST)
	public String createuser(UserForm form, BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		GaeUser currentUser = (GaeUser)authentication.getPrincipal();
		boolean succeeded = false;
		if (currentUser != null) {
			AppContext appContext = AppContext.getAppContext();
			OpenMFUserManager userManager = appContext.getUserManager();
			OpenMFUser user = userManager.newUser(currentUser.getUserId());
			user.setUsername(form.getUsername());
			user.setCreatedById(currentUser.getEmail());
			user.setTimestamp(System.currentTimeMillis());
			user.setAddress(form.getAddress());
			user.setContact(form.getContact());
			user.setEnabled(form.isEnabled());
			user.setForename(form.getForename());
			user.setSurname(form.getSurname());
			user.setMain_office(form.getMainoffice());
			user.setPassword(form.getPassword());
			user.setRole(form.getRole());
			user.setSupervisor(form.getSupervisor());
			user.setEmail(form.getEmail());
			userManager.upsertEntity(user);
			succeeded = true;
			if (succeeded) {
				//redirect to new role created 
			} else {
				//redirect to error page
			}
			//return openMFRoles;
		} else {
			//return null;
		}
		return "redirect:/users.htm";
	}
}
