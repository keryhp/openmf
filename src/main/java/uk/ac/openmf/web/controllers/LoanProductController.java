package uk.ac.openmf.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.ac.openmf.model.OpenMFLoanProduct;
import uk.ac.openmf.model.OpenMFLoanProductManager;
import uk.ac.openmf.model.nosql.OpenMFUserNoSql;
import uk.ac.openmf.users.GaeUser;
import uk.ac.openmf.utils.ServletUtils;
import uk.ac.openmf.utils.OMFUtils;
import uk.ac.openmf.web.AppContext;
import uk.ac.openmf.web.forms.LoanProductForm;

/**
 * @author harish
 */
@Controller
public class LoanProductController {

	@RequestMapping(value = "/loanproductdetails", method= RequestMethod.GET)
	public String loanproductdetails(HttpServletRequest req) {
		req.setAttribute("currentUser", AppContext.getAppContext().getCurrentUser());
		String lpId = req.getParameter("lpId");
		req.setAttribute("lpId", lpId);
		OpenMFLoanProduct lpdetails = null;
		if (lpId != null) {
			lpdetails = AppContext.getAppContext().getLoanProductManager().getLoanProduct(ServletUtils.validateEventId(lpId));
		}
		req.setAttribute("lpdetails", lpdetails);
		return "loanproductdetails";
	}

    @RequestMapping(value = "/loanproducts", method= RequestMethod.GET)
    public String loanproducts(HttpServletRequest req) {
		req.setAttribute("currentUser", AppContext.getAppContext().getCurrentUser());
		req.setAttribute("loanProducts", OMFUtils.getLoanProductsList());
        return "loanproducts";
    }
    
	@RequestMapping(value="/createloanproduct", method= RequestMethod.GET)
	public LoanProductForm loanProductForm(HttpServletRequest req) {
		req.setAttribute("currentUser", AppContext.getAppContext().getCurrentUser());
		return new LoanProductForm();
	}

	@RequestMapping(value="/createloanproduct", method = RequestMethod.POST)
	public String createloanproduct(LoanProductForm form, BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		OpenMFUserNoSql currentUser = (OpenMFUserNoSql)authentication.getPrincipal();
		boolean succeeded = false;
		if (currentUser != null) {
			AppContext appContext = AppContext.getAppContext();
			OpenMFLoanProductManager loanProductManager = appContext.getLoanProductManager();
			OpenMFLoanProduct loanProduct = loanProductManager.newLoanProduct(currentUser.getId().toString());
			loanProduct.setClosedate(form.getClosedate());
			loanProduct.setCreatedById(currentUser.getUsername());
			loanProduct.setDescription(form.getDescription());
			loanProduct.setLoancode(form.getLoancode());
			loanProduct.setProductname(form.getProductname());
			loanProduct.setRateofinterest(form.getRateofinterest());
			loanProduct.setStartdate(form.getStartdate());
			loanProduct.setRepaymentfrequency(form.getRepaymentfrequency());
			loanProduct.setRepaymentperiod(form.getRepaymentperiod());
			loanProduct.setLoanamount(form.getLoanamount());
			loanProduct.setLoantype(form.getLoantype());			
			loanProduct.setTimestamp(System.currentTimeMillis());
			loanProduct.setActive(form.isActive());			
			loanProductManager.upsertEntity(loanProduct);
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

		return "redirect:/loanproducts";
	}
}
