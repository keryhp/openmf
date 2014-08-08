package uk.ac.openmf.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import uk.ac.openmf.model.OpenMFLoanRepayment;
import uk.ac.openmf.model.OpenMFSavingsScheduledDeposit;
import uk.ac.openmf.model.OpenMFTask;
import uk.ac.openmf.utils.OMFDateUtils;
import uk.ac.openmf.utils.OMFUtils;
import uk.ac.openmf.utils.OpenMFConstants;
import uk.ac.openmf.web.AppContext;

public class TaskService {

	public static boolean generateScheduledTasks() throws ParseException{
		//today
		String forDate = OMFDateUtils.formatter.format(Calendar.getInstance().getTime());
		//create new tasks for tomorrow
		Calendar cal = new GregorianCalendar();
		Date forDateVal = OMFDateUtils.formatter.parse(forDate);
		cal.setTime(forDateVal);
		cal.add(Calendar.DATE, +1);
		Date tomoDateVal = cal.getTime();
		generateTodaysTasksForAllSchDeposits(OMFDateUtils.formatter.format(tomoDateVal));
		generateTodaysTasksForAllSchPayments(OMFDateUtils.formatter.format(tomoDateVal));	
		return true;
	}

	public static void generateTodaysTasksForAllSchDeposits(String forDate){
		ArrayList<OpenMFSavingsScheduledDeposit> schdeps = OMFUtils.getAllScheduledDepositsByDate(forDate);
		for (OpenMFSavingsScheduledDeposit dep : schdeps) {
			OpenMFTask openMFTask = AppContext.getAppContext().getTasksManager().newTask(OpenMFConstants.FIELD_VALUE_SYSTEM);
			openMFTask.setAccountnumber(dep.getSavingsaccountid());
			openMFTask.setAmount(dep.getPaidamount());
			openMFTask.setAssignedto(dep.getSupervisor());
			openMFTask.setCashier(null);
			openMFTask.setCollectiontype(OpenMFConstants.FIELD_VALUE_DEPOSIT);
			openMFTask.setCreatedById(OpenMFConstants.FIELD_VALUE_SYSTEM);
			openMFTask.setDateassigned(forDate);
			openMFTask.setDatecompleted("null");
			openMFTask.setDescription("Scheduled deposit collection");
			openMFTask.setNewclient(false);
			openMFTask.setStatus(false);
			int val = AppContext.getAppContext().getTasksManager().entityCount() + 1;
			openMFTask.setTaskId(new Integer(val).toString());
			openMFTask.setTimestamp(System.currentTimeMillis());
			AppContext.getAppContext().getTasksManager().upsertEntity(openMFTask);
		}
	}
	
	public static void generateTodaysTasksForAllSchPayments(String forDate){
		ArrayList<OpenMFLoanRepayment> schdeps = OMFUtils.getAllScheduledLoanRepaymentByDate(forDate);
		for (OpenMFLoanRepayment dep : schdeps) {
			OpenMFTask openMFTask = AppContext.getAppContext().getTasksManager().newTask(OpenMFConstants.FIELD_VALUE_SYSTEM);
			openMFTask.setAccountnumber(dep.getLoanaccountid());
			openMFTask.setAmount(dep.getPaidamount());
			openMFTask.setAssignedto(dep.getSupervisor());
			openMFTask.setCashier(null);
			openMFTask.setCollectiontype(OpenMFConstants.FIELD_VALUE_DEPOSIT);
			openMFTask.setCreatedById(OpenMFConstants.FIELD_VALUE_SYSTEM);
			openMFTask.setDateassigned(forDate);
			openMFTask.setDatecompleted("null");
			openMFTask.setDescription("Scheduled repayment collection");
			openMFTask.setNewclient(false);
			openMFTask.setStatus(false);
			int val = AppContext.getAppContext().getTasksManager().entityCount() + 1;
			openMFTask.setTaskId(new Integer(val).toString());
			openMFTask.setTimestamp(System.currentTimeMillis());
			AppContext.getAppContext().getTasksManager().upsertEntity(openMFTask);
		}
	}
	
}
