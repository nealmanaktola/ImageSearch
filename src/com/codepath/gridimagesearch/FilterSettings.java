package com.codepath.gridimagesearch;

import java.io.Serializable;

public class FilterSettings implements Serializable {
	private static final long serialVersionUID = 5219123404941467165L;
	private String imageSize;
	private String imageColour;
	private String imageType;
	private String imageSiteFilter;
	
	public FilterSettings(String imageSize, String imageColour, 
							String imageType, String imageSiteFilter) {
		this.imageSize = imageSize;
		this.imageColour = imageColour;
		this.imageType = imageType;
		this.imageSiteFilter = imageSiteFilter;
	}
	public FilterSettings() {
		this.imageSize = "";
		this.imageColour = "";
		this.imageType = "";
		this.imageSiteFilter = "";
		}
	public String getImageSize() {
		return imageSize;
	}
	public void setImageSize(String imageSize) {
		this.imageSize = imageSize;
	}
	public String getImageColour() {
		return imageColour;
	}
	public void setImageColour(String imageColour) {
		this.imageColour = imageColour;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	public String getImageSiteFilter() {
		return imageSiteFilter;
	}
	public void setImageSiteFilter(String imageSiteFilter) {
		this.imageSiteFilter = imageSiteFilter;
	}
	

}
