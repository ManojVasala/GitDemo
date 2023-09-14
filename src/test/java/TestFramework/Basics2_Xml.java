package TestFramework;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import googlAPIs.Resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;




import TestFramework.ReusableMethods;
import static org.hamcrest.Matchers.equalTo;

//add place
public class Basics2_Xml {
	
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
	public void postDataXML() throws IOException
	{
		String postData=GenerateStringFromResource("C:\\Manoj\\postdata.xml");
		RestAssured.baseURI=prop.getProperty("HOST");
		Response res=given().
				queryParam("key", prop.getProperty("KEY")).
				body(postData	).
	when().
	post(Resources.placePostDataXml()).
	then().assertThat().statusCode(200).and().contentType(ContentType.XML).extract().response();
		
		
		XmlPath x=ReusableMethods.rawToXml(res);
		String place_id=x.get("PlaceAddResponse.place_id");
		System.out.println("Place_id :"+place_id);
	
	
	}
	public static String GenerateStringFromResource(String path) throws IOException
	{
		return new String(Files.readAllBytes(Paths.get(path)));
	}
}
