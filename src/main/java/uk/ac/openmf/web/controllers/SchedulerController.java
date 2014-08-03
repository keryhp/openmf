package uk.ac.openmf.web.controllers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.ac.openmf.model.OpenMFChartOfAccounts;
import uk.ac.openmf.model.OpenMFGeneralLedger;
import uk.ac.openmf.model.OpenMFUser;
import uk.ac.openmf.services.GeneralLedgerService;
import uk.ac.openmf.utils.OMFUtils;
import uk.ac.openmf.utils.OpenMFConstants;
import uk.ac.openmf.web.AppContext;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SchedulerController {

	@RequestMapping(value = "/closingentries", method= RequestMethod.GET)
	public void assignroles(HttpServletRequest req) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		String subject = OpenMFConstants.FIELD_VALUE_CLOSINGENTRIES;
		sb.append("<html><body><br</br><p>The details of the daily closing are as follows:</p><br></br>");
		sb.append("<table><th style=\"width:20%;\">CoA Id</th><th style=\"width:20%;\">For Date</th><th style=\"width:20%;\">Balance Available</th><th style=\"width:20%;\">Balance Pending</th><th style=\"width:20%;\">Status</th>");
		Map<String, OpenMFGeneralLedger> ledgers = GeneralLedgerService.getTodaysGeneralLedgerAsList();
		for(Entry<String, OpenMFGeneralLedger> entry : ledgers.entrySet()){
			OpenMFGeneralLedger entryVal = entry.getValue(); 
			if(entryVal != null){
				sb.append("<tr><td style=\"width:18%;\">");
				sb.append(entryVal.getCoaid());
				sb.append("</td><td style=\"width:18%;\">");
				sb.append(entryVal.getForDate());
				sb.append("</td><td style=\"width:18%;\">");
				sb.append(entryVal.getBalanceavailable());
				sb.append("</td><td style=\"width:18%;\">");
				sb.append(entryVal.getBalancePending());
				sb.append("</td><td style=\"width:18%;\">");
				sb.append(entryVal.getStatus());
				sb.append("</td></tr>");
			}
		}
		sb.append("<br />");
		sb.append("<table><th style=\"width:20%;\">CoA Id</th><th style=\"width:20%;\">Funds</th><th style=\"width:20%;\">Account Type</th><th style=\"width:20%;\">CoA Name</th><th style=\"width:20%;\">Office</th>");
		ArrayList<OpenMFChartOfAccounts> coas = OMFUtils.getAllCoAsList();
		for(OpenMFChartOfAccounts coa : coas){
			sb.append("<tr><td style=\"width:18%;\">");
			sb.append(coa.getCoaid());
			sb.append("</td><td style=\"width:18%;\">");
			sb.append(coa.getFunds());
			sb.append("</td><td style=\"width:18%;\">");
			sb.append(coa.getMfiaccounttype());
			sb.append("</td><td style=\"width:18%;\">");
			sb.append(coa.getCoaname());
			sb.append("</td><td style=\"width:18%;\">");
			sb.append(coa.getOffice());
			sb.append("</td></tr>");
		}
		sb.append("</table></body></html>");
		for (OpenMFUser user : OMFUtils.getUsersList()) {
			AppContext.getAppContext().getEmailServiceManager().sendEmail(user, sb, subject);			
		}
	}
}
