package uk.ac.openmf.apis;

import java.util.ArrayList;
import java.util.List;

import uk.ac.openmf.model.OpenMFClient;
import uk.ac.openmf.model.nosql.OpenMFClientNoSql;
import uk.ac.openmf.utils.OMFUtils;
import uk.ac.openmf.utils.ServletUtils;
import uk.ac.openmf.web.AppContext;

public class ClientService {

	public static List<OpenMFClient> clients = new ArrayList<OpenMFClient>();

	//dummy
	public OpenMFClient addOpenMFClient() throws Exception {
		//Check for already exists
		int index = clients.indexOf(new OpenMFClientNoSql(null));
		if (index != -1) throw new Exception("OpenMFClient Record already exists");
		OpenMFClient q = new OpenMFClientNoSql(null);
		clients.add(q);
		return q;
	}

	//dummy
	public void removeOpenMFClient(Long id) throws Exception {
		int index = clients.indexOf(new OpenMFClientNoSql(null));
		if (index == -1)
			throw new Exception("OpenMFClient Record does not exist");
		clients.remove(index);
	}

	public List<OpenMFClient> getOpenMFClients() {
		return OMFUtils.getAllClientsList();
	}

	public OpenMFClient getOpenMFClientsById(String clientId) {
		OpenMFClient client = null;
		if (clientId != null) {
			client = AppContext.getAppContext().getClientManager().getClient(ServletUtils.validateEventId(clientId));
		}
		return client;
	}
}
