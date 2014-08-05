package uk.ac.openmf.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import uk.ac.openmf.model.OpenMFSavingsAccount;
import uk.ac.openmf.model.OpenMFSavingsProduct;
import uk.ac.openmf.model.OpenMFSavingsScheduledDeposit;
import uk.ac.openmf.model.OpenMFSavingsScheduledDepositManager;
import uk.ac.openmf.model.nosql.OpenMFUserNoSql;
import uk.ac.openmf.utils.OMFDateUtils;
import uk.ac.openmf.web.AppContext;

import com.google.appengine.api.datastore.DatastoreNeedIndexException;

public class ScheduledDepositService {

	public static ArrayList<OpenMFSavingsScheduledDeposit> generateSavingsScheduledDeposits(OpenMFSavingsAccount savingsaccount, OpenMFSavingsProduct savingsproduct, String userId, String initialDeposit) throws ParseException{
		//get frequency and savings tenure from savings product
		//get start date from savings account and calc schedule

		int frequencyVal = new Integer(savingsproduct.getDepositfrequency()).intValue();
		int tenureVal = new Integer(savingsproduct.getTenure()).intValue();
		//assumption is that the month has 30 days

		Calendar calendar = Calendar.getInstance();
		Date startdate = OMFDateUtils.formatter.parse(savingsaccount.getSavingsstartdate());
		calendar.setTime(startdate);
		boolean flag = true;
		int temp = frequencyVal;
		int count = 0;
		boolean depositFlag = false;
		Double initialDepositVal = 0.0;
		if(initialDeposit != null){
			depositFlag = true;
			initialDepositVal = new Double(initialDeposit);
		}

		ArrayList<OpenMFSavingsScheduledDeposit> savingsRepayments = new ArrayList<OpenMFSavingsScheduledDeposit>();
		while(flag){
			count++;
			AppContext appContext = AppContext.getAppContext();
			OpenMFSavingsScheduledDepositManager savingsRepaymentManager = appContext.getSavingsScheduledDepositManager();
			OpenMFSavingsScheduledDeposit savingsDepositSchedule = savingsRepaymentManager.newSavingsScheduledDeposits(userId);
			calendar.add(Calendar.DATE, frequencyVal);
			temp += frequencyVal;
			if(temp > tenureVal * 30){
				flag = false;
			}
			savingsDepositSchedule.setClientId(savingsaccount.getClientId());
			savingsDepositSchedule.setSavingsaccountid(savingsaccount.getId().toString());
			if(depositFlag){
				savingsDepositSchedule.setPaid(true);
				savingsDepositSchedule.setPaidamount(initialDepositVal.toString());
				savingsDepositSchedule.setPaiddate(savingsaccount.getSavingsstartdate());
				savingsDepositSchedule.setBalanceoutstandingamount(initialDepositVal.toString());
			}else{
				savingsDepositSchedule.setPaid(false);
				savingsDepositSchedule.setPaidamount(null);
				savingsDepositSchedule.setPaiddate(null);
				savingsDepositSchedule.setBalanceoutstandingamount(null);
			}
			savingsDepositSchedule.setScheduledate(OMFDateUtils.formatter.format(calendar.getTime()));
			savingsDepositSchedule.setSerialnumber(new Integer(count).toString());
			savingsDepositSchedule.setActive(true);
			savingsDepositSchedule.setInterestamount(null);
			savingsDepositSchedule.setSupervisor(savingsaccount.getSavingsofficer());
			savingsRepaymentManager.upsertEntity(savingsDepositSchedule);
			savingsRepayments.add(savingsDepositSchedule);			
		}
		return savingsRepayments;
	}
}