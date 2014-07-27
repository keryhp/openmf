package uk.ac.openmf.web.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.ac.openmf.model.OpenMFClient;
import uk.ac.openmf.model.OpenMFClientManager;
import uk.ac.openmf.model.OpenMFLoanAccount;
import uk.ac.openmf.users.GaeUser;
import uk.ac.openmf.utils.GenerateAccountNumber;
import uk.ac.openmf.utils.OpenMFConstants;
import uk.ac.openmf.utils.ServletUtils;
import uk.ac.openmf.web.AppContext;
import uk.ac.openmf.web.forms.ClientForm;

import com.google.appengine.api.datastore.DatastoreNeedIndexException;

/**
 * @author harish
 */
@Controller
public class ClientController {

	@RequestMapping(value = "/clients.htm", method= RequestMethod.GET)
	public String clients() {
		return "clients";
	}

	@RequestMapping(value = "/viewclient.htm", method= RequestMethod.GET)
	public String viewClient(HttpServletRequest req) {
		String clientId = req.getParameter("clientId");
		req.setAttribute("eventId", clientId);
		OpenMFClient client = null;
		if (clientId != null) {
			client = AppContext.getAppContext().getClientManager().getClient(ServletUtils.validateEventId(clientId));
		}
		req.setAttribute("client", client);    
		
		Iterable<OpenMFLoanAccount> loanAccountiter = AppContext.getAppContext().getLoanAccountManager().getAllLoanAccountsByClient(clientId);
		ArrayList<OpenMFLoanAccount> loanAccounts = new ArrayList<OpenMFLoanAccount>();
		try {
			for (OpenMFLoanAccount loanAccount : loanAccountiter) {
				loanAccounts.add(loanAccount);
			}
		} catch (DatastoreNeedIndexException e) {
			//log the error
		}
		req.setAttribute("loanAccounts", loanAccounts);
		
		return "viewclient";
	}

	@RequestMapping(value="/createclient.htm", method= RequestMethod.GET)
	public ClientForm clientForm() {
		return new ClientForm();
	}

	@RequestMapping(value="/createclient.htm", method = RequestMethod.POST)
	public String createclient(ClientForm form, BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		GaeUser currentUser = (GaeUser)authentication.getPrincipal();
		boolean succeeded = false;
		if (currentUser != null) {
			AppContext appContext = AppContext.getAppContext();
			OpenMFClientManager clientManager = appContext.getClientManager();
			OpenMFClient client = clientManager.newClient(currentUser.getUserId());
			client.setCreatedById(currentUser.getNickname());
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
			clientManager.upsertEntity(client);
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

		return "redirect:/clients.htm";
	}
}
