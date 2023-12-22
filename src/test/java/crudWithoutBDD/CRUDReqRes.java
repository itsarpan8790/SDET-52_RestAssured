package crudWithoutBDD;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CRUDReqRes {
	@Test
	public void create() {
		JSONObject job = new JSONObject();
		job.put("name", "Khiladi 786");
		job.put("Job", "Tester");

		Response resp = RestAssured.given().contentType(ContentType.JSON).body(job).post("https://reqres.in/api/users");

		resp.then().log().all();
		int respCode = resp.getStatusCode();
		String respText = resp.getStatusLine();

		System.out.println(respCode + " " + respText);
		Assert.assertEquals(respCode, 201);

	}

	@Test
	public void getUser() {
		Response resp = RestAssured.get("https://reqres.in/api/users/2");

		int respCode = resp.getStatusCode();

		Assert.assertEquals(respCode, 200);

	}

}
