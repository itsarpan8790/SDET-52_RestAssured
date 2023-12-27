package practise;

import static io.restassured.RestAssured.*;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ShoppersStackRequestChaining {
	
	@Test
	public void chaining() {
		baseURI="https://www.shoppersstack.com/shopping";
		
		//Login
		JSONObject job = new JSONObject();
		job.put("email", "itskhiladi007@gmail.com");
		job.put("password", "Ec@101017");
		job.put("role", "SHOPPER");
		
		Response resp = given().body(job).contentType(ContentType.JSON)
		.when().post("/users/login");
		//Capturing UserId and Token
	Object uid = resp.jsonPath().get("data.userId");
	String	token=resp.jsonPath().get("data.jwtToken");
	//Response Validation	
	resp.then().statusCode(200)
		.log().all();
	
	given().auth().oauth2(token)
	.when().get("/banks")
	.then().log().all();
	
	
		
		
		
		
		
	}

}
