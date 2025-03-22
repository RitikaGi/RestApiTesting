import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import Pojo.LoginRequest;
import Pojo.LoginResponse;
import Pojo.OrderDetail;
import Pojo.Orders;

public class EcommerceApiTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
         RequestSpecification req =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
         
         LoginRequest loginRequest= new LoginRequest();
         loginRequest.setUserEmail("ritika12@gmail.com");
         loginRequest.setUserPassword("Ritu@123");
        
         RequestSpecification reqLogin=given().spec(req).body(loginRequest);
         LoginResponse loginResponse =reqLogin.when().post("/api/ecom/auth/login").then().extract().response().as(LoginResponse.class);
         System.out.println(loginResponse.getToken());
         String token=loginResponse.getToken();
         System.out.println(loginResponse.getUserId());
         String userId=loginResponse.getUserId();
         System.out.println(loginResponse.getMessage());
         
         
        
        //Add Product
        
        RequestSpecification addProductBaseReq= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("authorization",token).build();
        RequestSpecification addProductReq = given().log().all().spec(addProductBaseReq).param("productName","qwerty").param("productCategory", "fashion")
        		.param("productAddedBy", "67c2c238c019fb1ad6115f12").param("productSubCategory","shirts").param("productPrice", "11500")
        		.param("productDescription", "Addias Originals").param("productFor", "women")
        		.multiPart("productImage", new File("C:\\Users\\prave\\OneDrive\\Pictures\\Screenshots\\Screenshot (177).png"));
        
        String addProductResponse=addProductReq.when().post("api/ecom/product/add-product").then().log().all().extract().response().asString();
        JsonPath js = new JsonPath(addProductResponse);
        String productId= js.get("productId");
        
        
        
        //Create Order
        RequestSpecification createOrderBaseReq= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("authorization",token).setContentType(ContentType.JSON).build();
        OrderDetail orderDetails = new OrderDetail();
        orderDetails.setCountry("India");
        orderDetails.setProductOrderedId(productId);
        
        List<OrderDetail> orderDetailList  = new ArrayList<OrderDetail>();
        orderDetailList.add(orderDetails);
        
        Orders orders = new Orders();
        orders.setOrders(orderDetailList);
        
        RequestSpecification createOrderReq = given().log().all().spec(createOrderBaseReq).body(orders);
        String responseAddOrder= createOrderReq.when().post("api/ecom/order/create-order").then().log().all().extract().response().asString();
        System.out.println(responseAddOrder);
        
        
         //Delete Product             
		
	
        RequestSpecification deleteProdBaseReq=	new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
        		.addHeader("authorization", token).setContentType(ContentType.JSON)
        		.build();

        		RequestSpecification deleteProdReq =given().log().all().spec(deleteProdBaseReq).pathParam("productId",productId);

        		String deleteProductResponse = deleteProdReq.when().delete("/api/ecom/product/delete-product/{productId}").then().log().all().
        		extract().response().asString();

        		JsonPath js1 = new JsonPath(deleteProductResponse);

        		Assert.assertEquals("Product Deleted Successfully",js1.get("message"));
        
        
        
	}
	

}
