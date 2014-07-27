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
import uk.ac.openmf.model.OpenMFLoanAccount;
import uk.ac.openmf.model.OpenMFLoanAccountManager;
import uk.ac.openmf.model.OpenMFLoanProduct;
import uk.ac.openmf.model.OpenMFUser;
import uk.ac.openmf.model.OpenMFUserManager;
import uk.ac.openmf.users.GaeUser;
import uk.ac.openmf.utils.GenerateAccountNumber;
import uk.ac.openmf.utils.ServletUtils;
import uk.ac.openmf.web.AppContext;
import uk.ac.openmf.web.forms.LoanAccountForm;

import com.google.appengine.api.datastore.DatastoreNeedIndexException;

/**
 * @author harish
 */
@Controller
public class LoanAccountController {

	@RequestMapping(value = "/viewloanaccount.htm", method= RequestMethod.GET)
	public String loanaccountdetails(HttpServletRequest req) {
		String lnaccId = req.getParameter("lnaccId");
		req.setAttribute("lnaccId", lnaccId);
		OpenMFLoanAccount lnaccdetails = null;
		if (lnaccId != null) {
			lnaccdetails = AppContext.getAppContext().getLoanAccountManager().getLoanAccount(ServletUtils.validateEventId(lnaccId));
		}
		req.setAttribute("lnaccdetails", lnaccdetails);
		
		OpenMFClient client = AppContext.getAppContext().getClientManager().getClient(new Long(lnaccdetails.getClientId()));
		req.setAttribute("client", client);
		
		OpenMFLoanProduct loanproduct = AppContext.getAppContext().getLoanProductManager().getLoanProductByLoanCode(lnaccdetails.getLoancode());
		req.setAttribute("loanproduct", loanproduct);
		
		return "viewloanaccount";
	}

	@RequestMapping(value = "/loanaccounts.htm", method= RequestMethod.GET)
	public String loanaccounts() {
		return "loanaccounts";
	}

	@RequestMapping(value="/createloanaccount.htm", method= RequestMethod.GET)
	public LoanAccountForm loanAccountForm(HttpServletRequest req) {
		String clientId = req.getParameter("clientId");
		req.setAttribute("clientId", clientId);
		Iterable<OpenMFLoanProduct> loanProductiter = AppContext.getAppContext().getLoanProductManager().getAllLoanProduct();
		ArrayList<OpenMFLoanProduct> loanProducts = new ArrayList<OpenMFLoanProduct>();
		try {
			for (OpenMFLoanProduct loanProduct : loanProductiter) {
				loanProducts.add(loanProduct);
			}
		} catch (DatastoreNeedIndexException e) {
			//log the error
		}
		req.setAttribute("loanProducts", loanProducts);

		OpenMFUserManager userManager = AppContext.getAppContext().getUserManager();
		Iterable<OpenMFUser> useriter = userManager.getAllUsers();
		ArrayList<OpenMFUser> omfusers= new ArrayList<OpenMFUser>();
		try {
			for (OpenMFUser user : useriter) {
				omfusers.add(user);
			}
		} catch (DatastoreNeedIndexException e) {
			//log error
		}
		req.setAttribute("omfusers", omfusers);
		LoanAccountForm form  = new LoanAccountForm();
		form.setClientId(clientId);
		return form;
	}

	@RequestMapping(value="/createloanaccount.htm", method = RequestMethod.POST)
	public String createloanaccount(LoanAccountForm form, BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		GaeUser currentUser = (GaeUser)authentication.getPrincipal();
		boolean succeeded = false;
		long lnaccId = 0;
		if (currentUser != null) {
			AppContext appContext = AppContext.getAppContext();
			OpenMFLoanAccountManager loanAccountManager = appContext.getLoanAccountManager();
			OpenMFLoanAccount loanAccount = loanAccountManager.newLoanAccount(currentUser.getUserId());
			loanAccount.setCreatedById(currentUser.getNickname());
			loanAccount.setTimestamp(System.currentTimeMillis());
			loanAccount.setActive(form.isActive());
			loanAccount.setApprovedamount(form.getApprovedamount());
			loanAccount.setApprovedby(form.getApprovedby());
			loanAccount.setApprovedon(form.getApprovedon());
			loanAccount.setArrearsby(form.getArrearsby());
			loanAccount.setBalanceoutstandingamount(form.getBalanceoutstandingamount());
			loanAccount.setClientId(form.getClientId());
			loanAccount.setDisbursedamount(form.getDisbursedamount());
			loanAccount.setDisbursedon(form.getDisbursedon());
			loanAccount.setFees(form.getFees());
			loanAccount.setFieldofficer(form.getFieldofficer());
			loanAccount.setInterestcalcperiod(form.getInterestcalcperiod());
			loanAccount.setInterestrepaymentamount(form.getInterestrepaymentamount());
			loanAccount.setLoanaccountnumber(GenerateAccountNumber.getAccNumberService().generateLoanAccNumber(loanAccountManager.entityCount() + 1));
			loanAccount.setLoanclosedate(form.getLoanclosedate());
			loanAccount.setLoancode(form.getLoancode());
			loanAccount.setLoanofficer(form.getLoanofficer());
			loanAccount.setLoanpurpose(form.getLoanpurpose());
			loanAccount.setLoanstartdate(form.getLoanstartdate());
			loanAccount.setMatureson(form.getMatureson());
			loanAccount.setNumrepaymentsmade(form.getNumrepaymentsmade());
			loanAccount.setPenalties(form.getPenalties());
			loanAccount.setPrincipaldueamount(form.getPrincipaldueamount());
			loanAccount.setSubmittedon(form.getSubmittedon());
			loanAccount.setTotalnumrepayments(form.getTotalnumrepayments());
			loanAccount.setTotalrepaymentamount(form.getTotalrepaymentamount());
			loanAccount.setNumpaymentsmissed(form.getNumpaymentsmissed());
			loanAccount.setDefaulted(form.isDefaulted());
			loanAccountManager.upsertEntity(loanAccount);
			lnaccId = loanAccount.getId();
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

		return "redirect:/viewloanaccount.htm?lnaccId=" + lnaccId;
	}
}
