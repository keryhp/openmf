package uk.ac.openmf.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import uk.ac.openmf.model.OpenMFChartOfAccounts;
import uk.ac.openmf.model.OpenMFGeneralJournal;
import uk.ac.openmf.model.OpenMFGeneralLedger;
import uk.ac.openmf.model.nosql.OpenMFUserNoSql;
import uk.ac.openmf.utils.GenerateAccountNumber;
import uk.ac.openmf.utils.OMFDateUtils;
import uk.ac.openmf.utils.OMFUtils;
import uk.ac.openmf.utils.OpenMFConstants;
import uk.ac.openmf.web.AppContext;

public class GeneralLedgerService {

	public static void updateLedger() throws ParseException{
		String forDate = OMFDateUtils.formatter.format(Calendar.getInstance().getTime());
		//get today's general ledger accounts
		Map<String, OpenMFGeneralLedger> ledgers = getTodaysGeneralLedgerAsList();
		//get today's journal entries
		ArrayList<OpenMFGeneralJournal> journals = getTodaysGeneralJournalAsList();
		Double tempVal = 0.0;
		//update today's ledger and reconcile the CoA balance
		for (OpenMFGeneralJournal journal : journals) {
			if(OpenMFConstants.FIELD_VALUE_DEBIT.equalsIgnoreCase(journal.getTransactiontype())){
				//get total debits
				if(journal.getTransactionamount() != null){
					OpenMFGeneralLedger ledger = ledgers.get(journal.getCoaid());
					if(ledger != null){
						if(ledger.getBalancePending() ==null){
							ledger.setBalancePending("0");
						}
						tempVal = new Double(ledger.getBalancePending()).doubleValue();
						tempVal += new Double(journal.getTransactionamount()).doubleValue();
						ledger.setBalancePending(tempVal.toString());
					}
				}
			}else if(OpenMFConstants.FIELD_VALUE_CREDIT.equalsIgnoreCase(journal.getTransactiontype())){
				//get total credits
				if(journal.getTransactionamount() != null){
					OpenMFGeneralLedger ledger = ledgers.get(journal.getCoaid());
					if(ledger != null){
						if(ledger.getBalanceavailable() ==null){
							ledger.setBalanceavailable("0");
						}
						tempVal = new Double(ledger.getBalanceavailable()).doubleValue();
						tempVal += new Double(journal.getTransactionamount()).doubleValue();
						ledger.setBalanceavailable(tempVal.toString());
					}
				}
			}
		}
		for (Entry<String, OpenMFGeneralLedger> entry : ledgers.entrySet()) {
			//close today's ledger 
			entry.getValue().setStatus(false);
			AppContext.getAppContext().getGeneralLedgerManager().upsertEntity(entry.getValue());
		}
		ArrayList<OpenMFChartOfAccounts> coas = OMFUtils.getAllCoAsList();
		for (OpenMFChartOfAccounts openMFChartOfAccounts : coas) {
			OpenMFGeneralLedger ledgerVal = ledgers.get(openMFChartOfAccounts.getCoaid());
			Double balAvail = new Double(ledgerVal.getBalanceavailable()).doubleValue();
			Double balPending = new Double(ledgerVal.getBalancePending()).doubleValue();
			openMFChartOfAccounts.setFunds(new Double(balAvail - balPending).toString());
		}

		//create a new ledger for tomorrow
		Calendar cal = new GregorianCalendar();
		Date forDateVal = OMFDateUtils.formatter.parse(forDate);
		cal.setTime(forDateVal);
		cal.set(Calendar.DAY_OF_YEAR, +1);
		Date tomoDateVal = cal.getTime();
		generateTodaysLedgerForAllCoAs(OMFDateUtils.formatter.format(tomoDateVal));
	}
	
	public static void generateTodaysLedgerForAllCoAs(String forDate){
		//get all CoAs
		//generate Ledger for each of the CoAs
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		OpenMFUserNoSql currentUser = (OpenMFUserNoSql)authentication.getPrincipal();
		ArrayList<OpenMFChartOfAccounts> coas = OMFUtils.getAllCoAsList();
		for (OpenMFChartOfAccounts openMFChartOfAccount : coas) {
			OpenMFGeneralLedger openMFGeneralLedger = AppContext.getAppContext().getGeneralLedgerManager().newGeneralLedger(currentUser.getId().toString());
			openMFGeneralLedger.setBalanceavailable(openMFChartOfAccount.getFunds());
			openMFGeneralLedger.setBalancePending(OpenMFConstants.FIELD_VALUE_ZERO);
			openMFGeneralLedger.setCoaid(openMFChartOfAccount.getCoaid());
			openMFGeneralLedger.setCreatedById(currentUser.getId().toString());
			int val = AppContext.getAppContext().getGeneralLedgerManager().entityCount() + 1;
			openMFGeneralLedger.setGeneralledgerid(GenerateAccountNumber.getAccNumberService().generateLedgerAccNumber(AppContext.getAppContext().getGeneralLedgerManager().entityCount() + 1));
			openMFGeneralLedger.setPostedby(currentUser.getId().toString());
			openMFGeneralLedger.setStatus(true);
			openMFGeneralLedger.setTimestamp(System.currentTimeMillis());
			openMFGeneralLedger.setTransactiontype(OpenMFConstants.FIELD_VALUE_SYSTEMRECONCILATION);
			openMFGeneralLedger.setForDate(forDate);
			AppContext.getAppContext().getGeneralLedgerManager().upsertEntity(openMFGeneralLedger);
		}
	}
	
	public static Map<String, OpenMFGeneralLedger> getTodaysGeneralLedgerAsList(){
		String today = OMFDateUtils.formatter.format(Calendar.getInstance().getTime());
		//get all CoAs
		//get todays Ledger for each of the CoAs
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		OpenMFUserNoSql currentUser = (OpenMFUserNoSql)authentication.getPrincipal();
		ArrayList<OpenMFChartOfAccounts> coas = OMFUtils.getAllCoAsList();
		Map<String, OpenMFGeneralLedger> ledgers = new HashMap<String, OpenMFGeneralLedger>();
		for (OpenMFChartOfAccounts coa : coas) {
			//get today's ledger account for each of CoA
			ledgers.put(coa.getCoaid(), AppContext.getAppContext().getGeneralLedgerManager().getGeneralLedgerByCoAandDate(coa.getCoaid(), today));
		}
		return ledgers;
	}
	
	public static ArrayList<OpenMFGeneralJournal> getTodaysGeneralJournalAsList(){
		String today = OMFDateUtils.formatter.format(Calendar.getInstance().getTime());
		//get all CoAs
		//get todays Ledger for each of the CoAs
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		OpenMFUserNoSql currentUser = (OpenMFUserNoSql)authentication.getPrincipal();
		ArrayList<OpenMFChartOfAccounts> coas = OMFUtils.getAllCoAsList();
		ArrayList<OpenMFGeneralJournal> journals = new ArrayList<OpenMFGeneralJournal>();
		for (OpenMFChartOfAccounts coa : coas) {
			//get today's ledger account for each of CoA
			journals.add(AppContext.getAppContext().getGeneralJournalManager().getGeneralJournalByCoAandDate(coa.getCoaid(), today));
		}
		return journals;
	}
}
