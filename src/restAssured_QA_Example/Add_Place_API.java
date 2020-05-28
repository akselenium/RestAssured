package restAssured_QA_Example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.codehaus.groovy.reflection.CachedMethod;
import org.testng.Assert;

import files.PayLoads;


public class Add_Place_API {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		// Validate the ADD Place API will work or not.
		
		//given- All the input details
		//when- Submit the API - Resources, Key values
		//then- For Validate purpose
	   
	/*	given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(PayLoads.AddPlace())
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP"))
		.header("server","Apache/2.4.18 (Ubuntu)");*/
		
		String responce = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		                  .body(PayLoads.AddPlace())
	                    	.when().post("maps/api/place/add/json")
		                      .then().assertThat().statusCode(200).body("scope",equalTo("APP"))
		                         .header("server","Apache/2.4.18 (Ubuntu)").extract().response().asString();
	    System.out.println(responce);
	    JsonPath jp = new JsonPath(responce);
	    String status_name = jp.getString("status");
	    System.out.println("The Value of Status is : " +status_name);
	    String place_id = jp.getString("place_id");
	    System.out.println("The PlaceId Value is: "+place_id);
		
	 // Add place----update place with new address----validate the new address will update or not 
	    
	  //update place 
	    String newAddress = "Summer Valli, Odisha";
	    
	   given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
	    .body("{\r\n" + 
	    		"\"place_id\":\""+place_id+"\",\r\n" + 
	    		"\"address\":\""+newAddress+"\",\r\n" + 
	    		"\"key\":\"qaclick123\"\r\n" + 
	    		"}")
	    .when().put("maps/api/place/update/json")
	    //.then().assertThat().statusCode(404).body("msg",equalTo("Update address operation failed, looks like the data doesn't exists"));
	   
	   .then().assertThat().statusCode(200)
	    .body("msg",equalTo("Address successfully updated"));
      
	   //Get place
	  String getPlaceResponse = given().log().all().queryParam("key","qaclick123")
	   .queryParam("place_id",place_id)
	   .when().get("maps/api/place/get/json")
	   .then().log().all().assertThat().statusCode(200).extract().response().asString();
	  
	  JsonPath jp1 = new JsonPath(getPlaceResponse);
	  String actual_Address = jp1.getString("address");
	  System.out.println(actual_Address);
	  Assert.assertEquals(actual_Address, newAddress);
	   
	   
	}

}
