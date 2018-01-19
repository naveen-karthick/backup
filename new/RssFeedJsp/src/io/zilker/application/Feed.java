package io.zilker.application;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
public class Feed {
	String url=null;
	JSONArray array=null;
	public Feed(String url) {
		super();
		this.url = url;
	}

	public  JSONArray fetchFeed() {
		String s=null;
		String s1;
		
		
		try {
			URL u=new URL(this.url);
			BufferedReader  reader=new BufferedReader(new InputStreamReader(u.openStream()));
			while((s1=reader.readLine())!=null)
			{
				s+=s1;
			}
			
			JSONObject xmlObject = XML.toJSONObject(s);
			JSONObject section =(JSONObject)  xmlObject.get("rss");
			JSONObject channel=(JSONObject) section.get("channel");
			JSONArray feedArray=(JSONArray) channel.get("item");
			this.array=feedArray;
		//	JSONObject sampleObject=(JSONObject) feedArray.get(4);
			return feedArray;
		}
		catch(Exception e ) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public String fetchImage(int i) {
		try {
			JSONArray feedArray=this.array;
			JSONObject object =(JSONObject) feedArray.get(i);
		//	System.out.println(object.get("description").toString());
			int startIndex=object.get("description").toString().indexOf("src");
			int endIndex=object.get("description").toString().indexOf("\" border");
			if(startIndex<endIndex) {
			return object.get("description").toString().substring(startIndex+5,endIndex);
			}
			else {
				return "false";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		return null;
	}
	public String fetchTitle(int i) {
		
		
		try {
			JSONArray feedArray=this.array;
			JSONObject object =(JSONObject) feedArray.get(i);
			return object.get("description").toString();		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		return null;
	}
	
	public String fetchLink(int i) {
		try {
			JSONArray feedArray=this.array;
			JSONObject object =(JSONObject) feedArray.get(i);
			
			return object.get("link").toString();		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		
		return null;
		
	}
	public static void main(String[] args) {
	
	
		
		
	}
} 
