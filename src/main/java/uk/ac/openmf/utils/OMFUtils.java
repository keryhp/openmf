package uk.ac.openmf.utils;

import java.util.ArrayList;

import uk.ac.openmf.model.OpenMFClient;
import uk.ac.openmf.model.OpenMFClientManager;
import uk.ac.openmf.model.OpenMFGroup;
import uk.ac.openmf.model.OpenMFGroupManager;
import uk.ac.openmf.model.OpenMFLoanAccount;
import uk.ac.openmf.model.OpenMFLoanProduct;
import uk.ac.openmf.model.OpenMFLoanRepayment;
import uk.ac.openmf.model.OpenMFLoanRepaymentManager;
import uk.ac.openmf.model.OpenMFModelException;
import uk.ac.openmf.model.OpenMFRoles;
import uk.ac.openmf.model.OpenMFRolesManager;
import uk.ac.openmf.model.OpenMFSavingsAccount;
import uk.ac.openmf.model.OpenMFSavingsProduct;
import uk.ac.openmf.model.OpenMFSavingsProductManager;
import uk.ac.openmf.model.OpenMFSavingsScheduledDeposit;
import uk.ac.openmf.model.OpenMFSavingsScheduledDepositManager;
import uk.ac.openmf.model.OpenMFUser;
import uk.ac.openmf.model.OpenMFUserManager;
import uk.ac.openmf.web.AppContext;

import com.google.appengine.api.datastore.DatastoreNeedIndexException;

/**
 * A utility class.
 *
 */
public final class OMFUtils {

	private OMFUtils() {

	}

	/**
	 * Helper method to assert the condition is satisfied.
	 *
	 * @param condition the condition to assert.
	 * @param msg the message if condition is not satisfied.
	 */
	public static void assertTrue(boolean condition, String msg) {
		if (!condition) {
			if (msg != null) {
				throw new OpenMFModelException(msg);
			} else {
				throw new OpenMFModelException();
			}
		}
	}

	public static ArrayList<OpenMFUser> getUsersList(){
		OpenMFUserManager userManager = AppContext.getAppContext().getUserManager();
		Iterable<OpenMFUser> useriter = userManager.getAllUsers();
		ArrayList<OpenMFUser> omfusers= new ArrayList<OpenMFUser>();
		try {
			for (OpenMFUser user : useriter) {
				omfusers.add(user);
			}
		} catch (DatastoreNeedIndexException e) {
			//log error
		}
		return omfusers;
	}

	public static ArrayList<OpenMFLoanProduct> getLoanProductsList(){
		Iterable<OpenMFLoanProduct> loanProductiter = AppContext.getAppContext().getLoanProductManager().getAllLoanProduct();
		ArrayList<OpenMFLoanProduct> loanProducts = new ArrayList<OpenMFLoanProduct>();
		try {
			for (OpenMFLoanProduct loanProduct : loanProductiter) {
				loanProducts.add(loanProduct);
			}
		} catch (DatastoreNeedIndexException e) {
			//log the error
		}
		return loanProducts;
	}

	public static ArrayList<OpenMFSavingsProduct> getSavingsProductsList(){
		OpenMFSavingsProductManager savingsProductManager = AppContext.getAppContext().getSavingsProductManager();
		Iterable<OpenMFSavingsProduct> savingsProductiter = savingsProductManager
				.getAllSavingsProduct();
		ArrayList<OpenMFSavingsProduct> savingsProducts = new ArrayList<OpenMFSavingsProduct>();
		try {
			for (OpenMFSavingsProduct savingsProduct : savingsProductiter) {
				savingsProducts.add(savingsProduct);
			}
		} catch (DatastoreNeedIndexException e) {
			//log error
		}
		return savingsProducts;
	}

	public static ArrayList<OpenMFLoanAccount> getLoanAccountsList(){
		Iterable<OpenMFLoanAccount> loanAccountiter = AppContext.getAppContext().getLoanAccountManager().getAllLoanAccounts();
		ArrayList<OpenMFLoanAccount> loanAccounts = new ArrayList<OpenMFLoanAccount>();
		try {
			for (OpenMFLoanAccount loanAccount : loanAccountiter) {
				loanAccounts.add(loanAccount);
			}
		} catch (DatastoreNeedIndexException e) {
			//log the error
		}
		return loanAccounts;
	}

	public static ArrayList<OpenMFSavingsAccount> getSavingsAccountsList(){
		Iterable<OpenMFSavingsAccount> savingsiter = AppContext.getAppContext().getSavingsAccountManager().getAllSavingsAccounts();
		ArrayList<OpenMFSavingsAccount> savingsAccounts = new ArrayList<OpenMFSavingsAccount>();
		try {
			for (OpenMFSavingsAccount savingsAccount : savingsiter) {
				savingsAccounts.add(savingsAccount);
			}
		} catch (DatastoreNeedIndexException e) {
			//log the error
		}
		return savingsAccounts;
	}				
	
	public static ArrayList<OpenMFLoanAccount> getLoanAccountsByClientList(String clientId){
		Iterable<OpenMFLoanAccount> loanAccountiter = AppContext.getAppContext().getLoanAccountManager().getAllLoanAccountsByClient(clientId);
		ArrayList<OpenMFLoanAccount> loanAccounts = new ArrayList<OpenMFLoanAccount>();
		try {
			for (OpenMFLoanAccount loanAccount : loanAccountiter) {
				loanAccounts.add(loanAccount);
			}
		} catch (DatastoreNeedIndexException e) {
			//log the error
		}
		return loanAccounts;
	}
	
	public static ArrayList<OpenMFLoanAccount> getLoanAccountsByGroupList(String groupId){
		Iterable<OpenMFLoanAccount> loanAccountiter = AppContext.getAppContext().getLoanAccountManager().getAllLoanAccountsByGroup(groupId);
		ArrayList<OpenMFLoanAccount> loanAccounts = new ArrayList<OpenMFLoanAccount>();
		try {
			for (OpenMFLoanAccount loanAccount : loanAccountiter) {
				loanAccounts.add(loanAccount);
			}
		} catch (DatastoreNeedIndexException e) {
			//log the error
		}
		return loanAccounts;
	}

	public static ArrayList<OpenMFSavingsAccount> getSavingsAccountsByClientList(String clientId){
		Iterable<OpenMFSavingsAccount> savingsAccountiter = AppContext.getAppContext().getSavingsAccountManager().getAllSavingsAccountsByClient(clientId);
		ArrayList<OpenMFSavingsAccount> savingsAccounts = new ArrayList<OpenMFSavingsAccount>();
		try {
			for (OpenMFSavingsAccount savingsAccount : savingsAccountiter) {
				savingsAccounts.add(savingsAccount);
			}
		} catch (DatastoreNeedIndexException e) {
			//log the error
		}
		return savingsAccounts;
	}
	
	public static ArrayList<OpenMFClient> getClientsByGroupId(String groupid){
		Iterable<OpenMFClient> clientsiter = AppContext.getAppContext().getClientManager().getClientsByGroupId(groupid);
		ArrayList<OpenMFClient> clients = new ArrayList<OpenMFClient>();
		try {
			for (OpenMFClient client : clientsiter) {
				clients.add(client);
			}
		} catch (DatastoreNeedIndexException e) {
			//log the error
		}
		return clients;
	}
	
	public static ArrayList<OpenMFClient> getAllClientsList(){
		OpenMFClientManager clientManager = AppContext.getAppContext().getClientManager();
		Iterable<OpenMFClient> clientsiter = clientManager.getAllClients();
		ArrayList<OpenMFClient> clients = new ArrayList<OpenMFClient>();
		try {
			for (OpenMFClient client : clientsiter) {
				clients.add(client);
			}
		} catch (DatastoreNeedIndexException e) {
			//log error
		}
		return clients;
	}
	
	public static ArrayList<OpenMFGroup> getAllGroupsList(){
		OpenMFGroupManager groupManager = AppContext.getAppContext().getGroupManager();
		Iterable<OpenMFGroup> groupsiter = groupManager.getAllGroups();
		ArrayList<OpenMFGroup> groups = new ArrayList<OpenMFGroup>();
		try {
			for (OpenMFGroup group : groupsiter) {
				groups.add(group);
			}
		} catch (DatastoreNeedIndexException e) {
			//log error
		}
		return groups;
	}

	public static ArrayList<OpenMFRoles> getAllRolesList(){
		OpenMFRolesManager rolesManager = AppContext.getAppContext().getRolesManager();
		Iterable<OpenMFRoles> rolesiter = rolesManager.getAllRoles();
		ArrayList<OpenMFRoles> roles = new ArrayList<OpenMFRoles>();
		try {
			for (OpenMFRoles role : rolesiter) {
				roles.add(role);
			}
		} catch (DatastoreNeedIndexException e) {
			//log error
		}
		return roles;
	}

	public static ArrayList<OpenMFLoanRepayment> getLoanRepaymentSchedulesList(){
		OpenMFLoanRepaymentManager loanRepaymentManager = AppContext.getAppContext().getLoanRepaymentManager();
		Iterable<OpenMFLoanRepayment> schedulesiter = loanRepaymentManager.getAllLoanRepaymentSchedules();
		ArrayList<OpenMFLoanRepayment> schedules = new ArrayList<OpenMFLoanRepayment>();
		try {
			for (OpenMFLoanRepayment schedule : schedulesiter) {
				schedules.add(schedule);
			}
		} catch (DatastoreNeedIndexException e) {
			//log error
		}
		return schedules;
	}

	public static ArrayList<OpenMFLoanRepayment> getLoanRepaymentSchedulesForLoanAccountList(String loanproductid){
		OpenMFLoanRepaymentManager loanRepaymentManager = AppContext.getAppContext().getLoanRepaymentManager();
		Iterable<OpenMFLoanRepayment> schedulesiter = loanRepaymentManager.getLoanRepaymentSchedulesByLoanAccount(loanproductid);
		ArrayList<OpenMFLoanRepayment> schedules = new ArrayList<OpenMFLoanRepayment>();
		try {
			for (OpenMFLoanRepayment schedule : schedulesiter) {
				schedules.add(schedule);
			}
		} catch (DatastoreNeedIndexException e) {
			//log error
		}
		return schedules;
	}
	
	public static ArrayList<OpenMFSavingsScheduledDeposit> getSavingsScheduledDepositList(){
		OpenMFSavingsScheduledDepositManager savingsScheduledDepositManager = AppContext.getAppContext().getSavingsScheduledDepositManager();
		Iterable<OpenMFSavingsScheduledDeposit> schedulesiter = savingsScheduledDepositManager.getAllSavingsScheduledDeposits();
		ArrayList<OpenMFSavingsScheduledDeposit> schedules = new ArrayList<OpenMFSavingsScheduledDeposit>();
		try {
			for (OpenMFSavingsScheduledDeposit schedule : schedulesiter) {
				schedules.add(schedule);
			}
		} catch (DatastoreNeedIndexException e) {
			//log error
		}
		return schedules;
	}

	public static ArrayList<OpenMFSavingsScheduledDeposit> getSavingsScheduledDepositForSavingsAccountList(String loanproductid){
		OpenMFSavingsScheduledDepositManager savingsScheduledDepositManager = AppContext.getAppContext().getSavingsScheduledDepositManager();
		Iterable<OpenMFSavingsScheduledDeposit> schedulesiter = savingsScheduledDepositManager.getSavingsScheduledDepositBySavingsAccount(loanproductid);
		ArrayList<OpenMFSavingsScheduledDeposit> schedules = new ArrayList<OpenMFSavingsScheduledDeposit>();
		try {
			for (OpenMFSavingsScheduledDeposit schedule : schedulesiter) {
				schedules.add(schedule);
			}
		} catch (DatastoreNeedIndexException e) {
			//log error
		}
		return schedules;
	}
}
