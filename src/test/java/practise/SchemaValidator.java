package practise;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

public class SchemaValidator {
	
	@Test
	public void schemaChekReqres() {
		
		File schemaFile=new File(".\\src\\test\\resources\\schemaChekReqres.json");
		
		when().get("https://reqres.in/api/users/2")
		.then().body(JsonSchemaValidator.matchesJsonSchema(schemaFile))
		.log().all();
	}
	
	
	@Test
	public void schemaCheckRmg() {
		
		File schemaFile=new File(".\\src\\test\\resources\\schemaChekReqres.json");
		
		when().get("http://rmgtestingserver:8084/projects/TY_PROJ_697")
		.then().log().all()
		.body(JsonSchemaValidator.matchesJsonSchema(schemaFile))
		.log().all();
	}
		
		
		
		
	

}
