import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.Payload;
import Files.ReUsablemethods;
public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//validate the add place api working as expected
		
		//given - all input details
		//when - submit api
		//then - validate the respose 
		
		
	String response = 	given().baseUri("https://rahulshettyacademy.com").log().all().queryParam("key"	, "qaclick123").body(Payload.addplace()).
		contentType("application/json").
		
		when().post("maps/api/place/add/json").
		
		then().log().all().assertThat().body("scope", equalTo("APP")).header("Connection", "Keep-Alive").extract().response().asString();
		
	System.out.println(response);
	
	JsonPath js = new JsonPath(response);
	String placeid = js.getString("place_id");
	
	System.out.println(placeid);
		
		//update place
	String place = "70 Summer walk, USA";
		
		
		given().baseUri("https://rahulshettyacademy.com").log().all().queryParam("key", "qaclick855").
		
		body("{\r\n"
				+ "\"place_id\":\""+placeid+"\",\r\n"
				+ "\"address\":\""+place+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "").
		when().put("maps/api/place/update/json").
		
		then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//Get plcae
		
		String getplaceresponse = given().baseUri("https://rahulshettyacademy.com").log().all().queryParam("key", "qaclick123").
		
		queryParam("place_id", placeid).
		
		when().get("maps/api/place/get/json").
		
		then().log().all().assertThat().body("address", equalTo(place)).extract().response().asString();
		
		JsonPath js1 = ReUsablemethods.rawToJson(getplaceresponse);
		
		String actual_address =js1.getString("address");
		
		System.out.println(actual_address);
		
		System.out.println(5);
		System.out.println(5);
		System.out.println(5);
		Assert.assertEquals(place,actual_address);
		
		
		
		
	}

}
