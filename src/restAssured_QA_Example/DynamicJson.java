package restAssured_QA_Example;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.PayLoads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	@Test(dataProvider = "BookData")
	public void addBook(String isbn,String aisle) 
	{
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().header("Content-Type","application/json")
		                .body(PayLoads.addBook(isbn,aisle))
		                 .when().post("/Library/Addbook.php")
		                  .then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath jp = new JsonPath(response);
		String id = jp.get("ID");
		System.out.println(id);
	}
   
	
	@DataProvider(name = "BookData")
	public Object[][] getData() 
	{
		return new Object[][] {{"gsaj","16546"},{"slfsh","15646"},{"bjmzsj","552"}};
	}
}
