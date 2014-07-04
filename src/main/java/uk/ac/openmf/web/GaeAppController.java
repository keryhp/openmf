package uk.ac.openmf.web;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author harish
 *
 */
@Controller
public class GaeAppController {

    @RequestMapping(value = "/", method= RequestMethod.GET)
    public String landing() {
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

        String logoutUrl = UserServiceFactory.getUserService().createLogoutURL("/loggedout.htm");

        response.sendRedirect(logoutUrl);
    }

    @RequestMapping(value = "/loggedout.htm", method= RequestMethod.GET)
    public String loggedOut() {
        return "loggedout";
    }
    
    @RequestMapping(value = "/clients.htm", method= RequestMethod.GET)
    public String clients() {
        return "clients";
    }
    
    @RequestMapping(value = "/createclient.htm", method= RequestMethod.GET)
    public String createClient() {
        return "createclient";
    }
    
    @RequestMapping(value = "/viewclient.htm", method= RequestMethod.GET)
    public String viewClient() {
        return "viewclient";
    }
    
    @RequestMapping(value = "/viewloanaccount.htm", method= RequestMethod.GET)
    public String viewLoanAccount() {
        return "viewloanaccount";
    }
}
