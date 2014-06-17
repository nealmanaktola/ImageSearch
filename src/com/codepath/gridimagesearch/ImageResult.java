package com.codepath.gridimagesearch;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageResult {
	private String fullUrl;
	private String thumbUrl;
	
	public ImageResult(JSONObject json)
	{
		try {
			this.fullUrl = json.getString("url");
			this.thumbUrl = json.getString("tbUrl");
			System.out.println("Thumb:" + this.fullUrl);
			System.out.println("Full:" + this.thumbUrl);
		} catch (JSONException e) {
			this.fullUrl = null;
			this.thumbUrl = null;
		}
		
		

	}
	public String getFullUrl() {
		return fullUrl;
	}
	
	public String toString() {
		return this.thumbUrl;
	}

	public String getThumbUrl() {
		return thumbUrl;
	}
	public static ArrayList<ImageResult> fromJSONArray(
			JSONArray array) {
		ArrayList<ImageResult> results = new ArrayList<ImageResult>();
		for (int x=0; x < array.length(); x++) {
			try {
				results.add(new ImageResult(array.getJSONObject(x)));
			} catch (JSONException e) {
				e.printStackTrace();			
			}
		}
		return results;
	}
	
}
