package TestFramework;
import static io.restassured.RestAssured.given;
import googlAPIs.Payload;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.testng.annotations.Test;



import TestFramework.ReusableMethods;

/**
 * 
 */

/**
 * @author RetailAdmin
 *for this we need to use existing comment id do update
 */
public class Basics8_UpdateComment {
	
	
	@Test
	public void updateComment()
	{
		
		RestAssured.baseURI="http://localhost:8082";
		Response res1=given().header("Cookie", "JSESSIONID="+ReusableMethods.getSessionKey()).
		header("Content-Type","application/json").
		pathParams("commentid","10102").
		body(Payload.JIRA_updateCommentbody("updating comment from payload method 8thjune4")).
    when().put("/rest/api/2/issue/10100/comment/{commentid}").
    then().statusCode(200).extract().response();
		
	}

}
