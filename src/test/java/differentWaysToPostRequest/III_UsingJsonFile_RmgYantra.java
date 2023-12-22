package differentWaysToPostRequest;

import static io.restassured.RestAssured.*;

import java.io.File;

import io.restassured.http.ContentType;

public class III_UsingJsonFile_RmgYantra {
	
	public void createProject()throws Throwable {
		baseURI="http://rmgtestingserver";
		port=8084;
		
		File f=new File(".\\src\\test\\resources\\PractiseJsonFile.json");
		
		// Step 1. Preconditions
		given().body(f).contentType(ContentType.JSON)
		// Step 2.Actions
		.when().post("/addProject")
		// Step 3.Validations
		.then().assertThat().statusCode(201).contentType(ContentType.JSON).log().all();
	}

}
