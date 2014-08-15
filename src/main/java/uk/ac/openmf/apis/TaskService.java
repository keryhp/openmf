package uk.ac.openmf.apis;

import java.util.ArrayList;
import java.util.List;

import uk.ac.openmf.model.OpenMFTask;
import uk.ac.openmf.model.OpenMFUser;
import uk.ac.openmf.model.nosql.OpenMFTasksNoSql;
import uk.ac.openmf.utils.OMFUtils;
import uk.ac.openmf.utils.ServletUtils;
import uk.ac.openmf.web.AppContext;

public class TaskService {

	public static List<OpenMFTask> tasks = new ArrayList<OpenMFTask>();

	//dummy
	public OpenMFTask addOpenMFTask() throws Exception {
		//Check for already exists
		int index = tasks.indexOf(new OpenMFTasksNoSql(null));
		if (index != -1) throw new Exception("OpenMFTask Record already exists");
		OpenMFTask q = new OpenMFTasksNoSql(null);
		tasks.add(q);
		return q;
	}

	public OpenMFTask updateOpenMFTask(String taskId) throws Exception {
		OpenMFTask currentOpenMFTask = getOpenMFTask(taskId);
		currentOpenMFTask.setStatus(true);
		return currentOpenMFTask;
	}

	//dummy
	public void removeOpenMFTask(Long id) throws Exception {
		int index = tasks.indexOf(new OpenMFTasksNoSql(null));
		if (index == -1)
			throw new Exception("OpenMFTask Record does not exist");
		tasks.remove(index);
	}

	public List<OpenMFTask> getOpenMFTasks() {
		return OMFUtils.getAllTasks();
	}

	public List<OpenMFTask> getOpenMFTasksByUsername(String omfuId) {
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

	//dummy
	public OpenMFTask getOpenMFTask(String taskId) throws Exception {
		return AppContext.getAppContext().getTasksManager().getTask(ServletUtils.validateEventId(taskId));
	}

}
