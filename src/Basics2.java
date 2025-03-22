import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Basics2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(new String(Files.readAllBytes(Paths.get("C:\\Api Testing\\addPlace.json")))).when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope",equalTo("APP")).header("Server",equalTo("Apache/2.4.52 (Ubuntu)"))
		.extract().response().asString();
		
	   System.out.println(response);
	   JsonPath js = new JsonPath(response);
	   String placeId=js.getString("place_id");
	   System.out.println(placeId);
	}

}
