package GenericUtility;
import io.restassured.response.Response;

/**
 * RestAssuredLibrary
 * Consists methods of Rest Assured
 * @author arpan
 */
public class RestAssuredLibrary {
	
	/**
	 * This method will return json Data from corresponding response body
	 * @author arpan
	 * @param resp
	 * @param path
	 * @return
	 */
	
	public String getJsonData(Response resp,String path) {
		
		String jsonData=resp.jsonPath().get(path);
		return jsonData;
	}

}
