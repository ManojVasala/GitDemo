package TestFramework;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

//https://api.twitter.com/1.1/statuses/home_timeline.json
public class Bascic9_TwitterAPI {
	
	String consumerKey="a6LBiqRLKaZw5WzgreUjn1TZ4";
	String consumerSecret="BNNOncfUGfRuuaz5jdpxoOH8rIQPB4IwWGaLDRit0zt0unzKEk";
	String token="1856425615-rBgszVxORhAAJrUcDXUQBQ85n3A8oyzlwAqy46W";
	String tokenSecret="Tu1b9DyNhftK3iaLjtfyfo8ps42zT3XGgpHjL2Mw3efXt";
	String tweetid;
	@Test(priority=0)
	public void getLatestTweet()
	{
		
		RestAssured.baseURI="https://api.twitter.com/1.1/statuses";
		Response res=given().auth().oauth(consumerKey, consumerSecret, token, tokenSecret).queryParam("count", 1)
		.when().get("/home_timeline.json")
		.then().extract().response();
		String response=res.asString();
		System.out.println("res1 :"+response);
		JsonPath js=new JsonPath(response);
		//String txt=js.get("text");
		//System.out.println(txt);
		System.out.println(js.get("text"));
		System.out.println(js.get("id"));
		
	}
	@Test(priority=1)
	public void createTweet()
	{
		RestAssured.baseURI="https://api.twitter.com/1.1/statuses";
		Response res=given().auth().oauth(consumerKey, consumerSecret, token, tokenSecret).queryParam("count", 1)
				.header("Content-Type", "application/json")
				.queryParam("status", "I am tweeting from RestAssured4")	
				.when().post("/update.json")
				.then().extract().response();
		String response=res.asString();
		System.out.println("res1 :"+response);
		JsonPath js=new JsonPath(response);
		//String txt=js.get("text");
		//System.out.println(txt);
		System.out.println("Below is the tweet added");
		System.out.println(js.get("text"));
		 tweetid=js.getString("id");
		System.out.println(tweetid);
	}
	
	@Test(priority=3)
	public void deleteTweet() throws InterruptedException
	{
		Thread.sleep(10000);
		RestAssured.baseURI="https://api.twitter.com/1.1/statuses";
		Response res=given().auth().oauth(consumerKey, consumerSecret, token, tokenSecret).
				when().post("/destroy/"+tweetid+".json")
				.then().extract().response();
		System.out.println("Tweet which got deleted with automation is below");
		String response=res.asString();
		JsonPath js=new JsonPath(response);
		System.out.println(js.get("text"));
		System.out.println(js.get("truncated"));
	}

}
