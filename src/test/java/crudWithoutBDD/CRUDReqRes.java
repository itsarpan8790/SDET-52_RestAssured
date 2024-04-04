package crudWithoutBDD;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CRUDReqRes {
	@Test
	public void create() {
		JSONObject job = new JSONObject();
		job.put("name", "Khiladi 786");
		job.put("Job", "Tester");

		Response resp = RestAssured.given().contentType(ContentType.JSON).body(job)
				.post("https://reqres.in/api/users");

		resp.then().log().all();
		int respCode = resp.getStatusCode();
		String respText = resp.getStatusLine();

		System.out.println(respCode + "--> " + respText);
		Assert.assertEquals(respCode, 201);
		
		System.out.println("*****");

	}

	@Test(dependsOnMethods ="create" )
	public void getUser() {
		Response resp = RestAssured.get("https://reqres.in/api/users/2");

		int respCode = resp.getStatusCode();

		Assert.assertEquals(respCode, 200,"verified");
		
		//Experiment--Use of contains in TestNg
		System.out.println("content type is- "+resp.getContentType());
		String actCType = resp.getContentType();
		String expCType="application/json";
		boolean flag1=actCType.contains(expCType);
		Assert.assertTrue(flag1, "Verified Content type");
//  or
		if(actCType.contains(expCType)) {
			boolean flag2=true;
		}
		Assert.assertTrue(flag1);
		
	}

}
