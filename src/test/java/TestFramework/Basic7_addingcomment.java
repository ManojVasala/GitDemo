package TestFramework;
import jiraAPI.Resources;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;



import TestFramework.ReusableMethods;

/**
 * 
 */

/**
 * JIRA adding comment to a bug
 *
 */
public class Basic7_addingcomment {

	@Test
	public void addComment()
	{
		RestAssured.baseURI="http://localhost:8082";
		Response res=given().header("Cookie", "JSESSIONID="+ReusableMethods.getSessionKey()).
		header("Content-Type","application/json").
		body("{"+
      "\"body\": \"Comment added from REST API\","+
      "\"visibility\": {"+
        "\"type\": \"role\","+
        "\"value\": \"Administrators\""+
      "}"+
    "}").
    when().post(Resources.createComment()).
    then().statusCode(201).extract().response();
		JsonPath js=ReusableMethods.rawToJson(res);
		String commentid=js.get("id");
		System.out.println("comment Id"+commentid);
		
		//update comment
		RestAssured.baseURI="http://localhost:8082";
		Response res1=given().header("Cookie", "JSESSIONID="+ReusableMethods.getSessionKey()).
		header("Content-Type","application/json").
		body("{"+
      "\"body\": \"Comment updated from RestAssured3\","+
      "\"visibility\": {"+
        "\"type\": \"role\","+
        "\"value\": \"Administrators\""+
      "}"+
    "}").
    when().put("/rest/api/2/issue/10036/comment/"+commentid).
    then().statusCode(200).extract().response();
		JsonPath js1=ReusableMethods.rawToJson(res1);
		String commenupdateid=js1.get("id");
		System.out.println("commentupdateid "+commenupdateid);
		
		
		
	}
}
