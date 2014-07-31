package uk.ac.openmf.model.nosql;

import uk.ac.openmf.model.OpenMFClientManager;
import uk.ac.openmf.model.OpenMFEntityManagerFactory;
import uk.ac.openmf.model.OpenMFGroupManager;
import uk.ac.openmf.model.OpenMFLoanAccountManager;
import uk.ac.openmf.model.OpenMFLoanActualPaymentManager;
import uk.ac.openmf.model.OpenMFLoanDisburseManager;
import uk.ac.openmf.model.OpenMFLoanProductManager;
import uk.ac.openmf.model.OpenMFLoanRepaymentManager;
import uk.ac.openmf.model.OpenMFPhotoManager;
import uk.ac.openmf.model.OpenMFRolesManager;
import uk.ac.openmf.model.OpenMFSavingsAccountManager;
import uk.ac.openmf.model.OpenMFSavingsDepositManager;
import uk.ac.openmf.model.OpenMFSavingsProductManager;
import uk.ac.openmf.model.OpenMFSavingsScheduledDepositManager;
import uk.ac.openmf.model.OpenMFSavingsWithdrawalManager;
import uk.ac.openmf.model.OpenMFUserManager;
import uk.ac.openmf.web.ConfigManager;

/**
 * Entity manager factory implementation for NoSQL.
 *
 * @author harish
 */
public class OpenMFEntityManagerNoSqlFactory implements OpenMFEntityManagerFactory {

	private OpenMFUserManagerNoSql openMFUserManager;
	private OpenMFRolesManagerNoSql openMFRolesManager;
	private OpenMFLoanProductManagerNoSql openMFLoanProductManager;
	private OpenMFSavingsProductManagerNoSql openMFSavingsProductManager;
	private OpenMFClientManagerNoSql openMFClientManager;
	private OpenMFGroupManagerNoSql openMFGroupManager;
	private OpenMFLoanAccountManagerNoSql openMFLoanAccountManager;
	private OpenMFSavingsAccountManagerNoSql openMFSavingsAccountManager;
	private OpenMFLoanRepaymentManagerNoSql openMFLoanRepaymentManager;
	private OpenMFLoanActualPaymentManagerNoSql openMFLoanActualPaymentManager;
	private OpenMFLoanDisburseManagerNoSql openMFLoanDisburseManager;
	private OpenMFSavingsDepositManagerNoSql openMFSavingsDepositManager;
	private OpenMFSavingsWithdrawalManagerNoSql openMFSavingsWithdrawalManager;
	private OpenMFSavingsScheduledDepositManagerNoSql openMFSavingsScheduledDepositManager;
	private OpenMFPhotoManagerNoSql openMFPhotoManager;
	private boolean initialized;


	@Override
	public OpenMFUserManager getUserManager() {
		return openMFUserManager;
	}

	@Override
	public OpenMFRolesManager getRolesManager() {
		return openMFRolesManager;
	}

	@Override
	public OpenMFLoanProductManager getLoanProductManager() {
		return openMFLoanProductManager;
	}

	@Override
	public void init(ConfigManager configManager) {
		if (!initialized) {
			openMFUserManager = new OpenMFUserManagerNoSql();
			openMFRolesManager = new OpenMFRolesManagerNoSql(openMFUserManager);
			openMFLoanProductManager = new OpenMFLoanProductManagerNoSql(openMFUserManager);
			openMFClientManager = new OpenMFClientManagerNoSql(openMFUserManager);
			openMFGroupManager = new OpenMFGroupManagerNoSql(openMFUserManager);
			openMFSavingsProductManager = new OpenMFSavingsProductManagerNoSql(openMFUserManager);
			openMFLoanAccountManager = new OpenMFLoanAccountManagerNoSql(openMFUserManager); 
			openMFSavingsAccountManager = new OpenMFSavingsAccountManagerNoSql(openMFUserManager); 			
			openMFLoanRepaymentManager = new OpenMFLoanRepaymentManagerNoSql(openMFUserManager);
			openMFLoanActualPaymentManager = new OpenMFLoanActualPaymentManagerNoSql(openMFUserManager);
			openMFLoanDisburseManager = new OpenMFLoanDisburseManagerNoSql(openMFUserManager);
			openMFSavingsDepositManager = new OpenMFSavingsDepositManagerNoSql(openMFUserManager);
			openMFSavingsWithdrawalManager = new OpenMFSavingsWithdrawalManagerNoSql(openMFUserManager);
			openMFSavingsScheduledDepositManager = new OpenMFSavingsScheduledDepositManagerNoSql(openMFUserManager);
			openMFPhotoManager = new OpenMFPhotoManagerNoSql(openMFUserManager);
			initialized = true;
		} else {
			throw new IllegalStateException("Should not initialize the factory more than once.");
		}
	}

	@Override
	public OpenMFClientManager getClientManager() {
		return openMFClientManager;
	}
	
	@Override
	public OpenMFGroupManager getGroupManager() {
		return openMFGroupManager;
	}

	@Override
	public OpenMFSavingsProductManager getSavingsProductManager() {
		return openMFSavingsProductManager;
	}

	@Override
	public OpenMFLoanAccountManager getLoanAccountManager() {
		return openMFLoanAccountManager;
	}
	
	@Override
	public OpenMFSavingsAccountManager getSavingsAccountManager() {
		return openMFSavingsAccountManager;
	}
	
	@Override
	public OpenMFLoanRepaymentManager getLoanRepaymentManager() {
		return openMFLoanRepaymentManager;
	}
	
	@Override
	public OpenMFLoanActualPaymentManager getLoanActualPaymentManager() {
		return openMFLoanActualPaymentManager;
	}
	
	@Override
	public OpenMFLoanDisburseManager getLoanDisburseManager() {
		return openMFLoanDisburseManager;
	}
	
	@Override
	public OpenMFSavingsDepositManager getSavingsDepositManager() {
		return openMFSavingsDepositManager;
	}

	@Override
	public OpenMFSavingsWithdrawalManager getSavingsWithdrawalManager() {
		return openMFSavingsWithdrawalManager;
	}

	@Override
	public OpenMFSavingsScheduledDepositManager getSavingsScheduledDepositManager() {
		return openMFSavingsScheduledDepositManager;
	}

	@Override
	public OpenMFPhotoManager getPhotoManager() {
		return openMFPhotoManager;
	}
}
