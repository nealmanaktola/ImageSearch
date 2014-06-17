package com.codepath.gridimagesearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class SettingsActivity extends Activity {

	Spinner spnSize;
	Spinner spnColor;
	Spinner spnType;
	EditText etSiteFilter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		setupViews();
	}

	private void setupViews() {
		// TODO Auto-generated method stub
		spnSize = (Spinner) findViewById(R.id.spnSize);
		spnColor = (Spinner) findViewById(R.id.spnColor);
		spnType = (Spinner) findViewById(R.id.spnType);
		etSiteFilter = (EditText) findViewById(R.id.etSiteFilter);
	}
	public void onSave(View v)
	{
		String imageSize = spnSize.getSelectedItem().toString().toLowerCase();
		String imageColor = spnColor.getSelectedItem().toString().toLowerCase();
		String imageType = spnType.getSelectedItem().toString().toLowerCase();
		String siteFilter = etSiteFilter.getText().toString();
		
		Log.d("Debug", imageSize + imageColor + imageType + siteFilter);
		
		FilterSettings settings = new FilterSettings(imageSize, imageColor,
														imageType, siteFilter);
		Intent i = new Intent();
		i.putExtra("settings", settings);
		setResult(RESULT_OK, i);
		finish();
		
		
	}
			
	
}
