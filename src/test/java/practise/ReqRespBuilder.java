package practise;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import GenericUtility.JavaUtility;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojoClass.RMGPojo;

public class ReqRespBuilder {
	
	@Test
	public void learnSpecBuilders() {
		JavaUtility jutil = new JavaUtility();
		RMGPojo rpj = new RMGPojo("Request", "Response"+jutil.getRandomNumber(), "Ongoing", 4);
		
		 RequestSpecification reqst = new RequestSpecBuilder()
		                .setBaseUri("http://rmgtestingserver:8084")
		                .setContentType(ContentType.JSON).build();
	
		ResponseSpecification resp = new ResponseSpecBuilder()
		              .expectContentType(ContentType.JSON)
		              .expectStatusCode(201).build();
		
		
		
		given().spec(reqst).body(rpj)
		.when().post("/addProject")
		.then().spec(resp).log().all();
		
	}
	
	
	@Test
	public void learnSpecBuilders_Arpan() {
		JavaUtility jutil = new JavaUtility();
		RMGPojo rpj = new RMGPojo("Request", "Response"+jutil.getRandomNumber(), "Ongoing", 4);
		
		RequestSpecBuilder reqstSB = new RequestSpecBuilder();
		RequestSpecification reqst = reqstSB.setBaseUri("http://rmgtestingserver:8084")
		                                    .setContentType(ContentType.JSON).build();
	
		ResponseSpecBuilder respSB = new ResponseSpecBuilder();
		ResponseSpecification resp = respSB.expectContentType(ContentType.JSON)
		                                   .expectStatusCode(201).build();
		
		
		
		given().spec(reqst).body(rpj)
		.when().post("/addProject")
		.then().spec(resp).log().all();
		
	}

}
