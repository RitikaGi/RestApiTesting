package files;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class GraphQLScript {

	public static void main(String[] args) {
              
		      int characterId = 894;
		      String response= given().log().all().header("Content-Type","application/json")
		      .body("{\"query\":\"query($characterId: Int!, $episodeId: Int!)\\n{\\n   character(characterId: $characterId){\\n    name\\n    gender\\n    status\\n  }\\n   location(locationId: 1900){\\n    name\\n    dimension\\n  }\\n   episode(episodeId: $episodeId) {\\n     id\\n    name\\n    air_date\\n    episode\\n   }\\n  characters(filters:{name: \\\"Rahul\\\"})\\n  {\\n    info{\\n      count\\n    }\\n    result{\\n      name\\n      type\\n    }\\n  }\\n  episodes(filters:{ episode:\\\"hulu\\\"})\\n   {\\n  result\\n{\\n    id\\n    name\\n    air_date\\n    episode\\n  }\\n  }\\n}\",\"variables\":{\"characterId\":"+characterId+",\"episodeId\":344}}")
		      .when().post("https://rahulshettyacademy.com/gq/graphql").then().extract().response().asString();
		       System.out.println(response);
		       JsonPath js = new JsonPath(response);
		       String characterName = js.getString("data.character.name");
		       Assert.assertEquals(characterName, "rama");
		       
		       //Mutation
		       String newCharacter="Robeen";
		       String mutation_response= given().log().all().header("Content-Type","application/json")
		 		      .body("{\"query\":\"mutation($locationName:String!,$characterName:String!,$episodeName:String!)\\n{\\n  createLocation(location: {name:$locationName,type:\\\"Southzone\\\",dimension: \\\"344\\\" })\\n{\\nid\\n}\\ncreateCharacter(character:{name:$characterName,type:\\\"Villan\\\", status: \\\"alive\\\", species:\\\"wild\\\",gender: \\\"M\\\", image:\\\"png\\\",originId: 1200, locationId: 15532}){\\n  id\\n}\\ncreateEpisode(episode:{name:$episodeName, air_date:\\\"1990 June\\\", episode: \\\"live\\\"}){\\n  id\\n}\\ndeleteLocations(locationIds:[15540,15541]){\\n  locationsDeleted\\n}\\n}\",\"variables\":{\"locationName\":\"Australia\",\"characterName\":\"Robiiin\",\"episodeName\":\"Second\"}}")
		 		      .when().post("https://rahulshettyacademy.com/gq/graphql").then().extract().response().asString();
		 		       System.out.println(mutation_response);
		 		        
		 		        
		       
		       
			}

	}


