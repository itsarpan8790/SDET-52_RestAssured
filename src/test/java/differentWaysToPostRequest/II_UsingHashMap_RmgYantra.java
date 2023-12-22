package differentWaysToPostRequest;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;
import static io.restassured.RestAssured.when;

import java.util.HashMap;

import org.testng.annotations.Test;

import GenericUtility.JavaUtility;
import io.restassured.http.ContentType;

public class II_UsingHashMap_RmgYantra {

	JavaUtility jUtil = new JavaUtility();

	@Test(priority = 1)
	public void createProject() {
		baseURI = "http://rmgtestingserver";
		port = 8084;

		HashMap<Object, Object> hm = new HashMap<Object, Object>();

		hm.put("createdBy", "UsingHashMap");
		hm.put("projectName", "Pubg" + jUtil.getRandomNumber());
		hm.put("status", "Ongoing");
		hm.put("teamSize", 5);

		// Step 1. Preconditions
		given().body(hm).contentType(ContentType.JSON)

				// Step 2.Actions
				.when().post("/addProject")

				// Step 3.Validations
				.then().assertThat().statusCode(201).contentType(ContentType.JSON).log().all();
	}
	
	
	@Test(dependsOnMethods ="createProject" )
	public void getSingleProject() {
		baseURI = "http://rmgtestingserver";
		port = 8084;
		
		// Step 2.Actions
		when().get("/projects/TY_PROJ_1297")
		// Step 3.Validations
		.then().assertThat().statusCode(200).contentType(ContentType.JSON).log().all();
		
	}
	

}
