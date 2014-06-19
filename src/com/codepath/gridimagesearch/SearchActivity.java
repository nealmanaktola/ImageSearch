package com.codepath.gridimagesearch;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class SearchActivity extends FragmentActivity {
	GridView gvResults;
	ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>();
	ImageResultArrayAdapter imageAdapter;
	FilterSettings settings;
	String mquery;
	SearchView searchView;
	
	private final int REQUEST_CODE = 20;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		setupViews();
		clearSettings();
		mquery = "";
		imageAdapter = new ImageResultArrayAdapter(this,imageResults);
		gvResults.setAdapter(imageAdapter);
		gvResults.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

		//		Intent i = new Intent(getApplicationContext(), ImageDisplayActivity.class);
				ImageResult imageResult = imageResults.get(position);
				showImageDialog(imageResult.getFullUrl());
			//	Log.d("image", "thumb: " + imageResult.getThumbUrl());
			//	Log.d("image", "full: " + imageResult.getFullUrl());
			//	i.putExtra("url", imageResult.getFullUrl());
			//	startActivity(i);		
		
			}
			
		});
		gvResults.setOnScrollListener(new EndlessScrollListener() {

			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				// TODO Auto-generated method stub
				Log.d("Debug", "PAge: " + page);
		        customLoadMoreDataFromApi(page); 				
			}
			
		});
	}
	
	private void showImageDialog(String url) {
      FragmentManager fm = getFragmentManager();
      ImageDialog imageDialog = ImageDialog.newInstance(url);
      imageDialog.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
      imageDialog.show(fm, "fragment_image_display");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main,menu);
		MenuItem searchItem = menu.findItem(R.id.action_search);
	    searchView = (SearchView) searchItem.getActionView();
		
	    searchView.setOnQueryTextListener(new OnQueryTextListener() {
	        @Override
	        public boolean onQueryTextSubmit(String query) {
	        	mquery = query;
	    		imageAdapter.clear();
	    		customLoadMoreDataFromApi(0);	
	            return true;
	        }

	        @Override
	        public boolean onQueryTextChange(String newText) {
	            return false;
	        }
	    });
	    return super.onCreateOptionsMenu(menu);
	}
	private void clearSettings()
	{
		settings = new FilterSettings();
	}
	public void onSettingsClick(MenuItem mi) {
		Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
		Intent i = new Intent(this,SettingsActivity.class);
		i.putExtra("mode", 2);
		startActivityForResult(i,REQUEST_CODE);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (resultCode == RESULT_OK && requestCode == REQUEST_CODE)
		{
			settings = (FilterSettings) data.getSerializableExtra("settings");
			Log.d("Debug", "settings:" + settings.getImageColour());
			
		}
	}
	public void setupViews() {
		// TODO Auto-generated method stub
		gvResults = (GridView) findViewById(R.id.gvResults);
				
	}
	
	private String createSettingsString()
	{
		//Get Settings
		String settingsStr = "";
		
		
		String imageSize = settings.getImageSize();
		if (imageSize != "")
		{
			settingsStr = "&imgsz=" + imageSize;
		}
		
		String imageColor = settings.getImageColour();
		if (imageColor != "")
		{
			settingsStr = settingsStr + "&imgcolor=" + imageColor ;
		}
		
		String imageType = settings.getImageType();
		if (imageType != "")
		{
			settingsStr = settingsStr +  "&imgtype=" + imageType;		
		}
		
		String siteFilter = settings.getImageSiteFilter();
		if (siteFilter != "")
		{
			settingsStr = settingsStr + "&as_sitesearch=" + siteFilter;
		}
		
		return settingsStr;
	}
	public void customLoadMoreDataFromApi(int offset) {

	
		//Add Settings to Query
		AsyncHttpClient client = new AsyncHttpClient();
		String settingsStr = createSettingsString();
		client.get("https://ajax.googleapis.com/ajax/services/search/images?rsz=8&" 
				+ "start=" + offset * 8 + settingsStr + "&v=1.0&q=" + Uri.encode(mquery),
			new JsonHttpResponseHandler(){
					public void onSuccess(JSONObject response) {
						JSONArray imageJsonResults = null;
						try {
							imageJsonResults = response.getJSONObject("responseData").getJSONArray("results");
							imageAdapter.addAll(ImageResult.fromJSONArray(imageJsonResults));
							Log.d("DEBUG", imageResults.toString());
						} catch (JSONException e){
							e.printStackTrace();									
						}
					};
				});
	}
	public void onImageSearch(View v) {
		imageAdapter.clear();
		customLoadMoreDataFromApi(0);		
	}
	
}
