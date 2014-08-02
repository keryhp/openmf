package uk.ac.openmf.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.ac.openmf.model.OpenMFRoles;
import uk.ac.openmf.model.OpenMFRolesManager;
import uk.ac.openmf.model.nosql.OpenMFUserNoSql;
import uk.ac.openmf.users.GaeUser;
import uk.ac.openmf.utils.OMFUtils;
import uk.ac.openmf.web.AppContext;
import uk.ac.openmf.web.forms.RolesForm;

/**
 * Handles requests for the application home page.
 */
@Controller
public class RolesController {
	
	@RequestMapping(value = "/assignroles.htm", method= RequestMethod.GET)
    public String assignroles(HttpServletRequest req) {
		req.setAttribute("currentUser", AppContext.getAppContext().getCurrentUser());
		req.setAttribute("roles", OMFUtils.getAllRolesList());
        return "assignroles";
    }
	
	@RequestMapping(value="/createrole.htm", method= RequestMethod.GET)
    public RolesForm rolesForm(HttpServletRequest req) {
		req.setAttribute("currentUser", AppContext.getAppContext().getCurrentUser());
		req.setAttribute("roles", OMFUtils.getAllRolesList());
        return new RolesForm();
    }
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value="/createrole.htm", method = RequestMethod.POST)
	public String createrole(RolesForm form, BindingResult result) {
		if (result.hasErrors()) {
            return null;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OpenMFUserNoSql currentUser = (OpenMFUserNoSql)authentication.getPrincipal();
		boolean succeeded = false;
		if (currentUser != null) {
			AppContext appContext = AppContext.getAppContext();
			OpenMFRolesManager rolesManager = appContext.getRolesManager();
				OpenMFRoles role = rolesManager.newRole(currentUser.getId().toString());
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
