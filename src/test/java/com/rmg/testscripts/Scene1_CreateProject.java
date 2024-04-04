package com.rmg.testscripts;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import GenericUtility.BaseClass_API;
import GenericUtility.EndPointsLibrary;
import io.restassured.response.Response;
import pojoClass.RMGPojo;

public class Scene1_CreateProject extends BaseClass_API {
	
	@Test
	public void createProject() throws Throwable {
		//Step 1.Prerequisites
	RMGPojo rp = new RMGPojo("Arpan", "Framework"+jUtil.getRandomNumber(), "created", 10);
	
	//Step 2.Send Request
	Response resp = given().log().all().spec(request).body(rp)
			.when().post(EndPointsLibrary.createProject);
	
	
	//Step 3.Capturing ProjectId
	String expectedData = rUtil.getJsonData(resp, "projectId"); //Framework23
	System.out.println(expectedData);
	
	//Step 4. Validate data in database
	String query="select * from project";
	String actData = dbUtil.readDataFromDBAndValidate(query, 1, expectedData);
	
	System.out.println("***********");
	Assert.assertEquals(actData, expectedData);
	System.out.println("Test case Passed and Verified");
	
	resp.then().log().all();
	
	//expected data=response data and actual data is D/B data
	
	
	
	
	
	
	
	
	
	
	}

}
