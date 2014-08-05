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
import uk.ac.openmf.model.OpenMFSavingsAccount;
import uk.ac.openmf.model.OpenMFSavingsDeposit;
import uk.ac.openmf.model.OpenMFSavingsDepositManager;
import uk.ac.openmf.model.OpenMFSavingsScheduledDeposit;
import uk.ac.openmf.model.OpenMFTransaction;
import uk.ac.openmf.model.nosql.OpenMFUserNoSql;
import uk.ac.openmf.utils.GenerateAccountNumber;
import uk.ac.openmf.utils.MFIAccountTypes;
import uk.ac.openmf.utils.OMFUtils;
import uk.ac.openmf.utils.OpenMFConstants;
import uk.ac.openmf.utils.ServletUtils;
import uk.ac.openmf.web.AppContext;
import uk.ac.openmf.web.forms.SavingsDepositForm;

/**
 * @author harish
 */
@Controller
public class SavingsDepositController {

	@RequestMapping(value="/savingsdeposit", method= RequestMethod.GET)
	public SavingsDepositForm savingsDepositForm(HttpServletRequest req) {
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
		SavingsDepositForm form = new SavingsDepositForm();
		form.setClientId(client.getId().toString());
		form.setSavingsaccountid(sgaccId);
		return form;
	}

	@RequestMapping(value="/savingsdeposit", method = RequestMethod.POST)
	public String savingsactualpayment(SavingsDepositForm form, BindingResult result) throws ParseException {
		if (result.hasErrors()) {
			return null;
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		OpenMFUserNoSql currentUser = (OpenMFUserNoSql)authentication.getPrincipal();
		long sgdptId = 0;
		String sgaccId = "0";
		if (currentUser != null) {
			AppContext appContext = AppContext.getAppContext();
			OpenMFSavingsDepositManager savingsDepositManager = appContext.getSavingsDepositManager();
			OpenMFSavingsDeposit savingsDeposit = savingsDepositManager.newSavingsDeposit(currentUser.getId().toString());
			savingsDeposit.setCreatedById(currentUser.getUsername());
			savingsDeposit.setTimestamp(System.currentTimeMillis());
			savingsDeposit.setStatus(form.isStatus());
			savingsDeposit.setClientId(form.getClientId());

			sgaccId = form.getSavingsaccountid();

			savingsDeposit.setSavingsaccountid(sgaccId);
			savingsDeposit.setAmountpaid(form.getAmountpaid());
			savingsDeposit.setDateofpayment(form.getDateofpayment());
			savingsDeposit.setPostedby(form.getPostedby());
			savingsDeposit.setTransactionnote(form.getTransactionnote());
			savingsDeposit.setTransactionreference(form.getTransactionreference());
			savingsDepositManager.upsertEntity(savingsDeposit);
			sgdptId = savingsDeposit.getId();
			OpenMFSavingsAccount savingsaccount = appContext.getAppContext().getSavingsAccountManager().getSavingsAccount(ServletUtils.validateEventId(sgaccId));
			//get all schedules for this savings
			ArrayList<OpenMFSavingsScheduledDeposit> schedules = OMFUtils.getSavingsScheduledDepositForSavingsAccountList(sgaccId);
			//get first unpaid schedule
			boolean flag = true;
			for (OpenMFSavingsScheduledDeposit openMFSavingsScheduledDeposit : schedules) {
				//means savings is compulsory
				if(openMFSavingsScheduledDeposit.isActive() && !openMFSavingsScheduledDeposit.isPaid()){
					String totalprincipaldepositAmt = savingsaccount.getTotalprincipaldeposit();
					if(totalprincipaldepositAmt == null)
						totalprincipaldepositAmt = "0.0";
					Double totalprincipaldepositAmtVal = new Double(totalprincipaldepositAmt);
					totalprincipaldepositAmtVal += new Double(savingsDeposit.getAmountpaid());
					if(flag){
						flag = false;
						openMFSavingsScheduledDeposit.setPaidamount(savingsDeposit.getAmountpaid());
						openMFSavingsScheduledDeposit.setPaiddate(savingsDeposit.getDateofpayment());
						openMFSavingsScheduledDeposit.setPaid(true);
						//TODO update interest and balance
						Double paidamt =  new Double(savingsDeposit.getAmountpaid());
						//update paid amount in savings account
						savingsaccount.setTotalprincipaldeposit(totalprincipaldepositAmtVal.toString());
						String balanceoutstandingamount = savingsaccount.getAvailablebalance();
						if(balanceoutstandingamount == null)
							balanceoutstandingamount = "0.0";
						Double balanceoutstandingamountVal = new Double(balanceoutstandingamount);
						balanceoutstandingamountVal -= new Double(savingsDeposit.getAmountpaid());
						savingsaccount.setAvailablebalance(balanceoutstandingamount);
						String numdepositsmade = savingsaccount.getTotalnumdeposits();
						if(numdepositsmade == null)
							numdepositsmade = "0";
						Integer numrepaymentsmadeVal = new Integer(numdepositsmade);
						numrepaymentsmadeVal += 1;
						savingsaccount.setTotalnumdeposits(numrepaymentsmadeVal.toString());
						appContext.getAppContext().getSavingsAccountManager().upsertEntity(savingsaccount);
					}
					//TODO calculate savings balance
					//String balanceofsavings = RepaymentService.calcBalanceofSavings(savingsaccount.getDisbursedamount(), savingsaccount.getFees(), savingsaccount.getPenalties(), totalprincipaldepositAmtVal.toString());
					//openMFSavingsScheduledDeposit.setBalanceoutstandingamount(balanceofsavings);
					appContext.getAppContext().getSavingsScheduledDepositManager().upsertEntity(openMFSavingsScheduledDeposit);
					//TODO update Transactions and general journal
					//get chart of account by mfi account type
					//incase of repayment account receivable
					OpenMFChartOfAccounts openMFChartOfAccounts = appContext.getAppContext().getChartOfAccountsManager().getChartOfAccountsByMFIAccountType(MFIAccountTypes.ACCOUNT_RECIEVABLE.getAccountType());
					//add a journal double entry for credit and debit
					OpenMFGeneralJournal openMFGeneralJournal = appContext.getAppContext().getGeneralJournalManager().newGeneralJournal(currentUser.getId().toString());
					openMFGeneralJournal.setApprovedby(savingsDeposit.getCreatedById());
					openMFGeneralJournal.setClientaccountid(savingsDeposit.getSavingsaccountid());
					openMFGeneralJournal.setCoaid(openMFChartOfAccounts.getCoaid());
					openMFGeneralJournal.setCreatedById(currentUser.getId().toString());
					//openMFGeneralJournal.setGeneraljournalid(generaljournalid);
					openMFGeneralJournal.setPostedby(savingsDeposit.getPostedby());
					openMFGeneralJournal.setStatus(true);
					openMFGeneralJournal.setTimestamp(System.currentTimeMillis());
					openMFGeneralJournal.setTransactionamount(savingsDeposit.getDateofpayment());
					openMFGeneralJournal.setTransactiontype(OpenMFConstants.FIELD_VALUE_DEBIT);
					appContext.getAppContext().getGeneralJournalManager().upsertEntity(openMFGeneralJournal);
					OpenMFGeneralJournal openMFGeneralJournal_credit = appContext.getAppContext().getGeneralJournalManager().newGeneralJournal(currentUser.getId().toString());
					openMFGeneralJournal_credit.setApprovedby(savingsDeposit.getCreatedById());
					openMFGeneralJournal_credit.setClientaccountid(savingsDeposit.getSavingsaccountid());
					openMFGeneralJournal_credit.setCoaid(openMFChartOfAccounts.getCoaid());
					openMFGeneralJournal_credit.setCreatedById(currentUser.getId().toString());
					//openMFGeneralJournal.setGeneraljournalid(generaljournalid);
					openMFGeneralJournal_credit.setPostedby(savingsDeposit.getPostedby());
					openMFGeneralJournal_credit.setStatus(true);
					openMFGeneralJournal_credit.setTimestamp(System.currentTimeMillis());
					openMFGeneralJournal_credit.setTransactionamount(savingsDeposit.getAmountpaid());
					openMFGeneralJournal_credit.setTransactiontype(OpenMFConstants.FIELD_VALUE_CREDIT);
					appContext.getAppContext().getGeneralJournalManager().upsertEntity(openMFGeneralJournal_credit);
					//add a transaction entry
					OpenMFTransaction transaction = appContext.getAppContext().getTransactionManager().newTransaction(currentUser.getId().toString());
					transaction.setApprovedby(savingsDeposit.getCreatedById());
					transaction.setCreatedById(savingsDeposit.getCreatedById());
					transaction.setDateoftransaction(savingsDeposit.getDateofpayment());
					transaction.setFromaccountid(savingsaccount.getSavingsaccountnumber());
					transaction.setPostedby(savingsDeposit.getPostedby());
					transaction.setProductid(savingsaccount.getSavingscode());
					transaction.setStatus(true);
					transaction.setClientId(savingsaccount.getClientId());
					transaction.setTransactiontype(OpenMFConstants.FIELD_VALUE_DEPOSIT);
					transaction.setTimestamp(System.currentTimeMillis());
					transaction.setToaccountid(openMFChartOfAccounts.getCoaid());
					transaction.setTransactionid(GenerateAccountNumber.getAccNumberService().generateTransactionNumber(appContext.getAppContext().getTransactionManager().entityCount() + 1));
					appContext.getAppContext().getTransactionManager().upsertEntity(transaction);
				}
			}
		}
		return "redirect:/viewsavingsaccount?sgaccId=" + sgaccId;
	}
}
