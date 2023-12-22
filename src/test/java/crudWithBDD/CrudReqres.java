package crudWithBDD;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class CrudReqres {
	@Test
	public void create() {

		baseURI = "https://reqres.in/";

		JSONObject job = new JSONObject();
		job.put("name", "Khiladi 786");
		job.put("Job", "Tester");

		given().body(job).contentType(ContentType.JSON)
		.when().post("/api/users")
		.then().statusCode(201).log().all();
	}
	@Test
	public void getSingleUser() {

		baseURI = "https://reqres.in/";

		when().get("/api/users/2")
		.then().statusCode(200).log().all();
		
	}
	
	@Test
	public void update() {

		baseURI = "https://reqres.in/";

		JSONObject job = new JSONObject();
		job.put("name", "Khiladi 786");
		job.put("Job", "AutomationTester");

		given().body(job).contentType(ContentType.JSON)
		.when().put("/api/users/2")
		.then().statusCode(200).log().all();
	}
	
	@Test
	public void delete() {
		baseURI = "https://reqres.in/";
		
		when().delete("/api/users/2")
		.then().statusCode(204).log().all();
	}
	
		
		
}
