package com.lawencon.bootcamptest.model;

public class QuestionOption extends BaseModel{
	private Question question;
	private String optionData;
	private Boolean isAnswer;
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public String getOptionData() {
		return optionData;
	}
	public void setOptionData(String optionData) {
		this.optionData = optionData;
	}
	public Boolean getIsAnswer() {
		return isAnswer;
	}
	public void setIsAnswer(Boolean isAnswer) {
		this.isAnswer = isAnswer;
	}
}
