package TestFramework;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import googlAPIs.Payload;
import googlAPIs.Resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;





import static org.hamcrest.Matchers.equalTo;

//add place
public class Basics2 {
	
	Properties prop;
	@BeforeTest
	public void getData() throws IOException
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\RetailAdmin\\workspace\\DemoPoject\\src\\files\\env.properties");
		prop=new Properties();
		prop.load(fis);
		prop.getProperty("HOST");
	}

	@Test
	public void createPlaceAPI()
	{
		RestAssured.baseURI=prop.getProperty("HOST");
		given().
				queryParam("key", prop.getProperty("KEY")).
				body(Payload.getPostData()).
	when().
	post(Resources.placePostData()).
	then().assertThat().statusCode(200).and().contentType(ContentType.JSON).
	and().body("status", equalTo("OK"));
		//create a place=response(place_id)
		//and delete place=request(place_id)
		
	
	
	}
}
