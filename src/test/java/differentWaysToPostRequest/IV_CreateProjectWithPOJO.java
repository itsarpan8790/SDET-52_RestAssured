package differentWaysToPostRequest;

import GenericUtility.JavaUtility;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;


import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class IV_CreateProjectWithPOJO {
	
	JavaUtility jUtil=new JavaUtility();
	@Test
	public void createProject() {
		
		//Creation Object Of PojoClass
		IV_UsingPOJO_RmgYantra pojo = new IV_UsingPOJO_RmgYantra("Arpan", "Pubg"+jUtil.getRandomNumber(), "Ongoing", 4);
		
		baseURI = "http://rmgtestingserver";
		port = 8084;
		
		//Step 1.PreConditions
		given().body(pojo).contentType(ContentType.JSON)
		//Step 2. Action
		.when().post("/addProject")
		//Step 3.Validation
		.then().assertThat()
		//Response Validation-Hamcrest
		.time(Matchers.lessThan(5000l), TimeUnit.MILLISECONDS)
		.statusCode(201).log().all();
		
		
		
	}
}
