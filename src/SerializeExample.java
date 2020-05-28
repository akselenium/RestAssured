import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.AddPlace;
import pojo.Location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SerializeExample {

	public static void main(String[] args) {
		
	   RestAssured.baseURI = "https://rahulshettyacademy.com";
	   
	   AddPlace ap = new AddPlace();
	   
	   ap.setAccuracy(50);
	   ap.setName("Frontline houseerw");
	   ap.setPhone_number("(+91) 983 893 3937");
	   ap.setAddress("29, side layout, cohen 09");
	   ap.setWebsite("https://rahulshettyacademy.com");
	   ap.setLanguage("French-IN");
	   List<String> lists = new ArrayList<String>();
	   lists.add("shoe park");
	   lists.add("shop");
	   ap.setTypes(lists);
	   
	   Location loc = new Location();
	   loc.setLat(-38.383494);
	   loc.setLng(33.427362);
	   
	   ap.setLocation(loc);
	   
	   
        String response =given().queryParam("key ","qaclick123").body(ap)
                          .when().post("/maps/api/place/add/json")
                             .then().assertThat().statusCode(200).extract().response().asString();
     
       System.out.println(response);
	}

}
