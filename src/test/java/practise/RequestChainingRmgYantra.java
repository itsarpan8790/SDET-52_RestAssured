package practise;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import GenericUtility.JavaUtility;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import pojoClass.RMGPojo;


public class RequestChainingRmgYantra {

	@Test
	public void requestChainPostAndGetSingle() {
		// pre requisites
		JavaUtility jUtil = new JavaUtility();
		baseURI = "http://rmgtestingserver";
		port = 8084;
		
		JSONObject job = new JSONObject();
		job.put("createdBy", "Sylvestor");
		job.put("projectName", "PUBG"+jUtil.getRandomNumber());
		job.put("status", "Ongoing");
		job.put("teamSize", 4);
		
		
		//Preconditions
		Response postResp = given()
				.body(job)
				.contentType(ContentType.JSON)
				
		//Actions
		.when().post("/addProject");
		 String pid4Get = postResp.jsonPath().get("projectId");
		 System.out.println(pid4Get);
		 
		//Validations
		 postResp.then()
		 .assertThat() //assertThat()-it isnot mandatory..only by luking to script one can know assertion starts
		 .time(Matchers.lessThan(5000l), TimeUnit.MILLISECONDS)
		 .statusCode(201).log().all();
		 
		 
		
		 System.out.println("**************************************");
		
		 //Step:4 Get Request
		
		 Response getResp = given().pathParam("projId1", pid4Get)
		.when().get("/projects/{projId1}");
		 String pid4Put=getResp.jsonPath().get("projectId");
		 getResp.then().statusCode(200).log().all();
		 
	System.out.println("**************************************");	
		
	//Step:5 Put request
	     RMGPojo rp = new RMGPojo("sachi", "pubg_1", "completed", 10);
		 Response putResp = given().pathParam("projId2", pid4Put).body(rp).contentType(ContentType.JSON)
		.when().put("projects/{projId2}");
		String pid4Delete = putResp.jsonPath().get("projectId");
		 putResp.then().statusCode(200).log().all();
		
		System.out.println(pid4Delete);
		System.out.println("**************************************");
		//Step:6 Delete Request
		
		given().pathParam("projId3", pid4Delete)
		.when().delete("projects/{projId3}")
		.then().statusCode(204).log().all();
		
	}

}
