package TestFramework;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestFramework.ReusableMethods;


public class basics6 {
	
	Properties prop;
	@BeforeTest
	public void getData() throws IOException
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\RetailAdmin\\workspace\\DemoPoject\\src\\files\\env.properties");
		prop=new Properties();
		prop.load(fis);
		prop.getProperty("JIRAHOST");
	}
	
	@Test
	public void JiraAPI()
	{
		//creating session for this we created a method in ReusableMethods class
		//creating issue in JIRA
		RestAssured.baseURI="http://localhost:8082";
		Response res=given().
		header("Content-Type","application/json").
		header("Cookie","JSESSIONID="+ReusableMethods.getSessionKey()).
		body("{"+
    "\"fields\": {"+
       "\"project\":"+
       "{"+
          "\"key\": \"RAA\""+
       "},"+
       "\"summary\": \"Issue 5 for adding comment.\","+
       "\"description\": \"Creating my first bug\","+
       "\"issuetype\": {"+
          "\"name\": \"Bug\""+
       "}"+
   "}"+
"}").
when().
post("/rest/api/2/issue").
then().statusCode(201).extract().response();
JsonPath js=ReusableMethods.rawToJson(res);
String issueid=js.get("id");
System.out.println(issueid);
		
		
		//we need to pass HEADER in given()
	}

}
