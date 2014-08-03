package uk.ac.openmf.web.controllers;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.ac.openmf.model.OpenMFUser;
import uk.ac.openmf.model.nosql.OpenMFEntityManagerNoSql;
import uk.ac.openmf.web.AppContext;

import com.google.appengine.api.users.UserServiceFactory;

/**
 *
 * @author harish
 *
 */
@Controller
public class GaeAppController {

	protected static final Logger logger =
			Logger.getLogger(GaeAppController.class.getCanonicalName());
	
    @RequestMapping(value = "/", method= RequestMethod.GET)
    public String landing(HttpServletRequest req) {
    	OpenMFUser currentUser = (OpenMFUser)AppContext.getAppContext().getCurrentUser();
    	if(currentUser == null){
    		return "redirect: /register.htm";
    	}
		req.setAttribute("currentUser", currentUser);
		logger.info("User details:" + currentUser.toString());
        return "landing";
    }
    
    @RequestMapping(value = "/landing.htm", method= RequestMethod.GET)
    public String showLanding() {
        return "landing";
    }

    @RequestMapping(value = "/home.htm", method= RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/disabled.htm", method= RequestMethod.GET)
    public String disabled() {
        return "disabled";
    }

    @RequestMapping(value = "/logout.htm", method= RequestMethod.GET)
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();

        String logoutUrl = UserServiceFactory.getUserService().createLogoutURL("/register.htm");

        response.sendRedirect(logoutUrl);
    }

    @RequestMapping(value = "/loggedout.htm", method= RequestMethod.GET)
    public String loggedOut() {
        return "loggedout";
    }
    
    @RequestMapping(value = "/help.htm", method= RequestMethod.GET)
    public String help() {
        return "help";
    }
    
    @RequestMapping(value = "/advsearch.htm", method= RequestMethod.GET)
    public String advsearch() {
        return "advsearch";
    }
    
    @RequestMapping(value = "/offices.htm", method= RequestMethod.GET)
    public String offices() {
        return "advsearch";
    }
    
    @RequestMapping(value = "/tasks.htm", method= RequestMethod.GET)
    public String tasks() {
        return "tasks";
    }
    
    @RequestMapping(value = "/entercollectionsheet.htm", method= RequestMethod.GET)
    public String entercollectionsheet() {
        return "entercollectionsheet";
    }
    
    @RequestMapping(value = "/freqposting.htm", method= RequestMethod.GET)
    public String freqposting() {
        return "freqposting";
    }
    
    @RequestMapping(value = "/journalentry.htm", method= RequestMethod.GET)
    public String journalentry() {
        return "journalentry";
    }
    
    @RequestMapping(value = "/accountsclosure.htm", method= RequestMethod.GET)
    public String accountsclosure() {
        return "accountsclosure";
    }
    
    @RequestMapping(value = "/createcenter.htm", method= RequestMethod.GET)
    public String createcenter() {
        return "createcenter";
    }
    
    @RequestMapping(value = "/reports/allrep.htm", method= RequestMethod.GET)
    public String allrep() {
        return "allrep";
    }
    
    @RequestMapping(value = "/reports/clientsrep.htm", method= RequestMethod.GET)
    public String clientsrep() {
        return "clientsrep";
    }
    
    @RequestMapping(value = "/reports/loansrep.htm", method= RequestMethod.GET)
    public String loansrep() {
        return "loansrep";
    }
    
    @RequestMapping(value = "/reports/savingsrep.htm", method= RequestMethod.GET)
    public String savingsrep() {
        return "savingsrep";
    }
    
    @RequestMapping(value = "/reports/fundsrep.htm", method= RequestMethod.GET)
    public String fundsrep() {
        return "fundsrep";
    }
    
    @RequestMapping(value = "/reports/accountingrep.htm", method= RequestMethod.GET)
    public String accountingrep() {
        return "accountingrep";
    }
    
    
    @RequestMapping(value = "/admin/organization.htm", method= RequestMethod.GET)
    public String organization() {
        return "organization";
    }
    
    @RequestMapping(value = "/admin/system.htm", method= RequestMethod.GET)
    public String system() {
        return "system";
    }
    
    @RequestMapping(value = "/admin/products.htm", method= RequestMethod.GET)
    public String products() {
        return "products";
    }
    
    @RequestMapping(value = "/profile.htm", method= RequestMethod.GET)
    public String profile() {
        return "profile";
    }
    
    @RequestMapping(value = "/usersetting.htm", method= RequestMethod.GET)
    public String usersetting() {
        return "usersetting";
    }

    @RequestMapping(value = "/roi.htm", method= RequestMethod.GET)
    public String roi() {
        return "roi";
    }
    
    @RequestMapping(value = "/policyprocedure.htm", method= RequestMethod.GET)
    public String policyprocedure() {
        return "policyprocedure";
    }
    
    @RequestMapping(value = "/managefunds.htm", method= RequestMethod.GET)
    public String managefunds() {
        return "managefunds";
    }
    
    @RequestMapping(value = "/approvebulk.htm", method= RequestMethod.GET)
    public String approvebulk() {
        return "approvebulk";
    }

    @RequestMapping(value = "/manageroles.htm", method= RequestMethod.GET)
    public String manageroles() {
        return "manageroles";
    }
            
    @RequestMapping(value = "/charges.htm", method= RequestMethod.GET)
    public String charges() {
        return "charges";
    }
    
    @RequestMapping(value = "/viewroles.htm", method= RequestMethod.GET)
    public String viewroles() {
        return "viewroles";
    }
    
}
