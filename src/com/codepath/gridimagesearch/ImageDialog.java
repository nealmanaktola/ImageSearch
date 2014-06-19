package com.codepath.gridimagesearch;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.codepath.gridimagesearch.R;
import com.loopj.android.image.SmartImageView;

public class ImageDialog extends DialogFragment {


	public ImageDialog() {
		// Empty constructor required for DialogFragment
	}
	
	public static ImageDialog newInstance(String url) {
		ImageDialog frag = new ImageDialog();
		Bundle args = new Bundle();
		
		args.putString("url", url);
		frag.setArguments(args);
		return frag;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_image_display, container);
		SmartImageView ivResult = (SmartImageView) view.findViewById(R.id.ivResult);
		String url = getArguments().getString("url", "");
		// Show soft keyboard automatically
		ivResult.setImageUrl(url);
		
		return view;
	}
}