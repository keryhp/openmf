package uk.ac.openmf.web.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.ac.openmf.model.OpenMFRoles;
import uk.ac.openmf.model.OpenMFRolesManager;
import uk.ac.openmf.users.GaeUser;
import uk.ac.openmf.web.AppContext;
import uk.ac.openmf.web.forms.RolesForm;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value="/createrole.htm")
public class RolesController {
	
	@RequestMapping(method= RequestMethod.GET)
    public RolesForm rolesForm() {
        return new RolesForm();
    }

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String createrole(RolesForm form, BindingResult result) {
		if (result.hasErrors()) {
            return null;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        GaeUser currentUser = (GaeUser)authentication.getPrincipal();
		boolean succeeded = false;
		if (currentUser != null) {
			AppContext appContext = AppContext.getAppContext();
			OpenMFRolesManager rolesManager = appContext.getRolesManager();
				OpenMFRoles role = rolesManager.newRole(currentUser.getUserId());
				role.setRoleId(form.getRoleId());
				role.setCreatedById(currentUser.getEmail());
				role.setTimestamp(System.currentTimeMillis());
				role.setDescription(form.getDescription());
				rolesManager.upsertEntity(role);
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
		return "redirect:/assignroles.htm";
	}
}
