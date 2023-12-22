package crudWithBDD;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import GenericUtility.JavaUtility;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
//Change project id for get and delete to work each method
public class CrudRmgYantra {
	JavaUtility jUtil = new JavaUtility();
	
	@Test(priority = 1)
	public void createProject() {
		baseURI="http://rmgtestingserver";
		port=8084;	
		
		JSONObject job = new JSONObject();
		job.put("createdBy", "Shahrukh");
		job.put("projectName", "BGMI" + jUtil.getRandomNumber());
		job.put("status", "Ongoing");
		job.put("teamSize", 5);

		// Step 1. Preconditions
		given().body(job).contentType(ContentType.JSON).log().all()

				// Step 2.Actions
				.when().post("/addProject")

				// Step 3.Validations
				.then().assertThat().statusCode(201).contentType(ContentType.JSON).log().all();
	}
	
	
	@Test(dependsOnMethods = "createProject",priority = 2)
	public void getSingleProject() {
		baseURI = "http://rmgtestingserver";
		port=8084;
		
		//Step 2.Actions
		when().get("/projects/TY_PROJ_1273")
		//Step 3.Preconditions
		.then().assertThat().statusCode(200).contentType(ContentType.JSON).log().all();
	}
	@Test(priority=3)
	public void updateProject() {
		JSONObject job = new JSONObject();
		job.put("createdBy", "Shahrukh");
		job.put("projectName", "BGMI" + jUtil.getRandomNumber());
		job.put("status", "Completed");
		job.put("teamSize", 4);
		
		//Step 1. Preconditions
		given().body(job).contentType(ContentType.JSON)
		
		//Step 2. Actions
		.when()
		.put("http://rmgtestingserver:8084/projects/TY_PROJ_1236")
		
		//Step 3.
		.then().assertThat().statusCode(200).contentType(ContentType.JSON).log().all();
		
		
		
	}
	@Test
	public void deleteProject() {
		//Step 2.Actions
		when().delete("http://rmgtestingserver:8084/projects/TY_PROJ_1273")
		//Step 3.
		.then().assertThat().statusCode(204).contentType(ContentType.JSON).log().all();
	}
	
	

}
