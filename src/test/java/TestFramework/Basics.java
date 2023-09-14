package TestFramework;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import googlAPIs.Resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;





public class Basics {

	/**
	 * @param args
	 * first Program usig Rest Assured
	 */
	Properties prop=new Properties();
	@BeforeTest
	public void getData() throws IOException
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\RetailAdmin\\workspace\\DemoPoject\\src\\files\\env.properties");
		prop=new Properties();
		prop.load(fis);
		prop.getProperty("HOST");
	}
	
	@Test
	public void getPlaceAPI() {
		// TODO Auto-generated method stub
		//https://maps.googleapis.com/maps/api/place/nearbysearch/json?key=AIzaSyBgGwuwrv5OtIFvJGM2ARNLeV3h0WF_fns&radius=1500&location=-33.8670522,151.1957362
		RestAssured.baseURI=prop.getProperty("HOST");
		
		given().
				param("location","-33.8670522,151.1957362").
				param("radius","1500").
				param("key",prop.getProperty("KEY")).log().all().
				when().
				get(Resources.placeGetData()).
				then().assertThat().statusCode(200).and().contentType(ContentType.JSON).
				and().body("results[0].name", equalTo("Sydney")).
				and().body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).
				and().header("Server", "scaffolding on HTTPServer2").log().all();
				/*
				 header("abc","def").
				 cookie("fff","ppp").
				 body()
				 */
		
	}

}
