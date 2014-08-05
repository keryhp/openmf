package uk.ac.openmf.web.controllers;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.ac.openmf.model.OpenMFChartOfAccounts;
import uk.ac.openmf.model.OpenMFChartOfAccountsManager;
import uk.ac.openmf.model.nosql.OpenMFUserNoSql;
import uk.ac.openmf.utils.GenerateAccountNumber;
import uk.ac.openmf.utils.OMFUtils;
import uk.ac.openmf.utils.ServletUtils;
import uk.ac.openmf.web.AppContext;
import uk.ac.openmf.web.forms.ChartOfAccountsForm;

/**
 * @author harish
 */
@Controller
public class CoAController {

	protected static final Logger logger =
			Logger.getLogger(CoAController.class.getCanonicalName());	

	@RequestMapping(value = "/finance/accountingcoa", method= RequestMethod.GET)
	public String coas(HttpServletRequest req) {
		req.setAttribute("currentUser", AppContext.getAppContext().getCurrentUser());
		req.setAttribute("coas", OMFUtils.getAllCoAsList());
		return "/finance/accountingcoa";
	}

	@RequestMapping(value = "/finance/viewcoa", method= RequestMethod.GET)
	public String viewCoA(HttpServletRequest req) {
		req.setAttribute("currentUser", AppContext.getAppContext().getCurrentUser());
		String coaId = req.getParameter("coaId");
		req.setAttribute("coaId", coaId);
		OpenMFChartOfAccounts coa = null;
		if (coaId != null) {
			coa = AppContext.getAppContext().getChartOfAccountsManager().getChartOfAccounts(ServletUtils.validateEventId(coaId));
		}
		req.setAttribute("coa", coa);
		//TODO
//		req.setAttribute("todaysledger", OMFUtils.getTodaysGeneralLedgerByMFIAccType(coa.getMfiaccounttype()));
//		req.setAttribute("todaysjournal", OMFUtils.getTodaysGeneralJournalByMFIAccType(coa.getMfiaccounttype()));
		req.setAttribute("todaysledger", OMFUtils.getTodaysGeneralLedgerByMFIAccType(coa.getCoaid()));
		req.setAttribute("todaysjournal", OMFUtils.getTodaysGeneralJournalByMFIAccType(coa.getCoaid()));
		return "/finance/viewcoa";
	}

	@RequestMapping(value = "/finance/createcoa", method= RequestMethod.GET)
	public ChartOfAccountsForm coAForm(HttpServletRequest req) {
		req.setAttribute("currentUser", AppContext.getAppContext().getCurrentUser());
		req.setAttribute("omfusers", OMFUtils.getUsersList());
		return new ChartOfAccountsForm();
	}

	@RequestMapping(value="/finance/createcoa", method = RequestMethod.POST)
	public String createcoa(ChartOfAccountsForm form, BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		OpenMFUserNoSql currentUser = (OpenMFUserNoSql)authentication.getPrincipal();
		if (currentUser != null) {
			logger.info("User details:" + currentUser.toString());
			AppContext appContext = AppContext.getAppContext();
			OpenMFChartOfAccountsManager coaManager = appContext.getChartOfAccountsManager();
			OpenMFChartOfAccounts coa = coaManager.newChartOfAccounts(currentUser.getId().toString());
			coa.setCreatedById(currentUser.getUsername());
			coa.setTimestamp(System.currentTimeMillis());
			coa.setOffice(form.getOffice());
			coa.setCoaid(GenerateAccountNumber.getAccNumberService().generateCoAAccNumber(coaManager.entityCount() + 1));
			coa.setCoaname(form.getCoaname());
			coa.setMfiaccounttype(form.getMfiaccounttype());
			coa.setFunds(form.getFunds());
			coaManager.upsertEntity(coa);
		}
		return "redirect:/finance/accountingcoa";
	}
}
