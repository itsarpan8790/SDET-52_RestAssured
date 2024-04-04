package mock;

import static io.restassured.RestAssured.when;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class PublicapisValidation {
	@Test
	public void fetchAPI() {

		Response resp = when().get("https://api.publicapis.org/entries");
		List<String> allApi = resp.jsonPath().get("entries.API");
		//List<String> allApi = resp.jsonPath().get("entries.Description");
		for (String api : allApi) {
			System.out.println(api);
		}

		String ApiAt1st = resp.jsonPath().get("entries[0].API");
		System.out.println(ApiAt1st);
	}

}
