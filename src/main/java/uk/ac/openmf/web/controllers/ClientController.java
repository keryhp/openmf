package uk.ac.openmf.web.controllers;

import java.util.ArrayList;
import java.util.logging.Logger;

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
import uk.ac.openmf.model.OpenMFLoanAccount;
import uk.ac.openmf.model.OpenMFSavingsAccount;
import uk.ac.openmf.model.nosql.OpenMFUserNoSql;
import uk.ac.openmf.utils.GenerateAccountNumber;
import uk.ac.openmf.utils.OMFUtils;
import uk.ac.openmf.utils.OpenMFConstants;
import uk.ac.openmf.utils.ServletUtils;
import uk.ac.openmf.web.AppContext;
import uk.ac.openmf.web.forms.ClientForm;

/**
 * @author harish
 */
@Controller
public class ClientController {

	protected static final Logger logger =
			Logger.getLogger(ClientController.class.getCanonicalName());	
	
	@RequestMapping(value = "/clients.htm", method= RequestMethod.GET)
	public String clients(HttpServletRequest req) {
		req.setAttribute("currentUser", AppContext.getAppContext().getCurrentUser());
		req.setAttribute("clients", OMFUtils.getAllClientsList());
		return "clients";
	}

	@RequestMapping(value = "/viewclient.htm", method= RequestMethod.GET)
	public String viewClient(HttpServletRequest req) {
		req.setAttribute("currentUser", AppContext.getAppContext().getCurrentUser());
		String clientId = req.getParameter("clientId");
		req.setAttribute("clientId", clientId);
		OpenMFClient client = null;
		if (clientId != null) {
			client = AppContext.getAppContext().getClientManager().getClient(ServletUtils.validateEventId(clientId));
		}
		req.setAttribute("client", client);
		OpenMFGroup group = null;
		if (client.getGroupid() != null) {
			group = AppContext.getAppContext().getGroupManager().getGroup(ServletUtils.validateEventId(client.getGroupid()));
			req.setAttribute("group", group);
		}
		ArrayList<OpenMFLoanAccount> loanAccounts = OMFUtils.getLoanAccountsByClientList(clientId);
		ArrayList<OpenMFSavingsAccount> savingsAccounts = OMFUtils.getSavingsAccountsByClientList(clientId);
		req.setAttribute("loanAccounts", loanAccounts);
		req.setAttribute("savingsAccounts", savingsAccounts);
		req.setAttribute("transactions", OMFUtils.getTransactionByClientId(clientId));
		logger.info("clientId" + clientId + " loanAccounts"  + loanAccounts + " savingsAccounts" + savingsAccounts);
		return "viewclient";
	}

	@RequestMapping(value="/createclient.htm", method= RequestMethod.GET)
	public ClientForm clientForm(HttpServletRequest req) {
		req.setAttribute("currentUser", AppContext.getAppContext().getCurrentUser());
		req.setAttribute("omfusers", OMFUtils.getUsersList());
		req.setAttribute("groups", OMFUtils.getAllGroupsList());
		return new ClientForm();
	}

	@RequestMapping(value="/createclient.htm", method = RequestMethod.POST)
	public String createclient(ClientForm form, BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		OpenMFUserNoSql currentUser = (OpenMFUserNoSql)authentication.getPrincipal();
		if (currentUser != null) {
			logger.info("User details:" + currentUser.toString());
			AppContext appContext = AppContext.getAppContext();
			OpenMFClientManager clientManager = appContext.getClientManager();
			OpenMFClient client = clientManager.newClient(currentUser.getId().toString());
			client.setCreatedById(currentUser.getUsername());
			client.setTimestamp(System.currentTimeMillis());
			client.setActive(form.isActive());		
			client.setEligible(form.isEligible());		
			client.setBlacklisted(false);					
			client.setActivationdate(form.getActivationdate());
			client.setClientclassification(form.getClientclassification());
			client.setClienttype(form.getClienttype());
			client.setContact(form.getContact());
			client.setDateofbirth(form.getDateofbirth());
			client.setExternalId(form.getExternalId());
			client.setForename(form.getForename());
			client.setGender(form.getGender());
			client.setMidname(form.getMidname());
			client.setOffice(form.getOffice());
			client.setSubmittedon(form.getSubmittedon());
			client.setSupervisor(form.getSupervisor());
			client.setSurname(form.getSurname());
			client.setAccountNumber(GenerateAccountNumber.getAccNumberService().generateClientAccNumber(clientManager.entityCount() + 1));
			client.setAddress(form.getAddress());
			client.setBalance(OpenMFConstants.FIELD_VALUE_ZERO);
			if("group".equalsIgnoreCase(form.getClienttype()))
				client.setGroupid(form.getGroupid());
			clientManager.upsertEntity(client);
		}
		return "redirect:/clients.htm";
	}
}
