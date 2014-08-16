package uk.ac.openmf.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.ac.openmf.model.OpenMFUser;
import uk.ac.openmf.model.nosql.OpenMFEntityManagerNoSql;
import uk.ac.openmf.utils.OMFDateUtils;
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
		req.setAttribute("currentUser", currentUser);
		logger.info("User details:" + currentUser.toString());
		List<String> clientStat = AppContext.getAppContext().getClientManager().getClientStat();//0,1
		//2,3
		clientStat.add(AppContext.getAppContext().getSavingsScheduledDepositManager().getTotalScheduledSavingsDepositAmtByDates(OMFDateUtils.getDateFromTimestamp(OMFDateUtils.getThisWeekStartDate()), OMFDateUtils.getDateFromTimestamp(System.currentTimeMillis())));
		clientStat.add(AppContext.getAppContext().getSavingsScheduledDepositManager().getTotalScheduledSavingsDepositAmtByDates(OMFDateUtils.getDateFromTimestamp(OMFDateUtils.getPrevWeekStartDate()), OMFDateUtils.getDateFromTimestamp(OMFDateUtils.getThisWeekStartDate())));
		//4,5
		clientStat.add(AppContext.getAppContext().getLoanRepaymentManager().getTotalScheduledRepmntAmtByDates(OMFDateUtils.getDateFromTimestamp(OMFDateUtils.getThisWeekStartDate()), OMFDateUtils.getDateFromTimestamp(System.currentTimeMillis())));
		clientStat.add(AppContext.getAppContext().getLoanRepaymentManager().getTotalScheduledRepmntAmtByDates(OMFDateUtils.getDateFromTimestamp(OMFDateUtils.getPrevWeekStartDate()), OMFDateUtils.getDateFromTimestamp(OMFDateUtils.getThisWeekStartDate())));
		req.setAttribute("clientStat", clientStat);
		
		List<String> amountStat = new ArrayList<String>();
		amountStat.add(AppContext.getAppContext().getSavingsDepositManager().getTotalSavingsDepositAmtByDates(OMFDateUtils.getDateFromTimestamp(OMFDateUtils.getThisWeekStartDate()), OMFDateUtils.getDateFromTimestamp(System.currentTimeMillis())));
		amountStat.add(AppContext.getAppContext().getSavingsDepositManager().getTotalSavingsDepositAmtByDates(OMFDateUtils.getDateFromTimestamp(OMFDateUtils.getPrevWeekStartDate()), OMFDateUtils.getDateFromTimestamp(OMFDateUtils.getThisWeekStartDate())));
		amountStat.add(AppContext.getAppContext().getLoanActualPaymentManager().getTotalActualPaymentAmtByDates(OMFDateUtils.getDateFromTimestamp(OMFDateUtils.getThisWeekStartDate()), OMFDateUtils.getDateFromTimestamp(System.currentTimeMillis())));
		amountStat.add(AppContext.getAppContext().getLoanActualPaymentManager().getTotalActualPaymentAmtByDates(OMFDateUtils.getDateFromTimestamp(OMFDateUtils.getPrevWeekStartDate()), OMFDateUtils.getDateFromTimestamp(OMFDateUtils.getThisWeekStartDate())));
		amountStat.add(AppContext.getAppContext().getSavingsWithdrawalManager().getTotalSavingsWithdrawalAmtByDates(OMFDateUtils.getDateFromTimestamp(OMFDateUtils.getThisWeekStartDate()), OMFDateUtils.getDateFromTimestamp(System.currentTimeMillis())));
		amountStat.add(AppContext.getAppContext().getSavingsWithdrawalManager().getTotalSavingsWithdrawalAmtByDates(OMFDateUtils.getDateFromTimestamp(OMFDateUtils.getPrevWeekStartDate()), OMFDateUtils.getDateFromTimestamp(OMFDateUtils.getThisWeekStartDate())));
		amountStat.add(AppContext.getAppContext().getLoanDisburseManager().getTotalLoanDisburseAmtByDates(OMFDateUtils.getDateFromTimestamp(OMFDateUtils.getThisWeekStartDate()), OMFDateUtils.getDateFromTimestamp(System.currentTimeMillis())));
		amountStat.add(AppContext.getAppContext().getLoanDisburseManager().getTotalLoanDisburseAmtByDates(OMFDateUtils.getDateFromTimestamp(OMFDateUtils.getPrevWeekStartDate()), OMFDateUtils.getDateFromTimestamp(OMFDateUtils.getThisWeekStartDate())));		
		req.setAttribute("amountStat", amountStat);
		
        return "landing";
    }
    
    @RequestMapping(value = "/landing", method= RequestMethod.GET)
    public String showLanding() {
        return "landing";
    }

    @RequestMapping(value = "/home", method= RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/disabled", method= RequestMethod.GET)
    public String disabled() {
        return "disabled";
    }

    @RequestMapping(value = "/logout", method= RequestMethod.GET)
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();

        String logoutUrl = UserServiceFactory.getUserService().createLogoutURL("/register");

        response.sendRedirect(logoutUrl);
    }

    @RequestMapping(value = "/loggedout", method= RequestMethod.GET)
    public String loggedOut() {
        return "loggedout";
    }
    
    @RequestMapping(value = "/help", method= RequestMethod.GET)
    public String help() {
        return "help";
    }
    
    @RequestMapping(value = "/advsearch", method= RequestMethod.GET)
    public String advsearch() {
        return "advsearch";
    }
    
    @RequestMapping(value = "/offices", method= RequestMethod.GET)
    public String offices() {
        return "advsearch";
    }
    
    @RequestMapping(value = "/tasks", method= RequestMethod.GET)
    public String tasks() {
        return "tasks";
    }
    
    @RequestMapping(value = "/entercollectionsheet", method= RequestMethod.GET)
    public String entercollectionsheet() {
        return "entercollectionsheet";
    }
    
    @RequestMapping(value = "/freqposting", method= RequestMethod.GET)
    public String freqposting() {
        return "freqposting";
    }
    
    @RequestMapping(value = "/journalentry", method= RequestMethod.GET)
    public String journalentry() {
        return "journalentry";
    }
    
    @RequestMapping(value = "/accountsclosure", method= RequestMethod.GET)
    public String accountsclosure() {
        return "accountsclosure";
    }
    
    @RequestMapping(value = "/createcenter", method= RequestMethod.GET)
    public String createcenter() {
        return "createcenter";
    }
    
    @RequestMapping(value = "/reports/allrep", method= RequestMethod.GET)
    public String allrep() {
        return "allrep";
    }
    
    @RequestMapping(value = "/reports/loansrep", method= RequestMethod.GET)
    public String loansrep() {
        return "loansrep";
    }
    
    @RequestMapping(value = "/reports/savingsrep", method= RequestMethod.GET)
    public String savingsrep() {
        return "savingsrep";
    }
    
    @RequestMapping(value = "/reports/fundsrep", method= RequestMethod.GET)
    public String fundsrep() {
        return "fundsrep";
    }
    
    @RequestMapping(value = "/reports/accountingrep", method= RequestMethod.GET)
    public String accountingrep() {
        return "accountingrep";
    }
    
    
    @RequestMapping(value = "/admin/organization", method= RequestMethod.GET)
    public String organization() {
        return "organization";
    }
    
    @RequestMapping(value = "/admin/system", method= RequestMethod.GET)
    public String system() {
        return "system";
    }
    
    @RequestMapping(value = "/admin/products", method= RequestMethod.GET)
    public String products() {
        return "products";
    }
    
    @RequestMapping(value = "/profile", method= RequestMethod.GET)
    public String profile() {
        return "profile";
    }
    
    @RequestMapping(value = "/usersetting", method= RequestMethod.GET)
    public String usersetting() {
        return "usersetting";
    }

    @RequestMapping(value = "/roi", method= RequestMethod.GET)
    public String roi() {
        return "roi";
    }
    
    @RequestMapping(value = "/policyprocedure", method= RequestMethod.GET)
    public String policyprocedure() {
        return "policyprocedure";
    }
    
    @RequestMapping(value = "/managefunds", method= RequestMethod.GET)
    public String managefunds() {
        return "managefunds";
    }
    
    @RequestMapping(value = "/approvebulk", method= RequestMethod.GET)
    public String approvebulk() {
        return "approvebulk";
    }

    @RequestMapping(value = "/manageroles", method= RequestMethod.GET)
    public String manageroles() {
        return "manageroles";
    }
            
    @RequestMapping(value = "/charges", method= RequestMethod.GET)
    public String charges() {
        return "charges";
    }    
}
