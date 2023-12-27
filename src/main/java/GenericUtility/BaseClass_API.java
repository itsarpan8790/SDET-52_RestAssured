package GenericUtility;

import org.testng.annotations.BeforeSuite;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseClass_API {

	public DataBaseLibrary dbUtil=new DataBaseLibrary();
	public RestAssuredLibrary rUtil=new RestAssuredLibrary();
	public JavaUtility jUtil=new JavaUtility();
	
	public RequestSpecification request;
	public ResponseSpecification response;
	
	@BeforeSuite
	public void bsConfig() throws Throwable {
		dbUtil.connectToDB();
		
		RequestSpecBuilder reqSB = new RequestSpecBuilder();
		request = reqSB.setBaseUri("http://rmgtestingserver:8084")
        .setContentType(ContentType.JSON).build();
		
		ResponseSpecBuilder resSb = new ResponseSpecBuilder();
		response = resSb.expectContentType(ContentType.JSON).build();
		
	}
	
	public void afConfig() throws Throwable {
		dbUtil.closeDB();
	}
}
