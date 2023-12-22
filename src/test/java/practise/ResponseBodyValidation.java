package practise;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class ResponseBodyValidation {

	@Test
	public void staticResponseBodyValidation() {
		// pre-requisites
		String expData = "TY_PROJ_003";
		baseURI = "http://rmgtestingserver";
		port = 8084;

		// Step 1.Preconditions

		// Step 2.Actions
		Response resp = when().get("/projects");
		// Step 3.Validations
		String actData = resp.jsonPath().get("[0].projectId");
		Assert.assertEquals(actData, expData);
		System.out.println("Data Verified");

		resp.then().log().all();

	}

	@Test
	public void dynamiResponseBodyValidation() {
		// pre-requisites
		String expData = "TY_PROJ_998";
		baseURI = "http://rmgtestingserver";
		port = 8084;

		// Step 2.Actions
		Response resp = when().get("/projects");

		// Step 3.Validations
		List<String> allPid = resp.jsonPath().get("projectId");
		boolean flag = false;
		for (String pid : allPid) {
			if (pid.equals(expData)) {
				flag = true;
				System.out.println(pid);
				break;
			}

		}
		Assert.assertTrue(flag);
		System.out.println("Data Verified");

		resp.then().time(Matchers.lessThan(5000l), TimeUnit.MILLISECONDS).log().all();

	}

	@Test
	public void dynamiResponseBodyValidationReqres() {

		String expEmail = "tobias.funke@reqres.in";

		// Step 2.Actions
		Response resp = when().get("https://reqres.in/api/users?page=2");

		// Step 3.Validations
		List<String> allEmail = resp.jsonPath().get("data[*].email");
		boolean flag = false;

		for (String email : allEmail) {
			if (email.equals(expEmail)) {
				flag = true;
				System.out.println(email);
				break;
			}

		}
		Assert.assertTrue(flag);
		System.out.println("Data Verified");

		resp.then()
		.time(Matchers.lessThan(5000l), TimeUnit.MILLISECONDS);
		//.log().all();

	}

}
