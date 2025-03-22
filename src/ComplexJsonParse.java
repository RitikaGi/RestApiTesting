

import org.testng.Assert;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int sum=0;
       JsonPath js= new JsonPath(payload.CoursePrice());
       int count= js.getInt("courses.size()");
       System.out.println(count); 
       int totalAmount= js.getInt("dashboard.purchaseAmount");
       System.out.println(totalAmount);
       String title1=js.get("courses[0].title");
       System.out.println(title1);
       
       for(int i=0;i<count;i++) {
    	  String courseTitles= js.get("courses["+i+"].title");
    	  System.out.println(courseTitles);
    	  System.out.println(js.get("courses["+i+"].price").toString());
       }
       
       System.out.println("Print number of copies sold by RPA course");
       for(int i=0;i<count;i++) {
    	   String courseTitles= js.get("courses["+i+"].title");
    	   if(courseTitles.equalsIgnoreCase("RPA")) {
    		   int copies =js.get("courses["+i+"].copies");
    		   System.out.println(copies);
    	   }
       }
       
       System.out.println("Verify if the Sum of all course price matches with the Purchase Amount");
       
       for(int i=0;i<count;i++) {
    	   String courseTitles= js.get("courses["+i+"].title");
    	   int price =js.get("courses["+i+"].price");
    	   int copies =js.get("courses["+i+"].copies");
    	   int amount =price*copies;
    	   System.out.println("The total amount for "+courseTitles+" course is: ");
    	   System.out.println(amount);
    	   sum=sum+amount;
    	   
       }
       System.out.println("The Total amount for all the courses is: ");
	   System.out.println(sum);
       Assert.assertEquals(totalAmount, sum);
       
	}

}
