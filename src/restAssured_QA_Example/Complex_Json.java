package restAssured_QA_Example;

import files.PayLoads;
import io.restassured.path.json.JsonPath;

public class Complex_Json {

	public static void main(String[] args) {
		
		JsonPath js = new JsonPath(PayLoads.rawToJson());
		
		//1. Print No of courses returned by API
		int course_count = js.getInt("courses.size()");
		System.out.println(course_count);
		
		//2.Print Purchase Amount
		int purch_amount = js.getInt("dashboard.purchaseAmount");
		System.out.println(purch_amount);
		
		//3. Print Title of the first course(we use get() method or getString() method)
		String first_course_title = js.get("courses[2].title");
		System.out.println(first_course_title);
		
		//4. Print All course titles and their respective Prices
		for(int i=0;i<course_count;i++) 
		{
			String All_course_title = js.get("courses["+i+"].title");
			System.out.println("title:"+All_course_title);
			System.out.println("price:" +js.get("courses["+i+"].price").toString());
		}
		//5. Print no of copies sold by RPA Course
		for(int i=0;i<course_count;i++) 
		{
			String All_course_title = js.get("courses["+i+"].title");
			if(All_course_title.equalsIgnoreCase("RPA")) 
			{
				int price = js.get("courses["+i+"].price");
				System.out.println("Price of RPA title is:"+price);
				break;
			}
			
		}
		//6. Verify if Sum of all Course prices matches with Purchase Amount


	}

}
