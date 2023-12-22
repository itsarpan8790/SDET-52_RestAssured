package crudWithoutBDD;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CRUDRmgYantra {
	@Test
	public void createProject() {
		JSONObject job = new JSONObject();
		job.put("createdBy", "Shahrukh");
		job.put("projectName", "BGMI11");
		job.put("status", "Ongoing");
		job.put("teamSize", 5);

		RequestSpecification reqS = RestAssured.given();
		reqS.body(job);
		reqS.contentType(ContentType.JSON);
		Response resp = reqS.post("http://rmgtestingserver:8084/addProject");

		System.out.println(resp.asString());
		
		int statusCode = resp.getStatusCode();
		System.out.println("Status Code---> " + statusCode);
		Assert.assertEquals(statusCode, 201);
	}

	@Test
	public void getAllProject() {

		Response resp = RestAssured.get("http://rmgtestingserver:8084/projects");
		resp.then().log().all();
		int actRespCode = resp.getStatusCode();
		System.out.println("Status Code---> " + actRespCode);
	}

	@Test
	public void getSingleProject() {
		Response resp = RestAssured.get("http://rmgtestingserver:8084/projects/TY_PROJ_1169");
		resp.then().log().all();
		int actRespCode = resp.getStatusCode();
		System.out.println("Status Code---> " + actRespCode);
		Assert.assertEquals(200, actRespCode);
	}

	@Test
	public void updateProject() {
		JSONObject job = new JSONObject();
		job.put("createdBy", "SylvestorStallone");
		job.put("projectName", "PubG");
		job.put("status", "completed");
		job.put("teamSize", 4);

		RequestSpecification reqS = RestAssured.given();
		reqS.contentType(ContentType.JSON);
		reqS.body(job);
		Response resp = reqS.put("http://rmgtestingserver:8084/projects/TY_PROJ_1169");
		int actRespCode = resp.getStatusCode();
		System.out.println("Status Code---> " + actRespCode);
		Assert.assertEquals(200, actRespCode);
	}
	@Test
	public void deleteProject() {
		Response resp = RestAssured.delete("http://rmgtestingserver:8084/projects/TY_PROJ_1169");
		int actRespCode = resp.getStatusCode();
		System.out.println("Status Code---> " + actRespCode);
		Assert.assertEquals(204, actRespCode);
	
	}

}
