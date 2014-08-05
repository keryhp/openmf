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

import uk.ac.openmf.model.OpenMFChartOfAccounts;
import uk.ac.openmf.model.OpenMFClient;
import uk.ac.openmf.model.OpenMFGeneralJournal;
import uk.ac.openmf.model.OpenMFLoanAccount;
import uk.ac.openmf.model.OpenMFLoanDisburse;
import uk.ac.openmf.model.OpenMFLoanDisburseManager;
import uk.ac.openmf.model.OpenMFLoanRepayment;
import uk.ac.openmf.model.OpenMFTransaction;
import uk.ac.openmf.model.nosql.OpenMFUserNoSql;
import uk.ac.openmf.services.ScheduledRepaymentService;
import uk.ac.openmf.utils.GenerateAccountNumber;
import uk.ac.openmf.utils.MFIAccountTypes;
import uk.ac.openmf.utils.OMFUtils;
import uk.ac.openmf.utils.OpenMFConstants;
import uk.ac.openmf.utils.ServletUtils;
import uk.ac.openmf.web.AppContext;
import uk.ac.openmf.web.forms.LoanDisburseForm;

/**
 * @author harish
 */
@Controller
public class LoanDisburseController {

	@RequestMapping(value="/loandisburse", method= RequestMethod.GET)
	public LoanDisburseForm loanDisburseForm(HttpServletRequest req) {
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
		req.setAttribute("omfusers", OMFUtils.getUsersList());
		LoanDisburseForm form = new LoanDisburseForm();
		form.setClientId(client.getId().toString());
		form.setLoanaccountid(lnaccId);
		return form;
	}

	@RequestMapping(value="/loandisburse", method = RequestMethod.POST)
	public String loandisburse(LoanDisburseForm form, BindingResult result) throws ParseException {
		if (result.hasErrors()) {
			return null;
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		OpenMFUserNoSql currentUser = (OpenMFUserNoSql)authentication.getPrincipal();
		long lnpmntId = 0;
		String lnaccId = "0";
		if (currentUser != null) {
			AppContext appContext = AppContext.getAppContext();
			OpenMFLoanDisburseManager loanDisburseManager = appContext.getLoanDisburseManager();
			OpenMFLoanDisburse loanDisburse = loanDisburseManager.newLoanDisburse(currentUser.getId().toString());
			loanDisburse.setCreatedById(currentUser.getUsername());
			loanDisburse.setTimestamp(System.currentTimeMillis());
			loanDisburse.setStatus(form.isStatus());
			loanDisburse.setClientId(form.getClientId());

			lnaccId = form.getLoanaccountid();

			loanDisburse.setLoanaccountid(lnaccId);
			loanDisburse.setDisbursedamount(form.getDisbursedamount());
			loanDisburse.setDisbursedon(form.getDisbursedon());
			loanDisburse.setPostedby(form.getPostedby());
			loanDisburse.setTransactionnote(form.getTransactionnote());
			loanDisburse.setTransactionreference(form.getTransactionreference());
			loanDisburseManager.upsertEntity(loanDisburse);
			lnpmntId = loanDisburse.getId();
			
			OpenMFLoanAccount loanaccount = appContext.getAppContext().getLoanAccountManager().getLoanAccount(ServletUtils.validateEventId(lnaccId));
			String disburseAmt = loanaccount.getDisbursedamount();
			if(disburseAmt == null)
				disburseAmt = "0.0";
			Double disburseAmtVal = new Double(disburseAmt);
			disburseAmtVal += new Double(form.getDisbursedamount());
			loanaccount.setDisbursedamount(disburseAmtVal.toString());
			loanaccount.setDisbursedon(form.getDisbursedon());
			appContext.getAppContext().getLoanAccountManager().upsertEntity(loanaccount);
			//TODO update Transactions and general journal
			//get chart of account by mfi account type
			//incase of repayment account receivable
			OpenMFChartOfAccounts openMFChartOfAccounts = appContext.getAppContext().getChartOfAccountsManager().getChartOfAccountsByMFIAccountType(MFIAccountTypes.ACCOUNT_PAYABLE.getAccountType());
			//add a journal double entry for credit and debit
			OpenMFGeneralJournal openMFGeneralJournal = appContext.getAppContext().getGeneralJournalManager().newGeneralJournal(currentUser.getId().toString());
			openMFGeneralJournal.setApprovedby(loanDisburse.getCreatedById());
			openMFGeneralJournal.setClientaccountid(loanDisburse.getLoanaccountid());
			openMFGeneralJournal.setCoaid(openMFChartOfAccounts.getCoaid());
			openMFGeneralJournal.setCreatedById(currentUser.getId().toString());
			//openMFGeneralJournal.setGeneraljournalid(generaljournalid);
			openMFGeneralJournal.setPostedby(loanDisburse.getPostedby());
			openMFGeneralJournal.setStatus(true);
			openMFGeneralJournal.setTimestamp(System.currentTimeMillis());
			openMFGeneralJournal.setTransactionamount(loanDisburse.getDisbursedamount());
			openMFGeneralJournal.setTransactiontype(OpenMFConstants.FIELD_VALUE_DEBIT);
			appContext.getAppContext().getGeneralJournalManager().upsertEntity(openMFGeneralJournal);
			OpenMFGeneralJournal openMFGeneralJournal_credit = appContext.getAppContext().getGeneralJournalManager().newGeneralJournal(currentUser.getId().toString());
			openMFGeneralJournal_credit.setApprovedby(loanDisburse.getCreatedById());
			openMFGeneralJournal_credit.setClientaccountid(loanDisburse.getLoanaccountid());
			openMFGeneralJournal_credit.setCoaid(openMFChartOfAccounts.getCoaid());
			openMFGeneralJournal_credit.setCreatedById(currentUser.getId().toString());
			//openMFGeneralJournal.setGeneraljournalid(generaljournalid);
			openMFGeneralJournal_credit.setPostedby(loanDisburse.getPostedby());
			openMFGeneralJournal_credit.setStatus(true);
			openMFGeneralJournal_credit.setTimestamp(System.currentTimeMillis());
			openMFGeneralJournal_credit.setTransactionamount(loanDisburse.getDisbursedamount());
			openMFGeneralJournal_credit.setTransactiontype(OpenMFConstants.FIELD_VALUE_CREDIT);
			appContext.getAppContext().getGeneralJournalManager().upsertEntity(openMFGeneralJournal_credit);
			//add a transaction entry
			OpenMFTransaction transaction = appContext.getAppContext().getTransactionManager().newTransaction(currentUser.getId().toString());
			transaction.setApprovedby(loanDisburse.getCreatedById());
			transaction.setCreatedById(loanDisburse.getCreatedById());
			transaction.setDateoftransaction(loanDisburse.getDisbursedon());
			transaction.setFromaccountid(loanaccount.getLoanaccountnumber());
			transaction.setPostedby(loanDisburse.getPostedby());
			transaction.setProductid(loanaccount.getLoancode());
			transaction.setStatus(true);
			transaction.setClientId(loanaccount.getClientId());
			transaction.setTransactiontype(OpenMFConstants.FIELD_VALUE_DISBURSAL);
			transaction.setTimestamp(System.currentTimeMillis());
			transaction.setToaccountid(openMFChartOfAccounts.getCoaid());
			transaction.setTransactionid(GenerateAccountNumber.getAccNumberService().generateTransactionNumber(appContext.getAppContext().getTransactionManager().entityCount() + 1));
			appContext.getAppContext().getTransactionManager().upsertEntity(transaction);

		}
		return "redirect:/viewloanaccount?lnaccId=" + lnaccId;
	}
}
