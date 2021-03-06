package uk.ac.openmf.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import uk.ac.openmf.model.OpenMFChartOfAccounts;
import uk.ac.openmf.model.OpenMFChartOfAccountsManager;
import uk.ac.openmf.model.OpenMFClient;
import uk.ac.openmf.model.OpenMFClientManager;
import uk.ac.openmf.model.OpenMFGeneralJournal;
import uk.ac.openmf.model.OpenMFGeneralJournalManager;
import uk.ac.openmf.model.OpenMFGeneralLedger;
import uk.ac.openmf.model.OpenMFGeneralLedgerManager;
import uk.ac.openmf.model.OpenMFGroup;
import uk.ac.openmf.model.OpenMFGroupManager;
import uk.ac.openmf.model.OpenMFLoanAccount;
import uk.ac.openmf.model.OpenMFLoanProduct;
import uk.ac.openmf.model.OpenMFLoanRepayment;
import uk.ac.openmf.model.OpenMFLoanRepaymentManager;
import uk.ac.openmf.model.OpenMFModelException;
import uk.ac.openmf.model.OpenMFPhoto;
import uk.ac.openmf.model.OpenMFPhotoManager;
import uk.ac.openmf.model.OpenMFRoles;
import uk.ac.openmf.model.OpenMFRolesManager;
import uk.ac.openmf.model.OpenMFSavingsAccount;
import uk.ac.openmf.model.OpenMFSavingsProduct;
import uk.ac.openmf.model.OpenMFSavingsProductManager;
import uk.ac.openmf.model.OpenMFSavingsScheduledDeposit;
import uk.ac.openmf.model.OpenMFSavingsScheduledDepositManager;
import uk.ac.openmf.model.OpenMFTask;
import uk.ac.openmf.model.OpenMFTransaction;
import uk.ac.openmf.model.OpenMFTransactionManager;
import uk.ac.openmf.model.OpenMFUser;
import uk.ac.openmf.model.OpenMFUserManager;
import uk.ac.openmf.model.nosql.OpenMFPhotoNoSql;
import uk.ac.openmf.web.AppContext;
import uk.ac.openmf.web.comparators.OpenMFLoanRepaymentComparator;
import uk.ac.openmf.web.comparators.OpenMFLoanRepaymentComparator.OpenMFLoanRepaymentComparatorKey;
import uk.ac.openmf.web.comparators.OpenMFSavingsScheduledDepositComparator;
import uk.ac.openmf.web.comparators.OpenMFSavingsScheduledDepositComparator.OpenMFSavingsScheduledDepositComparatorKey;
import uk.ac.openmf.web.controllers.ClientController;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.DatastoreNeedIndexException;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

/**
 * A utility class.
 *
 */
public final class OMFUtils {

	protected static final Logger logger =
			Logger.getLogger(ClientController.class.getCanonicalName());	

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
		logger.info("clientId" + clientId + " loanAccounts"  + loanAccounts);
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

	public static ArrayList<OpenMFClient> getClientsBySupervisor(String username){
		Iterable<OpenMFClient> clientsiter = AppContext.getAppContext().getClientManager().getClientsBySupervisor(username);
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

	public static ArrayList<OpenMFGroup> getGroupsBySupervisor(String username){
		Iterable<OpenMFGroup> groupsiter = AppContext.getAppContext().getGroupManager().getGroupsBySupervisor(username);
		ArrayList<OpenMFGroup> groups = new ArrayList<OpenMFGroup>();
		try {
			for (OpenMFGroup group : groupsiter) {
				groups.add(group);
			}
		} catch (DatastoreNeedIndexException e) {
			//log the error
		}
		return groups;
	}

	public static ArrayList<OpenMFTask> getTasksByUsername(String username, boolean status){
		Iterable<OpenMFTask> tasksiter = AppContext.getAppContext().getTasksManager().getTasksByUsername(username, status);
		ArrayList<OpenMFTask> tasks = new ArrayList<OpenMFTask>();
		try {
			for (OpenMFTask task : tasksiter) {
				tasks.add(task);
			}
		} catch (DatastoreNeedIndexException e) {
			//log the error
		}
		return tasks;
	}
	
	public static OpenMFTask getTaskByTaskId(String taskId){
		return AppContext.getAppContext().getTasksManager().getTask(ServletUtils.validateEventId(taskId));
	}

	public static ArrayList<OpenMFTask> getAllTasksByUsername(String username){
		Iterable<OpenMFTask> tasksiter = AppContext.getAppContext().getTasksManager().getAllTasksByUsername(username);
		ArrayList<OpenMFTask> tasks = new ArrayList<OpenMFTask>();
		try {
			for (OpenMFTask task : tasksiter) {
				tasks.add(task);
			}
		} catch (DatastoreNeedIndexException e) {
			//log the error
		}
		return tasks;
	}
	
	public static ArrayList<OpenMFTask> getAllTasks(){
		Iterable<OpenMFTask> tasksiter = AppContext.getAppContext().getTasksManager().getAllTasks();
		ArrayList<OpenMFTask> tasks = new ArrayList<OpenMFTask>();
		try {
			for (OpenMFTask task : tasksiter) {
				if(!task.isStatus())
					tasks.add(task);
			}
		} catch (DatastoreNeedIndexException e) {
			//log the error
		}
		return tasks;
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

	public static ArrayList<OpenMFChartOfAccounts> getAllCoAsList(){
		OpenMFChartOfAccountsManager coAManager = AppContext.getAppContext().getChartOfAccountsManager();
		Iterable<OpenMFChartOfAccounts> coasiter = coAManager.getAllChartOfAccounts();
		ArrayList<OpenMFChartOfAccounts> coas = new ArrayList<OpenMFChartOfAccounts>();
		try {
			for (OpenMFChartOfAccounts coa : coasiter) {
				coas.add(coa);
			}
		} catch (DatastoreNeedIndexException e) {
			//log error
		}
		return coas;
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

	public static ArrayList<OpenMFPhoto> getAllPhotoListByTypeId(Long typeId){
		OpenMFPhotoManager photoManager = AppContext.getAppContext().getPhotoManager();
		Iterable<OpenMFPhoto> photositer = photoManager.getPhoto(typeId);
		ArrayList<OpenMFPhoto> photos = new ArrayList<OpenMFPhoto>();
		try {
			for (OpenMFPhoto photo : photositer) {
				photos.add(photo);
			}
		} catch (DatastoreNeedIndexException e) {
			//log error
		}
		return photos;
	}

	public static OpenMFPhoto getPhotoByTypeId(Long typeId){
		Query qry = new Query(OpenMFPhoto.class.getSimpleName());
		qry.setFilter(FilterOperator.EQUAL.of(OpenMFConstants.FIELD_NAME_TYPEID, typeId));
		PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(qry);
		OpenMFPhoto photo = null; 
		//TODO add proper query
		for (Entity result : pq.asIterable()) {
			if(photo == null){
				photo = new OpenMFPhotoNoSql(result);
				photo.setCreatedById((String)result.getProperty(OpenMFConstants.FIELD_NAME_CREATEDBY));
				photo.setActive((boolean)result.getProperty(OpenMFConstants.FIELD_NAME_ACTIVE));
				photo.setBlobKey((BlobKey)result.getProperty(OpenMFConstants.FIELD_NAME_BLOB_KEY));
				photo.setTimestamp((long)result.getProperty(OpenMFConstants.FIELD_NAME_TIMESTAMP));
				photo.setType((String)result.getProperty(OpenMFConstants.FIELD_NAME_TYPE));
				photo.setTypeId((long)result.getProperty(OpenMFConstants.FIELD_NAME_TYPEID));
				break;
			}
		}
		return photo;
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

	@SuppressWarnings("unchecked")
	public static List<OpenMFLoanRepayment> getLoanRepaymentSchedulesForLoanAccountList(String loanproductid){
		OpenMFLoanRepaymentManager loanRepaymentManager = AppContext.getAppContext().getLoanRepaymentManager();
		Iterable<OpenMFLoanRepayment> schedulesiter = loanRepaymentManager.getLoanRepaymentSchedulesByLoanAccount(loanproductid);
		List schedules = new ArrayList<OpenMFLoanRepayment>();
		try {
			for (OpenMFLoanRepayment schedule : schedulesiter) {
				schedules.add(schedule);
			}
		} catch (DatastoreNeedIndexException e) {
			//log error
		}
		Collections.sort(schedules, new OpenMFLoanRepaymentComparator(OpenMFLoanRepaymentComparatorKey.serialnumber.toString(), OpenMFLoanRepaymentComparator.ComparatorSortOrder.asc.toString()));
		return schedules;
	}

	@SuppressWarnings("unchecked")
	public static List<OpenMFSavingsScheduledDeposit> getSavingsScheduledDepositList(){
		OpenMFSavingsScheduledDepositManager savingsScheduledDepositManager = AppContext.getAppContext().getSavingsScheduledDepositManager();
		Iterable<OpenMFSavingsScheduledDeposit> schedulesiter = savingsScheduledDepositManager.getAllSavingsScheduledDeposits();
		List schedules = new ArrayList<OpenMFSavingsScheduledDeposit>();
		try {
			for (OpenMFSavingsScheduledDeposit schedule : schedulesiter) {
				schedules.add(schedule);
			}
		} catch (DatastoreNeedIndexException e) {
			//log error
		}
		Collections.sort(schedules, new OpenMFLoanRepaymentComparator(OpenMFSavingsScheduledDepositComparatorKey.serialnumber.toString(), OpenMFSavingsScheduledDepositComparator.ComparatorSortOrder.asc.toString()));
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

	public static ArrayList<OpenMFGeneralJournal> getTodaysGeneralJournalByMFIAccType(String mfiaccounttype){
		OpenMFGeneralJournalManager generalJournalManager = AppContext.getAppContext().getGeneralJournalManager();
		Iterable<OpenMFGeneralJournal> entriesiter = generalJournalManager.getGeneralJournalByDate(mfiaccounttype, OMFDateUtils.formatter.format(Calendar.getInstance().getTime()));
		ArrayList<OpenMFGeneralJournal> entries = new ArrayList<OpenMFGeneralJournal>();
		try {
			for (OpenMFGeneralJournal entry : entriesiter) {
				entries.add(entry);
			}
		} catch (DatastoreNeedIndexException e) {
			//log error
		}
		return entries;
	}

	public static ArrayList<OpenMFGeneralLedger> getTodaysGeneralLedgerByMFIAccType(String mfiaccounttype){
		OpenMFGeneralLedgerManager generalLedgerManager = AppContext.getAppContext().getGeneralLedgerManager();
		Iterable<OpenMFGeneralLedger> entriesiter = generalLedgerManager.getGeneralLedgerByDate(mfiaccounttype, OMFDateUtils.formatter.format(Calendar.getInstance().getTime()));
		ArrayList<OpenMFGeneralLedger> entries = new ArrayList<OpenMFGeneralLedger>();
		try {
			for (OpenMFGeneralLedger entry : entriesiter) {
				entries.add(entry);
			}
		} catch (DatastoreNeedIndexException e) {
			//log error
		}
		return entries;
	}

	public static ArrayList<OpenMFTransaction> getTransactionByClientId(String clientId){
		OpenMFTransactionManager generalLedgerManager = AppContext.getAppContext().getTransactionManager();
		Iterable<OpenMFTransaction> entriesiter = generalLedgerManager.getTransactionsByClientId(clientId);
		ArrayList<OpenMFTransaction> entries = new ArrayList<OpenMFTransaction>();
		try {
			for (OpenMFTransaction entry : entriesiter) {
				entries.add(entry);
			}
		} catch (DatastoreNeedIndexException e) {
			//log error
		}
		return entries;
	}

	public static ArrayList<OpenMFSavingsScheduledDeposit> getTodaysScheduledDepositsByUser(String userId){
		//get all scheduled deposits by supervisor and todays date
		String today = OMFDateUtils.formatter.format(Calendar.getInstance().getTime());
		Iterable<OpenMFSavingsScheduledDeposit> schiter = AppContext.getAppContext().getSavingsScheduledDepositManager().getSavingsScheduledDepositBySupervisorAndDate(userId, today);
		ArrayList<OpenMFSavingsScheduledDeposit> schdep = new ArrayList<OpenMFSavingsScheduledDeposit>();
		try {
			for (OpenMFSavingsScheduledDeposit dep : schiter) {
				schdep.add(dep);
			}
		} catch (DatastoreNeedIndexException e) {
			//log error
		}
		return schdep;	
	}

	public static ArrayList<OpenMFSavingsScheduledDeposit> getAllScheduledDepositsByDate(String date){
		//get all scheduled deposits by todays date
		Iterable<OpenMFSavingsScheduledDeposit> schiter = AppContext.getAppContext().getSavingsScheduledDepositManager().getAllSavingsScheduledDepositByDate(date);
		ArrayList<OpenMFSavingsScheduledDeposit> schdep = new ArrayList<OpenMFSavingsScheduledDeposit>();
		try {
			for (OpenMFSavingsScheduledDeposit dep : schiter) {
				schdep.add(dep);
			}
		} catch (DatastoreNeedIndexException e) {
			//log error
		}
		return schdep;	
	}

	public static ArrayList<OpenMFLoanRepayment> getTodaysLoanRepaymentByUser(String userId){
		//get all scheduled repayment by supervisor and todays date
		String today = OMFDateUtils.formatter.format(Calendar.getInstance().getTime());
		Iterable<OpenMFLoanRepayment> schiter = AppContext.getAppContext().getLoanRepaymentManager().getScheduledRepaymentBySupervisorAndDate(userId, today);
		ArrayList<OpenMFLoanRepayment> schdep = new ArrayList<OpenMFLoanRepayment>();
		try {
			for (OpenMFLoanRepayment dep : schiter) {
				schdep.add(dep);
			}
		} catch (DatastoreNeedIndexException e) {
			//log error
		}
		return schdep;	
	}

	public static ArrayList<OpenMFLoanRepayment> getAllScheduledLoanRepaymentByDate(String date){
		//get all scheduled repayment by todays date
		Iterable<OpenMFLoanRepayment> schiter = AppContext.getAppContext().getLoanRepaymentManager().getAllScheduledRepaymentByDate(date);
		ArrayList<OpenMFLoanRepayment> schdep = new ArrayList<OpenMFLoanRepayment>();
		try {
			for (OpenMFLoanRepayment dep : schiter) {
				schdep.add(dep);
			}
		} catch (DatastoreNeedIndexException e) {
			//log error
		}
		return schdep;	
	}
}
