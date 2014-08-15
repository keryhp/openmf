package uk.ac.openmf.apis;

import java.util.ArrayList;
import java.util.List;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;

import uk.ac.openmf.model.OpenMFTask;
import uk.ac.openmf.model.OpenMFUser;
import uk.ac.openmf.model.nosql.OpenMFTasksNoSql;
import uk.ac.openmf.utils.OMFUtils;
import uk.ac.openmf.utils.ServletUtils;
import uk.ac.openmf.web.AppContext;

@Api(name="taskapi",version="v1", description="An API to manage tasks")
public class TaskServiceAPI {

	public static List<OpenMFTask> tasks = new ArrayList<OpenMFTask>();

	//dummy
	@ApiMethod(name="add")
	public OpenMFTask addOpenMFTask() throws Exception {
		//Check for already exists
		int index = tasks.indexOf(new OpenMFTasksNoSql(null));
		if (index != -1) throw new Exception("OpenMFTask Record already exists");
		OpenMFTask q = new OpenMFTasksNoSql(null);
		tasks.add(q);
		return q;
	}

	@ApiMethod(name="update")
	public OpenMFTask updateOpenMFTask(@Named("taskId") String taskId) throws Exception {
		OpenMFTask currentOpenMFTask = getOpenMFTask(taskId);
		currentOpenMFTask.setStatus(true);
		return currentOpenMFTask;
	}

	//dummy
	@ApiMethod(name="remove")
	public void removeOpenMFTask(@Named("id") Long id) throws Exception {
		int index = tasks.indexOf(new OpenMFTasksNoSql(null));
		if (index == -1)
			throw new Exception("OpenMFTask Record does not exist");
		tasks.remove(index);
	}

	@ApiMethod(name="list")
	public List<OpenMFTask> getOpenMFTasks() {
		return  OMFUtils.getAllTasks();
	}

	@ApiMethod(name="listByUsername")
	public List<OpenMFTask> getOpenMFTasksByUsername(@Named("omfuId") String omfuId) {
		List<OpenMFTask> results = new ArrayList<OpenMFTask>();
		OpenMFUser omfuser = null;
		if (omfuId != null) {
			omfuser = AppContext.getAppContext().getUserManager().getUser(ServletUtils.validateEventId(omfuId));
		}
		for (OpenMFTask task : OMFUtils.getTasksByUsername(omfuser.getUsername(), false)) {
			results.add(task);
		}
		return results;
	}

	@ApiMethod(name="getTask")
	public OpenMFTask getOpenMFTask(@Named("taskId") String taskId) throws Exception {
		return AppContext.getAppContext().getTasksManager().getTask(ServletUtils.validateEventId(taskId));
	}
}
