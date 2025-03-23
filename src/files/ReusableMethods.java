package files;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReusableMethods {

	// Converts the raw response into a JsonPath object for easy access to the response data
    public static JsonPath rawToJson(Response resp) {
        // Extract the body of the response as a String, then convert it to a JsonPath object
        String responseString = resp.getBody().asString();
        return new JsonPath(responseString);
    }
	public static JsonPath rawToJson(String response) {
        return new JsonPath(response);  // Converts the response string into JsonPath object
        }


	

	

	
	

	

	 

	

}
