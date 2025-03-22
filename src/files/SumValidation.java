package files;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	@Test
	public void SumOfCourses() {
		
		JsonPath js = new JsonPath(payload.CoursePrice());
	      int sum=0;
	       int count = js.getInt("courses.size()");
	       for(int i=0;i<count;i++) {
	    	   int price = js.get("courses["+i+"].price");
	    	   int copies = js.get("courses["+i+"].copies");
	    	   int amount = price*copies;
	    	   
	    	   sum+=amount;
	    	  
	       }
	       
	       System.out.println(sum);
	       
	       int purchaseAmount  = js.getInt("dashboard.purchaseAmount");
	       Assert.assertEquals(sum, purchaseAmount);
	       
	}

}
