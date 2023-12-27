package practise;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class LearnCookies {

	// Sending Cocckie
	@Test
	public void sendCookies() {
		given().cookies("1P_JAR", "2023-12-26-09", "AEC", "Ackid1RCnj_nTXOlesE5aft7RG1tOApHymmxbmLBEP_a3aKGfNWn-orHOkM")
				.when().get("https://www.google.com").then().log().all();

	}

	// Getting Cookies
	@Test
	public void getCookies() {
		Response resp = when().get("https://www.google.com");

		// String singleCokie = resp.getCookie("1P_JAR");
		// System.out.println(singleCokie);

		Map<String, String> allCookie = resp.getCookies();

//	for( String cookieKey:allCookie.keySet()) {
//		String cookieVal=allCookie.get(cookieKey);
//		System.out.println(cookieKey+"-"+cookieVal);
//	}

//		for (Entry<String, String> entry : allCookie.entrySet()) {
//
//			System.out.println(entry.getKey() + "=" + entry.getValue());
//      }

		for (String cookieKey : allCookie.keySet()) {
			String cookieVal = resp.getCookie(cookieKey);
			System.out.println(cookieKey + "=>" + cookieVal);
		}

	}

	// Validating Cookies
	@Test
	public void validateCookies() {
		when().get("https://www.google.com")
		.then().cookie("1P_JAR")
		.cookie("AEC")
		.cookie("NID").log().all();

	}

}
