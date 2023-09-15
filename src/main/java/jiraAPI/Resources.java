package jiraAPI;

public class Resources {

	public static String createComment()
	{
		String res="/rest/api/2/issue/10036/comment";
		System.out.println("PostJir2");
		System.out.println("PostJira3");
		return res;
		
	}
	
	
}
