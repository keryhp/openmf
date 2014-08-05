package uk.ac.openmf.model;


/**
 * Entity manager class to support roles datastore operations.
 *
 */
public interface OpenMFTasksManager extends OpenMFEntityManager<OpenMFTask> {

	OpenMFTask getTask(Long roleId);

	Iterable<OpenMFTask> getAllTasks();

	Iterable<OpenMFTask> getTasksByUsername(String username, boolean status);

	Iterable<OpenMFTask> getAllTasksByUsername(String username);

	/**
	 * Creates a new role object.
	 *
	 * @return a role entity.
	 */
	OpenMFTask newTask(String userId);
}
