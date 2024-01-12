package RestAssuredMock;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class ReqChainingReqres {

	@Test
	public void requestChainReqres() {

		baseURI = "https://reqres.in/";

		//ListUsers
		Response resp = when().get("/api/users?page=2");
		int data = resp.jsonPath().get("data[0].id"); //7
		System.out.println(data);
		resp.then().statusCode(200).log().all();
		
		System.out.println("*************************");
		
		//SingleUser
		given().pathParam("id", data)
		.when().get("/api/users/{id}")
		.then().assertThat().statusCode(200).log().all();
		
	}

}
