package com.lawencon.bootcamptest.model;

public class QuestionTopic extends BaseModel{
	private String topicName;
	private String topicCode;
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getTopicCode() {
		return topicCode;
	}
	public void setTopicCode(String topicCode) {
		this.topicCode = topicCode;
	}
}
