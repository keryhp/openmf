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
import uk.ac.openmf.model.OpenMFSavingsAccount;
import uk.ac.openmf.model.OpenMFSavingsWithdrawal;
import uk.ac.openmf.model.OpenMFSavingsWithdrawalManager;
import uk.ac.openmf.model.nosql.OpenMFUserNoSql;
import uk.ac.openmf.utils.OMFUtils;
import uk.ac.openmf.utils.ServletUtils;
import uk.ac.openmf.web.AppContext;
import uk.ac.openmf.web.forms.SavingsWithdrawalForm;

/**
 * @author harish
 */
@Controller
public class SavingsWithdrawalController {

	@RequestMapping(value="/savingswithdrawal.htm", method= RequestMethod.GET)
	public SavingsWithdrawalForm savingsWithdrawalForm(HttpServletRequest req) {
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
		req.setAttribute("omfusers", OMFUtils.getUsersList());
		SavingsWithdrawalForm form = new SavingsWithdrawalForm();
		form.setClientId(client.getId().toString());
		form.setSavingsaccountid(sgaccId);
		return form;
	}

	@RequestMapping(value="/savingswithdrawal.htm", method = RequestMethod.POST)
	public String savingswithdrawal(SavingsWithdrawalForm form, BindingResult result) throws ParseException {
		if (result.hasErrors()) {
			return null;
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		OpenMFUserNoSql currentUser = (OpenMFUserNoSql)authentication.getPrincipal();
		long sgdptId = 0;
		String sgaccId = "0";
		if (currentUser != null) {
			AppContext appContext = AppContext.getAppContext();
			OpenMFSavingsWithdrawalManager savingsWithdrawalManager = appContext.getSavingsWithdrawalManager();
			OpenMFSavingsWithdrawal savingsWithdrawal = savingsWithdrawalManager.newSavingsWithdrawal(currentUser.getId().toString());
			savingsWithdrawal.setCreatedById(currentUser.getUsername());
			savingsWithdrawal.setTimestamp(System.currentTimeMillis());
			savingsWithdrawal.setStatus(form.isStatus());
			savingsWithdrawal.setClientId(form.getClientId());

			sgaccId = form.getSavingsaccountid();

			savingsWithdrawal.setSavingsaccountid(sgaccId);
			savingsWithdrawal.setWithdrawalamount(form.getWithdrawalamount());
			savingsWithdrawal.setDateofwithdrawal(form.getDateofwithdrawal());
			savingsWithdrawal.setPostedby(form.getPostedby());
			savingsWithdrawal.setTransactionnote(form.getTransactionnote());
			savingsWithdrawal.setTransactionreference(form.getTransactionreference());
			savingsWithdrawalManager.upsertEntity(savingsWithdrawal);
			sgdptId = savingsWithdrawal.getId();
			OpenMFSavingsAccount savingsaccount = appContext.getAppContext().getSavingsAccountManager().getSavingsAccount(ServletUtils.validateEventId(sgaccId));
			String balanceAmt = savingsaccount.getAvailablebalance();
			if(balanceAmt == null)
				balanceAmt = "0.0";
			Double balanceVal = new Double(balanceAmt);
			balanceVal -= new Double(form.getWithdrawalamount());
			savingsaccount.setAvailablebalance(balanceVal.toString());
			appContext.getAppContext().getSavingsAccountManager().upsertEntity(savingsaccount);
			//TODO update transactions
		}
		return "redirect:/viewsavingsaccount.htm?sgaccId=" + sgaccId;
	}
}
