package com.elearning.bean;

import java.sql.Blob;

public class TopicBean extends BaseBean {

	private long userId;
	private long courseId;
	private String topicName;
	private String topicDescription;
	private Blob uploadTopicMaterial;
	private Blob uploadTopicVideo;
	
	

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getTopicDescription() {
		return topicDescription;
	}

	public void setTopicDescription(String topicDescription) {
		this.topicDescription = topicDescription;
	}

	public Blob getUploadTopicMaterial() {
		return uploadTopicMaterial;
	}

	public void setUploadTopicMaterial(Blob uploadTopicMaterial) {
		this.uploadTopicMaterial = uploadTopicMaterial;
	}

	public Blob getUploadTopicVideo() {
		return uploadTopicVideo;
	}

	public void setUploadTopicVideo(Blob uploadTopicVideo) {
		this.uploadTopicVideo = uploadTopicVideo;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return String.valueOf(id);
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return topicName;
	}

}
