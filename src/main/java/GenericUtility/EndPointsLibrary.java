package GenericUtility;
/**
 * @author arpan
 * This Interface include all the endpoints
 */

public interface EndPointsLibrary {
	
	String createProject="/addProject";
	String getAllProjects="/projects";
	String getSingleProjects="/projects/"; ///project/projectID
	String updateProject="/projects/";
	String deleteProject="/projects/";

}
