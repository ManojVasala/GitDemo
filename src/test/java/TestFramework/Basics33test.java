package TestFramework;
import org.testng.annotations.Test;



import static io.restassured.RestAssured.given;
import googlAPIs.Payload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

public class Basics33test {
	
	//Add and delete a place using google api
	@Test
	public void addandDelete()
	{
		RestAssured.baseURI="https://maps.googleapis.com";
	Response res=	given().
				queryParam("key","AIzaSyBgGwuwrv5OtIFvJGM2ARNLeV3h0WF_fns").and().
				body(Payload.getPostData()).
	when().
	post("maps/api/place/add/json").
	then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().	
	body("status",equalTo("OK")).
	extract().response();
	String responseString=res.asString();
	JsonPath js=new JsonPath(responseString);
	System.out.println("responseString:"+responseString);
	System.out.println("-------");
	String place_id=js.get("place_id");
	System.out.println("place_id: "+place_id);
	
	//now we need to delete the place_id
	given().
			queryParam("key","AIzaSyBgGwuwrv5OtIFvJGM2ARNLeV3h0WF_fns").and().
			body("{"+
  "\"place_id\": \""+place_id+"\""+
"}").
	when().
		post("maps/api/place/delete/json").
		then().assertThat().statusCode(200).and().
		contentType(ContentType.JSON).and().
		body("status",equalTo("OK"));
	}

}
