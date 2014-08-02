package uk.ac.openmf.web.controllers;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.ac.openmf.model.OpenMFClient;
import uk.ac.openmf.model.OpenMFSavingsAccount;
import uk.ac.openmf.model.OpenMFSavingsAccountManager;
import uk.ac.openmf.model.OpenMFSavingsProduct;
import uk.ac.openmf.model.nosql.OpenMFUserNoSql;
import uk.ac.openmf.services.DepositService;
import uk.ac.openmf.utils.GenerateAccountNumber;
import uk.ac.openmf.utils.OMFUtils;
import uk.ac.openmf.utils.ServletUtils;
import uk.ac.openmf.web.AppContext;
import uk.ac.openmf.web.forms.SavingsAccountForm;

/**
 * @author harish
 */
@Controller
public class SavingsAccountController {

	@RequestMapping(value = "/viewsavingsaccount.htm", method= RequestMethod.GET)
	public String savingsaccountdetails(HttpServletRequest req) {
		req.setAttribute("currentUser", AppContext.getAppContext().getCurrentUser());
		String sgaccId = req.getParameter("sgaccId");
		req.setAttribute("sgaccId", sgaccId);
		OpenMFSavingsAccount sgaccdetails = null;
		if (sgaccId != null) {
			sgaccdetails = AppContext.getAppContext().getSavingsAccountManager().getSavingsAccount(ServletUtils.validateEventId(sgaccId));
		}
		req.setAttribute("sgaccdetails", sgaccdetails);
		
		OpenMFClient client = AppContext.getAppContext().getClientManager().getClient(new Long(sgaccdetails.getClientId()));
		req.setAttribute("client", client);
		
		OpenMFSavingsProduct savingsproduct = AppContext.getAppContext().getSavingsProductManager().getSavingsProductBySavingsCode(sgaccdetails.getSavingscode());
		req.setAttribute("savingsproduct", savingsproduct);
		req.setAttribute("depositschedules", OMFUtils.getSavingsScheduledDepositForSavingsAccountList(sgaccdetails.getId().toString()));
		return "viewsavingsaccount";
	}

	@RequestMapping(value = "/savingsaccounts.htm", method= RequestMethod.GET)
	public String savingsaccounts(HttpServletRequest req) {
		req.setAttribute("currentUser", AppContext.getAppContext().getCurrentUser());
		return "savingsaccounts";
	}

	@RequestMapping(value="/createsavingsaccount.htm", method= RequestMethod.GET)
	public SavingsAccountForm savingsAccountForm(HttpServletRequest req) {
		req.setAttribute("currentUser", AppContext.getAppContext().getCurrentUser());
		String clientId = req.getParameter("clientId");
		req.setAttribute("clientId", clientId);
		req.setAttribute("savingsProducts", OMFUtils.getSavingsProductsList());
		req.setAttribute("omfusers", OMFUtils.getUsersList());
		SavingsAccountForm form  = new SavingsAccountForm();
		form.setClientId(clientId);
		return form;
	}

	@RequestMapping(value="/createsavingsaccount.htm", method = RequestMethod.POST)
	public String createsavingsaccount(SavingsAccountForm form, BindingResult result) throws ParseException {
		if (result.hasErrors()) {
			return null;
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		OpenMFUserNoSql currentUser = (OpenMFUserNoSql)authentication.getPrincipal();
		long sgaccId = 0;
		if (currentUser != null) {
			AppContext appContext = AppContext.getAppContext();
			OpenMFSavingsAccountManager savingsAccountManager = appContext.getSavingsAccountManager();
			OpenMFSavingsAccount savingsAccount = savingsAccountManager.newSavingsAccount(currentUser.getId().toString());
			savingsAccount.setCreatedById(currentUser.getUsername());
			savingsAccount.setTimestamp(System.currentTimeMillis());
			savingsAccount.setActive(form.isActive());
			savingsAccount.setApprovedby(form.getApprovedby());
			savingsAccount.setApprovedon(form.getApprovedon());
			savingsAccount.setClientId(form.getClientId());
			savingsAccount.setInterestcalcperiod(form.getInterestcalcperiod());
			savingsAccount.setSavingsaccountnumber(GenerateAccountNumber.getAccNumberService().generateSavingsAccNumber(savingsAccountManager.entityCount() + 1));
			savingsAccount.setSavingsclosedate(form.getSavingsclosedate());
			savingsAccount.setSavingscode(form.getSavingscode());
			savingsAccount.setSavingsofficer(form.getSavingsofficer());
			savingsAccount.setSavingspurpose(form.getSavingspurpose());
			savingsAccount.setSavingsstartdate(form.getSavingsstartdate());
			savingsAccount.setMatureson(form.getMatureson());
			savingsAccount.setSubmittedon(form.getSubmittedon());
			savingsAccount.setTotalinterestgain(form.getTotalinterestgain());
			if(form.getTotalprincipaldeposit() != null)
				form.setTotalnumdeposits("1");
			savingsAccount.setTotalnumdeposits(form.getTotalnumdeposits());
			savingsAccount.setTotalprincipaldeposit(form.getTotalprincipaldeposit());
			savingsAccount.setAvailablebalance(form.getTotalprincipaldeposit());//available balance same as initial deposit
			savingsAccount.setScheduledepositmissed(form.isScheduledepositmissed());
			savingsAccountManager.upsertEntity(savingsAccount);
			sgaccId = savingsAccount.getId();
			OpenMFSavingsProduct savingsProduct = AppContext.getAppContext().getSavingsProductManager().getSavingsProductBySavingsCode(savingsAccount.getSavingscode());
			if(savingsProduct.getSavingstype().equalsIgnoreCase("COMPULSORY")){
				DepositService.generateSavingsScheduledDeposits(savingsAccount, savingsProduct, AppContext.getAppContext().getCurrentUser().getId().toString(), form.getTotalprincipaldeposit());
			}
		} else {
			//return null;
		}
		return "redirect:/viewsavingsaccount.htm?sgaccId=" + sgaccId;
	}
}
