package googlAPIs;

public class Payload {

	//You can call any method from class to your test case by giving classname.methodname if the method name is defined as static
	public static String getPostData()
	{
		String b="{"+
   			 "\"location\": {"+
   			    "\"lat\": -33.8669710,"+
   			    "\"lng\": 151.1958750"+
   			  "},"+
   			  "\"accuracy\": 50,"+
   			  "\"name\": \"Google Shoes!\","+
   			  "\"phone_number\": \"(02) 9374 4000\","+
   			  "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+
   			  "\"types\": [\"shoe_store\"],"+
   			  "\"website\": \"http://www.google.com.au/\","+
   			  "\"language\": \"en-AU\""+
   				"}";
		return b;
	}
	
	public static String JIRA_updateCommentbody(String comment)
	{
		String b="{"+
			      "\"body\": \""+comment+"\","+
			      "\"visibility\": {"+
			        "\"type\": \"role\","+
			        "\"value\": \"Administrators\""+
			      "}"+
			    "}";
		return b;
	}
}
