package uk.ac.openmf.apis;

import java.util.ArrayList;
import java.util.List;

import uk.ac.openmf.model.OpenMFUser;
import uk.ac.openmf.model.nosql.OpenMFUserNoSql;
import uk.ac.openmf.utils.OMFUtils;
import uk.ac.openmf.utils.ServletUtils;
import uk.ac.openmf.web.AppContext;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;

@Api(name="usersapi",version="v1", description="An API to manage users")
public class UserServiceAPI {

	public static List<OpenMFUser> users = new ArrayList<OpenMFUser>();

	//dummy
	@ApiMethod(name="add")
	public OpenMFUser addOpenMFUser() throws Exception {
		//Check for already exists
		int index = users.indexOf(new OpenMFUserNoSql(null));
		if (index != -1) throw new Exception("OpenMFUser Record already exists");
		OpenMFUser q = new OpenMFUserNoSql(null);
		users.add(q);
		return q;
	}

	//dummy
	@ApiMethod(name="remove")
	public void removeOpenMFUser(@Named("id") Long id) throws Exception {
		int index = users.indexOf(new OpenMFUserNoSql(null));
		if (index == -1)
			throw new Exception("OpenMFUser Record does not exist");
		users.remove(index);
	}

	@ApiMethod(name="list")
	public List<OpenMFUser> getOpenMFUsers() {
		return OMFUtils.getUsersList();
	}


	@ApiMethod(name="getUserById")
	public OpenMFUser getOpenMFUsersById(@Named("omfuId") String omfuId) {
		OpenMFUser omfuser = null;
		if (omfuId != null) {
			omfuser = AppContext.getAppContext().getUserManager().getUser(ServletUtils.validateEventId(omfuId));
		}
		return omfuser;
	}
}
