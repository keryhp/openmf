package uk.ac.openmf.web.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.ac.openmf.model.OpenMFClient;
import uk.ac.openmf.model.OpenMFLoanAccount;
import uk.ac.openmf.model.OpenMFLoanRepayment;
import uk.ac.openmf.model.OpenMFSavingsAccount;
import uk.ac.openmf.model.OpenMFSavingsScheduledDeposit;
import uk.ac.openmf.utils.OMFUtils;
import uk.ac.openmf.utils.ServletUtils;
import uk.ac.openmf.web.AppContext;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ClientsRepController {
	
	@RequestMapping(value = "/reports/clients", method= RequestMethod.GET)
    public String clients(HttpServletRequest req) {
		req.setAttribute("currentUser", AppContext.getAppContext().getCurrentUser());
		req.setAttribute("clients", OMFUtils.getAllClientsList());
		return "reports/clients";
	}
	
	@RequestMapping(value = "/reports/clientsrep", method= RequestMethod.GET)
    public String clientsrep(HttpServletRequest req) {
		req.setAttribute("currentUser", AppContext.getAppContext().getCurrentUser());
		String clientId = req.getParameter("clientId");
		req.setAttribute("clientId", clientId);
		OpenMFClient client = null;
		if (clientId != null) {
			client = AppContext.getAppContext().getClientManager().getClient(ServletUtils.validateEventId(clientId));
		}
		req.setAttribute("client", client);
		ArrayList<OpenMFLoanAccount> loanAccounts = OMFUtils.getLoanAccountsByClientList(clientId);
		ArrayList<OpenMFSavingsAccount> savingsAccounts = OMFUtils.getSavingsAccountsByClientList(clientId);
		req.setAttribute("loanAccounts", loanAccounts);
		req.setAttribute("savingsAccounts", savingsAccounts);
		req.setAttribute("transactions", OMFUtils.getTransactionByClientId(clientId));
		List<OpenMFLoanRepayment> loanSchedules = new ArrayList<OpenMFLoanRepayment>();
		for (OpenMFLoanAccount openMFLoanAccount : loanAccounts) {
			loanSchedules.addAll(OMFUtils.getLoanRepaymentSchedulesForLoanAccountList(openMFLoanAccount.getId().toString()));
		} 
		req.setAttribute("repaymentschedules", loanSchedules);
		List<OpenMFSavingsScheduledDeposit> savingsSchedules = new ArrayList<OpenMFSavingsScheduledDeposit>();
		for (OpenMFSavingsAccount openMFSavingsAccount : savingsAccounts) {
			savingsSchedules.addAll(OMFUtils.getSavingsScheduledDepositForSavingsAccountList(openMFSavingsAccount.getId().toString()));			
		} 
		req.setAttribute("depositschedules", savingsSchedules);		
        return "reports/clientsrep";
    }
}
