package uk.ac.openmf.web.controllers;

import java.text.ParseException;
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
import uk.ac.openmf.model.nosql.OpenMFUserNoSql;
import uk.ac.openmf.services.RepaymentService;
import uk.ac.openmf.utils.GenerateAccountNumber;
import uk.ac.openmf.utils.OMFUtils;
import uk.ac.openmf.utils.ServletUtils;
import uk.ac.openmf.web.AppContext;
import uk.ac.openmf.web.forms.LoanAccountForm;

/**
 * @author harish
 */
@Controller
public class LoanAccountController {

	@RequestMapping(value = "/viewloanaccount.htm", method= RequestMethod.GET)
	public String loanaccountdetails(HttpServletRequest req) {
		req.setAttribute("currentUser", AppContext.getAppContext().getCurrentUser());
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
		req.setAttribute("repaymentschedules", OMFUtils.getLoanRepaymentSchedulesForLoanAccountList(lnaccdetails.getId().toString()));
		return "viewloanaccount";
	}

	@RequestMapping(value = "/loanaccounts.htm", method= RequestMethod.GET)
	public String loanaccounts(HttpServletRequest req) {
		req.setAttribute("currentUser", AppContext.getAppContext().getCurrentUser());
		return "loanaccounts";
	}

	@RequestMapping(value="/createloanaccount.htm", method= RequestMethod.GET)
	public LoanAccountForm loanAccountForm(HttpServletRequest req) {
		req.setAttribute("currentUser", AppContext.getAppContext().getCurrentUser());
		String clientId = req.getParameter("clientId");
		String groupId = req.getParameter("groupId");	
		LoanAccountForm form  = new LoanAccountForm();
		if(clientId != null){
			req.setAttribute("clientId", clientId);
		}else if(groupId != null){
			req.setAttribute("groupId", groupId);
		}
		req.setAttribute("loanProducts", OMFUtils.getLoanProductsList());
		req.setAttribute("omfusers", OMFUtils.getUsersList());
		return form;
	}

	@RequestMapping(value="/createloanaccount.htm", method = RequestMethod.POST)
	public String createloanaccount(LoanAccountForm form, BindingResult result) throws ParseException {
		if (result.hasErrors()) {
			return null;
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		OpenMFUserNoSql currentUser = (OpenMFUserNoSql)authentication.getPrincipal();
		long lnaccId = 0;
		if (currentUser != null) {
			AppContext appContext = AppContext.getAppContext();
			OpenMFLoanAccountManager loanAccountManager = appContext.getLoanAccountManager();

			if(form.getGroupId() != null){
				ArrayList<OpenMFClient> clients = OMFUtils.getClientsByGroupId(form.getGroupId());
				for (OpenMFClient openMFClient : clients) {
					OpenMFLoanAccount loanAccount = loanAccountManager.newLoanAccount(currentUser.getId().toString());
					loanAccount.setClientId(openMFClient.getId().toString());
					loanAccount.setGroupId(form.getGroupId());
					loanAccount.setGrouploan(true);				
					loanAccount.setCreatedById(currentUser.getUsername());
					loanAccount.setTimestamp(System.currentTimeMillis());
					loanAccount.setActive(form.isActive());
					loanAccount.setApprovedamount(form.getApprovedamount());
					loanAccount.setApprovedby(form.getApprovedby());
					loanAccount.setApprovedon(form.getApprovedon());
					loanAccount.setArrearsby(form.getArrearsby());
					loanAccount.setBalanceoutstandingamount(form.getBalanceoutstandingamount());
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
					RepaymentService.generateRepaymentSchedule(loanAccount, AppContext.getAppContext().getLoanProductManager().getLoanProductByLoanCode(loanAccount.getLoancode()), AppContext.getAppContext().getCurrentUser().getId().toString());					
				}
				return "redirect:/groups.htm";
			}else{
				OpenMFLoanAccount loanAccount = loanAccountManager.newLoanAccount(currentUser.getId().toString());
				loanAccount.setGroupId(form.getGroupId());
				loanAccount.setGrouploan(true);				
				loanAccount.setCreatedById(currentUser.getUsername());
				loanAccount.setTimestamp(System.currentTimeMillis());
				loanAccount.setClientId(form.getClientId());				
				loanAccount.setActive(form.isActive());
				loanAccount.setApprovedamount(form.getApprovedamount());
				loanAccount.setApprovedby(form.getApprovedby());
				loanAccount.setApprovedon(form.getApprovedon());
				loanAccount.setArrearsby(form.getArrearsby());
				loanAccount.setBalanceoutstandingamount(form.getBalanceoutstandingamount());
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
				RepaymentService.generateRepaymentSchedule(loanAccount, AppContext.getAppContext().getLoanProductManager().getLoanProductByLoanCode(loanAccount.getLoancode()), AppContext.getAppContext().getCurrentUser().getId().toString());
				return "redirect:/viewloanaccount.htm?lnaccId=" + lnaccId;
			}
		}
		return "redirect:/groups.htm";
	}
}
