package com.codepath.gridimagesearch;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.loopj.android.image.SmartImageView;

public class ImageResultArrayAdapter extends ArrayAdapter<ImageResult> {

    private static class ViewHolder {
    	SmartImageView img;
    }
	public ImageResultArrayAdapter(Context context, List<ImageResult> images) {
		super(context, R.layout.item_image_result, images);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageResult imageInfo = this.getItem(position);

		ViewHolder viewHolder;
		
		if (convertView == null) {
			viewHolder = new ViewHolder();
			LayoutInflater inflator = LayoutInflater.from(getContext());
			convertView = (SmartImageView) inflator.inflate(R.layout.item_image_result,parent,false);
			viewHolder.img = (SmartImageView) convertView.findViewById(R.id.iv_img);
			convertView.setTag(viewHolder);
			
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.img.setImageUrl(imageInfo.getThumbUrl());
		return convertView;
	}

}
