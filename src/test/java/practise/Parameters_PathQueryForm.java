package practise;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import GenericUtility.JavaUtility;
import io.restassured.http.ContentType;
import pojoClass.ReqresPojo;

public class Parameters_PathQueryForm {
	
	@Test
	public void pathParamRmgYantra() {
		
		//pre requisites
		baseURI = "http://rmgtestingserver";
		port = 8084;
		
		//Preconditions
		given().pathParam("pid", "TY_PROJ_998")
		//Actions
		.when().get("/projects/{pid}")
		//Validations
		.then().assertThat().statusCode(200).log().all();
	}
	@Test
	public void FormParamRmgYantraExample() {
		/*it will not execute..
		 * just an example..
		 * as form parameter is not metioned in api*/
		
		//pre requisites
		JavaUtility jUtil=new JavaUtility();
		baseURI = "http://rmgtestingserver";
		port = 8084;
		
		given().formParam("createdBy", "Dinasour")
		.formParam("projectName", "Sian"+jUtil.getRandomNumber())
		.formParam("status", "ongoing")
		.formParam("teamSize", 4)
		.when().post("/addProject")
		.then().statusCode(200).log().all();
		
	}
	@Test
	public void queryParamReqres() {
		//pre requisites
		ReqresPojo rp = new ReqresPojo("Arpan", "Tech Analyst");
		
		//Preconditions
		given().queryParam("page", 2).body(rp).contentType(ContentType.JSON)
		//Actions
		.when().get("https://reqres.in/api/users")
		//Validation
		.then().statusCode(200).log().all();
		
		
		
	}

}
