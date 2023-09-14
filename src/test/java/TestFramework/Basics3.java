package TestFramework;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;









import googlAPIs.Payload;
import googlAPIs.Resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;







import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;*/
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;






public class Basics3 {
	
	//create a place=response(place_id)
			//and delete place=request(place_id)
	//public static Logger log=LogManager.getLogger(Basics3.class.getName());
	public static Logger log=LogManager.getLogger(Basics3.class.getName());
	Properties prop;
	@BeforeTest
	public void getData() throws IOException
	{
		 prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\env.properties");
		prop.load(fis);
		prop.getProperty("HOST");
	}
    @Test
	public void AddandDeletePlace()
    {
    	
    	
    	//Task1- grab the response
    	log.info("Host Information :"+prop.getProperty("HOST"));
    	RestAssured.baseURI=prop.getProperty("HOST");
		Response res=given().
				queryParam("key", prop.getProperty("KEY")).
				body(Payload.getPostData()).
	when().
	post(Resources.placePostData()).
	then().assertThat().statusCode(200).and().contentType(ContentType.JSON).
	and().body("status", equalTo("OK")).
	extract().response();
		String responseString=res.asString();
		//when you grab the response, normally it will come in "raw",now we are converting raw into string
		log.info(responseString);
		//Task2-grab the place_id from the response
		//to traverse in the response, the response should be in the json format
		//but we have response in string, so that we need to convert into json
		JsonPath js=new JsonPath(responseString);
		String place_id=js.get("place_id");
		log.info("place_id :"+place_id);
		/*log.error(place_id);
		log.info("INFO : "+place_id);
		log.debug("Debug :"+place_id);
		log.fatal("Fatal : "+place_id);*/
		//delete request url-https://maps.googleapis.com/maps/api/place/delete/json?key=AIzaSyBgGwuwrv5OtIFvJGM2ARNLeV3h0WF_fns
		given().
				queryParam("key","AIzaSyBgGwuwrv5OtIFvJGM2ARNLeV3h0WF_fns").
				body("{"+
						"\"place_id\": \""+place_id+"\""+
						"}").
					when().
					post("maps/api/place/delete/json").
					then().
					assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
					body("status",equalTo("OK"));
		
		//Task3-place this place_id in the delete request
    }
}
