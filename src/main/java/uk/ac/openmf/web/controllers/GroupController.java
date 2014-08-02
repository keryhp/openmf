package uk.ac.openmf.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.ac.openmf.model.OpenMFClient;
import uk.ac.openmf.model.OpenMFClientManager;
import uk.ac.openmf.model.OpenMFGroup;
import uk.ac.openmf.model.OpenMFGroupManager;
import uk.ac.openmf.model.nosql.OpenMFUserNoSql;
import uk.ac.openmf.utils.GenerateAccountNumber;
import uk.ac.openmf.utils.OMFUtils;
import uk.ac.openmf.utils.ServletUtils;
import uk.ac.openmf.web.AppContext;
import uk.ac.openmf.web.forms.AssignGroupForm;
import uk.ac.openmf.web.forms.GroupForm;

/**
 * @author harish
 */
@Controller
public class GroupController {

	@RequestMapping(value = "/groups.htm", method= RequestMethod.GET)
	public String groups(HttpServletRequest req) {
		req.setAttribute("currentUser", AppContext.getAppContext().getCurrentUser());
		req.setAttribute("groups", OMFUtils.getAllGroupsList());
		return "groups";
	}

	@RequestMapping(value = "/viewgroup.htm", method= RequestMethod.GET)
	public String viewGroup(HttpServletRequest req) {
		req.setAttribute("currentUser", AppContext.getAppContext().getCurrentUser());
		String groupId = req.getParameter("groupId");
		req.setAttribute("groupId", groupId);
		OpenMFGroup group = null;
		if (groupId != null) {
			group = AppContext.getAppContext().getGroupManager().getGroup(ServletUtils.validateEventId(groupId));
		}
		req.setAttribute("group", group);    
		req.setAttribute("clients", OMFUtils.getClientsByGroupId(groupId));  
		req.setAttribute("loanAccounts", OMFUtils.getLoanAccountsByGroupList(groupId));
		return "viewgroup";
	}

	@RequestMapping(value="/creategroup.htm", method= RequestMethod.GET)
	public GroupForm groupForm(HttpServletRequest req) {
		req.setAttribute("currentUser", AppContext.getAppContext().getCurrentUser());
		req.setAttribute("omfusers", OMFUtils.getUsersList());
		return new GroupForm();
	}

	@RequestMapping(value="/creategroup.htm", method = RequestMethod.POST)
	public String creategroup(GroupForm form, BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		OpenMFUserNoSql currentUser = (OpenMFUserNoSql)authentication.getPrincipal();
		if (currentUser != null) {
			AppContext appContext = AppContext.getAppContext();
			OpenMFGroupManager groupManager = appContext.getGroupManager();
			OpenMFGroup group = groupManager.newGroup(currentUser.getId().toString());
			group.setCreatedById(currentUser.getUsername());
			group.setTimestamp(System.currentTimeMillis());
			group.setActive(form.isActive());		
			group.setEligible(form.isEligible());		
			group.setBlacklisted(false);					
			group.setActivationdate(form.getActivationdate());
			group.setContact(form.getContact());
			group.setExternalId(form.getExternalId());
			group.setOffice(form.getOffice());
			group.setSubmittedon(form.getSubmittedon());
			group.setSupervisor(form.getSupervisor());
			group.setGroupname(form.getGroupname());
			group.setAccountnumber(GenerateAccountNumber.getAccNumberService().generateGroupAccNumber(groupManager.entityCount() + 1));
			group.setAddress(form.getAddress());
			group.setLeadclientname(form.getLeadclientname());
			groupManager.upsertEntity(group);
		} 
		return "redirect:/groups.htm";
	}

	@RequestMapping(value = "/assigngroup.htm", method= RequestMethod.GET)
	public AssignGroupForm assignGroup(HttpServletRequest req) {
		req.setAttribute("currentUser", AppContext.getAppContext().getCurrentUser());
		String clientId = req.getParameter("clientId");
		req.setAttribute("clientId", clientId);
		OpenMFClient client = null;
		if (clientId != null) {
			client = AppContext.getAppContext().getClientManager().getClient(ServletUtils.validateEventId(clientId));
		}
		req.setAttribute("client", client);    
		req.setAttribute("groups", OMFUtils.getAllGroupsList());
		return new AssignGroupForm();
	}
	
	@RequestMapping(value="/assigngroup.htm", method = RequestMethod.POST)
	public String assigngroup(AssignGroupForm form, BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		OpenMFUserNoSql currentUser = (OpenMFUserNoSql)authentication.getPrincipal();
		if (currentUser != null) {
			AppContext appContext = AppContext.getAppContext();
			OpenMFClientManager clientManager = appContext.getClientManager();
			OpenMFClient client = clientManager.getClient(ServletUtils.validateEventId(form.getClientId()));
			client.setGroupid(form.getGroupid());
			clientManager.upsertEntity(client);
		} 
		return "redirect:/groups.htm";
	}
}
