package uk.ac.openmf.model.nosql;

import uk.ac.openmf.model.OpenMFClientManager;
import uk.ac.openmf.model.OpenMFEntityManagerFactory;
import uk.ac.openmf.model.OpenMFLoanProductManager;
import uk.ac.openmf.model.OpenMFRolesManager;
import uk.ac.openmf.model.OpenMFSavingsProductManager;
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
      openMFSavingsProductManager = new OpenMFSavingsProductManagerNoSql(openMFUserManager);
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
public OpenMFSavingsProductManager getSavingsProductManager() {
	return openMFSavingsProductManager;
}

}
