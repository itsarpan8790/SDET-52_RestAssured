package practise;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import GenericUtility.JavaUtility;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class Authentication {

	@Test
	public void basicAuth() {

		baseURI = "http://rmgtestingserver";
		port = 8084;

		given().auth().basic("rmgyantra", "rmgy@9999").when().get("/login").then().statusCode(202)
				.contentType(ContentType.JSON).log().all();
	}

	@Test
	public void basicAuthPreEmptive() {

		baseURI = "http://rmgtestingserver";
		port = 8084;

		given().auth().preemptive().basic("rmgyantra", "rmgy@9999").when().get("/login").then().statusCode(202)
				.contentType(ContentType.JSON).log().all();
	}
	
	@Test
	public void basicAuthDigestive() {

		baseURI = "http://rmgtestingserver";
		port = 8084;

		given().auth().digest("rmgyantra", "rmgy@9999")
		.when().get("/login")
		.then().statusCode(202)
				.contentType(ContentType.JSON).log().all();
	}
	
	@Test
	public void bearerToken() {
        
		JavaUtility jutil=new JavaUtility();
		JSONObject job = new JSONObject();
		job.put("name", "Dhoni"+jutil.getRandomNumber());

		baseURI = "https://api.github.com";
		
		given().auth().oauth2("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")
		.body(job).contentType(ContentType.JSON)
		.when().post("/user/repos")
		.then().assertThat().statusCode(201).contentType(ContentType.JSON)
		.log().all();
		//assertThat().statusCode(200).contentType(ContentType.JSON)--not able to validate
	}
	
	@Test
	public void oAuth2() {
		
		Response resp = given().formParam("client_id", "SuperMan")
		.formParam("client_secret", "762c6da58abbccb429d9dde79ef87e88")
		.formParam("grant_type", "client_credentials")
		.formParam("redirect_uri", "http://superman.com")
		.formParam("code", "authorization_code")
		
		.when().post("http://coop.apps.symfonycasts.com/token");
		resp.then().log().all();
		
		System.out.println("***************************");
		
		//Capturing Token
		String token = resp.jsonPath().get("access_token");
		System.out.println("Token Captured is-->"+token);
		
		System.out.println("***************************");
		//Creating  request with Captured Token
		
		//Request:1-get()
		baseURI="http://coop.apps.symfonycasts.com";
		
		given().auth().oauth2(token)
		.when().get("/api/me")
		.then().log().all();
		System.out.println("***************************");
		
		//Request:2-post()-Unlock the Barn
		given().auth().oauth2(token)
		.when().post("/api/USER_ID/barn-unlock")
		.then().log().all();
		System.out.println("***************************");
		
		//Request:3-post()--Put the Toilet Seat Down
		given().auth().oauth2(token)
		.when().post("/api/USER_ID/toiletseat-down")
		.then().log().all();	
		System.out.println("***************************");
		
		
		
	}
	

}
