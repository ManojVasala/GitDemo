package TestFramework;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods {

	
	public static XmlPath rawToXml(Response res)
	{
		String res1=res.asString();
		//System.out.println(res1);
		XmlPath x=new XmlPath(res1);
		return x;
		
	}
	public static JsonPath rawToJson(Response res)
	{
		String res1=res.asString();
		//System.out.println(res1);
		JsonPath js=new JsonPath(res1);
		return js;
	}
	public static String getSessionKey()
	{
		RestAssured.baseURI="http://localhost:8082";
		Response res=	given().header("Content-Type","application/json").
				body("{ \"username\": \"reddaiah\", \"password\": \"dgaps1\" }").
				when().
				post("/rest/auth/1/session").
				then().statusCode(200).
				extract().response();
				JsonPath js=ReusableMethods.rawToJson(res);
				String sessionid=js.get("session.value");
				return sessionid;
	}
}
