package com.elearning.bean;

import java.sql.Blob;

public class CourseBean extends BaseBean {

	private long userId;
	private String courseName;
	private String courseDescription;
	private Blob uploadMaterial;
	private Blob uploadVideo;
	

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public Blob getUploadMaterial() {
		return uploadMaterial;
	}

	public void setUploadMaterial(Blob uploadMaterial) {
		this.uploadMaterial = uploadMaterial;
	}

	public Blob getUploadVideo() {
		return uploadVideo;
	}

	public void setUploadVideo(Blob uploadVideo) {
		this.uploadVideo = uploadVideo;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
