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
import uk.ac.openmf.model.OpenMFLoanActualPayment;
import uk.ac.openmf.model.OpenMFLoanActualPaymentManager;
import uk.ac.openmf.model.OpenMFLoanRepayment;
import uk.ac.openmf.model.nosql.OpenMFUserNoSql;
import uk.ac.openmf.services.RepaymentService;
import uk.ac.openmf.utils.OMFUtils;
import uk.ac.openmf.utils.ServletUtils;
import uk.ac.openmf.web.AppContext;
import uk.ac.openmf.web.forms.LoanActualPaymentForm;

/**
 * @author harish
 */
@Controller
public class LoanActualPaymentController {

	@RequestMapping(value="/loanactualpayment.htm", method= RequestMethod.GET)
	public LoanActualPaymentForm loanActualPaymentForm(HttpServletRequest req) {
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
		LoanActualPaymentForm form = new LoanActualPaymentForm();
		form.setClientId(client.getId().toString());
		form.setLoanaccountid(lnaccId);
		return form;
	}

	@RequestMapping(value="/loanactualpayment.htm", method = RequestMethod.POST)
	public String loanactualpayment(LoanActualPaymentForm form, BindingResult result) throws ParseException {
		if (result.hasErrors()) {
			return null;
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		OpenMFUserNoSql currentUser = (OpenMFUserNoSql)authentication.getPrincipal();
		long lnpmntId = 0;
		String lnaccId = "0";
		if (currentUser != null) {
			AppContext appContext = AppContext.getAppContext();
			OpenMFLoanActualPaymentManager loanActualPaymentManager = appContext.getLoanActualPaymentManager();
			OpenMFLoanActualPayment loanActualPayment = loanActualPaymentManager.newLoanActualPayment(currentUser.getUserId());
			loanActualPayment.setCreatedById(currentUser.getUsername());
			loanActualPayment.setTimestamp(System.currentTimeMillis());
			loanActualPayment.setStatus(form.isStatus());
			loanActualPayment.setClientId(form.getClientId());

			lnaccId = form.getLoanaccountid();

			loanActualPayment.setLoanaccountid(lnaccId);
			loanActualPayment.setAmountpaid(form.getAmountpaid());
			loanActualPayment.setDateofpayment(form.getPostedby());
			loanActualPayment.setPostedby(form.getPostedby());
			loanActualPayment.setTransactionnote(form.getTransactionnote());
			loanActualPayment.setTransactionreference(form.getTransactionreference());
			loanActualPaymentManager.upsertEntity(loanActualPayment);
			lnpmntId = loanActualPayment.getId();
			//TODO update scheduled payment
			//get all schedules for this loan
			ArrayList<OpenMFLoanRepayment> schedules = OMFUtils.getLoanRepaymentSchedulesForLoanAccountList(lnaccId);
			//get first unpaid schedule
			boolean flag = true;
			OpenMFLoanAccount loanaccount = appContext.getAppContext().getLoanAccountManager().getLoanAccount(ServletUtils.validateEventId(lnaccId));
			for (OpenMFLoanRepayment openMFLoanRepayment : schedules) {
				if(openMFLoanRepayment.isActive() && !openMFLoanRepayment.isPaid()){
					String totalRepaymentAmt = loanaccount.getTotalrepaymentamount();
					if(totalRepaymentAmt == null)
						totalRepaymentAmt = "0.0";
					Double totalRepaymentAmtVal = new Double(totalRepaymentAmt);
					totalRepaymentAmtVal += new Double(loanActualPayment.getAmountpaid());
					if(flag){
						flag = false;
						openMFLoanRepayment.setPaidamount(loanActualPayment.getAmountpaid());
						openMFLoanRepayment.setPaiddate(loanActualPayment.getDateofpayment());
						openMFLoanRepayment.setPaid(true);
						Double dueamt =  new Double(openMFLoanRepayment.getDueamount());
						Double paidamt =  new Double(loanActualPayment.getAmountpaid());
						openMFLoanRepayment.setDueamount(new Double(dueamt- paidamt).toString());
						//update paid amount in loan account
						loanaccount.setTotalrepaymentamount(totalRepaymentAmtVal.toString());
						String balanceoutstandingamount = loanaccount.getBalanceoutstandingamount();
						if(balanceoutstandingamount == null)
							balanceoutstandingamount = "0.0";
						Double balanceoutstandingamountVal = new Double(balanceoutstandingamount);
						balanceoutstandingamountVal -= new Double(loanActualPayment.getAmountpaid());
						loanaccount.setBalanceoutstandingamount(balanceoutstandingamount);
						loanaccount.setArrearsby(balanceoutstandingamount);
						String numrepaymentsmade = loanaccount.getNumrepaymentsmade();
						if(numrepaymentsmade == null)
							numrepaymentsmade = "0";
						Integer numrepaymentsmadeVal = new Integer(numrepaymentsmade);
						loanaccount.setNumrepaymentsmade(numrepaymentsmadeVal.toString());
						appContext.getAppContext().getLoanAccountManager().upsertEntity(loanaccount);
					}
					String balanceofloan = RepaymentService.calcBalanceofLoan(loanaccount.getDisbursedamount(), loanaccount.getFees(), loanaccount.getPenalties(), totalRepaymentAmtVal.toString());
					openMFLoanRepayment.setBalanceoutstandingamount(balanceofloan);
					appContext.getAppContext().getLoanRepaymentManager().upsertEntity(openMFLoanRepayment);
				}
			}
			//check if previous schedule was missed
		} else {
			//return null;
		}
		return "redirect:/viewloanaccount.htm?lnaccId=" + lnaccId;
	}
}
