package files;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import Pojo.Api;
import Pojo.GetCourse;
import Pojo.WebAutomation;

public class OAuthTest {

	public static void main(String[] args) throws InterruptedException {
 
		
		
		String url ="https://rahulshettyacademy.com/getCourse.php?state=verifytest&code=4%2F0AQSTgQHcf6wcesH6fd_CX3l47DE0zWfVjMvMtHdZZW7dlA-et_ne98gLioXq3yZtHNUsBg&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
		String partialCode=url.split("code=")[1];
		String actualCode=partialCode.split("&scope")[0];
		System.out.println(actualCode);
		
		
		String accessTokenResponse =given().urlEncodingEnabled(false).queryParams("code",actualCode).queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("grant_type","authorization_code")
		.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		System.out.println(accessTokenResponse);
		JsonPath js = new JsonPath(accessTokenResponse);
		String accessToken = js.get("access_token");
		
		String r2=    given().contentType("application/json").

				queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON)

				.when()

				           .get("https://rahulshettyacademy.com/getCourse.php")

				.asString();

				System.out.println(r2);
		
		
		
	}

}
