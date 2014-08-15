package uk.ac.openmf.apis;

import java.util.ArrayList;
import java.util.List;

import uk.ac.openmf.model.OpenMFUser;
import uk.ac.openmf.model.nosql.OpenMFUserNoSql;
import uk.ac.openmf.utils.OMFUtils;
import uk.ac.openmf.utils.ServletUtils;
import uk.ac.openmf.web.AppContext;

public class UserService {

	public static List<OpenMFUser> quotes = new ArrayList<OpenMFUser>();

	//dummy
	public OpenMFUser addOpenMFUser() throws Exception {
		//Check for already exists
		int index = quotes.indexOf(new OpenMFUserNoSql(null));
		if (index != -1) throw new Exception("OpenMFUser Record already exists");
		OpenMFUser q = new OpenMFUserNoSql(null);
		quotes.add(q);
		return q;
	}

	//dummy
	public void removeOpenMFUser(Long id) throws Exception {
		int index = quotes.indexOf(new OpenMFUserNoSql(null));
		if (index == -1)
			throw new Exception("OpenMFUser Record does not exist");
		quotes.remove(index);
	}

	public List<OpenMFUser> getOpenMFUsers() {
		return OMFUtils.getUsersList();
	}

	public OpenMFUser getOpenMFUsersById(String omfuId) {
		OpenMFUser omfuser = null;
		if (omfuId != null) {
			omfuser = AppContext.getAppContext().getUserManager().getUser(ServletUtils.validateEventId(omfuId));
		}
		return omfuser;
	}
}
