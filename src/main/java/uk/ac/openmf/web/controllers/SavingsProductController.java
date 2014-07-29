package uk.ac.openmf.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.ac.openmf.model.OpenMFSavingsProduct;
import uk.ac.openmf.model.OpenMFSavingsProductManager;
import uk.ac.openmf.model.nosql.OpenMFUserNoSql;
import uk.ac.openmf.users.GaeUser;
import uk.ac.openmf.utils.ServletUtils;
import uk.ac.openmf.utils.OMFUtils;
import uk.ac.openmf.web.AppContext;
import uk.ac.openmf.web.forms.SavingsProductForm;

/**
 * @author harish
 */
@Controller
public class SavingsProductController {

	@RequestMapping(value = "/savingsproductdetails.htm", method= RequestMethod.GET)
	public String savingsproductdetails(HttpServletRequest req) {
		req.setAttribute("currentUser", AppContext.getAppContext().getCurrentUser());
		String spId = req.getParameter("spId");
		req.setAttribute("spId", spId);
		OpenMFSavingsProduct spdetails = null;
		if (spId != null) {
			spdetails = AppContext.getAppContext().getSavingsProductManager().getSavingsProduct(ServletUtils.validateEventId(spId));
		}
		req.setAttribute("spdetails", spdetails);
		return "savingsproductdetails";
	}

    @RequestMapping(value = "/savingsproducts.htm", method= RequestMethod.GET)
    public String savingsproducts(HttpServletRequest req) {
		req.setAttribute("currentUser", AppContext.getAppContext().getCurrentUser());
		req.setAttribute("savingsProducts", OMFUtils.getSavingsProductsList());
        return "savingsproducts";
    }
    
	@RequestMapping(value="/createsavingsproduct.htm", method= RequestMethod.GET)
	public SavingsProductForm savingsProductForm(HttpServletRequest req) {
		req.setAttribute("currentUser", AppContext.getAppContext().getCurrentUser());
		return new SavingsProductForm();
	}

	@RequestMapping(value="/createsavingsproduct.htm", method = RequestMethod.POST)
	public String createsavingsproduct(SavingsProductForm form, BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		OpenMFUserNoSql currentUser = (OpenMFUserNoSql)authentication.getPrincipal();
		boolean succeeded = false;
		if (currentUser != null) {
			AppContext appContext = AppContext.getAppContext();
			OpenMFSavingsProductManager savingsProductManager = appContext.getSavingsProductManager();
			OpenMFSavingsProduct savingsProduct = savingsProductManager.newSavingsProduct(currentUser.getUserId());
			savingsProduct.setClosedate(form.getClosedate());
			savingsProduct.setCreatedById(currentUser.getUsername());
			savingsProduct.setDescription(form.getDescription());
			savingsProduct.setSavingscode(form.getSavingscode());
			savingsProduct.setProductname(form.getProductname());
			savingsProduct.setRateofinterest(form.getRateofinterest());
			savingsProduct.setStartdate(form.getStartdate());
			savingsProduct.setDepositfrequency(form.getDepositfrequency());
			savingsProduct.setTenure(form.getTenure());
			savingsProduct.setSavingsamount(form.getSavingsamount());
			savingsProduct.setSavingstype(form.getSavingstype());
			savingsProduct.setTimestamp(System.currentTimeMillis());
			savingsProduct.setActive(form.isActive());			
			savingsProductManager.upsertEntity(savingsProduct);
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

		return "redirect:/savingsproducts.htm";
	}
}
