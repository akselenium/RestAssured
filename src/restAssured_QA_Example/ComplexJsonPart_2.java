package restAssured_QA_Example;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.PayLoads;
import io.restassured.path.json.JsonPath;

public class ComplexJsonPart_2 {
	
	@Test
	public void validation() 
	{
		int sum =0;
		JsonPath js = new JsonPath(PayLoads.rawToJson());
		int course_count = js.getInt("courses.size()");
		for(int i =0;i<course_count;i++) 
		{
			int price = js.getInt("courses["+i+"].price");
			int copies = js.getInt("courses["+i+"].copies");
			int Total_Amt = price*copies;
			sum= sum+Total_Amt;
			
		}
		
		System.out.println("The Total Purchase Amount:"+sum);
		int purchase_Amount = js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchase_Amount);
	}
	
	

}
