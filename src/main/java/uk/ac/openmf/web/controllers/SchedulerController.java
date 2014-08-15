package uk.ac.openmf.web.controllers;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.ac.openmf.model.OpenMFChartOfAccounts;
import uk.ac.openmf.model.OpenMFGeneralLedger;
import uk.ac.openmf.model.OpenMFTask;
import uk.ac.openmf.model.OpenMFUser;
import uk.ac.openmf.services.GeneralLedgerService;
import uk.ac.openmf.services.GenerateTaskService;
import uk.ac.openmf.utils.OMFUtils;
import uk.ac.openmf.utils.OpenMFConstants;
import uk.ac.openmf.web.AppContext;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SchedulerController {

	@RequestMapping(value = "/closingentries", method= RequestMethod.GET)
	public void closingentries(HttpServletRequest req) throws UnsupportedEncodingException, ParseException {
		if(GeneralLedgerService.updateLedger()){
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
				AppContext.getAppContext().getEmailServiceManager().sendEmail(user, sb, subject, AppContext.getAppContext().getUserManager().getUserByUsername(user.getSupervisor()));			
			}
		}else{
			StringBuilder sb = new StringBuilder();
			String subject = OpenMFConstants.FIELD_VALUE_CLOSINGENTRIESFAIL;
			sb.append("<html><body><br</br><p>There was an error in generating closing entries email. Please contact administrator! Thank you.</p><br></br></table></body></html>");
			for (OpenMFUser user : OMFUtils.getUsersList()) {
				AppContext.getAppContext().getEmailServiceManager().sendEmail(user, sb, subject, AppContext.getAppContext().getUserManager().getUserByUsername(user.getSupervisor()));			
			}
		}
	}

	@RequestMapping(value = "/generatetasks", method= RequestMethod.GET)
	public void generatetasks(HttpServletRequest req) throws UnsupportedEncodingException, ParseException {
		if(GenerateTaskService.generateScheduledTasks()){
			for (OpenMFUser user : OMFUtils.getUsersList()) {
				StringBuilder sb = new StringBuilder();
				String subject = OpenMFConstants.FIELD_VALUE_TASKLIST;
				ArrayList<OpenMFTask> tasks = OMFUtils.getTasksByUsername(user.getUsername(), false);
				if(tasks.size() > 0){
				sb.append("<html><body><br</br><p>The details of the all tasks assigned to you are as follows:</p><br></br>");
				sb.append("<h3>Your List of Pending Tasks:</h3>");
				sb.append("<table><th style=\"width:20%;\">TaskId</th><th style=\"width:20%;\">Collection Type</th><th style=\"width:20%;\">Date Assigned</th><th style=\"width:20%;\">Amount</th><th style=\"width:20%;\">Client Acc#</th>");
				for(OpenMFTask entryVal : tasks){
					if(entryVal != null){
						sb.append("<tr><td style=\"width:18%;\">");
						sb.append(entryVal.getTaskId());
						sb.append("</td><td style=\"width:18%;\">");
						sb.append(entryVal.getCollectiontype());
						sb.append("</td><td style=\"width:18%;\">");
						sb.append(entryVal.getDateassigned());
						sb.append("</td><td style=\"width:18%;\">");
						sb.append(entryVal.getAmount());
						sb.append("</td><td style=\"width:18%;\">");
						sb.append(entryVal.getAccountnumber());
						sb.append("</td></tr>");
					}
				}
				sb.append("<br />");
				sb.append("</table></body></html>");
				}else{
					sb.append("<html><body><br</br><p>The details of the all tasks assigned to you are as follows:</p><br></br>");
					sb.append("<h3>Your have no tasks pending.</h3>");
					sb.append("<br />");
					sb.append("</body></html>");
					
				}
				AppContext.getAppContext().getEmailServiceManager().sendEmail(user, sb, subject, AppContext.getAppContext().getUserManager().getUserByUsername(user.getSupervisor()));			
			}
		}else{
			StringBuilder sb = new StringBuilder();
			String subject = OpenMFConstants.FIELD_VALUE_TASKLISTFAIL;
			sb.append("<html><body><br</br><p>There was an error in generating closing entries email. Please contact administrator! Thank you.</p><br></br></table></body></html>");
			for (OpenMFUser user : OMFUtils.getUsersList()) {
				AppContext.getAppContext().getEmailServiceManager().sendEmail(user, sb, subject, AppContext.getAppContext().getUserManager().getUserByUsername(user.getSupervisor()));			
			}
		}
	}

}
