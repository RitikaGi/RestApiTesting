package files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class BugTest {
	
	public static void main(String[] args) {
		
		RestAssured.baseURI="https://ritikarajput055-1738767871590.atlassian.net/";
		String createIssueResponse =given().header("Content-Type", "application/json").header("Authorization", "Basic cml0aWthcmFqcHV0MDU1QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBGYXYtSHpWcmQ2dGozSExRVUFERzBGc3E0b0lzRlMwVEV3SE16SWlPUUt5RC1pUGJPd2NMbjExUGNhMEY0YnJBLXV6SFY1Q0pkN1lmY3NQd0s2OURsVDRyOFd6cEttalo1LUNRT2Y1STBONkY2YjRjODBQNGh4ZklkRnlycVp1aWg0QlhLeUJNdlBEY0Z4YmMxZElnQmFuUHRMNHc3ZGhmOXlPOVMzZVl4d289Njc4QzE4OUM=")
		.body("{\r\n"
				+ "  \"fields\": {\r\n"
				+ "    \"project\":\r\n"
				+ "    {\r\n"
				+ "        \"key\": \"SCRUM\"\r\n"
				+ "    },\r\n"
				+ "    \"summary\": \"Screens and radio buttons are not working\",\r\n"
				+ "    \"issuetype\": {\r\n"
				+ "        \"name\":\"Bug\"\r\n"
				+ "    }\r\n"
				+ "  }\r\n"
				+ "}").log().all().when().post("rest/api/3/issue").then().log().all().assertThat().statusCode(201).extract().response().asString();	
		
		JsonPath js = new JsonPath(createIssueResponse);
		String issueId=js.get("id");
		System.out.println(issueId);
		
		
		given().pathParam("key", issueId).header("X-Atlassian-Token", "no-check").
		header("Authorization", "Basic cml0aWthcmFqcHV0MDU1QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBGYXYtSHpWcmQ2dGozSExRVUFERzBGc3E0b0lzRlMwVEV3SE16SWlPUUt5RC1pUGJPd2NMbjExUGNhMEY0YnJBLXV6SFY1Q0pkN1lmY3NQd0s2OURsVDRyOFd6cEttalo1LUNRT2Y1STBONkY2YjRjODBQNGh4ZklkRnlycVp1aWg0QlhLeUJNdlBEY0Z4YmMxZElnQmFuUHRMNHc3ZGhmOXlPOVMzZVl4d289Njc4QzE4OUM=")
        .multiPart("file",new File("C:\\Users\\prave\\Downloads\\dress.jpg")).post("rest/api/3/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);
	
	}

}
