package uk.ac.openmf.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import uk.ac.openmf.model.OpenMFLoanAccount;
import uk.ac.openmf.model.OpenMFLoanProduct;
import uk.ac.openmf.model.OpenMFLoanRepayment;
import uk.ac.openmf.model.OpenMFLoanRepaymentManager;
import uk.ac.openmf.utils.OMFDateUtils;
import uk.ac.openmf.web.AppContext;


public class RepaymentService{

	public static String calcFlatInterestPartsOnLoanFrequency(String loanamount, String rateofinterest, String frequency, String periodinmonths){
		int frequencyVal = new Integer(frequency).intValue();
		double periodinmonthsVal = new Double(periodinmonths);
		//double partsperannum = Math.round(periodinmonthsVal * 30 /frequencyVal);
		double loanamountVal = new Double(loanamount);
		double rateofinterestVal = new Double(rateofinterest) / new Double(100.00);
		//freq = 30 days, repmntperiod = 12 month
		//partinterestamt = loanamount * rateofinterest / repmtperiod;
		//partpayable = loanamount/repmntperiod + partinterestamnt
		double partinterestamnt = loanamountVal * rateofinterestVal / periodinmonthsVal;
		//double partpayable = loanamountVal / periodinmonthsVal + partinterestamnt;
		Double interestamountpayableperfreq = 0.0;
		switch(frequencyVal){
		case 7: interestamountpayableperfreq = partinterestamnt/4;
		break;
		case 15: interestamountpayableperfreq = partinterestamnt/2;
		break;
		case 30: interestamountpayableperfreq = partinterestamnt;
		break;
		case 90: interestamountpayableperfreq = partinterestamnt * 3;
		break;
		default: interestamountpayableperfreq = partinterestamnt;
		break;
		}
		//return new Double(loanamountVal * rateofinterestVal / partsperannum).toString();
		return interestamountpayableperfreq.toString();
	}

	public static String calcPrincipalPartsDueOnLoan(String loanamount, String rateofinterest, String frequency, String periodinmonths, String fees, String penalties, String totalpaidamount){
		if(penalties == null)
			penalties = "0";
		if(totalpaidamount == null)
			totalpaidamount = "0";
		int frequencyVal = new Integer(frequency).intValue();
		double periodinmonthsVal = new Double(periodinmonths);
		double feesVal = new Double(fees);
		double penaltiesVal = new Double(penalties);
		double loanamountVal = new Double(loanamount);
		//double partsperannum = Math.round(periodinmonthsVal * 30/frequencyVal);
		double totalpaidamountVal = new Double(totalpaidamount);		
		Double interestAmntPerFreq = new Double(calcFlatInterestPartsOnLoanFrequency(loanamount, rateofinterest, frequency, periodinmonths));
		double interestfortotalPeriod = interestAmntPerFreq * periodinmonthsVal;
		double totalPrincipalDueonLoan = loanamountVal + feesVal + penaltiesVal + interestfortotalPeriod - totalpaidamountVal;
		double monthlyPrincipalDueonLoan = totalPrincipalDueonLoan/periodinmonthsVal;
		Double principalDueFreq = 0.00;
		switch (frequencyVal) {
		case 7: principalDueFreq = monthlyPrincipalDueonLoan/4;
		break;
		case 15: principalDueFreq = monthlyPrincipalDueonLoan/2;
		break;
		case 30: principalDueFreq = monthlyPrincipalDueonLoan;
		break;
		case 90: principalDueFreq = monthlyPrincipalDueonLoan * 3;
		break;
		default: principalDueFreq = monthlyPrincipalDueonLoan;
		break;

		}
		return principalDueFreq.toString();
	}

	public static String calcBalanceofLoan(String loanamount, String fees, String penalties, String totalamountrepaid){
		if(penalties == null)
			penalties = "0";
		if(totalamountrepaid == null)
			totalamountrepaid = "0";
		if(fees == null)
			fees = "0";
		double loanamountVal = new Double(loanamount);
		double feesVal = new Double(fees);
		double penaltiesVal = new Double(penalties);
		double totalamountrepaidVal = new Double(totalamountrepaid);
		return new Double((loanamountVal + feesVal + penaltiesVal)- totalamountrepaidVal).toString();
	}

	public static String calcPrincipalDue(String loanamount, String fees, String penalties, String totalamountrepaid, String balanceofloan){
		double loanamountVal = new Double(loanamount);
		if(totalamountrepaid == null)
			totalamountrepaid = "0";
		double totalamountrepaidVal = new Double(totalamountrepaid);
		//return new Double(loanamountVal - new Double(calcBalanceofLoan(loanamount, fees, penalties, totalamountrepaid))).toString();
		return new Double(loanamountVal - totalamountrepaidVal).toString();
	}

	public static String calcTotalNumberOfRepayments(String frequency, String periodinmonths, String numofpaymentsmissed){
		int frequencyVal = new Integer(frequency).intValue();
		int numofpaymentsmissedVal = new Integer(numofpaymentsmissed).intValue();
		double periodinmonthsVal = new Double(periodinmonths);
		return new Long(Math.round(periodinmonthsVal /frequencyVal) + numofpaymentsmissedVal).toString();
	}

	public static ArrayList<OpenMFLoanRepayment> generateRepaymentSchedule(OpenMFLoanAccount loanaccount, OpenMFLoanProduct loanproduct, String userId) throws ParseException{
		//get frequency and loan tenure from loan product
		//get start date from loan account and calc schedule

		int frequencyVal = new Integer(loanproduct.getRepaymentfrequency()).intValue();
		int repaymentperiodVal = new Integer(loanproduct.getRepaymentperiod()).intValue();
		//assumption is that the month has 30 days

		Calendar calendar = Calendar.getInstance();
		Date startdate = OMFDateUtils.formatter.parse(loanaccount.getLoanstartdate());
		calendar.set(startdate.getYear(), startdate.getMonth(), startdate.getDate());
		boolean flag = true;
		int temp = frequencyVal;
		int count = 0;
		ArrayList<OpenMFLoanRepayment> loanRepayments = new ArrayList<OpenMFLoanRepayment>();
		while(flag){
			count++;
			AppContext appContext = AppContext.getAppContext();
			OpenMFLoanRepaymentManager loanRepaymentManager = appContext.getLoanRepaymentManager();
			OpenMFLoanRepayment loanRepaymentSchedule = loanRepaymentManager.newLoanRepaymentSchedule(userId);
			calendar.add(Calendar.DATE, frequencyVal);
			temp += frequencyVal;
			if(temp > repaymentperiodVal * 30){
				flag = false;
			}
			String balanceofloan = calcBalanceofLoan(loanaccount.getDisbursedamount(), loanaccount.getFees(), loanaccount.getPenalties(), loanaccount.getTotalrepaymentamount());
			loanRepaymentSchedule.setBalanceoutstandingamount(balanceofloan);
			loanRepaymentSchedule.setClientId(loanaccount.getClientId());
			loanRepaymentSchedule.setDueamount(calcPrincipalPartsDueOnLoan(loanaccount.getDisbursedamount(), loanproduct.getRateofinterest(), loanproduct.getRepaymentfrequency(), loanproduct.getRepaymentperiod(), loanaccount.getFees(), loanaccount.getPenalties(), loanaccount.getTotalrepaymentamount()));
			loanRepaymentSchedule.setFees(loanaccount.getFees());
			loanRepaymentSchedule.setInterestamount(calcFlatInterestPartsOnLoanFrequency(loanaccount.getDisbursedamount(), loanproduct.getRateofinterest(), loanproduct.getRepaymentfrequency(), loanproduct.getRepaymentperiod()));
			loanRepaymentSchedule.setLoanaccountid(loanaccount.getId().toString());
			loanRepaymentSchedule.setPaid(false);
			loanRepaymentSchedule.setPaidamount(null);
			loanRepaymentSchedule.setPaiddate(null);
			loanRepaymentSchedule.setPenalties(loanaccount.getPenalties());
			loanRepaymentSchedule.setPrincipaldue(calcPrincipalDue(loanaccount.getDisbursedamount(), loanaccount.getFees(), loanaccount.getPenalties(), loanaccount.getTotalrepaymentamount(), balanceofloan));
			loanRepaymentSchedule.setScheduledate(OMFDateUtils.formatter.format(calendar.getTime()));
			loanRepaymentSchedule.setSerialnumber(new Integer(count).toString());
			loanRepaymentSchedule.setActive(true);
			loanRepaymentManager.upsertEntity(loanRepaymentSchedule);
			loanRepayments.add(loanRepaymentSchedule);
		}
		return loanRepayments;
	}

}
