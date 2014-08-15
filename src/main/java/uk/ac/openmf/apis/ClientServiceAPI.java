package uk.ac.openmf.apis;

import java.util.ArrayList;
import java.util.List;

import uk.ac.openmf.model.OpenMFClient;
import uk.ac.openmf.model.nosql.OpenMFClientNoSql;
import uk.ac.openmf.utils.OMFUtils;
import uk.ac.openmf.utils.ServletUtils;
import uk.ac.openmf.web.AppContext;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;

@Api(name="clientsapi",version="v1", description="An API to manage clients")
public class ClientServiceAPI {

	public static List<OpenMFClient> users = new ArrayList<OpenMFClient>();

	//dummy
	@ApiMethod(name="add")
	public OpenMFClient addOpenMFClient() throws Exception {
		//Check for already exists
		int index = users.indexOf(new OpenMFClientNoSql(null));
		if (index != -1) throw new Exception("OpenMFClient Record already exists");
		OpenMFClient q = new OpenMFClientNoSql(null);
		users.add(q);
		return q;
	}

	//dummy
	@ApiMethod(name="remove")
	public void removeOpenMFClient(@Named("id") Long id) throws Exception {
		int index = users.indexOf(new OpenMFClientNoSql(null));
		if (index == -1)
			throw new Exception("OpenMFClient Record does not exist");
		users.remove(index);
	}

	@ApiMethod(name="list")
	public List<OpenMFClient> getOpenMFClients() {
		return OMFUtils.getAllClientsList();
	}


	@ApiMethod(name="getClientById")
	public OpenMFClient getOpenMFClientsById(@Named("clientId") String clientId) {
		OpenMFClient client = null;
		if (clientId != null) {
			client = AppContext.getAppContext().getClientManager().getClient(ServletUtils.validateEventId(clientId));
		}
		return client;
	}
}
